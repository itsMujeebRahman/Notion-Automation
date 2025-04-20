package PAGE;

import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ThemeChange 
{
	WebDriver Driver;
	
	//OBJECT REPOSITORY
	By Main  	      = By.id(":r0:");
	By Settings       = By.xpath("//a[@href='/app/settings/account']");
	By Theme 		  = By.xpath("//a[@href='/app/settings/theme']");
	By ThemeSyncBtn   = By.xpath("//div[text()='Sync theme']");
	By ToDoistTheme   = By.xpath("//button[@aria-label='Todoist']");
	By MoonStoneTheme = By.xpath("//button[@aria-label='Moonstone']");
	By TangerineTheme = By.xpath("//button[@aria-label='Tangerine']");
	By DarkTheme      = By.xpath("//button[@aria-label='Dark']");
	By ThemeUpdateBtn = By.xpath("//button[span[text()='Update']]");
	By TabClose       = By.xpath("//button[@aria-label='Close settings']");
	
	public ThemeChange (WebDriver Driver)
	{
		this.Driver = Driver;
	}
	
	// change theme 
	public void ThemeSwitch() throws InterruptedException
	{
		Driver.findElement(Main).click();
		Driver.findElement(Settings).click();
		
		  String ParentWindow = Driver.getWindowHandle();	
		   Set <String> WindowHandles = Driver.getWindowHandles();
		   for (String handle : WindowHandles) 
		   {
			   if (!handle.equals(ParentWindow)) 
			   {
				   Driver.switchTo().window(handle);
			   }
		   }
		Thread.sleep(2000);
		Driver.findElement(Theme).click();
		Thread.sleep(2000);
		Driver.findElement(ThemeSyncBtn).click();
		
		WebElement White = Driver.findElement(ToDoistTheme);
		if( White.getAttribute("aria-checked").equals("true"))
		{
			Driver.findElement(MoonStoneTheme).click();
			Thread.sleep(2000);
			Driver.findElement(TangerineTheme).click();
			Thread.sleep(2000);
			Driver.findElement(DarkTheme).click();
		}
		else 
		{
			Driver.findElement(ToDoistTheme).click();
		}
		
		Thread.sleep(3000);
		Driver.findElement(ThemeUpdateBtn).click();
		
		Thread.sleep(2000);
		
		Driver.findElement(TabClose).click();
	}
	
}
