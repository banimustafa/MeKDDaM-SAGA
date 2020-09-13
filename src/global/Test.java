/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package global;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.net.URL;
import toolbox.Tools;
import toolbox.filetools.FileTools;
import toolbox.datatools.FilteringTools;
import toolbox.datatools.SplittingTools;
import toolbox.modeltools.AssociationTools;
import toolbox.modeltools.ClassificationTools;
import toolbox.modeltools.ClusteringTools;
import toolbox.modeltools.FeatureAnalysisTools;
import toolbox.modeltools.ROC;
import toolbox.modeltools.RegressionTools;
import toolbox.modeltools.algorithms.MLP;
import toolbox.modeltools.rcaller.R;
import toolbox.viewtools.ImageModelVisualization;
import toolbox.viewtools.VisualizationTools;
import weka.associations.Apriori;
import weka.associations.AssociatorEvaluation;
import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.classifiers.functions.LinearRegression;
import weka.classifiers.functions.MultilayerPerceptron;
import weka.classifiers.pmml.consumer.PMMLClassifier;
import weka.classifiers.trees.J48;
import weka.classifiers.trees.RandomForest;
import weka.clusterers.ClusterEvaluation;
import weka.clusterers.Clusterer;
import weka.clusterers.Cobweb;
import weka.clusterers.HierarchicalClusterer;
import weka.core.Instances;
import weka.core.pmml.PMMLFactory;
import weka.core.pmml.PMMLModel;

/**
 *
 * @author amb04
 */
public class Test{

public static void main(String args[]) throws Exception
{


URL outcomeURL= new URL("file:/E:/PhD/PhD/PhDThesis/PhDApplication/HiMet9IP/HiMet9IP_5.0/Process/Iteration 1/Phases/2- Data Pre-Processing/Iteration 1/Delivery/it/you.me");
URL currentProjectURL= new URL("file:/E:/abm");

System.out.println(Tools.toFile(outcomeURL).getName());
System.out.println(Tools.getFileNameOnly(Tools.toFile(outcomeURL)));


//        URL currentProjectURL=Tools.toURL(new File(Global.project.getLocation()));
        String currentProjectURLString=currentProjectURL.toString();
        String outcomeProjectURLString=outcomeURL.toString().substring(0, outcomeURL.toString().indexOf("/Process/"));
        if (outcomeURL.toString().contains("Delivery") && !outcomeProjectURLString.equals(currentProjectURLString))
        {            
      //     try {
                outcomeURL=new URL(outcomeURL.toString().replaceFirst(outcomeProjectURLString,currentProjectURLString));
//            } catch (MalformedURLException ex) {
  //              Logger.getLogger(Delivery.class.getName()).log(Level.SEVERE, null, ex);
    //        }            
//            return outcomeURL;
        }
//        else
//            return outcomeURL;





System.out.println(outcomeURL.toString());
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
/*
String trainingDataFileString="E:/CowDiet_Coded.csv";
String testingDataFileString="E:/irisTest.csv";

Instances trainingInstances=FileTools.LoadData(trainingDataFileString);
Instances testingInstances=FileTools.LoadData(testingDataFileString);

RandomForest rf= FeatureAnalysisTools.generateRandomForest(trainingInstances,"");
System.out.println(rf.toString());
System.out.println(rf.getMaxDepth());
System.out.println(rf.getNumTrees());
System.out.println(rf.getTechnicalInformation());
*/
    
    
/*    
String command="plot.ts(x)";  
String command1="heisenberg <- read.csv(file=\"simple.csv\",head=TRUE,sep=\",\")";
String command2="summary(heisenberg)";

File outFile= new File("e:/try.png");

R r= new R(command, outFile);

R r2= new R();
r2.runR(command1, command2);

*/
/*
File file=rtry.runR();

Tools.copyFile(file, outFile);
        
ImageModelVisualization jf= new ImageModelVisualization(outFile);
jf.setVisible(true);           
*/

        
        
        
        
/*
J48 generateTree = ClassificationTools.generateTree("", testingInstances);
ClassificationTools.visualizeClassificationError(generateTree, trainingInstances, testingInstances);
*/


/*

LinearRegression lr=RegressionTools.generateRegression(trainingInstances, "");
Evaluation evalLR=RegressionTools.evaluateLinearRegression(lr, testingInstances, 10);

String regEvalString=evalLR.toSummaryString();
String correlationCoefficient=regEvalString.substring(regEvalString.indexOf("Correlation coefficient")+"Correlation coefficient".length(), regEvalString.indexOf("Mean absolute error")-1).trim();
String meanAbsoluteError=regEvalString.substring(regEvalString.indexOf("Mean absolute error")+"Mean absolute error".length(), regEvalString.indexOf("Root mean squared error")-1).trim();
String rootMeanSquaredError=regEvalString.substring(regEvalString.indexOf("Root mean squared error")+"Root mean squared error".length(), regEvalString.indexOf("Relative absolute error")-1).trim();
String relativeAbsoluteError=regEvalString.substring(regEvalString.indexOf("Relative absolute error")+"Relative absolute error".length(), regEvalString.indexOf("Root relative squared error")-1).trim();
String rootRelativeSquaredError=regEvalString.substring(regEvalString.indexOf("Root relative squared error")+"Root relative squared error".length(), regEvalString.indexOf("Total Number of Instances")-1).trim();

System.out.println("\n Model |------>\n"+lr+"<--------|Model\n");    
*/




/*
Apriori associationRules = AssociationTools.generateAssociationRules(trainingInstances, "");
AssociatorEvaluation evalAss=AssociationTools.evaluateAssociationRules(associationRules, testingInstances, 10);
*/

/*
Object[] hcaResult=new Object[2];

hcaResult=ClusteringTools.generateAndEvaluateHCA("", trainingInstances,3);

HierarchicalClusterer hca= (HierarchicalClusterer) hcaResult[0];
ClusterEvaluation evalHCA=(ClusterEvaluation)hcaResult[1];
//
ClusterEvaluation eval=ClusteringTools.evaluateHCA(hca,testingInstances, 10);



String evalClusteringString=eval.clusterResultsToString();
String clusteredInstances=evalClusteringString.substring(evalClusteringString.indexOf("Clustered Instances")+"Clustered Instances :".length(), evalClusteringString.indexOf("Classes to Clusters")-1).trim();
String classesToClusteres=evalClusteringString.substring(evalClusteringString.indexOf("Classes to Clusters")+"Classes to Clusters :".length(), evalClusteringString.indexOf("Incorrectly clustered instances")-1);
String incorrectlyClusteredInstances=evalClusteringString.substring(evalClusteringString.indexOf("Incorrectly clustered instances :")+"Incorrectly clustered instances :".length(), evalClusteringString.indexOf(" %")+2).trim(); 
String incorrectlyClusteredInstancesCount=incorrectlyClusteredInstances.substring(0, incorrectlyClusteredInstances.indexOf("\t")).trim();
String incorrectlyClusteredInstancesPercentage=incorrectlyClusteredInstances.substring(incorrectlyClusteredInstances.indexOf("\t"),incorrectlyClusteredInstances.indexOf("%")).trim();

System.out.println("\n Model |------>\n"+hca+evalHCA.clusterResultsToString()+"<--------|Model\n");      
System.out.println(" ===> \n"+clusteredInstances);
System.out.println(" +++> \n"+classesToClusteres);
System.out.println(" ***> \n"+incorrectlyClusteredInstances);
System.out.println("--->"+incorrectlyClusteredInstancesCount+"<----");
System.out.println("--->"+incorrectlyClusteredInstancesPercentage+"<----");

*/
//System.out.println("\n Eval Matrix |------>\n"+evalLR.toMatrixString()+"<--------|Eval\n");
//System.out.println("\n Eval Class Details|------>\n"+evalLR.toClassDetailsString()+"<--------|Eval\n");
//System.out.println("\n Eval Cumulative Margin Distribution|------>\n"+evalLR.toCumulativeMarginDistributionString()+"<--------|Eval\n");


//MLP mlp=ClassificationTools.generateMLPVisualizationFile("", trainingInstances);
//Evaluation eval=ClassificationTools.evaluateMLP(mlp, trainingInstances, 10);
//ClassificationTools.generateMLPVisualizationFile("", trainingInstances,"e:/mlp.jpg"); 
/*
if (modelDescription!=null)
    System.out.println("------>"+modelDescription);
*/







//Cobweb generateIncrementalClusterer = ClusteringTools.generateIncrementalClusterer("", instances);


/*
URL dataURL=new File(dataFileString).toURI().toURL();

System.out.println("URL= "+dataURL.toString());
System.out.println("URL getPath Result= "+dataURL.getPath());

Instances instances=new Instances(new FileReader(dataFileString));

//ModellingTools.generateAndVisualizeMLP("", instances, "e:/mlp.jpg");
        
VisualizationTools.visualizeMLP("", dataFile.getPath(), "e:/mlp.jpg");
*/






/*
// this did not work?
PMMLModel model = PMMLFactory.getPMMLModel(dataFileString);
System.out.println(model);

if (model instanceof PMMLClassifier) 
{
   PMMLClassifier classifier = (PMMLClassifier) model;
   
}
*/
   // Since PMMLClassifier is a subclass of weka.classifiers.Classifier,
   // you can use it just like any other Weka Classifier. The only
   // exception is that calling buildClassifier() will raise an
   // Exception because PMML models are pre-built.

//String outFile="E:/iris.xrff";
//File converteFile1 = FileTools.convertFile(inFile, outFile);
//File converteFile2 = FileTools.convertFile(inFile);

//Instances instances=new Instances(new FileReader(inFile));
//VisualizationTools.visualizeAttribute(instances, 1);
//VisualizationTools.visualizeAllAttributes(new Instances(new FileReader(Global.activeDataSetFile.getPath())));


/*
String dataFile="E:/iris.arff";
System.out.println(Tools.fileToString(dataFile));
VisualizationTools.visualizeData(FileTools.LoadData(new File(dataFile).getPath()));
*/



/*
String CSVDataFile="E:/PhD/PhD/PhDThesis/PhDDataAnalysis/Weka/Weka Developer/developer-branch/data/Human_Kidney_NMR_Spectra_Bins_Data.csv";
//String CSVDataFile="E:/PhD/PhD/PhDThesis/PhDDataAnalysis/Weka/Weka Developer/developer-branch/data/iris.csv";
//String dataFile="E:/PhD/PhD/PhDThesis/PhDDataAnalysis/Weka/Weka Developer/developer-branch/data/iris.arff";

String dataFile="e:/data.arff";
FileTools.CSVToARFF(CSVDataFile, dataFile);
//Tools.writeStringToFile(FileTools.loadData(CSVDataFile),dataFile);



//String[] resultArray=SplittingTools.intelligentSplit(new File(dataFile)); //String[] resultArray=SplittingTools.intelligentSplit(dataFile);

FilteringTools.reduceDimensionality("e:/data.arff");
String[] resultArray=SplittingTools.randomizedSplit(dataFile, 80.0);


String trainingFile="e:/training1.arff";
String testingFile="e:/testing1.arff";

Tools.writeStringToFile(resultArray[0],trainingFile);
Tools.writeStringToFile(resultArray[1],testingFile);

FileTools.ARFFToCSV(trainingFile, "e:/training1.csv");
FileTools.ARFFToCSV(testingFile, "e:/testing1.csv");


FileTools.viewData(dataFile);
//
FileTools.viewData(trainingFile);
FileTools.viewData(testingFile);

*/



/*
 *
// Randomize
Tools.writeStringToFile(FilteringTools.randomize(dataFile, null),"e:/randomize.arff");
FileTools.viewData("e:/randomize.arff");
 *
*/






//System.out.println(ModellingTools.generateAndVisualizeTree(null,trainingFile,testingFile,10));




/*
VisualizationTools.visualizeMLP(null,dataFileString,"e:/MLP_Model.jpg");
System.out.println(VisualizationTools.visualizeMLP(null,dataFileString,dataFileString,10,"e:/MLP_Mode.png"));
*/




//----------------------------------------------------------------------------
//ModellingTools.viewData(fileString);
//ModellingTools.visualizeData(fileString);
//ModellingTools.GenerateAndVisualizedROC_ForeClassifierAndData((Classifier) new J48(), fileString);
/*
String filteredData=FilteringTools.pca(dataFile, null);
//String filteredData=FilteringTools.dataDiscretize(dataFile, null);


Tools.writeStringToFile(filteredData,filteredDataFile);

VisualizationTools.visualizeData(dataFile);
VisualizationTools.visualizeData(filteredDataFile);
*/






//MultilayerPerceptron mlp=ModellingTools.generateMLPVisualizationFile(" ", dataFile);


/*

    // Build the Model
    //-------------------------------------------------------------------------
    MultilayerPerceptron cls= new MultilayerPerceptron();
    String optionsString=" ";
    String[] optionsArray=Tools.stringToArray(optionsString);
    cls.setOptions(optionsArray);
    cls.buildClassifier(instances);
*/
    
//ModellingTools.generateAndVisualizeMLP(null,dataFile.getPath(), dataFile.getPath(), 10);

    

//ModellingTools.generateAndVisualizeMLP("",instances,"e:/try.jpg");// working butr with now graph


    
    
//ModellingTools.generateAndVisualizeMLP("",instances, instances,10);    
//VisualizationTools.visualizeMLP(cls);
// Calling the ROC Visualizer
    //-------------------------------------------------------------------------
//ModellingTools.GenerateAndVisualizedROC_ForeClassifierAndData(cls, fileString);



   
//*************************************************************************
//-------------------------------------------------------------------------


/*
 //-------------------------------------------------------------------------
ModellingTools.generateAndVisualizeMLP(null,dataFile,null,10);

System.out.println(ModellingTools.GenerateAndVisualizeBayesNet(fileString));
System.out.println(ModellingTools.GenerateAndVisualizeTreeForData(fileString));
ModellingTools.GenerateAndVisualizedROC_ForeClassifierAndData(, fileString);

*/


/*
File xmlFile=new File("E:/try/ProcessObjective.xml");
File xslFile=new File("E:/try/ProcessObjective.xsl");
File pdfFile=new File("E:/try/ProcessObjective.pdf");
 * 
 */
//File rtfFile=new File("E:/try/ProcessObjective.rtf");

//Tools.host("E:/try/fop-0.95bin/fop.bat "+" -xml "+ xmlFile +" -xsl "+xslFile+" -pdf "+ pdfFile);

//XMLTools.xmlToPDF(xmlFile, xslFile, pdfFile);
//XMLTools.xmlToRTF(xmlFile, xslFile, rtfFile);
/*
URL url=new URL("file:/c:/hi");
System.out.println(url.getFile().substring(1));
 *
 */
/*
String try2=System.getProperty("user.dir");
JOptionPane.showMessageDialog(null,"using getProperty for user.dir => "+try2 );
 *
 */
//JOptionPane.showMessageDialog(null,"using Confog.absuluteLocation => "+Config.absuluteLocation );

}
/*
try {
    OutputStream pdf = new FileOutputStream(new File(pdfFile.getPath()));
    Document foDoc=xml2FO(new Document(xmlFile.getPath()));
    pdf.write(fo2PDF(foDoc, xslFile.getPath()));
} catch (java.io.FileNotFoundException e) {
    e.printStackTrace();
    //System.out.print("Error creating PDF: " + pdfFile);
} catch (java.io.IOException e) {
    e.printStackTrace();
    //System.out.print("Error writing PDF: " + pdfFile);
}



 }
*/
/*
private static Document xml2FO(Document xml) throws Exception {
    DOMSource xmlDomSource = new DOMSource(xml);
    DOMResult domResult = new DOMResult();

    TransformerFactory factory = TransformerFactory.newInstance();
    Transformer transformer = factory.newTransformer();

    if (transformer == null) {
       //System.out.print("Error creating transformer");
       System.exit(1);
    }

    try {
        transformer.transform(xmlDomSource, domResult);
    } catch (javax.xml.transform.TransformerException e) {
        return null;
    }
    return (Document) domResult.getNode();
}

*/

/*
 *  Apply FOP to XSL-FO input
 *
 *  @param foDocument  The XSL-FO input
 *
 *  @return byte[]  PDF result
 */
/*
private static byte[] fo2PDF(Document foDocument, String styleSheet) {
    FopFactory fopFactory = FopFactory.newInstance();

    try {
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        org.apache.fop.apps.Fop fop = fopFactory.newFop(MimeConstants.MIME_PDF, out);
        Transformer transformer = getTransformer(styleSheet);

        Source src = new DOMSource(foDocument);
        Result res = new SAXResult(fop.getDefaultHandler());

        transformer.transform(src, res);

        return out.toByteArray();

    } catch (Exception ex) {
        return null;
    }
 }
*/

/*
 *  Create and return a Transformer for the specified stylesheet.
 *
 *  Based on the DOM2DOM.java example in the Xalan distribution.
 */
/*
private static Transformer getTransformer(String styleSheet) {
    try {
        TransformerFactory tFactory = TransformerFactory.newInstance();

        DocumentBuilderFactory dFactory = DocumentBuilderFactory.newInstance();
        dFactory.setNamespaceAware(true);

        DocumentBuilder dBuilder = dFactory.newDocumentBuilder();
        Document xslDoc = dBuilder.parse(styleSheet);
        DOMSource xslDomSource = new DOMSource(xslDoc);

        return tFactory.newTransformer(xslDomSource);
    } catch (javax.xml.transform.TransformerException e) {
        e.printStackTrace();
        return null;
    } catch (java.io.IOException e) {
        e.printStackTrace();
        return null;
    } catch (javax.xml.parsers.ParserConfigurationException e) {
        e.printStackTrace();
        return null;
    } catch (org.xml.sax.SAXException e) {
        e.printStackTrace();
        return null;
    }
}
*/


}




