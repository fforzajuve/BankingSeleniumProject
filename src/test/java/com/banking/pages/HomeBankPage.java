package com.banking.pages;

import org.openqa.selenium.By;

import com.banking.driver.DriverUtils;

public class HomeBankPage extends BasePage {
	
	private static final By managerIdLblBy = By.xpath("//tr[@class='heading3']/td");

	public HomeBankPage(DriverUtils driver) {
		super(driver);
	}
	
	public String getManagerId() {
		logger.info("Get Manager Id.");
		String id = driver.findElement(managerIdLblBy).getText().split(":")[1].trim();
		logger.info("Manager Id: " + id);
		return id;
	}

}
