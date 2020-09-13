/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package toolbox.modeltools;

import java.util.logging.Level;
import java.util.logging.Logger;
import weka.classifiers.evaluation.ThresholdCurve;
import weka.core.Instances;
import weka.core.Utils;
import weka.gui.visualize.PlotData2D;
import weka.gui.visualize.ThresholdVisualizePanel;
import java.awt.BorderLayout;
import java.util.Random;
import javax.swing.JFrame;
import toolbox.viewtools.VisualizationTools;
import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
/**
 *
 * @author amb04
 */
public class ROC
{

//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
//****************************************************************************
//----------------------------------------------------------------------------
//             Generate  & Visualized ROC fore Classifier & Data
//----------------------------------------------------------------------------
//****************************************************************************
public static String generateAndVisualizedROC_ForeClassifierAndData(Classifier classifier, Instances data)
  {
        try {
            
            //classifier = new NaiveBayes();
            Evaluation eval = new Evaluation(data); // evaluate classifier
            Random rand = new Random(1); // using seed = 1
            int folds = 10;
            eval.crossValidateModel(classifier, data, folds, rand);
            // generate curve
            ThresholdCurve tc = new ThresholdCurve();
            int classIndex = 0;
            Instances curve = tc.getCurve(eval.predictions(), classIndex);
            // plot curve
            ThresholdVisualizePanel tvp = new ThresholdVisualizePanel();
            tvp.setROCString("(Area under ROC = " + Utils.doubleToString(ThresholdCurve.getROCArea(curve), 4) + ")");
            tvp.setName(curve.relationName());
            PlotData2D plotdata = new PlotData2D(curve);
            plotdata.setPlotName(curve.relationName());
            plotdata.addInstanceNumberAttribute();
            // specify which points are connected
            boolean[] cp = new boolean[curve.numInstances()];
            for (int n = 1; n < cp.length; n++) {
                cp[n] = true;
            }
            plotdata.setConnectPoints(cp);
            // add plot
            tvp.addPlot(plotdata);
            // display curve
            final JFrame jf = new JFrame("ROC: " + tvp.getName());
            jf.setSize(500, 400);
            jf.getContentPane().setLayout(new BorderLayout());
            jf.getContentPane().add(tvp, BorderLayout.CENTER);
            jf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            jf.setVisible(true);
            String evalString = eval.toSummaryString() + eval.toClassDetailsString() + eval.toMatrixString();
            return eval.toString() + evalString + eval.toString();
        } catch (Exception ex) {
            Logger.getLogger(ROC.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }

  }


//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
//****************************************************************************
//----------------------------------------------------------------------------
//                      Visualized Evluation ROC
//----------------------------------------------------------------------------
//****************************************************************************
public static void visualizedEvaluationROC(Evaluation eval)
  {
        try {
            // generate curve
            ThresholdCurve tc = new ThresholdCurve();
            int classIndex = 0;
            Instances curve = tc.getCurve(eval.predictions(), classIndex);
            // plot curve
            ThresholdVisualizePanel tvp = new ThresholdVisualizePanel();
            tvp.setROCString("(Area under ROC = " + Utils.doubleToString(ThresholdCurve.getROCArea(curve), 4) + ")");
            tvp.setName(curve.relationName());
            PlotData2D plotdata = new PlotData2D(curve);
            plotdata.setPlotName(curve.relationName());
            plotdata.addInstanceNumberAttribute();
            // specify which points are connected
            boolean[] cp = new boolean[curve.numInstances()];
            for (int n = 1; n < cp.length; n++) {
                cp[n] = true;
            }
            plotdata.setConnectPoints(cp);
            // add plot
            tvp.addPlot(plotdata);
            // display curve
            final JFrame jf = new JFrame("ROC: " + tvp.getName());
            jf.setSize(500, 400);
            jf.getContentPane().setLayout(new BorderLayout());
            jf.getContentPane().add(tvp, BorderLayout.CENTER);
            jf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            jf.setVisible(true);
        } catch (Exception ex) {
            Logger.getLogger(ROC.class.getName()).log(Level.SEVERE, null, ex);
        }

  }


//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
//****************************************************************************
//----------------------------------------------------------------------------
//                          Visualize Data
//----------------------------------------------------------------------------
//****************************************************************************
public static void visualizeROCData(Instances data)
{
        try {
            // method visualize
            ThresholdVisualizePanel tvp = new ThresholdVisualizePanel();
            tvp.setROCString("(Area under ROC = " + Utils.doubleToString(ThresholdCurve.getROCArea(data), 4) + ")");
            tvp.setName(data.relationName());
            PlotData2D plotdata = new PlotData2D(data);
            plotdata.setPlotName(data.relationName());
            plotdata.addInstanceNumberAttribute();
            // specify which points are connected
            boolean[] cp = new boolean[data.numInstances()];
            for (int n = 1; n < cp.length; n++) {
                cp[n] = true;
            }
            plotdata.setConnectPoints(cp);
            // add plot
            tvp.addPlot(plotdata);
            // method visualizeClassifierErrors
            final JFrame jf = new JFrame("ROC: " + tvp.getName());
            jf.setSize(500, 400);
            jf.getContentPane().setLayout(new BorderLayout());
            jf.getContentPane().add(tvp, BorderLayout.CENTER);
            jf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            jf.setVisible(true);
        } catch (Exception ex) {
            Logger.getLogger(VisualizationTools.class.getName()).log(Level.SEVERE, null, ex);
        }
  }


}
