package Objects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PersonalInformations {
	WebDriver driver;
	public PersonalInformations(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//input[@id='first-name']")
	WebElement firstname;
	
	@FindBy(xpath = "//input[@id='last-name']")
	WebElement lastname;
	
	@FindBy(xpath = "//input[@id='postal-code']")
	WebElement postalcode;
	
	public void Informasion(String FirstName, String LastName, String PostalCode) {
		firstname.sendKeys(FirstName);
		lastname.sendKeys(LastName);
		postalcode.sendKeys(PostalCode);
		
}
}
