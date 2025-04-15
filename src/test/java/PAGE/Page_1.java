package PAGE;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Set;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;


public class Page_1 
{
	WebDriver Driver;
	//OBJECT RPOSITORY
	By Email 	 = By.id("element-0");
	By Pass  	 = By.id("element-2");
	By Login 	 = By.xpath("//button[@type='submit']");
	By Main  	 = By.id(":r0:");
	By ChangeLog = By.className("WeDBFxZ");
	
	File F = new File("D:\\Silenium\\ToDo-Data.xlsx");
	FileInputStream path;
	XSSFWorkbook wb ;
	
	public  Page_1(WebDriver Driver)
	{
		this.Driver = Driver;
	}
	
	// logging in using Username and password -- working
	public void userpass() throws IOException, InterruptedException
	{
		path = new FileInputStream(F);
		wb = new XSSFWorkbook(path);
		XSSFSheet sheet1 = wb.getSheet("Sheet1");
		for(int i=1;i<=sheet1.getLastRowNum();i++)
		{
			Driver.findElement(Email).clear();
			Driver.findElement(Email).sendKeys(sheet1.getRow(i).getCell(0).getStringCellValue());
			Driver.findElement(Pass).clear();
			Driver.findElement(Pass).sendKeys(sheet1.getRow(i).getCell(1).getStringCellValue());
			Driver.findElement(Login).click();
			Thread.sleep(2000);
		}
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
	
	//File Upload - Profile Picture Change
	public void upload() throws AWTException, InterruptedException
	{

		Driver.findElement(By.id(":r0:")).click();
		
		Driver.findElement(By.xpath("//a[@href='/app/settings/account']")).click();
		
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
			WebElement button = Driver.findElement(By.xpath("//button[span[text()='Remove photo']]"));
			button.click();
			WebElement removeBtn = Driver.findElement(By.xpath("//button[span[text()='Remove']]"));
			removeBtn.click();
			Driver.navigate().refresh();
			Thread.sleep(5000);
			uploadfn("D:\\mujeeb.png");
		}
		else
		{
			uploadfn("D:\\mujeeb.png");
		}
		
	}
	// function to upload
	public void uploadfn(String f) throws InterruptedException, AWTException 
	{
		
	WebElement upload = Driver.findElement(By.xpath("//button[span[text()='Upload photo']]"));
	upload.click();
	
	Thread.sleep(2000);
	StringSelection select = new StringSelection(f);
	Toolkit.getDefaultToolkit().getSystemClipboard().setContents(select, null);
	
	Robot rob = new Robot();
	rob.keyPress(KeyEvent.VK_CONTROL);
	rob.keyPress(KeyEvent.VK_V);
	rob.keyRelease(KeyEvent.VK_V);
	rob.keyRelease(KeyEvent.VK_CONTROL);
	rob.keyPress(KeyEvent.VK_ENTER);
	Thread.sleep(3000);
	Driver.findElement(By.xpath("//button[@aria-label='Close settings']")).click();
	}
		
	//adding tasks -- datariven
	public void AddTask() throws InterruptedException, IOException 
	{
		

		Thread.sleep(2000);
		path = new FileInputStream(F);
		wb = new XSSFWorkbook(path);
		XSSFSheet sheet2 = wb.getSheet("Sheet2");
		
		for(int i=1; i<=sheet2.getLastRowNum(); i++)
		{
		Driver.findElement(By.xpath("//*[@id=\"todoist_app\"]/div[1]/div[1]/div[2]/nav/div[2]/button")).click();
		Driver.findElement(By.xpath("//div[@aria-label='Task name']")).sendKeys(sheet2.getRow(i).getCell(0).getStringCellValue());
		Driver.findElement(By.xpath("//div[@aria-label='Description']")).sendKeys(sheet2.getRow(i).getCell(1).getStringCellValue());
		Thread.sleep(1000);
		Driver.findElement(By.xpath("//button[@data-testid='task-editor-submit-button']")).click();
		Driver.findElement(By.xpath("//button[@aria-label='Close']")).click();
		}
		
		//unchck all the tasks
		/*for(int i=1; i<=sheet2.getLastRowNum(); i++)
		{
		Driver.findElement(By.className("PtDaWGV")).click();
		Thread.sleep(500);
		Driver.findElement(By.xpath("//button[@aria-label='Close']")).click();
		}*/
		
	}
	// change theme 
	public void Theme() throws InterruptedException
	{
		Driver.findElement(By.id(":r0:")).click();
		Driver.findElement(By.xpath("//a[@href='/app/settings/account']")).click();
		
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
		Driver.findElement(By.xpath("//a[@href='/app/settings/theme']")).click();
		Thread.sleep(2000);
		Driver.findElement(By.xpath("//div[text()='Sync theme']")).click();
		
		WebElement White = Driver.findElement(By.xpath("//button[@aria-label='Todoist']"));
		if( White.getAttribute("aria-checked").equals("true"))
		{
			Driver.findElement(By.xpath("//button[@aria-label='Moonstone']")).click();
			Thread.sleep(2000);
			Driver.findElement(By.xpath("//button[@aria-label='Tangerine']")).click();
			Thread.sleep(2000);
			Driver.findElement(By.xpath("//button[@aria-label='Dark']")).click();
		}
		else 
		{
			Driver.findElement(By.xpath("//button[@aria-label='Todoist']")).click();
		}
		
		Thread.sleep(3000);
		Driver.findElement(By.xpath("//button[span[text()='Update']]")).click();
		
		Thread.sleep(2000);
		
		Driver.findElement(By.xpath("//button[@aria-label='Close settings']")).click();
	}
	
	public void TaskDetail() throws InterruptedException
	{
		WebElement Side = Driver.findElement(By.xpath("//button[@aria-controls='sidebar']"));
		Side.click();
		Thread.sleep(2000);
		Driver.findElement(By.xpath("//div[@data-index='1']")).click();		
		
		Driver.findElement(By.xpath("//button[span[text()='Today']]")).click();
		
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
			WebElement Month = Driver.findElement(By.className("date-picker-header"));
			String M = Month.getText();
			
			if (M.equals("Apr 2026"))
			{
				System.out.println("The Date is = "+ M);
				break;
			}
			else
			{
				Thread.sleep(500);
				Driver.findElement(By.xpath("//button[@aria-label='Navigate to next month']")).click();
			}
		}
		
		Thread.sleep(2000);
		List <WebElement> Date = Driver.findElements(By.className("calendar__day"));

		for (WebElement Date1:Date)
		{
			String Dat = Date1.getText();
			if(Dat.equals("20"))
			{
				Date1.click();
				break;
			}
		}
		
		Driver.findElement(By.xpath("//button[@aria-label='Close task']")).click();
		Side.click();
		Thread.sleep(3000);
		Driver.findElement(By.xpath("//button[@aria-label='Close']")).click();
	}
	
	public void scroll() throws InterruptedException
	{
		Driver.findElement(By.id("filter_inbox")).click();
		Thread.sleep(3000);
		WebElement scrollableDiv = Driver.findElement(By.id("content")); 
		((JavascriptExecutor) Driver).executeScript( "arguments[0].scrollTop = arguments[0].scrollHeight;", scrollableDiv);
	}
	
	// code is working fine and test gets passed but , drag is not working visibly
	public void DragDrop() throws InterruptedException
	{
		Driver.findElement(By.id("filter_today")).click();
		Actions Act = new Actions(Driver);
		Thread.sleep(3000);
		
		WebElement Drag = Driver.findElement(By.xpath("//div[@data-index='4']"));
		WebElement DragTo = Driver.findElement(By.className("plus_add_button"));
		Act.dragAndDrop(Drag, DragTo).perform();
	}
	
	public void Projects() throws InterruptedException, IOException
	{
		path = new FileInputStream(F);
		wb = new XSSFWorkbook(path);
		XSSFSheet sheet3 = wb.getSheet("Sheet3");
		
		Actions Act = new Actions(Driver);
		
		for(int i = 1; i<=sheet3.getLastRowNum(); i++)
		{
		Thread.sleep(3000);
		WebElement Move = Driver.findElement(By.className("WwLx9J2"));
		Act.moveToElement(Move);
		
		WebElement Project = Driver.findElement(By.xpath("//button[@aria-label='My projects menu']"));
		Project.click();
		
		Driver.findElement(By.xpath("//div[@aria-label='Add project']")).click();
		String ParentWindow = Driver.getWindowHandle();	
		   Set <String> WindowHandles = Driver.getWindowHandles();
		   for (String handle : WindowHandles) 
		   {
			   if (!handle.equals(ParentWindow)) 
			   {
				   Driver.switchTo().window(handle);
			   }
		   }
		   
		   Driver.findElement(By.xpath("//input[@name='name']")).sendKeys(sheet3.getRow(i).getCell(0).getStringCellValue());
		   
		   Driver.findElement(By.xpath("//div[text()='Charcoal']")).click();
		   Thread.sleep(2000);
		   WebElement color = Driver.findElement(By.id(sheet3.getRow(i).getCell(1).getStringCellValue()));
		   Thread.sleep(3000);
		   color.click();
		   
		  Driver.findElement(By.xpath("//div[text()='No Parent']")).click();
		   
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
		   
		   WebElement Parent =  Driver.findElement(By.xpath("//input[@name=\"search\"]"));
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
		  Driver.findElement(By.xpath("//button[span[text()='Add']]")).click();
		}
	}		

}

