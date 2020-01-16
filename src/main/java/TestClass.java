import java.io.IOException;

import org.openqa.selenium.Keys;

public class TestClass extends BaseClass {
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
}
public static void searchItems() throws IOException {
	driver.switchTo().parentFrame();
	Clickon(getWebelement("//button[@class='siteSearch__button']"));
	driver.switchTo().activeElement();
	passValue(getWebelement("//input[@class='form-control siteSearch__field']"), getData(1, 0));
	passKeys(getWebelement("//input[@class='form-control siteSearch__field']"), Keys.ENTER);
}
public static void main(String[] args) throws IOException, InterruptedException {
	loadTestpage();
	signIn();
	searchItems();
}
}
