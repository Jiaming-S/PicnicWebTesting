package Selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import Logger.Logger;

public class SeleniumCircleTester extends SeleniumTester{
    public SeleniumCircleTester(WebDriver driver) {
    super(driver);
  }

  @Override
  public void runAll() {
    testShareCircle();
  }

  /**
   * Shares the link of the top most circle.
   */
  public void testShareCircle() {
    Logger.log("Testing share first circle...");
    loadHomePage();

    WebElement firstCircle = getNthCircle(1);
    firstCircle.click();

    String circleURL = driver.getCurrentUrl();
    Logger.log("Share first circle successful: " + circleURL);
  }
}
