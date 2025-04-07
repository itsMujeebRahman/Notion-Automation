package PAGE;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Set;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class Page_1 
{
	WebDriver Driver;
	By Email = By.xpath("//*[@id=\"notion-email-input-1\"]");
	By Pass = By.xpath("//*[@id=\"notion-password-input-2\"]");
	By EClick = By.xpath("//*[@id=\"notion-app\"]/div/div[1]/div/div/main/div[1]/section/div/div/div/div[2]/div[1]/div[2]/form/div[4]");
	By PClick = By.xpath("//*[@id=\"notion-app\"]/div/div[1]/div/div/main/div[1]/section/div/div/div/div[2]/div[1]/div[2]/form/div[4]");
	By Window = By.xpath("//*[@id=':r3:']/div/div/div[2]/div/span");
	public  Page_1(WebDriver Driver)
	{
		this.Driver = Driver;
	}
	
	// logging in using username and password
	public void userpass() throws IOException, InterruptedException
	{
		File f = new File("D:\\Silenium\\Notion_login.xlsx");
		FileInputStream path = new FileInputStream(f);
		XSSFWorkbook wb = new XSSFWorkbook(path);
		XSSFSheet sheet = wb.getSheet("Sheet1");
		
		for(int i=1;i<sheet.getLastRowNum();i++)
		{
			Driver.findElement(Email).clear();
			Driver.findElement(Email).sendKeys(sheet.getRow(i).getCell(0).getStringCellValue());
			Driver.findElement(EClick).click();
			Thread.sleep(2000);
		}
		
		for(int i=1;i<sheet.getLastRowNum();i++)
		{
			Driver.findElement(Pass).clear();
			Driver.findElement(Pass).sendKeys(sheet.getRow(i).getCell(1).getStringCellValue());
			Driver.findElement(PClick).click();
			Thread.sleep(2000);
		}
	}
	
	// window handling
	public void WindowHandle()
	{
		Driver.findElement(Window).click();
		
		String Parent = Driver.getWindowHandle();
		Set<String> allWindow = Driver.getWindowHandles();
		
		for(String Window : allWindow)
		{
			if(!Window.equals(Parent))
			{
				Driver.switchTo().window(Parent);
			}
		}
	}
	
	//Actions
	public void Actions()
	{
		Actions act = new Actions(Driver);
		WebElement Right = Driver.findElement(By.xpath("//*[@id=\":r2:\"]/div/div/div[1]/a/div/div[2]"));
		act.contextClick(Right).perform();
		WebElement Rename = Driver.findElement(By.xpath("//*[@id=\":r2:\"]/div/div/div[1]/a/div/div[2]"));
		Rename.sendKeys("NwProject");
	}
	
	//File Upload
	
	public void upload(String f) throws AWTException
	{
		Driver.findElement(By.xpath("//*[@id=\"notion-app\"]/div/div[1]/div/nav/div/div/div/div[4]/div[1]/div/div[2]/div/div[1]/div[2]/div/div[1]/div[1]"));
		Driver.findElement(By.xpath("//*[@id=\"notion-app\"]/div/div[2]/div[2]/div/div/div[2]/div[2]/div/div/div/div/div[1]/div[2]/div[1]"));
		Driver.findElement(By.xpath("//*[@id=\"settings-tab-profile\"]/div/div[2]"));
		Driver.findElement(By.xpath("//*[@id=\"settings-tabpanel-profile\"]/div/div/div[3]/div[1]/div[1]/div/div/div/div"));
		
		StringSelection select = new StringSelection(f);
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(select, null);
		
		Robot rob = new Robot();
		rob.keyPress(KeyEvent.VK_CONTROL);
		rob.keyPress(KeyEvent.VK_V);
		rob.keyRelease(KeyEvent.VK_V);
		rob.keyRelease(KeyEvent.VK_CONTROL);
		rob.keyPress(KeyEvent.VK_ENTER);
	}
	
	//Drag and Drop
	public void DragDrop()
	{
		Actions act = new Actions(Driver);
		WebElement Drag = Driver.findElement(By.xpath("//*[@id=\"notion-app\"]/div/div[1]/div/div[1]/main/div/div/div[4]/div[2]/div/div/div/div[3]/div[2]/div/div[1]/div[2]/a"));
		WebElement DragTo = Driver.findElement(By.xpath("//*[@id=\"notion-app\"]/div/div[1]/div/div[1]/main/div/div/div[4]/div[2]/div/div/div/div[3]/div[2]/div/div[2]/div[2]/a"));
		
		act.dragAndDrop(Drag, DragTo).perform();
	}
		
	}

