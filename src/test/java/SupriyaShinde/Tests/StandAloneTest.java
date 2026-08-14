package SupriyaShinde.Tests;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import SupriyaShinde.pageobjects.LandingPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class StandAloneTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//new comments are added
		String ProductName="ZARA COAT 3";
		WebDriverManager.chromedriver().setup();
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://rahulshettyacademy.com/client");
		
		LandingPage landingPage= new LandingPage(driver);
		
		driver.findElement(By.id("userEmail")).sendKeys("supriyashinde@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Supriya@2026");
		driver.findElement(By.id("login")).click();
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(5));
		//wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".col-lg-4")));
		
		List<WebElement> products=driver.findElements(By.cssSelector(".col-lg-4"));
		WebElement prod= products.stream().filter(product->product.findElement(By.tagName("b")).getText().equals(ProductName)).findFirst().orElse(null);
		prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
		
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("toast-container")));
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
		
		List<WebElement> cartProducts=driver.findElements(By.cssSelector(".cartSection h3"));
		boolean match=cartProducts.stream().anyMatch(cartProduct->cartProduct.getText().equalsIgnoreCase(ProductName));
		Assert.assertTrue(match);
		driver.findElement(By.cssSelector(".totalRow button")).click();
		
		/*driver.findElement(By.xpath("//input[@placeholder='Select Country']")).sendKeys("Ind");
		List<WebElement> countries=driver.findElements(By.xpath("//button/span"));
		WebElement count=countries.stream().filter(country->country.getText().equalsIgnoreCase("India")).findFirst().orElse(null);
		count.click();*/
		
		Actions Action=new Actions(driver);
		Action.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")), "India").build().perform();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
		driver.findElement(By.xpath("(//button[contains(@class,'ta-item')])[2]")).click();
		driver.findElement(By.cssSelector(".action__submit")).click();
		String ConfirmationMessage=driver.findElement(By.cssSelector(".hero-primary")).getText();
		Assert.assertTrue(ConfirmationMessage.equalsIgnoreCase("Thankyou for the order."));
		
		
		
		
		

	}

}
