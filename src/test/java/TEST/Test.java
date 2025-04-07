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
			Driver.get("https://www.notion.so/login");
			Driver.manage().window().maximize();
			Thread.sleep(3000);
		}
		
		@org.testng.annotations.Test
		public void login() throws IOException, InterruptedException, AWTException 
		{
			Page_1 P =new Page_1(Driver);
			P.userpass();
			Thread.sleep(10000);
			P.WindowHandle();
			Thread.sleep(3000);
			P.Actions();
			Thread.sleep(3000);
			P.upload(null);
			Thread.sleep(3000);
			P.DragDrop();
			
		}
	}
	

