package com.setup;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.pages.EventHandler;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class DriverSetup {

	public static WebDriver driver;
	public FileInputStream propertyFile;
	public Properties property = new Properties();
	public String sChromeDriverPath;
	public static DesiredCapabilities capabilities = new DesiredCapabilities();
	public static String sCurrentBrowserName;
	public static String sFileDownloadsFolder;
	public String sAppUrl;
	public EventFiringWebDriver eventDriver;
	public ExtentTest test;
	public ExtentReports report;

	public WebDriver getDriver() {
		return driver;
	}

	private String getTimeStamp() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("ddMMyyyy_HHmmss");
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		return dateFormat.format(timestamp);
	}
	@Parameters({ "browserType" })
	@BeforeClass(alwaysRun = true)
	public void setBrowserAndDriver(@Optional("chrome") String browserType) {
		System.out.println("###################### BEFORE CLASS ######################");
		getAppUrl();
		initializeTestBaseSetup(browserType);
	}

	@BeforeSuite
	public void reportsetup () {
		System.out.println("###################### BEFORE SUITE ######################");
		report = new ExtentReports(System.getProperty("user.dir")+"\\"
				+ "TestAutomationReports\\ExtentReportResults.html");
		test = report.startTest("DemoQAReports_"+this.getTimeStamp());
	}
	@AfterMethod
	public void getResult(ITestResult result){
		System.out.println("###################### AFTER METHOD ######################");
		if(result.getStatus() == ITestResult.FAILURE){
			test.log(LogStatus.FAIL, "Test Case Failed is "+result.getName());
			test.log(LogStatus.FAIL, "Test Case Failed is "+result.getThrowable());
		}else if(result.getStatus() == ITestResult.SKIP){
			test.log(LogStatus.SKIP, "Test Case Skipped is "+result.getName());
		}else
			{
			test.log(LogStatus.PASS, "Test Case PASS is "+result.getName());
			}
		
	}
	
	@AfterSuite
	public void onFinish(ITestContext context) {
		System.out.println("###################### AFTER SUITE ######################");
		report.endTest(test);
		report.flush();
	}
	@AfterClass
	public void closeBrowser() {
		System.out.println("&&&&&&&&&&&&&&& QUIT &&&&&&&&&&&&&&&");
		driver.quit();
	}

	public void step(String sStepMessage) {
		System.out.println("\n====================\n" + sStepMessage + "\n====================");
	}

	private void getAppUrl() {
		String dataFileLoc = "src/ConfigFiles/Configuration.properties";
		InputStream propFile;
		try {
			propFile = new FileInputStream(dataFileLoc);
			property.load(propFile);
			sAppUrl = property.getProperty("App.URL");
		} catch (Exception e) {
			e.printStackTrace();
			throw new Error(e.getMessage());
		}

	}


	private static WebDriver initChromeDriver() {
		System.out.println("Launching google chrome with new profile..");
		System.setProperty("webdriver.chrome.driver", "D:\\pavan sir class files\\chromedriver_win32\\chromedriver.exe");
//		System.getProperty("user.dir") + File.separator
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		return driver;
	}

	public WebDriver initFirefoxDriver() {
		try {
			capabilities.setBrowserName("firefox");
			System.out.println(
					"\n============================\n Launching Firefox  Browser \n============================\n");
			// driver = new FirefoxDriver(profile);
			driver = new FirefoxDriver();
			sCurrentBrowserName = capabilities.getBrowserName();
			System.out.println("CURRENT BROWSER NAME " + sCurrentBrowserName);
			eventDriver = new EventFiringWebDriver(driver);
			EventHandler handler = new EventHandler();
			eventDriver.register(handler);
			driver = eventDriver;
		} catch (Exception e) {
			System.out.println("Exception occured while launching the Firefox browser");
			throw new Error(e.getMessage());
		}
		return driver;
	}

	public void initializeTestBaseSetup(String browserType) {
		switch (browserType) {
		case "chrome":
			driver = initChromeDriver();
			break;
		case "firefox":
			driver = initFirefoxDriver();
			break;
		default:
			System.out.println("browser : " + browserType + " is invalid, Launching Firefox as browser of choice..");
			driver = initFirefoxDriver();
		}
	}

}
