package com.util;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader {
	
	public static FileInputStream fis;
	public static XSSFWorkbook workbook;
	public static XSSFSheet sheet;
	public static XSSFRow row;
	public static XSSFCell cell;
	
	public ExcelReader(String path) throws IOException {
		fis=new FileInputStream(path);
		workbook=new XSSFWorkbook(fis);
		fis.close();
	}
	public int getRowCount(String sheetName){	
		sheet=workbook.getSheet(sheetName);			
		int row=sheet.getLastRowNum();
		return row;
	}
	public int getCellCount(String sheetName) {
		sheet=workbook.getSheet(sheetName);			
		row=sheet.getRow(0);
		int cell=row.getLastCellNum()-1;
		return cell;		
	}
	public String getCellData(int rows, int cols, String sheetName) {
		sheet=workbook.getSheet(sheetName);			
		row=sheet.getRow(rows);
		cell=row.getCell(cols);
		if(cell.getCellType()==CellType.STRING) {
			return cell.getStringCellValue();
		}else if(cell.getCellType()==CellType.NUMERIC) {
			return String.valueOf(cell.getNumericCellValue());
		}else {
			return "";
		}
	}

}
