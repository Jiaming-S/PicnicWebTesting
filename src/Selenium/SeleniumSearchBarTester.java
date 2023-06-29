package Selenium;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import Logger.Logger;

public class SeleniumSearchBarTester extends SeleniumTester {
  private final String[] WORD_LIST = { "test", "picnic", "gacha" };

  public SeleniumSearchBarTester(WebDriver driver) {
    super(driver);
  }

  @Override
  public void runAll() {
    this.testHomePageSearchBar();
    this.testDiscoverSearchBar();
  }

  public void testHomePageSearchBar() {
    Logger.log("Testing homepage search bar...");
    loadHomePage();

    WebElement searchBar = driver.findElement(By.cssSelector(".search-desktop-container input"));
    for (String word : WORD_LIST) {
      Logger.log("Searching for: \"" + word + "\"...");
      searchBar.sendKeys(word);

      waitUntilAppears(By.cssSelector(".result-container"), 10);
      List<WebElement> circleResults = driver.findElements(By.cssSelector(".search-desktop-container .result-container"));
      List<WebElement> userResults = driver.findElements(By.cssSelector(".search-desktop-container .info-container"));
      searchBar.clear();

      Logger.log("Results for \"" + word + "\": " + circleResults.size() + " circles, " + userResults.size() + " users");
    }

    Logger.log("Homepage search successful.");
  }

  public void testDiscoverSearchBar() {
    Logger.log("Testing discover page search bar...");
    loadDiscoverPage();

    WebElement searchBar = driver.findElement(By.cssSelector(".hero-container input"));
    for (String word : WORD_LIST) {
      Logger.log("Searching for: \"" + word + "\"...");
      searchBar.sendKeys(word);

      waitUntilAppears(By.cssSelector(".result-container"), 10);
      List<WebElement> circleResults = driver.findElements(By.cssSelector(".hero-container .result-container"));
      List<WebElement> userResults = driver.findElements(By.cssSelector(".hero-container .info-container"));
      searchBar.clear();

      Logger.log("Results for \"" + word + "\": " + circleResults.size() + " circles, " + userResults.size() + " users");
    }

    Logger.log("Discover page search successful.");
  }
}
