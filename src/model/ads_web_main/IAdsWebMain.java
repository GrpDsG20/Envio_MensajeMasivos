package model.ads_web_main;

import java.util.ArrayList;

public interface IAdsWebMain {
    
    public boolean insert();
    public boolean update();
    public boolean delete();
    public ArrayList<AdsWebMainModel> select();
    public AdsWebMainModel selectId();
    public ArrayList<AdsWebMainModel> selectWithoutImg();
}
