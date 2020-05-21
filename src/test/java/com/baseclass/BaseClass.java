package com.baseclass;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.util.ExcelReader;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {
	
	public Properties prop;
	public static WebDriver driver;
	public static WebDriverWait wait;
	public static ExcelReader excel;
	@BeforeSuite
	public void setUp() throws IOException {
		excel=new ExcelReader(System.getProperty("user.dir")+"\\src\\test\\resources\\Excel\\data.xlsx");
		prop=new Properties();
		FileInputStream config=new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\resources\\properties\\config.properties");
		prop.load(config);
		FileInputStream OR=new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\resources\\properties\\OR.properties");
		prop.load(OR);
		System.out.println(prop.getProperty("browser"));
		if(prop.getProperty("browser").equals("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver=new ChromeDriver();
		}
		driver.manage().window().maximize();		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get(prop.getProperty("url"));
		wait=new WebDriverWait(driver, 10);
	}
	@AfterSuite
	public void tearDown() {
		if(driver!=null) {
			driver.quit();
		}
	}

}
