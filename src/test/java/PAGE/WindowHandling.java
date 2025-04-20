package PAGE;

import java.util.Set;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class WindowHandling 
{
	WebDriver Driver;
	
	//OBJECT REPOSITORY
	By Main  	   = By.id(":r0:");
	By ChangeLog   = By.className("WeDBFxZ");
	
	public WindowHandling(WebDriver Driver)
	{
		this.Driver = Driver;
	}
	
	// window handling and Title Verification working
		public void windowhandle() throws InterruptedException
		{
			Driver.findElement(Main).click();
			
			Driver.findElement(ChangeLog).click();
			
			String Parent = Driver.getWindowHandle();
			Set<String> allWindow = Driver.getWindowHandles();
			
			for(String Window : allWindow)
			{
				if(!Window.equals(Parent))
				{
					Driver.switchTo().window(Window);
				}
			}
			
			Thread.sleep(3000);
			
			//Title Verification
			String title = Driver.getTitle();
			System.out.println(" Page title = " + title);
			String Extitle = "Changelog";
			
			if (title.equals(Extitle))
			{
				Driver.switchTo().window(Parent);
			}
		}
}
