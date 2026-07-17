package com.ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.utility.BrowserUtility;

public class ShoppingSummaryPage extends BrowserUtility{
	
	private static final By PROCEED_TO_CHECKOUT = By.xpath("//p[contains(@class, 'cart_navigation')]//a[@title='Proceed to checkout']");

	public ShoppingSummaryPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	public ConfirmAddressPage proceedToConfirmAddressPage() {
		clickOn(PROCEED_TO_CHECKOUT);
		ConfirmAddressPage confirmAdress = new ConfirmAddressPage(getDriver());
		return confirmAdress;
	}

}
