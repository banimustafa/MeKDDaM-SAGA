/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
// this is a fo example. It has never been used in the program
package toolbox.datatools;
import java.io.File;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.Templates;
import javax.xml.transform.stream.StreamSource;
import javax.xml.transform.stream.StreamResult;

//import com.xmlmind.fo.converter.OutputDestination;
//import com.xmlmind.fo.converter.Converter;

public class FoXML {
    public static void main(String[] args) throws Exception {
        if (args.length != 3) {
            System.err.println("usage: java Sample2 xslt_stylesheet" +
                               " xml_file rtf_file");
            System.exit(1);
        }
        File xslFile =new File(args[0]);
        File xmlFile = new File(args[1]);
        File rtfFile = new File(args[2]);
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
}
