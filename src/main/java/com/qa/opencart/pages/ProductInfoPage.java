package com.qa.opencart.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;
import java.util.Map;
import java.util.LinkedHashMap;
import java.util.TreeMap;

public class ProductInfoPage {
	
	private WebDriver driver;
	private ElementUtil elementUtil;
	Map<String,String> productInfoMap;
	private By productHeader = By.tagName("h1");
	private By productImages = By.cssSelector("ul.thumbnails img");
	private By productMetaData = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[position()=1]/li");
	
	private By productPriceData = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[position()=2]/li");
	private By quantity = By.id("input-quantity");
	
	
	private By addCartBtn = By.id("button-cart");
	
	
	private By cartSuccessMessage = By.cssSelector("div.alert.alert-success");

	public ProductInfoPage(WebDriver driver) {
		this.driver = driver;
		elementUtil = new ElementUtil(driver);
		
		
	}

	public String getProductHeaderValue()
	{
		String productHeaderValue = elementUtil.doElementGetText(productHeader);
		System.out.println(productHeaderValue);
		return productHeaderValue;
	}
	
	public int getProductImagesCount()
	{
	int ImagesCount =	elementUtil.waitForElementsVisible(productImages, AppConstants.DEFAULT_MEDIUM_TIME_OUT).size();
	System.out.println("Images Count" + ImagesCount);
	return ImagesCount;
	}
	//Brand: Apple
	//Product Code: Product 16
	//Reward Points: 600
	//Availability: In Stock
	//in hashmap the order is not maintained and if we want order as well then instead of hashmap we can use linked hashmap.
	//linked hashmap is same as hashmap order is maintained
	//now if we want sorting on keys like caps with alphabateic and then small and then numeric then we can use Treemap
	
	public Map<String,String> getProductInfo()
	{
			
		//productInfoMap = new HashMap<String,String>();
		//productInfoMap = new LinkedHashMap<String,String>();
		productInfoMap = new TreeMap<String,String>();
		
		productInfoMap.put("Product Name", getProductHeaderValue());
		
		getProductMetaData();
		getProductPriceData();
		return productInfoMap;
		
		
	}
	//fetching meta data
	private void getProductMetaData()
	{
		List<WebElement> metaLIst = elementUtil.getElements(productMetaData);
		//meta data
		for(WebElement e: metaLIst)
		{
			String meta =e.getText();
			String metaInfo[] = meta.split(":");
			String key = metaInfo[0].trim();
			String value = metaInfo[1].trim();
			productInfoMap.put(key, value);
		}
		
	}
	//fetching price data
	
	private void getProductPriceData()
	{
		//price data
				//$602.00
				//Ex Tax: $500.00
				
				List<WebElement> priceLIst = elementUtil.getElements(productPriceData);
				String price = priceLIst.get(0).getText();
				String exTax = priceLIst.get(1).getText();
				String exTaxValue = exTax.split(":")[1];
				productInfoMap.put("productPrice", price);
				productInfoMap.put("exTax", exTaxValue);
	}
	
	public void enterQuantity(int qty)
	{
		elementUtil.doSendKeys(quantity,String.valueOf(qty));
		
		
	}
	
	public String addProductToCart()
	{
		elementUtil.doClick(addCartBtn);
		String successMessage = elementUtil.waitForElementVisible(cartSuccessMessage, AppConstants.DEFAULT_SHORT_TIME_OUT).getText();
		System.out.println("success message  " + successMessage);
		StringBuilder sb = new StringBuilder(successMessage);
	    String msg = sb.substring(0,successMessage.length()-1).replace("\n", "").toString();
		
		return msg;
	}
}
