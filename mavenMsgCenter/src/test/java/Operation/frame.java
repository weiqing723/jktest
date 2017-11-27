/**
 * 
 */
package Operation;

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
 * @author wei 运营人员 相框 检查点
 */
public class frame extends TestBase {
	login l = null;
	public WebDriver wb = null;
	MyWDWait wait = null;
	MyDriver mywb = null;

	public WebDriver getDriver() {
		return wb;
	}

	// 3 跳转 相框页，点击浏览器自带返回按钮 能否返回到上一级页面
	@Test
	public void a1back() throws InterruptedException {
		wb.navigate().back();
		Thread.sleep(1000);
		wait.WaitURLContains("/edit/index");
		String s = wb.getCurrentUrl();
		Assert.assertEquals(s, "http://testmcenter.weixinxk.com/admin/pushmessage/edit/index");
	}

	// 4 跳转 相框页，点击左侧导航中，新消息按钮 能否成功跳转到新消息列表页
	@Test
	public void a2NewMsg() throws InterruptedException {

		WebElement eNewMsg = mywb.findElement(By.xpath(".//*[@id='app']//p[text()='新消息']"));
		eNewMsg.click();
		wait.WaiteAllElement(By.xpath(".//*[@id='app']/*"));

		Thread.sleep(100);
		Assert.assertEquals(wb.getCurrentUrl(), "http://testmcenter.weixinxk.com/admin/pushmessage/edit/index");

	}

	// 5 跳转 相框页，点击左侧导航中，历史消息按钮 能否成功跳转到历史消息页
	@Test
	public void a3HistoryMsg() throws InterruptedException {
		WebElement eOldMsg = mywb.findElement(By.xpath(".//*[@id='app']//p[text()='历史消息']"));
		eOldMsg.click();
		wait.WaiteAllElement(By.xpath(".//*[@id='app']/*"));

		Thread.sleep(100);
		Assert.assertEquals(wb.getCurrentUrl(), "http://testmcenter.weixinxk.com/admin/pushmessage/history/index");

	}

	// 6 跳转 相框页，点击左侧导航中，相框按钮 能否成功刷新页面
	@Test
	public void a4Frame() throws Exception

	{
		WebElement eFrame = mywb.findElement(By.xpath(".//*[@id='app']//p[text()='相框']"));
		eFrame.click();
		wait.WaiteAllElement(By.xpath(".//*[@id='app']/*"));

		Thread.sleep(100);
		Assert.assertEquals(wb.getCurrentUrl(), "http://testmcenter.weixinxk.com/admin/statistic/album/index");

	}

	// 7 跳转 相框页，点击左侧导航中，设备按钮 能否成功跳转到设备页面
	@Test
	public void a5Device() throws Exception {
		WebElement e = mywb.findElement(By.xpath(".//*[@id='app']//p[text()='设备']"));
		e.click();
		wait.WaiteAllElement(By.xpath(".//*[@id='app']/*"));

		Thread.sleep(100);
		Assert.assertEquals(wb.getCurrentUrl(), "http://testmcenter.weixinxk.com/admin/statistic/device/index");

	}

	// 8 跳转 相框页，点击左侧导航中，合作伙伴按钮 能否成功跳转到合作伙伴页面
	@Test
	public void a6Parnter() throws Exception

	{
		WebElement e = mywb.findElement(By.xpath(".//*[@id='app']//p[text()='合作伙伴']"));
		e.click();
		wait.WaiteAllElement(By.xpath(".//*[@id='app']/*"));

		Thread.sleep(100);
		Assert.assertEquals(wb.getCurrentUrl(), "http://testmcenter.weixinxk.com/admin/statistic/partner/index");

	}

	// 12 跳转 相框页，点击退出按钮 能否成功退出消息中心，回到登录页面
	@Test
	public void a7Logout() throws Exception

	{
		WebElement e = mywb.findElement(By.className("header-exit"));
		// By.className("header-exit") 或者用这个定位
		e.click();
		Thread.sleep(1000);
		wait.WaiteAllElement(By.xpath(".//*[@id='app']/*"));
		Assert.assertEquals(wb.getCurrentUrl(), "http://testmcenter.weixinxk.com/admin/logon");

	}

	@BeforeMethod
	public void beforeMethod() throws InterruptedException {

		wb.get("http://testmcenter.weixinxk.com/pages/album/index");
		Thread.sleep(1000);

		wait.WaiteAllElement(By.xpath(".//*[@id='app']/*"));

		System.out.println("方法运行前的URL是" + wb.getCurrentUrl());

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
	public void beforeClass(@Optional("13132919225") String OperationUser2) throws InterruptedException {
		l = new login(OperationUser2);
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
