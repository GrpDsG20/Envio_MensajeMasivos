package controller.ads;

import addons.ExtraCode;
import controller.MainController;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import model.access.AccessDetails;
import model.ads_web_main.AdsWebMainModel;
import model.ads_web_main.DAOAdsWebMain;
import view.ads.AdsWebView;

public class AdsWebController extends AdsWebView implements ActionListener{
    
    private static AdsWebController instance;
    private AccessDetails access;
    private ArrayList<AdsWebMainActivateController> list_awmac;
    
    public AdsWebController(AccessDetails access)throws Exception{
        super();
        this.registerEvents();
        this.registerObjects(access);
    }
    
    public static AdsWebController getInstance(AccessDetails access){
        try {
            instance=new AdsWebController(access);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return instance;
    }
    
    private void registerEvents(){
        this.jbtExamineMain.addActionListener(this);
        this.jbtNewMain.addActionListener(this);
        this.jbtRegisterMain.addActionListener(this);
        this.jbtMain.addActionListener(this);
        
        this.jtCurrentAdsMain.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e){
                clickInCurrentAdsMainTable(e);
            }
        });
        
        this.jtfSearchMain.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e){
               if(e.getKeyCode()==KeyEvent.VK_ENTER){
                   String search=jtfSearchMain.getText();
                   writeCurrentAdsMainTable(jtCurrentAdsMain, search);
               } 
            }
        });
        
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e){
                for(AdsWebMainActivateController aux:list_awmac){
                    aux.dispose();
                }
                MainController.getInstance(access);
            }
        });
    }
    
    private void registerObjects(AccessDetails access){
        this.access=access;
        this.list_awmac=new ArrayList<>();
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(jbtExamineMain==e.getSource()){
            this.btnExamineMain();
        }else if(jbtNewMain==e.getSource()){
            this.btnNewMain();
        }else if(jbtRegisterMain==e.getSource()){
            this.btnRegisterMain();
        }else if(jbtMain==e.getSource()){
            this.btnMain();
        }
    }
    
    private void btnMain(){
        this.writeCurrentAdsMainTable(jtCurrentAdsMain, null);
    }
    
    private void btnRegisterMain(){
        String title=jtfTitleMain.getText();
        String path=jtfPathMain.getText();
        String description=jtaDescripcionMain.getText();
        if(!title.isEmpty()){
            if(!path.isEmpty()){
                try {
                    FileInputStream fis=new FileInputStream(new File(path));
                    AdsWebMainModel awmm=new AdsWebMainModel(0, title, description, fis, null, false, ExtraCode.getCurrentDate(), null);
                    DAOAdsWebMain dao=new DAOAdsWebMain(awmm);
                    if(dao.insert()){
                        ExtraCode.sendMessageSuccessfully("Anuncio principal registrado con éxito.");
                        this.btnNewMain();
                    }else{
                        ExtraCode.sendMessageError("Error: No se pudo registrar el anuncio principal, comuniquese con soporte técnico.");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }else{
                ExtraCode.sendMessageError("Error: Imagen del anuncio no seleccionado.");
            }
        }else{
            ExtraCode.sendMessageError("Error: Título del anuncio no ingresado.");
        }
    }
    
    private void btnNewMain(){
        this.jbtExamineMain.setText(null);
        this.jbtRegisterMain.setEnabled(true);
        this.jbtUpdateMain.setEnabled(false);
        this.jtfTitleMain.setText(null);
        this.jtaDescripcionMain.setText(null);
        this.jtfPathMain.setText(null);
    }
    
    private void btnExamineMain(){
        JFileChooser jfc=new JFileChooser();
        boolean status=false;
        while(!status){
            if(jfc.showDialog(this, "Seleccione váucher(JPG o PNG)")==0){
                File file=jfc.getSelectedFile();
                if(file.canRead()){
                    if(file.getName().endsWith("jpg") || file.getName().endsWith("JPG")|| file.getName().endsWith("png") || file.getName().endsWith("PNG")){
                        this.jtfPathMain.setText(file.getAbsolutePath());
                        status=true;
                    }else{
                        ExtraCode.sendMessageError("Error: Archivo seleccionado no admitido, vuelva a intentarlo.");
                    }
                }else{
                    ExtraCode.sendMessageError("Error: Archivo seleccionado no admitido, vuelva a intentarlo.");
                }
            }else{
                status=true;
            }
        }
    }
    
    private void writeCurrentAdsMainTable(JTable table,String search){
        Object[] rows=new Object[table.getModel().getColumnCount()];
        DefaultTableModel dtm=ExtraCode.getDefaultTableModel(table);
        JButton btnActive=new JButton("Activar");
        JButton btnDetails=new JButton("Ver Detalles");
        JButton btnModify=new JButton("Modificar");
        JButton btnDelete=new JButton("Eliminar");
        ArrayList<AdsWebMainModel> list=new DAOAdsWebMain().selectWithoutImg();
        for(AdsWebMainModel aux:list){
            switch(jcbTypeSearchMain.getSelectedIndex()){
                case 0->{
                    if(search==null || search.isEmpty()){
                        rows[0]=aux.getId();
                        rows[1]=aux.getTitle();
                        rows[2]=aux.getDescription();
                        rows[3]=btnActive;
                        rows[4]=btnDetails;
                        rows[5]=btnModify;
                        rows[6]=btnDelete;
                        dtm.addRow(rows);
                    }else{
                        if(String.valueOf(aux.getId()).contains(search) || aux.getTitle().contains(search) || aux.getDescription().contains(search)){
                            rows[0]=aux.getId();
                            rows[1]=aux.getTitle();
                            rows[2]=aux.getDescription();
                            rows[3]=btnActive;
                            rows[4]=btnDetails;
                            rows[5]=btnModify;
                            rows[6]=btnDelete;
                            dtm.addRow(rows);
                        }
                    }
                    break;
                }
                case 1->{
                    if(search!=null || !search.isEmpty()){
                        if(String.valueOf(aux.getId()).equalsIgnoreCase(search)){
                            rows[0]=aux.getId();
                            rows[1]=aux.getTitle();
                            rows[2]=aux.getDescription();
                            rows[3]=btnActive;
                            rows[4]=btnDetails;
                            rows[5]=btnModify;
                            rows[6]=btnDelete;
                            dtm.addRow(rows);
                        }
                    }
                    break;
                }
                case 2->{
                    if(search!=null || !search.isEmpty()){
                        if(aux.getTitle().contains(search)){
                            rows[0]=aux.getId();
                            rows[1]=aux.getTitle();
                            rows[2]=aux.getDescription();
                            rows[3]=btnActive;
                            rows[4]=btnDetails;
                            rows[5]=btnModify;
                            rows[6]=btnDelete;
                            dtm.addRow(rows);
                        }
                    }
                    break;
                }
            }
        }
        dtm.fireTableStructureChanged();
        table.setModel(dtm);
    }
    
    private void clickInCurrentAdsMainTable(MouseEvent e){
        int click=jtCurrentAdsMain.rowAtPoint(e.getPoint());
        int id=Integer.parseInt(jtCurrentAdsMain.getValueAt(click, 0).toString());
        int column=jtCurrentAdsMain.getColumnModel().getColumnIndexAtX(e.getX());
        int row=e.getY()/jtCurrentAdsMain.getRowHeight();
        int countrow=jtCurrentAdsMain.getRowCount();
        int countcolumn=jtCurrentAdsMain.getColumnCount();
        if(row<countrow && row>=0 && column<countcolumn && column>=0){
            Object value=jtCurrentAdsMain.getValueAt(row, column);
            if(value instanceof JButton btn){
                btn.doClick();
                if(btn.getText().equalsIgnoreCase("Activar")){
                    DAOAdsWebMain dao=new DAOAdsWebMain(new AdsWebMainModel(id, null, null, null, null, false, null, null));
                    AdsWebMainModel awmm=dao.selectId();
                    this.list_awmac.add(AdsWebMainActivateController.getInstance(awmm));
                }
            }
        }
    }
}
