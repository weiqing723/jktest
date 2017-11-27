/**
 * 
 */
package Operation.WriteNewMsg;

import java.io.File;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
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
 * @author wei http://testmcenter.weixinxk.com/admin/pushmessage/edit/add/
 *         运营人员：新消息：撰写消息 Contents：标题 消息标签 内容 BeforeClass : login
 * 
 */
public class Contents extends TestBase {
	login l = null;
	public WebDriver wb = null;
	MyWDWait wait = null;
	MyDriver mywb = null;

	public WebDriver getDriver() {
		return wb;
	}

	// 17 消息标签 默认 能否默认选中，推广标签
	@Test

	public void aaDefaultLabel() throws InterruptedException {

		WebElement eLabel = wb.findElement(By.xpath(".//*[@id='app']//div[text()='推广']"));
		// display: inline-block; color: rgb(114, 114, 114); padding: 0px 14px; height:
		// 22px; line-height: 22px; font-size: 12px; background-color: rgb(250, 250,
		// 250); border: 1px solid rgb(232, 232, 232); box-shadow: none; border-radius:
		// 12px; cursor: pointer; margin-right: 12px;"
		// style="display: inline-block; color: rgb(46, 199, 93); padding: 0px 14px;
		// height: 22px; line-height: 22px; font-size: 12px; background-color: rgb(238,
		// 250, 242); border: 1px solid rgb(184, 240, 202); box-shadow: none;
		// border-radius: 12px; cursor: pointer; margin-right: 12px;"
		String s = eLabel.getAttribute("style");

		Assert.assertEquals(s.contains("rgb(46, 199, 93)"), true);
		Thread.sleep(100);

	}

	// 19 消息标签 切换 能否成功来回切换，各类型消息标签
	@Test

	public void abChangeLabel() throws InterruptedException {

		WebElement eLabel1 = wb.findElement(By.xpath(".//*[@id='app']//div[text()='推广']"));
		WebElement eLabel2 = wb.findElement(By.xpath(".//*[@id='app']//div[text()='服务消息']"));
		// display: inline-block; color: rgb(114, 114, 114); padding: 0px 14px; height:
		// 22px; line-height: 22px; font-size: 12px; background-color: rgb(250, 250,
		// 250); border: 1px solid rgb(232, 232, 232); box-shadow: none; border-radius:
		// 12px; cursor: pointer; margin-right: 12px;"
		// style="display: inline-block; color: rgb(46, 199, 93); padding: 0px 14px;
		// height: 22px; line-height: 22px; font-size: 12px; background-color: rgb(238,
		// 250, 242); border: 1px solid rgb(184, 240, 202); box-shadow: none;
		// border-radius: 12px; cursor: pointer; margin-right: 12px;"
		boolean flag1 = false;
		eLabel2.click();
		Thread.sleep(100);
		// 点击 服务消息 ，如果 推广 不显示彩色， 服务消息 显示彩色， 则flag =true
		if (!eLabel1.getAttribute("style").contains("rgb(46, 199, 93)")
				&& eLabel2.getAttribute("style").contains("rgb(46, 199, 93)")) {
			flag1 = true;

		}

		eLabel1.click();
		Thread.sleep(100);
		// 再点击 推广，如果 推广 不显示彩色，或 服务消息 显示彩色， 则flag =false
		if (!eLabel1.getAttribute("style").contains("rgb(46, 199, 93)")
				|| eLabel2.getAttribute("style").contains("rgb(46, 199, 93)")) {
			flag1 = false;
		}

		Assert.assertEquals(flag1, true);

	}

	// 20 消息标签 取消选中 能否取消选择 & 21 消息标签 重新选择 取消选择后，能否重新进行选择
	@Test
	public void acDefaultLabel() throws InterruptedException {

		WebElement eLabel = wb.findElement(By.xpath(".//*[@id='app']//div[text()='推广']"));
		// display: inline-block; color: rgb(114, 114, 114); padding: 0px 14px; height:
		// 22px; line-height: 22px; font-size: 12px; background-color: rgb(250, 250,
		// 250); border: 1px solid rgb(232, 232, 232); box-shadow: none; border-radius:
		// 12px; cursor: pointer; margin-right: 12px;"
		// style="display: inline-block; color: rgb(46, 199, 93); padding: 0px 14px;
		// height: 22px; line-height: 22px; font-size: 12px; background-color: rgb(238,
		// 250, 242); border: 1px solid rgb(184, 240, 202); box-shadow: none;
		// border-radius: 12px; cursor: pointer; margin-right: 12px;"
		String s = eLabel.getAttribute("style");
		// 如果 推广 不是选中 ，先点击 推广
		if (!s.contains("rgb(46, 199, 93)")) {
			eLabel.click();
			wait.WaiteAllElement(By.xpath(".//*[@id='app']"));
			Thread.sleep(10);
		}

		eLabel.click();
		Thread.sleep(100);

		// 点击已经是选中状态的推广，看是否颜色会变，应该不变
		Assert.assertEquals(s.contains("rgb(46, 199, 93)"), true);

	}

	// 14 标题 不输入 标题，是否添加为空验证
	@Test
	public void aTitleNull() throws InterruptedException {

		WebElement eTitle = wb.findElement(By.xpath(".//*[@id='app']/div/div/div[2]/div[2]/div[2]/div/div[1]//input"));
		eTitle.clear();
		WebElement eContent = wb.findElement(By.xpath(".//*[@id='app']/div/div/div[2]/div[2]/div[2]//textarea")); // 内容
		eContent.clear();
		eContent.sendKeys("11111");
		WebElement ePreview = wb.findElement(By.xpath(".//a[text()='预览']"));
		wait.WaitElementPresent(By.xpath(".//a[text()='预览']"));
		ElementVerify eV = new ElementVerify(ePreview);
		Assert.assertEquals(eV.VerifyClickIfRefresh(), false);
		Thread.sleep(100);

	}

	// 15 标题 输入超过14字符的标题 标题长度，是否添加限制
	@Test

	public void aTitleLength14() throws InterruptedException {

		WebElement eTitle = wb.findElement(By.xpath(".//*[@id='app']/div/div/div[2]/div[2]/div[2]/div/div[1]//input")); // 标题
		eTitle.clear();
		eTitle.sendKeys("123456789012345");
		Thread.sleep(10);
		String s = eTitle.getAttribute("value");
		Assert.assertEquals(s, "12345678901234");

	}

	// 37 内容 输入不超过96字符的内容 查看内容计数，能否显示正确
	@Test
	public void b0TextareaCount() throws InterruptedException {
		WebElement eContent = wb.findElement(By.xpath(".//*[@id='app']/div/div/div[2]/div[2]/div[2]//textarea")); // 内容
		eContent.clear();
		eContent.sendKeys(
				"123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456");
		Thread.sleep(10);
		WebElement eCount = wb
				.findElement(By.xpath(".//*[@id='app']/div/div/div[2]/div[2]/div[2]/div/div[3]/span/span"));
		String s = eCount.getText();
		Assert.assertEquals(s, "96");

	}

	// 43 内容 上传图片 能否成功弹出，文件资源管理器
	@Test
	public void b0PicClick() throws InterruptedException {
		WebElement ePic = wb.findElement(
				By.xpath(".//*[@id='app']/div/div/div[2]/div[2]/div[2]/div/div[4]/div[2]/div[1]/div[2]/a/form/input")); // 图片
		System.out.println((wb.getWindowHandle().toString()));

		ePic.clear();
		String s = new File(".").getAbsolutePath() + "/src/solution3.jpg";
		System.out.println(s);
		ePic.sendKeys(s);

		Thread.sleep(1500);
		WebElement eText = wb.findElement(By
				.xpath(".//*[@id='app']/div/div/div[2]/div[2]/div[2]/div/div[4]/div[2]/div[1]/div[2]/div/div/div[1]"));
		Assert.assertEquals(eText.getText(), "本地上传");
	}

	// 44 内容 图片上传完成，编辑 能否成功编辑
	@Test
	public void b1PicEdit() throws InterruptedException {

		WebElement eTextArea = wb.findElement(By.xpath(
				".//*[@id='app']/div/div/div[2]/div[2]/div[2]/div/div[4]/div[2]/div[1]/div[2]/div/div/div[4]/div/textarea"));
		eTextArea.clear();
		String sE = "Edit";
		eTextArea.sendKeys(sE);
		WebElement eFinish = wb.findElement(By.xpath(
				".//*[@id='app']/div/div/div[2]/div[2]/div[2]/div/div[4]/div[2]/div[1]/div[2]/div/div/div[5]/a"));
		eFinish.click();
		Thread.sleep(1200);
		wait.WaiteAllElement(By.xpath(".//*[@id='app']"));
		WebElement eShow = wb
				.findElement(By.xpath("//*[@id='app']/div/div/div[2]/div[2]/div[2]/div/div[4]/div/div[2]"));
		Assert.assertEquals(eShow.getText(), sE);
	}

	// 46 内容 图片上传完成，删除 能否成功删除
	@Test
	public void b2PicDelete() throws InterruptedException {
		// 从首页跳转到内容页
		mywb.get("http://testmcenter.weixinxk.com/admin/pushmessage/edit/index");
		Thread.sleep(3000);
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
		wait.WaiteAllElement(By.xpath("//*[@id='app']"));

		// 填写标题和内容
		WebElement eTitle = wb.findElement(By.xpath(".//*[@id='app']/div/div/div[2]/div[2]/div[2]/div/div[1]//input"));
		eTitle.clear();
		WebElement eContent = wb.findElement(By.xpath(".//*[@id='app']/div/div/div[2]/div[2]/div[2]//textarea")); // 内容
		eContent.clear();
		eContent.sendKeys("11111");
		eTitle.sendKeys("titile123");

		// 上传图片
		WebElement ePic = mywb.findElement(
				By.xpath(".//*[@id='app']/div/div/div[2]/div[2]/div[2]/div/div[4]/div[2]/div[1]/div[2]/a/form/input")); // 内容
		System.out.println((wb.getWindowHandle().toString()));
		ePic.clear();
		String s = new File(".").getAbsolutePath() + "/src/solution3.jpg";
		System.out.println(s);
		ePic.sendKeys(s);
		Thread.sleep(1500);
		// 点完成摁钮
		WebElement eFinish = wb.findElement(By.xpath(
				".//*[@id='app']/div/div/div[2]/div[2]/div[2]/div/div[4]/div[2]/div[1]/div[2]/div/div/div[5]/a"));
		eFinish.click();
		// 鼠标焦点移动到图片上
		Actions mouse = new Actions(wb);
		WebElement epic2 = mywb.findElement(By.xpath(".//*[@id='app']/div/div/div[2]/div[2]/div[2]/div/div[4]/div"));
		mouse.moveToElement(epic2).perform();
		Thread.sleep(200);

		WebElement eDelete = mywb.findElement(By.xpath(
				".//*[@id='app']/div/div/div[2]/div[2]/div[2]/div/div[4]/div/div[1]/div[2]/a[2]/span/*[name()='svg']"));

		System.out.println(eDelete.toString());

		eDelete.click();
		WebElement eConfirm = mywb
				.findElement(By.xpath(".//*[@id='app']/div/div/div[2]/div[3]/div/div/div/div/div[3]/a[1]"));
		Thread.sleep(100);
		eConfirm.click();
		Thread.sleep(1200);
		wait.WaiteAllElement(By.xpath(".//*[@id='app']"));
		boolean flag = true;
		try {
			wb.findElement(By.xpath(".//*[@id='app']/div/div/div[2]/div[2]/div[2]/div/div[4]/div[@class='fl mrr10']"));
		} // 判断显示图片框是否还显示
		catch (Exception ex) {
			flag = false;
		}
		Assert.assertEquals(flag, false);
	}

	// 47 内容 删除完成，重现上传 能否重新上传
	@Test

	public void b3UploadAgain() throws InterruptedException {

		// 上传图片
		WebElement ePic = mywb.findElement(
				By.xpath(".//*[@id='app']/div/div/div[2]/div[2]/div[2]/div/div[4]/div[2]/div[1]/div[2]/a/form/input")); // 内容
		System.out.println((wb.getWindowHandle().toString()));
		ePic.clear();
		String s = new File(".").getAbsolutePath() + "/src/solution3.jpg";
		System.out.println(s);
		ePic.sendKeys(s);
		Thread.sleep(1500);

		// 点完成摁钮
		WebElement eFinish = wb.findElement(By.xpath(
				".//*[@id='app']/div/div/div[2]/div[2]/div[2]/div/div[4]/div[2]/div[1]/div[2]/div/div/div[5]/a"));
		eFinish.click();

		Thread.sleep(1200);

		boolean flag = false;
		try {
			if (mywb.findElement(By
					.xpath(".//*[@id='app']/div/div/div[2]/div[2]/div[2]/div/div[4]/div[@class='fl mrr10']")) != null) {
				flag = true;
			}
		} // 判断显示图片框是否显示
		catch (Exception ex) {
		}
		Assert.assertEquals(flag, true);
	}

	// 48 内容 图片上传完成，预览 能否成功预览
	@Test

	public void b4Preview() throws InterruptedException {

		WebElement eLabel = mywb.findElement(By.xpath(".//*[@id='app']//div[text()='服务消息']"));
		try {
			eLabel.click();
		} catch (Exception e) {
		}

		WebElement ePre = mywb.findElement(
				By.xpath(".//*[@id='app']/div/div/div[2]/div[2]/div[2]/div/div[5]/div[2]/div[2]/a[text()='预览']"));
		ePre.click();
		Thread.sleep(1000);
		Assert.assertEquals(mywb.findElement(By.xpath(".//*[@id='app']//a[text()='提交审核']")) == null, false);

	}

	// 49 内容 上传视频 能否成功弹出，文件资源管理器
	@Test
	public void b5VideoClick() throws InterruptedException {
		// 从首页跳转到内容页
		mywb.get("http://testmcenter.weixinxk.com/admin/pushmessage/edit/index");
		Thread.sleep(3000);
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
		wait.WaiteAllElement(By.xpath("//*[@id='app']"));

		WebElement eVideo = wb.findElement(
				By.xpath(".//*[@id='app']/div/div/div[2]/div[2]/div[2]/div/div[4]/div[2]/div[1]/div[1]/a/form/input")); // 视频内容

		eVideo.clear();
		String s = new File(".").getAbsolutePath() + "/src/video1.mp4";
		System.out.println(s);
		eVideo.sendKeys(s);

		wait.WaiteTextPresent(By.xpath(
				".//*[@id='app']/div/div/div[2]/div[2]/div[2]/div/div[4]/div[2]/div[1]/div[1]/div/div/div[2]/div[2]/div/div/span[2]"),
				"上传成功");

		WebElement eText = wb.findElement(By.xpath(
				".//*[@id='app']/div/div/div[2]/div[2]/div[2]/div/div[4]/div[2]/div[1]/div[1]/div/div/div[2]/div[2]/div/div/span[2]"));
		Assert.assertEquals(eText.getText(), "上传成功!");
	}

	// 50 内容 视频上传完成，编辑 能否成功编辑
	@Test
	public void b6VideoEdit() throws InterruptedException {
		// 输入页面上的视频标题
		WebElement eTextArea = mywb.findElement(By.xpath(
				".//*[@id='app']/div/div/div[2]/div[2]/div[2]/div/div[4]/div[2]/div[1]/div[1]/div/div/div[2]/div[4]/div/textarea"));
		eTextArea.clear();
		String s = "Autotest Title";
		eTextArea.sendKeys(s);
		Thread.sleep(100);

		// 点击完成
		WebElement eFinish = mywb.findElement(By.xpath(
				".//*[@id='app']/div/div/div[2]/div[2]/div[2]/div/div[4]/div[2]/div[1]/div[1]/div/div/div[2]/div[5]/a"));
		eFinish.click();
		Thread.sleep(100);
		WebElement eText = mywb
				.findElement(By.xpath(".//*[@id='app']/div/div/div[2]/div[2]/div[2]/div/div[4]/div/div[2]"));
		Assert.assertEquals(eText.getText(), s); // 判断显示的Text和输入的标题一致

	}

	// 52 内容 视频上传完成，删除 能否成功删除
	@Test
	public void b7DeleteVideo() throws InterruptedException {

		// 鼠标焦点移动到图片上
		Actions mouse = new Actions(wb);
		WebElement epic2 = mywb.findElement(By.xpath(".//*[@id='app']/div/div/div[2]/div[2]/div[2]/div/div[4]/div"));
		mouse.moveToElement(epic2).perform();
		Thread.sleep(200);

		WebElement eDelete = mywb.findElement(By.xpath(
				".//*[@id='app']/div/div/div[2]/div[2]/div[2]/div/div[4]/div/div[1]/div[2]/a[2]/span/*[name()='svg']"));
		System.out.println(eDelete.toString());
		eDelete.click();
		WebElement eConfirm = mywb
				.findElement(By.xpath(".//*[@id='app']/div/div/div[2]/div[3]/div/div/div/div/div[3]/a[1]"));
		Thread.sleep(100);
		eConfirm.click();
		Thread.sleep(1200);
		wait.WaiteAllElement(By.xpath(".//*[@id='app']"));
		boolean flag = true;
		try {
			wb.findElement(By.xpath(".//*[@id='app']/div/div/div[2]/div[2]/div[2]/div/div[4]/div[@class='fl mrr10']"));
		} // 判断显示图片框是否还显示
		catch (Exception ex) {
			flag = false;
		}
		Assert.assertEquals(flag, false);
	}

	// 53 内容 删除完成，重现上传 能否重新上传
	@Test
	public void b8UploadVideoAgain() throws InterruptedException {

		// 上传视频
		WebElement eVideo = mywb.findElement(
				By.xpath(".//*[@id='app']/div/div/div[2]/div[2]/div[2]/div/div[4]/div[2]/div[1]/div[1]/a/form/input")); // 视频内容

		eVideo.clear();
		String s = new File(".").getAbsolutePath() + "/src/video1.mp4";
		System.out.println(s);
		eVideo.sendKeys(s);
		wait.WaiteTextPresent(By.xpath(
				".//*[@id='app']/div/div/div[2]/div[2]/div[2]/div/div[4]/div[2]/div[1]/div[1]/div/div/div[2]/div[2]/div/div/span[2]"),
				"上传成功");

		// 点完成摁钮
		WebElement eFinish = mywb.findElement(By.xpath(
				".//*[@id='app']/div/div/div[2]/div[2]/div[2]/div/div[4]/div[2]/div[1]/div[1]/div/div/div[2]/div[5]/a"));
		eFinish.click();

		Thread.sleep(300);

		boolean flag = false;
		try {
			if (mywb.findElement(By
					.xpath(".//*[@id='app']/div/div/div[2]/div[2]/div[2]/div/div[4]/div[@class='fl mrr10']")) != null) {
				flag = true;
			}
		} // 判断显示图片框是否显示
		catch (Exception ex) {
		}
		Assert.assertEquals(flag, true);
	}

	@Test
	// 23 消息标签 首次发布推广消息 推广标签，能否成为禁用态 有bug 15
	// public void bFirstPush() throws InterruptedException
	// {
	//
	// WebElement
	// eLabel1=wb.findElement(By.xpath(".//*[@id='app']//div[text()='推广']"));
	// eLabel1.click();
	// Thread.sleep(100);
	// WebElement
	// eTitle=wb.findElement(By.xpath(".//*[@id='app']/div/div/div[2]/div[2]/div[2]/div/div[1]//input"));
	// eTitle.clear();
	// WebElement
	// eContent=wb.findElement(By.xpath(".//*[@id='app']/div/div/div[2]/div[2]/div[2]//textarea"));
	// eContent.clear();
	// WebElement
	// eHour=wb.findElement(By.xpath("/html/body/div/div/div/div[2]/div[2]/div[2]/div/div[5]/div[1]/div/div/input"));
	// eHour.clear();
	// eHour.sendKeys("1");
	// eTitle.sendKeys("First Push Title");
	// eContent.sendKeys("Contents11111111");
	//
	// WebElement
	// ePre=wb.findElement(By.xpath(".//*[@id='app']/div/div/div/div[2]/div[2]/div[2]/div/div[5]/div[2]/div/a"));
	// ePre.click();
	// Thread.sleep(100);
	// WebElement
	// eSubmit=wb.findElement(By.xpath(".//*[@id='app']//a[text()='提交审核']"));
	// eSubmit.click();
	//
	// Thread.sleep(1000);
	// //以下是跳转到撰写消息的页面
	// wb.get("http://testmcenter.weixinxk.com/admin/pushmessage/edit/index");
	// wait.WaiteAllElement(By.xpath(".//*[@id='app']"));
	// Thread.sleep(2000);
	//
	// WebElement
	// e=wb.findElement(By.xpath("/html/body/div/div/div/div[2]/div[2]/div[2]/div[2]/ul/li[1]/div[1]"));
	// //select 第一个
	// e.click();
	// WebElement
	// e2=wb.findElement(By.xpath("//*[@id='app']/div/div/div[2]/div[2]/div[1]/div/div[1]/a[2]"));
	// // write message
	// e2.click();
	// wait.WaiteAllElement(By.xpath(".//*[@id='app']"));
	//
	// Thread.sleep(1000);
	//
	// eLabel1=wb.findElement(By.xpath(".//*[@id='app']//div[text()='推广']"));
	// String s=eLabel1.getAttribute("style");
	//
	//
	//
	// //点击已经是选中状态的推广，看是否颜色会变，应该不变
	// Assert.assertEquals(s.contains("rgb(46, 199, 93)"),false);
	//
	//
	// }
	//

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
	@Parameters({ "OperationUser" })
	public void beforeClass(@Optional("13132919225") String OperationUser) throws InterruptedException {
		l = new login(OperationUser);
		// Thread.sleep(1000);
		wb = l.my_driver;

		wait = new MyWDWait(wb);
		mywb = new MyDriver(wb);

		// 以下是跳转到撰写消息的页面
		wait.WaiteAllElement(By.xpath(".//*[@id='app']/*"));
		WebElement e = mywb
				.findElement(By.xpath(".//*[@id='app']/div/div/div[2]/div[2]/div[2]/div[2]/ul/li[1]/div[1]")); // select
																												// 第一个
		e.click();
		WebElement e2 = mywb.findElement(By.xpath(".//*[@id='app']/div/div/div[2]/div[2]/div[1]/div/div[1]/a[2]")); // write
																													// message

		wait.WaiteAllElement(By.xpath(".//*[@id='app']/*"));
		e2.click();
		Thread.sleep(1000);
		wait.WaiteAllElement(By.xpath(".//*[@id='app']/*"));
		System.out.println("方法运行前的URL是" + wb.getCurrentUrl());

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
