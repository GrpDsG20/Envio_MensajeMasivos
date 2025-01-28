package model.emails;

import java.util.ArrayList;

public interface ITestAccount {
    
    public boolean insert();
    public boolean update();
    public boolean delete();
    public ArrayList select();
    public TestAccountModel selectID(int id);
    public TestAccountModel selectAccount(String account);
}
