package model.proposals;

public class CustomerModel {
    
    private String codeScore;
    private String codeBT;
    private String name;
    private String dni;
    private double percentageDscto;
    private double campaign;
    private double capital;
    private int yearCast;
    private String emp;

    public CustomerModel(String codeScore, String codeBT, String name, String dni, double percentageDscto, double campaign, double capital, int yearCast, String emp) {
        this.codeScore = codeScore;
        this.codeBT = codeBT;
        this.name = name;
        this.dni = dni;
        this.percentageDscto = percentageDscto;
        this.campaign = campaign;
        this.capital = capital;
        this.yearCast = yearCast;
        this.emp = emp;
    }

    public CustomerModel() {
    }

    public String getCodeScore() {
        return codeScore;
    }

    public void setCodeScore(String codeScore) {
        this.codeScore = codeScore;
    }

    public String getCodeBT() {
        return codeBT;
    }

    public void setCodeBT(String codeBT) {
        this.codeBT = codeBT;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public double getPercentageDscto() {
        return percentageDscto;
    }

    public void setPercentageDscto(double percentageDscto) {
        this.percentageDscto = percentageDscto;
    }

    public double getCampaign() {
        return campaign;
    }

    public void setCampaign(double campaign) {
        this.campaign = campaign;
    }

    public double getCapital() {
        return capital;
    }

    public void setCapital(double capital) {
        this.capital = capital;
    }

    public int getYearCast() {
        return yearCast;
    }

    public void setYearCast(int yearCast) {
        this.yearCast = yearCast;
    }

    public String getEmp() {
        return emp;
    }

    public void setEmp(String emp) {
        this.emp = emp;
    }
}