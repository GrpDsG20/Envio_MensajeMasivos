package view.adm_datas;

import javax.swing.UIManager;

public class AdmDatasMainView extends javax.swing.JFrame {

    public AdmDatasMainView() throws Exception{
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelImage1 = new org.edisoncor.gui.panel.PanelImage();
        jbtGenerateAndSave = new javax.swing.JButton();
        jtfPath = new javax.swing.JTextField();
        jtfNameTable = new javax.swing.JTextField();
        jtfNameDatabase = new javax.swing.JTextField();
        jcbSelectDatabase = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        panelImage1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jbtGenerateAndSave.setText("Generar y Registrar");
        panelImage1.add(jbtGenerateAndSave, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 130, -1, -1));

        jtfPath.setBorder(javax.swing.BorderFactory.createTitledBorder("Path Excel"));
        panelImage1.add(jtfPath, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 30, 620, -1));

        jtfNameTable.setBorder(javax.swing.BorderFactory.createTitledBorder("Nombre de Tabla"));
        panelImage1.add(jtfNameTable, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 80, 260, -1));

        jtfNameDatabase.setBorder(javax.swing.BorderFactory.createTitledBorder("Nombre de Base de Datos"));
        panelImage1.add(jtfNameDatabase, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 80, 220, -1));

        jcbSelectDatabase.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "---- Seleccione ----" }));
        jcbSelectDatabase.setBorder(javax.swing.BorderFactory.createTitledBorder("Selecciones Base de Datos"));
        panelImage1.add(jcbSelectDatabase, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 80, 200, 40));

        jLabel1.setText("Ó");
        panelImage1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(565, 90, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelImage1, javax.swing.GroupLayout.DEFAULT_SIZE, 860, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelImage1, javax.swing.GroupLayout.DEFAULT_SIZE, 188, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    public javax.swing.JButton jbtGenerateAndSave;
    public javax.swing.JComboBox<String> jcbSelectDatabase;
    public javax.swing.JTextField jtfNameDatabase;
    public javax.swing.JTextField jtfNameTable;
    public javax.swing.JTextField jtfPath;
    private org.edisoncor.gui.panel.PanelImage panelImage1;
    // End of variables declaration//GEN-END:variables
}
