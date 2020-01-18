import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

public class TestClass extends BaseClass {
	static boolean flag; 
public static void loadTestpage() throws IOException {
	BrowserLaunch();
	loadURL("https://www.shopdisney.com/");
	CaptureScreen("homepage");
}
public static void signIn() throws IOException, InterruptedException {
	Clickon(getWebelement("//button[@class='signin-or-signup__button']"));
	CaptureScreen("signinPage");
	driver.switchTo().frame("disneyid-iframe");
	passValue(getWebelement("//input[@type='email']"), getData(0, 0));
	passValue(getWebelement("//input[@type='password']"), getData(0, 1));
	Clickon(getWebelement("//button[@type='submit']"));
	Thread.sleep(20000);
	CaptureScreen("SiginSuccess");
	flag = false;
}
public static void searchItem(String item) throws IOException {
	if(flag == false) {
		driver.switchTo().parentFrame();
		flag = true;
	}
	Clickon(getWebelement("//button[@class='siteSearch__button']"));
	driver.switchTo().activeElement();
	passValue(getWebelement("//input[@class='form-control siteSearch__field']"), item);
	passKeys(getWebelement("//input[@class='form-control siteSearch__field']"), Keys.ENTER);
	CaptureScreen("Searched-"+item);
}
public static void addtoCart(String item, String qty) throws InterruptedException {
	Thread.sleep(5000);
	List<WebElement> items = getWebelements("//a[@class='quickview hidden-md-down button--outline']");
	for (WebElement x : items) {
		if(x.getAttribute("data-productname").equals(item)) {
//			System.out.println(x.getAttribute("data-productname"));
			x.findElement(By.xpath("./..")).click();
			break;
		}
	}
	int quantity = Integer.parseInt(qty);
	for(int i = 0; i<quantity-1;i++) {
		Clickon(getWebelement("//button[@aria-label='Increase Quantity']"));
		Thread.sleep(2000);
	}
	Clickon(getWebelement("//button[@type='submit']"));
}
public static void checkBag() throws InterruptedException {
	hover(getWebelement("//a[@title='View Cart']"));
	Thread.sleep(3000);
	Clickon(getWebelement("//a[@class='checkout-btn']"));
}
public static void outToExcel() throws IOException {
	List<String> vals = new ArrayList<String>();
	List<WebElement> bagItems = getWebelements("//div[@class='line-item-name']/a/span");
	for (WebElement x : bagItems) {
		vals.add(x.getText());
	}
	vals.add(getWebelement("//div[@class='cart-summary__value grand-total']").getText());
	writetoExcel(vals);
}

public static void main(String[] args) throws IOException, InterruptedException {
	loadTestpage();
	signIn();
	searchItem(getData(1,0));
	addtoCart(getData(1,1),getData(1,2));
	checkBag();
	outToExcel();
}
}
