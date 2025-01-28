package controller.proposals;

import addons.ExtraCode;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFileChooser;
import model.access.AccessDetails;
import model.proposals.CustomerModel;
import model.proposals.DAOProposals;
import view.proposals.PManagerView;

public class PManagerController extends PManagerView implements ActionListener{

    private static PManagerController instance;
    private DAOProposals dao;
    private CustomerModel model;
    private AccessDetails access;
    
    public PManagerController(AccessDetails access)throws Exception{
        super();
        this.registerEvents();
        this.registerObjects(access);
    }
    
    public static PManagerController getInstance(AccessDetails access){
        try {
            instance=new PManagerController(access);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return instance;
    }
    
    private void registerEvents(){
        this.jbtDeleteAll.addActionListener(this);
        this.jbtExamine.addActionListener(this);
        this.jbtSearch.addActionListener(this);
        this.jbtRegister.addActionListener(this);
        this.jbtNew.addActionListener(this);
        
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e){
                PMainController.getInstance(access);
            }
        });
    }
    
    private void registerObjects(AccessDetails access){
        this.access=access;
        this.dao=new DAOProposals();
        this.setVisible(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(jbtDeleteAll==e.getSource()){
            this.btnDeleteAll();
        }else if(jbtExamine==e.getSource()){
            this.btnExamine();
        }else if(jbtSearch==e.getSource()){
            this.btnSearch();
        }else if(jbtNew==e.getSource()){
            this.btnNew();
        }else if(jbtRegister==e.getSource()){
            this.btnRegister();
        }
    }
    
    private void btnNew(){
        this.jtfPathFile.setText(null);
        this.jtaConsole.setText(null);
        this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    }
    
    private void btnRegister(){
        String date_start=ExtraCode.getCurrentDateFormat("dd/MM/yyyy hh:mm:ss aa");
        String path=jtfPathFile.getText();
        if(!path.isEmpty()){
            if(ExtraCode.sendMessageConfirm("Se empezará a subir la data a la base de datos, esto puede tardar unos minutos...\n¿Seguro que quiere continuar?")==0){
                this.setCursor(new Cursor(Cursor.WAIT_CURSOR));
                ExtraCode.writeConsole(jtaConsole, "Fecha de inicio: "+date_start);
                String sql="INSERT INTO customer_proposals (code_score,code_bt,name,dni,percentage_dscto,campaign,capital,year_cast,emp) VALUES (?,?,?,?,?,?,?,?,?)";
                boolean status=dao.insert(sql,path,jtaConsole);
                if(status){
                    this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                    String date_end=ExtraCode.getCurrentDateFormat("dd/MM/yyyy hh:mm:ss aa");
                    ExtraCode.writeConsole(jtaConsole, "Fecha de fin: "+date_end);
                }else{
                    ExtraCode.sendMessageError("Error: No se pudo registrar la data, vuelva a intentarlo.");
                }
            }
        }else{
            ExtraCode.sendMessageError("Error: Archivo a subir no encontrado, vuelva a intentarlo.");
        }
    }
    
    private void btnSearch(){
        String search=jtfSearch.getText();
        if(!search.isEmpty()){
            this.model=dao.select(search);
            if(model!=null){
                ExtraCode.writeConsole(jtaConsole, "Cliente: "+model.getName()+"\nCódigo Score: "+model.getCodeScore()+"\nCódigo Cliente/BT: "+model.getCodeBT()+"\nDNI: "+model.getDni()+"\nPorcentaje de descuento: "+ExtraCode.toPercentage(model.getPercentageDscto())+"\nCapital: "+model.getCapital()+"\nCampaña: "+model.getCampaign()+"\nAño de castigo: "+model.getYearCast()+"\nEmpresa: "+model.getEmp());
            }else{
                ExtraCode.sendMessageError("Error: Cliente no encontrado, vuelva a intentarlo.");
            }
        }else{
            ExtraCode.sendMessageError("Error: Datos a buscar no ingresado, vuelva a intentarlo.");
        }
    }
    
    private void btnExamine(){
        JFileChooser jfc=new JFileChooser();
        if(jfc.showDialog(this, "Seleccione archivo (.xlsx)")==JFileChooser.APPROVE_OPTION){
            String path=jfc.getSelectedFile().getAbsolutePath();
            if(path.endsWith("xlsx")){
                this.jtfPathFile.setText(path);
            }
        }
    }
    
    private void btnDeleteAll(){
        int result=ExtraCode.sendMessageConfirm("¿Seguro que quiere eliminar toda la data de la base de datos?");
        if(result==0){
            boolean status=dao.delete("DELETE FROM customer_proposals");
            if(status==true){
                ExtraCode.sendMessageSuccessfully("Data eliminada con éxito.");
                ExtraCode.writeConsole(jtaConsole, "["+ExtraCode.getCurrentDateFormat("dd/MM/yyyy hh:mm:ss aa")+"] Data eliminada con éxito.");
            }else{
                ExtraCode.sendMessageError("Error: No se pudo eliminar la data, consulte con el creado del programa.");
            }
        }
    }
}
