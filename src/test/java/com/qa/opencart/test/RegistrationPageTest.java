package com.qa.opencart.test;

import static org.testng.Assert.assertTrue;

import java.time.zone.ZoneOffsetTransitionRule;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ExcelUtil;
import java.util.Random;

public class RegistrationPageTest extends BaseTest{
	
	@BeforeClass 
	public void regPageSetUp()
	{
		registerPage = loginPage.navigateToRegisterPage();
	}
	
	public String getRandomEmail()
	{
		Random random = new Random();
		  //String email="automation" +random.nextInt(1000)+ "@gmail.com";
		String email="automation" + System.currentTimeMillis() + "@gmail.com";
		  return email;
	}
	
	@DataProvider
	public Object[][] getRegTestData()
	{
		Object regData[][] = ExcelUtil.getTestData(AppConstants.REGISTER_SHEET_NAME);
		return regData;
		
	}
	@Test(dataProvider = "getRegTestData")
	public void userRegTest(String firstName,String lastName, String telephone,String password,String subscribe)
	{
	 assertTrue(registerPage.registerUser(firstName,lastName,getRandomEmail(),telephone,password,subscribe));
	}
	

}
