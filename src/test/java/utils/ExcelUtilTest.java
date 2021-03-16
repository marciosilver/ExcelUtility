package utils;

import java.io.IOException;

public class ExcelUtilTest {
	
	public static void main (String[] args) throws IOException{
		
		String excelPath = "./data/TestSheet.xlsx";
		String sheetName = "Plan1";
		ExcelUtils excel = new ExcelUtils(excelPath, sheetName);
		
		/*excel.getRowCount();
		excel.getCellData(1, 0);
		excel.getCellData(1, 1);
		excel.getCellData(1, 0);*/
	}

}
