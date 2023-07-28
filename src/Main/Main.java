package Main;

import Sel.*;
import Sel.DiscoverPage.SeleniumDiscoverPageTester;
import Sel.HomePage.SeleniumHomePageTester;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;

import Log.*;

public class Main {
	public static void main(String[] args) {
		setup();

		WebDriver driver = new ChromeDriver();

		SeleniumTester[] allTesters = {
			new SeleniumHomePageTester(driver),
			new SeleniumDiscoverPageTester(driver),
		};

		for (SeleniumTester tester : allTesters) tester.runAll();

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
