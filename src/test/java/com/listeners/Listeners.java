package com.listeners;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.util.ExtentManager;

public class Listeners implements ITestListener {

	static String fileName = "ExtentReport";
	private static ExtentReports extent = ExtentManager
			.extentReport(System.getProperty("user.dir") + "\\reports\\" + fileName + ".html", fileName);
	public static ExtentTest test;
	public void onTestStart(ITestResult result) {
		test=extent.createTest(result.getTestClass().getName()+" @TestCase - "+result.getMethod().getMethodName().toUpperCase());
	}

	/**
	 * Invoked each time a test succeeds.
	 *
	 * @param result <code>ITestResult</code> containing information about the run
	 *               test
	 * @see ITestResult#SUCCESS
	 */
	public void onTestSuccess(ITestResult result) {
		Markup m=MarkupHelper.createLabel(result.getMethod().getMethodName()+" - PASS", ExtentColor.INDIGO);
		test.pass(m);
	}

	public void onFinish(ITestContext context) {
		if(extent !=null) {
			extent.flush();
		}
	}
}
