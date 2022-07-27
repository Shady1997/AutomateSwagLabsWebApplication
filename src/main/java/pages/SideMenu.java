package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SideMenu {
	
	WebDriver driver;

	public SideMenu(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//button[@id='react-burger-menu-btn']")
	public WebElement sidemenuButton;
	
	@FindBy(xpath = "//a[@id='logout_sidebar_link']")
	public WebElement logoutButton;

}
