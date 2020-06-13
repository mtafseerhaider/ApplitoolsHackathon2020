package com.hackathon.utils;

import org.apache.commons.lang3.time.StopWatch;
import org.openqa.selenium.WebDriver;

/**
 * @author Tafseer Haider
 * 10/06/2020
 */
public class TransactionTimer
{
	public static void start (StopWatch pageLoad)
	{
		// This function resets the system time and then starts it.
		pageLoad.reset ();
		pageLoad.start ();
	}

	public static void stop (StopWatch pageLoad, String pageName, WebDriver driver)
	{
		// This method calculates the page load time.
		pageLoad.stop ();
		String strBrowserID = driver.getWindowHandle ();
		// Calculate the Page load time
		double pageLoadTime = pageLoad.getTime ();
		double pageLoadTimeInSeconds = pageLoadTime / 1000.0;
		long pageLoadTimeInMillisecondsLong = Math.round (pageLoadTime);
		long pageLoadTimeInSecondsLong = Math.round (pageLoadTimeInSeconds);
		System.out.println ("The " + pageName + " TPL time is: " + pageLoadTimeInSecondsLong + " Seconds or " + pageLoadTimeInMillisecondsLong + " Milliseconds: " + strBrowserID);
	}

}
