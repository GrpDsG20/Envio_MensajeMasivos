package controller.emails;

import file.FileClass;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import model.emails.ReportEmailModel;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class UpdateReportController extends ReportEmailModel{

    public UpdateReportController(String nameData, String dni, String code, String customer, String email, String reportDate) {
        super(nameData, dni, code, customer, email, reportDate);
    }
    
    public boolean addRow(){
        boolean status=false;
        try {
            FileInputStream fis=new FileInputStream(FileClass.getInstance().getReportFile());
            XSSFWorkbook wb=new XSSFWorkbook(fis);
            XSSFSheet sheet=wb.getSheetAt(0);
            int rowNumber=1;
            int lastRow=sheet.getLastRowNum();
            XSSFRow row=sheet.createRow(lastRow+rowNumber);
            row.createCell(0).setCellValue(getNameData());
            row.createCell(1).setCellValue(getDni());
            row.createCell(2).setCellValue(getCode());
            row.createCell(3).setCellValue(getCustomer());
            row.createCell(4).setCellValue(getEmail());
            row.createCell(5).setCellValue(getReportDate());
            FileOutputStream fos=new FileOutputStream(FileClass.getInstance().getReportFile());
            wb.write(fos);
            fos.close();
            status=true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return status;
    }
}
