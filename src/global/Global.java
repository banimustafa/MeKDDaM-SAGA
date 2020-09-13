/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package global;
import java.io.File;
import java.util.ArrayList;
import project.Project;
import process_model.phase.Phase;
import process_model.process.Process;
import weka.core.Instances;

/**
 *
 * @author amb04
 */
public class Global {

//project
public static Project project=null;
public static Phase currentPhase=new Phase();
public static Process currentProcess=null;

//----------------------------------------------------
public static Object[] clipboard=null;
public static ArrayList<Object> clipboardArrayList=null;
//----------------------------------------------------
//public static Instances currentDataInstances=null;

//----------------------------------------------------
public static File activeDataSetFile=null;
public static File activeMetaDataFile=null;
public static Object[] activeModel=null;

//----------------------------------------------------
public static boolean isMetaData=false;
public static boolean isAcclimatization=false;
public static boolean isViewOnly=false;
public static File RPlotFile=null;

public static String techniques[]={"",
    "Association Rules",                    //1    
    "BayesNet",                             //2
    "Decsion Trees",                        //3
    "Hierarchical Cluster Analysis",        //4
    "Multiple Linear Regression",           //5 called linar regresssion in weka but it implement MLR
    "Multiple-Layer Neural Networks",       //6     
    "Clustering",                           //7
    
    "Principle component Analysis",         //8
    "Random Forest",                        //9
    "Support Vector Machine",               //10 implemented using SMOReg
    
    "OTHER"};                               //11
    //       

}
