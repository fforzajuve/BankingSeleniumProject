package com.banking.tests.fundtransfer;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.banking.bo.User;
import com.banking.driver.DriverManager;
import com.banking.pages.HomeBankPage;
import com.banking.pages.LoginPage;
import com.banking.pages.fundtransfer.FundTransferDetailsPage;
import com.banking.pages.fundtransfer.FundTransferInputPage;
import com.banking.tests.BaseTest;
import com.banking.utils.PropertiesUtil;

public class FundTransferTest extends BaseTest {

	private static final int AMOUNT = 100;
	private static final int PAYEE_ACCOUNT_ID = 93372;
	private static final int PAYER_ACCOUNT_ID = 93374;
	private static final String DESCRIPTION = "fund transfer";

	@Test
	public void fundTransferTest() {
		// Login to Banking App
		User user = new User(PropertiesUtil.getUserName(), PropertiesUtil.getPassword());
		LoginPage loginPage = new LoginPage(DriverManager.getDriver());
		HomeBankPage homePage = loginPage.doLogin(user);

		// Open Fund Transfer Menu
		FundTransferInputPage fundTransferInputPage = homePage.getNavigationComponent().selectFundTransferMenu();

		// Transfer fund from one account to another
		FundTransferDetailsPage fundTransferDetailsPage = fundTransferInputPage.transferFund(PAYER_ACCOUNT_ID,
				PAYEE_ACCOUNT_ID, AMOUNT, DESCRIPTION);

		// Verify Payer Id
		int payerId = fundTransferDetailsPage.getPayerAccountId();
		Assert.assertEquals(payerId, PAYER_ACCOUNT_ID, "Payer Id is wrong");

		// Verify Payee Id
		int payeeId = fundTransferDetailsPage.getPayeeAccountId();
		Assert.assertEquals(payeeId, PAYEE_ACCOUNT_ID, "Payee Id is wrong");

		// Verify Amount
		double amount = fundTransferDetailsPage.getAmount();
		Assert.assertEquals(amount, AMOUNT, "Amount is wrong");

		// Verify Description
		String desc = fundTransferDetailsPage.getDescription();
		Assert.assertEquals(desc, DESCRIPTION, "Description is wrong");
	}

}
