/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * PerformingForm.java
 *
 * Created on 04-Oct-2010, 12:08:43
 */

package gui.form.outcome;

//import global.Config;
import global.Global;
import gui.form.input.StudyForm;
import gui.form.supplement.ManagementForm;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import process_model.general.Procedure;
import process_model.issue.measurement.Measure;
import process_model.issue.measurement.MeasurmentOutcome;
import process_model.phase.Phase;
import process_model.process.result.Result;
import process_model.phase.delivery.knowledge.PresentedKnowledge;
import process_model.phase.delivery.selection.ModelingTechniqueSelection;
import process_model.phase.delivery.model.Model;
import process_model.phase.delivery.model.ModelEvaluation;
import process_model.phase.delivery.process_objective.DataMiningObjective;
import process_model.supplement.management.resource.Resource;
import process_model.supplement.management.resource.Resources;
import project.Project;
import process_model.process.Process;
import process_model.supplement.standard.Standard;
import toolbox.Tools;

import toolbox.modeltools.ClassificationTools;
import toolbox.modeltools.ClusteringTools;
import toolbox.modeltools.DimentionalityReductionTools;
import toolbox.modeltools.FeatureAnalysisTools;
import toolbox.modeltools.RegressionTools;

import toolbox.modeltools.algorithms.MLP;
import toolbox.viewtools.VisualizationTools;
import weka.associations.Apriori;
import weka.attributeSelection.PrincipalComponents;

import weka.classifiers.Classifier;

import weka.classifiers.bayes.BayesNet;
import weka.classifiers.functions.LinearRegression;
import weka.classifiers.functions.SMOreg;
import weka.classifiers.trees.J48;
import weka.classifiers.trees.RandomForest;

import weka.clusterers.Clusterer;
import weka.clusterers.Cobweb;
import weka.clusterers.DensityBasedClusterer;
import weka.clusterers.EM;
import weka.clusterers.HierarchicalClusterer;
import weka.clusterers.SimpleKMeans;
import weka.core.Instances;


/**
 *
 * @author amb04
 */
public class KnowledgePresentationForm extends javax.swing.JFrame {
private Procedure procedure=null;
private ModelEvaluation modelEvaluation;
private Resource resource=null;
private Phase phase=Global.currentPhase;
private ModelingTechniqueSelection techniqueSelection=null;
private Model model=null;
 //private Models models= new Models();
 //
 private PresentedKnowledge presentedKnowledge;
 //
private boolean automated=false;
private Standard standard;
private Measure measure=null;
private MeasurmentOutcome measuredOutcome =null;

private JFrame presentation_JFrame=null;
private File defaultPresentationFile=null;


private String tempLocation=null;
//AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA
private String approaches[]={"",
    "Hypotheis-Driven",
    "Data-Driven","Other"};

//------------------------------------------------------------------------------
private String tasks[]={"","Association",    
    "Classification",
    "Correlation Aanalysis",
    "Dimentionality Reduction",
    "Feature Extraction and Analysis",
    "Hypotheisis Testing",  
    "Regression",
    "Segmentation",    
    "Other"};

//------------------------------------------------------------------------------
private String goals[]={"","DESCRIPTION",
    "DISCOVERY",
    "PREDICTION",
    "VERIFICATION",
    "OTHER"};

//------------------------------------------------------------------------------
private String splits[]={"","All","Building Data",
    "Training Data",
    "Testing Data",
    "Other"};

//------------------------------------------------------------------------------
private String methods[]={"",
  "GRAPHICAL",
  "TABULAR",
  "TEXTUAL",
  "OTHER"};

//------------------------------------------------------------------------------
private String visulaizationTechniques[]={"",
"Algorithm Based Technique",
"Geometrical Transformation",
"Graph Based Technique",
"Hierarchical Technique",
"Icon Based Technique",
"Multi Dimension",
"One Dimension",
"Pixels Oriented Technique",
"Projection",
"Three_Dimension",
"Two Dimension",
"Other"
};


    //
    /** Creates new form PlanningForm */
//CCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCC
public KnowledgePresentationForm() {
     initComponents();        
     this.refresh_presentation();
     this.populate_presentation();
     this.setTitle("Knowledge Presentation: "+Global.currentPhase.getTitle());
     if (Global.project.getLocation()!=null && Global.project.getName()!=null)
     {
        this.tempLocation=Global.project.getLocation()+"/"+Global.project.getName()+"/"+"temp";    
        this.defaultPresentationFile= new File(tempLocation+"/"+"Presentation.obj");
     }

     
    }


    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Performing_jLayeredPane = new javax.swing.JLayeredPane();
        Performing_Details_jDesktopPane_jDesktopPane = new javax.swing.JDesktopPane();
        Performing_Details_TabbedPane = new javax.swing.JTabbedPane();
        Planning_Planner_jDesktopPane1 = new javax.swing.JDesktopPane();
        jScrollPane4 = new javax.swing.JScrollPane();
        Model_Selected_Standards_jList = new javax.swing.JList();
        Technique_jDesktopPane = new javax.swing.JDesktopPane();
        Approaches_jComboBox = new javax.swing.JComboBox();
        Planning_Planner_Role_jLabel1 = new javax.swing.JLabel();
        goal_jLabel = new javax.swing.JLabel();
        Goals_jComboBox = new javax.swing.JComboBox();
        Tasks_jComboBox = new javax.swing.JComboBox();
        task_jLabel = new javax.swing.JLabel();
        algorithm_jLabel = new javax.swing.JLabel();
        Supervised_jCheckBox = new javax.swing.JCheckBox();
        jScrollPane70 = new javax.swing.JScrollPane();
        Technique_jTextPane = new javax.swing.JTextPane();
        Planning_CustomisedPlanItem_jLabel = new javax.swing.JLabel();
        Algorithm_jTextPane = new javax.swing.JTextField();
        URL_jLabel = new javax.swing.JLabel();
        Model_URL_jTextField = new javax.swing.JTextField();
        Browse_jButton = new javax.swing.JButton();
        jScrollPane8 = new javax.swing.JScrollPane();
        Model_Used_Resources_jList = new javax.swing.JList();
        jScrollPane69 = new javax.swing.JScrollPane();
        ResultSummary_jTextPane = new javax.swing.JTextPane();
        ModelPerformance_jLabel = new javax.swing.JLabel();
        jScrollPane25 = new javax.swing.JScrollPane();
        Performed_Outcomes_Details_jTextArea = new javax.swing.JTextArea();
        jScrollPane1 = new javax.swing.JScrollPane();
        Model_MeasuredOutcomes_jList = new javax.swing.JList();
        ModelView_jButton = new javax.swing.JButton();
        Justification_Source_jDesktopPane = new javax.swing.JDesktopPane();
        Reporting_Customised_jDesktopPane8 = new javax.swing.JDesktopPane();
        jDesktopPane45 = new javax.swing.JDesktopPane();
        ModelUnderFit_jCheckBox = new javax.swing.JCheckBox();
        jDesktopPane51 = new javax.swing.JDesktopPane();
        ModelPerformanceAcceptable_jCheckBox = new javax.swing.JCheckBox();
        jDesktopPane50 = new javax.swing.JDesktopPane();
        ModelOverFit_jCheckBox = new javax.swing.JCheckBox();
        jScrollPane6 = new javax.swing.JScrollPane();
        Procedures_jList = new javax.swing.JList();
        jScrollPane5 = new javax.swing.JScrollPane();
        Evaluation_Outcomes_Details_jTextArea = new javax.swing.JTextArea();
        jScrollPane3 = new javax.swing.JScrollPane();
        Evaluation_Measurment_Outcomes_jList = new javax.swing.JList();
        jPanel6 = new javax.swing.JPanel();
        ModelEvaluation_URL_jTextField = new javax.swing.JTextField();
        View_jButton = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        Objectives_jTextArea = new javax.swing.JTextArea();
        jButton1 = new javax.swing.JButton();
        Planning_Resource_jDesktopPane = new javax.swing.JDesktopPane();
        Planning_Objectives_Control_jDesktopPane1 = new javax.swing.JDesktopPane();
        Resource_Edit_jButton = new javax.swing.JButton();
        Resources_Select_jButton1 = new javax.swing.JButton();
        Resources_UnSelect_jButton1 = new javax.swing.JButton();
        Reporting_Customised_jDesktopPane3 = new javax.swing.JDesktopPane();
        jScrollPane7 = new javax.swing.JScrollPane();
        Resources_jList = new javax.swing.JList();
        Reporting_Customised_jDesktopPane5 = new javax.swing.JDesktopPane();
        jScrollPane11 = new javax.swing.JScrollPane();
        Pres_Req_Resources_jList = new javax.swing.JList();
        Resource_jDesktopPane1 = new javax.swing.JDesktopPane();
        ConsumedTime_jLabel1 = new javax.swing.JLabel();
        ConsumedTimeDuration_jTextField = new javax.swing.JTextField();
        Reporting_Customised_jDesktopPane6 = new javax.swing.JDesktopPane();
        Project_Constraint_RemainingFunds_jLabel1 = new javax.swing.JLabel();
        RemainingFunds_jTextField = new javax.swing.JTextField();
        Reporting_Customised_jDesktopPane4 = new javax.swing.JDesktopPane();
        RemainingTime_jTextField = new javax.swing.JTextField();
        Project_Constraint_RemainingDuration_jLabel1 = new javax.swing.JLabel();
        Navigation_jDesktopPane = new javax.swing.JDesktopPane();
        Last_jButton = new javax.swing.JButton();
        Next_jButton = new javax.swing.JButton();
        Previous_jButton = new javax.swing.JButton();
        First_jButton = new javax.swing.JButton();
        Control_jDesktopPane = new javax.swing.JDesktopPane();
        Delete_jButton = new javax.swing.JButton();
        Save_jButton = new javax.swing.JButton();
        Refresh_jButton = new javax.swing.JButton();
        New_jDesktopPane = new javax.swing.JDesktopPane();
        New_jButton1 = new javax.swing.JButton();
        Add_jButton1 = new javax.swing.JButton();
        Planning_CustomisedPlan_jDesktopPane = new javax.swing.JDesktopPane();
        Performed_Plan_jPanel = new javax.swing.JPanel();
        Planning_Planner_Name_jLabel14 = new javax.swing.JLabel();
        Method_jComboBox = new javax.swing.JComboBox();
        Technique_jComboBox = new javax.swing.JComboBox();
        Planning_Planner_Name_jLabel13 = new javax.swing.JLabel();
        Planning_Planner_Name_jLabel12 = new javax.swing.JLabel();
        jScrollPane9 = new javax.swing.JScrollPane();
        Presentation_Description_jTextPane = new javax.swing.JTextPane();
        jPanel2 = new javax.swing.JPanel();
        Prerequisite_Source_ExternalSource_URL_jLabel4 = new javax.swing.JLabel();
        Presentation_URL_jTextField = new javax.swing.JTextField();
        Presentation_Browse_jButton = new javax.swing.JButton();
        KnowledgeView_jButton = new javax.swing.JButton();
        Build_Visualization_jButton = new javax.swing.JButton();
        FinalSelection_jCheckBox = new javax.swing.JCheckBox();
        Automated_jCheckBox = new javax.swing.JCheckBox();
        menuBar = new javax.swing.JMenuBar();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
            public void windowDeactivated(java.awt.event.WindowEvent evt) {
                formWindowDeactivated(evt);
            }
        });

        Performing_jLayeredPane.setBackground(new java.awt.Color(255, 255, 204));
        Performing_jLayeredPane.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        Performing_Details_jDesktopPane_jDesktopPane.setBackground(new java.awt.Color(255, 255, 204));
        Performing_Details_jDesktopPane_jDesktopPane.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        Planning_Planner_jDesktopPane1.setBackground(new java.awt.Color(255, 255, 204));
        Planning_Planner_jDesktopPane1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        Model_Selected_Standards_jList.setBackground(new java.awt.Color(255, 255, 153));
        Model_Selected_Standards_jList.setBorder(javax.swing.BorderFactory.createTitledBorder("Selected Standards"));
        Model_Selected_Standards_jList.setModel(this.getModelSelectedStandardsModel());
        Model_Selected_Standards_jList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        Model_Selected_Standards_jList.setEnabled(false);
        Model_Selected_Standards_jList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                Model_Selected_Standards_jListValueChanged(evt);
            }
        });
        jScrollPane4.setViewportView(Model_Selected_Standards_jList);

        jScrollPane4.setBounds(480, 80, 190, 220);
        Planning_Planner_jDesktopPane1.add(jScrollPane4, javax.swing.JLayeredPane.DEFAULT_LAYER);

        Technique_jDesktopPane.setBackground(new java.awt.Color(255, 255, 153));
        Technique_jDesktopPane.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        Approaches_jComboBox.setBackground(new java.awt.Color(255, 255, 204));
        Approaches_jComboBox.setModel(new DefaultComboBoxModel(this.approaches));
        Approaches_jComboBox.setEnabled(false);
        Approaches_jComboBox.setBounds(90, 10, 160, 22);
        Technique_jDesktopPane.add(Approaches_jComboBox, javax.swing.JLayeredPane.DEFAULT_LAYER);

        Planning_Planner_Role_jLabel1.setText(" Approach");
        Planning_Planner_Role_jLabel1.setBounds(10, 10, 110, 20);
        Technique_jDesktopPane.add(Planning_Planner_Role_jLabel1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        goal_jLabel.setText(" Goal");
        goal_jLabel.setBounds(10, 40, 110, 20);
        Technique_jDesktopPane.add(goal_jLabel, javax.swing.JLayeredPane.DEFAULT_LAYER);

        Goals_jComboBox.setBackground(new java.awt.Color(255, 255, 204));
        Goals_jComboBox.setModel(new DefaultComboBoxModel(this.goals));
        Goals_jComboBox.setEnabled(false);
        Goals_jComboBox.setBounds(90, 40, 160, 22);
        Technique_jDesktopPane.add(Goals_jComboBox, javax.swing.JLayeredPane.DEFAULT_LAYER);

        Tasks_jComboBox.setBackground(new java.awt.Color(255, 255, 204));
        Tasks_jComboBox.setModel(new DefaultComboBoxModel(this.tasks));
        Tasks_jComboBox.setEnabled(false);
        Tasks_jComboBox.setBounds(90, 70, 160, 22);
        Technique_jDesktopPane.add(Tasks_jComboBox, javax.swing.JLayeredPane.DEFAULT_LAYER);

        task_jLabel.setText(" Task");
        task_jLabel.setBounds(10, 70, 110, 20);
        Technique_jDesktopPane.add(task_jLabel, javax.swing.JLayeredPane.DEFAULT_LAYER);

        algorithm_jLabel.setText("Algoritm");
        algorithm_jLabel.setBounds(10, 150, 80, 20);
        Technique_jDesktopPane.add(algorithm_jLabel, javax.swing.JLayeredPane.DEFAULT_LAYER);

        Supervised_jCheckBox.setBackground(new java.awt.Color(255, 255, 153));
        Supervised_jCheckBox.setText("Supervised");
        Supervised_jCheckBox.setEnabled(false);
        Supervised_jCheckBox.setBounds(90, 193, 100, 20);
        Technique_jDesktopPane.add(Supervised_jCheckBox, javax.swing.JLayeredPane.DEFAULT_LAYER);

        Technique_jTextPane.setBackground(new java.awt.Color(255, 255, 204));
        Technique_jTextPane.setEnabled(false);
        jScrollPane70.setViewportView(Technique_jTextPane);

        jScrollPane70.setBounds(90, 100, 160, 40);
        Technique_jDesktopPane.add(jScrollPane70, javax.swing.JLayeredPane.DEFAULT_LAYER);

        Planning_CustomisedPlanItem_jLabel.setText("Technique");
        Planning_CustomisedPlanItem_jLabel.setBounds(10, 100, 70, 20);
        Technique_jDesktopPane.add(Planning_CustomisedPlanItem_jLabel, javax.swing.JLayeredPane.DEFAULT_LAYER);

        Algorithm_jTextPane.setBackground(new java.awt.Color(255, 255, 204));
        Algorithm_jTextPane.setBounds(90, 150, 160, 40);
        Technique_jDesktopPane.add(Algorithm_jTextPane, javax.swing.JLayeredPane.DEFAULT_LAYER);

        Technique_jDesktopPane.setBounds(20, 80, 260, 220);
        Planning_Planner_jDesktopPane1.add(Technique_jDesktopPane, javax.swing.JLayeredPane.DEFAULT_LAYER);

        URL_jLabel.setText("Model URL");
        URL_jLabel.setBounds(20, 10, 120, 20);
        Planning_Planner_jDesktopPane1.add(URL_jLabel, javax.swing.JLayeredPane.DEFAULT_LAYER);

        Model_URL_jTextField.setBackground(new java.awt.Color(255, 255, 153));
        Model_URL_jTextField.setEditable(false);
        Model_URL_jTextField.setBounds(20, 30, 500, 25);
        Planning_Planner_jDesktopPane1.add(Model_URL_jTextField, javax.swing.JLayeredPane.DEFAULT_LAYER);

        Browse_jButton.setText("Open");
        Browse_jButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Browse_jButtonActionPerformed(evt);
            }
        });
        Browse_jButton.setBounds(520, 30, 80, 25);
        Planning_Planner_jDesktopPane1.add(Browse_jButton, javax.swing.JLayeredPane.DEFAULT_LAYER);

        Model_Used_Resources_jList.setBackground(new java.awt.Color(255, 255, 153));
        Model_Used_Resources_jList.setBorder(javax.swing.BorderFactory.createTitledBorder("Model Building Requirements"));
        Model_Used_Resources_jList.setModel(this.getModelUsedResourcesModel());
        Model_Used_Resources_jList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        Model_Used_Resources_jList.setEnabled(false);
        Model_Used_Resources_jList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                Model_Used_Resources_jListValueChanged(evt);
            }
        });
        jScrollPane8.setViewportView(Model_Used_Resources_jList);

        jScrollPane8.setBounds(290, 80, 180, 220);
        Planning_Planner_jDesktopPane1.add(jScrollPane8, javax.swing.JLayeredPane.DEFAULT_LAYER);

        ResultSummary_jTextPane.setBackground(new java.awt.Color(255, 255, 153));
        ResultSummary_jTextPane.setEditable(false);
        jScrollPane69.setViewportView(ResultSummary_jTextPane);

        jScrollPane69.setBounds(680, 30, 250, 40);
        Planning_Planner_jDesktopPane1.add(jScrollPane69, javax.swing.JLayeredPane.DEFAULT_LAYER);

        ModelPerformance_jLabel.setText("Performance Result Summary");
        ModelPerformance_jLabel.setBounds(680, 10, 230, 20);
        Planning_Planner_jDesktopPane1.add(ModelPerformance_jLabel, javax.swing.JLayeredPane.DEFAULT_LAYER);

        Performed_Outcomes_Details_jTextArea.setBackground(new java.awt.Color(255, 255, 204));
        Performed_Outcomes_Details_jTextArea.setColumns(20);
        Performed_Outcomes_Details_jTextArea.setRows(5);
        jScrollPane25.setViewportView(Performed_Outcomes_Details_jTextArea);

        jScrollPane25.setBounds(680, 230, 250, 70);
        Planning_Planner_jDesktopPane1.add(jScrollPane25, javax.swing.JLayeredPane.DEFAULT_LAYER);

        Model_MeasuredOutcomes_jList.setBackground(new java.awt.Color(255, 255, 153));
        Model_MeasuredOutcomes_jList.setBorder(javax.swing.BorderFactory.createTitledBorder("Measured Outcomes"));
        Model_MeasuredOutcomes_jList.setModel(this.getModelOutcomes());
        Model_MeasuredOutcomes_jList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                Model_MeasuredOutcomes_jListValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(Model_MeasuredOutcomes_jList);

        jScrollPane1.setBounds(680, 80, 250, 140);
        Planning_Planner_jDesktopPane1.add(jScrollPane1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        ModelView_jButton.setText("View");
        ModelView_jButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ModelView_jButtonActionPerformed(evt);
            }
        });
        ModelView_jButton.setBounds(600, 30, 70, 25);
        Planning_Planner_jDesktopPane1.add(ModelView_jButton, javax.swing.JLayeredPane.DEFAULT_LAYER);

        Performing_Details_TabbedPane.addTab("Data Mining Model", Planning_Planner_jDesktopPane1);

        Justification_Source_jDesktopPane.setBackground(new java.awt.Color(255, 255, 153));
        Justification_Source_jDesktopPane.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        Reporting_Customised_jDesktopPane8.setBackground(new java.awt.Color(255, 255, 153));
        Reporting_Customised_jDesktopPane8.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jDesktopPane45.setBackground(new java.awt.Color(255, 255, 153));
        jDesktopPane45.setBorder(javax.swing.BorderFactory.createTitledBorder("Is The Model Underfit"));

        ModelUnderFit_jCheckBox.setBackground(new java.awt.Color(255, 255, 153));
        ModelUnderFit_jCheckBox.setText("Yes");
        ModelUnderFit_jCheckBox.setEnabled(false);
        ModelUnderFit_jCheckBox.setBounds(10, 20, 70, 20);
        jDesktopPane45.add(ModelUnderFit_jCheckBox, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jDesktopPane45.setBounds(630, 10, 280, 50);
        Reporting_Customised_jDesktopPane8.add(jDesktopPane45, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jDesktopPane51.setBackground(new java.awt.Color(255, 255, 153));
        jDesktopPane51.setBorder(javax.swing.BorderFactory.createTitledBorder("Does The Performance Match Expectations"));

        ModelPerformanceAcceptable_jCheckBox.setBackground(new java.awt.Color(255, 255, 153));
        ModelPerformanceAcceptable_jCheckBox.setText("Yes");
        ModelPerformanceAcceptable_jCheckBox.setEnabled(false);
        ModelPerformanceAcceptable_jCheckBox.setBounds(10, 20, 70, 20);
        jDesktopPane51.add(ModelPerformanceAcceptable_jCheckBox, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jDesktopPane51.setBounds(10, 10, 300, 50);
        Reporting_Customised_jDesktopPane8.add(jDesktopPane51, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jDesktopPane50.setBackground(new java.awt.Color(255, 255, 153));
        jDesktopPane50.setBorder(javax.swing.BorderFactory.createTitledBorder("Is the Model Overfit"));

        ModelOverFit_jCheckBox.setBackground(new java.awt.Color(255, 255, 153));
        ModelOverFit_jCheckBox.setText("Yes");
        ModelOverFit_jCheckBox.setEnabled(false);
        ModelOverFit_jCheckBox.setBounds(10, 20, 70, 20);
        jDesktopPane50.add(ModelOverFit_jCheckBox, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jDesktopPane50.setBounds(320, 10, 280, 50);
        Reporting_Customised_jDesktopPane8.add(jDesktopPane50, javax.swing.JLayeredPane.DEFAULT_LAYER);

        Procedures_jList.setBackground(new java.awt.Color(255, 255, 204));
        Procedures_jList.setBorder(javax.swing.BorderFactory.createTitledBorder("Validation Test Procedures"));
        Procedures_jList.setModel(this.getEvaluationProcedures());
        Procedures_jList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                Procedures_jListValueChanged(evt);
            }
        });
        jScrollPane6.setViewportView(Procedures_jList);

        jScrollPane6.setBounds(630, 70, 280, 170);
        Reporting_Customised_jDesktopPane8.add(jScrollPane6, javax.swing.JLayeredPane.DEFAULT_LAYER);

        Evaluation_Outcomes_Details_jTextArea.setBackground(new java.awt.Color(255, 255, 204));
        Evaluation_Outcomes_Details_jTextArea.setColumns(20);
        Evaluation_Outcomes_Details_jTextArea.setRows(5);
        Evaluation_Outcomes_Details_jTextArea.setBorder(null);
        jScrollPane5.setViewportView(Evaluation_Outcomes_Details_jTextArea);

        jScrollPane5.setBounds(320, 70, 280, 170);
        Reporting_Customised_jDesktopPane8.add(jScrollPane5, javax.swing.JLayeredPane.DEFAULT_LAYER);

        Evaluation_Measurment_Outcomes_jList.setBackground(new java.awt.Color(255, 255, 204));
        Evaluation_Measurment_Outcomes_jList.setBorder(javax.swing.BorderFactory.createTitledBorder("Model Evaluation Measured Outcomes"));
        Evaluation_Measurment_Outcomes_jList.setModel(this.getEvaluationOutcomes());
        Evaluation_Measurment_Outcomes_jList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                Evaluation_Measurment_Outcomes_jListValueChanged(evt);
            }
        });
        jScrollPane3.setViewportView(Evaluation_Measurment_Outcomes_jList);

        jScrollPane3.setBounds(20, 70, 290, 170);
        Reporting_Customised_jDesktopPane8.add(jScrollPane3, javax.swing.JLayeredPane.DEFAULT_LAYER);

        Reporting_Customised_jDesktopPane8.setBounds(10, 10, 920, 250);
        Justification_Source_jDesktopPane.add(Reporting_Customised_jDesktopPane8, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jPanel6.setBackground(new java.awt.Color(255, 255, 204));
        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "External Model Evaluation URL", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, null, java.awt.Color.black));
        jPanel6.setLayout(null);
        jPanel6.add(ModelEvaluation_URL_jTextField);
        ModelEvaluation_URL_jTextField.setBounds(18, 19, 460, 22);

        View_jButton.setText("View");
        View_jButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                View_jButtonActionPerformed(evt);
            }
        });
        jPanel6.add(View_jButton);
        View_jButton.setBounds(480, 17, 110, 25);

        jPanel6.setBounds(10, 270, 600, 50);
        Justification_Source_jDesktopPane.add(jPanel6, javax.swing.JLayeredPane.DEFAULT_LAYER);

        Performing_Details_TabbedPane.addTab("Model Evaluation Results", Justification_Source_jDesktopPane);

        jPanel1.setBackground(new java.awt.Color(255, 255, 153));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Process Data Mining Objectives"));

        Objectives_jTextArea.setBackground(new java.awt.Color(255, 255, 204));
        Objectives_jTextArea.setColumns(20);
        Objectives_jTextArea.setRows(5);
        jScrollPane2.setViewportView(Objectives_jTextArea);

        jButton1.setText("Aims of Study");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 909, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 909, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 222, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addGap(38, 38, 38))
        );

        Performing_Details_TabbedPane.addTab("Process Objectives", jPanel1);

        Planning_Resource_jDesktopPane.setBackground(new java.awt.Color(255, 255, 153));
        Planning_Resource_jDesktopPane.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        Planning_Resource_jDesktopPane.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                Planning_Resource_jDesktopPaneFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                Planning_Resource_jDesktopPaneFocusLost(evt);
            }
        });

        Planning_Objectives_Control_jDesktopPane1.setBackground(new java.awt.Color(255, 255, 153));

        Resource_Edit_jButton.setText("Edit");
        Resource_Edit_jButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Resource_Edit_jButtonActionPerformed(evt);
            }
        });
        Resource_Edit_jButton.setBounds(0, 50, 70, 20);
        Planning_Objectives_Control_jDesktopPane1.add(Resource_Edit_jButton, javax.swing.JLayeredPane.DEFAULT_LAYER);

        Resources_Select_jButton1.setText("+>");
        Resources_Select_jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Resources_Select_jButton1ActionPerformed(evt);
            }
        });
        Resources_Select_jButton1.setBounds(0, 0, 70, 20);
        Planning_Objectives_Control_jDesktopPane1.add(Resources_Select_jButton1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        Resources_UnSelect_jButton1.setText("<-");
        Resources_UnSelect_jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Resources_UnSelect_jButton1ActionPerformed(evt);
            }
        });
        Resources_UnSelect_jButton1.setBounds(0, 20, 70, 20);
        Planning_Objectives_Control_jDesktopPane1.add(Resources_UnSelect_jButton1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        Planning_Objectives_Control_jDesktopPane1.setBounds(400, 20, 70, 80);
        Planning_Resource_jDesktopPane.add(Planning_Objectives_Control_jDesktopPane1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        Reporting_Customised_jDesktopPane3.setBackground(new java.awt.Color(255, 255, 204));
        Reporting_Customised_jDesktopPane3.setBorder(javax.swing.BorderFactory.createTitledBorder("Available Resources"));

        Resources_jList.setBorder(javax.swing.BorderFactory.createTitledBorder("Project Resources"));
        Resources_jList.setModel(this.getProjectResourcesModel());
        Resources_jList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        Resources_jList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                Resources_jListValueChanged(evt);
            }
        });
        jScrollPane7.setViewportView(Resources_jList);

        jScrollPane7.setBounds(10, 20, 370, 280);
        Reporting_Customised_jDesktopPane3.add(jScrollPane7, javax.swing.JLayeredPane.DEFAULT_LAYER);

        Reporting_Customised_jDesktopPane3.setBounds(10, 10, 390, 310);
        Planning_Resource_jDesktopPane.add(Reporting_Customised_jDesktopPane3, javax.swing.JLayeredPane.DEFAULT_LAYER);

        Reporting_Customised_jDesktopPane5.setBackground(new java.awt.Color(255, 255, 204));
        Reporting_Customised_jDesktopPane5.setBorder(javax.swing.BorderFactory.createTitledBorder("Required Resources"));

        Pres_Req_Resources_jList.setBorder(javax.swing.BorderFactory.createTitledBorder("Model Building Requirements"));
        Pres_Req_Resources_jList.setModel(this.getPresentationUsedResourcesModel());
        Pres_Req_Resources_jList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        Pres_Req_Resources_jList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                Pres_Req_Resources_jListValueChanged(evt);
            }
        });
        jScrollPane11.setViewportView(Pres_Req_Resources_jList);

        jScrollPane11.setBounds(20, 20, 420, 220);
        Reporting_Customised_jDesktopPane5.add(jScrollPane11, javax.swing.JLayeredPane.DEFAULT_LAYER);

        Resource_jDesktopPane1.setBackground(new java.awt.Color(255, 255, 153));
        Resource_jDesktopPane1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        Resource_jDesktopPane1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Resource_jDesktopPane1MouseClicked(evt);
            }
        });

        ConsumedTime_jLabel1.setText("Required Time");
        ConsumedTime_jLabel1.setBounds(10, 0, 90, 22);
        Resource_jDesktopPane1.add(ConsumedTime_jLabel1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        ConsumedTimeDuration_jTextField.setBounds(10, 20, 100, 22);
        Resource_jDesktopPane1.add(ConsumedTimeDuration_jTextField, javax.swing.JLayeredPane.DEFAULT_LAYER);

        Resource_jDesktopPane1.setBounds(20, 250, 120, 50);
        Reporting_Customised_jDesktopPane5.add(Resource_jDesktopPane1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        Reporting_Customised_jDesktopPane6.setBackground(new java.awt.Color(255, 255, 204));
        Reporting_Customised_jDesktopPane6.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        Project_Constraint_RemainingFunds_jLabel1.setText("Remaining Funds");
        Project_Constraint_RemainingFunds_jLabel1.setBounds(10, 0, 100, 20);
        Reporting_Customised_jDesktopPane6.add(Project_Constraint_RemainingFunds_jLabel1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        RemainingFunds_jTextField.setBackground(new java.awt.Color(255, 255, 153));
        RemainingFunds_jTextField.setBounds(10, 20, 100, 22);
        Reporting_Customised_jDesktopPane6.add(RemainingFunds_jTextField, javax.swing.JLayeredPane.DEFAULT_LAYER);

        Reporting_Customised_jDesktopPane6.setBounds(320, 250, 120, 50);
        Reporting_Customised_jDesktopPane5.add(Reporting_Customised_jDesktopPane6, javax.swing.JLayeredPane.DEFAULT_LAYER);

        Reporting_Customised_jDesktopPane4.setBackground(new java.awt.Color(255, 255, 204));
        Reporting_Customised_jDesktopPane4.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        RemainingTime_jTextField.setBackground(new java.awt.Color(255, 255, 153));
        RemainingTime_jTextField.setBounds(10, 20, 100, 22);
        Reporting_Customised_jDesktopPane4.add(RemainingTime_jTextField, javax.swing.JLayeredPane.DEFAULT_LAYER);

        Project_Constraint_RemainingDuration_jLabel1.setText("Remaining Time");
        Project_Constraint_RemainingDuration_jLabel1.setBounds(10, 0, 100, 25);
        Reporting_Customised_jDesktopPane4.add(Project_Constraint_RemainingDuration_jLabel1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        Reporting_Customised_jDesktopPane4.setBounds(170, 250, 120, 50);
        Reporting_Customised_jDesktopPane5.add(Reporting_Customised_jDesktopPane4, javax.swing.JLayeredPane.DEFAULT_LAYER);

        Reporting_Customised_jDesktopPane5.setBounds(470, 10, 460, 310);
        Planning_Resource_jDesktopPane.add(Reporting_Customised_jDesktopPane5, javax.swing.JLayeredPane.DEFAULT_LAYER);

        Performing_Details_TabbedPane.addTab("Knowledg Presentation Requirments", Planning_Resource_jDesktopPane);

        Performing_Details_TabbedPane.setBounds(10, 270, 950, 360);
        Performing_Details_jDesktopPane_jDesktopPane.add(Performing_Details_TabbedPane, javax.swing.JLayeredPane.DEFAULT_LAYER);

        Navigation_jDesktopPane.setBackground(new java.awt.Color(255, 255, 153));
        Navigation_jDesktopPane.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        Last_jButton.setText("Last >>");
        Last_jButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Last_jButtonActionPerformed(evt);
            }
        });
        Last_jButton.setBounds(330, 10, 100, 23);
        Navigation_jDesktopPane.add(Last_jButton, javax.swing.JLayeredPane.DEFAULT_LAYER);

        Next_jButton.setText("Next >");
        Next_jButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Next_jButtonActionPerformed(evt);
            }
        });
        Next_jButton.setBounds(240, 10, 90, 23);
        Navigation_jDesktopPane.add(Next_jButton, javax.swing.JLayeredPane.DEFAULT_LAYER);

        Previous_jButton.setText("< Previous");
        Previous_jButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Previous_jButtonActionPerformed(evt);
            }
        });
        Previous_jButton.setBounds(130, 10, 110, 23);
        Navigation_jDesktopPane.add(Previous_jButton, javax.swing.JLayeredPane.DEFAULT_LAYER);

        First_jButton.setText("<< First");
        First_jButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                First_jButtonActionPerformed(evt);
            }
        });
        First_jButton.setBounds(10, 10, 120, 23);
        Navigation_jDesktopPane.add(First_jButton, javax.swing.JLayeredPane.DEFAULT_LAYER);

        Navigation_jDesktopPane.setBounds(10, 640, 440, 40);
        Performing_Details_jDesktopPane_jDesktopPane.add(Navigation_jDesktopPane, javax.swing.JLayeredPane.DEFAULT_LAYER);

        Control_jDesktopPane.setBackground(new java.awt.Color(255, 255, 153));
        Control_jDesktopPane.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        Delete_jButton.setText("Delete");
        Delete_jButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Delete_jButtonActionPerformed(evt);
            }
        });
        Delete_jButton.setBounds(180, 10, 80, 25);
        Control_jDesktopPane.add(Delete_jButton, javax.swing.JLayeredPane.DEFAULT_LAYER);

        Save_jButton.setText("Save");
        Save_jButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Save_jButtonActionPerformed(evt);
            }
        });
        Save_jButton.setBounds(100, 10, 80, 25);
        Control_jDesktopPane.add(Save_jButton, javax.swing.JLayeredPane.DEFAULT_LAYER);

        Refresh_jButton.setText("Refresh");
        Refresh_jButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Refresh_jButtonActionPerformed(evt);
            }
        });
        Refresh_jButton.setBounds(10, 10, 90, 25);
        Control_jDesktopPane.add(Refresh_jButton, javax.swing.JLayeredPane.DEFAULT_LAYER);

        Control_jDesktopPane.setBounds(470, 640, 270, 40);
        Performing_Details_jDesktopPane_jDesktopPane.add(Control_jDesktopPane, javax.swing.JLayeredPane.DEFAULT_LAYER);

        New_jDesktopPane.setBackground(new java.awt.Color(255, 255, 153));
        New_jDesktopPane.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        New_jButton1.setText("New");
        New_jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                New_jButton1ActionPerformed(evt);
            }
        });
        New_jButton1.setBounds(10, 10, 80, 23);
        New_jDesktopPane.add(New_jButton1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        Add_jButton1.setText("Add +");
        Add_jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Add_jButton1ActionPerformed(evt);
            }
        });
        Add_jButton1.setBounds(90, 10, 90, 23);
        New_jDesktopPane.add(Add_jButton1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        New_jDesktopPane.setBounds(770, 640, 190, 40);
        Performing_Details_jDesktopPane_jDesktopPane.add(New_jDesktopPane, javax.swing.JLayeredPane.DEFAULT_LAYER);

        Planning_CustomisedPlan_jDesktopPane.setBackground(new java.awt.Color(255, 255, 204));
        Planning_CustomisedPlan_jDesktopPane.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        Performed_Plan_jPanel.setBackground(new java.awt.Color(255, 255, 153));
        Performed_Plan_jPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        Performed_Plan_jPanel.setLayout(null);

        Planning_Planner_Name_jLabel14.setText("Prsentation Method");
        Performed_Plan_jPanel.add(Planning_Planner_Name_jLabel14);
        Planning_Planner_Name_jLabel14.setBounds(20, 10, 280, 20);

        Method_jComboBox.setBackground(new java.awt.Color(255, 255, 204));
        Method_jComboBox.setMaximumRowCount(10);
        Method_jComboBox.setModel(new DefaultComboBoxModel(this.methods));
        Performed_Plan_jPanel.add(Method_jComboBox);
        Method_jComboBox.setBounds(20, 30, 280, 22);

        Technique_jComboBox.setBackground(new java.awt.Color(255, 255, 204));
        Technique_jComboBox.setMaximumRowCount(10);
        Technique_jComboBox.setModel(new DefaultComboBoxModel(this.visulaizationTechniques));
        Performed_Plan_jPanel.add(Technique_jComboBox);
        Technique_jComboBox.setBounds(20, 80, 280, 22);

        Planning_Planner_Name_jLabel13.setText("Representation Technique");
        Performed_Plan_jPanel.add(Planning_Planner_Name_jLabel13);
        Planning_Planner_Name_jLabel13.setBounds(20, 60, 280, 20);

        Planning_Planner_Name_jLabel12.setText("Presentation Description");
        Performed_Plan_jPanel.add(Planning_Planner_Name_jLabel12);
        Planning_Planner_Name_jLabel12.setBounds(330, 10, 260, 20);

        jScrollPane9.setViewportView(Presentation_Description_jTextPane);

        Performed_Plan_jPanel.add(jScrollPane9);
        jScrollPane9.setBounds(330, 30, 570, 70);

        Performed_Plan_jPanel.setBounds(10, 10, 920, 120);
        Planning_CustomisedPlan_jDesktopPane.add(Performed_Plan_jPanel, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jPanel2.setBackground(new java.awt.Color(255, 255, 153));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel2.setLayout(null);

        Prerequisite_Source_ExternalSource_URL_jLabel4.setText("Presentation URL");
        jPanel2.add(Prerequisite_Source_ExternalSource_URL_jLabel4);
        Prerequisite_Source_ExternalSource_URL_jLabel4.setBounds(20, 40, 130, 20);
        jPanel2.add(Presentation_URL_jTextField);
        Presentation_URL_jTextField.setBounds(20, 60, 720, 25);

        Presentation_Browse_jButton.setText("Browse");
        Presentation_Browse_jButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Presentation_Browse_jButtonActionPerformed(evt);
            }
        });
        jPanel2.add(Presentation_Browse_jButton);
        Presentation_Browse_jButton.setBounds(740, 60, 80, 25);

        KnowledgeView_jButton.setText("View");
        KnowledgeView_jButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                KnowledgeView_jButtonActionPerformed(evt);
            }
        });
        jPanel2.add(KnowledgeView_jButton);
        KnowledgeView_jButton.setBounds(820, 60, 80, 25);

        Build_Visualization_jButton.setText("<< Generate Model Visualization >>");
        Build_Visualization_jButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Build_Visualization_jButtonActionPerformed(evt);
            }
        });
        jPanel2.add(Build_Visualization_jButton);
        Build_Visualization_jButton.setBounds(19, 10, 280, 25);

        FinalSelection_jCheckBox.setBackground(new java.awt.Color(255, 255, 153));
        FinalSelection_jCheckBox.setText("Final Selection");
        jPanel2.add(FinalSelection_jCheckBox);
        FinalSelection_jCheckBox.setBounds(500, 10, 160, 20);

        Automated_jCheckBox.setBackground(new java.awt.Color(255, 255, 153));
        Automated_jCheckBox.setText("Automated");
        Automated_jCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Automated_jCheckBoxActionPerformed(evt);
            }
        });
        jPanel2.add(Automated_jCheckBox);
        Automated_jCheckBox.setBounds(340, 10, 150, 20);

        jPanel2.setBounds(10, 140, 920, 100);
        Planning_CustomisedPlan_jDesktopPane.add(jPanel2, javax.swing.JLayeredPane.DEFAULT_LAYER);

        Planning_CustomisedPlan_jDesktopPane.setBounds(10, 10, 945, 250);
        Performing_Details_jDesktopPane_jDesktopPane.add(Planning_CustomisedPlan_jDesktopPane, javax.swing.JLayeredPane.DEFAULT_LAYER);

        Performing_Details_jDesktopPane_jDesktopPane.setBounds(10, 10, 980, 690);
        Performing_jLayeredPane.add(Performing_Details_jDesktopPane_jDesktopPane, javax.swing.JLayeredPane.DEFAULT_LAYER);

        setJMenuBar(menuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Performing_jLayeredPane, javax.swing.GroupLayout.PREFERRED_SIZE, 1000, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Performing_jLayeredPane, javax.swing.GroupLayout.PREFERRED_SIZE, 710, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void Resources_jListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_Resources_jListValueChanged

   if (Global.project!=null&&Global.project.getSupplements()!=null && 
       Global.project.getSupplements().getManagement()!=null &&
       Global.project.getSupplements().getManagement().getResources()!=null )
       if (this.Resources_jList.getSelectedIndex()>=0)
            this.resource=Global.project.getSupplements().getManagement().
            getResources().getResource(this.Resources_jList.getSelectedIndex());

        this.populateForm();


    }//GEN-LAST:event_Resources_jListValueChanged

    private void Sources_UnSelect_jButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Sources_UnSelect_jButtonActionPerformed

    }//GEN-LAST:event_Sources_UnSelect_jButtonActionPerformed



//DDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDD
//****************************************************************************
//                              Delete Resource
//DDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDD
//****************************************************************************
 private void delete_resource()
{
if (this.presentedKnowledge!=null &&
//    this.presentation.getFeasibility()!=null &&
    this.presentedKnowledge.getResources()!=null &&
    this.presentedKnowledge.getResources().getResourcesList().size()>0)
    {
        int index= this.presentedKnowledge.getResources().getResourcesList()
                                                                            .indexOf(this.resource);
        if (this.presentedKnowledge.getResources().delete(this.resource)==0);
        {
            if (this.presentedKnowledge.getResources().getResourcesList().size()>0)
            {
                if (index==0)
                    this.resource = this.presentedKnowledge.getResources().getResource(index);
                else if(index>0)
                    this.resource = this.presentedKnowledge.getResources().getResource(index-1);

            }
        }
        // setResult process objective
 }
}



    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated

this.refresh_presentation_details();

    }//GEN-LAST:event_formWindowActivated

    private void formWindowDeactivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowDeactivated
//this.save_model();
    }//GEN-LAST:event_formWindowDeactivated

    private void Pres_Req_Resources_jListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_Pres_Req_Resources_jListValueChanged

        if (this.presentedKnowledge!=null &&
//            this.model.getFeasibility()!=null &&
            this.presentedKnowledge.getResources()!=null)

         if (this.presentedKnowledge.getResources().getResourcesList().size()>=0
            && this.Pres_Req_Resources_jList.getSelectedIndex()>=0)

             this.resource= (Resource) this.presentedKnowledge.getResources().getResourcesList().get(this.Pres_Req_Resources_jList.getSelectedIndex());
    }//GEN-LAST:event_Pres_Req_Resources_jListValueChanged

    private void Resource_Edit_jButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Resource_Edit_jButtonActionPerformed
    new ManagementForm(this.resource).setVisible(true);
    }//GEN-LAST:event_Resource_Edit_jButtonActionPerformed

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed

System.gc();
//System.runFinalization();
    }//GEN-LAST:event_formWindowClosed



    private void Analysts_jList1ValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_Analysts_jList1ValueChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_Analysts_jList1ValueChanged

    private void Analysts_jList2ValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_Analysts_jList2ValueChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_Analysts_jList2ValueChanged

    private void Planning_Resource_jDesktopPaneFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_Planning_Resource_jDesktopPaneFocusGained
    if (Global.project!=null && Global.project.getSupplements()!=null &&
        Global.project.getSupplements().getManagement()!=null &&
        Global.project.getSupplements().getManagement().getConstraint()!=null)
        {
            this.RemainingTime_jTextField.setText(Global.project.getSupplements().getManagement().getConstraint().getRemainingTime()+"");
            this.RemainingFunds_jTextField.setText(Global.project.getSupplements().getManagement().getConstraint().getRemainingFunds()+"");
        }
    //------------------------------------------------------------------------
  /*
    if (this.model!=null && this.model.getFeasibility()!=null)
         {
            this.Duration_jTextField.setText(this.model.getFeasibility().getRequiredTime()+"");
            this.Feasible_jCheckBox.setSelected(this.model.getFeasibility().isFeasible());
         }
*/

    }//GEN-LAST:event_Planning_Resource_jDesktopPaneFocusGained

    private void Planning_Resource_jDesktopPaneFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_Planning_Resource_jDesktopPaneFocusLost
/*
   if (this.model!=null && this.model.getFeasibility()!=null
       && !this.Duration_jTextField.getText().equals(""))
    {
       this.model.getFeasibility().setRequiredTime(Double.parseDouble(this.Duration_jTextField.getText()));
       this.model.getFeasibility().setFeasible(this.Feasible_jCheckBox.isSelected());
     }
 *
 */
    }//GEN-LAST:event_Planning_Resource_jDesktopPaneFocusLost

    private void Presentation_Browse_jButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Presentation_Browse_jButtonActionPerformed
        
    File file =null;
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++    
    if (!this.Presentation_URL_jTextField.getText().equals("")) 
    {        
        try {            
            URL thisURL=new URL(this.Presentation_URL_jTextField.getText());        
            String homeDirectory=Tools.toFile(thisURL).getParent();
            file= Tools.chooseFile("Choose Model Presentation File", null, homeDirectory, false);
            } 
                
        catch (MalformedURLException ex) 
        {              
            file = Tools.chooseFile("Choose Model Presentation File",null, Global.project.getLocation(), false);
        }
    }
    else
        file = Tools.chooseFile("Choose Model Presentation File",null, Global.project.getLocation(), false);
        
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    if (file!=null)
    {
        //Tools.viewAnyFile(file);
        this.automated=false;
        this.Automated_jCheckBox.setSelected(false);
        
        int decision1=JOptionPane.showConfirmDialog(null, "Would you like to reset the knowledge presentation delivery?"); 
        if (decision1==0)              
            this.Delete_jButtonActionPerformed(evt);
                
        this.Presentation_URL_jTextField.setText(Tools.toURL(file).toString());        
        this.save_presentation();
        //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        int decision=JOptionPane.showConfirmDialog(null, "Would you like to import the file to the Temp folder as a local copy ?");
        if (decision==0)
         {
          try {
              URL selectedUrl = new URL(this.Presentation_URL_jTextField.getText());
              URL newUrl = Tools.copyFileToTemp(selectedUrl);
              if (newUrl!=null)
                  this.Presentation_URL_jTextField.setText(newUrl.toString());
           }
           catch (MalformedURLException ex) {
               Logger.getLogger(DataExplorationForm.class.getName()).log(Level.SEVERE, null, ex);
            }
          }
          this.save_presentation();
          this.refresh_presentation();
          this.populate_presentation();
    }    
    }//GEN-LAST:event_Presentation_Browse_jButtonActionPerformed

    private void New_jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_New_jButton1ActionPerformed
        this.clear_presentation();
}//GEN-LAST:event_New_jButton1ActionPerformed

    private void Refresh_jButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Refresh_jButtonActionPerformed
        this.refresh_presentation();
        this.refresh_presentation_details();
        this.populate_presentation();
    }//GEN-LAST:event_Refresh_jButtonActionPerformed

    private void Save_jButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Save_jButtonActionPerformed
        this.save_presentation();
}//GEN-LAST:event_Save_jButtonActionPerformed

    private void Delete_jButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Delete_jButtonActionPerformed

 if (this.presentedKnowledge!=null)
    {
        this.presentedKnowledge.UnDeliverAsMain();
        this.delete_presentation();  
        this.populate_presentation();
        this.populateForm();
    }     
 
}//GEN-LAST:event_Delete_jButtonActionPerformed

    private void Add_jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Add_jButton1ActionPerformed
        this.save_presentation();
        this.clear_presentation();
}//GEN-LAST:event_Add_jButton1ActionPerformed

    private void First_jButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_First_jButtonActionPerformed
        this.first_presentation();
}//GEN-LAST:event_First_jButtonActionPerformed

    private void Previous_jButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Previous_jButtonActionPerformed
        this.previous_presentation();
}//GEN-LAST:event_Previous_jButtonActionPerformed

    private void Next_jButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Next_jButtonActionPerformed
        this.next_presentation();
}//GEN-LAST:event_Next_jButtonActionPerformed

    private void Last_jButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Last_jButtonActionPerformed
        this.last_presentation();
}//GEN-LAST:event_Last_jButtonActionPerformed

    private void Resources_Select_jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Resources_Select_jButton1ActionPerformed

 this.save_presentation();
 if (this.presentedKnowledge!=null)
 {
        if (this.resource!=null )
        {
            if (this.presentedKnowledge.getResources()==null)
                this.presentedKnowledge.setResources(new Resources());
            this.addResourceFunds();
            this.presentedKnowledge.getResources().save(this.resource);
        }
        this.Pres_Req_Resources_jList.setModel(this.getPresentationUsedResourcesModel());

        this.save_presentation();
        this.populateForm();
  }
 else
  JOptionPane.showMessageDialog(null, "No Objective added yet ...!!!");

    }//GEN-LAST:event_Resources_Select_jButton1ActionPerformed

    private void Resources_UnSelect_jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Resources_UnSelect_jButton1ActionPerformed

        if (this.presentedKnowledge!=null)
        {
            this.deductResourceFunds();
            this.delete_resource();
        }
        //
 this.Pres_Req_Resources_jList.setModel(this.getPresentationUsedResourcesModel());
        //
        this.Pres_Req_Resources_jList.setSelectedIndex(this.presentedKnowledge.getResources().
                getResourcesList().indexOf(this.resource));

        this.populateForm();

    }//GEN-LAST:event_Resources_UnSelect_jButton1ActionPerformed

    private void Model_Selected_Standards_jListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_Model_Selected_Standards_jListValueChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_Model_Selected_Standards_jListValueChanged

    private void Model_Used_Resources_jListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_Model_Used_Resources_jListValueChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_Model_Used_Resources_jListValueChanged

    private void Resource_jDesktopPane1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Resource_jDesktopPane1MouseClicked
        // TODO add your handling code here:
}//GEN-LAST:event_Resource_jDesktopPane1MouseClicked

    private void Browse_jButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Browse_jButtonActionPerformed

   if (!this.Model_URL_jTextField.getText().equals(""))
   {
       String fileName=Model_URL_jTextField.getText().substring(6, this.Model_URL_jTextField.getText().length());
//       System.out.println(fileName);
       Tools.viewAnyFile(new File(fileName));
   }

    }//GEN-LAST:event_Browse_jButtonActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
new StudyForm().setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void Procedures_jListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_Procedures_jListValueChanged

        if (this.modelEvaluation!=null && this.procedure!=null &&
                this.modelEvaluation.getValidationTest()!=null)

            if (this.modelEvaluation.getValidationTest().getProceduresList().size()>=0
            && this.Procedures_jList.getSelectedIndex()>=0)

                this.procedure= this.modelEvaluation.getValidationTest().
                        getProcedure(this.Procedures_jList.getSelectedIndex());
}//GEN-LAST:event_Procedures_jListValueChanged

    private void Model_MeasuredOutcomes_jListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_Model_MeasuredOutcomes_jListValueChanged
        
        
        if (this.model!=null &&
                this.model.getPerformance()!=null &&
                this.model.getPerformance().getOutcomesList().size()>0  &&
                this.Model_MeasuredOutcomes_jList.getSelectedIndex()>=0) {
            this.measuredOutcome =  this.model.getPerformance().getOutcomesList().get(Model_MeasuredOutcomes_jList.getSelectedIndex());
            
            if (measuredOutcome!=null && measuredOutcome.getOutcome()!=null && measuredOutcome.getOutcome().getResult()!=null)
                Performed_Outcomes_Details_jTextArea.setText(measuredOutcome.getOutcome().getResult());
            else
                Performed_Outcomes_Details_jTextArea.setText("");
        }
    }//GEN-LAST:event_Model_MeasuredOutcomes_jListValueChanged

    private void Evaluation_Measurment_Outcomes_jListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_Evaluation_Measurment_Outcomes_jListValueChanged
        if (
                this.modelEvaluation!=null &&
                this.modelEvaluation.getEvaluatedPerformance() !=null &&
                this.modelEvaluation.getEvaluatedPerformance().getOutcomesList()!=null &&
                this.modelEvaluation.getEvaluatedPerformance().getOutcomesList().size()>0 &&
                this.Evaluation_Measurment_Outcomes_jList.getSelectedIndex()>=0) {
            this.measuredOutcome =  this.modelEvaluation.getEvaluatedPerformance().getOutcomesList().get(this.Evaluation_Measurment_Outcomes_jList.getSelectedIndex());
            
            if (measuredOutcome!=null && measuredOutcome.getOutcome()!=null && measuredOutcome.getOutcome().getResult()!=null)
                Evaluation_Outcomes_Details_jTextArea.setText(measuredOutcome.getOutcome().getResult());
            else
                Evaluation_Outcomes_Details_jTextArea.setText("");
            
        }
}//GEN-LAST:event_Evaluation_Measurment_Outcomes_jListValueChanged

    private void ModelView_jButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ModelView_jButtonActionPerformed
  
    if (this.model!=null && this.model.getOutcomeURL()!=null && this.model.getOutcomeURL().toString().contains("Model.obj"))
    {            
        String modelDescriptionFileName=model.getLocation()+"/"+"Model.txt";
        File modelDescriptionFile=new File(modelDescriptionFileName);

        if (modelDescriptionFile.exists())
            modelDescriptionFile.delete();         

        if (model!=null && model.getDescription()!=null)
         {
            Tools.writeStringToFile(model.getDescription(), modelDescriptionFile.getPath());         
            Tools.viewAnyFile(modelDescriptionFile);
         }

        if (modelDescriptionFile.exists())
            modelDescriptionFile.deleteOnExit();             
    }
    else
    {
        if (!Model_URL_jTextField.getText().equals(""))        
          try {                
                URL modelUrl=new URL(Model_URL_jTextField.getText());                
                Tools.viewAnyFile(Tools.toFile(modelUrl));
                
          } catch (MalformedURLException ex) {
                JOptionPane.showMessageDialog(null, "File URL is invalid ");
                Logger.getLogger(KnowledgePresentationForm.class.getName()).log(Level.SEVERE, null, ex);
          }
        
    }

}//GEN-LAST:event_ModelView_jButtonActionPerformed

    private void KnowledgeView_jButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_KnowledgeView_jButtonActionPerformed

//    String graph=null;
    
    if (this.automated==true)
    {
        if (this.presentation_JFrame!=null)
            this.presentation_JFrame.setVisible(true);
        else
            this.visualize();
    }
    //--------------------------------------------------------------------------
    else if (Presentation_URL_jTextField.getText()!=null && !Presentation_URL_jTextField.getText().equals(""))
    {
    
       try {
            Tools.viewAnyFile(Tools.toFile(new URL(Presentation_URL_jTextField.getText())));
       } catch (MalformedURLException ex) {
            JOptionPane.showMessageDialog(null, "Make sure the external file is represented by a valid URL!");
            Logger.getLogger(KnowledgePresentationForm.class.getName()).log(Level.SEVERE, null, ex);
            
       } 
    }    
    //--------------------------------------------------------------------------    
            
     
    }//GEN-LAST:event_KnowledgeView_jButtonActionPerformed

    
//-----------------------------------------------------------------------------
//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
//-----------------------------------------------------------------------------
private void visualize_association(Apriori apriori)
{            
        
     JOptionPane.showMessageDialog(null, "No Visualization is available for Assocuiation!");       
}



//-----------------------------------------------------------------------------
//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
//-----------------------------------------------------------------------------
private void visualize_classification(Classifier cls, Object graph)
{             
    
    if (this.presentedKnowledge==null)
        this.presentedKnowledge=new PresentedKnowledge();
      
    
   Object presentationResults=null;   
   
   //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
   if (cls.getClass().getSimpleName().equals("J48"))
   {       
       String graphString=(String) graph;
       presentationResults=ClassificationTools.getTreeVisualization(graphString, defaultPresentationFile);
       presentation_JFrame=(JFrame) presentationResults;

   }
           
   //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++   
   else  if (cls.getClass().getSimpleName().equals("BayesNet"))
   {       
       String graphString=(String) graph;
       presentationResults=ClassificationTools.getBayesNetVisualization(graphString, defaultPresentationFile);
       presentation_JFrame=(JFrame) presentationResults;
       
   }

   //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++           
   else  if (cls.getClass().getSimpleName().equals("MLP"))
   {
       MLP mlp=(MLP) cls;
       String mlpGraph=(String) graph;
       presentationResults=ClassificationTools.getMLPVisualization(mlpGraph);     
       presentation_JFrame=(JFrame) presentationResults;       
   }
   
   
if (defaultPresentationFile.exists())
    Presentation_URL_jTextField.setText(Tools.toURL(defaultPresentationFile).toString());
   

this.populateForm();

}


//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
//-----------------------------------------------------------------------------
private void visualize_external(Object graph)
{             
    
    if (this.presentedKnowledge==null)
        this.presentedKnowledge=new PresentedKnowledge();
      
    
    Object presentationResults=null;   
             
    String ExternalGraph=(String) graph;
    presentationResults=VisualizationTools.getExternalVisualization(ExternalGraph);     
    presentation_JFrame=(JFrame) presentationResults;          
   
    if (defaultPresentationFile.exists())
        Presentation_URL_jTextField.setText(Tools.toURL(defaultPresentationFile).toString());
   

    this.populateForm();

}



//-----------------------------------------------------------------------------
//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
//-----------------------------------------------------------------------------
private void visualize_hca_clustering(Clusterer cls, String graph)
{             
    
    if (this.presentedKnowledge==null)
        this.presentedKnowledge=new PresentedKnowledge();
  
    
   Object presentation=null;   
  
   HierarchicalClusterer hca= (HierarchicalClusterer) cls;   
    
   if (defaultPresentationFile.exists())
       Presentation_URL_jTextField.setText(Tools.toURL(defaultPresentationFile).toString());
   
   this.populateForm();  
}

//-----------------------------------------------------------------------------
//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
//-----------------------------------------------------------------------------
private void visualize_clustering(Clusterer cls, Instances instances)
{             
    
    if (this.presentedKnowledge==null)
        this.presentedKnowledge=new PresentedKnowledge();
  
    
    Object presentation=null;   
           
   //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++   
   if (cls.getClass().getSimpleName().equals("Cobweb"))
   {
       Cobweb cobweb= (Cobweb) cls; 
       JOptionPane.showMessageDialog(null, "No Visualization is available!");
   }

   //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++   
   else  if (cls.getClass().getSimpleName().equals("EM"))
   {
       EM  em= (EM) cls;      
       JOptionPane.showMessageDialog(null, "No Visualization is available!");
   }

   //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++   
   else  if (cls.getClass().getSimpleName().equals("HierarchicalClusterer"))
   {
       HierarchicalClusterer  hca= (HierarchicalClusterer) cls;
       presentation=ClusteringTools.getClustererVisualization(hca, instances);      
   }
              
   //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++   
   else  if (cls.getClass().getSimpleName().equals("SimpleKMeans"))
   {
       SimpleKMeans  simpleKMeans= (SimpleKMeans) cls;
       presentation=ClusteringTools.getClustererVisualization(simpleKMeans, instances);      
   }
   
   //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++   
   else  if (cls.getClass().getSimpleName().equals("DensityBasedClusterer"))
   {
       DensityBasedClusterer  densityBasedClusterer= (DensityBasedClusterer) cls;
       JOptionPane.showMessageDialog(null, "No Visualization is available!");       
   }
   
   if (defaultPresentationFile.exists())
       Presentation_URL_jTextField.setText(Tools.toURL(defaultPresentationFile).toString());
   
   this.populateForm();  
}


//-----------------------------------------------------------------------------
//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
//-----------------------------------------------------------------------------
private void visualize_regression(Classifier cls)
{             
    
        if (this.presentedKnowledge==null)
            this.presentedKnowledge=new PresentedKnowledge();
  
        Object presentation=null;   
   
   //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
   if (cls.getClass().getSimpleName().equals("LinearRegression"))
   {
    
    Object presentationResults=null;   
    
    if (this.presentedKnowledge==null)
        this.presentedKnowledge=new PresentedKnowledge();          
              
    LinearRegression lr= (LinearRegression) cls;
    
   presentationResults=RegressionTools.getLinearRegressionVisualization(lr.toString());
   presentation_JFrame=(JFrame) presentationResults;
   
   if (defaultPresentationFile.exists())
       Presentation_URL_jTextField.setText(Tools.toURL(defaultPresentationFile).toString());
   
   }
           
   //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++   
   else  if (cls.getClass().getSimpleName().equals("SMOreg"))
   {
           
    Object presentationResults=null;   
    
    if (this.presentedKnowledge==null)
        this.presentedKnowledge=new PresentedKnowledge();          
              
    SMOreg smo= (SMOreg) cls;
    
    presentationResults=RegressionTools.getSMORegressionVisualization(smo.toString());
    presentation_JFrame=(JFrame) presentationResults;
   
    if (defaultPresentationFile.exists())
       Presentation_URL_jTextField.setText(Tools.toURL(defaultPresentationFile).toString());
   
   }
   this.populateForm();  
   
}

//-----------------------------------------------------------------------------
//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
//-----------------------------------------------------------------------------
private void visualize_rulesInduction()
{        
        JOptionPane.showMessageDialog(null, "No Visualization is available!");   


}

//-----------------------------------------------------------------------------
//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
//-----------------------------------------------------------------------------
private void visualize_dimentionalityReduction(File transformedDataFile)
{
         
    Object presentationResults=null;
//   JOptionPane.showMessageDialog(null, "No Visualization is available!");
   
  if (this.presentedKnowledge==null)
        this.presentedKnowledge=new PresentedKnowledge();
  
    presentationResults=DimentionalityReductionTools.getPCAVisualization(transformedDataFile);    
    presentation_JFrame=(JFrame) presentationResults;
    
   if (defaultPresentationFile.exists())
       Presentation_URL_jTextField.setText(Tools.toURL(defaultPresentationFile).toString());
   
   
   
   this.populateForm(); 
     
}

//-----------------------------------------------------------------------------
//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
//-----------------------------------------------------------------------------
private void visualize_featureExtraction (RandomForest rf)
{
   JOptionPane.showMessageDialog(null, "No Visualization is available!");
       Object presentationResults=null;   
    
    if (this.presentedKnowledge==null)
        this.presentedKnowledge=new PresentedKnowledge();          
              
   presentationResults=FeatureAnalysisTools.getRandomForestVisualization(rf.toString());
   presentation_JFrame=(JFrame) presentationResults;
   
   if (defaultPresentationFile.exists())
       Presentation_URL_jTextField.setText(Tools.toURL(defaultPresentationFile).toString());
   
   this.populateForm();  
}

//-----------------------------------------------------------------------------
//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
//-----------------------------------------------------------------------------

    
    private void Build_Visualization_jButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Build_Visualization_jButtonActionPerformed
    this.automated=true;
    this.Automated_jCheckBox.setSelected(true);    
    this.visualize();
        
    }//GEN-LAST:event_Build_Visualization_jButtonActionPerformed

    private void Automated_jCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Automated_jCheckBoxActionPerformed
        if (Automated_jCheckBox.isSelected()) 
            automated=true;
         else
            automated=false;
}//GEN-LAST:event_Automated_jCheckBoxActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
    //if (this.presentedKnowledge!=null)
        this.save_presentation();

    }//GEN-LAST:event_formWindowClosing

        private void View_jButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_View_jButtonActionPerformed
       if (!ModelEvaluation_URL_jTextField.getText().equals(""))
        try {
            Tools.viewAnyFile(Tools.toFile(new URL(ModelEvaluation_URL_jTextField.getText())));
         // TODO add your handling code here:
        } catch (MalformedURLException ex) {
            JOptionPane.showMessageDialog(null, "File URL is invalid ");
            Logger.getLogger(ModelEvaluationForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_View_jButtonActionPerformed

    
private void visualize()
   {
            String graph=null;        
            //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
            if (Technique_jTextPane.getText().toLowerCase().equals(Global.techniques[1].toLowerCase())) 
            {
                
                if (model!=null && model.getOutcomeURL()!=null) 
                {
                    Apriori apriori = (Apriori) Tools.readObjectFromFile(Tools.toFile(model.getOutcomeURL()).getPath());                    
                    this.visualize_association(apriori);
                }
                
            }
            
            //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
            else if (Technique_jTextPane.getText().toLowerCase().equals(Global.techniques[2].toLowerCase())) 
            {
                BayesNet bayesNet =null;                
                
                if (this.presentedKnowledge!=null && presentedKnowledge.getOutcomeURL()!=null)                 
                {
                    if (Tools.toFile(presentedKnowledge.getOutcomeURL()).exists())
                        graph = (String) Tools.readObjectFromFile(Tools.toFile(presentedKnowledge.getOutcomeURL()).getPath());
                    
                    bayesNet = new BayesNet();
                }                                   
                else if (model!=null && model.getOutcomeURL()!=null)                                     
                {
                    if (Tools.toFile(model.getOutcomeURL()).exists())
                        bayesNet = (BayesNet) Tools.readObjectFromFile(Tools.toFile(model.getOutcomeURL()).getPath());
                    try {
                        graph=bayesNet.graph();
                    } catch (Exception ex) {
                        Logger.getLogger(KnowledgePresentationForm.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                
                if (bayesNet!=null && graph!=null)
                   this.visualize_classification(bayesNet,graph);
                
                if (graph!=null)
                   this.savePresentationInFile(graph, defaultPresentationFile);
                
                
            }
            
            //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
            else if (Technique_jTextPane.getText().toLowerCase().equals(Global.techniques[3].toLowerCase())) 
            {
                
                J48 j48 =null;                
                
                if (this.presentedKnowledge!=null && presentedKnowledge.getOutcomeURL()!=null)                 
                {
                    if (Tools.toFile(presentedKnowledge.getOutcomeURL()).exists())
                        graph = (String) Tools.readObjectFromFile(Tools.toFile(presentedKnowledge.getOutcomeURL()).getPath());
                    
                    j48 = new J48();
                }                                   
                else if (model!=null && model.getOutcomeURL()!=null)                                     
                {
                    if (Tools.toFile(model.getOutcomeURL()).exists())
                        j48 = (J48) Tools.readObjectFromFile(Tools.toFile(model.getOutcomeURL()).getPath());
                    try {
                        graph=j48.graph();
                    } catch (Exception ex) {
                        Logger.getLogger(KnowledgePresentationForm.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                
                if (j48!=null && graph!=null)
                   this.visualize_classification(j48,graph);

                if (graph!=null)
                    this.savePresentationInFile(graph, defaultPresentationFile);
                
            }
            
            //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
            else if (Technique_jTextPane.getText().toLowerCase().equals(Global.techniques[4].toLowerCase())) 
            {

                HierarchicalClusterer hierarchicalClusterer =null;                
                Instances instances=null;

               if (model!=null && model.getOutcomeURL()!=null)                                     
                {
                    if (Tools.toFile(model.getOutcomeURL()).exists())
                        hierarchicalClusterer = (HierarchicalClusterer) Tools.readObjectFromFile(Tools.toFile(model.getOutcomeURL()).getPath());
                    
                    try {
                        graph=hierarchicalClusterer.graph();
                    } catch (Exception ex) {
                        Logger.getLogger(KnowledgePresentationForm.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
          
                if (model!=null && 
                    model.getModelEvaluation()!=null &&
                    model.getModelEvaluation().getEvaluationData()!=null &&
                    model.getModelEvaluation().getEvaluationData().getInstances()!=null)
                    
                        instances=model.getModelEvaluation().getEvaluationData().getInstances();
                
                if (hierarchicalClusterer!=null && instances !=null)
                    this.visualize_clustering(hierarchicalClusterer, instances);
                                
                if (graph!=null)
                    this.savePresentationInFile(graph, defaultPresentationFile);
            }
            
            //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
            else if (Technique_jTextPane.getText().toLowerCase().equals(Global.techniques[5].toLowerCase())) 
            {

                if (model!=null && model.getOutcomeURL()!=null) {
                    
                    LinearRegression lr= (LinearRegression) Tools.readObjectFromFile(Tools.toFile(model.getOutcomeURL()).getPath());                    
                    this.visualize_regression(lr);
                                        
                }
            }
            //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
            else if (Technique_jTextPane.getText().toLowerCase().equals(Global.techniques[6].toLowerCase())) 
            {
                MLP mlp =null;                
                
                if (this.presentedKnowledge!=null && presentedKnowledge.getOutcomeURL()!=null)                 
                {
                    if (Tools.toFile(presentedKnowledge.getOutcomeURL()).exists())
                        graph = (presentedKnowledge.getOutcomeURL()).getPath();
                    
                    mlp = new MLP();
                }        
                        
                else if (model!=null && model.getOutcomeURL()!=null)                                     
                {
                    if (Tools.toFile(model.getOutcomeURL()).exists())
                        mlp = (MLP) Tools.readObjectFromFile(Tools.toFile(model.getOutcomeURL()).getPath());
                    graph = mlp.getOutputFile();                
                }
                
                if (mlp!=null && graph!=null)
                {
                   this.visualize_classification(mlp,graph);
                   this.save_MLP_Presentation_InFile( new File(graph));
                }
                 
            }        
            
            //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
            else if (Technique_jTextPane.getText().toLowerCase().equals(Global.techniques[7].toLowerCase())) 
            {

                Clusterer clusterer =null;                
                Instances instances=null;
                
               if (this.presentedKnowledge!=null && presentedKnowledge.getOutcomeURL()!=null)                 
                {
                    if (Tools.toFile(presentedKnowledge.getOutcomeURL()).exists())
                        clusterer = (Clusterer) Tools.readObjectFromFile(Tools.toFile(presentedKnowledge.getOutcomeURL()).getPath());                    
                }        
                        
                else if (model!=null && model.getOutcomeURL()!=null)                                     
                {
                    if (Tools.toFile(model.getOutcomeURL()).exists())
                        clusterer = (Clusterer) Tools.readObjectFromFile(Tools.toFile(model.getOutcomeURL()).getPath());                    
                }
                
                if (model!=null && 
                    model.getModelEvaluation()!=null &&
                    model.getModelEvaluation().getEvaluationData()!=null &&
                    model.getModelEvaluation().getEvaluationData().getInstances()!=null)
                    
                        instances=model.getModelEvaluation().getEvaluationData().getInstances();
       
                if (clusterer!=null && instances !=null)
                    this.visualize_clustering(clusterer, instances);
                                                
                this.savePresentationInFile(clusterer, defaultPresentationFile);
                
            }

            
            //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
            else if (Technique_jTextPane.getText().toLowerCase().equals(Global.techniques[8].toLowerCase())) // pca
            {

                PrincipalComponents pca =null;                
                
               if (this.presentedKnowledge!=null && presentedKnowledge.getOutcomeURL()!=null)                 
                {
                    if (Tools.toFile(presentedKnowledge.getOutcomeURL()).exists())
                        pca = (PrincipalComponents) Tools.readObjectFromFile(Tools.toFile(presentedKnowledge.getOutcomeURL()).getPath());                    
                }        
                        
                else if (model!=null && model.getOutcomeURL()!=null)                                     
                {
                    if (Tools.toFile(model.getOutcomeURL()).exists())
                        pca = (PrincipalComponents) Tools.readObjectFromFile(Tools.toFile(model.getOutcomeURL()).getPath());                    
                }
                                              
                if (model!=null && 
                    model.getModelEvaluation()!=null &&
                    model.getModelEvaluation().getEvaluationData()!=null &&
                    model.getModelEvaluation().getEvaluationData().getInstances()!=null)
                {   
                    File transformedDataFile=model.getTarnsformedData();
                    
                    if (transformedDataFile !=null)
                        this.visualize_dimentionalityReduction(transformedDataFile);                                                                
                }
                this.savePresentationInFile(pca, defaultPresentationFile);
            }            
            
            //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
            else if (Technique_jTextPane.getText().toLowerCase().equals(Global.techniques[9].toLowerCase())) // random forests
            {

                RandomForest randomForest =null;                
                Instances instances=null;
                
               if (this.presentedKnowledge!=null && presentedKnowledge.getOutcomeURL()!=null)                 
                {
                    if (Tools.toFile(presentedKnowledge.getOutcomeURL()).exists())
                        randomForest = (RandomForest) Tools.readObjectFromFile(Tools.toFile(presentedKnowledge.getOutcomeURL()).getPath());                    
                }        
                        
                else if (model!=null && model.getOutcomeURL()!=null)                                     
                {
                    if (Tools.toFile(model.getOutcomeURL()).exists())
                        randomForest = (RandomForest) Tools.readObjectFromFile(Tools.toFile(model.getOutcomeURL()).getPath());                    
                }
                
                if (model!=null && 
                    model.getModelEvaluation()!=null &&
                    model.getModelEvaluation().getEvaluationData()!=null &&
                    model.getModelEvaluation().getEvaluationData().getInstances()!=null)
                    
                        instances=model.getModelEvaluation().getEvaluationData().getInstances();
       
                if (randomForest!=null && instances !=null)
                    this.visualize_featureExtraction(randomForest);
                                                
                this.savePresentationInFile(randomForest, defaultPresentationFile);
                
            }
            
            //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
            else if (Technique_jTextPane.getText().toLowerCase().equals(Global.techniques[10].toLowerCase())) // som reg
            {

                SMOreg smo =null;                
                Instances instances=null;
                
               if (this.presentedKnowledge!=null && presentedKnowledge.getOutcomeURL()!=null)                 
                {
                    if (Tools.toFile(presentedKnowledge.getOutcomeURL()).exists())
                        smo = (SMOreg) Tools.readObjectFromFile(Tools.toFile(presentedKnowledge.getOutcomeURL()).getPath());                    
                }        
                        
                else if (model!=null && model.getOutcomeURL()!=null)                                     
                {
                    if (Tools.toFile(model.getOutcomeURL()).exists())
                        smo = (SMOreg) Tools.readObjectFromFile(Tools.toFile(model.getOutcomeURL()).getPath());                    
                }
                
                if (model!=null && 
                    model.getModelEvaluation()!=null &&
                    model.getModelEvaluation().getEvaluationData()!=null &&
                    model.getModelEvaluation().getEvaluationData().getInstances()!=null)
                    
                        instances=model.getModelEvaluation().getEvaluationData().getInstances();
       
                if (smo!=null && instances !=null)
                    this.visualize_regression(smo);
                                                
                this.savePresentationInFile(smo, defaultPresentationFile);
                
            }
        //*****************************************************************************            
        else  
            {                             
              if (this.presentedKnowledge!=null && presentedKnowledge.getOutcomeURL()!=null)                 
                {
                    if (Tools.toFile(presentedKnowledge.getOutcomeURL()).exists())
                        graph = (presentedKnowledge.getOutcomeURL()).getPath();
                    
                }        
                                        
                if (graph!=null)
                {                   
                   this.visualize_external(graph);
//                   this.savePresentationInFile( new File(graph));
                }
                 
            }                                                                    
            
        //*****************************************************************************
        if (  Global.project.getProcess()!=null &&
                Global.project.getProcess().getResults()!=null &&
                Global.project.getProcess().getResults().getResult(6)!=null && model!=null) {
            Global.project.getProcess().getResults().getModelBuildingResult().setMainDelivery(model);
        }        

}    
    
    
    
//-----------------------------------------------------------------------------    
//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
//                      save prsentation in a file
//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
//-----------------------------------------------------------------------------

private void savePresentationInFile(Object presentationObject, File presentationFile)    
{
 
 if (presentationObject!=null)
 {

    if (presentationFile!=null)
     {                              
       if (presentationFile.exists())
           presentationFile.delete();
    
       Tools.writeObjectToFile(presentationObject, presentationFile.getPath());       
       
       if (presentationFile!=null)
          this.Presentation_URL_jTextField.setText(Tools.toURL(presentationFile).toString());                        
        
       try {                    
           this.presentedKnowledge.setOutcomeURL(new URL(Presentation_URL_jTextField.getText()));
       } catch (MalformedURLException ex) {            
            Logger.getLogger(KnowledgePresentationForm.class.getName()).log(Level.SEVERE, null, ex);}
        
     }
    
                    
    //-------------------------------------------------------------------------    
    this.save_presentation();
    this.clear_presentation();
    this.refresh_presentation();
    this.populate_presentation();
   
    //-------------------------------------------------------------------------    
    if (presentationFile.exists())
        presentationFile.delete();  
}
}

private void savePresentationInFile(File presentationFile)    
{  
    if (presentationFile!=null)
     {                                  
       if (presentationFile!=null)
          this.Presentation_URL_jTextField.setText(Tools.toURL(presentationFile).toString());                        
        
       try {                    
           this.presentedKnowledge.setOutcomeURL(new URL(Presentation_URL_jTextField.getText()));
           
       } catch (MalformedURLException ex) {            
            Logger.getLogger(KnowledgePresentationForm.class.getName()).log(Level.SEVERE, null, ex);}
        
     }
    
                    
    //-------------------------------------------------------------------------    
    this.save_presentation();
    this.clear_presentation();
    this.refresh_presentation();
    this.populate_presentation();
   
    //-------------------------------------------------------------------------    
    if (presentationFile.exists())
        presentationFile.delete();  
}    


private void save_MLP_Presentation_InFile(File presentationFile)    
{  
    if (presentationFile!=null)
     {                                  
       if (presentationFile!=null)
          this.Presentation_URL_jTextField.setText(Tools.toURL(presentationFile).toString());                        
        
       try {                    
           this.presentedKnowledge.setOutcomeURL(new URL(Presentation_URL_jTextField.getText()));
           
       } catch (MalformedURLException ex) {            
            Logger.getLogger(KnowledgePresentationForm.class.getName()).log(Level.SEVERE, null, ex);}
        
     }
    
                    
    //-------------------------------------------------------------------------    
    this.save_presentation();
    this.clear_presentation();
    this.refresh_presentation();
    this.populate_presentation();
   
//-------------------------------------------------------------------------    
//    if (presentationFile.exists())
//        presentationFile.delete();  
}


//-----------------------------------------------------------------------------
//*****************************************************************************
//                     Resources Funds Calculations
//*****************************************************************************
//-----------------------------------------------------------------------------
private void addResourceFunds()
{
if (Global.project!=null &&
                Global.project.getSupplements()!=null &&
                Global.project.getSupplements().getManagement()!=null &&
                Global.project.getSupplements().getManagement().getConstraint()!=null)
                {
                      Double curruntUsedFunds=Global.project.getSupplements().getManagement().getConstraint().getUsedFunds();
                      Double totalFunds=Global.project.getSupplements().getManagement().getConstraint().getTotalFunds();

                      Double resourceCost=0.0;

                      if (this.resource!=null)
                              resourceCost= this.resource.getCost();

                      Double newUsedFunds=curruntUsedFunds+resourceCost;

                      if (newUsedFunds>totalFunds)
                          JOptionPane.showMessageDialog(null, "Exceeded the project Total Funds,"
                                                           +" Used Funds = "+newUsedFunds
                                                           +" Project Planned Total Funds = "+totalFunds);

                     Global.project.getSupplements().getManagement().getConstraint().setUsedFunds(newUsedFunds);

                }
}

//*****************************************************************************
private void deductResourceFunds()
{
 //------------------------------------------------------------------
            if (Global.project!=null &&
                Global.project.getSupplements()!=null &&
                Global.project.getSupplements().getManagement()!=null &&
                Global.project.getSupplements().getManagement().getConstraint()!=null)
                {
                      Double curruntUsedFunds=Global.project.getSupplements().getManagement().getConstraint().getUsedFunds();
                      Double totalFunds=Global.project.getSupplements().getManagement().getConstraint().getTotalFunds();

                      Double resourceCost=0.0;

                      if (this.resource!=null)
                              resourceCost= this.resource.getCost();

                      Double newUsedFunds=curruntUsedFunds-resourceCost;

                      if (newUsedFunds>totalFunds)
                          JOptionPane.showMessageDialog(null, "Exceeded the project Total Funds,"
                                                           +" Used Funds = "+newUsedFunds
                                                           +" Project Planned Total Funds = "+totalFunds);

                     Global.project.getSupplements().getManagement().getConstraint().setUsedFunds(newUsedFunds);

                }
}


//SSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSS
//****************************************************************************
//                              Save Plan
//****************************************************************************
//SSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSS

 private void save_presentation()
 {        
    if (this.presentedKnowledge == null)
        this.presentedKnowledge = new PresentedKnowledge();

    // ---------------------------------------------------------------
    
    if (this.Automated_jCheckBox.isSelected() )    
        this.presentedKnowledge.setAutomated(true);    
    else
        this.presentedKnowledge.setAutomated(false);

    if (this.Method_jComboBox.getSelectedIndex() > 0) {
        this.presentedKnowledge.setMethodIndex(this.Method_jComboBox.getSelectedIndex());}

    if (this.Technique_jComboBox.getSelectedIndex() > 0)
        this.presentedKnowledge.setTechniqueIndex(this.Technique_jComboBox.getSelectedIndex());

    try {

        if (!this.Presentation_URL_jTextField.getText().equals(""))
            this.presentedKnowledge.setURL(new URL(this.Presentation_URL_jTextField.getText()));
       else
           this.presentedKnowledge.setURL(null);

    } catch (MalformedURLException ex) {
        JOptionPane.showMessageDialog(null, "Wrong URL Format... use file:/ for local files!");}

    if (this.Presentation_Description_jTextPane != null && !this.Presentation_Description_jTextPane.getText().equals(""))
        this.presentedKnowledge.setDescription(this.Presentation_Description_jTextPane.getText());

    this.presentedKnowledge.setFinalSelection(this.FinalSelection_jCheckBox.isSelected());

    //if (this.knowledgePresentation.isFinalSelection())
        this.presentedKnowledge.deliverAsMain();
    //else
        //this.knowledgePresentation.deliverAsSupplement();

 //this.phase.save();
 }
//PPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPP
//                         Populate Plan Form
//PPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPP
 private void populate_presentation()
{    
    if ( this.presentedKnowledge !=null && this.presentedKnowledge.getPresentationMethod()!=null)
        this.Method_jComboBox.setSelectedIndex(this.presentedKnowledge.getPresentationMethod().ordinal()+1);
    else
        this.Method_jComboBox.setSelectedIndex(0);

    if (this.presentedKnowledge!=null)
        this.Automated_jCheckBox.setSelected(this.presentedKnowledge.isAutomated());
    else
        this.Automated_jCheckBox.setSelected(false);
    
    
    if ( this.presentedKnowledge !=null && this.presentedKnowledge.getVisualisationTechnique()!=null)
        this.Technique_jComboBox.setSelectedIndex(this.presentedKnowledge.getVisualisationTechnique().ordinal()+1);
    else
        this.Technique_jComboBox.setSelectedIndex(0);

    if ( this.presentedKnowledge !=null && this.presentedKnowledge.getURL()!=null)
        this.Presentation_URL_jTextField.setText(this.presentedKnowledge.getURL().toString());
    else
        this.Presentation_URL_jTextField.setText("");

    if ( this.presentedKnowledge !=null && this.presentedKnowledge.getDescription()!=null)
        this.Presentation_Description_jTextPane.setText(this.presentedKnowledge.getDescription().toString());
    else
        this.Presentation_Description_jTextPane.setText("");

    if (this.presentedKnowledge!=null)
        this.FinalSelection_jCheckBox.setSelected(this.presentedKnowledge.isFinalSelection());
    else
        this.FinalSelection_jCheckBox.setSelected(false);
    
    //--------------------------------------------------------------------
    if (Global.project!=null && Global.project.getSupplements()!=null &&
        Global.project.getSupplements().getManagement()!=null &&
        Global.project.getSupplements().getManagement().getConstraint()!=null)
    {
        this.RemainingTime_jTextField.setText(Global.project.getSupplements().getManagement().getConstraint().getRemainingTime()+"");
        this.RemainingFunds_jTextField.setText(Global.project.getSupplements().getManagement().getConstraint().getRemainingFunds()+"");
    }
    this.populate_evaluation();
    this.populate_model();
    this.refresh_presentation_details();
    this.populateForm();

}


//PPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPP
//                         Populate Evaluation Form
//PPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPP
 private void populate_evaluation()
{

if (this.modelEvaluation!=null)
{
    this.ModelOverFit_jCheckBox.setSelected(this.modelEvaluation.isModelOverFit());

    this.ModelUnderFit_jCheckBox.setSelected(this.modelEvaluation.isModelUnderFit());

    this.ModelPerformanceAcceptable_jCheckBox.setSelected(this.modelEvaluation.isModelPerformanceAcceptable());
    
    if (this.modelEvaluation.getOutcomeURL()!=null)
        this.ModelEvaluation_URL_jTextField.setText(this.modelEvaluation.getOutcomeURL().toString());         
    else
        this.ModelEvaluation_URL_jTextField.setText("");

    
 }

this.Procedures_jList.setModel(this.getEvaluationProcedures());
this.Evaluation_Measurment_Outcomes_jList.setModel(this.getEvaluationOutcomes());

}
 
 
 
//PPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPP
//                         Populate Model Form
//PPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPP
 private void populate_model()
 {
  if (this.model!=null)
  {    
      this.Supervised_jCheckBox.setSelected(this.model.isSupervised());
    
      if (this.model.getTechnique()!=null)
            this.Technique_jTextPane.setText(this.model.getTechnique());
       else
           this.Technique_jTextPane.setText("");

       if (this.model.getOutcomeURL()!=null)
            this.Model_URL_jTextField.setText(this.model.getOutcomeURL().toString());
       else
           this.Model_URL_jTextField.setText("");

        if (this.model.getAlgorithm()!=null)
            this.Algorithm_jTextPane.setText(this.model.getAlgorithm());
        else
           this.Algorithm_jTextPane.setText("");

         //--------------------------------------------------------------------
        if (this.model!=null&& this.model.getDataMiningApproach()!=null)
             this.Approaches_jComboBox.setSelectedIndex(this.model.getDataMiningApproach().ordinal()+1);
        else
             this.Approaches_jComboBox.setSelectedIndex(0);

         if (this.model!=null&& this.model.getDataMiningGoal()!=null)
             this.Goals_jComboBox.setSelectedIndex(this.model.getDataMiningGoal().ordinal()+1);
         else
             this.Goals_jComboBox.setSelectedIndex(0);

         if (this.model!=null&& this.model.getDataMiningTask()!=null)
             this.Tasks_jComboBox.setSelectedIndex(this.model.getDataMiningTask().ordinal()+1);
         else
             this.Tasks_jComboBox.setSelectedIndex(0);

         this.Supervised_jCheckBox.setSelected(this.model.isSupervised());


         //--------------------------------------------------------------------

        if ( this.model.getPerformance()!=null
          && this.model.getPerformance().getResultSummary()!=null)
            this.ResultSummary_jTextPane.setText(this.model.getPerformance().getResultSummary());
        else
            this.ResultSummary_jTextPane.setText("");


         if (this.model!=null && this.model.getRequirements()!=null)
         {
            this.ConsumedTimeDuration_jTextField.setText(this.model.getBuildDuration()+"");
         }
     }
  
    this.Model_MeasuredOutcomes_jList.setModel(this.getModelOutcomes());
 }



 //RRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRR
//****************************************************************************
//                              Refresh
//****************************************************************************
//RRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRR

 private void refresh_presentation()
 {

//*****************************************************************************
Result<PresentedKnowledge> knowledgePresentationResult=new Result<PresentedKnowledge>();
//*****************************************************************************

if (Global.currentPhase!=null &&
    Global.currentPhase.getResult()!=null)
{
    
    knowledgePresentationResult=Global.currentPhase.getResult();
       if (knowledgePresentationResult.getSupplementDeliveries()!=null &&
             knowledgePresentationResult.getSupplementDeliveries().getFirst()!=null)
        this.presentedKnowledge= (PresentedKnowledge) knowledgePresentationResult.getSupplementDeliveries().getFirst();          
}

else
 this.presentedKnowledge=new PresentedKnowledge();

//*****************************************************************************
 if (  Global.project.getProcess()!=null &&
    Global.project.getProcess().getResults()!=null &&
    Global.project.getProcess().getResults().getResult(1)!=null &&
    Global.project.getProcess().getResults().getResult(1)!=null)
{
    if (Global.project.getProcess().getResults().getResult(1).getMainDelivery()!=null)
    {
        DataMiningObjective processObjective=(DataMiningObjective) Global.project.getProcess().getResults().getResult(1).getMainDelivery();
        Objectives_jTextArea.setText(processObjective.toMyString());
    }
    else if (Global.project.getProcess().getResults().getResult(1).getSupplementDeliveries()!=null &&
            Global.project.getProcess().getResults().getResult(1).getSupplementDeliveries().getLast()!=null)
    {
       DataMiningObjective processObjective=(DataMiningObjective) Global.project.getProcess().getResults().getResult(1).getSupplementDeliveries().getLast();
        Objectives_jTextArea.setText(processObjective.toMyString());
    }
}

 //*****************************************************************************
 if (  Global.project.getProcess()!=null &&
    Global.project.getProcess().getResults()!=null &&
    Global.project.getProcess().getResults().getResult(6)!=null &&
    Global.project.getProcess().getResults().getResult(6)!=null)
{
    if (Global.project.getProcess().getResults().getResult(6).getMainDelivery()!=null)
    {
        this.model=(Model) Global.project.getProcess().getResults().getResult(6).getMainDelivery();
    }
    else if (Global.project.getProcess().getResults().getResult(6).getSupplementDeliveries()!=null &&
            Global.project.getProcess().getResults().getResult(6).getSupplementDeliveries().getLast()!=null)
            this.model=(Model) Global.project.getProcess().getResults().getResult(6).getSupplementDeliveries().getLast();
}

//*****************************************************************************
if (    Global.project.getProcess()!=null &&
        Global.project.getProcess().getResults()!=null &&
        Global.project.getProcess().getResults().getResult(7)!=null &&
        Global.project.getProcess().getResults().getResult(7).getSupplementDeliveries()!=null)
    {
    if (Global.project.getProcess().getResults().getResult(7).getMainDelivery()!=null)
        this.modelEvaluation=(ModelEvaluation) Global.project.getProcess().getResults().getResult(7).getMainDelivery();
    else
        if (Global.project.getProcess().getResults().getResult(7).getSupplementDeliveries().getLast()!=null)
        {
            this.modelEvaluation= (ModelEvaluation) Global.project.getProcess().getResults().getResult(7).getSupplementDeliveries().getLast();
            this.populate_presentation();
        }
    }

 this.refresh_presentation_details();
 this.populate_presentation();
}


 //RRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRR
 //                         Refresh Plan Details
 //RRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRR

 private void refresh_presentation_details()
 {
 this.Resources_jList.setModel(this.getProjectResourcesModel());
 this.Pres_Req_Resources_jList.setModel(this.getPresentationUsedResourcesModel());

 this.Model_MeasuredOutcomes_jList.setModel(this.getModelPerformanceOutcomes());
 this.Model_Used_Resources_jList.setModel(this.getModelUsedResourcesModel());
 this.Model_Selected_Standards_jList.setModel(this.getModelSelectedStandardsModel());
 this.Procedures_jList.setModel(this.getEvaluationProcedures());

 this.populateForm();
 }

//DDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDD
//****************************************************************************
//                              Delete Plan
//DDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDD
//****************************************************************************
 private void delete_presentation()
    {

    if (Global.currentPhase != null &&
    Global.currentPhase.getResult()!=null&&
    Global.currentPhase.getResult().getSupplementDeliveries()!=null  &&
    Global.currentPhase.getResult().getSupplementDeliveries().getDeliveriesList().size()>0)
    if (this.presentedKnowledge!=null)
    {
      int index= Global.currentPhase.getResult().getSupplementDeliveries().getDeliveriesList().indexOf(this.presentedKnowledge);
          if (Global.currentPhase.getResult().getSupplementDeliveries().delete(this.presentedKnowledge)>-1);
        {
            if (Global.currentPhase.getResult().getSupplementDeliveries().getDeliveriesList().size()>0)
            {
                if (index==0 && Global.currentPhase.getResult().getSupplementDeliveries().getDelivery(index)!=null)
                    this.presentedKnowledge = (PresentedKnowledge) Global.currentPhase.getResult().getSupplementDeliveries().getDelivery(index);
                else if(index>0)
                    this.presentedKnowledge = (PresentedKnowledge) Global.currentPhase.getResult().getSupplementDeliveries().getDelivery(index-1);
                /*
                if (knowledgePresentation.isFinalSelection()||index==0)
                    knowledgePresentation.deliverAsMain();
                else
                    knowledgePresentation.deliverAsSupplement();
                */
                //this.refresh_plan_details();
                this.refresh_presentation_details();
                this.populate_presentation();
            }
            else
            {
                presentedKnowledge.UnDeliver();
                this.clear_presentation();
            }
        }
     this.phase.save();
     //------------------------------------------------------------------------
     // to disable the details if the master is not there
     //------------------------------------------------------------------------
     if (this.phase.getPlanning()!=null && this.phase.getPlanning().getPlanList().size()==0)
     {
         this.phase.setPlanning(null);
         this.populateForm();
      }
 }
 }



//CCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCC
//****************************************************************************
//                     Clear Resource Plan & Resource
//****************************************************************************
 //CCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCC

 private void clear_presentation()
{
this.presentedKnowledge=null;
clear_presentation_details();
clear_presentationForm();
 }

 private void clear_presentation_details()
{
     this.resource=null;
     this.measure=null;
     this.measuredOutcome=null;
     this.standard=null;

 this.Resources_jList.setModel(this.getProjectResourcesModel());
 this.Pres_Req_Resources_jList.setModel(this.getPresentationUsedResourcesModel());

 this.Model_MeasuredOutcomes_jList.setModel(this.getModelPerformanceOutcomes()); 
 this.Model_Used_Resources_jList.setModel(this.getModelUsedResourcesModel());
 this.Model_Selected_Standards_jList.setModel(this.getModelSelectedStandardsModel());
 
 this.Evaluation_Measurment_Outcomes_jList.setModel(this.getEvaluationOutcomes()); 
 
 }

 
 
 //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
 private void clear_presentationForm()
 {
    this.Presentation_Description_jTextPane.setText("");
    this.Presentation_URL_jTextField.setText("");
    this.Method_jComboBox.setSelectedIndex(0);
    this.Technique_jComboBox.setSelectedIndex(0);
    this.Technique_jComboBox.setSelectedIndex(0);
    this.Automated_jCheckBox.setSelected(false);
     //-----------------------------------------------------------------------
     this.Presentation_URL_jTextField.setText("");
     this.Technique_jTextPane.setText("");
     this.Algorithm_jTextPane.setText("");           
     //--------------------------------------------------------------------
     this.Supervised_jCheckBox.setSelected(false);
     this.FinalSelection_jCheckBox.setSelected(false);
    //--------------------------------------------------------------------
    this.Approaches_jComboBox.setSelectedIndex(0);
    this.Goals_jComboBox.setSelectedIndex(0);
    this.Tasks_jComboBox.setSelectedIndex(0);
    //
    this.populateForm();
 }

//FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF
//****************************************************************************
//                            First & Last Plan
//****************************************************************************
//FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF

 private void first_presentation()
    {
     if (Global.currentPhase.getResult()!=null && 
         Global.currentPhase.getResult().getSupplementDeliveries()!=null &&
         Global.currentPhase.getResult().getSupplementDeliveries().getFirst()!=null)
     {
         this.save_presentation();
         this.presentedKnowledge=(PresentedKnowledge) Global.currentPhase.getResult().getSupplementDeliveries().getFirst();
         //
         this.clear_presentation_details();
         //
         this.refresh_presentation_details();
         //
         this.populate_presentation();
      }

       // this.refresh_Resource(this.thisResource);
 }

 //****************************************************************************
 private void last_presentation()
 {
if (Global.currentPhase.getResult()!=null &&
         Global.currentPhase.getResult().getSupplementDeliveries()!=null &&
         Global.currentPhase.getResult().getSupplementDeliveries().getLast()!=null)
    {
        this.save_presentation();
        this.presentedKnowledge=(PresentedKnowledge) Global.currentPhase.getResult().getSupplementDeliveries().getLast();
        this.clear_presentation_details();
        this.refresh_presentation_details();
        //
        this.populate_presentation();
  }    
 }


//NNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNN
//****************************************************************************
//                                Next Plan
//****************************************************************************
//NNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNN
 private void next_presentation()
    {
      
            if (this.presentedKnowledge!=null)
            {
            if (Global.currentPhase.getResult()!=null &&
                Global.currentPhase.getResult().getSupplementDeliveries()!=null &&
                Global.currentPhase.getResult().getSupplementDeliveries().getNext(presentedKnowledge)!=null
                )
                {
                    this.save_presentation();
                    this.presentedKnowledge=(PresentedKnowledge) Global.currentPhase.getResult().getSupplementDeliveries().getNext(this.presentedKnowledge);
                    this.clear_presentation_details();
                    this.refresh_presentation_details();
                    //
                    this.populate_presentation();
                }
            }      
        //else
            //System.out.print("No Phase Performing is found ");
    }

//PPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPP
//****************************************************************************
//                     Previous Plan& Resource
//****************************************************************************
//PPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPP
 private void previous_presentation()
    {

            if (this.presentedKnowledge!=null)
            {
            if (Global.currentPhase.getResult()!=null &&
                Global.currentPhase.getResult().getSupplementDeliveries()!=null &&
                Global.currentPhase.getResult().getSupplementDeliveries().getPrevious(presentedKnowledge)!=null
                )
                {
                    this.save_presentation();
                    this.presentedKnowledge=(PresentedKnowledge) Global.currentPhase.getResult().getSupplementDeliveries().getPrevious(this.presentedKnowledge);
                    this.clear_presentation_details();
                    this.refresh_presentation_details();
                    //
                    this.populate_presentation();
                }
            }
        //else
            //System.out.print("No Phase Performing is found ");
    }

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    if (Global.project == null) 
                        Global.project = new Project();

                    if (Global.project.getProcess() == null) 
                        Global.project.setProcess(new Process());
                     
                    Global.project.getProcess().setVersion(1);

                    if (Global.currentPhase==null)
                            Global.currentPhase=new Phase("ObjectivesDefinition");
                    
                    new KnowledgePresentationForm().setVisible(true);
                } catch (IOException ex) {
                    Logger.getLogger(KnowledgePresentationForm.class.getName()).log(Level.SEVERE, null, ex);
                } catch (Exception ex) {
                    Logger.getLogger(KnowledgePresentationForm.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Add_jButton1;
    private javax.swing.JTextField Algorithm_jTextPane;
    private javax.swing.JComboBox Approaches_jComboBox;
    private javax.swing.JCheckBox Automated_jCheckBox;
    private javax.swing.JButton Browse_jButton;
    private javax.swing.JButton Build_Visualization_jButton;
    private javax.swing.JTextField ConsumedTimeDuration_jTextField;
    private javax.swing.JLabel ConsumedTime_jLabel1;
    private javax.swing.JDesktopPane Control_jDesktopPane;
    private javax.swing.JButton Delete_jButton;
    private javax.swing.JList Evaluation_Measurment_Outcomes_jList;
    private javax.swing.JTextArea Evaluation_Outcomes_Details_jTextArea;
    private javax.swing.JCheckBox FinalSelection_jCheckBox;
    private javax.swing.JButton First_jButton;
    private javax.swing.JComboBox Goals_jComboBox;
    private javax.swing.JDesktopPane Justification_Source_jDesktopPane;
    private javax.swing.JButton KnowledgeView_jButton;
    private javax.swing.JButton Last_jButton;
    private javax.swing.JComboBox Method_jComboBox;
    private javax.swing.JTextField ModelEvaluation_URL_jTextField;
    private javax.swing.JCheckBox ModelOverFit_jCheckBox;
    private javax.swing.JCheckBox ModelPerformanceAcceptable_jCheckBox;
    private javax.swing.JLabel ModelPerformance_jLabel;
    private javax.swing.JCheckBox ModelUnderFit_jCheckBox;
    private javax.swing.JButton ModelView_jButton;
    private javax.swing.JList Model_MeasuredOutcomes_jList;
    private javax.swing.JList Model_Selected_Standards_jList;
    private javax.swing.JTextField Model_URL_jTextField;
    private javax.swing.JList Model_Used_Resources_jList;
    private javax.swing.JDesktopPane Navigation_jDesktopPane;
    private javax.swing.JButton New_jButton1;
    private javax.swing.JDesktopPane New_jDesktopPane;
    private javax.swing.JButton Next_jButton;
    private javax.swing.JTextArea Objectives_jTextArea;
    private javax.swing.JTextArea Performed_Outcomes_Details_jTextArea;
    private javax.swing.JPanel Performed_Plan_jPanel;
    private javax.swing.JTabbedPane Performing_Details_TabbedPane;
    private javax.swing.JDesktopPane Performing_Details_jDesktopPane_jDesktopPane;
    private javax.swing.JLayeredPane Performing_jLayeredPane;
    private javax.swing.JLabel Planning_CustomisedPlanItem_jLabel;
    private javax.swing.JDesktopPane Planning_CustomisedPlan_jDesktopPane;
    private javax.swing.JDesktopPane Planning_Objectives_Control_jDesktopPane1;
    private javax.swing.JLabel Planning_Planner_Name_jLabel12;
    private javax.swing.JLabel Planning_Planner_Name_jLabel13;
    private javax.swing.JLabel Planning_Planner_Name_jLabel14;
    private javax.swing.JLabel Planning_Planner_Role_jLabel1;
    private javax.swing.JDesktopPane Planning_Planner_jDesktopPane1;
    private javax.swing.JDesktopPane Planning_Resource_jDesktopPane;
    private javax.swing.JLabel Prerequisite_Source_ExternalSource_URL_jLabel4;
    private javax.swing.JList Pres_Req_Resources_jList;
    private javax.swing.JButton Presentation_Browse_jButton;
    private javax.swing.JTextPane Presentation_Description_jTextPane;
    private javax.swing.JTextField Presentation_URL_jTextField;
    private javax.swing.JButton Previous_jButton;
    private javax.swing.JList Procedures_jList;
    private javax.swing.JLabel Project_Constraint_RemainingDuration_jLabel1;
    private javax.swing.JLabel Project_Constraint_RemainingFunds_jLabel1;
    private javax.swing.JButton Refresh_jButton;
    private javax.swing.JTextField RemainingFunds_jTextField;
    private javax.swing.JTextField RemainingTime_jTextField;
    private javax.swing.JDesktopPane Reporting_Customised_jDesktopPane3;
    private javax.swing.JDesktopPane Reporting_Customised_jDesktopPane4;
    private javax.swing.JDesktopPane Reporting_Customised_jDesktopPane5;
    private javax.swing.JDesktopPane Reporting_Customised_jDesktopPane6;
    private javax.swing.JDesktopPane Reporting_Customised_jDesktopPane8;
    private javax.swing.JButton Resource_Edit_jButton;
    private javax.swing.JDesktopPane Resource_jDesktopPane1;
    private javax.swing.JButton Resources_Select_jButton1;
    private javax.swing.JButton Resources_UnSelect_jButton1;
    private javax.swing.JList Resources_jList;
    private javax.swing.JTextPane ResultSummary_jTextPane;
    private javax.swing.JButton Save_jButton;
    private javax.swing.JCheckBox Supervised_jCheckBox;
    private javax.swing.JComboBox Tasks_jComboBox;
    private javax.swing.JComboBox Technique_jComboBox;
    private javax.swing.JDesktopPane Technique_jDesktopPane;
    private javax.swing.JTextPane Technique_jTextPane;
    private javax.swing.JLabel URL_jLabel;
    private javax.swing.JButton View_jButton;
    private javax.swing.JLabel algorithm_jLabel;
    private javax.swing.JLabel goal_jLabel;
    private javax.swing.JButton jButton1;
    private javax.swing.JDesktopPane jDesktopPane45;
    private javax.swing.JDesktopPane jDesktopPane50;
    private javax.swing.JDesktopPane jDesktopPane51;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane25;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane69;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane70;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JLabel task_jLabel;
    // End of variables declaration//GEN-END:variables


//PPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPP
//****************************************************************************
//                             Populate
//****************************************************************************
//PPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPP

private void populateForm()
{
            this.repaint();
            this.pack();
 }

//COMPOCOMPOCOMPOCOMPOCOMPOCOMPOCOMPOCOMPOCOMPOCOMPOCOMPOCOMPOCOMPOCOMPOCOMPOC
//                       Get Project Remeasures Model
//COMPOCOMPOCOMPOCOMPOCOMPOCOMPOCOMPOCOMPOCOMPOCOMPOCOMPOCOMPOCOMPOCOMPOCOMPOC
public  DefaultComboBoxModel getModelOutcomes()
{
 //private MeasurmentOutcome expectedOutcome =null;
  DefaultComboBoxModel dcm=null;
  ArrayList<MeasurmentOutcome> expectedOutcomesList=new ArrayList<MeasurmentOutcome>();
  String[] expectedOutcomesArraythisNames = null;
  if (this.model!=null &&
//          this.model.getSuccessCriteria()!=null &&
      this.model.getPerformance()!=null)
  {
      if ( this.model.getPerformance().getOutcomesList().size()>0)
      {
        expectedOutcomesList=this.model.getPerformance().getOutcomesList();
        int size=expectedOutcomesList.size();
        expectedOutcomesArraythisNames = new String[size];
        int i=0;
        for (Object o:expectedOutcomesList)
            {
            String result="";
            MeasurmentOutcome  measurmentOutcome=(MeasurmentOutcome) o;

                  result=measurmentOutcome.toString();

              expectedOutcomesArraythisNames[i]=result;
              i=i+1;
              //--------------------------------------------------------------
             }
        dcm=new DefaultComboBoxModel(expectedOutcomesArraythisNames);
        return dcm;
        }
     else
      {
        String thisNames[] ={"<No expected outcome selected yet>"};
        dcm=new DefaultComboBoxModel(thisNames);
        return dcm;
      }
  }
 else
    {
    String thisNames[] ={"<No expected outcome selected yet>"};
    dcm=new DefaultComboBoxModel(thisNames);
     return dcm;
    }
 }


//COMPOCOMPOCOMPOCOMPOCOMPOCOMPOCOMPOCOMPOCOMPOCOMPOCOMPOCOMPOCOMPOCOMPOCOMPOC
//                       Get Project Remeasures Model
//COMPOCOMPOCOMPOCOMPOCOMPOCOMPOCOMPOCOMPOCOMPOCOMPOCOMPOCOMPOCOMPOCOMPOCOMPOC
public  DefaultComboBoxModel getEvaluationOutcomes()
{
  DefaultComboBoxModel dcm=null;
  ArrayList<MeasurmentOutcome> expectedOutcomesList=new ArrayList<MeasurmentOutcome>();
  String[] expectedOutcomesArraythisNames = null;
  if (this.modelEvaluation!=null && this.modelEvaluation.getEvaluatedPerformance()!=null)
  {
      if ( this.modelEvaluation.getEvaluatedPerformance().getOutcomesList().size()>0)
      {
        expectedOutcomesList=this.modelEvaluation.getEvaluatedPerformance().getOutcomesList();
        int size=expectedOutcomesList.size();
        expectedOutcomesArraythisNames = new String[size];
        
        int i=0;
        for (Object o:expectedOutcomesList)
            {
                String result="";
                MeasurmentOutcome  measurmentOutcome=(MeasurmentOutcome) o;
                result=measurmentOutcome.toString();
                expectedOutcomesArraythisNames[i]=result;
                i=i+1;               
             }
        
        dcm=new DefaultComboBoxModel(expectedOutcomesArraythisNames);
        return dcm;
        }
     else
      {
        String thisNames[] ={"<No expected outcome selected yet>"};
        dcm=new DefaultComboBoxModel(thisNames);
        return dcm;
      }
  }
 else
    {
    String thisNames[] ={"<No expected outcome selected yet>"};
    dcm=new DefaultComboBoxModel(thisNames);
     return dcm;
    }
 }










//COMPOCOMPOCOMPOCOMPOCOMPOCOMPOCOMPOCOMPOCOMPOCOMPOCOMPOCOMPOCOMPOCOMPOCOMPOC
//                       Get Project Resources Model
//COMPOCOMPOCOMPOCOMPOCOMPOCOMPOCOMPOCOMPOCOMPOCOMPOCOMPOCOMPOCOMPOCOMPOCOMPOC
public  DefaultComboBoxModel getModelSelectedStandardsModel()
{
  DefaultComboBoxModel dcm=null;
  ArrayList<Standard> selectedStandardsList=new ArrayList<Standard>();
  String[] sourcesArrayNames = null;
  if (this.model!=null && this.model.getStandards()!=null)
  {
      if (this.model.getStandards().getStandardsList().size()>0)
      {
        selectedStandardsList=this.model.getStandards().getStandardsList();
        int size=selectedStandardsList.size();
        sourcesArrayNames = new String[size];
        int i=0;
        for (Object o:selectedStandardsList)
            {
             String result="";
              Standard thisStandard=(Standard) o;
              result=thisStandard.toString();
              sourcesArrayNames[i]=result;
              i=i+1;
             }
        dcm=new DefaultComboBoxModel(sourcesArrayNames);
        return dcm;
        }
     else
      {
        String names[] ={"<No source selected yet>"};
        dcm=new DefaultComboBoxModel(names);
        return dcm;
      }
  }
 else
    {
    String names[] ={"<No source selected yet>"};
    dcm=new DefaultComboBoxModel(names);
     return dcm;
    }
 }



//COMPOCOMPOCOMPOCOMPOCOMPOCOMPOCOMPOCOMPOCOMPOCOMPOCOMPOCOMPOCOMPOCOMPOCOMPOC
//                       Get Project Remeasures Model
//COMPOCOMPOCOMPOCOMPOCOMPOCOMPOCOMPOCOMPOCOMPOCOMPOCOMPOCOMPOCOMPOCOMPOCOMPOC
public  DefaultComboBoxModel getModelPerformanceOutcomes()
{

 //private MeasurmentOutcome expectedOutcome =null;
  DefaultComboBoxModel dcm=null;
  ArrayList<MeasurmentOutcome> expectedOutcomesList=new ArrayList<MeasurmentOutcome>();
  String[] expectedOutcomesArrayNames = null;
  if (this.model!=null &&
//          this.model.getSuccessCriteria()!=null &&
      this.model.getPerformance()!=null)
  {
      if ( this.model.getPerformance().getOutcomesList().size()>0)
      {
        expectedOutcomesList=this.model.getPerformance().getOutcomesList();
        int size=expectedOutcomesList.size();
        expectedOutcomesArrayNames = new String[size];
        int i=0;
        for (Object o:expectedOutcomesList)
            {
            String result="";
            MeasurmentOutcome  measurmentOutcome=(MeasurmentOutcome) o;

                  result=measurmentOutcome.toString();

              expectedOutcomesArrayNames[i]=result;
              i=i+1;
              //--------------------------------------------------------------
             }
        dcm=new DefaultComboBoxModel(expectedOutcomesArrayNames);
        return dcm;
        }
     else
      {
        String names[] ={"<No expected outcome selected yet>"};
        dcm=new DefaultComboBoxModel(names);
        return dcm;
      }
  }
 else
    {
    String names[] ={"<No expected outcome selected yet>"};
    dcm=new DefaultComboBoxModel(names);
     return dcm;
    }
 }

//COMPOCOMPOCOMPOCOMPOCOMPOCOMPOCOMPOCOMPOCOMPOCOMPOCOMPOCOMPOCOMPOCOMPOCOMPOC
//                       Get Planned Resources Model
//COMPOCOMPOCOMPOCOMPOCOMPOCOMPOCOMPOCOMPOCOMPOCOMPOCOMPOCOMPOCOMPOCOMPOCOMPOC
public  DefaultComboBoxModel getModelUsedResourcesModel()
{
  DefaultComboBoxModel dcm=null;
  ArrayList<Resource> resourcesList=new ArrayList<Resource>();
  String[] resourcesArrayNames = null;

 if (this.model!=null &&
//     this.model.getFeasibility()!=null &&
     this.model.getRequirements()!=null)

  {
      if (this.model.getRequirements().getResourcesList().size()>0)
      {
        resourcesList=this.model.getRequirements().getResourcesList();
        int size=resourcesList.size();
        resourcesArrayNames = new String[size];
        int i=0;
        for (Object o:resourcesList)
            {
              Resource thisResource=(Resource) o;
              String result="";
              if (thisResource.getResourceType()!=null)
                  result=thisResource.getResourceType().toString()+"  ";
              if (thisResource.getDescription()!=null)
                   result=result+ thisResource.getDescription();
              resourcesArrayNames[i]=result;
              i=i+1;
             }
        dcm=new DefaultComboBoxModel(resourcesArrayNames);
        return dcm;
        }
     else
      {
        String names[] ={"<No selected resource yet>"};
        dcm=new DefaultComboBoxModel(names);
        return dcm;
      }
  }
 else
    {
    String names[] ={"<No selected resource yet>"};
    dcm=new DefaultComboBoxModel(names);
     return dcm;
    }
 }


//COMPOCOMPOCOMPOCOMPOCOMPOCOMPOCOMPOCOMPOCOMPOCOMPOCOMPOCOMPOCOMPOCOMPOCOMPOC
//                       Get Planned Resources PresentedKnowledge
//COMPOCOMPOCOMPOCOMPOCOMPOCOMPOCOMPOCOMPOCOMPOCOMPOCOMPOCOMPOCOMPOCOMPOCOMPOC
public  DefaultComboBoxModel getPresentationUsedResourcesModel()
{
  DefaultComboBoxModel dcm=null;
  ArrayList<Resource> resourcesList=new ArrayList<Resource>();
  String[] resourcesArrayNames = null;

 if (this.presentedKnowledge!=null &&
//     this.presentation.getFeasibility()!=null &&
     this.presentedKnowledge.getResources()!=null)

  {
      if (this.presentedKnowledge.getResources().getResourcesList().size()>0)
      {
        resourcesList=this.presentedKnowledge.getResources().getResourcesList();
        int size=resourcesList.size();
        resourcesArrayNames = new String[size];
        int i=0;
        for (Object o:resourcesList)
            {
              Resource thisResource=(Resource) o;
              String result="";
              if (thisResource.getResourceType()!=null)
                  result=thisResource.getResourceType().toString()+"  ";
              if (thisResource.getDescription()!=null)
                   result=result+ thisResource.getDescription();
              resourcesArrayNames[i]=result;
              i=i+1;
             }
        dcm=new DefaultComboBoxModel(resourcesArrayNames);
        return dcm;
        }
     else
      {
        String names[] ={"<No selected resource yet>"};
        dcm=new DefaultComboBoxModel(names);
        return dcm;
      }
  }
 else
    {
    String names[] ={"<No selected resource yet>"};
    dcm=new DefaultComboBoxModel(names);
     return dcm;
    }
 }


//COMPOCOMPOCOMPOCOMPOCOMPOCOMPOCOMPOCOMPOCOMPOCOMPOCOMPOCOMPOCOMPOCOMPOCOMPOC
//                       Get Project Resources Model
//COMPOCOMPOCOMPOCOMPOCOMPOCOMPOCOMPOCOMPOCOMPOCOMPOCOMPOCOMPOCOMPOCOMPOCOMPOC
public  DefaultComboBoxModel getProjectResourcesModel()
{
  DefaultComboBoxModel dcm=null;
  ArrayList<Resource> resourcesList=new ArrayList<Resource>();
  String[] resourcesArrayNames = null;
  if (Global.project!=null
          && Global.project.getSupplements()!=null
          && Global.project.getSupplements().getManagement()!=null )
  {
      if (  Global.project.getSupplements().getManagement().getResources()!=null
              && Global.project.getSupplements().getManagement().getResources().getResourcesList().size()>0)
      {
        resourcesList=Global.project.getSupplements().getManagement().getResources().getResourcesList();
        int size=resourcesList.size();
        resourcesArrayNames = new String[size];
        int i=0;
        for (Object o:resourcesList)
            {
              Resource thisResource=(Resource) o;
              String result="";
              if (thisResource.getResourceType()!=null)
                  result=thisResource.getResourceType().toString()+"  ";
              if (thisResource.getDescription()!=null)
                   result=result+ thisResource.getDescription();
              resourcesArrayNames[i]=result;
              i=i+1;
             }
        dcm=new DefaultComboBoxModel(resourcesArrayNames);
        return dcm;
        }
     else
      {
        String names[] ={"<No resource available yet>"};
        dcm=new DefaultComboBoxModel(names);
        return dcm;
      }
  }
 else
    {
    String names[] ={"<No resource available yet>"};
    dcm=new DefaultComboBoxModel(names);
     return dcm;
    }
 }

  //COMPOCOMPOCOMPOCOMPOCOMPOCOMPOCOMPOCOMPOCOMPOCOMPOCOMPOCOMPOCOMPOCOMPOCOMPOC
//                       Get Project Remeasures Model
//COMPOCOMPOCOMPOCOMPOCOMPOCOMPOCOMPOCOMPOCOMPOCOMPOCOMPOCOMPOCOMPOCOMPOCOMPOC
public  DefaultComboBoxModel getEvaluationProcedures()
{
  DefaultComboBoxModel dcm=null;
  ArrayList<Procedure> proceduresList=new ArrayList<Procedure>();
  String[] procedureArrayNames = null;
  if (this.modelEvaluation!=null &&this.modelEvaluation.getValidationTest()!=null)
  {
      if ( this.modelEvaluation.getValidationTest().getProceduresList().size()>0)
      {
        proceduresList=this.modelEvaluation.getValidationTest().getProceduresList();
        int size=proceduresList.size();
        procedureArrayNames = new String[size];
        int i=0;
        for (Object o:proceduresList)
            {
            String result="";
            Procedure  thisProcedure=(Procedure) o;
            result=thisProcedure.toString();
              procedureArrayNames[i]=result;
              i=i+1;
              //--------------------------------------------------------------
             }
        dcm=new DefaultComboBoxModel(procedureArrayNames);
        return dcm;
        }
     else
      {
        String names[] ={"<No Validation Procedures Yet>"};
        dcm=new DefaultComboBoxModel(names);
        return dcm;
      }
  }
 else
    {
    String names[] ={"<No Validation Procedures Yet>"};
    dcm=new DefaultComboBoxModel(names);
     return dcm;
    }
 }

//COMPOCOMPOCOMPOCOMPOCOMPOCOMPOCOMPOCOMPOCOMPOCOMPOCOMPOCOMPOCOMPOCOMPOCOMPOC
//                       Get Planned Resources Model
//COMPOCOMPOCOMPOCOMPOCOMPOCOMPOCOMPOCOMPOCOMPOCOMPOCOMPOCOMPOCOMPOCOMPOCOMPOC
public  DefaultComboBoxModel getSelectedResourcesModel()
{
  DefaultComboBoxModel dcm=null;
  ArrayList<Resource> resourcesList=new ArrayList<Resource>();
  String[] resourcesArrayNames = null;

 if (this.procedure!=null &&
     this.procedure.getResources()!=null )

  {
      if (this.procedure.getResources().getResourcesList().size()>0)
      {
        resourcesList=this.procedure.getResources().getResourcesList();
        int size=resourcesList.size();
        resourcesArrayNames = new String[size];
        int i=0;
        for (Object o:resourcesList)
            {
              Resource thisResource=(Resource) o;
              String result="";
              if (thisResource.getResourceType()!=null)
                  result=thisResource.getResourceType().toString()+"  ";
              if (thisResource.getDescription()!=null)
                   result=result+ thisResource.getDescription();
              resourcesArrayNames[i]=result;
              i=i+1;
             }
        dcm=new DefaultComboBoxModel(resourcesArrayNames);
        return dcm;
        }
     else
      {
        String names[] ={"<No selected resource yet>"};
        dcm=new DefaultComboBoxModel(names);
        return dcm;
      }
  }
 else
    {
    String names[] ={"<No selected resource yet>"};
    dcm=new DefaultComboBoxModel(names);
     return dcm;
    }
 }



}
