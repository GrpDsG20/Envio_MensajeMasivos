package model.vouchers;

import java.util.ArrayList;

public interface IMappingData {
    
    public boolean insert();
    public ArrayList<MappingData> selectWithDNI(String dni);
    public ArrayList<MappingData> selectWithCodeScore(String code);
    public ArrayList<MappingData> selectWithCodeCustomer(String code);
    public MappingData selectWithDNIandCodeScore(String dni,String code1,String code2);
}
