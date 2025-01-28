package model.emails;

import addons.ExtraCode;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import mysql.ConnectionMySQL;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class EmailSendConfigModel {

    private DAOEmailTemplate daoTemplate;

    private String email;
    private String password;
    private int timeSeconds;
    private int shippingMethod;
    private int nameMethod;
    private String directoryPDF;
    private String fileCustomers;
    private String nameTemplate;

    private EmailTemplateModel template;
    private ArrayList<String> customersFromPDF;
    private ArrayList<EmailDataCustomersModel> customersFromFile;

    public EmailSendConfigModel(String email, String password, int timeSeconds, int shippingMethod, int nameMethod, String directoryPDF, String fileCustomers, String nameTemplate) {
        this.nameMethod = nameMethod;
        this.email = email;
        this.password = password;
        this.timeSeconds = timeSeconds * 1000;
        this.shippingMethod = shippingMethod;
        this.directoryPDF = directoryPDF;
        this.fileCustomers = fileCustomers;
        this.nameTemplate = nameTemplate;
    }

    public EmailSendConfigModel() {
    }

    public int getNameMethod() {
        return nameMethod;
    }

    public void setNameMethod(int nameMethod) {
        this.nameMethod = nameMethod;
    }

    public int getTimeSeconds() {
        return timeSeconds;
    }

    public void setTimeSeconds(int timeSeconds) {
        this.timeSeconds = timeSeconds * 1000;
    }

    public int getShippingMethod() {
        return shippingMethod;
    }

    public void setShippingMethod(int shippingMethod) {
        this.shippingMethod = shippingMethod;
    }

    public ArrayList<String> getCustomersFromPDF() {
        return customersFromPDF;
    }

    public void setCustomersFromPDF(String path) {
        this.customersFromPDF = new ArrayList<>();
        File folder = new File(path);
        ArrayList<String> list = new ArrayList<>();
        for (File file : folder.listFiles()) {
            list.add(file.getName());
        }
        this.customersFromPDF = list;
    }

    public ArrayList<EmailDataCustomersModel> getCustomersFrom() {
        return customersFromFile;
    }

    public ArrayList<String> getCustomerWithoutDuplicate() {
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < customersFromFile.size(); i++) {
            if (!list.contains(customersFromFile.get(i).getCustomer())) {
                list.add(customersFromFile.get(i).getCustomer());
            }
        }
        return list;
    }

    public void setCustomersFrom(String path, String modality) {
        this.customersFromFile = new ArrayList<>();
        switch (modality) {
            case "Excel" -> {
                try {
                    FileInputStream fis = new FileInputStream(path);
                    XSSFWorkbook wb = new XSSFWorkbook(fis);
                    XSSFSheet sheet = wb.getSheetAt(0);
                    int numRows = sheet.getLastRowNum();
                    int numColumns = sheet.getRow(0).getPhysicalNumberOfCells();

                    for (int i = 1; i <= numRows; i++) {
                        Row row = sheet.getRow(i);
                        EmailDataCustomersModel dcm = new EmailDataCustomersModel();
                        for (int j = 0; j < numColumns; j++) {
                            if (sheet.getRow(0).getCell(j).getStringCellValue().equalsIgnoreCase("DNI")) {
                                dcm.setDni(ExtraCode.convertCellValueToString(row.getCell(j)));
                            }
                            if (sheet.getRow(0).getCell(j).getStringCellValue().equalsIgnoreCase("CODIGO_CLIENTE")) {
                                dcm.setCode(ExtraCode.convertCellValueToString(row.getCell(j)));
                            }
                            if (sheet.getRow(0).getCell(j).getStringCellValue().equalsIgnoreCase("CORREO")) {
                                dcm.setEmail(ExtraCode.convertCellValueToString(row.getCell(j)));
                            }
                            if (sheet.getRow(0).getCell(j).getStringCellValue().equalsIgnoreCase("CLIENTE")) {
                                dcm.setCustomer(ExtraCode.convertCellValueToString(row.getCell(j)));
                            }
                            if (sheet.getRow(0).getCell(j).getStringCellValue().equalsIgnoreCase("MONTO_CAMPAÑA")) {
                                dcm.setAmount_campaign(ExtraCode.convertCellValueToString(row.getCell(j)));
                            }
                            if (sheet.getRow(0).getCell(j).getStringCellValue().equalsIgnoreCase("MONTO_TOTAL")) {
                                dcm.setAmount_total(ExtraCode.convertCellValueToString(row.getCell(j)));
                            }
                            if (sheet.getRow(0).getCell(j).getStringCellValue().equalsIgnoreCase("ENTIDAD")) {
                                dcm.setBank(ExtraCode.convertCellValueToString(row.getCell(j)));
                            }
                            if (sheet.getRow(0).getCell(j).getStringCellValue().equalsIgnoreCase("APODERADO")) {
                                dcm.setProxy(ExtraCode.convertCellValueToString(row.getCell(j)));
                            }
                            if (sheet.getRow(0).getCell(j).getStringCellValue().equalsIgnoreCase("FECHA_DEUDA")) {
                                dcm.setDateDebt(ExtraCode.convertCellValueToString(row.getCell(j)));
                            }
                        }
                        System.out.println("Datos del cliente: {" + dcm.getDni() + "," + dcm.getCode() + "," + dcm.getCustomer() + "," + dcm.getEmail() + "," + dcm.getAmount_total() + "," + dcm.getBank() + "," + dcm.getAmount_campaign() + "," + dcm.getProxy() + "," + dcm.getDateDebt());
                        this.customersFromFile.add(dcm);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            }
            case "Database" -> {
                String url = "SELECT * FROM data_email";
                Connection connection =ConnectionMySQL.getInstance().openConnection();
                try (Statement statement = ConnectionMySQL.getInstance().openConnection().createStatement(); ResultSet rs = statement.executeQuery(url)) {
                    while (rs.next()) {
                        EmailDataCustomersModel edcm=new EmailDataCustomersModel();
                        edcm.setId(rs.getInt(1));
                        edcm.setDni(rs.getString(2));
                        edcm.setCode(rs.getString(3));
                        edcm.setEmail(rs.getString(4));
                        edcm.setCustomer(rs.getString(5));
                        edcm.setAmount_campaign(rs.getString(6));
                        edcm.setAmount_total(rs.getString(7));
                        edcm.setBank(rs.getString(8));
                        edcm.setProxy(rs.getString(9));
                        edcm.setDateDebt(rs.getString(10));
                        this.customersFromFile.add(edcm);
                    }
                    rs.close();
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        connection.close();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }
                break;
            }
        }
    }

    public boolean isCustomerInFile(String customer) {
        boolean status = false;
        for (int i = 0; i < getCustomersFrom().size(); i++) {
            EmailDataCustomersModel aux = getCustomersFrom().get(i);
            if (aux.getCustomer().equalsIgnoreCase(customer)) {
                status = true;
            }
        }
        return status;
    }

    public String getCustomerEmailsInLine(String customer, String separator) {
        String emails = "";
        String EMAIL_REGEX = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";
        Pattern pat = Pattern.compile(EMAIL_REGEX);
        for (EmailDataCustomersModel aux : customersFromFile) {
            if (aux.getCustomer().equalsIgnoreCase(customer)) {
                Matcher matcher = pat.matcher(aux.getEmail());
                if (matcher.find() && (!aux.getEmail().contains(" ") && !aux.getEmail().contains(" "))) {
                    emails = emails + aux.getEmail() + separator;
                }
            }
        }
        if (!emails.isEmpty()) {
            emails = (String) emails.subSequence(0, emails.length() - 2);
        }
        return emails;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDirectoryPDF() {
        return directoryPDF;
    }

    public void setDirectoryPDF(String directoryPDF) {
        this.directoryPDF = directoryPDF;
    }

    public String getFileCustomers() {
        return fileCustomers;
    }

    public void setFileCustomers(String fileCustomers) {
        this.fileCustomers = fileCustomers;
    }

    public String getNameTemplate() {
        return nameTemplate;
    }

    public void setNameTemplate(String nameTemplate) {
        this.nameTemplate = nameTemplate;
    }

    public EmailTemplateModel getTemplate() {
        return template;
    }

    public void setTemplate(String name) {
        this.daoTemplate = new DAOEmailTemplate();
        this.template = daoTemplate.selectSpecificName(getNameTemplate());
    }

    public void load(boolean statusGetCustomer, String modality) {
        if (statusGetCustomer == true) {
            this.setCustomersFromPDF(getDirectoryPDF());
        }
        this.setCustomersFrom(getFileCustomers(), modality);
        this.setTemplate(getNameTemplate());
    }
}
