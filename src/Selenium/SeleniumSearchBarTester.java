package Selenium;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import Logger.Logger;

public class SeleniumSearchBarTester extends SeleniumTester {
  private final String[] WORD_LIST = { "test", "picnic" };

  public SeleniumSearchBarTester(WebDriver driver) {
    super(driver);
  }

  @Override
  public void runAll() {
    this.testHomePageSearchBar();
    // this.testDiscoverSearchBar();
  }

  public void testHomePageSearchBar() {
    Logger.log("Testing homepage search bar...");
    loadHomePage();

    WebElement searchBar;
    for (String word : WORD_LIST) {
      Logger.log("Searching for: \"" + word + "\"...");

      searchBar = driver.findElement(By.cssSelector(".search-desktop-container input"));
      searchBar.sendKeys(word);
      waitUntilAppears(By.cssSelector(".result-container"), 10);

      List<WebElement> circleResults = driver.findElements(By.cssSelector(".search-desktop-container .dropdown-item > .result-container .data-container"));
      WebElement randomCircle = circleResults.get((int)(Math.random() * circleResults.size()));
      testCircleDropdownLink(randomCircle);
      loadHomePage();

      searchBar = driver.findElement(By.cssSelector(".search-desktop-container input"));
      searchBar.sendKeys(word);
      waitUntilAppears(By.cssSelector(".result-container"), 10);

      List<WebElement> userResults = driver.findElements(By.cssSelector(".search-desktop-container .dropdown-item > .info-container .data-container"));
      WebElement randomUser = userResults.get((int)(Math.random() * userResults.size()));
      testUserDropdownLink(randomUser);
      loadHomePage();

      Logger.log("Results for \"" + word + "\": " + circleResults.size() + " circles, " + userResults.size() + " users");
    }

    Logger.log("Homepage search successful.");
  }

  public void testDiscoverSearchBar() {
    Logger.log("Testing discover page search bar...");
    loadDiscoverPage();

    WebElement searchBar;
    for (String word : WORD_LIST) {
      Logger.log("Searching for: \"" + word + "\"...");

      searchBar = driver.findElement(By.cssSelector(".hero-container input"));
      searchBar.sendKeys(word);
      waitUntilAppears(By.cssSelector(".result-container"), 10);

      List<WebElement> circleResults = driver.findElements(By.cssSelector(".hero-container .result-container"));
      WebElement randomCircle = circleResults.get((int)(Math.random() * circleResults.size()));
      testCircleDropdownLink(randomCircle);
      loadDiscoverPage();

      searchBar = driver.findElement(By.cssSelector(".hero-container input"));
      searchBar.sendKeys(word);
      waitUntilAppears(By.cssSelector(".result-container"), 10);

      List<WebElement> userResults = driver.findElements(By.cssSelector(".hero-container .info-container"));
      WebElement randomUser = userResults.get((int)(Math.random() * userResults.size()));
      testUserDropdownLink(randomUser);
      loadDiscoverPage();

      Logger.log("Results for \"" + word + "\": " + circleResults.size() + " circles, " + userResults.size() + " users");
    }

    Logger.log("Discover page search successful.");
  }

  /**
	 * Tests a search dropdown that opens a link to a circle.
	 * @param linkButton WebElement containing the button to be tested.
	 */
	protected void testCircleDropdownLink (WebElement linkButton){
    String target = linkButton.findElement(By.cssSelector("span:first-child")).getText();
    Logger.log("Attempting to open random circle: \"" + target + "\"...");
		linkButton.click();

		waitUntilAppears(By.cssSelector("body"), 10);

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

		waitUntilAppears(By.cssSelector("body"), 10);

    Logger.log("Opened user profile.");
	}
}
