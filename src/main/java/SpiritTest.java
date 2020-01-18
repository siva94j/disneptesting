import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;

public class SpiritTest extends BaseClass {
public void LoadPage() throws IOException {
	BrowserLaunch();
	loadURL("https://www.spirit.com/");
	CaptureScreen("homepage");
}
public void selectTripType(String tripType) {
	List<WebElement> tripRadios = getWebelements("//div[@aria-labelledby='tripType']/app-input");
	if(tripType == "Round Trip") {
		tripRadios.get(0).click();
	}
	else if(tripType == "One Way") {
		tripRadios.get(1).click();
	}
	else {
		tripRadios.get(2).click();
	}
}
//have to merge origin and destination into single method
public void selectOrigin(String origin) {
	Clickon(getWebelement("//input[@id='flight-OriginStationCode']"));
	List<WebElement> originPlaces = getWebelements("//button[@class='btn btn-link pt-0 pb-0 font-size-dropdown h-unset text-left text-capitalize']");
	for (WebElement x : originPlaces) {
		if(x.getText().contains("("+origin+")")) {
			x.click();
			break;
		}
	}
}
public void selectDest(String destination){
	Clickon(getWebelement("//input[@name='destinationStationCode']"));
	List<WebElement> originPlaces = getWebelements("//button[@class='btn btn-link pt-0 pb-0 font-size-dropdown h-unset text-left text-capitalize']");
	for (WebElement x : originPlaces) {
		if(x.getText().contains("("+destination+")")) {
			x.click();
			break;
		}
	}
}
public void selectDate(String date) {
	String day = date.split("/")[0];
	String month = date.split("/")[1];
	String year = date.split("/")[2];
	Clickon(getWebelement("//input[@name='date']"));
	Clickon(getWebelement("//button[@class='current']"));
	
}
public static void main(String[] args) throws IOException{
	SpiritTest obj = new SpiritTest();
	obj.LoadPage();
	obj.selectTripType("One Way");
	obj.selectOrigin("PBI");
	obj.selectDest("ORD");
	obj.selectDate("12/02/2020");
}
}
