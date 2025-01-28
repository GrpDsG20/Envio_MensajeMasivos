package model.ads_web_main;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.sql.*;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import mysql.ConnectionMySQL;

public class DAOAdsWebMain extends ConnectionMySQL implements IAdsWebMain{
    
    private AdsWebMainModel model;
    
    public DAOAdsWebMain(){
        super();
        this.model=new AdsWebMainModel();
    }
    
    public DAOAdsWebMain(AdsWebMainModel model){
        super();
        this.model=model;
    }
    
    public void setModel(AdsWebMainModel model){
        this.model=model;
    }

    @Override
    public boolean insert() {
        boolean status=false;
        String url="INSERT INTO ads_web_main(title,description,image,status,register_date) VALUES(?,?,?,?,?)";
        try (PreparedStatement ps=openConnection().prepareStatement(url)){
            ps.setString(1, model.getTitle());
            ps.setString(2, model.getDescription());
            ps.setBinaryStream(3, model.getImg_fis());
            ps.setBoolean(4, model.isStatus());
            ps.setTimestamp(5, new Timestamp(model.getRegisterDate().getTime()));
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
    public boolean update() {
        boolean status=false;
        String url="UPDATE ads_web_main SET title=?,description=?,image=?,status=?,update_date=? WHERE id=?";
        try (PreparedStatement ps=openConnection().prepareStatement(url)){
            ps.setString(1, model.getTitle());
            ps.setString(2, model.getDescription());
            ps.setBinaryStream(3, model.getImg_fis());
            ps.setBoolean(4, model.isStatus());
            ps.setTimestamp(5, new Timestamp(model.getUpdateDate().getTime()));
            ps.setInt(6, model.getId());
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
    public boolean delete() {
        boolean status=false;
        String url="DELETE FROM ads_web_main WHERE id=?";
        try (PreparedStatement ps=openConnection().prepareStatement(url)){
            ps.setInt(1, model.getId());
            int result=ps.executeUpdate();
            if(result>0){
                status=false;
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
    public ArrayList<AdsWebMainModel> select() {
        ArrayList<AdsWebMainModel> list=new ArrayList<>();
        String url="SELECT * FROM ads_web_main";
        try (Statement statement=openConnection().createStatement();ResultSet rs=statement.executeQuery(url)){
            while(rs.next()){
                InputStream is=rs.getBinaryStream("image");
                File tempFile=File.createTempFile("temp", ".tmp");
                FileOutputStream fos = new FileOutputStream(tempFile);
                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = is.read(buffer)) != -1) {
                    fos.write(buffer, 0, bytesRead);
                }
                fos.close();
                is.close();
                FileInputStream fis = new FileInputStream(tempFile);
                ImageIcon img=new ImageIcon(ImageIO.read(fis));
                list.add(new AdsWebMainModel(rs.getInt("id"), rs.getString("title"), rs.getString("description"), new FileInputStream(tempFile), img, rs.getBoolean("status"), rs.getTimestamp("register_date"), rs.getTimestamp("update_date")));
                tempFile.delete();
            }
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }finally{
            this.closeConnection();
        }
        return list;
    }

    @Override
    public AdsWebMainModel selectId() {
        String url="SELECT * FROM ads_web_main WHERE id="+model.getId();
        this.model=null;
        try (Statement statement=openConnection().createStatement();ResultSet rs=statement.executeQuery(url)){
            if(rs.next()){
                InputStream is = rs.getBinaryStream("image");
                File tempFile = File.createTempFile("temp", ".png");
                FileOutputStream fos = new FileOutputStream(tempFile);
                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = is.read(buffer)) != -1) {
                    fos.write(buffer, 0, bytesRead);
                }
                fos.close();
                is.close();

                if (tempFile.exists() && tempFile.isFile()) {
                    FileInputStream fis = new FileInputStream(new File(tempFile.getAbsolutePath()));
                    BufferedImage bufferedImage = ImageIO.read(fis);
                    ImageIcon img = new ImageIcon(bufferedImage);
                    this.model=new AdsWebMainModel(rs.getInt("id"), rs.getString("title"), rs.getString("description"), new FileInputStream(new File(tempFile.getAbsolutePath())), img, rs.getBoolean("status"), rs.getTimestamp("register_date"), rs.getTimestamp("update_date"));
                    tempFile.delete();
                }
            }
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }finally{
            this.closeConnection();
        }
        return model;
    }

    @Override
    public ArrayList<AdsWebMainModel> selectWithoutImg() {
        ArrayList<AdsWebMainModel> list=new ArrayList<>();
        String url="SELECT id,title,description,status,register_date,update_date FROM ads_web_main";
        try (Statement statement=openConnection().createStatement();ResultSet rs=statement.executeQuery(url)){
            while(rs.next()){
                list.add(new AdsWebMainModel(rs.getInt("id"), rs.getString("title"), rs.getString("description"), null, null, rs.getBoolean("status"), rs.getTimestamp("register_date"), rs.getTimestamp("update_date")));
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
    
}
