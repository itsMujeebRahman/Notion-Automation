package PAGE;
import java.io.File;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

public class LogOut 
{
WebDriver Driver;
	
	//OBJECT REPOSIITORY
	By Main = By.id(":r0:");
	
	
	
	public LogOut (WebDriver Driver)
	{
		this.Driver = Driver;
	}
	
	
	//screnshot of an element, Screnshot of the webpage and Generating Report
	public void Out() throws IOException, InterruptedException
	{
		//screenshot
		File screenshot = ((TakesScreenshot)Driver).getScreenshotAs(OutputType.FILE);
		FileHandler.copy(screenshot, new File ("D://photo.png"));
		
		
		Driver.findElement(Main).click();
		Thread.sleep(2000);
		Driver.findElement(By.xpath("//div[span[text()='Log out']]")).click();
		
	}
}
