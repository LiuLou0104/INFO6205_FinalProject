package UserInterface.MultipleSimu;

import Business.Pathogen.Pathogen;
import Business.Platform.Platform;
import Business.Simulation.OnePathogenSimu;
import Business.Simulation.PageSimu;
import UserInterface.Canvas.SimuCanvasJPanel;
import UserInterface.ViewReport.ChooseSimuJPanel;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

/**
 * @Author: LiuLou
 * @Description:
 * @Date: Created in 2:38 2020/11/30
 * @Modified By:
 */
public class MultiSimuJPanel extends javax.swing.JPanel {

    /**
     * Creates new form MultiSimuJPanel
     */
    private JPanel jplContainer;
    private Platform platform;

    public MultiSimuJPanel(JPanel jplContainer, Platform platform) {
        initComponents();
        this.jplContainer = jplContainer;
        this.platform = platform;

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
        DefaultComboBoxModel<Object> dcbm1 = new DefaultComboBoxModel<>();
        DefaultComboBoxModel<Object> dcbm2 = new DefaultComboBoxModel<>();
        //TODO waiting for business code
        for (Pathogen pathogen : platform.getPathogenDirectory().getPathogenList()) {
            dcbm1.addElement(pathogen);
            dcbm2.addElement(pathogen);
        }
//        List<Pathogen> list = new ArrayList<>();
//        list.add(new Pathogen());
//        list.add(new Pathogen("SARS", 2, 1));
//        for (Pathogen pathogen : list) {
//            dcbm1.addElement(pathogen);
//            dcbm2.addElement(pathogen);
//        }
        cbxPathogen1.setModel(dcbm1);
        cbxPathogen1.setSelectedIndex(-1);

        cbxPathogen2.setModel(dcbm2);
        cbxPathogen2.setSelectedIndex(-1);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {

        rbtnMaskNo = new javax.swing.JRadioButton();
        jplCanvas1 = new SimuCanvasJPanel();
        jLabel3 = new javax.swing.JLabel();
        btnBack = new javax.swing.JButton();
        rbtnTestYes = new javax.swing.JRadioButton();
        btnSeeAllReport = new javax.swing.JButton();
        rbtnTestNo = new javax.swing.JRadioButton();
        txtPopulationDensity = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        rbtnQuarantineYes = new javax.swing.JRadioButton();
        rbtnQuarantineNo = new javax.swing.JRadioButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        cbxPathogen1 = new javax.swing.JComboBox<>();
        btnStartSimu = new javax.swing.JButton();
        rbtnMaskYes = new javax.swing.JRadioButton();
        jLabel6 = new javax.swing.JLabel();
        jplCanvas2 = new SimuCanvasJPanel();
        lblPathogen1 = new javax.swing.JLabel();
        lblPathogen2 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        cbxPathogen2 = new javax.swing.JComboBox<>();
        lblAreaUnitPosition = new javax.swing.JLabel();

        rbtnMaskNo.setText("no");
        rbtnMaskNo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbtnMaskNoActionPerformed(evt);
            }
        });

        jplCanvas1.setBackground(new java.awt.Color(240, 240, 240));
        jplCanvas1.setPreferredSize(new java.awt.Dimension(340, 340));

        javax.swing.GroupLayout jplCanvas1Layout = new javax.swing.GroupLayout(jplCanvas1);
        jplCanvas1.setLayout(jplCanvas1Layout);
        jplCanvas1Layout.setHorizontalGroup(
                jplCanvas1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 340, Short.MAX_VALUE)
        );
        jplCanvas1Layout.setVerticalGroup(
                jplCanvas1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 340, Short.MAX_VALUE)
        );

        jLabel3.setFont(new java.awt.Font("arial", 1, 12)); // NOI18N
        jLabel3.setText("Testing and Contact Tracing");

        btnBack.setFont(new java.awt.Font("arial", 1, 14)); // NOI18N
        btnBack.setText("<<Back");
        btnBack.setEnabled(false);
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });

        rbtnTestYes.setText("yes");

        btnSeeAllReport.setBackground(new java.awt.Color(255, 153, 51));
        btnSeeAllReport.setFont(new java.awt.Font("arial", 1, 14)); // NOI18N
        btnSeeAllReport.setText("See All Reports");
        btnSeeAllReport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSeeAllReportActionPerformed(evt);
            }
        });

        rbtnTestNo.setText("no");
        rbtnTestNo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbtnTestNoActionPerformed(evt);
            }
        });

        txtPopulationDensity.setToolTipText("");

        jLabel4.setFont(new java.awt.Font("arial", 1, 12)); // NOI18N
        jLabel4.setText("Quarantining");

        jLabel1.setFont(new java.awt.Font("arial", 1, 12)); // NOI18N
        jLabel1.setText("Population Density");

        rbtnQuarantineYes.setText("yes");

        rbtnQuarantineNo.setText("no");
        rbtnQuarantineNo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbtnQuarantineNoActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("arial", 1, 12)); // NOI18N
        jLabel2.setText("Wearing Masks");

        jLabel5.setFont(new java.awt.Font("arial", 1, 12)); // NOI18N
        jLabel5.setText("Pathogen1 Type");

        cbxPathogen1.setFont(new java.awt.Font("arial", 2, 12)); // NOI18N
        cbxPathogen1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        btnStartSimu.setBackground(new java.awt.Color(0, 0, 0));
        btnStartSimu.setFont(new java.awt.Font("arial", 1, 14)); // NOI18N
        btnStartSimu.setForeground(new java.awt.Color(255, 0, 0));
        btnStartSimu.setText("Start Simulation");
        btnStartSimu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStartSimuActionPerformed(evt);
            }
        });

        rbtnMaskYes.setText("yes");

        jLabel6.setFont(new java.awt.Font("arial", 1, 16)); // NOI18N
        jLabel6.setText("Multiple Pathogens Simulation Comparison");

        jplCanvas2.setBackground(new java.awt.Color(240, 240, 240));
        jplCanvas2.setPreferredSize(new java.awt.Dimension(340, 340));

        javax.swing.GroupLayout jplCanvas2Layout = new javax.swing.GroupLayout(jplCanvas2);
        jplCanvas2.setLayout(jplCanvas2Layout);
        jplCanvas2Layout.setHorizontalGroup(
                jplCanvas2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 340, Short.MAX_VALUE)
        );
        jplCanvas2Layout.setVerticalGroup(
                jplCanvas2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 0, Short.MAX_VALUE)
        );

        lblPathogen1.setFont(new java.awt.Font("arial", 3, 14)); // NOI18N
        lblPathogen1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPathogen1.setText("Pathogen1");

        lblPathogen2.setFont(new java.awt.Font("arial", 3, 14)); // NOI18N
        lblPathogen2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPathogen2.setText("Pathogen2");

        jLabel7.setFont(new java.awt.Font("arial", 1, 12)); // NOI18N
        jLabel7.setText("Pathogen2 Type");

        cbxPathogen2.setFont(new java.awt.Font("arial", 2, 12)); // NOI18N
        cbxPathogen2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

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
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 166, Short.MAX_VALUE)
                                                .addComponent(lblAreaUnitPosition, javax.swing.GroupLayout.PREFERRED_SIZE, 453, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(jLabel6)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                        .addComponent(lblPathogen1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(jplCanvas1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                .addGap(34, 34, 34)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                        .addComponent(jplCanvas2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(lblPathogen2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                                .addGap(23, 23, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(rbtnQuarantineYes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(rbtnQuarantineNo, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(btnSeeAllReport, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                                .addComponent(rbtnMaskYes)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(rbtnMaskNo, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                                .addComponent(rbtnTestYes)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(rbtnTestNo, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(cbxPathogen1, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(btnStartSimu, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(txtPopulationDensity, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(cbxPathogen2, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addContainerGap(47, Short.MAX_VALUE))
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
                                        .addComponent(jplCanvas2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jplCanvas1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel5)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(cbxPathogen1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jLabel7)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(cbxPathogen2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(jLabel1)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(txtPopulationDensity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(14, 14, 14)
                                                .addComponent(jLabel2)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(rbtnMaskYes)
                                                        .addComponent(rbtnMaskNo))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(jLabel4)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(rbtnQuarantineYes)
                                                        .addComponent(rbtnQuarantineNo))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(jLabel3)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(rbtnTestYes)
                                                        .addComponent(rbtnTestNo))))
                                .addGap(20, 20, 20)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(lblPathogen1)
                                                        .addComponent(lblPathogen2))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(btnBack)
                                                        .addComponent(lblAreaUnitPosition, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                        .addComponent(btnStartSimu, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(21, Short.MAX_VALUE))
        );
    }// </editor-fold>

    private void btnStartSimuActionPerformed(java.awt.event.ActionEvent evt) {
        if (cbxPathogen1.getSelectedIndex() == -1) {
            JOptionPane.showMessageDialog(null, "Please select Pathogen1!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        Pathogen pathogen1 = (Pathogen) cbxPathogen1.getSelectedItem();

        if (cbxPathogen2.getSelectedIndex() == -1) {
            JOptionPane.showMessageDialog(null, "Please select Pathogen2!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        Pathogen pathogen2 = (Pathogen) cbxPathogen2.getSelectedItem();

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

        // create one PageSimu
        PageSimu ps = new PageSimu();

        // change label text
        lblPathogen1.setText(pathogen1.getName());
        lblPathogen2.setText(pathogen2.getName());

        // create two OnePathogenSimu
        OnePathogenSimu ops1 = new OnePathogenSimu(pathogen1, popuDensity, isWearingMask, isQuarantine, isTest);
        OnePathogenSimu ops2 = new OnePathogenSimu(pathogen2, popuDensity, isWearingMask, isQuarantine, isTest);
//        ops2.setArea(ops1.getArea());
//        ops2.setAreaUnitArray(ops1.getAreaUnitArray());

        // set the two OnePathogenSimu have the same random values
        for(int i=0; i<OnePathogenSimu.AREA_WIDTH; i++) {
           for(int j=0; j<OnePathogenSimu.AREA_LENGTH; j++) {
               ops2.getAreaUnitArray()[i][j].setHeadcount(ops1.getAreaUnitArray()[i][j].getHeadcount());
               ops2.getAreaUnitArray()[i][j].setPopFlowSpeed(ops1.getAreaUnitArray()[i][j].getPopFlowSpeed());
               ops2.getAreaUnitArray()[i][j].setInfectNum(ops1.getAreaUnitArray()[i][j].getInfectNum());
               ops2.getAreaUnitArray()[i][j].setQuarantine(ops1.getAreaUnitArray()[i][j].isQuarantine());
               ops2.getAreaUnitArray()[i][j].setMask(ops1.getAreaUnitArray()[i][j].isMask());
               ops2.getAreaUnitArray()[i][j].setTest(ops1.getAreaUnitArray()[i][j].isTest());
           }
        }
        Random random = new Random();
//        int index1 = random.nextInt(OnePathogenSimu.AREA_WIDTH);
//        int index2 = random.nextInt(OnePathogenSimu.AREA_LENGTH);
        int index1 = random.nextInt(OnePathogenSimu.AREA_WIDTH);
        int index2 = random.nextInt(OnePathogenSimu.AREA_LENGTH);

        lblAreaUnitPosition.setText("This pandemic is starting from block [" + index1 + ", " + index2 + " ]");

//        ops1.getAreaUnitArray()[index1][index2].setInfectNum(random.nextInt((int) ops1.getAreaUnitArray()[index1][index2].getHeadcount()));
        ops1.getAreaUnitArray()[index1][index2].setPopulationDensity(popuDensity);
        ops1.getAreaUnitArray()[index1][index2].setQuarantine(isQuarantine);
        ops1.getAreaUnitArray()[index1][index2].setMask(isWearingMask);
        ops1.getAreaUnitArray()[index1][index2].setTest(isTest);
        ops1.getAreaUnitArray()[index1][index2].setInfectNum(ops1.getAreaUnitArray()[index1][index2].getHeadcount() / 2);

        ops2.getAreaUnitArray()[index1][index2].setPopulationDensity(popuDensity);
        ops2.getAreaUnitArray()[index1][index2].setQuarantine(isQuarantine);
        ops2.getAreaUnitArray()[index1][index2].setMask(isWearingMask);
        ops2.getAreaUnitArray()[index1][index2].setTest(isTest);
        ops2.getAreaUnitArray()[index1][index2].setInfectNum(ops2.getAreaUnitArray()[index1][index2].getHeadcount() / 2);

        ops1.addObserver(jplCanvas1);
        ops1.startSim(btnStartSimu);

        ops2.addObserver(jplCanvas2);
        ops2.startSim(btnStartSimu);

        // add above two OnePathogenSimu to OnePathogenSimuList in PageSimu
        ps.add(ops1);
        ps.add(ops2);

        // add above PageSimu to PageSimuDirectory in platform
        platform.getPageSimuDirectory().add(ps);
    }

    private void rbtnMaskNoActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        jplContainer.remove(this);
        CardLayout layout = (CardLayout) jplContainer.getLayout();
        layout.previous(jplContainer);
    }

    private void btnSeeAllReportActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        ChooseSimuJPanel csjp = new ChooseSimuJPanel(jplContainer, platform);
        CardLayout cardLayout = (CardLayout) jplContainer.getLayout();
        jplContainer.add("ChooseSimuJPanel",csjp);
        cardLayout.next(jplContainer);
    }

    private void rbtnTestNoActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void rbtnQuarantineNoActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }


    // Variables declaration - do not modify
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnStartSimu;
    private javax.swing.JButton btnSeeAllReport;
    private javax.swing.JComboBox<Object> cbxPathogen1;
    private javax.swing.JComboBox<Object> cbxPathogen2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel lblPathogen2;
    private SimuCanvasJPanel jplCanvas1;
    private SimuCanvasJPanel jplCanvas2;
    private javax.swing.JLabel lblAreaUnitPosition;
    private javax.swing.JLabel lblPathogen1;
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
