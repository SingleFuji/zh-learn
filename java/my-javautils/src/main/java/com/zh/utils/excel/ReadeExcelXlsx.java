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

import com.zh.xgd.SqlTool;
import com.zh.xgd.utils.CalacuateCVN;
import com.zh.xgd.utils.LRC;

public class ReadeExcelXlsx {

	private static final String BASE_PATH = "D:\\test\\";
	private static final String SOURCE_FILE = BASE_PATH + "test.xlsx";

	public static List<String> getCardNoList() throws IOException {
		String file_dir = SOURCE_FILE;
		Workbook book = null;
		book = getExcelWorkbook(file_dir);
		Sheet sheet = getSheetByNum(book, 0);

		int lastRowNum = sheet.getLastRowNum();

//		System.out.println("last number is " + lastRowNum);
		List<String> cardNoList = new ArrayList<String>();
		for (int i = 0; i <= lastRowNum; i++) {
			Row row = null;
			row = sheet.getRow(i);
			if (row != null) {
				// System.out.println("reading line is " + i);
				int lastCellNum = row.getLastCellNum();
				// System.out.println("lastCellNum is " + lastCellNum );
				Cell cell = null;

				for (int j = 0; j <= lastCellNum; j++) {
					cell = row.getCell(j);
					if (cell != null) {
						if(Cell.CELL_TYPE_STRING==cell.getCellType())
						{
							String cellValue = cell.getStringCellValue();
							System.out.println(cellValue);
						
						
//						String cellValue = cell.getStringCellValue();
						// System.out.println("cell value is \n" + cellValue);
//						System.out.println(cellValue);
						if(cellValue.matches("^[0-9]*$"))
						{
							cardNoList.add(cellValue);
//							System.out.println(cellValue);
						}
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

	/**
	 * 生成磁道信息
	 * 
	 * @param cardNoList
	 * @return
	 * @throws ServiceException
	 */
	public static List<String> createTrackInformation(List<String> cardNoList) {
		List<String> trackInfoLst = new ArrayList<String>();
		String stx = ";";
		// 字段分隔符,二三磁道用=
		String fs = "=";
		// 失效日期 YYMM
		String inValidDate4 = "0000";
		// 服务代码
		String sc = "501";
		// 结束标志
		String etx = "?";
		for (int i = 0; i < cardNoList.size(); i++) {
			// 主账号
			String pan = cardNoList.get(i);
			// 附加数据CVN编码,元数据来源于主账号拼接失效日期拼接服务代码
			String cvn = CalacuateCVN.getcalacuate(pan, inValidDate4, sc);
			StringBuffer trackInfo = new StringBuffer();
			trackInfo.append(stx).append(pan).append(fs).append(inValidDate4)
					.append(sc).append(cvn).append(etx);
			int lrc = LRC.getLRC(trackInfo.toString());
			trackInfoLst.add(trackInfo.toString() + lrc);
		}
		return trackInfoLst;
	}

	public static void main(String[] args) {
		setTrackInfo();
		
	}
	
	
	/**
	 * 读取excel生成二磁道信息
	 */
	public static void setTrackInfo()
	{

		try {
			List<String> cardNoList;

			cardNoList = getCardNoList();

			List<String> secondTrackList = createTrackInformation(cardNoList);
			System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++");
			System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++");
			System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++");
			System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++");
			System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++");
			System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++");
			for(String track : secondTrackList)
			{
				System.out.println(track);
				SqlTool.appendMethod(track+"\n");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
}