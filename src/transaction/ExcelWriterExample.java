package transaction;

// Import specific Apache POI classes used for working with Excel
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

/**
 * This class demonstrates how to use Apache POI to create an Excel file
 * and write data, including various data types, to it.
 */
public class ExcelWriterExample {
    
    // Constant for the output file path and sheet name
    private static final String FILE_PATH = "transactions.xlsx";
    private static final String SHEET_NAME = "Transactions";

    /**
     * Main method to execute the Excel writing example.
     * This method initializes an instance of ExcelWriterExample and writes sample data.
     */
    public static void main(String[] args) {
        ExcelWriterExample writer = new ExcelWriterExample();
        writer.writeSampleTransactionData();
    }
    
    /**
     * Creates an Excel workbook and writes sample transaction data,
     * including a header row and a sample data row, to an Excel file.
     */
    private void writeSampleTransactionData() {
        // Using try-with-resources to ensure workbook is closed automatically
        try (XSSFWorkbook workbook = new XSSFWorkbook()) {
            // Create a new sheet in the workbook with the specified name
            XSSFSheet sheet = workbook.createSheet(SHEET_NAME);
            
            // Create the header row with column names
            createHeaderRow(sheet);
            
            // Add a sample row with transaction data
            createSampleDataRow(sheet);

            // Write the workbook data to an Excel file at the specified file path
            try (FileOutputStream outputStream = new FileOutputStream(FILE_PATH)) {
                workbook.write(outputStream);
            }

        } catch (IOException e) {
            // Print error message to standard error if an IOException occurs
            System.err.println("Error writing Excel file: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Creates a header row for the Excel sheet with standard transaction column names.
     * This helps identify the type of data in each column.
     *
     * @param sheet The Excel sheet where the header row is created.
     */
    private void createHeaderRow(XSSFSheet sheet) {
        // Create the first row in the sheet, which will serve as the header
        Row header = sheet.createRow(0);
        
        // Set each cell in the header row with the column names
        header.createCell(0).setCellValue("Date");         // Column for transaction date
        header.createCell(1).setCellValue("Description");  // Column for transaction description
        header.createCell(2).setCellValue("Amount");       // Column for transaction amount
        header.createCell(3).setCellValue("Balance");      // Column for current balance
        header.createCell(4).setCellValue("Verified");     // Column to mark if transaction was verified
    }

    /**
     * Creates a sample data row in the Excel sheet with different data types,
     * such as date, string, numeric, and boolean values. This demonstrates
     * how to handle multiple data types when writing to an Excel file.
     *
     * @param sheet The Excel sheet where the data row is created.
     */
    private void createSampleDataRow(XSSFSheet sheet) {
        // Create the second row in the sheet to add sample transaction data
        Row row = sheet.createRow(1);

        // Add the current date to the first cell in the row
        Cell dateCell = row.createCell(0);
        dateCell.setCellValue(new Date()); // Automatically formats as a date in Excel
        
        // Add a string description to the second cell
        row.createCell(1).setCellValue("Groceries");  // Represents the type of transaction
        
        // Add a numeric value for the transaction amount
        row.createCell(2).setCellValue(50.75);  // Example transaction amount
        
        // Add another numeric value for the current balance
        row.createCell(3).setCellValue(500.25); // Example balance after the transaction
        
        // Add a boolean value indicating if the transaction is verified
        row.createCell(4).setCellValue(true);   // Boolean values are shown as TRUE/FALSE in Excel
    }
}
