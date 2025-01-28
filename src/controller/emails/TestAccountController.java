package controller.emails;

import addons.ExtraCode;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import model.access.AccessDetails;
import model.emails.TestAccountModel;
import model.emails.DAOTestAccount;
import view.emails.TestAccountView;

public class TestAccountController extends TestAccountView implements ActionListener{

    private static TestAccountController instance;
    private DAOTestAccount dao;
    private TableRowSorter sorter;
    private AccessDetails access;
    
    public TestAccountController(AccessDetails access)throws Exception{
        super();
        this.registerEvents();
        this.registerObjects(access);
    }
    
    private void registerEvents(){
        this.jtListTestAccounts.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e){
                btnInsideTable(e);
            }
        });
        
        this.jtfSearch.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e){
                String text=jtfSearch.getText();
                sorter.setRowFilter(RowFilter.regexFilter(text, 1));
            }
        });
        
        this.jbtCancelUpdate.addActionListener(this);
        this.jbtSaveTestAccount.addActionListener(this);
        this.jbtUpdateTestAccount.addActionListener(this);
        
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e){
                EmailMainController.getInstance(access);
            }
        });
    }
    
    private void registerObjects(AccessDetails access){
        this.access=access;
        this.dao=new DAOTestAccount();
        this.loadTable();
        this.setVisible(true);
    }
    
    public static TestAccountController getInstance(AccessDetails access){
        try {
            instance=new TestAccountController(access);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return instance;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(jbtSaveTestAccount==e.getSource()){
            this.btnSaveTestAccount();
        }else if(jbtUpdateTestAccount==e.getSource()){
            this.btnUpdateTestAccount();
        }else if(jbtCancelUpdate==e.getSource()){
            this.btnCancelUpdate();
        }
    }
    
    private void btnSaveTestAccount(){
        String email=jtfEmail.getText();
        if(!email.isEmpty()){
            TestAccountModel model=new TestAccountModel(0, email, ExtraCode.getCurrentDate(), null);
            this.dao=new DAOTestAccount(model);
            if(dao.insert()){
                ExtraCode.sendMessageSuccessfully("Correo de prueba guardado con éxito.");
                this.jtfEmail.setText(null);
                this.loadTable();
            }else{
                ExtraCode.sendMessageError("Error: Correo de prueba no guardado, vuelva a intentarlo.");
            }
        }else{
            ExtraCode.sendMessageError("Error: Correo de prueba no ingresado, vuelva a intentarlo.");
        }
    }
    
    private void btnUpdateTestAccount(){
        String email=jtfEmail.getText();
        int id=Integer.parseInt(jtfEmail.getName());
        if(!email.isEmpty()){
            TestAccountModel aux=dao.selectID(id);
            if(aux!=null){
                TestAccountModel aux2=dao.selectAccount(email);
                if(aux2==null){
                    aux.setAccount(email);
                    aux.setUpdateDate(ExtraCode.getCurrentDate());
                    this.dao=new DAOTestAccount(aux);
                    if(dao.update()){
                        ExtraCode.sendMessageSuccessfully("Correo de prueba actualizado con éxito.");
                        this.changeStatusButtons(true);
                        this.jtfEmail.setText(null);
                        this.loadTable();
                    }else{
                        ExtraCode.sendMessageError("Error: Correo de prueba no guardado, vuelva a intentarlo1.");
                    }
                }else{
                    ExtraCode.sendMessageError("Error: Correo de prueba ya existe, vuelva a intentarlo.");
                }
                aux.setAccount(email);
            }else{
                ExtraCode.sendMessageError("Error: Correo de prueba no existe, verifique su base de datos y vuelva a intentarlo.");
            }
        }else{
            ExtraCode.sendMessageError("Error: Correo de prueba no ingresado, vuelva a intentarlo.");
        }
    }
    
    private void btnCancelUpdate(){
        this.changeStatusButtons(true);
        this.jtfEmail.setText(null);
        this.jtfEmail.requestFocus();
        this.jtListTestAccounts.getSelectionModel().clearSelection();
    }
    
    private void btnInsideTable(MouseEvent e){
        int click=jtListTestAccounts.rowAtPoint(e.getPoint());
        int id=Integer.parseInt(jtListTestAccounts.getValueAt(click, 0).toString());
        String email=jtListTestAccounts.getValueAt(click, 1).toString();
        int column=jtListTestAccounts.getColumnModel().getColumnIndexAtX(e.getX());
        int row=e.getY()/jtListTestAccounts.getRowHeight();
        int countrow=jtListTestAccounts.getRowCount();
        int countcolumn=jtListTestAccounts.getColumnCount();
        if(row<countrow && row>=0 && column<countcolumn && column>=0){
            Object value=jtListTestAccounts.getValueAt(row, column);
            if(value instanceof JButton jButton){
                jButton.doClick();
                JButton jbt=jButton;
                if(jbt.getText().equalsIgnoreCase("Modificar")){
                    jtfEmail.setText(email);
                    jtfEmail.setName(String.valueOf(id));
                    changeStatusButtons(false);
                }else if(jbt.getText().equalsIgnoreCase("Eliminar")){
                    int confirm=ExtraCode.sendMessageConfirm("¿Seguro que quiere el correo de prueba: "+email+"?");
                    if(confirm==0){
                        TestAccountModel aux=dao.selectID(id);
                        this.dao=new DAOTestAccount(aux);
                        if(dao.delete()){
                            this.loadTable();
                            ExtraCode.sendMessageSuccessfully("Correo de prueba eliminado con éxito.");
                        }else{
                            ExtraCode.sendMessageError("Error: No se pudo eliminar el correo de prueba, vuelva a intentarlo.");
                        }
                    }
                }
            }
        }
    }
    
    private void loadTable(){
        Object[] rows=new Object[jtListTestAccounts.getModel().getColumnCount()];
        DefaultTableModel dtm=ExtraCode.getDefaultTableModel(jtListTestAccounts);
        ArrayList<TestAccountModel> list=dao.select();
        for(int i=0;i<list.size();i++){
            TestAccountModel aux=list.get(i);
            JButton btnUpdate=new JButton("Modificar");
            JButton btnDelete=new JButton("Eliminar");
            rows[0]=aux.getId();
            rows[1]=aux.getAccount();
            rows[2]=ExtraCode.convertDateFormat(aux.getRegisterDate(), "dd/MM/yyyy hh:mm:ss aa");
            if(aux.getUpdateDate()!=null){
                rows[3]=ExtraCode.convertDateFormat(aux.getUpdateDate(), "dd/MM/yyyy hh:mm:ss aa");
            }else{
                rows[3]="No se registra actualización";
            }
            rows[4]=btnUpdate;
            rows[5]=btnDelete;
            dtm.addRow(rows);
        }
        this.jtListTestAccounts.setModel(dtm);
        this.sorter=new TableRowSorter<>(jtListTestAccounts.getModel());
        this.jtListTestAccounts.setRowSorter(sorter);
    }
    
    private void changeStatusButtons(boolean status){
        this.jbtSaveTestAccount.setEnabled(status);
        this.jbtUpdateTestAccount.setEnabled(!status);
        this.jbtCancelUpdate.setEnabled(!status);
    }
}
