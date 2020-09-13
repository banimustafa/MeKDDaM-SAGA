package toolbox;
/*
Definitive Guide to Swing for Java 2, Second Edition
By John Zukowski
ISBN: 1-893115-78-X
Publisher: APress
*/
import global.Global;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.tree.*;
import java.awt.*;
import java.util.*;
import process_model.basic.util.ArrayList;
import process_model.phase.Phase;
import process_model.phase.objective.Objectives;
import process_model.phase.prerequisite.PreRequisites;
import process_model.phase.stage.performing.Performing;
import process_model.phase.stage.planning.Planning;
import process_model.phase.stage.reporting.Reporting;
import process_model.phase.stage.validating.Validating;


public class Tree {
    private static Object selectedObject=null;
    private static TreePath selectedPath=null;
    private static Phase currentPhase=null;
    private static Object[] feedbackPhases=null;
    private static int feedbackPhasesSize=0;

  //***************************************************************************
  //***************************************************************************
  //                            Getters Interfaces
  //***************************************************************************
  //***************************************************************************

//GGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGG
    public static Object getSelectedObject() {
        return selectedObject;
    }
    public static void clearSelectedObject() {
        selectedObject=null;
        selectedPath=null;
        currentPhase=null;
        feedbackPhases=null;
        feedbackPhasesSize=0;
    }

 //GGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGG
    public static TreePath getSelectedPath() {
        return selectedPath;
    }
   //GGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGG
    public static String getSelectedPathString() {
        String path=null;
        if (selectedPath!=null)
            path=" ";

        Object pathArray[]=Tree.selectedPath.getPath();
        for(int i=2;i<pathArray.length-1;i++)
               path=path+pathArray[i]+" -> ";
        
        return path;
    }

//GGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGG
    public static Object[] getAvailableFeedbackPhases(Phase phase) {
        currentPhase = (Phase) phase;
        feedbackPhases= getPhaseFeedbacksArray(currentPhase);
        return feedbackPhases;
    }
//GGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGG
    public static int getAvailableFeedbackPhasesSize() {
     if (feedbackPhases!=null&&feedbackPhases.length>0)
         feedbackPhasesSize=feedbackPhases.length;
    return feedbackPhasesSize;
    }


//GGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGG
    public static void creatPhaseFedbacksTree(Phase phase) {
  
            if (phase!=null)
            {
              currentPhase = (Phase) phase;
              create_tree("Feedbacks");
            }            
    }
//GGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGG
    public static void createPhaseReportsTree() {
        create_tree("Reports"); 
    }
//GGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGG
    public static void createPhaseTasksTree() {
        create_tree("Tasks");        
    }
//GGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGG
    public static Phase createPhaseVersionsTree() {
        create_tree("Versions");
        return (Phase) selectedObject;
    }
//GGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGG

    public static void main(String args[]) {
        create_tree("Tasks");
    }
    
  //***************************************************************************
  //***************************************************************************
  //                            Create Tree
  //***************************************************************************
  //***************************************************************************

 public static void create_tree(String type) {
    JFrame frame = new JFrame("Traverse Tree");
     frame.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
    Object[] phaseArray=null;
    Vector phaseVector=null;
    // the phase array is added to the phase vector after it is filled with processPhases
    //------------------------------------------------------------------------
    if (type.equals("Tasks"))
        if (getPhaseTasksArray()!=null)
            phaseVector=new NamedVector("Phases",getPhaseTasksArray());
        else
            phaseVector=null;

    else if (type.equals("Reports"))
             if (getPhaseReportsArray()!=null)
                 phaseVector=new NamedVector("Phases",getPhaseReportsArray());
             else
                 phaseVector=null;

    else if(type.equals("Feedbacks"))
            if (getPhaseFeedbacksArray(currentPhase)!=null)
                phaseVector=new NamedVector("Phases",getPhaseFeedbacksArray(currentPhase));
            else
                 phaseVector=null;

    else if(type.equals("Phases") )
            if (getPhasesVersionsArray()!=null)
                phaseVector=new NamedVector("Phases",getPhasesVersionsArray());
            else
                 phaseVector=null;
    //------------------------------------------------------------------------
    String dataSet=new String("Data Set");
    String metaData=new String("Meta Data");
    String aims=new String("Aims of Study");
    //
    Object[] metabolomicsDataArray={dataSet,metaData};
    Vector inputsVector=new NamedVector("Metabolomics Data",metabolomicsDataArray);
    Object[] metabolomicsInputArray={inputsVector,aims};
    //
    Vector inputVector=new NamedVector("Inputs",metabolomicsInputArray);
   // Vector inputVector=new NamedVector("Inputs",inputsVector);
    //------------------------------------------------------------------------
    
    
    
    
    //------------------------------------------------------------------------
    String humanInteraction=new String("Human Interaction");
    String management=new String("Process Management");
    String quality=new String("Quality Assurance");
    String standards=new String("Standards");    
    Object[] supplementsArray={humanInteraction,management,quality,standards};
    Vector supplementsVector=new NamedVector("Process Supplements",supplementsArray);       
    //------------------------------------------------------------------------
    
    Vector processVector=null;
   if (phaseVector!=null)
   {
     Object processArray[]={inputVector, phaseVector,supplementsVector,"results", "Cross-delivery Validation"};
     processVector=new NamedVector("Process", processArray);// name of the group and its children
   }
 else
   {
     Object processArray[]={inputVector};
     processVector=new NamedVector("Process", processArray);// name of the group and its children
    }
    //------------------------------------------------------------------------
    Object projectArry[] = {"Project Info",processVector};
    Vector projectVector = new NamedVector("Project", projectArry);
    
    //-----------------------------------------------------------------------    
    Object rootNodes[] = {projectVector};
    Vector rootVector = new NamedVector("Root Vector",rootNodes);

    //************************************************************************
    //************************************************************************
    //************************************************************************
    JTree tree = new JTree(rootVector);
    tree.setRootVisible(false);
    TreeModel model = tree.getModel();
    
    Object rootObject = model.getRoot();
    if ((rootObject != null)
        && (rootObject instanceof DefaultMutableTreeNode)) {      
    }

    // where to add the action performed when selecting a node
    //-------------------------------------------------------------------------
    TreeSelectionListener treeSelectionListener = new TreeSelectionListener() {
      public void valueChanged(TreeSelectionEvent treeSelectionEvent) {
        JTree treeSource = (JTree) treeSelectionEvent.getSource();
        TreePath path = treeSource.getSelectionPath();
        if (path!=null)
        {           
            selectedPath=path;
            selectedObject=((DefaultMutableTreeNode) path.getLastPathComponent()).getUserObject();
            //System.out.print(selectedObject.toString());
            //System.out.print("Selected object is of the class "+selectedObject.getClass().getSimpleName());
         }
       // else
         //System.out.print("Path not found !");
      }
    };
    tree.addTreeSelectionListener(treeSelectionListener);

    // Adding tree to the panel and then to the jFrame
    //-------------------------------------------------------------------------
    JScrollPane scrollPane = new JScrollPane(tree);
    frame.getContentPane().add(scrollPane, BorderLayout.CENTER);
    frame.setSize(300, 400);
    frame.setVisible(true);
    
  }
  //***************************************************************************
  //***************************************************************************
  //                         Array Builders: Phase Tasks
  //***************************************************************************
  //***************************************************************************
    public static Object[] getPhaseTasksArray()
    {
        if( Global.project !=null &&
            Global.project.getProcess()!=null && 
            Global.project.getProcess().getPhases()!=null&& 
            Global.project.getProcess().getPhases().get()!=null )
        {
        ArrayList<Phase> processPhases=Global.project.getProcess().getPhases().get();
        Object[] phaseArray=new Object[processPhases.size()];
            //--------------------------------------------------------------------------
        int i=0;
        for (Object o:processPhases)
         {
           Phase phase=new Phase();
           if (o!=null)
             {
             phase=(Phase) o;
             PreRequisites preRequisites=null;
             Objectives objectives=null;
             Planning planning=null;
             Performing performing=null;
             Validating validating=null;
             Reporting reporting=null;
             if (phase.getPreRequisites()!=null)
                  preRequisites=phase.getPreRequisites();
             else
                  preRequisites=new PreRequisites();

             if (phase.getObjectives()!=null)
                  objectives=phase.getObjectives();
             else
                  objectives=new Objectives();
                   

             if (phase.getPlanning()!=null)
                  planning=phase.getPlanning();
             else
                  planning=new Planning();
                  

             if (phase.getPerforming()!=null)
                  performing=phase.getPerforming();
             else
                  performing=new Performing();

             if (phase.getValidating()!=null)
                  validating=phase.getValidating();
             else
                  validating=new Validating();

             if (phase.getReporting()!=null)
                  reporting=phase.getReporting();
             else
                  reporting=new Reporting();

             Object taskArray[] ={ preRequisites,
                                   objectives,
                                   planning,
                                   performing,
                                   validating,
                                   reporting};


             // showing the phase name as the node and the task array elements as leafes
             Vector taskVector=new NamedVector(phase.getName().toString(),taskArray);

             // add the phase branch (its name and tasks vector) to the phase array
             phaseArray[i]=taskVector;
             i=i+1;
          }
        }
    return phaseArray;
    }
    else
    {   
        return null;
    }
    }



    //***************************************************************************
   //                  Phase Reports Array
   //***************************************************************************

    public static Object[] getPhaseReportsArray()
    {
    if( Global.project !=null &&
        Global.project.getProcess()!=null &&
        Global.project.getProcess().getPhases()!=null&&
        Global.project.getProcess().getPhases().get()!=null )
    {
    ArrayList<Phase> processPhases=Global.project.getProcess().getPhases().get();
    Object[] phaseArray=new Object[processPhases.size()];
    //--------------------------------------------------------------------------
    int i=0;
    for (Object o:processPhases)
     {
       Phase phase=new Phase();
       if (o!=null)
         {

         phase=(Phase) o;

         Reporting reporting=null;

         if (phase.getReporting()!=null)
              reporting=phase.getReporting();
         else
              reporting=new Reporting();

         Object taskArray[] ={reporting};

         // showing the phase name as the node and the task array elements as leafes
         Vector taskVector=new NamedVector(phase.getName().toString(),taskArray);

         // add the phase branch (its name and tasks vector) to the phase array
         phaseArray[i]=taskVector;
         i=i+1;
      }
    }
    return phaseArray;
   }
 else
     return null;
 }


  //***************************************************************************
  //                  Phases Versions Array
  //***************************************************************************

   public static Object[] getPhaseVersions(Phase phase)
  {

  boolean success =false;
  Object[] versions = null;
  if( phase!=null&& phase.getIteration()!=null&&phase.getIteration().size()>0)
    {
       versions = new Object[phase.getIteration().size()+1];
       versions[0]=phase;
        for (int i=1;i<phase.getIteration().size()+1;i++)
             versions[(phase.getIteration().size()+1)-i]=phase.getIteration().get(i-1);
       success=true;
    }
   if (success)
     return versions;
   else
     return null;
    
    }

   //***************************************************************************
  //                            Feedbacks Array
  //***************************************************************************

    public static Object[] getPhaseFeedbacksArray( Phase fromPhase)
    {
    if (Global.project!=null&&Global.project.getProcess()!=null && fromPhase!=null&& fromPhase.getNumber()>1)
    {
    ArrayList<Phase> processPhases=Global.project.getProcess().getPhases().get();
    Object[] phaseArray=new Object[fromPhase.getNumber()-1];
    //--------------------------------------------------------------------------
    int i=0;
    for (Object o:processPhases)
     {
       Phase phase=new Phase();
       if (o!=null)
         {
         phase=(Phase) o;
         if (phase.getNumber()<fromPhase.getNumber())
         {
             Object versionsArray[]=new Object[phase.getIteration().size()+1];
             if (getPhaseVersions(phase)!=null)
                 versionsArray=getPhaseVersions(phase);
             else
                 versionsArray[0]=phase;

         // showing the phase name as the node and the task array elements as leafes
         Vector versionsVector=new NamedVector(phase.getName().toString(),versionsArray);

         // add the phase branch (its name and tasks vector) to the phase array
         phaseArray[i]=versionsVector;

         i=i+1;
         }
      }
    }
    //System.out.print("if tree array size==>"+phaseArray.length);
    return phaseArray;

   }
  else{
     Object[] phaseArray={fromPhase};
     //System.out.print("else array size==>"+phaseArray.length);
     return phaseArray;
      }
}


  //***************************************************************************
  //                            Versions Array
  //***************************************************************************

    public static Object[] getPhasesVersionsArray()
    {

if( Global.project !=null &&
    Global.project.getProcess()!=null &&
    Global.project.getProcess().getPhases()!=null&&
    Global.project.getProcess().getPhases().get()!=null )
   {
    ArrayList<Phase> processPhases=Global.project.getProcess().getPhases().get();
    Object[] phaseArray=new Object[processPhases.size()];
    //--------------------------------------------------------------------------
    int i=0;
    for (Object o:processPhases)
     {
       Phase phase=new Phase();
       if (o!=null)
         {
         phase=(Phase) o;
             Object versionsArray[]=new Object[phase.getIteration().size()+1];
             if (getPhaseVersions(phase)!=null)
                 versionsArray=getPhaseVersions(phase);
             else
                 versionsArray[0]=phase;

         // showing the phase name as the node and the task array elements as leafes
         Vector versionsVector=new NamedVector(phase.getName().toString(),versionsArray);

         // add the phase branch (its name and tasks vector) to the phase array
         phaseArray[i]=versionsVector;

         i=i+1;

      }
    }
    return phaseArray;
    }
 else
     return null;
    }

}// closing the outer class


