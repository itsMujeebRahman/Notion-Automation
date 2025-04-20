package PAGE;

import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class TaskDetailsFilling 
{
	WebDriver Driver;
	
	//OBJCT REPOSITORY
	By Today		  = By.id("filter_today");
	By SideBarControl = By.xpath("//button[@aria-controls='sidebar']");
	By FirstTask	  = By.className("MIKYuSS");
	By NoDate 	  	  = By.xpath("//button[@aria-label='No Date']");
	By YearMonthPick  = By.className("date-picker-header");
	By NextMonthBtn   = By.xpath("//button[@aria-label='Navigate to next month']");
	By DayPick 		  = By.className("calendar__day");
	By TaskTabClose   = By.xpath("//button[@aria-label='Close task']");
	By ToastClose     = By.xpath("//button[@aria-label='Close']");
	
	public TaskDetailsFilling (WebDriver Driver)
	{
		this.Driver = Driver;
	}
	
	public void TaskEdit() throws InterruptedException
	{
	Driver.findElement(Today).click();
	Thread.sleep(2000);
	WebElement Side = Driver.findElement(SideBarControl);
	Side.click();
	Thread.sleep(3000);
	Driver.findElement(FirstTask).click();
	
	Driver.findElement(By.xpath("//div[@aria-label='Task name']")).click();
	Driver.findElement(By.xpath("//div[@aria-label='Task name']")).clear();
	Driver.findElement(By.xpath("//div[@aria-label='Task name']")).sendKeys("Go For Outing");
	Thread.sleep(1000);
	Driver.findElement(By.xpath("//div[@aria-label='Description']")).clear();
	Driver.findElement(By.xpath("//div[@aria-label='Description']"))
	.sendKeys("Made a new frind ystrday , going out with him for Movies");
	Thread.sleep(1000);
	Driver.findElement(By.xpath("//button[span[text()='Save']]")).click();
	Thread.sleep(2000);
	Driver.findElement(By.xpath("//button[span[text()='Add sub-task']]")).click();
	Thread.sleep(1000);
	Driver.findElement(By.xpath("//p[@data-placeholder='Task name']")).sendKeys("Bring The Phone ");
	Driver.findElement(By.xpath("//p[@data-placeholder='Description']")).sendKeys("Bring the Chargr and Headphone too");
	
	
	Driver.findElement(By.xpath("//div[@aria-label='Set date']")).click();
	DateChange("Jun 2025");
	
	Driver.findElement(By.xpath("//div[@aria-label='Set priority']")).click();
	Driver.findElement(By.xpath("//div[ul[li[@aria-label='Priority 2']]]")).click();
	

	   
	Thread.sleep(3000);
	Driver.findElement(By.xpath("//button[@type='submit']")).click();
	   
    Driver.findElement(By.xpath("//button[@aria-label='Cancel']")).click();	   
	Thread.sleep(2000);
	   
    Driver.findElement(By.xpath("//button[@aria-label='Open comment editor']")).click();
	Thread.sleep(1000);
	Driver.findElement(By.xpath("//div[@aria-label='Comment']"))
	.sendKeys("No comments");
	   
	Driver.findElement(By.xpath("//button[@type='submit']")).click();
	Driver.findElement(By.xpath("//button[@aria-label='Close comment editor']")).click();	
	   
	Driver.findElement(By.xpath("//button[@aria-label='Select a project']")).click();
	   
	WebElement Search = Driver.findElement(By.name("search"));
	Search.sendKeys("PROJECT -1");
	Thread.sleep(1000);
	Search.sendKeys(Keys.ENTER);
	   
	Thread.sleep(2000);
	Driver.findElement(NoDate).click();
	
	Driver.findElement(By.xpath("//div[button[span[text()='Date']]]")).click();
	DateChange("Aug 2025");
	  
	Thread.sleep(2000);
	   
	Driver.findElement(By.xpath("//button[span[text()='Labels']]")).click();
	WebElement Label =  Driver.findElement(By.name("search"));
	Label.sendKeys("Addition");
	Label.sendKeys(Keys.ENTER);
	Label.clear();
	Label.sendKeys("Nothing");
	Label.sendKeys(Keys.ENTER);
	Label.clear();
	Actions actions = new Actions(Driver);
	actions.moveByOffset(10, 10).click().perform();
	   
	Driver.findElement(TaskTabClose).click();
	Side.click();
	Thread.sleep(3000);
	Driver.findElement(ToastClose).click();
	   
	}
	
	//Date Change
	
	
	public void DateChange(String MDate) throws InterruptedException
	{		
		
		
		  String ParentWindow = Driver.getWindowHandle();	
		   Set <String> WindowHandles = Driver.getWindowHandles();
		   for (String handle : WindowHandles) 
		   {
			   if (!handle.equals(ParentWindow)) 
			   {
				   Driver.switchTo().window(handle);
			   }
		   }
		
		   Thread.sleep(3000);

		while(true)
		{
			WebElement Month = Driver.findElement(YearMonthPick);
			String M = Month.getText();
			
			if (M.equals(MDate))
			{
				System.out.println("The Date is = "+ M);
				break;
			}
			else
			{
				Thread.sleep(500);
				Driver.findElement(NextMonthBtn).click();
			}
		}
		
		Thread.sleep(2000);
		List <WebElement> Date = Driver.findElements(DayPick);

		for (WebElement Date1:Date)
		{
			String Dat = Date1.getText();
			if(Dat.equals("20"))
			{
				Date1.click();
				break;
			}
		}
	}
	
	
	
	
}
