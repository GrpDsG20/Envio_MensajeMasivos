package addons;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.io.File;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;
import org.apache.poi.ss.usermodel.Cell;
import static org.apache.poi.ss.usermodel.CellType.NUMERIC;
import static org.apache.poi.ss.usermodel.CellType.STRING;

public class ExtraCode {
    
    public static int sendOptionMessage(String title,Object obj){
        return JOptionPane.showOptionDialog(null, obj, title, JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, null, null);
    }
    
    public static Color getColorFromHTML(String htmlColor) {
        return new Color(
                Integer.valueOf(htmlColor.substring(1, 3), 16),
                Integer.valueOf(htmlColor.substring(3, 5), 16),
                Integer.valueOf(htmlColor.substring(5, 7), 16));
    }
    
    public static boolean deleteFile(String path){
        File file=new File(path);
        return file.delete();
    }
    
    public static void loadDataComboBox(JComboBox jcb,ArrayList data){
        jcb.removeAllItems();
        jcb.addItem("---- Seleccione ----");
        ArrayList<String> list=data;
        for(int i=0;i<list.size();i++){
            jcb.addItem(list.get(i));
        }
    }
    
    public static String isAllFilesPDF(String path){
        String fileProblem=null;
        File folder=new File(path);
        for (File file : folder.listFiles()) {
            if(!file.getName().endsWith(".pdf")){
                fileProblem=file.getName();
                break;
            }
        }
        return fileProblem;
    }

    public static String convertTextFormat(double data) {
        DecimalFormat df = new DecimalFormat("#,###.00");
        return df.format(data);
    }
    
    public static void consumeLetter(KeyEvent e){
        if(Character.isLetter(e.getKeyChar())){
            e.consume();
            sendMessageError("Error: Debe colocar solo números, no letras.");
        }
    }

    public static String toPercentage(double n) {
        DecimalFormat df = new DecimalFormat("#.##");
        return df.format(n * 100) + "%";
    }

    public static String convertCellValueToString(Cell cell) {
        String value = null;
        if (cell == null) {
            return " ";
        }
        switch (cell.getCellTypeEnum()) {
            case STRING ->
                value = cell.getStringCellValue();
            case NUMERIC ->
                value = String.valueOf(cell.getNumericCellValue());
            case BLANK ->
                value = String.valueOf(cell.getNumericCellValue());
            default -> {
            }
        }
        return value;
    }

    public static Date getCurrentDate() {
        return Calendar.getInstance().getTime();
    }

    public static String getCurrentDateFormat(String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(Calendar.getInstance().getTime());
    }

    public static void sendMessageError(String msg) {
        JOptionPane.showMessageDialog(null, msg, "Error", 0, new ImageIcon(new ExtraCode().getClass().getResource("/images/error32x32.png")));
    }

    public static void sendMessageSuccessfully(String msg) {
        JOptionPane.showMessageDialog(null, msg, "Éxito", 0, new ImageIcon(new ExtraCode().getClass().getResource("/images/exito32x32.png")));
    }

    public static int sendMessageConfirm(String msg) {
        return JOptionPane.showConfirmDialog(null, msg, "Confirmar", 0, 0, new ImageIcon(new ExtraCode().getClass().getResource("/images/archivos32x32.png")));
    }

    public static void writeConsole(JTextArea jta, String msg) {
        boolean scrollToBottom = false;
        if (jta.getCaretPosition() == jta.getDocument().getLength()) {
            scrollToBottom = true;
        }

        if (jta.getText().isEmpty()) {
            jta.append(msg);
        } else {
            jta.append("\n" + msg);
        }

        if (scrollToBottom) {
            jta.setCaretPosition(jta.getDocument().getLength());
        }
        jta.repaint();
        jta.update(jta.getGraphics());
    }

    public static DefaultTableModel getDefaultTableModel(JTable jtable) {
        jtable.setDefaultRenderer(Object.class, new RendererTable());
        String[] columns = new String[jtable.getModel().getColumnCount()];
        for (int i = 0; i < columns.length; i++) {
            columns[i] = jtable.getModel().getColumnName(i);
        }
        DefaultTableModel dtm = new DefaultTableModel(null, columns) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        return dtm;
    }

    public static String convertDateFormat(Date date, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }

    public static void writeComboBoxItems(ArrayList<String> list, JComboBox jcb) {
        ArrayList<String> aux = list;
        for (int i = 0; i < aux.size(); i++) {
            jcb.addItem(aux.get(i));
        }
    }
}
