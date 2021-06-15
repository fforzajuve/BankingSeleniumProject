package com.banking.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.banking.driver.DriverManager;
import com.banking.utils.PropertiesUtil;

public class BaseTest {
	
	@BeforeMethod
	public void setUp() {
		DriverManager.getDriver().get(PropertiesUtil.getBaseUrl());
	}

	@AfterMethod
	public void tearDown() {
		DriverManager.closeDefaultDriver();
	}
}
