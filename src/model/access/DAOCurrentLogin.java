package model.access;

import mysql.ConnectionMySQL;
import java.sql.*;

public class DAOCurrentLogin extends ConnectionMySQL implements ICurrentLogin{
    
    private CurrentLoginModel model;
    
    public DAOCurrentLogin(){
        super();
        this.model=new CurrentLoginModel();
    }
    
    public DAOCurrentLogin(CurrentLoginModel model){
        super();
        this.model=model;
    }
    
    public void setModel(CurrentLoginModel model){
        this.model=model;
    }

    @Override
    public boolean insert() {
        boolean status=false;
        String url="INSERT INTO current_login(idLogin,status,register_date) VALUES(?,?,?)";
        try (PreparedStatement ps=openConnection().prepareStatement(url);){
            ps.setInt(1, model.getIdLogin());
            ps.setBoolean(2, model.isStatus());
            ps.setTimestamp(3, new Timestamp(model.getRegisterDate().getTime()));
            int result=ps.executeUpdate();
            if(result>0){
                status=true;
            }
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            this.closeConnection();
        }
        return status;
    }

    @Override
    public boolean update() {
        boolean status=false;
        String url="UPDATE current_login SET status=?,update_date=? WHERE idLogin=?";
        try (PreparedStatement ps=openConnection().prepareStatement(url);){
            ps.setBoolean(1, model.isStatus());
            ps.setTimestamp(2, new Timestamp(model.getUpdateDate().getTime()));
            ps.setInt(3, model.getIdLogin());
            int result=ps.executeUpdate();
            if(result>0){
                status=true;
            }
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            this.closeConnection();
        }
        return status;
    }

    @Override
    public CurrentLoginModel selectWithIdLogin() {
        String url="SELECT * FROM current_login WHERE idLogin="+model.getIdLogin();
        this.model=null;
        try (Statement statement=openConnection().createStatement();ResultSet rs=statement.executeQuery(url)){
            if(rs.next()){
                model=new CurrentLoginModel(rs.getInt(1), rs.getInt(2), rs.getBoolean(3), rs.getTimestamp(4),rs.getTimestamp(5));
            }
            rs.close();
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return model;
    }
    
}
