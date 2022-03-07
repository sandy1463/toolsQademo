package com.pages;

/*************************************** PURPOSE **********************************
 - This class extends the AbstractWebDriverEventListener, which is included under events.
 The purpose of this to class is to define certain useful Log statements which would be displayed/logged as the application under test
 is being run.
 Do not call any of these methods, instead these methods will be invoked automatically
 as an when the action done (click, type etc). 
 This would allow us not to log any additional messages which we've used in other utility classes so far.
 */
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.AbstractWebDriverEventListener;


public class EventHandler extends AbstractWebDriverEventListener {
	public Logger log = Logger.getLogger("  Weblistener");

	public EventHandler() {
	}
    
	/**
	 * Prints logger message after clicking on the web element 
	 */
	public void afterClickOn(WebElement element, WebDriver driver) {
		log.info("Clicked on " + ElementValue(element));
	}
    
	/**
	 * Prints logger message after navigating to a URL
	 */
	public void afterNavigateTo(java.lang.String url, WebDriver driver) {
		log.info("- Navigated to URL: " + url);
	}
	
	/**
	 * Prints logger message before clicking on a web element
	 */
	public void beforeClickOn(WebElement element, WebDriver driver) {
	
		log.info("- Trying to Click on " + ElementValue(element));
	}
	
	/**
	 * prints logger message before navigating to a URL
	 */
	public void beforeNavigateTo(java.lang.String url, WebDriver driver) {
		log.info("- Navigating to " + url);
	}
	
	/**
	 * Prints failure trace in the logs when exception occurred.
	 */
	public void onException(java.lang.Throwable throwable, WebDriver driver) {
		throwable.getMessage();
	}
	public static String ElementValue(WebElement element) {
		String sValue = element.toString();
		int iStartindex = sValue.indexOf(">");
		int iEndindex = sValue.lastIndexOf("]");
		String sElementValue = sValue.substring(iStartindex + 1, iEndindex);
		return sElementValue;
	}

}
