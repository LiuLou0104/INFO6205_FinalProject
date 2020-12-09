package UserInterface.SingleSimu;

import Business.Pathogen.Pathogen;
import Business.Platform.Platform;
import Business.Simulation.OnePathogenSimu;
import Business.Simulation.PageSimu;
import UserInterface.Canvas.SimuCanvasJPanel;
import UserInterface.ViewReport.ChooseSimuJPanel;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @Author: LiuLou
 * @Description:
 * @Date: Created in 2:38 2020/11/30
 * @Modified By:
 */
public class SingleSimuJPanel extends javax.swing.JPanel {

    /**
     * Creates new form SingleSimuJPanel
     */
    private JPanel jplContainer;
    private Platform platform;

    public SingleSimuJPanel(JPanel jplContainer, Platform platform) {
        initComponents();
        this.jplContainer = jplContainer;
        this.platform = platform;
        btnSeeAllReport.setEnabled(false);

        populateCbxPathogen();
        initButtonGroups();
    }

    private void initButtonGroups() {
        bgMask = new ButtonGroup();
        bgQuarantine = new ButtonGroup();
        bgTest = new ButtonGroup();

        bgMask.add(rbtnMaskYes);
        bgMask.add(rbtnMaskNo);
        bgQuarantine.add(rbtnQuarantineYes);
        bgQuarantine.add(rbtnQuarantineNo);
        bgTest.add(rbtnTestYes);
        bgTest.add(rbtnTestNo);
    }

    private void populateCbxPathogen() {
        DefaultComboBoxModel<Object> dcbm = new DefaultComboBoxModel<>();
        //TODO waiting for business code
        for (Pathogen pathogen : platform.getPathogenDirectory().getPathogenList()) {
            dcbm.addElement(pathogen);
        }
//        List<Pathogen> list = new ArrayList<>();
//        list.add(new Pathogen());
//        list.add(new Pathogen("SARS", 2, 1));
//        for (Pathogen pathogen : list) {
//            dcbm.addElement(pathogen);
//        }
        cbxPathogen.setModel(dcbm);
        cbxPathogen.setSelectedIndex(-1);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {

        jplCanvas = new SimuCanvasJPanel();
        btnBack = new javax.swing.JButton();
        btnSeeAllReport = new javax.swing.JButton();
        txtPopulationDensity = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        rbtnMaskYes = new javax.swing.JRadioButton();
        rbtnMaskNo = new javax.swing.JRadioButton();
        jLabel3 = new javax.swing.JLabel();
        rbtnTestYes = new javax.swing.JRadioButton();
        rbtnTestNo = new javax.swing.JRadioButton();
        jLabel4 = new javax.swing.JLabel();
        rbtnQuarantineYes = new javax.swing.JRadioButton();
        rbtnQuarantineNo = new javax.swing.JRadioButton();
        jLabel5 = new javax.swing.JLabel();
        cbxPathogen = new javax.swing.JComboBox<>();
        btnStartSimu = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        lblAreaUnitPosition = new javax.swing.JLabel();

        jplCanvas.setBackground(new java.awt.Color(240, 240, 240));

        javax.swing.GroupLayout jplCanvasLayout = new javax.swing.GroupLayout(jplCanvas);
        jplCanvas.setLayout(jplCanvasLayout);
        jplCanvasLayout.setHorizontalGroup(
                jplCanvasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 460, Short.MAX_VALUE)
        );
        jplCanvasLayout.setVerticalGroup(
                jplCanvasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 320, Short.MAX_VALUE)
        );

        btnBack.setFont(new java.awt.Font("arial", 1, 14)); // NOI18N
        btnBack.setText("<<Back");
        btnBack.setEnabled(false);
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });

        btnSeeAllReport.setBackground(new java.awt.Color(255, 153, 51));
        btnSeeAllReport.setFont(new java.awt.Font("arial", 1, 14)); // NOI18N
        btnSeeAllReport.setText("See All Reports");
        btnSeeAllReport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSeeAllReportActionPerformed(evt);
            }
        });

        txtPopulationDensity.setToolTipText("");

        jLabel1.setFont(new java.awt.Font("arial", 1, 12)); // NOI18N
        jLabel1.setText("Population Density");

        jLabel2.setFont(new java.awt.Font("arial", 1, 12)); // NOI18N
        jLabel2.setText("Wearing Masks");

        rbtnMaskYes.setText("yes");

        rbtnMaskNo.setText("no");
        rbtnMaskNo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbtnMaskNoActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("arial", 1, 12)); // NOI18N
        jLabel3.setText("Testing and Contact Tracing");

        rbtnTestYes.setText("yes");

        rbtnTestNo.setText("no");
        rbtnTestNo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbtnTestNoActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("arial", 1, 12)); // NOI18N
        jLabel4.setText("Quarantining");

        rbtnQuarantineYes.setText("yes");

        rbtnQuarantineNo.setText("no");
        rbtnQuarantineNo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbtnQuarantineNoActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("arial", 1, 12)); // NOI18N
        jLabel5.setText("Pathogen Type");

        cbxPathogen.setFont(new java.awt.Font("arial", 2, 12)); // NOI18N
        cbxPathogen.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        btnStartSimu.setBackground(new java.awt.Color(0, 0, 0));
        btnStartSimu.setFont(new java.awt.Font("arial", 1, 14)); // NOI18N
        btnStartSimu.setForeground(new java.awt.Color(255, 0, 0));
        btnStartSimu.setText("Start Simulation");
        btnStartSimu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStartSimuActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("arial", 1, 16)); // NOI18N
        jLabel6.setText("Single Pathogen Simulation");

        lblAreaUnitPosition.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblAreaUnitPosition.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        lblAreaUnitPosition.setText("This pandemic is starting from block [x,y]");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(26, 26, 26)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(btnBack, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(lblAreaUnitPosition, javax.swing.GroupLayout.PREFERRED_SIZE, 316, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(jplCanvas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel6))
                                .addGap(38, 38, 38)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(btnSeeAllReport, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(txtPopulationDensity)
                                        .addComponent(jLabel5)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(rbtnMaskYes)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(rbtnMaskNo, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(rbtnTestYes)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(rbtnTestNo, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(rbtnQuarantineYes)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(rbtnQuarantineNo, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(cbxPathogen, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(btnStartSimu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addContainerGap(22, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(80, 80, 80)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(btnSeeAllReport)
                                        .addComponent(jLabel6))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jplCanvas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel5)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(cbxPathogen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jLabel1)
                                                .addGap(8, 8, 8)
                                                .addComponent(txtPopulationDensity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(jLabel2)
                                                .addGap(8, 8, 8)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(rbtnMaskYes)
                                                        .addComponent(rbtnMaskNo))
                                                .addGap(18, 18, 18)
                                                .addComponent(jLabel4)
                                                .addGap(8, 8, 8)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(rbtnQuarantineYes)
                                                        .addComponent(rbtnQuarantineNo))
                                                .addGap(18, 18, 18)
                                                .addComponent(jLabel3)
                                                .addGap(8, 8, 8)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(rbtnTestYes)
                                                        .addComponent(rbtnTestNo))))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(btnBack, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(btnStartSimu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(lblAreaUnitPosition, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addContainerGap(105, Short.MAX_VALUE))
        );
    }// </editor-fold>

    private void btnStartSimuActionPerformed(java.awt.event.ActionEvent evt) {
        if (cbxPathogen.getSelectedIndex() == -1) {
            JOptionPane.showMessageDialog(null, "Please select one Pathogen!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        Pathogen pathogen = (Pathogen) cbxPathogen.getSelectedItem();

        if (txtPopulationDensity.getText().isEmpty() || txtPopulationDensity.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please enter the population density!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        double popuDensity = Double.parseDouble(txtPopulationDensity.getText());

        ButtonModel bgMaskSelection = bgMask.getSelection();
        if (bgMaskSelection == null) {
            JOptionPane.showMessageDialog(null, "Please select the Wearing Masks option!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        boolean isWearingMask = rbtnMaskYes.isSelected();

        ButtonModel bgQuarantineSelection = bgQuarantine.getSelection();
        if (bgQuarantineSelection == null) {
            JOptionPane.showMessageDialog(null, "Please select the Quarantining option!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        boolean isQuarantine = rbtnQuarantineYes.isSelected();

        ButtonModel bgTestSelection = bgTest.getSelection();
        if (bgTestSelection == null) {
            JOptionPane.showMessageDialog(null, "Please select the Quarantining option!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        boolean isTest = rbtnTestYes.isSelected();

        // create PageSimu
        PageSimu ps = new PageSimu();
        // create OnePathogenSimu
        OnePathogenSimu ops = new OnePathogenSimu(pathogen, popuDensity, isWearingMask, isQuarantine, isTest);
        Random random = new Random();
        int index1 = random.nextInt(OnePathogenSimu.AREA_WIDTH);
        int index2 = random.nextInt(OnePathogenSimu.AREA_LENGTH);

        lblAreaUnitPosition.setText("This pandemic is starting from block [" + index1 + ", " + index2 + " ]");

        ops.getAreaUnitArray()[index1][index2].setPopulationDensity(popuDensity);
        ops.getAreaUnitArray()[index1][index2].setQuarantine(isQuarantine);
        ops.getAreaUnitArray()[index1][index2].setMask(isWearingMask);
        ops.getAreaUnitArray()[index1][index2].setTest(isTest);
        ops.getAreaUnitArray()[index1][index2].setInfectNum(ops.getAreaUnitArray()[index1][index2].getHeadcount());
//        ops1.getAreaUnitArray()[index1][index2].setInfectNum(random.nextInt((int) ops1.getAreaUnitArray()[index1][index2].getHeadcount()));

        ops.addObserver(jplCanvas);
        ops.startSim(btnStartSimu, btnSeeAllReport);

        // TODO use CompletableFuture to make sure that the PageSimu result appears only when the simulation has done
        // add above OnePathogenSimu to OnePathogenSimuList in PageSimu
        ps.add(ops);
        // add above PageSimu to PageSimuDirectory in platform
        platform.getPageSimuDirectory().add(ps);
    }

    private void btnSeeAllReportActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        ChooseSimuJPanel csjp = new ChooseSimuJPanel(jplContainer, platform);
        CardLayout cardLayout = (CardLayout) jplContainer.getLayout();
        jplContainer.add("ChooseSimuJPanel",csjp);
        cardLayout.next(jplContainer);
    }

    private void rbtnMaskNoActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void rbtnTestNoActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void rbtnQuarantineNoActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        jplContainer.remove(this);
        CardLayout layout = (CardLayout) jplContainer.getLayout();
        layout.previous(jplContainer);
    }


    // Variables declaration - do not modify
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnSeeAllReport;
    private javax.swing.JButton btnStartSimu;
    private javax.swing.JComboBox<Object> cbxPathogen;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private SimuCanvasJPanel jplCanvas;
    private javax.swing.JLabel lblAreaUnitPosition;
    private javax.swing.ButtonGroup bgMask;
    private javax.swing.ButtonGroup bgQuarantine;
    private javax.swing.ButtonGroup bgTest;
    private javax.swing.JRadioButton rbtnMaskNo;
    private javax.swing.JRadioButton rbtnMaskYes;
    private javax.swing.JRadioButton rbtnQuarantineNo;
    private javax.swing.JRadioButton rbtnQuarantineYes;
    private javax.swing.JRadioButton rbtnTestNo;
    private javax.swing.JRadioButton rbtnTestYes;
    private javax.swing.JTextField txtPopulationDensity;
    // End of variables declaration
}
