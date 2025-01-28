package controller.ads;

import addons.ExtraCode;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import model.ads_web_main.AdsWebMainActivateModel;
import model.ads_web_main.AdsWebMainModel;
import model.ads_web_main.DAOAdsWebMain;
import model.ads_web_main.DAOAdsWebMainActivate;
import view.ads.AdsWebMainActivateView;

public class AdsWebMainActivateController extends AdsWebMainActivateView implements ActionListener {
    
    private static AdsWebMainActivateController instance;
    private AdsWebMainModel awmm;
    
    public AdsWebMainActivateController(AdsWebMainModel awmm)throws Exception{
        super();
        this.registerEvents();
        this.registerObjects(awmm);
    }
    
    public static AdsWebMainActivateController getInstance(AdsWebMainModel awmm){
        try {
            instance=new AdsWebMainActivateController(awmm);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return instance;
    }
    
    private void registerEvents(){
        this.jckEdit.addActionListener(this);
        this.jbtEnable.addActionListener(this);
        this.jbtDisable.addActionListener(this);
    }
    
    private void registerObjects(AdsWebMainModel awmm){
        this.awmm=awmm;
        this.load();
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(jckEdit==e.getSource()){
            this.ckEdit();
        }else if(jbtEnable==e.getSource()){
            this.btnEnable();
        }else if(jbtDisable==e.getSource()){
            this.btnDisable();
        }
    }
    
    private void btnEnable(){
        int order=Integer.parseInt(String.valueOf(jsNumOrder.getValue()));
        ArrayList<AdsWebMainActivateModel> list=new DAOAdsWebMainActivate().select();
        if(list.isEmpty()){
            this.awmm.setStatus(true);
            this.awmm.setUpdateDate(ExtraCode.getCurrentDate());
            DAOAdsWebMain dao_awm=new DAOAdsWebMain(awmm);
            dao_awm.setModel(awmm);
            if(dao_awm.update()){
                AdsWebMainActivateModel aux_awmam=new AdsWebMainActivateModel(0, awmm.getId(), 0, null, null);
                DAOAdsWebMainActivate dao_awma=new DAOAdsWebMainActivate(aux_awmam);
                aux_awmam=dao_awma.selectWithIdAdsMain();
                if(aux_awmam==null){
                    aux_awmam=new AdsWebMainActivateModel(0, awmm.getId(), order, ExtraCode.getCurrentDate(), null);
                    dao_awma.setModel(aux_awmam);
                    if(dao_awma.insert()){
                        ExtraCode.sendMessageSuccessfully("Anuncio activado y posicionado.");
                    }else{
                        ExtraCode.sendMessageError("Error: No se pudo posicionar el anuncio, contacte con soporte técnico.");
                    }
                }else{
                    aux_awmam.setNumOrder(order);
                    aux_awmam.setUpdateDate(ExtraCode.getCurrentDate());
                    dao_awma.setModel(aux_awmam);
                    if(dao_awma.updateNumOrder()){
                        ExtraCode.sendMessageSuccessfully("Anuncio activado y posicionado.");
                    }else{
                        ExtraCode.sendMessageError("Error: No se pudo posicionar el anuncio, contacte con soporte técnico.");
                    }
                }
            }else{
                ExtraCode.sendMessageError("Error: No se pudo cambiar el estado del anuncio, contacte con soporte técnico.");
            }
        }else{
            for(int i=0;i<list.size();i++){
                AdsWebMainActivateModel aux=list.get(i);
                if(aux.getNumOrder()==order){
                    ExtraCode.sendMessageError("Error: Actualmente existe un anuncio en ese número de orden, intente con otro orden.");
                    break;
                }
                if(i==list.size()-1){
                    this.awmm.setStatus(true);
                    this.awmm.setUpdateDate(ExtraCode.getCurrentDate());
                    DAOAdsWebMain dao_awm=new DAOAdsWebMain(awmm);
                    if(dao_awm.update()){
                        AdsWebMainActivateModel aux_awmam=new AdsWebMainActivateModel(0, awmm.getId(), 0, null, null);
                        DAOAdsWebMainActivate dao_awma=new DAOAdsWebMainActivate(aux_awmam);
                        aux_awmam=dao_awma.selectWithIdAdsMain();
                        if(aux_awmam==null){
                            aux_awmam=new AdsWebMainActivateModel(0, awmm.getId(), order, ExtraCode.getCurrentDate(), null);
                            dao_awma.setModel(aux_awmam);
                            if(dao_awma.insert()){
                                ExtraCode.sendMessageSuccessfully("Anuncio activado y posicionado.");
                                break;
                            }else{
                                ExtraCode.sendMessageError("Error: No se pudo posicionar el anuncio, contacte con soporte técnico.");
                                break;
                            }
                        }else{
                            aux_awmam.setNumOrder(order);
                            aux_awmam.setUpdateDate(ExtraCode.getCurrentDate());
                            dao_awma.setModel(aux_awmam);
                            if(dao_awma.updateNumOrder()){
                                ExtraCode.sendMessageSuccessfully("Anuncio activado y posicionado.");
                                break;
                            }else{
                                ExtraCode.sendMessageError("Error: No se pudo posicionar el anuncio, contacte con soporte técnico.");
                                break;
                            }
                        }
                    }else{
                        ExtraCode.sendMessageError("Error: No se pudo cambiar el estado del anuncio, contacte con soporte técnico.");
                        break;
                    }
                }
            }
        }
    }
    
    private void btnDisable(){
        
    }
    
    private void ckEdit(){
        if(jckEdit.isSelected()){
            this.jsNumOrder.setEnabled(true);
        }else{
            this.jsNumOrder.setEnabled(false);
        }
    }
    
    private void load(){
        this.jtfIdAds.setText(String.valueOf(awmm.getId()));
        AdsWebMainActivateModel dao=new DAOAdsWebMainActivate(new AdsWebMainActivateModel(0, awmm.getId(), 0, null, null)).selectWithIdAdsMain();
        if(dao!=null){
            this.jsNumOrder.setValue(dao.getNumOrder());
            this.jlStatusAds.setForeground(new java.awt.Color(0, 153, 0));
            this.jlStatusAds.setText("Activado");
            this.jsNumOrder.setEnabled(false);
            this.jckEdit.setSelected(false);
            this.jbtDisable.setEnabled(true);
            this.jbtEnable.setEnabled(false);
        }else{
            this.jlStatusAds.setForeground(new java.awt.Color(255, 51, 51));
            this.jlStatusAds.setText("Desactivado");
            this.jsNumOrder.setEnabled(true);
            this.jckEdit.setSelected(true);
            this.jbtDisable.setEnabled(false);
            this.jbtEnable.setEnabled(true);
        }
    }
}
