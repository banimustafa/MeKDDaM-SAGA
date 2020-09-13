/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package toolbox.modeltools;
import toolbox.modeltools.algorithms.MLP;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.BufferedReader;
import java.io.FileReader;
import weka.classifiers.trees.J48;
import weka.gui.treevisualizer.PlaceNode2;
import weka.core.Instances;
import java.awt.BorderLayout;
import java.io.File;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import toolbox.Tools;
import toolbox.modeltools.algorithms.MLP_Visualization;
import toolbox.modeltools.algorithms.TreeVisualizer;
import toolbox.viewtools.ModelVisualization_JFrame;
import toolbox.viewtools.VisualizationTools;
import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.classifiers.bayes.BayesNet;
import weka.core.converters.ConverterUtils.DataSource;
import weka.gui.graphvisualizer.GraphVisualizer;
import weka.classifiers.functions.SMO;
import weka.gui.explorer.ClassifierErrorsPlotInstances;
import weka.gui.visualize.VisualizePanel;
/**
 *
 * @author amb04
 */
public class ClassificationTools
{

    
    
    
    
    
    
    
//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ 
//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@  
//                             Model Generation
//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ 
//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@  

    
    
    
//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
//****************************************************************************
//----------------------------------------------------------------------------
//                          Generate Neural Network
//----------------------------------------------------------------------------
//****************************************************************************
 public static MLP generateMLP(String optionsString, Instances trainingData)
   {
        try {
            //************************************************************************
            MLP mlp = new MLP();
            /*
            String[] optionsArray = null;            
            if (optionsString == null || optionsString.equals("") || optionsString.equals(" ")) 
            {                
                String[] mainOptionArray = new String[3];
                mainOptionArray[0] = "-t";
                mainOptionArray[1] = trainingData.toString();
                mainOptionArray[2] = "-G";
                optionsArray = mainOptionArray;
            } else {
                optionsArray = Tools.stringToArray(optionsString);                
            }
            //mlp.setOptions(optionsArray);
             //mlp.runMLP(optionsArray, optionsString);
            */
            mlp.buildClassifier(trainingData);
            
            return mlp;
            
        } catch (weka.core.UnsupportedAttributeTypeException x) {
            JOptionPane.showMessageDialog(null,"Classifier require multi-valued nominal class!");
            Logger.getLogger(ClassificationTools.class.getName()).log(Level.SEVERE, null, x);
            return null;
         
        } catch (Exception ex) {
            Logger.getLogger(ClassificationTools.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
 
 
 
//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
//****************************************************************************
//----------------------------------------------------------------------------
//                          Generate BayesNet
//----------------------------------------------------------------------------
//****************************************************************************
 public static BayesNet generateBayesNet(String optionsString, Instances trainingData)
  {
        try {
            // Training
            //-------------------------------------------------------------------------
            BayesNet cls = new BayesNet();
            if (optionsString==null || optionsString.equals(""))
                optionsString=" ";
                
            String[] optionsArray = Tools.stringToArray(optionsString);
            cls.setOptions(optionsArray);
            cls.buildClassifier(trainingData);
            return cls;
            
        } catch (weka.core.UnsupportedAttributeTypeException x) {
            JOptionPane.showMessageDialog(null,"Classifier require multi-valued nominal class!");
            Logger.getLogger(ClassificationTools.class.getName()).log(Level.SEVERE, null, x);
            return null;
            
        } catch (Exception ex) {
            Logger.getLogger(ClassificationTools.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
  }

  
//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
//****************************************************************************
//----------------------------------------------------------------------------
//                          Genertae Tree
//----------------------------------------------------------------------------
//****************************************************************************
 public static J48 generateTree(String optionsString, Instances dataInstances) {
        
     try {             
           if (optionsString==null || optionsString.equals(""))
               optionsString=" ";
            
            J48 cls = new J48();
            //-------------------------------------------------------------------------
            String[] optionsArray = Tools.stringToArray(optionsString);
            cls.setOptions(optionsArray);
            cls.buildClassifier(dataInstances);
            return cls;
     
            
        } catch (weka.core.UnsupportedAttributeTypeException x) {
            JOptionPane.showMessageDialog(null,"Classifier require multi-valued nominal class!");
            Logger.getLogger(ClassificationTools.class.getName()).log(Level.SEVERE, null, x);
            return null; } 
                
        catch (Exception ex) {
            Logger.getLogger(ClassificationTools.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        
        
        
  }

 
 

//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
 //****************************************************************************
//----------------------------------------------------------------------------
//                          Generate SMO
//----------------------------------------------------------------------------
//****************************************************************************
 public static  SMO generateSMO(String originalDataSet, String optionsString)
  {
        try {

            if (optionsString==null || optionsString.equals(""))
                optionsString=" ";
            // Load Data
            //-------------------------------------------------------------------------
            Instances originalData = DataSource.read(originalDataSet);
            originalData.setClassIndex(originalData.numAttributes() - 1);
            // Train classifier
            //-------------------------------------------------------------------------
            SMO smo = new SMO();
            String[] optionsArray = Tools.stringToArray(optionsString);
            smo.setOptions(optionsArray);
            smo.buildClassifier(originalData);
            // Return Results
            //-------------------------------------------------------------------------
            return smo;
            
        } catch (weka.core.UnsupportedAttributeTypeException x) {
            JOptionPane.showMessageDialog(null,"Classifier require multi-valued nominal class!");
            Logger.getLogger(ClassificationTools.class.getName()).log(Level.SEVERE, null, x);
            return null;            
            
        } catch (Exception ex) {
            Logger.getLogger(ClassificationTools.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
  }


 
 
 
 
 
 
 
//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ 
//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@  
//                             Model Evaluation
//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ 
//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@  
 
  
 
 
 
 
 
 
 
 
//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
//****************************************************************************
//----------------------------------------------------------------------------
//                          Evaluate Tree
//----------------------------------------------------------------------------
//****************************************************************************
 public static Evaluation evaluateTree(J48 cls, Instances testingData, int foldsNumber)
 {
        try {
            // train classifier
            
            Evaluation eval = new Evaluation(testingData);
            //-------------------------------------------------------------------------
            Random rand = new Random(1); // using seed = 1
            eval.crossValidateModel(cls, testingData, foldsNumber, rand);            
            return eval;
            
        } catch (weka.core.UnsupportedAttributeTypeException x) {
            JOptionPane.showMessageDialog(null,"Classifier require multi-valued nominal class!");
            Logger.getLogger(ClassificationTools.class.getName()).log(Level.SEVERE, null, x);
            return null;
            
        } catch (Exception ex) {
            Logger.getLogger(ROC.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
  }


 //@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
//****************************************************************************
//----------------------------------------------------------------------------
//                          Evaluate Tree
//----------------------------------------------------------------------------
//****************************************************************************
 public static Evaluation evaluate(Classifier cls, Instances testingData, int foldsNumber)
 {
        try {
            // train classifier
            
            Evaluation eval = new Evaluation(testingData);
            //-------------------------------------------------------------------------
            Random rand = new Random(1); // using seed = 1
            eval.crossValidateModel(cls, testingData, foldsNumber, rand);            
            return eval;
            
        } catch (weka.core.UnsupportedAttributeTypeException x) {
            JOptionPane.showMessageDialog(null,"Classifier require multi-valued nominal class!");
            Logger.getLogger(ClassificationTools.class.getName()).log(Level.SEVERE, null, x);
            return null;            
            
        } catch (Exception ex) {
            Logger.getLogger(ClassificationTools.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
  }


 
//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
//****************************************************************************
//----------------------------------------------------------------------------
//                          generate Neural Network
//----------------------------------------------------------------------------
//****************************************************************************
 public static Evaluation evaluateMLP (MLP cls,Instances testingData,int foldsNumber)
   {
        try {
            cls.setGUI(false);
            Evaluation eval = new Evaluation(testingData);
            //-------------------------------------------------------------------------
            Random rand = new Random(1); // using seed = 1
            eval.crossValidateModel(cls, testingData, foldsNumber, rand);
            return eval;
        } catch (Exception ex) {
            Logger.getLogger(ClassificationTools.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

 

//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
//****************************************************************************
//----------------------------------------------------------------------------
//                          evaluate BayesNet
//----------------------------------------------------------------------------
//****************************************************************************
 public static Evaluation evaluateBayesNet(BayesNet cls, Instances testingData,int flodsNumber)
  {
        try {
            // Evlauation
            //-------------------------------------------------------------------------
            Evaluation eval = new Evaluation(testingData);
            Random rand = new Random(1); // using seed = 1
            eval.crossValidateModel(cls, testingData, flodsNumber, rand);
            // Return Results
            //-------------------------------------------------------------------------
            //String evalString=eval.toSummaryString()+eval.toClassDetailsString()+eval.toMatrixString();
            return eval;
        } catch (Exception ex) {
            Logger.getLogger(ClassificationTools.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
  }

 
 
 
 
 
//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ 
//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
 //                             Model Visualization
//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ 
//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@  
 
  
 
 
 
 
 
 
 //@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
//****************************************************************************
//----------------------------------------------------------------------------
//                          Visualize Tree
//----------------------------------------------------------------------------
//****************************************************************************
 public static void visualizeTree(String graph, File outputFile) 
 {
        try {
            // display tree
            TreeVisualizer tv = new TreeVisualizer(null, graph, new PlaceNode2());
            JFrame jf = new JFrame("Classifier Tree Visualizer: J48");
            jf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            jf.setSize(800, 600);
            jf.getContentPane().setLayout(new BorderLayout());
            jf.getContentPane().add(tv, BorderLayout.CENTER);
                        
            jf.setVisible(true);

            // adjust tree
            tv.fitToScreen();
            
            VisualizationTools.visualizationToFile(tv, outputFile.getPath());
            
        } catch (Exception ex) {
            Logger.getLogger(ClassificationTools.class.getName()).log(Level.SEVERE, null, ex);
        }
  }
 
 
  
//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
//****************************************************************************
//----------------------------------------------------------------------------
//                          Visualize BayesNet
//----------------------------------------------------------------------------
//****************************************************************************
 public static void visualizeBayesNet(String graph, File outputFile)
  {
        try {
            // Display Graph
            //-------------------------------------------------------------------------
            GraphVisualizer gv = new GraphVisualizer();
            gv.readBIF(graph);
            JFrame jf = new JFrame("BayesNet graph");
            jf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            jf.setSize(800, 600);
            jf.getContentPane().setLayout(new BorderLayout());
            jf.getContentPane().add(gv, BorderLayout.CENTER);
            jf.setVisible(true);

            // layout graph
            //-------------------------------------------------------------------------
            gv.layoutGraph();
                        
            VisualizationTools.visualizationToFile(gv, outputFile.getPath());
            
        } catch (Exception ex) {
            Logger.getLogger(ClassificationTools.class.getName()).log(Level.SEVERE, null, ex);            
        }
  }
 
 
 
  
 //@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
//****************************************************************************
//----------------------------------------------------------------------------
//                          Visualize Tree
//----------------------------------------------------------------------------
//****************************************************************************
 public static JFrame getTreeVisualization(String graph, File outputFile) 
 {
        try {
            // display tree
            TreeVisualizer tv = new TreeVisualizer(null, graph, new PlaceNode2());               
           
            JFrame jf = new JFrame("Classifier Tree Visualizer: J48");
            jf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            jf.setSize(800, 600);
            jf.getContentPane().setLayout(new BorderLayout());
                       
            jf.getContentPane().add(tv, BorderLayout.CENTER);
                        
            jf.setVisible(true);

            // adjust tree
            tv.fitToScreen();              
            //tv.saveComponent(); // this will show the file as choser for saving the output file
            
            tv.print(tv.getGraphics());
            tv.setVisible(true);
            System.out.println(graph);
            VisualizationTools.visualizationToFile(tv,outputFile.getPath());      
            
           return jf;
        } catch (Exception ex) {
            Logger.getLogger(ClassificationTools.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
  }
  
 
 
 //@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
//****************************************************************************
//----------------------------------------------------------------------------
//                          Visualize BayesNet
//----------------------------------------------------------------------------
//****************************************************************************
 public static JFrame getBayesNetVisualization(String graph, File outputFile)
  {
        try {
            // Display Graph
            //-------------------------------------------------------------------------
            GraphVisualizer gv = new GraphVisualizer();
            gv.readBIF(graph);
            JFrame jf = new JFrame("BayesNet graph");
            jf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            jf.setSize(800, 600);
            jf.getContentPane().setLayout(new BorderLayout());
            jf.getContentPane().add(gv, BorderLayout.CENTER);
            jf.setVisible(true);

            // layout graph
            //-------------------------------------------------------------------------
            gv.layoutGraph();
            VisualizationTools.visualizationToFile(gv, outputFile.getPath());
            return jf;
        } catch (Exception ex) {
            Logger.getLogger(ClassificationTools.class.getName()).log(Level.SEVERE, null, ex);            
            return null;
        }
  }



 
//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
//****************************************************************************
//----------------------------------------------------------------------------
//                          Visualize Neural Network
//----------------------------------------------------------------------------
//****************************************************************************
 public static ModelVisualization_JFrame getMLPVisualization(String imageFilePath)
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
            Logger.getLogger(ClassificationTools.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
  }
 
 
//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
//****************************************************************************
//----------------------------------------------------------------------------
//                          Visualize Neural Network
//----------------------------------------------------------------------------
//****************************************************************************
 public static ModelVisualization_JFrame getMLPVisualization(MLP mlp)
  {
        try {        
            
            File presentationImageFile=null;
            
            if (mlp!=null && mlp.getOutputFile()!=null)
            {
                presentationImageFile= new File(mlp.getOutputFile());               
            }
                                                
           
            ModelVisualization_JFrame jf = new ModelVisualization_JFrame(presentationImageFile.getPath());
            return jf;
            // Evaluation
            //----------------------------------------------------------------            
        } catch (Exception ex) 
        {
            Logger.getLogger(ClassificationTools.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
  }
 
 
 
//****************************************************************************
//----------------------------------------------------------------------------
//                          Generate & Visualize Neural Network
//----------------------------------------------------------------------------
//****************************************************************************
 
 
 public static void generateMLPVisualizationFile(String optionsString,Instances dataSetInstances, String outputFile)
  {
        try {                        
            
            dataSetInstances.setClassIndex(dataSetInstances.numAttributes() - 1);
                        
            File tempFile=new File("temp.arff");
            Tools.writeStringToFile(dataSetInstances.toString(), tempFile.getPath());            
            
            MLP_Visualization mlp = new MLP_Visualization();
            String[] optionsArray = null;
            //
            if (optionsString == null || optionsString.equals("") || optionsString.equals(" "))
            {
                String[] mainOptionArray = new String[3];
                mainOptionArray[0] = "-t";
                mainOptionArray[1] = tempFile.toString();                
                mainOptionArray[2] = "-G";
                optionsArray = mainOptionArray;
            } 
            else 
            {
                optionsArray = Tools.stringToArray(optionsString);
                
            }
            
            mlp.runMLP(optionsArray, outputFile);
            
            if (mlp.getOutputFile()!=null && mlp.getM_nodePanel()!=null)
              {
                VisualizationTools.visualizationToFile(mlp.getM_nodePanel(), mlp.getOutputFile());
               }      
            
        } catch (Exception ex) {
            Logger.getLogger(ClassificationTools.class.getName()).log(Level.SEVERE, null, ex);
            
        }
  }

 
 
 
 
 
 
//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ 
//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@  
//                                Others
//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ 
//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@  
 
 
 
 
 
 
 
 
 
 
 //----------------------------------------------------------------------------
//                          Generate & Visualize Neural Network
//----------------------------------------------------------------------------
//****************************************************************************
 public static void generateAndVisualizeMLP(String optionsString,Instances dataSetInstances, String outputFile)
  {
        try {                        
            
            dataSetInstances.setClassIndex(dataSetInstances.numAttributes() - 1);
                        
            File tempFile=new File("temp.arff");
            Tools.writeStringToFile(dataSetInstances.toString(), tempFile.getPath());            
            
            MLP mlp = new MLP();
            String[] optionsArray = null;
            //
            if (optionsString == null || optionsString.equals("") || optionsString.equals(" "))
            {
                String[] mainOptionArray = new String[3];
                mainOptionArray[0] = "-t";
                mainOptionArray[1] = tempFile.toString();                
                mainOptionArray[2] = "-G";
                optionsArray = mainOptionArray;
            } 
            else 
            {
                optionsArray = Tools.stringToArray(optionsString);
                mlp.setOptions(optionsArray);
                mlp.setGUI(true);
                mlp.buildClassifier(dataSetInstances);                
            }
            
            mlp.runMLP(optionsArray, outputFile);            
            
            
        } catch (Exception ex) {
            Logger.getLogger(ClassificationTools.class.getName()).log(Level.SEVERE, null, ex);
            
        }
  }


 
 
 
 

//****************************************************************************
//----------------------------------------------------------------------------
//                     Generate BayesNet & Evaluation
//----------------------------------------------------------------------------
//****************************************************************************
 public static String generateAndEvaluateBayesNet(String optionsString, Instances trainingData, Instances testingData,int flodsNumber)
  {
        try {
            
            if (optionsString==null || optionsString.equals(""))
                optionsString=" ";
            
            
            // Training
            //-------------------------------------------------------------------------
            BayesNet cls = new BayesNet();
            String[] optionsArray = Tools.stringToArray(optionsString);
            cls.setOptions(optionsArray);
            cls.buildClassifier(trainingData);
            
            // Testing
            //************************************************************************
            if (testingData == null) {
                testingData = trainingData;
            }
            
            // Evlauation
            //-------------------------------------------------------------------------
            Evaluation eval = new Evaluation(testingData);
            Random rand = new Random(1); // using seed = 1
            eval.crossValidateModel(cls, testingData, flodsNumber, rand);
            // Return Results
            //-------------------------------------------------------------------------
            String evalString = eval.toSummaryString() + eval.toClassDetailsString() + eval.toMatrixString();
            return cls.toString() + evalString;
        } catch (Exception ex) {
            Logger.getLogger(ClassificationTools.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
  }

//****************************************************************************
//----------------------------------------------------------------------------
//                          Generate & Visualize BayesNet
//----------------------------------------------------------------------------
//****************************************************************************
 public static String generateAndVisualizeBayesNet(Instances trainingData, Instances testingData,int flodsNumber, String optionsString)
  {
        try {
         
            if (optionsString==null || optionsString.equals(""))
                optionsString=" ";            
            // Train classifier
            //-------------------------------------------------------------------------
            BayesNet cls = new BayesNet();
            String[] optionsArray = Tools.stringToArray(optionsString);
            cls.setOptions(optionsArray);
            cls.buildClassifier(trainingData);
            // Testing
            //************************************************************************
            if (testingData == null) {
                testingData = trainingData;
            }
            // Evlauation
            //-------------------------------------------------------------------------
            Evaluation eval = new Evaluation(testingData);
            Random rand = new Random(1); // using seed = 1
            eval.crossValidateModel(cls, testingData, flodsNumber, rand);
            // Display Graph
            //-------------------------------------------------------------------------
            GraphVisualizer gv = new GraphVisualizer();
            gv.readBIF(cls.graph());
            JFrame jf = new JFrame("BayesNet graph");
            jf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            jf.setSize(800, 600);
            jf.getContentPane().setLayout(new BorderLayout());
            jf.getContentPane().add(gv, BorderLayout.CENTER);
            jf.setVisible(true);
            // layout graph
            //-------------------------------------------------------------------------
            gv.layoutGraph();
            // Return Results
            //-------------------------------------------------------------------------
            String evalString = eval.toSummaryString() + eval.toClassDetailsString() + eval.toMatrixString();
            return cls.toString() + evalString + cls.toString();
        } catch (Exception ex) {
            Logger.getLogger(ClassificationTools.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
  }


//****************************************************************************
//----------------------------------------------------------------------------
//                      Genertae Tree & Evaluation
//----------------------------------------------------------------------------
//****************************************************************************
 public static String generateAndEvaluateTree(String optionsString, Instances trainingData, Instances testingData,int foldsNumber) {
        try {
            
            if (optionsString==null || optionsString.equals(""))
                optionsString=" ";
            
            // train classifier
            //************************************************************************
            J48 cls = new J48();
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
            Logger.getLogger(ClassificationTools.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
  }


//****************************************************************************
//----------------------------------------------------------------------------
//                       Generate & Visualize Tree
//----------------------------------------------------------------------------
//****************************************************************************
 public static String generateAndVisualizeTree(String optionsString, Instances trainingData, Instances testingData,int foldsNumber) {

     if (trainingData!=null)
     {
     try {

            if (optionsString==null || optionsString.equals(""))
                optionsString=" ";
            // train classifier
            //************************************************************************
            J48 cls = new J48();
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
            // display tree
            TreeVisualizer tv = new TreeVisualizer(null, cls.graph(), new PlaceNode2());
            JFrame jf = new JFrame("Classifier Tree Visualizer: J48");
            jf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            jf.setSize(800, 600);
            jf.getContentPane().setLayout(new BorderLayout());
            jf.getContentPane().add(tv, BorderLayout.CENTER);
            jf.setVisible(true);
            // adjust tree
            tv.fitToScreen();
            String evalString = eval.toSummaryString() + eval.toClassDetailsString() + eval.toMatrixString();
            return cls.toString() + evalString;
        } catch (Exception ex) {
            Logger.getLogger(ClassificationTools.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
     }
     else
         return null;
  }


//****************************************************************************
//----------------------------------------------------------------------------
//                       Generate & Visualize Tree
//----------------------------------------------------------------------------
//****************************************************************************
 public static String generateAndVisualizeTree(String optionsString, Instances instancesData, int foldsNumber) {
        try {

            if (optionsString==null || optionsString.equals(""))
                optionsString=" ";
            // train classifier
            //************************************************************************
            J48 cls = new J48();
            String[] optionsArray = Tools.stringToArray(optionsString);
            cls.setOptions(optionsArray);
            cls.buildClassifier(instancesData);

            //-------------------------------------------------------------------------
            Evaluation eval = new Evaluation(instancesData);
            //-------------------------------------------------------------------------
            Random rand = new Random(1); // using seed = 1
            eval.crossValidateModel(cls, instancesData, foldsNumber, rand);
            // display tree
            TreeVisualizer tv = new TreeVisualizer(null, cls.graph(), new PlaceNode2());
            JFrame jf = new JFrame("Classifier Tree Visualizer: J48");
            jf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            jf.setSize(800, 600);
            jf.getContentPane().setLayout(new BorderLayout());
            jf.getContentPane().add(tv, BorderLayout.CENTER);
            jf.setVisible(true);
            // adjust tree
            tv.fitToScreen();
            String evalString = eval.toSummaryString() + eval.toClassDetailsString() + eval.toMatrixString();
            return cls.toString() + evalString;
        } catch (Exception ex) {
            Logger.getLogger(ClassificationTools.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
  }


  
//****************************************************************************
//----------------------------------------------------------------------------
//                        Generate and Evaluate Neural Network
//----------------------------------------------------------------------------
//****************************************************************************
 public static String generateAndEvaluateMLP(String optionsString, Instances trainingDataSetInstances,Instances testingDataSetInstances,int foldsNumber, String outputFile)
   {
        try {
            
            //************************************************************************
            trainingDataSetInstances.setClassIndex(trainingDataSetInstances.numAttributes() - 1);
                        
            File tempTrainingFile=new File("tempTraininag.arff");
            Tools.writeStringToFile(trainingDataSetInstances.toString(), tempTrainingFile.getPath());            
            
            MLP mlp = new MLP();
            String[] optionsArray = null;
            //
            if (optionsString == null || optionsString.equals("") || optionsString.equals(" "))
            {
                String[] mainOptionArray = new String[2];
                mainOptionArray[0] = "-t";
                mainOptionArray[1] = tempTrainingFile.toString();                
                //mainOptionArray[2] = "-G";
                optionsArray = mainOptionArray;
            } 
            else 
            {
                optionsArray = Tools.stringToArray(optionsString);
                mlp.setOptions(optionsArray);
                mlp.setGUI(true);
                mlp.buildClassifier(trainingDataSetInstances);                
            }
            
            mlp.runMLP(optionsArray, outputFile);
                      
            //************************************************************************
            if (testingDataSetInstances == null) {
                testingDataSetInstances = trainingDataSetInstances;
            }
            //-------------------------------------------------------------------------
            Evaluation eval = new Evaluation(testingDataSetInstances);
            //-------------------------------------------------------------------------
            
            File tempTestingFile=new File("tempTesting.arff");
            Tools.writeStringToFile(trainingDataSetInstances.toString(), tempTestingFile.getPath());                        
            
            Random rand = new Random(1); // using seed = 1
            eval.crossValidateModel(mlp, testingDataSetInstances, foldsNumber, rand);
            if (optionsString == null || optionsString.equals("") || optionsString.equals(" ")) {
                String[] mainOptionArray = new String[3];
                mainOptionArray[0] = "-t";
                mainOptionArray[1] = tempTestingFile.toString();
                mainOptionArray[2] = "-G";
                optionsArray = mainOptionArray;
            }
            String evalString = eval.toSummaryString() + eval.toClassDetailsString() + eval.toMatrixString();
            return mlp.toString() + evalString;
        } catch (Exception ex) {
            Logger.getLogger(ClassificationTools.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

//****************************************************************************
//----------------------------------------------------------------------------
//                       Generate & Visualize Neural Network
//----------------------------------------------------------------------------
//****************************************************************************
 public static String generateAndVisualizeMLP(String optionsString,Instances trainingDataSetInstances,Instances testingDataSetInstances,int foldsNumber, String outputFile)
   {
        try {
            //************************************************************************
            trainingDataSetInstances.setClassIndex(trainingDataSetInstances.numAttributes() - 1);
                        
            File tempFile=new File("temp.arff");
            Tools.writeStringToFile(trainingDataSetInstances.toString(), tempFile.getPath());            
            
            MLP mlp = new MLP();
            String[] optionsArray = null;
            //
            if (optionsString == null || optionsString.equals("") || optionsString.equals(" "))
            {
                String[] mainOptionArray = new String[3];
                mainOptionArray[0] = "-t";
                mainOptionArray[1] = tempFile.toString();                
                mainOptionArray[2] = "-G";
                optionsArray = mainOptionArray;
            } 
            else 
            {
                optionsArray = Tools.stringToArray(optionsString);
                mlp.setOptions(optionsArray);
                mlp.setGUI(true);
                mlp.buildClassifier(trainingDataSetInstances);                
            }
            
            mlp.runMLP(optionsArray, outputFile);
            
            
            
            //************************************************************************
            if (testingDataSetInstances == null)
            {
                testingDataSetInstances = trainingDataSetInstances;
            }
            //-------------------------------------------------------------------------
            Evaluation eval = new Evaluation(testingDataSetInstances);
            //-------------------------------------------------------------------------
            Random rand = new Random(1); // using seed = 1
            eval.crossValidateModel(mlp, testingDataSetInstances, foldsNumber, rand);
            //mlp.main(optionsArray);            
            String evalString = eval.toSummaryString() + eval.toClassDetailsString() + eval.toMatrixString();
            return mlp.toString() + evalString + mlp.toString();
        } catch (Exception ex) {
            Logger.getLogger(ClassificationTools.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
//****************************************************************************

 //@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
//****************************************************************************
//----------------------------------------------------------------------------
//                          Generate & Visualize Neural Network
//----------------------------------------------------------------------------
//****************************************************************************
 public static String generateAndVisualizeMLP(String optionsString,String trainingDataSet,String testingDataSet,int foldsNumber ,String outputFile)
  {
        try {
            //************************************************************************
            BufferedReader readerTraining = new BufferedReader(new FileReader(trainingDataSet));
            Instances trainingData = new Instances(readerTraining);
            readerTraining.close();
            trainingData.setClassIndex(trainingData.numAttributes() - 1);
            //************************************************************************
            MLP mlp = new MLP();
            String[] optionsArray = null;
            //
            if (optionsString == null || optionsString.equals("") || optionsString.equals(" ")) {
                String[] mainOptionArray = new String[3];
                mainOptionArray[0] = "-t";
                mainOptionArray[1] = trainingData.toString();
                mainOptionArray[2] = "-G";
                optionsArray = mainOptionArray;
            } else {
                optionsArray = Tools.stringToArray(optionsString);
                mlp.setOptions(optionsArray);
            }
            mlp.runMLP(optionsArray, outputFile);
            
            // Evaluation
            //************************************************************************
            /*
            if (testingDataSet == null) {
                testingDataSet = trainingData;
            }
            */
            BufferedReader testingReader = new BufferedReader(new FileReader(testingDataSet));
            Instances testingData = new Instances(testingReader);
            testingReader.close();
            testingData.setClassIndex(testingData.numAttributes() - 1);
            //-------------------------------------------------------------------------
            Evaluation eval = new Evaluation(testingData);
            //-------------------------------------------------------------------------
            Random rand = new Random(1); // using seed = 1
            eval.crossValidateModel(mlp, testingData, foldsNumber, rand);
            return "\nxxxxxxxxxxx Crosss Validation xxxxxxxxxxx\n" + eval.toSummaryString() + eval.toClassDetailsString() + eval.toMatrixString() + "\nxxxxxxxxxxXXXXXXXXXXXXXXXXXXXXXXxxxxxxxx\n";
            //   return null;
        } catch (Exception ex) {
            Logger.getLogger(ClassificationTools.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
  }

 

 //****************************************************************************
//----------------------------------------------------------------------------
//                      Genertae SMO & Evaluation
//----------------------------------------------------------------------------
//****************************************************************************
 public static String generateAndEvaluateSMO(String optionsString, Instances trainingData, Instances testingData,int foldsNumber) {
        try {

            if (optionsString==null || optionsString.equals(""))
                optionsString=" ";
            // train classifier
            //************************************************************************
            SMO cls = new SMO();
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
            Logger.getLogger(ClassificationTools.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
  }
 
 
 
 

 
 public static void visualizeClassificationError(Classifier cls, Instances train, Instances test)
   { 
        try {
            // build classifier
            cls.buildClassifier(train);
            // evaluate classifier and generate plot instances
            ClassifierErrorsPlotInstances plotInstances = new ClassifierErrorsPlotInstances();
            plotInstances.setClassifier(cls);
            plotInstances.setInstances(train);
            plotInstances.setClassIndex(train.numAttributes()-1);
            
            
            Evaluation eval = new Evaluation(test);
            plotInstances.setEvaluation(eval);
            
            plotInstances.setUp();
            
            for (int i = 0; i < test.numInstances(); i++)
              plotInstances.process(test.instance(i), cls, eval);
            
            // generate visualization
            VisualizePanel visPanel = new VisualizePanel();
            visPanel.addPlot(plotInstances.getPlotData("Classification Error plot"));
            visPanel.setColourIndex(plotInstances.getPlotInstances().classIndex()-1);
            
            JFrame jf = new JFrame("Classifier Error: "+cls.getClass().getSimpleName());
            jf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            jf.setSize(800, 600);
            jf.getContentPane().setLayout(new BorderLayout());
            jf.getContentPane().add(visPanel, BorderLayout.CENTER);
            jf.setVisible(true);            
            
            // clean up
            plotInstances.cleanUp();
        } catch (Exception ex) {
            Logger.getLogger(ClassificationTools.class.getName()).log(Level.SEVERE, null, ex);
        }
  }
 }
        /*
        PMMLClassifier.runClassifier(cls, optionsArray);
        PMMLClassifier pmmlClassifier =null;
        TreeModel model =null; // from pmml consumer
        return pmmlClassifier;
         */
        /*
        // this did not work?
        PMMLModel model = PMMLFactory.getPMMLModel(dataFileString);
        System.out.println(model);
        if (model instanceof PMMLClassifier)
        {
        PMMLClassifier classifier = (PMMLClassifier) model;
        }
        PMMLFactory.serializePMMLModel(model, "e:/TreeModel.xml");
        System.out.println(model);
          catch (Exception ex) {
            Logger.getLogger(ClassificationTools.class.getName()).log(Level.SEVERE, null, ex);
        }
 
 }
 
 */
 /*            
            PMMLClassifier.runClassifier(cls, optionsArray);            
            PMMLClassifier pmmlClassifier =null;            
            
            TreeModel model =null; // from pmml consumer            
            return pmmlClassifier;
*/             

/*
// this did not work?
PMMLModel model = PMMLFactory.getPMMLModel(dataFileString);
System.out.println(model);

if (model instanceof PMMLClassifier) 
{
   PMMLClassifier classifier = (PMMLClassifier) model;
   
}                        
            PMMLFactory.serializePMMLModel(model, "e:/TreeModel.xml");
            System.out.println(model);
*/            
        
