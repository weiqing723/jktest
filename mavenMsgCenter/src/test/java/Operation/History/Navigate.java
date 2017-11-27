/**
 * 
 */
package Operation.History;

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

import Common.ElementVerify;
import Common.MyDriver;
import Common.MyWDWait;
import Common.TestBase;
import Common.login;

/**
 * @author wei 测试运营人员 历史消息 页面跳转的案例
 */
public class Navigate extends TestBase {
	login l = null;
	public WebDriver wb = null;
	MyWDWait wait = null;
	MyDriver mywb = null;

	public WebDriver getDriver() {
		return wb;
	}

	// 3 跳转 历史消息页，点击浏览器自带返回按钮 能否跳转至上一级页面
	@Test
	public void a1back() throws InterruptedException {
		wb.navigate().back();
		Thread.sleep(1000);
		wait.WaitURLContains("/edit/index");
		String s = wb.getCurrentUrl();
		Assert.assertEquals(s, "http://testmcenter.weixinxk.com/admin/pushmessage/edit/index");

	}

	// 4 跳转 历史消息页，点击左侧导航中，新消息按钮 能否成功跳转到新消息列表页
	@Test
	public void a2NewMsg() throws InterruptedException {

		WebElement eNewMsg = mywb.findElement(By.xpath(".//*[@id='app']//p[text()='新消息']"));
		eNewMsg.click();
		wait.WaiteAllElement(By.xpath(".//*[@id='app']/*"));

		Thread.sleep(100);
		Assert.assertEquals(wb.getCurrentUrl(), "http://testmcenter.weixinxk.com/admin/pushmessage/edit/index");

	}

	// 5 跳转 历史消息页，点击左侧导航中，历史消息按钮 能否成功刷新
	public void a3HistoryMsg() throws InterruptedException {
		WebElement eOldMsg = mywb.findElement(By.xpath(".//*[@id='app']//p[text()='历史消息']"));
		eOldMsg.click();
		wait.WaiteAllElement(By.xpath(".//*[@id='app']/*"));

		Thread.sleep(100);
		Assert.assertEquals(wb.getCurrentUrl(), "http://testmcenter.weixinxk.com/admin/pushmessage/history/index");

	}

	// 6 跳转 历史消息页，点击左侧导航中，相框按钮 能否成功跳转到个性化相框页面
	public void a4Frame() throws Exception

	{
		WebElement eFrame = mywb.findElement(By.xpath(".//*[@id='app']//p[text()='相框']"));
		eFrame.click();
		wait.WaiteAllElement(By.xpath(".//*[@id='app']/*"));

		Thread.sleep(100);
		Assert.assertEquals(wb.getCurrentUrl(), "http://testmcenter.weixinxk.com/admin/statistic/album/index");

	}

	// 7 跳转 历史消息页，点击左侧导航中，设备按钮 能否成功跳转到设备页面
	@Test
	public void a5Device() throws Exception

	{
		WebElement e = mywb.findElement(By.xpath(".//*[@id='app']//p[text()='设备']"));
		e.click();
		wait.WaiteAllElement(By.xpath(".//*[@id='app']/*"));

		Thread.sleep(100);
		Assert.assertEquals(wb.getCurrentUrl(), "http://testmcenter.weixinxk.com/admin/statistic/device/index");

	}

	// 8 跳转 历史消息页，点击左侧导航中，合作伙伴按钮 能否成功跳转到合作伙伴页面
	@Test

	public void a6Parnter() throws Exception

	{
		WebElement e = mywb.findElement(By.xpath(".//*[@id='app']//p[text()='合作伙伴']"));
		e.click();
		wait.WaiteAllElement(By.xpath(".//*[@id='app']/*"));

		Thread.sleep(100);
		Assert.assertEquals(wb.getCurrentUrl(), "http://testmcenter.weixinxk.com/admin/statistic/partner/index");

	}

	// 12 跳转 历史消息页，点击退出按钮 能否成功退出消息中心，回到登录页面
	@Test
	public void zLogout() throws Exception

	{
		WebElement e = mywb.findElement(By.className("header-exit"));
		// By.className("header-exit") 或者用这个定位
		e.click();
		Thread.sleep(1000);
		wait.WaiteAllElement(By.xpath(".//*[@id='app']/*"));
		Assert.assertEquals(wb.getCurrentUrl(), "http://testmcenter.weixinxk.com/admin/logon");

	}

	// 78 翻页 每页显示条数，切换为25 每页显示条数是否正确
	@Test
	public void b1Display25() throws Exception

	{
		// 点击切换显示50
		WebElement e25 = mywb
				.findElement(By.xpath(".//*[@id='app']/div/div[2]/div[2]/div[1]/div[2]/div[2]/div[1]/div[2]/a[1]"));
		e25.click();
		Thread.sleep(1500);
		// 获得列表消息的显示总数；
		List<WebElement> eList = wb
				.findElements(By.xpath(".//*[@id='app']/div/div[2]/div[2]/div[1]/div[2]/div[2]/div[1]/div[1]/div"));
		boolean flag = false;
		if (eList.size() <= 25) {
			flag = true;
		}

		Assert.assertEquals(flag, true);

	}

	// 79 翻页 每页显示条数，切换为50 每页显示条数是否正确
	@Test
	public void b2Display50() throws Exception

	{
		// 点击切换显示50
		WebElement e50 = mywb
				.findElement(By.xpath(".//*[@id='app']/div/div[2]/div[2]/div[1]/div[2]/div[2]/div[1]/div[2]/a[2]"));
		e50.click();
		Thread.sleep(1500);
		wait.WaiteAllElement(By.xpath(".//*[@id='app']/*"));
		// 获得列表消息的显示总数；
		List<WebElement> eList = wb
				.findElements(By.xpath(".//*[@id='app']/div/div[2]/div[2]/div[1]/div[2]/div[2]/div[1]/div[1]/div"));
		boolean flag = false;
		if (eList.size() <= 50 & eList.size() > 25) {
			flag = true;
		}
		WebElement e25 = mywb
				.findElement(By.xpath(".//*[@id='app']/div/div[2]/div[2]/div[1]/div[2]/div[2]/div[1]/div[2]/a[1]"));
		e25.click();
		Assert.assertEquals(flag, true);

	}

	// 80 翻页 每页显示条数，切换为100 每页显示条数是否正确
	@Test
	public void b3Display100() throws Exception

	{
		// 点击切换显示50
		WebElement e100 = mywb
				.findElement(By.xpath(".//*[@id='app']/div/div[2]/div[2]/div[1]/div[2]/div[2]/div[1]/div[2]/a[3]"));
		e100.click();
		Thread.sleep(1500);
		wait.WaiteAllElement(By.xpath(".//*[@id='app']/*"));
		// 获得列表消息的显示总数；
		List<WebElement> eList = wb
				.findElements(By.xpath(".//*[@id='app']/div/div[2]/div[2]/div[1]/div[2]/div[2]/div[1]/div[1]/div"));
		boolean flag = false;
		if (eList.size() >= 50 & eList.size() <= 100) {
			flag = true;
		}

		WebElement e25 = mywb
				.findElement(By.xpath(".//*[@id='app']/div/div[2]/div[2]/div[1]/div[2]/div[2]/div[1]/div[2]/a[1]"));
		e25.click();
		Assert.assertEquals(flag, true);

	}

	// 81 翻页 在第1页，点击页码1 是否屏蔽当前页码
	@Test
	public void b4Page1() throws Exception

	{
		WebElement ePage1 = mywb
				.findElement(By.xpath(".//*[@id='app']/div/div[2]/div[2]/div[1]/div[2]/div[2]/div[2]/div/ul/li[2]/a"));

		ElementVerify ev = new ElementVerify(ePage1);

		Assert.assertEquals(ev.VerifyClickIfRefresh(), false);

	}

	// 84 翻页 点击下一页 下一页按钮能否可用
	@Test
	public void b5Next() throws Exception

	{
		WebElement ePage = mywb.findElement(By.xpath(".//*[@id='app']//li[@class='next-btn']/a")); // 下一页
		ePage.click(); // 首页中点击下一页后 上一页会出现
		wait.WaitElementPresent(By.xpath(".//*[@id='app']//li[@class='next-btn']/a"));
		WebElement ePage2 = mywb.findElement(By.xpath(".//*[@id='app']//li[@class='previous-btn']/a")); // 上一页
		Thread.sleep(2000);
		Assert.assertEquals(ePage2.isDisplayed(), true);

	}

	// 85 翻页 点击上一页 上一页按钮能否可用
	@Test
	public void b6LastPage() throws InterruptedException {
		WebElement ePage = mywb.findElement(By.xpath(".//*[@id='app']//li[@class='next-btn']/a")); // 下一页
		ePage.click(); // 首页中点击下一页后 上一页会出现
		wait.WaitElementPresent(By.xpath(".//*[@id='app']//li[@class='previous-btn']/a"));
		Thread.sleep(1000);
		WebElement ePage2 = mywb.findElement(By.xpath(".//*[@id='app']//li[@class='previous-btn']/a")); // 上一页
		System.out.println("Start Click");
		try {
			ePage2.click();
		} catch (Exception ex) {
		} // 点击上一页
		System.out.println("Finish Click");
		Thread.sleep(2000);
		wait.WaiteAllElement(By.xpath(".//*[@id='app']/*"));

		Assert.assertEquals(ePage2.isDisplayed(), false); // 点击上一页后 回到第一页 上一页不再显示

	}

	@BeforeMethod
	public void beforeMethod() throws InterruptedException {

		wb.get("http://testmcenter.weixinxk.com/admin/pushmessage/history/index");
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
