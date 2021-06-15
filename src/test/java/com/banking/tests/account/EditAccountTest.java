package com.banking.tests.account;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.banking.bo.User;
import com.banking.driver.DriverManager;
import com.banking.enums.AccountType;
import com.banking.pages.HomeBankPage;
import com.banking.pages.LoginPage;
import com.banking.pages.account.AccountUpdateMsgPage;
import com.banking.pages.account.EditAccountInputPage;
import com.banking.pages.account.EditAccountPage;
import com.banking.tests.BaseTest;
import com.banking.utils.PropertiesUtil;

public class EditAccountTest extends BaseTest {
	
	private static final int ACCOUNT_ID = 93374;
	private static final String ACCOUNT_UPDATED_MESSAGE = "Account details updated Successfully!!!";

	@Test
	public void editAccountTest() {
		//Login to Banking App
		User user = new User(PropertiesUtil.getUserName(), PropertiesUtil.getPassword());
		LoginPage loginPage = new LoginPage(DriverManager.getDriver());
		HomeBankPage homePage = loginPage.doLogin(user);

		// Open Edit Account  Menu
		EditAccountInputPage editAccountInputPage = homePage.getNavigationComponent().selectEditAccountMenu();
		
		//Select Account
		EditAccountPage editAccountPage = editAccountInputPage.selectAccount(ACCOUNT_ID);
		
		//Get Account Type
		AccountType accountType = editAccountPage.getAccountType();
		
		//Change AccountType
		AccountType newAccountType = accountType == AccountType.SAVINGS ? AccountType.CURRENT :AccountType.SAVINGS;
		editAccountPage.selectAccountType(newAccountType);
		AccountUpdateMsgPage accountUpdateMsgPage = editAccountPage.clickSubmitButton();
		
		//Verify Account Updated Message
		String headingMessage = accountUpdateMsgPage.getInfoMessage();
		Assert.assertEquals(headingMessage, ACCOUNT_UPDATED_MESSAGE, "Message is wrong");
		
		//Verify Account Type
		AccountType updatedAccountType = accountUpdateMsgPage.getAccountType();
		Assert.assertEquals(updatedAccountType, newAccountType, "Account Type is not Updated");
	}

}
