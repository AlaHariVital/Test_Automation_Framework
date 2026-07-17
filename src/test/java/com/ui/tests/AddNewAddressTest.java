package com.ui.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.ui.pages.AddressPage;
import com.ui.pages.MyAccountPage;
import com.ui.pojo.AddressPOJO;
import com.utility.FakeAddressUtility;

@Listeners({ com.ui.listeners.TestListener.class })
public class AddNewAddressTest extends TestBase{
	
	private MyAccountPage myAccountPage;
	public AddressPOJO address;
	public AddressPage addressPage;
	
	@BeforeMethod(description = "Valid user logs into the application")
	public void setup() {
		myAccountPage = homePage.goToLoginPage().doLoginWith("keriro7786@daerdy.com", "password");
		address = FakeAddressUtility.getFakeAddress();
	}
	
	@Test(description = "Verifies if the the logged in user is able to add new address")
	public void addAddressTest() {
		String newAdress = myAccountPage.clickOnaddAddress().saveAddress(address);
		Assert.assertEquals(newAdress, address.getAddressAlias().toUpperCase());
	}

}
