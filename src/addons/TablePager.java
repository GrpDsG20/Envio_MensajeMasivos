package addons;

import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.util.List;
import javax.swing.Box;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JToggleButton;
import javax.swing.SwingConstants;

public class TablePager<T> {
    
    private final JTable table;
    private final IDataProvider<T> dp;
    private final int [] arrayRowsAllowed;
    private int rowAllowed;
    private TableModeler<T> modeler;
    private int currentPage=1;
    private final int limitButtonsPager=9;
    
    private JComboBox<Integer> cbRowAllowed;
    private JPanel pagerButtons;

    public TablePager(JTable table, IDataProvider<T> dp, int[] arrayRowsAllowed, int rowAllowed) {
        this.table = table;
        this.dp = dp;
        this.arrayRowsAllowed = arrayRowsAllowed;
        this.rowAllowed = rowAllowed;
        this.init();
    }
    
    private void init(){
        this.initModeler();
        this.pager();
    }
    
    private void initModeler(){
        try {
            this.modeler=(TableModeler<T>)table.getModel();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void pager(){
        int start=(currentPage-1)*rowAllowed;
        int ending=start+rowAllowed;
        if(ending>dp.getTotalRowCount()){
            ending=dp.getTotalRowCount();
        }
        List<T> list=dp.getRows(start, ending);
        this.modeler.setList(list);
        this.modeler.fireTableDataChanged();
    }
    
    public void createListRowsAllowed(JPanel panel){
        this.pagerButtons=new JPanel(new GridLayout(1,limitButtonsPager,3,3));
        this.pagerButtons.removeAll();
        panel.add(pagerButtons);
        if(arrayRowsAllowed!=null){
            Integer array[]=new Integer[arrayRowsAllowed.length];
            for(int i=0;i<arrayRowsAllowed.length;i++){
                array[i]=arrayRowsAllowed[i];
            }
            this.cbRowAllowed=new JComboBox<>(array);
            this.cbRowAllowed.setFocusable(false);
            this.cbRowAllowed.addActionListener((ActionEvent e) -> {
                cbEvent(cbRowAllowed);
            });
            panel.add(Box.createHorizontalStrut(15));
            panel.add(new JLabel("Número de filas: "));
            panel.add(cbRowAllowed);
        }
    }
    
    public void cbEvent(JComboBox<Integer> jcb){
        int startRowPage=((currentPage-1)*rowAllowed)+1;
        this.rowAllowed=(Integer)jcb.getSelectedItem();
        this.currentPage=((startRowPage-1)/rowAllowed)+1;
        this.pager();
    }

    public JComboBox<Integer> getCbRowAllowed() {
        return cbRowAllowed;
    }

    public void setCbRowAllowed(JComboBox<Integer> cbRowAllowed) {
        this.cbRowAllowed = cbRowAllowed;
    }
    
    private void addButtonsPager(JPanel panel,int numPage){
        JToggleButton jtb=new JToggleButton(Integer.toString(numPage));
        jtb.setFocusPainted(false);
        jtb.setMargin(new Insets(1, 3, 1, 3));
        panel.add(jtb);
        if(numPage==currentPage){
            jtb.setSelected(true);
        }
        jtb.addActionListener((ActionEvent e) -> {
            currentPage=Integer.parseInt(e.getActionCommand());
            pager();
        });
    }
    
    private void addRangeButtonsPager(JPanel panel,int start,int ending){
        int init=start;
        for(start=init;start<=ending;start++){
            addButtonsPager(panel, start);
        }
    }
    
    private JLabel createElipses(){
        return new JLabel("...",SwingConstants.CENTER);
    }
    
    public void updateButtonsPager(){
        this.pagerButtons.removeAll();
        int totalRows=dp.getTotalRowCount();
        int pages=(int)Math.ceil((double)totalRows/rowAllowed);
        if(pages>limitButtonsPager){
            this.addButtonsPager(pagerButtons, 1);
            if(currentPage<=(limitButtonsPager+1)/2){
                this.addRangeButtonsPager(pagerButtons, 2, limitButtonsPager-2);
                this.pagerButtons.add(createElipses());
                this.addButtonsPager(pagerButtons, pages);
            }else if(currentPage>(pages-((limitButtonsPager+1)/2))){
                this.pagerButtons.add(createElipses());
                this.addRangeButtonsPager(pagerButtons, pages-limitButtonsPager+3, pages);
            }else{
                this.pagerButtons.add(createElipses());
                int start=currentPage -(limitButtonsPager-4)/2;
                int ending=start+limitButtonsPager-5;
                this.addRangeButtonsPager(pagerButtons, start, ending);
                this.addButtonsPager(pagerButtons, pages);
            }
        }else{
            this.addRangeButtonsPager(pagerButtons, 1, pages);
        }
        if(pagerButtons.getParent()!=null){
            this.pagerButtons.getParent().validate();
            this.pagerButtons.getParent().repaint();
        }
    }
}
