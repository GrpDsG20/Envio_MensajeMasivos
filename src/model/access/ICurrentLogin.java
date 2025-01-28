package model.access;

public interface ICurrentLogin {
    
    public boolean insert();
    public boolean update();
    public CurrentLoginModel selectWithIdLogin();
    
}
