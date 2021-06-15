package com.banking.pages.account;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;

import com.banking.driver.DriverUtils;
import com.banking.enums.AccountType;
import com.banking.pages.BasePage;

public class EditAccountPage extends BasePage {
	
	private static final By accountTypeSelectBy = By.name("a_type");
	private static final By submitBtnBy = By.name("AccSubmit");
	private static final By resetBtnBy = By.name("AccReset");

	public EditAccountPage(DriverUtils driver) {
		super(driver);
	}
	
	public EditAccountPage selectAccountType(AccountType accountType) {
		logger.info("Select Account Type: " + accountType);
		Select accountTypeSelect = new Select(driver.findElement(accountTypeSelectBy));
		accountTypeSelect.selectByVisibleText(accountType.getName());
		return this;
	}
	
	public AccountType getAccountType() {
		logger.info("Get Account Type.");
		Select accountTypeSelect = new Select(driver.findElement(accountTypeSelectBy));
		String accountType = accountTypeSelect.getFirstSelectedOption().getText();
		logger.info("Account Type: " + accountType);
		return AccountType.getFrom(accountType);
	}
	
	
	public AccountUpdateMsgPage clickSubmitButton() {
		logger.info("Click Submit Button");
		driver.findElement(submitBtnBy).click();
		return new AccountUpdateMsgPage(driver);
	}
	
	public void clickResetButton() {
		logger.info("Click Reset Button");
		driver.findElement(resetBtnBy).click();
	}

}
