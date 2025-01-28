package addons;

import java.util.List;

public interface IDataProvider<T> {
    
    public int getTotalRowCount();
    List<T> getRows(int startindex,int endindex);
}
