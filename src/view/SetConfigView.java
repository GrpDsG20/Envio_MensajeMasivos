package view;

import javax.swing.UIManager;

public class SetConfigView extends javax.swing.JFrame {

    public SetConfigView() throws Exception{
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        piBackground = new org.edisoncor.gui.panel.PanelImage();
        jtfIp = new javax.swing.JTextField();
        jtfPort = new javax.swing.JTextField();
        jtfDatabase = new javax.swing.JTextField();
        jtfUsername = new javax.swing.JTextField();
        jpfPassword = new javax.swing.JPasswordField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtaConsole = new javax.swing.JTextArea();
        jbtRegisterMySQL = new javax.swing.JButton();
        jbtCreateFile = new javax.swing.JButton();
        jbtContinue = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Configuración MySQL");
        setIconImage(new javax.swing.ImageIcon(getClass().getResource("/images/main_logo.png")).getImage());
        setResizable(false);

        piBackground.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/setconfig/bg_setconfig_mysql.png"))); // NOI18N
        piBackground.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jtfIp.setBorder(null);
        piBackground.add(jtfIp, new org.netbeans.lib.awtextra.AbsoluteConstraints(154, 103, 172, -1));

        jtfPort.setBorder(null);
        piBackground.add(jtfPort, new org.netbeans.lib.awtextra.AbsoluteConstraints(154, 135, 172, -1));

        jtfDatabase.setBorder(null);
        piBackground.add(jtfDatabase, new org.netbeans.lib.awtextra.AbsoluteConstraints(154, 167, 172, -1));

        jtfUsername.setBorder(null);
        piBackground.add(jtfUsername, new org.netbeans.lib.awtextra.AbsoluteConstraints(154, 199, 172, -1));

        jpfPassword.setBorder(null);
        piBackground.add(jpfPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(154, 234, 170, -1));

        jScrollPane1.setBorder(null);

        jtaConsole.setEditable(false);
        jtaConsole.setColumns(20);
        jtaConsole.setLineWrap(true);
        jtaConsole.setRows(5);
        jtaConsole.setFocusable(false);
        jScrollPane1.setViewportView(jtaConsole);

        piBackground.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(355, 89, 384, 346));

        jbtRegisterMySQL.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/setconfig/btn_register_mysql_off.png"))); // NOI18N
        jbtRegisterMySQL.setBorder(null);
        jbtRegisterMySQL.setBorderPainted(false);
        jbtRegisterMySQL.setContentAreaFilled(false);
        jbtRegisterMySQL.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jbtRegisterMySQL.setFocusPainted(false);
        jbtRegisterMySQL.setFocusable(false);
        jbtRegisterMySQL.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/setconfig/btn_register_mysql_on.png"))); // NOI18N
        jbtRegisterMySQL.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/setconfig/btn_register_mysql_on.png"))); // NOI18N
        piBackground.add(jbtRegisterMySQL, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 320, -1, -1));

        jbtCreateFile.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/setconfig/btn_create_file_off.png"))); // NOI18N
        jbtCreateFile.setBorder(null);
        jbtCreateFile.setBorderPainted(false);
        jbtCreateFile.setContentAreaFilled(false);
        jbtCreateFile.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jbtCreateFile.setFocusPainted(false);
        jbtCreateFile.setFocusable(false);
        jbtCreateFile.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/setconfig/btn_create_file_on.png"))); // NOI18N
        jbtCreateFile.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/setconfig/btn_create_file_on.png"))); // NOI18N
        piBackground.add(jbtCreateFile, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 280, -1, -1));

        jbtContinue.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/setconfig/btn_continue_off.png"))); // NOI18N
        jbtContinue.setBorder(null);
        jbtContinue.setBorderPainted(false);
        jbtContinue.setContentAreaFilled(false);
        jbtContinue.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jbtContinue.setFocusPainted(false);
        jbtContinue.setFocusable(false);
        jbtContinue.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/setconfig/btn_continue_on.png"))); // NOI18N
        jbtContinue.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/setconfig/btn_continue_on.png"))); // NOI18N
        piBackground.add(jbtContinue, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 360, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(piBackground, javax.swing.GroupLayout.PREFERRED_SIZE, 772, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(piBackground, javax.swing.GroupLayout.PREFERRED_SIZE, 468, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JButton jbtContinue;
    public javax.swing.JButton jbtCreateFile;
    public javax.swing.JButton jbtRegisterMySQL;
    public javax.swing.JPasswordField jpfPassword;
    public javax.swing.JTextArea jtaConsole;
    public javax.swing.JTextField jtfDatabase;
    public javax.swing.JTextField jtfIp;
    public javax.swing.JTextField jtfPort;
    public javax.swing.JTextField jtfUsername;
    private org.edisoncor.gui.panel.PanelImage piBackground;
    // End of variables declaration//GEN-END:variables
}
