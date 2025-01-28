package controller.emails;

import addons.ExtraCode;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;
import model.emails.EmailModel;

public class SendEmailController {
    
    private static SendEmailController instance;
    private final EmailMainController controllerEmailMain;
    
    public SendEmailController(EmailMainController aux){
        this.controllerEmailMain=aux;
    }
    
    public static SendEmailController getInstance(EmailMainController aux){
        return instance=new SendEmailController(aux);
    }
    
    public boolean sendMail(EmailModel mail,boolean status){
        boolean enviado = false;
        try{
            String host="smtp.gmail.com";
            Properties prop=System.getProperties();

            //prop.put("mail.smtp.starttls.enable","true");
            /*prop.put("mail.smtp.host",host);
            prop.put("mail.smtp.user",mail.getUser());
            prop.put("mail.smtp.password",mail.getPassword());
            prop.put("mail.smtp.port",587);
            prop.put("mail.smtp.auth","true");*/
            
            prop.put("mail.smtp.host", host);
            prop.put("mail.smtp.port", "587");
            prop.put("mail.smtp.auth", "true");
            prop.put("mail.smtp.starttls.enable", "true");

            Session session=Session.getDefaultInstance(prop,
                new javax.mail.Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(mail.getUser(), mail.getPassword());
                    }
                });
            MimeMessage message= new MimeMessage(session);

            message.setFrom(new InternetAddress(mail.getUser()));

            String[] toEmails = mail.getDestiny().split(" ;");
            if (toEmails != null && toEmails.length != 0) {
                InternetAddress[] internetAddressTO = new InternetAddress[toEmails.length];
                for (int i = 0; i < toEmails.length; i++) {
                    internetAddressTO[i] = new InternetAddress(toEmails[i]);
                }
                message.setRecipients(Message.RecipientType.TO, internetAddressTO);
            }
            message.setSubject(mail.getSubject());
            
            MimeMultipart multipart = new MimeMultipart();
            MimeBodyPart content = createContent(mail.getMessage());
            multipart.addBodyPart(content);
            /*Establecer adjunto
            String[] attachments = email.getAttachments();
            if (attachments != null && attachments.length != 0) {
                for (String filename: attachments) {
                    MimeBodyPart attachPart = createAttachment(filename);
                    multipart.addBodyPart(attachPart);
                }
            }*/
            if(status==true){
                MimeBodyPart attachPart = createAttachment(mail.getFile());
                multipart.addBodyPart(attachPart);
            }
            
            message.setContent(multipart);
            message.saveChanges();

            try (Transport transport = session.getTransport("smtp")) {
                transport.connect(host,mail.getUser(),mail.getPassword());
                
                transport.sendMessage(message, message.getAllRecipients());
            }
            enviado=true;
        }catch (Exception e){
            ExtraCode.writeConsole(controllerEmailMain.jtaConsole, "["+ExtraCode.getCurrentDateFormat("dd/MM/yyyy hh:mm:ss aa")+"] Error1: "+e.getMessage());
            e.printStackTrace();
        }
        return enviado;
    }
    
    
    public static MimeBodyPart createContent(String body) throws Exception {
        MimeBodyPart content = new MimeBodyPart();
        MimeMultipart multipart = new MimeMultipart();

        if (!body.isEmpty()) {
            MimeBodyPart htmlBodyPart = new MimeBodyPart();
            htmlBodyPart.setContent(body, "text/html;charset=UTF-8");
            multipart.addBodyPart(htmlBodyPart);
        }
        content.setContent(multipart);
        return content;
    }
    
    public static MimeBodyPart createAttachment(String filepath) throws Exception {
        MimeBodyPart attachPart = new MimeBodyPart();
        FileDataSource fileDataSource = new FileDataSource(filepath);
        attachPart.setDataHandler(new DataHandler(fileDataSource));
        attachPart.setFileName(MimeUtility.encodeText(fileDataSource.getName()));
        return attachPart;
    }
}
