package test_cases;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import pages.CartPage;
import pages.CheckoutFinal;
import pages.CheckoutPage;
import pages.InventoryPage;
import pages.PageBase;
import pages.ReturnToHomePAge;
import pages.SideMenu;

public class E2E_Testcase001_PurchasingAllProducts extends TestBase {

	@Test(priority = 3, groups = "smoke", description = "Choose All Products in Products Page")
	private void chooseAllProducts() throws InterruptedException {
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

		// take screenshot to login page
		PageBase.captureScreenshot(driver, "ChooseAllProduct");

		js.executeScript("window.scrollBy(0,-1000)", "");

		// wait for 5 sec
		Thread.sleep(2000);
	}

	@Test(priority = 4, groups = "smoke", description = "Choose Pay Button")
	private void clickToPay() throws InterruptedException {
		inventoryPage.moveToPaymentPage.click();
		// take screenshot to login page
		PageBase.captureScreenshot(driver, "PayButton");
		// wait for 5 sec
		Thread.sleep(2000);
	}

	@Test(priority = 5, groups = "smoke", description = "Click Checkout Button")
	private void clickToCheckout() throws InterruptedException {
		cartPage = new CartPage(driver);

		// declare javascript executer object
		JavascriptExecutor js = (JavascriptExecutor) driver;

		js.executeScript("window.scrollBy(0,1000)", "");

		cartPage.checkOut.click();

		js.executeScript("window.scrollBy(0,-1000)", "");

		// wait for 5 sec
		Thread.sleep(2000);
	}

	@Test(priority = 6, groups = "smoke", description = "Completer Checkout Operation")
	private void Checkout() throws InterruptedException {
		checkoutPage = new CheckoutPage(driver);

		checkoutPage.firstName.sendKeys("Shady Ahmed");

		checkoutPage.lastName.sendKeys("Mohamed");

		checkoutPage.postalCode.sendKeys("44671");
		// take screenshot to login page
		PageBase.captureScreenshot(driver, "PaymentData");

		// declare javascript executer object
		JavascriptExecutor js = (JavascriptExecutor) driver;

		js.executeScript("window.scrollBy(0,1000)", "");

		checkoutPage.continueButton.click();

		// wait for 5 sec
		Thread.sleep(2000);
	}

	@Test(priority = 7, groups = "smoke", description = "Finalize Payment")
	private void finalizeCheckout() throws InterruptedException {
		checkoutFinal = new CheckoutFinal(driver);

		checkoutFinal.finishButton.click();
		// take screenshot to login page
		PageBase.captureScreenshot(driver, "FinalizePayment");

		// wait for 5 sec
		Thread.sleep(2000);
	}

	@Test(priority = 8, groups = "smoke", description = "Return Back to Home Page")
	private void returnToHomePage() throws InterruptedException {
		returnToHomePAge = new ReturnToHomePAge(driver);

		returnToHomePAge.returnToHomePage.click();

		// declare javascript executer object
		JavascriptExecutor js = (JavascriptExecutor) driver;

		js.executeScript("window.scrollBy(0,-1000)", "");

		// take screenshot to login page
		PageBase.captureScreenshot(driver, "ReturnBackToHomePage");

		// wait for 5 sec
		Thread.sleep(2000);
	}

	@Test(priority = 9, groups = "smoke", description = "Log out from application")
	private void logout() throws InterruptedException {
		sideMenu = new SideMenu(driver);

		sideMenu.sidemenuButton.click();

		Thread.sleep(2000);

		sideMenu.logoutButton.click();

		// take screenshot to login page
		PageBase.captureScreenshot(driver, "LogoutFromApp");

		// wait for 5 sec
		Thread.sleep(2000);
	}
}
