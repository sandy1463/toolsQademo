package com.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import actions.UserActions;

public class DemoQAHomePage extends UserActions{

	public DemoQAHomePage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
		PageFactory.initElements(driver, this);
	}
	@FindBy(xpath="//h5[text()='Elements']")
	WebElement homeControl;
	
	@FindBy(xpath="(//div[@class='card mt-4 top-card'])[1]")
	WebElement homeControl1;
	
	
	public ElementsPage navigateElementsPage() {
		//click login button 
		userClick(homeControl1, "Elements Page", SHORTWAIT);
	    waitForPageToLoad();
		return new ElementsPage(driver) ;
	}

}
