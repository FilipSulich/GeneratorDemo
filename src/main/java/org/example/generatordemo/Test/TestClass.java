package org.example.generatordemo.Test;

import org.example.generatordemo.Customer.Customer;
import org.example.generatordemo.Handler.ExcelReader;

import java.util.List;

public class TestClass {
    public static void main(String[] args) {
        ExcelReader excelReader = new ExcelReader();
        List<Customer> list = excelReader.readExcel("/Users/filipsulich/Desktop/Test123.xlsx");
        excelReader.printCustomers(list);
    }

}
