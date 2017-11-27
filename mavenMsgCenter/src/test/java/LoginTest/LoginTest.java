package LoginTest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import Common.MyDriver;
import Common.MyWDWait;
import Common.TestBase;
import Common.login;

public class LoginTest extends TestBase {
	login l = null;
	public WebDriver wb = null;
	MyWDWait wait = null;
	MyDriver mywb = null;

	public WebDriver getDriver() {
		return wb;
	}

	// 1.验证首页跳转正常
	@Test(priority = 0)
	public void getUrl() throws InterruptedException {
		wait.WaiteTitleContains("消息");
		String title = mywb.getDriver().getTitle();
		System.out.println("页面跳转至" + title);
		Assert.assertEquals(title, "消息中心");

		// l.quiteBrower();

	}

	@Test(priority = 1) // 2. 手机号为空
	public void phoneisNull() throws InterruptedException {

		wait.WaiteElementClickable(By.xpath(".//*[@id='mcenter-logon']/div[5]"));
		mywb.findElement(By.xpath(".//*[@id='mcenter-logon']/div[1]/div[1]/input")).clear(); // 找到手机输入框并清空
		mywb.findElement(By.xpath(".//*[@id='mcenter-logon']/div[5]")).click(); // 点击登录按钮
		String t = mywb.findElement(By.xpath(".//*[@id='mcenter-logon']/div[2]/div")).getText(); // 获得提示语句

		Assert.assertEquals(t, "*请输入手机号码");
	}

	@Test(priority = 2) // 3. 手机号为异常格式1 位数短
	public void phoneisWrongformat1() throws InterruptedException {
		wait.WaiteAllElement(By.xpath(".//*[@id='mcenter-logon']"));
		mywb.findElement(By.xpath(".//*[@id='mcenter-logon']/div[1]/div[1]/input")).clear(); // 找到手机输入框并清空
		mywb.findElement(By.xpath(".//*[@id='mcenter-logon']/div[1]/div[1]/input")).sendKeys("1234567890"); // 输入错误格式

		mywb.findElement(By.xpath(".//*[@id='mcenter-logon']/div[3]/div/input")).clear(); // 找到密码并清空
		mywb.findElement(By.xpath(".//*[@id='mcenter-logon']/div[3]/div/input")).sendKeys("8888"); // 输入密码
		mywb.findElement(By.xpath(".//*[@id='mcenter-logon']/div[5]")).click(); // 点击登录按钮
		String t = mywb.findElement(By.xpath(".//*[@id='mcenter-logon']/div[2]/div")).getText(); // 获得提示语句
		Assert.assertEquals(t, "*该手机号码格式有误，请重新输入");
		Thread.sleep(500);
	}

	@Test(priority = 3) // 4.手机号为异常格式 非法字符
	public void phoneisWrongformat2() throws InterruptedException {

		mywb.findElement(By.xpath(".//*[@id='mcenter-logon']/div[1]/div[1]/input")).clear(); // 找到手机输入框并清空
		mywb.findElement(By.xpath(".//*[@id='mcenter-logon']/div[1]/div[1]/input")).sendKeys("1333333333a"); // 输入错误格式

		mywb.findElement(By.xpath(".//*[@id='mcenter-logon']/div[3]/div/input")).clear(); // 找到密码并清空
		mywb.findElement(By.xpath(".//*[@id='mcenter-logon']/div[3]/div/input")).sendKeys("8888"); // 输入密码
		mywb.findElement(By.xpath(".//*[@id='mcenter-logon']/div[5]")).click(); // 点击登录按钮
		String t = mywb.findElement(By.xpath(".//*[@id='mcenter-logon']/div[2]/div")).getText(); // 获得提示语句
		Assert.assertEquals(t, "*该手机号码格式有误，请重新输入");
		Thread.sleep(500);

	}

	@Test(priority = 4) // 5.手机号为异常格式 位数长
	public void phoneisWrongformat3() throws InterruptedException {

		mywb.findElement(By.xpath(".//*[@id='mcenter-logon']/div[1]/div[1]/input")).clear(); // 找到手机输入框并清空
		mywb.findElement(By.xpath(".//*[@id='mcenter-logon']/div[1]/div[1]/input")).sendKeys("13321321321312321312"); // 输入错误格式

		mywb.findElement(By.xpath(".//*[@id='mcenter-logon']/div[3]/div/input")).clear(); // 找到密码并清空
		mywb.findElement(By.xpath(".//*[@id='mcenter-logon']/div[3]/div/input")).sendKeys("8888"); // 输入密码
		mywb.findElement(By.xpath(".//*[@id='mcenter-logon']/div[5]")).click(); // 点击登录按钮
		String t = mywb.findElement(By.xpath(".//*[@id='mcenter-logon']/div[1]/div[1]/input")).getAttribute("value"); // 获得提示语句
		Assert.assertEquals(t, "13321321321");
		Thread.sleep(500);
	}

	@Test(priority = 5) // 6.手机号为错误号码
	public void phoneisWrongNumber() throws InterruptedException {

		mywb.findElement(By.xpath(".//*[@id='mcenter-logon']/div[1]/div[1]/input")).clear(); // 找到手机输入框并清空
		mywb.findElement(By.xpath(".//*[@id='mcenter-logon']/div[1]/div[1]/input")).sendKeys("177733318271"); // 输入错误格式

		mywb.findElement(By.xpath(".//*[@id='mcenter-logon']/div[3]/div/input")).clear(); // 找到密码并清空
		mywb.findElement(By.xpath(".//*[@id='mcenter-logon']/div[3]/div/input")).sendKeys("8888"); // 输入密码
		mywb.findElement(By.xpath(".//*[@id='mcenter-logon']/div[5]")).click(); // 点击登录按钮
		String t = mywb.findElement(By.xpath(".//*[@id='mcenter-logon']/div[2]/div")).getText(); // 获得提示语句

		Assert.assertEquals(t, "*该手机号码未开设账号，请开设后再登录");
		Thread.sleep(500);
	}

	@Test(priority = 6) // 7.测试锁定
	@Parameters({ "lockedUser" })
	public void phoneisLock(@Optional("13123456701") String lockedUser) throws InterruptedException {

		mywb.findElement(By.xpath(".//*[@id='mcenter-logon']/div[1]/div[1]/input")).clear(); // 找到手机输入框并清空
		mywb.findElement(By.xpath(".//*[@id='mcenter-logon']/div[1]/div[1]/input")).sendKeys(lockedUser); // 输入锁住的user
		mywb.findElement(By.xpath(".//*[@id='mcenter-logon']/div[1]/div[2]")).click(); // 点击获得登录密码

		String t = mywb.findElement(By.xpath(".//*[@id='mcenter-logon']/div[2]/div")).getText(); // 获得提示语句
		Assert.assertEquals(t, "*此账号已被锁定，请与系统管理员联系");
		Thread.sleep(500);
	}

	@Test(priority = 9) // 8.密码长度验证 用的测试人员
	public void phoneisWrongPwd() throws InterruptedException {

		// String t=mywb.getCurrentUrl();
		// mywb.findElement(By.xpath(".//*[@id='mcenter-logon']/div[1]/div[1]/input")).clear();
		// //找到手机输入框并清空
		mywb.findElement(By.xpath(".//*[@id='mcenter-logon']/div[1]/div[1]/input")).sendKeys("17100000000"); // 输入错误格式

		mywb.findElement(By.xpath(".//*[@id='mcenter-logon']/div[1]/div[2]")).click(); // 点击获得登录密码
		mywb.findElement(By.xpath(".//*[@id='mcenter-logon']/div[3]/div/input")).clear(); // 找到密码并清空
		mywb.findElement(By.xpath(".//*[@id='mcenter-logon']/div[3]/div/input")).sendKeys("888888"); // 输入密码
		// mywb.findElement(By.xpath(".//*[@id='mcenter-logon']/div[5]")).click();
		// //点击登录
		// Thread.sleep(1500);
		String t = mywb.findElement(By.xpath(".//*[@id='mcenter-logon']/div[3]/div/input")).getAttribute("value");
		Assert.assertEquals(t, "8888");
		Thread.sleep(500);
	}

	@BeforeMethod
	public void beforeMethod() {
	}

	@AfterMethod
	public void afterMethod() {
	}

	@BeforeClass
	public void beforeClass() throws InterruptedException {
		l = new login();
		// Thread.sleep(1000);
		wb = l.my_driver;
		wait = new MyWDWait(wb);
		mywb = new MyDriver(wb);

	}

	@AfterClass
	public void afterClass() {
		l.quiteBrower();
	}

	@BeforeTest
	public void beforeTest() {
	}

	@AfterTest
	public void afterTest() {
	}

	@BeforeSuite
	public void beforeSuite() {
	}

	@AfterSuite
	public void afterSuite() {
	}

}
