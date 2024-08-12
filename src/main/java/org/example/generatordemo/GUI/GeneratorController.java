package org.example.generatordemo.GUI;

import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.example.generatordemo.Handler.ExcelReader;

import java.io.File;
import java.util.*;

public class GeneratorController {

    @FXML
    public Button uploadButton;

    @FXML
    private TableView tableView;

    @FXML
    private void handleUpload() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Excel Files", "*.xlsx"));
        File file = fileChooser.showOpenDialog(new Stage());
        if (file != null) {
            loadExcel(file);
        } else {
            alert("No file selected").showAndWait();
        }
    }

    private void loadExcel(File file) {
        System.out.println(file.getAbsolutePath());
            ExcelReader excelReader = ExcelReader.getInstance(file.getAbsolutePath());
            try{
                Workbook workbook = excelReader.readExcel();
                loadExcelFile(workbook);
            } catch (Exception e) {
                alert("Error while reading the file").showAndWait();
            }
    }

    private void loadExcelFile(Workbook workbook) {
            Sheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = sheet.iterator();

            tableView.getItems().clear();
            tableView.getColumns().clear();

            if (rowIterator.hasNext()) {
                Row headerRow = rowIterator.next();

                for (int i = 0; i < headerRow.getPhysicalNumberOfCells(); i++) {
                    final int columnIndex = i;
                    TableColumn<Map<String, String>, String> column = new TableColumn<>(headerRow.getCell(i).getStringCellValue());

                    column.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().get(String.valueOf(columnIndex))));
                    tableView.getColumns().add(column);
                }

                while (rowIterator.hasNext()) {
                    Row row = rowIterator.next();
                    Map<String, String> dataRow = new HashMap<>();

                    for (int i = 0; i < row.getPhysicalNumberOfCells(); i++) {
                        Cell cell = row.getCell(i);
                        String cellValue = getCellValue(cell);
                        dataRow.put(String.valueOf(i), cellValue);
                    }

                    tableView.getItems().add(dataRow);
                }
            }
    }

    private String getCellValue(Cell cell) {
        if (cell == null) {
            return "";
        }
        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                return Double.toString(cell.getNumericCellValue());
            case BOOLEAN:
                return Boolean.toString(cell.getBooleanCellValue());
            case FORMULA:
                return cell.getCellFormula();
            case BLANK:
                return "";
            default:
                return "";
        }
    }

    private Alert alert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        return alert;
    }
}