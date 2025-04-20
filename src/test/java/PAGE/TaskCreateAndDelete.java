package PAGE;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import UTILITIES.Utilities;

public class TaskCreateAndDelete extends Utilities
{
WebDriver Driver;
	
	//OBJCT REPOSITORY
	By TaskBtn        = By.xpath("//*[@id=\"todoist_app\"]/div[1]/div[1]/div[2]/nav/div[2]/button");
	By TaskName       = By.xpath("//div[@aria-label='Task name']");
	By TaskDisc       = By.xpath("//div[@aria-label='Description']");
	By TaskSubmit     = By.xpath("//button[@data-testid='task-editor-submit-button']");
	By ToastClose     = By.xpath("//button[@aria-label='Close']");
	By TaskCheck      = By.className("PtDaWGV");
	By Today		  = By.id("filter_today");
	
	public TaskCreateAndDelete (WebDriver Driver)
	{
		this.Driver = Driver;
	}
	
	//adding tasks -- datariven

	
		public void CreateTask(String Sheet) throws InterruptedException, IOException 
		{
			Thread.sleep(2000);
			
			path = new FileInputStream(F);
			wb = new XSSFWorkbook(path);
			XSSFSheet sheet2 = wb.getSheet(Sheet);
			
			for(int i=1; i<=sheet2.getLastRowNum(); i++)
			{
			Driver.findElement(TaskBtn).click();
			Driver.findElement(TaskName).sendKeys(sheet2.getRow(i).getCell(0).getStringCellValue());
			Driver.findElement(TaskDisc).sendKeys(sheet2.getRow(i).getCell(1).getStringCellValue());
			Thread.sleep(1000);
			Driver.findElement(TaskSubmit).click();
			Driver.findElement(ToastClose).click();
			}
			
		}
		
		//uncheck all the tasks
		public void DeleteTask() throws InterruptedException, IOException
		{
			Driver.findElement(Today);
			
			path = new FileInputStream(F);
			wb = new XSSFWorkbook(path);
			XSSFSheet sheet2 = wb.getSheet("Sheet2");
			
			for(int i=1; i<=sheet2.getLastRowNum()-5; i++)
			{
			Driver.findElement(TaskCheck).click();
			Thread.sleep(500);
			Driver.findElement(ToastClose).click();
			}
		}
}
