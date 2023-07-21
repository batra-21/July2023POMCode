package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class AccountsPage {
	
	private WebDriver driver;
	private ElementUtil elementUtil;
	
	private By accHeaders = By.cssSelector("div#content h2");
	private By logoutLink = By.linkText("Logout");
	private By search = By.name("search");
	private By searchIcon = By.cssSelector("#search button");
	
    public AccountsPage(WebDriver driver) {
    	this.driver= driver;
    	elementUtil = new ElementUtil(driver);
    }
    
    public String getAccPageTitle()
    {
    	String title = elementUtil.waitForTitleIsAndFetch(AppConstants.DEFAULT_SHORT_TIME_OUT, AppConstants.ACCOUNTS_PAGE_TITLE_VALUE);
		System.out.println("Account Page Title:" + title);
    	return title;
    }

    public String getAccPageURL()
    {
    	String url = elementUtil.waitForURLContainsAndFetch(AppConstants.DEFAULT_MEDIUM_TIME_OUT, AppConstants.ACCOUNTS_PAGE_URL_FRACTION_VALUE);
		System.out.println("Account Page URL " + url);
    	return url;
    }
    
    public boolean isSearchExist()
    {
    	return elementUtil.waitForElementVisible(search, AppConstants.DEFAULT_MEDIUM_TIME_OUT).isDisplayed();
    }
    
    public boolean isLogoutLinkExist()
    {
    	return elementUtil.waitForElementVisible(logoutLink,AppConstants.DEFAULT_MEDIUM_TIME_OUT).isDisplayed();
    }
    public List<String> getPageHeadersList()
    {
    	List<WebElement> accHeaderList = elementUtil.waitForElementsVisible(accHeaders, AppConstants.DEFAULT_MEDIUM_TIME_OUT);
    	
    	    	
    	List<String> accHeaderValueList = new ArrayList<String>();
    	
    	for(WebElement e: accHeaderList) {
    		String text = e.getText();
    		accHeaderValueList.add(text);
    		
    	}
    	return accHeaderValueList;
    	
    	
    }
    
    public SearchPage performSearch(String searchKey)
    {
    	if(isSearchExist())
    	{
    		elementUtil.doSendKeys(search, searchKey);
    		elementUtil.doClick(searchIcon);
    		return new SearchPage(driver);
    	}
    	else
    	{
    		System.out.println("search not available");
    		return null;
    	}
    	
    }
}
