package Sel.HomePage;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import Log.*;
import Sel.*;

public class TestCaseSharePostLink extends TestCase{
  public TestCaseSharePostLink(WebDriver driver) {
		super(driver);
	}

  @Override
  public void runTest() throws TestCaseFailedException {
    try {
      testShareLink();
    } catch (Exception e) {
      throw new TestCaseFailedException("Share post link unsuccessful.\n" + e.getMessage() + "\n", e.getStackTrace());
    }
  }

  /**
   * Shares the link of the top-most post on the homepage.
   */
  public void testShareLink() {
    Logger.log("Testing share link on first post...");
    loadHomePage();

    WebElement firstPost = getNthPost(1);
    List<WebElement> allButtons = getPostButtons(firstPost);
    WebElement shareButton = allButtons.get(2);
    shareButton.click();

    WebElement shareLinkButton = shareButton.findElement(By.cssSelector(".dropdown .scrollable-wrapper > div:first-child"));
    shareLinkButton.click();

    Logger.log("Share post link successful: " + getClipboardText());
  }
}
