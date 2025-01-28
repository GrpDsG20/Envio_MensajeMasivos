package model.vouchers;

import java.io.FileInputStream;
import java.util.Date;
import javax.swing.ImageIcon;

public class VouchersModel {
    
    private int id;
    private String code;
    private String customer;
    private FileInputStream voucherFis;
    private ImageIcon voucherIcon;
    private Date registerDate;

    public VouchersModel(int id, String code, String customer, FileInputStream voucherFis, Date registerDate) {
        this.id = id;
        this.code = code;
        this.customer = customer;
        this.voucherFis = voucherFis;
        this.registerDate = registerDate;
    }

    public VouchersModel(int id, String code, String customer, ImageIcon voucherIcon, Date registerDate) {
        this.id = id;
        this.code = code;
        this.customer = customer;
        this.voucherIcon = voucherIcon;
        this.registerDate = registerDate;
    }

    public VouchersModel() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public FileInputStream getVoucherFis() {
        return voucherFis;
    }

    public void setVoucherFis(FileInputStream voucherFis) {
        this.voucherFis = voucherFis;
    }

    public ImageIcon getVoucherIcon() {
        return voucherIcon;
    }

    public void setVoucherIcon(ImageIcon voucherIcon) {
        this.voucherIcon = voucherIcon;
    }

    public Date getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }
}
