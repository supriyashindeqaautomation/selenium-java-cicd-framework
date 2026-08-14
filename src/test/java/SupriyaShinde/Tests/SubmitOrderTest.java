package SupriyaShinde.Tests;

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import SupriyaShinde.AbstractComponents.AbstractComponent;
import SupriyaShinde.TestComponents.BaseTest;
import SupriyaShinde.pageobjects.CartPage;
import SupriyaShinde.pageobjects.CheckOutPage;
import SupriyaShinde.pageobjects.ConfirmationPage;
import SupriyaShinde.pageobjects.LandingPage;
import SupriyaShinde.pageobjects.OrderPage;
import SupriyaShinde.pageobjects.ProductCatalogue;
import io.github.bonigarcia.wdm.WebDriverManager;

public class SubmitOrderTest extends BaseTest {

	String ProductName="ZARA COAT 3";

		// TODO Auto-generated method stub
	@Test(dataProvider="getData",groups={"purchase"})
		public void submitOrder(HashMap<String,String> input) throws IOException
		{
		
		ProductCatalogue productCatalogue=landingPage.LoginApplication(input.get("email"),input.get("password"));
		//products
		List<WebElement> products=productCatalogue.getProductList();
		productCatalogue.getProductByName(input.get("ProductName"));
		productCatalogue.addProductToCart(input.get("ProductName"));
		//cart page
		CartPage cartPage=productCatalogue.goToCartPage();
	    Boolean match=cartPage.verifyCartProductDisplay(input.get("ProductName"));
		Assert.assertTrue(match);
		//checkout page
		CheckOutPage checkOutPage=cartPage.goToCheckOutPage();
		checkOutPage.selectCountry("India");
		ConfirmationPage confirmationPage=checkOutPage.submitOrder();
		//confirmation message
		String ConfirmationMessage=confirmationPage.getConfirmationMessage();
		Assert.assertTrue(ConfirmationMessage.equalsIgnoreCase("Thankyou for the order."));
		System.out.println("test passed");
		
		
		}
	
	
	@Test(dependsOnMethods="submitOrder")
	public void orderHistoryTest()
	{
		ProductCatalogue productCatalogue=landingPage.LoginApplication("supriyashinde@gmail.com", "Supriya@2026");
		OrderPage orderPage=productCatalogue.goToOrderPage();
		Assert.assertTrue(orderPage.verifyOrderDisplay(ProductName));
		
	}
	
	
	@DataProvider
	public Object[][] getData() throws IOException
	{
		
		
		List<HashMap<String,String>> data= getJsonDataToMap(System.getProperty("user.dir")+"\\src\\test\\java\\SupriyaShinde\\data\\PurchaseOrder.json");
		return new Object[] []  {{data.get(0)},{data.get(1)}};
	}
	
	/*@DataProvider
	public Object[][] getData()
	{
		
		HashMap<String,String> map=new HashMap<String,String>();
		map.put("email", "supriyashinde@gmail.com");
		map.put("password", "Supriya@2026");
		map.put("ProductName", "ZARA COAT 3");
		
		HashMap<String,String> map1=new HashMap<String,String>();
		map1.put("email", "Prachi@gmail.com");
		map1.put("password", "Prachi@2026");
		map1.put("ProductName", "ADIDAS ORIGINAL");
		
		
		
		return new Object[] []  {{map},{map1}};
	}*/
		
	/*@DataProvider
	public Object[][] getData()
	{
		return new Object[] []  {{"supriyashinde@gmail.com", "Supriya@2026","ZARA COAT 3"},{"anshika@gmail.com","Iamking@000","ADIDAS ORIGINAL"}};
	}*/
		
		
		
		
		
		
	

	

}
