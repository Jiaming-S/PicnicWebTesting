package Sel.HomePage;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import Log.*;
import Sel.*;

public class TestCaseSharePostEmbed extends TestCase{
  public TestCaseSharePostEmbed(WebDriver driver) {
		super(driver);
	}

  @Override
  public void runTest() throws TestCaseFailedException {
    try {
      testShareEmbed();
    } catch (Exception e) {
      throw new TestCaseFailedException("Share embed unsuccessful.\n" + e.getMessage() + "\n");
    }
  }

  /**
   * Shares the embed of the top-most post on the homepage.
   */
  public void testShareEmbed() {
    Logger.log("Testing share embed on first post...");
    loadHomePage();

    WebElement firstPost = getNthPost(1);
    List<WebElement> allButtons = getPostButtons(firstPost);
    WebElement shareButton = allButtons.get(2);
    shareButton.click();

    WebElement shareLinkButton = shareButton.findElement(By.cssSelector(".dropdown .scrollable-wrapper > div:nth-child(2)"));
    shareLinkButton.click();

    Logger.log("Share embed successful: " + getClipboardText());
  }
}