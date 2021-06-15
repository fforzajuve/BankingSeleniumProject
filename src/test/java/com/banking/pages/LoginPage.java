package com.banking.pages;

import org.openqa.selenium.By;

import com.banking.bo.User;
import com.banking.driver.DriverUtils;

public class LoginPage extends BasePage {

	private static final By loginContainerBy = By.name("frmLogin");
	private static final By userIdTxtBy = By.name("uid");
	private static final By passwordTxtBy = By.name("password");
	private static final By loginBtnBy = By.name("btnLogin");

	public LoginPage(DriverUtils driver) {
		super(driver);
	}

	public HomeBankPage doLogin(User user) {
		logger.info("Login to Banking App:  User Id: " + user.getUserName() + ", Password: " + user.getPassword());
		enterUserId(user.getUserName());
		enterPassword(user.getPassword());
		clickLoginButton();
		return new HomeBankPage(driver);
	}

	public void doLoginWithInvalidData(String userId, String password) {
		logger.info("Login to Banking app with invalid data :  User Id: " + userId + ", Password: " + password);
		enterUserId(userId);
		enterPassword(password);
		clickLoginButton();
	}

	public LoginPage enterUserId(String userId) {
		logger.info("Enter User ID: " + userId);
		driver.findElement(userIdTxtBy).clear();
		driver.findElement(userIdTxtBy).sendKeys(userId);
		return this;
	}

	public LoginPage enterPassword(String password) {
		logger.info("Enter Password: " + password);
		driver.findElement(passwordTxtBy).clear();
		driver.findElement(passwordTxtBy).sendKeys(password);
		return this;
	}

	public void clickLoginButton() {
		logger.info("Click Login Button.");
		driver.findElement(loginBtnBy).click();
	}

	public boolean isLoginFormDisplayed() {
		boolean isLoginFormDisplayed = driver.isElementDisplayed(loginContainerBy);
		logger.info("Is Logon Form Displayed? " + isLoginFormDisplayed);
		return isLoginFormDisplayed;
	}

}
