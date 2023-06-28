package Main;

import Selenium.SeleniumTester;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Main {
	public static void main (String[] args) {
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/chromedriver_mac64/chromedriver");

		WebDriver driver = new ChromeDriver();
		
		try {
			SeleniumTester tester = new SeleniumTester(driver);

			tester.testShareLink();
			tester.testShareEmbed();
			tester.testShareProfile();
			tester.testShareCircle();
			
			// ...
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			driver.quit();
		}
	}
}
