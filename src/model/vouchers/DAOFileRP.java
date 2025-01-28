package model.vouchers;

import addons.ExtraCode;
import addons.RendererTable;
import addons.TableModeler;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.sql.*;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JTable;
import javax.swing.table.TableModel;
import mysql.ConnectionMySQL;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class DAOFileRP implements IFileRP{
    
    private final ConnectionMySQL connection;
    private RPModel model;
    
    public DAOFileRP(){
        this.connection=new ConnectionMySQL();
        this.model=new RPModel();
    }
    
    public DAOFileRP(RPModel model){
        this.connection=new ConnectionMySQL();
        this.model=model;
    }
    
    public void setModel(RPModel model){
        this.model=model;
    }

    @Override
    public boolean register() {
        boolean status=false;
        String url="INSERT INTO files_rp(name,file,register_date) VALUES(?,?,?)";
        try (PreparedStatement ps = connection.openConnection().prepareStatement(url)) {
            ps.setString(1, model.getName());
            ps.setBinaryStream(2, model.getFis());
            ps.setTimestamp(3, new Timestamp(model.getRegisterDate().getTime()));
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
    public ArrayList<RPModel> selectWithoutFile(String search) {
        ArrayList<RPModel> list=new ArrayList<>();
        String url="SELECT * FROM files_rp WHERE CONCAT(id,' ', name,' ',register_date) LIKE '%"+search+"%'";
        try (Statement statement = connection.openConnection().createStatement(); ResultSet rs = statement.executeQuery(url)) {
            while(rs.next()){
                list.add(new RPModel(rs.getInt(1), rs.getString(2), rs.getTimestamp(4)));
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
    public boolean delete() {
        boolean status=false;
        String url="DELETE FROM files_rp WHERE id=?";
        try (PreparedStatement ps = connection.openConnection().prepareStatement(url)) {
            ps.setInt(1, model.getId());
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
    
    
    public void model(JTable table){
        table.setModel(createTableModeler());
        table.setDefaultRenderer(Object.class, new RendererTable());
    }
    
    private TableModel createTableModeler(){
        return new TableModeler<RPModel>(){
            @Override
            public Object getValueAt(RPModel t, int column) {
                JButton btnShow=new JButton("Descargar");
                JButton btnDelete=new JButton("Eliminar");
                switch (column) {
                    case 0 -> {
                        return t.getId();
                    }
                    case 1 -> {
                        return t.getName();
                    }
                    case 2 -> {
                        return ExtraCode.convertDateFormat(t.getRegisterDate(), "dd/MM/yyyy hh:mm:ss aa");
                    }
                    case 3 -> {
                        btnShow.setName(String.valueOf(t.getId()));
                        return btnShow;
                    }
                    case 4 -> {
                        btnDelete.setName(String.valueOf(t.getId()));
                        return btnDelete;
                    }
                }
                return null;
            }

            @Override
            public String getColumnName(int column) {
                switch (column) {
                    case 0 -> {
                        return "ID";
                    }
                    case 1 -> {
                        return "NOMBRE ARCHIVO";
                    }
                    case 2 -> {
                        return "FECHA REGISTRO";
                    }
                    case 3 -> {
                        return "DESCARGAR";
                    }
                    case 4 -> {
                        return "ELIMINAR";
                    }
                }
                return null;
            }

            @Override
            public int getColumnCount() {
                return 5;
            }
            
        };
    }

    @Override
    public void downloadDataExcel(int id) {
        String url="SELECT name,file FROM files_rp WHERE id="+id;
        try{
            Statement statement = connection.openConnection().createStatement(); 
            ResultSet rs = statement.executeQuery(url);
            if(rs.next()){
                InputStream is = rs.getBinaryStream("file");
                Workbook wb=WorkbookFactory.create(is);
                is.close();
                
                JFileChooser jfc = new JFileChooser();
                String name_file=rs.getString("name");
                jfc.setCurrentDirectory(new File("."));
                jfc.setDialogTitle("Seleccione la carpeta para guardar el archivo excel");
                jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                jfc.setAcceptAllFileFilterUsed(false);

                if (jfc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                    FileOutputStream out=new FileOutputStream(jfc.getSelectedFile()+"\\"+name_file);
                    wb.write(out);
                    out.close();
                }
            }
            rs.close();
            statement.close();
        }catch (InvalidFormatException | IOException | SQLException e) {
            e.printStackTrace();
        }finally{
            this.connection.closeConnection();
        }
    }
}
