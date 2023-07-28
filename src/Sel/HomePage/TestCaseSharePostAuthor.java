package Sel.HomePage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import Log.*;
import Sel.*;

public class TestCaseSharePostAuthor extends TestCase{
  public TestCaseSharePostAuthor(WebDriver driver) {
		super(driver);
	}

  @Override
  public void runTest() throws TestCaseFailedException {
    try {
      testShareProfile();
    } catch (Exception e) {
      throw new TestCaseFailedException("Share post author unsuccessful.\n" + e.getMessage() + "\n", e.getStackTrace());
    }
  }

  /**
   * Shares the profile link of the author of the top-most post from the homepage.
   */
  public void testShareProfile() {
    Logger.log("Testing share post author...");
    loadHomePage();

    WebElement firstPost = getNthPost(1);
    WebElement firstPostAuthor = getPostAuthor(firstPost);
    firstPostAuthor.click();

    String profileURL = driver.getCurrentUrl();
    Logger.log("Share post author successful: " + profileURL);
  }
}