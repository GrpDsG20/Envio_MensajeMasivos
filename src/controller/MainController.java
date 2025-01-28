package controller;

import addons.ExtraCode;
import controller.adm_datas.AdmDatasMainController;
import controller.ads.AdsMenuController;
import controller.emails.EmailMainController;
import controller.proposals.PMainController;
import controller.vouchers.VMainController;
import file.FileClass;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import model.access.AccessDetails;
import model.access.CurrentLoginModel;
import model.access.DAOCurrentLogin;
import view.MainView;

public class MainController extends MainView implements ActionListener{
    
    private static MainController instance;
    private AccessDetails access;
    
    public MainController(AccessDetails access)throws Exception{
        super();
        this.registerEvents();
        this.registerObjects(access);
    }
    
    public static MainController getInstance(AccessDetails access){
        try {
            instance=new MainController(access);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return instance;
    }
    
    private void registerEvents(){
        this.jbtEmail.addActionListener(this);
        this.jbtVouchers.addActionListener(this);
        this.jbtCloseSession.addActionListener(this);
        
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e){
                DAOCurrentLogin current_login=new DAOCurrentLogin(new CurrentLoginModel(0, access.getLogin().getId(), false, null, ExtraCode.getCurrentDate()));
                if(current_login.update()){
                    System.exit(0);
                }else{
                    ExtraCode.sendMessageError("Error: No se pudo cerrar sesión, comuniquese con soporte técnico.");
                }
            }
        });
    }
    
    private void registerObjects(AccessDetails access){
        this.access=access;
        if(access.getAccess_name().getName().equalsIgnoreCase("*")){
            this.setVisible(true);
        }else{
            this.jbtEmail.setVisible(false);
            this.setVisible(true);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(jbtCloseSession==e.getSource()){
            this.btnCloseSession();
        }else if(jbtEmail==e.getSource()){
            this.btnEmail();
        }else if(jbtVouchers==e.getSource()){
            this.btnVouchers();
        }
    }
    
    private void btnVouchers(){
        if(access.getAccess_name().getName().equalsIgnoreCase("*") || access.getAccess_name().getName().equalsIgnoreCase("vouchers")){
            VMainController.getInstance(access);
            this.dispose();
        }else{
            ExtraCode.sendMessageError("Error: Permisos insuficientes para acceder a esta opción.");
        }
    }
    
    private void btnEmail(){
        if(access.getAccess_name().getName().equalsIgnoreCase("*") || access.getAccess_name().getName().equalsIgnoreCase("emails")){
            EmailMainController.getInstance(access);
            this.dispose();
        }else{
            ExtraCode.sendMessageError("Error: Permisos insuficientes para acceder a esta opción.");
        }
    }
    
    private void btnCloseSession(){
        FileClass fc=new FileClass();
        if(fc.getStatusRememberAccount()){
           fc.saveLoginModel(null, false); 
        }
        DAOCurrentLogin current_login=new DAOCurrentLogin(new CurrentLoginModel(0, access.getLogin().getId(), false, null, ExtraCode.getCurrentDate()));
        if(current_login.update()){
            LoginController.getInstance();
            this.dispose();
        }else{
            ExtraCode.sendMessageError("Error: No se pudo cerrar sesión, comuniquese con soporte técnico.");
        }
    }
}
