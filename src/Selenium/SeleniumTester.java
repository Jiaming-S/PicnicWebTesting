package Selenium;

import java.awt.Toolkit;
import java.awt.datatransfer.DataFlavor;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class SeleniumTester {
	protected WebDriver driver;
	protected boolean windowIsOpen;
	
	public SeleniumTester (WebDriver driver) {
		this.driver = driver;
		windowIsOpen = false;
	}

	/**
	 * Implementations should run all their respective tests. 
	 */
	public abstract void runAll ();
	
	/**
	 * Opens a specified page. Refreshes the page if already open. 
	 */
	protected void loadPage (String URL) {
		if (windowIsOpen && driver.getCurrentUrl().equals(URL)){
			driver.navigate().refresh();
		}
		else {
			driver.get(URL);
			windowIsOpen = true;
		}
	}

	/**
	 * Loads the homepage. 
	 */
	protected void loadHomePage() {
		loadPage("https://picnic.zone/");

		// Wait until posts load
		waitUntilAppears(By.cssSelector(".post-list > post-wrapper"), 20);
	}

	/**
	 * Loads the discover page. 
	 */
	protected void loadDiscoverPage(){
		loadPage("https://picnic.zone/d");

		// Wait until posts load
		waitUntilAppears(By.cssSelector(".card-row > discover-card"), 20);
	}
	
	/**
	 * Wait until a certain condition is met.
	 * @param until On what condition to stop waiting.
	 * @param seconds How long to wait before giving up.
	 */
	protected void waitUntilAppears (By until, int seconds){
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(seconds)); 
		wait.pollingEvery(Duration.ofMillis(100));
		wait.until(ExpectedConditions.visibilityOfElementLocated(until));
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
			System.out.println("Problem with getting clipboard text");
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
		return post.findElements(By.cssSelector("post-actions div[_ngcontent-serverapp-c64] > div"));
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
