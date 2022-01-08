package pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class InventoryPage {
	
	WebDriver driver;
	
	public InventoryPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//button[@id='add-to-cart-sauce-labs-backpack']")
	public WebElement firstProduct;
	
	@FindBy(xpath = "//button[@id='add-to-cart-sauce-labs-bike-light']")
	public WebElement secondProduct;
	
	@FindBy(xpath = "//button[@id='add-to-cart-sauce-labs-bolt-t-shirt']")
	public WebElement thirdProduct;
	
	@FindBy(xpath = "//button[@id='add-to-cart-sauce-labs-fleece-jacket']")
	public WebElement fourthProduct;
	
	@FindBy(xpath = "//button[@id='add-to-cart-sauce-labs-onesie']")
	public WebElement fifthProduct;
	
	@FindBy(xpath = "//button[@id='add-to-cart-test.allthethings()-t-shirt-(red)']")
	public WebElement sixProduct;
	
	@FindBy(xpath = "//a[@class='shopping_cart_link']")
	public WebElement moveToPaymentPage;
	
}
