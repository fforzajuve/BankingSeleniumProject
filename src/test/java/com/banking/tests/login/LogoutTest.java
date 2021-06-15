package com.banking.tests.login;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.banking.bo.User;
import com.banking.driver.DriverManager;
import com.banking.pages.HomeBankPage;
import com.banking.pages.LoginPage;
import com.banking.tests.BaseTest;
import com.banking.utils.MyLogger;
import com.banking.utils.PropertiesUtil;

public class LogoutTest extends BaseTest {

	private static final String LOGOUT_MESSAGE = "You Have Succesfully Logged Out!!";

	@Test
	public void logoutTest() {
		//Login to Banking App
		User user = new User(PropertiesUtil.getUserName(), PropertiesUtil.getPassword());
		LoginPage loginPage = new LoginPage(DriverManager.getDriver());
		HomeBankPage homePage = loginPage.doLogin(user);
		// Click Logout
		homePage.getNavigationComponent().selectLogoutMenu();

		// Verify Alert is diaplayed
		boolean isAlertDisplayed = loginPage.isAlertPresent();
		Assert.assertTrue(isAlertDisplayed, "Alert is not displayed");

		// Verify Alert Message
		String alertMessage = loginPage.getAlertText();
		Assert.assertEquals(alertMessage, LOGOUT_MESSAGE, "Logout message message is wrong.");
		
		//Verify Logout
		boolean isLogout = loginPage.isLoginFormDisplayed();
		Assert.assertTrue(isLogout, "User is not logout");
	}

}
