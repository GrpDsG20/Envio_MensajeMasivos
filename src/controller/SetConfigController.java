package controller;

import addons.ExtraCode;
import file.FileClass;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.SwingWorker;
import model.mysql.DAOMySQL;
import model.mysql.MySQL;
import view.SetConfigView;

public class SetConfigController extends SetConfigView implements ActionListener {

    private static SetConfigController instance;

    public SetConfigController() throws Exception {
        super();
        this.registerEvents();
        this.registerObjects();
    }

    public static SetConfigController getInstance() {
        try {
            instance = new SetConfigController();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return instance;
    }

    private void registerEvents() {
        this.jbtCreateFile.addActionListener(this);
        this.jbtRegisterMySQL.addActionListener(this);
        this.jbtContinue.addActionListener(this);
    }

    private void registerObjects() {
        this.jbtContinue.setVisible(false);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (jbtCreateFile == e.getSource()) {
            this.btnCreateFile();
        } else if (jbtRegisterMySQL == e.getSource()) {
            this.btnRegisterMySQL();
        } else if (jbtContinue == e.getSource()) {
            this.btnContinue();
        }
    }

    private void btnContinue() {
        this.dispose();
        LoginController.getInstance();
    }

    private void btnCreateFile() {
        FileClass file = new FileClass();
        if (!file.isExistsConfigFile()) {
            if (file.createConfigFile() && file.createReportFile()) {
                ExtraCode.writeConsole(jtaConsole, "Archivo Config creado con éxito, en la ubicación:\n" + file.getFile().getAbsolutePath());
            } else {
                ExtraCode.writeConsole(jtaConsole, "Error: No se pudo crear el archivo config.");
            }
        } else {
            ExtraCode.writeConsole(jtaConsole, "Error: Archivo Config ya creado en la ubicación:\n" + file.getFile().getAbsolutePath());
        }
    }

    private void btnRegisterMySQL() {
        String ip = jtfIp.getText();
        String port = jtfPort.getText();
        String database = jtfDatabase.getText();
        String username = jtfUsername.getText();
        String password = new String(jpfPassword.getPassword());
        if (!ip.isEmpty()) {
            if (!port.isEmpty()) {
                if (!database.isEmpty()) {
                    if (!username.isEmpty()) {
                        MySQL model = new MySQL(ip, port, database, username, password, true);
                        FileClass file = new FileClass();
                        if (file.setParametersMySQL(model)) {
                            ExtraCode.writeConsole(jtaConsole, "[" + ExtraCode.getCurrentDateFormat("dd/MM/yyyy hh:mm:ss aa") + "] Base de datos registrado en el archivo config.\n");
                            SwingWorker<Void, String> sw = new SwingWorker<Void, String>() {

                                @Override
                                protected Void doInBackground() throws Exception {
                                    DAOMySQL dao = new DAOMySQL();
                                    publish("[" + ExtraCode.getCurrentDateFormat("dd/MM/yyyy hh:mm:ss aa") + "] Verificando si base de datos existe...");
                                    if (!dao.isExistsDatabase()) {
                                        publish("[" + ExtraCode.getCurrentDateFormat("dd/MM/yyyy hh:mm:ss aa") + "] Creando base de datos...");
                                        if (dao.createDatabase()) {
                                            publish("[" + ExtraCode.getCurrentDateFormat("dd/MM/yyyy hh:mm:ss aa") + "] Base de datos creada con éxito, creando tablas...");
                                            if (dao.createTables()) {
                                                publish("[" + ExtraCode.getCurrentDateFormat("dd/MM/yyyy hh:mm:ss aa") + "] Tablas creadas con éxito, creando usuario sistema...");
                                                if (dao.registerAdmin()) {
                                                    publish("[" + ExtraCode.getCurrentDateFormat("dd/MM/yyyy hh:mm:ss aa") + "] Usuario del sistema creado con éxito.\nProceso finalizado.");
                                                } else {
                                                    publish("[" + ExtraCode.getCurrentDateFormat("dd/MM/yyyy hh:mm:ss aa") + "] Error: No se pudo crear el usuario del sistema, registrelo manualmente o contacte con el desarrollador.");
                                                }
                                            } else {
                                                publish("[" + ExtraCode.getCurrentDateFormat("dd/MM/yyyy hh:mm:ss aa") + "] Error: No se pudo crear las tablas en la base de datos, verifica los datos registrados.");
                                            }
                                        } else {
                                            publish("[" + ExtraCode.getCurrentDateFormat("dd/MM/yyyy hh:mm:ss aa") + "] Error: No se pudo crear la base de datos, verifica los datos registrados.");
                                        }
                                    } else {
                                        publish("[" + ExtraCode.getCurrentDateFormat("dd/MM/yyyy hh:mm:ss aa") + "] Ya existe una base de datos con el nombre registrado en el archivo config.yml");
                                    }
                                    return null;
                                }

                                @Override
                                protected void process(List<String> chunks) {
                                    for (String message : chunks) {
                                        jtaConsole.append(message + "\n");
                                    }
                                }

                                @Override
                                protected void done() {
                                    jbtContinue.setVisible(true);
                                    repaint();
                                }
                            };
                            sw.execute();
                        } else {
                            ExtraCode.writeConsole(jtaConsole, "[" + ExtraCode.getCurrentDateFormat("dd/MM/yyyy hh:mm:ss aa") + "] Error: No se pudo registrar los parámetros en el archivo config.");
                        }
                    } else {
                        ExtraCode.sendMessageError("Error: Usuario no ingresado.");
                    }
                } else {
                    ExtraCode.sendMessageError("Error: Base de datos no ingresado.");
                }
            } else {
                ExtraCode.sendMessageError("Error: Puerto no ingresado.");
            }
        } else {
            ExtraCode.sendMessageError("Error: IP no ingresado.");
        }
    }
}
