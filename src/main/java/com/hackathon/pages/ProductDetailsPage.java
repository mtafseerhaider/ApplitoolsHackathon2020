package com.hackathon.pages;

import com.hackathon.utils.CommonActions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * @author Tafseer Haider
 * 11/06/2020
 */
public class ProductDetailsPage extends CommonActions
{
	private WebDriver webDriver;

	public static final String SHOE_NAME_LABEL_DOM_ID     = "shoe_name";
	public static final String SHOE_IMAGE_DOM_ID          = "shoe_img";
	public static final String RATING_SPAN_DOM_ID         = "SPAN__rating__76";
	public static final String REVIEWS_DOM_ID             = "EM____82";
	public static final String SHOE_MODEL_LABEL_DOM_ID    = "SMALL____84";
	public static final String SHOE_DESCRIPTION_DOM_ID    = "P____83";
	public static final String NEW_PRICE_DOM_ID           = "new_price";
	public static final String OLD_PRICE_DOM_ID           = "old_price";
	public static final String DISCOUNT_PERCENTAGE_DOM_ID = "discount";
	public static final String SIZE_LABEL_DOM_ID          = "STRONG____90";
	public static final String SELECTED_SIZE_DOM_ID       = ".current";
	public static final String QUANTITY_LABEL_DOM_ID      = "STRONG____100";
	public static final String QUANTITY_ROW_DOM_ID        = "DIV__numbersrow__102";
	public static final String QUANTITY_DEFAULT_DOM_ID    = "quantity_1";
	public static final String ADD_TO_CART_BUTTON_DOM_ID  = "A__btn__114";

	private By LOC_SHOE_NAME_LABEL     = By.id (SHOE_NAME_LABEL_DOM_ID);
	private By LOC_SHOE_IMAGE          = By.id (SHOE_IMAGE_DOM_ID);
	private By LOC_RATING_SPAN         = By.id (RATING_SPAN_DOM_ID);
	private By LOC_REVIEWS             = By.id (REVIEWS_DOM_ID);
	private By LOC_SHOE_MODEL_LABEL    = By.id (SHOE_MODEL_LABEL_DOM_ID);
	private By LOC_SHOE_DESCRIPTION    = By.id (SHOE_DESCRIPTION_DOM_ID);
	private By LOC_NEW_PRICE           = By.id (NEW_PRICE_DOM_ID);
	private By LOC_OLD_PRICE           = By.id (OLD_PRICE_DOM_ID);
	private By LOC_DISCOUNT_PERCENTAGE = By.id (DISCOUNT_PERCENTAGE_DOM_ID);
	private By LOC_SIZE_LABEL          = By.id (SIZE_LABEL_DOM_ID);
	private By LOC_SELECTED_SIZE       = By.cssSelector (SELECTED_SIZE_DOM_ID);
	private By LOC_QUANTITY_LABEL      = By.id (QUANTITY_LABEL_DOM_ID);
	private By LOC_QUANTITY_ROW        = By.id (QUANTITY_ROW_DOM_ID);
	private By LOC_QUANTITY_DEFAULT    = By.id (QUANTITY_DEFAULT_DOM_ID);
	private By LOC_ADD_TO_CART_BUTTON  = By.id (ADD_TO_CART_BUTTON_DOM_ID);

	public ProductDetailsPage (WebDriver webDriver)
	{
		this.webDriver = webDriver;
	}

	public boolean isCorrectShoeNameDisplayed (String strShoeName)
	{
		return isElementVisiblyCorrect (LOC_SHOE_NAME_LABEL, strShoeName);
	}

	public boolean isShoeImageDisplayedAndColorCorrect (String strColorCode)
	{
		return isImageColorCorrect (LOC_SHOE_IMAGE, strColorCode);
	}

	public boolean isRatingDisplayed ()
	{
		return isElementVisible (LOC_RATING_SPAN);
	}

	public boolean areReviewsDisplayedAndCorrect (String strReviews)
	{
		return isElementVisiblyCorrect (LOC_REVIEWS, strReviews);
	}

	public boolean isShoeModelDisplayedAndCorrect (String strShoeModel)
	{
		return isElementVisiblyCorrect (LOC_SHOE_MODEL_LABEL, strShoeModel);
	}

	public boolean isDescriptionDisplayedAndCorrect ()
	{
		String strDescription = "SKU: MTKRY-001\n" +
				"These boots are comfortable enough to wear all day. Well made. I love the “look”. Best Used For Casual Everyday Walk fearlessly into the cooler months in the Sorel Joan Of Arctic Wedge II Chelsea Boot. Boasting the iconic wedge platform that's as comfortable as it is stylish, this boot features a waterproof upper";
		return isElementVisiblyCorrect (LOC_SHOE_DESCRIPTION, strDescription);
	}

	public boolean isNewPriceDisplayedAndCorrect (String strNewPrice)
	{
		return isElementVisiblyCorrect (LOC_NEW_PRICE, strNewPrice);
	}

	public boolean isOldPriceDisplayedAndCorrect (String strOldPrice)
	{
		return isElementVisiblyCorrect (LOC_OLD_PRICE, strOldPrice);
	}

	public boolean isDiscountPercentageDisplayedAndCorrect (String strDiscountPercentage)
	{
		return isElementVisiblyCorrect (LOC_DISCOUNT_PERCENTAGE, strDiscountPercentage);
	}

	public boolean isSizeLabelDisplayedAndCorrect (String strLabelName)
	{
		return isElementVisiblyCorrect (LOC_SIZE_LABEL, strLabelName);
	}

	public boolean isSizeDropdownDisplayedAndCorrect (String strSelectedOption)
	{
		if (isElementVisiblyCorrect (LOC_SELECTED_SIZE, strSelectedOption))
		{
			return true;
		}

		return false;
	}

	public boolean isQuantityLabelDisplayedAndCorrect (String strLabelName)
	{
		return isElementVisiblyCorrect (LOC_QUANTITY_LABEL, strLabelName);
	}

	public boolean isQuantityRowDisplayedAndDefaultValueCorrect (String strDefaultQuantity)
	{
		if (isElementVisible (LOC_QUANTITY_ROW) == true && getTextByValue (LOC_QUANTITY_DEFAULT).equalsIgnoreCase (strDefaultQuantity))
		{
			return true;
		}

		return false;
	}

	public boolean isAddToCartButtonDisplayed ()
	{
		return isElementVisible (LOC_ADD_TO_CART_BUTTON);
	}

}
