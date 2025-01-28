package model.login;

import mysql.ConnectionMySQL;
import java.sql.*;

public class DAOLogin extends ConnectionMySQL implements ILogin{
    
    private LoginModel model;
    
    public DAOLogin(){
        super();
        this.model=new LoginModel();
    }
    
    public DAOLogin(LoginModel model){
        super();
        this.model=model;
    }
    
    public void setModel(LoginModel model){
        this.model=model;
    }

    @Override
    public LoginModel selectWithUsername() {
        String url="SELECT * FROM login WHERE username='"+model.getUsername()+"'";
        this.model=null;
        try (Statement statement=openConnection().createStatement();ResultSet rs=statement.executeQuery(url);){
            if(rs.next()){
                this.model=new LoginModel(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4), rs.getTimestamp(5), rs.getTimestamp(6));
            }
            rs.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            this.closeConnection();
        }
        return model;
    }
}
