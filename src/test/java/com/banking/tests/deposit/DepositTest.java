package com.banking.tests.deposit;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.banking.bo.User;
import com.banking.driver.DriverManager;
import com.banking.pages.HomeBankPage;
import com.banking.pages.LoginPage;
import com.banking.pages.balance.BalanceEnquiryInputPage;
import com.banking.pages.balance.BalanceEnquiryPage;
import com.banking.pages.deposit.DepositInputPage;
import com.banking.pages.deposit.DepositPage;
import com.banking.tests.BaseTest;
import com.banking.utils.PropertiesUtil;

public class DepositTest extends BaseTest {

	private static final String TRANSACTION_TYPE = "Deposit";
	private static final String DEPOSIT_DESCRIPTION = "Deposit";
	private static final int AMOUNT = 100;
	private static final int ACCOUNT_ID = 93372;

	private double initialBalance;

	@Test
	public void depositTest() {
		// Login to Banking App
		User user = new User(PropertiesUtil.getUserName(), PropertiesUtil.getPassword());
		LoginPage loginPage = new LoginPage(DriverManager.getDriver());
		HomeBankPage homePage = loginPage.doLogin(user);

		// Open Balance Page and Select Account
		BalanceEnquiryInputPage balanceEnquiryInputPage = homePage.getNavigationComponent().selectBalanceEnquiryMenu();
		BalanceEnquiryPage balanceEnquiryPage = balanceEnquiryInputPage.selectAccount(ACCOUNT_ID);

		// Get Initial Balance
		initialBalance = balanceEnquiryPage.getBalance();

		// Open Deposit Page and put deposit to account
		DepositInputPage depositInputPage = balanceEnquiryPage.getNavigationComponent().selectDepositMenu();
		DepositPage depositPage = depositInputPage.putDepositToAccount(ACCOUNT_ID, AMOUNT, DEPOSIT_DESCRIPTION);

		// Verify Account Id
		int accountId = depositPage.getAccountId();
		Assert.assertEquals(accountId, ACCOUNT_ID, "Account Id is wrong.");

		// Verify Deposit Amount
		double amount = depositPage.getAmount();
		Assert.assertEquals(amount, AMOUNT, "Amount is wrong.");

		// Verify Transaction Type
		String transactionType = depositPage.getTransactionType();
		Assert.assertEquals(transactionType, TRANSACTION_TYPE, "Transaction Type is wrong.");

		// Verify Description
		String desc = depositPage.getDescription();
		Assert.assertEquals(desc, DEPOSIT_DESCRIPTION, "Description is wrong.");

		// Verify Balance
		double balance = depositPage.getBalance();
		Assert.assertEquals(balance, initialBalance + AMOUNT, "Balance is wrong.");
	}

}
