package com.banking.tests.balance;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.banking.bo.User;
import com.banking.driver.DriverManager;
import com.banking.pages.HomeBankPage;
import com.banking.pages.LoginPage;
import com.banking.pages.balance.BalanceEnquiryInputPage;
import com.banking.pages.balance.BalanceEnquiryPage;
import com.banking.tests.BaseTest;
import com.banking.utils.PropertiesUtil;

public class BalanceEnquiryTest extends BaseTest {

	private static final int ACCOUNT_ID = 93372;

	@Test
	public void balanceEnquiryTest() {
		// Login to Banking App
		User user = new User(PropertiesUtil.getUserName(), PropertiesUtil.getPassword());
		LoginPage loginPage = new LoginPage(DriverManager.getDriver());
		HomeBankPage homePage = loginPage.doLogin(user);

		// Open Balance Page and Select Account
		BalanceEnquiryInputPage balanceEnquiryInputPage = homePage.getNavigationComponent().selectBalanceEnquiryMenu();
		BalanceEnquiryPage balanceEnquiryPage =  balanceEnquiryInputPage.selectAccount(ACCOUNT_ID);
		
		//Verify Account Id
		int accountId = balanceEnquiryPage.getAccountId();
		Assert.assertTrue(accountId == ACCOUNT_ID , "Account ID is wrong");
		
		//Verify Balance
		double balance = balanceEnquiryPage.getBalance();
		Assert.assertTrue(balance > 100, "Balance is wrong");
	}

}
