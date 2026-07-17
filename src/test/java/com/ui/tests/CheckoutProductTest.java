package com.ui.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.ui.pages.SearchResultPage;

@Listeners({ com.ui.listeners.TestListener.class })
public class CheckoutProductTest extends TestBase{
	
	private SearchResultPage searchResultPage;
	
	private static final String SEARCH_TERM = "Printed Chiffon Dress";
	
	@BeforeMethod(description = "Valid user logs into the application")
	public void setup() {
		searchResultPage = homePage.goToLoginPage().doLoginWith("keriro7786@daerdy.com", "password").searchForAProduct(SEARCH_TERM);
	}
	
	@Test
	public void addToCart() {
		
		String alertMessage = searchResultPage.selectTheSearchedProduct(1).addQuantityOfProduct().proceedToCart().proceedToConfirmAddressPage()
						.goToShippmentPage().goToPaymentPage().alertMessage();
		Assert.assertEquals(alertMessage, "No payment modules have been installed.");
	}

}
