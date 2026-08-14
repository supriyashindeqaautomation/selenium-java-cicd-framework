package SupriyaShinde.stepDefinitions;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import SupriyaShinde.TestComponents.BaseTest;
import SupriyaShinde.pageobjects.CartPage;
import SupriyaShinde.pageobjects.CheckOutPage;
import SupriyaShinde.pageobjects.ConfirmationPage;
import SupriyaShinde.pageobjects.LandingPage;
import SupriyaShinde.pageobjects.ProductCatalogue;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinitionImpl extends BaseTest {
	
	public LandingPage landingPage;
	public ProductCatalogue productCatalogue;
	ConfirmationPage confirmationPage;
	@Given("I landed on Ecommerce Page")
	public void I_landed_on_Ecommerce_Page() throws IOException
	{
		landingPage = launchApplication();
	}
	
	@When("^Logged in with username (.+) and password (.+)$")//regular expression if step definitions has parameters
	public void logged_in_with_username_and_password(String username, String password)
	{
		productCatalogue=landingPage.LoginApplication(username,password);
	}
	
	@When("^I add product (.+) to cart$")
	public void i_add_product_to_cart(String productName)
	{
		List<WebElement> products=productCatalogue.getProductList();
		productCatalogue.addProductToCart(productName);
	}
	
	@And("^Checkout (.+) and submit the order$")
	public void checkout_submit_order(String productName)
	{
		CartPage cartPage=productCatalogue.goToCartPage();
	    Boolean match=cartPage.verifyCartProductDisplay(productName);
		Assert.assertTrue(match);
		//checkout page
		CheckOutPage checkOutPage=cartPage.goToCheckOutPage();
		checkOutPage.selectCountry("India");
		confirmationPage=checkOutPage.submitOrder();
	}
	
	
	@Then("{string} message is displayed on confirmation page")
	public void message_displayed_on_confirmationPage(String string)
	{
		String ConfirmationMessage=confirmationPage.getConfirmationMessage();
		Assert.assertTrue(ConfirmationMessage.equalsIgnoreCase(string));
		driver.close();
	}
	
	@Then("{string} message is displayed")
	public void something_message_is_displayed(String string)
	{
		Assert.assertEquals(string, landingPage.getErrorMessage());
		driver.close();
	}

}
