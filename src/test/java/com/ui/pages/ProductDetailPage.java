package com.ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.utility.BrowserUtility;

public class ProductDetailPage extends BrowserUtility{
	
	private static final By QUANTITY_LOCATOR = By.id("quantity_wanted");
	private static final By ADD_TO_CART = By.xpath("//p[@id='add_to_cart']/button");
	private static final By PROCEED_TO_CHECKOUT = By.xpath("//a[@title='Proceed to checkout']");
	

	public ProductDetailPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	public ProductDetailPage addQuantityOfProduct() {
		clearText(QUANTITY_LOCATOR);
		enterText(QUANTITY_LOCATOR, "2");
		return new ProductDetailPage(getDriver());
	}
	
	
	
	public ShoppingSummaryPage proceedToCart() {
		clickOn(ADD_TO_CART);
		clickOn(PROCEED_TO_CHECKOUT);
		ShoppingSummaryPage summaryPage = new ShoppingSummaryPage(getDriver());
		return summaryPage;
	}
}
