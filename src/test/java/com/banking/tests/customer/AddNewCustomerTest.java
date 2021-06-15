package com.banking.tests.customer;

import org.apache.commons.lang3.RandomStringUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.banking.bo.Customer;
import com.banking.bo.User;
import com.banking.driver.DriverManager;
import com.banking.enums.Gender;
import com.banking.pages.HomeBankPage;
import com.banking.pages.LoginPage;
import com.banking.pages.customer.AddCustomerPage;
import com.banking.pages.customer.CustomerRegMsgPage;
import com.banking.tests.BaseTest;
import com.banking.utils.PropertiesUtil;

public class AddNewCustomerTest extends BaseTest {

	@Test
	public void addNewCustomerTest() {
		//Create Customer object
		Customer customer = new Customer.CustomerBuilder("Dimon").gender(Gender.FEMALE).dob("09/01/1985")
				.address("Borovaja").city("Minsk").state("Belarus").pin("123456").mobileNumber("1234556")
				.email("@mail.ru").password(RandomStringUtils.randomAlphabetic(8)).build();

		//Login to Banking App
		User user = new User(PropertiesUtil.getUserName(), PropertiesUtil.getPassword());
		LoginPage loginPage = new LoginPage(DriverManager.getDriver());
		HomeBankPage homePage = loginPage.doLogin(user);

		// Create new Customer
		AddCustomerPage addCustomerPage = homePage.getNavigationComponent().selectNewCusomerMenu();
		CustomerRegMsgPage customerRegMsgPage = addCustomerPage.addCustomer(customer);

		// Verify Customer ID
		int customerId = customerRegMsgPage.getCustomerId();
		Assert.assertTrue(customerId > 0, "Customer Id is wrong");

		// Verify Customer Name
		String customerName = customerRegMsgPage.getCustomerName();
		Assert.assertEquals(customerName, customer.getCustomerName(), "Customer Id is wrong");

		// Verify Gender
		String gender = customerRegMsgPage.getGender();
		Assert.assertTrue(gender.startsWith(customer.getGender().getName()), "Gender is wrong");

		// Verify Address
		String address = customerRegMsgPage.getAddress();
		Assert.assertEquals(address, customer.getAddress(), "Address is wrong");

		// Verify City
		String city = customerRegMsgPage.getCity();
		Assert.assertEquals(city, customer.getCity(), "City is wrong");

		// Verify State
		String state = customerRegMsgPage.getState();
		Assert.assertEquals(state, customer.getState(), "State is wrong");

		// Verify PIN
		String pin = customerRegMsgPage.getPin();
		Assert.assertEquals(pin, customer.getPin(), "PIN is wrong");

		// Verify Mobile Phone
		String mobilePhone = customerRegMsgPage.getMobilePhone();
		Assert.assertEquals(mobilePhone, customer.getMobileNumber(), "Mobile Phone is wrong");

		// Verify Email
		String email = customerRegMsgPage.getEmail();
		Assert.assertEquals(email, customer.getEmail(), "Email is wrong");
	}

}
