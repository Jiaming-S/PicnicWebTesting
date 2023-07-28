package Sel.DiscoverPage;

import org.openqa.selenium.WebDriver;

import Log.Logger;
import Sel.SeleniumTester;

public class SeleniumDiscoverPageTester extends SeleniumTester {
	public SeleniumDiscoverPageTester(WebDriver driver) {
		super(driver);
	}

	@Override
	public void runAll() {
		try {
			(new TestCaseDiscoverLink(driver)).runTest();
		} catch (Exception e) {
			Logger.log("Error running test case: " + e.getMessage());
		}
	}
}
