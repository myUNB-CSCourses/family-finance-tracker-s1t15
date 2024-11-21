package fftproject; 

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class LedgerReader {

    public List<Transaction> readFromExcel(final String filePath) throws IOException {
        final List<Transaction> transactions = new ArrayList<>();

        try (FileInputStream fis = new FileInputStream(filePath);
             Workbook workbook = new XSSFWorkbook(fis)) {

            final Sheet sheet = workbook.getSheetAt(0); 
            final int firstRow = 1; 
            final int lastRow = sheet.getLastRowNum();

            for (int i = firstRow; i <= lastRow; i++) {
                final Row row = sheet.getRow(i);

                if (row != null) {
                    final Date date = row.getCell(0).getDateCellValue();
                    final String description = row.getCell(1).getStringCellValue();
                    final double amount = row.getCell(2).getNumericCellValue();
                    final String buyerId = row.getCell(3).getStringCellValue();
                    final String category = row.getCell(4).getStringCellValue();

                    transactions.add(new Transaction(date, description, amount, buyerId, category));
                }
            }

        } catch (IOException e) {
            System.err.println("Error reading Excel file: " + e.getMessage());
            throw e;
        }

        return transactions;
    }
}
