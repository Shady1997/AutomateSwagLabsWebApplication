package pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutPage {

	WebDriver driver;

	public CheckoutPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//input[@id='first-name']")
	public WebElement firstName;

	@FindBy(xpath = "//input[@id='last-name']")
	public WebElement lastName;

	@FindBy(xpath = "//input[@id='postal-code']")
	public WebElement postalCode;

	@FindBy(xpath = "//input[@id='continue']")
	public WebElement continueButton;

}
