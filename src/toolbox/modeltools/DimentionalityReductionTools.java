/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package toolbox.modeltools;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import weka.core.Instances;
import weka.attributeSelection.PrincipalComponents;
import toolbox.Tools;
import toolbox.filetools.FileTools;
import weka.core.converters.ConverterUtils.DataSource;
/**
 *
 * @author amb04
 */
public class DimentionalityReductionTools
{


//****************************************************************************
//----------------------------------------------------------------------------
//                          Generate PCA
//----------------------------------------------------------------------------
//****************************************************************************
 public static Object generatePCA(Instances originalData)
  {
        try {
            Object[] result= new Object[2];
            //-------------------------------------------------------------------------
            PrincipalComponents pca = new PrincipalComponents();
            pca.buildEvaluator(originalData);
            // Return Results
            //-------------------------------------------------------------------------
            result[1]=pca;
            result[0]=pca.transformedData(originalData);
            return result;
            
        } catch (Exception ex) {
            Logger.getLogger(DimentionalityReductionTools.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
  }

 

 //****************************************************************************
//----------------------------------------------------------------------------
//                          Generate PCA
//----------------------------------------------------------------------------
//****************************************************************************
 public static PrincipalComponents evaluatePCA(PrincipalComponents pca,Instances testingData )
  {
        try {
            
            pca.buildEvaluator(testingData);
            
            // Return Results
            //-------------------------------------------------------------------------
            return pca;
        } catch (Exception ex) {
            Logger.getLogger(DimentionalityReductionTools.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
  }
    
    
    
    
//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
//****************************************************************************
//----------------------------------------------------------------------------
//                          Visualize Neural Network
//----------------------------------------------------------------------------
//****************************************************************************
 public static JFrame getPCAVisualization(File transformedDataFile)
  {                        
    if (transformedDataFile!=null)                       
    {
       return FileTools.viewData(transformedDataFile);                
    }                      
    else
        return null;
  }
     
    
    
    
    
    
    
    
    

//****************************************************************************
//----------------------------------------------------------------------------
//                          Generate PCA
//----------------------------------------------------------------------------
//****************************************************************************
 public static Instances generatePCA(Instances originalData, String optionsString)
  {
        try {

            //-------------------------------------------------------------------------
            PrincipalComponents pca = new PrincipalComponents();
            String[] optionsArray = Tools.stringToArray(optionsString);
            pca.setOptions(optionsArray);
            pca.setCenterData(true);          
            Instances transformedDataInstancs = pca.transformedData(originalData);
            // Return Results
            //-------------------------------------------------------------------------
            return transformedDataInstancs;
        } catch (Exception ex) {
            Logger.getLogger(DimentionalityReductionTools.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
  }

 
 //****************************************************************************
//----------------------------------------------------------------------------
//                          Generate PCA
//----------------------------------------------------------------------------
//****************************************************************************
 public static String generatePCA(String originalDataSet, String optionsString)
  {
        try {
            // Load Data
            //-------------------------------------------------------------------------
            Instances originalData = DataSource.read(originalDataSet);
            originalData.setClassIndex(originalData.numAttributes() - 1);
            // Train classifier
            //-------------------------------------------------------------------------
            PrincipalComponents pca = new PrincipalComponents();
            String[] optionsArray = Tools.stringToArray(optionsString);
            pca.setOptions(optionsArray);
            
            Instances transformedData = pca.transformedData(originalData);
            // Return Results
            //-------------------------------------------------------------------------
            return transformedData.toString();
        } catch (Exception ex) {
            Logger.getLogger(DimentionalityReductionTools.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
  }


}
