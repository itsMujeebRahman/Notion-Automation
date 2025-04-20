package PAGE;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ProfilePhotoUploading 
{
	WebDriver Driver;
	
	//OBJECT REPOSITORY
	By Main  	      = By.id(":r0:");
	By Settings       = By.xpath("//a[@href='/app/settings/account']");
	By RemovePhoto	  = By.xpath("//button[span[text()='Remove photo']]");
	By RemovePhoto2	  = By.xpath("//button[span[text()='Remove']]");
	By UploadPhoto         = By.xpath("//button[span[text()='Upload photo']]");
	By TabClose       = By.xpath("//button[@aria-label='Close settings']");
	
	public ProfilePhotoUploading (WebDriver Driver)
	{
		this.Driver = Driver;
	}
	
	//File Upload - Profile Picture Remove
	public void upload(String photo) throws AWTException, InterruptedException
	{

		Driver.findElement(Main).click();
		
		Driver.findElement(Settings).click();
		
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
		String Source = Driver.getPageSource();
		
		if (Source.contains("Remove photo"))
		{
			Thread.sleep(3000);
			WebElement button = Driver.findElement(RemovePhoto);
			button.click();
			WebElement removeBtn = Driver.findElement(RemovePhoto2);
			removeBtn.click();
			Driver.navigate().refresh();
			Thread.sleep(5000);
			uploadfn(photo);
		}
		else
		{
			uploadfn(photo);
		}
		
		Driver.findElement(TabClose).click();
	}
	
	// function to upload
	public void uploadfn(String f) throws InterruptedException, AWTException 
	{
		
	WebElement upload = Driver.findElement(UploadPhoto);
	upload.click();
	
	Thread.sleep(2000);
	StringSelection select = new StringSelection(f);
	Toolkit.getDefaultToolkit().getSystemClipboard().setContents(select, null);
	
	Robot rob = new Robot();
	rob.keyPress	(KeyEvent.VK_CONTROL);
	rob.keyPress	(KeyEvent.VK_V);
	rob.keyRelease  (KeyEvent.VK_V);
	rob.keyRelease  (KeyEvent.VK_CONTROL);
	rob.keyPress	(KeyEvent.VK_ENTER);
	Thread.sleep(3000);
	Driver.findElement(TabClose).click();
	}
}
