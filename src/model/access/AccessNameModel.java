package model.access;

import java.util.Date;

public class AccessNameModel {
    
    private int id;
    private String name;
    private String description;
    private Date registerdate;
    private Date updateDate;

    public AccessNameModel(int id, String name, String description, Date registerdate, Date updateDate) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.registerdate = registerdate;
        this.updateDate = updateDate;
    }

    public AccessNameModel() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getRegisterdate() {
        return registerdate;
    }

    public void setRegisterdate(Date registerdate) {
        this.registerdate = registerdate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
}
