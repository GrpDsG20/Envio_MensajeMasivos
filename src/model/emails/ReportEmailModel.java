package model.emails;

public class ReportEmailModel {
    
    private String nameData;
    private String dni;
    private String code;
    private String customer;
    private String email;
    private String reportDate;

    public ReportEmailModel(String nameData, String dni, String code, String customer, String email, String reportDate) {
        this.nameData = nameData;
        this.dni = dni;
        this.code = code;
        this.customer = customer;
        this.email = email;
        this.reportDate = reportDate;
    }

    public String getNameData() {
        return nameData;
    }

    public void setNameData(String nameData) {
        this.nameData = nameData;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getReportDate() {
        return reportDate;
    }

    public void setReportDate(String reportDate) {
        this.reportDate = reportDate;
    }
}
