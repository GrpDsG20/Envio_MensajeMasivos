package model.vouchers;

import java.util.Date;

public class LoginModel {
    
    private int id;
    private int idRank;
    private String username;
    private String password;
    private Date registerDate;
    private Date updateDate;

    public LoginModel(int id, int idRank, String username, String password, Date registerDate, Date updateDate) {
        this.id = id;
        this.idRank = idRank;
        this.username = username;
        this.password = password;
        this.registerDate = registerDate;
        this.updateDate = updateDate;
    }

    public LoginModel() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdRank() {
        return idRank;
    }

    public void setIdRank(int idRank) {
        this.idRank = idRank;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
