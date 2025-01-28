package model.vouchers;

import java.io.FileInputStream;
import java.util.Date;

public class RPModel {
    
    private int id;
    private String name;
    private FileInputStream fis;
    private Date registerDate;

    public RPModel(int id, String name, FileInputStream fis, Date registerDate) {
        this.id = id;
        this.name = name;
        this.fis = fis;
        this.registerDate = registerDate;
    }
    
    public RPModel(int id, String name, Date registerDate) {
        this.id = id;
        this.name = name;
        this.registerDate = registerDate;
    }

    public RPModel() {
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

    public FileInputStream getFis() {
        return fis;
    }

    public void setFis(FileInputStream fis) {
        this.fis = fis;
    }

    public Date getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }
}
