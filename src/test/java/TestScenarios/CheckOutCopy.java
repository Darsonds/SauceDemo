package TestScenarios;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import Objects.Login;
import Objects.PersonalInformations;
import io.github.bonigarcia.wdm.WebDriverManager;

public class CheckOutCopy{
	WebDriver driver;

	@BeforeTest
	public void CheckOutApp1() throws InterruptedException{
		
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.saucedemo.com/");
		
		Login login = new Login(driver);
		login.LoginApps("visual_user", "secret_sauce");

		String expect = "Products";
		Assert.assertEquals(expect, driver.findElement(By.xpath("(//span[@class='title'])[1]")).getText());
				
//		List<WebElement>listProducts = driver.findElements(By.xpath("(//div[@class='inventory_item'])"));
//		
//		for(WebElement webElement : listProducts) {
//			String product = webElement.findElement(By.xpath("(//div[@class='inventory_item'])")).getText();
//			System.out.println("Ini adalah nama product	:" +product);
//		}
//	
		//driver.quit();
	}
	@Test
	public void AddtoCart() throws InterruptedException {
		// Add to cart
		driver.findElement(By.xpath("//button[@id='add-to-cart-sauce-labs-fleece-jacket']")).click();		
		//Chart
		driver.findElement(By.xpath("//a[@class='shopping_cart_link']")).click();
		
		String  expectProduct= driver.findElement(By.xpath("//div[normalize-space()='Sauce Labs Fleece Jacket']")).getText();
		Assert.assertEquals(expectProduct, driver.findElement(By.xpath("//div[normalize-space()='Sauce Labs Fleece Jacket']")).getText());
		Thread.sleep(1000);
		System.out.println("ini nama product:" + expectProduct);
		driver.findElement(By.xpath("//button[@id='checkout']")).click();
		//verification information pages
		String  expec= "Checkout: Your Information";
		Assert.assertEquals(expec, driver.findElement(By.xpath("//span[@class='title']")).getText());
		System.out.println("ini halaman informasi :"+expec);

	}
	@Test
	public void Informations() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		PersonalInformations personalInformations = new PersonalInformations(driver);
		personalInformations.Informasion("Joni", "Mahendra", "97555");
		
//		//input personal information
//		driver.findElement(By.xpath("//input[@id='first-name']")).sendKeys("Joni");
//		driver.findElement(By.xpath("//input[@id='last-name']")).sendKeys("Mahendra");
//		driver.findElement(By.xpath("//input[@id='postal-code']")).sendKeys("97555");
//		
		driver.findElement(By.xpath("//input[@id='continue']")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//button[@id='finish']")).click();
		
		String  e= "Thank you for your order!";
		Assert.assertEquals(e, driver.findElement(By.cssSelector(".complete-header")).getText());
		System.out.println("ini pesan selesai order	: " +e);
	}
	@AfterTest
	public void AfterTest() {
		driver.close();
		
	}
}