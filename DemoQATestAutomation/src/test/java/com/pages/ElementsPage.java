package com.pages;

import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import actions.UserActions;
import org.testng.Assert;

public class ElementsPage extends UserActions{

	public ElementsPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
		// TODO Auto-generated constructor stub
	}
	@FindBy(xpath="//h5[text()='Elements']")
	WebElement homeControl;
	
	@FindBy(xpath="(//div[@class='element-list collapse show']//following-sibling::span)[.]")
	List<WebElement> elements_menus;
	@FindBy(xpath="//*[@id=\"item-0\"]") WebElement textbox;
	@FindBy(id="userName") WebElement fullname;
	@FindBy(id="userEmail") WebElement Email;
	@FindBy(id="currentAddress") WebElement currentadd;
	@FindBy(id="permanentAddress") WebElement permenantadd;
	@FindBy(id="submit") WebElement submitbtn;
	@FindBy(xpath="//*[@id=\"item-1\"]") WebElement checkbox;
	@FindBy(xpath="//*[@id=\"tree-node\"]/ol/li/span/label/span[1]/svg") WebElement checkcheckbox;
	@FindBy(xpath="//*[@id=\"app\"]/div/div/div[2]/div[1]/div/div/div[2]/span/div") WebElement form;
	@FindBy(id="firstName") WebElement firstName;
	@FindBy(id="lastName") WebElement lastName;
	@FindBy(id="userEmail") WebElement formEmail;
	@FindBy(xpath="//*[@id=\"genterWrapper\"]/div[2]/div[1]/label") WebElement formClickmale;
	@FindBy(id="userNumber") WebElement usernumber;
	@FindBy(id="dateOfBirthInput") WebElement dob;
	@FindBy()
	// Test data
	String elementsMenus [] = {"Text Box","Check Box","Radio Button", "Web Tables","Buttons","Links", "Broken Links - Images", "Upload and Download", "Dynamic Properties"};
	
	public void verifyElementsPage() {
		String elementsPageTitle= getPageURL();
		Assert.assertTrue(elementsPageTitle.contains("/elements"));
	}
	public void verifyElementsMenus() {
		for (int i = 0; i <elements_menus.size(); i++) {
			String menuStr=userGetText(elements_menus.get(i), "Menu", SHORTWAIT);
			Assert.assertEquals(menuStr, elementsMenus[i]);
		}
		
	}
	public void clickTextBox()
	{
		textbox.click();
	}
	public void sendFullname()
	{
		fullname.sendKeys("sandeep karnati");
	}
	public void sendEmail()
	{
		Email.sendKeys("karnatisandeep99@gmail.com");
	}
	public void sendcurrentadd()
	{
		currentadd.sendKeys("address");
	}
	public void sendPermenentadd()
	{
		permenantadd.sendKeys("address");
	}
	public void submitClick()
	{
		submitbtn.click();
	}
	public void clickCheckbox()
	{
		checkbox.click();
	}
	public void checkboxclick()
	{
		checkcheckbox.click();
	}
	public void clickForm()
	{
		form.click();
	}
	public void firstName()
	{
		firstName.sendKeys("sandeep");
	}
	public void lastName()
	{
		lastName.sendKeys("karnati");
	}
	public void formEmail()
	{
		formEmail.sendKeys("@karnati");
	}
	public void clickMale()
	{
		formClickmale.click();
	}
	public void userNumber()
	{
		usernumber.sendKeys("5396256365");
	}
	public void dateOfBirth()
	{
		dob.click();
	}
	
	
	
	
	
	
	

}
