/**
 * 
 */
package Operation;

import java.io.File;

import org.openqa.selenium.Alert;
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

import Common.CompareImage;
import Common.ElementVerify;
import Common.GetNetImage;
import Common.MyDriver;
import Common.MyString;
import Common.MyWDWait;
import Common.TestBase;
import Common.login;

/**
 * @author wei 运营人员 合作伙伴 检查点
 */
public class myAccount extends TestBase {
	login l = null;
	public WebDriver wb = null;
	MyWDWait wait = null;
	MyDriver mywb = null;

	public WebDriver getDriver() {
		return wb;
	}

	// 默认 我的头像
	@Test
	public void a1Head() throws InterruptedException {
		WebElement eMy = mywb
				.findElement(By.xpath(".//*[@id='app']/div/div[2]/div[2]/div[1]/div/div/div[2]/div[1]/div[1]"));
		String s = eMy.getAttribute("style");

		Assert.assertEquals(s.contains("color: rgb(56, 160, 226)"), true);
	}
	// 导航列表 登录完成后，查看左侧导航列表 左侧导航列表，是否默认选中列表项

	// 导航列表 切换导航项，查看导航项选中状态样式 左侧导航列表，选中样式能否显示正确
	@Test
	public void a2Selected() throws InterruptedException {
		WebElement eMy = mywb.findElement(By.xpath(".//*[@id='app']/div/div[2]/div[1]/div/ul/div[6]/li"));
		String s = eMy.getAttribute("style");

		// 判断左侧导航样式的背景颜色
		Assert.assertEquals(s.contains("background-color: rgba(255, 255, 255, 0.1)"), true);
	}

	// 导航列表 切换导航项，查看到航项未选中状态样式
	@Test
	public void a3NotSelected() throws InterruptedException {
		WebElement ePartner = mywb.findElement(By.xpath(".//*[@id='app']/div/div[2]/div[1]/div/ul/div[5]/li"));
		ePartner.click();
		Thread.sleep(1000);
		wait.WaiteAllElement(By.xpath(".//*[@id='app']/*"));
		WebElement eMy = mywb.findElement(By.xpath(".//*[@id='app']/div/div[2]/div[1]/div/ul/div[6]/li"));
		String s = eMy.getAttribute("style");

		// 判断左侧导航样式的背景颜色
		Assert.assertEquals(s.contains("background-color: rgba(255, 255, 255, 0.1)"), false);
	}

	// 3 跳转 相框页，点击浏览器自带返回按钮 能否返回到上一级页面
	@Test
	public void a5back() throws InterruptedException {
		mywb.get("http://testmcenter.weixinxk.com/admin/pushmessage/edit/index");
		WebElement eFrame = mywb.findElement(By.xpath(".//*[@id='app']//p[text()='相框']"));
		eFrame.click();
		Thread.sleep(1000);
		wb.navigate().back();
		Thread.sleep(1000);
		wait.WaitURLContains("/edit/index");
		String s = wb.getCurrentUrl();
		Assert.assertEquals(s, "http://testmcenter.weixinxk.com/admin/pushmessage/edit/index");
	}

	// 4 跳转 相框页，点击左侧导航中，新消息按钮 能否成功跳转到新消息列表页
	@Test
	public void a6NewMsg() throws InterruptedException {

		WebElement eNewMsg = mywb.findElement(By.xpath(".//*[@id='app']//p[text()='新消息']"));
		eNewMsg.click();
		wait.WaiteAllElement(By.xpath(".//*[@id='app']/*"));

		Thread.sleep(100);
		Assert.assertEquals(wb.getCurrentUrl(), "http://testmcenter.weixinxk.com/admin/pushmessage/edit/index");

	}

	// 5 跳转 相框页，点击左侧导航中，历史消息按钮 能否成功跳转到历史消息页
	@Test
	public void a7HistoryMsg() throws InterruptedException {
		WebElement eOldMsg = mywb.findElement(By.xpath(".//*[@id='app']//p[text()='历史消息']"));
		eOldMsg.click();
		wait.WaiteAllElement(By.xpath(".//*[@id='app']/*"));

		Thread.sleep(100);
		Assert.assertEquals(wb.getCurrentUrl(), "http://testmcenter.weixinxk.com/admin/pushmessage/history/index");

	}

	// 6 跳转 相框页，点击左侧导航中，相框按钮 能否成功刷新页面
	@Test
	public void a8Frame() throws Exception

	{
		WebElement eFrame = mywb.findElement(By.xpath(".//*[@id='app']//p[text()='相框']"));
		eFrame.click();
		wait.WaiteAllElement(By.xpath(".//*[@id='app']/*"));

		Thread.sleep(100);
		Assert.assertEquals(wb.getCurrentUrl(), "http://testmcenter.weixinxk.com/admin/statistic/album/index");

	}

	// 7 跳转 相框页，点击左侧导航中，设备按钮 能否成功跳转到设备页面
	@Test
	public void a9Device() throws Exception {
		WebElement e = mywb.findElement(By.xpath(".//*[@id='app']//p[text()='设备']"));
		e.click();
		wait.WaiteAllElement(By.xpath(".//*[@id='app']/*"));

		Thread.sleep(100);
		Assert.assertEquals(wb.getCurrentUrl(), "http://testmcenter.weixinxk.com/admin/statistic/device/index");

	}

	// 8 跳转 相框页，点击左侧导航中，合作伙伴按钮 能否成功跳转到合作伙伴页面
	@Test
	public void b1Parnter() throws Exception

	{
		WebElement e = mywb.findElement(By.xpath(".//*[@id='app']//p[text()='合作伙伴']"));
		e.click();
		wait.WaiteAllElement(By.xpath(".//*[@id='app']/*"));

		Thread.sleep(100);
		Assert.assertEquals(wb.getCurrentUrl(), "http://testmcenter.weixinxk.com/admin/statistic/partner/index");

	}

	// 跳转 我的账户页，点击合作伙伴 能否成功刷新页面
	@Test
	public void b2MyAccount() throws Exception

	{
		WebElement e = mywb.findElement(By.xpath(".//*[@id='app']//p[text()='我的账户']"));
		ElementVerify ev = new ElementVerify(e);

		Assert.assertEquals(ev.VerifyClickIfRefresh(), true);

	}

	// 跳转 我的账户页，点击系统消息 能否成功跳转至消息列表页（系统消息）
	@Test
	public void b3SytemMsg() throws Exception

	{
		WebElement e = mywb
				.findElement(By.xpath(".//*[@id='app']/div/div[1]/div[2]/div/div[2]/div[2]/*[name()='svg']"));
		e.click();
		wait.WaiteAllElement(By.xpath(".//*[@id='app']/*"));

		Thread.sleep(2000);
		Assert.assertEquals(wb.getCurrentUrl(), "http://testmcenter.weixinxk.com/admin/directmessage/news/");

	}

	// 12 跳转 相框页，点击退出按钮 能否成功退出消息中心，回到登录页面
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
	// 我的头像

	// 10 设置头像 上传其他任意格式头像 是否添加异常格式筛选
	@Test
	public void c1HeadStyle() throws Exception

	{

		WebElement eSetupHead = mywb.findElement(By.xpath(".//*[@id='app']//div[text()='设置头像']"));
		WebElement eUpload = mywb.findElement(
				By.xpath(".//*[@id='app']/div/div[2]/div[2]/div[2]/div/div/div/div[2]/div/div[2]/div/div/input"));
		eSetupHead.click();
		Thread.sleep(500);
		String s = new File(".").getAbsolutePath() + "/src/video1.mp4";
		eUpload.sendKeys(s);
		// 注释通过sikuli进行的窗口页面的点击
		// Thread.sleep(500);
		// Screen s = new Screen();
		//
		// String s1 = "/Users/wei/Desktop/1Image/1511245152056.png";
		//
		// String s2 = "/Users/wei/Desktop/1Image/1511243342327.png";
		// String s3 = "/Users/wei/Desktop/1Image/1511244417276.png";
		// String s4 = "/Users/wei/Desktop/1Image/1511245210838.png";
		// String s5 = "/Users/wei/Desktop/1Image/1511245359607.png";
		// String s6 = "/Users/wei/Desktop/1Image/1511246892742.png";
		// s.click(s1);
		// Thread.sleep(500);
		// s.click(s2);
		// Thread.sleep(500);
		// s.click(s3);
		// Thread.sleep(500);
		// s.click(s4);
		// Thread.sleep(500);
		// s.click(s5);
		// Thread.sleep(600);
		// s.click(s6);
		Thread.sleep(3000);

		Alert alt = wb.switchTo().alert();
		String salt = alt.getText();
		alt.dismiss();

		Assert.assertEquals(salt, "请上传正确的图片类型");

	}

	// 11 设置头像 上传头像过程中，点击弹层背景 是否屏蔽点击事件
	@Test
	public void c2UploadingClick() throws Exception

	{

		WebElement eSetupHead = mywb.findElement(By.xpath(".//*[@id='app']//div[text()='设置头像']"));
		WebElement eUpload = mywb.findElement(
				By.xpath(".//*[@id='app']/div/div[2]/div[2]/div[2]/div/div/div/div[2]/div/div[2]/div/div/input"));
		eSetupHead.click();
		Thread.sleep(500);
		String s = new File(".").getAbsolutePath() + "/Image/Jim.png";
		eUpload.sendKeys(s);
		Thread.sleep(3000);
		WebElement eSave = mywb.findElement(By.xpath(".//*[@id='app']//div[text()='保存']"));
		eSave.click();

		WebElement eBG = mywb.findElement(
				By.xpath(".//*[@id='app']/div/div[2]/div[2]/div[2]/div/div/div/div[2]/div/div[2]/div/div"));
		ElementVerify ev = new ElementVerify(eBG);
		Assert.assertEquals(ev.VerifyClickIfRefresh(), false);
	}

	// 13 设置头像 上传超过1mb头像 头像能否上传成功
	@Test
	public void c3Upload1MB() throws Exception

	{

		WebElement eSetupHead = mywb.findElement(By.xpath(".//*[@id='app']//div[text()='设置头像']"));
		WebElement eUpload = mywb.findElement(
				By.xpath(".//*[@id='app']/div/div[2]/div[2]/div[2]/div/div/div/div[2]/div/div[2]/div/div/input"));
		eSetupHead.click();
		Thread.sleep(500);
		String s = new File(".").getAbsolutePath() + "/Image/Jim.png";
		eUpload.sendKeys(s);
		Thread.sleep(3000);
		WebElement eSave = mywb.findElement(By.xpath(".//*[@id='app']//div[text()='保存']"));
		try {
			eSave.click();
		} catch (Exception e) {
		}
		boolean flag = wait.WaitElementNotPresent(
				By.xpath(".//*[@id='app']/div/div[2]/div[2]/div[2]/div/div/div/div[2]/div/div[1]/p[1]"));
		Assert.assertEquals(flag, true);
	}

	// 15 设置头像 上传完毕，查看头像 主区域的头像，和个人头像，是否更新为新头像 判断两个图片的style是否不一致
	@Test
	public void c4UpdateHead() throws Exception

	{
		// 第一次上传
		WebElement eSetupHead = mywb.findElement(By.xpath(".//*[@id='app']//div[text()='设置头像']"));

		WebElement eUpload = mywb.findElement(
				By.xpath(".//*[@id='app']/div/div[2]/div[2]/div[2]/div/div/div/div[2]/div/div[2]/div/div/input"));

		eSetupHead.click();
		Thread.sleep(500);
		String s = new File(".").getAbsolutePath() + "/Image/SuperMan.jpg";
		eUpload.clear();
		eUpload.sendKeys(s);
		Thread.sleep(3000);
		WebElement eSave = mywb.findElement(By.xpath(".//*[@id='app']//div[text()='保存']"));
		eSave.click();
		Thread.sleep(300);
		wb.navigate().refresh();
		wait.WaiteAllElement(By.xpath(".//*[@id='app']/*"));
		wait.WaitElementNotPresent(
				By.xpath(".//*[@id='app']/div/div[2]/div[2]/div[2]/div/div/div/div[2]/div/div[1]/p[1]"));
		String sStyle1 = mywb.findElement(By.xpath(
				".//*[@id='app']/div/div[2]/div[2]/div[1]/div/div/div[2]/div[2]/div[1]/div/div/div/div[1]/div[1]"))
				.getAttribute("style");

		String url1 = MyString.SubString(sStyle1, "http", "\");");
		String file1 = GetNetImage.GetImageOnline(url1);

		// GetNetImage.GetImageOnline(sStyle1.);

		// 第二次上传
		eSetupHead = mywb.findElement(By.xpath(".//*[@id='app']//div[text()='设置头像']"));
		eSetupHead.click();
		String s2 = new File(".").getAbsolutePath() + "/Image/Jim.png";
		Thread.sleep(300);
		WebElement eUpload2 = mywb.findElement(
				By.xpath(".//*[@id='app']/div/div[2]/div[2]/div[2]/div/div/div/div[2]/div/div[2]/div/div/input"));
		eUpload2.clear();
		eUpload2.sendKeys(s2);

		Thread.sleep(3000);
		eSave = mywb.findElement(By.xpath(".//*[@id='app']//div[text()='保存']"));
		eSave.click();
		Thread.sleep(300);
		wb.navigate().refresh();
		wait.WaiteAllElement(By.xpath(".//*[@id='app']/*"));
		String sStyle2 = mywb.findElement(By.xpath(
				".//*[@id='app']/div/div[2]/div[2]/div[1]/div/div/div[2]/div[2]/div[1]/div/div/div/div[1]/div[1]"))
				.getAttribute("style");

		String url2 = MyString.SubString(sStyle2, "http", "\");");
		String file2 = GetNetImage.GetImageOnline(url2);

		Assert.assertFalse(CompareImage.compareImageToBool(file1, file2), "两次的图片一致，更新失败");
	}

	// 18 姓名 内容正确性 我的姓名，能否与超管设置的信息一致
	@Test
	public void c5VerifyName() throws InterruptedException {
		WebElement e = mywb.findElement(
				By.xpath(".//*[@id='app']/div/div[2]/div[2]/div[1]/div/div/div[2]/div[2]/div[1]/div/div/div/div[1]/p"));
		Assert.assertEquals(e.getText(), "w运营人员");
	}

	// 19 角色 内容正确性 我的角色，能否与超管设置的信息一致
	@Test
	public void c6VerifyCharactor() throws InterruptedException {
		WebElement e = mywb.findElement(
				By.xpath(".//*[@id='app']/div/div[2]/div[2]/div[1]/div/div/div[2]/div[2]/div[1]/div/div/div/div[2]/p"));
		Assert.assertEquals(e.getText(), "运营人员");
	}

	// 20 所属企业 内容正确性 所属企业，能否与超管设置的信息一致
	@Test
	public void c7VerifyCompany() throws InterruptedException {
		WebElement e = mywb.findElement(By.xpath(
				".//*[@id='app']/div/div[2]/div[2]/div[1]/div/div/div[2]/div[2]/div[1]/div/div/div/div[3]/div/div[2]"));
		Assert.assertEquals(e.getText(), "蛙鸣");
	}

	// 21 手机号码 内容正确性 手机号码，能否与超管设置的信息一致
	@Test
	public void c8VerifyPhone() throws InterruptedException {
		WebElement e = mywb.findElement(By.xpath(
				".//*[@id='app']/div/div[2]/div[2]/div[1]/div/div/div[2]/div[2]/div[1]/div/div/div/div[1]/div[2]/div[2]"));
		Assert.assertEquals(e.getText(), "13123456707");
	}

	@BeforeMethod
	public void beforeMethod() throws InterruptedException {

		wb.get("http://testmcenter.weixinxk.com/admin/account/my/index");
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
