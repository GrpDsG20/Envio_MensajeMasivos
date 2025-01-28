package view.proposals;

import javax.swing.UIManager;

public class PManagerView extends javax.swing.JFrame {

    public PManagerView() throws Exception{
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        piBackground = new org.edisoncor.gui.panel.PanelImage();
        jtfPathFile = new javax.swing.JTextField();
        jtfSearch = new javax.swing.JTextField();
        jbtExamine = new javax.swing.JButton();
        jbtSearch = new javax.swing.JButton();
        jbtDeleteAll = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtaConsole = new javax.swing.JTextArea();
        jbtNew = new javax.swing.JButton();
        jbtRegister = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setIconImage(new javax.swing.ImageIcon(getClass().getResource("/images/proposals/search-1.png")).getImage());
        setResizable(false);

        piBackground.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/proposals/bg_main_1.png"))); // NOI18N
        piBackground.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jtfPathFile.setEditable(false);
        jtfPathFile.setBorder(null);
        piBackground.add(jtfPathFile, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 132, 390, -1));

        jtfSearch.setBorder(null);
        piBackground.add(jtfSearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 192, 390, -1));

        jbtExamine.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/proposals/examine_off.png"))); // NOI18N
        jbtExamine.setBorder(null);
        jbtExamine.setBorderPainted(false);
        jbtExamine.setContentAreaFilled(false);
        jbtExamine.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jbtExamine.setDefaultCapable(false);
        jbtExamine.setFocusPainted(false);
        jbtExamine.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/proposals/examine_on.png"))); // NOI18N
        jbtExamine.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/proposals/examine_on.png"))); // NOI18N
        piBackground.add(jbtExamine, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 120, -1, -1));

        jbtSearch.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/proposals/search_off.png"))); // NOI18N
        jbtSearch.setBorder(null);
        jbtSearch.setBorderPainted(false);
        jbtSearch.setContentAreaFilled(false);
        jbtSearch.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jbtSearch.setDefaultCapable(false);
        jbtSearch.setFocusPainted(false);
        jbtSearch.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/proposals/search_on.png"))); // NOI18N
        jbtSearch.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/proposals/search_on.png"))); // NOI18N
        piBackground.add(jbtSearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 180, -1, -1));

        jbtDeleteAll.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/proposals/delete_all_data_off.png"))); // NOI18N
        jbtDeleteAll.setBorder(null);
        jbtDeleteAll.setBorderPainted(false);
        jbtDeleteAll.setContentAreaFilled(false);
        jbtDeleteAll.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jbtDeleteAll.setDefaultCapable(false);
        jbtDeleteAll.setFocusPainted(false);
        jbtDeleteAll.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/proposals/delete_all_data_on.png"))); // NOI18N
        jbtDeleteAll.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/proposals/delete_all_data_on.png"))); // NOI18N
        piBackground.add(jbtDeleteAll, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 10, -1, -1));

        jScrollPane1.getViewport().setOpaque(false);
        jScrollPane1.setBorder(null);
        jScrollPane1.setOpaque(false);

        jtaConsole.setEditable(false);
        jtaConsole.setColumns(20);
        jtaConsole.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        jtaConsole.setLineWrap(true);
        jtaConsole.setRows(5);
        jtaConsole.setBorder(null);
        jtaConsole.setOpaque(false);
        jScrollPane1.setViewportView(jtaConsole);

        piBackground.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(14, 230, 950, 260));

        jbtNew.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/proposals/new_off.png"))); // NOI18N
        jbtNew.setBorder(null);
        jbtNew.setBorderPainted(false);
        jbtNew.setContentAreaFilled(false);
        jbtNew.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jbtNew.setDefaultCapable(false);
        jbtNew.setFocusPainted(false);
        jbtNew.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/proposals/new_on.png"))); // NOI18N
        jbtNew.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/proposals/new_on.png"))); // NOI18N
        piBackground.add(jbtNew, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 120, -1, -1));

        jbtRegister.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/proposals/register_off.png"))); // NOI18N
        jbtRegister.setBorder(null);
        jbtRegister.setBorderPainted(false);
        jbtRegister.setContentAreaFilled(false);
        jbtRegister.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jbtRegister.setDefaultCapable(false);
        jbtRegister.setFocusPainted(false);
        jbtRegister.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/proposals/register_on.png"))); // NOI18N
        jbtRegister.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/proposals/register_on.png"))); // NOI18N
        piBackground.add(jbtRegister, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 120, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(piBackground, javax.swing.GroupLayout.PREFERRED_SIZE, 976, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(piBackground, javax.swing.GroupLayout.PREFERRED_SIZE, 510, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JButton jbtDeleteAll;
    public javax.swing.JButton jbtExamine;
    public javax.swing.JButton jbtNew;
    public javax.swing.JButton jbtRegister;
    public javax.swing.JButton jbtSearch;
    public javax.swing.JTextArea jtaConsole;
    public javax.swing.JTextField jtfPathFile;
    public javax.swing.JTextField jtfSearch;
    private org.edisoncor.gui.panel.PanelImage piBackground;
    // End of variables declaration//GEN-END:variables
}
