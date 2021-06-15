package com.banking.tests.login;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.banking.bo.User;
import com.banking.driver.DriverManager;
import com.banking.pages.HomeBankPage;
import com.banking.pages.LoginPage;
import com.banking.tests.BaseTest;
import com.banking.utils.PropertiesUtil;

public class LoginTest extends BaseTest {
	
	private static final String WRONG_PASSWORD = "sss";
	private static final String PASSWORD_INVALID_MESSAGE = "User or Password is not valid";
	
	@Test
	public void loginTest() {
		//Login to Banking App
		User user = new User(PropertiesUtil.getUserName(), PropertiesUtil.getPassword());
		LoginPage loginPage = new LoginPage(DriverManager.getDriver());
		HomeBankPage homePage = loginPage.doLogin(user);
		
		//Verify Navigation component is displayed
		boolean isNavigationMenuDisplayed = homePage.getNavigationComponent().isNavigationMenuDisplayed();
		Assert.assertTrue(isNavigationMenuDisplayed, "Navigation Menu is not displayed");	
	}
	
	@Test
	public void loginWrongPasswordTest() {
		//Login To Banking App with wrong password
		LoginPage loginPage = new LoginPage(DriverManager.getDriver());
		loginPage.doLoginWithInvalidData(PropertiesUtil.getUserName(), WRONG_PASSWORD);
		
		//Verify Alert is diaplayed
		boolean isAlertDisplayed = loginPage.isAlertPresent();
		Assert.assertTrue(isAlertDisplayed, "Alert is not displayed");
		
		//Verify Alert Message
		String alertMessage = loginPage.getAlertText();
		Assert.assertEquals(alertMessage, PASSWORD_INVALID_MESSAGE, "Alert message is wrong.");
	}

}
