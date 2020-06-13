package com.hackathon.tests;

import com.hackathon.base.AutomationBase;
import com.hackathon.constants.GLOBAL;
import com.hackathon.pages.HomePage;
import com.hackathon.pages.ProductDetailsPage;
import com.hackathon.utils.*;
import org.apache.commons.lang3.time.StopWatch;
import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import static com.hackathon.pages.HomePage.*;
import static com.hackathon.pages.ProductDetailsPage.*;

/**
 * @author Tafseer Haider
 * 12/06/2020
 */
public class TraditionalTests extends AutomationBase
{
	private WebDriver webDriver;

	@Test (priority = 1, description = "Task 1 – Cross-Device Elements Test")
	public void verifyCrossDeviceElements ()
	{
		// Create Selenium WebDriver instance
		webDriver = getDriver ();

		// Create objects for the page classes required to be used in this test
		Launcher launcher = new Launcher ();
		HomePage homePage = new HomePage (webDriver);
		StopWatch stopwatch = new StopWatch ();
		HackathonReporter hackathonReporter = new HackathonReporter ();
		SoftAssertions softly = new SoftAssertions ();

		// Navigate to the application
		TransactionTimer.start (stopwatch);
		launcher.launchAppliFashionApp (GLOBAL.APP_VERSION);
		SmartWait.awaitUntilPageIsLoaded (webDriver, Timer.getWaitTime ());
		TransactionTimer.stop (stopwatch, "Home Page", webDriver);

		// Assertions
		softly.assertThat (hackathonReporter.
				       reportResult (
				  1,
			  "Search field is displayed",
					   GLOBAL.APP_VERSION,
					   SEARCH_FIELD_DOM_ID,
					   homePage.isSearchFieldDisplayed ()));

		//Report and then soft-assert
		softly.assertThat (hackathonReporter.
				       reportResult (
		          1,
		      "Search icon is displayed",
		               GLOBAL.APP_VERSION,
					   SEARCH_ICON_DOM_ID,
		               homePage.isSearchIconDisplayed ()));


		softly.assertThat (hackathonReporter.
				       reportResult (
				  1,
		      "Filters Funnel is displayed",
		               GLOBAL.APP_VERSION,
					   FILTERS_FUNNEL_DOM_ID,
		               homePage.isFiltersFunnelDisplayed ()));

		softly.assertThat (hackathonReporter.
				       reportResult (
		          1,
		      "Filters Column is displayed",
		               GLOBAL.APP_VERSION,
		               FILTERS_COLUMN_DOM_ID,
		               homePage.isFiltersColumnDisplayed ()));

		softly.assertAll();

	}

	@Test (priority = 2, description = "Task 2 – Shopping Experience Test")
	public void verifyFilterResults ()
	{
		// Create Selenium WebDriver instance
		webDriver = getDriver ();

		// Create objects for the page classes required to be used in this test
		Launcher launcher = new Launcher ();
		HomePage homePage = new HomePage (webDriver);
		StopWatch stopwatch = new StopWatch ();
		HackathonReporter hackathonReporter = new HackathonReporter ();
		SoftAssertions softly = new SoftAssertions ();

		// Navigate to the application
		TransactionTimer.start (stopwatch);
		launcher.launchAppliFashionApp (GLOBAL.APP_VERSION);
		SmartWait.awaitUntilPageIsLoaded (webDriver, Timer.getWaitTime ());
		TransactionTimer.stop (stopwatch, "Home Page", webDriver);

		// Select Black color from filter
		homePage.openFilters ();
		homePage.clickBlackColorCheckbox ();
		homePage.clickFilterButton ();

		// Assertions
		softly.assertThat (hackathonReporter.
				       reportResult (
		          2,
		      "Product Grid Section Displays 2 Black Shoes",
		               GLOBAL.APP_VERSION,
		               PRODUCT_GRID_DOM_ID,
		               homePage.isProductCountCorrect (2)));

		softly.assertThat (hackathonReporter.
				reportResult (
						2,
						"First Product Name is Displayed and Correct",
						GLOBAL.APP_VERSION,
						PRODUCT_ONE_NAME_DOM_ID,
						homePage.isCorrectProductOneNameDisplayed ("Appli Air x Night")));

		softly.assertThat (hackathonReporter.
				reportResult (
						2,
						"First Product Image Color is Black",
						GLOBAL.APP_VERSION,
						PRODUCT_ONE_IMAGE_DOM_ID,
						homePage.isProductOneImageDisplayedAndColorCorrect ("#004dda")));

		softly.assertThat (hackathonReporter.
				reportResult (
						2,
						"First Product New Price is Displayed and Correct",
						GLOBAL.APP_VERSION,
						PRODUCT_ONE_NEW_PRICE_DOM_ID,
						homePage.isNewPriceDisplayedAndCorrect ("$33.00")));

		softly.assertThat (hackathonReporter.
				       reportResult (
		          2,
		      "First Product Old Price is Displayed and Correct",
		               GLOBAL.APP_VERSION,
		               PRODUCT_ONE_OLD_PRICE_DOM_ID,
		               homePage.isOldPriceDisplayedAndCorrect ("$48.00")));

		softly.assertThat (hackathonReporter.
				       reportResult (
		          2,
		      "First Product Discount Countdown is Displayed and Correct",
		               GLOBAL.APP_VERSION,
		               PRODUCT_ONE_COUNTDOWN_DOM_ID,
		               homePage.isCountdownDisplayedAndCorrect ("1 day left")));

		softly.assertThat (hackathonReporter.
				       reportResult (
		          2,
		      "Second Product Name is Displayed and Correct",
		               GLOBAL.APP_VERSION,
		               PRODUCT_TWO_NAME_DOM_ID,
		               homePage.isCorrectProductTwoNameDisplayed ("Appli Air 720")));

		softly.assertThat (hackathonReporter.
				       reportResult (
		          2,
		      "Second Product Image Color is Black",
		               GLOBAL.APP_VERSION,
		               PRODUCT_TWO_IMAGE_DOM_ID,
		               homePage.isProductTwoImageDisplayedAndColorCorrect ("#004dda")));

		softly.assertThat (hackathonReporter.
				       reportResult (
		          2,
		      "Second Product Price is Displayed and Correct",
		               GLOBAL.APP_VERSION,
		               PRODUCT_TWO_PRICE_DOM_ID,
		               homePage.isPriceDisplayedAndCorrect ("$200.00")));

		softly.assertAll();

	}

	@Test (priority = 3, description = "Task 3 – Product Details Test")
	public void verifyProductDetails ()
	{
		// Create Selenium WebDriver instance
		webDriver = getDriver ();

		// Create objects for the page classes required to be used in this test
		Launcher launcher = new Launcher ();
		HomePage homePage = new HomePage (webDriver);
		ProductDetailsPage productDetailsPage = new ProductDetailsPage (webDriver);
		StopWatch stopwatch = new StopWatch ();
		HackathonReporter hackathonReporter = new HackathonReporter ();
		SoftAssertions softly = new SoftAssertions ();

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

		// Assertions
		softly.assertThat (hackathonReporter.
				       reportResult (
		          3,
		      "Product Name is Displayed and Correct",
		               GLOBAL.APP_VERSION,
		               SHOE_NAME_LABEL_DOM_ID,
		               productDetailsPage.isCorrectShoeNameDisplayed ("Appli Air x Night")));

		softly.assertThat (hackathonReporter.
				reportResult (
						3,
						"Product Image is Displayed and Correct",
						GLOBAL.APP_VERSION,
						SHOE_IMAGE_DOM_ID,
						productDetailsPage.isShoeImageDisplayedAndColorCorrect ("#444444")));

		softly.assertThat (hackathonReporter.
				reportResult (
						3,
						"Product Rating Span is Displayed and Correct",
						GLOBAL.APP_VERSION,
						RATING_SPAN_DOM_ID,
						productDetailsPage.isRatingDisplayed ()));

		softly.assertThat (hackathonReporter.
				reportResult (
						3,
						"Product Reviews are Displayed and Correct",
						GLOBAL.APP_VERSION,
						REVIEWS_DOM_ID,
						productDetailsPage.areReviewsDisplayedAndCorrect ("4 reviews")));

		softly.assertThat (hackathonReporter.
				reportResult (
						3,
						"Product Model is Displayed and Correct",
						GLOBAL.APP_VERSION,
						SHOE_MODEL_LABEL_DOM_ID,
						productDetailsPage.isShoeModelDisplayedAndCorrect ("SKU: MTKRY-001")));


		softly.assertThat (hackathonReporter.
				reportResult (
						3,
						"Product Description is Displayed and Correct",
						GLOBAL.APP_VERSION,
						SHOE_DESCRIPTION_DOM_ID,
						productDetailsPage.isDescriptionDisplayedAndCorrect ()));

		softly.assertThat (hackathonReporter.
				reportResult (
						3,
						"Product New Price is Displayed and Correct",
						GLOBAL.APP_VERSION,
						NEW_PRICE_DOM_ID,
						productDetailsPage.isNewPriceDisplayedAndCorrect ("$33.00")));

		softly.assertThat (hackathonReporter.
				reportResult (
						3,
						"Product Old Price is Displayed and Correct",
						GLOBAL.APP_VERSION,
						OLD_PRICE_DOM_ID,
						productDetailsPage.isOldPriceDisplayedAndCorrect ("$48.00")));

		softly.assertThat (hackathonReporter.
				reportResult (
						3,
						"Product Discount Percentage is Displayed and Correct",
						GLOBAL.APP_VERSION,
						DISCOUNT_PERCENTAGE_DOM_ID,
						productDetailsPage.isDiscountPercentageDisplayedAndCorrect ("-30% discount")));

		softly.assertThat (hackathonReporter.
				reportResult (
						3,
						"Product Size Label is Displayed and Correct",
						GLOBAL.APP_VERSION,
						SIZE_LABEL_DOM_ID,
						productDetailsPage.isSizeLabelDisplayedAndCorrect ("Size")));

		softly.assertThat (hackathonReporter.
				reportResult (
						3,
						"Product Default Selected Size is Displayed and Correct",
						GLOBAL.APP_VERSION,
						SELECTED_SIZE_DOM_ID,
						productDetailsPage.isSizeDropdownDisplayedAndCorrect ("Small (S)")));

		softly.assertThat (hackathonReporter.
				reportResult (
						3,
						"Product Quantity Label is Displayed and Correct",
						GLOBAL.APP_VERSION,
						QUANTITY_LABEL_DOM_ID,
						productDetailsPage.isQuantityLabelDisplayedAndCorrect ("Quantity")));

		softly.assertThat (hackathonReporter.
				reportResult (
						3,
						"Product Default Selected Quantity is Displayed and Correct",
						GLOBAL.APP_VERSION,
						QUANTITY_DEFAULT_DOM_ID,
						productDetailsPage.isQuantityRowDisplayedAndDefaultValueCorrect ("1")));

		softly.assertThat (hackathonReporter.
				reportResult (
						3,
						"Add to Cart Button is Displayed",
						GLOBAL.APP_VERSION,
						ADD_TO_CART_BUTTON_DOM_ID,
						productDetailsPage.isAddToCartButtonDisplayed ()));

		softly.assertAll();
	}
}
