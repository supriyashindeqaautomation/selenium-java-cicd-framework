package SupriyaShinde.Tests;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

//import com.sun.net.httpserver.Authenticator.Retry;
import SupriyaShinde.TestComponents.Retry;
import SupriyaShinde.TestComponents.BaseTest;
import SupriyaShinde.pageobjects.CartPage;
import SupriyaShinde.pageobjects.ProductCatalogue;

public class ErrorValidationsTest extends BaseTest {


		// TODO Auto-generated method stub
	@Test(groups={"ErrorHandling"}, retryAnalyzer=Retry.class)
		public void loginErrorValidation() throws IOException
		{
		
		landingPage.LoginApplication("supriya@gmail.com", "priya@2026");
		Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMessage());
		
		}
	@Test
	public void productErrorValidation() throws IOException
	{
	String ProductName="ZARA COAT 3";
	ProductCatalogue productCatalogue=landingPage.LoginApplication("supriyashinde@gmail.com", "Supriya@2026");
	//products
	List<WebElement> products=productCatalogue.getProductList();
	productCatalogue.getProductByName(ProductName);
	productCatalogue.addProductToCart(ProductName);
	//cart page
	CartPage cartPage=productCatalogue.goToCartPage();
    Boolean match=cartPage.verifyCartProductDisplay("ZARA COAT 33");
	Assert.assertFalse(match);
		
	}
}
