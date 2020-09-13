/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * PerformingForm.java
 *
 * Created on 04-Oct-2010, 12:08:43
 */

package gui.form.task;

import global.Global;
import gui.form.outcome.DataAcclimatisationForm;
import gui.form.outcome.DataExplorationForm;
import gui.form.outcome.DataPreProcessingForm;
import gui.form.outcome.DeploymentForm;
import gui.form.outcome.ModelEvaluationForm;
import gui.form.outcome.KnowledgePresentationForm;
import gui.form.outcome.ModelBuildingForm;
import gui.form.outcome.KnowledgeEvaluationForm;
import gui.form.outcome.ProcessEvaluationForm;
import gui.form.outcome.ObjectivesDefinitionForm;
import gui.form.outcome.TechniqueSelectionForm;
import gui.form.supplement.HumanInteractionForm;
import gui.form.supplement.ManagementForm;
import gui.form.supplement.SourceForm;
import java.awt.Color;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import process_model.basic.util.ArrayList;
import process_model.issue.justification.Justification;
import process_model.issue.problem.Problem;
import process_model.issue.problem.Problems;
import process_model.issue.problem.Reason;
import process_model.issue.problem.Solution;
import process_model.issue.tracibility.ExternalSource;
import process_model.issue.tracibility.HumanSource;
import process_model.issue.tracibility.InternalSource;
import process_model.issue.tracibility.Source;
import process_model.phase.Phase;
import process_model.phase.stage.performing.PerformedActivity;
import process_model.phase.stage.performing.Performing;
import process_model.phase.stage.planning.Plan;
import process_model.phase.stage.planning.PlanItem;
import process_model.phase.stage.reporting.Reporting;
import process_model.supplement.human_interaction.Analyst;
import process_model.supplement.management.resource.Resource;
import process_model.supplement.management.resource.Resources;
import toolbox.Tree;
/**
 *
 * @author amb04
 */
public class PerformingForm extends javax.swing.JFrame {
private Plan plan=null; // planning consists of plan
private PlanItem customisedPlanItem=null;
private PlanItem customisedSubPlanItem=null;
private Resource resource;

private Problem problem=null;
//
private Solution solution=null;
//
private Reason reason=null;
private String cause=null;
private Source solutionEvidence=null;
private Source justificationEvidence=null;
//
private Justification justification=null;
private Analyst analyst=null;
private PerformedActivity performedActivity=null;
private Phase phase=null;
private boolean outcomeFlag=false;
//AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA
    private String analystRole[]={"","Data Miner", "Biologist", "Domain Expert",
    "Statistician", "Lab Technician", "Computer Scientist", "Other"};

    private String analystLevel[]={"","Beginner", "Normal", "Professional"};

    private String resourceTypeArray[]={"","Hardware","Software","Human Expertise","Other"};
    private boolean newPerformedActivity;
    

    
    //
    /** Creates new form PlanningForm */
//CCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCC
public PerformingForm() {
     initComponents();

   if (this.phase==null)
    {
      if (Global.currentPhase!=null)
      {
         if (Global.currentPhase.getPerforming()==null)
             Global.currentPhase.setPerforming(new Performing());

         this.phase = Global.currentPhase.clone(true);
       }
      else
          JOptionPane.showMessageDialog(null, "Phase Does Not exists");
    }
     this.refresh_performedActivity();
     this.populate_performedPlan();
     this.setTitle("Planned Activities Performing: "+phase.getTitle());
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Performing_jLayeredPane = new javax.swing.JLayeredPane();
        Performing_Details_jDesktopPane_jDesktopPane = new javax.swing.JDesktopPane();
        Performing_Details_TabbedPane = new javax.swing.JTabbedPane();
        Performing_Performer_jDesktopPane = new javax.swing.JLayeredPane();
        Performing_Performer_jDesktopPane1 = new javax.swing.JDesktopPane();
        Planning_Planner_jDesktopPane = new javax.swing.JDesktopPane();
        Planning_Planner_info_jDesktopPane = new javax.swing.JDesktopPane();
        Planning_Planner_Name_jLabel = new javax.swing.JLabel();
        Planning_Planner_Contacts_jLabel = new javax.swing.JLabel();
        jScrollPane62 = new javax.swing.JScrollPane();
        Analyst_Contacts_jTextArea = new javax.swing.JTextArea();
        Analyst_Name_jTextField = new javax.swing.JTextField();
        Analyst_Role_jComboBox = new javax.swing.JComboBox();
        Planning_Planner_Level_jLabel = new javax.swing.JLabel();
        Analyst_Level_jComboBox = new javax.swing.JComboBox();
        Planning_Planner_Role_jLabel = new javax.swing.JLabel();
        jScrollPane9 = new javax.swing.JScrollPane();
        Analysts_jList = new javax.swing.JList();
        Performer_edit_jButton = new javax.swing.JButton();
        jLayeredPane39 = new javax.swing.JLayeredPane();
        Planning_Resource_jDesktopPane = new javax.swing.JDesktopPane();
        Planning_Objectives_Control_jDesktopPane1 = new javax.swing.JDesktopPane();
        Planning_Resources_Select_jButton = new javax.swing.JButton();
        Planning_Resources_UnSelect_jButton = new javax.swing.JButton();
        Resource_jDesktopPane = new javax.swing.JDesktopPane();
        Resource_Cost_jLabel = new javax.swing.JLabel();
        Resource_Type_jLabel = new javax.swing.JLabel();
        Resource_Type_jComboBox = new javax.swing.JComboBox();
        Resource_Cost_jTextField = new javax.swing.JTextField();
        Resource_Quantity_jTextField = new javax.swing.JTextField();
        Resource_Quantity_jLabel = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        Resource_Description_jTextArea = new javax.swing.JTextArea();
        jScrollPane7 = new javax.swing.JScrollPane();
        Resources_jList = new javax.swing.JList();
        jScrollPane8 = new javax.swing.JScrollPane();
        Planned_Resources_jList = new javax.swing.JList();
        jScrollPane11 = new javax.swing.JScrollPane();
        Selected_Resources_jList = new javax.swing.JList();
        Resource_Edit_jButton = new javax.swing.JButton();
        Performing_Justification_jLayeredPane = new javax.swing.JLayeredPane();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane34 = new javax.swing.JScrollPane();
        Justification_jTextPane = new javax.swing.JTextPane();
        Performing_JustificationDescription_jLabel1 = new javax.swing.JLabel();
        Justification_Source_jDesktopPane = new javax.swing.JDesktopPane();
        Justification_Sources_Control_jDesktopPane = new javax.swing.JDesktopPane();
        Justification_Sources_Select_jButton = new javax.swing.JButton();
        Justification_Sources_UnSelect_jButton = new javax.swing.JButton();
        Justification_Sources_Edit_jButton = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        Justification_Sources_jList = new javax.swing.JList();
        jScrollPane6 = new javax.swing.JScrollPane();
        Selected_Justification_Sources_jList = new javax.swing.JList();
        Performing_Problem_jLayeredPane = new javax.swing.JLayeredPane();
        Performing_Problem_jDesktopPane = new javax.swing.JDesktopPane();
        Performing_Problem_Control_jDesktopPane = new javax.swing.JDesktopPane();
        jScrollPane29 = new javax.swing.JScrollPane();
        Problem_jTextPane = new javax.swing.JTextPane();
        Solution_jDesktopPane = new javax.swing.JDesktopPane();
        jScrollPane32 = new javax.swing.JScrollPane();
        Solution_jTextPane = new javax.swing.JTextPane();
        Solution_Source_jDesktopPane = new javax.swing.JDesktopPane();
        Planning_Objectives_Control_jDesktopPane3 = new javax.swing.JDesktopPane();
        Solution_Sources_Select_jButton = new javax.swing.JButton();
        Solution_Sources_UnSelect_jButton = new javax.swing.JButton();
        Solution_Sources_Edit_jButton = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        Solution_Sources_jList = new javax.swing.JList();
        jScrollPane3 = new javax.swing.JScrollPane();
        Selected_Solution_Evidences_jList = new javax.swing.JList();
        Performing_Problem_Next_jButton = new javax.swing.JButton();
        Performing_Problem_Add_jButton = new javax.swing.JButton();
        Performing_Problem_Delete_jButton = new javax.swing.JButton();
        Performing_Problem_Previous_jButton = new javax.swing.JButton();
        Performing_Customised_info_jDesktopPane1 = new javax.swing.JDesktopPane();
        Performing_PerformedActivityDuration_jLabel1 = new javax.swing.JLabel();
        jScrollPane15 = new javax.swing.JScrollPane();
        Reason_jTextPane = new javax.swing.JTextPane();
        Performing_PerformedActivityDuration_jTextField1 = new javax.swing.JTextField();
        Reason_TracedTo_jButton = new javax.swing.JButton();
        Reason_TraceUndo_jButton = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        Selected_Causes_jList = new javax.swing.JList();
        Problem_Refresh_jButton = new javax.swing.JButton();
        Control_jDesktopPane = new javax.swing.JDesktopPane();
        Delete_jButton = new javax.swing.JButton();
        Save_jButton = new javax.swing.JButton();
        Refresh_jButton = new javax.swing.JButton();
        Last_jButton = new javax.swing.JButton();
        Next_jButton = new javax.swing.JButton();
        Previous_jButton = new javax.swing.JButton();
        First_jButton = new javax.swing.JButton();
        Control_jDesktopPane1 = new javax.swing.JDesktopPane();
        Refresh_Plan_jButton = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        Outcome_jButton = new javax.swing.JButton();
        Performed_Plan_jPanel = new javax.swing.JPanel();
        Planning_Performed_jCheckBox = new javax.swing.JCheckBox();
        jScrollPane68 = new javax.swing.JScrollPane();
        Planning_Customised_Description_jTextPane = new javax.swing.JTextPane();
        Planning_Customised_Optional_jCheckBox = new javax.swing.JCheckBox();
        Planning_CustomisedPlanItem_jLabel = new javax.swing.JLabel();
        Planning_CustomisedPlanSubItem_jLabel = new javax.swing.JLabel();
        jScrollPane69 = new javax.swing.JScrollPane();
        Planning_Customised_SubPlanItem_jTextPane = new javax.swing.JTextPane();
        Planning_Customised_SubPlanItem_jCheckBox = new javax.swing.JCheckBox();
        Planning_Preset_SubPlanItem_Next_jButton = new javax.swing.JButton();
        Planning_Preset_SubPlanItem_Previous_jButton = new javax.swing.JButton();
        PerformedActivityDuration_jLabel = new javax.swing.JLabel();
        PerformedActivity_Duration_jTextField = new javax.swing.JTextField();
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

        Performing_Performer_jDesktopPane1.setBackground(new java.awt.Color(255, 255, 204));
        Performing_Performer_jDesktopPane1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        Planning_Planner_jDesktopPane.setBackground(new java.awt.Color(255, 255, 102));
        Planning_Planner_jDesktopPane.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        Planning_Planner_info_jDesktopPane.setBackground(new java.awt.Color(255, 255, 153));
        Planning_Planner_info_jDesktopPane.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        Planning_Planner_info_jDesktopPane.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Planning_Planner_info_jDesktopPaneMouseClicked(evt);
            }
        });

        Planning_Planner_Name_jLabel.setText("Name");
        Planning_Planner_Name_jLabel.setBounds(20, 30, 210, 20);
        Planning_Planner_info_jDesktopPane.add(Planning_Planner_Name_jLabel, javax.swing.JLayeredPane.DEFAULT_LAYER);

        Planning_Planner_Contacts_jLabel.setText("Contact Details (Comma separated)");
        Planning_Planner_Contacts_jLabel.setBounds(20, 110, 210, 20);
        Planning_Planner_info_jDesktopPane.add(Planning_Planner_Contacts_jLabel, javax.swing.JLayeredPane.DEFAULT_LAYER);

        Analyst_Contacts_jTextArea.setBackground(new java.awt.Color(255, 255, 204));
        Analyst_Contacts_jTextArea.setColumns(20);
        Analyst_Contacts_jTextArea.setRows(5);
        jScrollPane62.setViewportView(Analyst_Contacts_jTextArea);

        jScrollPane62.setBounds(20, 130, 490, 110);
        Planning_Planner_info_jDesktopPane.add(jScrollPane62, javax.swing.JLayeredPane.DEFAULT_LAYER);

        Analyst_Name_jTextField.setBackground(new java.awt.Color(255, 255, 204));
        Analyst_Name_jTextField.setBounds(20, 50, 490, 20);
        Planning_Planner_info_jDesktopPane.add(Analyst_Name_jTextField, javax.swing.JLayeredPane.DEFAULT_LAYER);

        Analyst_Role_jComboBox.setBackground(new java.awt.Color(255, 255, 204));
        Analyst_Role_jComboBox.setEditable(true);
        Analyst_Role_jComboBox.setModel(new DefaultComboBoxModel(this.analystRole));
        Analyst_Role_jComboBox.setEnabled(false);
        Analyst_Role_jComboBox.setBounds(20, 290, 210, 20);
        Planning_Planner_info_jDesktopPane.add(Analyst_Role_jComboBox, javax.swing.JLayeredPane.DEFAULT_LAYER);

        Planning_Planner_Level_jLabel.setText("Level");
        Planning_Planner_Level_jLabel.setBounds(250, 270, 150, 20);
        Planning_Planner_info_jDesktopPane.add(Planning_Planner_Level_jLabel, javax.swing.JLayeredPane.DEFAULT_LAYER);

        Analyst_Level_jComboBox.setBackground(new java.awt.Color(255, 255, 204));
        Analyst_Level_jComboBox.setEditable(true);
        Analyst_Level_jComboBox.setModel(new DefaultComboBoxModel(this.analystLevel));
        Analyst_Level_jComboBox.setEnabled(false);
        Analyst_Level_jComboBox.setBounds(250, 290, 260, 20);
        Planning_Planner_info_jDesktopPane.add(Analyst_Level_jComboBox, javax.swing.JLayeredPane.DEFAULT_LAYER);

        Planning_Planner_Role_jLabel.setText("Role");
        Planning_Planner_Role_jLabel.setBounds(20, 270, 150, 20);
        Planning_Planner_info_jDesktopPane.add(Planning_Planner_Role_jLabel, javax.swing.JLayeredPane.DEFAULT_LAYER);

        Planning_Planner_info_jDesktopPane.setBounds(430, 10, 530, 340);
        Planning_Planner_jDesktopPane.add(Planning_Planner_info_jDesktopPane, javax.swing.JLayeredPane.DEFAULT_LAYER);

        Analysts_jList.setBackground(new java.awt.Color(255, 255, 204));
        Analysts_jList.setBorder(javax.swing.BorderFactory.createTitledBorder("Available Actors"));
        Analysts_jList.setModel(getAnalystsModel());
        Analysts_jList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                Analysts_jListValueChanged(evt);
            }
        });
        jScrollPane9.setViewportView(Analysts_jList);

        jScrollPane9.setBounds(10, 10, 410, 320);
        Planning_Planner_jDesktopPane.add(jScrollPane9, javax.swing.JLayeredPane.DEFAULT_LAYER);

        Performer_edit_jButton.setText("Edit");
        Performer_edit_jButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Performer_edit_jButtonActionPerformed(evt);
            }
        });
        Performer_edit_jButton.setBounds(10, 330, 410, 20);
        Planning_Planner_jDesktopPane.add(Performer_edit_jButton, javax.swing.JLayeredPane.DEFAULT_LAYER);

        Planning_Planner_jDesktopPane.setBounds(10, 10, 970, 360);
        Performing_Performer_jDesktopPane1.add(Planning_Planner_jDesktopPane, javax.swing.JLayeredPane.DEFAULT_LAYER);

        Performing_Performer_jDesktopPane1.setBounds(10, 0, 990, 380);
        Performing_Performer_jDesktopPane.add(Performing_Performer_jDesktopPane1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        Performing_Details_TabbedPane.addTab("Performer", Performing_Performer_jDesktopPane);

        Planning_Resource_jDesktopPane.setBackground(new java.awt.Color(255, 255, 153));
        Planning_Resource_jDesktopPane.setBorder(javax.swing.BorderFactory.createTitledBorder(null, " Project Resources ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        Planning_Objectives_Control_jDesktopPane1.setBackground(new java.awt.Color(255, 255, 153));

        Planning_Resources_Select_jButton.setText("+>");
        Planning_Resources_Select_jButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Planning_Resources_Select_jButtonActionPerformed(evt);
            }
        });
        Planning_Resources_Select_jButton.setBounds(180, 0, 70, 20);
        Planning_Objectives_Control_jDesktopPane1.add(Planning_Resources_Select_jButton, javax.swing.JLayeredPane.DEFAULT_LAYER);

        Planning_Resources_UnSelect_jButton.setText("<-");
        Planning_Resources_UnSelect_jButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Planning_Resources_UnSelect_jButtonActionPerformed(evt);
            }
        });
        Planning_Resources_UnSelect_jButton.setBounds(120, 0, 60, 20);
        Planning_Objectives_Control_jDesktopPane1.add(Planning_Resources_UnSelect_jButton, javax.swing.JLayeredPane.DEFAULT_LAYER);

        Planning_Objectives_Control_jDesktopPane1.setBounds(180, 160, 250, 20);
        Planning_Resource_jDesktopPane.add(Planning_Objectives_Control_jDesktopPane1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        Resource_jDesktopPane.setBackground(new java.awt.Color(255, 255, 204));
        Resource_jDesktopPane.setBorder(javax.swing.BorderFactory.createTitledBorder("Resource Details"));
        Resource_jDesktopPane.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Resource_jDesktopPaneMouseClicked(evt);
            }
        });

        Resource_Cost_jLabel.setText("Cost");
        Resource_Cost_jLabel.setBounds(290, 110, 50, 20);
        Resource_jDesktopPane.add(Resource_Cost_jLabel, javax.swing.JLayeredPane.DEFAULT_LAYER);

        Resource_Type_jLabel.setText("Resource Type");
        Resource_Type_jLabel.setBounds(280, 10, 90, 20);
        Resource_jDesktopPane.add(Resource_Type_jLabel, javax.swing.JLayeredPane.DEFAULT_LAYER);

        Resource_Type_jComboBox.setBackground(new java.awt.Color(255, 255, 204));
        Resource_Type_jComboBox.setModel(new DefaultComboBoxModel(this.resourceTypeArray));
        Resource_Type_jComboBox.setEnabled(false);
        Resource_Type_jComboBox.setBounds(280, 30, 220, 20);
        Resource_jDesktopPane.add(Resource_Type_jComboBox, javax.swing.JLayeredPane.DEFAULT_LAYER);

        Resource_Cost_jTextField.setBackground(new java.awt.Color(255, 255, 204));
        Resource_Cost_jTextField.setEditable(false);
        Resource_Cost_jTextField.setBounds(350, 110, 60, 20);
        Resource_jDesktopPane.add(Resource_Cost_jTextField, javax.swing.JLayeredPane.DEFAULT_LAYER);

        Resource_Quantity_jTextField.setBackground(new java.awt.Color(255, 255, 204));
        Resource_Quantity_jTextField.setEditable(false);
        Resource_Quantity_jTextField.setBounds(350, 70, 60, 20);
        Resource_jDesktopPane.add(Resource_Quantity_jTextField, javax.swing.JLayeredPane.DEFAULT_LAYER);

        Resource_Quantity_jLabel.setText("Quantity");
        Resource_Quantity_jLabel.setBounds(290, 70, 60, 20);
        Resource_jDesktopPane.add(Resource_Quantity_jLabel, javax.swing.JLayeredPane.DEFAULT_LAYER);

        Resource_Description_jTextArea.setBackground(new java.awt.Color(255, 255, 204));
        Resource_Description_jTextArea.setColumns(20);
        Resource_Description_jTextArea.setEditable(false);
        Resource_Description_jTextArea.setRows(5);
        jScrollPane1.setViewportView(Resource_Description_jTextArea);

        jScrollPane1.setBounds(20, 20, 250, 120);
        Resource_jDesktopPane.add(jScrollPane1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        Resource_jDesktopPane.setBounds(440, 220, 550, 150);
        Planning_Resource_jDesktopPane.add(Resource_jDesktopPane, javax.swing.JLayeredPane.DEFAULT_LAYER);

        Resources_jList.setBorder(javax.swing.BorderFactory.createTitledBorder("Project Resources"));
        Resources_jList.setModel(this.getProjectResourcesModel());
        Resources_jList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        Resources_jList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                Resources_jListValueChanged(evt);
            }
        });
        jScrollPane7.setViewportView(Resources_jList);

        jScrollPane7.setBounds(10, 180, 420, 170);
        Planning_Resource_jDesktopPane.add(jScrollPane7, javax.swing.JLayeredPane.DEFAULT_LAYER);

        Planned_Resources_jList.setBorder(javax.swing.BorderFactory.createTitledBorder("Planned Resources"));
        Planned_Resources_jList.setModel(this.getPlannedResourcesModel());
        Planned_Resources_jList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        Planned_Resources_jList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                Planned_Resources_jListValueChanged(evt);
            }
        });
        jScrollPane8.setViewportView(Planned_Resources_jList);

        jScrollPane8.setBounds(10, 20, 420, 140);
        Planning_Resource_jDesktopPane.add(jScrollPane8, javax.swing.JLayeredPane.DEFAULT_LAYER);

        Selected_Resources_jList.setBorder(javax.swing.BorderFactory.createTitledBorder("Performing Used Resources"));
        Selected_Resources_jList.setModel(this.getSelectedResourcesModel());
        Selected_Resources_jList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        Selected_Resources_jList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                Selected_Resources_jListValueChanged(evt);
            }
        });
        jScrollPane11.setViewportView(Selected_Resources_jList);

        jScrollPane11.setBounds(440, 20, 550, 190);
        Planning_Resource_jDesktopPane.add(jScrollPane11, javax.swing.JLayeredPane.DEFAULT_LAYER);

        Resource_Edit_jButton.setText("Edit");
        Resource_Edit_jButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Resource_Edit_jButtonActionPerformed(evt);
            }
        });
        Resource_Edit_jButton.setBounds(10, 350, 420, 20);
        Planning_Resource_jDesktopPane.add(Resource_Edit_jButton, javax.swing.JLayeredPane.DEFAULT_LAYER);

        Planning_Resource_jDesktopPane.setBounds(0, 0, 1000, 380);
        jLayeredPane39.add(Planning_Resource_jDesktopPane, javax.swing.JLayeredPane.DEFAULT_LAYER);

        Performing_Details_TabbedPane.addTab(" Resources", jLayeredPane39);

        jPanel2.setBackground(new java.awt.Color(255, 255, 153));
        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jScrollPane34.setViewportView(Justification_jTextPane);

        Performing_JustificationDescription_jLabel1.setText("Justification ");

        Justification_Source_jDesktopPane.setBackground(new java.awt.Color(255, 255, 153));
        Justification_Source_jDesktopPane.setBorder(javax.swing.BorderFactory.createTitledBorder("Supporting Evidences"));

        Justification_Sources_Control_jDesktopPane.setBackground(new java.awt.Color(255, 255, 153));

        Justification_Sources_Select_jButton.setText("+>");
        Justification_Sources_Select_jButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Justification_Sources_Select_jButtonActionPerformed(evt);
            }
        });
        Justification_Sources_Select_jButton.setBounds(0, 0, 60, 20);
        Justification_Sources_Control_jDesktopPane.add(Justification_Sources_Select_jButton, javax.swing.JLayeredPane.DEFAULT_LAYER);

        Justification_Sources_UnSelect_jButton.setText("<-");
        Justification_Sources_UnSelect_jButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Justification_Sources_UnSelect_jButtonActionPerformed(evt);
            }
        });
        Justification_Sources_UnSelect_jButton.setBounds(0, 22, 60, 20);
        Justification_Sources_Control_jDesktopPane.add(Justification_Sources_UnSelect_jButton, javax.swing.JLayeredPane.DEFAULT_LAYER);

        Justification_Sources_Edit_jButton.setText("Edit");
        Justification_Sources_Edit_jButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Justification_Sources_Edit_jButtonActionPerformed(evt);
            }
        });
        Justification_Sources_Edit_jButton.setBounds(0, 50, 60, 20);
        Justification_Sources_Control_jDesktopPane.add(Justification_Sources_Edit_jButton, javax.swing.JLayeredPane.DEFAULT_LAYER);

        Justification_Sources_Control_jDesktopPane.setBounds(430, 30, 60, 110);
        Justification_Source_jDesktopPane.add(Justification_Sources_Control_jDesktopPane, javax.swing.JLayeredPane.DEFAULT_LAYER);

        Justification_Sources_jList.setBorder(javax.swing.BorderFactory.createTitledBorder("Available Sources"));
        Justification_Sources_jList.setModel(this.getSourcesModel());
        Justification_Sources_jList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        Justification_Sources_jList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                Justification_Sources_jListValueChanged(evt);
            }
        });
        jScrollPane5.setViewportView(Justification_Sources_jList);

        jScrollPane5.setBounds(20, 30, 410, 200);
        Justification_Source_jDesktopPane.add(jScrollPane5, javax.swing.JLayeredPane.DEFAULT_LAYER);

        Selected_Justification_Sources_jList.setBorder(javax.swing.BorderFactory.createTitledBorder("Selected Justification Sources"));
        Selected_Justification_Sources_jList.setModel(this.getSelectedJustificationEvidencesModel());
        Selected_Justification_Sources_jList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        Selected_Justification_Sources_jList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                Selected_Justification_Sources_jListValueChanged(evt);
            }
        });
        jScrollPane6.setViewportView(Selected_Justification_Sources_jList);

        jScrollPane6.setBounds(490, 30, 470, 200);
        Justification_Source_jDesktopPane.add(jScrollPane6, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane34, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 972, Short.MAX_VALUE)
                    .addComponent(Performing_JustificationDescription_jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Justification_Source_jDesktopPane, javax.swing.GroupLayout.DEFAULT_SIZE, 972, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(Performing_JustificationDescription_jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane34, javax.swing.GroupLayout.DEFAULT_SIZE, 68, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(Justification_Source_jDesktopPane, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel2.setBounds(0, 0, 1000, 380);
        Performing_Justification_jLayeredPane.add(jPanel2, javax.swing.JLayeredPane.DEFAULT_LAYER);

        Performing_Details_TabbedPane.addTab("Justification", Performing_Justification_jLayeredPane);

        Performing_Problem_jDesktopPane.setBackground(new java.awt.Color(255, 255, 204));
        Performing_Problem_jDesktopPane.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        Performing_Problem_Control_jDesktopPane.setBackground(new java.awt.Color(255, 255, 153));
        Performing_Problem_Control_jDesktopPane.setBorder(javax.swing.BorderFactory.createTitledBorder("Performing Problems"));

        jScrollPane29.setViewportView(Problem_jTextPane);

        jScrollPane29.setBounds(10, 20, 290, 130);
        Performing_Problem_Control_jDesktopPane.add(jScrollPane29, javax.swing.JLayeredPane.DEFAULT_LAYER);

        Solution_jDesktopPane.setBackground(new java.awt.Color(255, 255, 204));
        Solution_jDesktopPane.setBorder(javax.swing.BorderFactory.createTitledBorder("Problem Solutions"));

        jScrollPane32.setViewportView(Solution_jTextPane);

        jScrollPane32.setBounds(10, 20, 220, 110);
        Solution_jDesktopPane.add(jScrollPane32, javax.swing.JLayeredPane.DEFAULT_LAYER);

        Solution_Source_jDesktopPane.setBackground(new java.awt.Color(255, 255, 153));
        Solution_Source_jDesktopPane.setBorder(javax.swing.BorderFactory.createTitledBorder("Solution Evidences"));

        Planning_Objectives_Control_jDesktopPane3.setBackground(new java.awt.Color(255, 255, 153));

        Solution_Sources_Select_jButton.setText("+>");
        Solution_Sources_Select_jButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Solution_Sources_Select_jButtonActionPerformed(evt);
            }
        });
        Solution_Sources_Select_jButton.setBounds(0, 0, 70, 20);
        Planning_Objectives_Control_jDesktopPane3.add(Solution_Sources_Select_jButton, javax.swing.JLayeredPane.DEFAULT_LAYER);

        Solution_Sources_UnSelect_jButton.setText("<-");
        Solution_Sources_UnSelect_jButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Solution_Sources_UnSelect_jButtonActionPerformed(evt);
            }
        });
        Solution_Sources_UnSelect_jButton.setBounds(0, 20, 70, 20);
        Planning_Objectives_Control_jDesktopPane3.add(Solution_Sources_UnSelect_jButton, javax.swing.JLayeredPane.DEFAULT_LAYER);

        Solution_Sources_Edit_jButton.setText("Edit");
        Solution_Sources_Edit_jButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Solution_Sources_Edit_jButtonActionPerformed(evt);
            }
        });
        Solution_Sources_Edit_jButton.setBounds(0, 50, 70, 20);
        Planning_Objectives_Control_jDesktopPane3.add(Solution_Sources_Edit_jButton, javax.swing.JLayeredPane.DEFAULT_LAYER);

        Planning_Objectives_Control_jDesktopPane3.setBounds(290, 20, 70, 100);
        Solution_Source_jDesktopPane.add(Planning_Objectives_Control_jDesktopPane3, javax.swing.JLayeredPane.DEFAULT_LAYER);

        Solution_Sources_jList.setBorder(javax.swing.BorderFactory.createTitledBorder("Sources"));
        Solution_Sources_jList.setModel(this.getSourcesModel());
        Solution_Sources_jList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        Solution_Sources_jList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                Solution_Sources_jListValueChanged(evt);
            }
        });
        jScrollPane2.setViewportView(Solution_Sources_jList);

        jScrollPane2.setBounds(20, 20, 270, 100);
        Solution_Source_jDesktopPane.add(jScrollPane2, javax.swing.JLayeredPane.DEFAULT_LAYER);

        Selected_Solution_Evidences_jList.setBorder(javax.swing.BorderFactory.createTitledBorder("Solution Evidences"));
        Selected_Solution_Evidences_jList.setModel(this.getSelectedSolutionEvidencesModel());
        Selected_Solution_Evidences_jList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        Selected_Solution_Evidences_jList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                Selected_Solution_Evidences_jListValueChanged(evt);
            }
        });
        jScrollPane3.setViewportView(Selected_Solution_Evidences_jList);

        jScrollPane3.setBounds(360, 20, 310, 100);
        Solution_Source_jDesktopPane.add(jScrollPane3, javax.swing.JLayeredPane.DEFAULT_LAYER);

        Solution_Source_jDesktopPane.setBounds(240, 10, 680, 130);
        Solution_jDesktopPane.add(Solution_Source_jDesktopPane, javax.swing.JLayeredPane.DEFAULT_LAYER);

        Solution_jDesktopPane.setBounds(10, 170, 930, 150);
        Performing_Problem_Control_jDesktopPane.add(Solution_jDesktopPane, javax.swing.JLayeredPane.DEFAULT_LAYER);

        Performing_Problem_Next_jButton.setText("Next >");
        Performing_Problem_Next_jButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Performing_Problem_Next_jButtonActionPerformed(evt);
            }
        });
        Performing_Problem_Next_jButton.setBounds(120, 330, 100, 20);
        Performing_Problem_Control_jDesktopPane.add(Performing_Problem_Next_jButton, javax.swing.JLayeredPane.DEFAULT_LAYER);

        Performing_Problem_Add_jButton.setText("Add +");
        Performing_Problem_Add_jButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Performing_Problem_Add_jButtonActionPerformed(evt);
            }
        });
        Performing_Problem_Add_jButton.setBounds(330, 330, 100, 20);
        Performing_Problem_Control_jDesktopPane.add(Performing_Problem_Add_jButton, javax.swing.JLayeredPane.DEFAULT_LAYER);

        Performing_Problem_Delete_jButton.setText("Delete -");
        Performing_Problem_Delete_jButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Performing_Problem_Delete_jButtonActionPerformed(evt);
            }
        });
        Performing_Problem_Delete_jButton.setBounds(430, 330, 100, 20);
        Performing_Problem_Control_jDesktopPane.add(Performing_Problem_Delete_jButton, javax.swing.JLayeredPane.DEFAULT_LAYER);

        Performing_Problem_Previous_jButton.setText("< Previous");
        Performing_Problem_Previous_jButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Performing_Problem_Previous_jButtonActionPerformed(evt);
            }
        });
        Performing_Problem_Previous_jButton.setBounds(10, 330, 110, 20);
        Performing_Problem_Control_jDesktopPane.add(Performing_Problem_Previous_jButton, javax.swing.JLayeredPane.DEFAULT_LAYER);

        Performing_Customised_info_jDesktopPane1.setBackground(new java.awt.Color(255, 255, 204));
        Performing_Customised_info_jDesktopPane1.setBorder(javax.swing.BorderFactory.createTitledBorder("Reason of the Problem"));

        Performing_PerformedActivityDuration_jLabel1.setText("Duration");
        Performing_PerformedActivityDuration_jLabel1.setBounds(10, 130, 60, 20);
        Performing_Customised_info_jDesktopPane1.add(Performing_PerformedActivityDuration_jLabel1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jScrollPane15.setViewportView(Reason_jTextPane);

        jScrollPane15.setBounds(10, 20, 230, 100);
        Performing_Customised_info_jDesktopPane1.add(jScrollPane15, javax.swing.JLayeredPane.DEFAULT_LAYER);
        Performing_PerformedActivityDuration_jTextField1.setBounds(70, 130, 60, 22);
        Performing_Customised_info_jDesktopPane1.add(Performing_PerformedActivityDuration_jTextField1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        Reason_TracedTo_jButton.setText("Trace+ >");
        Reason_TracedTo_jButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Reason_TracedTo_jButtonActionPerformed(evt);
            }
        });
        Reason_TracedTo_jButton.setBounds(240, 20, 90, 20);
        Performing_Customised_info_jDesktopPane1.add(Reason_TracedTo_jButton, javax.swing.JLayeredPane.DEFAULT_LAYER);

        Reason_TraceUndo_jButton.setText(" Undo -");
        Reason_TraceUndo_jButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Reason_TraceUndo_jButtonActionPerformed(evt);
            }
        });
        Reason_TraceUndo_jButton.setBounds(240, 40, 90, 20);
        Performing_Customised_info_jDesktopPane1.add(Reason_TraceUndo_jButton, javax.swing.JLayeredPane.DEFAULT_LAYER);

        Selected_Causes_jList.setBorder(javax.swing.BorderFactory.createTitledBorder("Identified Causes"));
        Selected_Causes_jList.setModel(this.getPlannedResourcesModel());
        Selected_Causes_jList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        Selected_Causes_jList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                Selected_Causes_jListValueChanged(evt);
            }
        });
        jScrollPane4.setViewportView(Selected_Causes_jList);

        jScrollPane4.setBounds(330, 20, 290, 100);
        Performing_Customised_info_jDesktopPane1.add(jScrollPane4, javax.swing.JLayeredPane.DEFAULT_LAYER);

        Performing_Customised_info_jDesktopPane1.setBounds(310, 20, 630, 130);
        Performing_Problem_Control_jDesktopPane.add(Performing_Customised_info_jDesktopPane1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        Problem_Refresh_jButton.setText("Refresh");
        Problem_Refresh_jButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Problem_Refresh_jButtonActionPerformed(evt);
            }
        });
        Problem_Refresh_jButton.setBounds(230, 330, 90, 20);
        Performing_Problem_Control_jDesktopPane.add(Problem_Refresh_jButton, javax.swing.JLayeredPane.DEFAULT_LAYER);
        Problem_Refresh_jButton.getAccessibleContext().setAccessibleName("Problem_Refresh_jButton");

        Performing_Problem_Control_jDesktopPane.setBounds(10, 10, 950, 360);
        Performing_Problem_jDesktopPane.add(Performing_Problem_Control_jDesktopPane, javax.swing.JLayeredPane.DEFAULT_LAYER);

        Performing_Problem_jDesktopPane.setBounds(0, 0, 970, 380);
        Performing_Problem_jLayeredPane.add(Performing_Problem_jDesktopPane, javax.swing.JLayeredPane.DEFAULT_LAYER);

        Performing_Details_TabbedPane.addTab("Problems", Performing_Problem_jLayeredPane);

        Performing_Details_TabbedPane.setBounds(10, 210, 1010, 410);
        Performing_Details_jDesktopPane_jDesktopPane.add(Performing_Details_TabbedPane, javax.swing.JLayeredPane.DEFAULT_LAYER);

        Control_jDesktopPane.setBackground(new java.awt.Color(255, 255, 153));
        Control_jDesktopPane.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        Delete_jButton.setText("Delete");
        Delete_jButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Delete_jButtonActionPerformed(evt);
            }
        });
        Delete_jButton.setBounds(530, 10, 80, 23);
        Control_jDesktopPane.add(Delete_jButton, javax.swing.JLayeredPane.DEFAULT_LAYER);

        Save_jButton.setText("Save");
        Save_jButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Save_jButtonActionPerformed(evt);
            }
        });
        Save_jButton.setBounds(460, 10, 70, 23);
        Control_jDesktopPane.add(Save_jButton, javax.swing.JLayeredPane.DEFAULT_LAYER);

        Refresh_jButton.setText("Refresh");
        Refresh_jButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Refresh_jButtonActionPerformed(evt);
            }
        });
        Refresh_jButton.setBounds(380, 10, 80, 23);
        Control_jDesktopPane.add(Refresh_jButton, javax.swing.JLayeredPane.DEFAULT_LAYER);

        Last_jButton.setText("Last >>");
        Last_jButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Last_jButtonActionPerformed(evt);
            }
        });
        Last_jButton.setBounds(280, 10, 90, 23);
        Control_jDesktopPane.add(Last_jButton, javax.swing.JLayeredPane.DEFAULT_LAYER);

        Next_jButton.setText("Next >");
        Next_jButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Next_jButtonActionPerformed(evt);
            }
        });
        Next_jButton.setBounds(190, 10, 90, 23);
        Control_jDesktopPane.add(Next_jButton, javax.swing.JLayeredPane.DEFAULT_LAYER);

        Previous_jButton.setText("< Previous");
        Previous_jButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Previous_jButtonActionPerformed(evt);
            }
        });
        Previous_jButton.setBounds(90, 10, 100, 23);
        Control_jDesktopPane.add(Previous_jButton, javax.swing.JLayeredPane.DEFAULT_LAYER);

        First_jButton.setText("<< First");
        First_jButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                First_jButtonActionPerformed(evt);
            }
        });
        First_jButton.setBounds(10, 10, 80, 23);
        Control_jDesktopPane.add(First_jButton, javax.swing.JLayeredPane.DEFAULT_LAYER);

        Control_jDesktopPane.setBounds(10, 630, 620, 40);
        Performing_Details_jDesktopPane_jDesktopPane.add(Control_jDesktopPane, javax.swing.JLayeredPane.DEFAULT_LAYER);

        Control_jDesktopPane1.setBackground(new java.awt.Color(255, 255, 153));
        Control_jDesktopPane1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        Refresh_Plan_jButton.setText("Reset Performed Activities");
        Refresh_Plan_jButton.setToolTipText("This will delete the performed activities");
        Refresh_Plan_jButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Refresh_Plan_jButtonActionPerformed(evt);
            }
        });
        Refresh_Plan_jButton.setBounds(5, 5, 190, 30);
        Control_jDesktopPane1.add(Refresh_Plan_jButton, javax.swing.JLayeredPane.DEFAULT_LAYER);

        Control_jDesktopPane1.setBounds(640, 630, 200, 40);
        Performing_Details_jDesktopPane_jDesktopPane.add(Control_jDesktopPane1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jPanel3.setBackground(new java.awt.Color(255, 204, 0));
        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel3.setLayout(null);

        Outcome_jButton.setFont(new java.awt.Font("Tahoma", 1, 11));
        Outcome_jButton.setText("< Outcomes >");
        Outcome_jButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Outcome_jButtonActionPerformed(evt);
            }
        });
        jPanel3.add(Outcome_jButton);
        Outcome_jButton.setBounds(5, 5, 160, 30);

        jPanel3.setBounds(850, 630, 170, 40);
        Performing_Details_jDesktopPane_jDesktopPane.add(jPanel3, javax.swing.JLayeredPane.DEFAULT_LAYER);

        Performed_Plan_jPanel.setBackground(new java.awt.Color(255, 255, 153));
        Performed_Plan_jPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        Performed_Plan_jPanel.setLayout(null);

        Planning_Performed_jCheckBox.setBackground(new java.awt.Color(255, 255, 153));
        Planning_Performed_jCheckBox.setText("Performed");
        Performed_Plan_jPanel.add(Planning_Performed_jCheckBox);
        Planning_Performed_jCheckBox.setBounds(470, 150, 150, 25);

        Planning_Customised_Description_jTextPane.setBackground(new java.awt.Color(255, 255, 204));
        Planning_Customised_Description_jTextPane.setEditable(false);
        jScrollPane68.setViewportView(Planning_Customised_Description_jTextPane);

        Performed_Plan_jPanel.add(jScrollPane68);
        jScrollPane68.setBounds(10, 30, 450, 150);

        Planning_Customised_Optional_jCheckBox.setBackground(new java.awt.Color(255, 255, 153));
        Planning_Customised_Optional_jCheckBox.setText("Optional");
        Planning_Customised_Optional_jCheckBox.setEnabled(false);
        Performed_Plan_jPanel.add(Planning_Customised_Optional_jCheckBox);
        Planning_Customised_Optional_jCheckBox.setBounds(860, 30, 90, 20);

        Planning_CustomisedPlanItem_jLabel.setText("Plan Item");
        Performed_Plan_jPanel.add(Planning_CustomisedPlanItem_jLabel);
        Planning_CustomisedPlanItem_jLabel.setBounds(10, 10, 60, 20);

        Planning_CustomisedPlanSubItem_jLabel.setText("Sub-Item");
        Performed_Plan_jPanel.add(Planning_CustomisedPlanSubItem_jLabel);
        Planning_CustomisedPlanSubItem_jLabel.setBounds(560, 10, 60, 20);

        Planning_Customised_SubPlanItem_jTextPane.setBackground(new java.awt.Color(255, 255, 204));
        Planning_Customised_SubPlanItem_jTextPane.setEditable(false);
        jScrollPane69.setViewportView(Planning_Customised_SubPlanItem_jTextPane);

        Performed_Plan_jPanel.add(jScrollPane69);
        jScrollPane69.setBounds(560, 30, 290, 80);

        Planning_Customised_SubPlanItem_jCheckBox.setBackground(new java.awt.Color(255, 255, 153));
        Planning_Customised_SubPlanItem_jCheckBox.setText("Optional");
        Planning_Customised_SubPlanItem_jCheckBox.setEnabled(false);
        Performed_Plan_jPanel.add(Planning_Customised_SubPlanItem_jCheckBox);
        Planning_Customised_SubPlanItem_jCheckBox.setBounds(470, 30, 80, 25);

        Planning_Preset_SubPlanItem_Next_jButton.setText(" >");
        Planning_Preset_SubPlanItem_Next_jButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Planning_Preset_SubPlanItem_Next_jButtonActionPerformed(evt);
            }
        });
        Performed_Plan_jPanel.add(Planning_Preset_SubPlanItem_Next_jButton);
        Planning_Preset_SubPlanItem_Next_jButton.setBounds(910, 90, 50, 20);

        Planning_Preset_SubPlanItem_Previous_jButton.setText("<");
        Planning_Preset_SubPlanItem_Previous_jButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Planning_Preset_SubPlanItem_Previous_jButtonActionPerformed(evt);
            }
        });
        Performed_Plan_jPanel.add(Planning_Preset_SubPlanItem_Previous_jButton);
        Planning_Preset_SubPlanItem_Previous_jButton.setBounds(860, 90, 50, 20);

        PerformedActivityDuration_jLabel.setText("Duration");
        Performed_Plan_jPanel.add(PerformedActivityDuration_jLabel);
        PerformedActivityDuration_jLabel.setBounds(560, 120, 60, 22);
        Performed_Plan_jPanel.add(PerformedActivity_Duration_jTextField);
        PerformedActivity_Duration_jTextField.setBounds(620, 120, 70, 22);

        Performed_Plan_jPanel.setBounds(10, 10, 1010, 190);
        Performing_Details_jDesktopPane_jDesktopPane.add(Performed_Plan_jPanel, javax.swing.JLayeredPane.DEFAULT_LAYER);

        Performing_Details_jDesktopPane_jDesktopPane.setBounds(10, 10, 1030, 680);
        Performing_jLayeredPane.add(Performing_Details_jDesktopPane_jDesktopPane, javax.swing.JLayeredPane.DEFAULT_LAYER);

        setJMenuBar(menuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(Performing_jLayeredPane, javax.swing.GroupLayout.DEFAULT_SIZE, 1052, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Performing_jLayeredPane, javax.swing.GroupLayout.DEFAULT_SIZE, 699, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    private void Save_jButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Save_jButtonActionPerformed
this.save();
}//GEN-LAST:event_Save_jButtonActionPerformed

    private void Refresh_jButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Refresh_jButtonActionPerformed
        this.refresh_performedActivity();
}//GEN-LAST:event_Refresh_jButtonActionPerformed

    private void Planning_Planner_info_jDesktopPaneMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Planning_Planner_info_jDesktopPaneMouseClicked
        HumanInteractionForm humanInteractionForm=null;
        humanInteractionForm=new HumanInteractionForm(this.analyst);
}//GEN-LAST:event_Planning_Planner_info_jDesktopPaneMouseClicked

    private void Analysts_jListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_Analysts_jListValueChanged
 if (Global.project!=null&&Global.project.getSupplements()!=null &&
     Global.project.getSupplements().getHumanInteraction()!=null )
      if (this.Analysts_jList.getSelectedIndex()>=0)
            this.analyst=Global.project.getSupplements().getHumanInteraction().
                                    get(this.Analysts_jList.getSelectedIndex());

if (this.performedActivity!=null && this.analyst!=null)
{
this.performedActivity.setPerformer(analyst.clone());
 this.save();
}
  this.populate_analyst();
  this.populateForm();


}//GEN-LAST:event_Analysts_jListValueChanged

    private void Planned_Resources_jListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_Planned_Resources_jListValueChanged
//Note: since the planned resources is saved in the plan which is now stored in the activity
// once the original plan changed the change is not reflected on planned resources stored in the performed plan 
// this seems to be logical since you cannot carry out an out of date plan
// in this case the nest thing is to delete the old edition and add the new on
// I will add some code to lock the plan once it is performed from change

        if (this.plan!=null &&
            this.plan.getResources()!=null &&
            this.plan.getResources().getResourcesList().size()>0)

            if (this.Planned_Resources_jList.getSelectedIndex()>=0)
                this.resource=this.plan.getResources().getResource(Planned_Resources_jList.getSelectedIndex());
        this.populate_resource();
        this.populateForm();
}//GEN-LAST:event_Planned_Resources_jListValueChanged

    private void Resources_jListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_Resources_jListValueChanged

        if (Global.project!=null &&
            Global.project.getSupplements()!=null &&
            Global.project.getSupplements().getManagement()!=null &&
            Global.project.getSupplements().getManagement().getResources()!=null )
            if (this.Resources_jList.getSelectedIndex()>=0)
                this.resource=Global.project.getSupplements().getManagement().getResources().getResource(this.Resources_jList.getSelectedIndex());
        this.populate_resource();
        this.populateForm();
    }//GEN-LAST:event_Resources_jListValueChanged

    private void Planning_Resources_Select_jButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Planning_Resources_Select_jButtonActionPerformed
   if (this.performedActivity!=null && this.resource!=null ) {
            //-----------------------------------------------------------------
            if (this.performedActivity.getResources()==null)
                this.performedActivity.setResources(new Resources());
                //-----------------------------------------------------------------
                // calculations
                 this.addResourceFunds();
                //-----------------------------------------------------------------
            this.performedActivity.getResources().save(this.resource);
        }
//*****************************************************************************
this.Selected_Resources_jList.setModel(this.getSelectedResourcesModel());
this.populate_resource();
this.populateForm();
}//GEN-LAST:event_Planning_Resources_Select_jButtonActionPerformed

    private void Planning_Resources_UnSelect_jButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Planning_Resources_UnSelect_jButtonActionPerformed

        if (this.performedActivity!=null && this.performedActivity.getResources()!=null )
        {            
         //------------------------------------------------------------------
         // Claculation of the remaining funds after deleting the current resource           
         this.deductResourceFunds();
         //---------------------------------------------------------------------
        this.delete_resource();
        }
        //------------------------------------------------------------------
        this.Selected_Resources_jList.setModel(this.getPlannedResourcesModel());
        this.Selected_Resources_jList.setSelectedIndex(this.performedActivity.getResources().getResourcesList().indexOf(this.resource));

        this.populate_resource();
        this.populateForm();
    }//GEN-LAST:event_Planning_Resources_UnSelect_jButtonActionPerformed

    private void Justification_Sources_Select_jButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Justification_Sources_Select_jButtonActionPerformed
        
        // to save the existing solution and to make sure that a justification exist to add the evidence to, since save creates the solution if it is null
        this.save();

        //---------------------------------------------------------------------
        // now the evidence is saved to an existing solution  rather than creating a new one
        // This saves the selected evidence which is now held in this.justificationEvidence to the solution
        if (this.performedActivity!=null && this.justification!=null )
            this.justification.saveEvidence(this.justificationEvidence);
        
        this.Selected_Justification_Sources_jList.setModel(this.getSelectedJustificationEvidencesModel());
        //---------------------------------------------------------------------
        this.save();
        
        this.populateForm();
    }//GEN-LAST:event_Justification_Sources_Select_jButtonActionPerformed

    private void Justification_Sources_UnSelect_jButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Justification_Sources_UnSelect_jButtonActionPerformed

    if (this.performedActivity!=null && this.performedActivity.getJustification()!=null )
       this.delete_justification_evidence();
        

        this.Selected_Justification_Sources_jList.setModel(this.getSelectedJustificationEvidencesModel());

        this.populate_resource();
        this.populateForm();

    }//GEN-LAST:event_Justification_Sources_UnSelect_jButtonActionPerformed

    private void Justification_Sources_Edit_jButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Justification_Sources_Edit_jButtonActionPerformed
        SourceForm projectSourceForm=null;
        projectSourceForm=new SourceForm((process_model.issue.tracibility.Source) this.justificationEvidence);

    }//GEN-LAST:event_Justification_Sources_Edit_jButtonActionPerformed

    private void Solution_Sources_Select_jButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Solution_Sources_Select_jButtonActionPerformed
// to save the existing solution and to make sure that a solution exist to add the evidence to, since save creates the solution if it is null
this.save();

// now the evidence is saved to an existing solution  rather than creating a new one
// This saves the selected evidence which is now held in this.solutionEvidence to the solution
if (this.solution!=null && this.solutionEvidence!=null)
       this.solution.saveEvidence(this.solutionEvidence);

this.save();

this.Selected_Solution_Evidences_jList.setModel(this.getSelectedSolutionEvidencesModel());
this.populateForm();

}//GEN-LAST:event_Solution_Sources_Select_jButtonActionPerformed

    private void Solution_Sources_UnSelect_jButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Solution_Sources_UnSelect_jButtonActionPerformed
   this.delete_solution_evidence();
     
}//GEN-LAST:event_Solution_Sources_UnSelect_jButtonActionPerformed

    private void Solution_Sources_Edit_jButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Solution_Sources_Edit_jButtonActionPerformed
    new SourceForm(this.solutionEvidence).setVisible(true);
}//GEN-LAST:event_Solution_Sources_Edit_jButtonActionPerformed

    private void Reason_TraceUndo_jButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Reason_TraceUndo_jButtonActionPerformed
    this.delete_cause();    // TODO add your handling code here:
    }//GEN-LAST:event_Reason_TraceUndo_jButtonActionPerformed

    private void Planning_Preset_SubPlanItem_Next_jButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Planning_Preset_SubPlanItem_Next_jButtonActionPerformed
         this.next_customised_subPlan();
}//GEN-LAST:event_Planning_Preset_SubPlanItem_Next_jButtonActionPerformed

    private void Planning_Preset_SubPlanItem_Previous_jButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Planning_Preset_SubPlanItem_Previous_jButtonActionPerformed
          this.previous_customised_subPlan();
}//GEN-LAST:event_Planning_Preset_SubPlanItem_Previous_jButtonActionPerformed

    private void Next_jButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Next_jButtonActionPerformed
    this.next_performedActivity();
    }//GEN-LAST:event_Next_jButtonActionPerformed

    private void Previous_jButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Previous_jButtonActionPerformed
    this.previous_performedActivity();
    }//GEN-LAST:event_Previous_jButtonActionPerformed

    private void First_jButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_First_jButtonActionPerformed
    this.first_performedActivity();
    }//GEN-LAST:event_First_jButtonActionPerformed

    private void Last_jButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Last_jButtonActionPerformed
    this.last_performedActivity();
    }//GEN-LAST:event_Last_jButtonActionPerformed

    private void Delete_jButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Delete_jButtonActionPerformed
   //**************************************************************************
   //  performed activity resources calculation after deletion
   //**************************************************************************
    this.deductPerformedActivityDuration();
    
    this.delete_performedActivity();

    }//GEN-LAST:event_Delete_jButtonActionPerformed

    private void Refresh_Plan_jButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Refresh_Plan_jButtonActionPerformed

int decision=JOptionPane.showConfirmDialog(null, "This will delete all the performed plan activities & will reload a fresh plan. Are you sure you want to do this?");
if (decision==0)
{
  this.clear_performedPlan();

   if (Global.currentPhase!=null)
      {
         clear_performedPlan();
         Global.currentPhase.setPerforming(new Performing());
         this.phase = Global.currentPhase.clone(true);
       }
      else
          JOptionPane.showMessageDialog(null, "Phase Does Not exists");

//this.save();
this.refresh_performedActivity();
this.populate_performedPlan();

}
else
 {
    //
 }




    }//GEN-LAST:event_Refresh_Plan_jButtonActionPerformed

    private void Reason_TracedTo_jButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Reason_TracedTo_jButtonActionPerformed
     Tree.create_tree("Tasks");
     this.save();
/*
   if (this.reason==null)
       this.reason=new Reason();

   if (this.cause!=null)
       this.reason.save(this.cause);
*/
/*
   if (this.problem==null)
       this.problem=new Problem();

  if (this.problem!=null && this.reason!=null)
      this.problem.setReason(this.reason);

   if (this.performedActivity!=null)
   {
        if (this.performedActivity.getProblems()==null)
            this.performedActivity.setProblems(new Problems());

        this.performedActivity.getProblems().save(problem);
        this.Selected_Causes_jList.setModel(this.getSelectedCausesModel());
   }

     this.save();
     this.phase.save();
      *
*/
/*
this.save();

this.Selected_Causes_jList.setModel(this.getSelectedCausesModel());

this.save();
this.phase.save();
 *
 */

    }//GEN-LAST:event_Reason_TracedTo_jButtonActionPerformed

    private void Performing_Problem_Add_jButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Performing_Problem_Add_jButtonActionPerformed
    this.save();
    this.clear_problem();
    }//GEN-LAST:event_Performing_Problem_Add_jButtonActionPerformed

    private void Performing_Problem_Next_jButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Performing_Problem_Next_jButtonActionPerformed
    this.next_problem();
    }//GEN-LAST:event_Performing_Problem_Next_jButtonActionPerformed

    private void Performing_Problem_Previous_jButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Performing_Problem_Previous_jButtonActionPerformed
    this.previous_problem();
    }//GEN-LAST:event_Performing_Problem_Previous_jButtonActionPerformed

    private void Solution_Sources_jListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_Solution_Sources_jListValueChanged
        if (Global.project !=null &&  Global.project.getSources()!=null &&
            Global.project.getSources().getSourcesList().size()>0)
         if (this.Solution_Sources_jList.getSelectedIndex()>=0)
                this.solutionEvidence=(Source) Global.project.getSources().getSourcesList().get(this.Solution_Sources_jList.getSelectedIndex());
    }//GEN-LAST:event_Solution_Sources_jListValueChanged

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated

if (outcomeFlag==true)
{
 this.phase=Global.currentPhase.clone(true);
 this.refresh_performedActivity();
 this.populate_performedPlan();
 this.outcomeFlag=false;
}
//-------------------------------------------------------------------------
if (Tree.getSelectedObject()!=null)
{        
    Object selectedObject=new Object();
    selectedObject=Tree.getSelectedObject();
    //
    String treePath=new String();
    treePath=Tree.getSelectedPathString();
    //
    String tracedCause= new String();
    tracedCause=treePath+selectedObject;

    if (this.reason==null)
        this.reason=new Reason();

    if (this.reason!=null && tracedCause!=null)
        this.reason.save(tracedCause);

    //this.Selected_Causes_jList.setModel(this.getSelectedCausesModel());
    Tree.clearSelectedObject();
}

//-------------------------------------------------------------------------    
    
    this.Analysts_jList.setModel(this.getAnalystsModel());
    this.Resources_jList.setModel(this.getProjectResourcesModel());
    this.Solution_Sources_jList.setModel(this.getSourcesModel());
    this.Justification_Sources_jList.setModel(this.getSourcesModel());
    this.Selected_Causes_jList.setModel(this.getSelectedCausesModel());

    }//GEN-LAST:event_formWindowActivated

    private void formWindowDeactivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowDeactivated
    
    }//GEN-LAST:event_formWindowDeactivated

    private void Performing_Problem_Delete_jButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Performing_Problem_Delete_jButtonActionPerformed
    this.delete_problem();
    }//GEN-LAST:event_Performing_Problem_Delete_jButtonActionPerformed

    private void Selected_Causes_jListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_Selected_Causes_jListValueChanged
    if (this.problem!=null && this.problem.getReason()!=null)

         if (this.Selected_Causes_jList.getSelectedIndex()>=0)
                this.cause=(String) this.problem.getReason().getCause(this.Selected_Causes_jList.getSelectedIndex());

    }//GEN-LAST:event_Selected_Causes_jListValueChanged

    private void Selected_Solution_Evidences_jListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_Selected_Solution_Evidences_jListValueChanged
    if (this.problem!=null && this.problem.getReason()!=null)
         if (this.Selected_Solution_Evidences_jList.getSelectedIndex()>=0)
             this.solutionEvidence= this.problem.getSolution().getEvidence(
                this.Selected_Solution_Evidences_jList.getSelectedIndex());

    }//GEN-LAST:event_Selected_Solution_Evidences_jListValueChanged

    private void Justification_Sources_jListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_Justification_Sources_jListValueChanged
    if (Global.project !=null &&  Global.project.getSources()!=null &&
        Global.project.getSources().getSourcesList().size()>0)
     if (this.Justification_Sources_jList.getSelectedIndex()>=0)

         this.justificationEvidence=(Source) Global.project.getSources().
                 getSourcesList().get(this.Justification_Sources_jList.getSelectedIndex());


    }//GEN-LAST:event_Justification_Sources_jListValueChanged

    private void Selected_Justification_Sources_jListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_Selected_Justification_Sources_jListValueChanged
    if (this.performedActivity!=null && this.performedActivity.getJustification()!=null)
         if (this.Selected_Justification_Sources_jList.getSelectedIndex()>=0)

         this.justificationEvidence= this.performedActivity.getJustification().
           getEvidence(this.Selected_Justification_Sources_jList.getSelectedIndex());

    }//GEN-LAST:event_Selected_Justification_Sources_jListValueChanged

    private void Selected_Resources_jListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_Selected_Resources_jListValueChanged
   if (this.performedActivity!=null &&
       this.performedActivity.getResources()!=null &&
       this.performedActivity.getResources().getResourcesList().size()>0)

       if( this.Selected_Resources_jList.getSelectedIndex()>=0)
           this.resource=this.performedActivity.getResources().getResource(this.Selected_Resources_jList.getSelectedIndex());

        this.populate_resource();
        this.populateForm();

    }//GEN-LAST:event_Selected_Resources_jListValueChanged

    private void Resource_Edit_jButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Resource_Edit_jButtonActionPerformed
    new ManagementForm(this.resource).setVisible(true);
    }//GEN-LAST:event_Resource_Edit_jButtonActionPerformed

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
  //  System.runFinalization();
    System.gc();

    }//GEN-LAST:event_formWindowClosed

    private void Performer_edit_jButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Performer_edit_jButtonActionPerformed
    HumanInteractionForm humanInteractionForm=null;
    humanInteractionForm=new HumanInteractionForm(this.analyst);
    }//GEN-LAST:event_Performer_edit_jButtonActionPerformed

    private void Outcome_jButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Outcome_jButtonActionPerformed
 
this.phase.setReporting(new Reporting("Reporting of this phase consists of \n "
                                                 + "the phase running report as described in this file "
                                                 + "and the delivered outcomes as described in the result"));
this.save();
this.outcomeFlag=true;
//----------------------------------------------------------------------
switch(this.phase.getNumber())
{
case 1:
    new ObjectivesDefinitionForm().setVisible(true);
    break;
case 2:
    new DataPreProcessingForm().setVisible(true);
    break;
case 3:
    new DataExplorationForm().setVisible(true);
    break;
case 4:
    new TechniqueSelectionForm().setVisible(true);
    break;
case 5:
    new DataAcclimatisationForm().setVisible(true);
    break;
case 6:
    new ModelBuildingForm().setVisible(true);
    break;
case 7:
    new ModelEvaluationForm().setVisible(true);
    break;
case 8:
    new KnowledgePresentationForm().setVisible(true);
    break;
case 9:
    new KnowledgeEvaluationForm().setVisible(true);
    break;
case 10:
    new DeploymentForm().setVisible(true);
    break;
case 11:
    new ProcessEvaluationForm().setVisible(true);
    break;
 default:
    JOptionPane.showMessageDialog(null, "Phase outcome form was not found");
}

/*
        if (this.phase.getName().equals("Objectives Definition"))
            new ObjectivesDefinitionForm().setVisible(true);
        else if (this.phase.getName().equals("Data Pre-Processing"))
            new DataPreProcessingForm().setVisible(true);
        else if (this.phase.getName().equals("Data Exploration"))
            new DataExplorationForm().setVisible(true);
        else if (this.phase.getName().equals("Data Acclimatisation"))
            new DataAcclimatisationForm().setVisible(true);
        else if (this.phase.getName().equals("Technique Selection"))
            new TechniqueSelectionForm().setVisible(true);
        else if (this.phase.getName().equals("Model Building"))
            new ModelBuildingForm().setVisible(true);
        else if (this.phase.getName().equals("Model Evaluation"))
            new ModelEvaluationForm().setVisible(true);
        else if (this.phase.getName().equals("Knowledge Presentation"))
            new KnowledgePresentationForm().setVisible(true);
        else if (this.phase.getName().equals("Knowledge Evaluation"))
            new KnowledgeEvaluationForm().setVisible(true);
        else if (this.phase.getName().equals("Deployment"))
            new DeploymentForm().setVisible(true);
        else if (this.phase.getName().equals("Process Evaluation"))
            new ProcessEvaluationForm().setVisible(true);
  */

/*
        this.save();
        this.outcomeFlag=true;
 * 
 */
    }//GEN-LAST:event_Outcome_jButtonActionPerformed

    private void Problem_Refresh_jButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Problem_Refresh_jButtonActionPerformed
    this.refresh_problem();
    this.populate_problem();
    this.populateForm();
    }//GEN-LAST:event_Problem_Refresh_jButtonActionPerformed

    private void Resource_jDesktopPaneMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Resource_jDesktopPaneMouseClicked
        ManagementForm projectManagementForm=null;
        projectManagementForm=new ManagementForm(this.resource);
}//GEN-LAST:event_Resource_jDesktopPaneMouseClicked

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
this.save();
    }//GEN-LAST:event_formWindowClosing

// this method is not used but it is the code of the (add selected plan item) button which was deleted
private void add_selected_plan_item()
{
     this.addPlanToBePerformed();
     this.clear_performedPlan();
     //----------------------------------------------------------------------------
     this.populate_performedPlan();
     this.populateForm();
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

//-----------------------------------------------------------------------------
//*****************************************************************************
//                     Performed Activity Duration Calculations
//*****************************************************************************
//-----------------------------------------------------------------------------
private void addPerformedActivityDuration()
{
    if (this.newPerformedActivity==true &&
        Global.project!=null &&
        Global.project.getSupplements()!=null &&
        Global.project.getSupplements().getManagement()!=null &&
        Global.project.getSupplements().getManagement().getConstraint()!=null)
        {
           if (this.performedActivity!=null)
           {
              Double currentElapsedTime=Global.project.getSupplements().getManagement().getConstraint().getElapsedTime();
              Double totalDuration=Global.project.getSupplements().getManagement().getConstraint().getTotalDuration();
              Double duration=0.0;

              if (this.performedActivity!=null)
                  duration=this.performedActivity.getDuration();

              Double newElapsedTime=currentElapsedTime+duration;

              if (newElapsedTime>totalDuration)
                  JOptionPane.showMessageDialog(null, "Elapsed time exceeded the project Total duration,"
                                                   +" Elapsed Time= "+newElapsedTime
                                                   +" Project Planned Duratione = "+totalDuration);

             Global.project.getSupplements().getManagement().getConstraint().setElapsedTime(newElapsedTime);
           }
        }
}

//*****************************************************************************
private void deductPerformedActivityDuration()
{
   if (Global.project!=null &&
                    Global.project.getSupplements()!=null &&
                    Global.project.getSupplements().getManagement()!=null &&
                    Global.project.getSupplements().getManagement().getConstraint()!=null)
                    {
                       if (this.performedActivity!=null)
                       {
                          Double currentElapsedTime=Global.project.getSupplements().getManagement().getConstraint().getElapsedTime();
                          Double totalDuration=Global.project.getSupplements().getManagement().getConstraint().getTotalDuration();
                          Double duration=0.0;

                          if (this.performedActivity!=null)
                              duration=this.performedActivity.getDuration();

                          Double newElapsedTime=currentElapsedTime-duration;

                          if (newElapsedTime>totalDuration)
                              JOptionPane.showMessageDialog(null, "Elapsed time exceeded the project Total duration,"
                                                               +" Elapsed Time= "+newElapsedTime
                                                               +" Project Planned Duratione = "+totalDuration);

                         Global.project.getSupplements().getManagement().getConstraint().setElapsedTime(newElapsedTime);
                       }
                    }
}

    
private void addPlanToBePerformed()
{
//----------------------------------------------------------------------------
boolean exists=false;
if (this.phase!=null && this.phase.getPerforming()!=null &&
this.phase.getPerforming().getPerformedActivitiesList()!=null)

for (Object o:this.phase.getPerforming().getPerformedActivitiesList())
{
 PerformedActivity pa=(PerformedActivity) o;
 Plan p=pa.getPerformedPlan();

 if (p !=null && p.getCustomisedPlanItem()!=null &&
     p.getCustomisedPlanItem().getDescription()!=null &&
     this.plan!=null && this.plan.getCustomisedPlanItem() !=null &&
     this.plan.getCustomisedPlanItem().getDescription()!=null)
     if (p.getCustomisedPlanItem().getDescription().equals(this.plan.getCustomisedPlanItem().getDescription()))
         exists=true;
}

//----------------------------------------------------------------------------
if (exists)
{
    JOptionPane.showMessageDialog(null, "Plan Item Aleraedy performed ...or added to performed Activity!");
    this.plan=null;
    this.customisedPlanItem=null;
    this.customisedSubPlanItem=null;
}
else
{
//this.save_plan();
}

}

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new PerformingForm().setVisible(true);
            }
        });
    }


//COMPOCOMPOCOMPOCOMPOCOMPOCOMPOCOMPOCOMPOCOMPOCOMPOCOMPOCOMPOCOMPOCOMPOCOMPOC
//                       Get Planned Objectives Model
//COMPOCOMPOCOMPOCOMPOCOMPOCOMPOCOMPOCOMPOCOMPOCOMPOCOMPOCOMPOCOMPOCOMPOCOMPOC

public  DefaultComboBoxModel getPlanModel()
{
  DefaultComboBoxModel dcm=null;
  ArrayList<Plan> planList=new ArrayList<Plan>();
  String[] planArrayNames = null;
  if (this.phase!=null&&this.phase.getPlanning()!=null)
  {
    if ( this.phase.getPlanning().getPlanList().size()>0)
      {
        planList=this.phase.getPlanning().getPlanList();
        int size=planList.size();
        planArrayNames = new String[size];
        int i=0;
        for (Object o:planList)
            {
              Plan thisPlan=(Plan) o;
              if (thisPlan!=null && thisPlan.getCustomisedPlanItem()!=null && thisPlan.getCustomisedPlanItem().getDescription()!=null)
                  planArrayNames[i]=thisPlan.getCustomisedPlanItem().getDescription();
              i=i+1;
             }
        dcm=new DefaultComboBoxModel(planArrayNames);
        return dcm;
        }
     else
      {
        String names[] ={"<No plan available>"};
        dcm=new DefaultComboBoxModel(names);
        return dcm;
      }
  }
 else
    {
    String names[] ={"<No plan available>"};
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
              if (thisResource!=null && thisResource.getResourceType()!=null)
                  result=thisResource.getResourceType().toString()+"  ";
              if (thisResource!=null && thisResource.getDescription()!=null)
                   result=result+ thisResource.getDescription();
              resourcesArrayNames[i]=result;
              i=i+1;
             }
        dcm=new DefaultComboBoxModel(resourcesArrayNames);
        return dcm;
        }
     else
      {
        String names[] ={"<No resources available>"};
        dcm=new DefaultComboBoxModel(names);
        return dcm;
      }
  }
 else
    {
    String names[] ={"<No resources available>"};
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
  if ( this.performedActivity!=null && this.performedActivity.getResources()!=null )
  {
      if (this.performedActivity.getResources().getResourcesList().size()>0)
      {
        resourcesList=this.performedActivity.getResources().getResourcesList();
        int size=resourcesList.size();
        resourcesArrayNames = new String[size];
        int i=0;
        for (Object o:resourcesList)
            {
              Resource thisResource=(Resource) o;
              String result="";
              if (thisResource!=null && thisResource.getResourceType()!=null)
                  result=thisResource.getResourceType().toString()+"  ";
              if (thisResource!=null &&thisResource.getDescription()!=null)
                   result=result+ thisResource.getDescription();
              resourcesArrayNames[i]=result;
              i=i+1;
             }
        dcm=new DefaultComboBoxModel(resourcesArrayNames);
        return dcm;
        }
     else
      {
        String names[] ={"<No selected resources yet>"};
        dcm=new DefaultComboBoxModel(names);
        return dcm;
      }
  }
 else
    {
    String names[] ={"<No selected resources yet>"};
    dcm=new DefaultComboBoxModel(names);
     return dcm;
    }
 }

//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
//                       Reusable in Performed
//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

//COMPOCOMPOCOMPOCOMPOCOMPOCOMPOCOMPOCOMPOCOMPOCOMPOCOMPOCOMPOCOMPOCOMPOCOMPOC
//                       Get Project Resources Model
//COMPOCOMPOCOMPOCOMPOCOMPOCOMPOCOMPOCOMPOCOMPOCOMPOCOMPOCOMPOCOMPOCOMPOCOMPOC
public  DefaultComboBoxModel getResourcesModel()
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
              if (thisResource!=null && thisResource.getResourceType()!=null)
                  result=thisResource.getResourceType().toString()+"  ";
              if (thisResource!=null && thisResource.getDescription()!=null)
                   result=result+ thisResource.getDescription();
              resourcesArrayNames[i]=result;
              i=i+1;
             }
        dcm=new DefaultComboBoxModel(resourcesArrayNames);
        return dcm;
        }
     else
      {
        String names[] ={"<No resources are available>"};
        dcm=new DefaultComboBoxModel(names);
        return dcm;
      }
  }
 else
    {
    String names[] ={"<No resources are available>"};
    dcm=new DefaultComboBoxModel(names);
     return dcm;
    }
 }

//COMPOCOMPOCOMPOCOMPOCOMPOCOMPOCOMPOCOMPOCOMPOCOMPOCOMPOCOMPOCOMPOCOMPOCOMPOC
//                       Get Planned Resources Model
//COMPOCOMPOCOMPOCOMPOCOMPOCOMPOCOMPOCOMPOCOMPOCOMPOCOMPOCOMPOCOMPOCOMPOCOMPOC
public  DefaultComboBoxModel getPlannedResourcesModel()
{
  DefaultComboBoxModel dcm=null;
  ArrayList<Resource> resourcesList=new ArrayList<Resource>();
  String[] resourcesArrayNames = null;
  if ( this.plan !=null && this.plan.getResources()!=null )
  {
      if (this.plan.getResources().getResourcesList().size()>0)
      {
        resourcesList=this.plan.getResources().getResourcesList();
        int size=resourcesList.size();
        resourcesArrayNames = new String[size];
        int i=0;
        for (Object o:resourcesList)
            {
              Resource thisResource=(Resource) o;
              String result="";
              if (thisResource!=null &&  thisResource.getResourceType()!=null)
                  result=thisResource.getResourceType().toString()+"  ";
              if (thisResource!=null && thisResource.getDescription()!=null)
                   result=result+ thisResource.getDescription();
              resourcesArrayNames[i]=result;
              i=i+1;
             }
        dcm=new DefaultComboBoxModel(resourcesArrayNames);
        return dcm;
        }
     else
      {
        String names[] ={"<No planned resources available>"};
        dcm=new DefaultComboBoxModel(names);
        return dcm;
      }
  }
 else
    {
    String names[] ={"<No planned resources available>"};
    dcm=new DefaultComboBoxModel(names);
     return dcm;
    }
 }

//COMPOCOMPOCOMPOCOMPOCOMPOCOMPOCOMPOCOMPOCOMPOCOMPOCOMPOCOMPOCOMPOCOMPOCOMPOC
//                       Get Analyst Model
//COMPOCOMPOCOMPOCOMPOCOMPOCOMPOCOMPOCOMPOCOMPOCOMPOCOMPOCOMPOCOMPOCOMPOCOMPOC

public  DefaultComboBoxModel getAnalystsModel()
{
  DefaultComboBoxModel dcm=null;
  ArrayList<Analyst> analystsList=new ArrayList<Analyst>();
  String[] analystArrayNames = null;
  if (Global.project!=null && Global.project.getSupplements()!=null
          && Global.project.getSupplements().getHumanInteraction()!=null )
  {
      if (  Global.project.getSupplements().getHumanInteraction().getAnalystsList().size()>0)
      {
        analystsList=Global.project.getSupplements().getHumanInteraction().getAnalystsList();
        int size=analystsList.size();
        analystArrayNames = new String[size];
        int i=0;
        for (Object o:analystsList)
            {
              Analyst visitedAnalyst=(Analyst) o;
              analystArrayNames[i]=
                      visitedAnalyst.getRole()+", " +visitedAnalyst.getLevel()+
                      "["+visitedAnalyst.getName()+", "+ visitedAnalyst.getContactDetails()+"]";
              i=i+1;
             }
        dcm=new DefaultComboBoxModel(analystArrayNames);
        return dcm;
        }
     else
      {
        String names[] ={"<No analyst available yet>"};
        dcm=new DefaultComboBoxModel(names);
        return dcm;
      }
  }
 else
    {
    String names[] ={"<No analyst available yet>"};
    dcm=new DefaultComboBoxModel(names);
     return dcm;
    }
 }


//COMPOCOMPOCOMPOCOMPOCOMPOCOMPOCOMPOCOMPOCOMPOCOMPOCOMPOCOMPOCOMPOCOMPOCOMPOC
//                       Get Project Resources Model
//COMPOCOMPOCOMPOCOMPOCOMPOCOMPOCOMPOCOMPOCOMPOCOMPOCOMPOCOMPOCOMPOCOMPOCOMPOC
public  DefaultComboBoxModel getSourcesModel()
{
  DefaultComboBoxModel dcm=null;
  ArrayList<Source> sourcesList=new ArrayList<Source>();
  String[] sourcesArrayNames = null;
  if (Global.project!=null && Global.project.getSources()!=null)
  {
      if ( Global.project.getSources().getSourcesList().size()>0)
      {
        sourcesList=Global.project.getSources().getSourcesList();
        int size=sourcesList.size();
        sourcesArrayNames = new String[size];
        int i=0;
        for (Object o:sourcesList)
            {
            String result="";
             if (o.getClass().getSimpleName().equals("InternalSource"))
              {
                  InternalSource thisSource=(InternalSource) o;
                  result=thisSource.toString();
              }
              else if (o.getClass().getSimpleName().equals("ExternalSource"))
              {
                  ExternalSource thisSource=(ExternalSource) o;
                  result=thisSource.toString();
              }
              else if (o.getClass().getSimpleName().equals("HumanSource"))
              {
                  HumanSource thisSource=(HumanSource) o;
                  result=thisSource.toString();
              }
              sourcesArrayNames[i]=result;
              i=i+1;
              //--------------------------------------------------------------
             }
        dcm=new DefaultComboBoxModel(sourcesArrayNames);
        return dcm;
        }
     else
      {
        String names[] ={"<No sources available>"};
        dcm=new DefaultComboBoxModel(names);
        return dcm;
      }
  }
 else
    {
    String names[] ={"<No sources available>"};
    dcm=new DefaultComboBoxModel(names);
     return dcm;
    }
 }

//COMPOCOMPOCOMPOCOMPOCOMPOCOMPOCOMPOCOMPOCOMPOCOMPOCOMPOCOMPOCOMPOCOMPOCOMPOC
//                       Get Project Resources Model
//COMPOCOMPOCOMPOCOMPOCOMPOCOMPOCOMPOCOMPOCOMPOCOMPOCOMPOCOMPOCOMPOCOMPOCOMPOC
public  DefaultComboBoxModel getSelectedSolutionEvidencesModel()
{
  DefaultComboBoxModel dcm=null;
  ArrayList<Source> selectedEvidencesList=new ArrayList<Source>();
  String[] sourcesArrayNames = null;
  if (this.solution!=null)
  {
      if ( this.solution.getEvidencesList().size()>0)
      {
        selectedEvidencesList=this.solution.getEvidencesList();
        int size=selectedEvidencesList.size();
        sourcesArrayNames = new String[size];
        int i=0;
        for (Object o:selectedEvidencesList)
            {
              String result="";
             if (o.getClass().getSimpleName().equals("InternalSource"))
              {
                  InternalSource thisSource=(InternalSource) o;
                  result=thisSource.toString();
              }
              else if (o.getClass().getSimpleName().equals("ExternalSource"))
              {
                  ExternalSource thisSource=(ExternalSource) o;
                  result=thisSource.toString();
              }
              else if (o.getClass().getSimpleName().equals("HumanSource"))
              {
                  HumanSource thisSource=(HumanSource) o;
                  result=thisSource.toString();
              }

              sourcesArrayNames[i]=result;
              i=i+1;
              //--------------------------------------------------------------
             }
        dcm=new DefaultComboBoxModel(sourcesArrayNames);
        return dcm;
        }
     else
      {
        String names[] ={"<No evidences selected yet>"};
        dcm=new DefaultComboBoxModel(names);
        return dcm;
      }
  }
 else
    {
    String names[] ={"<No resources are available>"};
    dcm=new DefaultComboBoxModel(names);
     return dcm;
    }
 }



//COMPOCOMPOCOMPOCOMPOCOMPOCOMPOCOMPOCOMPOCOMPOCOMPOCOMPOCOMPOCOMPOCOMPOCOMPOC
//                       Get Project Resources Model
//COMPOCOMPOCOMPOCOMPOCOMPOCOMPOCOMPOCOMPOCOMPOCOMPOCOMPOCOMPOCOMPOCOMPOCOMPOC
public  DefaultComboBoxModel getSelectedJustificationEvidencesModel()
{
  DefaultComboBoxModel dcm=null;
  ArrayList<Source> selectedJustificationEvidencesList=new ArrayList<Source>();
  String[] justificationArrayNames = null;
  if (this.justification!=null)
  {
      if (this.justification.getEvidencesList().size()>0)
      {
        selectedJustificationEvidencesList=this.justification.getEvidencesList();
        int size=selectedJustificationEvidencesList.size();
        justificationArrayNames = new String[size];
        int i=0;
        for (Object o:selectedJustificationEvidencesList)
            {
              String result="";
             if (o!=null && o.getClass().getSimpleName().equals("InternalSource"))
              {
                  InternalSource thisSource=(InternalSource) o;
                  result=thisSource.toString();
              }
              else if (o!=null && o.getClass().getSimpleName().equals("ExternalSource"))
              {
                  ExternalSource thisSource=(ExternalSource) o;
                  result=thisSource.toString();
              }
              else if (o!=null && o.getClass().getSimpleName().equals("HumanSource"))
              {
                  HumanSource thisSource=(HumanSource) o;
                  result=thisSource.toString();
              }
              justificationArrayNames[i]=result;
              i=i+1;
             }
        dcm=new DefaultComboBoxModel(justificationArrayNames);
        return dcm;
        }
     else
      {
        String names[] ={"<No justification evidences yet>"};
        dcm=new DefaultComboBoxModel(names);
        return dcm;
      }
  }
 else
    {
    String names[] ={"<No justification yet>"};
    dcm=new DefaultComboBoxModel(names);
     return dcm;
    }
 }



//COMPOCOMPOCOMPOCOMPOCOMPOCOMPOCOMPOCOMPOCOMPOCOMPOCOMPOCOMPOCOMPOCOMPOCOMPOC
//                       Get Project Resources Model
//COMPOCOMPOCOMPOCOMPOCOMPOCOMPOCOMPOCOMPOCOMPOCOMPOCOMPOCOMPOCOMPOCOMPOCOMPOC
public  DefaultComboBoxModel getSelectedCausesModel()
{
  DefaultComboBoxModel dcm=null;
  ArrayList<String> selectedCausesList=new ArrayList<String>();
  String[] causesArrayNames = null;
  if (this.reason!=null)
  {
      if (this.reason.getCausesList().size()>0)
      {
        selectedCausesList=this.reason.getCausesList();
        int size=selectedCausesList.size();
        causesArrayNames = new String[size];
        int i=0;
        for (Object o:selectedCausesList)
            {
              //--------------------------------------------------------------
              String thisCause=(String) o;
              //--------------------------------------------------------------
              causesArrayNames[i]=thisCause;
              i=i+1;
              //--------------------------------------------------------------
             }
        dcm=new DefaultComboBoxModel(causesArrayNames);
        return dcm;
        }
     else
      {
        String names[] ={"<No causes traced yet>"};
        dcm=new DefaultComboBoxModel(names);
        return dcm;
      }
  }
 else
    {
    String names[] ={"<No reasons recorded yet>"};
    dcm=new DefaultComboBoxModel(names);
     return dcm;
    }
 }




    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea Analyst_Contacts_jTextArea;
    private javax.swing.JComboBox Analyst_Level_jComboBox;
    private javax.swing.JTextField Analyst_Name_jTextField;
    private javax.swing.JComboBox Analyst_Role_jComboBox;
    private javax.swing.JList Analysts_jList;
    private javax.swing.JDesktopPane Control_jDesktopPane;
    private javax.swing.JDesktopPane Control_jDesktopPane1;
    private javax.swing.JButton Delete_jButton;
    private javax.swing.JButton First_jButton;
    private javax.swing.JDesktopPane Justification_Source_jDesktopPane;
    private javax.swing.JDesktopPane Justification_Sources_Control_jDesktopPane;
    private javax.swing.JButton Justification_Sources_Edit_jButton;
    private javax.swing.JButton Justification_Sources_Select_jButton;
    private javax.swing.JButton Justification_Sources_UnSelect_jButton;
    private javax.swing.JList Justification_Sources_jList;
    private javax.swing.JTextPane Justification_jTextPane;
    private javax.swing.JButton Last_jButton;
    private javax.swing.JButton Next_jButton;
    private javax.swing.JButton Outcome_jButton;
    private javax.swing.JLabel PerformedActivityDuration_jLabel;
    private javax.swing.JTextField PerformedActivity_Duration_jTextField;
    private javax.swing.JPanel Performed_Plan_jPanel;
    private javax.swing.JButton Performer_edit_jButton;
    private javax.swing.JDesktopPane Performing_Customised_info_jDesktopPane1;
    private javax.swing.JTabbedPane Performing_Details_TabbedPane;
    private javax.swing.JDesktopPane Performing_Details_jDesktopPane_jDesktopPane;
    private javax.swing.JLabel Performing_JustificationDescription_jLabel1;
    private javax.swing.JLayeredPane Performing_Justification_jLayeredPane;
    private javax.swing.JLabel Performing_PerformedActivityDuration_jLabel1;
    private javax.swing.JTextField Performing_PerformedActivityDuration_jTextField1;
    private javax.swing.JLayeredPane Performing_Performer_jDesktopPane;
    private javax.swing.JDesktopPane Performing_Performer_jDesktopPane1;
    private javax.swing.JButton Performing_Problem_Add_jButton;
    private javax.swing.JDesktopPane Performing_Problem_Control_jDesktopPane;
    private javax.swing.JButton Performing_Problem_Delete_jButton;
    private javax.swing.JButton Performing_Problem_Next_jButton;
    private javax.swing.JButton Performing_Problem_Previous_jButton;
    private javax.swing.JDesktopPane Performing_Problem_jDesktopPane;
    private javax.swing.JLayeredPane Performing_Problem_jLayeredPane;
    private javax.swing.JLayeredPane Performing_jLayeredPane;
    private javax.swing.JList Planned_Resources_jList;
    private javax.swing.JLabel Planning_CustomisedPlanItem_jLabel;
    private javax.swing.JLabel Planning_CustomisedPlanSubItem_jLabel;
    private javax.swing.JTextPane Planning_Customised_Description_jTextPane;
    private javax.swing.JCheckBox Planning_Customised_Optional_jCheckBox;
    private javax.swing.JCheckBox Planning_Customised_SubPlanItem_jCheckBox;
    private javax.swing.JTextPane Planning_Customised_SubPlanItem_jTextPane;
    private javax.swing.JDesktopPane Planning_Objectives_Control_jDesktopPane1;
    private javax.swing.JDesktopPane Planning_Objectives_Control_jDesktopPane3;
    private javax.swing.JCheckBox Planning_Performed_jCheckBox;
    private javax.swing.JLabel Planning_Planner_Contacts_jLabel;
    private javax.swing.JLabel Planning_Planner_Level_jLabel;
    private javax.swing.JLabel Planning_Planner_Name_jLabel;
    private javax.swing.JLabel Planning_Planner_Role_jLabel;
    private javax.swing.JDesktopPane Planning_Planner_info_jDesktopPane;
    private javax.swing.JDesktopPane Planning_Planner_jDesktopPane;
    private javax.swing.JButton Planning_Preset_SubPlanItem_Next_jButton;
    private javax.swing.JButton Planning_Preset_SubPlanItem_Previous_jButton;
    private javax.swing.JDesktopPane Planning_Resource_jDesktopPane;
    private javax.swing.JButton Planning_Resources_Select_jButton;
    private javax.swing.JButton Planning_Resources_UnSelect_jButton;
    private javax.swing.JButton Previous_jButton;
    private javax.swing.JButton Problem_Refresh_jButton;
    private javax.swing.JTextPane Problem_jTextPane;
    private javax.swing.JButton Reason_TraceUndo_jButton;
    private javax.swing.JButton Reason_TracedTo_jButton;
    private javax.swing.JTextPane Reason_jTextPane;
    private javax.swing.JButton Refresh_Plan_jButton;
    private javax.swing.JButton Refresh_jButton;
    private javax.swing.JLabel Resource_Cost_jLabel;
    private javax.swing.JTextField Resource_Cost_jTextField;
    private javax.swing.JTextArea Resource_Description_jTextArea;
    private javax.swing.JButton Resource_Edit_jButton;
    private javax.swing.JLabel Resource_Quantity_jLabel;
    private javax.swing.JTextField Resource_Quantity_jTextField;
    private javax.swing.JComboBox Resource_Type_jComboBox;
    private javax.swing.JLabel Resource_Type_jLabel;
    private javax.swing.JDesktopPane Resource_jDesktopPane;
    private javax.swing.JList Resources_jList;
    private javax.swing.JButton Save_jButton;
    private javax.swing.JList Selected_Causes_jList;
    private javax.swing.JList Selected_Justification_Sources_jList;
    private javax.swing.JList Selected_Resources_jList;
    private javax.swing.JList Selected_Solution_Evidences_jList;
    private javax.swing.JDesktopPane Solution_Source_jDesktopPane;
    private javax.swing.JButton Solution_Sources_Edit_jButton;
    private javax.swing.JButton Solution_Sources_Select_jButton;
    private javax.swing.JButton Solution_Sources_UnSelect_jButton;
    private javax.swing.JList Solution_Sources_jList;
    private javax.swing.JDesktopPane Solution_jDesktopPane;
    private javax.swing.JTextPane Solution_jTextPane;
    private javax.swing.JLayeredPane jLayeredPane39;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane15;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane29;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane32;
    private javax.swing.JScrollPane jScrollPane34;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane62;
    private javax.swing.JScrollPane jScrollPane68;
    private javax.swing.JScrollPane jScrollPane69;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JMenuBar menuBar;
    // End of variables declaration//GEN-END:variables


 //***************************************************************************
 private void save_customised_subPlan()
 {
     if (this.plan!=null && this.customisedPlanItem!=null)
    {
        if(this.customisedSubPlanItem==null)
            this.customisedSubPlanItem= new PlanItem();

        this.customisedSubPlanItem.setDescription(this.Planning_Customised_SubPlanItem_jTextPane.getText());
        this.customisedSubPlanItem.setOptional(this.Planning_Customised_SubPlanItem_jCheckBox.isSelected());
        this.customisedPlanItem.saveSub(this.customisedSubPlanItem);
        this.plan.setCustomisedPlanItem(this.customisedPlanItem);
    }  
 }

//SSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSS
//****************************************************************************
//                              SAVE
//****************************************************************************
//SSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSS
 private void save()
{
 if (this.phase.getPerforming()==null)
     this.phase.setPerforming(new Performing());

  if (this.performedActivity==null && !this.Planning_Customised_Description_jTextPane.getText().equals(""))
      this.performedActivity=new PerformedActivity();

 if (performedActivity!=null)
 {
 //*****************************************************************************
    if(this.problem==null)
          this.problem= new Problem();

    if (this.problem!=null)
    {
                try {
                    if ((!this.Problem_jTextPane.getText().equals("")) && (this.Problem_jTextPane.getText() != null))
                        this.problem.setDescription(this.Problem_jTextPane.getText());
                    
                    if (this.reason == null)
                        this.reason = new Reason();

                    if ((!this.Reason_jTextPane.getText().equals("")) && (this.Reason_jTextPane.getText() != null))
                        this.reason.setExplanation(this.Reason_jTextPane.getText());
                    
                    /*
                     if (this.cause != null) 
                        this.reason.save(this.cause);
                     *
                     */
                    
                    if (this.reason != null)
                        this.problem.setReason(this.reason.clone());
                   
                    if (this.solution == null)
                        this.solution = new Solution();
                    
                    if ((!this.Solution_jTextPane.getText().equals("")) && (this.Solution_jTextPane.getText() != null)) {
                        this.solution.setDescription(this.Solution_jTextPane.getText());
                    }
                    this.problem.setSolution(this.solution);
                    //---------------------------------------------------------------------
                    if (this.performedActivity.getProblems() == null)
                        this.performedActivity.setProblems(new Problems());

                    this.performedActivity.getProblems().save(problem);

                } catch (CloneNotSupportedException ex) {
                    Logger.getLogger(PerformingForm.class.getName()).log(Level.SEVERE, null, ex);
                }
    }

//*****************************************************************************
    if (this.analyst!=null)
        this.performedActivity.setPerformer(analyst);

//*****************************************************************************
    if (this.justification == null)
        this.justification = new Justification();

    if (this.Justification_jTextPane.getText() != null && !this.Justification_jTextPane.getText().equals(""))
        this.justification.setRational(this.Justification_jTextPane.getText());

    if (this.justification != null)
        this.performedActivity.setJustification(this.justification);

//*****************************************************************************
    if (this.plan!=null)
    {
        this.plan.setPerformed(Planning_Performed_jCheckBox.isSelected());
        this.performedActivity.setPerformedPlan(plan);
    }
    //*****************************************************************************
/*
    if ( this.performedActivity!=null && this.performedActivity.getProblems()==null)
         this.performedActivity.setProblems(new Problems());

    if ( (this.performedActivity!=null) && (this.performedActivity.getProblems()!=null))
          this.performedActivity.getProblems().save(problem);
*/
    if (this.PerformedActivity_Duration_jTextField.getText() != null && !this.PerformedActivity_Duration_jTextField.getText().equals(""))
       this.performedActivity.setDuration(Double.parseDouble(PerformedActivity_Duration_jTextField.getText()));
    // add the activity duration to the elapsed time
    this.addPerformedActivityDuration();

this.phase.getPerforming().save(this.performedActivity);
//*****************************************************************************
this.phase.save();
}

 }

 //RRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRR
//****************************************************************************
//                              Refresh
//****************************************************************************
//RRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRR

 private void refresh_performedActivity()
{
if (this.phase!=null && this.phase.getPerforming() != null && this.phase.getPerforming().getFirst()!=null)
    {
         this.performedActivity=this.phase.getPerforming().getFirst();
         this.refresh_performedActivity_details();
         this.populate_performedPlan();
    }
}

 //RRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRR
 //                         Refresh Plan Details
 //RRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRR

 private void refresh_performedActivity_details()
 {
     if(this.performedActivity!=null)
             if (this.performedActivity.getPerformedPlan()!=null)
                this.plan = this.performedActivity.getPerformedPlan();
             else
                 this.plan =null;

      if ( this.plan!=null)
          if (this.plan.getCustomisedPlanItem()!=null)
              this.customisedPlanItem=this.plan.getCustomisedPlanItem();
          else
              this.customisedPlanItem=null;


     if ( this.customisedPlanItem!=null && this.customisedPlanItem.getSubPlanList().size()>0)
         if (this.customisedPlanItem.getFirstSub()!=null)
             this.customisedSubPlanItem=this.customisedPlanItem.getFirstSub();
         else
             this.customisedSubPlanItem=null;
        //--------------------------------------------------------------------
        //                      Refreshing Analyst
        //--------------------------------------------------------------------
        if (this.performedActivity!=null)
            if (this.performedActivity.getPerformer()!=null)
                this.analyst=this.performedActivity.getPerformer();
            else
                this.analyst=null;
        //--------------------------------------------------------------------
        //                      Refreshing Resources
        //--------------------------------------------------------------------
        if (this.performedActivity!=null)
            if (this.performedActivity.getResources()!=null)
                this.resource=this.performedActivity.getResources().getFirst();
            else
                this.resource=null;
        //--------------------------------------------------------------------
        this.Resources_jList.setModel(this.getResourcesModel());
        this.Selected_Resources_jList.setModel(this.getPlannedResourcesModel());

        //--------------------------------------------------------------------
        //                      Refreshing Problem
        //--------------------------------------------------------------------
        
        if (this.performedActivity!=null && this.performedActivity.getProblems()!=null)
        {
            this.refresh_problem();
            this.Selected_Causes_jList.setModel(this.getSelectedCausesModel());
        }
        //--------------------------------------------------------------------
        this.Solution_Sources_jList.setModel(this.getSourcesModel());
        this.Selected_Solution_Evidences_jList.setModel(this.getSelectedSolutionEvidencesModel());                
        
        //--------------------------------------------------------------------
        //                    Refreshing Justification
        //--------------------------------------------------------------------
        if (this.performedActivity!=null && this.performedActivity.getJustification()!=null)
            this.justification=this.performedActivity.getJustification();
        //--------------------------------------------------------------------
        if (this.justification !=null && this.justification.getEvidencesList().size()>0)
            this.justificationEvidence=this.justification.getFirstEvidence();
        //--------------------------------------------------------------------
        this.Justification_Sources_jList.setModel(this.getSourcesModel());
        this.Selected_Justification_Sources_jList.setModel(this.getSelectedJustificationEvidencesModel());
                    
        this.refresh_problem();
 }

//DDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDD
//****************************************************************************
//                              Delete Plan
//DDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDD
//****************************************************************************
 private void delete_performedActivity()
    {
    if (this.plan!=null && this.phase.getPerforming()!=null)
    {
      int index= this.phase.getPerforming().getPerformedActivitiesList().indexOf(this.performedActivity);
      /*
      //-----------------------------------------------------------------------
      // un perform the deleted plan
      this.plan.setPerformed(false);
      Planning planning=this.phase.getPlanning();
      planning.save(plan);
      this.phase.setPlanning(planning);
      this.phase.save();
     //-----------------------------------------------------------------------
      */
          if (this.phase.getPerforming().delete(this.performedActivity));
        {
            if (this.phase.getPerforming().getPerformedActivitiesList().size()>0)
            {
                if (index==0 && this.phase.getPerforming().getPerformedActivity(index)!=null)
                    this.performedActivity = this.phase.getPerforming().getPerformedActivity(index);
                else if(index>0)
                    this.performedActivity = this.phase.getPerforming().getPerformedActivity(index-1);

                //this.plan=this.performedActivity.getPerformedPlan();
                
                //this.refresh_plan_details();
                this.refresh_performedActivity_details();
                this.populate_performedPlan();
            }
            else
                this.clear_performedPlan();
        }
     //this.phase.save();
     //------------------------------------------------------------------------
     // to disable the details if the master is not there
     //------------------------------------------------------------------------
     if (this.phase.getPlanning().getPlanList().size()==0)
     {
         this.phase.setPlanning(null);
         this.populateForm();
      }
 }
 this.populate_performedPlan();
 }

//FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF
//****************************************************************************
//                            First & Last Plan
//****************************************************************************
//FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF

 //****************************************************************************
 private void first_performedActivity()
 {
    if (this.phase.getPerforming()!=null && this.phase.getPerforming().getFirst()!=null)
    {
        this.save();
        this.clear_performedActivity_details();
        this.performedActivity=this.phase.getPerforming().getFirst();
        this.refresh_performedActivity_details();
        this.populate_performedPlan();
  }
 }

 //****************************************************************************
 private void last_performedActivity()
 {
    if (this.phase.getPerforming()!=null && this.phase.getPerforming().getLast()!=null)
    {        
        this.save();
        this.clear_performedActivity_details();
        this.performedActivity=this.phase.getPerforming().getLast();
        this.refresh_performedActivity_details();
        this.populate_performedPlan();
  }     
 }


//NNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNN
//****************************************************************************
//                                Next Plan
//****************************************************************************
//NNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNN
 private void next_performedActivity()
    {
      if(this.phase.getPerforming()!=null &&
         this.performedActivity!=null &&
         this.phase.getPerforming().getNext(this.performedActivity)!=null)
                {
                    this.save();
                    this.clear_performedActivity_details();
                    this.performedActivity=this.phase.getPerforming().getNext(this.performedActivity);
                    this.refresh_performedActivity_details();           
                    this.populate_performedPlan();                    
                }                    
        //else
            //System.out.print("No Phase Performing is found ");
    }

//NNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNN
//****************************************************************************
//                                Previous Plan
//****************************************************************************
//NNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNN
 private void previous_performedActivity()
    {
      if(this.phase.getPerforming()!=null &&
         this.performedActivity!=null &&
         this.phase.getPerforming().getPrevious(this.performedActivity)!=null
         )
                {
                    //this.save_performedActivity();
                    this.save();
                    this.clear_performedActivity_details();
                    this.performedActivity=this.phase.getPerforming().getPrevious(this.performedActivity);
                    this.refresh_performedActivity_details();
                    this.populate_performedPlan();
                }
        //else
            //System.out.print("No Phase Performing is found ");
    }


//NNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNN
//****************************************************************************
//                                Next problem
//****************************************************************************
//NNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNN
 private void next_problem()
    {
      if(this.performedActivity!=null && this.performedActivity.getProblems()!=null)
      {
          this.save();
          this.clear_problem_details();
          this.problem=this.performedActivity.getProblems().getNext(this.problem);
          this.refresh_problem_details();
          this.populate_problem();
       }
        //else
            //System.out.print("No Phase problem yet!");
    }



//PPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPP
//****************************************************************************
//                     Previous Plan& Resource
//****************************************************************************
//PPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPP

 private void previous_problem()
    {
      if(this.performedActivity!=null && this.performedActivity.getProblems()!=null)
      {
           this.save();
           this.clear_problem_details();
           this.problem=this.performedActivity.getProblems().getPrevious(this.problem);
           this.refresh_problem_details();
           this.populate_problem();
           //this.populateForm();
       }
        //else
            //System.out.print("No Phase problem yet!");
    }


//CCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCC
//****************************************************************************
//                     Clear Performed Plan
//****************************************************************************
 //CCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCC

 private void clear_performedPlan()
{
     this.performedActivity= new PerformedActivity();
     //     
     this.plan=new Plan();
     //
     this.customisedPlanItem=null;
     this.customisedSubPlanItem=null;
     /*
     this.Performed_Plan_jPanel.setLocation(430, 10);
     this.Plan_jList.setVisible(true);
     this.Select_jButton.setVisible(true);
     */
     this.clear_performedActivity_details();
     //
     this.clear_PerformedPlanForm();

 }

 private void clear_performedActivity_details()
{
     this.plan=null;
     this.customisedPlanItem=null;
     this.customisedSubPlanItem=null;
     //
     this.analyst=null;
     this.problem=null;
     //
     //
     this.justification=null;
     //
     this.justificationEvidence=null;
     //
     this.clear_customised_subPlan();
     //
     this.clear_resource();
     //
     this.clear_problem();
     //
     this.clear_analyst();
     //     
     //
     this.clear_PerformedPlanForm();
     //
     this.populate_performedPlan();// another way to clear the form using the build in else statments in populate form
 }


 //CCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCC
 //                              Clear Problem
 //CCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCC
 private void clear_problem( )
{
     this.problem=null;
     this.clear_problem_details();
 }

 private void clear_problem_details()
{
     this.solution=null;
     this.solutionEvidence=null;
     this.reason=null;
     this.cause=null;
     this.clear_problemForm();
 }

 //CCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCC
 //                              Clear Problem Form
 //CCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCC
  private void clear_problemForm()
{
     //--------------------------------------------------------------------
     this.Selected_Solution_Evidences_jList.setSelectionBackground(Color.white);
     this.Solution_Sources_jList.setSelectionBackground(Color.white);
     this.Selected_Causes_jList.setSelectionBackground(Color.white);
     //--------------------------------------------------------------------
     this.Problem_jTextPane.setText("");
     //--------------------------------------------------------------------
     this.Reason_jTextPane.setText("");
     //--------------------------------------------------------------------
     this.Solution_jTextPane.setText("");
     //--------------------------------------------------------------------
     this.Resource_Type_jComboBox.setSelectedIndex(0);
     //--------------------------------------------------------------------
     String evidencesNames[] ={""};
     this.Selected_Solution_Evidences_jList.setModel(new DefaultComboBoxModel(evidencesNames));
     this.Selected_Solution_Evidences_jList.setSelectedValue(null,false);
     //--------------------------------------------------------------------
     String reasonsNames[] ={""};
     this.Selected_Causes_jList.setModel(new DefaultComboBoxModel(reasonsNames));
     this.Selected_Causes_jList.setSelectedValue(null,false);
 }


 //CCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCC
 //                              Clear Analyst
 //CCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCC
 private void clear_analyst( )
{
     this.clear_analystForm();
     this.analyst=null;

     this.Analysts_jList.setModel(this.getAnalystsModel());
     this.Analysts_jList.setSelectedValue(null,false);
 }
 private void clear_analystForm()
{
     this.Analyst_Name_jTextField.setText("");
     this.Analyst_Contacts_jTextArea.setText("");
     this.Analyst_Role_jComboBox.setSelectedIndex(0);
     this.Analyst_Level_jComboBox.setSelectedIndex(0);
 }


//CCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCC
//                              Clear thisResource
//CCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCC
private void clear_resource( )
{
     this.clear_resourceForm();
     this.resource=null;
 }
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


//PPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPP
//                         Populate Plan Form
//PPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPP
 private void populate_performedPlan()
{
       if (this.plan!=null)
         this.Planning_Performed_jCheckBox.setSelected(this.plan.isPerformed());

         if (this.customisedPlanItem!=null && this.customisedPlanItem.getDescription()!=null)
        {
            this.Planning_Customised_Description_jTextPane.setText(this.customisedPlanItem.getDescription());
            this.Planning_Customised_Optional_jCheckBox.setSelected(this.customisedPlanItem.isOptional());
         }
         else
           this.Planning_Customised_Description_jTextPane.setText("");
         //--------------------------------------------------------------------
         if (this.customisedPlanItem!=null && this.customisedSubPlanItem!=null && this.customisedSubPlanItem.getDescription()!=null)
         {
            this.Planning_Customised_SubPlanItem_jCheckBox.setSelected(customisedSubPlanItem.isOptional());
            this.Planning_Customised_SubPlanItem_jTextPane.setText(this.customisedSubPlanItem.getDescription());
         }
         else
            this.Planning_Customised_SubPlanItem_jTextPane.setText("");
         //-------------------------------------------------------------------- 
         
         //---------------------------------------------------------------------
         if (this.justification!=null && this.justification.getRational()!=null)
             this.Justification_jTextPane.setText(this.justification.getRational());
         else
             this.Justification_jTextPane.setText("");

        if (this.performedActivity!=null)
            this.PerformedActivity_Duration_jTextField.setText(String.valueOf(this.performedActivity.getDuration()));

         //*********************************************************************
         this.Selected_Justification_Sources_jList.setModel(this.getSelectedJustificationEvidencesModel());
         this.Selected_Resources_jList.setModel(this.getSelectedResourcesModel());
         //*********************************************************************
         this.populate_customised_subplan();
         this.populate_problem();         
         this.populate_resource();
         this.populate_analyst();         


//-----------------------------------------------------------------------------
this.populateForm();
}

//PPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPP
//                         Populate Resource Form
//PPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPP
 private void populate_analyst()
{
         if (analyst!=null && analyst.getName()!=null)
            this.Analyst_Name_jTextField.setText(analyst.getName());
         else
           this.Analyst_Name_jTextField.setText("");
         //--------------------------------------------------------------------
         if (analyst!=null && analyst.getContactDetails()!=null)
            this.Analyst_Contacts_jTextArea.setText(analyst.getContactDetails());
         else
           this.Analyst_Contacts_jTextArea.setText("");
         //--------------------------------------------------------------------
         if (analyst!=null && analyst.getLevel()!=null)
            this.Analyst_Level_jComboBox.setSelectedIndex(analyst.getLevel().ordinal()+1);
         else
             this.Analyst_Level_jComboBox.setSelectedIndex(0);
         //--------------------------------------------------------------------
         if (analyst!=null && analyst.getRole()!=null)
            this.Analyst_Role_jComboBox.setSelectedIndex(analyst.getRole().ordinal()+1);
         else
             this.Analyst_Role_jComboBox.setSelectedIndex(0);

     this.populateForm();
 }

  //RRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRR
//****************************************************************************
//                              Refresh Resource
//****************************************************************************
//RRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRR

 private void refresh_problem()
 {
    if (this.performedActivity!=null)
        if (this.performedActivity.getProblems()!=null &&  this.performedActivity.getProblems().getProblemsList().size()>0)
    {
        this.problem = this.performedActivity.getProblems().getFirst();
        this.refresh_problem_details();        
    }
    else
        this.problem=null;

    //else
        //System.out.print(" No Resource is available");
this.refresh_problem_details();
this.populate_problem();
}
//****************************************************************************
//****************************************************************************
 private void refresh_problem_details()
{
if (this.problem!=null)
    if (this.problem.getSolution()!=null)
        this.solution=this.problem.getSolution();
    else
        this.solution=null;

if (this.problem!=null)
  if (this.problem.getReason()!=null)
     {
        this.reason=this.problem.getReason();

        if (this.reason.getCausesList().size()>0)
            this.cause=(String) this.reason.getCausesList().get(0);
        else
            this.cause=null;
    }
  else
  {
      this.reason=null;
      this.cause=null;
   }

//
this.Selected_Causes_jList.setModel(this.getSelectedCausesModel());
//
this.Selected_Solution_Evidences_jList.setModel(this.getSelectedSolutionEvidencesModel());
//
}

//DDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDD
//****************************************************************************
//                              Delete Problem
//DDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDD
//****************************************************************************
 private void delete_problem()
   {
    if (this.problem!=null && this.performedActivity!=null  &&
        this.performedActivity.getProblems()!=null )
    {
        int index= this.performedActivity.getProblems().getProblemsList().indexOf(this.problem);
        if (this.performedActivity.getProblems().delete(this.problem)==0);
        {
            if (this.performedActivity.getProblems().getProblemsList().size()>0)
            {
                if (index==0)
                    this.problem = this.performedActivity.getProblems().getProblem(index);
                else
                    this.problem = this.performedActivity.getProblems().getProblem(index-1);

                this.refresh_problem_details();
                this.populate_problem();
            }
            else
                this.clear_problem();
        }

     this.save();
 }
this.populate_problem();
}

 //DDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDD
//****************************************************************************
//                           Delete Solution Evidence
//DDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDD
//****************************************************************************
 private void delete_solution_evidence()
   {
    if (this.solutionEvidence!=null &&
        this.problem!=null && this.problem.getSolution()!=null &&
        this.problem.getSolution().getEvidencesList().size()>0)
    {
        int index= this.problem.getSolution().getEvidencesList().indexOf(this.solutionEvidence);
        if (this.problem.getSolution().deleteEvidence(this.solutionEvidence)==0);
        {
            if (this.problem.getSolution().getEvidencesList().size()>0)
            {
                if (index==0)
                    this.solutionEvidence = this.problem.getSolution().getEvidence(index);
                else if (index>0)
                    this.solutionEvidence = this.problem.getSolution().getEvidence(index-1);
            }
        }

     this.save();
     this.Selected_Solution_Evidences_jList.setModel(this.getSelectedSolutionEvidencesModel());
 }
}

 //DDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDD
//****************************************************************************
//                           Delete Solution Evidence
//DDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDD
//****************************************************************************
 private void delete_justification_evidence()
   {
    if (this.performedActivity!=null &&this.justificationEvidence!=null && this.justification!=null &&
            this.justification.getEvidencesList().size()>0)
    {
        int index= this.performedActivity.getJustification().getEvidencesList().indexOf(this.justificationEvidence);
        if (this.performedActivity.getJustification().deleteEvidence(this.justificationEvidence)==0);
        {
            if (this.justification.getEvidencesList().size()>0)
            {
                if (index==0)
                    this.justificationEvidence = this.performedActivity.getJustification().getEvidence(index);
                else if (index>0)
                    this.justificationEvidence = this.performedActivity.getJustification().getEvidence(index-1);
            }
        }     
     this.save();
     this.Selected_Justification_Sources_jList.setModel(this.getSelectedJustificationEvidencesModel());
 }
}


  //DDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDD
//****************************************************************************
//                           Delete Problem Cause
//DDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDD
//****************************************************************************
 private void delete_cause()
   {
    if (this.cause!=null &&
        this.problem!=null && this.problem.getReason()!=null &&
        this.problem.getReason().getCausesList().size()>0)
    {
        int index= this.problem.getReason().getCausesList().indexOf(this.problem);
        if (this.problem.getReason().delete(this.cause)==0);
        {
            if (this.problem.getReason().getCausesList().size()>0)
            {
                if (index==0)
                    this.cause = this.problem.getReason().getCause(index);
                else if (index>0)
                    this.cause = this.problem.getReason().getCause(index-1);
            }
        }
     
     this.save();
     this.Selected_Causes_jList.setModel(this.getSelectedCausesModel());
 }
}



//DDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDD
//****************************************************************************
//                              Delete Resource
//DDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDD
//****************************************************************************
 private void delete_resource()
   {
    if (this.resource!=null && this.performedActivity!=null  &&
        this.performedActivity.getResources()!=null &&
        this.performedActivity.getResources().getResourcesList().size()>0)
    {
        int index= this.performedActivity.getResources().getResourcesList().indexOf(this.resource);
        if (this.performedActivity.getResources().delete(this.resource)==0);
        {
            if (this.performedActivity.getResources().getResourcesList().size()>0)
            {
                if (index==0)
                    this.resource = this.performedActivity.getResources().getResource(index);
                else if(index>0)
                    this.resource = this.performedActivity.getResources().getResource(index-1);

                this.populate_resource();
            }
            else
            this.clear_resource();
        }
        //this.save_resource();
        this.save();
 }
}


//CCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCC
//****************************************************************************
//                     Clear Source Resource & Source
//****************************************************************************
 //CCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCC


 private void clear_PerformedPlanForm()
{
    //------------------------------------------------------------------------
    this.Planning_Customised_Description_jTextPane.setText("");
    this.Planning_Customised_Optional_jCheckBox.setSelected(false);
    //------------------------------------------------------------------------
    this.Planning_Performed_jCheckBox.setSelected(false);
    //------------------------------------------------------------------------    
    this.clear_resourceForm();
    this.clear_analystForm();
    this.clear_problemForm();
    //------------------------------------------------------------------------
    this.populateForm();
 }

//CCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCC
//****************************************************************************
//                     Clear Resource Form
//****************************************************************************
 //CCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCC

 private void clear_resourceForm()
{
     this.Resource_Cost_jTextField.setText("");
     this.Resource_Quantity_jTextField.setText("");
     this.Resource_Description_jTextArea.setText("");
     this.Resource_Type_jComboBox.setSelectedIndex(0);

     String names[] ={"<No resources selected yet>"};
     this.Selected_Resources_jList.setModel(new DefaultComboBoxModel(names));
     this.Selected_Resources_jList.setSelectionBackground(Color.white);

     this.Resources_jList.setSelectionBackground(Color.white);
     this.Resources_jList.setSelectedValue(null,false);     
     
     this.Planned_Resources_jList.setSelectionBackground(Color.white);
     this.Planned_Resources_jList.setSelectedValue(null,false);


 }
//PPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPP
//                         Populate Resource Form
//PPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPP
 private void populate_problem()
{
     //this.clear_problemForm();
     //
     if (this.problem!=null)
     {
         this.Selected_Solution_Evidences_jList.setSelectionBackground(Color.white);
         this.Solution_Sources_jList.setSelectionBackground(Color.white);
         this.Selected_Causes_jList.setSelectionBackground(Color.white);
         //--------------------------------------------------------------------
         if (this.problem.getDescription()!=null)
            this.Problem_jTextPane.setText(this.problem.getDescription());
         else
           this.Problem_jTextPane.setText("");
         //--------------------------------------------------------------------
         if (this.problem.getReason()!=null)
            this.Reason_jTextPane.setText(this.problem.getReason().getExplanation());
         else
             this.Reason_jTextPane.setText("");
        //--------------------------------------------------------------------
        if (this.problem.getSolution()!=null)
            this.Solution_jTextPane.setText(this.problem.getSolution().getDescription());
        else
            this.Solution_jTextPane.setText("");         
    }
    //else
        //System.out.print(" No Resource found to populate");
   
    this.Selected_Causes_jList.setModel(this.getSelectedCausesModel());
    this.Solution_Sources_jList.setModel(this.getSourcesModel());
    this.Selected_Solution_Evidences_jList.setModel(this.getSelectedSolutionEvidencesModel());
    this.populateForm();
 }

//PPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPP
//                         Populate Resource Form
//PPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPP
 private void populate_resource()
{
     if (resource!=null)
     {
         this.Resources_jList.setSelectionBackground(Color.white);
         this.Planned_Resources_jList.setSelectionBackground(Color.white);
         this.Selected_Resources_jList.setSelectionBackground(Color.white);
         //--------------------------------------------------------------------
         if (resource.getDescription()!=null)
            this.Resource_Description_jTextArea.setText(resource.getDescription());
         else
           this.Resource_Description_jTextArea.setText("");
         //
         if (resource.getResourceType()!=null)
            this.Resource_Type_jComboBox.setSelectedIndex(resource.getResourceType().ordinal()+1);

         this.Resource_Cost_jTextField.setText(String.valueOf(resource.getCost()));

         this.Resource_Quantity_jTextField.setText(String.valueOf(resource.getQuantiy()));
     // this.Selected_Resources_jList.setSelectedIndex(0);

     }
    //else
    //System.out.print(" No Resource found to populate");
    //
    this.Planned_Resources_jList.setModel(this.getPlannedResourcesModel());
    this.populateForm();
 }


 //============================================================================
 private void populate_customised_subplan()
    {
      //--------------------------------------------------------------------
         if (this.customisedPlanItem!=null &&
                 this.customisedSubPlanItem!=null &&
                 this.customisedSubPlanItem.getDescription()!=null)
         {
            this.Planning_Customised_SubPlanItem_jCheckBox.setSelected(customisedSubPlanItem.isOptional());
            this.Planning_Customised_SubPlanItem_jTextPane.setText(this.customisedSubPlanItem.getDescription());
         }
         else
            this.Planning_Customised_SubPlanItem_jTextPane.setText("");
 }

  //CCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCC
 //                         Clear sub Plan Form
 //CCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCC
 //-----------------------------------------------------------------------------
//-----------------------------------------------------------------------------
private void clear_customised_subPlanForm()
{
    this.Planning_Customised_SubPlanItem_jTextPane.setText("");
    this.Planning_Customised_SubPlanItem_jCheckBox.setSelected(false);
}
//-----------------------------------------------------------------------------
private void clear_customised_subPlan()
{
       this.customisedSubPlanItem=null;
       clear_customised_subPlanForm();
}

//NNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNN
//****************************************************************************
//                     Next Customised SubPlan
//****************************************************************************
//NNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNN
 private void next_customised_subPlan()
    {
      if(this.phase.getPlanning()!=null)
        {
            if (this.plan!=null)
            {
                if (this.customisedPlanItem!=null && this.customisedSubPlanItem!=null)
                {
                    this.save_customised_subPlan();
                    this.customisedSubPlanItem=this.customisedPlanItem.getNextSub(customisedSubPlanItem);
                }
                else
                    this.customisedSubPlanItem=this.plan.getCustomisedPlanItem().getLastSub();
                this.populate_customised_subplan();
            }
        }
        //else
            //System.out.print("No Phase PreRequisits is found yet ");
    }


 //NNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNN
//****************************************************************************
//                          Previous Customised SubPlan
//****************************************************************************
//NNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNN
 private void previous_customised_subPlan()
    {
      if(this.phase.getPlanning()!=null)
        {
            if (this.plan!=null)
            {
                if (this.customisedPlanItem!=null && this.customisedSubPlanItem!=null)
                {
                    this.save_customised_subPlan();
                    this.customisedSubPlanItem=this.customisedPlanItem.getPreviousSub(customisedSubPlanItem);
                }
                else
                    this.customisedSubPlanItem=this.plan.getCustomisedPlanItem().getFirstSub();
               this.populate_customised_subplan();
            }
        }
        //else
            //System.out.print("No Phase PreRequisits is found yet ");
    }
}
