package Sel.HomePage;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import Log.*;
import Main.Constants;
import Sel.*;

public class TestCaseSearchBar extends TestCase{
  public TestCaseSearchBar(WebDriver driver) {
		super(driver);
	}

  @Override
  public void runTest() throws TestCaseFailedException {
    try {
      testHomePageSearchBar();
    } catch (Exception e) {
      throw new TestCaseFailedException("Homepage search unsuccessful.\n" + e.getMessage() + "\n", e.getStackTrace());
    }
  }

  public void testHomePageSearchBar() {
    Logger.log("Testing homepage search bar...");
    final String[] WORD_LIST = { "test", "picnic" };
    loadHomePage();

    WebElement searchBar;
    for (String word : WORD_LIST) {
      Logger.log("Searching for: \"" + word + "\"...");

      searchBar = driver.findElement(By.cssSelector(".search-desktop-container input"));
      searchBar.sendKeys(word);
      waitUntilAppears(By.cssSelector(".result-container"), Constants.MAX_ELEMENT_LOAD_TIME);

      List<WebElement> circleResults = driver.findElements(By.cssSelector(".search-desktop-container .dropdown-item > .result-container .data-container"));
      WebElement randomCircle = circleResults.get((int)(Math.random() * circleResults.size()));
      testCircleDropdownLink(randomCircle);
      loadHomePage();

      searchBar = driver.findElement(By.cssSelector(".search-desktop-container input"));
      searchBar.sendKeys(word);
      waitUntilAppears(By.cssSelector(".result-container"), Constants.MAX_ELEMENT_LOAD_TIME);

      List<WebElement> userResults = driver.findElements(By.cssSelector(".search-desktop-container .dropdown-item > .info-container .data-container"));
      WebElement randomUser = userResults.get((int)(Math.random() * userResults.size()));
      testUserDropdownLink(randomUser);
      loadHomePage();

      Logger.log("Results for \"" + word + "\": " + circleResults.size() + " circles, " + userResults.size() + " users");
    }

    Logger.log("Homepage search successful.");
  }
  
  /**
	 * Tests a search dropdown that opens a link to a circle.
	 * @param linkButton WebElement containing the button to be tested.
	 */
	protected void testCircleDropdownLink (WebElement linkButton){
    String target = linkButton.findElement(By.cssSelector("span:first-child")).getText();
    Logger.log("Attempting to open random circle: \"" + target + "\"...");
		linkButton.click();

		waitUntilAppears(By.cssSelector("body"), Constants.MAX_PAGE_LOAD_TIME);

    Logger.log("Opened circle.");
	}
  /**
	 * Tests a search dropdown that opens a link to a user profile.
	 * @param linkButton WebElement containing the button to be tested. 
	 */
	protected void testUserDropdownLink (WebElement linkButton){
    String target = linkButton.findElement(By.cssSelector("span")).getText();
    Logger.log("Attempting to open random user profile: \"" + target + "\"...");
		linkButton.click();

		waitUntilAppears(By.cssSelector("body"), Constants.MAX_PAGE_LOAD_TIME);

    Logger.log("Opened user profile.");
	}
}