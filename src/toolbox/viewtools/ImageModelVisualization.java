/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * ModelVisualization_JFrame.java
 *
 * Created on 22-Jul-2011, 07:45:14
 */
package toolbox.viewtools;

import java.io.File;
import toolbox.Tools;

/**
 *
 * @author amb04
 */
public class ImageModelVisualization extends javax.swing.JFrame {

    /** Creates new form ModelVisualization_JFrame */
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++    
    public ImageModelVisualization(File imageFile) 
    {
        initComponents();              
        this.setVisible(true);
        
        if (imageFile!=null && imageFile.exists())
        {
            model_jLabel.setIcon(new javax.swing.ImageIcon(Tools.toURL(imageFile))); // NOI18N                        
        }
//        else
//            System.out.println(" image file was not found");
         
         
    this.pack();
    this.repaint();
    }
    
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    @Override
    public void pack() {
        super.pack();
    }

    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        model_jLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        model_jLabel.setIcon(new javax.swing.ImageIcon("E:\\PhD\\PhD\\PhDThesis\\PhDSoftwareApplication\\Environment\\MeKDaM\\src\\toolbox\\viewtools\\blank.png")); // NOI18N
        model_jLabel.setName("model_jLabel"); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(model_jLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 910, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(model_jLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 630, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new ImageModelVisualization(null).setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel model_jLabel;
    // End of variables declaration//GEN-END:variables
}
