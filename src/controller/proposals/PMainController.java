package controller.proposals;

import addons.ExtraCode;
import controller.MainController;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import model.access.AccessDetails;
import model.proposals.CustomerModel;
import model.proposals.DAOProposals;
import view.proposals.PMainVIew;

public class PMainController extends PMainVIew implements ActionListener{

    private static PMainController instance;
    private DAOProposals dao;
    private CustomerModel model;
    private AccessDetails access;
    
    public PMainController(AccessDetails access)throws Exception{
        super();
        this.registerEvents();
        this.registerObjects(access);
    }
    
    public static PMainController getInstance(AccessDetails access){
        try {
            instance=new PMainController(access);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return instance;
    }
    
    private void registerEvents(){
        this.jbtCopy.addActionListener(this);
        this.jbtSearch.addActionListener(this);
        this.jbtConcat.addActionListener(this);
        this.jbtClear.addActionListener(this);
        this.jbtManager.addActionListener(this);
        
        this.jtfAccountBT.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e){
                if(e.getKeyCode()==KeyEvent.VK_ENTER){
                    btnSearch();
                }
            }
        });
        
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e){
                MainController.getInstance(access);
            }
        });
    }
    
    private void registerObjects(AccessDetails access){
        this.access=access;
        this.dao=new DAOProposals();
        this.model=new CustomerModel();
        this.setVisible(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(jbtCopy==e.getSource()){
            this.btnCopy();
        }else if(jbtSearch==e.getSource()){
            this.btnSearch();
        }else if(jbtConcat==e.getSource()){
            this.btnConcat();
        }else if(jbtClear==e.getSource()){
            this.btnClear();
        }else if(jbtManager==e.getSource()){
            this.btnManager();
        }
    }
    
    private void btnCopy(){
        String text=jtaData.getText();
        StringSelection ss=new StringSelection(text);
        Clipboard cb=Toolkit.getDefaultToolkit().getSystemClipboard();
        cb.setContents(ss, null);
        ExtraCode.sendMessageSuccessfully("Texto Copiado");
    }
    
    private void btnClear(){
        this.jtfAccountBT.setText(null);
        this.jtfProposal.setText(null);
        this.jtaData.setText(null);
        this.jcbEntity.setEnabled(true);
        this.jcbEntity.setSelectedIndex(0);
    }
    
    private void btnConcat(){
        if(!jtfAccountBT.getText().isEmpty()){
            if(!jtaData.getText().isEmpty()){
                if(!jtfProposal.getText().isEmpty()){
                    if(!jtaData.getText().contains("Propuesta")){
                        if(jcbEntity.getSelectedIndex()!=0){
                            String entity=jcbEntity.getSelectedItem().toString();
                            if(entity.equalsIgnoreCase("ECL")){
                                this.jtaData.append("\n*Propuesta:* S/. "+jtfProposal.getText());
                            }else if(entity.equalsIgnoreCase("JZG")){
                                this.jtaData.append("\nPropuesta: S/. "+jtfProposal.getText());
                            }
                        }else{
                            ExtraCode.sendMessageError("Error: Empresa no seleccionada, vuelva a intentarlo.");
                        }
                    }else{
                        ExtraCode.sendMessageError("Error: La propuesta de pago ya ha sido agregada, si desea corregirlo, deberá dar click en nuevo y volver a buscar.");
                    }
                }else{
                    ExtraCode.sendMessageError("Error: Propuesta no ingresada, vuelva a intentarlo.");
                }
            }else{
                ExtraCode.sendMessageError("Error: No puede agregar una propuesta sin antes buscar al cliente.");
            }
        }else{
            ExtraCode.sendMessageError("Error: No puede agregar una propuesta sin antes buscar al cliente.");
        }
    }
    
    private void btnSearch(){
        String account=jtfAccountBT.getText();
        String entity=(jcbEntity.getSelectedIndex()!=0) ? jcbEntity.getSelectedItem().toString() : "";
        if(!account.isEmpty()){
            if(!entity.isEmpty()){
                this.jcbEntity.setEnabled(false);
                this.model=dao.select(account);
                if(model!=null){
                    if(entity.equalsIgnoreCase("ECL")){
                        if(model.getEmp().equalsIgnoreCase("ECL")){
                            this.jtaData.setText("*Estimad@, solicitó evaluación a propuesta de pago.*\n\n*Código BT:* "+model.getCodeBT()+"\n*Cliente:* "+model.getName()+"\n*DNI:* "+model.getDni()+"\n*Capital: * S/. "+ExtraCode.convertTextFormat(model.getCapital())+"\n*Porcentaje de Descuento:* "+ExtraCode.toPercentage(model.getPercentageDscto())+"\n*Monto Campaña:* S/. "+ExtraCode.convertTextFormat(model.getCampaign())+"\n*Año de Castigo:* "+model.getYearCast());
                        }else{
                            ExtraCode.sendMessageError("Error: Entidad no pertenece a lo seleccionado.");
                        }
                    }else if(entity.equalsIgnoreCase("JZG")){
                        if(model.getEmp().equalsIgnoreCase("JZG")){
                            String currentDate=ExtraCode.getCurrentDateFormat("aa");
                            if(currentDate.contains("a. m.")){
                                this.jtaData.setText("Buenos días.\nEnvío datos del cliente, para su aprobación.\n\nCuenta BT: "+model.getCodeBT()+"\nCliente: "+model.getName()+"\nDNI: "+model.getDni()+"\nCapital: S/. "+ExtraCode.convertTextFormat(model.getCapital())+"\nPorcentaje de Descuento: "+ExtraCode.toPercentage(model.getPercentageDscto())+"\nMonto Campaña: S/. "+ExtraCode.convertTextFormat(model.getCampaign())+"\nAño Castigo: "+model.getYearCast());
                            }else if(currentDate.contains("p. m.")){
                                currentDate=ExtraCode.getCurrentDateFormat("hh");
                                if(Integer.parseInt(currentDate)==12){
                                    this.jtaData.setText("Buenas tardes.\nEnvío datos del cliente, para su aprobación.\n\nCuenta BT: "+model.getCodeBT()+"\nCliente: "+model.getName()+"\nDNI: "+model.getDni()+"\nCapital: S/. "+ExtraCode.convertTextFormat(model.getCapital())+"\nPorcentaje de Descuento: "+ExtraCode.toPercentage(model.getPercentageDscto())+"\nMonto Campaña: S/. "+ExtraCode.convertTextFormat(model.getCampaign())+"\nAño Castigo: "+model.getYearCast());
                                }else{
                                    if((Integer.parseInt(currentDate)+12)>=19){
                                        this.jtaData.setText("Buenas noches.\nEnvío datos del cliente, para su aprobación.\n\nCuenta BT: "+model.getCodeBT()+"\nCliente: "+model.getName()+"\nDNI: "+model.getDni()+"\nCapital: S/. "+ExtraCode.convertTextFormat(model.getCapital())+"\nPorcentaje de Descuento: "+ExtraCode.toPercentage(model.getPercentageDscto())+"\nMonto Campaña: S/. "+ExtraCode.convertTextFormat(model.getCampaign())+"\nAño Castigo: "+model.getYearCast());
                                    }else{
                                        this.jtaData.setText("Buenas tardes.\nEnvío datos del cliente, para su aprobación.\n\nCuenta BT: "+model.getCodeBT()+"\nCliente: "+model.getName()+"\nDNI: "+model.getDni()+"\nCapital: S/. "+ExtraCode.convertTextFormat(model.getCapital())+"\nPorcentaje de Descuento: "+ExtraCode.toPercentage(model.getPercentageDscto())+"\nMonto Campaña: S/. "+ExtraCode.convertTextFormat(model.getCampaign())+"\nAño Castigo: "+model.getYearCast());
                                    }
                                }
                            }
                        }else{
                            ExtraCode.sendMessageError("Error: Entidad no pertenece a lo seleccionado.");
                        }
                    }else{
                        ExtraCode.sendMessageError("Error: Al cliente no se le disignó una empressa, contacte con el equipo de soporte.");
                    }
                }else{
                    ExtraCode.sendMessageError("Error: Cliente no encontrado, contacte con el equipo de soporte.");
                }
            }else{
                ExtraCode.sendMessageError("Error: Empresa no seleccionada, vuelva a intentarlo.");
            }
        }else{
            ExtraCode.sendMessageError("Error: Cuenta BT no ingresada, vuelva a intentarlo.");
        }
    }
    
    private void btnManager(){
        if(access.getAccess_name().getName().equalsIgnoreCase("*") || access.getAccess_name().getName().equalsIgnoreCase("proposals")){
            PManagerController.getInstance(access);
            this.dispose();
        }else{
            ExtraCode.sendMessageError("Error: Permisos insuficientes para acceder a esta opción.");
        }
    }
}
