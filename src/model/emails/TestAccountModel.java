package model.emails;

import java.util.Date;

public class TestAccountModel {
    
    private int id;
    private String account;
    private Date registerDate;
    private Date updateDate;

    public TestAccountModel(int id, String account, Date registerDate, Date updateDate) {
        this.id = id;
        this.account = account;
        this.registerDate = registerDate;
        this.updateDate = updateDate;
    }

    public TestAccountModel() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public Date getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
}
