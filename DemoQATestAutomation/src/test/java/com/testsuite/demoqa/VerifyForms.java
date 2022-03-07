package com.testsuite.demoqa;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.pages.DemoQAHomePage;
import com.pages.ElementsPage;
import com.setup.DriverSetup;

public class VerifyForms extends DriverSetup{
	DemoQAHomePage homePage;
	ElementsPage elementsPage;
	
	@BeforeClass
	public void beforeTest(){
		homePage = new DemoQAHomePage(driver);
	}

	@Test
	public void tc001_VerifyControlElements(){
		driver.get(sAppUrl);
		elementsPage = homePage.navigateElementsPage();
		elementsPage.verifyElementsPage();
	}
	@Test
	public void tc0002_Verifywidget(){
		driver.get(sAppUrl);
		elementsPage = homePage.navigateElementsPage();
		elementsPage.verifyElementsPage();
	}
	@Test
	public void tc_003_VerifyPracticeForm()
	{
		elementsPage.clickForm();
		elementsPage.firstName();
		elementsPage.lastName();
		elementsPage.formEmail();
		elementsPage.clickMale();
		elementsPage.userNumber();
		elementsPage.dateOfBirth();
		
	}
	
}
