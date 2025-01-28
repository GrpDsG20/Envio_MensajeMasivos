package controller;

import addons.ExtraCode;
import file.FileClass;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.access.AccessDetails;
import model.access.CurrentLoginModel;
import model.access.DAOCurrentLogin;
import model.login.DAOLogin;
import model.login.LoginModel;
import view.LoginView;

public class LoginController extends LoginView implements ActionListener{
    
    private static LoginController instance;
    
    public LoginController()throws Exception{
        super();
        this.registerEvents();
        this.registerObjects();
    }
    
    public static LoginController getInstance(){
        try {
            instance=new LoginController();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return instance;
    }
    
    private void registerEvents(){
        this.jbtJoin.addActionListener(this);
        this.jckShowPassword.addActionListener(this);
        this.jckSaveUser.addActionListener(this);
    }
    
    private void registerObjects(){
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(jckShowPassword==e.getSource()){
            this.ckShowPassword();
        }else if(jbtJoin==e.getSource()){
            this.btnJoin();
        }
    }
    
    private void ckShowPassword(){
        if(jckShowPassword.isSelected()){
            this.jpfPassword.setEchoChar((char)0);
        }else{
            this.jpfPassword.setEchoChar('•');
        }
    }
    
    private void btnJoin(){
        String username=jtfUsername.getText();
        String password=new String(jpfPassword.getPassword());
        if(!username.isEmpty()){
            if(!password.isEmpty()){
                DAOLogin login=new DAOLogin(new LoginModel(0, 0, username, password, null, null));
                LoginModel aux_lm=login.selectWithUsername();
                if(aux_lm!=null){
                    if(aux_lm.getPassword().equals(password)){
                        if(jckSaveUser.isSelected()){
                            AccessDetails access=new AccessDetails(aux_lm).getAccessDetails();
                            FileClass fc=new FileClass();
                            if(fc.saveLoginModel(aux_lm,true)){
                                DAOCurrentLogin current_login=new DAOCurrentLogin(new CurrentLoginModel(0, access.getLogin().getId(), true, null, null));
                                CurrentLoginModel aux_cl=current_login.selectWithIdLogin();
                                if(aux_cl!=null){
                                    if(!aux_cl.isStatus()){
                                        aux_cl.setStatus(true);
                                        aux_cl.setUpdateDate(ExtraCode.getCurrentDate());
                                        current_login.setModel(aux_cl);
                                        if(current_login.update()){
                                            MainController.getInstance(access);
                                            this.dispose();
                                        }else{
                                            ExtraCode.sendMessageError("Error: Estamos teniendo problemas para actualizar el estado de conexión del usuario, intente más tarde.");
                                        }
                                    }else{
                                        ExtraCode.sendMessageError("Error: Actualmente el usuario se encuentra conectado al sistema, vuelva a intentarlo más tarde o contacte con soporte técnico.");
                                    }
                                }else{
                                    current_login=new DAOCurrentLogin(new CurrentLoginModel(0, access.getLogin().getId(), true, ExtraCode.getCurrentDate(), null));
                                    if(current_login.insert()){
                                        MainController.getInstance(access);
                                        this.dispose();
                                    }else{
                                        ExtraCode.sendMessageError("Error: Estamos teniendo problemas para registrar el estado de conexión del usuario, intente más tarde o contacte con soporte técnico.");
                                    }
                                }
                            }else{
                                ExtraCode.sendMessageError("Error: No se pudo iniciar sesión, debido a un problema interno, contacte con soporte técnico.");
                            }
                        }else{
                            AccessDetails access=new AccessDetails(aux_lm).getAccessDetails();
                            DAOCurrentLogin current_login=new DAOCurrentLogin(new CurrentLoginModel(0, access.getLogin().getId(), true, null, null));
                            CurrentLoginModel aux_cl=current_login.selectWithIdLogin();
                            if(aux_cl!=null){
                                if(!aux_cl.isStatus()){
                                    aux_cl.setStatus(true);
                                    aux_cl.setUpdateDate(ExtraCode.getCurrentDate());
                                    current_login.setModel(aux_cl);
                                    if(current_login.update()){
                                        MainController.getInstance(access);
                                        this.dispose();
                                    }else{
                                        ExtraCode.sendMessageError("Error: Estamos teniendo problemas para actualizar el estado de conexión del usuario, intente más tarde.");
                                    }
                                }else{
                                    ExtraCode.sendMessageError("Error: Actualmente el usuario se encuentra conectado al sistema, vuelva a intentarlo más tarde o contacte con soporte técnico.");
                                }
                            }else{
                                current_login=new DAOCurrentLogin(new CurrentLoginModel(0, access.getLogin().getId(), true, ExtraCode.getCurrentDate(), null));
                                if(current_login.insert()){
                                    MainController.getInstance(access);
                                    this.dispose();
                                }else{
                                    ExtraCode.sendMessageError("Error: Estamos teniendo problemas para registrar el estado de conexión del usuario, intente más tarde o contacte con soporte técnico.");
                                }
                            }
                        }
                    }else{
                        ExtraCode.sendMessageError("Error: Contraseña erronea.");
                    }
                }else{
                    ExtraCode.sendMessageError("Error: Usuario no registrado o no encontrado.");
                }
            }else{
                ExtraCode.sendMessageError("Error: Contraseña no ingresada.");
            }
        }else{
            ExtraCode.sendMessageError("Error: Usuario no ingresado.");
        }
    }
}
