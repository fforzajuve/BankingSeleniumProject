package com.banking.pages.customer;

import java.util.stream.Collectors;
import org.openqa.selenium.By;
import com.banking.bo.Customer;
import com.banking.driver.DriverUtils;
import com.banking.enums.Gender;
import com.banking.pages.BasePage;

public class AddCustomerPage extends BasePage {

	private static final By customerNameTxtBy = By.name("name");
	private static final By genderRadioBtnBy = By.name("rad1");
	private static final By dobTxtBy = By.name("dob");
	private static final By addressTxtBy = By.name("addr");
	private static final By cityTxtBy = By.name("city");
	private static final By stateTxtBy = By.name("state");
	private static final By pinTxtBy = By.name("pinno");
	private static final By mobileNumberTxtBy = By.name("telephoneno");
	private static final By emailTxtBy = By.name("emailid");
	private static final By passwordTxtBy = By.name("password");
	private static final By submitBtnBy = By.name("sub");
	private static final By resetBtnBy = By.name("res");

	public AddCustomerPage(DriverUtils driver) {
		super(driver);
	}

	public CustomerRegMsgPage addCustomer(Customer customer) {
		enterCustomerName(customer.getCustomerName());
		selectGender(customer.getGender());
		selectDateOfBirth(customer.getDob());
		enterAddress(customer.getAddress());
		enterCity(customer.getCity());
		enterState(customer.getState());
		enterPIN(customer.getPin());
		enterMobileNumber(customer.getMobileNumber());
		enterEmail(customer.getEmail());
		enterPassword(customer.getPassword());
		clickSubmitButton();
		return new CustomerRegMsgPage(driver);
	}

	public AddCustomerPage enterCustomerName(String customerName) {
		logger.info("Enter Customer Name: " + customerName);
		driver.findElement(customerNameTxtBy).sendKeys(customerName);
		return this;
	}

	public AddCustomerPage selectGender(Gender gender) {
		logger.info("Select Gender: " + gender);
		driver.findElements(genderRadioBtnBy).stream()
				.filter(genderElement -> genderElement.getAttribute("value").equals(gender.getName()))
				.collect(Collectors.toList()).get(0).click();
		return this;
	}

	public AddCustomerPage selectDateOfBirth(String dof) {
		logger.info("Select Date Of Birth: " + dof);
		driver.findElement(dobTxtBy).sendKeys(dof);
		return this;
	}

	public AddCustomerPage enterAddress(String address) {
		logger.info("Enter Address " + address);
		driver.findElement(addressTxtBy).sendKeys(address);
		return this;
	}

	public AddCustomerPage enterCity(String city) {
		logger.info("Enter City " + city);
		driver.findElement(cityTxtBy).sendKeys(city);
		return this;
	}

	public AddCustomerPage enterState(String state) {
		logger.info("Enter State " + state);
		driver.findElement(stateTxtBy).sendKeys(state);
		return this;
	}

	public AddCustomerPage enterPIN(String pin) {
		logger.info("Enter PIN " + pin);
		driver.findElement(pinTxtBy).sendKeys(pin);
		return this;
	}

	public AddCustomerPage enterMobileNumber(String mobileNumber) {
		logger.info("Enter Mobile Number " + mobileNumber);
		driver.findElement(mobileNumberTxtBy).sendKeys(mobileNumber);
		return this;
	}

	public AddCustomerPage enterEmail(String email) {
		logger.info("Enter Email " + email);
		driver.findElement(emailTxtBy).sendKeys(email);
		return this;
	}

	public AddCustomerPage enterPassword(String password) {
		logger.info("Enter Password " + password);
		driver.findElement(passwordTxtBy).sendKeys(password);
		return this;
	}

	public AddCustomerPage clickSubmitButton() {
		logger.info("Click Submit Button.");
		driver.findElement(submitBtnBy).click();
		return this;
	}

	public AddCustomerPage clickResetButton() {
		logger.info("Click Reset Button.");
		driver.findElement(resetBtnBy).click();
		return this;
	}
}
