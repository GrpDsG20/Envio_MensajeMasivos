package main;

import addons.ExtraCode;
import controller.LoginController;
import controller.MainController;
import controller.SetConfigController;
import file.FileClass;
import javax.swing.UIManager;
import model.access.AccessDetails;
import model.access.CurrentLoginModel;
import model.access.DAOCurrentLogin;
import model.login.LoginModel;

public class MainClass {
    
    public static void main(String[] args) throws Exception{
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        FileClass file=new FileClass();
        if(!file.isExistsConfigFile()){
            SetConfigController.getInstance();
        }else{
            if(file.getStatusRememberAccount()){
                LoginModel model=file.getRememberAccount();
                AccessDetails access=new AccessDetails(model).getAccessDetails();
                
                DAOCurrentLogin current_login=new DAOCurrentLogin(new CurrentLoginModel(0, access.getLogin().getId(), true, null, null));
                CurrentLoginModel aux_cl=current_login.selectWithIdLogin();
                if(aux_cl!=null){
                    if(!aux_cl.isStatus()){
                        aux_cl.setStatus(true);
                        aux_cl.setUpdateDate(ExtraCode.getCurrentDate());
                        current_login.setModel(aux_cl);
                        if(current_login.update()){
                            MainController.getInstance(access);
                        }else{
                            ExtraCode.sendMessageError("Error: Estamos teniendo problemas para actualizar el estado de conexión del usuario, intente más tarde.");
                        }
                    }else{
                        ExtraCode.sendMessageError("Error: Actualmente el usuario se encuentra conectado al sistema, vuelva a intentarlo más tarde.");
                        System.exit(0);
                    }
                }else{
                    current_login=new DAOCurrentLogin(new CurrentLoginModel(0, access.getLogin().getId(), true, ExtraCode.getCurrentDate(), null));
                    if(current_login.insert()){
                        MainController.getInstance(access);
                    }else{
                        ExtraCode.sendMessageError("Error: Estamos teniendo problemas para registrar el estado de conexión del usuario, intente más tarde.");
                    }
                }
            }else{
                LoginController.getInstance();
            }
        }
    }
}
