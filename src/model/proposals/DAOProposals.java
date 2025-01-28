package model.proposals;

import addons.ExtraCode;
import java.io.File;
import java.io.FileInputStream;
import mysql.ConnectionMySQL;
import java.sql.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutionException;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class DAOProposals implements IProposals {

    private final ConnectionMySQL connection;
    private CustomerModel model;

    public DAOProposals() {
        this.connection = new ConnectionMySQL();
        this.model = new CustomerModel();
    }

    @Override
    public CustomerModel select(String code_score) {
        this.model = null;
        String url = "SELECT * FROM customer_proposals WHERE code_score='" + code_score + "'";
        try (Statement statement = connection.openConnection().createStatement(); ResultSet rs = statement.executeQuery(url)) {
            if (rs.next()) {
                this.model = new CustomerModel(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getDouble(5), rs.getDouble(6), rs.getDouble(7), rs.getInt(8), rs.getString(9));
            }
            rs.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.connection.closeConnection();
        }
        return model;
    }

    @Override
    public boolean insert(String sql, String path, JTextArea txt) {
        SwingWorker<Boolean, String> sw = new SwingWorker<Boolean, String>() {
            @Override
            protected Boolean doInBackground() throws Exception {
                FileInputStream fis = new FileInputStream(new File(path));
                XSSFWorkbook workbook = new XSSFWorkbook(fis);
                Sheet sheet = workbook.getSheetAt(0);
                Iterator<Row> rowIterator = sheet.rowIterator();
                int batchSize = 1000;
                int count = 0;
                int totalRows = sheet.getLastRowNum() + 1;
                int currentRow = 0;
                List<CustomerModel> dataRows = new ArrayList<>();
                while (rowIterator.hasNext()) {
                    Row row = rowIterator.next();
                    if (row.getRowNum() == 0) {
                        continue;
                    }
                    CustomerModel dataRow = new CustomerModel();
                    dataRow.setCodeScore(ExtraCode.convertCellValueToString(row.getCell(0)));
                    dataRow.setCodeBT(ExtraCode.convertCellValueToString(row.getCell(1)));
                    dataRow.setName(ExtraCode.convertCellValueToString(row.getCell(2)));
                    dataRow.setDni(ExtraCode.convertCellValueToString(row.getCell(3)));
                    dataRow.setPercentageDscto(row.getCell(4).getNumericCellValue());
                    dataRow.setCampaign(row.getCell(5).getNumericCellValue());
                    dataRow.setCapital(row.getCell(6).getNumericCellValue());
                    int year = (int) Math.round(row.getCell(7).getNumericCellValue());
                    dataRow.setYearCast(year);
                    dataRow.setEmp(ExtraCode.convertCellValueToString(row.getCell(8)));
                    dataRows.add(dataRow);

                    currentRow++;
                    double progress = 100.0 * currentRow / totalRows;
                    publish("Cargando datos... " + (int) progress + "% completado");
                }
                try (PreparedStatement ps = connection.openConnection().prepareStatement(sql)) {
                    for (CustomerModel dataRow : dataRows) {
                        ps.setString(1, dataRow.getCodeScore());
                        ps.setString(2, dataRow.getCodeBT());
                        ps.setString(3, dataRow.getName());
                        ps.setString(4, dataRow.getDni());
                        ps.setDouble(5, dataRow.getPercentageDscto());
                        ps.setDouble(6, dataRow.getCampaign());
                        ps.setDouble(7, dataRow.getCapital());
                        ps.setInt(8, dataRow.getYearCast());
                        ps.setString(9, dataRow.getEmp());
                        ps.addBatch();
                        if (count % batchSize == 0 || !rowIterator.hasNext()) {
                            ps.executeBatch();
                            double progress = 100.0 * currentRow / totalRows;
                            String msg = "[" + ExtraCode.getCurrentDateFormat("dd/MM/yyyy hh:mm:ss aa") + "] Subiendo datos... " + (int) progress + "% completado";
                            System.out.println(msg);
                            publish(msg);
                            ps.clearBatch();
                        }
                    }
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                    return false;
                } finally {
                    connection.closeConnection();
                }
                return true;
            }

            @Override
            protected void process(List<String> chunks) {
                for (String msg : chunks) {
                    txt.append(msg);
                }
            }

            @Override
            protected void done() {
                if (txt != null) {
                    txt.append("[" + ExtraCode.getCurrentDateFormat("dd/MM/yyyy hh:mm:ss aa") + "] Subida de datos completada\n");
                }
            }
        };
        sw.execute();
        try {
            return sw.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(String sql) {
        boolean status = false;
        try (PreparedStatement ps = connection.openConnection().prepareStatement(sql)) {
            int result = ps.executeUpdate();
            if (result > 0) {
                status = true;
            }
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.connection.closeConnection();
        }
        return status;
    }

    @Override
    public boolean delete(String sql) {
        boolean status = false;
        try (PreparedStatement ps = connection.openConnection().prepareStatement(sql)) {
            int result = ps.executeUpdate();
            if (result > 0) {
                status = true;
            }
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.connection.closeConnection();
        }
        return status;
    }
}
