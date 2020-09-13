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
import weka.classifiers.Evaluation;
import weka.core.converters.ConverterUtils.DataSource;
import weka.classifiers.trees.M5P;
/**
 *
 * @author amb04
 */
public class RulesInduction
{ 
//****************************************************************************
//----------------------------------------------------------------------------
//                          Generate M5P
//----------------------------------------------------------------------------
//****************************************************************************
 public static  M5P generateM5P(String originalDataSet, String optionsString)
  {
        try {
            // Load Data
            //-------------------------------------------------------------------------
            Instances originalData = DataSource.read(originalDataSet);
            originalData.setClassIndex(originalData.numAttributes() - 1);
            // Train classifier
            //-------------------------------------------------------------------------
            M5P m5P = new M5P();
            String[] optionsArray = Tools.stringToArray(optionsString);
            m5P.setOptions(optionsArray);
            m5P.buildClassifier(originalData);
            // Return Results
            //-------------------------------------------------------------------------
            return m5P;
        } catch (Exception ex) {
            Logger.getLogger(RegressionTools.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
  }

//****************************************************************************
//----------------------------------------------------------------------------
//                      Genertae M5P & Evaluation
//----------------------------------------------------------------------------
//****************************************************************************
 public static Evaluation evaluateM5P(M5P m5p, Instances trainingData, Instances testingData,int foldsNumber)
 {
        try {
            // train classifier
            //************************************************************************ 

            Evaluation eval = new Evaluation(testingData);
            //-------------------------------------------------------------------------
            Random rand = new Random(1); // using seed = 1
            eval.crossValidateModel(m5p, testingData, foldsNumber, rand);            
            return eval;
        } catch (Exception ex) {
            Logger.getLogger(RegressionTools.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
  }

 
//****************************************************************************
//----------------------------------------------------------------------------
//                      Genertae M5P & Evaluation
//----------------------------------------------------------------------------
//****************************************************************************
 public static String generateAndEvaluateM5P(String optionsString, Instances trainingData, Instances testingData,int foldsNumber)
 {
        try {
            // train classifier
            //************************************************************************
            M5P cls = new M5P();
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
            Logger.getLogger(RegressionTools.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
  } 
 

}
