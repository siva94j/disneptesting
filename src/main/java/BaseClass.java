import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class BaseClass {
	static WebDriver driver;
public static void BrowserLaunch() {
	System.setProperty("webdriver.chrome.driver", "driver/chromedriver.exe");
	driver = new ChromeDriver();
	driver.manage().window().maximize();
}
public static void loadURL(String url) {
	driver.get(url);
}
public static String getTitle() {
	return driver.getTitle();
}
public static String getCurrenturl() {
	return driver.getCurrentUrl();
}
public static WebElement getWebelement(String xpath) {
	return driver.findElement(By.xpath(xpath));
}
public static List<WebElement> getWebelements(String xpath) {
	return driver.findElements(By.xpath(xpath));
}
public static void BrowserClose() {
	driver.close();
}
public static void passValue(WebElement ele, String value) {
	ele.sendKeys(value);
}
public static void passKeys(WebElement ele, Keys key) {
	ele.sendKeys(key);
}
public static void Clickon(WebElement ele) {
	ele.click();
}
public static String getData(int row, int cell) throws IOException
{
	File loc = new File("excelfiles/input.xlsx");
	FileInputStream stream = new FileInputStream(loc);
	Workbook w = new XSSFWorkbook(stream);
	Sheet s = w.getSheet("Sheet1");
	Row r = s.getRow(row);
	Cell c = r.getCell(cell);
	String value = null;
	int cellType = c.getCellType();
		if(cellType==1){
			value = c.getStringCellValue();
		}
		else if(cellType==0){
			if(DateUtil.isCellDateFormatted(c)){
			Date d = c.getDateCellValue();
			System.out.println(d);
			SimpleDateFormat sd = new SimpleDateFormat("MM-dd-yyyy");
			value = sd.format(d);
			}
			else{
			double numericValue = c.getNumericCellValue();
			long l = (long) numericValue;
			value = String.valueOf(l);
			}
		}
	w.close();
	return value;
}
public static void CaptureScreen(String filename) throws IOException {
	TakesScreenshot tk = (TakesScreenshot) driver;
	File src = tk.getScreenshotAs(OutputType.FILE);
	File dest = new File("screenshots/"+filename+".png");
	FileUtils.copyFile(src, dest);
}
//public static void main(String[] args) throws IOException {
//	BrowserLaunch();
//	loadURL("https://www.google.com/");
//	CaptureScreen("sample");
//}
}
