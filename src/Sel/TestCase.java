package Sel;

import java.awt.Toolkit;
import java.awt.datatransfer.DataFlavor;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Log.Logger;
import Main.Constants;

public abstract class TestCase {
  protected WebDriver driver;
  
  private boolean windowIsOpen;

  public TestCase(WebDriver driver) {
    this.driver = driver;
    this.windowIsOpen = false;
  }

  public abstract void runTest() throws TestCaseFailedException;

  protected void shortWait () {
    try { Thread.sleep(Constants.SHORT_WAIT_TIME); } catch (InterruptedException e) { e.printStackTrace(); }
  }

	/**
	 * Opens a specified page. Refreshes the page if already open. 
	 */
	protected void loadPage (String URL) {
		Logger.log("Loading page: " + URL);

    if (windowIsOpen && driver.getCurrentUrl().equals(URL)) {
      driver.navigate().refresh();
    } else {
      driver.get(URL);
      windowIsOpen = true;
    }
	}

  /**
	 * Loads the homepage. 
	 */
	protected void loadHomePage() {
    boolean loadSuccess = false;
    int currentTryCount = 0;

    while (!loadSuccess || currentTryCount > Constants.MAX_RELOAD_ATTEMPTS) {
      try {
        loadPage("https://picnic.zone/");

        // Wait until posts load
        waitUntilAppears(By.cssSelector(".post-list > post-wrapper"), Constants.MAX_PAGE_LOAD_TIME);
        shortWait();

        currentTryCount++;
        loadSuccess = true;
      } catch (Exception e) {
        Logger.log("Loading homepage unsuccessful. Retrying...");
        currentTryCount++;
      }
    }

    if (!loadSuccess) Logger.log("Loading homepage unsuccessful. Max attempts exceeded.");
	}

	/**
	 * Loads the homepage from dev server. 
	 */
	protected void loadHomePageDev (){
		loadPage("https://stg.picnic.zone");
		waitUntilAppears(By.cssSelector(".post-list > post-wrapper"), Constants.MAX_PAGE_LOAD_TIME);
		shortWait();
	}

	/**
	 * Signs into dev server.
	 */
	protected void signInDev (WebDriver driver){
		WebElement signInButton = driver.findElement(By.cssSelector("picnic-button[ng-reflect-label='sign in'].d-sm-block"));
		signInButton.click();

		waitUntilAppears(By.cssSelector(".bottom-container picnic-button"), Constants.MAX_PAGE_LOAD_TIME);
		shortWait();
		WebElement logInButton = driver.findElement(By.cssSelector(".bottom-container picnic-text-button"));
		logInButton.click();

		shortWait();
		

	}

	/**
	 * Loads the discover page. 
	 */
	protected void loadDiscoverPage(){
		loadPage("https://picnic.zone/d");

		// Wait until circles load
		waitUntilAppears(By.cssSelector(".card-row > discover-card"), Constants.MAX_PAGE_LOAD_TIME);
	}
	
	/**
	 * Wait until a certain condition is met.
	 * @param until On what condition to stop waiting.
	 * @param time How long to wait before giving up.
	 * @return resultant element based on query
	 */
	protected WebElement waitUntilAppears (By until, Duration time){
		WebDriverWait wait = new WebDriverWait(driver, time); 
		wait.pollingEvery(Duration.ofMillis(100));
		wait.until(ExpectedConditions.visibilityOfElementLocated(until));
		return driver.findElement(until);
	}
	
	/**
	 * Wait until the current page loads
	 * @param seconds How long to wait before giving up.
	 */
	protected void waitUntilPageLoads (int seconds) {
		waitUntilAppears(By.cssSelector("body"), Constants.MAX_PAGE_LOAD_TIME);
	}
	
	/**
	 * Gets the clipboard's text from the current browser instance. 
	 * @return The String contents of the clipboard. 
	 */
	protected String getClipboardText () {
		try {
			return (String)Toolkit.getDefaultToolkit().getSystemClipboard().getData(DataFlavor.stringFlavor);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Problem with getting clipboard text.");
			return "";
		} 
	}

	/**
	 * Gets the n-th post from the homepage. 
	 * @param n Specify which post (1, 2, 3, ...). 
	 * @return The WebElement of the corresponding post beginning at the <post-wrapper> tag.   
	 * @pre The active page is the homepage. 
	 */
	protected WebElement getNthPost (int n) {
		return driver.findElement(By.cssSelector(".post-list > *:nth-child(" + n + ")"));
	}
	
	/**
	 * Gets list of buttons below a post.  
	 * @param post A post beginning at the <post-wrapper> tag. 
	 * @return A List of WebElements containing the buttons: (like/dislike, comment, share, bookmark, flag)
	 */
	protected List<WebElement> getPostButtons (WebElement post) {
		return post.findElements(By.cssSelector("post-actions > div > div"));
	}
	
	/**
	 * Gets the author of a post. 
	 * @param post A post beginning at the <post-wrapper> tag. 
	 * @return The WebElement containing the author of a post. 
	 */
	protected WebElement getPostAuthor (WebElement post) {
		return post.findElement(By.cssSelector(".username-label"));
	}
	
	/**
	 * Gets the n-th circle from the sidebar. 
	 * @param n Specify which circle (1, 2, 3, ...).
	 * @return The WebElement of the corresponding circle beginning at the <list-item> tag.
	 */
	protected WebElement getNthCircle (int n) {
		return driver.findElement(By.cssSelector("expandable-sidebar-items .item-list > *:nth-child(" + n + ")"));
	}
}
