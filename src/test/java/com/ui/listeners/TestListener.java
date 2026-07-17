package com.ui.listeners;

import java.util.Arrays;

import org.apache.logging.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.ui.tests.TestBase;
import com.utility.BrowserUtility;
import com.utility.ExtentReporterUtility;
import com.utility.LoggerUtility;

public class TestListener implements ITestListener{
	
	Logger logger = LoggerUtility.getLogger(this.getClass());
	
	ExtentSparkReporter extentSparkReporter; // is used to customize the look and feel of the report in html.
	ExtentReports extentReports;// heavyLifting, the dumping of the logs and screenshots into the report.
	ExtentTest extentTest; // store information about the test method and its result.

	public void onTestStart(ITestResult result) {
		logger.info(result.getMethod().getMethodName());
		logger.info(result.getMethod().getDescription());
		logger.info(Arrays.toString(result.getMethod().getGroups()));
		
		ExtentReporterUtility.createExtentTest(result.getMethod().getMethodName());
	}

	public void onTestSuccess(ITestResult result) {
		logger.info(result.getMethod().getMethodName() + " " + "PASSED");
		
		ExtentReporterUtility.getTest().log(Status.PASS, result.getMethod().getMethodName() + " " + "PASSED");
	}

	public void onTestFailure(ITestResult result) {
		logger.error(result.getMethod().getMethodName() + " " + "FAILED");
		logger.error(result.getThrowable().getMessage());
		
		ExtentReporterUtility.getTest().log(Status.FAIL, result.getMethod().getMethodName() + " " + "FAILED");
		ExtentReporterUtility.getTest().log(Status.FAIL,result.getThrowable().getMessage());
		
		Object testClass = result.getInstance();
		
		BrowserUtility browserUtility = ((TestBase) testClass).getInstance();
		logger.info("Capture Screenshot on Test Failure");
		
		String screenShotPath = browserUtility.takeScreenhot(result.getMethod().getMethodName());
		logger.info("Attaching Screenshot to the HTML file");
		
		ExtentReporterUtility.getTest().addScreenCaptureFromPath(screenShotPath);
	}

	public void onTestSkipped(ITestResult result) {
		logger.warn(result.getMethod().getMethodName() + " " + "SKIPPED");
		
		ExtentReporterUtility.getTest().log(Status.SKIP, result.getMethod().getMethodName() + " " + "SKIPPED");

	}

	public void onStart(ITestContext context) {
		logger.info("Test Suite Started");
		
		ExtentReporterUtility.setupSparkReporter("report.html");
		
	}

	public void onFinish(ITestContext context) {
		logger.info("Test Suite Completed");
		ExtentReporterUtility.flushReport();
	}

}
