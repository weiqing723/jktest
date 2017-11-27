/**
 * 
 */
package Operation.History;

import java.text.ParseException;

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
import Common.MyString;
import Common.MyWDWait;
import Common.TestBase;
import Common.login;

/**
 * @author wei 测试运营人员 历史消息 消息列表的案例
 *
 */
public class MsgList extends TestBase {
	login l = null;
	public WebDriver wb = null;
	MyWDWait wait = null;
	MyDriver mywb = null;

	public WebDriver getDriver() {
		return wb;
	}

	// 27 消息列表 默认 能否默认显示：全部的已提审的历史消息
	@Test
	public void a1DefaultDisplay() throws InterruptedException {
		// 提取已提交消息的父级的style

		WebElement eDefault = mywb
				.findElement(By.xpath(".//*[@id='app']/div/div[2]/div[2]/div[1]/div[2]/div[1]/div/div[1]"));
		String sDe = eDefault.getAttribute("style");

		// 获得数字
		WebElement eCount = mywb
				.findElement(By.xpath(".//*[@id='app']/div/div[2]/div[2]/div[1]/div[2]/div[1]/div/div[1]/span/div"));
		String count = eCount.getText();
		count = count.replace("(", "");
		count = count.replace(")", "");
		int i = Integer.parseInt(count);

		boolean flag = false;
		// 判断颜色是否为选中颜色，且数字大于1
		if (sDe.contains("color: rgb(56, 160, 226)") & i > 1) {
			flag = true;
		}
		Assert.assertEquals(flag, true);

	}

	// 28 消息列表 历史消息类别 能否显示：已提审消息、被驳回消息、已推送消息、已删除消息
	@Test
	public void a2DefaultType() throws InterruptedException {
		boolean flag = true;
		// 判断找 页面上的这些text元素是否存在
		try {
			wb.findElement(By.xpath(".//*[@id='app']//div[text()='已提交消息']"));
			wb.findElement(By.xpath(".//*[@id='app']//div[text()='被驳回消息']"));
			wb.findElement(By.xpath(".//*[@id='app']//div[text()='已推送消息']"));
			wb.findElement(By.xpath(".//*[@id='app']//div[text()='已撤回消息']"));
		} catch (Exception e) {
			System.out.println(e);
			flag = false;
		}

		Assert.assertEquals(flag, true);

	}

	// 31 消息列表 已提审列表-排序 能否按提审时间，倒序排列
	@SuppressWarnings("deprecation")
	@Test
	public void a3DefaulSequence() throws InterruptedException, ParseException {
		boolean flag = false;
		// 找寻页面上提交列表的时间

		WebElement l1 = mywb.findElement(By.xpath(
				".//*[@id='app']/div/div[2]/div[2]/div[1]/div[2]/div[2]/div[1]/div[1]/div[1]/div[1]/div[1]/div[3]/p[2]"));
		WebElement l2 = mywb.findElement(By.xpath(
				".//*[@id='app']/div/div[2]/div[2]/div[1]/div[2]/div[2]/div[1]/div[1]/div[2]/div[1]/div[1]/div[3]/p[2]"));

		String s1 = l1.getText();
		String s2 = l2.getText();

		java.util.Date date1 = MyString.string2Date(s1);

		java.util.Date date2 = MyString.string2Date(s2);

		System.out.println(date1 + "  " + date2);
		if (date1.getTime() < date2.getTime() == false) {
			flag = true;
		}

		Assert.assertEquals(flag, true);
	}

	// 32 消息列表 已提审消息详情 能否展示：正确的消息，删除按钮 部分支持，判断了标题和撤回按钮
	// @Test
	// public void a4SubmitDetail() throws InterruptedException {
	// // 列表上的标题
	// WebElement eMsgTitle = mywb.findElement(By.xpath(
	// ".//*[@id='app']/div/div[2]/div[2]/div[1]/div[2]/div[2]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/p"));
	// // 列表Details中的标题
	// WebElement eMsgInDetails = mywb.findElement(By.xpath(
	// ".//*[@id='app']/div/div[2]/div[2]/div[1]/div[2]/div[2]/div[1]/div[1]/div[1]/div[2]/div[2]/div[1]/div[1]/div[1]"));
	//
	// String sMsgTitle = eMsgTitle.getText();
	// String sMsgInDetails = eMsgInDetails.getText();
	// Thread.sleep(1000000);
	// Assert.assertEquals(sMsgInDetails.contains(sMsgTitle), true);
	//
	// }

	// 41 消息列表 被驳回消息-排序 能否按被驳回时间，倒序排列
	@Test
	public void a5RejectedSequence() throws InterruptedException, ParseException {
		// 点击 被驳回消息，切换列表
		WebElement eRej = mywb.findElement(By.xpath(".//*[@id='app']//div[text()='被驳回消息']"));
		eRej.click();
		Thread.sleep(2000);

		boolean flag = false;
		// 找寻页面上提交列表的时间
		WebElement l1 = mywb.findElement(By.xpath(
				".//*[@id='app']/div/div[2]/div[2]/div[1]/div[2]/div[2]/div[1]/div[1]/div[1]/div[1]/div[1]/div[3]/p[2]"));
		WebElement l2 = mywb.findElement(By.xpath(
				".//*[@id='app']/div/div[2]/div[2]/div[1]/div[2]/div[2]/div[1]/div[1]/div[2]/div[1]/div[1]/div[3]/p[2]"));

		String s1 = l1.getText();
		String s2 = l2.getText();

		java.util.Date date1 = MyString.string2Date(s1);

		java.util.Date date2 = MyString.string2Date(s2);

		System.out.println(date1 + "  " + date2);
		if (date1.getTime() > date2.getTime()) {
			flag = true;
		}

		Assert.assertEquals(flag, true);

	}

	// 44 消息列表 被驳回消息详情-编辑 能否跳转到撰写消息页，并成功读取消息详情
	// 46 消息列表 被驳回消息-提审成功 能否自动跳转至待提审消息列表
	// 47 消息列表 被驳回消息-提审成功 内容审核员，能否查看到新的提审消息
	// 48 消息列表 已推送消息 能否显示，正确的已推送消息数
	// 49 消息列表 已推送消息 已推送列表项，能否显示正确
	// 50 消息列表 已推送消息-排序 能否按被推送时间，倒序排列
	@Test
	public void a7RejectedSequence() throws InterruptedException, ParseException {
		// 点击 已推送消息，切换列表
		WebElement ePush = mywb.findElement(By.xpath(".//*[@id='app']//div[text()='已推送消息']"));
		ePush.click();
		wait.WaiteAllElement(By.xpath(".//*[@id='app']/*"));
		// Thread.sleep(2000);

		boolean flag = false;
		// 找寻页面上提交列表的时间
		WebElement l1 = mywb.findElement(By.xpath(
				".//*[@id='app']/div/div[2]/div[2]/div[1]/div[2]/div[2]/div[1]/div[1]/div[1]/div[1]/div[1]/div[3]/p[2]"));
		WebElement l2 = mywb.findElement(By.xpath(
				".//*[@id='app']/div/div[2]/div[2]/div[1]/div[2]/div[2]/div[1]/div[1]/div[2]/div[1]/div[1]/div[3]/p[2]"));

		String s1 = l1.getText();
		String s2 = l2.getText();
		java.util.Date date1 = MyString.string2Date(s1);
		java.util.Date date2 = MyString.string2Date(s2);

		if (date1.getTime() < date2.getTime() == false) {
			flag = true;
		}
		Assert.assertEquals(flag, true);

	}
	// 67 消息列表 查看新消息-消息类别 能否成功进入，消息列表页

	// 71 消息列表 其他消息详情 能否还原上一个消息，并展开新抽屉
	@Test
	public void a8ClickNextDrawer() throws InterruptedException {
		// 点击展开第一个列表
		WebElement e1 = mywb.findElement(
				By.xpath(".//*[@id='app']/div/div[2]/div[2]/div[1]/div[2]/div[2]/div[1]/div[1]/div[1]/div[1]/div[2]"));
		e1.click();
		// 获得第一个列表下box的style
		WebElement eBox1 = mywb.findElement(
				By.xpath(".//*[@id='app']/div/div[2]/div[2]/div[1]/div[2]/div[2]/div[1]/div[1]/div[1]/div[2]"));
		String sBox1 = eBox1.getAttribute("style");
		Thread.sleep(100);
		// 点击展开第二个列表
		WebElement e2 = mywb.findElement(
				By.xpath(".//*[@id='app']/div/div[2]/div[2]/div[1]/div[2]/div[2]/div[1]/div[1]/div[2]/div[1]/div[2]"));
		e2.click();
		Thread.sleep(100);
		// 获得第二个列表下box的style
		WebElement eBox2 = mywb.findElement(
				By.xpath(".//*[@id='app']/div/div[2]/div[2]/div[1]/div[2]/div[2]/div[1]/div[1]/div[2]/div[2]"));
		String sBox2 = eBox2.getAttribute("style");

		// 再次获得第一个列表下box的style
		WebElement eBox1new = mywb.findElement(
				By.xpath(".//*[@id='app']/div/div[2]/div[2]/div[1]/div[2]/div[2]/div[1]/div[1]/div[1]/div[2]"));
		String sBox1new = eBox1new.getAttribute("style");

		boolean flag = false;

		if (sBox1.contains("max-height: 2000px") & sBox2.contains("max-height: 2000px")
				& sBox1new.contains("max-height: 0px")) {

			flag = true;
		}
		Assert.assertEquals(flag, true);

	}
	// 73 消息列表 点击列表最后一行的消息 上一抽屉内容能否还原，被点击列表下拉并显示抽屉内容

	@Test
	public void a9ClickLastDrawer() throws InterruptedException {
		// 点击展开倒第二个列表
		WebElement e1 = mywb.findElement(By.xpath(
				".//*[@id='app']/div/div[2]/div[2]/div[1]/div[2]/div[2]/div[1]/div[1]/div[last()-1]/div[1]/div[2]"));
		e1.click();
		Thread.sleep(1000);
		// 获得倒第二个列表下box的style
		WebElement eBox1 = mywb.findElement(
				By.xpath(".//*[@id='app']/div/div[2]/div[2]/div[1]/div[2]/div[2]/div[1]/div[1]/div[last()-1]/div[2]"));
		String sBox1 = eBox1.getAttribute("style");
		Thread.sleep(1000);
		// 点击展开倒第一个列表
		WebElement e2 = mywb.findElement(By.xpath(
				".//*[@id='app']/div/div[2]/div[2]/div[1]/div[2]/div[2]/div[1]/div[1]/div[last()]/div[1]/div[2]"));
		e2.click();
		Thread.sleep(1000);
		// 获得倒第一个列表下box的style
		WebElement eBox2 = mywb.findElement(
				By.xpath(".//*[@id='app']/div/div[2]/div[2]/div[1]/div[2]/div[2]/div[1]/div[1]/div[last()]/div[2]"));
		String sBox2 = eBox2.getAttribute("style");

		// 再次获得倒第二个列表下box的style
		WebElement eBox1new = mywb.findElement(
				By.xpath(".//*[@id='app']/div/div[2]/div[2]/div[1]/div[2]/div[2]/div[1]/div[1]/div[last()-1]/div[2]"));
		String sBox1new = eBox1new.getAttribute("style");

		boolean flag = false;

		if (sBox1.contains("max-height: 2000px") & sBox2.contains("max-height: 2000px")
				& sBox1new.contains("max-height: 0px")) {

			flag = true;
		}

		Assert.assertEquals(flag, true);

	}

	// 74 消息列表 最末页，点击列表最后一行 能否成功查看历史消息详情；
	@Test
	public void b1LastDrawer() throws InterruptedException {
		// 点击最后一页
		WebElement eLastPage = mywb.findElement(
				By.xpath(".//*[@id='app']/div/div[2]/div[2]/div[1]/div[2]/div[2]/div[2]/div/ul/li[last()-1]/a"));
		eLastPage.click();
		wait.WaiteAllElement(By.xpath(".//*[@id='app']/*"));
		Thread.sleep(2000);

		WebElement e1 = mywb.findElement(By.xpath(
				".//*[@id='app']/div/div[2]/div[2]/div[1]/div[2]/div[2]/div[1]/div[1]/div[last()]/div[1]/div[2]"));
		e1.click();

		Thread.sleep(200);

		// 获得倒第一个列表下box的style
		// WebElement eBox = mywb.findElement(
		// By.xpath(".//*[@id='app']/div/div[2]/div[2]/div[1]/div[2]/div[2]/div[1]/div[1]/div["
		// + n + "]/div[2]"));

		WebElement eBox = mywb.findElement(
				By.xpath(".//*[@id='app']/div/div[2]/div[2]/div[1]/div[2]/div[2]/div[1]/div[1]/div[last()]/div[2]"));

		String sBox = eBox.getAttribute("style");
		System.out.println(sBox);

		Assert.assertEquals(sBox.contains("max-height: 2000px"), true);

	}

	// 75 编辑 页面跳转 能否成功跳转到撰写信息页
	@Test
	public void b2EditNavigate() throws InterruptedException {
		// 点击已撤回消息
		WebElement eRe = mywb.findElement(By.xpath(".//*[@id='app']//div[text()='已撤回消息']"));
		eRe.click();
		wait.WaiteAllElement(By.xpath(".//*[@id='app']/*"));
		Thread.sleep(2000);
		// 点击展开第一个列表
		WebElement e1 = mywb.findElement(
				By.xpath(".//*[@id='app']/div/div[2]/div[2]/div[1]/div[2]/div[2]/div[1]/div[1]/div[1]/div[1]/div[2]"));
		e1.click();
		Thread.sleep(2000);
		// 点击编辑
		WebElement eEdit = mywb.findElement(By.xpath(
				".//*[@id='app']/div/div[2]/div[2]/div[1]/div[2]/div[2]/div[1]/div[1]/div[1]//div[text()='编辑']"));
		wait.WaiteElementClickable(eEdit);

		eEdit.click();

		wait.WaitElementPresent(By.xpath(".//*[@id='app']/*"));
		String sURL = wb.getCurrentUrl();

		Assert.assertEquals(sURL.contains("http://testmcenter.weixinxk.com/admin/pushmessage/edit/edit"), true);

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
