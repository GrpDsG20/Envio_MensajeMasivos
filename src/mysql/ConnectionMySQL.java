package mysql;

import file.FileClass;
import java.sql.*;
import java.util.ArrayList;
import model.mysql.MySQL;

public class ConnectionMySQL {

    private Connection connection;
    private final MySQL model;
    private final FileClass file;
    private static ConnectionMySQL instance;

    public ConnectionMySQL() {
        this.file = new FileClass();
        this.model = file.getMySQL();
    }

    public static ConnectionMySQL getInstance() {
        return instance = new ConnectionMySQL();
    }

    public MySQL getModel() {
        return model;
    }

    public Connection openConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.connection = DriverManager.getConnection("jdbc:mysql://" + model.getIp() + ":" + model.getPort() + "/" + model.getDatabase() + "?autoReconnect=true&useSSL=false", model.getUsername(), model.getPassword());
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public Connection openConnection2(String db) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.connection = DriverManager.getConnection("jdbc:mysql://" + model.getIp() + ":" + model.getPort() + "/" + db + "?autoReconnect=true&useSSL=false", model.getUsername(), model.getPassword());
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public ArrayList<String> getAllDatabase() {
        ArrayList<String> list = new ArrayList<>();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.connection = DriverManager.getConnection("jdbc:mysql://" + model.getIp() + ":" + model.getPort() + "/", model.getUsername(), model.getPassword());
            DatabaseMetaData metaData = connection.getMetaData();
            ResultSet resultSet = metaData.getCatalogs();
            while (resultSet.next()) {
                String databaseName = resultSet.getString("TABLE_CAT");
                list.add(databaseName);
            }
            resultSet.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return list;
    }

    public ArrayList<String> getAllNameTables() {
        ArrayList<String> list = new ArrayList<>();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.connection = DriverManager.getConnection("jdbc:mysql://" + model.getIp() + ":" + model.getPort() + "/" + model.getDatabase() + "?autoReconnect=true&useSSL=false", model.getUsername(), model.getPassword());
            DatabaseMetaData metadata = connection.getMetaData();
            ResultSet resultSet = metadata.getTables(model.getDatabase(), null, null, new String[]{"TABLE"});
            while (resultSet.next()) {
                String tableName = resultSet.getString("TABLE_NAME");
                list.add(tableName);
            }
            resultSet.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }finally{
            this.closeConnection();
        }
        return list;
    }

    public boolean databaseExists(String database) {
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://" + model.getIp() + ":" + model.getPort() + "/", model.getUsername(), model.getPassword()); Statement stmt = conn.createStatement()) {
            String query = "SELECT SCHEMA_NAME FROM INFORMATION_SCHEMA.SCHEMATA WHERE SCHEMA_NAME = '" + database + "'";
            return stmt.executeQuery(query).next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean createDatabase(String database) {
        boolean status = false;
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://" + model.getIp() + ":" + model.getPort() + "/", model.getUsername(), model.getPassword()); Statement stmt = conn.createStatement()) {
            String query = "CREATE DATABASE IF NOT EXISTS " + database;
            int result = stmt.executeUpdate(query);
            if (result > 0) {
                status = true;
            }
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return status;
    }

    public boolean closeConnection() {
        boolean status = false;
        try {
            if (!connection.isClosed()) {
                this.connection.close();
            }
            status = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return status;
    }
}
