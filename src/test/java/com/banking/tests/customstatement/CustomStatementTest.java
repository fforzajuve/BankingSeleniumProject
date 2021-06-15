package com.banking.tests.customstatement;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.banking.bo.Transaction;
import com.banking.bo.User;
import com.banking.driver.DriverManager;
import com.banking.pages.HomeBankPage;
import com.banking.pages.LoginPage;
import com.banking.pages.balance.BalanceEnquiryInputPage;
import com.banking.pages.balance.BalanceEnquiryPage;
import com.banking.pages.customstatement.CustomisedStatementDetailsPage;
import com.banking.pages.customstatement.CustomisedStatementInputPage;
import com.banking.tests.BaseTest;
import com.banking.utils.PropertiesUtil;

public class CustomStatementTest extends BaseTest {

	private static final int TRANSACTION_NUMBER = 100;
	private static final int MIN_TRANSACTION_VALUE = 1;
	private static final int ACCOUNT_ID = 93372;

	@Test
	public void selectTransactionsTest() {
		// Login to Banking App
		User user = new User(PropertiesUtil.getUserName(), PropertiesUtil.getPassword());
		LoginPage loginPage = new LoginPage(DriverManager.getDriver());
		HomeBankPage homePage = loginPage.doLogin(user);

		// Open CustomStatement Menu and select transactions for account
		CustomisedStatementInputPage customisedStatementInputPage = homePage.getNavigationComponent()
				.selectCustomStatementMenu();
		CustomisedStatementDetailsPage customisedStatementDetailsPage = customisedStatementInputPage
				.selectTransactionsForAccount(ACCOUNT_ID, MIN_TRANSACTION_VALUE, TRANSACTION_NUMBER);

		// Get All transactions
		List<Transaction> transactions = customisedStatementDetailsPage.getAllTransactions();

		// Get Debit
		double totalDebit = transactions.stream().filter(tran -> tran.getTransactionType().equalsIgnoreCase("d"))
				.reduce(0.0, (res, t1) -> res + t1.getAmount(), Double::sum);

		// Get Withdrawal
		double totalWithdrawal = transactions.stream().filter(tran -> tran.getTransactionType().equalsIgnoreCase("w"))
				.reduce(0.0, (res, t1) -> res + t1.getAmount(), Double::sum);
		
		double totalBalance = totalDebit - totalWithdrawal;

		// Open Balance Enquiry and select account
		BalanceEnquiryInputPage balanceEnquiryInputPage = customisedStatementDetailsPage.getNavigationComponent()
				.selectBalanceEnquiryMenu();
		BalanceEnquiryPage balanceEnquiryPage = balanceEnquiryInputPage.selectAccount(ACCOUNT_ID);
		
		//Get Balance for Account
		double balance = balanceEnquiryPage.getBalance();
		
		//Verify balance and total transactions
		Assert.assertEquals(totalBalance, balance, "Transactions are wrong.");
	}

}
