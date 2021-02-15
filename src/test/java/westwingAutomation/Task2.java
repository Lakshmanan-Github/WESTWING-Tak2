package westwingAutomation;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import pageObject.Page_Dashboard;
import pageObject.Page_Wishlist;
import pageObject.Popups;
import suportingFiles.BrowserSelection;
import suportingFiles.Screenshot;

public class Task2 
{

	BrowserSelection browser = new BrowserSelection();
	WebDriver driver;
	WebDriverWait wait;

	Page_Dashboard dashboard;
	Page_Wishlist wishlist;
	Popups popup;
	Screenshot screenshot =new Screenshot();

	static ExtentTest test;
	static ExtentReports report;

	@BeforeTest
	public void TestSetup()
	{
		report = new ExtentReports(System.getProperty("user.dir")+"/Output/Report/ExtentReport.html");
	}

	@BeforeMethod
	public void Setup()
	{
		driver = browser.openBrowser("Chrome");
		wait = new WebDriverWait(driver, 10);
		driver.get("https://www.westwingnow.de/");

		dashboard = new Page_Dashboard(driver);
		wishlist = new Page_Wishlist(driver);
		popup = new Popups(driver);
	}

	@Test
	public void TC1_SearchProduct() throws Exception
	{
		test = report.startTest("TC1_SearchProduct");

		String userEmail = ""; //Please enter the user email address before execution
		String password = ""; //Please enter the password before execution

		popup.accectCookies();

		dashboard.searchProduct("möble");

		popup.moveToLoginPopup();

		popup.emailInLoginPopup(userEmail); 
		popup.passwordInLoginPopup(password);

		popup.clickLoginInPopup();

		screenshot.Take_ScreenShot("TC_1_SearchProuct_Step2");
		int initialWishlistCount = dashboard.wishlistCount();

		String productName = dashboard.getProductName(1);

		dashboard.clickWishlistByProductIndex(1);

		int currentWishlistCount = dashboard.wishlistCount();
		screenshot.Take_ScreenShot("TC_1_SearchProuct_Step3");

		SoftAssert validate = new SoftAssert();
		validate.assertEquals(currentWishlistCount, initialWishlistCount+1);

		dashboard.clickWishlistIcon();	
		screenshot.Take_ScreenShot("TC_1_SearchProuct_Step4");

		wishlist.removeWishlistProductByName(productName);
		screenshot.Take_ScreenShot("TC_1_SearchProuct_Step5");

		int wishlistCountAfterProductRemoved = dashboard.wishlistCount();

		validate.assertEquals(wishlistCountAfterProductRemoved, initialWishlistCount);

		validate.assertAll();
		test.log(LogStatus.INFO, "test");
	}
	@AfterMethod
	public void TeadDown()
	{
		driver.quit();
	}

	@AfterTest
	public void TestEnd()
	{
		report.endTest(test);
		report.flush();
	}
}