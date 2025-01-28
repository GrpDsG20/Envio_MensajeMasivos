package view.proposals;

public class PMainVIew extends javax.swing.JFrame {

    public PMainVIew() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jpiBackground = new org.edisoncor.gui.panel.PanelImage();
        jtfAccountBT = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtaData = new javax.swing.JTextArea();
        jbtCopy = new javax.swing.JButton();
        jbtSearch = new javax.swing.JButton();
        jbtConcat = new javax.swing.JButton();
        jbtClear = new javax.swing.JButton();
        jtfProposal = new javax.swing.JFormattedTextField();
        jcbEntity = new javax.swing.JComboBox<>();
        jbtManager = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setIconImage(new javax.swing.ImageIcon(getClass().getResource("/images/proposals/search-1.png")).getImage());
        setResizable(false);

        jpiBackground.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/proposals/bg_main.png"))); // NOI18N
        jpiBackground.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jtfAccountBT.setBorder(null);
        jpiBackground.add(jtfAccountBT, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 88, 200, -1));

        jScrollPane1.getViewport().setOpaque(false);
        jScrollPane1.setBorder(null);
        jScrollPane1.setOpaque(false);

        jtaData.setEditable(false);
        jtaData.setColumns(20);
        jtaData.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jtaData.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        jtaData.setFocusable(false);
        jtaData.setOpaque(false);
        jtaData.setRequestFocusEnabled(false);
        jScrollPane1.setViewportView(jtaData);

        jpiBackground.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 210, 620, 220));

        jbtCopy.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/proposals/copy_off.png"))); // NOI18N
        jbtCopy.setBorder(null);
        jbtCopy.setBorderPainted(false);
        jbtCopy.setContentAreaFilled(false);
        jbtCopy.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jbtCopy.setFocusPainted(false);
        jbtCopy.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/proposals/copy_on.png"))); // NOI18N
        jbtCopy.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/proposals/copy_on.png"))); // NOI18N
        jpiBackground.add(jbtCopy, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 440, -1, -1));

        jbtSearch.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/proposals/search_off.png"))); // NOI18N
        jbtSearch.setBorder(null);
        jbtSearch.setBorderPainted(false);
        jbtSearch.setContentAreaFilled(false);
        jbtSearch.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jbtSearch.setFocusPainted(false);
        jbtSearch.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/proposals/search_on.png"))); // NOI18N
        jbtSearch.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/proposals/search_on.png"))); // NOI18N
        jpiBackground.add(jbtSearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 80, -1, -1));

        jbtConcat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/proposals/add_proposal_off.png"))); // NOI18N
        jbtConcat.setBorder(null);
        jbtConcat.setBorderPainted(false);
        jbtConcat.setContentAreaFilled(false);
        jbtConcat.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jbtConcat.setFocusPainted(false);
        jbtConcat.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/proposals/add_proposal_on.png"))); // NOI18N
        jbtConcat.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/proposals/add_proposal_on.png"))); // NOI18N
        jpiBackground.add(jbtConcat, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 120, -1, -1));

        jbtClear.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/proposals/new_off.png"))); // NOI18N
        jbtClear.setBorder(null);
        jbtClear.setBorderPainted(false);
        jbtClear.setContentAreaFilled(false);
        jbtClear.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jbtClear.setFocusPainted(false);
        jbtClear.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/proposals/new_on.png"))); // NOI18N
        jbtClear.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/proposals/new_on.png"))); // NOI18N
        jpiBackground.add(jbtClear, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 440, -1, -1));

        jtfProposal.setBorder(null);
        jtfProposal.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,###.00"))));
        jpiBackground.add(jtfProposal, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 130, 200, -1));

        jcbEntity.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "---- SELECCIONAR ----", "JZG", "ECL" }));
        jcbEntity.setBorder(null);
        jcbEntity.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jcbEntity.setFocusable(false);
        jpiBackground.add(jcbEntity, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 167, 200, -1));

        jbtManager.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/proposals/open_manager_off.png"))); // NOI18N
        jbtManager.setBorder(null);
        jbtManager.setBorderPainted(false);
        jbtManager.setContentAreaFilled(false);
        jbtManager.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jbtManager.setFocusPainted(false);
        jbtManager.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/proposals/open_manager_on.png"))); // NOI18N
        jbtManager.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/proposals/open_manager_on.png"))); // NOI18N
        jpiBackground.add(jbtManager, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 10, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jpiBackground, javax.swing.GroupLayout.PREFERRED_SIZE, 663, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jpiBackground, javax.swing.GroupLayout.PREFERRED_SIZE, 475, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JButton jbtClear;
    public javax.swing.JButton jbtConcat;
    public javax.swing.JButton jbtCopy;
    public javax.swing.JButton jbtManager;
    public javax.swing.JButton jbtSearch;
    public javax.swing.JComboBox<String> jcbEntity;
    private org.edisoncor.gui.panel.PanelImage jpiBackground;
    public javax.swing.JTextArea jtaData;
    public javax.swing.JTextField jtfAccountBT;
    public javax.swing.JFormattedTextField jtfProposal;
    // End of variables declaration//GEN-END:variables
}
