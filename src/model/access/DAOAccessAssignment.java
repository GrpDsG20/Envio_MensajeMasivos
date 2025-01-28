package model.access;

import mysql.ConnectionMySQL;
import java.sql.*;

public class DAOAccessAssignment extends ConnectionMySQL implements IAccessAssignment{
    
    private AccessAssignmentModel model;
    
    public DAOAccessAssignment(){
        super();
        this.model=new AccessAssignmentModel();
    }
    
    public DAOAccessAssignment(AccessAssignmentModel model){
        super();
        this.model=model;
    }
    
    public void setModel(AccessAssignmentModel model){
        this.model=model;
    }

    @Override
    public AccessAssignmentModel selectWithLogin() {
        String url="SELECT * FROM access_assignment WHERE idLogin="+model.getIdLogin();
        this.model=null;
        try (Statement statement=openConnection().createStatement();ResultSet rs=statement.executeQuery(url)){
            if(rs.next()){
                this.model=new AccessAssignmentModel(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getTimestamp(4), rs.getTimestamp(5));
            }
            rs.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            this.closeConnection();
        }
        return model;
    }
}
