package PAGE;

import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class ScrollToLastTaskAndEdit 
{
	WebDriver Driver;
	
	//OBJECT REPOSIITORY
	By Inbox		  = By.id("filter_inbox");
	By ContentDiv	  = By.id("content");
	
	
	public ScrollToLastTaskAndEdit (WebDriver Driver)
	{
		this.Driver = Driver;
	}
	
	public void scrollAndDelete() throws InterruptedException
	{
		Driver.findElement(Inbox).click();
		Thread.sleep(3000);
		WebElement scrollableDiv = Driver.findElement(ContentDiv); 
		((JavascriptExecutor) Driver).executeScript( "arguments[0].scrollTop = arguments[0].scrollHeight;", scrollableDiv);
		
		Actions Act = new Actions(Driver);
		WebElement Last = Driver.findElement(By.xpath("//div[@data-index='18']"));
		Act.contextClick(Last).perform();
		Thread.sleep(2000);
		
		WebElement Edit = Driver.findElement(By.xpath("//div[@aria-label='task edit menu']"));
		((JavascriptExecutor) Driver).executeScript( "arguments[0].scrollTop = arguments[0].scrollHeight;", Edit);
		
		  String ParentWindow = Driver.getWindowHandle();	
		   Set <String> WindowHandles = Driver.getWindowHandles();
		   for (String handle : WindowHandles) 
		   {
			   if (!handle.equals(ParentWindow)) 
			   {
				   Driver.switchTo().window(handle);
			   }
		   }
		
		Driver.findElement(By.xpath("//div[text()='Delete']")).click();
		Thread.sleep(2000);
		
		  String ParentWindow1 = Driver.getWindowHandle();	
		   Set <String> WindowHandles1 = Driver.getWindowHandles();
		   for (String handle : WindowHandles1) 
		   {
			   if (!handle.equals(ParentWindow1)) 
			   {
				   Driver.switchTo().window(handle);
			   }
		   }
		   
		Driver.findElement(By.xpath("//button[span[text()='Delete']]")).click();
	}
}
