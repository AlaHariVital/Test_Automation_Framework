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
public class LoginTest extends TestBase{
	
	Logger logger = LoggerUtility.getLogger(this.getClass());

	// @Test(description = "Verifies if the valid user login is successful", groups = { "e2e",
	// 		"sanity" }, dataProviderClass = com.ui.dataproviders.LoginDataProvider.class, dataProvider = "LoginTestDataProvider")
	// public void loginTest(User user) {
	// 	assertEquals(homePage.goToLoginPage().doLoginWith(user.getEmailAddress(), user.getPassword()).getUserName(),
	// 			"Dhanush Kumar");
	// }

	@Test(description = "Verifies if the valid user login is successful", groups = { "e2e",
			"sanity" }, dataProviderClass = com.ui.dataproviders.LoginDataProvider.class, dataProvider = "LoginTestCSVDataProvider")
	public void loginCSVTest(User user) {
		assertEquals(homePage.goToLoginPage().doLoginWith(user.getEmailAddress(), user.getPassword()).getUserName(),
				"Dhanush Kumar");
	}

	// @Test(description = "Verifies with the valid user is able to login into the application", groups = { "e2e",
	// 		"sanity" }, dataProviderClass = com.ui.dataproviders.LoginDataProvider.class, dataProvider = "LoginTestExcelDataProvider"
	// 		, retryAnalyzer = com.ui.listeners.MyRetryAnalyzer.class)
	// public void loginExcelTest(User user) {

	// 	assertEquals(homePage.goToLoginPage().doLoginWith(user.getEmailAddress(), user.getPassword()).getUserName(),
	// 			"Dhanush Kumar1");

	// }
}
