package Main;

import Selenium.*;
import Logger.*;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;

public class Main {
	public static void main(String[] args) {
		setup();

		WebDriver driver = new ChromeDriver();

		SeleniumTester[] allTesters = {
				// new SeleniumPostTester(driver),
				// new SeleniumCircleTester(driver),
				// new SeleniumSearchBarTester(driver),
				new SeleniumDiscoverLinksTester(driver),
		};

		for (SeleniumTester tester : allTesters) {
			try {
				tester.runAll();
			} catch (Exception e) {
				e.printStackTrace();
				Logger.log("An exception has occured. Test unsuccessful.");
			}
		}

		Logger.log("*** Tests concluded ***");
		driver.quit();
	}

	private static void setup() {
		Logger.log("*** --------------- ***");
		Logger.log("Starting tests...");

		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/chromedriver_mac64/chromedriver");
		System.setProperty(ChromeDriverService.CHROME_DRIVER_SILENT_OUTPUT_PROPERTY, "true"); 
		java.util.logging.Logger.getLogger("org.openqa.selenium").setLevel(java.util.logging.Level.OFF);
	}
}
