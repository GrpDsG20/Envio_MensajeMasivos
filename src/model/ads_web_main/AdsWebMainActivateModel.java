package model.ads_web_main;

import java.util.Date;

public class AdsWebMainActivateModel {
    
    private int id;
    private int idAdsMain;
    private int numOrder;
    private Date registerDate;
    private Date updateDate;

    public AdsWebMainActivateModel(int id, int idAdsMain, int numOrder, Date registerDate, Date updateDate) {
        this.id = id;
        this.idAdsMain = idAdsMain;
        this.numOrder = numOrder;
        this.registerDate = registerDate;
        this.updateDate = updateDate;
    }

    public AdsWebMainActivateModel() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdAdsMain() {
        return idAdsMain;
    }

    public void setIdAdsMain(int idAdsMain) {
        this.idAdsMain = idAdsMain;
    }

    public int getNumOrder() {
        return numOrder;
    }

    public void setNumOrder(int numOrder) {
        this.numOrder = numOrder;
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
