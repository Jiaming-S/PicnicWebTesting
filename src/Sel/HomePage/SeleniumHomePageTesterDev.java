package Sel.HomePage;

import org.openqa.selenium.*;

import Log.*;
import Sel.*;

public class SeleniumHomePageTesterDev extends SeleniumTester {
  public SeleniumHomePageTesterDev(WebDriver driver) {
    super(driver);
  }

  @Override
  public void runAll() {
    int numFailedTests = 0;
    TestCase allHomePageTests[] = {
      new TestCaseCreatePost(driver),
    };

    for (TestCase testCase : allHomePageTests) {
      try {
        testCase.runTest();
      } catch (TestCaseFailedException e) {
        Logger.log("Error running test case: " + e.getMessage());
        numFailedTests++;
      }
    }

    System.out.println("Home Page Testing (dev server) Concluded.");
    printResults(allHomePageTests.length, numFailedTests);
  }
}
