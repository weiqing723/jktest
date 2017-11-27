package Operation;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
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
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import Common.ElementVerify;
import Common.MyDriver;
import Common.MyWDWait;
import Common.TestBase;
import Common.login;

public class NewsList extends TestBase {
	login l = null;
	public WebDriver wb = null;
	MyWDWait wait = null;
	MyDriver mywb = null;

	public WebDriver getDriver() {
		return wb;
	}

	@Test(priority = 1)
	// 21 默认 最近推送 能否默认选中，最近推送按钮
	// 判断style的方式看默认格式是否为选中
	public void a1default1() throws InterruptedException {
		wait.WaiteAllElement(By.xpath(".//*[@id='app']/*"));
		WebElement e1 = mywb.findElement(
				By.xpath(".//*[@id='app']/div/div/div[2]/div[2]/div[2]/div[1]/div/div[1]/div/span/*[name()='svg']"));
		Assert.assertEquals(e1.getAttribute("style").toString(), "width: 15px; height: 13px; fill: rgb(46, 199, 93);");
		// style="width: 15px; height: 13px; fill: rgb(46, 199, 93);

	}

	@Test(priority = 1)
	// 23默认 列表默认头像 列表默认头像，是否为相框
	public void a2default2() throws InterruptedException {
		wait.WaiteAllElement(By.xpath(".//*[@id='app']/*"));
		WebElement e1 = mywb
				.findElement(By.xpath("//*[@id='app']/div/div/div[2]/div[2]/div[2]/div[2]/ul/li[1]/div[1]"));
		String stl = e1.getAttribute("style").toString();
		String sub = stl.substring(stl.indexOf("background-image")); //  截取 background-image 开始的字符串
		String sub2 = sub.substring(0, sub.indexOf(";")); // 上边的字符串截取到“；”之前
		Assert.assertEquals(sub2,
				"background-image: url(\"http://res.frogshealth.com/images/detail/89e17eb94900c9f69d30664cadf0f6e819\")");

	}

	@Test(priority = 1)
	// 24 默认 列表默认条数 列表默认条数，是否为8列*5行=40个
	public void a3default3() throws InterruptedException {
		wait.WaiteAllElement(By.xpath(".//*[@id='app']/*"));
		WebElement e1 = mywb.findElement(By.xpath(".//*[@id='app']/div/div/div[2]/div[2]/div[2]/div[3]/a[1]"));
		Assert.assertEquals(e1.getText(), "40");

	}

	@Test(priority = 1)
	// 8 跳转 消息列表页，点击新消息 页面能否成功刷新页面
	// 多次点击元素，若报异常说明正在刷新中,报异常则设置e2为空，判断是否为空
	public void a4NewMsgz_click() throws Exception {
		String clickString = ".//*[@id='app']//p[text()='新消息']";
		// wait.WaiteElementClickable(By.xpath(clickString));
		WebElement e1 = mywb.findElement(By.xpath(clickString));
		ElementVerify eleVerify = new ElementVerify(e1);
		Assert.assertEquals(eleVerify.VerifyClickIfRefresh(), true);
		wait.WaiteElementClickable(By.xpath(clickString));

		System.out.print(2);

	}

	@Test(priority = 2)
	// 导航列表 2登录完成后，查看左侧导航列表样式 左侧导航列表，是否默认选中列表项
	public void a5leftnavi1() throws InterruptedException {
		wait.WaitElementPresent(By.xpath(".//*[@id='app']/div/div/div[2]/div[2]/div[1]/div/div[2]"));
		String e = mywb.findElement(By.xpath(".//*[@id='app']/div/div/div[2]/div[2]/div[1]/div/div[2]")).getText();
		Assert.assertEquals(e, "选择推送对象  ");
		System.out.print(1);

	}

	@Test(priority = 3)
	// 9跳转 消息列表页，点击历史消息 能否成功跳转到历史消息页
	public void a6history_click() throws InterruptedException {
		WebElement e3 = mywb.findElement(By.xpath(".//*[@id='app']//p[text()='历史消息']"));
		e3.click();
		wait.WaiteAllElement(By.xpath(".//*[@id='app']/*"));
		String s = wb.getCurrentUrl();
		Assert.assertEquals(s, "http://testmcenter.weixinxk.com/admin/pushmessage/history/index");

		System.out.println(3);
	}

	@Test(priority = 4)
	// 10 跳转 消息列表页，点击相框 能否成功跳转到相框页面
	public void a7Album_click() throws InterruptedException {
		System.out.println(4);

		// wait.WaitElementPresent(By.xpath("//*[@id='app']/div/div/div[2]/div[1]/div/ul/div[3]//p[text()='相框']"));
		WebElement e1 = mywb.findElement(By.xpath("//*[@id='app']//p[text()='相框']"));
		e1.click();
		wait.WaiteAllElement(By.xpath(".//*[@id='app']/*"));
		String s = wb.getCurrentUrl();
		Assert.assertEquals(s, "http://testmcenter.weixinxk.com/admin/statistic/album/index");
		Thread.sleep(500);

	}

	@Test(priority = 5)
	// 11跳转 消息列表页，点击设备 能否成功跳转到设备页面
	public void a8Device_click() throws InterruptedException {
		// wait.WaitElementPresent(By.xpath(".//*[@id='app']//p[text()='设备']"));
		System.out.print(5);

		WebElement e2 = mywb.findElement(By.xpath(".//*[@id='app']//p[text()='设备']"));
		e2.click();
		wait.WaiteAllElement(By.xpath(".//*[@id='app']/*"));
		String s = wb.getCurrentUrl();
		Assert.assertEquals(s, "http://testmcenter.weixinxk.com/admin/statistic/device/index");
		Thread.sleep(500);

	}

	@Test(priority = 6)
	// 12跳转 消息列表页，点击合作伙伴 能否成功跳转到合作伙伴页面
	public void a9Partner_click() throws InterruptedException {

		WebElement e1 = mywb.findElement(By.xpath(".//*[@id='app']//p[text()='合作伙伴']"));
		e1.click();
		wait.WaiteAllElement(By.xpath(".//*[@id='app']/*"));
		String s = wb.getCurrentUrl();
		Assert.assertEquals(s, "http://testmcenter.weixinxk.com/admin/statistic/partner/index");

		System.out.print(6);
		Thread.sleep(500);
	}

	@Test(priority = 7)
	// 17 标题 选择推送对象 标题能否显示正确
	public void b1pushObj() throws InterruptedException {
		mywb.get("http://testmcenter.weixinxk.com/admin/pushmessage/edit/index");
		wait.WaitElementPresent(By.xpath(".//*[@id='app']/div/div/div[2]/div[2]/div[1]/div/div[2]"));
		Thread.sleep(500);
		String e = wb.findElement(By.xpath(".//*[@id='app']/div/div/div[2]/div[2]/div[1]/div/div[2]")).getText();
		Assert.assertEquals(e, "选择推送对象  ");
		System.out.print(1);
	}

	@Test(priority = 7)
	// 18 标题 选中某项 推送对象人数，能否显示正确
	public void b2pushObjNums() throws InterruptedException {

		// wb.navigate().to("http://testmcenter.weixinxk.com/admin/pushmessage/edit/index");
		wait.WaiteAllElement(By.xpath(".//*[@id='app']/*"));
		WebElement e = mywb.findElement(By.xpath(".//*[@id='app']/div/div/div[2]/div[2]/div[2]/div[2]/ul/li[1]"));
		e.click();
		wait.WaitElementPresent(By.xpath(".//*[@id='app']/div/div/div[2]/div[2]/div[1]/div/div[2]/span/i"));
		String i = mywb.findElement(By.xpath(".//*[@id='app']/div/div/div[2]/div[2]/div[1]/div/div[2]/span/i"))
				.getText();
		Assert.assertEquals(i, "1");
		System.out.print(1);
		Thread.sleep(500);
	}

	@Test(priority = 8) // 19 标题 全选 推送对象人数，能否显示正确 v1.2.0
	// 25 按钮状态 全选按钮 无选中列表项，能否全选
	public void b3apushObjAll() throws InterruptedException {

		// wb.navigate().to("http://testmcenter.weixinxk.com/admin/pushmessage/edit/index");
		wait.WaiteAllElement(By.xpath(".//*[@id='app']/*"));
		WebElement e = mywb.findElement(By.xpath(".//*[@id='app']/div/div/div[2]/div[2]/div[1]/div/div[1]/a[1]"));
		Thread.sleep(500);
		e.click();

		wait.WaiteAllElement(By.xpath(".//*[@id='app']/*"));
		String i = mywb.findElement(By.xpath(".//*[@id='app']/div/div/div[2]/div[2]/div[1]/div/div[2]/span/i"))
				.getText();
		Assert.assertEquals(i, "40");
		System.out.print(19);

	}

	@Test(priority = 8) // 19 标题 全选 推送对象人数，能否显示正确 v1.2.0
	public void b4WriteSelectAll() throws InterruptedException {

		wb.navigate().to("http://testmcenter.weixinxk.com/admin/pushmessage/edit/index");
		wait.WaiteAllElement(By.xpath(".//*[@id='app']/*"));
		WebElement e = mywb.findElement(By.xpath(".//*[@id='app']/div/div/div[2]/div[2]/div[1]/div/div[1]/a[1]")); // select
																													// all
		Thread.sleep(1000);
		e.click();
		WebElement e2 = mywb.findElement(By.xpath("//*[@id='app']/div/div/div[2]/div[2]/div[1]/div/div[1]/a[2]")); // write
																													// message

		wait.WaiteAllElement(By.xpath(".//*[@id='app']/*"));
		e2.click();
		wait.WaiteAllElement(By.xpath(".//*[@id='app']/*"));
		String i = wb.getCurrentUrl();
		Assert.assertEquals(i, "http://testmcenter.weixinxk.com/admin/pushmessage/edit/add/");
		System.out.print(19);

	}

	@Test(priority = 8) // 20 标题 取消全选 推送对象人数，能否显示正确
	// 29 按钮状态 取消全选功能 全选后，能否成功取消全选
	public void b5pushObjCancelAll() throws InterruptedException {

		wb.navigate().to("http://testmcenter.weixinxk.com/admin/pushmessage/edit/index");
		wait.WaiteAllElement(By.xpath(".//*[@id='app']/*"));
		WebElement e = mywb.findElement(By.xpath(".//*[@id='app']/div/div/div[2]/div[2]/div[1]/div/div[1]/a[1]"));
		Thread.sleep(1500);
		e.click();
		wait.WaiteElementClickable(e);
		e.click();
		boolean flag = true;
		try {
			// wait.WaitElementPresent(By.xpath(".//*[@id='app']/div/div/div[2]/div[2]/div[1]/div/div[2]/span/i"));

			WebElement ele = wb.findElement(By.xpath(".//*[@id='app']/div/div/div[2]/div[2]/div[1]/div/div[2]/span/i"));

			if (ele.isDisplayed()) {
				flag = false;
				// System.out.println(ele);
			}
		} catch (StaleElementReferenceException e1) {
			System.out.println(e1);
		} catch (NoSuchElementException e2) {
			System.out.println(e2);
		}

		Assert.assertEquals(flag, true);
		wait.WaiteAllElement(By.xpath(".//*[@id='app']/*"));
		System.out.print(20);

	}

	@Test(priority = 8) // 26 按钮状态 全选按钮 选中某列表项，能否全选
	public void b6seleckallAfterSelectone() throws InterruptedException {

		wb.navigate().to("http://testmcenter.weixinxk.com/admin/pushmessage/edit/index");
		Thread.sleep(1000);
		wait.WaiteAllElement(By.xpath("html"));

		// 选中第一个相框 .//*[@id='app']/div/div/div[2]/div[2]/div[2]/div[2]/ul/li[1]/div[1]
		WebElement e1 = mywb
				.findElement(By.xpath(".//*[@id='app']/div/div/div[2]/div[2]/div[2]/div[2]/ul/li[1]/div[1]"));
		e1.click();

		wait.WaiteElementClickable(By.xpath(".//*[@id='app']/div/div/div[2]/div[2]/div[1]/div/div[1]/a[1]"));
		// 选中 全选
		WebElement e = mywb.findElement(By.xpath(".//*[@id='app']/div/div/div[2]/div[2]/div[1]/div/div[1]/a[1]"));
		e.click();
		wait.WaiteAllElement(By.xpath(".//*[@id='app']/*"));
		String i = mywb.findElement(By.xpath(".//*[@id='app']/div/div/div[2]/div[2]/div[1]/div/div[2]/span/i"))
				.getText();
		Assert.assertEquals(i, "40");
		System.out.print(26);

	}

	@Test(priority = 9) // 28 按钮状态 取消全选样式 全选后，按钮上文案能否变更能否显示正确
	public void b7textOfCancelAll() throws InterruptedException {

		wb.navigate().to("http://testmcenter.weixinxk.com/admin/pushmessage/edit/index");
		wait.WaiteAllElement(By.xpath(".//*[@id='app']/*"));
		WebElement e = mywb.findElement(By.xpath(".//*[@id='app']/div/div/div[2]/div[2]/div[1]/div/div[1]/a[1]"));
		Thread.sleep(1500);
		e.click();
		wait.WaitElementPresent(By.xpath(".//*[@id='app']/div/div/div[2]/div[2]/div[1]/div/div[1]/a[1]"));

		Assert.assertEquals(e.getText(), "取消全选");
		wait.WaiteAllElement(By.xpath(".//*[@id='app']/*"));
		System.out.print(28);

	}

	@Test(priority = 9) // 30 按钮状态 取消全选功能 取消全选后，不选择，撰写消息
	public void b8WhiteAfterCancelAll() throws InterruptedException {

		wb.navigate().to("http://testmcenter.weixinxk.com/admin/pushmessage/edit/index");
		wait.WaiteAllElement(By.xpath(".//*[@id='app']/*"));
		WebElement e = mywb.findElement(By.xpath(".//*[@id='app']/div/div/div[2]/div[2]/div[1]/div/div[1]/a[1]"));
		Thread.sleep(1500);
		e.click();
		wait.WaiteElementClickable(e);
		e.click();
		wait.WaitElementPresent(By.xpath(".//*[@id='app']/div/div/div[2]/div[2]/div[1]/div/div[1]/a[2]"));
		WebElement e2 = mywb.findElement(By.xpath(".//*[@id='app']/div/div/div[2]/div[2]/div[1]/div/div[1]/a[2]"));

		Assert.assertEquals(e2.getAttribute("class"), "btn-disabled-primary");
		wait.WaiteAllElement(By.xpath(".//*[@id='app']/*"));
		System.out.print(28);

	}

	@Test(priority = 9) // 31 按钮状态 取消全选功能 取消全选后，再次选择某项，并撰写消息 v1.2.0
	// 36 按钮状态 撰写消息按钮 取消全选后，再次选择某项，并撰写消息
	// 79 撰写消息按钮 撰写消息 取消全选后，再次选择某项，并撰写消息
	public void b9WhiteSelectAllAgain() throws InterruptedException {

		wb.navigate().to("http://testmcenter.weixinxk.com/admin/pushmessage/edit/index");
		wait.WaiteAllElement(By.xpath(".//*[@id='app']/*"));
		WebElement e = mywb.findElement(By.xpath(".//*[@id='app']/div/div/div[2]/div[2]/div[1]/div/div[1]/a[1]")); // 全选
		Thread.sleep(1500);
		e.click();
		wait.WaiteElementClickable(e);
		e.click();

		WebElement e1 = mywb
				.findElement(By.xpath(".//*[@id='app']/div/div/div[2]/div[2]/div[2]/div[2]/ul/li[1]/div[1]")); // 选中第一个相框
		wait.WaiteElementClickable(e1);
		e1.click();
		WebElement e2 = mywb.findElement(By.xpath(".//*[@id='app']/div/div/div[2]/div[2]/div[1]/div/div[1]/a[2]"));// 撰写消息

		wait.WaiteElementClickable(e2);
		e2.click();
		wait.WaiteAllElement(By.xpath(".//*[@id='app']/*"));
		String i = wb.getCurrentUrl();
		Assert.assertEquals(i, "http://testmcenter.weixinxk.com/admin/pushmessage/edit/add/");
		System.out.print(31);

	}

	@Test(priority = 9) // 33 按钮状态 撰写消息按钮 能否隔页选中，并撰写消息
	// 76 撰写消息按钮 撰写消息 能否隔页选中，并撰写消息 v1.2.0
	public void c1WhiteSelectTwoPages() throws InterruptedException {

		wb.navigate().to("http://testmcenter.weixinxk.com/admin/pushmessage/edit/index");
		wait.WaiteAllElement(By.xpath(".//*[@id='app']/*"));
		Thread.sleep(1500);
		WebElement e1 = mywb
				.findElement(By.xpath(".//*[@id='app']/div/div/div[2]/div[2]/div[2]/div[2]/ul/li[1]/div[1]")); // 选中第一页第一个相框
		wait.WaiteElementClickable(e1);
		e1.click();

		WebElement e2 = mywb.findElement(By.xpath(".//*[text()='下一页']"));// 下一页

		wait.WaiteElementClickable(e2);
		e2.click();
		wait.WaiteAllElement(By.xpath(".//*[@id='app']/*"));
		wait.WaiteElementClickable(e1);
		e1.click(); // 点击第二页的第一个

		wait.WaiteElementClickable(By.xpath(".//*[@id='app']/div/div/div[2]/div[2]/div[1]/div/div[1]/a[2]"));
		WebElement e3 = mywb.findElement(By.xpath(".//*[@id='app']/div/div/div[2]/div[2]/div[1]/div/div[1]/a[2]"));// 撰写消息

		wait.WaiteElementClickable(e3);
		e3.click();
		wait.WaiteAllElement(By.xpath(".//*[@id='app']/*"));
		String i = wb.getCurrentUrl();
		Assert.assertEquals(i, "http://testmcenter.weixinxk.com/admin/pushmessage/edit/add/");
		System.out.print(33);

	}

	@Test(priority = 9) // 34 按钮状态 撰写消息按钮 能否选中最后一项，并撰写消息
	// 77 撰写消息按钮 撰写消息 能否选中最后一项，并撰写消息
	public void c2WhiteSelectTwoPages() throws InterruptedException {

		wb.navigate().to("http://testmcenter.weixinxk.com/admin/pushmessage/edit/index");
		wait.WaiteAllElement(By.xpath(".//*[@id='app']/*"));
		Thread.sleep(1500);
		// List<WebElement>
		// ePage=wb.findElements(By.xpath(".//*[@id='app']/div/div/div[2]/div[2]/div[3]/div/ul/li[@class='page-num-box']"));
		// //最后一页
		// System.out.println(ePage.size());
		// WebElement[] es= new WebElement [ePage.size()];
		// es[ePage.size()-1].click();
		WebElement ePage = mywb.findElement(
				By.xpath(".//*[@id='app']/div/div/div[2]/div[2]/div[3]/div/ul/li[@class='page-num-box'][last()]")); // 最后一页
		ePage.click();
		Thread.sleep(1000);
		wait.WaiteAllElement(By.xpath(".//*[@id='app']/*"));
		wait.WaiteElementClickable(
				By.xpath(".//*[@id='app']/div/div/div[2]/div[2]/div[2]/div[2]/ul/li[last()]/div[1]"));
		WebElement eLast = mywb
				.findElement(By.xpath(".//*[@id='app']/div/div/div[2]/div[2]/div[2]/div[2]/ul/li[last()]/div[1]")); // 最后一个相框
		eLast.click();
		Thread.sleep(1000);
		wait.WaiteElementClickable(By.xpath(".//*[@id='app']/div/div/div[2]/div[2]/div[1]/div/div[1]/a[2]"));
		WebElement e3 = mywb.findElement(By.xpath(".//*[@id='app']/div/div/div[2]/div[2]/div[1]/div/div[1]/a[2]"));// 撰写消息

		e3.click();
		wait.WaiteAllElement(By.xpath(".//*[@id='app']/*"));
		String i = wb.getCurrentUrl();
		Assert.assertEquals(i, "http://testmcenter.weixinxk.com/admin/pushmessage/edit/add/");
		System.out.print(34);

	}

	@Test(priority = 9) // 35 按钮状态 撰写消息按钮 能否全选后，撰写消息
	// 27 按钮状态 全选按钮 全选后，撰写消息
	// 78 撰写消息按钮 撰写消息 能否全选后，撰写消息
	public void c3WhiteSelectAll() throws InterruptedException {

		wb.navigate().to("http://testmcenter.weixinxk.com/admin/pushmessage/edit/index");
		wait.WaiteAllElement(By.xpath(".//*[@id='app']/*"));
		WebElement e = wb.findElement(By.xpath(".//*[@id='app']/div/div/div[2]/div[2]/div[1]/div/div[1]/a[1]")); // 全选
		Thread.sleep(1500);
		e.click();
		wait.WaiteElementClickable(By.xpath(".//*[@id='app']/div/div/div[2]/div[2]/div[1]/div/div[1]/a[2]"));

		WebElement e2 = wb.findElement(By.xpath(".//*[@id='app']/div/div/div[2]/div[2]/div[1]/div/div[1]/a[2]"));// 撰写消息
		e2.click();
		wait.WaiteAllElement(By.xpath(".//*[@id='app']/*"));
		String i = wb.getCurrentUrl();
		Assert.assertEquals(i, "http://testmcenter.weixinxk.com/admin/pushmessage/edit/add/");
		System.out.print(31);

	}

	@Test(priority = 9)
	// 37 最近推送 取消选中 已选中状态，能否取消
	public void c4RecentPushCancel() throws InterruptedException {
		wb.navigate().to("http://testmcenter.weixinxk.com/admin/pushmessage/edit/index");
		wait.WaiteAllElement(By.xpath(".//*[@id='app']/*"));

		Thread.sleep(1500);
		WebElement e1 = mywb.findElement(
				By.xpath(".//*[@id='app']/div/div/div[2]/div[2]/div[2]/div[1]/div/div[1]/div/span/*[name()='svg']"));
		e1.click();
		wait.WaiteElementClickable(e1);
		Assert.assertEquals(e1.getAttribute("style").toString(),
				"width: 15px; height: 13px; fill: rgb(153, 153, 153);");
		// style="width: 15px; height: 13px; fill: rgb(46, 199, 93);

	}

	// @Test (priority=9)
	// //48 地区筛选 删除已选地区 选择框能否恢复初始态，并收回
	// public void hRegionDelete() throws InterruptedException
	// {
	// wb.navigate().to("http://testmcenter.weixinxk.com/admin/pushmessage/edit/index");
	// wait.WaiteAllElement(By.xpath(".//*[@id='app']/*"));
	//
	// Thread.sleep(1500);
	// WebElement
	// e1=wb.findElement(By.xpath(".//*[@id='app']/div/div/div[2]/div[2]/div[2]/div[1]/div/div[2]/div"));
	// //地区
	// e1.click();
	// wait.WaiteElementClickable(By.xpath(".//*[@id='app']/div/div/div[2]/div[2]/div[2]/div[4]/div/div[2]/ul/li[1]"));
	// //北京
	// WebElement
	// e2=wb.findElement(By.xpath(".//*[@id='app']/div/div/div[2]/div[2]/div[2]/div[4]/div/div[2]/ul/li[1]"));
	// e2.click();
	// wait.WaiteElementClickable(By.xpath(".//*[@id='app']/div/div/div[2]/div[2]/div[2]/div[4]/div/div[1]/span/a/*[name()='svg']"));
	// //选中的北京的x
	// WebElement
	// e3=wb.findElement(By.xpath(".//*[@id='app']/div/div/div[2]/div[2]/div[2]/div[4]/div/div[1]/span/a/*[name()='svg']"));
	// e3.click();
	// wait.WaiteElementClickable(By.xpath(".//*[@id='app']/*")); //选中的北京的x
	// WebElement
	// e4=wb.findElement(By.xpath(".//*[@id='app']/div/div/div[2]/div[2]/div[2]/div[1]/div/div[2]/div/span/*[name()='svg']"));
	// //地区
	//
	//
	// Assert.assertEquals(e4.getAttribute("style").toString(),"width: 15px; height:
	// 13px; fill: rgb(153, 153, 153);");
	// // style="width: 15px; height: 13px; fill: rgb(46, 199, 93);
	//
	// }
	//

	// @Test (priority=9)
	// 58 合作伙伴筛选 删除已选合作伙伴 选择框能否恢复初始态，并收回
	// public void iPartnerDelete() throws InterruptedException
	// {
	// wb.navigate().to("http://testmcenter.weixinxk.com/admin/pushmessage/edit/index");
	// wait.WaiteAllElement(By.xpath(".//*[@id='app']/*"));
	//
	// Thread.sleep(1500);
	// WebElement
	// e1=wb.findElement(By.xpath(".//*[@id='app']/div/div/div[2]/div[2]/div[2]/div[1]/div/div[3]/div"));
	// //合作伙伴
	// e1.click();
	// wait.WaiteElementClickable(By.xpath(".//*[@id='app']/div/div/div[2]/div[2]/div[2]/div[5]/div/div[2]/ul/li[1]"));
	// //第一个伙伴
	// WebElement
	// e2=wb.findElement(By.xpath(".//*[@id='app']/div/div/div[2]/div[2]/div[2]/div[5]/div/div[2]/ul/li[1]"));
	// e2.click();
	// wait.WaiteElementClickable(By.xpath(".//*[@id='app']/div/div/div[2]/div[2]/div[2]/div[5]/div/div[1]/span/a/*[name()='svg']"));
	// //选中的北京的x
	// WebElement
	// e3=wb.findElement(By.xpath(".//*[@id='app']/div/div/div[2]/div[2]/div[2]/div[5]/div/div[1]/span/a/*[name()='svg']"));
	// e3.click();
	// wait.WaiteElementClickable(By.xpath(".//*[@id='app']/*")); //选中的北京的x
	// WebElement
	// e4=wb.findElement(By.xpath(".//*[@id='app']/div/div/div[2]/div[2]/div[2]/div[1]/div/div[3]/div/span/*[name()='svg']"));
	// //地区
	//
	//
	// Assert.assertEquals(e4.getAttribute("style").toString(),"width: 15px; height:
	// 13px; fill: rgb(153, 153, 153);");
	// // style="width: 15px; height: 13px; fill: rgb(46, 199, 93);
	//
	// }
	////
	// @Test (priority=9)
	//// 60 年龄筛选 获得鼠标焦点 能否成功显示，年龄筛选框
	// public void jAgeFrameDispaly() throws InterruptedException
	// {
	// wb.navigate().to("http://testmcenter.weixinxk.com/admin/pushmessage/edit/index");
	// wait.WaiteAllElement(By.xpath(".//*[@id='app']/*"));
	//
	// Thread.sleep(1500);
	// boolean flag= false;
	// Actions mouse=new Actions(wb);
	// WebElement e1=
	// wb.findElement(By.xpath(".//*[@id='app']/div/div/div[2]/div[2]/div[2]/div[1]/div/div[4]/div"));
	// mouse.moveToElement(e1).perform();
	// Thread.sleep(1000);
	// WebElement e2=
	// wb.findElement(By.xpath(".//*[@id='app']/div/div/div[2]/div[2]/div[2]/div[1]/div/div[4]/div/a"));
	// if (e2.isDisplayed())
	// {
	// flag=true;
	// }
	// Assert.assertEquals(flag, true);
	// }
	////
	// @Test (priority=9)
	//// 61 年龄筛选 失去鼠标焦点 是否成功收起，年龄筛选框
	// public void kAgeFrameNotDisplay() throws InterruptedException
	// {
	// wb.navigate().to("http://testmcenter.weixinxk.com/admin/pushmessage/edit/index");
	// wait.WaiteAllElement(By.xpath(".//*[@id='app']/*"));
	//
	// Thread.sleep(1500);
	// boolean flag= false;
	// Actions mouse=new Actions(wb);
	// WebElement e1=
	// wb.findElement(By.xpath(".//*[@id='app']/div/div/div[2]/div[2]/div[2]/div[1]/div/div[4]/div"));
	// mouse.moveToElement(e1).perform();
	// Thread.sleep(1000);
	// WebElement e2=
	// wb.findElement(By.xpath(".//*[@id='app']/div/div/div[2]/div[2]/div[2]/div[1]/div/div[4]/div/a"));
	// mouse.moveToElement(e1, 300, 300).perform();
	// Thread.sleep(1000);
	// if (e2.isDisplayed())
	// {
	// flag=true;
	// }
	// Assert.assertEquals(flag, false);
	// }
	// // @Test (priority=9)
	// 62 年龄筛选 选择年龄 能否收回选择框，且输入框中的值已更新
	// public void lAgeFrameInput() throws InterruptedException
	// {
	// wb.navigate().to("http://testmcenter.weixinxk.com/admin/pushmessage/edit/index");
	// wait.WaiteAllElement(By.xpath(".//*[@id='app']/*"));
	//
	// Thread.sleep(1500);
	// boolean flag= false;
	// Actions mouse=new Actions(wb);
	// WebElement e1=
	// wb.findElement(By.xpath(".//*[@id='app']/div/div/div[2]/div[2]/div[2]/div[1]/div/div[4]/div/input[1]"));
	// //输入框1
	// WebElement e2=
	// wb.findElement(By.xpath(".//*[@id='app']/div/div/div[2]/div[2]/div[2]/div[1]/div/div[4]/div/input[2]"));
	// //输入框2
	//
	// e1.sendKeys("1");
	// e2.sendKeys("150");
	// mouse.moveToElement(e1).perform(); //鼠标悬停
	// Thread.sleep(1000);
	// WebElement e3=
	// wb.findElement(By.xpath(".//*[@id='app']/div/div/div[2]/div[2]/div[2]/div[1]/div/div[4]/div/a"));
	// //确定键
	// e3.click();
	// mouse.moveToElement(e1, 300,300).perform(); //鼠标离开
	//// Thread.sleep(1000);
	//// System.out.println(e3.isDisplayed());
	//// System.out.println(e1.getAttribute("value").toString());
	//// System.out.println(e2.getAttribute("value").toString());
	// if
	// ((!e3.isDisplayed())&&(e1.getAttribute("value").equals("1"))&&(e2.getAttribute("value").equals("150")))
	// {
	// flag=true;
	// }
	// Assert.assertEquals(flag, true);
	// }
	//
	// @Test (priority=9)
	// //63 年龄筛选 重新输入年龄 能否收回选择框，且按钮上文案已更新
	// 65 年龄筛选 置空后重新选择 取消选择后，能否重新选择年龄
	// public void mAgeFrameInputChange() throws InterruptedException
	// {
	// wb.navigate().to("http://testmcenter.weixinxk.com/admin/pushmessage/edit/index");
	// wait.WaiteAllElement(By.xpath(".//*[@id='app']/*"));
	//
	// Thread.sleep(1500);
	// boolean flag= false;
	// Actions mouse=new Actions(wb);
	// WebElement e1=
	// wb.findElement(By.xpath(".//*[@id='app']/div/div/div[2]/div[2]/div[2]/div[1]/div/div[4]/div/input[1]"));
	// //输入框1
	// WebElement e2=
	// wb.findElement(By.xpath(".//*[@id='app']/div/div/div[2]/div[2]/div[2]/div[1]/div/div[4]/div/input[2]"));
	// //输入框2
	//
	// e1.sendKeys("1");
	// e2.sendKeys("150");
	// mouse.moveToElement(e1).perform();
	// Thread.sleep(1000);
	// WebElement e3=
	// wb.findElement(By.xpath(".//*[@id='app']/div/div/div[2]/div[2]/div[2]/div[1]/div/div[4]/div/a"));
	// //确定键
	// e3.click();
	// mouse.moveToElement(e1, 300,300).perform(); //鼠标离开
	// Thread.sleep(1000);
	// e1.clear();
	// e1.sendKeys("2");
	// e2.clear();
	// e2.sendKeys("149");
	// mouse.moveToElement(e1).perform();
	// Thread.sleep(1000);
	// e3.click();
	// System.out.println(e3.isDisplayed());
	// mouse.moveToElement(e1, 300,300).perform();
	// System.out.println(e1.getAttribute("value").toString());
	// System.out.println(e2.getAttribute("value").toString());
	// if
	// ((!e3.isDisplayed())&&(e1.getAttribute("value").equals("2"))&&(e2.getAttribute("value").equals("149")))
	// {
	// flag=true;
	// }
	// Assert.assertEquals(flag, true);
	// }
	//
	// @Test (priority=9)
	// 64 年龄筛选 置空已选年龄 选择框能否恢复初始态，并收回
	// public void nAgeFrameInputClear() throws InterruptedException
	// {
	// wb.navigate().to("http://testmcenter.weixinxk.com/admin/pushmessage/edit/index");
	// wait.WaiteAllElement(By.xpath(".//*[@id='app']/*"));
	//
	// Thread.sleep(1500);
	// boolean flag= false;
	// Actions mouse=new Actions(wb);
	// WebElement e1=
	// wb.findElement(By.xpath(".//*[@id='app']/div/div/div[2]/div[2]/div[2]/div[1]/div/div[4]/div/input[1]"));
	// //输入框1
	// WebElement e2=
	// wb.findElement(By.xpath(".//*[@id='app']/div/div/div[2]/div[2]/div[2]/div[1]/div/div[4]/div/input[2]"));
	// //输入框2
	//
	// e1.sendKeys("1");
	// e2.sendKeys("150");
	// mouse.moveToElement(e1).perform();
	// Thread.sleep(1000);
	// WebElement e3=
	// wb.findElement(By.xpath(".//*[@id='app']/div/div/div[2]/div[2]/div[2]/div[1]/div/div[4]/div/a"));
	// //确定键
	// e3.click();
	// mouse.moveToElement(e1, 300,300).perform(); //鼠标离开
	// Thread.sleep(1000);
	// e1.clear();
	//
	// e2.clear();
	//
	// mouse.moveToElement(e1).perform();
	// Thread.sleep(1000);
	// e3.click();
	// System.out.println(e3.isDisplayed());
	// mouse.moveToElement(e1, 300,300).perform();
	// if
	// ((!e3.isDisplayed())&&(e1.getAttribute("value").equals(""))&&(e2.getAttribute("value").equals("")))
	// {
	// flag=true;
	// }
	// Assert.assertEquals(flag, true);
	// }

	// 85 翻页 在第1页，点击页码1 是否屏蔽当前页码
	@Test(priority = 10)
	public void c6Page1Disabled() throws InterruptedException {
		wb.navigate().to("http://testmcenter.weixinxk.com/admin/pushmessage/edit/index");
		wait.WaiteAllElement(By.xpath(".//*[@id='app']/*"));
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		WebElement ePage = mywb.findElement(
				By.xpath(".//*[@id='app']/div/div/div[2]/div[2]/div[3]/div/ul/li[@class='page-num-box'][1]")); // 第一页

		ElementVerify eVerify = new ElementVerify(ePage);
		Assert.assertEquals(eVerify.VerifyClickIfRefresh(), false);
	}

	@Test(priority = 10)
	// 86 翻页 在最后一页，点击当前页码 是否屏蔽当前页码
	public void c7PageLastDisabled() throws InterruptedException {
		wb.navigate().to("http://testmcenter.weixinxk.com/admin/pushmessage/edit/index");
		wait.WaiteAllElement(By.xpath(".//*[@id='app']/*"));
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		WebElement ePage = mywb.findElement(
				By.xpath(".//*[@id='app']/div/div/div[2]/div[2]/div[3]/div/ul/li[@class='page-num-box'][last()]")); // 最后一页

		ElementVerify eVerify = new ElementVerify(ePage);
		Assert.assertEquals(eVerify.VerifyClickIfRefresh(), false);
	}

	@Test(priority = 10)
	// 88 翻页 点击下一页 下一页按钮能否可用
	public void c8PageNext() throws InterruptedException {
		wb.navigate().to("http://testmcenter.weixinxk.com/admin/pushmessage/edit/index");
		wait.WaiteAllElement(By.xpath(".//*[@id='app']/*"));
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		WebElement ePage = wb
				.findElement(By.xpath(".//*[@id='app']/div/div/div[2]/div[2]/div[3]/div/ul/li[@class='next-btn']/a")); // 下一页
		System.out.println(ePage.toString());

		ePage.click(); // 首页中点击下一页后 上一页会出现
		wait.WaitElementPresent(
				By.xpath(".//*[@id='app']/div/div/div[2]/div[2]/div[3]/div/ul/li[@class='next-btn']/a"));
		WebElement ePage2 = mywb.findElement(
				By.xpath(".//*[@id='app']/div/div/div[2]/div[2]/div[3]/div/ul/li[@class='previous-btn']/a")); // 上一页
		Assert.assertEquals(ePage2.isDisplayed(), true);
		Thread.sleep(1000);
	}

	@Test(priority = 10)
	// 89 翻页 点击上一页 上一页按钮能否可用
	public void c9LastPage() throws InterruptedException {
		WebElement ePage = mywb.findElement(
				By.xpath(".//*[@id='app']/div/div/div[2]/div[2]/div[3]/div/ul/li[@class='previous-btn']/a")); // 上一页
		ePage.click(); // 首页中点击下一页后 上一页会出现
		wait.WaiteAllElement(By.xpath(".//*[@id='app']/*"));
		Assert.assertEquals(ePage.isDisplayed(), false); // 点击上一页后 回到第一页 上一页不再显示

	}

	@Test(priority = 99)
	public void zlogout() throws InterruptedException {
		wait.WaitElementPresent(By.className("header-exit"));
		WebElement e1 = mywb.findElement(By.className("header-exit"));
		e1.click();
		wait.WaitURLContains("logon");
		String s = wb.getCurrentUrl();
		Assert.assertEquals(s, "http://testmcenter.weixinxk.com/admin/logon");
		System.out.print(7);
	}

	@BeforeMethod
	public void beforeMethod() {
	}

	@AfterMethod
	public void afterMethod() {
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

	@AfterClass(groups = "only")
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
