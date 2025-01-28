package controller.emails;

import addons.ExtraCode;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import model.access.AccessDetails;
import model.emails.EmailTemplateModel;
import model.emails.DAOEmailTemplate;
import view.emails.EmailTemplateView;

public class EmailTemplateController extends EmailTemplateView implements ActionListener{
    
    private static EmailTemplateController instance;
    private DAOEmailTemplate dao;
    private AccessDetails access;
    
    public EmailTemplateController(AccessDetails access)throws Exception{
        super();
        this.registerEvents();
        this.registerObjects(access);
    }
    
    public static EmailTemplateController getInstance(AccessDetails access){
        try {
            instance=new EmailTemplateController(access);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return instance;
    }
    
    private void registerEvents(){
        this.jbtCancelTemplate.addActionListener(this);
        this.jbtSaveTemplate.addActionListener(this);
        this.jbtUpdateTemplate.addActionListener(this);
        this.jbtDeleteTemplate.addActionListener(this);
        
        this.jtaTemplate.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e){
                String htmlCode = jtaTemplate.getText();
                jepVisualizeTemplate.setText(htmlCode);
            }
        });
        
        this.jcbTemplates.addItemListener((ItemEvent e) -> {
            if(jcbTemplates.getSelectedIndex()>0){
                String template=jcbTemplates.getSelectedItem().toString();
                loadDataTemplate(template);
                jpiBackground.requestFocus();
            }else{
                changeStatusButtons(true);
                jtfNameTemplate.setEditable(true);
                jtfNameTemplate.setText(null);
                jtfNameTemplate.requestFocus();
                jtfSubject.setText(null);
                jtaTemplate.setText(null);
            }
        });
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e){
                EmailMainController.getInstance(access);
            }
        });
    }
    
    private void registerObjects(AccessDetails access){
        this.access=access;
        this.dao=new DAOEmailTemplate();
        this.clearInputs();
        this.setVisible(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(jbtCancelTemplate==e.getSource()){
            this.btnCancelTemplate();
        }else if(jbtSaveTemplate==e.getSource()){
            this.btnSaveTemplate();
        }else if(jbtUpdateTemplate==e.getSource()){
            this.btnUpdateTemplate();
        }else if(jbtDeleteTemplate==e.getSource()){
            this.btnDeleteTemplate();
        }
    }
    
    private void btnDeleteTemplate(){
        String name=jtfNameTemplate.getText();
        if(ExtraCode.sendMessageConfirm("¿Seguro que quiere elimina la plantilla: "+name+"?")==0){
            EmailTemplateModel aux=dao.selectSpecificName(name);
            if(aux!=null){
                this.dao=new DAOEmailTemplate(aux);
                if(dao.delete()){
                    ExtraCode.sendMessageSuccessfully("Plantilla eliminada con éxito.");
                    this.clearInputs();
                    this.changeStatusButtons(true);
                }else{
                    ExtraCode.sendMessageError("Error: No se pudo eliminar la plantilla seleccionada, vuelva a intentarlo.");
                }
            }else{
                ExtraCode.sendMessageError("Error: Plantilla ya no existe, verifique su bases de datos o vuelva a intentarlo.");
            }
        }
    }
    
    private void btnUpdateTemplate(){
        String name=jtfNameTemplate.getText();
        String subject=jtfSubject.getText();
        String template=jtaTemplate.getText();
        if(!name.isEmpty()){
            if(!subject.isEmpty()){
                if(!template.isEmpty()){
                    EmailTemplateModel aux=dao.selectSpecificName(name);
                    if(aux!=null){
                        aux.setSuject(subject);
                        aux.setTemplate(template);
                        aux.setUpdateDate(ExtraCode.getCurrentDate());
                        this.dao=new DAOEmailTemplate(aux);
                        if(dao.update()){
                            ExtraCode.sendMessageSuccessfully("Plantilla actualizada con éxito.");
                            this.clearInputs();
                            this.changeStatusButtons(true);
                        }else{
                            ExtraCode.sendMessageError("Error: No se pudo actualizar la plantilla, verifique su base de datos y vuelva a intentarlo.");
                        }
                    }else{
                        ExtraCode.sendMessageError("Error: Plantilla ya no existe, verifique su bases de datos o vuelva a intentarlo.");
                    }
                }else{
                    ExtraCode.sendMessageError("Error: Estructura de la plantilla no ingresada, vuelva a intentarlo.");
                }
            }else{
                ExtraCode.sendMessageError("Error: Asunto del correo no ingresada, vuelva a intentarlo.");
            }
        }else{
            ExtraCode.sendMessageError("Error: Nombre de la plantilla no ingresado, vuelva a intentarlo.");
        }
    }
    
    private void btnSaveTemplate(){
        String name=jtfNameTemplate.getText();
        String subject=jtfSubject.getText();
        String template=jtaTemplate.getText();
        if(!name.isEmpty()){
            if(!subject.isEmpty()){
                if(!template.isEmpty()){
                    if(dao.selectSpecificName(name)==null){
                        EmailTemplateModel model=new EmailTemplateModel(0, name, subject, template, ExtraCode.getCurrentDate(), null);
                        this.dao=new DAOEmailTemplate(model);
                        if(dao.insert()){
                            ExtraCode.sendMessageSuccessfully("Plantilla registrada con éxito.");
                            this.clearInputs();
                        }else{
                            ExtraCode.sendMessageError("Error: No se pudo registrar la plantilla, verifique su base de datos y vuelva a intentarlo.");
                        }
                    }else{
                        ExtraCode.sendMessageError("Error: Plantilla ya registrada, vuelva a intentarlo.");
                    }
                }else{
                    ExtraCode.sendMessageError("Error: Estructura de la plantilla no ingresada, vuelva a intentarlo.");
                }
            }else{
                ExtraCode.sendMessageError("Error: Asunto del correo no ingresada, vuelva a intentarlo.");
            }
        }else{
            ExtraCode.sendMessageError("Error: Nombre de plantilla no ingresada, vuelva a intentarlo.");
        }
    }
    
    private void btnCancelTemplate(){
        this.jtfNameTemplate.setEditable(true);
        this.changeStatusButtons(true);
        this.clearInputs();
    }
    
    private void changeStatusButtons(boolean status){
        this.jbtSaveTemplate.setEnabled(status);
        this.jbtUpdateTemplate.setEnabled(!status);
        this.jbtCancelTemplate.setEnabled(!status);
        this.jbtDeleteTemplate.setEnabled(!status);
    }
    
    private void clearInputs(){
        this.jtfNameTemplate.requestFocus();
        this.jtfNameTemplate.setText(null);
        this.jtfSubject.setText(null);
        this.jtaTemplate.setText(null);
        this.jepVisualizeTemplate.setText(null);
        this.loadTemplates(dao.select());
    }
    
    private void loadTemplates(ArrayList<EmailTemplateModel> list){
        ArrayList<String> templates=new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            templates.add(list.get(i).getName());
        }
        ExtraCode.loadDataComboBox(jcbTemplates, templates);
    }
    
    private void loadDataTemplate(String template){
        this.jtfNameTemplate.setEditable(false);
        this.jtfNameTemplate.setText(template);
        this.jtfSubject.setText(dao.selectSpecificName(template).getSuject());
        this.jtaTemplate.setText(dao.selectSpecificName(template).getTemplate());
        this.changeStatusButtons(false);
    }
}
