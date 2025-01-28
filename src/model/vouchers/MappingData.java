package model.vouchers;

import java.util.Date;

public class MappingData {
    
    private int id;
    private String portfolio;
    private String dni;
    private String codeScore;
    private String codeCustomer;
    private Date registerDate;

    public MappingData(int id, String portfolio, String dni, String codeScore, String codeCustomer, Date registerDate) {
        this.id = id;
        this.portfolio = portfolio;
        this.dni = dni;
        this.codeScore = codeScore;
        this.codeCustomer = codeCustomer;
        this.registerDate = registerDate;
    }

    public MappingData() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPortfolio() {
        return portfolio;
    }

    public void setPortfolio(String portfolio) {
        this.portfolio = portfolio;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getCodeScore() {
        return codeScore;
    }

    public void setCodeScore(String codeScore) {
        this.codeScore = codeScore;
    }

    public String getCodeCustomer() {
        return codeCustomer;
    }

    public void setCodeCustomer(String codeCustomer) {
        this.codeCustomer = codeCustomer;
    }

    public Date getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }
}
