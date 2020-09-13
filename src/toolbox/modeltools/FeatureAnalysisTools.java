/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package toolbox.modeltools;
import java.util.logging.Level;
import java.util.logging.Logger;
import weka.core.Instances;
import java.util.Random;
import toolbox.Tools;
import toolbox.viewtools.ModelVisualization_JFrame;
import weka.classifiers.Evaluation;
import weka.classifiers.trees.RandomForest;
/**
 *
 * @author amb04
 */
public class FeatureAnalysisTools
{

//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
 //****************************************************************************
//----------------------------------------------------------------------------
//                          Generate RandomForest
//----------------------------------------------------------------------------
//****************************************************************************
 public static  RandomForest generateRandomForest(Instances originalData, String optionsString)
  {
        try {            
            
            // Train classifier
            //-------------------------------------------------------------------------
            RandomForest randomForest = new RandomForest();
            
            if (optionsString!=null && !optionsString.equals("") && !optionsString.equals(" "))
            {
                String[] optionsArray = Tools.stringToArray(optionsString);
                randomForest.setOptions(optionsArray);
            }             
            randomForest.setNumFeatures(originalData.numAttributes());
            randomForest.buildClassifier(originalData);
            // Return Results
            //-------------------------------------------------------------------------
            return randomForest;
        } catch (Exception ex) {
            Logger.getLogger(FeatureAnalysisTools.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
  }

//****************************************************************************
//----------------------------------------------------------------------------
//                      Genertae RandomForest & Evaluation
//----------------------------------------------------------------------------
//****************************************************************************
 public static Evaluation evaluateRandomForest(RandomForest randomForest, Instances testingData,int foldsNumber) {
        try {
            //************************************************************************
            Evaluation eval = new Evaluation(testingData);
            //-------------------------------------------------------------------------
            Random rand = new Random(1); // using seed = 1
            eval.crossValidateModel(randomForest, testingData, foldsNumber, rand);
            return eval;
        } catch (Exception ex) {
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
 public static ModelVisualization_JFrame getRandomForestVisualization(String text)
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
//                      Genertae RandomForest & Evaluation
//----------------------------------------------------------------------------
//****************************************************************************
 public static String generateAndEvaluateRandomForest(String optionsString, Instances trainingData, Instances testingData,int foldsNumber) {
        try {
            // train classifier
            //************************************************************************
            RandomForest cls = new RandomForest();
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
            String evalString = eval.toSummaryString() + eval.toClassDetailsString() + eval.toMatrixString();
            return cls.toString() + evalString;
        } catch (Exception ex) {
            Logger.getLogger(FeatureAnalysisTools.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
  }

}
