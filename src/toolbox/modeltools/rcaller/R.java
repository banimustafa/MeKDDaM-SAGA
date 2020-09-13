package toolbox.modeltools.rcaller;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import rcaller.exception.RCallerExecutionException;
import toolbox.Tools;
import toolbox.viewtools.ImageModelVisualization;

/**
 *
 * @author Mehmet Hakan Satman
 */

public class R
{
    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    public R(String command, File outputFile )
    {
        R r= new R();
        File file=r.runR(command);
        Tools.copyFile(file, outputFile);        
        ImageModelVisualization jf= new ImageModelVisualization(outputFile);    
        jf.setVisible(true);
    }
    
    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++    
    public R()
    {        
    }
    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    public File runR(String command)
    {
        try {             
             RCaller caller = new RCaller();
             caller.setRscriptExecutable("C:/Program Files/R/R-2.13.0/bin/Rscript");
             caller.cleanRCode();

             double[] numbers = new double[]{1, 4, 3, 5, 6, 10};

             caller.addDoubleArray("x", numbers);
             File file = caller.startPlot();                          
             //System.out.println("Plot will be saved to : " + file);
             // caller.addRCode("plot.ts(x)");
             caller.addRCode(command);
             caller.endPlot();             
             caller.runOnly();
             File outFile = caller.plot(file,false);
             return outFile;
        } catch (RCallerExecutionException ex) {
            Logger.getLogger(R.class.getName()).log(Level.SEVERE, null, ex);
            return null;            
        } catch (IOException ex) {
            Logger.getLogger(R.class.getName()).log(Level.SEVERE, null, ex);        
            return null;
        }catch (Exception e) {
            Logger.getLogger(RCaller.class.getName()).log(Level.SEVERE, null, e);            
            return null;   
        }
    }

    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    public File runR(String command1,String command2)
    {
        try {             
             RCaller caller = new RCaller();
             caller.setRscriptExecutable("C:/Program Files/R/R-2.13.0/bin/Rscript");
             caller.cleanRCode();

             double[] numbers = new double[]{1, 4, 3, 5, 6, 10};

             caller.addDoubleArray("x", numbers);
             File file = caller.startPlot();                          
             //System.out.println("Plot will be saved to : " + file);
             // caller.addRCode("plot.ts(x)");
             caller.addRCode(command1);
             caller.endPlot();             
             caller.runOnly();
             
             caller.addRCode(command2);
             caller.endPlot();                         
             caller.runOnly();
             
             File outFile = caller.plot(file,false);             
             return outFile;
        } catch (RCallerExecutionException ex) {
            Logger.getLogger(R.class.getName()).log(Level.SEVERE, null, ex);
            return null;            
        } catch (IOException ex) {
            Logger.getLogger(R.class.getName()).log(Level.SEVERE, null, ex);        
            return null;
        }catch (Exception e) {
            Logger.getLogger(RCaller.class.getName()).log(Level.SEVERE, null, e);            
            return null;   
        }
    }
    
     //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    public File runR(String command1,String command2, String command3)
    {
        try {             
             RCaller caller = new RCaller();
             caller.setRscriptExecutable("C:/Program Files/R/R-2.13.0/bin/Rscript");
             caller.cleanRCode();

             double[] numbers = new double[]{1, 4, 3, 5, 6, 10};

             caller.addDoubleArray("x", numbers);
             File file = caller.startPlot();                          
             //System.out.println("Plot will be saved to : " + file);
             // caller.addRCode("plot.ts(x)");
             caller.addRCode(command1);
             caller.endPlot();             
             caller.runOnly();
             
             caller.addRCode(command2);
             caller.endPlot();                         
             caller.runOnly();
             
             caller.addRCode(command3);
             caller.endPlot();                         
             caller.runOnly();
             
             File outFile = caller.plot(file,false);             
             return outFile;
        } catch (RCallerExecutionException ex) {
            Logger.getLogger(R.class.getName()).log(Level.SEVERE, null, ex);
            return null;            
        } catch (IOException ex) {
            Logger.getLogger(R.class.getName()).log(Level.SEVERE, null, ex);        
            return null;
        }catch (Exception e) {
            Logger.getLogger(RCaller.class.getName()).log(Level.SEVERE, null, e);            
            return null;   
        }
    }
   
    

}


