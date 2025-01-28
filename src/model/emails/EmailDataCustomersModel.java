package model.emails;

import addons.ExtraCode;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class EmailDataCustomersModel {
    
    private int id;
    private String dni;
    private String code;
    private String customer;
    private String proxy;
    private String dateDebt;
    private String amount_campaign;
    private String amount_total;
    private String bank;
    private String email;
    
    private ArrayList<EmailDataCustomersModel> customers;

    public EmailDataCustomersModel(int id, String dni, String code, String customer, String amount_campaign, String amount_total, String bank, String email,ArrayList<EmailDataCustomersModel> customers,String proxy,String dateDebt) {
        this.id = id;
        this.dni = dni;
        this.code = code;
        this.customer = customer;
        this.amount_campaign = amount_campaign;
        this.amount_total = amount_total;
        this.bank = bank;
        this.email = email;
        this.customers=customers;
        this.proxy=proxy;
        this.dateDebt=dateDebt;
    }

    public EmailDataCustomersModel() {
        this.customers=new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getAmount_campaign() {
        return amount_campaign;
    }

    public void setAmount_campaign(String amount_campaign) {
        this.amount_campaign = amount_campaign;
    }

    public String getAmount_total() {
        return amount_total;
    }

    public void setAmount_total(String amount_total) {
        this.amount_total = amount_total;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public ArrayList<EmailDataCustomersModel> uploadDataCustomer(String path){
        try {
            FileInputStream fis=new FileInputStream(path);
            XSSFWorkbook wb = new XSSFWorkbook(fis);
            XSSFSheet sheet = wb.getSheetAt(0);
            int numRows=sheet.getLastRowNum();
            int numColumns=sheet.getRow(0).getPhysicalNumberOfCells();

            for (int i = 1; i <= numRows; i++) {
                Row row = sheet.getRow(i);
                EmailDataCustomersModel dcm=new EmailDataCustomersModel();
                for(int j=0;j<numColumns;j++){
                    if(sheet.getRow(0).getCell(j).getStringCellValue().equalsIgnoreCase("DNI")){
                        dcm.setDni(ExtraCode.convertCellValueToString(row.getCell(j)));
                    }
                    if(sheet.getRow(0).getCell(j).getStringCellValue().equalsIgnoreCase("CODIGO_CLIENTE")){
                        dcm.setCode(ExtraCode.convertCellValueToString(row.getCell(j)));
                    }
                    if(sheet.getRow(0).getCell(j).getStringCellValue().equalsIgnoreCase("CORREO")){
                        dcm.setEmail(ExtraCode.convertCellValueToString(row.getCell(j)));
                    }
                    if(sheet.getRow(0).getCell(j).getStringCellValue().equalsIgnoreCase("CLIENTE")){
                        dcm.setCustomer(ExtraCode.convertCellValueToString(row.getCell(j)));
                    }
                    if(sheet.getRow(0).getCell(j).getStringCellValue().equalsIgnoreCase("MONTO_CAMPAÑA")){
                        dcm.setAmount_campaign(ExtraCode.convertCellValueToString(row.getCell(j)));
                    }
                    if(sheet.getRow(0).getCell(j).getStringCellValue().equalsIgnoreCase("MONTO_TOTAL")){
                        dcm.setAmount_total(ExtraCode.convertCellValueToString(row.getCell(j)));
                    }
                    if(sheet.getRow(0).getCell(j).getStringCellValue().equalsIgnoreCase("ENTIDAD")){
                        dcm.setBank(ExtraCode.convertCellValueToString(row.getCell(j)));
                    }
                }
                System.out.println("Datos del cliente: {"+dcm.getDni()+","+dcm.getCode()+","+dcm.getCustomer()+","+dcm.getEmail()+","+dcm.getAmount_total()+","+dcm.getBank()+","+dcm.getAmount_campaign());
                this.customers.add(dcm);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return customers;
    }

    public ArrayList<EmailDataCustomersModel> getCustomers() {
        return customers;
    }

    public void setCustomers(ArrayList<EmailDataCustomersModel> customers) {
        this.customers = customers;
    }

    public String getProxy() {
        return proxy;
    }

    public void setProxy(String proxy) {
        this.proxy = proxy;
    }

    public String getDateDebt() {
        return dateDebt;
    }

    public void setDateDebt(String dateDebt) {
        this.dateDebt = dateDebt;
    }
}
