/**
 * 
 */
package Operation.WriteNewMsg;

import java.io.File;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
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
 * @author wei 测试运营人员-新消息撰写-图片上传的案例
 */
public class PictureUpload extends TestBase {
	login l = null;
	public WebDriver wb = null;
	MyWDWait wait = null;
	MyDriver mywb = null;

	public WebDriver getDriver() {
		return wb;
	}

	// 57 上传图片 图片标题 标题输入框，是否存在文案提示
	@Test

	public void a1TitleText() throws InterruptedException {
		WebElement ePic = mywb.findElement(
				By.xpath(".//*[@id='app']/div/div/div[2]/div[2]/div[2]/div/div[4]/div[2]/div[1]/div[2]/a/form/input")); // 图片
		ePic.clear();
		String s = new File(".").getAbsolutePath() + "/src/solution3.jpg";
		System.out.println(s);
		ePic.sendKeys(s);
		wait.WaiteTextPresent(
				By.xpath(
						".//*[@id='app']/div/div/div[2]/div[2]/div[2]/div/div[4]/div[2]/div/div[2]/div/div/div[2]/div"),
				"已上传");

		WebElement eTextArea = mywb.findElement(By.xpath(
				".//*[@id='app']/div/div/div[2]/div[2]/div[2]/div/div[4]/div[2]/div/div[2]/div/div/div[4]/div/textarea"));
		Assert.assertEquals(eTextArea.getAttribute("placeholder"), "限30个字");
	}

	// 59 上传图片 输入超过30字符标题 是否添加长度验证
	@Test
	public void a2Title30Chars() throws InterruptedException {
		WebElement ePic = mywb.findElement(
				By.xpath(".//*[@id='app']/div/div/div[2]/div[2]/div[2]/div/div[4]/div[2]/div[1]/div[2]/a/form/input")); // 图片

		ePic.clear();
		String s = new File(".").getAbsolutePath() + "/src/solution3.jpg";
		System.out.println(s);
		ePic.sendKeys(s);
		wait.WaiteTextPresent(
				By.xpath(
						".//*[@id='app']/div/div/div[2]/div[2]/div[2]/div/div[4]/div[2]/div/div[2]/div/div/div[2]/div"),
				"已上传");

		WebElement eTextArea = mywb.findElement(By.xpath(
				".//*[@id='app']/div/div/div[2]/div[2]/div[2]/div/div[4]/div[2]/div/div[2]/div/div/div[4]/div/textarea"));
		eTextArea.clear();
		eTextArea.sendKeys("123456789012345678901234567890123");
		Assert.assertEquals(eTextArea.getText(), "123456789012345678901234567890");

	}

	// 62 上传图片 上传分辨率，过大或过小(超过标准尺寸)的图片 是否添加图片分辨率和大小限制
	@Test
	public void a3PictureSizeVerify() throws InterruptedException {
		WebElement ePic = mywb.findElement(
				By.xpath(".//*[@id='app']/div/div/div[2]/div[2]/div[2]/div/div[4]/div[2]/div[1]/div[2]/a/form/input")); // 图片
		ePic.clear();
		String s = new File(".").getAbsolutePath() + "/src/largesize.jpg";
		System.out.println(s);
		ePic.sendKeys(s);
		Thread.sleep(1000);
		String alert = mywb.findElement(By.xpath(".//*[@id='app']/div/div/div[2]/div[4]/div/div/div/div[3]/a"))
				.getText();
		Assert.assertEquals(alert, "知道了");

	}

	// 66 上传图片 图片上传过程中，输入标题 能否成功输入标题
	@Test
	public void a4UploadingTextInput() throws InterruptedException {
		WebElement ePic = mywb.findElement(
				By.xpath(".//*[@id='app']/div/div/div[2]/div[2]/div[2]/div/div[4]/div[2]/div[1]/div[2]/a/form/input")); // 图片
		ePic.clear();
		String s = new File(".").getAbsolutePath() + "/src/largesize2.jpg";
		ePic.sendKeys(s);
		WebElement eTextArea = wb.findElement(By.xpath(
				".//*[@id='app']/div/div/div[2]/div[2]/div[2]/div/div[4]/div[2]/div/div[2]/div/div/div[4]/div/textarea"));
		wait.WaitElementPresent(By.xpath(
				".//*[@id='app']/div/div/div[2]/div[2]/div[2]/div/div[4]/div[2]/div/div[2]/div/div/div[4]/div/textarea"));
		eTextArea.clear();
		eTextArea.sendKeys("123456");
		Assert.assertEquals(eTextArea.getText(), "123456");

	}

	// 67 上传图片 图片上传过程中，滑动鼠标滚轴 能否滑动背景，但不影响上传
	@Test
	public void a5UploadingScroll() throws InterruptedException {
		WebElement ePic = mywb.findElement(
				By.xpath(".//*[@id='app']/div/div/div[2]/div[2]/div[2]/div/div[4]/div[2]/div[1]/div[2]/a/form/input")); // 图片
		ePic.clear();
		String s = new File(".").getAbsolutePath() + "/src/largesize2.jpg";
		ePic.sendKeys(s);
		JavascriptExecutor js = (JavascriptExecutor) wb;

		js.executeScript("window.scrollBy(0,1000)");

		js.executeScript("window.scrollBy(0,-1000)");
		boolean flag = wait.WaiteTextPresent(
				By.xpath(
						".//*[@id='app']/div/div/div[2]/div[2]/div[2]/div/div[4]/div[2]/div/div[2]/div/div/div[2]/div"),
				"已上传");

		Assert.assertEquals(flag, true);

	}

	// 68 上传图片 图片上传过程中，滑点击上传弹层外的其他区域 能否不影响图片上传
	@Test
	public void a6UploadingClickOutside() throws InterruptedException {
		WebElement ePic = mywb.findElement(
				By.xpath(".//*[@id='app']/div/div/div[2]/div[2]/div[2]/div/div[4]/div[2]/div[1]/div[2]/a/form/input")); // 图片
		ePic.clear();
		String s = new File(".").getAbsolutePath() + "/src/largesize2.jpg";
		ePic.sendKeys(s);
		Actions act = new Actions(wb);
		act.moveByOffset(0, 200);
		act.click();

		boolean flag = wait.WaiteTextPresent(
				By.xpath(
						".//*[@id='app']/div/div/div[2]/div[2]/div[2]/div/div[4]/div[2]/div/div[2]/div/div/div[2]/div"),
				"已上传");

		Assert.assertEquals(flag, true);

	}

	// 69 上传图片 图片上传过程中，点击完成 完成按钮是否为禁用态
	@Test
	public void a7UploadingClickFinish() throws InterruptedException {
		WebElement ePic = mywb.findElement(
				By.xpath(".//*[@id='app']/div/div/div[2]/div[2]/div[2]/div/div[4]/div[2]/div[1]/div[2]/a/form/input")); // 图片
		ePic.clear();
		String s = new File(".").getAbsolutePath() + "/src/largesize2.jpg";
		ePic.sendKeys(s);

		// 点完成摁钮
		WebElement eFinish = mywb.findElement(By.xpath(
				".//*[@id='app']/div/div/div[2]/div[2]/div[2]/div/div[4]/div[2]/div[1]/div[2]/div/div/div[5]/a"));

		String sFinish = eFinish.getAttribute("style");

		Assert.assertEquals(sFinish.contains("rgba(46, 199, 93, 0.5)"), false); // 判断 完成 不是绿色

	}

	// 70 上传图片 图片上传过程中，关闭 能否成功弹出确认弹窗
	@Test
	public void a8UploadingClickX() throws InterruptedException {
		WebElement ePic = mywb.findElement(
				By.xpath(".//*[@id='app']/div/div/div[2]/div[2]/div[2]/div/div[4]/div[2]/div[1]/div[2]/a/form/input")); // 图片
		ePic.clear();
		String s = new File(".").getAbsolutePath() + "/src/3MB.jpg";
		ePic.sendKeys(s);

		// 点X摁钮
		WebElement eX = mywb.findElement(By.xpath(
				".//*[@id='app']/div/div/div[2]/div[2]/div[2]/div/div[4]/div[2]/div[1]/div[2]/div/div/i[3]/*[name()='svg']"));
		eX.click();
		boolean flag = wait
				.WaitElementPresent(By.xpath(".//*[@id='app']/div/div/div[2]/div[3]/div/div/div/div/div[3]/a[1]")); // 确定摁钮

		Assert.assertEquals(flag, true); // 判断弹出确定摁钮

	}

	// 71 上传图片 图片上传过程中，取消关闭 能否完成图片上传
	@Test
	public void a8UploadingCloseCancel() throws InterruptedException {
		WebElement ePic = mywb.findElement(
				By.xpath(".//*[@id='app']/div/div/div[2]/div[2]/div[2]/div/div[4]/div[2]/div[1]/div[2]/a/form/input")); // 图片
		ePic.clear();
		String s = new File(".").getAbsolutePath() + "/src/3MB.jpg";
		ePic.sendKeys(s);

		// 点X摁钮
		WebElement eX = mywb.findElement(By.xpath(
				".//*[@id='app']/div/div/div[2]/div[2]/div[2]/div/div[4]/div[2]/div[1]/div[2]/div/div/i[3]/*[name()='svg']"));
		eX.click();

		// 点取消
		wait.WaitElementPresent(By.xpath(".//*[@id='app']/div/div/div[2]/div[3]/div/div/div/div/div[3]/a[2]"));
		WebElement eCancel = mywb
				.findElement(By.xpath(".//*[@id='app']/div/div/div[2]/div[3]/div/div/div/div/div[3]/a[2]")); // 弹框的取消按钮
		eCancel.click();

		boolean flag = wait.WaiteTextPresent(
				By.xpath(
						".//*[@id='app']/div/div/div[2]/div[2]/div[2]/div/div[4]/div[2]/div/div[2]/div/div/div[2]/div"),
				"已上传");

		Assert.assertEquals(flag, true); // 判断已上传 出现

	}
	// 72 上传图片 图片上传过程中，确认关闭 弹层能否成功关闭

	@Test
	public void a9UploadingCloseConfirm() throws InterruptedException {
		WebElement ePic = mywb.findElement(
				By.xpath(".//*[@id='app']/div/div/div[2]/div[2]/div[2]/div/div[4]/div[2]/div[1]/div[2]/a/form/input")); // 图片
		ePic.clear();
		String s = new File(".").getAbsolutePath() + "/src/3MB.jpg";
		ePic.sendKeys(s);

		// 点X摁钮
		WebElement eX = mywb.findElement(By.xpath(
				".//*[@id='app']/div/div/div[2]/div[2]/div[2]/div/div[4]/div[2]/div[1]/div[2]/div/div/i[3]/*[name()='svg']"));
		eX.click();

		// 点确定
		wait.WaitElementPresent(By.xpath(".//*[@id='app']/div/div/div[2]/div[3]/div/div/div/div/div[3]/a[1]"));
		WebElement eConfirm = mywb
				.findElement(By.xpath(".//*[@id='app']/div/div/div[2]/div[3]/div/div/div/div/div[3]/a[1]")); // 弹框的确定按钮
		eConfirm.click();
		Thread.sleep(10);
		// 判断弹层关闭 高度为0
		String eH = mywb
				.findElement(By.xpath(".//*[@id='app']/div/div/div[2]/div[2]/div[2]/div/div[4]/div[2]/div/div[2]/div"))
				.getAttribute("style");
		System.out.println(eH);
		Assert.assertEquals(eH.contains("height: 0px"), true);

	}

	// 73 上传图片 取消/关闭上传后，重新上传 能否重新上传图片
	@Test
	public void b1UploadingCloseConfirmUploadAgain() throws InterruptedException {
		WebElement ePic = mywb.findElement(
				By.xpath(".//*[@id='app']/div/div/div[2]/div[2]/div[2]/div/div[4]/div[2]/div[1]/div[2]/a/form/input")); // 图片
		ePic.clear();
		String s = new File(".").getAbsolutePath() + "/src/3MB.jpg";
		ePic.sendKeys(s);

		// 点X摁钮
		WebElement eX = mywb.findElement(By.xpath(
				".//*[@id='app']/div/div/div[2]/div[2]/div[2]/div/div[4]/div[2]/div[1]/div[2]/div/div/i[3]/*[name()='svg']"));
		eX.click();

		// 点确定
		wait.WaitElementPresent(By.xpath(".//*[@id='app']/div/div/div[2]/div[3]/div/div/div/div/div[3]/a[1]"));
		WebElement eConfirm = mywb
				.findElement(By.xpath(".//*[@id='app']/div/div/div[2]/div[3]/div/div/div/div/div[3]/a[1]")); // 弹框的确定按钮
		eConfirm.click();
		Thread.sleep(10);

		// 再次上传图片
		ePic.clear();
		String s2 = new File(".").getAbsolutePath() + "/src/solution3.jpg";
		ePic.sendKeys(s2);

		boolean flag = wait.WaiteTextPresent(
				By.xpath(
						".//*[@id='app']/div/div/div[2]/div[2]/div[2]/div/div[4]/div[2]/div/div[2]/div/div/div[2]/div"),
				"已上传");

		Assert.assertEquals(flag, true); // 判断已上传 出现

	}

	// 75 上传图片 上传完毕后，滑动鼠标滚轴 能否滑动背景，但不影响上传
	@Test
	public void b2UploadedScroll() throws InterruptedException {
		WebElement ePic = mywb.findElement(
				By.xpath(".//*[@id='app']/div/div/div[2]/div[2]/div[2]/div/div[4]/div[2]/div[1]/div[2]/a/form/input")); // 图片
		ePic.clear();
		String s = new File(".").getAbsolutePath() + "/src/solution3.jpg";
		ePic.sendKeys(s);
		wait.WaiteTextPresent(
				By.xpath(
						".//*[@id='app']/div/div/div[2]/div[2]/div[2]/div/div[4]/div[2]/div/div[2]/div/div/div[2]/div"),
				"已上传");
		JavascriptExecutor js = (JavascriptExecutor) wb;
		Thread.sleep(100);
		js.executeScript("window.scrollBy(0,1000)");
		Thread.sleep(100);
		js.executeScript("window.scrollBy(0,-1000)");
		WebElement eText = mywb.findElement(By
				.xpath(".//*[@id='app']/div/div/div[2]/div[2]/div[2]/div/div[4]/div[2]/div/div[2]/div/div/div[2]/div"));
		String s2 = eText.getText();
		Assert.assertEquals(s2.contains("已上传"), true);

	}

	// 76 上传图片 上传完毕后，滑点击上传弹层外的其他区域 能否不影响图片上传
	@Test
	public void b3UloadedClickOutside() throws InterruptedException {
		WebElement ePic = mywb.findElement(
				By.xpath(".//*[@id='app']/div/div/div[2]/div[2]/div[2]/div/div[4]/div[2]/div[1]/div[2]/a/form/input")); // 图片
		ePic.clear();
		String s = new File(".").getAbsolutePath() + "/src/solution3.jpg";
		ePic.sendKeys(s);
		wait.WaiteTextPresent(
				By.xpath(
						".//*[@id='app']/div/div/div[2]/div[2]/div[2]/div/div[4]/div[2]/div/div[2]/div/div/div[2]/div"),
				"已上传");

		Actions act = new Actions(wb);
		act.moveByOffset(0, 200);
		act.click();

		WebElement eText = mywb.findElement(By
				.xpath(".//*[@id='app']/div/div/div[2]/div[2]/div[2]/div/div[4]/div[2]/div/div[2]/div/div/div[2]/div"));
		String s2 = eText.getText();
		Assert.assertEquals(s2.contains("已上传"), true);

	}

	// 78 上传图片 上传完毕后，输入标题，完成 能否完成图片上传
	@Test
	public void b4UploadedTextInput() throws InterruptedException {
		WebElement ePic = mywb.findElement(
				By.xpath(".//*[@id='app']/div/div/div[2]/div[2]/div[2]/div/div[4]/div[2]/div[1]/div[2]/a/form/input")); // 图片
		ePic.clear();
		String s = new File(".").getAbsolutePath() + "/src/solution3.jpg";
		ePic.sendKeys(s);
		wait.WaiteTextPresent(
				By.xpath(
						".//*[@id='app']/div/div/div[2]/div[2]/div[2]/div/div[4]/div[2]/div/div[2]/div/div/div[2]/div"),
				"已上传");

		WebElement eTextArea = wb.findElement(By.xpath(
				".//*[@id='app']/div/div/div[2]/div[2]/div[2]/div/div[4]/div[2]/div/div[2]/div/div/div[4]/div/textarea"));

		eTextArea.clear();
		eTextArea.sendKeys("123456");

		WebElement eFinish = mywb.findElement(By.xpath(
				".//*[@id='app']/div/div/div[2]/div[2]/div[2]/div/div[4]/div[2]/div[1]/div[2]/div/div/div[5]/a"));
		eFinish.click();

		Thread.sleep(1200);
		wait.WaiteAllElement(By.xpath(".//*[@id='app']"));
		WebElement eShow = wb
				.findElement(By.xpath("//*[@id='app']/div/div/div[2]/div[2]/div[2]/div/div[4]/div/div[2]"));
		Assert.assertEquals(eShow.getText(), "123456");
	}

	// 79 上传图片 上传完毕后，重新上传 能否重新上传图片
	@Test
	public void b5UploadAgain() throws InterruptedException {
		WebElement ePic = mywb.findElement(
				By.xpath(".//*[@id='app']/div/div/div[2]/div[2]/div[2]/div/div[4]/div[2]/div[1]/div[2]/a/form/input")); // 图片
		ePic.clear();
		String s = new File(".").getAbsolutePath() + "/src/solution3.jpg";
		ePic.sendKeys(s);
		wait.WaiteTextPresent(
				By.xpath(
						".//*[@id='app']/div/div/div[2]/div[2]/div[2]/div/div[4]/div[2]/div/div[2]/div/div/div[2]/div"),
				"已上传");

		String s2 = new File(".").getAbsolutePath() + "/src/largesize2.jpg";
		WebElement ePic2 = mywb.findElement(By.xpath(
				".//*[@id='app']/div/div/div[2]/div[2]/div[2]/div/div[4]/div[2]/div/div[2]/div/div/div[2]/a/form/input"));
		ePic2.clear();
		ePic2.sendKeys(s2);
		wait.WaiteTextPresent(
				By.xpath(
						".//*[@id='app']/div/div/div[2]/div[2]/div[2]/div/div[4]/div[2]/div/div[2]/div/div/div[2]/div"),
				"已上传");

		WebElement eText = mywb.findElement(By
				.xpath(".//*[@id='app']/div/div/div[2]/div[2]/div[2]/div/div[4]/div[2]/div/div[2]/div/div/div[2]/div"));
		String s3 = eText.getText();

		Assert.assertEquals(s3.contains("largesize2"), true);
	}

	// 80 上传图片 重新上传完毕后，使用之前输入的标题，完成 能否完成图片上传
	@Test
	public void b6UploadedAgainTextVerify() throws InterruptedException {
		// 上传图1
		WebElement ePic = mywb.findElement(
				By.xpath(".//*[@id='app']/div/div/div[2]/div[2]/div[2]/div/div[4]/div[2]/div[1]/div[2]/a/form/input")); // 图片
		ePic.clear();
		String s = new File(".").getAbsolutePath() + "/src/solution3.jpg";
		ePic.sendKeys(s);
		wait.WaiteTextPresent(
				By.xpath(
						".//*[@id='app']/div/div/div[2]/div[2]/div[2]/div/div[4]/div[2]/div/div[2]/div/div/div[2]/div"),
				"已上传");

		WebElement eTextArea = wb.findElement(By.xpath(
				".//*[@id='app']/div/div/div[2]/div[2]/div[2]/div/div[4]/div[2]/div/div[2]/div/div/div[4]/div/textarea"));
		// 输入标题
		eTextArea.clear();
		eTextArea.sendKeys("123456");
		// 重新上传图2
		String s2 = new File(".").getAbsolutePath() + "/src/largesize2.jpg";
		WebElement ePic2 = mywb.findElement(By.xpath(
				".//*[@id='app']/div/div/div[2]/div[2]/div[2]/div/div[4]/div[2]/div/div[2]/div/div/div[2]/a/form/input"));
		ePic2.clear();
		ePic2.sendKeys(s2);
		wait.WaiteTextPresent(
				By.xpath(
						".//*[@id='app']/div/div/div[2]/div[2]/div[2]/div/div[4]/div[2]/div/div[2]/div/div/div[2]/div"),
				"已上传");
		// 点击完成
		WebElement eFinish = mywb.findElement(By.xpath(
				".//*[@id='app']/div/div/div[2]/div[2]/div[2]/div/div[4]/div[2]/div[1]/div[2]/div/div/div[5]/a"));
		eFinish.click();

		Thread.sleep(1200);
		wait.WaiteAllElement(By.xpath(".//*[@id='app']"));
		// 判断显示的标题还是重新上传图2之前设置的
		WebElement eShow = wb
				.findElement(By.xpath("//*[@id='app']/div/div/div[2]/div[2]/div[2]/div/div[4]/div/div[2]"));
		Assert.assertEquals(eShow.getText(), "123456");
	}

	// 81 上传图片 重新上传完毕后，再次上传 能否重新上传图片
	@Test
	public void b7UploadedAgainTwice() throws InterruptedException {
		// 上传图1
		WebElement ePic = mywb.findElement(
				By.xpath(".//*[@id='app']/div/div/div[2]/div[2]/div[2]/div/div[4]/div[2]/div[1]/div[2]/a/form/input")); // 图片
		ePic.clear();
		String s = new File(".").getAbsolutePath() + "/src/solution3.jpg";
		ePic.sendKeys(s);
		wait.WaiteTextPresent(
				By.xpath(
						".//*[@id='app']/div/div/div[2]/div[2]/div[2]/div/div[4]/div[2]/div/div[2]/div/div/div[2]/div"),
				"已上传");

		// 重新上传图2
		String s2 = new File(".").getAbsolutePath() + "/src/largesize2.jpg";
		WebElement ePic2 = mywb.findElement(By.xpath(
				".//*[@id='app']/div/div/div[2]/div[2]/div[2]/div/div[4]/div[2]/div/div[2]/div/div/div[2]/a/form/input"));
		ePic2.clear();
		ePic2.sendKeys(s2);
		wait.WaiteTextPresent(
				By.xpath(
						".//*[@id='app']/div/div/div[2]/div[2]/div[2]/div/div[4]/div[2]/div/div[2]/div/div/div[2]/div"),
				"已上传");
		// 重新上传图1
		WebElement ePic3 = mywb.findElement(By.xpath(
				".//*[@id='app']/div/div/div[2]/div[2]/div[2]/div/div[4]/div[2]/div/div[2]/div/div/div[2]/a/form/input"));
		ePic3.clear();

		ePic3.sendKeys(s);
		wait.WaiteTextPresent(
				By.xpath(
						".//*[@id='app']/div/div/div[2]/div[2]/div[2]/div/div[4]/div[2]/div/div[2]/div/div/div[2]/div"),
				"已上传");

		WebElement eText = mywb.findElement(By
				.xpath(".//*[@id='app']/div/div/div[2]/div[2]/div[2]/div/div[4]/div[2]/div/div[2]/div/div/div[2]/div"));
		String sText = eText.getText();

		Assert.assertEquals(sText.contains("solution3"), true);
	}

	// 82 上传图片 上传完毕后，关闭 能否成功弹出确认弹窗
	@Test
	public void b8UploadedClose() throws InterruptedException {
		// 上传图1
		WebElement ePic = mywb.findElement(
				By.xpath(".//*[@id='app']/div/div/div[2]/div[2]/div[2]/div/div[4]/div[2]/div[1]/div[2]/a/form/input")); // 图片
		ePic.clear();
		String s = new File(".").getAbsolutePath() + "/src/solution3.jpg";
		ePic.sendKeys(s);
		wait.WaiteTextPresent(
				By.xpath(
						".//*[@id='app']/div/div/div[2]/div[2]/div[2]/div/div[4]/div[2]/div/div[2]/div/div/div[2]/div"),
				"已上传");

		// 点X摁钮
		WebElement eX = mywb.findElement(By.xpath(
				".//*[@id='app']/div/div/div[2]/div[2]/div[2]/div/div[4]/div[2]/div[1]/div[2]/div/div/i[3]/*[name()='svg']"));
		eX.click();

		boolean flag = wait
				.WaitElementPresent(By.xpath(".//*[@id='app']/div/div/div[2]/div[3]/div/div/div/div/div[3]/a[1]")); // 确定摁钮
		Assert.assertEquals(flag, true); // 判断弹出确定摁钮

	}

	// 83 上传图片 上传完毕后，取消关闭 能否完成图片上传
	@Test
	public void b9UploadedCloseCancel() throws InterruptedException {
		// 上传图1
		WebElement ePic = mywb.findElement(
				By.xpath(".//*[@id='app']/div/div/div[2]/div[2]/div[2]/div/div[4]/div[2]/div[1]/div[2]/a/form/input")); // 图片
		ePic.clear();
		String s = new File(".").getAbsolutePath() + "/src/solution3.jpg";
		ePic.sendKeys(s);
		wait.WaiteTextPresent(
				By.xpath(
						".//*[@id='app']/div/div/div[2]/div[2]/div[2]/div/div[4]/div[2]/div/div[2]/div/div/div[2]/div"),
				"已上传");

		// 点X摁钮
		WebElement eX = mywb.findElement(By.xpath(
				".//*[@id='app']/div/div/div[2]/div[2]/div[2]/div/div[4]/div[2]/div[1]/div[2]/div/div/i[3]/*[name()='svg']"));
		eX.click();
		// 点取消
		wait.WaitElementPresent(By.xpath(".//*[@id='app']/div/div/div[2]/div[3]/div/div/div/div/div[3]/a[2]"));
		WebElement eCancel = mywb
				.findElement(By.xpath(".//*[@id='app']/div/div/div[2]/div[3]/div/div/div/div/div[3]/a[2]")); // 弹框的取消按钮
		eCancel.click();

		boolean flag = wait.WaiteTextPresent(
				By.xpath(
						".//*[@id='app']/div/div/div[2]/div[2]/div[2]/div/div[4]/div[2]/div/div[2]/div/div/div[2]/div"),
				"已上传");
		Assert.assertEquals(flag, true); // 判断已上传 出现

	}

	// 84 上传图片 上传完毕后，确认关闭 弹层能否成功关闭
	@Test
	public void c1UploadedCloseConfirm() throws InterruptedException {
		WebElement ePic = mywb.findElement(
				By.xpath(".//*[@id='app']/div/div/div[2]/div[2]/div[2]/div/div[4]/div[2]/div[1]/div[2]/a/form/input")); // 图片
		ePic.clear();
		String s = new File(".").getAbsolutePath() + "/src/solution3.jpg";
		ePic.sendKeys(s);
		// 判断上传完
		wait.WaiteTextPresent(
				By.xpath(
						".//*[@id='app']/div/div/div[2]/div[2]/div[2]/div/div[4]/div[2]/div/div[2]/div/div/div[2]/div"),
				"已上传");

		// 点X摁钮
		WebElement eX = mywb.findElement(By.xpath(
				".//*[@id='app']/div/div/div[2]/div[2]/div[2]/div/div[4]/div[2]/div[1]/div[2]/div/div/i[3]/*[name()='svg']"));
		eX.click();

		// 点确定
		wait.WaitElementPresent(By.xpath(".//*[@id='app']/div/div/div[2]/div[3]/div/div/div/div/div[3]/a[1]"));
		WebElement eConfirm = mywb
				.findElement(By.xpath(".//*[@id='app']/div/div/div[2]/div[3]/div/div/div/div/div[3]/a[1]")); // 弹框的确定按钮
		eConfirm.click();
		Thread.sleep(10);
		// 判断弹层关闭 高度为0
		String eH = mywb
				.findElement(By.xpath(".//*[@id='app']/div/div/div[2]/div[2]/div[2]/div/div[4]/div[2]/div/div[2]/div"))
				.getAttribute("style");
		System.out.println(eH);
		Assert.assertEquals(eH.contains("height: 0px"), true);

	}

	// 85 上传图片 取消/关闭上传后，重新上传 能否重新上传图片
	@Test
	public void c2UploadedCloseConfirmUploadAgain() throws InterruptedException {
		WebElement ePic = mywb.findElement(
				By.xpath(".//*[@id='app']/div/div/div[2]/div[2]/div[2]/div/div[4]/div[2]/div[1]/div[2]/a/form/input")); // 图片
		ePic.clear();
		String s = new File(".").getAbsolutePath() + "/src/solution3.jpg";
		ePic.sendKeys(s);
		// 判断上传完
		wait.WaiteTextPresent(
				By.xpath(
						".//*[@id='app']/div/div/div[2]/div[2]/div[2]/div/div[4]/div[2]/div/div[2]/div/div/div[2]/div"),
				"已上传");

		// 点X摁钮
		WebElement eX = mywb.findElement(By.xpath(
				".//*[@id='app']/div/div/div[2]/div[2]/div[2]/div/div[4]/div[2]/div[1]/div[2]/div/div/i[3]/*[name()='svg']"));
		eX.click();

		// 点确定
		wait.WaitElementPresent(By.xpath(".//*[@id='app']/div/div/div[2]/div[3]/div/div/div/div/div[3]/a[1]"));
		WebElement eConfirm = mywb
				.findElement(By.xpath(".//*[@id='app']/div/div/div[2]/div[3]/div/div/div/div/div[3]/a[1]")); // 弹框的确定按钮
		eConfirm.click();
		Thread.sleep(10);

		// 再次上传图片
		ePic.clear();
		String s2 = new File(".").getAbsolutePath() + "/src/solution3.jpg";
		ePic.sendKeys(s2);

		boolean flag = wait.WaiteTextPresent(
				By.xpath(
						".//*[@id='app']/div/div/div[2]/div[2]/div[2]/div/div[4]/div[2]/div/div[2]/div/div/div[2]/div"),
				"已上传");

		Assert.assertEquals(flag, true); // 判断已上传 出现

	}

	@BeforeMethod
	public void beforeMethod() throws InterruptedException {
		// 以下是跳转到撰写消息的页面
		mywb.get("http://testmcenter.weixinxk.com/admin/pushmessage/edit/index");
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
		WebElement eService = mywb.findElement(By.xpath(".//*[@id='app']//div[text()='服务消息']"));
		eService.click();

		System.out.println("方法运行前的URL是" + wb.getCurrentUrl());

	}

	@AfterClass
	public void afterClass() {
		l.quiteBrower();
	}

	@BeforeClass(groups = "only")
	@Parameters({ "OperationUser" })
	public void beforeClass(@Optional("13123456707") String OperationUser) throws InterruptedException {
		l = new login(OperationUser);

		wb = l.my_driver;
		mywb = new MyDriver(wb);
		wait = new MyWDWait(wb);

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
