/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package toolbox.datatools;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import process_model.basic.util.DateTime;
import toolbox.Tools;
import weka.core.Instance;
import weka.core.Instances;
import weka.filters.unsupervised.instance.Resample;
import weka.filters.unsupervised.instance.Randomize;
import weka.filters.unsupervised.instance.RemoveFolds;
import weka.filters.unsupervised.instance.RemovePercentage;
import weka.filters.unsupervised.instance.RemoveRange;

/**
 *
 * @author amb04
 */
public class SplittingTools {

    //@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
//****************************************************************************
//----------------------------------------------------------------------------
//                          Data RemovePercentage
//----------------------------------------------------------------------------
//****************************************************************************
 public static Instances[] removePercentage(Instances inputDataInstances, String optionsString, Double percentage)
  {
        try {

            //
            RemovePercentage trainingRemovePercentage = new RemovePercentage();
            trainingRemovePercentage.setOptions(Tools.stringToArray(optionsString));
            trainingRemovePercentage.setInputFormat(inputDataInstances);
            trainingRemovePercentage.setPercentage(percentage);
            Instances resultTrainingDataInstances = RemovePercentage.useFilter(inputDataInstances, trainingRemovePercentage);
            //************************************************************************
            RemovePercentage testingRemovePercentage = new RemovePercentage();
            testingRemovePercentage.setInvertSelection(true);
            testingRemovePercentage.setOptions(Tools.stringToArray(optionsString));
            testingRemovePercentage.setInputFormat(inputDataInstances);
            testingRemovePercentage.setPercentage(100 - percentage);
            Instances resultTestingDataInstances = RemovePercentage.useFilter(inputDataInstances, testingRemovePercentage);
            //
            Instances[] resultArray = {resultTrainingDataInstances, resultTestingDataInstances};
            return resultArray;
        } catch (Exception ex) {
            Logger.getLogger(SplittingTools.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
  }




//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
//****************************************************************************
//----------------------------------------------------------------------------
//                          Data RemoveRange
//----------------------------------------------------------------------------
//****************************************************************************
 public static Instances[] split(Instances inputDataInstances, String optionsString, Double percentage)
  {
     //***********************************************************************
        try {

            // Training
            //***********************************************************************
            int trainingDataLength = inputDataInstances.toArray().length;
            int trainingUpperRang = (int) Math.round(trainingDataLength*(100-percentage)/100);
            RemoveRange trainingRemoveRange = new RemoveRange();
            trainingRemoveRange.setOptions(Tools.stringToArray(optionsString));
            trainingRemoveRange.setInputFormat(inputDataInstances);
            trainingRemoveRange.setInstancesIndices("1-" + trainingUpperRang);
            Instances resultTrainingDataInstances = RemoveRange.useFilter(inputDataInstances, trainingRemoveRange);
            // testing
            //************************************************************************
            RemoveRange testingRemoveRange = new RemoveRange();
            //testingRemoveRange.setInvertSelection(true);
            testingRemoveRange.setOptions(Tools.stringToArray(optionsString));
            testingRemoveRange.setInputFormat(inputDataInstances);
            testingRemoveRange.setInstancesIndices((trainingUpperRang + 1) + "-" + trainingDataLength);           
            Instances resultTestingDataInstances = RemoveRange.useFilter(inputDataInstances, testingRemoveRange);
            //
            Instances[] resultArray = {resultTrainingDataInstances, resultTestingDataInstances};
            return resultArray;
        } catch (Exception ex) {
            Logger.getLogger(SplittingTools.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
  }




 public static Instances removeRange(Instances inputInstances,int startRange,int endRange)
 {
     Instances instances=null;
     instances = inputInstances;

     for (int i=startRange; i<endRange;i++)
     {
         instances.delete(i);
     }
     
     return instances;
 }
 
//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
//****************************************************************************
//----------------------------------------------------------------------------
//                          Data RemoveFold
//----------------------------------------------------------------------------
//****************************************************************************
 public static String removeFolds(Instances inputDataInstances, String optionsString, int foldsNumber)
  {
        try {
            //
            RemoveFolds removeFolds = new RemoveFolds();
            removeFolds.setOptions(Tools.stringToArray(optionsString));
            removeFolds.setInputFormat(inputDataInstances);
            removeFolds.setNumFolds(foldsNumber);
            Instances resultDataInstances = RemoveFolds.useFilter(inputDataInstances, removeFolds);
            //
            return resultDataInstances.toString();
        } catch (Exception ex) {
            Logger.getLogger(SplittingTools.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
  }
/*
//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
//****************************************************************************
//----------------------------------------------------------------------------
//                          Data RemoveRange
//----------------------------------------------------------------------------
//****************************************************************************
 public static Instances[] foldingSplit(String dataSetFile, int foldsNumber)
  {
        try {

            // Training
            //***********************************************************************
            BufferedReader trainingDataReader = new BufferedReader(new FileReader(dataSetFile));
            Instances trainingInputInstances = new Instances(trainingDataReader);
            trainingDataReader.close();
            //
            int dataLength = trainingInputInstances.toArray().length;
            //
            RemoveFolds trainingRemoveFolds = new RemoveFolds();
            trainingRemoveFolds.setInputFormat(trainingInputInstances);
            trainingRemoveFolds.setNumFolds(foldsNumber);
            Instances trainingDataInstances = RemoveFolds.useFilter(trainingInputInstances, trainingRemoveFolds);
            //
            System.out.println(trainingDataInstances.toString());
            //

            // Testing
            //***********************************************************************
            BufferedReader testingDataReader = new BufferedReader(new FileReader(dataSetFile));
            Instances testingInputInstances = new Instances(testingDataReader);
            testingDataReader.close();

            RemoveFolds testingRemoveFolds = new RemoveFolds();
            testingRemoveFolds.setInputFormat(testingInputInstances);
            testingRemoveFolds.setNumFolds(foldsNumber);
            testingRemoveFolds.setInvertSelection(true);
            Instances testingDataInstances = RemoveFolds.useFilter(testingInputInstances, testingRemoveFolds);
            //
            System.out.println(testingDataInstances.toString());
            //
            //
            Instances[] resultArray = {trainingDataInstances, testingDataInstances};
            return resultArray;
        } catch (Exception ex) {
            Logger.getLogger(SplittingTools.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
  }
*/



/*
//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
//****************************************************************************
//----------------------------------------------------------------------------
//                          Data RemoveRange
//----------------------------------------------------------------------------
//****************************************************************************
 public static Instances[] split(String dataSetFile, Double percentage)
  {
        try {
            
            // Training
            //***********************************************************************
            BufferedReader trainingDataReader = new BufferedReader(new FileReader(dataSetFile));
            Instances trainingInputInstances = new Instances(trainingDataReader);
            trainingDataReader.close();
            //
            int dataLength = trainingInputInstances.toArray().length;
            //
            int trainingUpperRang = (int) Math.round(dataLength*(100-percentage)/100);

            RemoveRange trainingRemoveRange = new RemoveRange();
            trainingRemoveRange.setInputFormat(trainingInputInstances);
            trainingRemoveRange.setInstancesIndices("1-" + trainingUpperRang);

            Instances trainingDataInstances = RemoveRange.useFilter(trainingInputInstances, trainingRemoveRange);
            //Instances trainingDataInstances = removeRange(trainingInputInstances, 1,trainingUpperRang-1);
            //
            System.out.println(trainingDataInstances.toString());
            //

            // testing
            //************************************************************************
            BufferedReader testingDataReader = new BufferedReader(new FileReader(dataSetFile));
            Instances testingInputInstances = new Instances(testingDataReader);
            testingDataReader.close();
            
            RemoveRange testingRemoveRange = new RemoveRange();            
            testingRemoveRange.setInputFormat(testingInputInstances);
            testingRemoveRange.setInstancesIndices(trainingUpperRang + "-" + dataLength);

            Instances testingDataInstances=RemoveRange.useFilter(testingInputInstances, testingRemoveRange);
            //Instances testingDataInstances = removeRange(testingInputInstances, trainingUpperRang,dataLength-1);
            //
            System.out.println(testingDataInstances.toString());
            //
            Instances[] resultArray = {trainingDataInstances, testingDataInstances};
            return resultArray;
        } catch (Exception ex) {
            Logger.getLogger(SplittingTools.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
  }
*/


 /*
//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
//****************************************************************************
//----------------------------------------------------------------------------
//                          Data RemoveRange
//----------------------------------------------------------------------------
//****************************************************************************
 public static Instances[] randomizedPercentageSplit(Instances inputDataInstances, String optionsString, Double percentage)
  {
     //************************************************************************
        try {

            // Randomize
            //************************************************************************
            Randomize randomize = new Randomize();
            randomize.setRandomSeed(1);
            randomize.setOptions(Tools.stringToArray(optionsString));
            randomize.setInputFormat(inputDataInstances);
            Instances dataInstances = Randomize.useFilter(inputDataInstances, randomize);
            // Training
            //***********************************************************************
            Instances trainingInputDataInstances = dataInstances;
            int trainingDataLength = trainingInputDataInstances.toArray().length;
            //trainingReader.close();
            int trainingUpperRang = (int) Math.round(trainingDataLength*(100-percentage)/100);
            RemoveRange trainingRemoveRange = new RemoveRange();
            trainingRemoveRange.setOptions(Tools.stringToArray(optionsString));
            trainingRemoveRange.setInputFormat(trainingInputDataInstances);
            trainingRemoveRange.setInstancesIndices("1-" + trainingUpperRang);
            Instances resultTrainingDataInstances = RemoveRange.useFilter(trainingInputDataInstances, trainingRemoveRange);
            // testing
            //************************************************************************
            Instances testingInputDataInstances = dataInstances;
            //int testingDataLength=testingInputDataInstances.toArray().length;
            //testingReader.close();
            RemoveRange testingRemoveRange = new RemoveRange();
            testingRemoveRange.setInvertSelection(true);
            testingRemoveRange.setOptions(Tools.stringToArray(optionsString));
            testingRemoveRange.setInputFormat(testingInputDataInstances);
            testingRemoveRange.setInstancesIndices((trainingUpperRang + 1) + "-" + trainingDataLength);
            Instances resultTestingDataInstances = RemoveRange.useFilter(testingInputDataInstances, testingRemoveRange);
            //
            Instances[] resultArray = {resultTrainingDataInstances, resultTestingDataInstances};
            return resultArray;
        } catch (Exception ex) {
            Logger.getLogger(SplittingTools.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
  }
*/




/*
 //@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
//****************************************************************************
//----------------------------------------------------------------------------
//                          Data RemoveRange
//----------------------------------------------------------------------------
//****************************************************************************
 public static Instances[] randomizedPercentageSplit(Instances inputDataInstances,option, Double percentage)
  {     
        try {
            // Randomize
            //***********************************************************************

            Randomize randomize = new Randomize();
            randomize.setRandomSeed(1);
            randomize.setInputFormat(inputDataInstances);
            Instances dataInstances = Randomize.useFilter(inputDataInstances, randomize);
            // Training
            //***********************************************************************
            Instances trainingInputDataInstances = dataInstances;
            int trainingDataLength = trainingInputDataInstances.toArray().length;
            //trainingReader.close();
            int trainingUpperRang = (int) Math.round(trainingDataLength*(100-percentage)/100);
            RemoveRange trainingRemoveRange = new RemoveRange();
            trainingRemoveRange.setInputFormat(trainingInputDataInstances);
            trainingRemoveRange.setInstancesIndices("1-" + trainingUpperRang);
            Instances resultTrainingDataInstances = RemoveRange.useFilter(trainingInputDataInstances, trainingRemoveRange);
            // testing
            //************************************************************************
            Instances testingInputDataInstances = dataInstances;
            //int testingDataLength=testingInputDataInstances.toArray().length;
            //testingReader.close();
            RemoveRange testingRemoveRange = new RemoveRange();
            testingRemoveRange.setInvertSelection(true);
            testingRemoveRange.setInputFormat(testingInputDataInstances);
            testingRemoveRange.setInstancesIndices((trainingUpperRang + 1) + "-" + trainingDataLength);
            Instances resultTestingDataInstances = RemoveRange.useFilter(testingInputDataInstances, testingRemoveRange);
            //
            Instances[] resultArray = {resultTrainingDataInstances, resultTestingDataInstances};
            return resultArray;
        } catch (Exception ex) {
            Logger.getLogger(SplittingTools.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
  }

*/

 //@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
//****************************************************************************
//----------------------------------------------------------------------------
//                          Data RemoveRange
//----------------------------------------------------------------------------
//****************************************************************************
 public static Instances removeRange(Instances inputDataInstances, String optionsString)
  {
        try {                        
            RemoveRange removeRange = new RemoveRange();
            removeRange.setOptions(Tools.stringToArray(optionsString));
            removeRange.setInputFormat(inputDataInstances);
            Instances resultDataInstances = RemoveRange.useFilter(inputDataInstances, removeRange);
            //
            return resultDataInstances;
        } catch (Exception ex) {
            Logger.getLogger(SplittingTools.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
  }


//
// this should be used only when data have less examples for some classes than others
//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
//****************************************************************************
//----------------------------------------------------------------------------
//                          Data RemoveWithValues
//----------------------------------------------------------------------------
//****************************************************************************
 public static Instances[] reSample(Instances inputDataInstances, Double sampleSizePercent,int numFolds, int numFold)
  {
        try {
            //
            Resample resampler = new Resample();
            resampler.setInputFormat(inputDataInstances);
            resampler.setSampleSizePercent(sampleSizePercent);
            //
            Instances resultDataInstances = Resample.useFilter(inputDataInstances, resampler);
            //
            Instances resultDataInstances1 = resultDataInstances.trainCV(numFolds, numFold);
            Instances resultDataInstances2 = resultDataInstances.testCV(numFolds, numFold);
            //
            Instances[] resultString = {resultDataInstances1, resultDataInstances2};
            //
            return resultString;
        } catch (Exception ex) {
            Logger.getLogger(SplittingTools.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
  }





//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
//****************************************************************************
//----------------------------------------------------------------------------
//                          Data RemoveRange
//----------------------------------------------------------------------------
//****************************************************************************
 public static Instances[] intelligentSplit(Instances inputDataInstances)
  {
     Instances resultTrainingDataInstances=inputDataInstances;
 
     int i=0;
     while (i<resultTrainingDataInstances.size())
     {
        resultTrainingDataInstances.remove(i);
        i=i+2;
     }

     // testing
     //***********************************************************************     
     Instances resultTestingDataInstances=resultTrainingDataInstances;
     int j=1;
     while (j<resultTestingDataInstances.size()-2)
     {
        resultTestingDataInstances.remove(j);
        j=j+2;
     }

     Instances[] resultArray={resultTrainingDataInstances,resultTestingDataInstances};

     return resultArray;
  }


// this is designed for
//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
//****************************************************************************
//----------------------------------------------------------------------------
//                          intelligentSplit
//----------------------------------------------------------------------------
//****************************************************************************
 public static Instances[] intelligentSplit(File dataSetFile)
  {
        try {
            //************************************************************************
            BufferedReader dataReader1 = new BufferedReader(new FileReader(dataSetFile.getPath()));
            Instances inputDataInstances = new Instances(dataReader1);
            dataReader1.close();
            BufferedReader dataReader2 = new BufferedReader(new FileReader(dataSetFile.getPath()));
            Instances trainingDataInstances = new Instances(dataReader2);
            dataReader2.close();
            BufferedReader dataReader3 = new BufferedReader(new FileReader(dataSetFile.getPath()));
            Instances testingDataInstances = new Instances(dataReader3);
            dataReader3.close();            
            inputDataInstances.setClassIndex(inputDataInstances.numAttributes() - 1);            

            // training
            //************************************************************************
            ArrayList<Instance> instanceList = new ArrayList<Instance>();
            for (int i = 0; i < inputDataInstances.size(); i++) {
                if (inputDataInstances.get(i) != null) {
                    instanceList.add(inputDataInstances.get(i));
                }
            }
            //--------------------------------------------------------------------
            int j = 0;
            while (j < instanceList.size() - 2) {
                instanceList.remove(j + 2);
                j = j + 2;
            }
            //---------------------------------------------------------------------
            // saving the processed data as instance
            trainingDataInstances.clear();
            for (int i = 0; i < instanceList.size(); i++) {
                if (instanceList.get(i) != null) {
                    trainingDataInstances.add(instanceList.get(i));
                }
            }
            // Resetting the array list to hold the original data
            //************************************************************************
            instanceList = new ArrayList<Instance>();
            for (int i = 0; i < inputDataInstances.size(); i++) {
                if (inputDataInstances.get(i) != null) {
                    instanceList.add(inputDataInstances.get(i));
                }
            }
            //************************************************************************
            // testing
            //************************************************************************
            int k = 0;
            while (k < instanceList.size()) {
                instanceList.remove(k);
                k = k + 2;
            }
            int m = 0;
            while (m < instanceList.size() - 1) {
                instanceList.remove(m);
                m = m + 1;
            }
            //---------------------------------------------------------------------
            testingDataInstances.clear();
            for (int i = 0; i < instanceList.size(); i++) {
                testingDataInstances.add(instanceList.get(i));
            }
            Instances[] resultArray = {trainingDataInstances, testingDataInstances};
            return resultArray;
        } catch (IOException ex) {
            Logger.getLogger(SplittingTools.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
  }


 // this is designed for
//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
//****************************************************************************
//----------------------------------------------------------------------------
//                          intelligentSplit
//----------------------------------------------------------------------------
//****************************************************************************
 public static Instances[] intelligentSplit(File dataSetFile,int replicaNumber)
  {
        try {
            //************************************************************************
            BufferedReader dataReader1 = new BufferedReader(new FileReader(dataSetFile.getPath()));
            Instances inputDataInstances = new Instances(dataReader1);
            dataReader1.close();
            BufferedReader dataReader2 = new BufferedReader(new FileReader(dataSetFile.getPath()));
            Instances trainingDataInstances = new Instances(dataReader2);
            dataReader2.close();
            BufferedReader dataReader3 = new BufferedReader(new FileReader(dataSetFile.getPath()));
            Instances testingDataInstances = new Instances(dataReader3);
            dataReader3.close();
            inputDataInstances.setClassIndex(inputDataInstances.numAttributes() - 1);

            // training
            //************************************************************************
            ArrayList<Instance> instanceList = new ArrayList<Instance>();
            for (int i = 0; i < inputDataInstances.size(); i++) {
                if (inputDataInstances.get(i) != null) {
                    instanceList.add(inputDataInstances.get(i));
                }
            }
            //--------------------------------------------------------------------
            int j = 0;
            while (j < instanceList.size() - (replicaNumber-1)) {
                instanceList.remove(j + (replicaNumber-1));
                j = j + (replicaNumber-1);
            }
            //---------------------------------------------------------------------
            // saving the processed data as instance
            trainingDataInstances.clear();
            for (int i = 0; i < instanceList.size(); i++) {
                if (instanceList.get(i) != null) {
                    trainingDataInstances.add(instanceList.get(i));
                }
            }
            // Resetting the array list to hold the original data
            //************************************************************************
            instanceList = new ArrayList<Instance>();
            for (int i = 0; i < inputDataInstances.size(); i++) {
                if (inputDataInstances.get(i) != null) {
                    instanceList.add(inputDataInstances.get(i));
                }
            }
            //************************************************************************
            // testing
            //************************************************************************
            int k = 0;
            while (k < instanceList.size()) {
                instanceList.remove(k);
                k = k + (replicaNumber-1);
            }
            int m = 0;
            while (m < instanceList.size() - 1) {
                instanceList.remove(m);
                m = m + 1;
            }
            //---------------------------------------------------------------------
            testingDataInstances.clear();
            for (int i = 0; i < instanceList.size(); i++) {
                testingDataInstances.add(instanceList.get(i));
            }
            Instances[] resultArray = {trainingDataInstances, testingDataInstances};
            return resultArray;
        } catch (IOException ex) {
            Logger.getLogger(SplittingTools.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
  }






//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
//****************************************************************************
//----------------------------------------------------------------------------
//                          Data RemoveRange
//----------------------------------------------------------------------------
//****************************************************************************
 public static Instances[] percentageSplit(Instances inputDataInstances, Double percentage)
  {
        try {

            // Training
            //***********************************************************************
            int trainingDataLength = inputDataInstances.toArray().length;
            int trainingUpperRang = (int) Math.round(trainingDataLength*(100-percentage)/100);
            RemoveRange trainingRemoveRange = new RemoveRange();
            trainingRemoveRange.setInputFormat(inputDataInstances);
            trainingRemoveRange.setInstancesIndices("1-" + trainingUpperRang);
            Instances resultTrainingDataInstances = RemoveRange.useFilter(inputDataInstances, trainingRemoveRange);

            // testing
            //************************************************************************
            RemoveRange testingRemoveRange = new RemoveRange();
            testingRemoveRange.setInputFormat(inputDataInstances);
            testingRemoveRange.setInstancesIndices((trainingUpperRang + 1) + "-" + trainingDataLength);
            Instances resultTestingDataInstances = RemoveRange.useFilter(inputDataInstances, testingRemoveRange);
            //
            Instances[] resultArray = {resultTrainingDataInstances, resultTestingDataInstances};
            return resultArray;
        } catch (Exception ex) {
            Logger.getLogger(SplittingTools.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
  }


 //@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
//****************************************************************************
//----------------------------------------------------------------------------
//                          Data RemoveRange
//----------------------------------------------------------------------------
//****************************************************************************
 public static Instances[] randomizedPercentageSplit(Instances dataInstances, Double percentage)
  {
        try {

            // Randomize
            //***********************************************************************
            Randomize randomize = new Randomize();
            randomize.setRandomSeed(new DateTime().getSecond());
            randomize.setInputFormat(dataInstances);
            Instances inputDataInstances = Randomize.useFilter(dataInstances, randomize);

            // Training
            //***********************************************************************
            int trainingDataLength = inputDataInstances.toArray().length;
            int trainingUpperRang = (int) Math.round(trainingDataLength*(100-percentage)/100);
            RemoveRange trainingRemoveRange = new RemoveRange();
            trainingRemoveRange.setInputFormat(inputDataInstances);
            trainingRemoveRange.setInstancesIndices("1-" + trainingUpperRang);
            Instances resultTrainingDataInstances = RemoveRange.useFilter(inputDataInstances, trainingRemoveRange);

            // testing
            //************************************************************************
            RemoveRange testingRemoveRange = new RemoveRange();
            testingRemoveRange.setInputFormat(inputDataInstances);
            testingRemoveRange.setInstancesIndices((trainingUpperRang + 1) + "-" + trainingDataLength);
            Instances resultTestingDataInstances = RemoveRange.useFilter(inputDataInstances, testingRemoveRange);
            //
            Instances[] resultArray = {resultTrainingDataInstances, resultTestingDataInstances};
            return resultArray;
        } catch (Exception ex) {
            Logger.getLogger(SplittingTools.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
  }



  //@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
//****************************************************************************
//----------------------------------------------------------------------------
//                          Data RemoveRange
//----------------------------------------------------------------------------
//****************************************************************************
 public static Instances[] randomizedPercentageSplit(Instances dataInstances)
  {
        try {

            // Randomized Percentage between (50% and 75%)
            //***********************************************************************
            Random generator = new Random(new DateTime().getSecond()/60);
            double percentage = generator.nextDouble() * 30.0 + 50.0;

            // Randomize
            //***********************************************************************
            Randomize randomize = new Randomize();
            randomize.setRandomSeed(new DateTime().getSecond());
            randomize.setInputFormat(dataInstances);
            Instances inputDataInstances = Randomize.useFilter(dataInstances, randomize);

            // Training
            //***********************************************************************
            int trainingDataLength = inputDataInstances.toArray().length;
            int trainingUpperRang = (int) Math.round(trainingDataLength*(100-percentage)/100);
            RemoveRange trainingRemoveRange = new RemoveRange();
            trainingRemoveRange.setInputFormat(inputDataInstances);
            trainingRemoveRange.setInstancesIndices("1-" + trainingUpperRang);
            Instances resultTrainingDataInstances = RemoveRange.useFilter(inputDataInstances, trainingRemoveRange);

            // testing
            //************************************************************************
            RemoveRange testingRemoveRange = new RemoveRange();
            testingRemoveRange.setInputFormat(inputDataInstances);
            testingRemoveRange.setInstancesIndices((trainingUpperRang + 1) + "-" + trainingDataLength);
            Instances resultTestingDataInstances = RemoveRange.useFilter(inputDataInstances, testingRemoveRange);
            //
            Instances[] resultArray = {resultTrainingDataInstances, resultTestingDataInstances};
            return resultArray;
        } catch (Exception ex) {
            Logger.getLogger(SplittingTools.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
  }






//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
//****************************************************************************
//----------------------------------------------------------------------------
//                          Data RemoveRange
//----------------------------------------------------------------------------
//****************************************************************************
 public static Instances[] foldingSplit(Instances inputInstances, int foldsNumber)
  {
     //***********************************************************************
        try {
            // Randomize
            //***********************************************************************
            Randomize randomize = new Randomize();
            randomize.setRandomSeed(new DateTime().getSecond());
            randomize.setInputFormat(inputInstances);
            Instances inputDataInstances = Randomize.useFilter(inputInstances, randomize);

            // Training
            //***********************************************************************
            RemoveFolds trainingRemoveFolds = new RemoveFolds();
            trainingRemoveFolds.setInputFormat(inputDataInstances);
            trainingRemoveFolds.setNumFolds(foldsNumber);
            trainingRemoveFolds.setInvertSelection(true);
            Instances trainingDataInstances = RemoveFolds.useFilter(inputDataInstances, trainingRemoveFolds);

            // testing
            //************************************************************************
            RemoveFolds testingRemoveFolds = new RemoveFolds();
            testingRemoveFolds.setInputFormat(inputDataInstances);
            testingRemoveFolds.setNumFolds(foldsNumber);            
            Instances testingDataInstances = RemoveFolds.useFilter(inputDataInstances, testingRemoveFolds);
            //
            Instances[] resultArray = {trainingDataInstances, testingDataInstances};
            return resultArray;
        } catch (Exception ex) {
            Logger.getLogger(SplittingTools.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
  }

}
