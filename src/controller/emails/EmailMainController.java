package controller.emails;

import addons.ExtraCode;
import controller.MainController;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import javax.swing.JFileChooser;
import model.access.AccessDetails;
import model.emails.EmailDataCustomersModel;
import model.emails.EmailModel;
import model.emails.EmailSendConfigModel;
import model.emails.EmailTemplateModel;
import model.emails.DAOEmailTemplate;
import model.emails.DAOTestAccount;
import view.emails.EmailMainView;

public class EmailMainController extends EmailMainView implements ActionListener {

    private static EmailMainController instance;
    private DAOEmailTemplate daoTemplate;
    private DAOTestAccount daoTestAcccount;
    private AccessDetails access;

    private EmailSendConfigModel configSend;
    private EmailExecuteAutomaticController eeac;
    private EmailModel emailModel;

    public EmailMainController(AccessDetails access) throws Exception {
        super();
        this.registerEvents();
        this.registerObjects(access);
    }

    public static EmailMainController getInstance(AccessDetails access) {
        try {
            instance = new EmailMainController(access);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return instance;
    }

    private void registerEvents() {
        this.jbtTemplates.addActionListener(this);
        this.jbtTestAccounts.addActionListener(this);
        this.jbtUploadDataExcel.addActionListener(this);
        this.jcbShowPassword.addActionListener(this);
        this.jbtBrowseFolderFiles.addActionListener(this);
        this.jbtStart.addActionListener(this);
        this.jbtPause.addActionListener(this);
        this.jbtFinish.addActionListener(this);
        this.jbtPause.addActionListener(this);
        this.jbtContinue.addActionListener(this);
        this.jrbTest.addActionListener(this);
        this.jrbCustomers.addActionListener(this);
        this.jrbDatabase.addActionListener(this);
        this.jrbPDF.addActionListener(this);
        this.jrbExcel.addActionListener(this);

        this.jtfSetTimeSeconds.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                ExtraCode.consumeLetter(e);
            }
        });

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                MainController.getInstance(access);
            }
        });
    }

    private void registerObjects(AccessDetails access) {
        this.access = access;
        this.jpiBackground.requestFocus();
        this.daoTemplate = new DAOEmailTemplate();
        this.daoTestAcccount = new DAOTestAccount();
        this.configSend = new EmailSendConfigModel();
        this.emailModel = new EmailModel();
        this.eeac = new EmailExecuteAutomaticController(this);
        this.loadTemplates(daoTemplate.select());
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (jbtTemplates == e.getSource()) {
            this.btnJoinTemplates();
        } else if (jbtTestAccounts == e.getSource()) {
            this.btnJoinTestAccounts();
        } else if (jbtBrowseFolderFiles == e.getSource()) {
            this.btnBrowseFolderFiles();
        } else if (jbtUploadDataExcel == e.getSource()) {
            this.btnUploadDataExcel();
        } else if (jbtStart == e.getSource()) {
            this.btnStart();
        } else if (jcbShowPassword == e.getSource()) {
            this.cbShowPassword();
        } else if (jbtPause == e.getSource()) {
            this.btnPause();
        } else if (jbtContinue == e.getSource()) {
            this.btnContinue();
        } else if (jbtFinish == e.getSource()) {
            this.btnFinish();
        } else if (jrbDatabase == e.getSource()) {
            this.rbModalitySendGet();
        } else if (jrbPDF == e.getSource()) {
            this.rbModalitySendGet();
        } else if (jrbExcel == e.getSource()) {
            this.rbModalitySendGet();
        }
    }

    /*Buttons*/
    private void rbModalitySendGet() {
        if (jrbDatabase.isSelected()) {
            this.jbtUploadDataExcel.setEnabled(false);
        } else {
            if (jrbExcel.isSelected()) {
                this.jbtBrowseFolderFiles.setEnabled(false);
                this.jbtUploadDataExcel.setEnabled(true);
            } else {
                this.jbtBrowseFolderFiles.setEnabled(true);
                this.jbtUploadDataExcel.setEnabled(true);
            }
        }
    }

    private void btnFinish() {
        this.eeac.cancel();
        this.jcbTemplates.setSelectedIndex(0);
        this.jtfDirectoryPDFs.setText(null);
        this.jtfCurrentPathFile.setText(null);
        this.setStatusButtonsStart(true, false, false, false);
        this.statusButtons(true);
        this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    }

    private void btnContinue() {
        int seconds = (jtfSetTimeSeconds.getText().isEmpty() || jtfSetTimeSeconds.getText() == null) ? 0 : Integer.parseInt(jtfSetTimeSeconds.getText());
        if (seconds != 0) {
            this.setStatusButtonsStart(false, false, true, true);
            this.configSend.setTimeSeconds(seconds);
            this.eeac.resumeThread();
        } else {
            ExtraCode.sendMessageError("Error: Debe establecer un tiempo de envio por segundo, vuelva a intentarlo.");
        }
    }

    private void btnPause() {
        this.setStatusButtonsStart(false, true, false, true);
        this.eeac.pauseThread();
        this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    }

    private void btnJoinTemplates() {
        EmailTemplateController.getInstance(access);
        this.dispose();
    }

    private void btnJoinTestAccounts() {
        TestAccountController.getInstance(access);
        this.dispose();
    }

    private void btnBrowseFolderFiles() {
        JFileChooser jfc = new JFileChooser();
        jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        jfc.setAcceptAllFileFilterUsed(false);
        if (jfc.showDialog(this, "Seleccione carpeta de archivos PDF") == JFileChooser.APPROVE_OPTION) {
            this.setCursor(new Cursor(Cursor.WAIT_CURSOR));
            String path = jfc.getSelectedFile().getAbsolutePath();
            String status = ExtraCode.isAllFilesPDF(path);
            if (status == null) {
                this.jtfDirectoryPDFs.setText(path);
                this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                this.repaint();
            } else {
                ExtraCode.sendMessageError("Error: Se encontró un archivo con diferente extensión a lo indicado.\nEl archivo se llama: " + status);
            }
        }
    }

    private void btnUploadDataExcel() {
        JFileChooser jfc = new JFileChooser();
        if (jfc.showDialog(this, "Seleccione archivo (.xlsx)") == JFileChooser.APPROVE_OPTION) {
            String path = jfc.getSelectedFile().getAbsolutePath();
            if (path.endsWith("xlsx")) {
                this.jtfPathFileExcel.setText(path);
                this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                this.repaint();
                /**/
            } else {
                ExtraCode.sendMessageError("Error: Archivo no admitido, vuelva a intentarlo.");
            }
        }
    }

    private void btnStart() {
        this.eeac = new EmailExecuteAutomaticController(this);
        String email = jtfEmail.getText();
        String password = new String(jpPasswordEmail.getPassword());
        int seconds = (jtfSetTimeSeconds.getText().isEmpty() || jtfSetTimeSeconds.getText() == null) ? 0 : Integer.parseInt(jtfSetTimeSeconds.getText());
        int shippingMethod = (jrbTest.isSelected()) ? 0 : 1;
        int nameMethod = (jrbPDF.isSelected()) ? 0 : 1;
        String directory = jtfDirectoryPDFs.getText();
        String file = jtfPathFileExcel.getText();
        String nameTemplate = (jcbTemplates.getSelectedIndex() == 0) ? null : jcbTemplates.getSelectedItem().toString();

        if (jrbDatabase.isSelected()) {
            if (!email.isEmpty()) {
                if (!password.isEmpty()) {
                    if (seconds != 0) {
                        if (nameTemplate != null) {
                            if (ExtraCode.sendMessageConfirm("Se empezará a subir la data a la memoria caché, esto puede tardar unos minutos...\n¿Seguro que quiere continuar?") == 0) {
                                nameMethod = 2;
                                this.configSend = new EmailSendConfigModel(email, password, seconds, shippingMethod, nameMethod, directory, file, nameTemplate);
                                this.configSend.load(jrbPDF.isSelected(), "Database");
                                this.setArrayListCustomer();
                                this.statusButtons(false);
                                ExtraCode.writeConsole(jtaConsole, "[" + ExtraCode.getCurrentDateFormat("dd/MM/yyyy hh:mm:ss aa") + "] Data almacenada en la caché con éxito.");
                                this.eeac.start();
                                this.setStatusButtonsStart(false, false, true, true);
                            } else {
                                this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                            }
                        } else {
                            ExtraCode.sendMessageError("Error: Debe seleccionar un tipo de plantilla, vuelva a intentarlo.");
                        }
                    } else {
                        ExtraCode.sendMessageError("Error: Debe establecer un tiempo de envio por minuto, vuelva a intentarlo.");
                    }
                } else {
                    ExtraCode.sendMessageError("Error: Contraseña de la cuenta quién envia no ingresado, vuelva a intentarlo1.");
                }
            } else {
                ExtraCode.sendMessageError("Error: Correo del quién envia no ingresado, vuelva a intentarlo.");
            }
        } else {
            if (!email.isEmpty()) {
                if (!password.isEmpty()) {
                    if (seconds != 0) {
                        if (!file.isEmpty()) {
                            if (nameTemplate != null) {
                                if (jrbPDF.isSelected() == true) {
                                    if (!directory.isEmpty()) {
                                        if (ExtraCode.sendMessageConfirm("Se empezará a subir la data a la memoria caché, esto puede tardar unos minutos...\n¿Seguro que quiere continuar?") == 0) {
                                            this.configSend = new EmailSendConfigModel(email, password, seconds, shippingMethod, nameMethod, directory, file, nameTemplate);
                                            this.configSend.load(jrbPDF.isSelected(), "Excel");
                                            this.statusButtons(false);
                                            ExtraCode.writeConsole(jtaConsole, "[" + ExtraCode.getCurrentDateFormat("dd/MM/yyyy hh:mm:ss aa") + "] Data almacenada en la caché con éxito.");
                                            this.eeac.start();
                                            this.setStatusButtonsStart(false, false, true, true);
                                        } else {
                                            this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                                        }
                                    } else {
                                        ExtraCode.sendMessageError("Error: Directorio de PDF no seleccionado, vuelva a intentarlo.");
                                    }
                                } else {
                                    if (ExtraCode.sendMessageConfirm("Se empezará a subir la data a la memoria caché, esto puede tardar unos minutos...\n¿Seguro que quiere continuar?") == 0) {
                                        this.configSend = new EmailSendConfigModel(email, password, seconds, shippingMethod, nameMethod, directory, file, nameTemplate);
                                        this.configSend.load(jrbPDF.isSelected(), "Excel");
                                        this.statusButtons(false);
                                        this.setArrayListCustomer();
                                        ExtraCode.writeConsole(jtaConsole, "[" + ExtraCode.getCurrentDateFormat("dd/MM/yyyy hh:mm:ss aa") + "] Data almacenada en la caché con éxito.");
                                        this.eeac.start();
                                        this.setStatusButtonsStart(false, false, true, true);
                                    } else {
                                        this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                                    }
                                }
                            } else {
                                ExtraCode.sendMessageError("Error: Debe seleccionar un tipo de plantilla, vuelva a intentarlo.");
                            }
                        } else {
                            ExtraCode.sendMessageError("Error: Archivo de la data no seleccionado, vuelva a intentarlo.");
                        }
                    } else {
                        ExtraCode.sendMessageError("Error: Debe establecer un tiempo de envio por minuto, vuelva a intentarlo.");
                    }
                } else {
                    ExtraCode.sendMessageError("Error: Contraseña de la cuenta quién envia no ingresado, vuelva a intentarlo1.");
                }
            } else {
                ExtraCode.sendMessageError("Error: Correo del quién envia no ingresado, vuelva a intentarlo.");
            }
        }
    }

    private void cbShowPassword() {
        if (jcbShowPassword.isSelected()) {
            this.jpPasswordEmail.setEchoChar((char) 0);
        } else {
            this.jpPasswordEmail.setEchoChar('•');
        }
    }

    /*Methods*/
    private ArrayList<String> listCustomers;

    private void setArrayListCustomer() {
        this.listCustomers = configSend.getCustomerWithoutDuplicate();
    }

    private void loadTemplates(ArrayList<EmailTemplateModel> list) {
        ArrayList<String> templates = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            templates.add(list.get(i).getName());
        }
        ExtraCode.loadDataComboBox(jcbTemplates, templates);
    }

    private void statusButtons(boolean status) {
        this.jtfEmail.setEditable(status);
        this.jpPasswordEmail.setEditable(status);
        this.jrbTest.setEnabled(status);
        this.jrbCustomers.setEnabled(status);
        this.jrbPDF.setEnabled(status);
        this.jrbExcel.setEnabled(status);
        this.jbtBrowseFolderFiles.setEnabled(status);
        this.jbtUploadDataExcel.setEnabled(status);
        this.jcbTemplates.setEnabled(status);
    }

    private void setStatusButtonsStart(boolean start, boolean continu, boolean pause, boolean finish) {
        this.jbtStart.setEnabled(start);
        this.jbtContinue.setEnabled(continu);
        this.jbtPause.setEnabled(pause);
        this.jbtFinish.setEnabled(finish);
    }

    public void sendMailAutomatic() {
        this.setCursor(new Cursor(Cursor.WAIT_CURSOR));
        try {
            switch (configSend.getNameMethod()) {
                case 0 -> {
                    switch (configSend.getShippingMethod()) {
                        case 0 -> {
                            if (!configSend.getCustomersFromPDF().isEmpty()) {
                                this.emailModel.setUser(configSend.getEmail());
                                this.emailModel.setPassword(configSend.getPassword());
                                this.emailModel.setDestiny(daoTestAcccount.getAllAccountsInLine());
                                String customer = configSend.getCustomersFromPDF().get(0).replace(".pdf", "");
                                if (configSend.isCustomerInFile(customer)) {
                                    for (int i = 0; i < configSend.getCustomersFrom().size(); i++) {
                                        EmailDataCustomersModel aux = configSend.getCustomersFrom().get(i);
                                        if (aux.getCustomer().equals(customer)) {
                                            this.emailModel.setSubject(configSend.getTemplate().getSuject()
                                                    .replaceAll("%customer_fullname%", aux.getCustomer())
                                                    .replaceAll("%entity%", aux.getBank())
                                                    .replaceAll("%proxy_fullname%", aux.getProxy())
                                                    .replaceAll("%date_debt%", aux.getDateDebt()));
                                            this.emailModel.setMessage(configSend.getTemplate().getTemplate().replaceAll("%name_financial%", aux.getBank())
                                                    .replaceAll("%customer_fullname%", aux.getCustomer())
                                                    .replaceAll("%num_account%", aux.getCode())
                                                    .replaceAll("%balance_total%", aux.getAmount_total())
                                                    .replaceAll("%amount_campaign%", aux.getAmount_campaign())
                                                    .replaceAll("%entity%", aux.getBank())
                                                    .replaceAll("%proxy_fullname%", aux.getProxy())
                                                    .replaceAll("%date_debt%", aux.getDateDebt()));
                                            this.emailModel.setFile(configSend.getDirectoryPDF() + "\\" + aux.getCustomer() + ".pdf");
                                            this.jtfCurrentPathFile.setText(aux.getCustomer());
                                            if (sendMail(aux, emailModel)) {
                                                this.configSend.getCustomersFromPDF().remove(0);
                                                Thread.sleep(configSend.getTimeSeconds());
                                                break;
                                            } else {
                                                this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                                                break;
                                            }
                                        }
                                    }
                                } else {
                                    this.configSend.getCustomersFromPDF().remove(0);
                                }
                            } else {
                                ExtraCode.sendMessageSuccessfully("Todos los archivos fueron enviados correctamente.\nAdvertencia: Si aún verifica archivos en el directorio indicado, \nes posible que no esté el nombre del archivo en la data indicada.");
                                this.setStatusButtonsStart(true, false, false, false);
                                this.statusButtons(true);
                                this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                                this.eeac.cancel();
                            }
                            break;
                        }
                        case 1 -> {
                            if (!configSend.getCustomersFromPDF().isEmpty()) {
                                this.emailModel.setUser(configSend.getEmail());
                                this.emailModel.setPassword(configSend.getPassword());
                                String customer = configSend.getCustomersFromPDF().get(0).replace(".pdf", "");
                                this.emailModel.setDestiny(configSend.getCustomerEmailsInLine(customer, " ;"));
                                if (configSend.isCustomerInFile(customer)) {
                                    for (int i = 0; i < configSend.getCustomersFrom().size(); i++) {
                                        EmailDataCustomersModel aux = configSend.getCustomersFrom().get(i);
                                        if (aux.getCustomer().equals(customer)) {
                                            this.emailModel.setSubject(configSend.getTemplate().getSuject()
                                                    .replaceAll("%customer_fullname%", aux.getCustomer())
                                                    .replaceAll("%entity%", aux.getBank())
                                                    .replaceAll("%proxy_fullname%", aux.getProxy())
                                                    .replaceAll("%date_debt%", aux.getDateDebt()));
                                            this.emailModel.setMessage(configSend.getTemplate().getTemplate().replaceAll("%name_financial%", aux.getBank())
                                                    .replaceAll("%customer_fullname%", aux.getCustomer())
                                                    .replaceAll("%num_account%", aux.getCode())
                                                    .replaceAll("%balance_total%", aux.getAmount_total())
                                                    .replaceAll("%amount_campaign%", aux.getAmount_campaign())
                                                    .replaceAll("%entity%", aux.getBank())
                                                    .replaceAll("%proxy_fullname%", aux.getProxy())
                                                    .replaceAll("%date_debt%", aux.getDateDebt()));
                                            this.emailModel.setFile(configSend.getDirectoryPDF() + "\\" + aux.getCustomer() + ".pdf");
                                            this.jtfCurrentPathFile.setText(aux.getCustomer());
                                            if (sendMail(aux, emailModel)) {
                                                this.configSend.getCustomersFromPDF().remove(0);
                                                Thread.sleep(configSend.getTimeSeconds());
                                                break;
                                            } else {
                                                this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                                                break;
                                            }
                                        }
                                    }
                                } else {
                                    this.configSend.getCustomersFromPDF().remove(0);
                                }
                            } else {
                                ExtraCode.sendMessageSuccessfully("Todos los archivos fueron enviados correctamente.\nAdvertencia: Si aún verifica archivos en el directorio indicado, \nes posible que no esté el nombre del archivo en la data indicada.");
                                this.setStatusButtonsStart(true, false, false, false);
                                this.statusButtons(true);
                                this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                                this.eeac.cancel();
                            }
                            break;
                        }
                    }
                    break;
                }
                case 1 -> {
                    switch (configSend.getShippingMethod()) {
                        case 0 -> {
                            if (!listCustomers.isEmpty()) {
                                this.emailModel.setUser(configSend.getEmail());
                                this.emailModel.setPassword(configSend.getPassword());
                                for (int h = 0; h < listCustomers.size(); h++) {
                                    String customer = listCustomers.get(h);
                                    this.emailModel.setDestiny(daoTestAcccount.getAllAccountsInLine());
                                    ArrayList<EmailDataCustomersModel> listCustomerFromFile = configSend.getCustomersFrom();
                                    for (int i = 0; i < listCustomerFromFile.size(); i++) {
                                        EmailDataCustomersModel aux = listCustomerFromFile.get(i);
                                        System.out.println(emailModel.getDestiny() + " - " + customer + " - " + aux.getAmount_total() + " - " + aux.getProxy());
                                        if (aux.getCustomer().equals(customer)) {
                                            this.emailModel.setSubject(configSend.getTemplate().getSuject()
                                                    .replaceAll("%customer_fullname%", aux.getCustomer())
                                                    .replaceAll("%entity%", aux.getBank())
                                                    .replaceAll("%proxy_fullname%", aux.getProxy())
                                                    .replaceAll("%date_debt%", aux.getDateDebt()));
                                            this.emailModel.setMessage(configSend.getTemplate().getTemplate().replaceAll("%name_financial%", aux.getBank())
                                                    .replaceAll("%customer_fullname%", aux.getCustomer())
                                                    .replaceAll("%num_account%", aux.getCode())
                                                    .replaceAll("%balance_total%", aux.getAmount_total())
                                                    .replaceAll("%amount_campaign%", aux.getAmount_campaign())
                                                    .replaceAll("%entity%", aux.getBank())
                                                    .replaceAll("%proxy_fullname%", aux.getProxy())
                                                    .replaceAll("%date_debt%", aux.getDateDebt()));
                                            this.jtfCurrentPathFile.setText(aux.getCustomer());
                                            if (sendMail(aux, emailModel)) {
                                                listCustomers.remove(customer);
                                                Thread.sleep(configSend.getTimeSeconds());
                                                break;
                                            } else {
                                                this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                                                break;
                                            }
                                        }
                                    }
                                }
                                break;
                            } else {
                                ExtraCode.sendMessageSuccessfully("Todos los correos fueron enviados correctamente.");
                                this.setStatusButtonsStart(true, false, false, false);
                                this.statusButtons(true);
                                this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                                this.eeac.cancel();
                            }
                        }
                        case 1 -> {
                            if (!listCustomers.isEmpty()) {
                                this.emailModel.setUser(configSend.getEmail());
                                this.emailModel.setPassword(configSend.getPassword());
                                for (int h = 0; h < listCustomers.size(); h++) {
                                    String customer = listCustomers.get(h);
                                    this.emailModel.setDestiny(configSend.getCustomerEmailsInLine(customer, " ;"));
                                    ArrayList<EmailDataCustomersModel> listCustomerFromFile = configSend.getCustomersFrom();
                                    for (int i = 0; i < listCustomerFromFile.size(); i++) {
                                        EmailDataCustomersModel aux = listCustomerFromFile.get(i);
                                        System.out.println(emailModel.getDestiny() + " - " + customer + " - " + aux.getAmount_total() + " - " + aux.getProxy());
                                        if (aux.getCustomer().equals(customer)) {
                                            this.emailModel.setSubject(configSend.getTemplate().getSuject()
                                                    .replaceAll("%customer_fullname%", aux.getCustomer())
                                                    .replaceAll("%entity%", aux.getBank())
                                                    .replaceAll("%proxy_fullname%", aux.getProxy())
                                                    .replaceAll("%date_debt%", aux.getDateDebt()));
                                            this.emailModel.setMessage(configSend.getTemplate().getTemplate().replaceAll("%name_financial%", aux.getBank())
                                                    .replaceAll("%customer_fullname%", aux.getCustomer())
                                                    .replaceAll("%num_account%", aux.getCode())
                                                    .replaceAll("%balance_total%", aux.getAmount_total())
                                                    .replaceAll("%amount_campaign%", aux.getAmount_campaign())
                                                    .replaceAll("%entity%", aux.getBank())
                                                    .replaceAll("%proxy_fullname%", aux.getProxy())
                                                    .replaceAll("%date_debt%", aux.getDateDebt()));
                                            this.jtfCurrentPathFile.setText(aux.getCustomer());
                                            if (sendMail(aux, emailModel)) {
                                                listCustomers.remove(customer);
                                                Thread.sleep(configSend.getTimeSeconds());
                                                break;
                                            } else {
                                                this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                                                break;
                                            }
                                        }
                                    }
                                }
                                break;
                            } else {
                                ExtraCode.sendMessageSuccessfully("Todos los correos fueron enviados correctamente.");
                                this.setStatusButtonsStart(true, false, false, false);
                                this.statusButtons(true);
                                this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                                this.eeac.cancel();
                            }
                        }
                    }
                    break;
                }
                case 2 -> {
                    switch (configSend.getShippingMethod()) {
                        case 0 -> {
                            if (!listCustomers.isEmpty()) {
                                this.emailModel.setUser(configSend.getEmail());
                                this.emailModel.setPassword(configSend.getPassword());
                                for (int h = 0; h < listCustomers.size(); h++) {
                                    String customer = listCustomers.get(h);
                                    this.emailModel.setDestiny(daoTestAcccount.getAllAccountsInLine());
                                    ArrayList<EmailDataCustomersModel> listCustomerFromFile = configSend.getCustomersFrom();
                                    for (int i = 0; i < listCustomerFromFile.size(); i++) {
                                        EmailDataCustomersModel aux = listCustomerFromFile.get(i);
                                        System.out.println(emailModel.getDestiny() + " - " + customer + " - " + aux.getAmount_total() + " - " + aux.getProxy());
                                        if (aux.getCustomer().equals(customer)) {
                                            this.emailModel.setSubject(configSend.getTemplate().getSuject()
                                                    .replaceAll("%customer_fullname%", aux.getCustomer())
                                                    .replaceAll("%entity%", aux.getBank())
                                                    .replaceAll("%proxy_fullname%", aux.getProxy())
                                                    .replaceAll("%date_debt%", aux.getDateDebt()));
                                            this.emailModel.setMessage(configSend.getTemplate().getTemplate().replaceAll("%name_financial%", aux.getBank())
                                                    .replaceAll("%customer_fullname%", aux.getCustomer())
                                                    .replaceAll("%num_account%", aux.getCode())
                                                    .replaceAll("%balance_total%", aux.getAmount_total())
                                                    .replaceAll("%amount_campaign%", aux.getAmount_campaign())
                                                    .replaceAll("%entity%", aux.getBank())
                                                    .replaceAll("%proxy_fullname%", aux.getProxy())
                                                    .replaceAll("%date_debt%", aux.getDateDebt()));
                                            this.jtfCurrentPathFile.setText(aux.getCustomer());
                                            if (sendMail(aux, emailModel)) {
                                                listCustomers.remove(customer);
                                                Thread.sleep(configSend.getTimeSeconds());
                                                break;
                                            } else {
                                                this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                                                break;
                                            }
                                        }
                                    }
                                }
                                break;
                            } else {
                                ExtraCode.sendMessageSuccessfully("Todos los correos fueron enviados correctamente.");
                                this.setStatusButtonsStart(true, false, false, false);
                                this.statusButtons(true);
                                this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                                this.eeac.cancel();
                            }
                        }
                        case 1 -> {
                            if (!listCustomers.isEmpty()) {
                                this.emailModel.setUser(configSend.getEmail());
                                this.emailModel.setPassword(configSend.getPassword());
                                for (int h = 0; h < listCustomers.size(); h++) {
                                    String customer = listCustomers.get(h);
                                    this.emailModel.setDestiny(configSend.getCustomerEmailsInLine(customer, " ;"));
                                    ArrayList<EmailDataCustomersModel> listCustomerFromFile = configSend.getCustomersFrom();
                                    for (int i = 0; i < listCustomerFromFile.size(); i++) {
                                        EmailDataCustomersModel aux = listCustomerFromFile.get(i);
                                        System.out.println(emailModel.getDestiny() + " - " + customer + " - " + aux.getAmount_total() + " - " + aux.getProxy());
                                        if (aux.getCustomer().equals(customer)) {
                                            this.emailModel.setSubject(configSend.getTemplate().getSuject()
                                                    .replaceAll("%customer_fullname%", aux.getCustomer())
                                                    .replaceAll("%entity%", aux.getBank())
                                                    .replaceAll("%proxy_fullname%", aux.getProxy())
                                                    .replaceAll("%date_debt%", aux.getDateDebt()));
                                            this.emailModel.setMessage(configSend.getTemplate().getTemplate().replaceAll("%name_financial%", aux.getBank())
                                                    .replaceAll("%customer_fullname%", aux.getCustomer())
                                                    .replaceAll("%num_account%", aux.getCode())
                                                    .replaceAll("%balance_total%", aux.getAmount_total())
                                                    .replaceAll("%amount_campaign%", aux.getAmount_campaign())
                                                    .replaceAll("%entity%", aux.getBank())
                                                    .replaceAll("%proxy_fullname%", aux.getProxy())
                                                    .replaceAll("%date_debt%", aux.getDateDebt()));
                                            this.jtfCurrentPathFile.setText(aux.getCustomer());
                                            if (sendMail(aux, emailModel)) {
                                                listCustomers.remove(customer);
                                                Thread.sleep(configSend.getTimeSeconds());
                                                break;
                                            } else {
                                                this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                                                break;
                                            }
                                        }
                                    }
                                }
                                break;
                            } else {
                                ExtraCode.sendMessageSuccessfully("Todos los correos fueron enviados correctamente.");
                                this.setStatusButtonsStart(true, false, false, false);
                                this.statusButtons(true);
                                this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                                this.eeac.cancel();
                            }
                        }
                    }
                    break;
                }
            }
        } catch (InterruptedException e) {
            ExtraCode.writeConsole(jtaConsole, "[" + ExtraCode.getCurrentDateFormat("dd/MM/yyyy hh:mm:ss aa") + "] Error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private boolean sendMail(EmailDataCustomersModel edcm, EmailModel em) {
        boolean status = false;
        if (SendEmailController.getInstance(instance).sendMail(em, jrbPDF.isSelected())) {
            UpdateReportController ur = new UpdateReportController(jtfPathFileExcel.getText(), edcm.getDni(), edcm.getCode(), edcm.getCustomer(), em.getDestiny(), ExtraCode.getCurrentDateFormat("dd/MM/yyyy hh:mm:ss aa"));
            if (ur.addRow()) {
                this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                ExtraCode.writeConsole(jtaConsole, "[" + ExtraCode.getCurrentDateFormat("dd/MM/yyyy hh:mm:ss aa") + "] Correo enviado con éxito a " + edcm.getCustomer() + " [" + em.getDestiny() + "]");
                if (jrbPDF.isSelected() == true) {
                    if (ExtraCode.deleteFile(em.getFile())) {
                        this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                        status = true;
                    } else {
                        ExtraCode.writeConsole(jtaConsole, "[" + ExtraCode.getCurrentDateFormat("dd/MM/yyyy hh:mm:ss aa") + "] No se pudo eliminar el archivo de " + edcm.getCustomer());
                        this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                    }
                } else {
                    status = true;
                }
            } else {
                ExtraCode.writeConsole(jtaConsole, "[" + ExtraCode.getCurrentDateFormat("dd/MM/yyyy hh:mm:ss aa") + "] No se pudo guardar el reporte del archivo de " + edcm.getCustomer());
                this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
        } else {
            this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        }
        return status;
    }
}
