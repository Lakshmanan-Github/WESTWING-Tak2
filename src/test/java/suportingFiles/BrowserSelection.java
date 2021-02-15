package suportingFiles;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class BrowserSelection {

	public WebDriver openBrowser(String browser)
	{
		WebDriver driver=null;
		if(browser.equalsIgnoreCase("chrome")) 
		{
			Map<String, Object> prefs = new HashMap<String, Object>();
			prefs.put("profile.default_content_setting_values.notifications", 2);
			prefs.put("credentials_enable_service", false);
			prefs.put("profile.password_manager_enabled", false);
			ChromeOptions options = new ChromeOptions();
			options.addArguments("disable-infobars");
			options.setExperimentalOption("prefs", prefs);
			options.setExperimentalOption("useAutomationExtension", false);
			options.setExperimentalOption("excludeSwitches",Collections.singletonList("enable-automation"));

			System.setProperty("webdriver.chrome.driver", "src/test/resources/Drivers/chromedriver");
			driver=new ChromeDriver(options);

			driver.manage().window().maximize();
		}

		return driver;

	}
}