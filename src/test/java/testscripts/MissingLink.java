package testscripts;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import constant.ConstantValue;

public class MissingLink {

	static WebDriver driver;

	public static void start(String url) {
		System.setProperty(ConstantValue.CHROMEDRIVERKEY, ConstantValue.CHROMEDRIVER);
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get(url);
	}

	@Test
	public void verifyMissingLink() throws IOException {
		start("file:///E://Apurva//Automation Aug22//MavenHybridDrivenFramework-Apurva/BrokenLink.html");
		List<WebElement> listOfLinks = driver.findElements(By.tagName("a"));

		for (WebElement e : listOfLinks) {
			if (e.getAttribute("href") == null || e.getAttribute("href").equals("")) {
				System.out.println(e.getText() + " is a missing link");
			} else {
				URL url = new URL(e.getAttribute("href"));

				HttpURLConnection httpConnection = (HttpURLConnection) url.openConnection();
				httpConnection.setConnectTimeout(2000);
				httpConnection.connect();

				if (httpConnection.getResponseCode() == 200) {
					System.out.println(e.getText() + "--" + httpConnection.getResponseCode());
				} else if (httpConnection.getResponseCode() > 399) {
					System.out.println(e.getText() + "--" + httpConnection.getResponseCode());
				}
			}
		}
	}
}
