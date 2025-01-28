package view.vouchers;

import javax.swing.UIManager;

public class VMainView extends javax.swing.JFrame {

    public VMainView() throws Exception{
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        piBackground = new org.edisoncor.gui.panel.PanelImage();
        jbtVouchers = new javax.swing.JButton();
        jbtExamine = new javax.swing.JButton();
        jbtRegister = new javax.swing.JButton();
        jtfCode = new javax.swing.JTextField();
        jtfName = new javax.swing.JTextField();
        jlImage = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setIconImage(new javax.swing.ImageIcon(getClass().getResource("/images/vouchers/gift-voucher.png")).getImage());
        setResizable(false);

        piBackground.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/vouchers/bg_mainview.png"))); // NOI18N
        piBackground.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jbtVouchers.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/vouchers/btn_vouchers_off.png"))); // NOI18N
        jbtVouchers.setBorder(null);
        jbtVouchers.setBorderPainted(false);
        jbtVouchers.setContentAreaFilled(false);
        jbtVouchers.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jbtVouchers.setDefaultCapable(false);
        jbtVouchers.setFocusPainted(false);
        jbtVouchers.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/vouchers/btn_vouchers_on.png"))); // NOI18N
        jbtVouchers.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/vouchers/btn_vouchers_on.png"))); // NOI18N
        piBackground.add(jbtVouchers, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 90, -1, -1));

        jbtExamine.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/vouchers/btn_examine_off.png"))); // NOI18N
        jbtExamine.setBorder(null);
        jbtExamine.setBorderPainted(false);
        jbtExamine.setContentAreaFilled(false);
        jbtExamine.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jbtExamine.setDefaultCapable(false);
        jbtExamine.setFocusPainted(false);
        jbtExamine.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/vouchers/btn_examine_on.png"))); // NOI18N
        jbtExamine.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/vouchers/btn_examine_on.png"))); // NOI18N
        piBackground.add(jbtExamine, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 30, -1, -1));

        jbtRegister.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/vouchers/btn_register_off.png"))); // NOI18N
        jbtRegister.setBorder(null);
        jbtRegister.setBorderPainted(false);
        jbtRegister.setContentAreaFilled(false);
        jbtRegister.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jbtRegister.setDefaultCapable(false);
        jbtRegister.setFocusPainted(false);
        jbtRegister.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/vouchers/btn_register_on.png"))); // NOI18N
        jbtRegister.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/vouchers/btn_register_on.png"))); // NOI18N
        piBackground.add(jbtRegister, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 60, -1, -1));

        jtfCode.setBorder(null);
        piBackground.add(jtfCode, new org.netbeans.lib.awtextra.AbsoluteConstraints(151, 73, 190, -1));

        jtfName.setBorder(null);
        piBackground.add(jtfName, new org.netbeans.lib.awtextra.AbsoluteConstraints(151, 100, 190, -1));

        jlImage.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        piBackground.add(jlImage, new org.netbeans.lib.awtextra.AbsoluteConstraints(173, 160, 365, 251));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(piBackground, javax.swing.GroupLayout.PREFERRED_SIZE, 710, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(piBackground, javax.swing.GroupLayout.PREFERRED_SIZE, 431, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton jbtExamine;
    public javax.swing.JButton jbtRegister;
    public javax.swing.JButton jbtVouchers;
    public javax.swing.JLabel jlImage;
    public javax.swing.JTextField jtfCode;
    public javax.swing.JTextField jtfName;
    private org.edisoncor.gui.panel.PanelImage piBackground;
    // End of variables declaration//GEN-END:variables
}
