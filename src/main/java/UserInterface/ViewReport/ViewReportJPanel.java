package UserInterface.ViewReport;

import javax.swing.*;
import java.awt.*;

/**
 * @Author: LiuLou
 * @Description:
 * @Date: Created in 2:43 2020/11/30
 * @Modified By:
 */
public class ViewReportJPanel extends javax.swing.JPanel {

    /**
     * Creates new form ViewReportJPanel
     */
    private JPanel jplContainer;

    public ViewReportJPanel(JPanel jplContainer) {
        initComponents();
        this.jplContainer = jplContainer;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {

        jLabel6 = new javax.swing.JLabel();
        btnBack1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        btnShowInExplorer = new javax.swing.JButton();

        jLabel6.setFont(new java.awt.Font("微软雅黑", 1, 16)); // NOI18N
        jLabel6.setText("Simulation Made On [yyyy:MM:dd HH:mm:ss] ");

        btnBack1.setFont(new java.awt.Font("微软雅黑", 1, 14)); // NOI18N
        btnBack1.setText("<<Back");
        btnBack1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBack1ActionPerformed(evt);
            }
        });

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        System.out.println(getClass().getResource("/").getPath());
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/test.jpg")));
        jLabel1.setText("Graph");

        btnShowInExplorer.setFont(new java.awt.Font("微软雅黑", 1, 14)); // NOI18N
        btnShowInExplorer.setText("Show In Explorer");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(26, 26, 26)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(btnBack1, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel6)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 377, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(57, 57, 57)
                                                .addComponent(btnShowInExplorer)))
                                .addContainerGap(100, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(80, 80, 80)
                                .addComponent(jLabel6)
                                .addGap(68, 68, 68)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnShowInExplorer))
                                .addGap(82, 82, 82)
                                .addComponent(btnBack1)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>

    private void btnBack1ActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        jplContainer.remove(this);
        CardLayout layout = (CardLayout) jplContainer.getLayout();
        layout.previous(jplContainer);
    }


    // Variables declaration - do not modify
    private javax.swing.JButton btnBack1;
    private javax.swing.JButton btnShowInExplorer;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel6;
    // End of variables declaration
}
