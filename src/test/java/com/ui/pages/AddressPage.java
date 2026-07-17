package com.ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.ui.pojo.AddressPOJO;
import com.utility.BrowserUtility;

public class AddressPage extends BrowserUtility{
	
	private static final By COMPANY = By.id("company");
	private static final By ADDRESS = By.id("address1");
	private static final By ADDRESS_2 = By.id("address2");
	private static final By CITY = By.id("city");
	private static final By COUNTRY = By.id("id_country");
	private static final By STATE = By.xpath("//select[@id='id_state']");
	private static final By ZIP_CODE = By.id("postcode");
	private static final By HOME_PHONE = By.id("phone");
	private static final By MOBILE_PHONE = By.id("phone_mobile");
	private static final By ADDITIONAL_INFO = By.id("other");
	private static final By ADDRESS_TITLE = By.id("alias");
	private static final By SAVE = By.id("submitAddress");
	private static final By ADDRESS_HEADING = By.tagName("h3");
	
	public AddressPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	public String saveAddress(AddressPOJO address) {
		enterText(COMPANY, address.getCompany());
		enterText(ADDRESS, address.getAddressLine1());
		enterText(ADDRESS_2, address.getAddressLine2());
		enterText(CITY, address.getCity());
		enterText(ZIP_CODE, address.getPostCode());
		enterText(HOME_PHONE, address.getHomePhoneNumber());
		enterText(MOBILE_PHONE, address.getMobileNumber());
		clickOn(By.id("uniform-id_state"));
		clickOn(By.xpath("//option[text()='Indiana']"));
		enterText(ADDITIONAL_INFO, address.getOtherInformation());
		clearText(ADDRESS_TITLE);
		enterText(ADDRESS_TITLE, address.getAddressAlias());
		clickOn(SAVE);
		String newAddress = getVisibleText(ADDRESS_HEADING);
		return newAddress;
	}

}
