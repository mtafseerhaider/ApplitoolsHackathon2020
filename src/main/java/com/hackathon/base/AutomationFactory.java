package com.hackathon.base;

import com.applitools.eyes.*;
import com.applitools.eyes.selenium.BrowserType;
import com.applitools.eyes.selenium.ClassicRunner;
import com.applitools.eyes.selenium.Configuration;
import com.applitools.eyes.selenium.Eyes;
import com.applitools.eyes.visualgrid.model.DeviceName;
import com.applitools.eyes.visualgrid.model.ScreenOrientation;
import com.applitools.eyes.visualgrid.services.VisualGridRunner;
import com.hackathon.constants.GLOBAL;
import com.hackathon.utils.ConfigsReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import static com.hackathon.base.DeviceTypes.LAPTOP;
import static com.hackathon.base.DriverOptions.FIREFOX;
import static com.hackathon.utils.ConfigsReader.getProperty;

/**
 * @author Tafseer Haider
 * 10/06/2020
 */
public class AutomationFactory
{
	protected static WebDriver     webDriver;
	private          DeviceTypes   selectedDeviceType;
	private          DriverOptions selectedDriverOption;
	private          Eyes          eyes;
	private          Configuration suiteConfig;
	private          EyesRunner    runner             = null;
	private final    String        operatingSystem    = System.getProperty ("os.name").toUpperCase ();
	private final    String        systemArchitecture = System.getProperty ("os.arch");
	private final    Boolean       useVisualGrid      = Boolean.getBoolean ("useVisualGrid");
	private final    String        concurrentSessions = System.getProperty ("ultrafastGridConcurrency", "10");
	private final    String        batchName          = System.getProperty ("batchName", "UFG Hackathon");
	private final    String        appName            = System.getProperty ("appName", "AppliFashion");
	private final    String        viewPortWidth      = System.getProperty ("viewPortWidth", "800");
	private final    String        viewPortHeight     = System.getProperty ("viewPortHeight", "600");

	public AutomationFactory ()
	{
		ConfigsReader.readProperties (GLOBAL.CONFIG_FILE);
		DeviceTypes deviceType = LAPTOP;
		DriverOptions driverOption = FIREFOX;
		String device = System.getProperty ("deviceType", deviceType.toString ().toUpperCase ());
		String browser = System.getProperty ("browserType", driverOption.toString ().toUpperCase ());
		try
		{
			deviceType = DeviceTypes.valueOf (device.toUpperCase ());
		}
		catch (IllegalArgumentException ignored)
		{
			System.err.println ("Unknown device specified, defaulting to '" + deviceType + "'...");
		}
		catch (NullPointerException ignored)
		{
			System.err.println ("No device specified, defaulting to '" + deviceType + "'...");
		}
		try
		{
			driverOption = DriverOptions.valueOf (browser.toUpperCase ());
		}
		catch (IllegalArgumentException ignored)
		{
			System.err.println ("Unknown driver specified, defaulting to '" + driverOption + "'...");
		}
		catch (NullPointerException ignored)
		{
			System.err.println ("No driver specified, defaulting to '" + driverOption + "'...");
		}
		selectedDeviceType = deviceType;
		selectedDriverOption = driverOption;
	}

	public WebDriver getDriver ()
	{
		if (null == webDriver)
		{
			instantiateWebDriver (selectedDriverOption);
		}

		return webDriver;
	}

	public WebDriver instantiateWebDriver (DriverOptions driverOption)
	{
		System.out.println (" ");
		System.out.println ("Current Operating System: " + operatingSystem);
		System.out.println ("Current Architecture: " + systemArchitecture);
		System.out.println ("Device Selected: " + selectedDeviceType);
		System.out.println ("Browser Selected: " + selectedDriverOption);
		System.out.println ("Applitools Visual Testing Enabled: " + GLOBAL.APPLITOOLS_ENABLE_EYES);
		System.out.println (" ");

		DesiredCapabilities desiredCapabilities = new DesiredCapabilities ();
		webDriver = driverOption.getWebDriverObject (desiredCapabilities);
		if (!GLOBAL.APPLITOOLS_ENABLE_EYES)
		{
			webDriver.manage ().window ().setSize (selectedDeviceType.getDisplayResolution ());
		}

		return webDriver;
	}

	public Eyes instantiateEyes (String strCurrentTestName)
	{
		final String eyesAPIKey = System.getProperty ("eyesAPIKey", getProperty ("MY_API_KEY"));

		if (eyes == null && GLOBAL.APPLITOOLS_ENABLE_EYES)
		{
			if (  useVisualGrid)
			{
				runner = new VisualGridRunner (Integer.valueOf (concurrentSessions));
				suiteConfig = new Configuration ();
				suiteConfig.addBrowser (1200, 700, BrowserType.CHROME).
						    addBrowser (1200, 700, BrowserType.FIREFOX).
						    addBrowser (1200, 700, BrowserType.EDGE_CHROMIUM).
						    addBrowser (768, 700, BrowserType.CHROME).
						    addBrowser (768, 700, BrowserType.FIREFOX).
						    addBrowser (768, 700, BrowserType.EDGE_CHROMIUM).
						    addDeviceEmulation (DeviceName.iPhone_X, ScreenOrientation.PORTRAIT).
						    setApiKey (eyesAPIKey).
						    setAppName (appName).
						    setBatch (new BatchInfo (batchName)).
						    setViewportSize (new RectangleSize (Integer.valueOf(viewPortWidth), Integer.valueOf(viewPortHeight)));

				System.out.println ("Getting Eyes for " + appName);
				eyes = new Eyes (runner);
				eyes.setIsDisabled (false);
				eyes.setConfiguration (suiteConfig);
			}
			else
			{
				runner = new ClassicRunner ();
				System.out.println ("Getting Eyes for " + appName);
				eyes = new Eyes (runner);
				eyes.setIsDisabled (false);
				eyes.setApiKey (eyesAPIKey);
				eyes.setBatch (new BatchInfo (batchName));
				eyes.setExplicitViewportSize (new RectangleSize (Integer.valueOf(viewPortWidth), Integer.valueOf(viewPortHeight)));
			}
			eyes.open (getDriver (), appName, strCurrentTestName);
		}

		return eyes;
	}

	public void closeEyes ()
	{
		try
		{
			eyes.closeAsync ();
		}
		catch (Exception e)
		{
			e.printStackTrace ();
		}
		eyes = null;
	}

	public void abortEyes ()
	{
		try
		{
			eyes.abortAsync ();
		}
		catch (Exception e)
		{
			e.printStackTrace ();
		}
		eyes = null;

	}

	public void retrieveTestResults ()
	{
		TestResultsSummary allTestResults = runner.getAllTestResults (false);
		for (TestResultContainer result : allTestResults)
		{
			handleTestResults (result);
		}
	}

	void handleTestResults (TestResultContainer summary)
	{
		Throwable ex = summary.getException ();
		if (ex != null)
		{
			System.out.printf ("System error occured while checking target.\n");
		}
		TestResults result = summary.getTestResults ();
		if (result == null)
		{
			System.out.printf ("No test results information available\n");
		}
		else
		{
			System.out.printf (
					"URL = %s, AppName = %s, testname = %s, Browser = %s,OS = %s, viewport = %dx%d, matched = %d,mismatched = %d, missing = %d,aborted = %s\n",
					result.getUrl (), result.getAppName (), result.getName (), result.getHostApp (), result.getHostOS (),
					result.getHostDisplaySize ().getWidth (), result.getHostDisplaySize ().getHeight (), result.getMatches (), result.getMismatches (),
					result.getMissing (), (result.isAborted () ? "aborted" : "no"));
		}
	}

	public void quitDriver ()
	{
		if (null != webDriver)
		{
			webDriver.quit ();
			webDriver = null;
		}
	}

}
