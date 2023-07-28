package Sel.HomePage;

import org.openqa.selenium.*;

import Log.*;
import Sel.*;

public class SeleniumHomePageTester extends SeleniumTester {
  public SeleniumHomePageTester(WebDriver driver) {
    super(driver);
  }

  @Override
  public void runAll() {
    TestCase allHomePageTests[] = {
      new TestCaseSearchBar(driver),
      new TestCaseShareCircle(driver),
      new TestCaseSharePostAuthor(driver),
      new TestCaseSharePostEmbed(driver),
      new TestCaseSharePostLink(driver),
    };

    for (TestCase testCase : allHomePageTests)
      try {
        testCase.runTest();
      } catch (TestCaseFailedException e) {
        Logger.log("Error running test case: " + e.getMessage());
      }
  }
}
