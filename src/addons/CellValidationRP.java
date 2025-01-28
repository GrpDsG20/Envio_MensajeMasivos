package addons;

import model.vouchers.MappingData;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;

public class CellValidationRP {
    
    public static void paintCellsRow(XSSFRow row,int numColumns,XSSFCellStyle style){
        for (int j = 0; j < numColumns; j++) {
            XSSFCell cell=row.getCell(j);
            cell.setCellStyle(style);
        }
    }
    
    public static boolean validation(String column,Object data){
        boolean status=false;
        if(data!=null){
            switch(column){
                case "SEMANA":
                    if(data instanceof Integer){
                        status=true;
                        break;
                    }else if(data instanceof Double){
                        status=true;
                        break;
                    }else if(data instanceof String){
                        status=false;
                        break;
                    }else{
                        status=false;
                        break;
                    }
                case "COORDINADOR":
                    if(data instanceof Integer){
                        status=false;
                        break;
                    }else if(data instanceof Double){
                        status=false;
                        break;
                    }else if(data instanceof String){
                        String data_s=(String)data;
                        if(!data_s.equalsIgnoreCase("") && !data_s.equalsIgnoreCase(" ") && !data_s.equalsIgnoreCase(" ") && !data_s.equalsIgnoreCase(" ") && !data_s.contains(" ")){
                            status=true;
                            break;
                        }else{
                            status=false;
                            break;
                        }
                    }else{
                        status=false;
                        break;
                    }
                case "CARTERA":
                    if(data instanceof Integer){
                        status=false;
                        break;
                    }else if(data instanceof Double){
                        status=false;
                        break;
                    }else if(data instanceof String){
                        String data_s=(String)data;
                        if(!data_s.equalsIgnoreCase("") && !data_s.equalsIgnoreCase(" ") && !data_s.equalsIgnoreCase(" ") && !data_s.equalsIgnoreCase(" ") && !data_s.contains(" ")){
                            status=true;
                            break;
                        }else{
                            status=false;
                            break;
                        }
                    }else{
                        status=false;
                        break;
                    }
                case "SUPERVISOR(A)":
                    if(data instanceof Integer){
                        status=false;
                        break;
                    }else if(data instanceof Double){
                        status=false;
                        break;
                    }else if(data instanceof String){
                        String data_s=(String)data;
                        if(!data_s.equalsIgnoreCase("") && !data_s.equalsIgnoreCase(" ") && !data_s.equalsIgnoreCase(" ") && !data_s.equalsIgnoreCase(" ") && !data_s.contains(" ")){
                            status=true;
                            break;
                        }else{
                            status=false;
                            break;
                        }
                    }else{
                        status=false;
                        break;
                    }
                case "GERENCIA":
                    if(data instanceof Integer){
                        status=false;
                        break;
                    }else if(data instanceof Double){
                        status=false;
                        break;
                    }else if(data instanceof String){
                        String data_s=(String)data;
                        if(!data_s.equalsIgnoreCase("") && !data_s.equalsIgnoreCase(" ") && !data_s.equalsIgnoreCase(" ") && !data_s.equalsIgnoreCase(" ") && !data_s.contains(" ")){
                            status=true;
                            break;
                        }else{
                            status=false;
                            break;
                        }
                    }else{
                        status=false;
                        break;
                    }
                case "TELÉFONO":
                    if(data instanceof Integer){
                        status=true;
                        break;
                    }else if(data instanceof Double){
                        status=true;
                        break;
                    }else if(data instanceof String){
                        status=false;
                        break;
                    }else{
                        status=false;
                        break;
                    }
                case "DNI":
                    if(data instanceof Integer){
                        status=false;
                        break;
                    }else if(data instanceof Double){
                        status=false;
                        break;
                    }else if(data instanceof String){
                        String data_s=(String)data;
                        if(!data_s.equalsIgnoreCase("") && !data_s.equalsIgnoreCase(" ") && !data_s.equalsIgnoreCase(" ") && !data_s.equalsIgnoreCase(" ") && !data_s.contains(" ")){
                            status=true;
                            break;
                        }else{
                            status=false;
                            break;
                        }
                    }else{
                        status=false;
                        break;
                    }
                case "NOMBRE":
                    if(data instanceof Integer){
                        status=false;
                        break;
                    }else if(data instanceof Double){
                        status=false;
                        break;
                    }else if(data instanceof String){
                        String data_s=(String)data;
                        if(!data_s.equalsIgnoreCase("") && !data_s.equalsIgnoreCase(" ") && !data_s.equalsIgnoreCase(" ") && !data_s.equalsIgnoreCase(" ") && !data_s.contains(" ")){
                            status=true;
                            break;
                        }else{
                            status=false;
                            break;
                        }
                    }else{
                        status=false;
                        break;
                    }
                case "CÓDIGO DEL CLIENTE":
                    if(data instanceof Integer){
                        status=false;
                        break;
                    }else if(data instanceof Double){
                        status=false;
                        break;
                    }else if(data instanceof String){
                        String data_s=(String)data;
                        if(!data_s.equalsIgnoreCase("") && !data_s.equalsIgnoreCase(" ") && !data_s.equalsIgnoreCase(" ") && !data_s.equalsIgnoreCase(" ") && !data_s.contains(" ")){
                            status=true;
                            break;
                        }else{
                            status=false;
                            break;
                        }
                    }else{
                        status=false;
                        break;
                    }
                case "CÓDIGO DEL PRESTAMO":
                    if(data instanceof Integer){
                        status=false;
                        break;
                    }else if(data instanceof Double){
                        status=false;
                        break;
                    }else if(data instanceof String){
                        String data_s=(String)data;
                        if(!data_s.equalsIgnoreCase("") && !data_s.equalsIgnoreCase(" ") && !data_s.equalsIgnoreCase(" ") && !data_s.equalsIgnoreCase(" ") && !data_s.contains(" ")){
                            status=true;
                            break;
                        }else{
                            status=false;
                            break;
                        }
                    }else{
                        status=false;
                        break;
                    }
                case "FECHA DE PAGO":
                    if(data instanceof Integer){
                        status=false;
                        break;
                    }else if(data instanceof Double){
                        status=true;
                        break;
                    }else if(data instanceof String){
                        status=false;
                        break;
                    }else{
                        status=false;
                        break;
                    }
                case "MONTO":
                    if(data instanceof Integer){
                        status=true;
                        break;
                    }else if(data instanceof Double){
                        status=true;
                        break;
                    }else if(data instanceof String){
                        status=false;
                        break;
                    }else{
                        status=false;
                        break;
                    }
                case "TIPO DE PAGO":
                    if(data instanceof Integer){
                        status=false;
                        break;
                    }else if(data instanceof Double){
                        status=false;
                        break;
                    }else if(data instanceof String){
                        String data_s=(String)data;
                        if(!data_s.equalsIgnoreCase("") && !data_s.equalsIgnoreCase(" ") && !data_s.equalsIgnoreCase(" ") && !data_s.equalsIgnoreCase(" ") && !data_s.contains(" ")){
                            status=true;
                            break;
                        }else{
                            status=false;
                            break;
                        }
                    }else{
                        status=false;
                        break;
                    }
                case "COORDINADOR (SÓLO SI EL COORDINADORES RESPONSABLE DEL PAGO)":
                    if(data instanceof Integer){
                        status=false;
                        break;
                    }else if(data instanceof Double){
                        status=false;
                        break;
                    }else if(data instanceof String){
                        String data_s=(String)data;
                        if(!data_s.equalsIgnoreCase("") && !data_s.equalsIgnoreCase(" ") && !data_s.equalsIgnoreCase(" ") && !data_s.equalsIgnoreCase(" ") && !data_s.contains(" ")){
                            status=true;
                            break;
                        }
                    }else{
                        status=false;
                        break;
                    }
                case "SUPERVISOR":
                    if(data instanceof Integer){
                        status=false;
                        break;
                    }else if(data instanceof Double){
                        status=false;
                        break;
                    }else if(data instanceof String){
                        String data_s=(String)data;
                        if(!data_s.equalsIgnoreCase("") && !data_s.equalsIgnoreCase(" ") && !data_s.equalsIgnoreCase(" ") && !data_s.equalsIgnoreCase(" ") && !data_s.contains(" ")){
                            status=true;
                            break;
                        }
                    }else{
                        status=false;
                        break;
                    }
                case "%":
                    if(data instanceof Integer){
                        status=true;
                        break;
                    }else if(data instanceof Double){
                        status=true;
                        break;
                    }else if(data instanceof String){
                        status=false;
                        break;
                    }else{
                        status=false;
                        break;
                    }
                case "ASESOR TELÉFONICO":
                    if(data instanceof Integer){
                        status=false;
                    }else if(data instanceof Double){
                        status=false;
                    }else if(data instanceof String){
                        String data_s=(String)data;
                        if(!data_s.equalsIgnoreCase("") && !data_s.equalsIgnoreCase(" ") && !data_s.equalsIgnoreCase(" ") && !data_s.equalsIgnoreCase(" ") && !data_s.contains(" ")){
                            status=true;
                            break;
                        }else{
                            status=false;
                            break;
                        }
                    }else{
                        status=false;
                        break;
                    }
                case "%2":
                    if(data instanceof Integer){
                        status=true;
                        break;
                    }else if(data instanceof Double){
                        status=true;
                        break;
                    }else if(data instanceof String){
                        status=false;
                        break;
                    }else{
                        status=false;
                        break;
                    }
                case "GESTOR":
                    if(data instanceof Integer){
                        status=false;
                        break;
                    }else if(data instanceof Double){
                        status=false;
                        break;
                    }else if(data instanceof String){
                        String data_s=(String)data;
                        if(!data_s.equalsIgnoreCase("") && !data_s.equalsIgnoreCase(" ") && !data_s.equalsIgnoreCase(" ") && !data_s.equalsIgnoreCase(" ") && !data_s.contains(" ")){
                            status=true;
                            break;
                        }else{
                            status=false;
                            break;
                        }
                    }else{
                        status=false;
                        break;
                    }
                case "%3":
                    if(data instanceof Integer){
                        status=true;
                        break;
                    }else if(data instanceof Double){
                        status=true;
                        break;
                    }else if(data instanceof String){
                        status=false;
                        break;
                    }else{
                        status=false;
                        break;
                    }
                case "DERIVACION":
                    if(data instanceof Integer){
                        status=false;
                        break;
                    }else if(data instanceof Double){
                        status=false;
                        break;
                    }else if(data instanceof String){
                        String data_s=(String)data;
                        if(!data_s.equalsIgnoreCase("") && !data_s.equalsIgnoreCase(" ") && !data_s.equalsIgnoreCase(" ") && !data_s.equalsIgnoreCase(" ") && !data_s.contains(" ")){
                            status=true;
                            break;
                        }else{
                            status=false;
                            break;
                        }
                    }else{
                        status=false;
                        break;
                    }
            }
        }
        return status;
    }
    
    public static void validation_database(String column,Object data,MappingData md){
        if(data!=null){
            switch(column){
                case "DNI":
                    md.setDni((String)data);
                    break;
                case "CÓDIGO DEL CLIENTE":
                    md.setCodeScore((String)data);
                    break;
                case "CÓDIGO DEL PRESTAMO":
                    md.setCodeCustomer((String)data);
                    break;
            }
        }
    }
}
