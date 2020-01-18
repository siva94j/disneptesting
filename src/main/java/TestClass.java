import java.io.IOException;

import org.openqa.selenium.Keys;

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
public static void addtoCart() {
	
}
public static void main(String[] args) throws IOException, InterruptedException {
	loadTestpage();
	signIn();
	searchItem(getData(1,0));
	searchItem(getData(2,0));
	searchItem(getData(3,0));
}
}
