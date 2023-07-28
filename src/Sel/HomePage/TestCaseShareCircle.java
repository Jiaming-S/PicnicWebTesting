package Sel.HomePage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import Log.*;
import Sel.*;

public class TestCaseShareCircle extends TestCase{
  public TestCaseShareCircle(WebDriver driver) {
		super(driver);
	}

  @Override
  public void runTest() throws TestCaseFailedException {
    try {
      testShareCircle();
    } catch (Exception e) {
      throw new TestCaseFailedException("Share first circle unsuccessful.\n" + e.getMessage() + "\n");
    }
  }

  /**
   * Shares the link of the top most circle.
   */
  public void testShareCircle() throws TestCaseFailedException {
    Logger.log("Testing share first circle...");
    loadHomePage();

    WebElement firstCircle = getNthCircle(1);
    firstCircle.click();

    String circleURL = driver.getCurrentUrl();
    Logger.log("Share first circle successful: " + circleURL);
  }  
}