package view.emails;

import javax.swing.UIManager;

public class TestAccountView extends javax.swing.JFrame {

    public TestAccountView() throws Exception{
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jpiBackground = new org.edisoncor.gui.panel.PanelImage();
        jtfSearch = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtListTestAccounts = new javax.swing.JTable();
        jbtUpdateTestAccount = new javax.swing.JButton();
        jbtSaveTestAccount = new javax.swing.JButton();
        jbtCancelUpdate = new javax.swing.JButton();
        jtfEmail = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setIconImage(new javax.swing.ImageIcon(getClass().getResource("/images/emails/enviar-correo.png")).getImage());
        setResizable(false);

        jpiBackground.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/emails/bg_test_email.png"))); // NOI18N
        jpiBackground.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jtfSearch.setFont(new java.awt.Font("Inter", 0, 12)); // NOI18N
        jtfSearch.setBorder(null);
        jpiBackground.add(jtfSearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(118, 165, 265, -1));

        jScrollPane1.setBorder(null);

        jtListTestAccounts.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Correo", "Fecha de Registro", "Fecha de Actualización", "Modificar", "Eliminar"
            }
        ));
        jtListTestAccounts.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(jtListTestAccounts);

        jpiBackground.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(21, 218, 708, 191));

        jbtUpdateTestAccount.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/emails/btn_update_off.png"))); // NOI18N
        jbtUpdateTestAccount.setBorder(null);
        jbtUpdateTestAccount.setBorderPainted(false);
        jbtUpdateTestAccount.setContentAreaFilled(false);
        jbtUpdateTestAccount.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jbtUpdateTestAccount.setEnabled(false);
        jbtUpdateTestAccount.setFocusPainted(false);
        jbtUpdateTestAccount.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/emails/btn_update_on.png"))); // NOI18N
        jbtUpdateTestAccount.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/emails/btn_update_on.png"))); // NOI18N
        jpiBackground.add(jbtUpdateTestAccount, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 120, -1, -1));

        jbtSaveTestAccount.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/emails/btn_save_off.png"))); // NOI18N
        jbtSaveTestAccount.setBorder(null);
        jbtSaveTestAccount.setBorderPainted(false);
        jbtSaveTestAccount.setContentAreaFilled(false);
        jbtSaveTestAccount.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jbtSaveTestAccount.setFocusPainted(false);
        jbtSaveTestAccount.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/emails/btn_save_on.png"))); // NOI18N
        jbtSaveTestAccount.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/emails/btn_save_on.png"))); // NOI18N
        jpiBackground.add(jbtSaveTestAccount, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 120, -1, -1));

        jbtCancelUpdate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/emails/btn_cancel_off.png"))); // NOI18N
        jbtCancelUpdate.setBorder(null);
        jbtCancelUpdate.setBorderPainted(false);
        jbtCancelUpdate.setContentAreaFilled(false);
        jbtCancelUpdate.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jbtCancelUpdate.setEnabled(false);
        jbtCancelUpdate.setFocusPainted(false);
        jbtCancelUpdate.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/emails/btn_cancelar_on.png"))); // NOI18N
        jbtCancelUpdate.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/emails/btn_cancelar_on.png"))); // NOI18N
        jpiBackground.add(jbtCancelUpdate, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 120, -1, -1));

        jtfEmail.setFont(new java.awt.Font("Inter", 0, 12)); // NOI18N
        jtfEmail.setBorder(null);
        jpiBackground.add(jtfEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(118, 80, 265, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jpiBackground, javax.swing.GroupLayout.PREFERRED_SIZE, 757, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jpiBackground, javax.swing.GroupLayout.PREFERRED_SIZE, 433, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JButton jbtCancelUpdate;
    public javax.swing.JButton jbtSaveTestAccount;
    public javax.swing.JButton jbtUpdateTestAccount;
    private org.edisoncor.gui.panel.PanelImage jpiBackground;
    public javax.swing.JTable jtListTestAccounts;
    public javax.swing.JTextField jtfEmail;
    public javax.swing.JTextField jtfSearch;
    // End of variables declaration//GEN-END:variables
}
