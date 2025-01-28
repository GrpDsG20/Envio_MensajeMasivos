package file;

import addons.ExtraCode;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.swing.JTextArea;
import model.login.LoginModel;
import model.mysql.MySQL;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.bukkit.configuration.file.YamlConfiguration;

public class FileClass {
    
    private final File file;
    private final File directory;
    private final YamlConfiguration config;
    private static FileClass instance;
    
    private final File directoryReport;
    private final File fileReport;
    private final YamlConfiguration configReport;
    
    public FileClass(){
        this.directory=new File("C:\\JBP");
        this.file=new File(directory,"config.yml");
        this.config=YamlConfiguration.loadConfiguration(file);
        
        this.directoryReport=new File("C:\\JBP\\SECM - Reports");
        this.fileReport=new File(directoryReport,ExtraCode.getCurrentDateFormat("MM-yyyy")+".xlsx");
        this.configReport=YamlConfiguration.loadConfiguration(file);
    }
    
    public static FileClass getInstance(){
        return instance=new FileClass();
    }
    
    public boolean saveStyleCell(String path,XSSFWorkbook wb,JTextArea console){
        boolean status=false;
        try (FileOutputStream out = new FileOutputStream(path)) {
            wb.write(out);
            out.close();
            status=true;
        }catch (IOException e) {
            e.printStackTrace();
            console.append("Error: "+e.getLocalizedMessage()+"\n");
        }
        return status;
    }
    
    public boolean isExistsConfigFile(){
        return file.exists();
    }
    
    public boolean createConfigFile(){
        boolean status=false;
        try {
            this.directory.mkdir();
            this.file.createNewFile();
            this.setParametersMySQL(new MySQL(null, null, null, null, null, false));
            status=true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return status;
    }
    
    public boolean setParametersMySQL(MySQL mysql){
        boolean status=false;
        try {
            this.config.set("MySQL.ip", mysql.getIp());
            this.config.set("MySQL.port", mysql.getPort());
            this.config.set("MySQL.database", mysql.getDatabase());
            this.config.set("MySQL.username", mysql.getUsername());
            this.config.set("MySQL.password", mysql.getPassword());
            this.config.set("MySQL.status", mysql.isStatus());
            this.config.save(file);
            status=true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return status;
    }
    
    public MySQL getMySQL(){
        return new MySQL(config.getString("MySQL.ip"), config.getString("MySQL.port"), config.getString("MySQL.database"), config.getString("MySQL.username"), config.getString("MySQL.password"), config.getBoolean("MySQL.status"));
    }
    
    public File getFile(){
        return file;
    }
    
    public boolean saveLoginModel(LoginModel lm,boolean save){
        boolean status=false;
        try {
            this.config.set("RememberAccount.status", save);
            if(lm==null){
                this.config.set("RememberAccount.username", null);
                this.config.set("RememberAccount.password", null);
                this.config.save(file);
            }else{
                this.config.set("RememberAccount.username", lm.getUsername());
                this.config.set("RememberAccount.password", lm.getPassword());
                this.config.save(file);
            }
            status=true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return status;
    }
    
    public boolean getStatusRememberAccount(){
        return config.getBoolean("RememberAccount.status");
    }
    
    public LoginModel getRememberAccount(){
        return new LoginModel(0, 0, config.getString("RememberAccount.username"), config.getString("RememberAccount.password"), null,null);
    }
    
    public boolean createReportFile(){
        boolean status=false;
        try {
            if(!fileReport.exists()){
                this.directoryReport.mkdir();
                this.fileReport.createNewFile();
                Workbook book=new XSSFWorkbook();
                Sheet sheet = book.createSheet(ExtraCode.getCurrentDateFormat("MM-yyyy"));
                Row row=sheet.createRow(0);
                row.createCell(0).setCellValue("DATA NAME");
                row.createCell(1).setCellValue("DNI");
                row.createCell(2).setCellValue("CODE CUSTOMER");
                row.createCell(3).setCellValue("CUSTOMER");
                row.createCell(4).setCellValue("E-MAIL");
                row.createCell(5).setCellValue("DATE");
                FileOutputStream fos=new FileOutputStream(fileReport);
                book.write(fos);
                fos.close();
                status=true;
            }else{
                status=true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return status;
    }
    
    public File getReportFile(){
        return this.fileReport;
    }
}