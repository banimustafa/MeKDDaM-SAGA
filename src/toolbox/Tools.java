package toolbox;
import global.Config;
import toolbox.filetools.CustomFileFilter;
import global.Global;
import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.net.MalformedURLException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Date;
import java.util.TimeZone;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import process_model.process.input.data.utility.DataRow;
 public class Tools {
     
//-----------------------------------------------------------------------------     
public static double Round(double number, int precision) 
{
  float roundingPrecision = (float)Math.pow(10,precision);
  number = number * roundingPrecision;
  float roundedNumber = Math.round(number);
  return (double)roundedNumber/roundingPrecision;
}

//-----------------------------------------------------------------------------
public  static URL toURL(File file)
{
        try {            
            
            URL url=file.toURL();            
            return url;
        } catch (MalformedURLException ex) 
        {            
            Logger.getLogger(Tools.class.getName()).log(Level.SEVERE, null, ex);            
            return null;
        }    
}

//-----------------------------------------------------------------------------
public  static File toFile(URL url)
{
       try {        
                      
            return new File(url.getFile());
            
        } catch (Exception ex) 
        {            
            Logger.getLogger(Tools.class.getName()).log(Level.SEVERE, null, ex);            
            return null;
        }    
}

//----------------------------------------------------------------------------

public static Date getTime() 
{
    Calendar cal = new GregorianCalendar(TimeZone.getTimeZone("London"));    
    return cal.getTime();
}

public String readKeyboard()
{

Scanner in=new Scanner(System.in);

//System.out.print("enter name e.g c:/test.txt : ");

String s=in.next();

return s;

}


/*
//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
public static boolean deleteDirectory(File directory) 
{
   if (directory.isDirectory()) 
    {
            String[] children = directory.list();
        
            for (int i=0; i<children.length; i++) 
            {
                boolean success = Tools.deleteDirectory(new File(directory, children[i]));
                if (!success) 
                    return false;                
            }
      }
    else
       JOptionPane.showMessageDialog(null,"No directory to be deleted!");
        
    return directory.delete();
}

*/


public static boolean deleteDirectory(File directory) 
{
    if( directory.exists() ) 
    {
      File[] files = directory.listFiles();
      for(int i=0; i<files.length; i++) 
      {
         if(files[i].isDirectory()) 
         {
           Tools.deleteDirectory(files[i]);
         }
         else 
         {
           files[i].delete();
         }
      }
    }    
    return( directory.delete() );
  }

//****************************************************************************

public ArrayList<String> fileToArrayList(ArrayList<String> arrayList , String inFileName)
{
   try {
        Scanner readerFileScanner = new Scanner(new FileReader(new File(inFileName)));
        while (readerFileScanner.hasNext()) {
            // read from the FileScanner
            // read from the FileScanner
            String s = readerFileScanner.nextLine();
            arrayList.add(s);
        }
        return arrayList;
    } catch (FileNotFoundException ex) {
        Logger.getLogger(Tools.class.getName()).log(Level.SEVERE, null, ex);
        return null;
    }
}

public static File renameFileTo(String oldFileName, String newFileName )
{
    File oldFile=new File(oldFileName);
    File newFile=new File(newFileName);

    if (!oldFileName.equals(newFileName))
    {
        try
        {
            // if a file exists with the same name delete it
            //----------------------------------------------------------------
            if (new File(newFileName).exists()) 
                new File(newFileName).delete();
            
//            System.out.println("\n1- newFile after deleting its phiscal copy **> "+newFile.getPath());
            boolean renameSuccessfull = oldFile.renameTo(newFile);
            
//            System.out.println("\n2- oldFile After Renaming **> "+oldFile);
            
            if (renameSuccessfull==true)
            {
                // delete the file with the old name
                //------------------------------------------------------------
//                System.out.println("\n3- oldFile before physically deleted **> "+oldFile.getPath()); 
                if (new File(oldFileName).exists()) 
                {
//                    System.out.println("\n4- oldFile just before physically deleted **> "+oldFile.getPath());
                    new File(oldFileName).delete();                    
                }

                return newFile;
            }
            else
            {
                JOptionPane.showMessageDialog(null,"Problem in renaming the file See RenameFileTo code in Tools Class");
                return null;
            }
        }
        catch (Exception x)
        {
            JOptionPane.showMessageDialog(null,"Problem in renaming the file See RenameFileTo code in Tools Class");
            return null;
        }
    }
    else
        return oldFile;
}



public static ArrayList<String> fileToArrayList(String inFileName)
{
        try {
            Scanner readerFileScanner = new Scanner(new FileReader(new File(inFileName)));
            ArrayList<String> arrayList = new ArrayList<String>();
            while (readerFileScanner.hasNext()) {
                // read from the FileScanner
                String s = readerFileScanner.nextLine();
                arrayList.add(s);
            }
            return arrayList;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Tools.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
}



//-------------------------------------------------------------------------
public static ArrayList<DataRow> fileToTokenisedArrayList(String inFileName)throws IOException
{
    Scanner readerFileScanner=new Scanner(new FileReader(new  File(inFileName)));
    DataRow raw=null;
    String[] valuesArray=null;
    ArrayList<String> stringsArrayList=new ArrayList<String>();
    while (readerFileScanner.hasNext())
    {    
        String s=readerFileScanner.nextLine();
        stringsArrayList.add(s);
    }

    //----------------------------------------------------------------------
    ArrayList<DataRow> rawsArrayList=new ArrayList<DataRow>();
    for(Object o:stringsArrayList )
    {
        String s=(String) o;
        valuesArray=s.split("	");
        raw=new DataRow();
        for (int i=0; i<valuesArray.length;i++)        
             raw.add(valuesArray[i]);
             
        rawsArrayList.add(raw);
    }
return rawsArrayList;
}

//-------------------------------------------------------------------------------------------------------------------------------------------------------

public static void writeArrayListToFile(ArrayList<String> arrayList, String outFileName)throws IOException

{
FileWriter writerFileStream=new FileWriter(new File(outFileName));

for (Object i:arrayList)
{
    writerFileStream.write(i+"\n");
}

writerFileStream.flush();
writerFileStream.close(); 

}

//-------------------------------------------------------------------------------------------------------------------------------------------------------
public static void appendArrayListToFile(ArrayList<String> arrayList, String outFileName)throws IOException

{
FileWriter writerFileStream=new FileWriter(new File(outFileName),true);
for (Object i:arrayList)

{
writerFileStream.append(i+"\n");
}

writerFileStream.flush();
writerFileStream.close(); 

}
//-------------------------------------------------------------------------------------------------------------------------------------------------------
public static void appendStringToFile(String string, String outFileName)

{
    FileWriter writerFileStream = null;

    //------------------------------------------------------------------------
    try {
        writerFileStream = new FileWriter(new File(outFileName), true);
        writerFileStream.append(string + "\n");
        //writerFileStream.flush();
        writerFileStream.close();

    //------------------------------------------------------------------------
    } catch (IOException ex) {
        Logger.getLogger(Tools.class.getName()).log(Level.SEVERE, null, ex);

    //------------------------------------------------------------------------
    } finally
    {
        try {
            writerFileStream.close();
        } catch (IOException ex) {
            Logger.getLogger(Tools.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}


//-------------------------------------------------------------------------------------------------------------------------------------------------------
public static void writeStringToFile(String string, String outFileName)
{
if (new File(outFileName).exists())
    new File(outFileName).delete();
    /*
    if (!new File(outFileName).exists())
        new File (new File(outFileName).getParent()).mkdirs();
*/
    try {
        FileWriter writerFileStream = new FileWriter(new File(outFileName), false);
        writerFileStream.write(string + "\n");
        writerFileStream.flush();
        writerFileStream.close();
    } catch (IOException ex) {
        Logger.getLogger(Tools.class.getName()).log(Level.SEVERE, null, ex);
    }

}

//-------------------------------------------------------------------------------------------------------------------------------------------------------
public static void appendStringToFile(String string, File outFile)
{
    if (!outFile.exists())
        new File (outFile.getParent()).mkdirs();

    try {
        FileWriter writerFileStream = new FileWriter(outFile, true);
        writerFileStream.write(string + "\n");
        writerFileStream.flush();
        writerFileStream.close();
    } catch (IOException ex) {
        Logger.getLogger(Tools.class.getName()).log(Level.SEVERE, null, ex);
    }

}

//-------------------------------------------------------------------------------------------------------------------------------------------------------

public static void printArrayList(ArrayList<String> arraList)
{
for (Object i:arraList)
{
      //System.out.print(i);

}

}

//-------------------------------------------------------------------------------------------------------------------------------------------------------

public static String pickRandomElementFromArrayList(ArrayList<String> arraList)

{

return arraList.get((new Random().nextInt(arraList.size())));


}

public static boolean host (String executionString)  throws IOException
{    
   // String app=ap;
    Process process=null;
    Runtime newRuntime = Runtime.getRuntime();   
     //   String app1;
       // String app2;
      try
      {
          process = newRuntime.exec(executionString);
          return true;
      }

      catch(Exception x)
      {      
         JOptionPane.showMessageDialog(null,"Executable Hosted Program Not Found!");         
         return false;
         /*Tools t=new Tools();
         app1=t.chooseFile();
         JOptionPane.showMessageDialog(null," Now Choose the File you want to open");
         //System.out.print(app1);
         app2=t.chooseFile();
         //System.out.print(app2);
         app=app1+" "+app2;
         Process info = newRuntime.exec(app);  
          */
      }
  }


//----------------------------------------------------------------------------
//----------------------------( File Chooser)---------------------------------
//----------------------------------------------------------------------------
public static File chooseFile(String title,
                              CustomFileFilter filter,
                              String defaultDirectory,
                              boolean directoryOnly)
{

if (defaultDirectory==null && Global.project!=null && Global.project.getLocation()!=null && new File(Global.project.getLocation()).exists())
    defaultDirectory=Global.project.getLocation();

final JFileChooser jfc = new JFileChooser();
int returnVal;

//--set the title of the file chooser
//----------------------------------------------------------------------------
jfc.setDialogTitle(title);

//--default directory
//----------------------------------------------------------------------------
if (defaultDirectory==null)
    jfc.setCurrentDirectory(new File("."));
else
    jfc.setCurrentDirectory(new File(defaultDirectory));

//--add filter if exists
//----------------------------------------------------------------------------
if (filter!=null) {
   jfc.addChoosableFileFilter(filter);
   jfc.setAcceptAllFileFilterUsed(false);}

//--folders only
//----------------------------------------------------------------------------
if (directoryOnly==true)
{
     jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
     returnVal=jfc.showDialog(jfc,"Create in folder ...");
 }
 else
     returnVal = jfc.showOpenDialog(null);

//--validate options
//----------------------------------------------------------------------------
if( returnVal==JFileChooser.APPROVE_OPTION)
      return jfc.getSelectedFile();
else
    return null;

}



//ooooooooooooooooooo( Overloaded file chooser )ooooooooooooooooooooooooooooooo

//----------------------------------------------------------------------------
//----------------------------( File Chooser)---------------------------------
//----------------------------------------------------------------------------
public static String chooseFile(String title,
                              String fileExt, String fileExtMessage,
                              String defaultDirectory,
                              boolean directoryOnly)
{
if (defaultDirectory==null && Global.project!=null && Global.project.getLocation()!=null && new File(Global.project.getLocation()).exists())
    defaultDirectory=Global.project.getLocation();

final JFileChooser jfc = new JFileChooser();
int returnVal;
CustomFileFilter filter= new CustomFileFilter(fileExt,fileExtMessage);
//--set the title of the file chooser
//----------------------------------------------------------------------------
jfc.setDialogTitle(title);

//--default directory
//----------------------------------------------------------------------------
if (defaultDirectory==null)
    jfc.setCurrentDirectory(new File("."));
else
    jfc.setCurrentDirectory(new File(defaultDirectory));

//--add filter if exists
//----------------------------------------------------------------------------
if (filter!=null) {
   jfc.addChoosableFileFilter(filter);
   jfc.setAcceptAllFileFilterUsed(false);}

//--folders only
//----------------------------------------------------------------------------
if (directoryOnly==true)
{
     jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
     returnVal=jfc.showDialog(jfc,"Create in folder ...");
 }
 else
     returnVal = jfc.showOpenDialog(null);

//--validate options
//----------------------------------------------------------------------------
if( returnVal==JFileChooser.APPROVE_OPTION)
      return jfc.getSelectedFile().getPath();
else
{
    //System.out.print("Opps... No File Has been Choosen or invalid option \n");
    return null;
}

}


//ooooooooooooooooooo( Overloaded file chooser )ooooooooooooooooooooooooooooooo

//----------------------------------------------------------------------------
//----------------------------( File Chooser)---------------------------------
//----------------------------------------------------------------------------
public static String chooseFile(String title,
                              String[] fileExtentionsArray,
                              String[] fileExtMessage,
                              String defaultDirectory,
                              boolean directoryOnly)
{
    
 if (defaultDirectory==null && Global.project!=null && Global.project.getLocation()!=null && new File(Global.project.getLocation()).exists())
    defaultDirectory=Global.project.getLocation();
 
final JFileChooser jfc = new JFileChooser();
int returnVal;

//--set the title of the file chooser
//----------------------------------------------------------------------------
jfc.setDialogTitle(title);

//--default directory
//----------------------------------------------------------------------------
if (defaultDirectory==null)
    jfc.setCurrentDirectory(new File("."));
else
    jfc.setCurrentDirectory(new File(defaultDirectory));

//--add filter if exists
//----------------------------------------------------------------------------
if (fileExtentionsArray!=null)
for (int i=0;i<fileExtentionsArray.length;i++)
{
    CustomFileFilter filter= new CustomFileFilter(fileExtentionsArray[i],fileExtMessage[i]);
    jfc.addChoosableFileFilter(filter);
}
jfc.setAcceptAllFileFilterUsed(false);

//--folders only
//----------------------------------------------------------------------------
if (directoryOnly==true)
{
     jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
     returnVal=jfc.showDialog(jfc,"Create in folder ...");
 }
 else
     returnVal = jfc.showOpenDialog(null);

//--validate options
//----------------------------------------------------------------------------
if( returnVal==JFileChooser.APPROVE_OPTION)
      return jfc.getSelectedFile().getPath();
else
{
    //System.out.print("Opps... No File Has been Choosen or invalid option \n");
    return null;
}

}


 public static String getFileExtentionOnly(File file) 
 {
  String fileName = file.getPath(); 
  String ext="";
  int mid= fileName.lastIndexOf("."); 
  ext=fileName.substring(mid+1,fileName.length());    
  return ext;
  }



//*****************************************************************************
public static String  getFileNameOnly(File file)
{
  String fileName = file.getName();
  String fname="";
  int mid= fileName.lastIndexOf(".");
  fname=fileName.substring(0,mid);
  return fname;
}


//*****************************************************************************
//****************************************************************************
public static    void  viewFile(File file)
{
        try {            
                //Tools.host("c:/windows/notepad.exe"+" "+file.getPath());                
                Tools.host("C:/Windows/System32/write.exe"+" /r "+"\""+file.getPath()+"\"");
            } 
        catch( Exception e) 
            {
            }
}


//*****************************************************************************
//****************************************************************************
 
public static void viewAnyFile(File file) 
{ 

            /*
            File thisFile=null;
            //String fileString=null;
            System.out.println(Config.defaultLocation.substring(2, Config.defaultLocation.length()-1));
            if (file.getPath().contains(Config.defaultLocation.substring(2, Config.defaultLocation.length()-1)))
            {    
              try {
                  thisFile=file.getCanonicalFile();
                  // fileString=file.getCanonicalPath(); // both workd fine
              } catch (IOException ex) {
                Logger.getLogger(Tools.class.getName()).log(Level.SEVERE, null, ex);
              }
              //  thisFile= new File(fileString);    
            }
            else
                thisFile=file;
            */
    
    
try {    
    file=file.getCanonicalFile();    
} catch (IOException ex) {
JOptionPane.showMessageDialog(null, "problems converting the file into a cannonical file!\n"+file.getPath());
Logger.getLogger(Tools.class.getName()).log(Level.SEVERE, null, ex);
}

//-----------------------------------------------------------------------------        
if (file.exists())
{
  Desktop desktop = Desktop.getDesktop();
  if (Desktop.isDesktopSupported() && desktop.isSupported(Desktop.Action.OPEN)) 
  {
    try {
        desktop.open(file);
    } catch (IOException ex) {
        Logger.getLogger(Tools.class.getName()).log(Level.SEVERE, null, ex);
    }
  }
}
else
    JOptionPane.showMessageDialog(null, "File was not found!\n"+file.getPath());
}
 
//*****************************************************************************
//****************************************************************************
public static    void  viewExcelFile(File file)
{
        try {
            //------------------------------------------------------------------------------
      Tools.host("C:/Program Files/Microsoft Office/Office12/EXCEL.EXE"+" /r "+"\""+file.getPath()+"\"");
            //------------------------------------------------------------------------------
            }
        catch( Exception e)
            {
            }
}

//*****************************************************************************
//****************************************************************************
public static  void  viewXMLFile(File file)
{
        try {
            //------------------------------------------------------------------------------
             //Tools.host("c:/windows/notepad.exe"+" "+file.getPath()+".xml");
             Tools.host("C:/Windows/System32/write.exe"+" /r "+"\""+file.getPath()+".xml"+"\"");
            //------------------------------------------------------------------------------
            }
        catch( Exception e)
            {
            }
}

public static String fileToString(String inFile)
{
    String resultString="";    
    if (inFile!=null)
    {
        ArrayList<String> fileToArrayList = Tools.fileToArrayList(inFile);

        for (Object o:fileToArrayList)
          {
            if (o!=null)
             {
               String s=(String) o;
               resultString=resultString+s+"\n";
             }
          }
           return resultString;
    }
    else
        return null;
 }

//*****************************************************************************
public static void moveFile(String inFileString, String outFileString){
try
  {
    if (new File(inFileString).exists())
    {
        if (!inFileString.toLowerCase().equals(outFileString.toLowerCase()) )
            {
                String fileContentsStrings=Tools.fileToString(inFileString);
                Tools.writeStringToFile(fileContentsStrings, outFileString);
            }
//        else
 //           System.out.println(" source and destination files are identical");
    }
    else
        JOptionPane.showMessageDialog(null," Moving was unsuccessfull! source file does not exist");


    
    if (new File(inFileString).exists())
        new File(inFileString).delete();

  }
  catch(Exception e){
      Logger.getLogger(Tools.class.getName()).log(Level.SEVERE, null, e);
  }

}

//*****************************************************************************
public static void copyFile(String inFileString, String outFileString){
try
{
    if (new File(inFileString).exists())
    {
        if (!inFileString.toLowerCase().equals(outFileString.toLowerCase()))
            {
                String fileContentsStrings=Tools.fileToString(inFileString);
                Tools.writeStringToFile(fileContentsStrings, outFileString);
            }
//        else
//            System.out.println("source and destination files are identical");
    }
    else
        JOptionPane.showMessageDialog(null," copying was unsuccessfull! source file does not exist");
  }

  catch(Exception e){
      Logger.getLogger(Tools.class.getName()).log(Level.SEVERE, null, e);
  }
}



//*****************************************************************************
public static boolean copyFile(File sourceFile, File destinationFile)
{
if (sourceFile!=null && sourceFile.exists())
{
  if (!sourceFile.getPath().toLowerCase().equals(destinationFile.getPath().toLowerCase()))
  {
     try
     {
        
        if(destinationFile.exists())
           destinationFile.delete();
       
        if(!destinationFile.exists())
            destinationFile.createNewFile();

        InputStream in = null;
        OutputStream out = null;

        try{
            in = new FileInputStream(sourceFile);
            out = new FileOutputStream(destinationFile);
            byte[] buf = new byte[1024];
            int len;
            while((len = in.read(buf)) > 0)
            {
                out.write(buf, 0, len);
            }
        }
        finally
        {
            in.close();
            out.close();
        }
                
        return true;
    }
     catch(IOException ex)
     {
        Logger.getLogger(Tools.class.getName()).log(Level.SEVERE, null, ex);
        return false;
     }
  }
  else 
  {
//      System.out.println("No copy was made as the source and destination are identical");
      return false;
  }

 }
else
{
    JOptionPane.showMessageDialog(null," copying was unsuccessfull! source file does not exist");
    return false;
}
}


//*****************************************************************************
public static boolean moveFile(File sourceFile, File destinationFile)
{
if (sourceFile!=null && sourceFile.exists())
{
  if (!sourceFile.getPath().toLowerCase().equals(destinationFile.getPath().toLowerCase()))
  {
     try
     {
        
        if(destinationFile.exists())
           destinationFile.delete();
       
        if(!destinationFile.exists())
            destinationFile.createNewFile();

        InputStream in = null;
        OutputStream out = null;

        try{
            in = new FileInputStream(sourceFile);
            out = new FileOutputStream(destinationFile);
            byte[] buf = new byte[1024];
            int len;
            while((len = in.read(buf)) > 0)
            {
                out.write(buf, 0, len);
            }
        }
        finally
        {
            in.close();
            out.close();
        }
        
        if (sourceFile.exists())
            sourceFile.delete();
        
        return true;
    }
     catch(IOException ex)
     {
        Logger.getLogger(Tools.class.getName()).log(Level.SEVERE, null, ex);
        return false;
     }
  }
  else 
  {
//      System.out.println("No copy was made as the source and destination are identical");
      return false;
  }

 }
else
{
    JOptionPane.showMessageDialog(null," copying was unsuccessfull! source file does not exist");
    return false;
}
}

//*****************************************************************************
public static void moveDirectory(File sourceDir, File destDir)
{

  if ( sourceDir !=null && sourceDir.exists() && sourceDir.isDirectory())
  {
            
   if(destDir!=null && !destDir.isDirectory())
      {
        destDir.mkdir();
      }

    if (sourceDir!=null && sourceDir.isDirectory())
     {
        File[] children = sourceDir.listFiles();

        for(File sourceChild : children)
         {
            String name = sourceChild.getName();
            File destChild = new File(destDir, name);
            if(sourceChild.isDirectory())
            {
                moveDirectory(sourceChild, destChild);
            }
            else
            {
                Tools.moveFile(sourceChild, destChild);
            }
        }
      }
 }
  else
      JOptionPane.showMessageDialog(null," Directory copying was unsuccessfull! \n "
                                        +" source directory does not exist / not a directory \n "
                                        +" See the code of copyDirectory in class Tools");
}


//-----------------------------------------------------------------------------

public static void moveDirectoryForce(File sourceDir, File destDir)
{

  if ( sourceDir !=null && sourceDir.exists() && sourceDir.isDirectory())
  {

    if(destDir!=null && destDir.exists() && destDir.isDirectory())
      {
        Tools.deleteDirectory(destDir);                
      }
            
    if(destDir!=null && !destDir.isDirectory())
      {
        destDir.mkdirs();
      }

    if (sourceDir!=null && sourceDir.isDirectory())
     {
        File[] children = sourceDir.listFiles();

        for(File sourceChild : children)
         {
            String name = sourceChild.getName();
            File destChild = new File(destDir, name);
            if(sourceChild.isDirectory())
            {
                moveDirectoryForce(sourceChild, destChild);
            }
            else
            {
                Tools.moveFile(sourceChild, destChild);
            }
        }
      }
 }
}

//*****************************************************************************
public static void copyDirectory(File sourceDir, File destDir)
{

  if ( sourceDir !=null && sourceDir.exists() && sourceDir.isDirectory())
  {
   if(destDir!=null && !destDir.isDirectory())
      {
        destDir.mkdir();
      }

    if (sourceDir!=null && sourceDir.isDirectory())
     {
        File[] children = sourceDir.listFiles();

        for(File sourceChild : children)
         {
            String name = sourceChild.getName();
            File destChild = new File(destDir, name);
            if(sourceChild.isDirectory())
            {
                copyDirectory(sourceChild, destChild);
            }
            else
            {
                copyFile(sourceChild, destChild);
            }
        }
      }
 }
  else
      JOptionPane.showMessageDialog(null," Directory copying was unsuccessfull! \n "
                                        +" source directory does not exist / not a directory \n "
                                        +" See the code of copyDirectory in class Tools");
}




//*****************************************************************************
public static void copyDirectoryForce(File sourceDir, File destDir)
{

  if ( sourceDir !=null && sourceDir.exists() && sourceDir.isDirectory())
  {
      
      
    if(destDir!=null && destDir.exists() && destDir.isDirectory())
      {
        Tools.deleteDirectory(destDir);                
      }      
      
   if(destDir!=null && !destDir.isDirectory())
      {
        destDir.mkdirs();
      }

    if (sourceDir!=null && sourceDir.isDirectory())
     {
        File[] children = sourceDir.listFiles();

        for(File sourceChild : children)
         {
            String name = sourceChild.getName();
            File destChild = new File(destDir, name);
            if(sourceChild.isDirectory())
            {
                copyDirectory(sourceChild, destChild);
            }
            else
            {
                copyFile(sourceChild, destChild);
            }
        }
      }
 }
}




//*****************************************************************************
public static URL copyFileToTemp (URL fileUrl )
{
if (Global.project!=null && Global.project.getName()!=null && Global.project.getLocation()!=null)
{
    String location=Global.project.getLocation()+"/"+Global.project.getName();
    File dataFileLocation=new File(location+"/"+"Temp");

    if (!dataFileLocation.exists() || !dataFileLocation.isDirectory())
        dataFileLocation.mkdir();

    if (dataFileLocation.isDirectory()&& fileUrl!=null)
     {
         String originalFileString= fileUrl.toString().substring(6);
         File originalFile=new File(originalFileString);
         if (originalFile.exists() && dataFileLocation!=null)
         {            
                String newFileString = dataFileLocation.getPath() + "/" + originalFile.getName();
        
                File newFile=new File(newFileString);                
                
                if (Tools.copyFile(originalFile, newFile))
                    return Tools.toURL(newFile);
                        
                else if (originalFile.exists())
                    return Tools.toURL(originalFile);
                else
                    return null;
                
                
                
         }
         else
             return null;
    }
     else
        return null;
 }
else
   return null;
}


//*****************************************************************************
public static URL copyFileToInputs (URL fileUrl )
{
if (Global.project!=null && Global.project.getName()!=null && Global.project.getLocation()!=null)
{
    String location=Global.project.getLocation()+"/"+Global.project.getName();
    File dataFileLocation=new File(location+"/"+"Inputs");

    if (!dataFileLocation.exists() || !dataFileLocation.isDirectory())
        dataFileLocation.mkdir();

    if (dataFileLocation.isDirectory()&& fileUrl!=null)
     {
         String originalFileString= fileUrl.toString().substring(6);
         File originalFile=new File(originalFileString);
         if (originalFile.exists() && dataFileLocation!=null)
         {            
                String newFileString = dataFileLocation.getPath() + "/" + originalFile.getName();
         
                File newFile=new File(newFileString);
                
                if (Tools.copyFile(originalFile, newFile))
                    return Tools.toURL(newFile);
                        
                else if (originalFile.exists())
                    return Tools.toURL(originalFile);
                else
                    return null;
                
         }
         else
             return null;
    }
     else
        return null;
 }
else
   return null;
}


//*****************************************************************************
public static URL copyFileToPreProcessing (URL fileUrl )
{
if (Global.project!=null && Global.project.getName()!=null && Global.project.getLocation()!=null)
{
    String location=Global.project.getLocation()+"/"+Global.project.getName();
    File dataFileLocation=new File(location+"/"+"Inputs");

    if (!dataFileLocation.exists() || !dataFileLocation.isDirectory())
        dataFileLocation.mkdir();

    if (dataFileLocation.isDirectory()&& fileUrl!=null)
     {
         String originalFileString= fileUrl.toString().substring(6);
         File originalFile=new File(originalFileString);
         if (originalFile.exists() && dataFileLocation!=null)
         {            
                String newFileString = dataFileLocation.getPath() + "/" + originalFile.getName();
         
                File newFile=new File(newFileString);
                
                if (Tools.copyFile(originalFile, newFile))
                    return Tools.toURL(newFile);
                        
                else if (originalFile.exists())
                    return Tools.toURL(originalFile);
                else
                    return null;
                
         }
         else
             return null;
    }
     else
        return null;
 }
else
   return null;
}






//*****************************************************************************
public static URL moveFileToDirectory (URL sourceFileUrl,String destinationDirectoryString )
{
    File destinationDirectory=new File(destinationDirectoryString);

    if (!destinationDirectory.exists() || !destinationDirectory.isDirectory())
        destinationDirectory.mkdirs();

    if (destinationDirectory.isDirectory()&& sourceFileUrl!=null)
     {
         String originalFileString= sourceFileUrl.getFile();
         File originalFile=new File(originalFileString);
         if (originalFile.exists() && destinationDirectory!=null)
         {            
                String newFileString = destinationDirectory.getPath() + "/" + originalFile.getName();                
    
                File newFile=new File(newFileString);
                
                
                boolean copyFileResult = Tools.moveFile(originalFile, newFile);
                 
                if (copyFileResult==true)
                    return Tools.toURL(newFile);
                        
                else if (originalFile.exists())
                    return Tools.toURL(originalFile);
                      
                else
                    return null;                  
         }
         else
             return null;
    }
     else
        return null;
 
}

//*****************************************************************************
public static File moveFileToDirectory (File sourceFile,String destinationDirectoryString )
{

    File destinationDirectory=new File(destinationDirectoryString);

    if (!destinationDirectory.exists() || !destinationDirectory.isDirectory())
        destinationDirectory.mkdirs();

    if (destinationDirectory.isDirectory()&& sourceFile!=null)
     {
         String originalFileString= sourceFile.getPath();
         File originalFile=new File(originalFileString);
         if (originalFile.exists() && destinationDirectory!=null)
         {            
                String newFileString = destinationDirectory.getPath() + "/" + originalFile.getName();
                File newFile=new File(newFileString);
                                                                
                if (Tools.moveFile(originalFile, newFile))
                    return newFile;
                        
                else if (originalFile.exists())
                    return originalFile;
                        
                else
                    return null;
                
         }
         else
             return null;
    }
     else
        return null;

}


//*****************************************************************************
public static URL copyFileToDirectory (URL sourceFileUrl,String destinationDirectoryString )
{
    File destinationDirectory=new File(destinationDirectoryString);

    if (!destinationDirectory.exists() || !destinationDirectory.isDirectory())
        destinationDirectory.mkdirs();

    if (destinationDirectory.isDirectory()&& sourceFileUrl!=null)
     {
         String originalFileString= sourceFileUrl.getFile();
         File originalFile=new File(originalFileString);
         if (originalFile.exists() && destinationDirectory!=null)
         {            
                String newFileString = destinationDirectory.getPath() + "/" + originalFile.getName();                
    
                File newFile=new File(newFileString);
                boolean copyFileResult = Tools.moveFile(originalFile, newFile);
                 
                if (copyFileResult==true)
                    return Tools.toURL(newFile);
                        
                else if (originalFile.exists())
                    return Tools.toURL(originalFile);
                      
                else
                    return null;                  
         }
         else
             return null;
    }
     else
        return null;
 
}

//*****************************************************************************
public static File copyFileToDirectory (File sourceFile,String destinationDirectoryString )
{

    File destinationDirectory=new File(destinationDirectoryString);

    if (!destinationDirectory.exists() || !destinationDirectory.isDirectory())
        destinationDirectory.mkdirs();

    if (destinationDirectory.isDirectory()&& sourceFile!=null)
     {
         String originalFileString= sourceFile.getPath();
         File originalFile=new File(originalFileString);
         if (originalFile.exists() && destinationDirectory!=null)
         {            
                String newFileString = destinationDirectory.getPath() + "/" + originalFile.getName();
                File newFile=new File(newFileString);
                                                                
                if (Tools.moveFile(originalFile, newFile))
                    return newFile;
                        
                else if (originalFile.exists())
                    return originalFile;
                        
                else
                    return null;
                
         }
         else
             return null;
    }
     else
        return null;

}






//*****************************************************************************
//*****************************************************************************
public static String[] stringToArray(String stringToSplit, String separator)
{
   String[] stringArray;


   if (stringToSplit!=null)
       stringArray = Pattern.compile(separator).split(stringToSplit);
   else
   {
       String[] s={""};
       stringArray=s;
    }

   return stringArray;
 }


//*****************************************************************************
//*****************************************************************************
public static String[] stringToArray(String stringToSplit)
{
   String[] stringArray;
   
   if (stringToSplit!=null)
       stringArray = Pattern.compile(" ").split(stringToSplit);
   else
   {
       String[] s={" "};
       stringArray=s;
    }

   return stringArray;
 }



//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
//-----------------------------------------------------------------------------
//                      Writing Object
//-----------------------------------------------------------------------------
//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
public static void writeObjectToFile(Object object, String destinationFileString)
{ 
 if (object !=null && destinationFileString!=null)
 {
    try
      {
         FileOutputStream fileOut = new FileOutputStream(destinationFileString);
         ObjectOutputStream out = new ObjectOutputStream(fileOut);
         out.writeObject(object);
         out.close();
          fileOut.close();
      }
      catch(IOException ioex)
      {
        Logger.getLogger(Tools.class.getName()).log(Level.SEVERE, null, ioex);
      }
 }
}
//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
//-----------------------------------------------------------------------------
//                              Reading Object
//-----------------------------------------------------------------------------
//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
public static Object readObjectFromFile(String fileString)
{         
    
if (fileString!=null)    
{
        Object object= null;
         try
         {
            FileInputStream fileIn = new FileInputStream(fileString);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            object = in.readObject();
            in.close();
            fileIn.close();
            return object;
        }
         catch(IOException ioex)
         {
            JOptionPane.showMessageDialog(null,"Cannot read object from file ("
                                               +fileString+
                                               ")\n Make sure the file exist & "
                                             + "the object is readable");
            Logger.getLogger(Tools.class.getName()).log(Level.SEVERE, null, ioex);
            return null;
        }
         catch(ClassNotFoundException cnfex)
        {
            Logger.getLogger(Tools.class.getName()).log(Level.SEVERE, null, cnfex);
            return null;
        }
}
else
    return null;
                  
}




/*
//*****************************************************************************
public static URL copyFileToInputs (URL fileUrl )
{
 if (Global.project!=null && Global.project.getName()!=null && Global.project.getLocation()!=null)
 {
    String location=Global.project.getLocation()+"/"+Global.project.getName();
    File dataFileLocation=new File(location+"/"+"Inputs");

    if ((!dataFileLocation.exists()) || (!dataFileLocation.isDirectory()))
         dataFileLocation.mkdir();

    if (dataFileLocation.isDirectory()&& fileUrl!=null)
     {
         String originalFileString= fileUrl.toString().substring(6);

         File originalFile=new File(originalFileString);

         if (originalFile.exists() && dataFileLocation!=null)
         {
            try {
                String newFileString = dataFileLocation.getPath() + "/" + originalFile.getName();
                File newFile=new File(newFileString);
                Tools.moveFile(originalFile, newFile);
                return new URL("file:/" + newFileString);
            } catch (MalformedURLException ex) {
                Logger.getLogger(Tools.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(null," No File was copied");
                return null;
            }
         }
         else
         {
             JOptionPane.showMessageDialog(null," original file does not exist,  No File was copied");
             return null;
         }
    }
     else
    {
        JOptionPane.showMessageDialog(null," file url was not found or file directory was not found, No File was copied");
        return null;
     }
 }
else
 {
   JOptionPane.showMessageDialog(null," No File was copied");
   return null;
  }
}
*/
}
