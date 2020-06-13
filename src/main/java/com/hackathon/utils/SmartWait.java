package com.hackathon.utils;

import com.hackathon.base.AutomationBase;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
import static org.awaitility.Awaitility.*;
import static java.util.concurrent.TimeUnit.SECONDS;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Tafseer Haider
 * 10/05/2020
 */
public class SmartWait extends AutomationBase
{
	/**
	 * waitForURL method to wait up to a designated period before
	 *
	 * @param url
	 * @param timer
	 */
	public static void waitForURL (String url, int timer)
	{
		WebDriver driver = getDriver ();
		WebDriverWait wait = new WebDriverWait (driver, timer);
		wait.until (ExpectedConditions.refreshed (ExpectedConditions.urlContains (url)));
	}

	/**
	 * isPageReady - method to verify that a page has completely rendered
	 *
	 * @param webDriver
	 * @return boolean
	 */
	public static boolean isPageReady (WebDriver webDriver)
	{
		JavascriptExecutor javascriptExecutor = (JavascriptExecutor) webDriver;
		return javascriptExecutor.executeScript ("return document.readyState").equals ("complete");
	}

	/**
	 * awaitUntilPageIsLoaded method will wait until DOM is ready
	 *
	 * @param timer the timeout time in seconds
	 */
	public static void awaitUntilPageIsLoaded (WebDriver webDriver, int timer)
	{
		await ().
				atMost (timer, SECONDS).
				pollDelay (5, SECONDS).
				pollInterval (1, SECONDS).
				ignoreExceptions ().
				untilAsserted (() -> assertThat (((JavascriptExecutor) webDriver).executeScript ("return document.readyState").toString ()).
						as ("Wait for the web Page to be rendered and loaded completely.").
						isEqualTo ("complete"));
	}

	/**
	 * awaitFor method will wait until specified web element is displayed
	 *
	 * @param by    the web element locator
	 * @param timer the timeout time in seconds
	 */
	public static void awaitFor (By by, int timer)
	{
		await ().
				atMost (timer, SECONDS).
				pollDelay (1, SECONDS).
				pollInterval (1, SECONDS).
				ignoreExceptions ().
				untilAsserted (() -> assertThat (getDriver ().findElement (by).isDisplayed ()).
						as ("Wait for Web Element to be displayed.").
						isEqualTo (true));
	}

}
