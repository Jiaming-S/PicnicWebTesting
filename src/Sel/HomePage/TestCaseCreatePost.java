package Sel.HomePage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import Log.*;
import Sel.*;

public class TestCaseCreatePost extends TestCase {
  public TestCaseCreatePost(WebDriver driver) {
		super(driver);
	}

  @Override
  public void runTest() throws TestCaseFailedException {
    try {
      testCreatePost();
    } catch (Exception e) {
      throw new TestCaseFailedException("Create post unsuccessful.\n" + e.getMessage() + "\n", e.getStackTrace());
    }
  }

  public void testCreatePost() throws TestCaseFailedException {
    Logger.log("Testing create random post...");
    loadHomePageDev();
    signInDev(driver);

    

    Logger.log("Create post successful.");
  }  
}