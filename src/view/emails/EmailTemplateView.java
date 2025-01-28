package view.emails;

import javax.swing.UIManager;

public class EmailTemplateView extends javax.swing.JFrame {

    public EmailTemplateView() throws Exception{
        UIManager.setLookAndFeel(UIManager.getLookAndFeel());
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jpiBackground = new org.edisoncor.gui.panel.PanelImage();
        jtfSubject = new javax.swing.JTextField();
        jbtSaveTemplate = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtaTemplate = new javax.swing.JTextArea();
        jbtUpdateTemplate = new javax.swing.JButton();
        jbtCancelTemplate = new javax.swing.JButton();
        jbtDeleteTemplate = new javax.swing.JButton();
        jtfNameTemplate = new javax.swing.JTextField();
        jcbTemplates = new javax.swing.JComboBox<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        jepVisualizeTemplate = new javax.swing.JEditorPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setIconImage(new javax.swing.ImageIcon(getClass().getResource("/images/emails/enviar-correo.png")).getImage());
        setResizable(false);

        jpiBackground.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/emails/bg_template_email.png"))); // NOI18N
        jpiBackground.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jtfSubject.setFont(new java.awt.Font("Inter", 0, 12)); // NOI18N
        jtfSubject.setBorder(null);
        jpiBackground.add(jtfSubject, new org.netbeans.lib.awtextra.AbsoluteConstraints(201, 212, 320, -1));

        jbtSaveTemplate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/emails/btn_save_off.png"))); // NOI18N
        jbtSaveTemplate.setBorder(null);
        jbtSaveTemplate.setBorderPainted(false);
        jbtSaveTemplate.setContentAreaFilled(false);
        jbtSaveTemplate.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jbtSaveTemplate.setFocusPainted(false);
        jbtSaveTemplate.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/emails/btn_save_on.png"))); // NOI18N
        jbtSaveTemplate.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/emails/btn_save_on.png"))); // NOI18N
        jpiBackground.add(jbtSaveTemplate, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 110, -1, -1));

        jScrollPane1.setBorder(null);

        jtaTemplate.setColumns(20);
        jtaTemplate.setLineWrap(true);
        jScrollPane1.setViewportView(jtaTemplate);

        jpiBackground.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(31, 305, 406, 337));

        jbtUpdateTemplate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/emails/btn_update_off.png"))); // NOI18N
        jbtUpdateTemplate.setBorder(null);
        jbtUpdateTemplate.setBorderPainted(false);
        jbtUpdateTemplate.setContentAreaFilled(false);
        jbtUpdateTemplate.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jbtUpdateTemplate.setEnabled(false);
        jbtUpdateTemplate.setFocusPainted(false);
        jbtUpdateTemplate.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/emails/btn_update_on.png"))); // NOI18N
        jbtUpdateTemplate.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/emails/btn_update_on.png"))); // NOI18N
        jpiBackground.add(jbtUpdateTemplate, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 140, -1, -1));

        jbtCancelTemplate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/emails/btn_cancel_off.png"))); // NOI18N
        jbtCancelTemplate.setBorder(null);
        jbtCancelTemplate.setBorderPainted(false);
        jbtCancelTemplate.setContentAreaFilled(false);
        jbtCancelTemplate.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jbtCancelTemplate.setEnabled(false);
        jbtCancelTemplate.setFocusPainted(false);
        jbtCancelTemplate.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/emails/btn_cancelar_on.png"))); // NOI18N
        jbtCancelTemplate.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/emails/btn_cancelar_on.png"))); // NOI18N
        jpiBackground.add(jbtCancelTemplate, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 200, -1, -1));

        jbtDeleteTemplate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/emails/btn_delete_off.png"))); // NOI18N
        jbtDeleteTemplate.setBorder(null);
        jbtDeleteTemplate.setBorderPainted(false);
        jbtDeleteTemplate.setContentAreaFilled(false);
        jbtDeleteTemplate.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jbtDeleteTemplate.setEnabled(false);
        jbtDeleteTemplate.setFocusPainted(false);
        jbtDeleteTemplate.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/emails/btn_delete_on.png"))); // NOI18N
        jbtDeleteTemplate.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/emails/btn_delete_on.png"))); // NOI18N
        jpiBackground.add(jbtDeleteTemplate, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 170, -1, -1));

        jtfNameTemplate.setFont(new java.awt.Font("Inter", 0, 12)); // NOI18N
        jtfNameTemplate.setBorder(null);
        jpiBackground.add(jtfNameTemplate, new org.netbeans.lib.awtextra.AbsoluteConstraints(201, 177, 213, -1));

        jcbTemplates.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "---- Seleccionar ----" }));
        jcbTemplates.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jcbTemplates.setFocusable(false);
        jpiBackground.add(jcbTemplates, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 80, 230, -1));

        jScrollPane2.setBorder(null);

        jepVisualizeTemplate.setEditable(false);
        jepVisualizeTemplate.setContentType("text/html"); // NOI18N
        jScrollPane2.setViewportView(jepVisualizeTemplate);

        jpiBackground.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(457, 303, 406, 338));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jpiBackground, javax.swing.GroupLayout.DEFAULT_SIZE, 893, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jpiBackground, javax.swing.GroupLayout.PREFERRED_SIZE, 656, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    public javax.swing.JButton jbtCancelTemplate;
    public javax.swing.JButton jbtDeleteTemplate;
    public javax.swing.JButton jbtSaveTemplate;
    public javax.swing.JButton jbtUpdateTemplate;
    public javax.swing.JComboBox<String> jcbTemplates;
    public javax.swing.JEditorPane jepVisualizeTemplate;
    public org.edisoncor.gui.panel.PanelImage jpiBackground;
    public javax.swing.JTextArea jtaTemplate;
    public javax.swing.JTextField jtfNameTemplate;
    public javax.swing.JTextField jtfSubject;
    // End of variables declaration//GEN-END:variables
}
