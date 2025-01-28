package controller.ads;

import controller.MainController;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import model.access.AccessDetails;
import view.ads.AdsMenuView;

public class AdsMenuController extends AdsMenuView implements ActionListener{
    
    private static AdsMenuController instance;
    private AccessDetails access;
    
    public AdsMenuController(AccessDetails access)throws Exception{
        super();
        this.registerEvents();
        this.registerObjects(access);
    }
    
    public static AdsMenuController getInstance(AccessDetails access){
        try {
            instance=new AdsMenuController(access);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return instance;
    }
    
    private void registerEvents(){
        this.jbtDesktop.addActionListener(this);
        this.jbtWeb.addActionListener(this);
        
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e){
                MainController.getInstance(access);
            }
        });
    }
    
    private void registerObjects(AccessDetails access){
        this.access=access;
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(jbtWeb==e.getSource()){
            this.btnWeb();
        }
    }
    
    private void btnWeb(){
        AdsWebController.getInstance(access);
        this.dispose();
    }
}
