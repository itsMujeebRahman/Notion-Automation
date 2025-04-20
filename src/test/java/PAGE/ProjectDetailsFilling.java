package PAGE;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Set;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import UTILITIES.Utilities;

public class ProjectDetailsFilling extends Utilities
{
	WebDriver Driver;
	//OBJECT RPOSITORY
	By ProjectsHover  = By.className("WwLx9J2");
	By ProjectPlusBtn = By.xpath("//button[@aria-label='My projects menu']");
	By AddProject 	  = By.xpath("//div[@aria-label='Add project']");	
	By ProjectName	  = By.name("name");
	By ProjectColor   = By.xpath("//div[text()='Charcoal']");
	By ProjectParent  = By.xpath("//div[text()='No Parent']");
	By ProjectParentSearch = By.xpath("//input[@name=\"search\"]");
	By ProjectAdd		= By.xpath("//button[span[text()='Add']]");
	
	public ProjectDetailsFilling (WebDriver Driver)
	{
		this.Driver = Driver;
	}
	
	
	public void ProjectCreation() throws InterruptedException, IOException
	{
		Actions Act = new Actions(Driver);
		
		path = new FileInputStream(F);
		wb = new XSSFWorkbook(path);
		XSSFSheet sheet3 = wb.getSheet("Sheet3");
		
		for(int i = 1; i<=sheet3.getLastRowNum(); i++)
		{
		Thread.sleep(3000);
		WebElement Move = Driver.findElement(ProjectsHover);
		Act.moveToElement(Move);
		
		WebElement Project = Driver.findElement(ProjectPlusBtn);
		Project.click();
		
		Driver.findElement(AddProject).click();
		String ParentWindow = Driver.getWindowHandle();	
		   Set <String> WindowHandles = Driver.getWindowHandles();
		   for (String handle : WindowHandles) 
		   {
			   if (!handle.equals(ParentWindow)) 
			   {
				   Driver.switchTo().window(handle);
			   }
		   }
		   
		   Driver.findElement(ProjectName).sendKeys(sheet3.getRow(i).getCell(0).getStringCellValue());
		   
		   Driver.findElement(ProjectColor).click();
		   Thread.sleep(2000);
		   WebElement color = Driver.findElement(By.id(sheet3.getRow(i).getCell(1).getStringCellValue()));
		   Thread.sleep(3000);
		   color.click();
		   
		  Driver.findElement(ProjectParent).click();
		   
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
		   
		   WebElement Parent =  Driver.findElement(ProjectParentSearch);
		   Thread.sleep(2000);
		   Parent.sendKeys(sheet3.getRow(i).getCell(2).getStringCellValue());
		   Thread.sleep(2000);
		   try
		   {
		   WebElement pro = Driver.findElement(By.xpath("//div[text()= '"+sheet3.getRow(i).getCell(2).getStringCellValue()+"']"));
		   pro.click();
		   }
		   catch(Exception e)
		   {
			   Actions actions = new Actions(Driver);
		       actions.sendKeys(Keys.ENTER).perform();
		   }
		   Thread.sleep(2000);
		  Driver.findElement(ProjectAdd).click();
		}
	}		
}
