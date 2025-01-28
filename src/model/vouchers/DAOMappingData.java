package model.vouchers;

import java.util.ArrayList;
import java.sql.*;
import mysql.ConnectionMySQL;

public class DAOMappingData implements IMappingData{
    
    private ConnectionMySQL connection;
    private MappingData model;
    
    public DAOMappingData(){
        this.connection=new ConnectionMySQL();
        this.model=new MappingData();
    }
    
    public DAOMappingData(MappingData model){
        this.model=model;
        this.connection=new ConnectionMySQL();
    }
    
    public void setModel(MappingData model){
        this.model=model;
    }

    @Override
    public boolean insert() {
        boolean status=false;
        String url="INSERT INTO mapping_data(portfolio,dni,code_score,code_customer,register_date) VALUES(?,?,?,?)";
        try (PreparedStatement ps = connection.openConnection().prepareStatement(url)) {
            ps.setString(1, model.getPortfolio());
            ps.setString(2, model.getDni());
            ps.setString(3, model.getCodeScore());
            ps.setString(4, model.getCodeCustomer());
            ps.setTimestamp(5, new Timestamp(model.getRegisterDate().getTime()));
            if (ps.executeUpdate() > 0) {
                status = true;
            }
            ps.close();
        }catch (SQLException e) {
            e.printStackTrace();
        }finally{
            this.connection.closeConnection();
        }
        return status;
    }

    @Override
    public ArrayList<MappingData> selectWithDNI(String dni) {
        ArrayList<MappingData> list=new ArrayList<>();
        String url="SELECT * FROM mapping_data WHERE dni LIKE '%"+dni+"%'";
        try (Statement statement = connection.openConnection().createStatement(); ResultSet rs = statement.executeQuery(url)) {
            while(rs.next()){
                list.add(new MappingData(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getTimestamp(6)));
            }
            rs.close();
            statement.close();
        }catch (SQLException e) {
            e.printStackTrace();
        }finally{
            this.connection.closeConnection();
        }
        return list;
    }

    @Override
    public ArrayList<MappingData> selectWithCodeScore(String code) {
        ArrayList<MappingData> list=new ArrayList<>();
        String url="SELECT * FROM mapping_data WHERE code_score LIKE '%"+code+"%'";
        try (Statement statement = connection.openConnection().createStatement(); ResultSet rs = statement.executeQuery(url)) {
            while(rs.next()){
                list.add(new MappingData(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getTimestamp(6)));
            }
            rs.close();
            statement.close();
        }catch (SQLException e) {
            e.printStackTrace();
        }finally{
            this.connection.closeConnection();
        }
        return list;
    }

    @Override
    public ArrayList<MappingData> selectWithCodeCustomer(String code) {
        ArrayList<MappingData> list=new ArrayList<>();
        String url="SELECT * FROM mapping_data WHERE code_customer LIKE '%"+code+"%'";
        try (Statement statement = connection.openConnection().createStatement(); ResultSet rs = statement.executeQuery(url)) {
            while(rs.next()){
                list.add(new MappingData(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getTimestamp(6)));
            }
            rs.close();
            statement.close();
        }catch (SQLException e) {
            e.printStackTrace();
        }finally{
            this.connection.closeConnection();
        }
        return list;
    }

    @Override
    public MappingData selectWithDNIandCodeScore(String dni, String code1,String code2) {
        MappingData aux=null;
        String url="SELECT * FROM mapping_data WHERE dni='"+dni+"' AND code_score='"+code1+"' AND code_customer='"+code2+"'";
        try (Statement statement = connection.openConnection().createStatement(); ResultSet rs = statement.executeQuery(url)) {
            if(rs.next()){
                aux=new MappingData(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getTimestamp(6));
            }
            rs.close();
            statement.close();
        }catch (SQLException e) {
            e.printStackTrace();
        }finally{
            this.connection.closeConnection();
        }
        return aux;
    }
}
