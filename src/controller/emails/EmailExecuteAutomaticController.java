package controller.emails;

public class EmailExecuteAutomaticController extends Thread{
    
    private final Object monitor=new Object();
    private boolean status=false;
    private final EmailMainController emc;
    private volatile boolean running = true;
    
    public EmailExecuteAutomaticController(EmailMainController emc){
        super();
        this.emc=emc;
    }
    
    @Override
    @SuppressWarnings("deprecation")
    public void run(){
        while(running){
            this.checkForPaused();
            this.emc.sendMailAutomatic();
        }
    }
    
    private void checkForPaused(){
        synchronized(monitor){
            while(status==true){
                try {
                    this.monitor.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    
    public void pauseThread(){
        this.status=true;
    }
    
    public void resumeThread() {
        synchronized(monitor) {
            status = false;
            this.monitor.notify();
        }
    }
    
    public void cancel() {
        running = false;
    }
}
