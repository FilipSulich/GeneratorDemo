package org.example.generatordemo.Handler;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.example.generatordemo.Customer.Customer;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ExcelReader {

    public List<Customer> readExcel(String filePath) {
        try (FileInputStream fis = new FileInputStream(filePath); Workbook workbook = new XSSFWorkbook(fis)) {
            Sheet sheet = workbook.getSheetAt(0);
            return customerList(sheet);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Customer> customerList(Sheet sheet) {
        List<Customer> list = new ArrayList<>();
        for (Row row : sheet) {
            if(row.getRowNum() != 0) {
                String name = getCellValueAsString(row.getCell(0));
                String surname = getCellValueAsString(row.getCell(1));
                int age = (int) getCellValueAsNumeric(row.getCell(2));
                int nip = (int) getCellValueAsNumeric(row.getCell(3));
                double value = getCellValueAsNumeric(row.getCell(4));
                Customer customer = new Customer(name, surname, age, nip, value);
                list.add(customer);
            }

        }
        return list;
    }

    private String getCellValueAsString(Cell cell) {
        if (cell == null) {
            return "";
        }
        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                return String.valueOf(cell.getNumericCellValue());
            default:
                return "";
        }
    }

    private double getCellValueAsNumeric(Cell cell) {
        if (cell == null) {
            return 0;
        }
        switch (cell.getCellType()) {
            case NUMERIC:
                return cell.getNumericCellValue();
            case STRING:
                try {
                    return Double.parseDouble(cell.getStringCellValue());
                } catch (NumberFormatException e) {
                    return 0;
                }
            default:
                return 0;
        }
    }

    public void printCustomers(List<Customer> customers) {
        for (Customer customer : customers) {
            System.out.println(customer.getName() + " " + customer.getSurname() + " " + customer.getAge() + " " + customer.getNIP() + " " + customer.getValue());
        }
    }
}
