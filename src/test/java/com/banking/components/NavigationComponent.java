package com.banking.components;

import org.openqa.selenium.By;

import com.banking.driver.DriverUtils;
import com.banking.enums.NavigationMenu;
import com.banking.pages.BasePage;
import com.banking.pages.account.AddAccountPage;
import com.banking.pages.account.DeleteAccountInputPage;
import com.banking.pages.account.EditAccountInputPage;
import com.banking.pages.balance.BalanceEnquiryInputPage;
import com.banking.pages.customer.AddCustomerPage;
import com.banking.pages.customstatement.CustomisedStatementInputPage;
import com.banking.pages.deposit.DepositInputPage;
import com.banking.pages.fundtransfer.FundTransferInputPage;
import com.banking.pages.ministatement.MiniStatementInputPage;
import com.banking.pages.withdrawal.WithdrawalInputPage;

public class NavigationComponent extends BaseComponent {
	
	private static final By navigationContainerBy = By.className("menusubnav");
	private static final By addNewCustomerLnkBy = By.linkText("New Customer");
	private static final By addNewAccountLnkBy = By.linkText("New Account");
	private static final By editAccountLnkBy = By.linkText("Edit Account");
	private static final By deleteAccountLnkBy = By.linkText("Delete Account");
	private static final By balanceEnquiryLnkBy = By.linkText("Balance Enquiry");
	private static final By depositLnkBy = By.linkText("Deposit");
	private static final By withdrawalLnkBy = By.linkText("Withdrawal");
	private static final By miniStatementLnkBy = By.linkText("Mini Statement");
	private static final By customStatementLnkBy = By.linkText("Customised Statement");
	private static final By fundTransferLnkBy = By.linkText("Fund Transfer");
	private static final By logoutLnkBy = By.linkText("Log out");
	private static final String NAVIGATION_LINK_FORMAT  = "//a[text()='%s']"; 
	

	public NavigationComponent(DriverUtils driver) {
		super(driver);
	}
	
	public AddCustomerPage selectNewCusomerMenu() {
		logger.info("Open New Customer Menu.");
		driver.findElement(navigationContainerBy).findElement(addNewCustomerLnkBy).click();
		return new AddCustomerPage(driver);
	}
	
	public AddAccountPage selectNewAccountMenu() {
		logger.info("Open New Account Menu.");
		driver.findElement(navigationContainerBy).findElement(addNewAccountLnkBy).click();
		return new AddAccountPage(driver);
	}
	
	public EditAccountInputPage selectEditAccountMenu() {
		logger.info("Open Edit Account Menu.");
		driver.findElement(navigationContainerBy).findElement(editAccountLnkBy).click();
		return new EditAccountInputPage(driver);
	}
	
	public DeleteAccountInputPage selectDeleteAccountMenu() {
		logger.info("Open Delete Account Menu.");
		driver.findElement(navigationContainerBy).findElement(deleteAccountLnkBy).click();
		return new DeleteAccountInputPage(driver);
	}
	
	public BalanceEnquiryInputPage selectBalanceEnquiryMenu() {
		logger.info("Open Balance Enquiry Menu.");
		driver.findElement(navigationContainerBy).findElement(balanceEnquiryLnkBy).click();
		return new BalanceEnquiryInputPage(driver);
	}
	
	public DepositInputPage selectDepositMenu() {
		logger.info("Open Deposit Menu.");
		driver.findElement(navigationContainerBy).findElement(depositLnkBy).click();
		return new DepositInputPage(driver);
	}
	
	public WithdrawalInputPage selectWithdrawalMenu() {
		logger.info("Open Withdrawal Menu.");
		driver.findElement(navigationContainerBy).findElement(withdrawalLnkBy).click();
		return new WithdrawalInputPage(driver);
	}
	
	public MiniStatementInputPage selectMiniStatementMenu() {
		logger.info("Open MiniStatement Menu.");
		driver.findElement(navigationContainerBy).findElement(miniStatementLnkBy).click();
		return new MiniStatementInputPage(driver);
	}
	
	public CustomisedStatementInputPage selectCustomStatementMenu() {
		logger.info("Open Customised Statement Menu.");
		driver.findElement(navigationContainerBy).findElement(customStatementLnkBy).click();
		return new CustomisedStatementInputPage(driver);
	}
	
	public FundTransferInputPage selectFundTransferMenu() {
		logger.info("Open Fund Transfer Menu.");
		driver.findElement(navigationContainerBy).findElement(fundTransferLnkBy).click();
		return new FundTransferInputPage(driver);
	}
	
	public void selectLogoutMenu() {
		logger.info("Click Logout Menu");
		driver.findElement(navigationContainerBy).findElement(logoutLnkBy).click();
	}
	
	
	public <P extends BasePage> P selectNavigationMenu(NavigationMenu navigationMenu, Class <P> pageClass){
		logger.info("Open " + navigationMenu.getName()  +" Menu");
		String navLink = String.format(NAVIGATION_LINK_FORMAT, navigationMenu.getName());
		driver.findElement(navigationContainerBy).findElement(By.xpath(navLink)).click();
		try{
			P pageObject = (P)pageClass.getConstructor(DriverUtils.class).newInstance(driver);
			return pageObject;
		}
		catch(final Exception e){
			throw new ClassCastException("QualityLibrary: Failed to create a new page window with the class: " + pageClass.toString());
		}
	}
	
	
	public <P extends BasePage> P selectNavigationMenuWithJS(NavigationMenu navigationMenu, Class <P> pageClass){
		logger.info("Open " + navigationMenu.getName()  +" Menu");
		String navLink = String.format(NAVIGATION_LINK_FORMAT, navigationMenu.getName());
		driver.clickElementWithJS(driver.findElement(navigationContainerBy).findElement(By.xpath(navLink)));
		try{
			P pageObject = (P)pageClass.getConstructor(DriverUtils.class).newInstance(driver);
			return pageObject;
		}
		catch(final Exception e){
			e.printStackTrace();
			throw new ClassCastException("QualityLibrary: Failed to create a new page window with the class: " + pageClass.toString());
		}
	}
	
	public boolean isNavigationMenuDisplayed() {
		boolean isNavigationDisplayed = driver.isElementDisplayed(navigationContainerBy);
		logger.info("Is Navigation Menu Displayed? " + isNavigationDisplayed);
		return isNavigationDisplayed;
	}

	
	public int getTotaNavMenuNumber() {
		logger.info("Get Nav Menu Number.");
		int navMenuNumber = driver.findElement(navigationContainerBy).findElements(By.tagName("li")).size();
		logger.info("Nav menu Number: " + navMenuNumber);
		return navMenuNumber;
	}
}
