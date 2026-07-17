package com.ui.pages;

import static com.constants.Env.QA;
import static com.utility.PropertiesUtil.readProperty;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.constants.Browser;
import com.utility.BrowserUtility;
import com.utility.LoggerUtility;

public final class HomePage extends BrowserUtility{

	Logger logger = LoggerUtility.getLogger(this.getClass());
	
	private static final By SIGN_IN_LINK_LOCATOR = By.xpath("//a[contains(text(),\"Sign\")]");
	
	public HomePage(WebDriver driver) {
		super(driver); // To Call the Parent Class constructor from the child constructor
		goToWebsite(readProperty(QA, "URL"));
	}
	
	public HomePage(Browser browserName, boolean IsHeadless) {
		super(browserName, IsHeadless);
		goToWebsite(readProperty(QA, "URL"));
	}
	
	public LoginPage goToLoginPage() { // Page Functions------> cannot use void!!!
		logger.info("Trying to performing click to go to Sign in Page");
		clickOn(SIGN_IN_LINK_LOCATOR);
		LoginPage loginPage = new LoginPage(getDriver());
		return loginPage;
	}
	
	

}
