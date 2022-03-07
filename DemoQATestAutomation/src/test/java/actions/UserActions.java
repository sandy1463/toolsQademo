package actions;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.UnreachableBrowserException;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

//import com.pages.DateUtils;
import com.setup.DriverSetup;

public class UserActions{
   public WebDriver driver;
	public static WebElement dropdownElement;
	public static String dropdownText;
	public static int VERYSHORTWAIT; // 5 seconds
	public static int SHORTWAIT; // 15 seconds
	public static int MEDIUMWAIT; // 25 seconds
	public static int NORMALWAIT; // 35 seconds
	public static int LONGWAIT; // 50 seconds
	public static int VERYLONGWAIT; // 80 seconds
	public static int VIEWBUTTONWAIT; // 500 seconds
	public int Count = 5;
	public static String sMainWindowHandle;
	public static String sAlertDialog;
	public String outputDir = "E:\\LA_TEST_FILES\\";
	
	public String mainWindow;
	public String childWindow;
	static Properties property = new Properties();
	public DesiredCapabilities sCapabilities = DriverSetup.capabilities;
	public String sCurrentRunningBrowserName;
	public String sBrowserDownloadsFolder;
	File lastModifiedFile;

	public UserActions(WebDriver driver) {
		this.driver=driver;
		String dataFileLoc = "src/ConfigFiles/Configuration.properties";
		InputStream propFile;
		try {
			propFile = new FileInputStream(dataFileLoc);
			property.load(propFile);
			VERYSHORTWAIT = Integer.parseInt(property.getProperty("VERYSHORTWAIT"));
			SHORTWAIT = Integer.parseInt(property.getProperty("SHORTWAIT"));
			MEDIUMWAIT = Integer.parseInt(property.getProperty("MEDIUMWAIT"));
			NORMALWAIT = Integer.parseInt(property.getProperty("NORMALWAIT"));
			LONGWAIT = Integer.parseInt(property.getProperty("LONGWAIT"));
			VERYLONGWAIT = Integer.parseInt(property.getProperty("VERYLONGWAIT"));
			VIEWBUTTONWAIT = Integer.parseInt(property.getProperty("VIEWBUTTONWAIT"));
			// desiredCapabilities = new DesiredCapabilities();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
 
 public void hitActionsEnterKey(){
	 Actions action = new Actions(driver);
	 action.sendKeys(Keys.chord(Keys.RETURN));
 }
 

	/**
	 * To get the alert text
	 * 
	 * @param string
	 */
	public void verifyalertText(String string) {
		Alert alert = driver.switchTo().alert();
		String text = alert.getText();
		System.out.println(text);
		alert.accept();

		Assert.assertTrue("Comparision failure:-" + text + "with" + string, text.equals(string));
	}



	public void setDriver(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	/**
	 * Getter method to return the driver object
	 * 
	 * @return
	 */
	public WebDriver getDriver() {

		return driver;
	}

	/**
	 * Returns current web page title as a String variable.
	 * 
	 * @return
	 * @exception Exception
	 */
	public String userGetTitle() {
		String sCurrenTitle = null;
		try {
			waitForPageToLoad();
			sCurrenTitle = driver.getTitle();
		} catch (Exception e) {
			System.out.println(e.getStackTrace());
			e.printStackTrace();
		}
		return sCurrenTitle;
	}
	public String getPageURL() {
		String sCurrenTitle = null;
		try {
			waitForPageToLoad();
			sCurrenTitle = driver.getCurrentUrl();
		} catch (Exception e) {
			System.out.println(e.getStackTrace());
			e.printStackTrace();
		}
		return sCurrenTitle;
	}



	/**
	 * Checks whether the check box is selected or not, returns true if selected
	 * and false if not.
	 * 
	 * @param element
	 * @return true or false
	 */
	public boolean isCheckBoxSelected(WebElement element) {
		isElementPresent(element, element.toString(), SHORTWAIT);
		if (element.isSelected()) {
			return true;
		} else
			return false;
	}



	/**
	 * High lights the web element with red color
	 * 
	 * @param element
	 * @exception Exception
	 */
	public void setHighlight(WebElement element) {
		try {
			if (true) {
				String attributevalue = "border:3px solid Crimson;";
				JavascriptExecutor executor = (JavascriptExecutor) driver;
				executor.executeScript("arguments[0].setAttribute('style', arguments[1]);", element, attributevalue);
				sleep(1 / 2);

			}
		} catch (Exception e) {
			System.out.println("- Element could not be highlighted");
		}
	}

	/**
	 * Highlights the web element with green color, used in verifications
	 * 
	 * @param element
	 */
	public void setVerificationHighlight(WebElement element) {
		if (true) {
			String attributevalue = "border:3px solid LimeGreen;";
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].setAttribute('style', arguments[1]);", element, attributevalue);
		}
	}

	/**
	 * Method - user Method for check box selection, waits until the element is
	 * loaded and then selects check box
	 * 
	 * @param locator
	 * @param waitTime
	 * @return - boolean (returns True when the checkbox is selected else
	 *         returns false)
	 * @throws Exception
	 */
	public void userCheck(WebElement element, String sCheckBoxName, int optionWaitTime) {
		try {
			waitForElementToLoad(element, sCheckBoxName, optionWaitTime);
			WebElement checkBox = element;
			setHighlight(checkBox);
			if (checkBox.isSelected())
				System.out.println("- CheckBox for " + sCheckBoxName + "is already selected");
			else
				checkBox.click();

		} catch (StaleElementReferenceException e) {
		} catch (NoSuchElementException e) {
			System.out.println("- Element for Check Box " + sCheckBoxName + " was not found in DOM"
					+ getStackTrace());
			Assert.fail("Element for Check Box " + sCheckBoxName + " was not found in DOM");
		} catch (Exception e) {
			System.out.println("- Unable to check the checkbox for " + sCheckBoxName + " "
					+ getStackTrace());
			Assert.fail("Unable to check the checkbox for'" + sCheckBoxName + "'");
		}
	}

	/**
	 * Switches to alert displayed and saves the alert dialog in a string
	 * variable.
	 * 
	 * @param driver
	 * @return Alert Text
	 */
	public String getAlertText(WebDriver driver) {
		String alertText = null;

		try {
			Alert alert = driver.switchTo().alert();
			alertText = alert.getText();
			alert.accept();
			System.out.println("- \n--------------\n" + "AlertDialog - " + alertText + "\n--------------");
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Alert not found");
		}
		return alertText;

	}


	/**
	 * Method - user Method for User Click, waits until the element is loaded
	 * and then performs a click action
	 * 
	 * @param element
	 * @param waitTime
	 */

	public void userClick(WebElement element, String sElementName, int optionWaitTime) {
		try {
			waitForElementToLoad(element, sElementName, optionWaitTime);
			setHighlight(element);
			element.click();
		} catch (StaleElementReferenceException e) {
			System.out.println("- Element " + sElementName + " is not attached to the page document");
			e.printStackTrace();
			Assert.fail("Element " + sElementName + " is not attached to the page document");
		} catch (NoSuchElementException e) {
			System.out.println("- Element " + sElementName + " was not found in DOM");
			e.printStackTrace();
			Assert.fail("Element " + sElementName + " was not found in DOM");
		} catch (Exception e) {
			System.out.println("- Element " + sElementName + " was not clickable in time-"
					+ optionWaitTime);
			e.printStackTrace();
			Assert.fail("Element " + sElementName + " was not clickable in time-" + optionWaitTime);
		}
	}

	/**
	 * Method - user Method for User Click. Mostly used for chrome browser at
	 * times to avoid 'Timed out receiving message from renderer' by escaping
	 * wait and highlight actions on web element
	 * 
	 * @param element
	 * @param waitTime
	 */

	public void userClickNormal(WebElement element, String sElementName) {
		try {
			element.click();
		} catch (StaleElementReferenceException e) {
			System.out.println("- Element " + sElementName + " is not attached to the page document");
			e.printStackTrace();
			Assert.fail("Element " + sElementName + " is not attached to the page document");
		} catch (NoSuchElementException e) {
			System.out.println("- Element " + sElementName + " was not found in DOM");
			e.printStackTrace();
			Assert.fail("Element " + sElementName + " was not found in DOM");
		} catch (Exception e) {
			System.out.println("- Element " + sElementName + " was not clickable.");
			e.printStackTrace();
			Assert.fail("Element " + sElementName + " was not clickable.");
		}
	}

	/**
	 * Web driver waits for visibility of specified web element for the amount
	 * of time specified.
	 * 
	 * @param element
	 * @param sElementName
	 * @param waitTime
	 * @return
	 */
	public boolean waitForElementToLoad(WebElement element, String sElementName, int waitTime) {
		try {
			System.out.println("- Waiting until element " + sElementName + " is visible in time "
					+ waitTime + " secs");
			WebDriverWait wait = new WebDriverWait(driver, waitTime);
			wait.until(ExpectedConditions.visibilityOf(element));
		} catch (TimeoutException e) {
			System.out.println("- Element " + sElementName + " was not visible in time - " + waitTime);
			e.printStackTrace();
			Assert.fail("- Element " + sElementName + " was not visible in time - " + waitTime);
			return false;
		} catch (NoSuchElementException e) {
			System.out.println("- Element " + element + "is not attached to the page document"
					+ getStackTrace());
			e.printStackTrace();
			Assert.fail("- Element " + element + "is not attached to the page document");
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("- Unable to find the element " + sElementName);
			return false;
		}
		return true;
	}

	/**
	 * Method - user Method for User Clear and Type, waits until the element is
	 * loaded and then enters some text
	 * 
	 * @param element
	 * @param sText
	 * @param waitTime
	 */
	public void userClear(WebElement element, String sElementName, int... optionWaitTime) {
		try {
			int waitTime = getWaitTime(optionWaitTime);
			if (isElementPresent(element, sElementName, waitTime)) {
				// scrollIntoElementView(element);
				setHighlight(element);
				element.clear();
				System.out.println("- Cleared the field in the element - " + sElementName);
			} else {
				System.out.println("- Unable to clear field " + sElementName + getStackTrace());
				Assert.fail("Unable to clear field " + sElementName);
			}
		} catch (StaleElementReferenceException e) {
			Assert.fail("Element for " + sElementName + " is not attached to the page document");
		} catch (NoSuchElementException e) {
			System.out.println(
					"- Element for " + sElementName + " was not found in DOM" + getStackTrace());
			Assert.fail("Element for " + sElementName + " was not found in DOM");
		} catch (Exception e) {
			System.out.println("- Unable to clear text in field with element -" + sElementName
					+ getStackTrace());
			Assert.fail("Unable to clear text in field with element -" + sElementName);
		}
	}

	/**
	 * Method - user Method for User Clear and Type, waits until the element is
	 * loaded and then enters some text
	 * 
	 * @param element
	 * @param sText
	 * @param waitTime
	 */
	public void userClearAndType(WebElement element, String text, String sElementName, int... optionWaitTime) {
		try {
			int waitTime = getWaitTime(optionWaitTime);
			if (isElementPresent(element, sElementName, waitTime)) {
				// scrollIntoElementView(element);
				setHighlight(element);
				element.clear();
				element.sendKeys(text);
				System.out.println("- Cleared the field and entered -** " + text + " ** in the element - "
						+ sElementName);
			} else {
				System.out.println("- Unable to clear and enter " + text + " in field " + sElementName
						+ getStackTrace());
				Assert.fail("Unable to clear and enter " + text + " in field " + sElementName);
			}
		} catch (StaleElementReferenceException e) {			
			Assert.fail("Element for " + sElementName + " is not attached to the page document");
		} catch (NoSuchElementException e) {
			System.out.println(
					"- Element for " + sElementName + " was not found in DOM" + getStackTrace());
			Assert.fail("Element for " + sElementName + " was not found in DOM");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("- Unable to clear and enter '" + text + "' text in field with element -"
					+ sElementName + getStackTrace());
			Assert.fail("Unable to clear and enter '" + text + "' text in field with element -" + sElementName);
		}
	}

	/**
	 * Selects required drop down option using the value attribute of the
	 * required option.
	 */
	public void userSelectOptionByValue(WebElement element, String valueOfOptionToSelect, String sDropDownOptionName,
			int... optionWaitTime) {
		try {
			int waitTime = getWaitTime(optionWaitTime);
			if (isElementPresent(element, sDropDownOptionName, waitTime)) {
				WebElement selectElement = element;
				setHighlight(selectElement);
				Select select = new Select(selectElement);
				select.selectByValue(valueOfOptionToSelect);

			} else {
				System.out.println("- Unable to select drop down option " + sDropDownOptionName + " "
						+ getStackTrace());
				Assert.fail("Unable to select drop down option " + sDropDownOptionName);
			}
		} catch (StaleElementReferenceException e) {
			System.out.println("- Element for drop down option " + sDropDownOptionName
					+ " is not attached to the page document" + getStackTrace());
			Assert.fail(
					"Element for drop down option " + sDropDownOptionName + " is not attached to the page document");
		} catch (NoSuchElementException e) {
			System.out.println(
					"Element for drop down option " + sDropDownOptionName + " was not found in DOM" + getStackTrace());
			Assert.fail("Element for drop down option " + sDropDownOptionName + " was not found in DOM");
		} catch (Exception e) {
			System.out.println("- Unable to select drop down option " + sDropDownOptionName + " "
					+ getStackTrace());
			Assert.fail("Unable to select option from " + sDropDownOptionName + " dropdown");
		}

	}


	/**
	 * Selects required drop down option using the visible text.
	 * 
	 * @param element
	 * @param textOfOptionToSelect
	 * @param optionWaitTime
	 */
	public void userSelectOptionByVisibleText(WebElement element, String textOfOptionToSelect, int... optionWaitTime) {
		try {
			int waitTime = getWaitTime(optionWaitTime);
			if (isElementPresent(element, textOfOptionToSelect, waitTime)) {
				WebElement selectElement = element;
				setHighlight(selectElement);
				Select select = new Select(selectElement);
				select.selectByVisibleText((textOfOptionToSelect));
			}
		} catch (ElementNotVisibleException e) {
			System.out.println("- unable to select the dropdown option with visible text- "
					+ textOfOptionToSelect);
			e.printStackTrace();
			System.out.println(e.getStackTrace());
			Assert.fail("ElementNotVisibleException occured while selection dropdown option-" + textOfOptionToSelect);
		} catch (Exception e) {
			System.out.println("- unable to select the dropdown option with visible text- "
					+ textOfOptionToSelect);
			System.out.println(e.getStackTrace());
			e.printStackTrace();
			Assert.fail("Unable to select dropdown option with value -" + textOfOptionToSelect);
		}
	}

	/**
	 * Returns selected drop down option as a string variable.
	 * 
	 * @param element
	 * @param optionWaitTime
	 * @return
	 */
	public String userGetSelectedDropDownOption(WebElement element, int... optionWaitTime) {
		String sSelectedOption = null;
		try {
			int waitTime = getWaitTime(optionWaitTime);
			if (isElementPresent(element, "FirstSelectedDropDownOption", waitTime)) {
				WebElement selectElement = element;
				setHighlight(selectElement);
				Select select = new Select(selectElement);
				sSelectedOption = select.getFirstSelectedOption().getText();
			}
		} catch (Exception e) {
			System.out.println("- unable to get text from selected drop down option");
			System.out.println(e.getStackTrace());
			e.printStackTrace();
			Assert.fail("Unable to get selected dropdown option");
		}
		return sSelectedOption;
	}



	/**
	 * Returns the stack trace from report log.
	 * 
	 * @return
	 */
	public String getStackTrace() {
		String trace = "";
		int i;
		System.out.println("printing stracktrace");
		StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
		for (i = 1; i < stackTrace.length; i++) {
			trace = trace + "\n" + stackTrace[i];
		}
		return trace;
	}

	/**
	 * Checks either the web element is displayed or enabled with or condition.
	 * If yes returns true, if not returns false and fails the test
	 * 
	 * @param element
	 * @param sElementName
	 * @param waitTime
	 * @return true or false
	 */
	public boolean isElementPresent(WebElement element, String sElementName, int waitTime)
			throws UnreachableBrowserException {
		boolean bFlag = false;
		System.out.println("- Waiting for presence of element " + sElementName + " in time " + waitTime
				+ " secs");
		try {
			WebDriverWait wait = new WebDriverWait(driver, waitTime);
			wait.until(ExpectedConditions.visibilityOf(element));
			if (element.isDisplayed() || element.isEnabled()) {
				bFlag = true;
				setVerificationHighlight(element);
				System.out.println("Waiting for element " + sElementName + " is displayed");
			}
		} catch (NoSuchElementException e) {
			System.out.println("- There is no such element for " + sElementName);
			System.out.println(e.getMessage());
			e.printStackTrace();
			Assert.fail("- No such element as " + sElementName);
		} catch (TimeoutException e) {
			System.out.println("- Element for " + sElementName + " was not displayed in time - "
					+ waitTime + getStackTrace());
			e.printStackTrace();
			Assert.fail("- Timed out after waiting for the element " + sElementName);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("- Element for " + sElementName + " is not displayed" + getStackTrace());
			Assert.fail("- Element for " + sElementName + " is not displayed");
		}
		return bFlag;
	}

	/**
	 * Return true / false when the element is enabled
	 * 
	 * @param element
	 * @return
	 */
	public boolean isElementEnabled(WebElement element) {
		return element.isEnabled();
	}

	public int getWaitTime(int[] optionalWaitArray) {
		if (optionalWaitArray.length <= 0) {
			return 10;
		} else {
			return optionalWaitArray[0];
		}
	}

	/**
	 * Halts the execution for specified number of seconds
	 * 
	 * @param secs
	 */
	public void sleep(int secs) {
		try {
			Thread.sleep(secs * 1000);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}



	/**
	 * Switches to the window using specified window handle passed as argument.
	 * 
	 * @param winHandleBefore
	 * @exception NoSuchWindowException
	 *                , {@link Exception}
	 */
	public void switchToWindow(String winHandleBefore) {
		try {
			sleep(2);
			driver.switchTo().window(winHandleBefore);
			waitForPageToLoad();
			System.out.println("- Navigated succesfsully to window " + winHandleBefore);
		} catch (NoSuchWindowException e) {
			System.out.println("- Window with sepcified number "
					+ " doesn't exists. Please check the window number or wait until the new window appears"
					+ getStackTrace());
			Assert.fail("Window with sepcified number  "
					+ "doesn't exists. Please check the window number or wait until the new window appears");
		} catch (Exception e) {
			System.out.println("- Some exception occured while switching to new window with number: ");
			Assert.fail("Some exception occured while switching to new window with number: ");
		}
	}

	/**
	 * Purpose- This function lets the webdriver wait until the page loads
	 * completely
	 * 
	 * @throws TimeoutException
	 */
	public boolean waitForPageToLoad() {
		System.out.println("- Waiting for page to load");
		try {
			acceptAlert();
			int waitTime = 0;
			boolean isPageLoadComplete = false;
			do {
				isPageLoadComplete = ((String) ((JavascriptExecutor) driver)
						.executeScript("return document.readyState")).equals("complete");
				sleep(1);
				waitTime++;
				if (waitTime > 250) {
					System.out.println("- Page Load Complete");
					break;
				}
			} while (!isPageLoadComplete);
			{
			}
			// wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(waitString)));
		} catch (TimeoutException e) {
			return false;
		}
		return true;
	}




	/**
	 * Lest implicitly wait the webdriver for given point of time.
	 * 
	 * @param iWaitTime
	 */
	public void setImplicitWait(int iWaitTime) {
		driver.manage().timeouts().implicitlyWait(iWaitTime, TimeUnit.SECONDS);
	}

	/**
	 * 
	 * @param element
	 *            - element value by which an element is located
	 * @param waitTime
	 *            - Time to wait for an element
	 * @return - returns the text value from element
	 */
	/**/



	/**
	 * 
	 * @param element
	 *            - element value by which an element is located
	 * @param waitTime
	 *            - Time to wait for an element
	 * @return - returns the text value from element
	 */
	public String userGetText(WebElement element, String sElementName, int waitTime) {
		String sValue = null;
		try {
			if (isElementPresent(element, sElementName, waitTime)) {
				setVerificationHighlight(element);
				sValue = element.getText();
			} else {
				Assert.fail("Unable to get the text from the element " + sElementName);
			}

		} catch (StaleElementReferenceException e) {
			System.out.println("- Element for " + sElementName + "is not attached to the page document"
					+ getStackTrace());
			Assert.fail("Element for " + sElementName + "is not attached to the page document");
		} catch (NoSuchElementException e) {
			System.out.println(
					"- Element for " + sElementName + " was not found in DOM" + getStackTrace());
			Assert.fail("Element for " + sElementName + " was not found in DOM");
		} catch (Exception e) {
			System.out.println("- Unable to get the text from the element " + sElementName + "\n"
					+ getStackTrace());
			Assert.fail("Unable to get the text from the element " + sElementName);
		}
		return sValue;
	}

	/**
	 * Clicks on invisible webelement using java script.
	 * 
	 * @param element
	 * @param i
	 */
	public void userNativeClick(WebElement element, String sElementName, int i) {
		try {
			setHighlight(element);
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].click();", element);
			System.out.println("- clicked on native element " + sElementName);
		} catch (Exception e) {
			System.out.println("- Unable to natively click on the element " + sElementName + " "
					+ e.getStackTrace());
			Assert.fail("Unable to natively click on the element " + sElementName);
		}
	}

	/**
	 * Switches to alert and returns true or false regarding it's presence.
	 * 
	 * @exception NoSuchElementException
	 * @return
	 */
	public boolean isAlertPresent() {
		try {
			driver.switchTo().alert();
			return true;
		} catch (NoAlertPresentException Ex) {
			return false;
		}
	}

	/**
	 * Scroll the web page to bottom
	 */
	public void scrollDown() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0,Math.max(document.documentElement.scrollHeight,"
				+ "document.body.scrollHeight,document.documentElement.clientHeight));");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Checks if there is an alert on the web page, if so accepts it.
	 */
	public void acceptAlert() {
		while (isAlertPresent()) {
			try {
				Alert alert = driver.switchTo().alert();
				sAlertDialog = alert.getText();
				alert.accept();
				System.out.println("- \n--------------\n" + "AlertDialog - " + sAlertDialog
						+ "\n--------------");
				if (sAlertDialog.contains("One or more required participants have not been provided.")) {
					Assert.fail("One or more required Ajax fields have not been loaded properly.");
				} else if (sAlertDialog.contains("You must choose a proposal to award.")) {
					Assert.fail("Unexepected alert 'You must choose a proposal to award.' displayed");
				} else if (sAlertDialog.contains("You must select at least one document.")) {

					Assert.fail("Unexepected alert 'You must select at least one document.' displayed");
				} else if (sAlertDialog.contains("Schedule Number must be provided.")) {
					Assert.fail("Alert 'Schedule Number must be provided'displayed");
				} else if (sAlertDialog.contains("ODA Pricing may not be less than 0.01.")) {
					Assert.fail("Alert Message:ODA Pricing may not be less than 0.01.");
				}

				else if (sAlertDialog.contains(
						"Base financing terms have been changed; you must update the ODA pricing from the calculator before proceeding.")) {
					Assert.fail(
							"Alert Message:Base financing terms have been changed; you must update the ODA pricing from the calculator before proceeding.");
				}
			} catch (NoAlertPresentException e) {
				System.out.println("- Alert not found " + e.getMessage());
			}
		}
	}


	

	/**
	 * 
	 * user method to get the attribute value
	 * 
	 * @param element
	 *            - element value by which an element is located
	 * @param sAttributeValue
	 *            - attribute type
	 * @param waitTime
	 *            - Time to wait for an element
	 * @return - returns the attribute value of type string
	 */
	public String userGetAttribute(WebElement element, String sAttributeValue, String sElementName, int waitTime) {
		String sValue = null;
		try {
			if (isElementPresent(element, sElementName, waitTime)) {
				setVerificationHighlight(element);
				sValue = element.getAttribute(sAttributeValue);
			} else {
				System.out.println("- Unable to get attribute " + sAttributeValue + " from element "
						+ sElementName);
				Assert.fail("Unable to get attribute " + sAttributeValue + " from element " + sElementName);
			}
		} catch (StaleElementReferenceException e) {
			System.out.println("- Element for " + sElementName + " is not attached to the page document"
					+ getStackTrace());
			Assert.fail("Element for " + sElementName + " is not attached to the page document");
		} catch (NoSuchElementException e) {
			System.out.println(
					"- Element for " + sElementName + " was not found in DOM" + getStackTrace());
			Assert.fail("Element for " + sElementName + " was not found in DOM");
		} catch (Exception e) {
			System.out.println("- Unable to get attribute value of type " + sAttributeValue
					+ " from the element " + sElementName + "\n" + getStackTrace());
			Assert.fail(
					"Unable to get attribute value of type " + sAttributeValue + " from the element " + sElementName);
		}
		return sValue;
	}


	
	/**
	 * Checks whether the element is displayed or not with out failing the test
	 * when the web element is not displayed. Displays error message including
	 * element name.
	 * 
	 * @param sElementName
	 * @param element
	 * @return
	 */
	public boolean isElementDisplayedFailuser(String sElementName, WebElement element) {
		try {
			waitForPageToLoad();

			if (element.isDisplayed()) {
				setVerificationHighlight(element);
				return true;
			} else
				return false;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("- Element " + sElementName + " is not displayed " + getStackTrace());
			return false;
		}
	}

	/**
	 * Checks whether the element is displayed or not, fails the test when the
	 * web element is not displayed. Displays error message including element
	 * name.
	 * 
	 * @param sElementName
	 * @param element
	 * @return
	 */
	public boolean isElementDisplayed(WebElement element, String sElementName) {
		try {
			waitForPageToLoad();
			if (element.isDisplayed()) {
				setVerificationHighlight(element);
				return true;
			} else
				return false;
		} catch (Exception e) {
			System.out.println("- Element " + sElementName + " is not displayed " + getStackTrace());
			Assert.fail("Element " + sElementName + " is not displayed");
			return false;
		}
	}

	/**
	 * Switches to the child window using window index
	 * 
	 * @param num
	 */
	public void switchToWindow(int num) {
		try {
			waitForPageToLoad();
			int numWindow = driver.getWindowHandles().size();
			String[] window = (String[]) driver.getWindowHandles().toArray(new String[numWindow]);
			mainWindow = window[0];
			childWindow = window[1];
			driver.switchTo().window(window[num]);
			System.out.println("- Navigated succesfsully to window with sepcified number: " + num);
		} catch (NoSuchWindowException e) {
			System.out.println("- Window with sepcified number " + num
					+ " doesn't exists. Please check the window number or wait until the new window appears"
					+ getStackTrace());
			Assert.fail("Window with sepcified number  " + num
					+ "doesn't exists. Please check the window number or wait until the new window appears");
		} catch (Exception e) {
			System.out.println("- New window not displayed or some exception occured while switching to new window with number: "
					+ num + getStackTrace());
			Assert.fail("New window not displayed or some exception occured while switching to new window with number: "
					+ num);
		}
	}

	/**
	 * 
	 * This method is used to switch to windows based on provided unique element
	 * locater
	 * 
	 * @param locater
	 *            , unique element locater @param, waitTime
	 * 
	 **/

	public void switchToWindow(WebElement element, int waitTime) {
		try {
			int numWindows = driver.getWindowHandles().size();
			int i = 0;
			while (numWindows < 2) {
				driver.wait(500);
				if (++i > 21) {
					break;
				}
			}
			if (numWindows >= 2) {
				boolean bFlag = false;
				for (String handle : driver.getWindowHandles()) {
					driver.switchTo().window(handle);
					if (isElementPresent(element, "ElementOnChildWindow", waitTime)) {
						System.out.println("- Navigated succesfsully to new window");
						bFlag = true;
						break;
					}
				}
				if (!bFlag) {
					System.out.println("- Window with sepcified element " + element
							+ " doesn't exists. Please check the element or wait until the new window appears"
							+ getStackTrace());
					Assert.fail("Window with sepcified element  " + element
							+ "doesn't exists. Please check the element or wait until the new window appears");
				}
			} else {
				System.out.println("- Can not switch to new window as there is only one window, wait until the new window appears");
			}
		} catch (NoSuchWindowException e) {
			System.out.println("- Can not switch to new window " + getStackTrace());
			Assert.fail("Can not switch to new window ");
		} catch (Exception e) {
			System.out.println("- Some exception occured while switching to new window with number: "
					+ getStackTrace());
			Assert.fail("Some exception occured while switching to new window with number: ");
		}
	}

	/**
	 * 
	 * This method is used to switch to windows based on provided window title
	 * 
	 * @param title
	 *            , Window title to which @param, waitTime
	 * 
	 **/

	public void switchToWindowOfTitle(String sWindowTitle) {
		try {
			waitForPageToLoad();
			Set<String> allWindowHandles = driver.getWindowHandles();
			for (String currentWindow : allWindowHandles) {
				driver.switchTo().window(currentWindow);
				if (driver.getTitle().contains(sWindowTitle)) {
					System.out.println("- Navigated succesfsully to window " + sWindowTitle);
					driver.switchTo().window(currentWindow);
					waitForPageToLoad();
					break;
				}
			}
		} catch (Exception e) {
			System.out.println("- Unable to switch to window with title " + sWindowTitle);
		}
	}

	/**
	 * Switches to the child window using window handle.
	 */
	public void switchToWindowHandle() {
		String sCurrentWindowHandle = null;
		try {
			waitForPageToLoad();
			sleep(5);
			Set<String> allWindowHandles = driver.getWindowHandles();
			for (String currentWindow : allWindowHandles) {
				currentWindow = sCurrentWindowHandle;
				if (currentWindow != sMainWindowHandle) {
					System.out.println("- Navigated succesfsully to window with handle " + currentWindow);
					driver.switchTo().window(currentWindow);
					waitForPageToLoad();
					break;
				}
			}
		} catch (Exception e) {
			System.out.println("- Unable to switch to window with handle " + sCurrentWindowHandle);
		}
	}

	

	/**
	 * Highlight the web element using
	 * 
	 * @param id
	 */
	public void setHighlight(String id) {
		if (true) {
			String attributevalue = "border:3px solid Crimson;";
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].setAttribute('style', arguments[1]);", driver.findElement(By.id(id)),
					attributevalue);

		}
	}


	

	/**
	 * Returns the selected option from a dropdown
	 * 
	 * @param selectElement
	 * @param waitTime
	 * @return
	 */
	public String userGetSelectedText(WebElement selectElement, int waitTime) {

		String selectedOption = null;
		try {
			if (isElementPresent(selectElement, "FirstSelectedOptionTxt", waitTime)) {
				setVerificationHighlight(selectElement);
				WebElement option = new Select(selectElement).getFirstSelectedOption();
				selectedOption = option.getText();

			} else {
			}

		} catch (StaleElementReferenceException e) {
			System.out.println("- Element with " + selectElement + "is not attached to the page document"
					+ getStackTrace());
			Assert.fail("Element with " + selectElement + "is not attached to the page document");
		} catch (NoSuchElementException e) {
			System.out.println("- Element " + selectElement + " was not found in DOM" + getStackTrace());
			Assert.fail("Element " + selectElement + " was not found in DOM");
		} catch (Exception e) {
			System.out.println("- Unable to get the text from the element " + selectElement + "\n"
					+ getStackTrace());
			Assert.fail("Unable to get the text from the element " + selectElement);
		}
		return selectedOption;

	}


	/**
	 * Selects last drop down option using visible text from the drop down.
	 * 
	 * @param element
	 */
	public void selectLastDropdownOption(WebElement element) {
		WebElement selectDropDown = element;
		List<WebElement> option = selectDropDown.findElements(By.tagName("option"));
		ArrayList<String> list = new ArrayList<>();

		for (int i = 0; i < option.size(); i++) {
			list.add(option.get(i).getText().trim());
		}
		Collections.reverse(list);

		String lastOption = list.get(0);

		new Select(selectDropDown).selectByVisibleText(lastOption);
	}

	/**
	 * Closes all child windows opened other than the main window.
	 */
	public void closeChildWindows() {
		Set<String> windowHandles = driver.getWindowHandles();
		System.out.println("main window handle " + sMainWindowHandle);
		for (String windowHandle : windowHandles) {
			System.out.println("window handle " + windowHandle);
			if (!windowHandle.equals(sMainWindowHandle)) {
				System.out.println("Closed window handle " + windowHandle);
				driver.switchTo().window(windowHandle);
				driver.close();
			}
		}
	}


	/**
	 * Coverts and returns error stack trace message in string format.
	 * 
	 * @param e
	 * @return
	 */
	public String getStackTraceAsString(Exception e) {
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		e.printStackTrace(pw);
		return sw.toString();
	}
}
