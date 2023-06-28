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

public class SeleniumTester {
	private WebDriver driver;
	private boolean windowIsOpen;
	
	public SeleniumTester (WebDriver driver) {
		this.driver = driver;
		windowIsOpen = false;
	}
	
	/**
	 * Shares the link of the top-most post on the homepage. 
	 */
	public void testShareLink () {
		try {
			System.out.println("Testing share link on first post...");
			loadHomePage();
			
			WebElement firstPost = getNthPost(1);
			List<WebElement> allButtons = getPostButtons(firstPost);
			WebElement shareButton = allButtons.get(2);
			shareButton.click();
			
			WebElement shareLinkButton = shareButton.findElement(By.cssSelector(".dropdown .scrollable-wrapper > div:first-child"));
			shareLinkButton.click();
			
			System.out.println("Share link successful: " + getClipboardText());
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Share link unsuccessful");
		}
	}
	
	/**
	 * Shares the embed of the top-most post on the homepage. 
	 */
	public void testShareEmbed () {
		try {
			System.out.println("Testing share embed on first post...");
			loadHomePage();
			
			WebElement firstPost = getNthPost(1);
			List<WebElement> allButtons = getPostButtons(firstPost);
			WebElement shareButton = allButtons.get(2);
			shareButton.click();
			
			WebElement shareLinkButton = shareButton.findElement(By.cssSelector(".dropdown .scrollable-wrapper > div:nth-child(2)"));
			shareLinkButton.click();

			System.out.println("Share embed successful: " + getClipboardText());
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Share embed unsuccessful");
		}
	}
	
	/**
	 * Shares the profile link of the author of the top-most post from the homepage.  
	 */
	public void testShareProfile () {
		try {
			System.out.println("Testing share user profile...");
			loadHomePage();
			
			WebElement firstPost = getNthPost(1);
			WebElement firstPostAuthor = getPostAuthor(firstPost);
			firstPostAuthor.click();
			
			String profileURL = driver.getCurrentUrl();
			System.out.println("Share user profile successful: " + profileURL);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Share user profile unsuccessful");
		}
	}
	
	/**
	 * Shares the link of the top most circle. 
	 */
	public void testShareCircle () {
		try {
			System.out.println("Testing share first circle...");
			loadHomePage();
			
			WebElement firstCircle = getNthCircle(1);
			firstCircle.click();
			
			String circleURL = driver.getCurrentUrl();
			System.out.println("Share first circle successful: " + circleURL);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Share first circle unsuccessful");
		}
	}
	
	/**
	 * Opens the home page. Refreshes the page if already open. 
	 */
	private void loadHomePage () {
		if (windowIsOpen && driver.getCurrentUrl().equals("https://picnic.zone/")){
			driver.navigate().refresh();
		}
		else {
			driver.get("https://picnic.zone/");
			windowIsOpen = true;
		}
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); 
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".post-list > *")));
	}
	
	/**
	 * Gets the n-th post from the for you page. 
	 * @param n Specify which post (1, 2, 3, ...). 
	 * @return The WebElement of the corresponding post beginning at the <post-wrapper> tag.   
	 */
	private WebElement getNthPost (int n) {
		return driver.findElement(By.cssSelector(".post-list > *:nth-child(" + n + ")"));
	}
	
	/**
	 * Gets list of buttons below a post.  
	 * @param post A post beginning at the <post-wrapper> tag. 
	 * @return A List of WebElements containing the buttons: (like/dislike, comment, share, bookmark, flag)
	 */
	private List<WebElement> getPostButtons (WebElement post) {
		return post.findElements(By.cssSelector("post-actions div[_ngcontent-serverapp-c64] > div"));
	}
	
	/**
	 * Gets the author of a post. 
	 * @param post A post beginning at the <post-wrapper> tag. 
	 * @return The WebElement containing the author of a post. 
	 */
	private WebElement getPostAuthor (WebElement post) {
		return post.findElement(By.cssSelector(".username-label"));
	}
	
	/**
	 * Gets the n-th circle from the sidebar. 
	 * @param n Specify which circle (1, 2, 3, ...).
	 * @return The WebElement of the corresponding circle beginning at the <list-item> tag.
	 */
	private WebElement getNthCircle (int n) {
		return driver.findElement(By.cssSelector("expandable-sidebar-items .item-list > *:nth-child(" + n + ")"));
	}
	
	/**
	 * Gets the clipboard's text from the current browser instance. 
	 * @return The String contents of the clipboard. 
	 */
	private String getClipboardText () {
		try {
			return (String)Toolkit.getDefaultToolkit().getSystemClipboard().getData(DataFlavor.stringFlavor);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Problem with getting clipboard text");
			return "";
		} 
	}
}
