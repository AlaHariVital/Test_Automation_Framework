package com.ui.pages;

import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.utility.BrowserUtility;

public class SearchResultPage extends BrowserUtility{
	
	private static final By PRODUCT_HEADING = By.xpath("//span[@class='lighter']");
	private static final By PRODUCT_LIST = By.xpath("//h5[@itemprop='name']/a");
	
	
	public SearchResultPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	public String SearchProductTitle() {
		return getVisibleText(PRODUCT_HEADING);
	}
	
	public boolean isSearchTermPresentInProductList(String searchTerm) {
		List<String> keywords=Arrays.asList(searchTerm.toLowerCase().split(" "));
		List<String> productNamesList=getAllVisibleText(PRODUCT_LIST);
		boolean  result=productNamesList.stream()
		.anyMatch(name -> (keywords.stream().anyMatch(name.toLowerCase()::contains)));
		return result;
	}
	
	
	public ProductDetailPage selectTheSearchedProduct(int index) {
		clickOn(getAllElements(PRODUCT_LIST).get(index));
		ProductDetailPage productDetail = new ProductDetailPage(getDriver());
		return productDetail;
	}

}
