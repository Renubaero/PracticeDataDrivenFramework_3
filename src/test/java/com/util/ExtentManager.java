package com.util;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentManager {

	private static ExtentReports extent;

	public static ExtentReports extentReport(String filePath, String fileName) {
		ExtentHtmlReporter html = new ExtentHtmlReporter(filePath);
		html.config().setEncoding("utf-8");
		html.config().setDocumentTitle(fileName);
		html.config().setReportName(fileName);
		html.config().setTheme(Theme.STANDARD);
		extent = new ExtentReports();
		extent.attachReporter(html);
		// config
		extent.setSystemInfo("Automation", "Banking App");
		extent.setSystemInfo("Environment", "Production");

		return extent;
	}

}
