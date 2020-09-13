package toolbox.datatools;
import global.Config;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.transform.Templates;

import javax.xml.transform.stream.StreamResult;

//import com.xmlmind.fo.converter.OutputDestination;
//import com.xmlmind.fo.converter.Converter;

//Java
import java.io.File;
import java.io.OutputStream;

//JAXP
import javax.swing.JOptionPane;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.Source;
import javax.xml.transform.Result;
import javax.xml.transform.stream.StreamSource;
import javax.xml.transform.sax.SAXResult;

//FOP
import org.apache.fop.apps.FOUserAgent;
import org.apache.fop.apps.Fop;
import org.apache.fop.apps.FopFactory;
import org.apache.fop.apps.MimeConstants;
import process_model.phase.delivery.Delivery;
import toolbox.filetools.CustomFileFilter;
import toolbox.Tools;

public class XMLTools {

    //*************************************************************************
    //***********(Marshaeller and unmarshaller from and to a file )************
    // ************************************************************************
    public static void marshal(Object object, String distinationFile) throws Exception {
        Class objectClass = object.getClass();
        //
        JAXBContext context = JAXBContext.newInstance(objectClass);
        //
        Marshaller marshaller = context.createMarshaller();
        //      
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        //       
        marshaller.setProperty(Marshaller.JAXB_FRAGMENT,false);
        //
        marshaller.setProperty(Marshaller.JAXB_NO_NAMESPACE_SCHEMA_LOCATION,"");
        //
        FileOutputStream fileOutputStream=new FileOutputStream(distinationFile);
        marshaller.marshal(object, fileOutputStream);
        fileOutputStream.flush();
        fileOutputStream.close();


        
    }
   //--------------------------------------------------------------------------
    public static Object unMarshal(Object object, String sourceFile) throws Exception {
        
        try
        {
            Class objectClass = object.getClass();
            JAXBContext context = JAXBContext.newInstance(objectClass);
            FileInputStream fileInputStream=new FileInputStream(sourceFile);
            Object backObject = context.createUnmarshaller().unmarshal(fileInputStream);
            fileInputStream.close();
        return backObject;
        }
        catch(Exception ex)
        {
            System.out.println(" Could'nt Load the selected xml file");
            Logger.getLogger(object.getClass().getSimpleName()).log(Level.SEVERE, null, ex);
         return null;
        }
        
    }

    //*************************************************************************
    //**********************(write an object into an xml file )****************
    // ************************************************************************
    public static void storeObject(Object object, File destination,String fname) throws IOException, Exception {        
        String name = object.getClass().getSimpleName();
        if (fname==null)
            fname=name;
        File directory = null;
        try {
            if (destination == null) {
                File fileDestination = Tools.chooseFile("Choose " + fname+ " location", null, Config.defaultLocation, true);
                if (fileDestination != null) 
                {
                     String result=(fileDestination.mkdirs()) ? "Success... Distination directory is created with file chooser ...in store object... with fname ": "Failed... to create the directories in store object... with fname ";
                     directory=fileDestination;
                }  
            }
            else {
                String result=null;
                if (destination.exists())
                    result="\n Ok... Destination directory ....alreday created... in store object... with fname ";
                else
                    result=(destination.mkdirs()) ?"Success... Destination directory... is created ...in store object... with fname ": "Failed... Destination directory... is failed ...in store object... with fname ";
                directory=destination;
            }
            if (object != null) 
            {
                  if(directory!=null)
                     XMLTools.marshal(object, directory.getPath() + "/" + fname + ".xml");}

        } catch (Exception x) 
        {
                Logger.getLogger(object.getClass().getSimpleName()).log(Level.SEVERE, null, x);
        }
    }

    //************************************************************************
    //********( This code is used for lodaing objects from an XML file)********
    //*************************************************************************
    public static Object loadObject(Object object, String source) {
        File objectFile= null;

        String name = object.getClass().getName();
        try {
          
            //if no file already passed
           if (source == null||source.equals(""))
            {
            CustomFileFilter fileFilter = new CustomFileFilter("xml", name + " XML File  *.xml");            
            File location = Tools.chooseFile("Open" + name + "XML file", fileFilter, null, false);
            String objectLocation = (location != null) ? location.getPath() : null;
            
            //--
            if (objectLocation!=null)
                objectFile= new File(objectLocation); // the browsed file
            else
            {                
                return null;
            }
            }

           //------------------------------------------------------------------
           else
             objectFile= new File(source);           // the passed file

           
           if (objectFile.exists())
           {
               // process the chosen file or the passed file                      
               //---------------------------------------------------------------
               object = XMLTools.unMarshal(object, objectFile.getPath());
               if (object!=null)
                   return object;
               else                   
                   return null;
                
           }
           else 
               return null;
           
           
            //----------------------------------------------
        } catch (Exception ex) {
            Logger.getLogger(XMLTools.class.getName()).log(Level.SEVERE, null, ex);

            return null;
        }
    }











    //-------------------------------------------------------------------------
    //*************************************************************************
    //******(1. This code is used to generate schema from raw java classes)****
    // ************************************************************************
    //-------------------------------------------------------------------------
    public static void GenerateXMLSchemaFromJavaClass() {
        try {

            File location = Tools.chooseFile(" Select the class location... ",
                    new CustomFileFilter("java", "Java class source file *.java"), Config.defaultLocation, false);
            String classLocation = (location != null) ? location.getPath() : null;

            if (classLocation != null)
            {
                File destination = Tools.chooseFile(" Select the location of the generated schema...", null, classLocation, true);
                String schemaDestination = (destination != null) ? destination.getPath() : null;

                if (schemaDestination != null)
                    XMLTools.schemaGen(classLocation, schemaDestination);
            }
            

        } catch (Exception ex) {
             JOptionPane.showMessageDialog(null,"Conversion was unsuccessfull "
                 + "check the tool location, source file  or target location");
            Logger.getLogger(XMLTools.class.getName()).log(Level.SEVERE, null, ex);
        }
    }



    //-------------------------------------------------------------------------
    //*************************************************************************
    //********(2. This code is used to generate classes from schema )**********
    //*************************************************************************
    //-------------------------------------------------------------------------
    public static void GenerateJavaClassFromXMLSchema() {
        try {

            File location = Tools.chooseFile(" Select the source XML schema... ",
                    new CustomFileFilter("xsd", "XML Schema File *.xsd"), Config.defaultLocation,
                    false);
            String schemaLocation = (location != null) ? location.getPath() : null;

            if (schemaLocation!=null)
            {
                File destination = Tools.chooseFile(" Select the location of the generated java class ...", null, schemaLocation, true);
                String classDestination = (destination != null) ? destination.getPath() : null;

                if (classDestination != null)
                    XMLTools.xjc(schemaLocation, classDestination);
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null,"Conversion was unsuccessfull "
                    + "check the tool location, source file  or target location");
            Logger.getLogger(XMLTools.class.getName()).log(Level.SEVERE, null, ex);
        }
    }



    //-------------------------------------------------------------------------
    //*************************************************************************
    //********(2. This code is used to generate classes from schema )**********
    // ************************************************************************
    //-------------------------------------------------------------------------
    public static void GenerateXMLSchemaFromXMLFile() {
        try {

            File xmlFile = Tools.chooseFile(" Select the source XML File... ",
                    new CustomFileFilter("xml", "XML Schema File *.xml"), Config.defaultLocation,
                    false);
            String xmlFileString = (xmlFile != null) ? xmlFile.getPath() : null;           

            if (xmlFileString!=null)
            {
                File schemaDestinationFile = Tools.chooseFile(" Select the location of the generated schema ...", null, xmlFileString, true);
                String schemaDestinationFileString = (schemaDestinationFile != null) ? schemaDestinationFile.getPath() : null;

                if (schemaDestinationFileString != null)
                    XMLTools.xmlToSchema(xmlFileString, schemaDestinationFileString);

            }
        } catch (Exception ex)
        {
            JOptionPane.showMessageDialog(null,"Conversion was unsuccessfull check the tool location, source file  or target location");
            Logger.getLogger(XMLTools.class.getName()).log(Level.SEVERE, null, ex);
        }
    }



    //--------------------------------------------------------------------------
    //                   Form xsd scheama to java class code
    //--------------------------------------------------------------------------
    public static void xjc(String schemaLocation, String classDestination) throws Exception {
     try
     {
        boolean hostingSuccessful = Tools.host(Config.xmlLocation + "xjc.exe" + " " + schemaLocation + " -d " + classDestination);
        if (hostingSuccessful==true)
                JOptionPane.showMessageDialog(null,"Java Class Code Generating was successfull!\n Generated file is "+
                                       classDestination+"/"+Tools.getFileNameOnly(new File(schemaLocation))+".java");
     }
     catch (Exception x)
     {
        JOptionPane.showMessageDialog(null,"Java Class Code Generating was unsuccessfull");
        Logger.getLogger(XMLTools.class.getName()).log(Level.SEVERE, null, x);
    }
    }
    
    
    //--------------------------------------------------------------------------
    //                      Form java class file to xsd scheam
    //-------------------------------------------------------------------------
    public static void schemaGen(String classLocation, String schemaDestination) throws Exception 
    {
        try
        {
            boolean hostingSuccessful = Tools.host(Config.xmlLocation + "schemagen.exe" + " " + classLocation + " -d " + schemaDestination);
            if (hostingSuccessful==true)
            JOptionPane.showMessageDialog(null,"Schema Generating was successfull!\n Generated file is "+
                                               schemaDestination+"/"+Tools.getFileNameOnly(new File(classLocation))+".xsd");
        }
        catch (Exception x)
        {
            JOptionPane.showMessageDialog(null,"Schema Generating was unsuccessfull");
            Logger.getLogger(XMLTools.class.getName()).log(Level.SEVERE, null, x);
        }
    }

    //-------------------------------------------------------------------------
    //                      Form xml file to xsd scheam
    //-------------------------------------------------------------------------
    public static void xmlToSchema(String XMLFile, String targetLocation)
    {
       if (XMLFile!=null && targetLocation!=null)
        try 
        {
            boolean hostingSuccessful = Tools.host(Config.xmlLocation + "xsd.exe" + "  " + XMLFile.trim() + "  /c  /out:" + targetLocation.trim() + " /l:" + "CS");            

            if (hostingSuccessful==true)
                 JOptionPane.showMessageDialog(null,"Schema Generating was successfull!\n Generated file is "+
                                               targetLocation+"/"+Tools.getFileNameOnly(new File(XMLFile))+".xsd");
        }
        catch (IOException ex)
        {
            Logger.getLogger(XMLTools.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null,"Schema Generating was unsuccessfull");
        }
       else
           JOptionPane.showMessageDialog(null,"source file or target directory is missing");
    }


    //--------------------------------------------------------------------------
    public static void schemaToJava(String schemaLocation, String classDestination) throws Exception {
        XMLTools.xjc(schemaLocation, classDestination);
    }

    //-------------------------------------------------------------------------
    public static void javaToSchema(String classLocation, String schemaDestination) throws Exception {
        XMLTools.schemaGen(classLocation, schemaDestination);
    }




    public static void xmlToRTF(File xmlFile1, File xslFile1,File rtfFile1)
    {

        File xslFile =xslFile1;
        File xmlFile =xmlFile1;
        File rtfFile = rtfFile1;
        File foFile = null;

        try {
            TransformerFactory factory = TransformerFactory.newInstance();
            // Efficient, reusable, thread-safe representation of the XSLT
            // style sheet.
            Templates compiledStylesheet =
                factory.newTemplates(new StreamSource(xslFile));

            // [1] Convert XML to XSL-FO ---

            Transformer transformer = compiledStylesheet.newTransformer();
            foFile = File.createTempFile("sample2_", ".fo");
            transformer.transform(new StreamSource(xmlFile),
                                  new StreamResult(foFile));

            // [2] Convert XSL-FO to RTF ---
/*
            Converter converter = new Converter();
            converter.setProperty("outputFormat", "rtf");
            converter.setProperty("outputEncoding", "Cp1252");
            converter.setProperty("imageResolution", "72");
            converter.setProperty("baseURL", xmlFile.toURI().toASCIIString());

            InputSource src = new InputSource(foFile.toURI().toASCIIString());
            OutputDestination dst = new OutputDestination(rtfFile.getPath());
            converter.convert(src, dst);
 * 
 */
        } catch (Exception e) {
            System.err.println("cannot convert '" + xmlFile +
                               "' to '" + rtfFile +
                               "' using XSLT stylesheet '" + xslFile +
                               "': " + e);
            System.exit(2);
        } finally {
            if (foFile != null && foFile.exists()) {
                foFile.delete();
            }
        }
    }


//*****************************************************************************
    public static void xmlToPDF(File xmlFile1, File xslFile1,File pdfFile1)
    {
      try {

//            System.out.println("FOP ExampleXML2PDF\n");
//            System.out.println("Preparing...");

            // Setup directories
            File baseDir = new File(".");
            File outDir = new File(baseDir, "out");
            outDir.mkdirs();

            // Setup input and output files
            File xmlfile = xmlFile1;
            File xsltfile =xslFile1 ;
            File pdffile = pdfFile1;
/*
            System.out.println("Input: XML (" + xmlfile + ")");
            System.out.println("Stylesheet: " + xsltfile);
            System.out.println("Output: PDF (" + pdffile + ")");
            System.out.println();
            System.out.println("Transforming...");
*/
            // configure fopFactory as desired
            FopFactory fopFactory = FopFactory.newInstance();

            FOUserAgent foUserAgent = fopFactory.newFOUserAgent();
            // configure foUserAgent as desired

            // Setup output
            OutputStream out = new java.io.FileOutputStream(pdffile);
            out = new java.io.BufferedOutputStream(out);

            try {
                // Construct fop with desired output format
                Fop fop = fopFactory.newFop(MimeConstants.MIME_PDF, foUserAgent, out);

                // Setup XSLT
                TransformerFactory factory = TransformerFactory.newInstance();
                Transformer transformer = factory.newTransformer(new StreamSource(xsltfile));

                // Set the value of a <param> in the stylesheet
                transformer.setParameter("versionParam", "2.0");

                // Setup input for XSLT transformation
                Source src = new StreamSource(xmlfile);

                // Resulting SAX events (the generated FO) must be piped through to FOP
                Result res = new SAXResult(fop.getDefaultHandler());

                // Start XSLT transformation and FOP processing
                transformer.transform(src, res);
            } finally {
                out.close();
            }

//            System.out.println("Success!");
        } catch (Exception e) {
            e.printStackTrace(System.err);
            System.exit(-1);
        }
    }

public static void report( Delivery delivery, String location)
{
    File xmlFile=new File(location+"/"+Delivery.class.getSimpleName()+".xml");
    File xslFile=new File(Config.xslLocation+Delivery.class.getSimpleName()+".xsl");
    File pdfFile=new File(location+"/"+Delivery.class.getSimpleName()+".pdf");
    File rtfFile=new File(location+"/"+Delivery.class.getSimpleName()+".rtf");

    XMLTools.xmlToPDF(xmlFile, xslFile, pdfFile);
    XMLTools.xmlToRTF(xmlFile, xslFile, rtfFile);
    /*
    try {
            Tools.host("E:/try/fop-0.95bin/fop.bat " + " -xml " + xmlFile + " -xsl " + xslFile + " -pdf " + pdfFile);
        } catch (IOException ex) {
            Logger.getLogger(XMLTools.class.getName()).log(Level.SEVERE, null, ex);
        }
     *
     */
}

//*****************************************************************************
/*
public static void xmlToPdf(File xmlFile1, File xslFile1,File rtfFile1)
    {

        File xslFile =xslFile1;
        File xmlFile =xmlFile1;
        File rtfFile = rtfFile1;
        File foFile = null;

        try {
            TransformerFactory factory = TransformerFactory.newInstance();
            // Efficient, reusable, thread-safe representation of the XSLT
            // style sheet.
            Templates compiledStylesheet =
                factory.newTemplates(new StreamSource(xslFile));

            // [1] Convert XML to XSL-FO ---

            Transformer transformer = compiledStylesheet.newTransformer();
            foFile = File.createTempFile("sample2_", ".fo");
            transformer.transform(new StreamSource(xmlFile),
                                  new StreamResult(foFile));

            // [2] Convert XSL-FO to RTF ---

            Converter converter = new Converter();
            converter.setProperty("outputFormat", "pdf");
            converter.setProperty("outputEncoding", "Cp1252");
            converter.setProperty("imageResolution", "72");
            converter.setProperty("baseURL", xmlFile.toURI().toASCIIString());

            InputSource src = new InputSource(foFile.toURI().toASCIIString());
            OutputDestination dst = new OutputDestination(rtfFile.getPath());
            converter.convert(src, dst);
        } catch (Exception e) {
            System.err.println("cannot convert '" + xmlFile +
                               "' to '" + rtfFile +
                               "' using XSLT stylesheet '" + xslFile +
                               "': " + e);
            System.exit(2);
        } finally {
            if (foFile != null && foFile.exists()) {
                foFile.delete();
            }
        }
    }
 * 
 */
}
