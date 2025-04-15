package TEST;
import java.awt.AWTException;
import java.io.IOException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import PAGE.Page_1;

	public class Test 
	{
		WebDriver Driver ;
		@BeforeTest
		public void Setup()
		{
			Driver = new ChromeDriver();
		}
		
		@BeforeMethod
		public void Start() throws InterruptedException 
		{
			Driver.get("https://app.todoist.com/auth/login?_gl=1*1x3n4r8*_ga*ODkzNjk5MTg4LjE3NDQ1NDE5Mjc.*_ga_L1YQDZCTV5*MTc0NDU1MzA1OC4yLjAuMTc0NDU1MzA1OC42MC4wLjA.");
			Driver.manage().window().maximize();
			Thread.sleep(3000);
		
		}
		
		@org.testng.annotations.Test
		public void login() throws IOException, InterruptedException, AWTException 
		{
			Page_1 P =new Page_1(Driver);
			P.userpass();
			Thread.sleep(10000);


		
		}
	}
	

