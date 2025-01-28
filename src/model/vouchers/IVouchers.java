package model.vouchers;

import java.util.ArrayList;

public interface IVouchers {
    
    public boolean insert();
    public boolean delete();
    public ArrayList<VouchersModel> select(String search);
    public ArrayList<VouchersModel> selectWithoutImage(String search);
    public VouchersModel selectSpecificID(int id);
}
