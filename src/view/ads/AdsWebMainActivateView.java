package view.ads;

import javax.swing.UIManager;

public class AdsWebMainActivateView extends javax.swing.JFrame {

    public AdsWebMainActivateView() throws Exception{
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        piBackground = new org.edisoncor.gui.panel.PanelImage();
        jbtDisable = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jlCurrentAdsActive = new javax.swing.JList<>();
        jtfIdAds = new javax.swing.JTextField();
        jckEdit = new javax.swing.JCheckBox();
        jlStatusAds = new javax.swing.JLabel();
        jbtEnable = new javax.swing.JButton();
        jsNumOrder = new javax.swing.JSpinner();
        jbtDisable1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Administración de Anuncios Web Activados - Sección Principal");
        setIconImage(new javax.swing.ImageIcon(getClass().getResource("/images/main_logo.png")).getImage());
        setResizable(false);

        piBackground.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/ads/bg_ads_web_main_activate.png"))); // NOI18N
        piBackground.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jbtDisable.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/ads/btn_disable_main_off.png"))); // NOI18N
        jbtDisable.setBorder(null);
        jbtDisable.setBorderPainted(false);
        jbtDisable.setContentAreaFilled(false);
        jbtDisable.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jbtDisable.setDefaultCapable(false);
        jbtDisable.setFocusPainted(false);
        jbtDisable.setFocusable(false);
        jbtDisable.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/ads/btn_disable_main_on.png"))); // NOI18N
        jbtDisable.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/ads/btn_disable_main_on.png"))); // NOI18N
        piBackground.add(jbtDisable, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 180, -1, -1));

        jScrollPane1.setBorder(null);

        jlCurrentAdsActive.setBorder(null);
        jlCurrentAdsActive.setFont(new java.awt.Font("Inter", 1, 18)); // NOI18N
        jlCurrentAdsActive.setForeground(new java.awt.Color(102, 102, 102));
        jlCurrentAdsActive.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Anuncio 1 - <id>", "Anuncio 2 - <id>", "Anuncio 3 - <id>", "Anuncio 4 - <id>", "Anuncio 5 - <id>" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jlCurrentAdsActive.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jlCurrentAdsActive.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jScrollPane1.setViewportView(jlCurrentAdsActive);

        piBackground.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(21, 62, 163, 160));

        jtfIdAds.setEditable(false);
        jtfIdAds.setForeground(new java.awt.Color(102, 102, 102));
        jtfIdAds.setBorder(null);
        piBackground.add(jtfIdAds, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 75, 31, -1));

        jckEdit.setForeground(new java.awt.Color(102, 102, 102));
        jckEdit.setText("Editar");
        jckEdit.setBorder(null);
        jckEdit.setContentAreaFilled(false);
        jckEdit.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jckEdit.setFocusPainted(false);
        jckEdit.setFocusable(false);
        piBackground.add(jckEdit, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 135, -1, -1));

        jlStatusAds.setFont(new java.awt.Font("Inter", 1, 12)); // NOI18N
        jlStatusAds.setForeground(new java.awt.Color(255, 51, 51));
        jlStatusAds.setText("Desactivado");
        piBackground.add(jlStatusAds, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 104, -1, -1));

        jbtEnable.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/ads/btn_enable_main_off.png"))); // NOI18N
        jbtEnable.setBorder(null);
        jbtEnable.setBorderPainted(false);
        jbtEnable.setContentAreaFilled(false);
        jbtEnable.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jbtEnable.setDefaultCapable(false);
        jbtEnable.setFocusPainted(false);
        jbtEnable.setFocusable(false);
        jbtEnable.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/ads/btn_enable_main_on.png"))); // NOI18N
        jbtEnable.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/ads/btn_enable_main_on.png"))); // NOI18N
        piBackground.add(jbtEnable, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 180, -1, -1));

        jsNumOrder.setModel(new javax.swing.SpinnerNumberModel(0, 0, 5, 1));
        jsNumOrder.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        piBackground.add(jsNumOrder, new org.netbeans.lib.awtextra.AbsoluteConstraints(298, 133, 40, 20));

        jbtDisable1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/ads/btn_update_awma_off.png"))); // NOI18N
        jbtDisable1.setBorder(null);
        jbtDisable1.setBorderPainted(false);
        jbtDisable1.setContentAreaFilled(false);
        jbtDisable1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jbtDisable1.setDefaultCapable(false);
        jbtDisable1.setFocusPainted(false);
        jbtDisable1.setFocusable(false);
        jbtDisable1.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/ads/btn_update_awma_on.png"))); // NOI18N
        jbtDisable1.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/ads/btn_update_awma_on.png"))); // NOI18N
        piBackground.add(jbtDisable1, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 180, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(piBackground, javax.swing.GroupLayout.PREFERRED_SIZE, 556, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(piBackground, javax.swing.GroupLayout.DEFAULT_SIZE, 235, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JButton jbtDisable;
    public javax.swing.JButton jbtDisable1;
    public javax.swing.JButton jbtEnable;
    public javax.swing.JCheckBox jckEdit;
    public javax.swing.JList<String> jlCurrentAdsActive;
    public javax.swing.JLabel jlStatusAds;
    public javax.swing.JSpinner jsNumOrder;
    public javax.swing.JTextField jtfIdAds;
    private org.edisoncor.gui.panel.PanelImage piBackground;
    // End of variables declaration//GEN-END:variables
}
