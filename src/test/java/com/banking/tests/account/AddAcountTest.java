package com.banking.tests.account;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.banking.bo.User;
import com.banking.driver.DriverManager;
import com.banking.enums.AccountType;
import com.banking.pages.HomeBankPage;
import com.banking.pages.LoginPage;
import com.banking.pages.account.AccountCreateMessagePage;
import com.banking.pages.account.AddAccountPage;
import com.banking.tests.BaseTest;
import com.banking.utils.PropertiesUtil;

public class AddAcountTest extends BaseTest {

	private static final String ACCOUNT_CREATED_MESSAGE = "Account Generated Successfully!!!";
	private static final double INITIAL_DEPOSIT = 500;
	private static final AccountType ACCOUNT_TYPE = AccountType.SAVINGS;
	private static final int CUSTOMER_ID = 84217;

	@Test
	public void createAccountTest() {
		//Login to Banking App
		User user = new User(PropertiesUtil.getUserName(), PropertiesUtil.getPassword());
		LoginPage loginPage = new LoginPage(DriverManager.getDriver());
		HomeBankPage homePage = loginPage.doLogin(user);
		
		// Create New Account
		AddAccountPage addAccountPage = homePage.getNavigationComponent().selectNewAccountMenu();
		AccountCreateMessagePage accountCreateMessagePage = addAccountPage.addNewAccount(CUSTOMER_ID,
				ACCOUNT_TYPE, INITIAL_DEPOSIT);
		
		//Verify Heading
		String heading = accountCreateMessagePage.getInfoMessage();
		Assert.assertEquals(heading, ACCOUNT_CREATED_MESSAGE, "Account is not created");

		// Verify Account Id
		int accountId = accountCreateMessagePage.getAccountId();
		Assert.assertTrue(accountId > 0, "Account Id is wrong.");

		// Verify Customer Id
		int customerId = accountCreateMessagePage.getCustomerId();
		Assert.assertTrue(customerId == CUSTOMER_ID, "Customer Id is wrong.");
		
		//Verify Account Type
		AccountType accountType = accountCreateMessagePage.getAccountType();
		Assert.assertEquals(accountType, ACCOUNT_TYPE, "Account Type is wrong");
		
		//Verify Account Amount 
		double amount = accountCreateMessagePage.getAccountAmount();
		Assert.assertEquals(amount, INITIAL_DEPOSIT, "Account Amount is wrong");
	}

}
