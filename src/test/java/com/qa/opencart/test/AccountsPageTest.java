package com.qa.opencart.test;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;

public class AccountsPageTest extends BaseTest{
	
	@BeforeClass
	public void productInfoPageSetup()
	{
		accountPage = loginPage.doLogin(prop.getProperty("username").trim(),prop.getProperty("password").trim());
	}
	
	@Test
	public void accPageTitleTest()
	{
		String actualTitle = accountPage.getAccPageTitle();
		Assert.assertEquals(actualTitle, AppConstants.ACCOUNTS_PAGE_TITLE_VALUE);
	}
	
	public void accPageLogoutTest()
	{
		Assert.assertTrue(accountPage.isLogoutLinkExist());
	}

	@Test
	public void accPageHeadersCountTest()
	{
	List<String> listActualHeaderList =	accountPage.getPageHeadersList();
	Assert.assertEquals(listActualHeaderList.size(), AppConstants.ACCOUNTS_PAGE_HEADERS_COUNT);
	}
	@Test
	public void accPageHeadersValueTest()
	{
	List<String> listActualHeaderList =	accountPage.getPageHeadersList();
	System.out.println("acc page headrer list" + listActualHeaderList);
	Assert.assertEquals(listActualHeaderList, AppConstants.EXPECTED_ACCOUNTS_PAGE_HEADER_LIST);
	
	}
	
	@DataProvider
	public Object[][] getProductData()
	{
		return new Object[][] {
			{"Macbook"},
			{"iMac"},
			{"Apple"},
			{"Samsung"}
			
		};
	}
	@Test(dataProvider = "getProductData")
	public void searchProductCountTest(String searchKey)
	{
		searchPage = accountPage.performSearch(searchKey);
		Assert.assertTrue(searchPage.getSearchProductsCount()>0);
	}
	@DataProvider
	public Object[][] getProductTestData()
	{
		return new Object[][] {
			{"Macbook" ,"MacBook Pro"},
			{"Macbook" ,"MacBook Air"},
			{"iMac" ,"iMac"},
			{"Apple", "Apple Cinema 30\""},
			{"Samsung","Samsung SyncMaster 941BW"}
			
		};
	}
	
	@Test(dataProvider = "getProductTestData")
	public void searchProductTest(String searchKey, String productName)
	{
	    searchPage =accountPage.performSearch(searchKey);
	    if(searchPage.getSearchProductsCount()>0)
	    {
	    	productInfoPage = searchPage.selectProduct(productName);	    
		  String actualProductHeader =productInfoPage.getProductHeaderValue();
		  Assert.assertEquals(actualProductHeader, productName);
	    }
	}
	
	
}
