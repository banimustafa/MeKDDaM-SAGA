package gui.form;

import gui.form.phase.PhaseForm;
import global.DashBoard;
import process_model.issue.tracibility.Sources;
import project.Project;
import java.awt.BorderLayout;
import java.awt.Graphics;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import global.Config;
import global.Global;
import gui.form.supplement.HumanInteractionForm;
import gui.form.supplement.ManagementForm;
import gui.form.supplement.QualityForm;
import gui.form.supplement.SourceForm;
import gui.form.supplement.StandardsForm;
import gui.image.ProcessImagesPanel;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.KeyStroke;
import process_model.phase.Phase;
import toolbox.Tools;
import process_model.process.Process;
import process_model.supplement.Supplements;
import process_model.supplement.human_interaction.HumanInteraction;
import process_model.supplement.management.Management;
import process_model.supplement.quality.Quality;
import process_model.supplement.standard.Standards;
import toolbox.Tree;
import toolbox.datatools.XMLTools;
import weka.gui.ComponentHelper;

public class MainMenu extends javax.swing.JFrame {
    Graphics graphics;
    ProcessImagesPanel imagePanel=new ProcessImagesPanel(1);
    boolean rolledbaclk;
    private boolean isInput=false;
    //------------------------------------------------------------------------
    public MainMenu() {
    initComponents();
    imagePanel.setImage("");
    enhance_menu();
    this.imagePanel.setEnabled(false);
    if (Global.project!=null)
        this.populateForm();
    
    }

    private void enhance_menu()
    {    
    Project_Save_jMenuItem.setIcon(ComponentHelper.getImageIcon(Config.iconsLocation+"save.gif"));
    Project_Save_jMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_MASK));
    //
    Project_SaveAs_jMenuItem.setIcon(ComponentHelper.getImageIcon(Config.iconsLocation+"saveAs.png"));
    Project_SaveAs_jMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_MASK + KeyEvent.SHIFT_MASK));
    //
    Project_Load_jMenuItem.setIcon(ComponentHelper.getImageIcon(Config.iconsLocation+"open.gif"));
    Project_Load_jMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, KeyEvent.CTRL_MASK));
    //
    Project_New_jMenuItem.setIcon(ComponentHelper.getImageIcon(Config.iconsLocation+"new.gif"));
    Project_New_jMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, KeyEvent.CTRL_MASK));
    //
    Project_Exit_jMenuItem.setIcon(ComponentHelper.getImageIcon(Config.iconsLocation+"exit.gif"));
    Project_Exit_jMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, KeyEvent.CTRL_MASK));

    this.Standards_jMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_T, KeyEvent.CTRL_MASK));
    this.Quality_jMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, KeyEvent.CTRL_MASK));
    this.HumanInteraction_jMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H, KeyEvent.CTRL_MASK));
    this.Management_jMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_M, KeyEvent.CTRL_MASK));
    


    this.repaint();
    }



    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        desktopPane = new javax.swing.JDesktopPane();
        MeKDDaP = new javax.swing.JInternalFrame();
        jMenuBar1 = new javax.swing.JMenuBar();
        copyRightLabel = new java.awt.Label();
        Process_jPanel = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        process_jTextArea = new javax.swing.JTextArea();
        Process_Iterate_jButton1 = new javax.swing.JButton();
        Process_New_jButton1 = new javax.swing.JButton();
        Process_Load_jButton = new javax.swing.JButton();
        Process_Save_jButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        Process_Version_jComboBox = new javax.swing.JComboBox();
        Process_Tree_jButton = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        DescriptionLabel = new javax.swing.JLabel();
        jSeparator9 = new javax.swing.JSeparator();
        message2_jLabel1 = new javax.swing.JLabel();
        Project_Location_jTextField = new javax.swing.JTextField();
        projectNameLabel = new javax.swing.JLabel();
        projectLocationLabel = new javax.swing.JLabel();
        jSeparator7 = new javax.swing.JSeparator();
        jScrollPane3 = new javax.swing.JScrollPane();
        Project_Description_jTextArea = new javax.swing.JTextArea();
        message1_jLabel1 = new javax.swing.JLabel();
        jSeparator8 = new javax.swing.JSeparator();
        Project_delete_jButton = new javax.swing.JButton();
        Project_Browse_jButton = new javax.swing.JButton();
        Project_Load_jButton = new javax.swing.JButton();
        Project_Name_jTextField = new javax.swing.JTextField();
        Project_Create_jButton = new javax.swing.JButton();
        Project_Save_jButton = new javax.swing.JButton();
        Project_SaveAs_jButton = new javax.swing.JButton();
        menuBar = new javax.swing.JMenuBar();
        dataBaseMenu = new javax.swing.JMenu();
        jMenuItem21 = new javax.swing.JMenuItem();
        jMenuItem20 = new javax.swing.JMenuItem();
        jMenuItem18 = new javax.swing.JMenuItem();
        jMenuItem19 = new javax.swing.JMenuItem();
        Project_menu = new javax.swing.JMenu();
        Project_New_jMenuItem = new javax.swing.JMenuItem();
        Project_Load_jMenuItem = new javax.swing.JMenuItem();
        jSeparator5 = new javax.swing.JPopupMenu.Separator();
        Project_Save_jMenuItem = new javax.swing.JMenuItem();
        Project_SaveAs_jMenuItem = new javax.swing.JMenuItem();
        jSeparator3 = new javax.swing.JPopupMenu.Separator();
        Project_Exit_jMenuItem = new javax.swing.JMenuItem();
        Process_jMenu = new javax.swing.JMenu();
        Process_New_jMenuItem = new javax.swing.JMenuItem();
        Process_Load_jMenuItem = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        Process_Iterate_jMenuItem = new javax.swing.JMenuItem();
        jSeparator4 = new javax.swing.JPopupMenu.Separator();
        Process_Save_jMenuItem = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();
        ImportCustomisation_jMenuItem = new javax.swing.JMenuItem();
        ExportCustomisation_jMenuItem = new javax.swing.JMenuItem();
        jSeparator6 = new javax.swing.JPopupMenu.Separator();
        storeCustomisation_jMenuItem = new javax.swing.JMenuItem();
        Supplements_menu = new javax.swing.JMenu();
        ImportSupplements_jMenuItem = new javax.swing.JMenuItem();
        ExportSupplements_jMenuItem = new javax.swing.JMenuItem();
        StoreSupplements_jMenuItem = new javax.swing.JMenuItem();
        jSeparator14 = new javax.swing.JPopupMenu.Separator();
        HumanInteraction_jMenuItem = new javax.swing.JMenuItem();
        Management_jMenuItem = new javax.swing.JMenuItem();
        Quality_jMenuItem = new javax.swing.JMenuItem();
        Standards_jMenuItem = new javax.swing.JMenuItem();
        Sources_jMenu = new javax.swing.JMenu();
        ImportSources_jMenuItem = new javax.swing.JMenuItem();
        ExportSources_jMenuItem = new javax.swing.JMenuItem();
        StoreSources_jMenuItem = new javax.swing.JMenuItem();
        jSeparator11 = new javax.swing.JPopupMenu.Separator();
        jMenuItem1 = new javax.swing.JMenuItem();
        Tools_jMenu = new javax.swing.JMenu();
        jMenu4 = new javax.swing.JMenu();
        XMLTOSchema_jMenuItem = new javax.swing.JMenuItem();
        JavaToXMLSchema_jMenuItem = new javax.swing.JMenuItem();
        XMLSchemaToJava_jMenuItem = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Welcaome to Me-KDDaM Process");
        addWindowFocusListener(new java.awt.event.WindowFocusListener() {
            public void windowGainedFocus(java.awt.event.WindowEvent evt) {
                formWindowGainedFocus(evt);
            }
            public void windowLostFocus(java.awt.event.WindowEvent evt) {
            }
        });
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        desktopPane.setBackground(new java.awt.Color(255, 255, 204));
        desktopPane.setInheritsPopupMenu(true);
        desktopPane.setMinimumSize(new java.awt.Dimension(800, 480));
        desktopPane.setSelectedFrame(MeKDDaP);
        desktopPane.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                myMouse(evt);
            }
        });

        MeKDDaP.setClosable(true);
        MeKDDaP.setMaximizable(true);
        MeKDDaP.setTitle("MeKDaP Process");
        MeKDDaP.setName("MeKDDaP"); // NOI18N
        MeKDDaP.setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout MeKDDaPLayout = new javax.swing.GroupLayout(MeKDDaP.getContentPane());
        MeKDDaP.getContentPane().setLayout(MeKDDaPLayout);
        MeKDDaPLayout.setHorizontalGroup(
            MeKDDaPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 714, Short.MAX_VALUE)
        );
        MeKDDaPLayout.setVerticalGroup(
            MeKDDaPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 498, Short.MAX_VALUE)
        );

        MeKDDaP.setBounds(10, 10, 730, 530);
        desktopPane.add(MeKDDaP, javax.swing.JLayeredPane.DEFAULT_LAYER);
        try {
            MeKDDaP.setMaximum(true);
        } catch (java.beans.PropertyVetoException e1) {
            e1.printStackTrace();
        }
        MeKDDaP.getAccessibleContext().setAccessibleName("MeKDDaP Process");

        copyRightLabel.setBackground(new java.awt.Color(236, 233, 216));
        copyRightLabel.setFont(new java.awt.Font("Arial", 0, 12));
        copyRightLabel.setText("Ahmed Bani Mustafa, Computer Science Dept., Aberystwyth University (c)");
        copyRightLabel.setBounds(0, 870, 860, 40);
        desktopPane.add(copyRightLabel, javax.swing.JLayeredPane.DEFAULT_LAYER);

        Process_jPanel.setBackground(new java.awt.Color(255, 255, 204));
        Process_jPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Process", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        Process_jPanel.setLayout(null);

        process_jTextArea.setBackground(new java.awt.Color(240, 240, 240));
        process_jTextArea.setColumns(20);
        process_jTextArea.setRows(5);
        jScrollPane4.setViewportView(process_jTextArea);

        Process_jPanel.add(jScrollPane4);
        jScrollPane4.setBounds(26, 79, 340, 215);

        Process_Iterate_jButton1.setText("Iterate");
        Process_Iterate_jButton1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        Process_Iterate_jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Process_Iterate_jButton1ActionPerformed(evt);
            }
        });
        Process_jPanel.add(Process_Iterate_jButton1);
        Process_Iterate_jButton1.setBounds(140, 30, 90, 40);

        Process_New_jButton1.setText("New Process");
        Process_New_jButton1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        Process_New_jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Process_New_jButton1(evt);
            }
        });
        Process_jPanel.add(Process_New_jButton1);
        Process_New_jButton1.setBounds(26, 30, 110, 40);

        Process_Load_jButton.setText("Load");
        Process_Load_jButton.setActionCommand("save");
        Process_Load_jButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Process_Load_jButtonActionPerformed(evt);
            }
        });
        Process_jPanel.add(Process_Load_jButton);
        Process_Load_jButton.setBounds(264, 303, 108, 27);

        Process_Save_jButton.setText("Save");
        Process_Save_jButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Process_Save_jButton(evt);
            }
        });
        Process_jPanel.add(Process_Save_jButton);
        Process_Save_jButton.setBounds(24, 303, 100, 27);

        jLabel1.setText("Rollback");
        Process_jPanel.add(jLabel1);
        jLabel1.setBounds(240, 30, 136, 16);

        Process_Version_jComboBox.setMaximumRowCount(100);
        Process_Version_jComboBox.setModel(this.getProcessVersions());
        Process_Version_jComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Process_Version_jComboBoxActionPerformed(evt);
            }
        });
        Process_jPanel.add(Process_Version_jComboBox);
        Process_Version_jComboBox.setBounds(236, 48, 130, 20);

        Process_Tree_jButton.setText("Tree View");
        Process_Tree_jButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Process_Tree_jButtonActionPerformed(evt);
            }
        });
        Process_jPanel.add(Process_Tree_jButton);
        Process_Tree_jButton.setBounds(124, 303, 140, 27);

        jPanel1.setBackground(new java.awt.Color(255, 255, 204));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Project", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        jPanel1.setPreferredSize(new java.awt.Dimension(325, 375));
        jPanel1.setLayout(null);

        DescriptionLabel.setText("Project Description:");
        jPanel1.add(DescriptionLabel);
        DescriptionLabel.setBounds(24, 144, 200, 30);
        jPanel1.add(jSeparator9);
        jSeparator9.setBounds(16, 455, 1, 10);
        jPanel1.add(message2_jLabel1);
        message2_jLabel1.setBounds(407, 298, 297, 22);
        jPanel1.add(Project_Location_jTextField);
        Project_Location_jTextField.setBounds(20, 110, 260, 30);

        projectNameLabel.setText("Project Name:");
        jPanel1.add(projectNameLabel);
        projectNameLabel.setBounds(20, 30, 190, 16);

        projectLocationLabel.setText("Project Location:");
        jPanel1.add(projectLocationLabel);
        projectLocationLabel.setBounds(20, 90, 200, 16);
        jPanel1.add(jSeparator7);
        jSeparator7.setBounds(16, 312, 0, 10);

        Project_Description_jTextArea.setColumns(20);
        Project_Description_jTextArea.setRows(5);
        jScrollPane3.setViewportView(Project_Description_jTextArea);

        jPanel1.add(jScrollPane3);
        jScrollPane3.setBounds(20, 170, 350, 100);
        jPanel1.add(message1_jLabel1);
        message1_jLabel1.setBounds(407, 274, 297, 18);
        jPanel1.add(jSeparator8);
        jSeparator8.setBounds(16, 439, 1, 10);

        Project_delete_jButton.setText("Delete ");
        Project_delete_jButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Project_delete_jButtonActionPerformed(evt);
            }
        });
        jPanel1.add(Project_delete_jButton);
        Project_delete_jButton.setBounds(270, 280, 100, 29);

        Project_Browse_jButton.setText("Location");
        Project_Browse_jButton.setActionCommand("path");
        Project_Browse_jButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Project_Browse_jButtonActionPerformed(evt);
            }
        });
        jPanel1.add(Project_Browse_jButton);
        Project_Browse_jButton.setBounds(280, 110, 90, 30);

        Project_Load_jButton.setText("Load from file");
        Project_Load_jButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Project_Load_jButtonActionPerformed(evt);
            }
        });
        jPanel1.add(Project_Load_jButton);
        Project_Load_jButton.setBounds(140, 280, 130, 29);
        jPanel1.add(Project_Name_jTextField);
        Project_Name_jTextField.setBounds(20, 50, 350, 30);

        Project_Create_jButton.setText("New Project");
        Project_Create_jButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Project_Create_jButton(evt);
            }
        });
        jPanel1.add(Project_Create_jButton);
        Project_Create_jButton.setBounds(20, 280, 120, 29);

        Project_Save_jButton.setText("Save");
        Project_Save_jButton.setActionCommand("save");
        Project_Save_jButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Project_Save_jButtonActionPerformed(evt);
            }
        });
        jPanel1.add(Project_Save_jButton);
        Project_Save_jButton.setBounds(20, 310, 190, 29);

        Project_SaveAs_jButton.setText("Save as");
        Project_SaveAs_jButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Project_SaveAs_jButtonActionPerformed(evt);
            }
        });
        jPanel1.add(Project_SaveAs_jButton);
        Project_SaveAs_jButton.setBounds(210, 310, 160, 29);

        menuBar.setMaximumSize(new java.awt.Dimension(950, 32769));
        menuBar.setMinimumSize(new java.awt.Dimension(930, 470));

        jMenuItem21.setText("Navigate Data Base");
        jMenuItem21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem21ActionPerformed(evt);
            }
        });
        dataBaseMenu.add(jMenuItem21);

        jMenuItem20.setText("Search Data Base");
        dataBaseMenu.add(jMenuItem20);

        jMenuItem18.setText("Manage Data Base");
        dataBaseMenu.add(jMenuItem18);

        jMenuItem19.setText("Generate Reports");
        dataBaseMenu.add(jMenuItem19);

        menuBar.add(dataBaseMenu);

        Project_menu.setText("Project");

        Project_New_jMenuItem.setText("New Project");
        Project_New_jMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Project_New_jMenuItemActionPerformed(evt);
            }
        });
        Project_menu.add(Project_New_jMenuItem);

        Project_Load_jMenuItem.setText("Load Project");
        Project_Load_jMenuItem.setActionCommand("OpenProject");
        Project_Load_jMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Project_Load_jMenuItemActionPerformed(evt);
            }
        });
        Project_menu.add(Project_Load_jMenuItem);
        Project_Load_jMenuItem.getAccessibleContext().setAccessibleName("OpenProject");

        Project_menu.add(jSeparator5);

        Project_Save_jMenuItem.setText("Save");
        Project_Save_jMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Project_Save_jMenuItemActionPerformed(evt);
            }
        });
        Project_menu.add(Project_Save_jMenuItem);

        Project_SaveAs_jMenuItem.setText("Save As ...");
        Project_SaveAs_jMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Project_SaveAs_jMenuItemActionPerformed(evt);
            }
        });
        Project_menu.add(Project_SaveAs_jMenuItem);
        Project_menu.add(jSeparator3);

        Project_Exit_jMenuItem.setText("Exit");
        Project_Exit_jMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Project_Exit_jMenuItemActionPerformed(evt);
            }
        });
        Project_menu.add(Project_Exit_jMenuItem);

        menuBar.add(Project_menu);

        Process_New_jMenuItem.setText("New Process");
        Process_New_jMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Process_New_jMenuItemActionPerformed(evt);
            }
        });
        Process_jMenu.add(Process_New_jMenuItem);

        Process_Load_jMenuItem.setText("Load Process");
        Process_Load_jMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Process_Load_jMenuItemActionPerformed(evt);
            }
        });
        Process_jMenu.add(Process_Load_jMenuItem);
        Process_jMenu.add(jSeparator2);

        Process_Iterate_jMenuItem.setText("Iterate");
        Process_Iterate_jMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Process_Iterate_jMenuItemActionPerformed(evt);
            }
        });
        Process_jMenu.add(Process_Iterate_jMenuItem);
        Process_jMenu.add(jSeparator4);

        Process_Save_jMenuItem.setText("Save");
        Process_Save_jMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Process_Save_jMenuItemActionPerformed(evt);
            }
        });
        Process_jMenu.add(Process_Save_jMenuItem);

        menuBar.add(Process_jMenu);

        jMenu1.setText("Phases Customisation");

        ImportCustomisation_jMenuItem.setText("Import Phase Customisation");
        ImportCustomisation_jMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ImportCustomisation_jMenuItemActionPerformed(evt);
            }
        });
        jMenu1.add(ImportCustomisation_jMenuItem);

        ExportCustomisation_jMenuItem.setText("Export Phase Customisation");
        ExportCustomisation_jMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ExportCustomisation_jMenuItemActionPerformed(evt);
            }
        });
        jMenu1.add(ExportCustomisation_jMenuItem);
        jMenu1.add(jSeparator6);

        storeCustomisation_jMenuItem.setText("Store Phase Customisation");
        storeCustomisation_jMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                storeCustomisation_jMenuItemActionPerformed(evt);
            }
        });
        jMenu1.add(storeCustomisation_jMenuItem);

        menuBar.add(jMenu1);

        Supplements_menu.setText("Supplements");

        ImportSupplements_jMenuItem.setText("Import Process Supplements");
        ImportSupplements_jMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ImportSupplements_jMenuItemActionPerformed(evt);
            }
        });
        Supplements_menu.add(ImportSupplements_jMenuItem);

        ExportSupplements_jMenuItem.setText("Export Process Supplements");
        ExportSupplements_jMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ExportSupplements_jMenuItemActionPerformed(evt);
            }
        });
        Supplements_menu.add(ExportSupplements_jMenuItem);

        StoreSupplements_jMenuItem.setText("Store Process Supplements");
        StoreSupplements_jMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                StoreSupplements_jMenuItemActionPerformed(evt);
            }
        });
        Supplements_menu.add(StoreSupplements_jMenuItem);
        Supplements_menu.add(jSeparator14);

        HumanInteraction_jMenuItem.setText("Human Interaction");
        HumanInteraction_jMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                HumanInteraction_jMenuItemActionPerformed(evt);
            }
        });
        Supplements_menu.add(HumanInteraction_jMenuItem);

        Management_jMenuItem.setText("Process Mangement");
        Management_jMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Management_jMenuItemActionPerformed(evt);
            }
        });
        Supplements_menu.add(Management_jMenuItem);

        Quality_jMenuItem.setText("Quality Issues");
        Quality_jMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Quality_jMenuItemActionPerformed(evt);
            }
        });
        Supplements_menu.add(Quality_jMenuItem);

        Standards_jMenuItem.setText("Adapted Standards");
        Standards_jMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Standards_jMenuItemActionPerformed(evt);
            }
        });
        Supplements_menu.add(Standards_jMenuItem);

        menuBar.add(Supplements_menu);

        Sources_jMenu.setText("Sources");

        ImportSources_jMenuItem.setText("Import Sources");
        ImportSources_jMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ImportSources_jMenuItemActionPerformed(evt);
            }
        });
        Sources_jMenu.add(ImportSources_jMenuItem);

        ExportSources_jMenuItem.setText("Export Sources");
        ExportSources_jMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ExportSources_jMenuItemActionPerformed(evt);
            }
        });
        Sources_jMenu.add(ExportSources_jMenuItem);

        StoreSources_jMenuItem.setText("Store Sources");
        StoreSources_jMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                StoreSources_jMenuItemActionPerformed(evt);
            }
        });
        Sources_jMenu.add(StoreSources_jMenuItem);
        Sources_jMenu.add(jSeparator11);

        jMenuItem1.setText("Sources Form");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        Sources_jMenu.add(jMenuItem1);

        menuBar.add(Sources_jMenu);

        Tools_jMenu.setText("Tools");

        jMenu4.setText("XML Tool Box");

        XMLTOSchema_jMenuItem.setText("Generate XML Schema From XML File");
        XMLTOSchema_jMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                XMLTOSchema_jMenuItemActionPerformed(evt);
            }
        });
        jMenu4.add(XMLTOSchema_jMenuItem);

        JavaToXMLSchema_jMenuItem.setText("Generate XML Schema From Java Class");
        JavaToXMLSchema_jMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JavaToXMLSchema_jMenuItemActionPerformed(evt);
            }
        });
        jMenu4.add(JavaToXMLSchema_jMenuItem);
        JavaToXMLSchema_jMenuItem.getAccessibleContext().setAccessibleName("GenerateXMLSchemaFromJavaClass");

        XMLSchemaToJava_jMenuItem.setText("Generate Java Class Code From XML Schema");
        XMLSchemaToJava_jMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                XMLSchemaToJava_jMenuItemActionPerformed(evt);
            }
        });
        jMenu4.add(XMLSchemaToJava_jMenuItem);

        Tools_jMenu.add(jMenu4);

        menuBar.add(Tools_jMenu);

        setJMenuBar(menuBar);
        this.setExtendedState(MAXIMIZED_BOTH);
        setExtendedState(MAXIMIZED_BOTH);
        imagePanel =new ProcessImagesPanel(1);
        imagePanel.setImage(Config.processImage);
        getContentPane().add(imagePanel,BorderLayout.CENTER);
        pack();
        repaint();

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(desktopPane, javax.swing.GroupLayout.PREFERRED_SIZE, 867, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Process_jPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 388, Short.MAX_VALUE))
                .addContainerGap(27, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 351, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Process_jPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(desktopPane, javax.swing.GroupLayout.PREFERRED_SIZE, 687, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(23, Short.MAX_VALUE))
        );

        this.setExtendedState(MAXIMIZED_BOTH);

        pack();
    }// </editor-fold>//GEN-END:initComponents
    private void exitMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitMenuItemActionPerformed
        System.exit(0);
    }//GEN-LAST:event_exitMenuItemActionPerformed

    private void Project_Save_jMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Project_Save_jMenuItemActionPerformed
    this.Project_Save_jButtonActionPerformed(evt);
    

  // create object//GEN-LAST:event_Project_Save_jMenuItemActionPerformed
    }
    private void Project_SaveAs_jMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Project_SaveAs_jMenuItemActionPerformed
        this.Project_SaveAs_jMenuItemActionPerformed(evt);
}//GEN-LAST:event_Project_SaveAs_jMenuItemActionPerformed

    private void myMouse(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_myMouse
         Object selection=null;
        int x=evt.getX();
        int y=evt.getY();
       
            try {
                if (this.imagePanel.isEnabled())
                {
                    selection = DashBoard.getXYAction(x, y);                    
                }

            } catch (Exception ex) {
                Logger.getLogger(MainMenu.class.getName()).log(Level.SEVERE, null, ex);
            }
        this.populateForm();
    }//GEN-LAST:event_myMouse

    private void Project_Load_jMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Project_Load_jMenuItemActionPerformed

this.Project_Load_jButtonActionPerformed(evt);
}//GEN-LAST:event_Project_Load_jMenuItemActionPerformed

    private void Project_New_jMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Project_New_jMenuItemActionPerformed
Project_Create_jButton(evt);
    }//GEN-LAST:event_Project_New_jMenuItemActionPerformed

private void jMenuItem21ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem21ActionPerformed
// TODO add your handling code here:
}//GEN-LAST:event_jMenuItem21ActionPerformed

private void GenerateJavaClassFromXMLSchemaMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GenerateJavaClassFromXMLSchemaMenuItemActionPerformed
    XMLTools.GenerateJavaClassFromXMLSchema();
}//GEN-LAST:event_GenerateJavaClassFromXMLSchemaMenuItemActionPerformed

private void JavaToXMLSchema_jMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JavaToXMLSchema_jMenuItemActionPerformed
    XMLTools.GenerateXMLSchemaFromJavaClass();

}//GEN-LAST:event_JavaToXMLSchema_jMenuItemActionPerformed

private void Project_Exit_jMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Project_Exit_jMenuItemActionPerformed
    System.exit(0);
}//GEN-LAST:event_Project_Exit_jMenuItemActionPerformed

private void Process_New_jMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Process_New_jMenuItemActionPerformed
    this.Process_New_jButton1(evt);
}//GEN-LAST:event_Process_New_jMenuItemActionPerformed

private void Process_Load_jMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Process_Load_jMenuItemActionPerformed
   this.Process_Load_jButtonActionPerformed(evt);
}//GEN-LAST:event_Process_Load_jMenuItemActionPerformed

private void Process_Save_jMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Process_Save_jMenuItemActionPerformed

 this.Process_Save_jButton(evt);

}//GEN-LAST:event_Process_Save_jMenuItemActionPerformed

private void Process_Iterate_jMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Process_Iterate_jMenuItemActionPerformed

 this.Process_Iterate_jButton1ActionPerformed(evt);
}//GEN-LAST:event_Process_Iterate_jMenuItemActionPerformed

private void Project_delete_jButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Project_delete_jButtonActionPerformed

 if (Global.project!=null)
  {
    String projectLocation=null;

    if (Global.project.getLocation()!=null)
        projectLocation=Global.project.getLocation();

    String projectName=null;

    if (Global.project.getName()!=null)
        projectName=Global.project.getName();

    Global.project=null;
   
    this.populateForm();
    this.imagePanel.setImage(new File(Config.imagesLocation).getAbsolutePath());    
    this.repaint();
    this.pack();

    File projectDirectory=null;
    if (projectLocation!=null && projectName!=null)
        projectDirectory=new File(projectLocation+"/"+projectName);


    if (projectDirectory.isDirectory())
    {
        
        int decision=JOptionPane.showConfirmDialog(null, "Project was deleted successfully!\n"+
                                                         "However, Project files are still stored on the system!\n"+
                                                         "Would you also like to delete the project Files?");
        if (decision==0)
        {
            if (Tools.deleteDirectory(projectDirectory))
            {       
                this.setTitle("");
            }
            else            
                JOptionPane.showMessageDialog(null, "Project deletion was unsuccessfull!\n" );
        }
     }
    else
       JOptionPane.showMessageDialog(null, "No Project was found to be deleted!\n" +" --> "+projectDirectory);



  }
}//GEN-LAST:event_Project_delete_jButtonActionPerformed

private void Project_Browse_jButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Project_Browse_jButtonActionPerformed

File selectedFile=Tools.chooseFile("Please select the project location", null,null, true);
String selectedFileString = (selectedFile != null) ? selectedFile.getPath() : null;
if (selectedFileString != null) {
    File file = new File(selectedFileString);
    Project_Location_jTextField.setText(file.getAbsolutePath());
    Project_Create_jButton(evt);    
}
else
    Project_Location_jTextField.setText("");

 //-----------------------------------------------------------------------
}//GEN-LAST:event_Project_Browse_jButtonActionPerformed

private void Project_Load_jButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Project_Load_jButtonActionPerformed
        try {
            this.load_project();

        } catch (IOException ex) {
            Logger.getLogger(MainMenu.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(MainMenu.class.getName()).log(Level.SEVERE, null, ex);
        }
}//GEN-LAST:event_Project_Load_jButtonActionPerformed

private void Project_Create_jButton(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Project_Create_jButton

    if (Global.project!=null)
    {
        int decision=JOptionPane.showConfirmDialog(null, "Would you like reset the current project?");
        if (decision==0)
           create_project();               
    }
    else
        create_project();
   
}//GEN-LAST:event_Project_Create_jButton


private void create_project()
{
this.clear();
if (this.new_project() == false) 
{
    JOptionPane.showMessageDialog(rootPane, "Failed to create the project...");
}
else
{
    int decision=JOptionPane.showConfirmDialog(null, "Would you like to \n start a new fresh process execution?");
    if (decision==0)
    {                     
        this.new_process();
    }
    
}    
}



private void Project_Save_jButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Project_Save_jButtonActionPerformed

// set the name in the name box
if (Global.project!=null)
{
    if (!Project_Name_jTextField.getText().equals(""))
        Global.project.setName(Project_Name_jTextField.getText());

    if (Global.project.getName()!=null)
    {
        if (!Project_Location_jTextField.getText().equals("") && Project_Location_jTextField.getText().contains(":") &&
            (Project_Location_jTextField.getText().contains("/") || Project_Location_jTextField.getText().contains("\\")))
            this.save_project(this.Project_Location_jTextField.getText());
        else
            this.save_project(null);
    }
}
else
{
    if (this.new_project() == false) 
    {    
        JOptionPane.showMessageDialog(rootPane, "Failed to create the project...");
    }
 }
}//GEN-LAST:event_Project_Save_jButtonActionPerformed

private void Process_Iterate_jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Process_Iterate_jButton1ActionPerformed
     this.iterate_process();
}//GEN-LAST:event_Process_Iterate_jButton1ActionPerformed

private void Process_New_jButton1(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Process_New_jButton1
    this.new_process();
}//GEN-LAST:event_Process_New_jButton1

private void Process_Load_jButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Process_Load_jButtonActionPerformed
    this.load_process();
}//GEN-LAST:event_Process_Load_jButtonActionPerformed

private void Process_Save_jButton(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Process_Save_jButton
        String location =null;
    this.save_process(location);
}//GEN-LAST:event_Process_Save_jButton

private void Process_Version_jComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Process_Version_jComboBoxActionPerformed
          Process_Version_jComboBox= this.selectProcessVersion(Process_Version_jComboBox);

}//GEN-LAST:event_Process_Version_jComboBoxActionPerformed

private void Process_Tree_jButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Process_Tree_jButtonActionPerformed
    Tree.create_tree("Tasks");
}//GEN-LAST:event_Process_Tree_jButtonActionPerformed

private void formWindowGainedFocus(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowGainedFocus
if (Global.project!=null && !this.isInput)
{
    Global.project.save();
    this.populateForm();
}     
}//GEN-LAST:event_formWindowGainedFocus

private void Project_SaveAs_jButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Project_SaveAs_jButtonActionPerformed

if (Global.project!=null)
 {
    this.save_project(null);

    if (Global.project.getLocation()!=null)
        Global.project.store(Global.project.getLocation());

    // Hold the old project information to do copying and clean up
    //-------------------------------------------------------------------------
    String oldLocationProjcetFolder=null;
    String oldLocationProjcetName=null;
    if (Global.project.getLocation()!=null && Global.project.getName()!=null)
    {
        oldLocationProjcetName=Global.project.getName();
        oldLocationProjcetFolder=Global.project.getLocation()+"/"+Global.project.getName();        
    }
    // Start saving as the project
    //-------------------------------------------------------------------------
    try {
            // time stamp the save
            //---------------------------------------------------------
            Global.project.setDate(Tools.getTime().toString());

            // tell the window focus to expect input
            //---------------------------------------------------------
            isInput=true;

            // get the name of the new project
            //---------------------------------------------------------
            String newProjectName = JOptionPane.showInputDialog("Input the save as project name");
            if (newProjectName!=null)            
                Project_Name_jTextField.setText(newProjectName);
             
            if (Project_Name_jTextField.getText()!=null)
            {
                // get the location of the new project
                //---------------------------------------------------------
                File selectedFile=null;
                
             int decision=JOptionPane.showConfirmDialog(null, "Would you like to \n use the same existing project location?");
             if (decision==0)
              {
                   if (Global.project.getLocation()!=null)
                       selectedFile=new File(Global.project.getLocation());
                   else
                       selectedFile=Tools.chooseFile("Choose project location", null, null, true);
              }            
             else 
             {                                   
                  if (Global.project.getLocation()!=null)
                     selectedFile=Tools.chooseFile("Choose project location", null, Global.project.getLocation(),true);
                 else
                     selectedFile=Tools.chooseFile("Choose project location", null, null, true);                 
             }

                String selectedFileString = (selectedFile != null) ? selectedFile.getPath() : null;
                if (selectedFileString!=null)
                {
                    // set the new name in the name box
                    //---------------------------------------------------------
                    if (!Project_Name_jTextField.getText().equals(""))
                         Global.project.setName(Project_Name_jTextField.getText());

                    Global.project.setLocation(selectedFileString);
                    Global.project.store(selectedFileString);                

                    // Copy old project files and folders to the new project
                    //---------------------------------------------------------
                    String newLocationProjcetFolder=null;
                    if (Global.project.getLocation()!=null && Global.project.getName()!=null)
                        newLocationProjcetFolder=Global.project.getLocation()+"/"+Global.project.getName();                    
                    Tools.copyDirectory(new File(oldLocationProjcetFolder), new File(newLocationProjcetFolder));// to make sure that we copy clipboard files

                    // deleteing the old projcet xml file which was copied to the new project folder
                    // --------------------------------------------------------
                    if (new File(Global.project.getLocation()+"\\"+Global.project.getName()+"\\"+oldLocationProjcetName+".xml").exists())
                        new File(Global.project.getLocation()+"\\"+Global.project.getName()+"\\"+oldLocationProjcetName+".xml").delete();

                    // Update the location values in all (process, phase and delivery)
                    //---------------------------------------------------------
                     Global.project.store(selectedFileString);// 
                }

             isInput=false;// tell the window focus input is ended
           }
            else
               JOptionPane.showMessageDialog(rootPane, "Oops...No Project name was found !");
         }
         catch(Exception x)
         {             
             Logger.getLogger(PhaseForm.class.getName()).log(Level.SEVERE, null, x);
         }
    }
else
    JOptionPane.showMessageDialog(rootPane, "Oops...No Project was found to be saved");




}//GEN-LAST:event_Project_SaveAs_jButtonActionPerformed

private void Standards_jMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Standards_jMenuItemActionPerformed
Standards selection=new Standards();
StandardsForm standardsForm=new StandardsForm(null);
standardsForm.setTitle(selection.getClass().getSimpleName());
 standardsForm.setVisible(true);
}//GEN-LAST:event_Standards_jMenuItemActionPerformed

private void Management_jMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Management_jMenuItemActionPerformed
Management selection=new Management();
ManagementForm supplementForm=new ManagementForm();
supplementForm.setTitle(selection.getClass().getSimpleName());
supplementForm.setVisible(true);
}//GEN-LAST:event_Management_jMenuItemActionPerformed

private void HumanInteraction_jMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_HumanInteraction_jMenuItemActionPerformed
HumanInteraction selection=new HumanInteraction();
HumanInteractionForm humanInteractionForm=new HumanInteractionForm();
humanInteractionForm.setTitle(selection.getClass().getSimpleName());
humanInteractionForm.setVisible(true);
}//GEN-LAST:event_HumanInteraction_jMenuItemActionPerformed

private void Quality_jMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Quality_jMenuItemActionPerformed
Quality selection=new Quality();
QualityForm qualityForm=new QualityForm();
qualityForm.setTitle(selection.getClass().getSimpleName());
qualityForm.setVisible(true);

}//GEN-LAST:event_Quality_jMenuItemActionPerformed

private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing

this.dispose();

}//GEN-LAST:event_formWindowClosing

@Override
public void dispose()
{
int decision=JOptionPane.showConfirmDialog(null, "Would you like to save before exiting the project\n"+
                                                  "Press cancel to remain in the project"+ "!?\n");
if (decision==0)
{
   Project_Save_jButtonActionPerformed(null);
   System.exit(0);

}
else if (decision==1)
{
    System.exit(0);
}
else
{
  this.repaint();
}
}


private void XMLTOSchema_jMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_XMLTOSchema_jMenuItemActionPerformed
    
    XMLTools.GenerateXMLSchemaFromXMLFile();

}//GEN-LAST:event_XMLTOSchema_jMenuItemActionPerformed

private void XMLSchemaToJava_jMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_XMLSchemaToJava_jMenuItemActionPerformed
    XMLTools.GenerateJavaClassFromXMLSchema();
}//GEN-LAST:event_XMLSchemaToJava_jMenuItemActionPerformed

    private void ImportCustomisation_jMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ImportCustomisation_jMenuItemActionPerformed

    if (Global.project!=null && Global.project.getLocation()!=null)        
    {

        String projectName=Global.project.getName();
        String projectLocation=Global.project.getLocation();    
        //-------------------------------------------------------------------------    
        String customisationLocation=projectLocation+ "/" + projectName+ "/Customisation/";    
        File customisationFolder= new File(customisationLocation);    
        
        File allPhasesCustomisationFolder= new File(Config.defaultLocation+"/Customisation");            
        if  (allPhasesCustomisationFolder.exists() && allPhasesCustomisationFolder.isDirectory())   
        {
            int decision=JOptionPane.showConfirmDialog(null, 
            "Would  you like to import phases customisation from utilities ?"
           + "\nThis will overwrite the process customisation folder !"
           + "\nAre you sure you want to continue !?");
            if (decision==0)
            {
                 Tools.copyDirectoryForce(allPhasesCustomisationFolder,customisationFolder);                     
                 JOptionPane.showMessageDialog(null, "Importing was successful!\n"
                                                    + "You still need to load customisation to each phase!");
            }
        }
        else
             JOptionPane.showMessageDialog(null, "No Customisation was found to import!"
                                                + "\nCheck utilites customisation folder and try again!");
    }
    else
        JOptionPane.showMessageDialog(null, "You need to create or load a project first!");
    

    }//GEN-LAST:event_ImportCustomisation_jMenuItemActionPerformed

    private void ExportCustomisation_jMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ExportCustomisation_jMenuItemActionPerformed

    if (Global.project!=null && Global.project.getLocation()!=null)        
    {
                
        if (Global.project.getProcess()!=null)                                        
            Global.project.getProcess().storeCustomisation();        
                
        String projectName=Global.project.getName();
        String projectLocation=Global.project.getLocation();    
        //-------------------------------------------------------------------------    
        String customisationLocation=projectLocation+ "/" + projectName+ "/Customisation";    
        File customisationFolder= new File(customisationLocation);    
        
        File allPhasesCustomisationFolder= new File(Config.defaultLocation+"/Customisation");            
        if  (customisationFolder.exists() && customisationFolder.isDirectory())   
        {
            int decision=JOptionPane.showConfirmDialog(null, 
            "Would  you like to export phases customisation to utilities ?"
            + "\nThis will overwrite utilities customisation folder!"
            + "\nAre you sure you want to continue !?");
            if (decision==0)
            {
                 Tools.copyDirectoryForce(customisationFolder,allPhasesCustomisationFolder);                     
                 JOptionPane.showMessageDialog(null, "Exporting was successful!");
            }
        }
        else
             JOptionPane.showMessageDialog(null, "No Customisation was found to export!"
                                                + "\nCheck project customisation folder and try again!");
        
    } 
    else
        JOptionPane.showMessageDialog(null, "You need to create or load a project first!");            
    
    
    }//GEN-LAST:event_ExportCustomisation_jMenuItemActionPerformed

    private void storeCustomisation_jMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_storeCustomisation_jMenuItemActionPerformed
      
        if (Global.project!=null && Global.project.getProcess()!=null)
        {
            int decision=JOptionPane.showConfirmDialog(null, 
            "Are you sure you want to store all process phases customisation !?"
            + "\nThis will overwrite the project customisation folder!\n Do you want to continue?");
            if (decision==0)            
                Global.project.getProcess().storeCustomisation();      
            
            JOptionPane.showMessageDialog(null, "Customisation Storing was successful!");
        }
        else
            JOptionPane.showMessageDialog(null, "You need to create or load a project and start a process!");
        
    }//GEN-LAST:event_storeCustomisation_jMenuItemActionPerformed

    private void ImportSupplements_jMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ImportSupplements_jMenuItemActionPerformed
//******************************************************************************    
    
if (Global.project!=null && Global.project.getLocation()!=null)        
    {

        String projectName=Global.project.getName();
        String projectLocation=Global.project.getLocation();    
        //-------------------------------------------------------------------------    
        String supplementsLocation=projectLocation+ "/" + projectName+ "/Supplements";    
        File supplementsFolder= new File(supplementsLocation);    
        
        File allPhasesSupplementsFolder= new File(Config.defaultLocation+"/Supplements");            
        if  (allPhasesSupplementsFolder.exists() && allPhasesSupplementsFolder.isDirectory())   
        {
            int decision=JOptionPane.showConfirmDialog(null, 
            "Would  you like to import supplements from utilities ?"
           + "\nThis will overwrite the process supplements folder and reset the process supplemnts!"
           + "\nAre you sure you want to continue !?");
            if (decision==0)
            {
                 Tools.copyDirectoryForce(allPhasesSupplementsFolder,supplementsFolder);                     
                 if (Global.project!=null)
                 {
                     
                    if (Global.project.getSupplements()==null)
                        Global.project.setSupplements(new Supplements());                     
                     
                    Supplements supplements = Global.project.getSupplements().open(supplementsLocation+"/Supplements.xml");                    
                    
                    if (supplements!=null)
                    {
                        Global.project.setSupplements(supplements.clone());                    
                        JOptionPane.showMessageDialog(null, "Supplements importing was successful!");
                    }
                  }                                  
            }
        }
        else
             JOptionPane.showMessageDialog(null, "No Supplements was found to import!"
                                                + "\nCheck utilites supplements folder and try again!");
    }
    else
        JOptionPane.showMessageDialog(null, "You need to create or load a project first!");    

    }//GEN-LAST:event_ImportSupplements_jMenuItemActionPerformed

    private void ExportSupplements_jMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ExportSupplements_jMenuItemActionPerformed
       
//******************************************************************************    
    if (Global.project!=null && Global.project.getLocation()!=null)        
    {
                
        if (Global.project.getProcess()!=null)                                        
            Global.project.getProcess().storeSupplemnts();
        
                
        String projectName=Global.project.getName();
        String projectLocation=Global.project.getLocation();    
        //-------------------------------------------------------------------------    
        String supplementsLocation=projectLocation+ "/" + projectName+ "/Supplements";    
        File supplementsFolder= new File(supplementsLocation);    
        
        File allPhasesupplementsFolder= new File(Config.defaultLocation+"/Supplements");            
        if  (supplementsFolder.exists() && supplementsFolder.isDirectory())   
        {
            int decision=JOptionPane.showConfirmDialog(null, 
            "Would  you like to export supplements to utilities ?"
            + "\nThis will overwrite utilities supplements folder!"
            + "\nAre you sure you want to continue !?");
            if (decision==0)
            {
                 Tools.copyDirectoryForce(supplementsFolder,allPhasesupplementsFolder);                     
                 JOptionPane.showMessageDialog(null, "Supplements exporting was successful!");
            }
        }
        else
             JOptionPane.showMessageDialog(null, "No Supplements was found to export!"
                                                + "\nCheck project supplements folder and try again!");
        
    } 
    else
        JOptionPane.showMessageDialog(null, "You need to create or load a project first!");
        
    }//GEN-LAST:event_ExportSupplements_jMenuItemActionPerformed

    private void StoreSupplements_jMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_StoreSupplements_jMenuItemActionPerformed
                if (Global.project!=null && Global.project.getProcess()!=null)
        {
            int decision=JOptionPane.showConfirmDialog(null, 
            "Are you sure you want to store supplemnts !?"
            + "\nThis will overwrite the project Supplemnts folder!\n Do you want to continue?");
            if (decision==0)   
            {
                Global.project.getProcess().storeSupplemnts();            
                JOptionPane.showMessageDialog(null, "Supplements storing was successful!");
            }
        }
        else
            JOptionPane.showMessageDialog(null, "You need to create or load a project and start a process!");

    }//GEN-LAST:event_StoreSupplements_jMenuItemActionPerformed

    private void ImportSources_jMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ImportSources_jMenuItemActionPerformed
       
if (Global.project!=null && Global.project.getLocation()!=null)        
    {
        String projectName=Global.project.getName();
        String projectLocation=Global.project.getLocation();    
        //-------------------------------------------------------------------------    
        String sourcesLocation=projectLocation+ "/" + projectName+ "/Sources";    
        
        
        File sourcesFolder= new File(sourcesLocation);    
        
        File allPhasesSourcesFolder= new File(Config.defaultLocation+"/Sources");
        if  (allPhasesSourcesFolder.exists() && allPhasesSourcesFolder.isDirectory())   
        {
            int decision=JOptionPane.showConfirmDialog(null, 
            "Would  you like to import sources from utilities ?"
           + "\nThis will overwrite the process sources folder and reset the process sources!"
           + "\nAre you sure you want to continue !?");
            if (decision==0)
            {
                 Tools.copyDirectoryForce(allPhasesSourcesFolder,sourcesFolder);                   
                 if (Global.project!=null)
                 {                       
                     
                    if (Global.project.getSources()==null)
                        Global.project.setSources(new Sources());
                    
                    File sourceFile= new File(sourcesLocation+"/"+"Sources.xml");
                    if (sourceFile!=null && sourceFile.exists())
                    {
                        Sources sources = new Sources();
                        sources=Global.project.getSources().open(sourceFile);                    
                        if (sources!=null)
                        {
                            Global.project.setSources(sources.clone());
                            JOptionPane.showMessageDialog(null, "Sources importing was successful!");
                        }
                    }
                                                            
                  }
            }
        }
        else
             JOptionPane.showMessageDialog(null, "No Sources was found to import!"
                                                + "\nCheck utilites sources folder and try again!");
    }
    else
        JOptionPane.showMessageDialog(null, "You need to create or load a project first!");    
        
        
    }//GEN-LAST:event_ImportSources_jMenuItemActionPerformed

    private void ExportSources_jMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ExportSources_jMenuItemActionPerformed
       
    if (Global.project!=null && Global.project.getLocation()!=null)        
    {
                
        if (Global.project.getProcess()!=null)                                        
            Global.project.getProcess().storeSources();
        
                
        String projectName=Global.project.getName();
        String projectLocation=Global.project.getLocation();    
        //-------------------------------------------------------------------------    
        String sourcesLocation=projectLocation+ "/" + projectName+ "/Sources";    
        File sourcesFolder= new File(sourcesLocation);    
        
        File allPhasesSourcesFolder= new File(Config.defaultLocation+"/Sources");            
        if  (sourcesFolder.exists() && sourcesFolder.isDirectory())   
        {
            int decision=JOptionPane.showConfirmDialog(null, 
            "Would  you like to export sources to utilities ?"
            + "\nThis will overwrite utilities sources folder!"
            + "\nAre you sure you want to continue !?");
            if (decision==0)
            {
                 Tools.copyDirectoryForce(sourcesFolder,allPhasesSourcesFolder);                     
                 JOptionPane.showMessageDialog(null, "Sources exporting was successful!");
            }
        }
        else
             JOptionPane.showMessageDialog(null, "No Sources was found to export!"
                                                + "\nCheck project sources folder and try again!");
        
    } 
    else
        JOptionPane.showMessageDialog(null, "You need to create or load a project first!");
        
    }//GEN-LAST:event_ExportSources_jMenuItemActionPerformed

    private void StoreSources_jMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_StoreSources_jMenuItemActionPerformed
      if (Global.project!=null && Global.project.getProcess()!=null)
        {
            int decision=JOptionPane.showConfirmDialog(null, 
            "Are you sure you want to store phases sources !?"
            + "\nThis will overwrite the project sources folder!\n Do you want to continue?");
            if (decision==0)         
            {
                Global.project.getProcess().storeSources();                  
                JOptionPane.showMessageDialog(null, "Sources storing was successful!");
            }
        }
        else
            JOptionPane.showMessageDialog(null, "You need to create or load a project and start a process!");

    }//GEN-LAST:event_StoreSources_jMenuItemActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        SourceForm projectSourceForm=null;
        projectSourceForm=new SourceForm(null);

    }//GEN-LAST:event_jMenuItem1ActionPerformed

//EEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE
//-----------------------------------------------------------------------------
//                             Enable Process
//-----------------------------------------------------------------------------
//EEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE
private void enableProcess()
{
    this.imagePanel.setEnabled(true); 
    this.imagePanel.setImage(Config.processImageFileLocation);
    this.populateForm();
   }

//DDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDD
//-----------------------------------------------------------------------------
//                             Disable Process
//-----------------------------------------------------------------------------
//DDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDD
private void disableProcess()
{
    this.imagePanel.setEnabled(false);    
    imagePanel.setImage("");
    pack();
    repaint();
   }

private void clear()
{
Global.project=null;
Global.currentProcess=null;
Global.currentPhase=null;
this.disableProcess();
this.setTitle("");
}

//NNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNN
//-----------------------------------------------------------------------------
//                             New Project
//-----------------------------------------------------------------------------
//NNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNN
public boolean new_project()
{
        if (Project_Name_jTextField.getText().isEmpty())
        {
            JOptionPane.showMessageDialog(rootPane, "Oops...Missing project name");
            return false;}

       if (Project_Location_jTextField.getText().isEmpty())
       {
           JOptionPane.showMessageDialog(rootPane, "Oops...Missing project name");
           return false;
       }

            try {
                Project project=new Project(null);
                Global.project=project;
                Global.project.setName(Project_Name_jTextField.getText());
                // Note: empty boxes is recognised as "" but not seen as null
                Global.project.setLocation(Project_Location_jTextField.getText());
                Global.project.setDescription(Project_Description_jTextArea.getText());
                Global.project.setDate(Tools.getTime().toString());
                this.save_project(Global.project.getLocation());
                this.populateForm();
                return true;

            } catch (Exception ex) {
                Logger.getLogger(MainMenu.class.getName()).log(Level.SEVERE, null, ex);
                this.populateForm();
                return false;
            }
        }



//NNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNN
//-----------------------------------------------------------------------------
//                             New Process
//-----------------------------------------------------------------------------
//NNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNN
private void new_process()
{
   if(Global.project==null)
       JOptionPane.showMessageDialog(rootPane, "Oops...No project is " +
                        "created yet, Create or Load project and try again!");
   else
   {
     try {
            Process newProcess= new Process();
            newProcess.save();
            Global.project.save();            
        } catch (Exception ex) {
            Logger.getLogger(MainMenu.class.getName()).log(Level.SEVERE, null, ex);
        }
   this.populateForm();
   this.enableProcess();
    }
  }


//SSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSS
//-----------------------------------------------------------------------------
//                             Save Project
//-----------------------------------------------------------------------------
//SSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSS

private void save_project(String location)
{
    if (Global.project!=null)
    {
        try{
            if (location!=null && !location.equals("") )
            {
                Global.project.store(location);
            }
            else if (Global.project.getLocation()!=null)
                    Global.project.store(Global.project.getLocation());
                else
                    JOptionPane.showMessageDialog(rootPane, "Oops...Project location is not specified");

        } catch (Exception ex){
        Logger.getLogger(MainMenu.class.getName()).log(Level.SEVERE, null, ex);
       }
    }     
    else
       JOptionPane.showMessageDialog(rootPane, "Failed to save the project!");

    this.populateForm();
}


//SSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSS
//-----------------------------------------------------------------------------
//                             Save Project
//-----------------------------------------------------------------------------
//SSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSS
private void save_process( String location)
{
        if(Global.project==null)
                JOptionPane.showMessageDialog(rootPane, "Oops...No project is "
                        + "created yet...Create a project and try again!");
        else
           if(Global.project.getProcess()==null)
                     JOptionPane.showMessageDialog(rootPane, "Oops...No process"
                        + " is created yet...Create a process and try again!");
                else
                    Global.project.getProcess().store(location);

        this.populateForm();
    }



//LLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLL
//-----------------------------------------------------------------------------
//                             Load Project
//-----------------------------------------------------------------------------
//LLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLL
public boolean load_project() throws IOException, Exception
{
Project project =new Project();
   try {
         project = (Project) project.open();
        } catch (Exception ex) {
            Logger.getLogger(MainMenu.class.getName()).log(Level.SEVERE, null, ex);
        }
if (project!=null)
{
    //System.out.print(project.toString());
    Global.project=project;

   //--------------------------------------------------------------------------
    if (Global.project.getProcess()!=null)    
        this.enableProcess();
    else
        this.disableProcess();

    return true;
}
else
{         
    return false;
 }
}



//LLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLL
//-----------------------------------------------------------------------------
//                             Load Process
//-----------------------------------------------------------------------------
//LLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLL
private void load_process()
{
   // Process loading
   //------------------------------------------------------------------------
   if(Global.project==null)
                JOptionPane.showMessageDialog(rootPane, "Oops...No project is created yet, Create or Load project and try again!");
   else
      try {
        Process process=new Process();
        process=(Process) process.open();
        if (process!=null)
        {
            if(Global.project==null)
                JOptionPane.showMessageDialog(rootPane, "Oops...No project is created yet...Create a project and try again!");
            else
            {
                Global.project.setProcess(process);
                this.enableProcess();
                this.populateForm();
            }
        }
        } catch (Exception ex) {
            Logger.getLogger(PhaseForm.class.getName()).log(Level.SEVERE, null, ex);
        }
 }



//IIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIII
//-----------------------------------------------------------------------------
//                             Iterate Process
//-----------------------------------------------------------------------------
//IIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIII
private void iterate_process()
{
        if(Global.project==null)
                JOptionPane.showMessageDialog(rootPane, "Oops...No project is "
                        + "created yet...Create a project and try again!");
        else
           if(Global.project.getProcess()==null)
                     JOptionPane.showMessageDialog(rootPane, "Oops...No process "
                             + "is created yet...Create a process and try again!");
                else
                    Global.project.getProcess().iterate();


        this.populateForm();

    }

//COMBOCOMBOCOMBOCOMBOCOMBOCOMBOCOMBOCOMBOCOMBOCOMBOCOMBOCOMBOCOMBOCOMBOCOMBOCOE
//-----------------------------------------------------------------------------
//                             Select Process Versions
//-----------------------------------------------------------------------------
//COMBOCOMBOCOMBOCOMBOCOMBOCOMBOCOMBOCOMBOCOMBOCOMBOCOMBOCOMBOCOMBOCOMBOCOMBOCOE

private JComboBox selectProcessVersion(JComboBox jComboBox)
{
    try {
            Process process =new Process();
            process=Global.project.getProcess();
            int version = jComboBox.getSelectedIndex() + 1; //e.g. index 0 ==> version 1
       
            //-------------------------------------------------------------------------
            process=process.rollback(version);
            //save to Global project
            //----------------------------------------------------------------
            if (process!=null)
            {
                Global.project.setProcess(process);
            }
            else
                JOptionPane.showMessageDialog(null, "Message 2: Oops...No version is avilable...");

            this.populateForm();
            jComboBox.setSelectedIndex(version - 1); // e.g. version 1 ==> index 0
        }

         catch (Exception ex) {
            Logger.getLogger(MainMenu.class.getName()).log(Level.SEVERE, null, ex);
           }
            return jComboBox;
    }

//COMBOCOMBOCOMBOCOMBOCOMBOCOMBOCOMBOCOMBOCOMBOCOMBOCOMBOCOMBOCOMBOCOMBOCOMBOCOE
//-----------------------------------------------------------------------------
//                             Get Process Versions
//-----------------------------------------------------------------------------
//COMBOCOMBOCOMBOCOMBOCOMBOCOMBOCOMBOCOMBOCOMBOCOMBOCOMBOCOMBOCOMBOCOMBOCOMBOCOE
//-----------------------------------------------------------------------------
private ComboBoxModel getProcessVersions()
{
   String[] noVersions = {"no versions"};
   DefaultComboBoxModel dcm= new DefaultComboBoxModel(noVersions);   
   if (Global.project==null)
    {   
       return dcm;
    }

    else if(Global.project.getProcess()==null)
    {   
       return dcm;
    }

    else if (Global.project.getProcess().getIteration()==null)
    {
  
        return dcm;
    }
    else
    {
    String[] versions = new String[Global.project.getProcess().getIteration().getSize()];
   
   
   
    for (int i=0;i<Global.project.getProcess().getIteration().getSize();i++)
    {
        versions[i]="Version " +Integer.toString(
                Global.project.getProcess().getIteration(i).getVersion());
    
    }
    
    dcm=new DefaultComboBoxModel(versions);
    return dcm;
    }
 }

//------------------------------------------------------------------------------
//------------------------------------------------------------------------------
// ---------------------------( Main )------------------------------------------
//------------------------------------------------------------------------------
//------------------------------------------------------------------------------
  public static void main(String args[]) {
java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {                                               
                new MainMenu().setVisible(true);
            }
        });
  
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel DescriptionLabel;
    private javax.swing.JMenuItem ExportCustomisation_jMenuItem;
    private javax.swing.JMenuItem ExportSources_jMenuItem;
    private javax.swing.JMenuItem ExportSupplements_jMenuItem;
    private javax.swing.JMenuItem HumanInteraction_jMenuItem;
    private javax.swing.JMenuItem ImportCustomisation_jMenuItem;
    private javax.swing.JMenuItem ImportSources_jMenuItem;
    private javax.swing.JMenuItem ImportSupplements_jMenuItem;
    private javax.swing.JMenuItem JavaToXMLSchema_jMenuItem;
    private javax.swing.JMenuItem Management_jMenuItem;
    private javax.swing.JInternalFrame MeKDDaP;
    private javax.swing.JButton Process_Iterate_jButton1;
    private javax.swing.JMenuItem Process_Iterate_jMenuItem;
    private javax.swing.JButton Process_Load_jButton;
    private javax.swing.JMenuItem Process_Load_jMenuItem;
    private javax.swing.JButton Process_New_jButton1;
    private javax.swing.JMenuItem Process_New_jMenuItem;
    private javax.swing.JButton Process_Save_jButton;
    private javax.swing.JMenuItem Process_Save_jMenuItem;
    private javax.swing.JButton Process_Tree_jButton;
    private javax.swing.JComboBox Process_Version_jComboBox;
    private javax.swing.JMenu Process_jMenu;
    private javax.swing.JPanel Process_jPanel;
    private javax.swing.JButton Project_Browse_jButton;
    private javax.swing.JButton Project_Create_jButton;
    private javax.swing.JTextArea Project_Description_jTextArea;
    private javax.swing.JMenuItem Project_Exit_jMenuItem;
    private javax.swing.JButton Project_Load_jButton;
    private javax.swing.JMenuItem Project_Load_jMenuItem;
    private javax.swing.JTextField Project_Location_jTextField;
    private javax.swing.JTextField Project_Name_jTextField;
    private javax.swing.JMenuItem Project_New_jMenuItem;
    private javax.swing.JButton Project_SaveAs_jButton;
    private javax.swing.JMenuItem Project_SaveAs_jMenuItem;
    private javax.swing.JButton Project_Save_jButton;
    private javax.swing.JMenuItem Project_Save_jMenuItem;
    private javax.swing.JButton Project_delete_jButton;
    private javax.swing.JMenu Project_menu;
    private javax.swing.JMenuItem Quality_jMenuItem;
    private javax.swing.JMenu Sources_jMenu;
    private javax.swing.JMenuItem Standards_jMenuItem;
    private javax.swing.JMenuItem StoreSources_jMenuItem;
    private javax.swing.JMenuItem StoreSupplements_jMenuItem;
    private javax.swing.JMenu Supplements_menu;
    private javax.swing.JMenu Tools_jMenu;
    private javax.swing.JMenuItem XMLSchemaToJava_jMenuItem;
    private javax.swing.JMenuItem XMLTOSchema_jMenuItem;
    private java.awt.Label copyRightLabel;
    private javax.swing.JMenu dataBaseMenu;
    private javax.swing.JDesktopPane desktopPane;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem18;
    private javax.swing.JMenuItem jMenuItem19;
    private javax.swing.JMenuItem jMenuItem20;
    private javax.swing.JMenuItem jMenuItem21;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JPopupMenu.Separator jSeparator11;
    private javax.swing.JPopupMenu.Separator jSeparator14;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JPopupMenu.Separator jSeparator3;
    private javax.swing.JPopupMenu.Separator jSeparator4;
    private javax.swing.JPopupMenu.Separator jSeparator5;
    private javax.swing.JPopupMenu.Separator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JSeparator jSeparator9;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JLabel message1_jLabel1;
    private javax.swing.JLabel message2_jLabel1;
    private javax.swing.JTextArea process_jTextArea;
    private javax.swing.JLabel projectLocationLabel;
    private javax.swing.JLabel projectNameLabel;
    private javax.swing.JMenuItem storeCustomisation_jMenuItem;
    // End of variables declaration//GEN-END:variables
    //-------------------------------------------------------------------------
    //-------------------------------------------------------------------------
private void populateForm()
{
//====================================================================
if (Global.project != null) {
    this.Project_Name_jTextField.setText(Global.project.getName());
    this.Project_Description_jTextArea.setText(Global.project.getDescription());
    this.Project_Location_jTextField.setText(Global.project.getLocation());
       
    //-------------------------------------------------------------------------
    if (Global.project!=null && Global.project.getProcess()!=null)
    {    Config.processImage="phase100.jpg";// initialise the process with empty process
        this.setTitle( Global.project.getName()+"       "+Global.project.getLocation()+ "       "+
        Global.project.getDate()+"       "+  "Process Version "+ Global.project.getProcess().getVersion());     
        this.process_jTextArea.setText(Global.project.getProcess().toString());        
        int currentProcess=Global.project.getProcess().getVersion();
        //---------------------------------------------------------------------
        if ( Global.project!=null&& Global.project.getProcess()!=null)
          if (Global.project.getProcess().getPhases()!=null)
            {                
                int currentPhase=Global.project.getProcess().getPhases().get().size();
                Random random = null;
                if (Global.project.getProcess().getVersion()<=4)
                {
                    Config.processImage="phase"+currentProcess+"0"+currentPhase+""+".jpg";
                }
                else
                {                    
                    Config.processImage="phase"+(currentProcess%3)+2+"0"+currentPhase+""+".jpg";
                }
           }
         else
          {
               currentProcess=Global.project.getProcess().getVersion();               
               Random random = null;
               if (currentProcess<=4)
                 {
                   Config.processImage="phase"+currentProcess+"00.jpg";
                 }
               else
                 {
                   Config.processImage="phase"+random.nextInt(3)+2+"00.jpg";
                 }
            }

        //---------------------------------------------------------------------
        this.imagePanel.setImage(new File(Config.imagesLocation+Config.processImage).getAbsolutePath());
        }
       //------------------------------------------------------------------
        else
         {
           this.setTitle( Global.project.getName()+"       "+
           Global.project.getLocation()+ "       "+
           Global.project.getDate());
           this.process_jTextArea.setText("");
         }            
       //------------------------------------------------------------------
  } else
    {
      this.Project_Name_jTextField.setText("");
      this.Project_Description_jTextArea.setText("");
      this.Project_Location_jTextField.setText("");      
      this.process_jTextArea.setText("");      
    }
 this.Process_Version_jComboBox.setModel(this.getProcessVersions());
this.repaint();
this.pack();
}

}
