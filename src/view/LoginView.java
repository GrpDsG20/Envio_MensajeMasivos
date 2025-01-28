package view;

import javax.swing.UIManager;

public class LoginView extends javax.swing.JFrame {

    public LoginView() throws Exception{
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        piBackground = new org.edisoncor.gui.panel.PanelImage();
        jtfUsername = new javax.swing.JTextField();
        jpfPassword = new javax.swing.JPasswordField();
        jbtJoin = new javax.swing.JButton();
        jckShowPassword = new javax.swing.JCheckBox();
        jckSaveUser = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Ingreso al sistema JBP");
        setIconImage(new javax.swing.ImageIcon(getClass().getResource("/images/main_logo.png")).getImage());
        setResizable(false);

        piBackground.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/login/bg_main.png"))); // NOI18N
        piBackground.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jtfUsername.setBorder(null);
        piBackground.add(jtfUsername, new org.netbeans.lib.awtextra.AbsoluteConstraints(442, 170, 256, -1));

        jpfPassword.setBorder(null);
        piBackground.add(jpfPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(442, 225, 256, -1));

        jbtJoin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/login/btn_join_off.png"))); // NOI18N
        jbtJoin.setBorder(null);
        jbtJoin.setBorderPainted(false);
        jbtJoin.setContentAreaFilled(false);
        jbtJoin.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jbtJoin.setFocusPainted(false);
        jbtJoin.setFocusable(false);
        jbtJoin.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/login/btn_join_on.png"))); // NOI18N
        jbtJoin.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/login/btn_join_on.png"))); // NOI18N
        piBackground.add(jbtJoin, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 270, -1, -1));

        jckShowPassword.setBorder(null);
        jckShowPassword.setContentAreaFilled(false);
        jckShowPassword.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jckShowPassword.setFocusPainted(false);
        jckShowPassword.setFocusable(false);
        jckShowPassword.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/login/btn_hidden_password.png"))); // NOI18N
        jckShowPassword.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/login/btn_show_password.png"))); // NOI18N
        piBackground.add(jckShowPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 220, -1, -1));

        jckSaveUser.setText("¿Recordar Contraseña?");
        jckSaveUser.setContentAreaFilled(false);
        jckSaveUser.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jckSaveUser.setFocusPainted(false);
        piBackground.add(jckSaveUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 320, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(piBackground, javax.swing.GroupLayout.PREFERRED_SIZE, 755, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(piBackground, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton jbtJoin;
    public javax.swing.JCheckBox jckSaveUser;
    public javax.swing.JCheckBox jckShowPassword;
    public javax.swing.JPasswordField jpfPassword;
    public javax.swing.JTextField jtfUsername;
    private org.edisoncor.gui.panel.PanelImage piBackground;
    // End of variables declaration//GEN-END:variables
}
