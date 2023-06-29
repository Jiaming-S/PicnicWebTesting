package Main;

import Selenium.*;
import Logger.*;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Main {
	public static void main(String[] args) {
		setup();

		WebDriver driver = new ChromeDriver();

		SeleniumTester[] allTesters = {
				new SeleniumPostTester(driver),
				new SeleniumCircleTester(driver),
				new SeleniumSearchBarTester(driver),
		};

		for (SeleniumTester tester : allTesters) {
			try {
				tester.runAll();
			} catch (Exception e) {
				e.printStackTrace();
				Logger.log("An exception has occured. Test unsuccessful.");
			}
		}

		Logger.log("Tests concluded");
		driver.quit();
	}

	private static void setup() {
		System.out.println("-----");
		Logger.log("Starting tests...");

		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/chromedriver_mac64/chromedriver");
	}
}
