package com.testsuite.demoqa;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.pages.DemoQAHomePage;
import com.pages.ElementsPage;
import com.setup.DriverSetup;

public class VerifyElementst extends DriverSetup{
	DemoQAHomePage homePage;
	ElementsPage elementsPage;
	
	@BeforeClass
	public void beforeTest(){
		homePage = new DemoQAHomePage(driver);
	}

	@Test
	public void tc_001_VerifyControlElementsPage(){
		driver.get(sAppUrl);
		elementsPage = homePage.navigateElementsPage();
		elementsPage.verifyElementsPage();
	}
	@Test
	public void tc_002_VerifyElementsMenus(){
		driver.get(sAppUrl);
		elementsPage = homePage.navigateElementsPage();
		elementsPage.verifyElementsMenus();
	}
	@Test
	public void tc_003_VerifyTextBox()
	{
		driver.get(sAppUrl);
		elementsPage = homePage.navigateElementsPage();
		elementsPage.clickTextBox();
		elementsPage.sendFullname();
		elementsPage.sendEmail();
		elementsPage.sendcurrentadd();
		elementsPage.sendPermenentadd();
		elementsPage.submitClick();
		elementsPage.clickCheckbox();
		elementsPage.checkboxclick();
	}
	
}
	

