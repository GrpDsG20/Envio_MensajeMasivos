package model.mysql;

import addons.ExtraCode;
import java.sql.*;
import mysql.ConnectionMySQL;

public class DAOMySQL extends ConnectionMySQL implements IConnectionMySQL {

    private Connection connection;

    public DAOMySQL() {
        super();
    }

    @Override
    public boolean isExistsDatabase() {
        boolean status = false;
        try {
            this.connection = DriverManager.getConnection("jdbc:mysql://" + getModel().getIp() + ":" + getModel().getPort() + "/", getModel().getUsername(), getModel().getPassword());
            DatabaseMetaData dmd = connection.getMetaData();
            ResultSet rs = dmd.getCatalogs();
            while (rs.next()) {
                String dbName = rs.getString("TABLE_CAT");
                if (dbName.equalsIgnoreCase(getModel().getDatabase())) {
                    status = true;
                    break;
                }
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                this.connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return status;
    }

    @Override
    public boolean createDatabase() {
        boolean status = false;
        try {
            this.connection = DriverManager.getConnection("jdbc:mysql://" + getModel().getIp() + ":" + getModel().getPort() + "/", getModel().getUsername(), getModel().getPassword());
            Statement statement = connection.createStatement();
            String url = "CREATE DATABASE IF NOT EXISTS " + getModel().getDatabase();
            int result = statement.executeUpdate(url);
            if (result > 0) {
                status = true;
            }
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                this.connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return status;
    }

    @Override
    public boolean createTables() {
        boolean status = false;
        this.connection = openConnection();
        try (Statement stmt = connection.createStatement()) {
            this.connection.setAutoCommit(false);
            String createRanksTableSql = "CREATE TABLE IF NOT EXISTS ranks("
                    + "id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,"
                    + "name TEXT NOT NULL,"
                    + "description TEXT DEFAULT NULL,"
                    + "register_date TIMESTAMP NOT NULL,"
                    + "update_date TIMESTAMP DEFAULT NULL"
                    + ")";
            int result = stmt.executeUpdate(createRanksTableSql);
            if (result >= 0) {
                String createLoginTableSql = "CREATE TABLE IF NOT EXISTS login("
                        + "id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,"
                        + "idRank INT NOT NULL,"
                        + "username TEXT NOT NULL,"
                        + "password TEXT NOT NULL,"
                        + "register_date TIMESTAMP NOT NULL,"
                        + "update_date TIMESTAMP DEFAULT NULL,"
                        + "FOREIGN KEY (idRank) REFERENCES ranks(id)"
                        + ")";
                result = stmt.executeUpdate(createLoginTableSql);
                if (result >= 0) {
                    String createPersonalTableSql = "CREATE TABLE IF NOT EXISTS personal("
                            + "id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,"
                            + "idRank INT NOT NULL,"
                            + "idLogin INT NOT NULL,"
                            + "name TEXT NOT NULL,"
                            + "lastname TEXT NOT NULL,"
                            + "dni TEXT NOT NULL,"
                            + "telcel TEXT NOT NULL,"
                            + "email TEXT NOT NULL,"
                            + "description TEXT DEFAULT NULL,"
                            + "photo LONGBLOB DEFAULT NULL,"
                            + "register_date TIMESTAMP NOT NULL,"
                            + "update_date TIMESTAMP DEFAULT NULL,"
                            + "FOREIGN KEY (idRank) REFERENCES ranks(id),"
                            + "FOREIGN KEY (idLogin) REFERENCES login(id)"
                            + ")";
                    result = stmt.executeUpdate(createPersonalTableSql);
                    if (result >= 0) {
                        String createAccessNameTableSql = "CREATE TABLE IF NOT EXISTS access_name("
                                + "id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,"
                                + "name TEXT NOT NULL,"
                                + "description TEXT DEFAULT NULL,"
                                + "register_date TIMESTAMP NOT NULL,"
                                + "update_date TIMESTAMP DEFAULT NULL"
                                + ")";
                        result = stmt.executeUpdate(createAccessNameTableSql);
                        if (result >= 0) {
                            String createAccessAssignmentTableSql = "CREATE TABLE IF NOT EXISTS access_assignment("
                                    + "id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,"
                                    + "idLogin INT NOT NULL,"
                                    + "idAccess INT NOT NULL,"
                                    + "register_date TIMESTAMP NOT NULL,"
                                    + "update_date TIMESTAMP DEFAULT NULL,"
                                    + "FOREIGN KEY (idLogin) REFERENCES login(id),"
                                    + "FOREIGN KEY (idAccess) REFERENCES access_name(id)"
                                    + ")";
                            result = stmt.executeUpdate(createAccessAssignmentTableSql);
                            if (result >= 0) {
                                String createCurrentLogin = "CREATE TABLE IF NOT EXISTS current_login("
                                        + "id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,"
                                        + "idLogin INT NOT NULL,"
                                        + "status INT NOT NULL,"
                                        + "register_date TIMESTAMP NOT NULL,"
                                        + "update_date TIMESTAMP DEFAULT NULL,"
                                        + "FOREIGN KEY (idLogin) REFERENCES login(id)"
                                        + ")";
                                result = stmt.executeUpdate(createCurrentLogin);
                                if (result >= 0) {
                                    String createTemplateEmail = "CREATE TABLE IF NOT EXISTS email_templates("
                                            + "id INT PRIMARY KEY AUTO_INCREMENT NOT NULL,"
                                            + "name TEXT NOT NULL,"
                                            + "subject TEXT NOT NULL,"
                                            + "template TEXT NOT NULL,"
                                            + "register_date TIMESTAMP NOT NULL,"
                                            + "update_date TIMESTAMP DEFAULT NULL"
                                            + ")";
                                    result = stmt.executeUpdate(createTemplateEmail);
                                    if (result >= 0) {
                                        String creatEmailTestAccount = "CREATE TABLE IF NOT EXISTS email_test_account("
                                                + "id INT PRIMARY KEY AUTO_INCREMENT NOT NULL,"
                                                + "account TEXT NOT NULL,"
                                                + "register_date TIMESTAMP NOT NULL,"
                                                + "update_date TIMESTAMP DEFAULT NULL"
                                                + ")";
                                        result = stmt.executeUpdate(creatEmailTestAccount);
                                        if (result >= 0) {
                                            String createVouchers = "CREATE TABLE IF NOT EXISTS vouchers("
                                                    + "id INT PRIMARY KEY AUTO_INCREMENT NOT NULL,"
                                                    + "code TEXT NOT NULL,"
                                                    + "customer TEXT NOT NULL,"
                                                    + "image LONGBLOB NOT NULL,"
                                                    + "register_date TIMESTAMP NOT NULL"
                                                    + ")";
                                            result = stmt.executeUpdate(createVouchers);
                                            if (result >= 0) {
                                                String createDataEmail = "CREATE TABLE IF NOT EXISTS data_email("
                                                        + "id INT PRIMARY KEY AUTO_INCREMENT NOT NULL,"
                                                        + "dni TEXT,"
                                                        + "codigo_cliente TEXT NOT NULL,"
                                                        + "correo TEXT NOT NULL,"
                                                        + "cliente TEXT NOT NULL,"
                                                        + "monto_campaña TEXT,"
                                                        + "monto_total TEXT,"
                                                        + "entidad TEXT,"
                                                        + "apoderado TEXT,"
                                                        + "fecha_deuda TEXT"
                                                        + ")";
                                                result = stmt.executeUpdate(createDataEmail);
                                                if (result >= 0) {
                                                    status = true;
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            stmt.close();
            this.connection.close();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        }
        return status;
    }

    public boolean registerAdmin() {
        boolean status = false;
        try {
            this.connection = openConnection();
            this.connection.setAutoCommit(false);
            String url = "INSERT INTO ranks(name,description,register_date) VALUES(?,?,?)";
            PreparedStatement ps = connection.prepareStatement(url, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, "administrador");
            ps.setString(2, "Rango generado automáticamente para uso administrativo del programa.");
            ps.setTimestamp(3, new Timestamp(ExtraCode.getCurrentDate().getTime()));
            int result = ps.executeUpdate();
            if (result > 0) {
                ResultSet rs = ps.getGeneratedKeys();
                int idRank = -1;
                if (rs.next()) {
                    idRank = rs.getInt(1);
                }
                url = "INSERT INTO login(idRank,username,password,register_date) VALUES(?,?,?,?)";
                ps = connection.prepareStatement(url, Statement.RETURN_GENERATED_KEYS);
                ps.setInt(1, idRank);
                ps.setString(2, "admin");
                ps.setString(3, "admin");
                ps.setTimestamp(4, new Timestamp(ExtraCode.getCurrentDate().getTime()));
                result = ps.executeUpdate();
                if (result > 0) {
                    rs = ps.getGeneratedKeys();
                    int idLogin = -1;
                    if (rs.next()) {
                        idLogin = rs.getInt(1);
                    }
                    url = "INSERT INTO access_name(name,description,register_date) VALUES(?,?,?)";
                    ps = connection.prepareStatement(url, Statement.RETURN_GENERATED_KEYS);
                    ps.setString(1, "*");
                    ps.setString(2, "Acceso total a todas las funciones del sistema.");
                    ps.setTimestamp(3, new Timestamp(ExtraCode.getCurrentDate().getTime()));
                    result = ps.executeUpdate();
                    if (result > 0) {
                        rs = ps.getGeneratedKeys();
                        int idAccess = -1;
                        if (rs.next()) {
                            idAccess = rs.getInt(1);
                        }
                        url = "INSERT INTO access_assignment(idLogin,idAccess,register_date) VALUES(?,?,?)";
                        ps = connection.prepareStatement(url);
                        ps.setInt(1, idLogin);
                        ps.setInt(2, idAccess);
                        ps.setTimestamp(3, new Timestamp(ExtraCode.getCurrentDate().getTime()));
                        result = ps.executeUpdate();
                        if (result > 0) {
                            url = "INSERT INTO access_name(name,description,register_date) VALUES(?,?,?)";
                            ps = connection.prepareStatement(url);
                            ps.setString(1, "ads");
                            ps.setString(2, "Acceso total a todas las funciones del sistema de anuncios (web/escritorio).");
                            ps.setTimestamp(3, new Timestamp(ExtraCode.getCurrentDate().getTime()));
                            result = ps.executeUpdate();
                            if (result > 0) {
                                url = "INSERT INTO access_name(name,description,register_date) VALUES(?,?,?)";
                                ps = connection.prepareStatement(url);
                                ps.setString(1, "emails");
                                ps.setString(2, "Acceso total a todas las funciones del sistema de correos.");
                                ps.setTimestamp(3, new Timestamp(ExtraCode.getCurrentDate().getTime()));
                                result = ps.executeUpdate();
                                if (result > 0) {
                                    url = "INSERT INTO access_name(name,description,register_date) VALUES(?,?,?)";
                                    ps = connection.prepareStatement(url);
                                    ps.setString(1, "fingering_test");
                                    ps.setString(2, "Acceso total a todas las funciones administrativas del sistema de digitación.");
                                    ps.setTimestamp(3, new Timestamp(ExtraCode.getCurrentDate().getTime()));
                                    result = ps.executeUpdate();
                                    if (result > 0) {
                                        url = "INSERT INTO access_name(name,description,register_date) VALUES(?,?,?)";
                                        ps = connection.prepareStatement(url);
                                        ps.setString(1, "proposals");
                                        ps.setString(2, "Acceso total a todas las funciones administrativas del sistema de propuestas.");
                                        ps.setTimestamp(3, new Timestamp(ExtraCode.getCurrentDate().getTime()));
                                        result = ps.executeUpdate();
                                        if (result > 0) {
                                            url = "INSERT INTO access_name(name,description,register_date) VALUES(?,?,?)";
                                            ps = connection.prepareStatement(url);
                                            ps.setString(1, "vouchers");
                                            ps.setString(2, "Acceso total a todas las funciones adminisratias del sistema de vauchers.");
                                            ps.setTimestamp(3, new Timestamp(ExtraCode.getCurrentDate().getTime()));
                                            result = ps.executeUpdate();
                                            if (result > 0) {
                                                url = "INSERT INTO access_name(name,description,register_date) VALUES(?,?,?)";
                                                ps = connection.prepareStatement(url);
                                                ps.setString(1, "validate_emails");
                                                ps.setString(2, "Acceso total a todas las funciones del sistema de validación de correos.");
                                                ps.setTimestamp(3, new Timestamp(ExtraCode.getCurrentDate().getTime()));
                                                result = ps.executeUpdate();
                                                if (result > 0) {
                                                    url = "INSERT INTO access_name(name,description,register_date) VALUES(?,?,?)";
                                                    ps = connection.prepareStatement(url);
                                                    ps.setString(1, "admin_datas");
                                                    ps.setString(2, "Acceso total a todas las funciones del sistema de administración de datas.");
                                                    ps.setTimestamp(3, new Timestamp(ExtraCode.getCurrentDate().getTime()));
                                                    result = ps.executeUpdate();
                                                    if (result > 0) {
                                                        url = "INSERT INTO access_name(name,description,register_date) VALUES(?,?,?)";
                                                        ps = connection.prepareStatement(url);
                                                        ps.setString(1, "users");
                                                        ps.setString(2, "Acceso total a todas las funciones del sistema de usuarios");
                                                        ps.setTimestamp(3, new Timestamp(ExtraCode.getCurrentDate().getTime()));
                                                        result = ps.executeUpdate();
                                                        if (result > 0) {
                                                            url = "INSERT INTO access_name(name,description,register_date) VALUES(?,?,?)";
                                                            ps = connection.prepareStatement(url);
                                                            ps.setString(1, "schedule_send");
                                                            ps.setString(2, "Acceso total a todas las funciones administratias del sistema de cronograma de envio.");
                                                            ps.setTimestamp(3, new Timestamp(ExtraCode.getCurrentDate().getTime()));
                                                            result = ps.executeUpdate();
                                                            if (result > 0) {
                                                                status = true;
                                                                this.connection.commit();
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        } catch (SQLException e) {
            try {
                if (connection != null) {
                    connection.rollback();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            try {
                this.closeConnection();
                if (!connection.isClosed()) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return status;
    }
}
