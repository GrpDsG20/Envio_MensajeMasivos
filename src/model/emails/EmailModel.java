package model.emails;

public class EmailModel {
    
    private String user;
    private String password;
    private String destiny;
    private String subject;
    private String message;
    private String file;

    public EmailModel(String user, String password, String destiny, String subject, String message, String file) {
        this.user = user;
        this.password = password;
        this.destiny = destiny;
        this.subject = subject;
        this.message = message;
        this.file = file;
    }

    public EmailModel() {
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDestiny() {
        return destiny;
    }

    public void setDestiny(String destiny) {
        this.destiny = destiny;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }
}
