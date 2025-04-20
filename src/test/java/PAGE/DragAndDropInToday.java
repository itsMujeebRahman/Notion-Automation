package PAGE;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class DragAndDropInToday 
{
	WebDriver Driver;
	
	//OBJECT REPOSIITORY
	By Today		  = By.id("filter_today");
	By Filter		  = By.id("filters_labels");
	By TodayFirstTask = By.xpath("//div[@data-index='1']");
	By AddBtnAtEnd    = By.className("plus_add_button");
	By ToastClose     = By.xpath("//button[@aria-label='Close']");
	
	
	public DragAndDropInToday (WebDriver Driver)
	{
		this.Driver = Driver;
	}
		
	
	// code is working fine and test gets passed but , drag is not working visibly
	public void DragDrop() throws InterruptedException
	{
		Driver.findElement(Filter);
		
		Driver.findElement(Today).click();
		Actions Act = new Actions(Driver);
		Thread.sleep(3000);
		
		WebElement Drag = Driver.findElement(TodayFirstTask);
		WebElement DragTo = Driver.findElement(AddBtnAtEnd);
		Act.dragAndDrop(Drag, DragTo).perform();
		
	}
}
