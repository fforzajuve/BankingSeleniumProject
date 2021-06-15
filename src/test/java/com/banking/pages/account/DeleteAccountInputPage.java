package com.banking.pages.account;

import org.openqa.selenium.By;

import com.banking.driver.DriverUtils;
import com.banking.pages.BasePage;

public class DeleteAccountInputPage extends BasePage {
	
	private static final By accountIdTxtBy = By.name("accountno");
	private static final By submitBtnBy = By.name("AccSubmit");
	private static final By resetBtnBy = By.name("res");

	public DeleteAccountInputPage(DriverUtils driver) {
		super(driver);
	}
	
	
	public String deleteAccount(int accountId) {
		logger.info("Delete Account: " + accountId);
		enterAccountId(accountId);
		clickSubmitButton();
		driver.acceptAlert();
		String alertMessage = driver.getAlertMessage();
		logger.info("Alert Message: " +alertMessage);
		driver.acceptAlert();
		return alertMessage;
	}

	public void enterAccountId(int accountId) {
		logger.info("Enter Account ID: " + accountId);
		driver.findElement(accountIdTxtBy).sendKeys(String.valueOf(accountId));
	}

	public void clickSubmitButton() {
		logger.info("Click Submit Button");
		driver.findElement(submitBtnBy).click();
	}
	
	public void clickResetButton() {
		logger.info("Click Reset Button");
		driver.findElement(resetBtnBy).click();
	}

}
