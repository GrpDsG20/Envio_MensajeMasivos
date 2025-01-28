package model.mysql;

public interface IConnectionMySQL {
    
    public boolean isExistsDatabase();
    public boolean createDatabase();
    public boolean createTables();
}
