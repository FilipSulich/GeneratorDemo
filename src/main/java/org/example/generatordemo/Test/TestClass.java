package org.example.generatordemo.Test;

import org.example.generatordemo.Customer.Customer;
import org.example.generatordemo.Handler.ExcelReader;

import java.util.List;

public class TestClass {
    public static void main(String[] args) {
        String filePath = "/Users/filipsulich/Desktop/Test123.xlsx";
        ExcelReader excelReader = ExcelReader.getInstance(filePath);

        List<Customer> list = excelReader.readExcel();
        excelReader.printCustomers(list);
    }

}
