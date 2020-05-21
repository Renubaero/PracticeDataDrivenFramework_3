package com.testcase;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.baseclass.BaseClass;

public class HomePage extends BaseClass {

	@Test(priority = 1)
	public void homePage() {
		Boolean homeButton = driver.findElement(By.cssSelector(prop.getProperty("homePageBtn_CSS"))).isDisplayed();
		Assert.assertTrue(homeButton);
	}

	@Test(priority = 2, dataProvider = "getData")
	public void addCustomer(String firstName, String lastName, String postCode) {
		driver.findElement(By.cssSelector(prop.getProperty("bankManagerLoginBtn_CSS"))).click();
		driver.findElement(By.cssSelector(prop.getProperty("addCustomerBtn"))).click();
		driver.findElement(By.cssSelector(prop.getProperty("firstNameBox_CSS"))).sendKeys(firstName);
		driver.findElement(By.cssSelector(prop.getProperty("lastNameBox_CSS"))).sendKeys(lastName);
		driver.findElement(By.cssSelector(prop.getProperty("postCodeNameBox_CSS"))).sendKeys(postCode);
		driver.findElement(By.cssSelector(prop.getProperty("addSubmitBtn"))).click();
		Alert alert=wait.until(ExpectedConditions.alertIsPresent());
		System.out.println(alert.getText());
		alert.accept();
	}

	@DataProvider
	public Object[][] getData() {
		
		String sheetName="addCustomer";
		int rowCount=excel.getRowCount(sheetName);
		int colCount=excel.getCellCount(sheetName);
		Object[][] data=new Object[rowCount][colCount+1];
		
		for(int i=0;i<rowCount;i++) {
			for(int j=0;j<=colCount;j++) {
				data[i][j]=excel.getCellData(i+1, j, sheetName);
			}
			
		}
		return data;
	}

}
