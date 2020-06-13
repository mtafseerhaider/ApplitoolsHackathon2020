package com.hackathon.pages;

import com.hackathon.utils.CommonActions;
import com.hackathon.utils.Timer;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static com.hackathon.utils.SmartWait.awaitFor;

/**
 * @author Tafseer Haider
 * 11/06/2020
 */
public class HomePage extends CommonActions
{
	private WebDriver webDriver;

	public static final String SEARCH_FIELD_DOM_ID          = "INPUTtext____42";
	public static final String SEARCH_ICON_DOM_ID           = "BUTTONsubmit____43";
	public static final String FILTERS_FUNNEL_DOM_ID        = "ti-filter";
	public static final String FILTERS_COLUMN_DOM_ID        = "filter_col";
	public static final String BLACK_COLOR_CHECKBOX_DOM_ID  = "colors__Black";
	public static final String FILTER_BUTTON_DOM_ID         = "filterBtn";
	public static final String PRODUCT_GRID_DOM_ID          = "product_grid";
	public static final String PRODUCT_LIST_DOM_XPATH       = "//*[@id='product_grid']/div";
	public static final String SHOES_LIST_DOM_XPATH         = "//*[@id='product_grid']/div/div/figure/a";
	public static final String PRODUCT_ONE_IMAGE_DOM_ID     = "IMG__imgfluid__215";
	public static final String PRODUCT_ONE_NAME_DOM_ID      = "H3____218";
	public static final String PRODUCT_ONE_COUNTDOWN_DOM_ID = "DIV__countdown__216";
	public static final String PRODUCT_ONE_NEW_PRICE_DOM_ID = "SPAN__newprice__220";
	public static final String PRODUCT_ONE_OLD_PRICE_DOM_ID = "SPAN__oldprice__221";
	public static final String PRODUCT_TWO_IMAGE_DOM_ID     = "IMG__imgfluid__239";
	public static final String PRODUCT_TWO_NAME_DOM_ID      = "H3____241";
	public static final String PRODUCT_TWO_PRICE_DOM_ID     = "SPAN__newprice__243";

	private By LOC_SEARCH_FIELD          = By.id (SEARCH_FIELD_DOM_ID);
	private By LOC_SEARCH_ICON           = By.id (SEARCH_ICON_DOM_ID);
	private By LOC_FILTERS_FUNNEL        = By.id (FILTERS_FUNNEL_DOM_ID);
	private By LOC_FILTERS_COLUMN        = By.id (FILTERS_COLUMN_DOM_ID);
	private By LOC_BLACK_COLOR_CHECKBOX  = By.id (BLACK_COLOR_CHECKBOX_DOM_ID);
	private By LOC_FILTER_BUTTON         = By.id (FILTER_BUTTON_DOM_ID);
	private By LOC_PRODUCT_GRID          = By.id (PRODUCT_GRID_DOM_ID);
	private By LOC_PRODUCT_GRID_LIST     = By.xpath (PRODUCT_LIST_DOM_XPATH);
	private By LOC_SHOES_LIST            = By.xpath (SHOES_LIST_DOM_XPATH);
	private By LOC_PRODUCT_ONE_IMAGE     = By.id (PRODUCT_ONE_IMAGE_DOM_ID);
	private By LOC_PRODUCT_ONE_NAME      = By.id (PRODUCT_ONE_NAME_DOM_ID);
	private By LOC_PRODUCT_ONE_COUNTDOWN = By.id (PRODUCT_ONE_COUNTDOWN_DOM_ID);
	private By LOC_PRODUCT_ONE_NEW_PRICE = By.id (PRODUCT_ONE_NEW_PRICE_DOM_ID);
	private By LOC_PRODUCT_ONE_OLD_PRICE = By.id (PRODUCT_ONE_OLD_PRICE_DOM_ID);
	private By LOC_PRODUCT_TWO_IMAGE     = By.id (PRODUCT_TWO_IMAGE_DOM_ID);
	private By LOC_PRODUCT_TWO_NAME      = By.id (PRODUCT_TWO_NAME_DOM_ID);
	private By LOC_PRODUCT_TWO_PRICE     = By.id (PRODUCT_TWO_PRICE_DOM_ID);

	public HomePage (WebDriver webDriver)
	{
		this.webDriver = webDriver;
	}

	public boolean isSearchFieldDisplayed ()
	{
		return isElementVisible (LOC_SEARCH_FIELD);
	}

	public boolean isSearchIconDisplayed ()
	{
		return isElementVisible (LOC_SEARCH_ICON);
	}

	public boolean isFiltersFunnelDisplayed ()
	{
		return isElementVisible (LOC_FILTERS_FUNNEL);
	}

	public boolean isFiltersColumnDisplayed ()
	{
		return isElementVisible (LOC_FILTERS_COLUMN);
	}

	public void openFilters ()
	{
		if (isElementVisible (LOC_FILTERS_FUNNEL))
		{
			click (LOC_FILTERS_FUNNEL);
		}

	}

	public void clickBlackColorCheckbox ()
	{
		awaitFor (LOC_FILTERS_COLUMN, Timer.getWaitTime ());
		click (LOC_BLACK_COLOR_CHECKBOX);
	}

	public void clickFilterButton ()
	{
		click (LOC_FILTER_BUTTON);
	}

	public boolean isProductCountCorrect (int expectedCount)
	{
		if (returnWebElementsSize (LOC_PRODUCT_GRID_LIST) == expectedCount)
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	public ProductDetailsPage clickShoe (int intShoeIndex)
	{
		click (LOC_SHOES_LIST, intShoeIndex);

		return new ProductDetailsPage (webDriver);
	}

	public boolean isProductOneImageDisplayedAndColorCorrect (String strColorCode)
	{
		return isImageColorCorrect (LOC_PRODUCT_ONE_IMAGE, strColorCode);
	}

	public boolean isCorrectProductOneNameDisplayed (String strShoeName)
	{
		return isElementVisiblyCorrect (LOC_PRODUCT_ONE_NAME, strShoeName);
	}

	public boolean isNewPriceDisplayedAndCorrect (String strNewPrice)
	{
		return isElementVisiblyCorrect (LOC_PRODUCT_ONE_NEW_PRICE, strNewPrice);
	}

	public boolean isOldPriceDisplayedAndCorrect (String strOldPrice)
	{
		return isElementVisiblyCorrect (LOC_PRODUCT_ONE_OLD_PRICE, strOldPrice);
	}

	public boolean isCountdownDisplayedAndCorrect (String strCountdown)
	{
		return isElementVisiblyCorrect (LOC_PRODUCT_ONE_COUNTDOWN, strCountdown);
	}

	public boolean isProductTwoImageDisplayedAndColorCorrect (String strColorCode)
	{
		return isImageColorCorrect (LOC_PRODUCT_TWO_IMAGE, strColorCode);
	}

	public boolean isCorrectProductTwoNameDisplayed (String strShoeName)
	{
		return isElementVisiblyCorrect (LOC_PRODUCT_TWO_NAME, strShoeName);
	}

	public boolean isPriceDisplayedAndCorrect (String strPrice)
	{
		return isElementVisiblyCorrect (LOC_PRODUCT_TWO_PRICE, strPrice);
	}

}
