package Operation.WriteNewMsg;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import Common.ElementVerify;
import Common.MyWDWait;
import Common.TestBase;
import Common.login;

public class TestMultiWrite extends TestBase{
	login l=null;
	public WebDriver wb=null;
	MyWDWait wait=null;
	boolean flag=true;
	int i;
	
	 @Test
	 public void push1() throws InterruptedException
	 {
			while(true)
			{
				try {
				beforeClass();
				}catch(Exception e) {}
				l.quiteBrower();
				
			}
	 }
	
	 @BeforeMethod
	  public void beforeMethod() throws InterruptedException  {

		
		
		
		
		
		 
		
	  }

	  @AfterMethod
	  public void afterMethod() {
		  
	  }
	  

	 

	  @AfterClass
	  public void afterClass() {
		  l.quiteBrower();
	  }

	  @BeforeClass (groups="only")
	 
	  public void beforeClass() throws InterruptedException {
		  l=new login();
				//Thread.sleep(1000);
				wb=l.my_driver;
				wait=new MyWDWait(wb);
				wb.findElement(By.xpath(".//*[@id='mcenter-logon']/div[1]/div[1]/input")).clear(); //找到手机输入框并清空
				wb.findElement(By.xpath(".//*[@id='mcenter-logon']/div[1]/div[1]/input")).sendKeys("13132919225"); //

				wb.findElement(By.xpath(".//*[@id='mcenter-logon']/div[1]/div[2]")).click();    //点击获得登录密码
				wb.findElement(By.xpath(".//*[@id='mcenter-logon']/div[3]/div/input")).clear(); //找到密码并清空
				wb.findElement(By.xpath(".//*[@id='mcenter-logon']/div[3]/div/input")).sendKeys("8888"); //输入密码
				wb.findElement(By.xpath(".//*[@id='mcenter-logon']/div[5]")).click();    //点击登录 
				
				wait.WaiteAllElement(By.xpath(".//*[@id='app']/*"));	
	  }

	  @AfterTest
	  public void afterTest() throws Exception {
		  
	  }

	  @BeforeSuite
	  public void beforeSuite() {
	  }

	  @AfterSuite
	  public void afterSuite() {
	  }

	}
