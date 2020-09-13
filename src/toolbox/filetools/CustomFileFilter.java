/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package toolbox.filetools;

import java.io.File;
import javax.swing.*;
import javax.swing.filechooser.*;
//import org.jcp.xml.dsig.internal.dom.Utils;

/**
 *
 * @author amb04
 */
//------------------------------------------------------------------------------    
public class CustomFileFilter extends FileFilter {
   String fileExtention=null;
   String filedescription=null;

public CustomFileFilter(String fileExtention,String fileDescription)
{
  this.fileExtention=fileExtention;
  this.filedescription=fileDescription;
 }
//------------------------------------------------------------------------------
    public void setDescription(String description) {
        this.filedescription = description;
    }

    public String getDescription() {
        return filedescription;
    }
    
    public void setFileExtention(String fileExtention) {
        this.fileExtention = fileExtention;
    }

    public String getFileExtention() {
        return fileExtention;
    }
//------------------------------------------------------------------------------    
    //Accept all directories and all gif, jpg, tiff, or png files.
    public boolean accept(File f) {
        if (f.isDirectory()) {
            return true;
        }
        //String extension = XMLSchemaUtils.getExtension(f);
        String extension = getExtension(f);
        if (extension != null) {
            if (extension.equals(fileExtention)) {
                    return true;
            } else {
                return false;
            }
        }
        return false;
    }
//------------------------------------------------------------------------------
    //The description of this filter

    public static String getExtension(File f) {
        String ext = null;
        String s = f.getName();
        int i = s.lastIndexOf('.');

        if (i > 0 &&  i < s.length() - 1) {
            ext = s.substring(i+1).toLowerCase();
        }
        return ext;
    }
}