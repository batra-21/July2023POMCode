package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class LoginPage {
	
	private WebDriver driver;
	private ElementUtil elementUtil;
	
	 //1. private By locators
	private By emailId = By.id("input-email");
	private By password = By.id("input-password");
	private By loginBtn = By.xpath("//input[@value='Login']");
	
	private By forgotPwdLink = By.linkText("Forgotten Password");
	private By registerLink = By.linkText("Register");
	
	//2. page cons
	public LoginPage(WebDriver driver)
	{
		this.driver = driver;
		elementUtil = new ElementUtil(driver);
	}
	//3. page methods
	
	public String getLoginPageTitle()
	{
		String title = elementUtil.waitForTitleIsAndFetch(AppConstants.DEFAULT_SHORT_TIME_OUT, AppConstants.LOGIN_PAGE_TITLE_VALUE);
		System.out.println("Login Page Title:" + title);
		return title;
	}
	
	public String getLoginPageURL()
	{
		String url = elementUtil.waitForURLContainsAndFetch(10, AppConstants.LOGIN_PAGE_URL_FRACTION_VALUE);
		System.out.println("Login Page URL " + url);
		return url;
	}
	
	public boolean isForgotPwdLinkExist()
	{
		return elementUtil.waitForElementVisible(forgotPwdLink, AppConstants.DEFAULT_MEDIUM_TIME_OUT).isDisplayed();
	}
	
	public AccountsPage doLogin(String un,String pwd)
	{
		elementUtil.waitForElementPresence(emailId, AppConstants.DEFAULT_MEDIUM_TIME_OUT).sendKeys(un);
		elementUtil.doSendKeys(password, pwd);
		elementUtil.doClick(loginBtn);
		
		return new AccountsPage(driver);
	}
	
	public RegisterPage navigateToRegisterPage()
	{
		elementUtil.doClick(registerLink);
		 return new RegisterPage(driver);
	}

}
