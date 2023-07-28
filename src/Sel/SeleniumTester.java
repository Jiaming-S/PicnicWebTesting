package Sel;

import org.openqa.selenium.WebDriver;

import Log.Logger;

public abstract class SeleniumTester {
	protected WebDriver driver;
	
	public SeleniumTester (WebDriver driver) {
		this.driver = driver;
	}

	/**
	 * Implementations should run all their respective tests. 
	 */
	public abstract void runAll ();

	protected void printResults (int numTests, int numFailedTests){
		System.out.println("Ran a total of " + Logger.toGreen("" + numTests) + " tests. " + (numFailedTests > 0 ? Logger.toRed("" + numFailedTests) : Logger.toGreen("" + numFailedTests)) + " tests failed.");
	}
}
