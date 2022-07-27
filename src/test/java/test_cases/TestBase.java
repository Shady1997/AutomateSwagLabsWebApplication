package test_cases;

import java.awt.AWTException;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import pages.CartPage;
import pages.CheckoutFinal;
import pages.CheckoutPage;
import pages.InventoryPage;
import pages.LoginPage;
import pages.PageBase;
import pages.ReturnToHomePAge;
import pages.SideMenu;

public class TestBase {
	// npm install -g newman - newman run <collection name> --data <file name> -n
	// <no of iterations> -d <delay time> -e <environment name>

	// define main properties
	public static WebDriver driver;
	FileInputStream readProperty;
	public static Properties prop;
	public static ChromeOptions options;
	JavascriptExecutor js;
	// pages declaration
	LoginPage loginPage;
	InventoryPage inventoryPage;
	CartPage cartPage;
	CheckoutPage checkoutPage;
	CheckoutFinal checkoutFinal;
	ReturnToHomePAge returnToHomePAge;
	SideMenu sideMenu;

	@Parameters("browser")
	@BeforeTest
	public void prepareClassProperties(String browser) throws IOException, AWTException {
		readProperty = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\generalProperties.properties");
		prop = new Properties();
		prop.load(readProperty);
		options = new ChromeOptions();
		options.addArguments("--start-maximized");
		options.addArguments("--disable-web-security");
		options.addArguments("--no-proxy-server");

		Map<String, Object> prefs = new HashMap<String, Object>();
		prefs.put("credentials_enable_service", false);
		prefs.put("profile.password_manager_enabled", false);

		options.setExperimentalOption("prefs", prefs);
		options.setExperimentalOption("excludeSwitches", new String[] { "enable-automation" });

		if (browser.equalsIgnoreCase("Firefox")) {
			System.setProperty("webdriver.gecko.driver",
					System.getProperty("user.dir") + prop.getProperty("firefoxdriver"));
			driver = new FirefoxDriver();
		} else if (browser.equalsIgnoreCase("Chrome")) {
			System.setProperty("webdriver.chrome.driver",
					System.getProperty("user.dir") + prop.getProperty("chromedriver"));
			driver = new ChromeDriver(options);
		} else {
			throw new IllegalArgumentException("Invalid browser value!!");
			// Change thread count 1 for sequential , 2 for parallel 3 ..browser..
		}
	}

	@Test(priority = 1, groups = "smoke", description = "Start Swag Labs Web Application")
	private void startApplication() throws InterruptedException {
		// Mazimize current window
		driver.manage().window().maximize();
		// navigate to website
		driver.get("https://www.saucedemo.com/");
		// assertion if login page loaded
		PageBase.assertToObjectExistWithGetText(driver, "Accepted usernames are:");
		// take screenshot to login page
		PageBase.captureScreenshot(driver, "LoginPage");
	}

	@Test(priority = 2, groups = "smoke", description = "Login to Swag Labs Application")
	private void login() {
		loginPage = new LoginPage(driver);
		loginPage.userName.sendKeys("standard_user");
		loginPage.userPassword.sendKeys("secret_sauce");
		loginPage.loginButton.click();
		// assert if login successfully
		PageBase.assertToObjectExistWithGetText(driver, "Products");
		// take screenshot to login page
		PageBase.captureScreenshot(driver, "LoginPage");
	}

	@AfterTest
	public void tearDown() {
		driver.quit();
	}

	public static void getScreenshotOnFailure() {
		PageBase.captureScreenshot(driver, "fail" + java.time.LocalTime.now());
	}

}
