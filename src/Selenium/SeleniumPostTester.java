package Selenium;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import Logger.Logger;

public class SeleniumPostTester extends SeleniumTester {
  public SeleniumPostTester(WebDriver driver) {
    super(driver);
  }

  @Override
  public void runAll() {
    testShareLink();
    testShareEmbed();
    testShareProfile();
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

    Logger.log("Share link successful: " + getClipboardText());
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

  /**
   * Shares the profile link of the author of the top-most post from the homepage.
   */
  public void testShareProfile() {
    Logger.log("Testing share user profile...");
    loadHomePage();

    WebElement firstPost = getNthPost(1);
    WebElement firstPostAuthor = getPostAuthor(firstPost);
    firstPostAuthor.click();

    String profileURL = driver.getCurrentUrl();
    Logger.log("Share user profile successful: " + profileURL);
  }
}
