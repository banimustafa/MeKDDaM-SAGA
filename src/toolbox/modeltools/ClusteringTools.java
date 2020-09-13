/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package toolbox.modeltools;
import java.util.logging.Level;
import java.util.logging.Logger;
import weka.clusterers.DensityBasedClusterer;
import weka.core.Instances;
import java.awt.BorderLayout;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import javax.swing.JFrame;
import toolbox.Tools;
import toolbox.viewtools.ClustererPanel;

import weka.clusterers.ClusterEvaluation;
import weka.clusterers.Clusterer;
import weka.gui.explorer.ClustererAssignmentsPlotInstances;
import weka.gui.visualize.VisualizePanel;
import weka.clusterers.AbstractClusterer;
import weka.clusterers.Cobweb;
import weka.clusterers.EM;
import weka.clusterers.HierarchicalClusterer;
import weka.clusterers.SimpleKMeans;
/**
 *
 * @author amb04
 */
public class ClusteringTools
{

//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
//****************************************************************************
//----------------------------------------------------------------------------
//                           Clustering
//----------------------------------------------------------------------------
//****************************************************************************
public static Clusterer generateClusterer(Clusterer cls,String optionsString,Instances trainingData )
{        
        try {            
            String[] dataSetsArray = Tools.stringToArray(optionsString);

            //-----------------------------------------------------------------
            if (cls.getClass().getSimpleName().equals("SimpleKMeans"))
            {
                SimpleKMeans simpleKMeans = (SimpleKMeans) cls; 
                simpleKMeans.setOptions(dataSetsArray);
                
                simpleKMeans.buildClusterer(trainingData);
                return simpleKMeans;
            }
            else if (cls.getClass().getSimpleName().equals("EM"))
            {
                
                EM em = (EM) cls; 
                em.setOptions(dataSetsArray);
                em.buildClusterer(trainingData);
                return em;
            }
            else if (cls.getClass().getSimpleName().equals("Cobweb"))
            {
                
                Cobweb cobweb = (Cobweb) cls; 
                cobweb.setOptions(dataSetsArray);
                cobweb.buildClusterer(trainingData);
                return cobweb;
            }
            else 
                return null;            
            
        } catch (Exception ex) {
            Logger.getLogger(ClusteringTools.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
  }


//****************************************************************************
//----------------------------------------------------------------------------
//                      HCA and Visulaization
//----------------------------------------------------------------------------
//****************************************************************************
public static HierarchicalClusterer generateHCA(String optionsString,Instances dataSet, int numberOfClusters)
{
        try {
            //************************************************************************            
            HierarchicalClusterer hca = new HierarchicalClusterer();
            
            if (optionsString==null ||optionsString.equals("") ||optionsString.equals(" "))                       
            {
                String[] dataSetsArray = new String[2];
                dataSetsArray[0]="-L";
                dataSetsArray[1]="";                 
                hca.setOptions(dataSetsArray);
            }
            else
             {
                String[] dataSetsArray = Tools.stringToArray(optionsString);             
                hca.setOptions(dataSetsArray);
            }
            hca.setNumClusters(numberOfClusters);            
            hca.setPrintNewick(true);              
            hca.buildClusterer(dataSet);            
            return hca;
            
        } catch (Exception ex) {
            Logger.getLogger(ClusteringTools.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
  }



//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
//****************************************************************************
//----------------------------------------------------------------------------
//                          Evaluate Clustering
//----------------------------------------------------------------------------
//****************************************************************************
public static ClusterEvaluation evaluateClusterer(Clusterer cls,String optionsString, Instances testingData, int foldsNumber )
{
        try {            
            //-------------------------------------------------------------------------
            // evaluate clusterer
            ClusterEvaluation eval = new ClusterEvaluation();
            
            if (cls!=null)
                eval.setClusterer(cls);
            
            if (testingData!=null)
                eval.evaluateClusterer(testingData);
            
//            Random rand = new Random(1); // using seed = 1
//            eval.crossValidateModel((DensityBasedClusterer) cls, testingData, foldsNumber, rand);
            return eval;
        } catch (Exception ex) {
            Logger.getLogger(ROC.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }

  }

//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
//****************************************************************************
//----------------------------------------------------------------------------
//                          Evaluate Clustering
//----------------------------------------------------------------------------
//****************************************************************************
public static ClusterEvaluation evaluateClusterer(Clusterer cls,Instances testingData )
{
        try {            
            // evaluate clusterer
            //-----------------------------------------------------------------
            ClusterEvaluation eval = new ClusterEvaluation();
            eval.setClusterer(cls);
            eval.evaluateClusterer(testingData);
            
            return eval;
            
        } catch (Exception ex) {
            Logger.getLogger(ROC.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }

  }



//****************************************************************************
//----------------------------------------------------------------------------
//                      HCA and Visulaization
//----------------------------------------------------------------------------
//****************************************************************************
public static ClusterEvaluation evaluateHCA(HierarchicalClusterer hca, Instances testingData, int foldsNumber )
{
        try {
            //hca.setNumClusters(3);
            ClusterEvaluation eval = new ClusterEvaluation();
            eval.setClusterer(hca);            
            eval.evaluateClusterer(testingData);                        
            
            return eval;
        } catch (Exception ex) {
            Logger.getLogger(ClusteringTools.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
  }



//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
//****************************************************************************
//----------------------------------------------------------------------------
//                          Visualize Clustering
//----------------------------------------------------------------------------
//****************************************************************************
public static void visualizeClusterer(Clusterer cls )
{
        try {
            // setup visualization
            // taken from: ClustererPanel.startClusterer()
            ClustererAssignmentsPlotInstances plotInstances = new ClustererAssignmentsPlotInstances();
            plotInstances.setClusterer(cls);
            //plotInstances.setInstances(data);
            //plotInstances.setClusterEvaluation(eval);
            plotInstances.setUp();
            String name = (new SimpleDateFormat("HH:mm:ss - ")).format(new Date());
            String cname = cls.getClass().getName();
            if (cname.startsWith("weka.clusterers."))
              name += cname.substring("weka.clusterers.".length());
            else
              name += cname;
            //name = name + " (" + data.relationName() + ")";
            VisualizePanel vp = new VisualizePanel();
            vp.setName(name);
            vp.addPlot(plotInstances.getPlotData(cname));

            // display data
            // taken from: ClustererPanel.visualizeClusterAssignments(VisualizePanel)
            JFrame jf = new JFrame("Clusterer Visualize: " + vp.getName());
            jf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            jf.setSize(500, 400);
            jf.getContentPane().setLayout(new BorderLayout());
            jf.getContentPane().add(vp, BorderLayout.CENTER);
            jf.setVisible(true);
        } catch (Exception ex) {
            Logger.getLogger(ClusteringTools.class.getName()).log(Level.SEVERE, null, ex);
        }
  }



//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
//****************************************************************************
//----------------------------------------------------------------------------
//                          Visualize Clustering
//----------------------------------------------------------------------------
//****************************************************************************
public static void visualizeClusterer(Clusterer cls, ClusterEvaluation eval, Instances data) throws Exception
{
    //************************************************************************
    
    // taken from: ClustererPanel.startClusterer()
    ClustererAssignmentsPlotInstances plotInstances = new ClustererAssignmentsPlotInstances();
    plotInstances.setClusterer(cls);
    plotInstances.setInstances(data);
    plotInstances.setClusterEvaluation(eval);
    plotInstances.setUp();
    String name = (new SimpleDateFormat("HH:mm:ss - ")).format(new Date());
    String cname = cls.getClass().getName();
    if (cname.startsWith("weka.clusterers."))
      name += cname.substring("weka.clusterers.".length());
    else
      name += cname;
    name = name + " (" + data.relationName() + ")";
    VisualizePanel vp = new VisualizePanel();
    vp.setName(name);
    vp.addPlot(plotInstances.getPlotData(cname));

    // display data
    // taken from: ClustererPanel.visualizeClusterAssignments(VisualizePanel)
    JFrame jf = new JFrame("Weka Clusterer Visualize: " + vp.getName());
    jf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    jf.setSize(500, 400);
    jf.getContentPane().setLayout(new BorderLayout());
    jf.getContentPane().add(vp, BorderLayout.CENTER);
    jf.setVisible(true);

  }

























//****************************************************************************
//----------------------------------------------------------------------------
//                        Clustering & Evaluate
//----------------------------------------------------------------------------
//****************************************************************************
public static String generateAndEvaluateClusterer(String clustererName,String optionsString,Instances trainingData, Instances testingData, int foldsNumber )
{
        try {
            //************************************************************************
            // load trainingData
            //classname="weka.clusterers.SimpleKMeans";
            String[] dataSetsArray = Tools.stringToArray(optionsString);
            //-------------------------------------------------------------------------
            Clusterer cls = AbstractClusterer.forName(clustererName, dataSetsArray);
            cls.buildClusterer(trainingData);
            //************************************************************************
            if (testingData == null) {
                testingData = trainingData;
            }
            //-------------------------------------------------------------------------
            // evaluate clusterer
            ClusterEvaluation eval = new ClusterEvaluation();
            eval.setClusterer(cls);
            eval.evaluateClusterer(trainingData);
            Random rand = new Random(1); // using seed = 1
            eval.crossValidateModel((DensityBasedClusterer) cls, testingData, foldsNumber, rand);
            return cls.toString() + eval.toString();
        } catch (Exception ex) {
            Logger.getLogger(ClusteringTools.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }

  }




//****************************************************************************
//----------------------------------------------------------------------------
//                      HCA and Visulaization
//----------------------------------------------------------------------------
//****************************************************************************
public static Object[] generateAndEvaluateHCA(String optionsString,Instances dataSet, int numberOfClusters)
{
        try {
            //************************************************************************            
            HierarchicalClusterer hca = new HierarchicalClusterer();
            
            if (optionsString==null ||optionsString.equals("") ||optionsString.equals(" "))                       
            {
                String[] dataSetsArray = new String[2];
                dataSetsArray[0]="-L";
                dataSetsArray[1]="";                 
                hca.setOptions(dataSetsArray);
            }
            else
             {
                String[] dataSetsArray = Tools.stringToArray(optionsString);             
                hca.setOptions(dataSetsArray);
            }
            hca.setNumClusters(numberOfClusters);            
            hca.setPrintNewick(true);              
            hca.buildClusterer(dataSet);  
            //
            ClusterEvaluation eval = new ClusterEvaluation();
            eval.setClusterer(hca);
            eval.evaluateClusterer(dataSet);                        

            Object[] result= new Object[2];
            result[0]=hca;
            result[1]=eval;
            return result;
            
        } catch (Exception ex) {
            Logger.getLogger(ClusteringTools.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
  }





//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
//****************************************************************************
//----------------------------------------------------------------------------
//                          Visualize Clustering
//----------------------------------------------------------------------------
//****************************************************************************
public static JFrame getClustererVisualization( Clusterer cls, Instances data )
{
        try {
            // setup visualization
            // taken from: ClustererPanel.startClusterer()
            
            data.setClassIndex(data.numAttributes()-1);
            
            //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++            
            ClusterEvaluation eval=null;
            if (data!=null)
                eval=ClusteringTools.evaluateClusterer(cls, "", data, 10);
                                      
            ClustererAssignmentsPlotInstances plotInstances = new ClustererAssignmentsPlotInstances();
            
            plotInstances.setClusterer(cls);
            plotInstances.setInstances(data);  
            
            if (eval!=null)
                plotInstances.setClusterEvaluation(eval);            
            
            plotInstances.setUp();      
            
            //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++            
            String name = (new SimpleDateFormat("HH:mm:ss - ")).format(new Date());
            String cname = cls.getClass().getName();
            if (cname.startsWith("weka.clusterers."))
              name += cname.substring("weka.clusterers.".length());
            else
              name += cname;            
            name = name + " (" + data.relationName() + ")";
            
            //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
            VisualizePanel vp = new VisualizePanel();                                    
            vp.setName(name);            
            vp.addPlot(plotInstances.getPlotData(cname));                         
            
            
            ClustererPanel cp= new ClustererPanel();
            JFrame jf =cp.visualizeClusterAssignments(vp);
            
            if (cls.getClass().getSimpleName().equals("HierarchicalClusterer"))
            {
                HierarchicalClusterer hca= (HierarchicalClusterer) cls;                
                cp.visualizeTree(hca.graph(), " tree visualization");
            }
            // display data
            // taken from: ClustererPanel.visualizeClusterAssignments(VisualizePanel)
            //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
            /*
            JFrame jf = new JFrame("Clusterer Visualize: " + vp.getName());
            jf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            jf.setSize(500, 400);
            jf.getContentPane().setLayout(new BorderLayout());
            jf.getContentPane().add(vp, BorderLayout.CENTER);
            jf.setVisible(true);
            */
            return jf;
        } catch (Exception ex) {
            Logger.getLogger(ClusteringTools.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }

  }




//****************************************************************************
//----------------------------------------------------------------------------
//                      Clustering and Visulaization
//----------------------------------------------------------------------------
//****************************************************************************
public static String generateAndVisualizeClusterer(String clustererName,String optionsString,Instances trainingData, Instances testingData, int foldsNumber )
{
        try {
            //************************************************************************
            // load trainingData
            //classname="weka.clusterers.SimpleKMeans";
            String[] dataSetsArray = Tools.stringToArray(optionsString);
            //-------------------------------------------------------------------------
            Clusterer cls = AbstractClusterer.forName(clustererName, dataSetsArray);
            cls.buildClusterer(trainingData);
            //************************************************************************
            if (testingData == null) {
                testingData = trainingData;
            }
            testingData.setClassIndex(testingData.numAttributes() - 1);
            //-------------------------------------------------------------------------
            // evaluate clusterer
            ClusterEvaluation eval = new ClusterEvaluation();
            eval.setClusterer(cls);
            eval.evaluateClusterer(trainingData);
            Random rand = new Random(1); // using seed = 1
            eval.crossValidateModel((DensityBasedClusterer) cls, testingData, foldsNumber, rand);
            // setup visualization
            // taken from: ClustererPanel.startClusterer()
            ClustererAssignmentsPlotInstances plotInstances = new ClustererAssignmentsPlotInstances();
            plotInstances.setClusterer(cls);
            plotInstances.setInstances(trainingData);
            plotInstances.setClusterEvaluation(eval);
            plotInstances.setUp();
            String name = (new SimpleDateFormat("HH:mm:ss - ")).format(new Date());
            String cname = cls.getClass().getName();
            if (cname.startsWith("weka.clusterers.")) {
                name += cname.substring("weka.clusterers.".length());
            } else {
                name += cname;
            }
            name = name + " (" + trainingData.relationName() + ")";
            VisualizePanel vp = new VisualizePanel();
            vp.setName(name);
            vp.addPlot(plotInstances.getPlotData(cname));
            // display trainingData
            // taken from: ClustererPanel.visualizeClusterAssignments(VisualizePanel)
            JFrame jf = new JFrame("Clusterer Visualize: " + vp.getName());
            jf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            jf.setSize(500, 400);
            jf.getContentPane().setLayout(new BorderLayout());
            jf.getContentPane().add(vp, BorderLayout.CENTER);
            jf.setVisible(true);
            return cls.toString() + eval.toString();
        } catch (Exception ex) {
            Logger.getLogger(ClusteringTools.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
  }



//****************************************************************************
//----------------------------------------------------------------------------
//                      HCA and Visulaization
//----------------------------------------------------------------------------
//****************************************************************************
public static String generateAndEvaluateHCA(String optionsString,Instances trainingData, Instances testingData, int foldsNumber )
{
        try {
            //************************************************************************
            // load trainingData
            //String classname="weka.clusterers.SimpleKMeans";
            //String classname="weka.clusterers.HierarchicalClusterer";
            HierarchicalClusterer hca = new HierarchicalClusterer();
            
            if (optionsString==null ||optionsString.equals("") ||optionsString.equals(" "))                       
            {
                String[] dataSetsArray = new String[2];
                dataSetsArray[0]="-L";
                dataSetsArray[1]="";                 
                hca.setOptions(dataSetsArray);
            }
            else
             {
                String[] dataSetsArray = Tools.stringToArray(optionsString);             
                hca.setOptions(dataSetsArray);
            }
            
            hca.buildClusterer(trainingData);            
            //************************************************************************
            if (testingData == null) {
                testingData = trainingData;
            }
            //-------------------------------------------------------------------------
            // evaluate clusterer
            ClusterEvaluation eval = new ClusterEvaluation();
            eval.setClusterer(hca);
            eval.evaluateClusterer(trainingData);
            Random rand = new Random(1); // using seed = 1
            
            return hca.graph().substring(hca.graph().indexOf("("));//+ eval.toString();
            
        } catch (Exception ex) {
            Logger.getLogger(ClusteringTools.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
  }


//****************************************************************************
//----------------------------------------------------------------------------
//                      HCA and Visulaization
//----------------------------------------------------------------------------
//****************************************************************************
public static String generateAndVisualizeHCA(String optionsString,Instances trainingData, Instances testingData, int foldsNumber )
{
        try {
            //************************************************************************
            // load trainingData
            //String classname="weka.clusterers.SimpleKMeans";
            //String classname="weka.clusterers.HierarchicalClusterer";
            HierarchicalClusterer hca = new HierarchicalClusterer();
            
            if (optionsString==null ||optionsString.equals("") ||optionsString.equals(" "))                       
            {
                String[] dataSetsArray = new String[2];
                dataSetsArray[0]="-L";
                dataSetsArray[1]="";                 
                hca.setOptions(dataSetsArray);
            }
            else
             {
                String[] dataSetsArray = Tools.stringToArray(optionsString);             
                hca.setOptions(dataSetsArray);
            }
            
            hca.buildClusterer(trainingData);            
            //************************************************************************
            if (testingData == null) {
                testingData = trainingData;
            }
            //-------------------------------------------------------------------------
            // evaluate clusterer
            ClusterEvaluation eval = new ClusterEvaluation();
            eval.setClusterer(hca);
            eval.evaluateClusterer(trainingData);
            Random rand = new Random(1); // using seed = 1
            
            return hca.graph().substring(hca.graph().indexOf("("));//+ eval.toString();
            
        } catch (Exception ex) {
            Logger.getLogger(ClusteringTools.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
  }



/*
//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
//****************************************************************************
//----------------------------------------------------------------------------
//                          Gnerate Incremental Clusterer
//----------------------------------------------------------------------------
//****************************************************************************
public static Cobweb generateIncrementalClusterer(String optionsString, String trainingData)
{
        try {
            //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
            // load trainingData
            ArffLoader loader = new ArffLoader();
            loader.setFile(new File(trainingData));
            Instances instances = loader.getStructure();
            // train Cobweb
            //----------------------------------------------------------------------------    #
            Cobweb cls = new Cobweb();
            cls.buildClusterer(instances);
            Instance current;            
            while ((current = loader.getNextInstance(instances)) != null) {
                cls.updateClusterer(current);
            }
            cls.updateFinished();
            // build associator
            //-------------------------------------------------------------------------
            String[] optionsArray = Tools.stringToArray(optionsString);
            cls.setOptions(optionsArray);
            //-------------------------------------------------------------------------
            return cls;
        } catch (Exception ex) {
            Logger.getLogger(ClusteringTools.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
  }



//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
//****************************************************************************
//----------------------------------------------------------------------------
//                          Gnerate Incremental Clusterer
//----------------------------------------------------------------------------
//****************************************************************************
public static Cobweb generateIncrementalClusterer(String optionsString, Instances instances)
{
        try {
            
            // train Cobweb
            //----------------------------------------------------------------------------    #
            Cobweb cls = new Cobweb();
            cls.buildClusterer(instances);
            Instance currentInstance;
            int i=0;
            //-----------------------------------------------------------------------------
            while (i<instances.numInstances()) 
            {
                currentInstance= instances.get(i);
                cls.updateClusterer(currentInstance);
            }
            
            cls.updateFinished();
            // build associator
            //-------------------------------------------------------------------------
            String[] optionsArray = Tools.stringToArray(optionsString);
            cls.setOptions(optionsArray);                        
            //-------------------------------------------------------------------------
            return cls;
        } catch (Exception ex) {
            Logger.getLogger(ClusteringTools.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
  }


//****************************************************************************
//----------------------------------------------------------------------------
//                          Gnerate Incremental Clusterer
//----------------------------------------------------------------------------
//****************************************************************************
public static String generateAndEvaluateIncrementalClusterer(String trainingDataSet, Instances testingData, int foldsNumber, String optionsString)
{
        try {
            //************************************************************************

            // load trainingData
            ArffLoader loader = new ArffLoader();
            loader.setFile(new File(trainingDataSet));
            Instances structure = loader.getStructure();

            // train Cobweb
            //----------------------------------------------------------------------------
            Cobweb cls = new Cobweb();
            cls.buildClusterer(structure);
            Instance current;
            while ((current = loader.getNextInstance(structure)) != null) {
                cls.updateClusterer(current);
            }
            cls.updateFinished();
            // build associator
            //-------------------------------------------------------------------------
            String[] optionsArray = Tools.stringToArray(optionsString);
            cls.setOptions(optionsArray);
            //-------------------------------------------------------------------------
            //************************************************************************
            //
     
//            if (testingDataSet == null) {
//                testingDataSet = trainingDataSet;
//            }
  

            //-------------------------------------------------------------------------
            Evaluation eval = new Evaluation(testingData);
            //-------------------------------------------------------------------------
            Random rand = new Random(1); // using seed = 1
            eval.crossValidateModel(cls.getClass().getSimpleName().toString(), testingData, foldsNumber, null, rand);
            return cls.toString() + eval.toString();
        } catch (Exception ex) {
            Logger.getLogger(ClusteringTools.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
  }


    
//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
//****************************************************************************
//----------------------------------------------------------------------------
//                          Gnerate Incremental Clusterer
//----------------------------------------------------------------------------
//****************************************************************************
public static Evaluation evaluateIncrementalClusterer(Cobweb cls, Instances testingData, int foldsNumber)
{
        try {

            Evaluation eval = new Evaluation(testingData);
            Random rand = new Random(1); // using seed = 1
            eval.crossValidateModel(cls.getClass().getSimpleName().toString(), testingData, foldsNumber, null, rand);
            return eval;
        } catch (Exception ex) {
            Logger.getLogger(ROC.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
  }
    
*/
    

}
