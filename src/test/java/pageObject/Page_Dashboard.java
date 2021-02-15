package pageObject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Page_Dashboard {

	WebDriver driver;
	WebDriverWait wait;

	public Page_Dashboard(WebDriver driver)
	{
		this.driver = driver;
		wait = new WebDriverWait(driver, 10);
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath="//input[contains(@placeholder, 'Produktsuche')]")
	public WebElement searchInput;

	@FindBy(xpath="//*[starts-with(@class, 'ProductGrid')]")
	public WebElement allProducts;

	@FindBy(xpath="//*[@class= 'icon-wishlist']")
	public WebElement wishlistIcon;


	@FindBy(id="spanMessage")
	public WebElement loginMsg;


	public void searchProduct(String searchText) throws InterruptedException
	{
		wait.until(ExpectedConditions.visibilityOf(searchInput));
		searchInput.clear();
		searchInput.sendKeys(searchText);
		searchInput.sendKeys(Keys.ENTER);
	}

	public void clickProductByIndex(Integer productIndex)
	{
		wait.until(ExpectedConditions.visibilityOf(allProducts.findElement(By.xpath("/div["+productIndex+"]"))));
		driver.findElement(By.xpath("//*[starts-with(@class, 'ProductGrid')]/div["+productIndex+"]")).click();
	}

	public String getProductName(Integer productIndex)
	{
		wait.until(ExpectedConditions.visibilityOf(allProducts));
		String productName = driver.findElement(By.xpath("//*[starts-with(@class, 'ProductGrid')]/div["+productIndex+"]//*[contains(@class, 'StyledTitle')]")).getText().toString();


		return productName;
	}

	public void clickWishlistByProductIndex(Integer productIndex) throws Exception
	{
		String wishlistIcon = "/div["+productIndex+"]//div[contains(@class, 'WishlistIcon')]";

		wait.until(ExpectedConditions.visibilityOf(allProducts));
		driver.findElement(By.xpath("//*[starts-with(@class, 'ProductGrid')]"+wishlistIcon)).click();
		driver.navigate().refresh();
		Thread.sleep(2000);
	}	

	public void clickWishlistIcon()
	{
		wait.until(ExpectedConditions.visibilityOf(wishlistIcon));
		wishlistIcon.click();
		driver.navigate().refresh();
	}

	public Integer wishlistCount()
	{
		wait.until(ExpectedConditions.visibilityOf(wishlistIcon));
		int count =0;
		List<WebElement> countBubble = driver.findElements(By.xpath("//*[@class='icon-wishlist']/../*"));

		for(int i=0; i<countBubble.size(); i++)
		{
			String tagName=countBubble.get(i).getTagName().toString();
			if(tagName.contentEquals("span"))
			{
				count = Integer.parseInt(countBubble.get(i).getText());
			}
		}

		return count;
	}
}
