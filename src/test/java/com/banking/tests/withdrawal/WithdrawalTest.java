package com.banking.tests.withdrawal;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.banking.bo.User;
import com.banking.driver.DriverManager;
import com.banking.pages.HomeBankPage;
import com.banking.pages.LoginPage;
import com.banking.pages.balance.BalanceEnquiryInputPage;
import com.banking.pages.balance.BalanceEnquiryPage;
import com.banking.pages.withdrawal.WithdrawalDeatailsPage;
import com.banking.pages.withdrawal.WithdrawalInputPage;
import com.banking.tests.BaseTest;
import com.banking.utils.PropertiesUtil;

public class WithdrawalTest extends BaseTest {

	private static final String WITHDRAWAL_DESCRIPTION = "Take money";
	private static final String TRANSACTION_TYPE = "Withdrawal";
	private static final int AMOUNT = 100;
	private static final int ACCOUNT_ID = 93372;

	private double initialBalance;

	@Test
	public void withdrawalTest() {
		// Login to Banking App
		User user = new User(PropertiesUtil.getUserName(), PropertiesUtil.getPassword());
		LoginPage loginPage = new LoginPage(DriverManager.getDriver());
		HomeBankPage homePage = loginPage.doLogin(user);

		// Open Balance Page and Select Account
		BalanceEnquiryInputPage balanceEnquiryInputPage = homePage.getNavigationComponent().selectBalanceEnquiryMenu();
		BalanceEnquiryPage balanceEnquiryPage = balanceEnquiryInputPage.selectAccount(ACCOUNT_ID);

		// Get Initial Balance
		initialBalance = balanceEnquiryPage.getBalance();

		// Open Withdrawal Page and take money from account
		WithdrawalInputPage withdrawalInputPage = balanceEnquiryPage.getNavigationComponent().selectWithdrawalMenu();
		WithdrawalDeatailsPage withdrawalDeatailsPage = withdrawalInputPage.withdrawMoneyFromAccount(ACCOUNT_ID, AMOUNT,
				WITHDRAWAL_DESCRIPTION);

		// Verify Account Id
		int accountId = withdrawalDeatailsPage.getAccountId();
		Assert.assertEquals(accountId, ACCOUNT_ID, "Account Id is wrong.");

		// Verify Deposit Amount
		double amount = withdrawalDeatailsPage.getAmount();
		Assert.assertEquals(amount, AMOUNT, "Amount is wrong.");

		// Verify Transaction Type
		String transactionType = withdrawalDeatailsPage.getTransactionType();
		Assert.assertEquals(transactionType, TRANSACTION_TYPE, "Transaction Type is wrong.");

		// Verify Description
		String desc = withdrawalDeatailsPage.getDescription();
		Assert.assertEquals(desc, WITHDRAWAL_DESCRIPTION, "Description is wrong.");

		// Verify Balance
		double balance = withdrawalDeatailsPage.getBalance();
		Assert.assertEquals(balance, initialBalance - AMOUNT, "Balance is wrong.");
	}

}
