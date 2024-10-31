package com.sidone;




import java.io.File;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class TestApachePOIMain {
	
	public static void main(String[] args) throws IOException {
			
		/////////////////////First We Write////////////////
		
		String sheetName = "MySheet";
		
		XSSFWorkbook workbook = new XSSFWorkbook();

		XSSFSheet sheet = workbook.createSheet(sheetName);
		
		Row row1 = sheet.createRow(0);
		Row row2 = sheet.createRow(1);
		
		Cell cell1 = row1.createCell(0);
		Cell cell2 = row1.createCell(1);
		Cell cell3 = row2.createCell(0);
		Cell cell4 = row2.createCell(1);
		
		cell1.setCellValue("Independent Value");
		cell2.setCellValue("Dependent Value");
		cell3.setCellValue(4);
		cell4.setCellValue(16);
		
		File myFile = new File("myFiles/MyTable.xlsx");
		
		try (FileOutputStream fileOut = new FileOutputStream(myFile);) {
			workbook.write(fileOut);
		}
		
		catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			workbook.close();
		}
		
		catch (IOException e) {
			e.printStackTrace();
		}
		
		/////////////////////Then We Read///////////////////
		
		String fileName = ".\\myFiles\\MyTable.xlsx";
		
		
		try (FileInputStream inputStream = new FileInputStream(fileName);) {
			workbook = new XSSFWorkbook(inputStream);
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		
		sheet = workbook.getSheetAt(0);
		
		Iterator<Row> iterator = sheet.iterator();
		
		while (iterator.hasNext()) {
			XSSFRow row = (XSSFRow) iterator.next();
			Iterator<Cell> cellIterator = row.cellIterator();
			while (cellIterator.hasNext()) {
				XSSFCell cell = (XSSFCell) cellIterator.next();
				switch (cell.getCellType()) {
					case STRING: System.out.print(cell.getStringCellValue()); break;
					case NUMERIC: System.out.print(cell.getNumericCellValue()); break;
					case BOOLEAN: System.out.print(cell.getBooleanCellValue()); break;
					default:
						break;
				}
				System.out.print("\t");
			}
			
			System.out.println();
		}
		
		try {
			workbook.close();
		}
		
		catch (IOException e) {
			e.printStackTrace();
		}

	}

}
