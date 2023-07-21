package com.qa.opencart.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;

public class ProductPageInfoTest extends BaseTest {
	
	@BeforeClass
	public void accPageSetup()
	{
		accountPage = loginPage.doLogin(prop.getProperty("username").trim(),prop.getProperty("password").trim());
	}
	
	@DataProvider
	public Object[][] getProductTestData()
	{
		return new Object[][] {
			{"Macbook" ,"MacBook Pro",4},
			{"iMac" ,"iMac",3},
			{"Apple", "Apple Cinema 30\"",6},
			{"Samsung","Samsung SyncMaster 941BW",1}
			
		};
	}
	@Test(dataProvider = "getProductTestData")
	public void productImagesCountTest(String searchKey,String productName,int imagesCount)
	{
		searchPage = accountPage.performSearch(searchKey);
		productInfoPage = searchPage.selectProduct(productName);
		int actualImagesCount = productInfoPage.getProductImagesCount();
		Assert.assertEquals(actualImagesCount , imagesCount);
		
	}
	//if we use assert then if one is failed then all will also failed so we can use softassert
	//assert is static class so no need to create object but softassert is non static so need to create objetc
	//in below case we should use soft assert.
	//assert vs verify(soft assert)
	
	@Test
	public void productInfoTest()
	{
		searchPage = accountPage.performSearch("MacBook");
		productInfoPage = searchPage.selectProduct("MacBook Pro");
		Map<String,String> actProductInfoMap = productInfoPage.getProductInfo();
		System.out.println(actProductInfoMap);
		softAssert.assertEquals(actProductInfoMap.get("Brand"), "Apple");
		softAssert.assertEquals(actProductInfoMap.get("Product Code"), "Product 18");
		softAssert.assertEquals(actProductInfoMap.get("Product Name"), "MacBook Pro");
		softAssert.assertEquals(actProductInfoMap.get("productPrice"), "$2,000.00");
		//below will tell which is failed so it is mandatory
		softAssert.assertAll();
		
	}
	@DataProvider
	public Object[][] getAddCartTestData()
	{
		return new Object[][] {
			{"MacBook","MacBook Pro",1},
			{"iMac", "iMac", 2},
		};
	}

	@Test(dataProvider = "getAddCartTestData")
	public void addtToCartTest(String searchKey,String productName,int quantity) {
		searchPage = accountPage.performSearch(searchKey);
		productInfoPage = searchPage.selectProduct(productName);
		productInfoPage.enterQuantity(quantity);
		String actCartMesg = productInfoPage.addProductToCart();
		//Success: You have added MacBook Pro to your shopping cart!
		softAssert.assertTrue(actCartMesg.indexOf("Success")>=0);
		softAssert.assertTrue(actCartMesg.indexOf("MacBook Pro")>=0);
		softAssert.assertEquals(actCartMesg, "Success: You have added "+productName+" to your shopping cart!");
		
		softAssert.assertAll();

	}

}
