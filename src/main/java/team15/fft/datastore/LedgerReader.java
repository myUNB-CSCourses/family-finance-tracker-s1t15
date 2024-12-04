package fftproject;


import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LedgerReader {
    // Reads an Excel file and returns a Ledger object
    public Ledger readTransaction(String filePath) {
        Ledger ledger = new Ledger();

        try (FileInputStream fis = new FileInputStream(filePath);
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheetAt(0); // Assuming the first sheet contains the transactions

            for (Row row : sheet) {
                if (row.getRowNum() == 0) continue; // Skip header row

                // Extracting data from each cell
                String date = row.getCell(0).getStringCellValue();
                String description = row.getCell(1).getStringCellValue();
                double debit = row.getCell(2) != null ? row.getCell(2).getNumericCellValue() : 0.0;
                double credit = row.getCell(3) != null ? row.getCell(3).getNumericCellValue() : 0.0;
                double balance = row.getCell(4).getNumericCellValue();

                // Create a transaction and add it to the ledger
                Transaction transaction = new Transaction(date, description, debit - credit, null, null);
                ledger.addTransaction(transaction);
            }

        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }

        return ledger;
    }
}

