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
import com.banking.pages.account.DeleteAccountInputPage;
import com.banking.tests.BaseTest;

import com.banking.utils.PropertiesUtil;

public class DeleteAccountTest extends BaseTest {

	private static final String ACCOUNT_DELETED_MESSAGE = "Account Deleted Sucessfully";
	private static final double INITIAL_DEPOSIT = 500;
	private static final AccountType ACCOUNT_TYPE = AccountType.SAVINGS;
	private static final int CUSTOMER_ID = 84217;

	private String userId = PropertiesUtil.getUserName();
	private String password = PropertiesUtil.getPassword();

	@Test
	public void deleteAccountTest() {
		//Create Account
		int accountId = createAccount();
		//Select Delete Account Menu
		HomeBankPage homePage = new HomeBankPage(DriverManager.getDriver());
		DeleteAccountInputPage deleteAccountInputPage = homePage.getNavigationComponent().selectDeleteAccountMenu();
		//Delete Account
		String message = deleteAccountInputPage.deleteAccount(accountId);
		
		//Verify Delete Account Message
		Assert.assertEquals(message, ACCOUNT_DELETED_MESSAGE, "Account is not deleted");
	}

	private int createAccount() {
		// Login to Banking App
		User user = new User(PropertiesUtil.getUserName(), PropertiesUtil.getPassword());
		LoginPage loginPage = new LoginPage(DriverManager.getDriver());
		HomeBankPage homePage = loginPage.doLogin(user);
		
		// Create New Account
		AddAccountPage addAccountPage = homePage.getNavigationComponent().selectNewAccountMenu();
		AccountCreateMessagePage accountCreateMessagePage = addAccountPage.addNewAccount(CUSTOMER_ID, ACCOUNT_TYPE, INITIAL_DEPOSIT);
		return accountCreateMessagePage.getAccountId();
	}
}
