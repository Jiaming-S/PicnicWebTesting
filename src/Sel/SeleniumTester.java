package Sel;

import org.openqa.selenium.WebDriver;

public abstract class SeleniumTester {
	protected WebDriver driver;
	
	public SeleniumTester (WebDriver driver) {
		this.driver = driver;
	}

	/**
	 * Implementations should run all their respective tests. 
	 */
	public abstract void runAll ();
}
