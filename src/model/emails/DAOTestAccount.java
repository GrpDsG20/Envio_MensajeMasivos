package model.emails;

import java.sql.*;
import java.util.ArrayList;
import mysql.ConnectionMySQL;

public class DAOTestAccount implements ITestAccount{
    
    private final ConnectionMySQL connection;
    private TestAccountModel model;

    public DAOTestAccount(TestAccountModel model) {
        this.model = model;
        this.connection = new ConnectionMySQL();
    }
    
    public DAOTestAccount() {
        this.connection = new ConnectionMySQL();
    }

    @Override
    public boolean insert() {
        boolean status=false;
        String url="INSERT INTO email_test_account(account,register_date) VALUES(?,?)";
        try (PreparedStatement ps = connection.openConnection().prepareStatement(url)) {
            ps.setString(1, model.getAccount());
            ps.setTimestamp(2, new Timestamp(model.getRegisterDate().getTime()));
            int result=ps.executeUpdate();
            if(result>0){
                status=true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            this.connection.closeConnection();
        }
        return status;
    }

    @Override
    public boolean update() {
        boolean status=false;
        String url="UPDATE email_test_account SET account=?, update_date=? WHERE id=?";
        try (PreparedStatement ps = connection.openConnection().prepareStatement(url)) {
            ps.setString(1, model.getAccount());
            ps.setTimestamp(2, new Timestamp(model.getUpdateDate().getTime()));
            ps.setInt(3, model.getId());
            int result=ps.executeUpdate();
            if(result>0){
                status=true;
            }
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            this.connection.closeConnection();
        }
        return status;
    }

    @Override
    public boolean delete() {
        boolean status=false;
        String url="DELETE FROM email_test_account WHERE id=?";
        try (PreparedStatement ps = connection.openConnection().prepareStatement(url)) {
            ps.setInt(1, model.getId());
            int result=ps.executeUpdate();
            if(result>0){
                status=true;
            }
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            this.connection.closeConnection();
        }
        return status;
    }

    @Override
    public ArrayList<TestAccountModel> select() {
        ArrayList<TestAccountModel> list=new ArrayList<>();
        String url="SELECT * FROM email_test_account";
        try (Statement statement = connection.openConnection().createStatement(); ResultSet rs = statement.executeQuery(url)) {
            while(rs.next()){
                list.add(new TestAccountModel(rs.getInt(1), rs.getString(2), rs.getTimestamp(3), rs.getTimestamp(4)));   
            }
            rs.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            this.connection.closeConnection();
        }
        return list;
    }
    
    public String getAllAccountsInLine(){
        ArrayList<TestAccountModel> accounts=select();
        String destiny="";
        for(TestAccountModel aux:accounts){
            destiny=destiny+aux.getAccount()+" ;";
        }
        destiny=(!destiny.isEmpty())?(String)destiny.subSequence(0, destiny.length()-2):null;
        return destiny;
    }

    @Override
    public TestAccountModel selectID(int id) {
        TestAccountModel aux=null;
        String url="SELECT * FROM email_test_account WHERE id="+id;
        try (Statement statement = connection.openConnection().createStatement(); ResultSet rs = statement.executeQuery(url)) {
            if(rs.next()){
                aux=new TestAccountModel(rs.getInt(1), rs.getString(2), rs.getTimestamp(3), rs.getTimestamp(4));
            }
            rs.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            this.connection.closeConnection();
        }
        return aux;
    }

    @Override
    public TestAccountModel selectAccount(String account) {
        TestAccountModel aux=null;
        String url="SELECT * FROM email_test_account WHERE id='"+account+"'";
        try (Statement statement = connection.openConnection().createStatement(); ResultSet rs = statement.executeQuery(url)) {
            if(rs.next()){
                aux=new TestAccountModel(rs.getInt(1), rs.getString(2), rs.getTimestamp(3), rs.getTimestamp(4));
            }
            rs.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            this.connection.closeConnection();
        }
        return aux;
    }
}
