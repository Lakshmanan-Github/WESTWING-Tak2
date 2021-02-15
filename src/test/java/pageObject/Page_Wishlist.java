package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Page_Wishlist {

	WebDriver driver;
	WebDriverWait wait;
	
	Page_Dashboard dashboard;
	
	public Page_Wishlist(WebDriver driver)
	{
		this.driver = driver;
		
		wait = new WebDriverWait(driver, 10);
		dashboard = new Page_Dashboard(driver);
		
        PageFactory.initElements(driver, this);
	}

	
	@FindBy(xpath="//*[@class='listProducts']/*[contains(@class,'blockListProduct')]")
    public WebElement allWishlistItems;
	
    
    public void removeWishlistProductByName(String productName)
    {	
    	
    	String deleteItemPath="//*[contains(@class,'ListProduct__name') and text()='"+productName+"']/preceding::button[1][contains(@class,'delete')]";
    	wait.until(ExpectedConditions.visibilityOf(allWishlistItems));
    	allWishlistItems.findElement(By.xpath(deleteItemPath)).click();
    }
    
    public void removeWishlistProductByIndex(Integer productIndex)
    {
    	wait.until(ExpectedConditions.visibilityOf(allWishlistItems.findElement(By.xpath("[1]/button[contains(@class,'delete')]"))));
    	allWishlistItems.findElement(By.xpath("[1]/button")).click();
    }
}
