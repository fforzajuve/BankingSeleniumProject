package com.banking.pages.customstatement;

import org.openqa.selenium.By;

import com.banking.driver.DriverUtils;
import com.banking.pages.BasePage;

public class CustomisedStatementInputPage extends BasePage {
	
	private static final By accountIdTxtBy = By.name("accountno");
	private static final By minValueTxtBy = By.name("amountlowerlimit");
	private static final By transactionNumberTxtBy = By.name("numtransaction");
	private static final By dateFromTxtBy = By.name("fdate");
	private static final By dateToTxtBy = By.name("tdate");
	private static final By submitBtnBy = By.name("AccSubmit");
	private static final By resetBtnBy = By.name("res");

	public CustomisedStatementInputPage(DriverUtils driver) {
		super(driver);
	}
	
	public CustomisedStatementDetailsPage selectTransactionsForAccount(int accountId, int minValue, int numberOfTransactions ) {
		enterAccountId(accountId);
		enterFromDate("01.04.2021");
		enterToDate("01.09.2021");
		enterMinTransactionValue(minValue);
		enterTransactionNumber(numberOfTransactions);
		clickSubmitButton();
		return new CustomisedStatementDetailsPage(driver);
	}
	
	public void enterAccountId(int accountId) {
		logger.info("Enter Account ID: " + accountId);
		driver.findElement(accountIdTxtBy).sendKeys(String.valueOf(accountId));
	}
	
	public void enterMinTransactionValue(int minValue) {
		logger.info("Enter minimum transaction value: " + minValue);
		driver.findElement(minValueTxtBy).sendKeys(String.valueOf(minValue));
	}
	
	public void enterTransactionNumber(int numberOfTransactions) {
		logger.info("Enter number of transactions: " + numberOfTransactions);
		driver.findElement(transactionNumberTxtBy).sendKeys(String.valueOf(numberOfTransactions));
	}
	
	public void enterFromDate(String date) {
		logger.info("Enter From Date: " + date);
		driver.findElement(dateFromTxtBy).sendKeys(date);
	}
	
	public void enterToDate(String date) {
		logger.info("Enter To Date: " + date);
		driver.findElement(dateToTxtBy).sendKeys(date);
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
