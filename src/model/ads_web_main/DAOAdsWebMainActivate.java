package model.ads_web_main;

import java.util.ArrayList;
import java.sql.*;
import mysql.ConnectionMySQL;

public class DAOAdsWebMainActivate extends ConnectionMySQL implements IAdsWebMainActivate{
    
    private AdsWebMainActivateModel model;
    
    public DAOAdsWebMainActivate(){
        super();
        this.model=new AdsWebMainActivateModel();
    }
    
    public DAOAdsWebMainActivate(AdsWebMainActivateModel model){
        super();
        this.model=model;
    }
    
    public void setModel(AdsWebMainActivateModel model){
        this.model=model;
    }

    @Override
    public boolean insert() {
        boolean status=false;
        String url="INSERT INTO ads_web_main_num_order(idAdsMain,num_order,register_date) VALUES(?,?,?)";
        try (PreparedStatement ps=openConnection().prepareStatement(url)){
            ps.setInt(1, model.getIdAdsMain());
            ps.setInt(2, model.getNumOrder());
            ps.setTimestamp(3, new Timestamp(model.getRegisterDate().getTime()));
            int result=ps.executeUpdate();
            if(result>0){
                status=true;
            }
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            this.closeConnection();
        }
        return status;
    }

    @Override
    public boolean updateNumOrder() {
        boolean status=false;
        String url="UPDATE ads_web_main_num_order SET num_order=?,update_date=? WHERE idAdsMain=?";
        try (PreparedStatement ps=openConnection().prepareStatement(url)){
            ps.setInt(1, model.getNumOrder());
            ps.setTimestamp(2, new Timestamp(model.getUpdateDate().getTime()));
            ps.setInt(3, model.getIdAdsMain());
            int result=ps.executeUpdate();
            if(result>0){
                status=true;
            }
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            this.closeConnection();
        }
        return status;
    }

    @Override
    public ArrayList<AdsWebMainActivateModel> select() {
        ArrayList<AdsWebMainActivateModel> list=new ArrayList<>();
        String url="SELECT * FROM ads_web_main_num_order";
        try (Statement statement=openConnection().createStatement();ResultSet rs=statement.executeQuery(url)){
            while(rs.next()){
                list.add(new AdsWebMainActivateModel(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getTimestamp(4), rs.getTimestamp(5)));
            }
            statement.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            this.closeConnection();
        }
        return list;
    }

    @Override
    public AdsWebMainActivateModel selectWithIdAdsMain() {
        String url="SELECT * FROM ads_web_main_num_order WHERE idAdsMain="+model.getIdAdsMain();
        this.model=null;
        try (Statement statement=openConnection().createStatement();ResultSet rs=statement.executeQuery(url)){
            while(rs.next()){
                this.model=new AdsWebMainActivateModel(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getTimestamp(4), rs.getTimestamp(5));
            }
            statement.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            this.closeConnection();
        }
        return model;
    }
}
