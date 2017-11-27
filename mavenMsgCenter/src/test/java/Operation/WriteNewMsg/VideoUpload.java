/**
 * 
 */
package Operation.WriteNewMsg;

import java.io.File;

import org.openqa.selenium.Alert;
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
 * @author wei 测试运营人员-新消息撰写-视频上传的案例
 */
public class VideoUpload extends TestBase {
	login l = null;
	public WebDriver wb = null;
	MyWDWait wait = null;
	MyDriver mywb = null;

	public WebDriver getDriver() {
		return wb;
	}

	// 87 上传视频 视频标题 标题输入框，是否存在文案提示 v1.2.0 Y
	@Test
	public void a1TitleAlert() throws InterruptedException {
		WebElement eVideo = wb.findElement(
				By.xpath(".//*[@id='app']/div/div/div[2]/div[2]/div[2]/div/div[4]/div[2]/div[1]/div[1]/a/form/input")); // 视频内容
		eVideo.clear();
		String s = new File(".").getAbsolutePath() + "/src/video1.mp4";
		System.out.println(s);
		eVideo.sendKeys(s);
		// 等待上传成功
		wait.WaiteTextPresent(By.xpath(
				".//*[@id='app']/div/div/div[2]/div[2]/div[2]/div/div[4]/div[2]/div[1]/div[1]/div/div/div[2]/div[2]/div/div/span[2]"),
				"上传成功");

		// 查看页面上的视频标题
		WebElement eTextArea = mywb.findElement(By.xpath(
				".//*[@id='app']/div/div/div[2]/div[2]/div[2]/div/div[4]/div[2]/div[1]/div[1]/div/div/div[2]/div[4]/div/textarea"));
		String sText = eTextArea.getAttribute("placeholder");

		// 点击完成
		Assert.assertEquals(sText, "限30个字"); // 判断显示的Text是默认的

	}

	// 89 上传视频 输入超过30字符标题 是否添加长度验证 v1.2.0 Y
	@Test
	public void a2Title30chars() throws InterruptedException {
		WebElement eVideo = wb.findElement(
				By.xpath(".//*[@id='app']/div/div/div[2]/div[2]/div[2]/div/div[4]/div[2]/div[1]/div[1]/a/form/input")); // 视频内容
		eVideo.clear();
		String s = new File(".").getAbsolutePath() + "/src/video1.mp4";
		System.out.println(s);
		eVideo.sendKeys(s);
		// 等待上传成功
		wait.WaiteTextPresent(By.xpath(
				".//*[@id='app']/div/div/div[2]/div[2]/div[2]/div/div[4]/div[2]/div[1]/div[1]/div/div/div[2]/div[2]/div/div/span[2]"),
				"上传成功");

		// 查看页面上的视频标题
		WebElement eTextArea = mywb.findElement(By.xpath(
				".//*[@id='app']/div/div/div[2]/div[2]/div[2]/div/div[4]/div[2]/div[1]/div[1]/div/div/div[2]/div[4]/div/textarea"));
		eTextArea.clear();
		eTextArea.sendKeys("1234567890123456789012345678901"); // 输入31位 看是否显示30位

		// 点击完成
		Assert.assertEquals(eTextArea.getText(), "123456789012345678901234567890"); // 判断显示的是30位

	}

	// 91 上传视频 上传错误格式的视频 视频格式是否添加限制 v1.2.0 Y
	@Test
	public void a3WrongFormat() throws InterruptedException {
		WebElement eVideo = mywb.findElement(
				By.xpath(".//*[@id='app']/div/div/div[2]/div[2]/div[2]/div/div[4]/div[2]/div[1]/div[1]/a/form/input")); // 视频内容
		eVideo.clear();
		String s = new File(".").getAbsolutePath() + "/src/solution3.jpg";
		System.out.println(s);
		eVideo.sendKeys(s);

		Thread.sleep(2000);
		// 等待Alert 出来
		Alert alt = wb.switchTo().alert();
		String salt = alt.getText();
		alt.dismiss();

		Assert.assertEquals(salt, "请上传正确的mp4的视频");

	}

	// 92 上传视频 上传小于10MB的视频 是否添加视频分辨率和大小限制 v1.2.0 Y Bug15035

	// 95 上传视频 视频上传过程中，输入标题 能否成功输入标题 v1.2.0 Y
	@Test
	public void a4UploadingInputTitle() throws InterruptedException {
		WebElement eVideo = mywb.findElement(
				By.xpath(".//*[@id='app']/div/div/div[2]/div[2]/div[2]/div/div[4]/div[2]/div[1]/div[1]/a/form/input")); // 视频内容
		eVideo.clear();
		String s = new File(".").getAbsolutePath() + "/video/1MB480X360.mp4";
		eVideo.sendKeys(s);

		// 输入页面上的视频标题
		WebElement eTextArea = mywb.findElement(By.xpath(
				".//*[@id='app']/div/div/div[2]/div[2]/div[2]/div/div[4]/div[2]/div[1]/div[1]/div/div/div[2]/div[4]/div/textarea"));
		eTextArea.clear();
		eTextArea.sendKeys("12345");
		// 等待上传完成
		wait.WaiteTextPresent(By.xpath(
				".//*[@id='app']/div/div/div[2]/div[2]/div[2]/div/div[4]/div[2]/div[1]/div[1]/div/div/div[2]/div[2]/div/div/span[2]"),
				"上传成功");
		// 点击完成
		WebElement eFinish = mywb.findElement(By.xpath(
				".//*[@id='app']/div/div/div[2]/div[2]/div[2]/div/div[4]/div[2]/div/div[1]/div/div/div[2]/div[5]/a"));
		eFinish.click();

		boolean flag = wait.WaiteTextPresent(
				By.xpath(".//*[@id='app']/div/div/div[2]/div[2]/div[2]/div/div[4]/div/div[2]"), "12345");
		Assert.assertEquals(flag, true); // 判断显示的是12345

	}

	// 96 上传视频 视频上传过程中，滑动鼠标滚轴 能否滑动背景，但不影响上传 v1.2.0 Y
	@Test
	public void a5UploadingScroll() throws InterruptedException {
		WebElement eVideo = mywb.findElement(
				By.xpath(".//*[@id='app']/div/div/div[2]/div[2]/div[2]/div/div[4]/div[2]/div[1]/div[1]/a/form/input")); // 视频内容
		eVideo.clear();
		String s = new File(".").getAbsolutePath() + "/video/1MB480X360.mp4";
		eVideo.sendKeys(s);
		// 滚动滚轴
		JavascriptExecutor js = (JavascriptExecutor) wb;
		Thread.sleep(100);
		js.executeScript("window.scrollBy(0,1000)");
		Thread.sleep(100);
		js.executeScript("window.scrollBy(0,-1000)");

		// 等待上传完成
		boolean flag = wait.WaiteTextPresent(By.xpath(
				".//*[@id='app']/div/div/div[2]/div[2]/div[2]/div/div[4]/div[2]/div[1]/div[1]/div/div/div[2]/div[2]/div/div/span[2]"),
				"上传成功");

		Assert.assertEquals(flag, true);

	}

	// 97 上传视频 视频上传过程中，滑点击上传弹层外的其他区域 能否不影响视频上传 v1.2.0 Y
	@Test
	public void a6UploadingClickOutside() throws InterruptedException {
		WebElement eVideo = mywb.findElement(
				By.xpath(".//*[@id='app']/div/div/div[2]/div[2]/div[2]/div/div[4]/div[2]/div[1]/div[1]/a/form/input")); // 视频内容
		eVideo.clear();
		String s = new File(".").getAbsolutePath() + "/video/1MB480X360.mp4";
		eVideo.sendKeys(s);
		// 点击外边
		Actions act = new Actions(wb);
		act.moveByOffset(0, 200);
		act.click();

		// 等待上传完成
		boolean flag = wait.WaiteTextPresent(By.xpath(
				".//*[@id='app']/div/div/div[2]/div[2]/div[2]/div/div[4]/div[2]/div[1]/div[1]/div/div/div[2]/div[2]/div/div/span[2]"),
				"上传成功");

		Assert.assertEquals(flag, true);

	}

	// 98 上传视频 视频上传过程中，点击完成 完成按钮是否为禁用态 v1.2.0 Y
	@Test
	public void a7UploadingVerifyFinish() throws InterruptedException {
		WebElement eVideo = mywb.findElement(
				By.xpath(".//*[@id='app']/div/div/div[2]/div[2]/div[2]/div/div[4]/div[2]/div[1]/div[1]/a/form/input")); // 视频内容
		eVideo.clear();
		String s = new File(".").getAbsolutePath() + "/video/2MB480X480.mp4";
		eVideo.sendKeys(s);

		// 完成摁钮
		WebElement eFinish = mywb.findElement(By.xpath(
				".//*[@id='app']/div/div/div[2]/div[2]/div[2]/div/div[4]/div[2]/div/div[1]/div/div/div[2]/div[5]/a"));
		String sFinish = eFinish.getAttribute("style");

		Assert.assertEquals(sFinish.contains("rgba(46, 199, 93, 0.5)"), false); // 判断 完成 不是绿色

	}

	// 99 上传视频 上传到50%时，关闭 能否成功弹出确认弹窗 Y 无法精确到50%，模拟40%-60%
	@Test
	public void a8Uploading50Close() throws InterruptedException {
		WebElement eVideo = mywb.findElement(
				By.xpath(".//*[@id='app']/div/div/div[2]/div[2]/div[2]/div/div[4]/div[2]/div[1]/div[1]/a/form/input")); // 视频内容
		eVideo.clear();
		String s = new File(".").getAbsolutePath() + "/video/3.8MB480x720.mp4";
		eVideo.sendKeys(s);

		// 进度条
		WebElement eSpan = null;
		boolean flag = false;
		int t = 0;
		int i;
		String text = null;
		// 进度条在40-60多的时候，点击X ，弹窗显示 flag则为真
		while (true) {
			eSpan = mywb.findElement(By.xpath(
					".//*[@id='app']/div/div/div[2]/div[2]/div[2]/div/div[4]/div[2]/div/div[1]/div/div/div[2]/div[2]/div/div/span[2]/span"));

			i = Integer.parseInt(eSpan.getText().replace("%", ""));

			if (i > 40 & i < 60) {
				// 点击X摁钮
				WebElement eX = mywb.findElement(By.xpath(
						".//*[@id='app']/div/div/div[2]/div[2]/div[2]/div/div[4]/div[2]/div/div[1]/div/div/i[3]/*[name()='svg']"));
				eX.click();
				flag = wait.WaitElementPresent(
						By.xpath(".//*[@id='app']/div/div/div[2]/div[3]/div/div/div/div/div[3]/a[1]"));
				break;

			} else {
				Thread.sleep(1);
				t++;

				try {
					text = wb.findElement(By.xpath(
							".//*[@id='app']/div/div/div[2]/div[2]/div[2]/div/div[4]/div[2]/div[1]/div[1]/div/div/div[2]/div[2]/div/div/span[2]"))
							.toString();
				} catch (Exception e) {
				}
				if (text.contains("上传成功")) {
					System.out.println("直接上传完了");
					break;
				}
				if (t > 6000) {
					System.out.println(t);
					break;
				}
			}
		}

		Assert.assertEquals(flag, true);

	}

	// 100 上传视频 上传到90%时，关闭 能否成功弹出确认弹窗 Y 测不了
	// 101 上传视频 上传到99%时，关闭 能否成功弹出确认弹窗 Y 测不了99%，测的事90-99
	@Test
	public void a9Uploading90Close() throws InterruptedException {
		WebElement eVideo = mywb.findElement(
				By.xpath(".//*[@id='app']/div/div/div[2]/div[2]/div[2]/div/div[4]/div[2]/div[1]/div[1]/a/form/input")); // 视频内容
		eVideo.clear();
		String s = new File(".").getAbsolutePath() + "/video/3.8MB480x720.mp4";
		eVideo.sendKeys(s);

		// 进度条
		WebElement eSpan = null;
		boolean flag = false;
		int t = 0;
		int i;
		String text = null;
		// 进度条在90多的时候，点击X ，弹窗显示 flag则为真
		while (true) {
			eSpan = mywb.findElement(By.xpath(
					".//*[@id='app']/div/div/div[2]/div[2]/div[2]/div/div[4]/div[2]/div/div[1]/div/div/div[2]/div[2]/div/div/span[2]/span"));

			i = Integer.parseInt(eSpan.getText().replace("%", ""));

			if (i > 89 & i < 100) {
				// 点击X摁钮
				WebElement eX = mywb.findElement(By.xpath(
						".//*[@id='app']/div/div/div[2]/div[2]/div[2]/div/div[4]/div[2]/div/div[1]/div/div/i[3]/*[name()='svg']"));
				eX.click();
				flag = wait.WaitElementPresent(
						By.xpath(".//*[@id='app']/div/div/div[2]/div[3]/div/div/div/div/div[3]/a[1]"));
				break;

			} else {
				Thread.sleep(1);
				t++;

				try {
					text = wb.findElement(By.xpath(
							".//*[@id='app']/div/div/div[2]/div[2]/div[2]/div/div[4]/div[2]/div[1]/div[1]/div/div/div[2]/div[2]/div/div/span[2]"))
							.toString();
				} catch (Exception e) {
				}
				if (text.contains("上传成功")) {
					System.out.println("直接上传完了");
					break;
				}
				if (t > 6000) {
					System.out.println(t);
					break;
				}
			}
		}

		Assert.assertEquals(flag, true);

	}

	// 102 上传视频 视频上传过程中，取消关闭 能否完成视频上传 v1.2.0 Y
	@Test
	public void b1UploadingCancelX() throws InterruptedException {
		WebElement eVideo = mywb.findElement(
				By.xpath(".//*[@id='app']/div/div/div[2]/div[2]/div[2]/div/div[4]/div[2]/div[1]/div[1]/a/form/input")); // 视频内容
		eVideo.clear();
		String s = new File(".").getAbsolutePath() + "/video/3.8MB480x720.mp4";
		eVideo.sendKeys(s);

		// 点击X摁钮
		WebElement eX = mywb.findElement(By.xpath(
				".//*[@id='app']/div/div/div[2]/div[2]/div[2]/div/div[4]/div[2]/div/div[1]/div/div/i[3]/*[name()='svg']"));
		eX.click();

		Thread.sleep(10);

		// 点击取消按钮
		WebElement eCancel = mywb
				.findElement(By.xpath(".//*[@id='app']/div/div/div[2]/div[3]/div/div/div/div/div[3]/a[2]"));
		eCancel.click();
		Thread.sleep(10);

		// 等待上传完成
		boolean flag = wait.WaiteTextPresent(By.xpath(
				".//*[@id='app']/div/div/div[2]/div[2]/div[2]/div/div[4]/div[2]/div[1]/div[1]/div/div/div[2]/div[2]/div/div/span[2]"),
				"上传成功");

		Assert.assertEquals(flag, true);

	}

	// 103 上传视频 视频上传过程中，确认关闭 弹层能否成功关闭 v1.2.0 Y
	@Test
	public void b2UploadingConfirmClose() throws InterruptedException {
		WebElement eVideo = mywb.findElement(
				By.xpath(".//*[@id='app']/div/div/div[2]/div[2]/div[2]/div/div[4]/div[2]/div[1]/div[1]/a/form/input")); // 视频内容
		eVideo.clear();
		String s = new File(".").getAbsolutePath() + "/video/3.8MB480x720.mp4";
		eVideo.sendKeys(s);

		// 点击X摁钮
		WebElement eX = mywb.findElement(By.xpath(
				".//*[@id='app']/div/div/div[2]/div[2]/div[2]/div/div[4]/div[2]/div/div[1]/div/div/i[3]/*[name()='svg']"));
		eX.click();

		Thread.sleep(10);

		// 点击确认按钮
		WebElement eConfirm = mywb
				.findElement(By.xpath(".//*[@id='app']/div/div/div[2]/div[3]/div/div/div/div/div[3]/a[1]"));
		eConfirm.click();
		Thread.sleep(10);

		// 弹层的style属性 hidden
		WebElement eFrame = mywb
				.findElement(By.xpath(".//*[@id='app']/div/div/div[2]/div[2]/div[2]/div/div[4]/div[2]/div/div[1]/div"));
		String sFrame = eFrame.getAttribute("style");

		// 判断弹层 hidden
		Assert.assertEquals(sFrame.contains("hidden"), true);

	}

	// 104 上传视频 取消/关闭上传后，重新上传 能否重新上传视频 v1.2.0 Y
	@Test
	public void b3UploadAgain() throws InterruptedException {
		WebElement eVideo = mywb.findElement(
				By.xpath(".//*[@id='app']/div/div/div[2]/div[2]/div[2]/div/div[4]/div[2]/div[1]/div[1]/a/form/input")); // 视频内容
		eVideo.clear();
		String s = new File(".").getAbsolutePath() + "/video/1MB480X360.mp4";
		eVideo.sendKeys(s);

		// 点击X摁钮
		WebElement eX = mywb.findElement(By.xpath(
				".//*[@id='app']/div/div/div[2]/div[2]/div[2]/div/div[4]/div[2]/div/div[1]/div/div/i[3]/*[name()='svg']"));
		eX.click();

		Thread.sleep(10);

		// 点击确认按钮
		WebElement eConfirm = mywb
				.findElement(By.xpath(".//*[@id='app']/div/div/div[2]/div[3]/div/div/div/div/div[3]/a[1]"));
		eConfirm.click();
		Thread.sleep(100);

		// 重新上传
		eVideo.sendKeys(s);

		// 等待上传完成
		boolean flag = wait.WaiteTextPresent(By.xpath(
				".//*[@id='app']/div/div/div[2]/div[2]/div[2]/div/div[4]/div[2]/div[1]/div[1]/div/div/div[2]/div[2]/div/div/span[2]"),
				"上传成功");

		Assert.assertEquals(flag, true);

	}

	// 106 上传视频 上传完毕后，滑动鼠标滚轴 能否滑动背景，但不影响上传 v1.2.0 Y
	@Test
	public void b4UploadedScroll() throws InterruptedException {
		WebElement eVideo = mywb.findElement(
				By.xpath(".//*[@id='app']/div/div/div[2]/div[2]/div[2]/div/div[4]/div[2]/div[1]/div[1]/a/form/input")); // 视频内容
		eVideo.clear();
		String s = new File(".").getAbsolutePath() + "/video/1MB480X360.mp4";
		eVideo.sendKeys(s);
		// 等待上传完成
		wait.WaiteTextPresent(By.xpath(
				".//*[@id='app']/div/div/div[2]/div[2]/div[2]/div/div[4]/div[2]/div[1]/div[1]/div/div/div[2]/div[2]/div/div/span[2]"),
				"上传成功");

		// 滚动滚轴
		JavascriptExecutor js = (JavascriptExecutor) wb;
		Thread.sleep(100);
		js.executeScript("window.scrollBy(0,1000)");
		Thread.sleep(100);
		js.executeScript("window.scrollBy(0,-1000)");

		// 完成摁钮
		WebElement eFinish = mywb.findElement(By.xpath(
				".//*[@id='app']/div/div/div[2]/div[2]/div[2]/div/div[4]/div[2]/div/div[1]/div/div/div[2]/div[5]/a"));
		eFinish.click();
		Thread.sleep(100);

		// 判断显示图片框是否还显示
		boolean flag = true;
		try {
			wb.findElement(By.xpath(".//*[@id='app']/div/div/div[2]/div[2]/div[2]/div/div[4]/div[@class='fl mrr10']"));
		} catch (Exception ex) {
			flag = false;
		}
		Assert.assertEquals(flag, true);

	}

	// 107 上传视频 上传完毕后，滑点击上传弹层外的其他区域 能否不影响视频上传 v1.2.0 Y
	@Test
	public void b5UploadingClickOutside() throws InterruptedException {
		WebElement eVideo = mywb.findElement(
				By.xpath(".//*[@id='app']/div/div/div[2]/div[2]/div[2]/div/div[4]/div[2]/div[1]/div[1]/a/form/input")); // 视频内容
		eVideo.clear();
		String s = new File(".").getAbsolutePath() + "/video/1MB480X360.mp4";
		eVideo.sendKeys(s);
		// 等待上传完成
		wait.WaiteTextPresent(By.xpath(
				".//*[@id='app']/div/div/div[2]/div[2]/div[2]/div/div[4]/div[2]/div[1]/div[1]/div/div/div[2]/div[2]/div/div/span[2]"),
				"上传成功");

		// 点击外边
		Actions act = new Actions(wb);
		act.moveByOffset(0, 200);
		act.click();

		// 完成摁钮
		WebElement eFinish = mywb.findElement(By.xpath(
				".//*[@id='app']/div/div/div[2]/div[2]/div[2]/div/div[4]/div[2]/div/div[1]/div/div/div[2]/div[5]/a"));
		eFinish.click();
		Thread.sleep(100);

		// 判断显示图片框是否还显示
		boolean flag = true;
		try {
			wb.findElement(By.xpath(".//*[@id='app']/div/div/div[2]/div[2]/div[2]/div/div[4]/div[@class='fl mrr10']"));
		} catch (Exception ex) {
			flag = false;
		}
		Assert.assertEquals(flag, true);

	}

	// 113 上传视频 上传完毕后，关闭 能否成功弹出确认弹窗 v1.2.0 Y
	@Test
	public void b6UploadedClose() throws InterruptedException {
		WebElement eVideo = mywb.findElement(
				By.xpath(".//*[@id='app']/div/div/div[2]/div[2]/div[2]/div/div[4]/div[2]/div[1]/div[1]/a/form/input")); // 视频内容
		eVideo.clear();
		String s = new File(".").getAbsolutePath() + "/video/1MB480X360.mp4";
		eVideo.sendKeys(s);
		// 等待上传完成
		wait.WaiteTextPresent(By.xpath(
				".//*[@id='app']/div/div/div[2]/div[2]/div[2]/div/div[4]/div[2]/div[1]/div[1]/div/div/div[2]/div[2]/div/div/span[2]"),
				"上传成功");
		// 点击X摁钮
		WebElement eX = mywb.findElement(By.xpath(
				".//*[@id='app']/div/div/div[2]/div[2]/div[2]/div/div[4]/div[2]/div/div[1]/div/div/i[3]/*[name()='svg']"));
		eX.click();
		// 判断弹窗的确定按钮出现
		boolean flag = wait
				.WaitElementPresent(By.xpath(".//*[@id='app']/div/div/div[2]/div[3]/div/div/div/div/div[3]/a[1]"));

		Assert.assertEquals(flag, true);

	}

	// 114 上传视频 上传完毕后，取消关闭 能否完成视频上传 v1.2.0 Y
	@Test
	public void b7UploadedCancelClose() throws InterruptedException {
		WebElement eVideo = mywb.findElement(
				By.xpath(".//*[@id='app']/div/div/div[2]/div[2]/div[2]/div/div[4]/div[2]/div[1]/div[1]/a/form/input")); // 视频内容
		eVideo.clear();
		String s = new File(".").getAbsolutePath() + "/video/1MB480X360.mp4";
		eVideo.sendKeys(s);
		// 等待上传完成
		wait.WaiteTextPresent(By.xpath(
				".//*[@id='app']/div/div/div[2]/div[2]/div[2]/div/div[4]/div[2]/div[1]/div[1]/div/div/div[2]/div[2]/div/div/span[2]"),
				"上传成功");
		// 点击X摁钮
		WebElement eX = mywb.findElement(By.xpath(
				".//*[@id='app']/div/div/div[2]/div[2]/div[2]/div/div[4]/div[2]/div/div[1]/div/div/i[3]/*[name()='svg']"));
		eX.click();
		// 取消按钮
		WebElement eCancel = mywb
				.findElement(By.xpath(".//*[@id='app']/div/div/div[2]/div[3]/div/div/div/div/div[3]/a[2]"));
		eCancel.click();
		Thread.sleep(100);
		// 完成摁钮
		WebElement eFinish = mywb.findElement(By.xpath(
				".//*[@id='app']/div/div/div[2]/div[2]/div[2]/div/div[4]/div[2]/div/div[1]/div/div/div[2]/div[5]/a"));
		eFinish.click();
		Thread.sleep(100);

		// 判断显示图片框是否还显示
		boolean flag = true;
		try {
			wb.findElement(By.xpath(".//*[@id='app']/div/div/div[2]/div[2]/div[2]/div/div[4]/div[@class='fl mrr10']"));
		} catch (Exception ex) {
			flag = false;
		}
		Assert.assertEquals(flag, true);

	}

	// 115 上传视频 上传完毕后，确认关闭 弹层能否成功关闭 v1.2.0 Y
	@Test
	public void b8UploadedCloseConfirm() throws InterruptedException {
		WebElement eVideo = mywb.findElement(
				By.xpath(".//*[@id='app']/div/div/div[2]/div[2]/div[2]/div/div[4]/div[2]/div[1]/div[1]/a/form/input")); // 视频内容
		eVideo.clear();
		String s = new File(".").getAbsolutePath() + "/video/1MB480X360.mp4";
		eVideo.sendKeys(s);
		// 等待上传完成
		wait.WaiteTextPresent(By.xpath(
				".//*[@id='app']/div/div/div[2]/div[2]/div[2]/div/div[4]/div[2]/div[1]/div[1]/div/div/div[2]/div[2]/div/div/span[2]"),
				"上传成功");
		// 点击X摁钮
		WebElement eX = mywb.findElement(By.xpath(
				".//*[@id='app']/div/div/div[2]/div[2]/div[2]/div/div[4]/div[2]/div/div[1]/div/div/i[3]/*[name()='svg']"));
		eX.click();
		// 确认按钮
		WebElement eConfirm = mywb
				.findElement(By.xpath(".//*[@id='app']/div/div/div[2]/div[3]/div/div/div/div/div[3]/a[1]"));
		eConfirm.click();
		Thread.sleep(100);

		// 弹层的style属性 hidden
		WebElement eFrame = mywb
				.findElement(By.xpath(".//*[@id='app']/div/div/div[2]/div[2]/div[2]/div/div[4]/div[2]/div/div[1]/div"));
		String sFrame = eFrame.getAttribute("style");

		// 判断弹层 hidden
		Assert.assertEquals(sFrame.contains("hidden"), true);

	}

	@BeforeMethod
	public void beforeMethod() throws InterruptedException {
		// 以下是跳转到撰写消息的页面
		mywb.get("http://testmcenter.weixinxk.com/admin/pushmessage/edit/index");
		wait.WaiteAllElement(By.xpath(".//*[@id='app']/*"));
		WebElement e = mywb.findElement(By.xpath(".//*[@id='app']/div/div/div[2]/div[2]/div[2]/div[2]/ul/li[1]")); // select
																													// 第一个
		e.click();
		WebElement e2 = mywb.findElement(By.xpath(".//*[@id='app']/div/div/div[2]/div[2]/div[1]/div/div[1]/a[2]")); // write
																													// message

		wait.WaiteAllElement(By.xpath(".//*[@id='app']/*"));
		e2.click();
		Thread.sleep(1000);
		wait.WaiteAllElement(By.xpath(".//*[@id='app']/*"));
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
