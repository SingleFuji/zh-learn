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

public class ExcelUtils {

	private static final String BASE_PATH = "D:\\test\\";
	private static final String SOURCE_FILE = BASE_PATH + "test.xlsx";

	public static List<String> getCardNoList() throws IOException {
		// fail("Not yet implemented");
		String file_dir = SOURCE_FILE;
		Workbook book = null;
		book = getExcelWorkbook(file_dir);
		Sheet sheet = getSheetByNum(book, 0);

		int lastRowNum = sheet.getLastRowNum();

		// System.out.println("last number is " + lastRowNum);
		List<String> cardNoList = new ArrayList<String>();
		for (int i = 0; i <= lastRowNum; i++) {
			Row row = null;
			row = sheet.getRow(i);
			if (row != null) {
				System.out.println("reading line is " + i);
				int lastCellNum = row.getLastCellNum();
				System.out.println("lastCellNum is " + lastCellNum);
				Cell cell = null;

				for (int j = 0; j <= lastCellNum; j++) {
					cell = row.getCell(j);
					if (cell != null) {
						if(Cell.CELL_TYPE_STRING==cell.getCellType())
						{
							String cellValue = cell.getStringCellValue();
							System.out.println(cellValue);
						}
						else
						{
							System.out.println("****");
						}

					}
				}
			}
		}
		return cardNoList;
	}

	public static Sheet getSheetByNum(Workbook book, int number) {
		Sheet sheet = null;
		try {
			sheet = book.getSheetAt(number);
			// if(sheet == null){
			// sheet = book.createSheet("Sheet"+number);
			// }
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
		return sheet;
	}

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

	public static void main(String[] args) {
		try {
			getCardNoList();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
