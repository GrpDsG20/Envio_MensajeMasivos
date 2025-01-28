package view;

import javax.swing.UIManager;

public class MainView extends javax.swing.JFrame {

    public MainView() throws Exception{
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        piBackground = new org.edisoncor.gui.panel.PanelImage();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        jbtEmail = new javax.swing.JButton();
        jbtVouchers = new javax.swing.JButton();
        jbtCloseSession = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Menú principal");
        setIconImage(new javax.swing.ImageIcon(getClass().getResource("/images/main_logo.png")).getImage());
        setResizable(false);

        piBackground.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/main/bg_main.png"))); // NOI18N
        piBackground.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jScrollPane1.setBorder(null);

        jPanel1.setLayout(new java.awt.GridBagLayout());

        jbtEmail.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/main/btn_email_off.png"))); // NOI18N
        jbtEmail.setBorder(null);
        jbtEmail.setBorderPainted(false);
        jbtEmail.setContentAreaFilled(false);
        jbtEmail.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jbtEmail.setFocusPainted(false);
        jbtEmail.setFocusable(false);
        jbtEmail.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/main/btn_email_on.png"))); // NOI18N
        jbtEmail.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/main/btn_email_on.png"))); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.ipady = 5;
        jPanel1.add(jbtEmail, gridBagConstraints);

        jbtVouchers.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/main/btn_vouchers_off.png"))); // NOI18N
        jbtVouchers.setBorder(null);
        jbtVouchers.setBorderPainted(false);
        jbtVouchers.setContentAreaFilled(false);
        jbtVouchers.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jbtVouchers.setFocusPainted(false);
        jbtVouchers.setFocusable(false);
        jbtVouchers.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/main/btn_vouchers_on.png"))); // NOI18N
        jbtVouchers.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/main/btn_vouchers_on.png"))); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.ipady = 5;
        jPanel1.add(jbtVouchers, gridBagConstraints);

        jScrollPane1.setViewportView(jPanel1);

        piBackground.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(48, 92, 280, 300));

        jbtCloseSession.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/main/btn_close_session_off.png"))); // NOI18N
        jbtCloseSession.setBorder(null);
        jbtCloseSession.setBorderPainted(false);
        jbtCloseSession.setContentAreaFilled(false);
        jbtCloseSession.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jbtCloseSession.setFocusPainted(false);
        jbtCloseSession.setFocusable(false);
        jbtCloseSession.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/main/btn_close_session_on.png"))); // NOI18N
        jbtCloseSession.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/main/btn_close_session_on.png"))); // NOI18N
        piBackground.add(jbtCloseSession, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 50, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(piBackground, javax.swing.GroupLayout.DEFAULT_SIZE, 652, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(piBackground, javax.swing.GroupLayout.DEFAULT_SIZE, 424, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JButton jbtCloseSession;
    public javax.swing.JButton jbtEmail;
    public javax.swing.JButton jbtVouchers;
    private org.edisoncor.gui.panel.PanelImage piBackground;
    // End of variables declaration//GEN-END:variables
}
