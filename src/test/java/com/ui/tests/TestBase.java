package com.ui.tests;

import static com.constants.Browser.CHROME;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.constants.Browser;
import com.ui.pages.HomePage;
import com.utility.BrowserUtility;
import com.utility.LambdaTestUtility;
import com.utility.LoggerUtility;

public class TestBase {
	
	protected HomePage homePage;
	private boolean isLambdaTest;
	
	Logger logger = LoggerUtility.getLogger(this.getClass());
	
	@Parameters({"browser", "isLambdaTest", "isHeadless"})
	@BeforeMethod(description = "Setsup the WebDriver instance")
	public void setup(
			@Optional("chrome") String browser, 
			@Optional("false")  boolean isLambdaTest, 
			@Optional("false") boolean isHeadless, ITestResult result) {
		
		this.isLambdaTest = isLambdaTest;
		WebDriver lambdaDriver;
		if(isLambdaTest) {
			lambdaDriver = LambdaTestUtility.intializeLambdaTestSession(browser, result.getMethod().getMethodName());
			homePage = new HomePage(lambdaDriver);
		} else {
			logger.info("Load HomePage of the website...");
			String browserName = browser != null ? browser.trim() : "chrome";
			if (browserName.isEmpty() || browserName.startsWith("${")) {
				browserName = "chrome";
			}
			Browser browserEnum;
			try {
				browserEnum = Browser.valueOf(browserName.toUpperCase());
			} catch (IllegalArgumentException ex) {
				logger.warn("Invalid browser parameter '{}'; defaulting to CHROME", browserName, ex);
				browserEnum = CHROME;
			}
			homePage = new HomePage(browserEnum, isHeadless);
		}
	}
	
	public BrowserUtility getInstance() {
		return homePage;
	}
	
	@AfterMethod(description = "Tear Down the browser")
	public void tearDown() {

		if (isLambdaTest) {
			LambdaTestUtility.quitSession(); // quit or close the browsersession on LT
		} else {
			homePage.quit(); // local
		}
	}

}
