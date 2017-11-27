/**
 * 
 */
package Operation;

import java.util.List;

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

import Common.MyDriver;
import Common.MyWDWait;
import Common.TestBase;
import Common.login;

/**
 * @author wei 运营人员 我的工作记录
 */
public class myWorkingHistory extends TestBase {
	login l = null;
	public WebDriver wb = null;
	MyWDWait wait = null;
	MyDriver mywb = null;

	public WebDriver getDriver() {
		return wb;
	}

	// 2 标题 我的工作记录 标题能否显示正确
	@Test
	public void a1Title() throws InterruptedException {

		wb.get("http://testmcenter.weixinxk.com/admin/account/my/index");
		Thread.sleep(1000);
		wait.WaiteAllElement(By.xpath(".//*[@id='app']/*"));
		System.out.println("方法运行前的URL是" + wb.getCurrentUrl());
		WebElement e = mywb
				.findElement(By.xpath(".//*[@id='app']/div/div[2]/div[2]/div[1]/div/div/div[2]/div[1]/div[2]/p"));
		e.click();
		Thread.sleep(300);
		Assert.assertEquals(e.getText(), "我的工作记录");

	}

	// 15 工作记录 登录消息中心 能否成功添加工作记录，且记录内容正确
	@Test
	@Parameters({ "OperationUser2" })
	public void b1LoginHistory(@Optional("13123456707") String OperationUser) throws InterruptedException {
		WebElement elogout = mywb.findElement(By.className("header-exit"));
		// By.className("header-exit") 或者用这个定位
		elogout.click();
		Thread.sleep(1000);
		l.quiteBrower();
		l = new login(OperationUser);

		wb = l.my_driver;

		wait = new MyWDWait(wb);
		mywb = new MyDriver(wb);

		wb.get("http://testmcenter.weixinxk.com/admin/account/my/index");
		Thread.sleep(1000);
		wait.WaiteAllElement(By.xpath(".//*[@id='app']/*"));
		System.out.println("方法运行前的URL是" + wb.getCurrentUrl());
		WebElement e = mywb
				.findElement(By.xpath(".//*[@id='app']/div/div[2]/div[2]/div[1]/div/div/div[2]/div[1]/div[2]/p"));
		e.click();
		Thread.sleep(300);

		WebElement eMsg1 = mywb.findElement(By.xpath(
				".//*[@id='app']/div/div[2]/div[2]/div[1]/div/div/div[2]/div[2]/div[2]/div/div[1]/div[1]/div[1]/span[1]"));
		Assert.assertEquals(eMsg1.getText(), "登录消息中心");
	}

	// 16 工作记录 退出消息中心 能否成功添加工作记录，且记录内容正确
	@Test
	public void b2LogoutHistory() throws InterruptedException {

		WebElement eMsg1 = mywb.findElement(By.xpath(
				".//*[@id='app']/div/div[2]/div[2]/div[1]/div/div/div[2]/div[2]/div[2]/div/div[1]/div[1]/div[2]/span[1]"));
		Assert.assertEquals(eMsg1.getText(), "退出消息中心");

	}
	// 17 工作记录 超过25条工作记录 能否出现分页

	@Test
	public void b3Pages() throws InterruptedException {

		List<WebElement> ePages = mywb.findElements(By.xpath(
				".//*[@id='app']/div/div[2]/div[2]/div[1]/div/div/div[2]/div[2]/div[2]/div/div[2]/div/ul/li[contains(@class,'page-num')]"));
		boolean flag = false;
		if (ePages.size() > 1) {
			flag = true;
		}
		Assert.assertEquals(flag, true);
	}

	@BeforeMethod
	public void beforeMethod() throws InterruptedException {

	}

	@AfterMethod
	public void afterMethod() {

	}

	@AfterClass
	public void afterClass() {
		l.quiteBrower();
	}

	@BeforeClass(groups = "only")
	@Parameters({ "OperationUser2" })
	public void beforeClass(@Optional("13123456707") String OperationUser) throws InterruptedException {

		l = new login(OperationUser);
		// Thread.sleep(1000);
		wb = l.my_driver;

		wait = new MyWDWait(wb);
		mywb = new MyDriver(wb);
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