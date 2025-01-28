package model.proposals;

import javax.swing.JTextArea;

public interface IProposals {
    
    public CustomerModel select(String code_score);
    public boolean insert(String sql,String path,JTextArea txt);
    public boolean update(String sql);
    public boolean delete(String sql);
}
