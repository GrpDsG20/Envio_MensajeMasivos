package model.emails;

import java.util.Date;

public class EmailTemplateModel {
    
    private int id;
    private String name;
    private String suject;
    private String template;
    private Date registerDate;
    private Date updateDate;

    public EmailTemplateModel(int id, String name, String subject,String template, Date registerDate, Date updateDate) {
        this.id = id;
        this.name = name;
        this.suject=subject;
        this.template = template;
        this.registerDate = registerDate;
        this.updateDate = updateDate;
    }

    public EmailTemplateModel() {
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

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
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

    public String getSuject() {
        return suject;
    }

    public void setSuject(String suject) {
        this.suject = suject;
    }
}
