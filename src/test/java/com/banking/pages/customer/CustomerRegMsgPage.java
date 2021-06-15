package com.banking.pages.customer;

import org.openqa.selenium.By;

import com.banking.driver.DriverUtils;
import com.banking.pages.BasePage;

public class CustomerRegMsgPage extends BasePage {

	private static final By customerContainerBy = By.id("customer");
	private static final By customerIdLblBy = By.xpath("//td[text()='Customer ID']/following-sibling::td[1]");
	private static final By customerNameLblBy = By.xpath("//td[text()='Customer Name']/following-sibling::td[1]");
	private static final By genderLblBy = By.xpath("//td[text()='Gender']/following-sibling::td[1]");
	private static final By birthdateLblBy = By.xpath("//td[text()='Birthdate']/following-sibling::td[1]");
	private static final By addressLblBy = By.xpath("//td[text()='Address']/following-sibling::td[1]");
	private static final By cityLblBy = By.xpath("//td[text()='City']/following-sibling::td[1]");
	private static final By stateLblBy = By.xpath("//td[text()='State']/following-sibling::td[1]");
	private static final By pinLblBy = By.xpath("//td[text()='Pin']/following-sibling::td[1]");
	private static final By mobileNumberLblBy = By.xpath("//td[text()='Mobile No.']/following-sibling::td[1]");
	private static final By emailLblBy = By.xpath("//td[text()='Email']/following-sibling::td[1]");

	public CustomerRegMsgPage(DriverUtils driver) {
		super(driver);
	}

	public int getCustomerId() {
		logger.info("Get Customer Id.");
		String customerId = driver.findElement(customerContainerBy).findElement(customerIdLblBy).getText();
		logger.info("Customer Id: " + customerId);
		return Integer.parseInt(customerId);
	}

	public String getCustomerName() {
		logger.info("Get Customer Name.");
		String customerName = driver.findElement(customerContainerBy).findElement(customerNameLblBy).getText();
		logger.info("Customer Name: " + customerName);
		return customerName;
	}

	public String getGender() {
		logger.info("Get Gender.");
		String gender = driver.findElement(customerContainerBy).findElement(genderLblBy).getText();
		logger.info("Gender: " + gender);
		return gender;
	}

	public String getDateOfBirth() {
		logger.info("Get Date Of Birth.");
		String dob = driver.findElement(customerContainerBy).findElement(birthdateLblBy).getText();
		logger.info("Date Of Birth: " + dob);
		return dob;
	}

	public String getAddress() {
		logger.info("Get Address.");
		String address = driver.findElement(customerContainerBy).findElement(addressLblBy).getText();
		logger.info("Address: " + address);
		return address;
	}

	public String getCity() {
		logger.info("Get City.");
		String city = driver.findElement(customerContainerBy).findElement(cityLblBy).getText();
		logger.info("City: " + city);
		return city;
	}
	
	public String getState() {
		logger.info("Get State.");
		String state = driver.findElement(customerContainerBy).findElement(stateLblBy).getText();
		logger.info("State: " + state);
		return state;
	}
	
	public String getPin() {
		logger.info("Get PIN.");
		String pin = driver.findElement(customerContainerBy).findElement(pinLblBy).getText();
		logger.info("PIN: " + pin);
		return pin;
	}
	
	public String getMobilePhone() {
		logger.info("Get Mobile Phone.");
		String mobilePhone = driver.findElement(customerContainerBy).findElement(mobileNumberLblBy).getText();
		logger.info("Mobile Phone: " + mobilePhone);
		return mobilePhone;
	}
	
	public String getEmail() {
		logger.info("Get Email.");
		String email = driver.findElement(customerContainerBy).findElement(emailLblBy).getText();
		logger.info("Email: " + email);
		return email;
	}
	
}
