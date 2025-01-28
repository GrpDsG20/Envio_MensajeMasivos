package model.access;

import java.util.Date;

public class AccessAssignmentModel {
    
    private int id;
    private int idLogin;
    private int idAccess;
    private Date registerDate;
    private Date updateDate;

    public AccessAssignmentModel(int id, int idLogin, int idAccess, Date registerDate, Date updateDate) {
        this.id = id;
        this.idLogin = idLogin;
        this.idAccess = idAccess;
        this.registerDate = registerDate;
        this.updateDate = updateDate;
    }

    public AccessAssignmentModel() {
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

    public int getIdAccess() {
        return idAccess;
    }

    public void setIdAccess(int idAccess) {
        this.idAccess = idAccess;
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
