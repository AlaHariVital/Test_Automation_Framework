package com.ui.tests;

import static com.constants.Browser.CHROME;
import static org.testng.Assert.assertEquals;

import org.apache.logging.log4j.Logger;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.ui.pages.HomePage;
import com.ui.pojo.User;
import com.utility.LoggerUtility;


@Listeners({ com.ui.listeners.TestListener.class })
public class InvalidCredsLoginTest extends TestBase{
	
	Logger logger = LoggerUtility.getLogger(this.getClass());
	private static final String INVALID_EMAILADDRESS = "dhanush@inavlid.com";
	private static final String INVALID_PASSWORD = "password12";

	@Test(description = "Verifies if the proper error message is displayed for the invalid credentials", groups = { "e2e",
			"sanity" , "smoke"})
	public void loginTest() {
		assertEquals(homePage.goToLoginPage().doLoginWithInvalidCredentials(INVALID_EMAILADDRESS, INVALID_PASSWORD).getErrorMessage(),
				"Authentication failed.");
	}

}
