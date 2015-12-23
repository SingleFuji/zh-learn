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

public class ChangeStringExcelUtil {

	private static final String BASE_PATH = "D:\\test\\";
	private static final String SOURCE_FILE = BASE_PATH + "test.xlsx";
	private static final String SEL_SQL_HEAD = "SELECT * FROM	t_ca_businessfundacc WHERE c_baccount_id = (SELECT c_baccount_id FROM t_cf_c_businessaccount WHERE card_no = '";
	private static final String SEL_SQL_TAIL = "'	) and account_type='02';";
	private static final String UPDATE_SQL_HEAD = "UPDATE t_ca_businessfundacc SET balance = balance + ";
	private static final String UPDATE_SQL_MIDDLE = " WHERE account_type = '02' AND c_baccount_id = ( SELECT c_baccount_id FROM t_cf_c_businessaccount WHERE card_no = '";
	private static final String UPDATE_SQL_TAIL = "');";

	public static List<String> getCardNoList() throws IOException {
		String file_dir = SOURCE_FILE;
		Workbook book = null;
		book = getExcelWorkbook(file_dir);
		Sheet sheet = getSheetByNum(book, 0);
		int lastRowNum = sheet.getLastRowNum();
		System.out.println("last row number is " + lastRowNum);
		List<String> cardNoList = new ArrayList<String>();
		boolean isStopScanRow = false;
		for (int i = 0; i <= lastRowNum; i++) {
			if (!isStopScanRow) {
				Row row = null;
				row = sheet.getRow(i);
				if (row != null) {
					System.out.println("reading line is " + (i + 1));
					int lastCellNum = row.getLastCellNum();
					System.out.println("lastCellNum is " + lastCellNum);
					Cell cell = null;

					for (int j = 0; j <= lastCellNum; j++) {
						cell = row.getCell(j);

						if (cell != null) {
							int cellType = cell.getCellType();
							if (Cell.CELL_TYPE_STRING == cellType) {
								String cellValue = cell.getStringCellValue();
								System.out.println(cellValue);
								if (cellValue.matches("^[0-9]*$")) {
									cardNoList.add(cellValue);
								}
							} else if (Cell.CELL_TYPE_BLANK == cellType) {
								isStopScanRow = true;
								break;
							} else {
								System.out.println(cell.getCellType());
								System.out.println("第" + j + "列****");
							}

						}
					}
				} 
			}else {
				break;
			}
		}
		return cardNoList;
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
			System.out.println("*******************************************");
			List<String> cardList = getCardNoList();
			StringBuilder sb = new StringBuilder();
			for(String cardNo : cardList)
			{
				sb.append("'").append(cardNo).append("',");
			}
			System.out.println(sb.toString());
			System.out.println("*******************************************");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void dealAnjinCarSql(List<String> cardList)
	{
		for (int i = 0; i < cardList.size(); i+=2) {
			
			System.out.println(UPDATE_SQL_HEAD+cardList.get(i+1)+UPDATE_SQL_MIDDLE+cardList.get(i)+UPDATE_SQL_TAIL);

	}
	}
	
}
