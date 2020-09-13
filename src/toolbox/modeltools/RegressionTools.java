/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package toolbox.modeltools;
import java.util.logging.Level;
import java.util.logging.Logger;
import weka.core.Instances;
import java.util.Random;
import javax.swing.JOptionPane;
import toolbox.Tools;
import toolbox.viewtools.ModelVisualization_JFrame;
import weka.classifiers.Evaluation;
import weka.classifiers.functions.LinearRegression;
import weka.classifiers.functions.SMOreg;
import weka.core.converters.ConverterUtils.DataSource;
/**
 *
 * @author amb04
 */
public class RegressionTools
{

 
//****************************************************************************
//----------------------------------------------------------------------------
//                          Generate LinearRegression
//----------------------------------------------------------------------------
//****************************************************************************
 public static  LinearRegression generateLinearRegression(Instances originalData, String optionsString)
  {
        try {
            // Load Data
            //-------------------------------------------------------------------------
            //Instances originalData = DataSource.read(originalDataSet);
            //originalData.setClassIndex(originalData.numAttributes() - 1);
            // Train classifier
            //-------------------------------------------------------------------------
            LinearRegression regression = new LinearRegression();
            String[] optionsArray = Tools.stringToArray(optionsString);
            regression.setOptions(optionsArray);
            regression.buildClassifier(originalData);
            // Return Results
            //-------------------------------------------------------------------------
            return regression;
       } catch (weka.core.UnsupportedAttributeTypeException ex) {
            JOptionPane.showMessageDialog(null,"Linear Regression cannot handle multi-valued nominal class!");
            //Logger.getLogger(RegressionTools.class.getName()).log(Level.SEVERE, null, ex);            
            return null;        
       
       } catch (Exception ex) 
       {   
            Logger.getLogger(RegressionTools.class.getName()).log(Level.SEVERE, null, ex);                       
            return null;        
       }
  }

 //****************************************************************************
//----------------------------------------------------------------------------
//                      Genertae LinearRegression & Evaluation
//----------------------------------------------------------------------------
//****************************************************************************
 public static Evaluation evaluateLinearRegression(LinearRegression cls, Instances testingData,int foldsNumber) {
        try {
            // train classifier
            //************************************************************************            
            Evaluation eval = new Evaluation(testingData);
            Random rand = new Random(1); // using seed = 1
            eval.crossValidateModel(cls, testingData, foldsNumber, rand);            
            return eval;
            
        } catch (weka.core.UnsupportedAttributeTypeException ex) {
            JOptionPane.showMessageDialog(null,"Linear Regression cannot handle multi-valued nominal class!");
            //Logger.getLogger(RegressionTools.class.getName()).log(Level.SEVERE, null, ex);            
            return null;
                        
        } catch (Exception ex) {
            Logger.getLogger(RegressionTools.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
  }
 
 
 //****************************************************************************
//----------------------------------------------------------------------------
//                          Generate SMO Regression using SVM
//----------------------------------------------------------------------------
//****************************************************************************
 public static  SMOreg generateSMORegression(Instances originalData, String optionsString)
  {
        try {
            // Load Data
            //-------------------------------------------------------------------------            
            originalData.setClassIndex(originalData.numAttributes() - 1);
            // Train classifier
            //-------------------------------------------------------------------------
            SMOreg smo = new SMOreg();
            if (optionsString!=null && !optionsString.equals("") && !optionsString.equals(" "))
            {
                String[] optionsArray = Tools.stringToArray(optionsString);                
                smo.setOptions(optionsArray);
            }
            smo.buildClassifier(originalData);
            // Return Results
            //-------------------------------------------------------------------------
            return smo;
            
        } catch (weka.core.UnsupportedAttributeTypeException ex) {
            JOptionPane.showMessageDialog(null,"Support Vector machine SOMReg cannot handle multi-valued nominal class!");
            //Logger.getLogger(RegressionTools.class.getName()).log(Level.SEVERE, null, ex);            
            return null;                    
            
        } catch (Exception ex) {
            Logger.getLogger(RegressionTools.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
  }

//****************************************************************************
//----------------------------------------------------------------------------
//                      Genertae SMO Regression using SVM & Evaluation
//----------------------------------------------------------------------------
//****************************************************************************
 public static Evaluation evaluateSMORegression(SMOreg smo, Instances testingData,int foldsNumber)
 {
        try {
            // train classifier
            //************************************************************************ 

            Evaluation eval = new Evaluation(testingData);
            //-------------------------------------------------------------------------
            Random rand = new Random(1); // using seed = 1
            eval.crossValidateModel(smo, testingData, foldsNumber, rand);               
            return eval;
        } catch (Exception ex) {
            Logger.getLogger(RegressionTools.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
  }

 
//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
//****************************************************************************
//----------------------------------------------------------------------------
//                          Visualize Neural Network
//----------------------------------------------------------------------------
//****************************************************************************
 public static ModelVisualization_JFrame getSMORegressionVisualization(String text)
  {
        try {        
                        
            if (text!=null)                       
            {
                ModelVisualization_JFrame jf = new ModelVisualization_JFrame(text);
                return jf;
            }
           else
               return null;
           
            // Evaluation
            //----------------------------------------------------------------            
        } catch (Exception ex) 
        {
            Logger.getLogger(FeatureAnalysisTools.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
  }
 
 
 
 
 
//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
//****************************************************************************
//----------------------------------------------------------------------------
//                          Visualize Neural Network
//----------------------------------------------------------------------------
//****************************************************************************
 public static ModelVisualization_JFrame getLinearRegressionVisualization(String text)
  {
        try {        
                        
            if (text!=null)                       
            {
                ModelVisualization_JFrame jf = new ModelVisualization_JFrame(text);
                return jf;
            }
           else
               return null;
           
            // Evaluation
            //----------------------------------------------------------------            
        } catch (Exception ex) 
        {
            Logger.getLogger(FeatureAnalysisTools.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
  }
 
//****************************************************************************
//----------------------------------------------------------------------------
//                      Genertae LinearRegression & Evaluation
//----------------------------------------------------------------------------
//****************************************************************************
 public static String generateAndEvaluateLinearRegression(String optionsString, Instances trainingData, Instances testingData,int foldsNumber) {
        try {
            // train classifier
            //************************************************************************
            LinearRegression cls = new LinearRegression();

            //-------------------------------------------------------------------------
            String[] optionsArray = Tools.stringToArray(optionsString);
            cls.setOptions(optionsArray);
            cls.buildClassifier(trainingData);
            //************************************************************************
            if (testingData == null) {
                testingData = trainingData;
            }

            //-------------------------------------------------------------------------
            Evaluation eval = new Evaluation(testingData);
            //-------------------------------------------------------------------------
            Random rand = new Random(1); // using seed = 1
            eval.crossValidateModel(cls, testingData, foldsNumber, rand);
            String evalString = eval.toSummaryString();
            return cls.toString() + evalString;
       } catch (weka.core.UnsupportedAttributeTypeException ex) {
            JOptionPane.showMessageDialog(null,"Linear Regression cannot handle multi-valued nominal class!");
            Logger.getLogger(RegressionTools.class.getName()).log(Level.SEVERE, null, ex);            
            return null;
                        
        } catch (Exception ex) {
            Logger.getLogger(RegressionTools.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
  }

}
