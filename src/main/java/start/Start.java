package start;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import pom.LoginPage;

public class Start {

	// TODO declare class properties
	WebDriver driver;
	FileInputStream readProperty;
	LoginPage login;

	@BeforeTest
	private void prepareClassProperties() throws IOException {
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
	private void startApplication() throws InterruptedException {

		// Mazimize current window
		driver.manage().window().maximize();

		// navigate to website
		driver.get("https://www.saucedemo.com/");

		// wait for 5 sec
		Thread.sleep(10000);
	}

	@Test(priority = 1)
	private void login() {
		login.userName.sendKeys("standard_user");
		login.userPassword.sendKeys("secret_sauce");
		login.loginButton.click();
	}

	@AfterTest
	private void closeApplication() {
		driver.quit();
	}

}
