/**
 * 
 */
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

import Common.MyDriver;
import Common.MyWDWait;
import Common.TestBase;
import Common.login;

/**
 * @author wei http://testmcenter.weixinxk.com/admin/pushmessage/edit/add/
 *         运营人员：新消息：撰写消息 Navigate：跳转 BeforeClass : login
 *         BeforeMethod:全选点击撰写消息跳转到 撰写消息页面
 * 
 */
public class Navigate extends TestBase {
	login l = null;
	public WebDriver wb = null;
	MyWDWait wait = null;
	MyDriver mywb = null;

	public WebDriver getDriver() {
		return wb;
	}

	@Test
	// 2 跳转 撰写消息页，点击浏览器自带返回按钮 能否成功返回到上一级页面
	public void aBackBtn() throws Exception {
		wb.navigate().back();
		wait.WaiteAllElement(By.xpath(".//*[@id='app']"));

		Thread.sleep(100);
		Assert.assertEquals(wb.getCurrentUrl(), "http://testmcenter.weixinxk.com/admin/pushmessage/edit/index");

	}

	@Test
	// 3 跳转 撰写消息页，点击左侧导航中，新消息按钮 能否成功跳转到新消息列表页
	public void bNewMsgClk() throws Exception

	{
		WebElement eNewMsg = mywb.findElement(By.xpath(".//*[@id='app']/div/div/div[2]/div[1]/div/ul/div[1]/li/div/p"));
		eNewMsg.click();
		wait.WaiteAllElement(By.xpath(".//*[@id='app']/*"));

		Thread.sleep(100);
		Assert.assertEquals(wb.getCurrentUrl(), "http://testmcenter.weixinxk.com/admin/pushmessage/edit/index");

	}

	@Test
	// 4 跳转 撰写消息页，点击左侧导航中，历史消息按钮 能否成功跳转到历史消息列表页
	public void cHistoryMsg() throws Exception

	{
		WebElement eOldMsg = mywb
				.findElement(By.xpath(".//*[@id='app']/div/div/div[2]/div[1]/div/ul/div[2]/li/div/p[text()='历史消息']"));
		eOldMsg.click();
		wait.WaiteAllElement(By.xpath(".//*[@id='app']"));

		Thread.sleep(100);
		Assert.assertEquals(wb.getCurrentUrl(), "http://testmcenter.weixinxk.com/admin/pushmessage/history/index");

	}

	@Test
	// 5 跳转 撰写消息页，点击左侧导航中，相框按钮 能否成功跳转到个性化相框页面
	public void dFrame() throws Exception

	{
		WebElement eFrame = mywb
				.findElement(By.xpath(".//*[@id='app']/div/div/div[2]/div[1]/div/ul/div[3]/li/div/p[text()='相框']"));
		eFrame.click();
		wait.WaiteAllElement(By.xpath(".//*[@id='app']"));

		Thread.sleep(100);
		Assert.assertEquals(wb.getCurrentUrl(), "http://testmcenter.weixinxk.com/admin/statistic/album/index");

	}

	@Test
	// 6 跳转 撰写消息页，点击左侧导航中，设备按钮 能否成功跳转到设备页面
	public void eDevice() throws Exception

	{
		WebElement e = mywb
				.findElement(By.xpath(".//*[@id='app']/div/div/div[2]/div[1]/div/ul/div[4]/li/div/p[text()='设备']"));
		e.click();
		wait.WaiteAllElement(By.xpath(".//*[@id='app']"));

		Thread.sleep(100);
		Assert.assertEquals(wb.getCurrentUrl(), "http://testmcenter.weixinxk.com/admin/statistic/device/index");

	}

	@Test
	// 7 跳转 撰写消息页，点击左侧导航中，合作伙伴按钮 能否成功跳转到合作伙伴页面
	public void fParnter() throws Exception

	{
		WebElement e = wb
				.findElement(By.xpath(".//*[@id='app']/div/div/div[2]/div[1]/div/ul/div[5]/li/div/p[text()='合作伙伴']"));
		e.click();
		wait.WaiteAllElement(By.xpath(".//*[@id='app']"));

		Thread.sleep(100);
		Assert.assertEquals(wb.getCurrentUrl(), "http://testmcenter.weixinxk.com/admin/statistic/partner/index");

	}

	@Test
	// 8 跳转 撰写消息页，点击我的账户 能否成功跳转至我的账户页
	public void gMy() throws Exception

	{
		WebElement e = mywb
				.findElement(By.xpath(".//*[@id='app']/div/div/div[2]/div[1]/div/ul/div[6]/li/div/p[text()='我的账户']"));
		e.click();
		wait.WaiteAllElement(By.xpath(".//*[@id='app']"));

		Thread.sleep(100);
		Assert.assertEquals(wb.getCurrentUrl(), "http://testmcenter.weixinxk.com/admin/account/my/index");

	}

	@Test
	// 11 跳转 撰写消息页，点击退出按钮 能否成功退出消息中心，回到登录页面
	public void hLogout() throws Exception

	{
		WebElement e = mywb
				.findElement(By.xpath(".//*[@id='app']/div/div/div[1]/div[2]/div/div[2]/div[3]/*[name()='svg']"));
		// By.className("header-exit") 或者用这个定位
		e.click();
		Thread.sleep(1000);
		wait.WaiteAllElement(By.xpath(".//*[@id='app']"));
		Assert.assertEquals(wb.getCurrentUrl(), "http://testmcenter.weixinxk.com/admin/logon");

	}

	@BeforeMethod
	public void beforeMethod() throws InterruptedException {

		mywb.get("http://testmcenter.weixinxk.com/admin/pushmessage/edit/index");
		Thread.sleep(1000);
		wait.WaiteAllElement(By.xpath(".//*[@id='app']/*"));
		WebElement e = mywb.findElement(By.xpath(".//*[@id='app']/div/div/div[2]/div[2]/div[1]/div/div[1]/a[1]")); // select
																													// all
		e.click();
		WebElement e2 = mywb.findElement(By.xpath("//*[@id='app']/div/div/div[2]/div[2]/div[1]/div/div[1]/a[2]")); // write
																													// message

		wait.WaiteAllElement(By.xpath(".//*[@id='app']"));
		e2.click();
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
	@Parameters({ "OperationUser" })
	public void beforeClass(@Optional("13132919225") String OperationUser) throws InterruptedException {
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
