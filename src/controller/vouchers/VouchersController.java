package controller.vouchers;

import java.awt.Image;
import javax.swing.ImageIcon;
import model.vouchers.VouchersModel;
import model.vouchers.DAOVouchers;
import view.vouchers.VouchersView;

public class VouchersController extends VouchersView{
    
    private static VouchersController instance;
    private DAOVouchers dao;
    private VouchersModel model;
    
    public VouchersController(int id)throws Exception{
        super();
        this.registerObjects(id);
    }
    
    public static VouchersController getInstance(int id){
        try {
            instance=new VouchersController(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return instance;
    }
    
    private void registerObjects(int id){
        this.dao=new DAOVouchers();
        this.model=dao.selectSpecificID(id);
        this.jlImage.setIcon(new ImageIcon(model.getVoucherIcon().getImage().getScaledInstance(jlImage.getWidth(), jlImage.getHeight(), Image.SCALE_SMOOTH)));
        this.setVisible(true);
    }
}
