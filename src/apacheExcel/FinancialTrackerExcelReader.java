import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class FinancialTrackerExcelReader {

    public static void main(String[] args) {
        String excelFilePath = "SampleInput.xlsx"; // Path to your input file

        try (FileInputStream fis = new FileInputStream(new File(excelFilePath));
             Workbook workbook = new XSSFWorkbook(fis)) {

            for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
                Sheet sheet = workbook.getSheetAt(i);
                String sheetName = sheet.getSheetName();
                System.out.println("Reading data from sheet: " + sheetName);

                // Read and process rows
                for (Row row : sheet) {
                    if (row.getRowNum() == 0) {
                        // Skip the header row
                        continue;
                    }
                    String date = getCellValueAsString(row.getCell(0));
                    String merchant = getCellValueAsString(row.getCell(1));
                    String debit = getCellValueAsString(row.getCell(2));
                    String credit = getCellValueAsString(row.getCell(3));
                    String balance = getCellValueAsString(row.getCell(4));

                    System.out.printf("Date: %s, Merchant: %s, Debit: %s, Credit: %s, Balance: %s%n",
                            date, merchant, debit, credit, balance);
                }
                System.out.println("Finished reading sheet: " + sheetName + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String getCellValueAsString(Cell cell) {
        if (cell == null) {
            return "";
        }
        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    return cell.getDateCellValue().toString();
                } else {
                    return String.valueOf(cell.getNumericCellValue());
                }
            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            case FORMULA:
                return cell.getCellFormula();
            default:
                return "";
        }
    }
}
