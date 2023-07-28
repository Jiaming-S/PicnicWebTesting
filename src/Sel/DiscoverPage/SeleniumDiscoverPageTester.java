package Sel.DiscoverPage;

import org.openqa.selenium.WebDriver;

import Log.*;
import Sel.*;

public class SeleniumDiscoverPageTester extends SeleniumTester {
	public SeleniumDiscoverPageTester(WebDriver driver) {
		super(driver);
	}

	@Override
	public void runAll() {
		int numFailedTests = 0;
		TestCase allDiscoverPageTests[] = {
			new TestCaseDiscoverLink(driver),
		};

		for (TestCase testCase : allDiscoverPageTests) {
			try {
				testCase.runTest();
			} catch (TestCaseFailedException e) {
				Logger.log("Error running test case: " + e.getMessage());
				numFailedTests++;
			}
		}

		System.out.println("Discover Page Testing Concluded.");
		printResults(allDiscoverPageTests.length, numFailedTests);
	}
}
