package model.access;

import model.login.DAOLogin;
import model.login.LoginModel;

public class AccessDetails {
    
    private LoginModel login;
    private AccessAssignmentModel access_assignment;
    private AccessNameModel access_name;
    
    public AccessDetails(LoginModel model) {
        this.login=model;
    }

    public AccessDetails(LoginModel login, AccessAssignmentModel access_assignment, AccessNameModel access_name) {
        this.login = login;
        this.access_assignment = access_assignment;
        this.access_name = access_name;
    }

    public AccessDetails() {
    }

    public LoginModel getLogin() {
        return login;
    }

    public void setLogin(LoginModel login) {
        this.login = login;
    }

    public AccessAssignmentModel getAccess_assignment() {
        return access_assignment;
    }

    public void setAccess_assignment(AccessAssignmentModel access_assignment) {
        this.access_assignment = access_assignment;
    }

    public AccessNameModel getAccess_name() {
        return access_name;
    }

    public void setAccess_name(AccessNameModel access_name) {
        this.access_name = access_name;
    }
    
    public AccessDetails getAccessDetails(){
        DAOLogin dao_login=new DAOLogin(login);
        LoginModel aux_lm=dao_login.selectWithUsername();
        AccessAssignmentModel aux_aam=new DAOAccessAssignment(new AccessAssignmentModel(0, aux_lm.getId(), 0, null, null)).selectWithLogin();
        AccessNameModel aux_an=new DAOAccessName(new AccessNameModel(aux_aam.getIdAccess(), null, null, null, null)).selectWithId();
        return new AccessDetails(aux_lm, aux_aam, aux_an);
    }
}
