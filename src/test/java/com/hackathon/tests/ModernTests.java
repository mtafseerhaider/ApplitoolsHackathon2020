package com.hackathon.tests;

import com.applitools.eyes.selenium.Eyes;
import com.hackathon.base.AutomationBase;
import com.hackathon.constants.GLOBAL;
import com.hackathon.pages.HomePage;
import com.hackathon.utils.*;
import org.apache.commons.lang3.time.StopWatch;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import static com.hackathon.pages.HomePage.*;

/**
 * @author Tafseer Haider
 * 12/06/2020
 */
public class ModernTests extends AutomationBase
{
	private WebDriver webDriver;
	private Eyes      eyes;
	private String    strCurrentTestName = null;

	@Test(priority = 1, description = "Task 1 – Cross-Device Elements Test")
	public void verifyCrossDeviceElements ()
	{
		strCurrentTestName = "Task 1";

		// Create Selenium WebDriver instance
		webDriver = getDriver ();

		// Create Applitools Eyes instance
		eyes = openEyes (strCurrentTestName);

		// Create objects for the page classes required to be used in this test
		Launcher launcher = new Launcher ();
		StopWatch stopwatch = new StopWatch ();

		// Navigate to the application
		TransactionTimer.start (stopwatch);
		launcher.launchAppliFashionApp (GLOBAL.APP_VERSION);
		SmartWait.awaitUntilPageIsLoaded (webDriver, Timer.getWaitTime ());
		TransactionTimer.stop (stopwatch, "Home Page", webDriver);

		// Take a screenshot of Home Page via Applitools Eyes
		eyes.checkWindow ("Cross-Device Elements Test");

	}

	@Test (priority = 2, description = "Task 2 – Shopping Experience Test")
	public void verifyFilterResults ()
	{
		strCurrentTestName = "Task 2";

		// Create Selenium WebDriver instance
		webDriver = getDriver ();

		// Create Applitools Eyes instance
		eyes = openEyes (strCurrentTestName);

        // Create objects for the page classes required to be used in this test
		Launcher launcher = new Launcher ();
		HomePage homePage = new HomePage (webDriver);
		StopWatch stopwatch = new StopWatch ();


		// Navigate to the application
		TransactionTimer.start (stopwatch);
		launcher.launchAppliFashionApp (GLOBAL.APP_VERSION);
		SmartWait.awaitUntilPageIsLoaded (webDriver, Timer.getWaitTime ());
		TransactionTimer.stop (stopwatch, "Home Page", webDriver);

		// Select Black color from filter
		homePage.openFilters ();
		homePage.clickBlackColorCheckbox ();
		homePage.clickFilterButton ();

		// Take a screenshot of Product Grid Section via Applitools Eyes
		eyes.checkRegion (By.id (PRODUCT_GRID_DOM_ID),"Filter Results Test");

	}

	@Test (priority = 3, description = "Task 3 – Product Details Test")
	public void verifyProductDetails ()
	{
		strCurrentTestName = "Task 3";

		// Create Selenium WebDriver instance
		webDriver = getDriver ();

		// Create Applitools Eyes instance
		eyes = openEyes (strCurrentTestName);

		// Create objects for the page classes required to be used in this test
		Launcher launcher = new Launcher ();
		HomePage homePage = new HomePage (webDriver);
		StopWatch stopwatch = new StopWatch ();

		// Navigate to the application
		TransactionTimer.start (stopwatch);
		launcher.launchAppliFashionApp (GLOBAL.APP_VERSION);
		SmartWait.awaitUntilPageIsLoaded (webDriver, Timer.getWaitTime ());
		TransactionTimer.stop (stopwatch, "Home Page", webDriver);

		// Select Black color from filter
		homePage.openFilters ();
		homePage.clickBlackColorCheckbox ();
		homePage.clickFilterButton ();

		// Click the first black shoe
		TransactionTimer.start (stopwatch);
		homePage.clickShoe (0);
		SmartWait.awaitUntilPageIsLoaded (webDriver, Timer.getWaitTime ());
		TransactionTimer.stop (stopwatch, "Product Details Page", webDriver);

		// Take a full-page screenshot of Product Details Page via Applitools Eyes
		eyes.checkWindow ("Product Details Test", true);

	}

}
