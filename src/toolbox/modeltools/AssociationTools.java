/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package toolbox.modeltools;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.BufferedReader;
import java.io.FileReader;
import weka.classifiers.trees.J48;
import weka.clusterers.DensityBasedClusterer;
import weka.gui.treevisualizer.PlaceNode2;
import weka.gui.treevisualizer.TreeVisualizer;

import weka.core.Instances;

import weka.attributeSelection.PrincipalComponents;
import java.awt.BorderLayout;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import toolbox.Tools;
import weka.associations.Apriori;
import weka.associations.AssociatorEvaluation;

import weka.classifiers.Evaluation;
import weka.classifiers.bayes.BayesNet;
import weka.classifiers.functions.LinearRegression;

import weka.clusterers.ClusterEvaluation;
import weka.clusterers.Clusterer;
import weka.clusterers.Cobweb;
import weka.core.Instance;
import weka.core.converters.ArffLoader;
import weka.core.converters.ConverterUtils.DataSource;
import weka.gui.explorer.ClustererAssignmentsPlotInstances;
import weka.gui.visualize.VisualizePanel;
import weka.gui.graphvisualizer.GraphVisualizer;
import weka.classifiers.functions.MultilayerPerceptron;
import weka.classifiers.functions.SMO;
import weka.classifiers.pmml.consumer.Regression;
import weka.classifiers.trees.M5P;
import weka.classifiers.trees.RandomForest;
import weka.classifiers.trees.j48.C45PruneableClassifierTree;
import weka.clusterers.AbstractClusterer;
import weka.clusterers.HierarchicalClusterer;
/**
 *
 * @author amb04
 */
public class AssociationTools
{

//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
//****************************************************************************
//----------------------------------------------------------------------------
//                          Gnerate AssociationTools Rules
//----------------------------------------------------------------------------
//****************************************************************************
 public static Apriori generateAssociationRules(Instances dataInstances, String optionsString)
  {
        try {
            // load trainingData
            //*************************************************************************
            Apriori apriori = new Apriori();
            //-------------------------------------------------------------------------
            String[] optionsArray = Tools.stringToArray(optionsString);
            apriori.setOptions(optionsArray);
            apriori.setClassIndex(dataInstances.classIndex());
            apriori.buildAssociations(dataInstances);
            return apriori;
        } catch (Exception ex) {
            Logger.getLogger(AssociationTools.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
  }


//****************************************************************************
//----------------------------------------------------------------------------
//                          Gnerate  & Evaluate AssociationTools Rules
//----------------------------------------------------------------------------
//****************************************************************************
 public static String generateAndEvaluateAssociationRules(Instances trainingData,Instances testingData,String optionsString)
  {
        try {
            // load trainingData
            // build associator
            Apriori apriori = new Apriori();
            //-------------------------------------------------------------------------
            String[] optionsArray = Tools.stringToArray(optionsString);
            apriori.setOptions(optionsArray);
            //-------------------------------------------------------------------------
            apriori.setClassIndex(trainingData.classIndex());

            apriori.buildAssociations(trainingData);
            
            //************************************************************************
            if (testingData == null) {
                testingData = trainingData;
            }
                        
            //-------------------------------------------------------------------------
            //Evaluation eval = new Evaluation(testingData);
            //-------------------------------------------------------------------------
           // Random rand = new Random(1); // using seed = 1
            //eval.crossValidateModel(apriori.getClass().getSimpleName().toString(), testingData, 10, null, rand);            
            // output associator
            //-------------------------------------------------------------------------
            //return apriori.toString() + eval.toString();
            return apriori.toString();
        
        } catch (weka.core.UnsupportedAttributeTypeException ex) {            
            JOptionPane.showMessageDialog(null, "Apriori Algorithime cannot handle numeric values !\n "
                                              + "Discretize the numeric values of the data set and try again");
            Logger.getLogger(AssociationTools.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }        
        catch (Exception ex) 
        {
            JOptionPane.showMessageDialog(null, "Model building using Apriori Algorithm was unsuccessfull!\n");
            Logger.getLogger(AssociationTools.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
  }
  
 
 
//****************************************************************************
//----------------------------------------------------------------------------
//                          Gnerate  & Evaluate AssociationTools Rules
//----------------------------------------------------------------------------
//****************************************************************************
 public static String generateAndEvaluateAssociationRules(Instances trainingData,Instances testingData,int foldsNumber, String optionsString)
  {
        try {
            // load trainingData
            // build associator
            Apriori apriori = new Apriori();
            //-------------------------------------------------------------------------
            String[] optionsArray = Tools.stringToArray(optionsString);
            apriori.setOptions(optionsArray);
            //-------------------------------------------------------------------------
            apriori.setClassIndex(trainingData.classIndex());
            apriori.buildAssociations(trainingData);
            //************************************************************************
            if (testingData == null) {
                testingData = trainingData;
            }
            //-------------------------------------------------------------------------
            Evaluation eval = new Evaluation(testingData);
            //-------------------------------------------------------------------------
            Random rand = new Random(1); // using seed = 1
            eval.crossValidateModel(apriori.getClass().getSimpleName().toString(), testingData, foldsNumber, null, rand);
            // output associator
            //-------------------------------------------------------------------------
            return apriori.toString() + eval.toString();
        } catch (Exception ex) {
            Logger.getLogger(AssociationTools.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
  }
 

//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
//****************************************************************************
//----------------------------------------------------------------------------
//                          Gnerate Association Rules
//----------------------------------------------------------------------------
//****************************************************************************
 public static AssociatorEvaluation evaluateAssociationRules(Apriori apriori, Instances testingData,int foldsNumber)
  {
        try {

            //-------------------------------------------------------------------------
            AssociatorEvaluation eval = new AssociatorEvaluation();
            eval.evaluate(apriori, testingData);            
            return eval;
            
        } catch (Exception ex) {
            Logger.getLogger(ROC.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
  }

 
 
 
}
