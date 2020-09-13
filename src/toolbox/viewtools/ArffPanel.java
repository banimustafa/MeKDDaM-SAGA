
package toolbox.viewtools;
import global.Global;
import weka.core.Attribute;
import weka.core.Instances;
import weka.core.Undoable;
import weka.core.Utils;
import weka.gui.ComponentHelper;
import weka.gui.JTableHelper;
import weka.gui.ListSelectorDialog;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;
import java.util.Vector;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.TableModelEvent;
import javax.swing.table.TableColumn;
import process_model.basic.util.DateTime;
import process_model.general.Procedure;
import process_model.general.Procedures;
import process_model.phase.delivery.data.AcclimatisedData;
import process_model.process.input.data.utility.SplitType;
import toolbox.Tools;
import toolbox.filetools.FileTools;
import toolbox.datatools.FilteringTools;
import weka.core.Instance;

/**
 * A Panel representing an ARFF-Table and the associated filename.
 *
 *
 * @author FracPete (fracpete at waikato dot ac dot nz)
 * @version $Revision: 6455 $ 
 */

public class ArffPanel extends JPanel
  implements ActionListener, ChangeListener, MouseListener, Undoable {
  
  /** for serialization */
  static final long serialVersionUID = -4697041150989513939L;
  
  /** the name of the tab for currentDataInstances that were set directly */
  public final static String TAB_INSTANCES = "Instances";

  /** the underlying table */
  private ArffTable m_TableArff;
  /** the popup menu for the header row */
  private JPopupMenu m_PopupHeader;
  /** the popup menu for the data rows */
  private JPopupMenu m_PopupRows;
  /** displays the relation name */
  private JLabel m_LabelName;
  /** whether to display the attribute index in the table header. */
  //---------------------------------------------------------------------------
  private boolean m_ShowAttributeIndex;
  private int fileNameCounter=0;
  private AcclimatisedData acclimatisedData=null;

  // menu items
  private JMenuItem             menuItemMean;
  private JMenuItem             menuItemAttributeDistribution;
  private JMenuItem             menuItemAttributeStatistics;
  //---------------------------------------------------------------------------
  private JMenuItem             menuItemCopyAttribute;
  private JMenuItem             menuItemPasteAttribute;
  private JMenuItem             menuItemAddAttribute;
  private JMenuItem             menuItemSetAllValues;
  //---------------------------------------------------------------------------
  private JMenuItem             menuItemDiscretize;
  private JMenuItem             menuItemBinarize;
  private JMenuItem             menuItemNominalize;
  private JMenuItem             menuItemStringize;
  //---------------------------------------------------------------------------
  private JMenuItem             menuItemSetMissingValuesToMean;
  private JMenuItem             menuItemSetMissingValuesToMedian;
  private JMenuItem             menuItemSetMissingValuesToZero;
  private JMenuItem             menuItemSetMissingValues;
  //---------------------------------------------------------------------------
  private JMenuItem             menuItemReplaceValues;
  private JMenuItem             menuItemRenameAttribute;
  private JMenuItem             menuItemAttributeAsClass;
  private JMenuItem             menuItemDeleteAttribute;
  private JMenuItem             menuItemDeleteAttributes;
  
  //--------------------------------------------------------------------------
  private JMenuItem             menuItemSortInstances;
  private JMenuItem             menuItemDeleteSelectedInstance;
  private JMenuItem             menuItemDeleteAllSelectedInstances;
  //--------------------------------------------------------------------------
  private JMenuItem             menuItemSearch;
  private JMenuItem             menuItemClearSearch;
  private JMenuItem             menuItemUndo;
  //--------------------------------------------------------------------------
  private JMenuItem             menuItemCopy;
  private JMenuItem             menuItemPaste;
  //--------------------------------------------------------------------------
  private JMenuItem             menuItemOptimalColWidth;
  private JMenuItem             menuItemOptimalColWidths;
  
  /** the filename used in the title */
  private String m_Filename;
  /** the title prefix */
  private String m_Title;
  /** the currently selected column */
  private int m_CurrentCol;
  //
  private int m_curruntRaw;
  /** isMetaData for whether data got changed */
  private boolean m_Changed;
  /** the listeners that listen for modifications */
  private HashSet m_ChangeListeners;
  /** the string used in the last search */
  private String m_LastSearch;
  /** the string used in the last replace */
  private String m_LastReplace;
  
  /**
   * initializes the panel with no data
   */
  
  public ArffPanel()
  {
    super();    
    initialize();
    createPanel();    
  }

  public ArffPanel(String filename)
  {
    this();
    loadFile(filename);
    refresh_procedures();
  }
/*
  public ArffPanel(Instances data) {
    this();
    m_Filename = "";
    setInstances(data);
    refresh_procedures();
  }
*/
  //&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&
  //&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&
  private void refresh_procedures()
{
  if (Global.isAcclimatization==true)
    {
    Procedures procedures=null;

    File currentfile=new File(this.getFilename());
   // check for old version of the delivery
   //------------------------------------------------------------
    if (Global.currentPhase!=null &&
        Global.currentPhase.getResult()!=null &&
        Global.currentPhase.getResult().getSupplementDeliveries()!=null &&
        Global.currentPhase.getResult().getSupplementDeliveries().getDeliveriesList()!=null &&            
        Global.currentPhase.getResult().getSupplementDeliveries().getDeliveriesList().size()>0 )
      {
         for (Object o:Global.currentPhase.getResult().getSupplementDeliveries().getDeliveriesList())
         {             
             AcclimatisedData thisAcclimatisedData=(AcclimatisedData) o;
             if ( thisAcclimatisedData!=null && thisAcclimatisedData.getOutcomeURL()!=null && 
                  thisAcclimatisedData.getOutcomeURL().toString().toLowerCase().contains(Tools.getFileNameOnly(currentfile).toLowerCase()))
             {
                 if (thisAcclimatisedData.getProcedures()!=null)
                    procedures=thisAcclimatisedData.getProcedures().clone();
             }
         }
      }

   // check for old version of the delivery
   //------------------------------------------------------------

    if (Global.currentProcess!=null &&
        Global.currentProcess.getResults()!=null &&
        Global.currentProcess.getResults().getDataAcclimatisedResult()!=null &&
        Global.currentProcess.getResults().getDataAcclimatisedResult().getSupplementDeliveries()!=null &&
        Global.currentProcess.getResults().getDataAcclimatisedResult().getSupplementDeliveries().getDeliveriesList()!=null &&
        Global.currentProcess.getResults().getDataAcclimatisedResult().getSupplementDeliveries().getDeliveriesList().size()>0 &&
        this.acclimatisedData==null // if it is still null
        )
        {
         for (Object o:Global.currentProcess.getResults().getDataAcclimatisedResult().getSupplementDeliveries().getDeliveriesList())
         {
             AcclimatisedData thisAcclimatisedData=(AcclimatisedData) o;
             if ( thisAcclimatisedData!=null && thisAcclimatisedData.getOutcomeURL()!=null && thisAcclimatisedData.getOutcomeURL().toString().toLowerCase().contains(Tools.getFileNameOnly(currentfile).toLowerCase()))
             {
                 if (thisAcclimatisedData.getProcedures()!=null)
                    procedures=thisAcclimatisedData.getProcedures().clone();
             }
         }
      }

    //-------------------------------------------------------------------------
    if (Global.project!=null &&
        Global.project.getProcess()!=null &&
        Global.project.getProcess().getResults()!=null &&
        Global.project.getProcess().getResults().getDataAcclimatisedResult()!=null &&
        Global.project.getProcess().getResults().getDataAcclimatisedResult().getSupplementDeliveries()!=null &&
        Global.project.getProcess().getResults().getDataAcclimatisedResult().getSupplementDeliveries().getDeliveriesList()!=null &&
        Global.project.getProcess().getResults().getDataAcclimatisedResult().getSupplementDeliveries().getDeliveriesList().size()>0 &&
        this.acclimatisedData==null // if it is still null
        )
        {
         for (Object o:Global.project.getProcess().getResults().getDataAcclimatisedResult().getSupplementDeliveries().getDeliveriesList())
         {
             AcclimatisedData thisDeliveryFile=(AcclimatisedData) o;
             
             File deliveryFile=null;             
             if (thisDeliveryFile!=null && thisDeliveryFile.getOutcomeURL()!=null)
                 deliveryFile=Tools.toFile(thisDeliveryFile.getOutcomeURL());
             
             if ( thisDeliveryFile!=null && deliveryFile!=null && deliveryFile.getName()!=null && deliveryFile.getName().toLowerCase().equals(Tools.getFileNameOnly(currentfile).toLowerCase()))
             {
                 if (thisDeliveryFile.getProcedures()!=null)
                    procedures=thisDeliveryFile.getProcedures().clone();
             }
         }
      }
    //*************************************************************************
     if (this.acclimatisedData==null)
            this.acclimatisedData=new AcclimatisedData();

        if (this.acclimatisedData!=null && this.acclimatisedData.getProcedures()==null)
            acclimatisedData.setProcedures(procedures);

  }
}

/*
private void refresh()
{

    File currentfile=new File(this.getFilename());
   // check for old version of the delivery
   //------------------------------------------------------------
    if (Global.currentPhase!=null &&
        Global.currentPhase.getResult()!=null &&
        Global.currentPhase.getResult().getSupplementDeliveries().getDeliveriesList()!=null &&
        Global.currentPhase.getResult().getSupplementDeliveries().getDeliveriesList().size()>0 )
      {
         for (Object o:Global.currentPhase.getResult().getSupplementDeliveries().getDeliveriesList())
         {
             AcclimatisedData d=(AcclimatisedData) o;
             if (d.getOutcomeURL().toString().toLowerCase().contains(currentfile.getName().toLowerCase()))
             {
                 acclimatisedData=d;
             }
         }
      }

   // check for old version of the delivery
   //------------------------------------------------------------

    if (Global.currentProcess!=null &&
        Global.currentProcess.getResults()!=null &&
        Global.currentProcess.getResults().getAcclimatisedData()!=null &&
        Global.currentProcess.getResults().getAcclimatisedData().getSupplementDeliveries()!=null &&
        Global.currentProcess.getResults().getAcclimatisedData().getSupplementDeliveries().getDeliveriesList()!=null &&
        Global.currentProcess.getResults().getAcclimatisedData().getSupplementDeliveries().getDeliveriesList().size()>0 &&
        this.acclimatisedData==null // if it is still null
        )
        {
         for (Object o:Global.currentProcess.getResults().getAcclimatisedData().getSupplementDeliveries().getDeliveriesList())
         {
             AcclimatisedData d=(AcclimatisedData) o;
             if (d.getOutcomeURL().toString().toLowerCase().contains(currentfile.getName().toLowerCase()))
             {
                acclimatisedData=d;
             }
         }
      }

    // load acclimatized data
   //-------------------------------------------------------------------------
   if (Global.currentPhase!=null && Global.currentPhase.getResult()!=null &&
       Global.currentPhase.getResult().getMainDelivery()!=null &&
       this.acclimatisedData==null) // if it is still null
    {
        acclimatisedData=(AcclimatisedData) Global.currentPhase.getResult().getMainDelivery();
    }

    // load acclimatized data
   //-------------------------------------------------------------------------
       if (Global.currentProcess!=null &&
        Global.currentProcess.getResults()!=null &&
        Global.currentProcess.getResults().getAcclimatisedData()!=null &&
        Global.currentProcess.getResults().getAcclimatisedData().getMainDelivery()!=null &&
        this.acclimatisedData==null) // if it is still null
    {
        acclimatisedData=(AcclimatisedData) Global.currentProcess.getResults().getAcclimatisedData().getMainDelivery();
    }

}
*/





//*****************************************************************************
    public AcclimatisedData getAcclimatisedData() {
        return acclimatisedData;
    }

    public void setAcclimatisedData(AcclimatisedData acclimatisedData) {
        this.acclimatisedData = acclimatisedData;
    }
  

//----------------------------------------------------------------------------
//****************************************************************************
//                              Discretize
//****************************************************************************
//----------------------------------------------------------------------------
  public void discretize()
    {
        int currentAttributeIndex=m_CurrentCol-1;

        //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        Instances currentInstances=this.getInstances();
        currentInstances.setClassIndex(this.getInstances().numAttributes()-1);
        String currentFile=this.getFilename();        
        
        //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++        
        if (currentInstances!=null)
        {
            Instances acclimatizedInstances=FilteringTools.discretize(currentInstances, currentAttributeIndex);
            this.setInstances(acclimatizedInstances);            
            this.setFilename(currentFile);            
        }
        else
            System.out.println(" instance is null");
        
        //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        m_CurrentCol=currentAttributeIndex+1;
        if (currentAttributeIndex> -1 )
            m_TableArff.setSelectedColumn(m_CurrentCol);

        //@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
        logProcedure("Transform Attribute "+
                                         this.getInstances().attribute(currentAttributeIndex).name() +
                                         " from Numeric to Discret Values");
    }


  //---------------------------------------------------------------------------
  //***************************************************************************
  //                        replace Missing values with median
  //***************************************************************************
  //---------------------------------------------------------------------------
  private void appendValues()
  {    
    ArffSortedTableModel      model;
    model = (ArffSortedTableModel) m_TableArff.getModel();
    model.setNotificationEnabled(false);    

    // undo
    //-------------------------------------------------------------------------
    addUndoPoint();
    model.setUndoEnabled(false);

    // set value
    //-------------------------------------------------------------------------
    for (int i = 0; i < m_TableArff.getRowCount(); i++)
    {
         String newValue=(String) model.getValueAt(i, m_CurrentCol)+"_nominal"; 
         model.setValueAt(newValue, i, m_CurrentCol);
    }

    //-------------------------------------------------------------------------
    model.setUndoEnabled(true);
    model.setNotificationEnabled(true);
    model.notifyListener(new TableModelEvent(model, 0, model.getRowCount(), m_CurrentCol, TableModelEvent.UPDATE));

    // refresh
    //-------------------------------------------------------------------------
    m_TableArff.repaint();

    logProcedure("Attribute "+this.getInstances().attribute(m_CurrentCol-1).name() +" missing values was replaced with Zero");

  }
/*
//----------------------------------------------------------------------------
//****************************************************************************
//                              Normalize
//****************************************************************************
//----------------------------------------------------------------------------
  public void nominalToString()
    {
        int currentAttributeIndex=m_CurrentCol-1;

        //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        Instances currentInstances=this.getInstances();
        currentInstances.setClassIndex(this.getInstances().numAttributes()-1);        
        String currentFile=this.getFilename();

        //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++        
        if (currentInstances!=null)
        {
            Instances acclimatizedInstances=FilteringTools.NominalToString(currentInstances, currentAttributeIndex);
            this.setInstances(acclimatizedInstances);            
            this.setFilename(currentFile);            
            
        }
        else
            System.out.println(" instance is null");

        //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        m_CurrentCol=currentAttributeIndex+1;
        if (currentAttributeIndex> -1 )
            m_TableArff.setSelectedColumn(m_CurrentCol);

        //@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
logProcedure("Transform Attribute "+
                                         this.getInstances().attribute(currentAttributeIndex).name() +
                                         " from Numeric to Nominal Values");

  }
  
*/
//----------------------------------------------------------------------------
//****************************************************************************
//                              Normalize
//****************************************************************************
//----------------------------------------------------------------------------
  public void nominalize()
    {
        int currentAttributeIndex=m_CurrentCol-1;

        //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        Instances currentInstances=this.getInstances();
        currentInstances.setClassIndex(this.getInstances().numAttributes()-1);        
        String currentFile=this.getFilename();

        //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++        
        if (currentInstances!=null)
        {
            Instances acclimatizedInstances=FilteringTools.numericToNominal(currentInstances, currentAttributeIndex);
            this.setInstances(acclimatizedInstances);            
            this.setFilename(currentFile);            
            
        }
        else
            System.out.println(" instance is null");

        //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        m_CurrentCol=currentAttributeIndex+1;
        if (currentAttributeIndex> -1 )
            m_TableArff.setSelectedColumn(m_CurrentCol);

        //@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
logProcedure("Transformed Attribute "+
                                         this.getInstances().attribute(currentAttributeIndex).name() +
                                         " from Numeric to Nominal Values");

  }

//----------------------------------------------------------------------------
//****************************************************************************
//                              Binarize
//****************************************************************************
//----------------------------------------------------------------------------
    public void binarize()
    {
    int currentAttributeIndex=m_CurrentCol-1;

        //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        Instances currentInstances=this.getInstances();
        currentInstances.setClassIndex(this.getInstances().numAttributes()-1);        
        String currentFile=this.getFilename();

        //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        
        if (currentInstances!=null)
        {
            Instances acclimatizedInstances=FilteringTools.binarize(currentInstances, currentAttributeIndex);
            this.setInstances(acclimatizedInstances);            
            this.setFilename(currentFile);
        }
        else
            System.out.println(" instance is null");

        //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        m_CurrentCol=currentAttributeIndex+1;
        if (currentAttributeIndex> -1 )
            m_TableArff.setSelectedColumn(m_CurrentCol);


        //@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
logProcedure("Transformed Attribute "+
                                         this.getInstances().attribute(currentAttributeIndex).name() +
                                         " from Numeric to Binary Nominal Values");

    }

    
    
//-----------------------------------------------------------------------------
//-----------------------------------------------------------------------------
//                              Add Attribute
//-----------------------------------------------------------------------------
//-----------------------------------------------------------------------------

   public void stringize()
    {                    
    String name = ComponentHelper.showInputBox(getParent(), "Add Attribute...", "Enter new Attribute name", "new_name");

    if (attributeAlreadyExists(name))
       name= name+(++fileNameCounter);

    // get current model which the tabe is based on
    //-------------------------------------------------------------------------
    ArffSortedTableModel model = (ArffSortedTableModel) m_TableArff.getModel();
    Instances instances=model.getInstances();

    
     int oldColumnIndex=m_CurrentCol;    
     String oldName=model.getAttributeAt(oldColumnIndex).name();
     ArrayList<String>   oldValues=new ArrayList<String>();     
    for (int i=0;i<instances.numInstances();i++)
    {
        oldValues.add(model.getValueAt(i,oldColumnIndex).toString()+"_value");
    }
    
    
    // set the new model to the table
    //-------------------------------------------------------------------------
    String type = ComponentHelper.showInputBox(getParent(), "Add Attribute...", "Enter new Attribute Type", "string");
    instances=FileTools.addAttribute(name,type,null,instances);
        
    model.setInstances(instances);
    m_TableArff.setModel(model);
    
    // filling the values in the model
    //-------------------------------------------------------------------------    
    TableColumn newColumn = new TableColumn();
    newColumn.setWidth(75);
    newColumn.setHeaderValue(name);
    newColumn.setCellEditor(m_TableArff.getCellEditor());

    // adding the column to the table
    //-------------------------------------------------------------------------
      m_TableArff.addColumn(newColumn);
      this.m_CurrentCol=m_TableArff.getColumnCount()-1;

    // adding the column to the table
    //-------------------------------------------------------------------------
     m_TableArff.setSelectedColumn(m_TableArff.getColumnCount()-1);

     // renameing the column (checking if the name already exists)
    //-------------------------------------------------------------------------
     String newName=oldName+"*";
     if (attributeAlreadyExists(newName))
         newName=newName+(++fileNameCounter);    
    model.renameAttributeAt(m_CurrentCol-1,newName);
    
    // copying the values
    for (int i=0;i<oldValues.size();i++)
    {
        model.setValueAt(oldValues.get(i),i,m_CurrentCol-1);
    }
        
    //model.getInstances().deleteAttributeAt(oldColumnIndex-1);
    model.deleteAttributeAt(oldColumnIndex);

   //@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
logProcedure("Transformed Attribute "+
                                         oldName +
                                         " from Numeric to String");

}
    
//----------------------------------------------------------------------------
//****************************************************************************
//                              Acclimatize
//****************************************************************************
//----------------------------------------------------------------------------
    public void acclimatize(String acclimatization)
    {
        Instances acclimatizedInstances=null;
        String acclimatizationProcedure="";
        ArffSortedTableModel arffTableModel = (ArffSortedTableModel) m_TableArff.getModel();
        Instances currentInstances=arffTableModel.getInstances();
        String acclimatizationResult=null;

        currentInstances.setClassIndex(this.getInstances().numAttributes()-1);
                
        File currentFile=new File(this.getFilename());

        if (acclimatization.toLowerCase().equals("resample"))
        {
            //acclimatizedInstances=currentInstances.resample(new Random(1));
            acclimatizedInstances=FilteringTools.resample(currentInstances);
            acclimatizationProcedure="Resampled the data";
            acclimatizationResult="resampled";
            //System.out.println(acclimatizedInstances.toString());
        }
        else if(acclimatization.toLowerCase().equals("randomize"))
        {
            //currentInstances.randomize(new Random(1));
            //acclimatizedInstances=currentInstances;
            acclimatizedInstances=FilteringTools.randomize(currentInstances);
            acclimatizationProcedure="Randomized the instances of the data";
            acclimatizationResult="randomized";
            //System.out.println(acclimatizedInstances.toString());
        }

        //---------------------------------------------------------------------
        else if(acclimatization.toLowerCase().equals("centre"))
        {
            acclimatizedInstances=FilteringTools.center(currentInstances);
            acclimatizationProcedure="Centred the data";
            acclimatizationResult="centred";
            //System.out.println(acclimatizedInstances.toString());
        }

        else if(acclimatization.toLowerCase().equals("standardize"))
        {
            acclimatizedInstances=FilteringTools.standardize(currentInstances);
            acclimatizationProcedure="Standardized the data";
            acclimatizationResult="standardized";
            //System.out.println(acclimatizedInstances.toString());
        }


        //---------------------------------------------------------------------
        else if(acclimatization.toLowerCase().equals("normalize"))
        {
            acclimatizedInstances=FilteringTools.normalize(currentInstances);
            acclimatizationProcedure="Normalized the data ";
            acclimatizationResult="normalized";
            //System.out.println(acclimatizedInstances.toString());
        }

        //---------------------------------------------------------------------
        else if (acclimatization.toLowerCase().equals("reduce"))
        {
            acclimatizedInstances=FilteringTools.reduceDimensionality(currentInstances);
            acclimatizationProcedure="Reduced Dimentionality of Data";
            acclimatizationResult="reduced";
            //System.out.println(acclimatizedInstances.toString());
        }
        
        //---------------------------------------------------------------------
        else if(acclimatization.toLowerCase().equals("replacemissingvalues"))
        {
            acclimatizedInstances=FilteringTools.replaceMissingValues(currentInstances);
            acclimatizationProcedure="replaced Missing Values in data with Modes or Means";
            acclimatizationResult="missingvaluesreplaced";
            //System.out.println(acclimatizedInstances.toString());
        }        
        //---------------------------------------------------------------------

        this.setInstances(acclimatizedInstances);        
        arffTableModel.setInstances(acclimatizedInstances);
        m_TableArff.setModel(arffTableModel);
        //this.setInstances(acclimatizedInstances);
       
        //********************************************************************
        String fileName=Tools.getFileNameOnly(currentFile);

        //if (fileName.contains("DataSet"))
        //    fileName="AcclimatizedData";

        File newFile=new File(currentFile.getParent()+"/"+fileName+"_"+acclimatizationResult +".csv");

        if (newFile.exists())
            newFile.delete();

        this.setFilename(newFile.getPath());

        //@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
         String procedurePurpose=ComponentHelper.showInputBox(null,
                        "Purpose of Acclimatization Procedure...",
                        "Enter the purpose of the acclimatization Procedure",
                        "No Purpose");
        save_procedure( acclimatizationProcedure+" on "+ new DateTime().getDateTime().toString(), procedurePurpose );

        if (newFile.getPath().toLowerCase().contains("training"))
            save_aclimatisedData(newFile,SplitType.TRAINING);
        else if(newFile.getPath().toLowerCase().contains("testing"))
            save_aclimatisedData(newFile,SplitType.TESTING);
        else if(newFile.getPath().toLowerCase().contains("acclimat"))
            save_aclimatisedData(newFile,SplitType.ALL);
        else
            save_aclimatisedData(newFile,SplitType.OTHER);
        //@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
        setChanged(true);        
    }


    
    

//DDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDD
//                       Delete old Delivery
//DDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDD
private void delete_oldDelivery(File newDeliveryFile)
{

   // check for old version of the delivery
   //------------------------------------------------------------
    if (Global.currentPhase!=null &&
        Global.currentPhase.getResult()!=null &&
        Global.currentPhase.getResult().getSupplementDeliveries()!=null &&
        Global.currentPhase.getResult().getSupplementDeliveries().getDeliveriesList()!=null &&
        Global.currentPhase.getResult().getSupplementDeliveries().getDeliveriesList().size()>0 )
      {
         AcclimatisedData oldDelivery=null;         
         for (Object o:Global.currentPhase.getResult().getSupplementDeliveries().getDeliveriesList())
         {
             AcclimatisedData delivery=(AcclimatisedData) o;
             if (delivery!=null && delivery.getOutcomeURL()!=null && delivery.getOutcomeURL().toString().toLowerCase().contains(newDeliveryFile.getName().toLowerCase()))
             {
                 oldDelivery=delivery;
             }
         }

         if (oldDelivery!=null)
             Global.currentPhase.getResult().getSupplementDeliveries().delete(oldDelivery);
      }

   // check for old version of the delivery
   //------------------------------------------------------------

    if (Global.currentProcess!=null &&
        Global.currentProcess.getResults()!=null &&
        Global.currentProcess.getResults().getDataAcclimatisedResult()!=null &&
        Global.currentProcess.getResults().getDataAcclimatisedResult().getSupplementDeliveries()!=null &&
        Global.currentProcess.getResults().getDataAcclimatisedResult().getSupplementDeliveries().getDeliveriesList()!=null &&
        Global.currentProcess.getResults().getDataAcclimatisedResult().getSupplementDeliveries().getDeliveriesList().size()>0 )
        {
         AcclimatisedData oldDelivery=null;         
         for (Object o:Global.currentProcess.getResults().getDataAcclimatisedResult().getSupplementDeliveries().getDeliveriesList())
         {
             AcclimatisedData delivery=(AcclimatisedData) o;
             if (delivery!=null && delivery.getOutcomeURL()!=null && delivery.getOutcomeURL().toString().toLowerCase().contains(newDeliveryFile.getName().toLowerCase()))
             {
                 oldDelivery=delivery;
             }
         }

         if (oldDelivery!=null)
             Global.currentProcess.getResults().getDataAcclimatisedResult().getSupplementDeliveries().delete(oldDelivery);
      }


}

public void save_aclimatisedData( File newFile, SplitType splitType )
{
     this.delete_oldDelivery(newFile);

        if (this.acclimatisedData==null)
             this.acclimatisedData=new AcclimatisedData();

        if (acclimatisedData!=null && newFile!=null )
        {
            this.acclimatisedData.setOutcomeURL(Tools.toURL(newFile));                              
            this.acclimatisedData.setSplitType(splitType);
        }
        
        this.acclimatisedData.deliverAsMain();
 }
    
    
    
/*    
//DDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDD
//                       Delete old Delivery
//DDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDD
private void delete_oldDelivery(File newDeliveryFile)
{

   // check for old version of the delivery
   //------------------------------------------------------------
    if (Global.currentPhase!=null &&
        Global.currentPhase.getResult()!=null &&
        Global.currentPhase.getResult().getSupplementDeliveries()!=null &&
        Global.currentPhase.getResult().getSupplementDeliveries().getDeliveriesList()!=null &&
        Global.currentPhase.getResult().getSupplementDeliveries().getDeliveriesList().size()>0 )
      {
         AcclimatisedData oldDelivery=null;         
         for (Object o:Global.currentPhase.getResult().getSupplementDeliveries().getDeliveriesList())
         {
             AcclimatisedData delivery=(AcclimatisedData) o;
             if (delivery.getOutcomeURL().toString().toLowerCase().contains(newDeliveryFile.getName().toLowerCase()))
             {
                 oldDelivery=delivery;
             }
         }

         if (oldDelivery!=null)
             Global.currentPhase.getResult().getSupplementDeliveries().delete(oldDelivery);
      }

   // check for old version of the delivery
   //------------------------------------------------------------

    if (Global.currentProcess!=null &&
        Global.currentProcess.getResults()!=null &&
        Global.currentProcess.getResults().getDataAcclimatisedResult()!=null &&
        Global.currentProcess.getResults().getDataAcclimatisedResult().getSupplementDeliveries()!=null &&
        Global.currentProcess.getResults().getDataAcclimatisedResult().getSupplementDeliveries().getDeliveriesList()!=null &&
        Global.currentProcess.getResults().getDataAcclimatisedResult().getSupplementDeliveries().getDeliveriesList().size()>0 )
        {
         AcclimatisedData oldDelivery=null;         
         for (Object o:Global.currentProcess.getResults().getDataAcclimatisedResult().getSupplementDeliveries().getDeliveriesList())
         {
             AcclimatisedData delivery=(AcclimatisedData) o;
             if (delivery.getOutcomeURL().toString().toLowerCase().contains(newDeliveryFile.getName().toLowerCase()))
             {
                 oldDelivery=delivery;
             }
         }

         if (oldDelivery!=null)
             Global.currentProcess.getResults().getDataAcclimatisedResult().getSupplementDeliveries().delete(oldDelivery);
      }


}


public void save_aclimatisedData( File newFile, SplitType splitType )
{
     this.delete_oldDelivery(newFile);

        if (this.acclimatisedData==null)
             this.acclimatisedData=new AcclimatisedData();

        if ( acclimatisedData!=null && newFile!=null )
        {
             try {
                  this.acclimatisedData.setOutcomeURL(new URL("file:/"+newFile.getPath()));
             } catch (MalformedURLException ex) {
                 JOptionPane.showMessageDialog(null, "Wrong URL Format... use file:/ for local files!");
             }
                this.acclimatisedData.setSplitType(splitType);
        }
        this.acclimatisedData.deliverAsMain();
 }

*/


private void save_procedure( String procedureDescription, String procedurePurpose )
{
        Procedure procedure=new Procedure();

        if (this.acclimatisedData==null)
            this.acclimatisedData=new AcclimatisedData();

        if (this.acclimatisedData!=null && this.acclimatisedData.getProcedures()==null)
            acclimatisedData.setProcedures(new Procedures());

        if (procedurePurpose!=null)
            procedure.setPurpose(procedurePurpose);

        if (procedureDescription!=null)
            procedure.setDescription(procedureDescription);

       if (procedure!=null && this.acclimatisedData!=null && this.acclimatisedData.getProcedures()!=null)
            this.acclimatisedData.getProcedures().save(procedure);
    }


//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
//                      Log Acclimatization procedure and save
//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
  public void logProcedure(String changeMessage)
  {
   if (Global.isAcclimatization==true)
   {
    //----------------------------------------------------------------
    String acclimatizationProcedure=changeMessage+" on "+ new DateTime().getDateTime().toString();
    //----------------------------------------------------------------
    String procedurePurpose=ComponentHelper.showInputBox(null,
                    "Purpose of Acclimatization Procedure...",
                    "Enter the purpose of the acclimatization Procedure",
                    "No Purpose");
    //----------------------------------------------------------------
    save_procedure(acclimatizationProcedure, procedurePurpose);
    //----------------------------------------------------------------
    if (getFilename().toLowerCase().contains("training"))
        save_aclimatisedData(new File(getFilename()),SplitType.TRAINING);
    else if(getFilename().toLowerCase().contains("testing"))
        save_aclimatisedData(new File(getFilename()),SplitType.TESTING);
    else if(getFilename().toLowerCase().contains("acclimat"))
        save_aclimatisedData(new File(getFilename()),SplitType.ALL);
    else
        save_aclimatisedData(new File(getFilename()),SplitType.OTHER);
    //----------------------------------------------------------------
    }
   else
   {
       String currentFileString=this.getFilename();
       File currentFile=new File(currentFileString);
       File logFile=new File(currentFile.getParent()+"/"+"DataSetChangesLog.csv");

       String logEntry= new String();
       logEntry=new DateTime().getDateTime().toString()+", "+changeMessage;

       Tools.appendStringToFile(logEntry, logFile);       
   }
    setChanged(true);

  }

  




//-----------------------------------------------------------------------------
//-----------------------------------------------------------------------------
//                              Add Attribute
//-----------------------------------------------------------------------------
//-----------------------------------------------------------------------------

   public void addAttribute()
    {
    String name = ComponentHelper.showInputBox(getParent(), "Add Attribute...", "Enter new Attribute name", "new_name");

    if (attributeAlreadyExists(name))
       name= name+(++fileNameCounter);

    // get current model which the tabe is based on
    //-------------------------------------------------------------------------
    ArffSortedTableModel model = (ArffSortedTableModel) m_TableArff.getModel();
    Instances instances=model.getInstances();

    // set the new model to the table
    //-------------------------------------------------------------------------
    String type = ComponentHelper.showInputBox(getParent(), "Add Attribute...", "Enter new Attribute Type", "string");
    instances=FileTools.addAttribute(name,type,null,instances);
    
    model.setInstances(instances);
    m_TableArff.setModel(model);
    
    // filling the values in the model
    //-------------------------------------------------------------------------    
    TableColumn newColumn = new TableColumn();
    newColumn.setWidth(75);
    newColumn.setHeaderValue(name);
    newColumn.setCellEditor(m_TableArff.getCellEditor());

    // adding the column to the table
    //-------------------------------------------------------------------------
      m_TableArff.addColumn(newColumn);
      this.m_CurrentCol=m_TableArff.getColumnCount()-1;

    // adding the column to the table
    //-------------------------------------------------------------------------
     m_TableArff.setSelectedColumn(m_TableArff.getColumnCount()-1);

     // renameing the column (checking if the name already exists)
    //-------------------------------------------------------------------------
     String newName=name+(++fileNameCounter);

     if (attributeAlreadyExists(newName))
         newName=newName+(++fileNameCounter);
     
    model.renameAttributeAt(m_CurrentCol-1,newName);


        //@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
logProcedure("Attribute "+
                                         this.getInstances().attribute(m_CurrentCol-2).name() +
                                         " was added");

}

   private boolean attributeAlreadyExists(String newName)
    {
       boolean nameAlreadyExists=false;
       for (int i=0;i<m_TableArff.getColumnCount();i++)

       {
        if (newName.equals(m_TableArff.getColumnName(i)))
            nameAlreadyExists=true;
       }
       return nameAlreadyExists;
    }

  //**************************************************************************
  /*
  private void setNewAttributeValues()
  {
    String[]                     valueNew;
    ArffSortedTableModel      model;

    // replacement
      valueNew =(String[]) Tools.stringToArray((String) Global.clipboard[1]);


      if (valueNew == null)
        return;


    model = (ArffSortedTableModel) m_TableArff.getModel();
    model.setNotificationEnabled(false);


    // set value
    for (int i = 0; i < m_TableArff.getRowCount(); i++)
    {
      model.setValueAt(valueNew[i].toString(), i, m_CurrentCol);
    }

    model.setUndoEnabled(true);
    model.setNotificationEnabled(true);
    model.notifyListener(new TableModelEvent(model, 0, model.getRowCount(), m_CurrentCol, TableModelEvent.UPDATE));

    // refresh
    m_TableArff.repaint();
  }

*/

    /**
   * renames the current attribute
   */
  public void renameAttribute()
  {
    ArffSortedTableModel   model;
    String            newName;

    // no column selected?
    if (m_CurrentCol == -1)
      return;

    model   = (ArffSortedTableModel) m_TableArff.getModel();

    // really an attribute column?
    if (model.getAttributeAt(m_CurrentCol) == null)
      return;

    String oldName=this.getInstances().attribute(m_CurrentCol-1).name();

    newName = ComponentHelper.showInputBox(getParent(), "Rename attribute...", "Enter new Attribute name", model.getAttributeAt(m_CurrentCol).name());
    if (newName == null)
      return;

    setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
    model.renameAttributeAt(m_CurrentCol, newName);
    setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));

            //@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
logProcedure("Attribute "+ oldName + " was renamed to "+ newName);
 
  }


  //****************************************************************************
     public void deleteAttribute() {
    ArffSortedTableModel   model;

    // no column selected?
    if (m_CurrentCol == -1)
      return;

    model = (ArffSortedTableModel) m_TableArff.getModel();

    // really an attribute column?
    if (model.getAttributeAt(m_CurrentCol) == null)
      return;

    String deletedAttributeName=this.getInstances().attribute(m_CurrentCol-1).name();



    // prevent deleting the class attribute
    if (m_CurrentCol!=model.getColumnCount()-1)
    {
        // really?
        if (ComponentHelper.showMessageBox(
            getParent(),
            "Confirm...",
            "Do you really want to delete the attribute '"
            + model.getAttributeAt(m_CurrentCol).name() + "'?",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE) != JOptionPane.YES_OPTION)
          return;

          setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
          model.deleteAttributeAt(m_CurrentCol);
      }
    else
        JOptionPane.showMessageDialog(null, "Deletion of class attribute is not allowed\n"
                                          + "Assign another class attribute and try again!\n" );

    
    setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));


            //@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
logProcedure("Attribute "+
                                         deletedAttributeName +
                                         " was deleted");

  }


 //***************************************************************************
public void addAttribute(ArffTable table, Object headerLabel, Object[] values)
{
    ArffTableModel arffTableModel = (ArffTableModel) m_TableArff.getModel();
    TableColumn newColumn = new TableColumn(arffTableModel.getColumnCount());

    // Disable autoCreateColumnsFromModel
    //------------------------------------------------------------------------
    table.setAutoCreateColumnsFromModel(false);

    // Ensure that auto-create is off
    if (table.getAutoCreateColumnsFromModel())
    {
        throw new IllegalStateException();
    }

    //------------------------------------------------------------------------
    newColumn.setHeaderValue(headerLabel);
    table.addColumn(newColumn);
    arffTableModel.addColumn(headerLabel.toString(), values);

    m_TableArff.setModel(arffTableModel);
    ArffSortedTableModel sortedModel= (ArffSortedTableModel) m_TableArff.getModel();
    m_TableArff.setModel(sortedModel);
    //
    setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
    setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));

            //@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
logProcedure("Attribute "+
                                         this.getInstances().attribute(m_CurrentCol-1).name() +
                                         " was added");


}




  //***************************************************************************
  public void addAttribute(String columnName, Object[] values)
  {

    ArffTableModel   model;
    model = (ArffTableModel) m_TableArff.getModel();

    ArffTable table = new ArffTable(model);
    model.addColumn(columnName, values);
    table.setAutoCreateColumnsFromModel(false);

    setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
    setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));

    //@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
logProcedure("Attribute "+
                                     this.getInstances().attribute(m_CurrentCol-1).name() +
                                     " was added");


}




  /**
   * copies the content of the selection to the clipboard
   */
  public void copyContent() {
    StringSelection      selection;
    Clipboard            clipboard;

    selection = getTable().getStringSelection();
    if (selection == null)
      return;

    clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
    clipboard.setContents(selection, selection);
  }



  /**
   * any member variables are initialized here
   */
  protected void initialize() {
    m_Filename           = "";
    m_Title              = "";
    m_CurrentCol         = -1;
    m_LastSearch         = "";
    m_LastReplace        = "";
    m_ShowAttributeIndex = true;
    m_Changed            = false;
    m_ChangeListeners    = new HashSet();
  }
  
  /**
   * creates all the components in the frame
   */
  protected void createPanel() {
    JScrollPane                pane;
    

    setLayout(new BorderLayout());
    
    menuItemMean = new JMenuItem("Get mean...");
    menuItemMean.addActionListener(this);

    menuItemAttributeDistribution = new JMenuItem("Visulaize Attribute Distribution...");
    menuItemAttributeDistribution.addActionListener(this);


    menuItemAttributeStatistics = new JMenuItem("view Attribute Statistics...");
    menuItemAttributeStatistics.addActionListener(this);


    //-------------------------------------------------------------------------
    menuItemAttributeAsClass = new JMenuItem("Attribute as class");
    menuItemAttributeAsClass.addActionListener(this);
    //-------------------------------------------------------------------------
    menuItemDiscretize = new JMenuItem("Convert Numeric To Discret Values");
    menuItemDiscretize.addActionListener(this);

    menuItemBinarize = new JMenuItem("Convert Numeric To Binary Values");
    menuItemBinarize.addActionListener(this);

    menuItemNominalize = new JMenuItem("Convert Numeric To Nominal Values");
    menuItemNominalize.addActionListener(this);    
        
    menuItemStringize = new JMenuItem(" Convert Numeric to String Values");
    menuItemStringize.addActionListener(this);    
    //-------------------------------------------------------------------------
    menuItemAddAttribute = new JMenuItem("Add Attrubute");
    menuItemAddAttribute.addActionListener(this);
    //-------------------------------------------------------------------------
    menuItemCopyAttribute = new JMenuItem("copy Attrubute");
    menuItemCopyAttribute.addActionListener(this);

    menuItemPasteAttribute = new JMenuItem("Paste Attrubute");
    menuItemPasteAttribute.addActionListener(this);
    //-------------------------------------------------------------------------
    menuItemReplaceValues = new JMenuItem("Replace a value with...");
    menuItemReplaceValues.addActionListener(this);

    menuItemSetAllValues = new JMenuItem("Replace all values with...");
    menuItemSetAllValues.addActionListener(this);

    //-------------------------------------------------------------------------
    menuItemSetMissingValuesToMean = new JMenuItem("Set missing values to mean or mode");
    menuItemSetMissingValuesToMean.addActionListener(this);

    menuItemSetMissingValuesToMedian = new JMenuItem("Set missing values to median");
    menuItemSetMissingValuesToMedian.addActionListener(this);

    menuItemSetMissingValuesToZero = new JMenuItem("Set missing values to Zero");
    menuItemSetMissingValuesToZero.addActionListener(this);

    menuItemSetMissingValues = new JMenuItem("Set missing values to...");
    menuItemSetMissingValues.addActionListener(this);

    //-------------------------------------------------------------------------
    menuItemRenameAttribute = new JMenuItem("Rename attribute...");
    menuItemRenameAttribute.addActionListener(this);
    //-------------------------------------------------------------------------
    menuItemDeleteAttribute = new JMenuItem("Delete attribute");
    menuItemDeleteAttribute.addActionListener(this);

    menuItemDeleteAttributes = new JMenuItem("Delete attributes...");
    menuItemDeleteAttributes.addActionListener(this);

    
    //-------------------------------------------------------------------------
    menuItemSortInstances = new JMenuItem("Sort data (ascending)");
    menuItemSortInstances.addActionListener(this);
    //-------------------------------------------------------------------------
    menuItemOptimalColWidth = new JMenuItem("Optimal column width (current)");
    menuItemOptimalColWidth.addActionListener(this);
    menuItemOptimalColWidths = new JMenuItem("Optimal column width (all)");
    menuItemOptimalColWidths.addActionListener(this);

    // row popup
    //*************************************************************************
    menuItemUndo = new JMenuItem("Undo");
    menuItemUndo.addActionListener(this);
    menuItemCopy = new JMenuItem("Copy");
    menuItemCopy.addActionListener(this);

    menuItemPaste = new JMenuItem("Paste");
    menuItemPaste.addActionListener(this);

    menuItemSearch = new JMenuItem("Search");
    menuItemSearch.addActionListener(this);
    menuItemClearSearch = new JMenuItem("Clear Search");
    menuItemClearSearch.addActionListener(this);
    menuItemDeleteSelectedInstance = new JMenuItem("Delete");
    menuItemDeleteSelectedInstance.addActionListener(this);
    menuItemDeleteAllSelectedInstances = new JMenuItem("Delete ALL selected ...");
    menuItemDeleteAllSelectedInstances.addActionListener(this);
    
    // table
    //*************************************************************************
    m_TableArff = new ArffTable();
    m_TableArff.setToolTipText("Right click (or left+alt) for context menu");
    m_TableArff.getTableHeader().addMouseListener(this);
    m_TableArff.getTableHeader().setToolTipText("<html><b>Sort view:</b> left click = ascending / Shift + left click = descending<br><b>Menu:</b> right click (or left+alt)</html>");
    m_TableArff.getTableHeader().setDefaultRenderer(new ArffTableCellRenderer());
    m_TableArff.addChangeListener(this);
    m_TableArff.addMouseListener(this);
    pane = new JScrollPane(m_TableArff);
    add(pane, BorderLayout.CENTER);
    
    // relation name
    m_LabelName   = new JLabel();
    add(m_LabelName, BorderLayout.NORTH);
  }

  /**
   * initializes the popup menus
   */
  private void initPopupMenus() {
    // header popup
    m_PopupHeader  = new JPopupMenu();
    m_PopupHeader.addMouseListener(this);
    m_PopupHeader.add(menuItemMean);
    m_PopupHeader.add(menuItemAttributeStatistics);
    m_PopupHeader.add(menuItemAttributeDistribution);

    if (!isReadOnly()) {

      m_PopupHeader.addSeparator();
      //----------------------------------------------------------------------
      m_PopupHeader.add(menuItemAttributeAsClass);

      m_PopupHeader.addSeparator();
      m_PopupHeader.add(menuItemDiscretize);
      m_PopupHeader.add(menuItemBinarize);
      m_PopupHeader.add(menuItemNominalize);
      m_PopupHeader.add(menuItemStringize);      
      m_PopupHeader.addSeparator();
      //----------------------------------------------------------------------
      m_PopupHeader.add(menuItemAddAttribute);
      m_PopupHeader.addSeparator();
      //----------------------------------------------------------------------
      m_PopupHeader.add(menuItemCopyAttribute);
      m_PopupHeader.add(menuItemPasteAttribute);
      m_PopupHeader.addSeparator();
      //----------------------------------------------------------------------
      m_PopupHeader.add(menuItemSetMissingValuesToMean);
      m_PopupHeader.add(menuItemSetMissingValuesToMedian);
      m_PopupHeader.add(menuItemSetMissingValuesToZero);
      m_PopupHeader.add(menuItemSetMissingValues);
      m_PopupHeader.addSeparator();
      //----------------------------------------------------------------------
      m_PopupHeader.add(menuItemRenameAttribute);      
      m_PopupHeader.add(menuItemDeleteAttribute);
      m_PopupHeader.add(menuItemDeleteAttributes);
      m_PopupHeader.addSeparator();
      //----------------------------------------------------------------------
      m_PopupHeader.add(menuItemSetAllValues);      
      m_PopupHeader.add(menuItemReplaceValues);
      m_PopupHeader.addSeparator();
      //----------------------------------------------------------------------
      m_PopupHeader.add(menuItemSortInstances);
    }

    m_PopupHeader.addSeparator();
    //----------------------------------------------------------------------
    m_PopupHeader.add(menuItemOptimalColWidth);
    m_PopupHeader.add(menuItemOptimalColWidths);





    // row popup
    //*************************************************************************
    m_PopupRows = new JPopupMenu();
    m_PopupRows.addMouseListener(this);
    if (!isReadOnly()) {
      m_PopupRows.add(menuItemUndo);
      m_PopupRows.addSeparator();
    }
    m_PopupRows.add(menuItemCopy);
    m_PopupRows.add(menuItemPaste);
    m_PopupRows.addSeparator();
    m_PopupRows.add(menuItemSearch);
    m_PopupRows.add(menuItemClearSearch);
    if (!isReadOnly()) {
      m_PopupRows.addSeparator();
      m_PopupRows.add(menuItemDeleteSelectedInstance);
      m_PopupRows.add(menuItemDeleteAllSelectedInstances);
    }
  }
  
  /**
   * sets the enabled/disabled state of the menu items 
   */
  private void setMenu() {
    boolean			isNumeric;
    boolean			hasColumns;
    boolean			hasRows;
    boolean			attSelected;
    ArffSortedTableModel	model;
    boolean			isNull;
    
    model       = (ArffSortedTableModel) m_TableArff.getModel();
    isNull      = (model.getInstances() == null);
    hasColumns  = !isNull && (model.getInstances().numAttributes() > 0);
    hasRows     = !isNull && (model.getInstances().numInstances() > 0);
    attSelected = hasColumns && (m_CurrentCol > 0);
    isNumeric   = attSelected && (model.getAttributeAt(m_CurrentCol).isNumeric());
    
    menuItemUndo.setEnabled(canUndo());
    menuItemCopy.setEnabled(true);
    menuItemSearch.setEnabled(true);
    menuItemClearSearch.setEnabled(true);
    menuItemMean.setEnabled(isNumeric);

    menuItemAttributeDistribution.setEnabled(attSelected);
    menuItemAttributeStatistics.setEnabled(attSelected);

    menuItemSetAllValues.setEnabled(attSelected);
    menuItemSetMissingValues.setEnabled(attSelected);
    menuItemReplaceValues.setEnabled(attSelected);
    //
    this.menuItemBinarize.setEnabled(attSelected);
    this.menuItemBinarize.setEnabled(!this.getInstances().attribute(this.getInstances().numAttributes()-1).isNumeric());
    this.menuItemBinarize.setToolTipText("Numericc values can only be binarized, if the dataset class attribute is not numeric");

    this.menuItemDiscretize.setEnabled(attSelected);
    this.menuItemDiscretize.setEnabled(!this.getInstances().attribute(this.getInstances().numAttributes()-1).isNumeric());
    this.menuItemDiscretize.setToolTipText("Numericc values can only be discretized, if the dataset class attribute is not numeric");

    this.menuItemNominalize.setEnabled(attSelected);
    this.menuItemNominalize.setEnabled(isNumeric);
    
    this.menuItemStringize.setEnabled(attSelected);

    this.menuItemSetMissingValuesToMean.setEnabled(isNumeric);
    this.menuItemSetMissingValuesToMedian.setEnabled(isNumeric);
    this.menuItemSetMissingValuesToZero.setEnabled(isNumeric);
    //
    menuItemReplaceValues.setEnabled(attSelected);
    menuItemReplaceValues.setEnabled(attSelected);

    //--------------------------------------------------------------------------
    this.menuItemCopyAttribute.setEnabled(attSelected);
    this.menuItemCopyAttribute.setEnabled(attSelected);

    if (Global.clipboard!=null)
        this.menuItemPasteAttribute.setEnabled(true);
    else
        this.menuItemPasteAttribute.setEnabled(false);

    this.menuItemAttributeAsClass.setEnabled(attSelected);
    //--------------------------------------------------------------------------
    menuItemRenameAttribute.setEnabled(attSelected);
    menuItemDeleteAttribute.setEnabled(attSelected);
    menuItemDeleteAttributes.setEnabled(attSelected);
    menuItemSortInstances.setEnabled(hasRows && attSelected);
    menuItemDeleteSelectedInstance.setEnabled(hasRows && m_TableArff.getSelectedRow() > -1);
    menuItemDeleteAllSelectedInstances.setEnabled(hasRows && (m_TableArff.getSelectedRows().length > 0));
  }
  
  /**
   * returns the table component
   * 
   * @return 		the table
   */
  public ArffTable getTable() {
    return m_TableArff;
  }
  
  /**
   * returns the title for the Tab, i.e. the filename
   * 
   * @return 		the title for the tab
   */
  public String getTitle() {
    return m_Title;
  }
  
  /**
   * returns the filename
   * 
   * @return		the filename
   */
  public String getFilename() {
    return m_Filename;
  }
  
  /**
   * sets the filename
   * 
   * @param filename	the new filename
   */
  public void setFilename(String filename) {
    m_Filename = filename;
    createTitle();
  }
  
  /**
   * returns the currentDataInstances of the panel, if none then NULL
   * 
   * @return		the currentDataInstances of the panel
   */
  public Instances getInstances() {
    Instances            result;
    
    result = null;
    
    if (m_TableArff.getModel() != null)
      result = ((ArffSortedTableModel) m_TableArff.getModel()).getInstances();
    
    return result;
  }
  
  /**
   * displays the given currentDataInstances, i.e. creates a tab with the title
   * TAB_INSTANCES. if one already exists it closes it.<br>
   * if a different currentDataInstances object is used here, don't forget to clear
   * the undo-history by calling <code>clearUndo()</code>
   * 
   * @param data	the currentDataInstances to display
   * @see               #TAB_INSTANCES
   * @see               #clearUndo()
   */
  public void setInstances(Instances data) {
    ArffSortedTableModel         model;
    
    m_Filename = TAB_INSTANCES;
    
    createTitle();
    model = new ArffSortedTableModel(data);
    model.setShowAttributeIndex(m_ShowAttributeIndex);
    
    m_TableArff.setModel(model);
    clearUndo();
    setChanged(false);
    createName();
  }
  
  /**
   * returns a list with the attributes
   * 
   * @return		a list of the attributes
   */
  public Vector getAttributes() {
    Vector               result;
    int                  i;
    
    result = new Vector();
    for (i = 0; i < getInstances().numAttributes(); i++)
      result.add(getInstances().attribute(i).name());
    Collections.sort(result);
    
    return result;
  }
  
  /**
   * can only reset the changed state to FALSE
   * 
   * @param changed		if false, resets the changed state
   */
  public void setChanged(boolean changed) {
    if (!changed) {
      this.m_Changed = changed;
      createTitle();
    }
  }
  
  /**
   * returns whether the content of the panel was changed
   * 
   * @return		true if the content was changed
   */
  public boolean isChanged() {
    return m_Changed;
  }

  /**
   * returns whether the model is read-only
   * 
   * @return 		true if model is read-only
   */
  public boolean isReadOnly() {
    if (m_TableArff == null)
      return true;
    else
      return ((ArffSortedTableModel) m_TableArff.getModel()).isReadOnly();
  }
  
  /**
   * sets whether the model is read-only
   * 
   * @param value	if true the model is set to read-only
   */
  public void setReadOnly(boolean value) {
    if (m_TableArff != null)
      ((ArffSortedTableModel) m_TableArff.getModel()).setReadOnly(value);
  }

  /**
   * Sets whether to display the attribute index in the header.
   * 
   * @param value	if true then the attribute indices are displayed in the
   * 			table header
   */
  public void setShowAttributeIndex(boolean value) {
    m_ShowAttributeIndex = value;
    if (m_TableArff != null)
      ((ArffSortedTableModel) m_TableArff.getModel()).setShowAttributeIndex(value);
  }
  
  /**
   * Returns whether to display the attribute index in the header.
   * 
   * @return		true if the attribute indices are displayed in the
   * 			table header
   */
  public boolean getShowAttributeIndex() {
    return m_ShowAttributeIndex;
  }
  
  /**
   * returns whether undo support is enabled
   * 
   * @return 		true if undo is enabled
   */
  public boolean isUndoEnabled() {
    return ((ArffSortedTableModel) m_TableArff.getModel()).isUndoEnabled();
  }
  
  /**
   * sets whether undo support is enabled
   * 
   * @param enabled		whether to enable/disable undo support
   */
  public void setUndoEnabled(boolean enabled) {
    ((ArffSortedTableModel) m_TableArff.getModel()).setUndoEnabled(enabled);
  }
  
  /**
   * removes the undo history
   */
  public void clearUndo() {
    ((ArffSortedTableModel) m_TableArff.getModel()).clearUndo();
  }
  
  /**
   * returns whether an undo is possible 
   * 
   * @return		true if undo is possible
   */
  public boolean canUndo() {
    return ((ArffSortedTableModel) m_TableArff.getModel()).canUndo();
  }
  
  /**
   * performs an undo action
   */
  public void undo() {
    if (canUndo()) {
      ((ArffSortedTableModel) m_TableArff.getModel()).undo();
      
      // notify about update
      notifyListener();
    }
  }
  
  /**
   * adds the current state of the currentDataInstances to the undolist
   */
  public void addUndoPoint() {
    ((ArffSortedTableModel) m_TableArff.getModel()).addUndoPoint();
        
    // update menu
    setMenu();
  }
  
  /**
   * sets the title (i.e. filename)
   */
  private void createTitle() {
    File              file;
    
    if (m_Filename.equals("")) {
      m_Title = "-none-";
    }
    else if (m_Filename.equals(TAB_INSTANCES)) {
      m_Title = TAB_INSTANCES;
    }
    else {
      try {
        file  = new File(m_Filename);
        m_Title = file.getName();
      }
      catch (Exception e) {
        m_Title = "-none-";
      }
    }
    
    if (isChanged())
      m_Title += " *";
  }
  
  /**
   * sets the relation name
   */
  private void createName() {
    ArffSortedTableModel         model;
    
    model = (ArffSortedTableModel) m_TableArff.getModel();
    if ((model != null) && (model.getInstances() != null))
      m_LabelName.setText("Relation: " + model.getInstances().relationName());
    else
      m_LabelName.setText("");
  }
  
  /**
   * loads the specified file into the table
   * 
   * @param filename		the file to load
   */
  private void loadFile(String filename) {
    ArffSortedTableModel         model;
    
    this.m_Filename = filename;
    
    createTitle();
    
    if (filename.equals("")) {
      model = null;
    }
    else {
      model = new ArffSortedTableModel(filename);
      model.setShowAttributeIndex(getShowAttributeIndex());
    }
    
    m_TableArff.setModel(model);
    setChanged(false);
    createName();
  }


  private void loadInstances(Instances instances) {
    ArffSortedTableModel         model;

    //-------------------------------------------------------------------------
    File currentFile=new File(this.getFilename());
    File instancesFile=new File(currentFile.getParent()+"/"+instances.relationName()+".csv");
    Tools.writeStringToFile(instances.toString(), instancesFile.getPath());
    //-------------------------------------------------------------------------
    String instancesFilename=instancesFile.getPath();
    this.m_Filename = instancesFilename;
    //-------------------------------------------------------------------------
    createTitle();
    if (instancesFilename.equals(""))
    {
      model = null;
    }
    else
    {
      model = new ArffSortedTableModel(instancesFilename);
      model.setShowAttributeIndex(getShowAttributeIndex());
    }

    m_TableArff.setModel(model);
    setChanged(false);
    createName();
  }


  /**
   * calculates the mean of the given numeric column
   */
  private void calcMean() {
    ArffSortedTableModel   model;
    int               i;
    double            mean;
    
    // no column selected?
    if (m_CurrentCol == -1)
      return;
    
    model = (ArffSortedTableModel) m_TableArff.getModel();
    
    // not numeric?
    if (!model.getAttributeAt(m_CurrentCol).isNumeric())
      return;
    
    mean = 0;
    for (i = 0; i < model.getRowCount(); i++)
      mean += model.getInstances().instance(i).value(m_CurrentCol - 1);
    mean = mean / model.getRowCount();
    
    // show result
    ComponentHelper.showMessageBox(
        getParent(), 
        "Mean for attribute...",
        "Mean for attribute '" 
        + m_TableArff.getPlainColumnName(m_CurrentCol) 
        + "':\n\t" + Utils.doubleToString(mean, 3),
        JOptionPane.OK_CANCEL_OPTION,
        JOptionPane.PLAIN_MESSAGE);
  }

   //---------------------------------------------------------------------------
  //***************************************************************************
  //                        replace Missing values with means
  //***************************************************************************
  //---------------------------------------------------------------------------
  private void replaceMessingValuesWithMedian(int attributeIndex)
  {
    Object                     value=null;
    ArffSortedTableModel      model;

    model = (ArffSortedTableModel) m_TableArff.getModel();
    model.setNotificationEnabled(false);
    //Object retValue=null;
    value=((ArffSortedTableModel) m_TableArff.getModel()).median(attributeIndex);

    //if (((ArffSortedTableModel) m_TableArff.getModel()).getAttributeAt(attributeIndex).isNumeric())
    //   value=Double.parseDouble((String) retValue);
    //else
    //    value=retValue.toString();
 
    // undo
    //-------------------------------------------------------------------------
    addUndoPoint();
    model.setUndoEnabled(false);

    // set value
    //-------------------------------------------------------------------------
    for (int i = 0; i < m_TableArff.getRowCount(); i++)
    {
         if ( model.isMissingAt(i, m_CurrentCol) )
              model.setValueAt(value, i, m_CurrentCol);
    }

    //-------------------------------------------------------------------------
    model.setUndoEnabled(true);
    model.setNotificationEnabled(true);
    model.notifyListener(new TableModelEvent(model, 0, model.getRowCount(), m_CurrentCol, TableModelEvent.UPDATE));

    // refresh
    //-------------------------------------------------------------------------
    m_TableArff.repaint();

        //@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
logProcedure("Attribute "+
                                     this.getInstances().attribute(m_CurrentCol-1).name() +
                                     " miising values was replaced with median");


  }

  /**
   * sets the specified values in a column to a new value
   *
   * @param o		the menu item
   */
  //---------------------------------------------------------------------------
  //***************************************************************************
  //                        replace Missing values with Zero
  //***************************************************************************
  //---------------------------------------------------------------------------
  private void replaceMessingValuesWithMean(int attributeIndex)
  {
    Object                     value=null;
    ArffSortedTableModel      model;    

    model = (ArffSortedTableModel) m_TableArff.getModel();
    model.setNotificationEnabled(false);

    value=((ArffSortedTableModel) m_TableArff.getModel()).meanOrMode(attributeIndex);

    // undo
    //-------------------------------------------------------------------------
    addUndoPoint();
    model.setUndoEnabled(false);

    // set value
    //-------------------------------------------------------------------------
    for (int i = 0; i < m_TableArff.getRowCount(); i++)
    {
         if ( model.isMissingAt(i, m_CurrentCol) )
              model.setValueAt(value, i, m_CurrentCol);
    }

    //-------------------------------------------------------------------------
    model.setUndoEnabled(true);
    model.setNotificationEnabled(true);
    model.notifyListener(new TableModelEvent(model, 0, model.getRowCount(), m_CurrentCol, TableModelEvent.UPDATE));

    // refresh
    //-------------------------------------------------------------------------
    m_TableArff.repaint();

    //@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
    logProcedure("Attribute "+
                                     this.getInstances().attribute(m_CurrentCol-1).name() +
                                     " miisng values was replaced with means");
  }




  /**
   * sets the specified values in a column to a new value
   *
   * @param o		the menu item
   */
  //---------------------------------------------------------------------------
  //***************************************************************************
  //                        replace Missing values with median
  //***************************************************************************
  //---------------------------------------------------------------------------
  private void replaceMessingValuesWithZero(int attributeIndex)
  {
    Object                     value=null;
    ArffSortedTableModel      model;

    model = (ArffSortedTableModel) m_TableArff.getModel();
    model.setNotificationEnabled(false);

    value=0;

    // undo
    //-------------------------------------------------------------------------
    addUndoPoint();
    model.setUndoEnabled(false);

    // set value
    //-------------------------------------------------------------------------
    for (int i = 0; i < m_TableArff.getRowCount(); i++)
    {
         if ( model.isMissingAt(i, m_CurrentCol) )
              model.setValueAt(value, i, m_CurrentCol);
    }

    //-------------------------------------------------------------------------
    model.setUndoEnabled(true);
    model.setNotificationEnabled(true);
    model.notifyListener(new TableModelEvent(model, 0, model.getRowCount(), m_CurrentCol, TableModelEvent.UPDATE));

    // refresh
    //-------------------------------------------------------------------------
    m_TableArff.repaint();

    logProcedure("Attribute "+this.getInstances().attribute(m_CurrentCol-1).name() +" miisng values was replaced with Zero");

  }

  /**
   * sets the specified values in a column to a new value
   * 
   * @param o		the menu item
   */
  //---------------------------------------------------------------------------
  //***************************************************************************
  //                        set values
  //***************************************************************************
  //---------------------------------------------------------------------------
  private void setValues(Object o)
  {
    String                     msg;
    String                     title;
    String                     value;
    String                     valueNew;
    int                        i;
    ArffSortedTableModel      model;
    
    value    = "";
    valueNew = "";
    
    if (o == menuItemSetMissingValues) {
      title = "Replace missing values..."; 
      msg   = "New value for MISSING values";
    }
    else if (o == menuItemSetAllValues) {
      title = "Set all values..."; 
      msg   = "New value for ALL values";
    }
    else if (o == menuItemReplaceValues) {
      title = "Replace values..."; 
      msg   = "Old value";
    }
    else
      return;
    
    value = ComponentHelper.showInputBox(m_TableArff.getParent(), title, msg, m_LastSearch);
    
    // cancelled?
    //-----------------------------------------------------------------
    if (value == null)
      return;

    m_LastSearch = value;
    
    // replacement
    //-----------------------------------------------------------------
    if (o == menuItemReplaceValues)
    {
      valueNew = ComponentHelper.showInputBox(m_TableArff.getParent(), title, "New value", m_LastReplace);
      if (valueNew == null)
        return;
      m_LastReplace = valueNew;
    }
    
    model = (ArffSortedTableModel) m_TableArff.getModel();
    model.setNotificationEnabled(false);

    // undo
    //-----------------------------------------------------------------
    addUndoPoint();
    model.setUndoEnabled(false);
    String valueCopy = value;

    // set value
   //-----------------------------------------------------------------
    for (i = 0; i < m_TableArff.getRowCount(); i++)
    {
          if (o == menuItemSetAllValues)
          {
                if (valueCopy.equals("NaN") || valueCopy.equals("?") || valueCopy.equals("")|| valueCopy.equals(" "))
                {
                  value = null;
                }
                model.setValueAt(value, i, m_CurrentCol);
          }
          else if ( (o == menuItemSetMissingValues) && model.isMissingAt(i, m_CurrentCol) )
              model.setValueAt(value, i, m_CurrentCol);

          else if ( (o == menuItemReplaceValues) &&
                  model!=null &&
                  model.getValueAt(i, m_CurrentCol)!=null &&
                  model.getValueAt(i, m_CurrentCol).toString().equals(value) )

         model.setValueAt(valueNew, i, m_CurrentCol);
    }
    model.setUndoEnabled(true);
    model.setNotificationEnabled(true);
    model.notifyListener(new TableModelEvent(model, 0, model.getRowCount(), m_CurrentCol, TableModelEvent.UPDATE));


    //@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
logProcedure("Attribute "+
                                     this.getInstances().attribute(m_CurrentCol-1).name() +
                                     " values was set to "+value);





    // refresh
    //-----------------------------------------------------------------
    m_TableArff.repaint();
  }  



  //***************************************************************************
  //                         Delete Attributes
  //***************************************************************************
  /**
   * deletes the chosen attributes
   */
  public void deleteAttributes() {
    ListSelectorDialog    dialog;
    ArffSortedTableModel       model;
    Object[]              atts;
    int[]                 indices;
    int                   i;
    JList                 list;
    int                   result;
    
    list   = new JList(getAttributes());
    dialog = new ListSelectorDialog(null, list);
    result = dialog.showDialog();
    
    if (result != ListSelectorDialog.APPROVE_OPTION)
      return;
    
    atts = list.getSelectedValues();
    
    // really?
    if (ComponentHelper.showMessageBox(
        getParent(), 
        "Confirm...",
        "Do you really want to delete these " 
        + atts.length + " attributes?",
        JOptionPane.YES_NO_OPTION,
        JOptionPane.QUESTION_MESSAGE) != JOptionPane.YES_OPTION)
      return;
    
    model   = (ArffSortedTableModel) m_TableArff.getModel();
    indices = new int[atts.length];
    for (i = 0; i < atts.length; i++)
      indices[i] = model.getAttributeColumn(atts[i].toString());
    
    setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
    model.deleteAttributes(indices);
    setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));

   //@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
    logProcedure("Attribute"+ indices.toString()+ " was deleted");

  }
  
  /**
   * sets the current attribute as class attribute, i.e. it moves it to the end
   * of the attributes
   */
  public void attributeAsClass() {
    ArffSortedTableModel   model;
    
    // no column selected?
    if (m_CurrentCol == -1)
      return;
    
    model   = (ArffSortedTableModel) m_TableArff.getModel();

    // really an attribute column?
    if (model.getAttributeAt(m_CurrentCol) == null)
      return;
    
    setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
    model.attributeAsClassAt(m_CurrentCol);
    setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));

   //@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@

    logProcedure("Attribute "+
                                     this.getInstances().attribute(m_CurrentCol-1).name() +
                                     " was set as a class attribute");

  }
  

  /**
   * deletes the currently selected instance
   */
  public void deleteInstance() {
    int               index;
    
    index = m_TableArff.getSelectedRow();
    if (index == -1)
      return;
    
    ((ArffSortedTableModel) m_TableArff.getModel()).deleteInstanceAt(index);

   //@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
    logProcedure("Instance number "+
                                     this.m_curruntRaw +
                                     " was deleted");

  }

//-----------------------------------------------------------------------------
//-----------------------------------------------------------------------------
//                               Copy Attribute
//-----------------------------------------------------------------------------
//-----------------------------------------------------------------------------
  public void copyAttribute()
  {
    Object[]  content = {new Object(),new Object()};

    ArffSortedTableModel   model;
    model = (ArffSortedTableModel) m_TableArff.getModel();
    m_TableArff.setColumnSelectionAllowed(true);
    model.sort(0);
    Object[] columnValues=new Object[m_TableArff.getRowCount()] ;
    for (int i=0;i<m_TableArff.getRowCount();i++)
       columnValues[i]=m_TableArff.getValueAt(i, m_CurrentCol);

    content[0]=model.getAttributeAt(m_CurrentCol).copy(model.getAttributeAt(m_CurrentCol).name()+"_Copy");
    content[1]=columnValues;
    Global.clipboard=content;
  }

//-----------------------------------------------------------------------------
//-----------------------------------------------------------------------------
//                               Paste Attribute
//-----------------------------------------------------------------------------
//-----------------------------------------------------------------------------

  public void pasteAttribute()
  {

    // get current model which the tabe is based on
    //-------------------------------------------------------------------------
    ArffSortedTableModel model = (ArffSortedTableModel) m_TableArff.getModel();
    Instances instances=model.getInstances();

    Attribute newAttribute=(Attribute)Global.clipboard[0];

    //String[] valuesArray=Tools.stringToArray((String) Global.clipboard[1]);
    Object[] valuesArray=(Object[]) Global.clipboard[1];

    instances.insertAttributeAt(newAttribute, instances.numAttributes()-1);

    // set the new model to the table
    //-------------------------------------------------------------------------
    model.setInstances(instances);
    model.sort(0);

    // filling the values in the model
    //-------------------------------------------------------------------------

    for (int i=0;i<valuesArray.length;i++)
    {
        model.setValueAt(valuesArray[i],// value
                i,                      // row
                model.getColumnCount()-2);// index of the column where the value is
    }


    //-------------------------------------------------------------------------
    m_TableArff.setModel(model);
    TableColumn newColumn = new TableColumn();
    newColumn.setHeaderValue(newAttribute.name());
    newColumn.setCellEditor(m_TableArff.getCellEditor());


      // adding the column to the table
    //-------------------------------------------------------------------------
      m_TableArff.addColumn(newColumn);
      this.m_CurrentCol=m_TableArff.getColumnCount()-2;


     m_TableArff.setSelectedColumn(m_TableArff.getColumnCount()-2);
     model.renameAttributeAt(m_CurrentCol,newAttribute.name()+""+(++fileNameCounter));

    Global.clipboard=null;// reset the copied value


   //@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
    logProcedure("Attribute "+
                                     this.getInstances().attribute(this.m_CurrentCol-2) +
                                     " was copied and pasted");

}


  //-----------------------------------------------------------
  //***********************************************************
  //                   copy Raws
  //***********************************************************
  //-----------------------------------------------------------
  public void copyInstances()
  {
    int[]                   indices = null;    
    indices = m_TableArff.getSelectedRows();

    // Getting the current active model
    //------------------------------------------------
    Global.clipboardArrayList=new ArrayList<Object>();   
    for (int i = 0; i < indices.length; i++)
    {
      Global.clipboardArrayList.add(((ArffSortedTableModel) m_TableArff.getModel()).getInstances().get(indices[i]));
      System.out.println(((ArffSortedTableModel) m_TableArff.getModel()).getInstances().get(indices[i]).toString());
    }
  }

  //-----------------------------------------------------------
  //***********************************************************
  //                   paste Raws
  //***********************************************************
  //-----------------------------------------------------------
  public void pasteInstances ()
  {
    
    // paste the contents of the instance array list
    //------------------------------------------------
if (Global.clipboardArrayList!=null &&Global.clipboardArrayList.size()>0)
   for (Object o:Global.clipboardArrayList)
    {
      Instance copiedInstance=(Instance) o;      
      ((ArffSortedTableModel) m_TableArff.getModel()).addInstance(copiedInstance);
    }

    this.repaint();


   //@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
    logProcedure("Instance number "+
                                     this.m_curruntRaw +
                                     " was copied and pasted");
  }


  /**
   * deletes all the currently selected currentDataInstances
   */
  public void deleteInstances() {
    int[]             indices;
    
    if (m_TableArff.getSelectedRow() == -1)
      return;
    
    indices = m_TableArff.getSelectedRows();
    ((ArffSortedTableModel) m_TableArff.getModel()).deleteInstances(indices);


//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
logProcedure("Instances number "+
                                     indices.toString()+
                                     " was deleted");
  }
  
  /**
   * sorts the currentDataInstances via the currently selected column
   */
  public void sortInstances() {
    if (m_CurrentCol == -1)
      return;
    
    ((ArffSortedTableModel) m_TableArff.getModel()).sortInstances(m_CurrentCol);
    setChanged(true);
  }
  
  /**
   * searches for a string in the cells
   */
  public void search() {
    String              searchString;
    
    // display dialog
    searchString = ComponentHelper.showInputBox(getParent(), "Search...", "Enter the string to search for", m_LastSearch);
    if (searchString != null)
      m_LastSearch = searchString;
    
    getTable().setSearchString(searchString);
  }
  
  /**
   * clears the search, i.e. resets the found cells
   */
  public void clearSearch() {
    getTable().setSearchString("");
  }
  
  /**
   * calculates the optimal column width for the current column
   */
  public void setOptimalColWidth() {
    // no column selected?
    if (m_CurrentCol == -1)
      return;

    JTableHelper.setOptimalColumnWidth(getTable(), m_CurrentCol);
  }
  
  /**
   * calculates the optimal column widths for all columns
   */
  public void setOptimalColWidths() {
    JTableHelper.setOptimalColumnWidth(getTable());
  }
  
  /**
   * invoked when an action occurs
   * 
   * @param e		the action event
   */
  public void actionPerformed(ActionEvent e) {
    Object          o;
    
    o = e.getSource();
    
    if (o == menuItemMean)
      calcMean();

    //--------------------------------------------------------------------------

    else if (o == menuItemSetMissingValuesToMean)
      this.replaceMessingValuesWithMean(m_CurrentCol-1);

    else if (o == menuItemSetMissingValuesToMedian)
      this.replaceMessingValuesWithMedian(m_CurrentCol-1);


    else if (o == menuItemDiscretize)
      this.discretize();

    else if (o == menuItemBinarize)
      this.binarize();

    else if (o == menuItemNominalize)    
       this.nominalize();

    else if (o==menuItemStringize)    
        this.stringize();        
    

    else if (o == menuItemSetMissingValuesToZero)
      this.replaceMessingValuesWithZero(m_CurrentCol-1);

    else if (o == menuItemSetMissingValues)
      setValues(menuItemSetMissingValues);

    //--------------------------------------------------------------------------
    else if (o == menuItemSetAllValues)
      setValues(menuItemSetAllValues);

    else if (o == menuItemReplaceValues)
      setValues(menuItemReplaceValues);

    //--------------------------------------------------------------------------
    else if (o == menuItemRenameAttribute)
      renameAttribute();

    //--------------------------------------------------------------------------
    else if (o == menuItemCopyAttribute)
      copyAttribute();
    else if (o == menuItemPasteAttribute)
      pasteAttribute();
    else if (o == menuItemAddAttribute)
      addAttribute();


    //--------------------------------------------------------------------------
    else if (o == menuItemAttributeAsClass)
      attributeAsClass();
    else if (o == menuItemDeleteAttribute)
      deleteAttribute();
    else if (o == menuItemDeleteAttributes)
      deleteAttributes();
    else if (o == menuItemDeleteSelectedInstance)
      deleteInstance();
    else if (o == menuItemDeleteAllSelectedInstances)
      deleteInstances();
    else if (o == menuItemSortInstances)
      sortInstances();
    else if (o == menuItemSearch)
      search();
    else if (o == menuItemClearSearch)
      clearSearch();
    else if (o == menuItemAttributeDistribution)
    {      
      VisualizationTools.visualizeAttribute(this.getInstances(), this.m_CurrentCol-1);
    }
    else if (o == menuItemAttributeStatistics)
    {
      clearSearch();
      JOptionPane.showMessageDialog(null,this.getInstances().attributeStats(this.m_CurrentCol-1));
    }


    else if (o == menuItemUndo)
      undo();
    else if (o == menuItemCopy)
      this.copyInstances();
      //copyContent();

    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    else if (o == menuItemPaste)    
      this.pasteInstances();    
    else if (o == menuItemOptimalColWidth)
      setOptimalColWidth();
    else if (o == menuItemOptimalColWidths)
      setOptimalColWidths();
  }
  
  /**
   * Invoked when a mouse button has been pressed and released on a component
   * 
   * @param e		the mouse event
   */
  public void mouseClicked(MouseEvent e) {
    int		col;

    boolean	popup;
    
    col   = m_TableArff.columnAtPoint(e.getPoint());

    popup =    ((e.getButton() == MouseEvent.BUTTON3) && (e.getClickCount() == 1))
            || ((e.getButton() == MouseEvent.BUTTON1) && (e.getClickCount() == 1) && e.isAltDown() && !e.isControlDown() && !e.isShiftDown());
    popup = popup && (getInstances() != null);



    if (e.getSource() == m_TableArff.getTableHeader())
    {
      m_CurrentCol = col;
      m_curruntRaw = m_TableArff.getSelectedRow();
      System.out.println("current raw = "+m_curruntRaw);


      // Popup-Menu
      //----------------------------------------------------------------------
      if (popup) {
        e.consume();
        setMenu();
        initPopupMenus();
        m_PopupHeader.show(e.getComponent(), e.getX(), e.getY());
      }
    }
    else if (e.getSource() == m_TableArff) {
      // Popup-Menu
      if (popup) {
        e.consume();
        setMenu();
        initPopupMenus();
        m_PopupRows.show(e.getComponent(), e.getX(), e.getY());
      }
    }
    
    // highlihgt column
    if (    (e.getButton() == MouseEvent.BUTTON1)  
         && (e.getClickCount() == 1) 
         && (!e.isAltDown())
         && (col > -1) ) {
      m_TableArff.setSelectedColumn(col);
    }
  }
  
  /**
   * Invoked when the mouse enters a component.
   * 
   * @param e		the mouse event
   */
  public void mouseEntered(MouseEvent e) {
  }
  
  /**
   * Invoked when the mouse exits a component
   * 
   * @param e		the mouse event
   */
  public void mouseExited(MouseEvent e) {
  }
  
  /**
   * Invoked when a mouse button has been pressed on a component
   * 
   * @param e		the mouse event
   */
  public void mousePressed(MouseEvent e) {
  }
  
  /**
   * Invoked when a mouse button has been released on a component.
   * 
   * @param e		the mouse event
   */
  public void mouseReleased(MouseEvent e) {
  }
  
  /**
   * Invoked when the target of the listener has changed its state.
   * 
   * @param e		the change event
   */
  public void stateChanged(ChangeEvent e) {
    m_Changed = true;
    createTitle();
    notifyListener();
  }
  
  /**
   * notfies all listener of the change
   */
  public void notifyListener() {
    Iterator                iter;
    
    iter = m_ChangeListeners.iterator();
    while (iter.hasNext())
      ((ChangeListener) iter.next()).stateChanged(new ChangeEvent(this));
  }
  
  /**
   * Adds a ChangeListener to the panel
   * 
   * @param l		the listener to add
   */
  public void addChangeListener(ChangeListener l) {
    m_ChangeListeners.add(l);
  }
  
  /**
   * Removes a ChangeListener from the panel
   * 
   * @param l		the listener to remove
   */
  public void removeChangeListener(ChangeListener l) {
    m_ChangeListeners.remove(l);
  }
}
