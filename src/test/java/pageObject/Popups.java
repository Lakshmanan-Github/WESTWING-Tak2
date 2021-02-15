package pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Popups {
	WebDriver driver;
	WebDriverWait wait;

	public Popups(WebDriver driver)
	{
		this.driver = driver;

		wait = new WebDriverWait(driver, 10);
		PageFactory.initElements(driver, this);
	}

	//Cookies Popup
	@FindBy(xpath="//button[text()='Cookies akzeptieren']")
	public WebElement acceptCookie;

	//Registration Popup
	@FindBy(xpath="//button[text()='Hier einloggen']")
	public WebElement moveToLoginPopup;

	//Login Popup
	@FindBy(xpath="//button[text()='Anmelden']")
	public WebElement loginButton;

	@FindBy(name="email")
	public WebElement email_login;

	@FindBy(name="password")
	public WebElement password_login;


	public void accectCookies() throws InterruptedException
	{
		wait.until(ExpectedConditions.visibilityOf(acceptCookie));
		acceptCookie.click();
	}

	public void moveToLoginPopup() throws InterruptedException
	{
		wait.until(ExpectedConditions.visibilityOf(moveToLoginPopup));
		moveToLoginPopup.click();
	}

	public void emailInLoginPopup( String email) throws InterruptedException
	{
		wait.until(ExpectedConditions.visibilityOf(loginButton));
		email_login.sendKeys(email);
	}

	public void passwordInLoginPopup( String password) throws InterruptedException
	{
		wait.until(ExpectedConditions.visibilityOf(loginButton));
		password_login.sendKeys(password);
	}

	public void clickLoginInPopup()
	{
		wait.until(ExpectedConditions.visibilityOf(loginButton));
		loginButton.click();
	}

}
