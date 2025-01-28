package model.access;

import mysql.ConnectionMySQL;
import java.sql.*;

public class DAOAccessName extends ConnectionMySQL implements IAccessName{
    
    private AccessNameModel model;
    
    public DAOAccessName(){
        super();
        this.model=new AccessNameModel();
    }
    
    public DAOAccessName(AccessNameModel model){
        super();
        this.model=model;
    }
    
    public void setModel(AccessNameModel model){
        this.model=model;
    }

    @Override
    public AccessNameModel selectWithId() {
        String url="SELECT * FROM access_name WHERE id="+model.getId();
        this.model=null;
        try (Statement statement=openConnection().createStatement();ResultSet rs=statement.executeQuery(url)){
            if(rs.next()){
                this.model=new AccessNameModel(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getTimestamp(4), rs.getTimestamp(5));
            }
            rs.close();
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            this.closeConnection();
        }
        return model;
    }
}
