package com.hackathon.utils;

import com.hackathon.base.AutomationBase;
import org.openqa.selenium.*;
import org.openqa.selenium.support.Color;
import static com.hackathon.utils.SmartWait.*;

/**
 * @author Tafseer Haider
 * 12/06/2020
 */
public class CommonActions extends AutomationBase
{
	WebDriver webDriver = getDriver ();

	/**
	 * loadPage - method to load the page URL for the AUT
	 *
	 * @param pageURL
	 */
	public void loadPage (String pageURL, int timeout)
	{
		webDriver.navigate ().to (pageURL);
		waitForURL (pageURL, timeout);
		isPageReady (webDriver);
	}


	/**
	 * @param by the web element locator
	 * click method to click on a web element
	 */
	public void click (By by)
	{
		webDriver.findElement (by).click ();
	}

	/**
	 * click method to click using web element index
	 *
	 * @param by the web element locator
	 * @param index the index of the value to click
	 */
	public void click (By by, int index)
	{
		WebElement anchor = webDriver.findElements (by).get (index);
		anchor.click ();
	}

	/**
	 * isElementVisible return true if element is displayed on the page
	 *
	 * @param by the web element locator
	 */
	public boolean isElementVisible (By by)
	{
		boolean isVisible = false;
		try
		{
			if (webDriver.findElement (by).isDisplayed ())
			{
				isVisible = true;
			}
		}
		catch (NoSuchElementException  e)
		{
			isVisible = false;
		}

		return isVisible;
	}

	/**
	 * isElementVisiblyCorrect return true if element is displayed on the page with the correct value specified
	 *
	 * @param by the web element locator
	 * @param expectedValue value to verify on the page
	 */
	public boolean isElementVisiblyCorrect (By by, String expectedValue)
	{
		boolean isVisible = false;
		try
		{
			String actualValue = webDriver.findElement (by).getText ();
			if (webDriver.findElement (by).isDisplayed () && actualValue.equalsIgnoreCase (expectedValue))
			{
				isVisible = true;
			}
		}
		catch (NoSuchElementException  e)
		{
			isVisible = false;
		}

		return isVisible;
	}

	/**
	 * getTextByValue returns inner text of the element
	 *
	 * @param by the web element locator
	 */
	public String getTextByValue (By by)
	{
		return webDriver.findElement (by).getAttribute ("value");
	}

	/**
	 * isImageColorCorrect return true if the color of the image is correct
	 *
	 * @param by the web element locator
	 * @param expectedColorHexCode hexadecimal code of the color
	 */
	public boolean isImageColorCorrect (By by, String expectedColorHexCode)
	{
		boolean isVisible = false;
		try
		{
			String color = webDriver.findElement (by).getCssValue ("color");
			String hex = Color.fromString (color).asHex();

			if (webDriver.findElement (by).isDisplayed () && hex.equals (expectedColorHexCode))
			{
				isVisible = true;
			}
		}
		catch (NoSuchElementException  e)
		{
			isVisible = false;
		}

		return isVisible;
	}

	/**
	 * returnWebElementsSize returns size of elements
	 *
	 * @param by the web element locator
	 */
	public int returnWebElementsSize (By by)
	{
		return webDriver.findElements (by).size ();
	}

}
