package Sel.DiscoverPage;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Log.*;
import Sel.*;

public class TestCaseDiscoverLink extends TestCase {
	private final int NUM_OF_TEST_LINKS = 3;

  public TestCaseDiscoverLink(WebDriver driver) {
		super(driver);
	}

  @Override
  public void runTest() throws TestCaseFailedException {
    try {
      testDiscoverLinks();
    } catch (Exception e) {
      throw new TestCaseFailedException("Accessing circle links unsuccessful.\n" + e.getMessage() + "\n");
    }
  }

  public void testDiscoverLinks () {
		Logger.log("Testing access to discover page circle links...");
		for (int i = 0; i < NUM_OF_TEST_LINKS; i++) {
			loadDiscoverPage();
			
			WebElement col = waitUntilAppears(By.cssSelector("app-main-page"), 4);
			List<WebElement> discoverCardRows = col.findElements(By.cssSelector("div.grid-container > .card-row"));
			
			WebElement curDiscoverCardRow = discoverCardRows.get(i);
			List<WebElement> allDiscoverCards = curDiscoverCardRow.findElements(By.cssSelector("discover-card"));
			
			WebElement randomDiscoverCard = allDiscoverCards.get((int)(allDiscoverCards.size() * Math.random()));
			String randomDiscoverCardText = randomDiscoverCard.findElement(By.cssSelector("span.circle-name")).getText();

			Logger.log("Attempting to click on circle: \"" + randomDiscoverCardText + "\"...");

			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5)); 
			wait.until(ExpectedConditions.elementToBeClickable(randomDiscoverCard)).click();
			
			Logger.log("Opened \"" + randomDiscoverCardText + "\" with resulting url: " + driver.getCurrentUrl());
		}
		Logger.log("Accessing circle links successful.");
	}
}
