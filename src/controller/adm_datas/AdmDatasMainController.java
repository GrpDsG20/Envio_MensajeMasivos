package controller.adm_datas;

import addons.ExtraCode;
import controller.MainController;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import model.access.AccessDetails;
import view.adm_datas.AdmDatasMainView;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.sql.*;
import mysql.ConnectionMySQL;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class AdmDatasMainController extends AdmDatasMainView implements ActionListener {

    private static AdmDatasMainController instance;
    private AccessDetails access;

    public AdmDatasMainController(AccessDetails access) throws Exception {
        super();
        this.registerEvents();
        this.registerObjects(access);
    }

    public static AdmDatasMainController getInstance(AccessDetails access) {
        try {
            instance = new AdmDatasMainController(access);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return instance;
    }

    private void registerEvents() {
        this.jbtGenerateAndSave.addActionListener(this);

        this.jcbSelectDatabase.addItemListener((ItemEvent e) -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                if (jcbSelectDatabase.getSelectedIndex() != 0) {
                    jtfNameDatabase.setEnabled(false);
                } else {
                    jtfNameDatabase.setEnabled(true);
                }
            }
        });

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                MainController.getInstance(access);
            }
        });
    }

    private void registerObjects(AccessDetails access) {
        this.setVisible(true);
        this.access = access;
        ExtraCode.writeComboBoxItems(ConnectionMySQL.getInstance().getAllDatabase(), jcbSelectDatabase);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (jbtGenerateAndSave == e.getSource()) {
            this.btnGenerateAndSave();
        }
    }

    private void btnGenerateAndSave() {
        String path = jtfPath.getText();
        String name_table = jtfNameTable.getText();
        if (jcbSelectDatabase.getSelectedIndex() != 0) {
            String name_db = jcbSelectDatabase.getSelectedItem().toString();
            if (!name_table.isEmpty()) {
                if (!name_db.isEmpty()) {
                    if (!ConnectionMySQL.getInstance().databaseExists(name_db)) {
                        if (ConnectionMySQL.getInstance().createDatabase(name_db)) {
                            File[] files = new File(path).listFiles();
                            for(File file : files){
                                System.out.println(file.getAbsolutePath());
                                try (Workbook workbook = WorkbookFactory.create(file)){
                                    Sheet sheet = workbook.getSheetAt(0);
                                    Connection con = new ConnectionMySQL().openConnection2(name_db);
                                    generateAndSaveDataQuery(name_table, con, sheet);
                                    workbook.close();
                                } catch (InvalidFormatException | IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        } else {
                            ExtraCode.sendMessageError("Error: No se pudo crear la base de datos.");
                        }
                    } else {
                        File[] files = new File(path).listFiles();
                        for(File file : files){
                            System.out.println(file.getAbsolutePath());
                            try (Workbook workbook = WorkbookFactory.create(file)){
                                Sheet sheet = workbook.getSheetAt(0);
                                Connection con = new ConnectionMySQL().openConnection2(name_db);
                                generateAndSaveDataQuery(name_table, con, sheet);
                                workbook.close();
                            } catch (InvalidFormatException | IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                } else {
                    ExtraCode.sendMessageError("Error: Nombre de base de datos no seleccionada/ingresada, vuelva a intentarlo.");
                }
            } else {
                ExtraCode.sendMessageError("Error: Nombre de tabla no ingresado, vuelva a intentarlo.");
            }
        } else {
            String name_db = jtfNameDatabase.getText();
            if (!name_table.isEmpty()) {
                if (!name_db.isEmpty()) {
                    if (!ConnectionMySQL.getInstance().databaseExists(name_db)) {
                        if (ConnectionMySQL.getInstance().createDatabase(name_db)) {
                            File[] files = new File(path).listFiles();
                            for(File file : files){
                                System.out.println(file.getAbsolutePath());
                                try (Workbook workbook = WorkbookFactory.create(file)){
                                    Sheet sheet = workbook.getSheetAt(0);
                                    Connection con = new ConnectionMySQL().openConnection2(name_db);
                                    generateAndSaveDataQuery(name_table, con, sheet);
                                    workbook.close();
                                } catch (InvalidFormatException | IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        } else {
                            ExtraCode.sendMessageError("Error: No se pudo crear la base de datos.");
                        }
                    } else {
                        File[] files = new File(path).listFiles();
                        for(File file : files){
                            System.out.println(file.getAbsolutePath());
                            try (Workbook workbook = WorkbookFactory.create(file)){
                                Sheet sheet = workbook.getSheetAt(0);
                                Connection con = new ConnectionMySQL().openConnection2(name_db);
                                generateAndSaveDataQuery(name_table, con, sheet);
                                workbook.close();
                            } catch (InvalidFormatException | IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                } else {
                    ExtraCode.sendMessageError("Error: Nombre de base de datos no seleccionada/ingresada, vuelva a intentarlo.");
                }
            } else {
                ExtraCode.sendMessageError("Error: Nombre de tabla no ingresado, vuelva a intentarlo.");
            }
        }
    }

    private void generateAndSaveDataQuery(String tableName, Connection con, Sheet sheet) {
        try {
            Row headerRow = sheet.getRow(0);
            String createTableQuery = generateCreateTableQuery(tableName, headerRow);
            createTable(con, createTableQuery);
            String insertModelQuery = generateInsertModelSQL(tableName, headerRow);
            Iterator<Row> rowIterator = sheet.rowIterator();

            int batchSize = 2000;
            int totalRows = sheet.getLastRowNum() + 1;
            int currentRow = 0;
            int count = 0;
            PreparedStatement ps = con.prepareStatement(insertModelQuery);
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                if (row.getRowNum() == 0) {
                    continue;
                }
                processRow(row, ps);
                ps.addBatch();
                if (++count % batchSize == 0) {
                    ps.executeBatch();
                    System.out.println("[" + ExtraCode.getCurrentDateFormat("dd/MM/yyyy hh:mm:ss aa") + "] Subiendo datos... " + (int) (100.0 * currentRow / totalRows) + "% completado");
                }
                currentRow++;
            }
            if (count % batchSize != 0) {
                ps.executeBatch();
                System.out.println("["+ExtraCode.getCurrentDateFormat("dd/MM/yyyy hh:mm:ss aa")+"] Subiendo datos... " + (int) (100.0 * currentRow / totalRows) + "% completado");
            }
        } catch (SQLException e) {
            try {
                sheet.getWorkbook().close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private void processRow(Row row, PreparedStatement preparedStatement) throws SQLException {
        int lastCellNum = row.getLastCellNum();
        for (int cellIndex = 0; cellIndex < lastCellNum; cellIndex++) {
            preparedStatement.setString(cellIndex + 1, ExtraCode.convertCellValueToString(row.getCell(cellIndex)));
        }
    }

    private String generateCreateTableQuery(String tableName, Row headerRow) {
        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("CREATE TABLE IF NOT EXISTS ").append(tableName).append(" (");

        Iterator<Cell> cellIterator = headerRow.cellIterator();
        while (cellIterator.hasNext()) {
            Cell cell = cellIterator.next();
            String columnName = cell.getStringCellValue();
            queryBuilder.append(columnName).append(" TEXT, ");
        }

        queryBuilder.setLength(queryBuilder.length() - 2);
        queryBuilder.append(")");

        return queryBuilder.toString();
    }

    private String generateInsertModelSQL(String tableName, Row headerRow) {
        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("INSERT INTO ").append(tableName).append(" (");

        Iterator<Cell> cellIterator = headerRow.cellIterator();
        int count = 0;
        while (cellIterator.hasNext()) {
            count++;
            Cell cell = cellIterator.next();
            String columnName = cell.getStringCellValue();
            queryBuilder.append(columnName).append(",");
        }

        queryBuilder.setLength(queryBuilder.length() - 1);
        queryBuilder.append(") VALUES(");

        for (int i = 0; i < count; i++) {
            queryBuilder.append("?,");
        }

        queryBuilder.setLength(queryBuilder.length() - 1);
        queryBuilder.append(")");

        return queryBuilder.toString();
    }

    private void createTable(Connection connection, String createTableQuery) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(createTableQuery)) {
            statement.executeUpdate();
        }
    }

    public int countOccurrences(String text, char character) {
        int count = 0;
        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) == character) {
                count++;
            }
        }
        return count;
    }
}
