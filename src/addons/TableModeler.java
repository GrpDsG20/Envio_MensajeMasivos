package addons;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public abstract class TableModeler<T> extends AbstractTableModel{

    private List<T> list=new ArrayList<>();
    
    public List<T> getList() {
        return list;
    }
    public void setList(List<T> list) {
        this.list = list;
    }
    
    @Override
    public int getRowCount(){
        return list.size();
    }
    
    @Override
    public Object getValueAt(int row,int column){
        T t=list.get(row);
        return getValueAt(t, column);
    }
    
    public abstract Object getValueAt(T t,int column);
    
    @Override
    public abstract String getColumnName(int column);
}
