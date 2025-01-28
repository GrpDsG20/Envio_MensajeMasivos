package model.ads_web_main;

import java.util.ArrayList;

public interface IAdsWebMainActivate{
    
    public boolean insert();
    public boolean updateNumOrder();
    public ArrayList<AdsWebMainActivateModel> select();
    public AdsWebMainActivateModel selectWithIdAdsMain();
}
