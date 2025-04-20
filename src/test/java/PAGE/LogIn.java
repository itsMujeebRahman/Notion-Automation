package PAGE;

import java.io.FileInputStream;
import java.io.IOException;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import UTILITIES.Utilities;

public class LogIn extends Utilities
{
	WebDriver Driver;
	
	//OBJECT RPOSITORY
	By Email = By.id("element-0");
	By Pass  = By.id("element-2");
	By Login = By.xpath("//button[@type='submit']");
	
	public  LogIn(WebDriver Driver)
	{
		this.Driver = Driver;
	}
	
	// logging in using Username and password -- working
	public void userpass(String Sheet) throws IOException, InterruptedException
	{
		path = new FileInputStream(F);
		wb = new XSSFWorkbook(path);
		XSSFSheet sheet1 = wb.getSheet(Sheet);
		
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
}
