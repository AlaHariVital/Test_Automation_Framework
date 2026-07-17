package com.ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.utility.BrowserUtility;

public class PaymentPage extends BrowserUtility{
	
	public static final By ALERT_MESSAGE =  By.xpath("//p[contains(@class,'alert-warning')]");

	public PaymentPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	public String alertMessage() {
		return getVisibleText(ALERT_MESSAGE);
	}

}
