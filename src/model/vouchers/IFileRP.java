package model.vouchers;

import java.util.ArrayList;

public interface IFileRP {
    
    public boolean register();
    public ArrayList<RPModel> selectWithoutFile(String search);
    public boolean delete();
    public void downloadDataExcel(int id);
}
