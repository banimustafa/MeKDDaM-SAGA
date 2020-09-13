package toolbox.viewtools;
import global.Global;
import toolbox.viewtools.DataFileViewerMainPanel;
import weka.gui.ComponentHelper;
import weka.gui.LookAndFeel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import weka.core.Instances;

public class DataFileViewer   extends JFrame
{      
  private DataFileViewerMainPanel m_MainPanel;
  private boolean m_FilesLoaded;
  private String[] m_Args;
  
  public DataFileViewer(String[] args)
  {
    super("Metabolomics Data Viewer");
    m_Args=args;    
    createFrame();    
    processArgs();
    this.setVisible(true);
  }
public DataFileViewer()
  {
    super("Metabolomics Data Viewer");
    createFrame();
    this.setVisible(true);
    
  }


@Override
public void dispose()
{
    
if (this.getMainPanel().getCurrentPanel().isChanged()==true )
{
    int decision=JOptionPane.showConfirmDialog(null, "Would you like to save data before exiting !?");
    if (decision==0)
    {
        if (Global.isAcclimatization)
        {
           if (getMainPanel().getCurrentFilename().toLowerCase().contains("testing"))
               this.getMainPanel().saveData("testing");
           else if (getMainPanel().getCurrentFilename().toLowerCase().contains("training"))
               this.getMainPanel().saveData("training");
           else
                this.getMainPanel().saveData("all");           
        }
        else
           this.getMainPanel().saveFile();
        
        super.dispose();
    }
    else if (decision==1)    
        super.dispose();       
    else
        this.repaint();
}
else
    super.dispose();       
}

        
        
        
public String[] getM_Args()
{
        return m_Args;
}
private void processArgs()
{
  if ( (m_Args.length > 0) && (!m_FilesLoaded) )
  {
    for (int i = 0; i < m_Args.length; i++)
    {
//      System.out.println("Loading " + (i+1) + "/"  + m_Args.length +  ": '" + m_Args[i] + "'...");
      this.getMainPanel().loadFile(m_Args[i]);
    }
    this.getMainPanel().getTabbedPane().setSelectedIndex(0);
//    System.out.println("Finished!");
    m_FilesLoaded = true;
  }  
}

public void setM_Args(String[] m_Args)
{
  this.m_Args = m_Args;
  processArgs();
 }
  
  /**
   * creates all the components in the frame
   */
  private void createFrame() {
    // basic setup
    setIconImage(ComponentHelper.getImage(""));
    setSize(DataFileViewerMainPanel.WIDTH, DataFileViewerMainPanel.HEIGHT);
    setCenteredLocation();
    setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    // remove the listener - otherwise we get the strange behavior that one
    // frame receives a window-event for every single open frame!
    //removeWindowListener(this);
    // add listener anew
    //addWindowListener(this);
    
    getContentPane().setLayout(new BorderLayout());
    
    m_MainPanel = new DataFileViewerMainPanel(this);

    m_MainPanel.setConfirmExit(false);
    m_MainPanel.setBackground(Color.orange);
    getContentPane().add(m_MainPanel, BorderLayout.CENTER);
    
    setJMenuBar(m_MainPanel.getMenu());
    LookAndFeel.getSystemLookAndFeel();    
  }
//----------------------------------------------------------------------------

  /**
   * returns the left coordinate if the frame would be centered
   * 
   * @return 		the left coordinate
   */
  protected int getCenteredLeft() {
    int            width;
    int            x;
    
    width  = getBounds().width;
    x      = (getGraphicsConfiguration().getBounds().width  - width) / 2;
    
    if (x < 0) 
      x = 0;
    
    return x;
  }
  
  /**
   * returns the top coordinate if the frame would be centered
   * 
   * @return		the top coordinate
   */
  protected int getCenteredTop() {
    int            height;
    int            y;
    
    height = getBounds().height;
    y      = (getGraphicsConfiguration().getBounds().height - height) / 2;
    
    if (y < 0) 
      y = 0;
    
    return y;
  }
  
  /**
   * positions the window at the center of the screen
   */
  public void setCenteredLocation() { 
    setLocation(getCenteredLeft(), getCenteredTop());
  }
  
  /**
   * whether to present a MessageBox on Exit or not
   * @param confirm           whether a MessageBox pops up or not to confirm
   *                          exit
   */
  public void setConfirmExit(boolean confirm) {
    m_MainPanel.setConfirmExit(confirm);
  }
  
  /**
   * returns the setting of whether to display a confirm messagebox or not
   * on exit
   * @return                  whether a messagebox is displayed or not
   */
  public boolean getConfirmExit() {
    return m_MainPanel.getConfirmExit();
  }

  /**
   * whether to do a System.exit(0) on close
   * 
   * @param value	enables/disables the System.exit(0)
   */
  public void setExitOnClose(boolean value) {
    m_MainPanel.setExitOnClose(value);
  }

  /**
   * returns TRUE if a System.exit(0) is done on a close
   * 
   * @return		true if System.exit(0) is done
   */
  public boolean getExitOnClose() {
    return m_MainPanel.getExitOnClose();
  }
  
  /**
   * returns the main panel
   * 
   * @return		the main panel
   */
  public DataFileViewerMainPanel getMainPanel() {
    return m_MainPanel;
  }
  
  /**
   * validates and repaints the frame
   */
  public void refresh() {
    validate();
    repaint();
  }

  //***************************************************************************
  /**
   * invoked when a window is in the process of closing
   * 
   * @param e		the window event
   */
  public void windowClosing(WindowEvent e) {
    int         button;
    
    while (getMainPanel().getTabbedPane().getTabCount() > 0)
      getMainPanel().closeFile(true);
    
    if (getConfirmExit()) {
      button = ComponentHelper.showMessageBox(
          this,
          "Quit - " + getTitle(),
          "Do you really want to quit?",
          JOptionPane.YES_NO_OPTION,
          JOptionPane.QUESTION_MESSAGE);
      if (button == JOptionPane.YES_OPTION)
        dispose();
      
    } 
    else {
      dispose();
    }

    if (getExitOnClose())
      System.exit(0);
  }

  /**
   * returns only the classname
   * 
   * @return 		the classname
   */
    @Override
  public String toString() {
    return this.getClass().getName();
  }

}
