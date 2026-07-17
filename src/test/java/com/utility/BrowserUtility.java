package com.utility;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.constants.Browser;

public abstract class BrowserUtility {

	public static ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();
	private Logger logger = LoggerUtility.getLogger(this.getClass());
	private WebDriverWait wait;
	private JavascriptExecutor jsExecutor;

	public WebDriver getDriver() {
		return driver.get();
	}

	public BrowserUtility(WebDriver driver) {
		super();
		this.driver.set(driver);
		// initialize the instance variable
		wait = new WebDriverWait(driver, Duration.ofSeconds(30L));
		jsExecutor = (JavascriptExecutor) getDriver(); 	
	}

	public BrowserUtility(String browserName) {
		logger.info("Launching Browser for " + browserName);
		if (browserName.equalsIgnoreCase("chrome")) {
			driver.set(new ChromeDriver());
			wait = new WebDriverWait(driver.get(), Duration.ofSeconds(30L));
		}

		else if (browserName.equalsIgnoreCase("edge")) {
			driver.set(new EdgeDriver());
			wait = new WebDriverWait(driver.get(), Duration.ofSeconds(30L));
		}

		else {
			logger.error("Invalid Browser Name....Please select Chrome or Edge only");
			System.err.print("Invalid Browser Name....Please select Chrome or Edge only");
		}
	}

	public BrowserUtility(Browser browserName) {
		logger.info("Launching Browser for " + browserName);
		if (browserName == Browser.CHROME) {
			driver.set(new ChromeDriver());
			wait = new WebDriverWait(driver.get(), Duration.ofSeconds(30L));
		}

		else if (browserName == Browser.EDGE) {
			driver.set(new EdgeDriver());
			wait = new WebDriverWait(driver.get(), Duration.ofSeconds(30L));
		}

		else {
			System.err.print("Invalid Browser Name....Please select Chrome or Edge only");
		}
	}

	public BrowserUtility(Browser browserName, boolean Isheadless) {
		logger.info("Launching Browser for " + browserName);

		if (browserName == Browser.CHROME) {
			if (Isheadless) {
				ChromeOptions options = new ChromeOptions();
				options.addArguments("--headless");
				options.addArguments("--window-size=1920,1080");
				driver.set(new ChromeDriver(options));
				wait = new WebDriverWait(driver.get(), Duration.ofSeconds(30L));
			} else {
				driver.set(new ChromeDriver());
				wait = new WebDriverWait(driver.get(), Duration.ofSeconds(30L));
			}
		}

		else if (browserName == Browser.EDGE) {
			if (Isheadless) {
				EdgeOptions options = new EdgeOptions();
				options.addArguments("--headless=old");
				options.addArguments("--disable-gpu");
				driver.set(new EdgeDriver(options));
				wait = new WebDriverWait(driver.get(), Duration.ofSeconds(30L));
			} else {
				driver.set(new EdgeDriver());
				wait = new WebDriverWait(driver.get(), Duration.ofSeconds(30L));
			}
		}

		else {
			System.err.print("Invalid Browser Name....Please select Chrome or Edge only");
		}
	}

	public void goToWebsite(String url) {
		logger.info("Visiting the website " + url);
		driver.get().get(url);
	}

	public void maximizeWindow() {
		logger.info("Maximizing the browser window");
		driver.get().manage().window().maximize();
	}

	public void clickOn(By locator) {
		logger.info("Finding Element with the locator" + locator);
//		WebElement element = driver.get().findElement(locator);// Find the element!!!
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
		
		logger.info("Element Found and now performing Click");
		element.click();
	}
	
	public void clickOn(WebElement element) {

		logger.info("Element Found and now performing Click");

		element.click();
	}

	public void enterText(By locator, String textToEnter) {
		logger.info("Finding Element with the locator" + locator);
		WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		element.sendKeys(textToEnter);
	}
	
	public void clearText(By locator) {
		logger.info("Finding Element with the locator" + locator);
		WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		element.clear();
	}
	
	public void selectFromDropDown(By dropDownLocator, String visibleText) {
		logger.info("Finding Element with the locator" + dropDownLocator);
		
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", getDriver().findElement(dropDownLocator));
		
	    WebElement dropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(dropDownLocator));
	    Select select = new Select(dropdown);
	    select.selectByVisibleText(visibleText);
	}

	public String getVisibleText(By locator) {
		logger.info("Finding Element with the locator" + locator);

		WebElement element = driver.get().findElement(locator);
		return element.getText();
	}
	
	public String getVisibleText(WebElement we) {
		logger.info("Returning the visible text of the WebElement " + we.getText());

		return we.getText();
	}
	
	public List<String> getAllVisibleText(By locator) {
		logger.info("Finding all Elements with the locator" + locator);

		List<WebElement> elements = driver.get().findElements(locator);
		logger.info("All elements are found & now printing the visible text of it");
		
		List<String> visibleTexttList = new ArrayList();
		for(WebElement element: elements) {
//			System.out.println(getVisibleText(element));
			visibleTexttList.add(getVisibleText(element));
		}
		
		return visibleTexttList;
		
	}
	
	public List<WebElement> getAllElements(By locator) {
		logger.info("Finding All Elements with the locator" + locator);

		List<WebElement> elementList = driver.get().findElements(locator);
		logger.info("Elements Found and now printing the List of Elements");

		return elementList;

	}

	public String takeScreenhot(String name) {

		TakesScreenshot screenshot = (TakesScreenshot) driver.get();
		File screenshotData = screenshot.getScreenshotAs(OutputType.FILE);

		Date date = new Date();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH-mm-ss");
		String timeStamp = simpleDateFormat.format(date);

		String path = "./screenshots/" + name + "-" + timeStamp + ".png";
		File screenshotFile = new File(path);

		try {
			FileUtils.copyFile(screenshotData, screenshotFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return path;
	}
	
	public void enterSpecialKey(By locator, Keys keyToEnter) {
		logger.info("Finding Element with the locator" + locator);

		WebElement element = driver.get().findElement(locator);

		logger.info("Element Found and now enter special Key " + keyToEnter);

		element.sendKeys(keyToEnter);
	}
	
	public void quit() {
		driver.get().quit();
	}
}
