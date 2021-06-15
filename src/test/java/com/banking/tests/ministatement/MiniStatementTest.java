package com.banking.tests.ministatement;

import java.util.Arrays;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.banking.bo.Transaction;
import com.banking.bo.User;
import com.banking.driver.DriverManager;
import com.banking.pages.HomeBankPage;
import com.banking.pages.LoginPage;
import com.banking.pages.ministatement.MiniStatementDetailsPage;
import com.banking.pages.ministatement.MiniStatementInputPage;
import com.banking.tests.BaseTest;
import com.banking.utils.PropertiesUtil;

public class MiniStatementTest extends BaseTest {

	private static final int ACCOUNT_ID = 93372;
	private static final List<String> TRANSACTION_HEADERS = Arrays.asList("Transaction ID", "Amount",
			"Transaction Type", "Date of Transaction", "Description");

	@Test
	public void firstTransactionTest() {
		// Login to Banking App
		User user = new User(PropertiesUtil.getUserName(), PropertiesUtil.getPassword());
		LoginPage loginPage = new LoginPage(DriverManager.getDriver());
		HomeBankPage homePage = loginPage.doLogin(user);

		// Open MiniStatement Menu and select account
		MiniStatementInputPage miniStatementInputPage = homePage.getNavigationComponent().selectMiniStatementMenu();
		MiniStatementDetailsPage miniStatementDetailsPage = miniStatementInputPage.selectAccount(ACCOUNT_ID);

		// Verify Transaction Headers
		List<String> transactionHeaders = miniStatementDetailsPage.getTransctionHeaders();
		Assert.assertEquals(transactionHeaders, TRANSACTION_HEADERS, "Transaction Headers are wrong.");

		// Verify First Transaction
		Transaction transaction = miniStatementDetailsPage.getTransactionByIndex(2);
		System.out.println(transaction);

		// Verify Transaction Amount
		Assert.assertTrue(transaction.getAmount() > 0, "Amount is wrong");

		// Verify Transaction Amount
		String transactionType = transaction.getTransactionType();
		Assert.assertTrue("wd".contains(transactionType), "Transaction Type is wrong.");
	}

	@Test
	public void allTransactionsTest() {
		// Login to Banking App
		User user = new User(PropertiesUtil.getUserName(), PropertiesUtil.getPassword());
		LoginPage loginPage = new LoginPage(DriverManager.getDriver());
		HomeBankPage homePage = loginPage.doLogin(user);

		// Open MiniStatement Menu and select account
		MiniStatementInputPage miniStatementInputPage = homePage.getNavigationComponent().selectMiniStatementMenu();
		MiniStatementDetailsPage miniStatementDetailsPage = miniStatementInputPage.selectAccount(ACCOUNT_ID);

		// Get All Transactions
		List<Transaction> transactions = miniStatementDetailsPage.getAllTransactions();
		Assert.assertTrue(transactions.size()> 0 && transactions.size() <=5, "Number of transactions is wrong.");
	}

}
