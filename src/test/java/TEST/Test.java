package TEST;
import java.awt.AWTException;
import java.io.IOException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import PAGE.DragAndDropInToday;
import PAGE.LogIn;
import PAGE.LogOut;
import PAGE.ProfilePhotoUploading;
import PAGE.ProjectDetailsFilling;
import PAGE.ScrollToLastTaskAndEdit;
import PAGE.TaskCreateAndDelete;
import PAGE.TaskDetailsFilling;
import PAGE.ThemeChange;
import PAGE.WindowHandling;


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
		public void Function() throws IOException, InterruptedException, AWTException 
		{
			LogIn                   Login   = new LogIn		      (Driver);
			TaskCreateAndDelete     Create  = new TaskCreateAndDelete     (Driver);
			ProfilePhotoUploading   Profile = new ProfilePhotoUploading   (Driver);
			WindowHandling          Window  = new WindowHandling          (Driver);
			ThemeChange	        Theme   = new ThemeChange             (Driver);
			ScrollToLastTaskAndEdit Scroll  = new ScrollToLastTaskAndEdit (Driver);
			ProjectDetailsFilling   Project = new ProjectDetailsFilling   (Driver);
			DragAndDropInToday	Drag    = new DragAndDropInToday      (Driver);
			TaskDetailsFilling      Detail  = new TaskDetailsFilling      (Driver);
			LogOut			Logout  = new LogOut		      (Driver);
			
			
			Login.userpass("Sheet1");
			Thread.sleep(5000);
			
			//uploading profil picture - working
			Profile.upload("D:\\mujeeb.png");
			Thread.sleep(2000);
			
			//Changing Tabs and coming Back to Parent page - working
			Window.windowhandle();
			Thread.sleep(2000);
			
			//Creating 20 Tasks Continiosly - working
			Create.CreateTask("Sheet2");
			Thread.sleep(2000);
			
			//Switching Themes To Dark and White - working
			Theme.ThemeSwitch();
			Thread.sleep(2000);
			
			//Scroll to last of Inbox and Delete a task
			Scroll.scrollAndDelete();	
			Thread.sleep(2000);
			
			//Creating 5 Projects Continiously
			Project.ProjectCreation();
			Thread.sleep(2000);
			
			//chcking the unwanted projects
			Create.DeleteTask();
			Thread.sleep(2000);
			
			//Drags a Task to Another Task
			Drag.DragDrop();
			Thread.sleep(2000);
			
			//Task Details Filling by editig
			Detail.TaskEdit();
			Thread.sleep(2000);
			
			//Screnshot and logging out
			Logout.Out();
			Thread.sleep(2000);
		}
		
		@AfterMethod
		public void Finish()
		{
			System.out.println("TESTING COMPLETED");
			System.out.println("CLOSING THE BROWSER");
			
		}
		
		@AfterTest
		public void End()
		{
			Driver.quit();
		}
		
	}
	

