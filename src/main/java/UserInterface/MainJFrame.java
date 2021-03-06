package UserInterface;

import Business.Pathogen.Pathogen;
import Business.Platform.Platform;
import UserInterface.MultipleSimu.MultiSimuJPanel;
import UserInterface.SingleSimu.SingleSimuJPanel;

import javax.swing.*;
import java.awt.*;

/**
 * @Author: LiuLou
 * @Description:
 * @Date: Created in 2:38 2020/11/30
 * @Modified By:
 */
public class MainJFrame extends javax.swing.JFrame {

    private Platform platform;

    /**
     * Creates new form MainJFrame
     */
    public MainJFrame() {
        initComponents();
        this.setSize(1480, 700);
        testInitialization();
    }

    private void testInitialization() {
        this.platform = new Platform();
        //TODO initialize platform
        platform.getPathogenDirectory().add(new Pathogen());
        platform.getPathogenDirectory().add(new Pathogen("SARS", 0.86, 1.2));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jSplitPane1 = new javax.swing.JSplitPane();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        btnSingleSimu = new javax.swing.JButton();
        btnMultiSimu = new javax.swing.JButton();
        jplContainer = new javax.swing.JPanel();

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("arial", 1, 16)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Covid-19 Simulation Platform");
        jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        btnSingleSimu.setFont(new java.awt.Font("arial", 1, 12)); // NOI18N
        btnSingleSimu.setText("Single Pathogen Simulation");
        btnSingleSimu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSingleSimuActionPerformed(evt);
            }
        });

        btnMultiSimu.setFont(new java.awt.Font("arial", 1, 12)); // NOI18N
        btnMultiSimu.setText("Multiple Pathogens Simulation");
        btnMultiSimu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMultiSimuActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addContainerGap(34, Short.MAX_VALUE)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(btnMultiSimu, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(jPanel2Layout.createSequentialGroup()
                                                        .addComponent(btnSingleSimu, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addContainerGap())
                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                                        .addComponent(jLabel1)
                                                        .addGap(33, 33, 33)))))
        );
        jPanel2Layout.setVerticalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(80, 80, 80)
                                .addComponent(jLabel1)
                                .addGap(74, 74, 74)
                                .addComponent(btnSingleSimu)
                                .addGap(43, 43, 43)
                                .addComponent(btnMultiSimu)
                                .addContainerGap(153, Short.MAX_VALUE))
        );

        jSplitPane1.setLeftComponent(jPanel2);

        jplContainer.setLayout(new java.awt.CardLayout());
        jSplitPane1.setRightComponent(jplContainer);

        getContentPane().add(jSplitPane1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>

    private void btnSingleSimuActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        SingleSimuJPanel ssjp = new SingleSimuJPanel(jplContainer, platform);
        CardLayout cardLayout = (CardLayout) jplContainer.getLayout();
        jplContainer.add("SingleSimuJPanel",ssjp);
        cardLayout.next(jplContainer);
    }

    private void btnMultiSimuActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        MultiSimuJPanel msjp = new MultiSimuJPanel(jplContainer, platform);
        CardLayout cardLayout = (CardLayout) jplContainer.getLayout();
        jplContainer.add("MultiSimuJPanel",msjp);
        cardLayout.next(jplContainer);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainJFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify
    private javax.swing.JButton btnMultiSimu;
    private javax.swing.JButton btnSingleSimu;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JPanel jplContainer;
    // End of variables declaration
}
