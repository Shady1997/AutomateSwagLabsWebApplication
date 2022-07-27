package start;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import pages.CartPage;
import pages.CheckoutFinal;
import pages.CheckoutPage;
import pages.InventoryPage;
import pages.LoginPage;
import pages.ReturnToHomePAge;
import pages.SideMenu;

public class Start {

	// TODO declare class properties
	static WebDriver driver;
	static FileInputStream readProperty;
	static LoginPage login;
	static InventoryPage inventoryPage;
	static CartPage cartPage;
	static CheckoutPage checkoutPage;
	static CheckoutFinal checkoutFinal;
	static ReturnToHomePAge returnToHomePAge;
	static SideMenu sideMenu;

	public static void main(String[] args) throws IOException, InterruptedException {

		prepareClassProperties();

		startApplication();

		login();

		chooseAllProducts();

		clickToPay();

		clickToCheckout();

		Checkout();

		finalizeCheckout();

		returnToHomePage();

		logout();

		closeApplication();

	}

	@BeforeTest
	private static void prepareClassProperties() throws IOException {
		readProperty = new FileInputStream(
				"C:\\Users\\G525585\\eclipse-workspace\\AutomateSwagLabsWebApplication\\src\\main\\java\\properties\\generalProperties.properties");
		Properties prop = new Properties();
		prop.load(readProperty);

		System.setProperty("webdriver.gecko.driver", prop.getProperty("firefoxdriver"));
		System.setProperty("webdriver.chrome.driver", prop.getProperty("chromedriver"));

		driver = new ChromeDriver();
		login = new LoginPage(driver);
	}

	@Test(priority = 1)
	private static void startApplication() throws InterruptedException {

		// Mazimize current window
		driver.manage().window().maximize();

		// navigate to website
		driver.get("https://www.saucedemo.com/");

		// wait for 10 sec
		Thread.sleep(5000);
	}

	@Test(priority = 2)
	private static void login() throws InterruptedException {
		login.userName.sendKeys("standard_user");
		login.userPassword.sendKeys("secret_sauce");
		login.loginButton.click();

		// wait for 5 sec
		Thread.sleep(5000);
	}

	@Test(priority = 3)
	private static void chooseAllProducts() throws InterruptedException {
		inventoryPage = new InventoryPage(driver);

		inventoryPage.firstProduct.click();
		inventoryPage.secondProduct.click();

		// declare javascript executer object
		JavascriptExecutor js = (JavascriptExecutor) driver;

		js.executeScript("window.scrollBy(0,200)", "");

		inventoryPage.thirdProduct.click();

		inventoryPage.fourthProduct.click();

		// Locating element by link text and store in variable "Element"
		WebElement element = driver.findElement(By.linkText("Test.allTheThings() T-Shirt (Red)"));

		// Scrolling down the page till the element is found
		js.executeScript("arguments[0].scrollIntoView();", element);

		inventoryPage.fifthProduct.click();

		inventoryPage.sixProduct.click();

		js.executeScript("window.scrollBy(0,-1000)", "");

		// wait for 5 sec
		Thread.sleep(5000);
	}

	@Test(priority = 4)
	private static void clickToPay() throws InterruptedException {
		inventoryPage.moveToPaymentPage.click();

		// wait for 5 sec
		Thread.sleep(5000);
	}

	@Test(priority = 5)
	private static void clickToCheckout() throws InterruptedException {
		cartPage = new CartPage(driver);

		// declare javascript executer object
		JavascriptExecutor js = (JavascriptExecutor) driver;

		js.executeScript("window.scrollBy(0,1000)", "");

		cartPage.checkOut.click();

		js.executeScript("window.scrollBy(0,-1000)", "");

		// wait for 5 sec
		Thread.sleep(5000);
	}

	@Test(priority = 6)
	private static void Checkout() throws InterruptedException {
		checkoutPage = new CheckoutPage(driver);

		checkoutPage.firstName.sendKeys("Shady Ahmed");

		checkoutPage.lastName.sendKeys("Mohamed");

		checkoutPage.postalCode.sendKeys("44671");

		// declare javascript executer object
		JavascriptExecutor js = (JavascriptExecutor) driver;

		js.executeScript("window.scrollBy(0,1000)", "");

		checkoutPage.continueButton.click();

		// wait for 5 sec
		Thread.sleep(5000);
	}

	@Test(priority = 7)
	private static void finalizeCheckout() throws InterruptedException {
		checkoutFinal = new CheckoutFinal(driver);

		checkoutFinal.finishButton.click();

		// wait for 5 sec
		Thread.sleep(5000);
	}

	@Test(priority = 8)
	private static void returnToHomePage() throws InterruptedException {
		returnToHomePAge = new ReturnToHomePAge(driver);

		returnToHomePAge.returnToHomePage.click();

		// declare javascript executer object
		JavascriptExecutor js = (JavascriptExecutor) driver;

		js.executeScript("window.scrollBy(0,-1000)", "");

		// wait for 5 sec
		Thread.sleep(5000);
	}

	@Test(priority = 9)
	private static void logout() throws InterruptedException {
		sideMenu = new SideMenu(driver);

		sideMenu.sidemenuButton.click();

		Thread.sleep(5000);

		sideMenu.logoutButton.click();

		// wait for 5 sec
		Thread.sleep(5000);
	}

	@AfterTest
	private static void closeApplication() {
		driver.quit();
	}

}
