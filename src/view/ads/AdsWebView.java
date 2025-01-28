package view.ads;

import javax.swing.UIManager;

public class AdsWebView extends javax.swing.JFrame {

    public AdsWebView() throws Exception{
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        piBackground = new org.edisoncor.gui.panel.PanelImage();
        jbtMain = new javax.swing.JButton();
        piBackgroundMain = new org.edisoncor.gui.panel.PanelImage();
        jbtUpdateMain = new javax.swing.JButton();
        jbtExamineMain = new javax.swing.JButton();
        jbtNewMain = new javax.swing.JButton();
        jbtRegisterMain = new javax.swing.JButton();
        jtfSearchMain = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtaDescripcionMain = new javax.swing.JTextArea();
        jtfTitleMain = new javax.swing.JTextField();
        jtfPathMain = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        jtCurrentAdsMain = new javax.swing.JTable();
        jcbTypeSearchMain = new javax.swing.JComboBox<>();
        jbtGrids = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Anuncios Web - Foro");
        setIconImage(new javax.swing.ImageIcon(getClass().getResource("/images/main_logo.png")).getImage());
        setResizable(false);

        piBackground.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/ads/bg_ads_web.png"))); // NOI18N
        piBackground.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jbtMain.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/ads/btn_main_off.png"))); // NOI18N
        jbtMain.setBorder(null);
        jbtMain.setBorderPainted(false);
        jbtMain.setContentAreaFilled(false);
        jbtMain.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jbtMain.setDefaultCapable(false);
        jbtMain.setFocusPainted(false);
        jbtMain.setFocusable(false);
        jbtMain.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/ads/btn_main_on.png"))); // NOI18N
        jbtMain.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/ads/btn_main_on.png"))); // NOI18N
        piBackground.add(jbtMain, new org.netbeans.lib.awtextra.AbsoluteConstraints(7, 140, -1, -1));

        piBackgroundMain.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/ads/bg_ads_web_main.png"))); // NOI18N
        piBackgroundMain.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jbtUpdateMain.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/ads/btn_update_off.png"))); // NOI18N
        jbtUpdateMain.setBorder(null);
        jbtUpdateMain.setBorderPainted(false);
        jbtUpdateMain.setContentAreaFilled(false);
        jbtUpdateMain.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jbtUpdateMain.setDefaultCapable(false);
        jbtUpdateMain.setEnabled(false);
        jbtUpdateMain.setFocusPainted(false);
        jbtUpdateMain.setFocusable(false);
        jbtUpdateMain.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/ads/btn_update_on.png"))); // NOI18N
        jbtUpdateMain.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/ads/btn_update_on.png"))); // NOI18N
        piBackgroundMain.add(jbtUpdateMain, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 160, -1, -1));

        jbtExamineMain.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/ads/btn_examine_off.png"))); // NOI18N
        jbtExamineMain.setBorder(null);
        jbtExamineMain.setBorderPainted(false);
        jbtExamineMain.setContentAreaFilled(false);
        jbtExamineMain.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jbtExamineMain.setDefaultCapable(false);
        jbtExamineMain.setFocusPainted(false);
        jbtExamineMain.setFocusable(false);
        jbtExamineMain.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/ads/btn_examine_on.png"))); // NOI18N
        jbtExamineMain.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/ads/btn_examine_on.png"))); // NOI18N
        piBackgroundMain.add(jbtExamineMain, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 120, -1, -1));

        jbtNewMain.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/ads/btn_new_off.png"))); // NOI18N
        jbtNewMain.setBorder(null);
        jbtNewMain.setBorderPainted(false);
        jbtNewMain.setContentAreaFilled(false);
        jbtNewMain.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jbtNewMain.setDefaultCapable(false);
        jbtNewMain.setFocusPainted(false);
        jbtNewMain.setFocusable(false);
        jbtNewMain.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/ads/btn_new_on.png"))); // NOI18N
        jbtNewMain.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/ads/btn_new_on.png"))); // NOI18N
        piBackgroundMain.add(jbtNewMain, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 160, -1, -1));

        jbtRegisterMain.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/ads/btn_register_off.png"))); // NOI18N
        jbtRegisterMain.setBorder(null);
        jbtRegisterMain.setBorderPainted(false);
        jbtRegisterMain.setContentAreaFilled(false);
        jbtRegisterMain.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jbtRegisterMain.setDefaultCapable(false);
        jbtRegisterMain.setFocusPainted(false);
        jbtRegisterMain.setFocusable(false);
        jbtRegisterMain.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/ads/btn_register_on.png"))); // NOI18N
        jbtRegisterMain.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/ads/btn_register_on.png"))); // NOI18N
        piBackgroundMain.add(jbtRegisterMain, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 160, -1, -1));

        jtfSearchMain.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jtfSearchMain.setForeground(new java.awt.Color(102, 102, 102));
        jtfSearchMain.setBorder(null);
        piBackgroundMain.add(jtfSearchMain, new org.netbeans.lib.awtextra.AbsoluteConstraints(66, 275, 240, -1));

        jScrollPane1.setBorder(null);

        jtaDescripcionMain.setColumns(20);
        jtaDescripcionMain.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jtaDescripcionMain.setForeground(new java.awt.Color(102, 102, 102));
        jtaDescripcionMain.setLineWrap(true);
        jtaDescripcionMain.setTabSize(0);
        jtaDescripcionMain.setBorder(null);
        jScrollPane1.setViewportView(jtaDescripcionMain);

        piBackgroundMain.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(104, 109, 237, 79));

        jtfTitleMain.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jtfTitleMain.setForeground(new java.awt.Color(102, 102, 102));
        jtfTitleMain.setBorder(null);
        piBackgroundMain.add(jtfTitleMain, new org.netbeans.lib.awtextra.AbsoluteConstraints(105, 71, 236, -1));

        jtfPathMain.setEditable(false);
        jtfPathMain.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jtfPathMain.setForeground(new java.awt.Color(102, 102, 102));
        jtfPathMain.setBorder(null);
        piBackgroundMain.add(jtfPathMain, new org.netbeans.lib.awtextra.AbsoluteConstraints(406, 90, 421, -1));

        jScrollPane2.setBorder(null);

        jtCurrentAdsMain.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Título", "Descripción", "¿Activar?", "Detalles", "Modificar", "Eliminar"
            }
        ));
        jtCurrentAdsMain.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(jtCurrentAdsMain);

        piBackgroundMain.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(17, 307, 823, 132));

        jcbTypeSearchMain.setForeground(new java.awt.Color(51, 51, 51));
        jcbTypeSearchMain.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "---- Seleccionar ----", "ID", "Título" }));
        jcbTypeSearchMain.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        jcbTypeSearchMain.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jcbTypeSearchMain.setFocusable(false);
        piBackgroundMain.add(jcbTypeSearchMain, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 270, 170, -1));

        piBackground.add(piBackgroundMain, new org.netbeans.lib.awtextra.AbsoluteConstraints(155, 58, 856, 453));

        jbtGrids.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/ads/btn_grids_off.png"))); // NOI18N
        jbtGrids.setBorder(null);
        jbtGrids.setBorderPainted(false);
        jbtGrids.setContentAreaFilled(false);
        jbtGrids.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jbtGrids.setDefaultCapable(false);
        jbtGrids.setFocusPainted(false);
        jbtGrids.setFocusable(false);
        jbtGrids.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/ads/btn_grids_on.png"))); // NOI18N
        jbtGrids.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/ads/btn_grids_on.png"))); // NOI18N
        piBackground.add(jbtGrids, new org.netbeans.lib.awtextra.AbsoluteConstraints(7, 180, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(piBackground, javax.swing.GroupLayout.PREFERRED_SIZE, 1016, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(piBackground, javax.swing.GroupLayout.PREFERRED_SIZE, 518, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    public javax.swing.JButton jbtExamineMain;
    public javax.swing.JButton jbtGrids;
    public javax.swing.JButton jbtMain;
    public javax.swing.JButton jbtNewMain;
    public javax.swing.JButton jbtRegisterMain;
    public javax.swing.JButton jbtUpdateMain;
    public javax.swing.JComboBox<String> jcbTypeSearchMain;
    public javax.swing.JTable jtCurrentAdsMain;
    public javax.swing.JTextArea jtaDescripcionMain;
    public javax.swing.JTextField jtfPathMain;
    public javax.swing.JTextField jtfSearchMain;
    public javax.swing.JTextField jtfTitleMain;
    private org.edisoncor.gui.panel.PanelImage piBackground;
    public org.edisoncor.gui.panel.PanelImage piBackgroundMain;
    // End of variables declaration//GEN-END:variables
}
