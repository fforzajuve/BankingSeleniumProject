package com.banking.pages.balance;

import org.openqa.selenium.By;

import com.banking.driver.DriverUtils;
import com.banking.pages.BasePage;

public class BalanceEnquiryInputPage extends BasePage {
	
	private static final By accountIdTxtBy = By.name("accountno");
	private static final By submitBtnBy = By.name("AccSubmit");
	private static final By resetBtnBy = By.name("res");


	public BalanceEnquiryInputPage(DriverUtils driver) {
		super(driver);
	}
	
	public BalanceEnquiryPage selectAccount(int accountId) {
		enterAccountId(accountId);
		clickSubmitButton();
		return new BalanceEnquiryPage(driver);
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
