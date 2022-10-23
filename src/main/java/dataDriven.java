
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.commons.collections4.functors.WhileClosure;
import org.apache.http.impl.bootstrap.SSLServerSetupHandler;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import com.google.inject.Key;


public class dataDriven {
	public static WebDriver driver;

	
//	public static void readData() throws IOException{
//
//	
//	
//	
//	}
	public ArrayList<String> getData() throws IOException
	{
	//fileInputStream argument
	ArrayList<String> a=new ArrayList<String>();


	FileInputStream fis = new FileInputStream("C:\\Program Files\\en\\src\\main\\java\\Excel.xlsx");
	XSSFWorkbook workbook=new XSSFWorkbook(fis);
	// Iterate each row one by one
	int sheets = workbook.getNumberOfSheets();
	for (int i = 0; i<sheets; i++) {
		if(workbook.getSheetName(i).equalsIgnoreCase("Saturday")) {
			XSSFSheet sheet = workbook.getSheetAt(i);
			Iterator<Row> rows = sheet.iterator();
			Row firstRow = rows.next();
			Iterator<Cell> ce = firstRow.cellIterator();
			int k =0;
			int col = 0;
			while (ce.hasNext()) {
			Cell value	=ce.next();
			if(value.getStringCellValue().equalsIgnoreCase("ShortestOption")) {
				col = k;
			}
			k++;
				
			}
			System.out.println(col);
			
			rows.hasNext();
			Row r = rows.next();
			a.add(r.getCell(col).getStringCellValue());	
					
					
				
//			}
		
		
	
}


}
	return a;
}
	
//	public static void setup() {
//		
//	     
//
//		}
	
	
	public static void main(String[] args) throws IOException, InterruptedException {
		
		dataDriven ot= new dataDriven();
		ArrayList data= ot.getData();
		String valueString = (String) data.get(0);
		
		 System.setProperty("webdriver.chrome.driver", "D:\\seleniumJar\\chromedriver\\chromedriver.exe");
	     WebDriver driver = new ChromeDriver();
	     driver.manage().window().maximize();
	     driver.get("https://google.com");
	     Thread.sleep(3000);
	     driver.findElement(By.name("q")).click();
	     driver.findElement(By.name("q")).clear();
	     driver.findElement(By.name("q")).sendKeys(valueString);
	     Thread.sleep(3000);
	     driver.findElement(By.name("q")).sendKeys(Keys.ENTER);
	     
	     driver.close();
		
		
}

}
