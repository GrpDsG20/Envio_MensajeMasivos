package view.vouchers;

import javax.swing.JComboBox;
import javax.swing.UIManager;

public class VManagerView extends javax.swing.JFrame {

    public JComboBox<Integer> jcb;
    
    public VManagerView() throws Exception{
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        piBackground = new org.edisoncor.gui.panel.PanelImage();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtList = new javax.swing.JTable();
        jtfSearch = new javax.swing.JTextField();
        jbtDownload = new javax.swing.JButton();
        jpPagination = new javax.swing.JPanel();
        jbtDownloadImage = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setIconImage(new javax.swing.ImageIcon(getClass().getResource("/images/vouchers/gift-voucher.png")).getImage());
        setResizable(false);

        piBackground.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/vouchers/bg_managerview.png"))); // NOI18N
        piBackground.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jScrollPane1.setBorder(null);

        jtList.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "CÓDIGO", "CLIENTE", "FECHA REGISTRO", "IMAGEN", "ELIMINAR"
            }
        ));
        jtList.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(jtList);

        piBackground.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 106, 695, 200));

        jtfSearch.setBorder(null);
        piBackground.add(jtfSearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(208, 56, 188, -1));

        jbtDownload.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/vouchers/btn_download_excel_off.png"))); // NOI18N
        jbtDownload.setBorder(null);
        jbtDownload.setBorderPainted(false);
        jbtDownload.setContentAreaFilled(false);
        jbtDownload.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jbtDownload.setDefaultCapable(false);
        jbtDownload.setFocusPainted(false);
        jbtDownload.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/vouchers/btn_download_excel_on.png"))); // NOI18N
        jbtDownload.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/vouchers/btn_download_excel_on.png"))); // NOI18N
        piBackground.add(jbtDownload, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 20, -1, -1));
        piBackground.add(jpPagination, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 310, 695, 30));

        jbtDownloadImage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/vouchers/btn_download_img_off.png"))); // NOI18N
        jbtDownloadImage.setBorder(null);
        jbtDownloadImage.setBorderPainted(false);
        jbtDownloadImage.setContentAreaFilled(false);
        jbtDownloadImage.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jbtDownloadImage.setDefaultCapable(false);
        jbtDownloadImage.setFocusPainted(false);
        jbtDownloadImage.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/vouchers/btn_download_img_on.png"))); // NOI18N
        jbtDownloadImage.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/vouchers/btn_download_img_on.png"))); // NOI18N
        piBackground.add(jbtDownloadImage, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 60, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(piBackground, javax.swing.GroupLayout.DEFAULT_SIZE, 737, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(piBackground, javax.swing.GroupLayout.DEFAULT_SIZE, 371, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JButton jbtDownload;
    public javax.swing.JButton jbtDownloadImage;
    public javax.swing.JPanel jpPagination;
    public javax.swing.JTable jtList;
    public javax.swing.JTextField jtfSearch;
    private org.edisoncor.gui.panel.PanelImage piBackground;
    // End of variables declaration//GEN-END:variables
}
