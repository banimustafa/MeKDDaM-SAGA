/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package global;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author amb04
 */

//xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Config", propOrder = {
    "JDKLocation",
    "defaultLocation",
    "processImage"
})
@XmlRootElement
//xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx

public class Config {

    public static String JDKLocation="C:/Program Files/Java/jdk1.6.0_21/bin/";    
    //public static String  absuluteLocation=new File("").getAbsolutePath();
    //public static String  defaultLocation=absuluteLocation+"/utilities/";
   
    //public static String  absuluteLocation=new File("").getAbsolutePath();
    //public static String try1=Util.class.getResource("Util.class").toString().substring(
   //         Util.class.getResource("Util.class").toString().indexOf("/")+1,
   //         Util.class.getResource("Util.class").toString().indexOf("MetaboMining"));    
    public static String  defaultLocation="./utilities/";
    public static String  imagesLocation=defaultLocation+"images/";
    public static String  helpLocation=defaultLocation+"help/";
    public static String  xslLocation=defaultLocation+"xsl/";
    public static String  xmlLocation=defaultLocation+"xml/";
    public static String  descLocation=defaultLocation+"desc/";
    public static String  iconsLocation="gui/icons/";
    public static String  processImage=imagesLocation+"process.jpg";    
    //public static String  processImage="process.jpg";



    //public static String  processImageFileLocation=new File(imagesLocation+processImage).getAbsolutePath();
    public static String  processImageFileLocation=imagesLocation+processImage;
}
