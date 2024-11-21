package fftproject; // 

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class LedgerWriter {

    private static final String DEFAULT_SHEET_NAME = "Transactions";

    public void writeToExcel(final String filePath, final List<Transaction> transactions) throws IOException {
        try (Workbook workbook = new XSSFWorkbook()) {
            final Sheet sheet = workbook.createSheet(DEFAULT_SHEET_NAME);

            // Create header row
            final Row header = sheet.createRow(0);
            header.createCell(0).setCellValue("Date");
            header.createCell(1).setCellValue("Description");
            header.createCell(2).setCellValue("Amount");
            header.createCell(3).setCellValue("BuyerId");
            header.createCell(4).setCellValue("Category");

            // Write data rows
            int rowIndex = 1;
            for (Transaction transaction : transactions) {
                final Row row = sheet.createRow(rowIndex++);

                final Cell dateCell = row.createCell(0);
                if (transaction.getDate() != null) {
                    dateCell.setCellValue(transaction.getDate().toString());
                }

                row.createCell(1).setCellValue(transaction.getDescription() != null ? transaction.getDescription() : "");
                row.createCell(2).setCellValue(transaction.getAmount());
                row.createCell(3).setCellValue(transaction.getBuyerId() != null ? transaction.getBuyerId() : "");
                row.createCell(4).setCellValue(transaction.getCategory() != null ? transaction.getCategory() : "");
            }

            try (FileOutputStream fos = new FileOutputStream(filePath)) {
                workbook.write(fos);
            }

        } catch (IOException e) {
            System.err.println("Error writing Excel file: " + e.getMessage());
            throw e;
        }
    }
}
