/**
 * 
 */
package Operation.WriteNewMsg;

import java.io.File;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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
 * @author wei 新消息：撰写消息中 【预览按钮】和【预览页】的检查点
 */
public class Preview extends TestBase {
	login l = null;
	public WebDriver wb = null;
	MyWDWait wait = null;
	MyDriver mywb = null;

	public WebDriver getDriver() {
		return wb;
	}

	@BeforeMethod
	public void beforeMethod() throws InterruptedException {

	}

	@AfterMethod
	public void afterMethod() {

	}

	// 117 预览按钮 默认 是否默认为不可点击状态
	@Test
	public void a1PreviewNotClickable() throws InterruptedException {
		WebElement ePre = mywb.findElement(By.xpath(".//*[@id='app']//a[text()='预览']"));
		String sClass = ePre.getAttribute("class");
		Assert.assertEquals(sClass, "preview-btn btn-disabled-primary");

	}

	// 118 预览按钮 输入任意内容 是否为可点击态
	@Test
	public void a2PreviewClickable() throws InterruptedException {
		// 填写标题和内容
		WebElement eTitle = wb.findElement(By.xpath(".//*[@id='app']/div/div/div[2]/div[2]/div[2]/div/div[1]//input"));
		eTitle.clear();
		WebElement eContent = wb.findElement(By.xpath(".//*[@id='app']/div/div/div[2]/div[2]/div[2]//textarea")); // 内容
		eContent.clear();
		eContent.sendKeys("11111");
		eTitle.sendKeys("titile123");
		Thread.sleep(100);

		WebElement ePre = mywb.findElement(By.xpath(".//*[@id='app']//a[text()='预览']"));
		String sClass = ePre.getAttribute("class");
		Assert.assertEquals(sClass, "preview-btn btn-primary");

	}

	// 123 预览页 预览完成后，返回 预览完成后，能否成功返回到预览页
	// 135 预览页 编辑 能否跳转到撰写消息页
	@Test
	public void a3BacktoWrite() throws InterruptedException {
		// 填写标题和内容
		WebElement eTitle = wb.findElement(By.xpath(".//*[@id='app']/div/div/div[2]/div[2]/div[2]/div/div[1]//input"));
		eTitle.clear();
		WebElement eContent = wb.findElement(By.xpath(".//*[@id='app']/div/div/div[2]/div[2]/div[2]//textarea")); // 内容
		eContent.clear();
		eContent.sendKeys("11111");
		eTitle.sendKeys("titile123");
		Thread.sleep(300);

		WebElement ePre = mywb.findElement(By.xpath(".//*[@id='app']//a[text()='预览']"));
		ePre.click();
		Thread.sleep(300);
		wait.WaiteTextPresent(By.xpath(".//*[@id='app']/div/div/div[2]/div[2]/div[3]/div/div[3]/a[1]"), "编辑");

		WebElement eEdit = mywb.findElement(By.xpath(".//*[@id='app']/div/div/div[2]/div[2]/div[3]/div/div[3]/a[1]"));
		eEdit.click();

		Thread.sleep(300);
		// 回到撰写消息页面后 上边当前位置的预览text不见了
		boolean flag = true;
		try {
			WebElement etext = wb
					.findElement(By.xpath(".//*[@id='app']/div/div/div[2]/div[2]/div[1]/div[1]/div[text()='预览']"));
		} catch (Exception e) {
			flag = false;
		}
		Assert.assertEquals(flag, false);

	}

	// 124 预览页 预览视频 视频能否进行播放预览
	@Test
	public void a4VideoPreview() throws InterruptedException {
		// 填写标题和内容
		WebElement eTitle = wb.findElement(By.xpath(".//*[@id='app']/div/div/div[2]/div[2]/div[2]/div/div[1]//input"));
		eTitle.clear();
		WebElement eContent = wb.findElement(By.xpath(".//*[@id='app']/div/div/div[2]/div[2]/div[2]//textarea")); // 内容
		eContent.clear();
		eContent.sendKeys("11111");
		eTitle.sendKeys("titile123");
		Thread.sleep(300);
		// 上传video
		WebElement eVideo = mywb.findElement(
				By.xpath(".//*[@id='app']/div/div/div[2]/div[2]/div[2]/div/div[4]/div[2]/div[1]/div[1]/a/form/input")); // 视频内容
		eVideo.clear();
		String s = new File(".").getAbsolutePath() + "/video/1MB480X360.mp4";
		eVideo.sendKeys(s);
		// 等待上传完成
		wait.WaiteTextPresent(By.xpath(
				".//*[@id='app']/div/div/div[2]/div[2]/div[2]/div/div[4]/div[2]/div[1]/div[1]/div/div/div[2]/div[2]/div/div/span[2]"),
				"上传成功");

		// 完成摁钮
		WebElement eFinish = mywb.findElement(By.xpath(
				".//*[@id='app']/div/div/div[2]/div[2]/div[2]/div/div[4]/div[2]/div/div[1]/div/div/div[2]/div[5]/a"));
		eFinish.click();
		Thread.sleep(300);
		// 点击Preview
		WebElement ePre = mywb.findElement(By.xpath(".//*[@id='app']//a[text()='预览']"));
		ePre.click();
		Thread.sleep(300);

		WebElement videoPlayer = mywb.findElement(By.tagName("video"));

		// 对video这个元素执行播放操作 播放不了会报异常
		boolean flag = true;
		JavascriptExecutor javascriptExecutor = (JavascriptExecutor) mywb.getDriver();
		try {
			javascriptExecutor.executeScript("arguments[0].play()", videoPlayer);
		} catch (Exception e) {
			flag = false;
		}

		Assert.assertEquals(flag, true);

	}

	// 126 预览页 当前位置 当前位置，能否显示正确
	@Test
	public void a5PreviewCurrentLocation() throws InterruptedException {
		// 填写标题和内容
		WebElement eTitle = wb.findElement(By.xpath(".//*[@id='app']/div/div/div[2]/div[2]/div[2]/div/div[1]//input"));
		eTitle.clear();
		WebElement eContent = wb.findElement(By.xpath(".//*[@id='app']/div/div/div[2]/div[2]/div[2]//textarea")); // 内容
		eContent.clear();
		eContent.sendKeys("11111");
		eTitle.sendKeys("titile123");
		Thread.sleep(300);

		// 点击Preview
		WebElement ePre = mywb.findElement(By.xpath(".//*[@id='app']//a[text()='预览']"));
		ePre.click();
		Thread.sleep(300);

		// 当前位置的预览可以显示
		boolean flag = true;
		try {
			WebElement etext = wb
					.findElement(By.xpath(".//*[@id='app']/div/div/div[2]/div[2]/div[1]/div[1]/div[text()='预览']"));
		} catch (Exception e) {
			flag = false;
		}
		Assert.assertEquals(flag, true);

	}

	// 127 预览页 当前位置：撰写消息 能否成功跳转到，撰写消息页
	@Test
	public void a6CurrentLocationWrite() throws InterruptedException {
		// 填写标题和内容
		WebElement eTitle = wb.findElement(By.xpath(".//*[@id='app']/div/div/div[2]/div[2]/div[2]/div/div[1]//input"));
		eTitle.clear();
		WebElement eContent = wb.findElement(By.xpath(".//*[@id='app']/div/div/div[2]/div[2]/div[2]//textarea")); // 内容
		eContent.clear();
		eContent.sendKeys("11111");
		eTitle.sendKeys("titile123");
		Thread.sleep(300);

		// 点击Preview
		WebElement ePre = mywb.findElement(By.xpath(".//*[@id='app']//a[text()='预览']"));
		ePre.click();
		Thread.sleep(300);

		// 当前位置的撰写消息按钮
		WebElement eWrite = mywb.findElement(By.xpath(".//*[@id='app']/div/div/div[2]/div[2]/div[1]/div[1]/div[1]"));
		eWrite.click();
		Thread.sleep(300);
		// 回到撰写消息页面后 上边当前位置的预览text不见了
		boolean flag = true;
		try {
			wb.findElement(By.xpath(".//*[@id='app']/div/div/div[2]/div[2]/div[1]/div[1]/div[text()='预览']"));
		} catch (Exception e) {
			flag = false;
		}
		Assert.assertEquals(flag, false);

	}

	// 128 预览页 当前位置：预览 能否屏蔽，预览按钮的点击事件
	@Test
	public void a7PreviewNotClickable() throws InterruptedException {
		// 填写标题和内容
		WebElement eTitle = wb.findElement(By.xpath(".//*[@id='app']/div/div/div[2]/div[2]/div[2]/div/div[1]//input"));
		eTitle.clear();
		WebElement eContent = wb.findElement(By.xpath(".//*[@id='app']/div/div/div[2]/div[2]/div[2]//textarea")); // 内容
		eContent.clear();
		eContent.sendKeys("11111");
		eTitle.sendKeys("titile123");
		Thread.sleep(300);

		// 点击Preview
		WebElement ePre = mywb.findElement(By.xpath(".//*[@id='app']//a[text()='预览']"));
		ePre.click();
		Thread.sleep(300);

		// 当前位置的预览按钮
		WebElement ePreview = wb
				.findElement(By.xpath(".//*[@id='app']/div/div/div[2]/div[2]/div[1]/div[1]/div[text()='预览']"));
		ePreview.click();
		// 点击预览判断 预览text是否还在 若不在 说明刷新了，在说明 没刷新
		boolean flag = true;
		try {
			wb.findElement(By.xpath(".//*[@id='app']/div/div/div[2]/div[2]/div[1]/div[1]/div[text()='预览']"));
		} catch (Exception e) {
			flag = false;
		}

		Assert.assertEquals(flag, true);

	}

	// 132 预览页 更改推送对象 页面跳转，是否正确
	@Test
	public void a8ClickChangeObj() throws InterruptedException {
		// 填写标题和内容
		WebElement eTitle = wb.findElement(By.xpath(".//*[@id='app']/div/div/div[2]/div[2]/div[2]/div/div[1]//input"));
		eTitle.clear();
		WebElement eContent = wb.findElement(By.xpath(".//*[@id='app']/div/div/div[2]/div[2]/div[2]//textarea")); // 内容
		eContent.clear();
		eContent.sendKeys("11111");
		eTitle.sendKeys("titile123");
		Thread.sleep(300);

		// 点击Preview
		WebElement ePre = mywb.findElement(By.xpath(".//*[@id='app']//a[text()='预览']"));
		ePre.click();
		Thread.sleep(300);

		// 当前位置的预览按钮
		WebElement ePreview = mywb
				.findElement(By.xpath(".//*[@id='app']/div/div/div[2]/div[2]/div[1]/div[1]/div[text()='预览']"));
		ePreview.click();
		// 点击预览判断 预览text是否还在 若不在 说明刷新了，在说明 没刷新
		Thread.sleep(300);
		WebElement eChange = mywb
				.findElement(By.xpath(".//*[@id='app']/div/div/div[2]/div[2]/div[3]/div/div[1]/div[1]/a"));
		eChange.click();

		// 判断URL跳转
		Assert.assertEquals(wait.WaitURLContains("http://testmcenter.weixinxk.com/admin/pushmessage/edit/index"), true);

	}

	@AfterClass
	public void afterClass() {
		l.quiteBrower();
	}

	@BeforeClass(groups = "only")
	@Parameters({ "OperationUser" })
	public void beforeClass(@Optional("13132919225") String OperationUser) throws InterruptedException {
		l = new login(OperationUser);
		wb = l.my_driver;
		mywb = new MyDriver(wb);
		wait = new MyWDWait(wb);

		// 以下是跳转到撰写消息的页面
		wait.WaiteAllElement(By.xpath(".//*[@id='app']"));
		WebElement e = mywb
				.findElement(By.xpath(".//*[@id='app']/div/div/div[2]/div[2]/div[2]/div[2]/ul/li[1]/div[1]")); // select
																												// 第一个
		e.click();
		WebElement e2 = mywb.findElement(By.xpath(".//*[@id='app']/div/div/div[2]/div[2]/div[1]/div/div[1]/a[2]")); // write
																													// message

		wait.WaiteAllElement(By.xpath(".//*[@id='app']"));
		e2.click();
		Thread.sleep(1000);
		wait.WaiteAllElement(By.xpath(".//*[@id='app']"));
		System.out.println("方法运行前的URL是" + wb.getCurrentUrl());

		WebElement eServ = mywb.findElement(By.xpath(".//*[@id='app']//div[text()='服务消息']"));
		eServ.click();
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
