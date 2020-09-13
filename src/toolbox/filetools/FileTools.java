/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package toolbox.filetools;
import toolbox.viewtools.DataFileViewer;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import java.util.List;
import javax.swing.JFrame;
import toolbox.Tools;
import weka.core.Attribute;
import weka.core.Capabilities;
import weka.core.Instances;
import weka.core.converters.AbstractSaver;
import weka.core.converters.ArffLoader;
import weka.core.converters.ArffSaver;
import weka.core.converters.CSVLoader;
import weka.core.converters.CSVSaver;
import weka.core.converters.ConverterUtils.DataSource;
import weka.filters.unsupervised.attribute.*;
import weka.gui.ComponentHelper;
import weka.gui.ConverterFileChooser;
/**
 *
 * @author amb04
 */
public class FileTools
{

//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
//****************************************************************************
//----------------------------------------------------------------------------
//                          View Data
//----------------------------------------------------------------------------
//****************************************************************************
 public static void viewData(Instances instances)
   {

     if (instances!=null)
     {
        try {                   
            File temp= new File("temp.arff");
            
            Tools.writeStringToFile(instances.toString(), temp.getPath());            
            
            FileTools.viewData(temp.getPath());            
            
            if (temp.exists())
                temp.delete();
            
            } catch (Exception ex) {
               Logger.getLogger(FileTools.class.getName()).log(Level.SEVERE, null, ex); 
           }
        
     }       
}


//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
//****************************************************************************
//----------------------------------------------------------------------------
//                          View Data
//----------------------------------------------------------------------------
//****************************************************************************
 public static void viewData(String dataFileString)
   {

     if (dataFileString!=null && new File(dataFileString).exists())
     {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(dataFileString));
            reader.close();
            String[] mainOptionArray = {dataFileString};
            DataFileViewer viewer = new DataFileViewer(mainOptionArray);

         } catch (Exception ex) {
            Logger.getLogger(FileTools.class.getName()).log(Level.SEVERE, null, ex);
         } finally
         {
           try {
               reader.close();
           } catch (IOException ex) {
               Logger.getLogger(FileTools.class.getName()).log(Level.SEVERE, null, ex); }
         }
       }
}

 

//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
//****************************************************************************
//----------------------------------------------------------------------------
//                          View Data
//----------------------------------------------------------------------------
//****************************************************************************
 public static JFrame viewData(File dataFile)
   {
     if (dataFile!=null && dataFile.exists())
     {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(dataFile.getPath()));
            reader.close();
            String[] mainOptionArray = {dataFile.getPath()};
            DataFileViewer viewer = new DataFileViewer(mainOptionArray);
            return viewer;

         } catch (Exception ex) {
            Logger.getLogger(FileTools.class.getName()).log(Level.SEVERE, null, ex);
            return null;
         } finally
         {
           try {
               reader.close();
               return null;
           } catch (IOException ex) {
               Logger.getLogger(FileTools.class.getName()).log(Level.SEVERE, null, ex); }
           return null;
         }
       }
     else
         return null;
}
 
 
//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
//****************************************************************************
//----------------------------------------------------------------------------
//                          Add Data
//----------------------------------------------------------------------------
//****************************************************************************
 public static  String AddData(String attributeName, String dataSet, boolean isClassAttributeLast)
   {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(dataSet));
            Instances data = new Instances(reader);
            reader.close();
            if (isClassAttributeLast)
                  data.setClassIndex(data.numAttributes() - 1);
            else
                  data.setClassIndex(0);
            Add add = null;
            add.setAttributeName(attributeName);
            Instances resultDataInstances = Add.useFilter(data, add);
            return resultDataInstances.toString();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FileTools.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } catch (IOException ex) {
            Logger.getLogger(FileTools.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } catch (Exception ex) {
            Logger.getLogger(FileTools.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } finally {
            try {
                reader.close();
            } catch (IOException ex) {
                Logger.getLogger(FileTools.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }


//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
//****************************************************************************
//----------------------------------------------------------------------------
//                          Add Data
//----------------------------------------------------------------------------
//****************************************************************************
 public static  Instances addAttribute(String attributeName,String type,ArrayList<String> nominal_values,Instances instances, int index)
   {
     if (attributeName!=null && type!=null && instances!=null )
     {
        try {
            //-----------------------------------------------------------------
            Attribute attribute=null;
            if (type.toLowerCase().equals("numeric"))
            {
                attribute= new Attribute(attributeName);
            }

            else if (type.toLowerCase().equals("nominal"))
            {
                List<String> valuesList =(List<String>) nominal_values;
                attribute= new Attribute(attributeName,valuesList);
            }

            else if (type.toLowerCase().equals("string"))
            {
                List<String> s=null;
                attribute= new Attribute(attributeName,s);
            }

            else if (type.toLowerCase().equals("date"))
            {
                //String dateFormat= "dd-MM-yyyy HH:mm:ss";
                String dateFormat= "dd-mm-yyyy";
                attribute= new Attribute(attributeName,dateFormat);
            }

            instances.insertAttributeAt(attribute,index-1);

           return instances;

            } catch (Exception ex) {
                Logger.getLogger(FileTools.class.getName()).log(Level.SEVERE, null, ex);
                return null;
            }
    }
    else
         return null;
    }
  
 
 
//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
//****************************************************************************
//----------------------------------------------------------------------------
//                          Add Data
//----------------------------------------------------------------------------
//****************************************************************************
 public static  Instances addAttribute(String attributeName,String type,ArrayList<String> nominal_values,Instances instances)
   {
     if (attributeName!=null && type!=null && instances!=null )
     {
        try {
            //-----------------------------------------------------------------
            Attribute attribute=null;
            if (type.toLowerCase().equals("numeric"))
            {
                attribute= new Attribute(attributeName);
            }

            else if (type.toLowerCase().equals("nominal"))
            {
                List<String> valuesList =(List<String>) nominal_values;
                attribute= new Attribute(attributeName,valuesList);
            }

            else if (type.toLowerCase().equals("string"))
            {
                List<String> s=null;
                attribute= new Attribute(attributeName,s);
            }

            else if (type.toLowerCase().equals("date"))
            {
                //String dateFormat= "dd-MM-yyyy HH:mm:ss";
                String dateFormat= "dd-mm-yyyy";
                attribute= new Attribute(attributeName,dateFormat);
            }

            instances.insertAttributeAt(attribute,instances.numAttributes()-1);

           return instances;

            } catch (Exception ex) {
                Logger.getLogger(FileTools.class.getName()).log(Level.SEVERE, null, ex);
                return null;
            }
    }
    else
         return null;
    }
 
/*

//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
//****************************************************************************
//----------------------------------------------------------------------------
//                          Load Data
//----------------------------------------------------------------------------
//****************************************************************************
 public  static File LoadData(File file)
 {
//     int classAtColumnNumber = Integer.parseInt(ComponentHelper.showInputBox(null, "Class Column Chooser", "Enter the number of column where the class is defined", "-1"));
     if (file !=null)
     {
          String fileParentPath= file.getParent();
          String fileName=Tools.getFileNameOnly(file);
          String fileExtention=Tools.getFileExtentionOnly(file);
          try {
              
              Instances instances = DataSource.read(file.getPath());
              
//                DataSource source = new DataSource(file.getPath());
//                Instances instances = source.getDataSet();
               
               
//                if (classAtColumnNumber>0)
//                    instances.setClassIndex(classAtColumnNumber - 1);
//                else
                
              
              //instances.setClassIndex(instances.numAttributes()-1);
                //write instances to temp arff file
                File arffFile=new File(fileParentPath+"/"+fileName+".arff");

                if (arffFile.exists())
                    arffFile.delete();

                Tools.writeStringToFile(instances.toString(),arffFile.getPath());
                
                // convert
                File csvFile=FileTools.ARFFToCSV(arffFile);

                // delete
                if (arffFile.exists())
                    arffFile.delete();
                return csvFile;

         } catch (Exception ex)
         {
            JOptionPane.showMessageDialog(null, "Problem in opening the data File!");
            Logger.getLogger(FileTools.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
     }
     else
         return null;
 }

*/

//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
//****************************************************************************
//----------------------------------------------------------------------------
//                          Load Data
//----------------------------------------------------------------------------
//****************************************************************************
 public  static File LoadData(File inFile)
 {
     if (inFile !=null)
     {
          String fileParentPath= inFile.getParent();
          String fileName=Tools.getFileNameOnly(inFile);
          String fileExtention=Tools.getFileExtentionOnly(inFile);
          if (!fileExtention.toLowerCase().equals("csv"))
          {
              File outFile=new File(fileParentPath+"/"+fileName+".csv");              
              
              try
              {
                  outFile = FileTools.convertFile(inFile.getPath(), outFile.getPath());
                  if (outFile!=null)
                      return outFile;
                  else
                      return null;

             } catch (Exception ex)
             {
                JOptionPane.showMessageDialog(null, "Problem in opening the data File!");
                Logger.getLogger(FileTools.class.getName()).log(Level.SEVERE, null, ex);
                return null;
            }
          }
          else
          {
              JOptionPane.showMessageDialog(null, "The file you are trying to load is already in *.csv file format!");
              return null;
          }
     }
     else
         return null;
 }



//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
//****************************************************************************
//----------------------------------------------------------------------------
//                          Load Data
//----------------------------------------------------------------------------
//****************************************************************************
 public  static File LoadData(File fromFile, File tofile)
 {
     if (fromFile !=null)
     {
        if (!fromFile.getPath().toLowerCase().equals(tofile.getPath().toLowerCase()))
       {
              try {

                    // convert
                    //-------------------------------------------------------------
                    tofile = FileTools.convertFile(fromFile.getPath(), fromFile.getPath());

                    return tofile;

             } catch (Exception ex)
             {
                JOptionPane.showMessageDialog(null, "Problem in opening the data File!");
                Logger.getLogger(FileTools.class.getName()).log(Level.SEVERE, null, ex);
                return null;
            }

       }
         else
            {
              JOptionPane.showMessageDialog(null, "the loaded source file and the destination are the same");
              return null;
            }
    }
     else
     {
         JOptionPane.showMessageDialog(null, "source file does not exist");
         return null;
     }
 }


//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
//****************************************************************************
//----------------------------------------------------------------------------
//                          Load Data
//----------------------------------------------------------------------------
//****************************************************************************
 public  static File LoadData(File fromFile, String targetFilEextention)
 {
   if (fromFile !=null)
   {
          String fileParentPath= fromFile.getParent();
          String fileName=Tools.getFileNameOnly(fromFile);
          String fileExtention=Tools.getFileExtentionOnly(fromFile);

      if (!targetFilEextention.toLowerCase().contains(fileExtention.toLowerCase()))
        {
              if (!targetFilEextention.contains("."))
                   targetFilEextention="."+targetFilEextention;

              File tofile=new File(fileParentPath+"/"+fileName+targetFilEextention);
              try {
                    // convert
                    //-------------------------------------------------------------
                    tofile = FileTools.convertFile(fromFile.getPath(), fromFile.getPath());

                    return tofile;

             } catch (Exception ex)
             {
                JOptionPane.showMessageDialog(null, "Problem in opening the data File!");
                Logger.getLogger(FileTools.class.getName()).log(Level.SEVERE, null, ex);
                return null;
            }
       }
     else
     {
         JOptionPane.showMessageDialog(null, " the target file type is the same as the orginal!");
         return null;
     }
   }
   else
       return null;

 }

 
//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
//****************************************************************************
//----------------------------------------------------------------------------
//                          Load Data
//----------------------------------------------------------------------------
//****************************************************************************
 public  static Instances LoadData(String fileString)
 {
     if (fileString !=null)
        try {
            Instances instances = DataSource.read(fileString);
            instances.setClassIndex(instances.numAttributes()-1);
            return instances;

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Problem in opening the data File!");
            Logger.getLogger(FileTools.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
     else
         return null;
 }

//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
//****************************************************************************
//----------------------------------------------------------------------------
//                          Load Data
//----------------------------------------------------------------------------
//****************************************************************************
 public  static Instances LoadData(File file, boolean assignClass)
 {
     if (file !=null)
        try {
            Instances instances = DataSource.read(file.getPath());
            
            if (assignClass==true)
                instances.setClassIndex(instances.numAttributes()-1);
                        
            return instances;

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Problem in opening the data File!");
            Logger.getLogger(FileTools.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
     else
         return null;
 }

//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
//****************************************************************************
//----------------------------------------------------------------------------
//                          Load Data
//----------------------------------------------------------------------------
//****************************************************************************
 public  static Instances LoadData(String file, boolean selectClassAttribute)
 {

    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
     int classAtColumnNumber=-1;
     if (selectClassAttribute==true)
         classAtColumnNumber = Integer.parseInt(ComponentHelper.showInputBox(null,
                                                "Class Column Chooser", "Enter the "
                                                + "number of column where the class "
                                                + "is defined", "-1"));

     //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
     if (file !=null)
        try {
            Instances instances = DataSource.read(file);

            // assign the class attribute
            //-----------------------------------------------------------------
            if (selectClassAttribute==true)
            {
                if (classAtColumnNumber>0)
                    instances.setClassIndex(classAtColumnNumber - 1);
                else
                    instances.setClassIndex(instances.numAttributes()-1);
            }

            return instances;

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Problem in opening the data File!");
            Logger.getLogger(FileTools.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
     else
         return null;
 }


//-----------------------------------------------------------------------------
//*****************************************************************************
//       universal file converter - calling the file cooser to select target
//*****************************************************************************
//-----------------------------------------------------------------------------
public static File convertFile(String fromFileName)
  {
    try {
        ConverterFileChooser fileChooser = new ConverterFileChooser();
        AbstractSaver saver;
        fileChooser.setCapabilitiesFilter(Capabilities.forInstances(FileTools.LoadData(fromFileName)));
        int retVal = fileChooser.showSaveDialog(null);
        //if (retVal != ConverterFileChooser.APPROVE_OPTION)
        //  return;
        String toFileName = fileChooser.getSelectedFile().getAbsolutePath();
        File fromFile = new File(fromFileName);
        File toFile = new File(toFileName);
        fileChooser.setSelectedFile(toFile);
        saver = fileChooser.getSaver();
        if (saver != null) {
            try {
                saver.setInstances(FileTools.LoadData(fromFileName));
                saver.writeBatch();
                
                if (fromFile.exists())
                    fromFile.delete();
                
                return toFile;
            } catch (IOException ex) {
                Logger.getLogger(FileTools.class.getName()).log(Level.SEVERE, null, ex);
                return null;
            }
        } else {
            JOptionPane.showMessageDialog(null, "No File was selected");
            return null;
        }
    } catch (Exception ex) {
        Logger.getLogger(FileTools.class.getName()).log(Level.SEVERE, null, ex);
        return null;
    }
  }



//-----------------------------------------------------------------------------
//*****************************************************************************
//    universal file converter - calling the file cooser to select target
//*****************************************************************************
//-----------------------------------------------------------------------------
public static File convertFile(String fromFileName, boolean keepConvertedFile)
  {
    try {
        ConverterFileChooser fileChooser = new ConverterFileChooser();
        AbstractSaver saver;
        fileChooser.setCapabilitiesFilter(Capabilities.forInstances(FileTools.LoadData(fromFileName)));
        int retVal = fileChooser.showSaveDialog(null);
        //if (retVal != ConverterFileChooser.APPROVE_OPTION)
        //  return;
        String toFileName = fileChooser.getSelectedFile().getAbsolutePath();
        File fromFile = new File(fromFileName);
        File toFile = new File(toFileName);
        fileChooser.setSelectedFile(toFile);
        saver = fileChooser.getSaver();
        if (saver != null) {
            try {
                saver.setInstances(FileTools.LoadData(fromFileName));
                saver.writeBatch();

                if (keepConvertedFile==false && fromFile.exists())
                    fromFile.delete();

                return toFile;
            } catch (IOException ex) {
                Logger.getLogger(FileTools.class.getName()).log(Level.SEVERE, null, ex);
                return null;
            }
        } else {
            JOptionPane.showMessageDialog(null, "No File was selected");
            return null;
        }
    } catch (Exception ex) {
        Logger.getLogger(FileTools.class.getName()).log(Level.SEVERE, null, ex);
        return null;
    }
  }


//-----------------------------------------------------------------------------
//*****************************************************************************
//                           universal file converter
//*****************************************************************************
//-----------------------------------------------------------------------------
public static File convertFile(String fromFileName,String toFileName)
  {
    ConverterFileChooser fileChooser = new ConverterFileChooser();
    AbstractSaver       saver;
    
    File fromFile = new File(fromFileName);
    File toFile = new File(toFileName);
    
    // to overwrite the file if it already exists
    //-------------------------------------------------------------------------
    if (toFile.exists())
        toFile.delete();
    
    fileChooser.setSelectedFile(toFile);
    saver = fileChooser.getSaver();
    if (saver!=null)
    {
        try {

            saver.setInstances(FileTools.LoadData(fromFileName));
            saver.writeBatch();

            if (fromFile.exists())
                fromFile.delete();

            return toFile;
        } catch (IOException ex) {
            Logger.getLogger(FileTools.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
     }
    else
    {
        JOptionPane.showMessageDialog(null, "No File was selected");
        return null;
     }
  }

//-----------------------------------------------------------------------------
//*****************************************************************************
//                           universal file converter
//*****************************************************************************
//-----------------------------------------------------------------------------
public static File convertFile(String fromFileName,String toFileName, boolean keepConvertedFile)
  {
    ConverterFileChooser fileChooser = new ConverterFileChooser();
    AbstractSaver       saver;

    File fromFile = new File(fromFileName);
    File toFile = new File(toFileName);

    fileChooser.setSelectedFile(toFile);
    saver = fileChooser.getSaver();
    if (saver!=null)
    {
        try {

            saver.setInstances(FileTools.LoadData(fromFileName));
            saver.writeBatch();

            if ( keepConvertedFile==false && fromFile.exists())
                fromFile.delete();

            return toFile;
        } catch (IOException ex) {
            Logger.getLogger(FileTools.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
     }
    else
    {
        JOptionPane.showMessageDialog(null, "No File was selected");
        return null;
     }
  }


//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
//****************************************************************************
//----------------------------------------------------------------------------
//                          CSV TO ARFF
//----------------------------------------------------------------------------
//****************************************************************************
  public static File CSVToARFF(File csvFile)
  {
      File arffFile=null;
      if (csvFile!=null)
        try {
            
            // load CSV
            //-----------------------------------------------------------------
            CSVLoader loader = new CSVLoader();
            loader.setSource(csvFile);
            Instances instances= loader.getDataSet();            
            String arffFileString=csvFile.getParent()+"/"+Tools.getFileNameOnly(csvFile)+".arff";
            arffFile=new File (arffFileString);

            // save ARFF
            //-----------------------------------------------------------------
            ArffSaver saver = new ArffSaver();
            saver.setInstances(instances);
            saver.setFile(arffFile);
            saver.writeBatch();

        } catch (IOException ex) {
            Logger.getLogger(FileTools.class.getName()).log(Level.SEVERE, null, ex);            
        }
      return arffFile;
  }


//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
//****************************************************************************
//----------------------------------------------------------------------------
//                          CSV TO ARFF
//----------------------------------------------------------------------------
//****************************************************************************
  public static void CSVToARFF(String csvFile,String arffFile, boolean isClassAttributeLast)
  {
      if (csvFile!=null)
        try {
            
            // load CSV
            //-----------------------------------------------------------------
            CSVLoader loader = new CSVLoader();
            loader.setSource(new File(csvFile));
            Instances instances= loader.getDataSet();

            // save ARFF
            //-----------------------------------------------------------------
            ArffSaver saver = new ArffSaver();
            saver.setInstances(instances);
            saver.setFile(new File(arffFile));
            saver.writeBatch();

        } catch (IOException ex) {
            Logger.getLogger(FileTools.class.getName()).log(Level.SEVERE, null, ex);
        }
  }


//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
//****************************************************************************
//----------------------------------------------------------------------------
//                          CSV TO ARFF
//----------------------------------------------------------------------------
//****************************************************************************
  public static File ARFFToCSV(File arffFile)
  {
      File csvFile=null;
      if (arffFile!=null)
        try {

            // load arff
            //-----------------------------------------------------------------
            ArffLoader loader = new ArffLoader();
            loader.setSource(arffFile);
            Instances instances= loader.getDataSet();
            String CSVFileString=arffFile.getParent()+"/"+Tools.getFileNameOnly(arffFile)+".csv";
            csvFile=new File (CSVFileString);

            // save ARFF
            //-----------------------------------------------------------------
            CSVSaver saver = new CSVSaver();
            saver.setInstances(instances);
            saver.setFile(csvFile);
            saver.writeBatch();

        } catch (IOException ex) {
            Logger.getLogger(FileTools.class.getName()).log(Level.SEVERE, null, ex);
        }

      arffFile.delete();
      return csvFile;
  }

//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
//****************************************************************************
//----------------------------------------------------------------------------
//                          ARFF To CSV
//----------------------------------------------------------------------------
//****************************************************************************
 public static void ARFFToCSV(String arffFile,String csvFile,boolean isClassAttributeLast)
 {
     if (arffFile!=null)
        try {

            // load CSV
            //----------------------------------------------------------------
            ArffLoader loader = new ArffLoader();
            loader.setSource(new File(arffFile));
            Instances instances=loader.getDataSet();

            // save ARFF
            //----------------------------------------------------------------
            CSVSaver saver = new CSVSaver();
            saver.setInstances(instances);
            saver.setFile(new File(csvFile));            
            saver.writeBatch();

        } catch (IOException ex) {
            Logger.getLogger(FileTools.class.getName()).log(Level.SEVERE, null, ex);
        }
  }

}
