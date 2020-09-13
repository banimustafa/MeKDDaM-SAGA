/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package toolbox.viewtools;
import java.awt.AWTException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import weka.core.Instances;
import weka.gui.visualize.PlotData2D;
import weka.gui.visualize.ThresholdVisualizePanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import toolbox.Tools;
import weka.core.converters.ConverterUtils.DataSource;
import weka.gui.visualize.VisualizePanel;
import weka.gui.AttributeVisualizationPanel;
import weka.gui.visualize.BMPWriter;
import weka.gui.visualize.JPEGWriter;
import weka.gui.visualize.PNGWriter;
/**
 *
 * @author amb04
 */
public class VisualizationTools
{
//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
//****************************************************************************
//----------------------------------------------------------------------------
//                          Visualize Data
//----------------------------------------------------------------------------
//****************************************************************************
public static void visualizeData(Instances dataInstances)
{
        try {
            VisualizePanel vp = new VisualizePanel();
            
            PlotData2D plotdata = new PlotData2D(dataInstances);
            plotdata.setPlotName(dataInstances.relationName());
            plotdata.addInstanceNumberAttribute();
            // specify which points are connected
            boolean[] cp = new boolean[dataInstances.numInstances()];
            for (int n = 1; n < cp.length; n++) {
                cp[n] = true;
            }
            plotdata.setConnectPoints(cp);
            // add plot
            vp.addPlot(plotdata);
            // method visualizeClassifierErrors
            final JFrame jf = new JFrame("Visulaized Data: " + vp.getName());
            jf.setSize(850, 650);
            jf.getContentPane().setLayout(new BorderLayout());
            jf.getContentPane().add(vp, BorderLayout.CENTER);
            jf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            jf.setVisible(true);
        } catch (Exception ex) {
            Logger.getLogger(VisualizationTools.class.getName()).log(Level.SEVERE, null, ex);
        }
  }



//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
//****************************************************************************
//----------------------------------------------------------------------------
//                          Visualize Multiple Data
//----------------------------------------------------------------------------
//****************************************************************************

 public static void visualizeMultipleData(String optionsString)
 {
    boolean first = true;
    ThresholdVisualizePanel tvp = new ThresholdVisualizePanel();

    //-------------------------------------------------------------------------
    String[] dataSetsArray=Tools.stringToArray(optionsString);
    //-------------------------------------------------------------------------

    for (int i = 0; i < dataSetsArray.length; i++)
    {
            try {
                Instances curve = DataSource.read(dataSetsArray[i]);
                curve.setClassIndex(curve.numAttributes() - 1);
                // method visualize
                PlotData2D plotdata = new PlotData2D(curve);
                plotdata.setPlotName(curve.relationName());
                plotdata.addInstanceNumberAttribute();
                // specify which points are connected
                boolean[] cp = new boolean[curve.numInstances()];
                for (int n = 1; n < cp.length; n++) {
                    cp[n] = true;
                }
                plotdata.setConnectPoints(cp);
                // add plot
                if (first) {
                    tvp.setMasterPlot(plotdata);
                } else {
                    tvp.addPlot(plotdata);
                }
                first = false;
            } catch (Exception ex) {
                Logger.getLogger(VisualizationTools.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    // method visualizeClassifierErrors
    final JFrame jf = new JFrame("WEKA ROC");
    jf.setSize(500,400);
    jf.getContentPane().setLayout(new BorderLayout());
    jf.getContentPane().add(tvp, BorderLayout.CENTER);
    jf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    jf.setVisible(true);

  }




//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
//****************************************************************************
//----------------------------------------------------------------------------
//                      Store Visual Jcomponnet to File
//----------------------------------------------------------------------------
//****************************************************************************

public static void visualizationToFile(JComponent jComponent, String fileName) throws Exception
 {

String fileType= Tools.getFileExtentionOnly(new File(fileName));

  if (fileName!=null && jComponent!=null)
     {
       if (fileType.toLowerCase().equals("png")||fileType.toLowerCase().equals(".png"))
          PNGWriter.toOutput(new PNGWriter(), jComponent, new File(fileName));

       else if(fileType.toLowerCase().equals("jpg") || fileType.toLowerCase().equals(".jpg"))
          JPEGWriter.toOutput(new JPEGWriter(), jComponent, new File(fileName));
          
        else if(fileType.toLowerCase().equals("bmp") || fileType.toLowerCase().equals(".bmp"))
          BMPWriter.toOutput(new BMPWriter(), jComponent, new File(fileName));
       //else
          //JOptionPane.showMessageDialog(null, "File Type was not found or invalid");
     }
 }


public static void visualizationToFile(String outputFile)
{
        try {
            BufferedImage screencapture = new Robot().createScreenCapture(
                    new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()) );
                          
              File file = new File(outputFile);
              ImageIO.write(screencapture, "png", file);
              
        } catch (IOException ex) {
            Logger.getLogger(VisualizationTools.class.getName()).log(Level.SEVERE, null, ex);
        } 
        
        
        catch (AWTException ex) {
            Logger.getLogger(VisualizationTools.class.getName()).log(Level.SEVERE, null, ex);
        }

}



public static void visualizationToFile(Component myframe, String outputFile)
{    
try {            
      BufferedImage screencapture = new Robot().createScreenCapture( 
      new Rectangle( myframe.getX(), myframe.getY(), 
                  myframe.getWidth(), myframe.getHeight() ) );            
            
              File file = new File(outputFile);
              ImageIO.write(screencapture, "png", file);
              
        } catch (IOException ex) {
            Logger.getLogger(VisualizationTools.class.getName()).log(Level.SEVERE, null, ex);
        } 
        
        
        catch (AWTException ex) {
            Logger.getLogger(VisualizationTools.class.getName()).log(Level.SEVERE, null, ex);
        }

}

//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
//****************************************************************************
//----------------------------------------------------------------------------
//         Visualize Neural Network this a link to the ModellingTools
//----------------------------------------------------------------------------
//****************************************************************************
 public static CustomisedAttributeVisualizationPanel visualizeAttributePanel(Instances instances, int attributeIndex)
 {
    AttributeVisualizationPanel avp= new AttributeVisualizationPanel();

    //--------------------------------------------------------------
    avp.setInstances(instances);
    avp.setAttribute(attributeIndex);
    //avp.setSize(150,100);

    avp.setName(instances.attribute(attributeIndex).name());
    CustomisedAttributeVisualizationPanel cavp= new CustomisedAttributeVisualizationPanel(avp,instances, attributeIndex);


    return cavp;
  }



 //@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
//****************************************************************************
//----------------------------------------------------------------------------
//         Visualize Neural Network this a link to the ModellingTools
//----------------------------------------------------------------------------
//****************************************************************************
 public static void visualizeAllAttributes(Instances instances)
  {        
    GridLayout gl=new GridLayout();
    gl.setRows(4);
    gl.setColumns(4);
    
    //--------------------------------------------------------------
    JFrame jf = new JFrame("All Instances Attributes Visualization");
    jf.setTitle(instances.relationName()+" Attributes Values Distribution "+ "          *click the attribute name for details");
    jf.setSize(610, 510);
    jf.setMaximumSize(new java.awt.Dimension(1200,800));
    jf.setLayout(gl);
    jf.setDefaultCloseOperation( jf.DISPOSE_ON_CLOSE);
    jf.setLayout(gl);
    for (int i=0;i<instances.numAttributes();i++)
        jf.getContentPane().add(visualizeAttributePanel(instances,i));
    
    //--------------------------------------------------------------
    jf.setVisible(true);
    jf.pack();
    jf.repaint();
    
    

  }

//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
//****************************************************************************
//----------------------------------------------------------------------------
//         Visualize Neural Network this a link to the ModellingTools
//----------------------------------------------------------------------------
//****************************************************************************
 public static void visualizeAttribute(Instances instances, int attributeIndex)
  {
    AttributeVisualizationPanel avp= new AttributeVisualizationPanel();
    
    //--------------------------------------------------------------
    avp.setInstances(instances);
    avp.setAttribute(attributeIndex);
    avp.setSize(150,100);

    //--------------------------------------------------------------
    JFrame jf = new JFrame("Attribute Visualization");
    jf.setSize(500, 300);
    jf.setTitle(instances.attribute(attributeIndex).name());
    jf.getContentPane().add(avp,BorderLayout.CENTER);
    jf.setDefaultCloseOperation( jf.DISPOSE_ON_CLOSE);
    jf.setVisible(true);    
    jf.repaint();
  }
 
  public static ModelVisualization_JFrame getExternalVisualization(String imageFilePath)
  {
        try {        
            
            File presentationImageFile=null;
            
            if (imageFilePath!=null)
                presentationImageFile= new File(imageFilePath);
            
           if (presentationImageFile!=null && presentationImageFile.exists())
            {
                ModelVisualization_JFrame jf = new ModelVisualization_JFrame(presentationImageFile);
                return jf;
            }
           else
               return null;
            // Evaluation
            //----------------------------------------------------------------            
        } catch (Exception ex) 
        {
            Logger.getLogger(VisualizationTools.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
  }
 

}
