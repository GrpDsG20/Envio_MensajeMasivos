package view.ads;

import javax.swing.UIManager;

public class AdsMenuView extends javax.swing.JFrame {

    public AdsMenuView() throws Exception{
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        piBackground = new org.edisoncor.gui.panel.PanelImage();
        jbtDesktop = new javax.swing.JButton();
        jbtWeb = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Menú de Anuncios");
        setIconImage(new javax.swing.ImageIcon(getClass().getResource("/images/main_logo.png")).getImage());
        setResizable(false);

        piBackground.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/ads/bg_ads_menu.png"))); // NOI18N
        piBackground.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jbtDesktop.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/ads/btn_desktop_off.png"))); // NOI18N
        jbtDesktop.setBorder(null);
        jbtDesktop.setBorderPainted(false);
        jbtDesktop.setContentAreaFilled(false);
        jbtDesktop.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jbtDesktop.setFocusPainted(false);
        jbtDesktop.setFocusable(false);
        jbtDesktop.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/ads/btn_desktop_on.png"))); // NOI18N
        jbtDesktop.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/ads/btn_desktop_on.png"))); // NOI18N
        piBackground.add(jbtDesktop, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 110, -1, -1));

        jbtWeb.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/ads/btn_web_off.png"))); // NOI18N
        jbtWeb.setBorder(null);
        jbtWeb.setBorderPainted(false);
        jbtWeb.setContentAreaFilled(false);
        jbtWeb.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jbtWeb.setFocusPainted(false);
        jbtWeb.setFocusable(false);
        jbtWeb.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/ads/btn_web_on.png"))); // NOI18N
        jbtWeb.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/ads/btn_web_on.png"))); // NOI18N
        piBackground.add(jbtWeb, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 110, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(piBackground, javax.swing.GroupLayout.PREFERRED_SIZE, 652, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(piBackground, javax.swing.GroupLayout.PREFERRED_SIZE, 305, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton jbtDesktop;
    public javax.swing.JButton jbtWeb;
    private org.edisoncor.gui.panel.PanelImage piBackground;
    // End of variables declaration//GEN-END:variables
}
