package model.emails;

import java.sql.*;
import java.util.ArrayList;
import mysql.ConnectionMySQL;

public class DAOEmailTemplate implements IEmailTemplate{

    private final ConnectionMySQL connection;
    private EmailTemplateModel model;

    public DAOEmailTemplate(EmailTemplateModel model) {
        this.model=model;
        this.connection=new ConnectionMySQL();
    }
    
    public DAOEmailTemplate() {
        this.connection=new ConnectionMySQL();
    }
    
    @Override
    public boolean insert() {
        boolean status=false;
        String url="INSERT INTO email_templates(name,subject,template,register_date) VALUES(?,?,?,?)";
        try (PreparedStatement ps = connection.openConnection().prepareStatement(url)) {
            ps.setString(1, model.getName());
            ps.setString(2, model.getSuject());
            ps.setString(3, model.getTemplate());
            ps.setTimestamp(4, new Timestamp(model.getRegisterDate().getTime()));
            int result=ps.executeUpdate();
            if(result>0){
                status=true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }finally{
            this.connection.closeConnection();
        }
        return status;
    }

    @Override
    public boolean update() {
        boolean status=false;
        String url="UPDATE email_templates SET name=?,subject=?,template=?,update_date=? WHERE id=?";
        try (PreparedStatement ps = connection.openConnection().prepareStatement(url)) {
            ps.setString(1, model.getName());
            ps.setString(2, model.getSuject());
            ps.setString(3, model.getTemplate());
            ps.setTimestamp(4, new Timestamp(model.getUpdateDate().getTime()));
            ps.setInt(5, model.getId());
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
    public ArrayList<EmailTemplateModel> select() {
        ArrayList<EmailTemplateModel> list=new ArrayList<>();
        String url="SELECT * FROM email_templates";
        try {
            Statement statement=connection.openConnection().createStatement();
            ResultSet rs=statement.executeQuery(url);
            while(rs.next()){
                list.add(new EmailTemplateModel(rs.getInt(1), rs.getString(2), rs.getString(3),rs.getString(4), rs.getTimestamp(5), rs.getTimestamp(6)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public EmailTemplateModel selectSpecificID(int id) {
        EmailTemplateModel aux=null;
        String url="SELECT * FROM email_templates WHERE id="+id;
        try (Statement statement = connection.openConnection().createStatement(); ResultSet rs = statement.executeQuery(url)) {
            if(rs.next()){
                aux=new EmailTemplateModel(rs.getInt(1), rs.getString(2), rs.getString(3),rs.getString(4), rs.getTimestamp(5), rs.getTimestamp(6));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            this.connection.closeConnection();
        }
        return aux;
    }

    @Override
    public EmailTemplateModel selectSpecificName(String name) {
        EmailTemplateModel aux=null;
        String url="SELECT * FROM email_templates WHERE name='"+name+"'";
        try (Statement statement = connection.openConnection().createStatement(); ResultSet rs = statement.executeQuery(url)) {
            if(rs.next()){
                aux=new EmailTemplateModel(rs.getInt(1), rs.getString(2), rs.getString(3),rs.getString(4), rs.getTimestamp(5), rs.getTimestamp(6));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            this.connection.closeConnection();
        }
        return aux;
    }

    @Override
    public boolean delete() {
        boolean status=false;
        String url="DELETE FROM email_templates WHERE id=?";
        try (PreparedStatement ps = connection.openConnection().prepareStatement(url)) {
            ps.setInt(1, model.getId());
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
}
