package fftproject;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class LedgerWriter {
    // Writes a Ledger object to an Excel file
    public boolean writeTransaction(Ledger ledger, String filePath) {
        try (Workbook workbook = new XSSFWorkbook()) {
            // Create Balance Sheet
            Sheet balanceSheet = workbook.createSheet("Balance");
            Row balanceHeader = balanceSheet.createRow(0);
            balanceHeader.createCell(0).setCellValue("Month");
            balanceHeader.createCell(1).setCellValue("Total Transactions");

            // Create Monthly Sheets
            for (Transaction transaction : ledger.getTransactions()) {
                String month = transaction.getDate().split("-")[1]; // Extracting month
                Sheet monthSheet = workbook.getSheet(month);
                if (monthSheet == null) {
                    monthSheet = workbook.createSheet(month);
                    Row header = monthSheet.createRow(0);
                    header.createCell(0).setCellValue("Date");
                    header.createCell(1).setCellValue("Description");
                    header.createCell(2).setCellValue("Amount");
                    header.createCell(3).setCellValue("BuyerID");
                    header.createCell(4).setCellValue("Category");
                }

                int rowNum = monthSheet.getLastRowNum() + 1;
                Row row = monthSheet.createRow(rowNum);
                row.createCell(0).setCellValue(transaction.getDate());
                row.createCell(1).setCellValue(transaction.getDescription());
                row.createCell(2).setCellValue(transaction.getAmount());
                row.createCell(3).setCellValue(transaction.getBuyerId() != null ? transaction.getBuyerId() : "N/A");
                row.createCell(4).setCellValue(transaction.getCategory() != null ? transaction.getCategory() : "N/A");
            }

            // Write the workbook to the file
            try (FileOutputStream fos = new FileOutputStream(filePath)) {
                workbook.write(fos);
            }

            return true;

        } catch (IOException e) {
            System.err.println("Error writing file: " + e.getMessage());
            return false;
        }
    }
}


