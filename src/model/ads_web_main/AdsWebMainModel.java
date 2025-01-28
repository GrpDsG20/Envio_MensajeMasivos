package model.ads_web_main;

import java.io.FileInputStream;
import java.util.Date;
import javax.swing.ImageIcon;

public class AdsWebMainModel {
    
    private int id;
    private String title;
    private String description;
    private FileInputStream img_fis;
    private ImageIcon img;
    private boolean status;
    private Date registerDate;
    private Date updateDate;

    public AdsWebMainModel(int id, String title, String description, FileInputStream img_fis, ImageIcon img, boolean status, Date registerDate, Date updateDate) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.img_fis = img_fis;
        this.img = img;
        this.status = status;
        this.registerDate = registerDate;
        this.updateDate = updateDate;
    }

    public AdsWebMainModel() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public FileInputStream getImg_fis() {
        return img_fis;
    }

    public void setImg_fis(FileInputStream img_fis) {
        this.img_fis = img_fis;
    }

    public ImageIcon getImg() {
        return img;
    }

    public void setImg(ImageIcon img) {
        this.img = img;
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
