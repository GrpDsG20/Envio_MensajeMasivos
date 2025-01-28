package model.access;

import java.util.Date;

public class CurrentLoginModel {
    
    private int id;
    private int idLogin;
    private boolean status;
    private Date registerDate;
    private Date updateDate;

    public CurrentLoginModel(int id, int idLogin, boolean status, Date registerDate, Date updateDate) {
        this.id = id;
        this.idLogin = idLogin;
        this.status = status;
        this.registerDate = registerDate;
        this.updateDate = updateDate;
    }

    public CurrentLoginModel() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdLogin() {
        return idLogin;
    }

    public void setIdLogin(int idLogin) {
        this.idLogin = idLogin;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
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
