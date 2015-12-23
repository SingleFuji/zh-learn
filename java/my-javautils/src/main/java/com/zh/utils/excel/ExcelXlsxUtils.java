package com.zh.utils.excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelXlsxUtils {

	private static final String BASE_PATH = "D:\\test\\";
	private static final String SOURCE_FILE = BASE_PATH + "test.xlsx";

	public static void readExcel() throws IOException {
		String file_dir = SOURCE_FILE;
		Workbook book = getExcelWorkbook(file_dir);
		Sheet sheet = getSheetByNum(book, 0);

		int firstRowNum = sheet.getFirstRowNum();
		int lastRowNum = sheet.getLastRowNum();
		for (int i = 0; i <= lastRowNum; i++) {
			Row row = null;
			row = sheet.getRow(i);
			if (row != null) {
				int firstCellNum = row.getFirstCellNum();
				int lastCellNum = row.getLastCellNum();
				Cell cell = null;
				for (int j = 0; j <= lastCellNum; j++) {
					cell = row.getCell(j);
					if (cell != null) {
						int cellType = cell.getCellType();
						System.out.print(cellType+":");
						if (Cell.CELL_TYPE_STRING == cellType) {
							String cellValue = cell.getStringCellValue();
							System.out.println(cellValue);
						}
						else if(Cell.CELL_TYPE_NUMERIC == cell.getCellType())
						{
							double cellValue = cell.getNumericCellValue();
							System.out.println(cellValue);
						}
					}
				}
				System.out.println("-------------");
			}
		}
	}

	/**
	 * 获取整个Excel
	 * 
	 * @param filePath
	 * @return
	 * @throws IOException
	 */
	public static Workbook getExcelWorkbook(String filePath) throws IOException {
		Workbook book = null;
		File file = null;
		FileInputStream fis = null;
		try {
			file = new File(filePath);
			if (!file.exists()) {
				throw new RuntimeException("文件不存在");
			} else {
				fis = new FileInputStream(file);
				book = WorkbookFactory.create(fis);
			}
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		} finally {
			if (fis != null) {
				fis.close();
			}
		}
		return book;
	}

	public static Sheet getSheetByNum(Workbook book, int number) {
		Sheet sheet = null;
		try {
			sheet = book.getSheetAt(number);
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
		return sheet;
	}

	public static void main(String[] args) {

		try {
			readExcel();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
