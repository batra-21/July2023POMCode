package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class SearchPage {
	private WebDriver driver;
	private ElementUtil elementUtil;
	private By searchProductResults = By.cssSelector("div#content div.product-layout");
	
	public SearchPage(WebDriver driver)
	{
		this.driver = driver;
		elementUtil = new ElementUtil(driver);
	}
	
	public int getSearchProductsCount()
	{
		int ProdCount = elementUtil.waitForElementsVisible(searchProductResults, AppConstants.DEFAULT_MEDIUM_TIME_OUT).size();
		System.out.println("product count" + ProdCount);
		return ProdCount;
	}

	 public ProductInfoPage selectProduct(String productName)
	 {
		 By productLocator = By.linkText(productName);
		 elementUtil.waitForElementVisible(productLocator, AppConstants.DEFAULT_MEDIUM_TIME_OUT).click();
		 return new ProductInfoPage(driver);
		 
	 }
}
