package utility;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class ReadData {

	
	public static String readProperty(String value) throws IOException
	{
		Properties	prop = new Properties();
		String path = System.getProperty("user.dir");  // Gets the project root path
		String filePath = path + "/src/main/java/config/config.properties";
		FileInputStream fis = new FileInputStream(filePath);
		prop.load(fis);
		return prop.getProperty(value);
	}
	
	public static String readExcel(int rowNum,int colNum) throws EncryptedDocumentException, IOException
	{
		FileInputStream fis = new FileInputStream("C:\\Users\\darek\\git\\Selenium-Java\\HybridFramework\\testdata\\data.xlsx");
		Workbook wk = WorkbookFactory.create(fis);
	    Sheet sheet = wk.getSheet("Sheet1");
	    String value = sheet.getRow(rowNum).getCell(colNum).getStringCellValue();
	    return value;
	}
	
	public static String[][] readExcel(String sheetName) throws EncryptedDocumentException, IOException
	{
		String data[][];
		FileInputStream fis = new FileInputStream("C:\\Users\\darek\\git\\Selenium-Java\\HybridFramework\\testdata\\data1.xlsx");
		Workbook wk = WorkbookFactory.create(fis);
	    Sheet sheet = wk.getSheet(sheetName);
	    int columns = sheet.getRow(1).getLastCellNum();
	    int rows = sheet.getLastRowNum();
	    System.out.println(rows+"-----"+columns);
	    data = new String[rows][columns];
	    System.out.println("data["+rows+"]["+columns+"]");
	    for(int i=1;i<=rows;i++)
	    { 
	    	
	    	
	    	for(int j=0;j<columns;j++)
	    	{
	    	    System.out.println("data["+i+"]["+j+"]");

	    		if(sheet.getRow(i).getCell(j)!=null)
	    	    data[i-1][j] = sheet.getRow(i).getCell(j).getStringCellValue();
	    		else
	    			data[i-1][j]="";

	    	}
	    }
	    wk.close();
	    return data;
	    
	}

}
