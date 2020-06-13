package com.hackathon.base;

import com.applitools.eyes.selenium.Eyes;
import com.hackathon.constants.GLOBAL;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Tafseer Haider
 * 10/06/2020
 */
public class AutomationBase
{
	private static List<AutomationFactory>        automationDriversThreadPool = Collections.synchronizedList (new ArrayList<> ());
	private static ThreadLocal<AutomationFactory> automationDriverThread;

	@BeforeSuite(alwaysRun = true)
	public static void initiateAutomation ()
	{
		automationDriverThread = ThreadLocal.withInitial (() ->
		{
			AutomationFactory automationThread = new AutomationFactory ();
			automationDriversThreadPool.add (automationThread);
			return automationThread;
		});
	}

	public static WebDriver getDriver ()
	{
		return automationDriverThread.get ().getDriver ();
	}

	public static Eyes openEyes (String strCurrentTestName)
	{
		return automationDriverThread.get ().instantiateEyes (strCurrentTestName);
	}

	@AfterMethod (alwaysRun = true)
	public void clearCookies ()
	{
		try
		{
			getDriver ().manage ().deleteAllCookies ();
		}
		catch (Exception ex)
		{
			System.err.println ("Unable to clear cookies: " + ex.getCause ());
		}
	}

	@AfterMethod (alwaysRun = true)
	public void closeEyes (ITestResult result)
	{
		if (GLOBAL.APPLITOOLS_ENABLE_EYES)
		{
			boolean testFailed = result.getStatus () == ITestResult.FAILURE;
			if (!testFailed)
			{
				automationDriverThread.get ().closeEyes ();
			}
			else
			{
				automationDriverThread.get ().abortEyes ();
			}
			automationDriverThread.get ().retrieveTestResults ();
		}
	}

	@AfterSuite (alwaysRun = true)
	public void quitAutomationDrivers ()
	{
		for (AutomationFactory automationDriversThread : automationDriversThreadPool)
		{
			automationDriversThread.quitDriver ();
		}
	}
}
