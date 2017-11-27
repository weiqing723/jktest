package Common;

import java.io.File;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

//登陆方法 默认Chrome
public class login {
	public WebDriver my_driver = null;
	public String username = null;

	public login() throws InterruptedException {
		// safari
		// WebDriver my_driver=new SafariDriver();

		// -----------------------------�򿪻�������------------------------------------------------
		// //WebDriver my_dr = new FirefoxDriver();// �򿪻�������
		// ԭ��֧�ֵ�����������ǲ�֧�ֻ���߼��İ汾
		File file_gecko = new File("driverlib/geckodriver");
		System.setProperty("webdriver.gecko.driver", file_gecko.getAbsolutePath());
		my_driver = new FirefoxDriver();// �򿪻������� ԭ��֧�ֵ�����������ǲ�֧�ֻ���߼��İ汾
		my_driver.manage().window().maximize();
		// File file_chrome = new File("driverlib/chromedriver");
		// System.setProperty("webdriver.chrome.driver", file_chrome.getAbsolutePath());
		// my_driver = new ChromeDriver();
		// my_driver.get("http://testmcenter.weixinxk.com/admin/logon");
		MyDriver my_mydriver = new MyDriver(my_driver);
		my_mydriver.get("http://testmcenter.weixinxk.com/admin/logon");
		MyWDWait waite = new MyWDWait(my_driver);

		waite.WaiteAllElement(By.xpath(".//*[@id='mcenter-logon']/*"));

	}

	public login(String name) throws InterruptedException {
		// safari
		// WebDriver my_driver=new SafariDriver();

		// -----------------------------�򿪻�������------------------------------------------------
		// //WebDriver my_dr = new FirefoxDriver();// �򿪻�������
		// ԭ��֧�ֵ�����������ǲ�֧�ֻ���߼��İ汾
		File file_gecko = new File("driverlib/geckodriver");
		System.setProperty("webdriver.gecko.driver", file_gecko.getAbsolutePath());
		// my_driver = new FirefoxDriver();// �򿪻������� ԭ��֧�ֵ�����������ǲ�֧�ֻ���߼��İ汾

		// File file_chrome = new File("driverlib/chromedriver");
		// System.setProperty("webdriver.chrome.driver", file_chrome.getAbsolutePath());
		// my_driver = new ChromeDriver();
		// my_driver.get("http://testmcenter.weixinxk.com/admin/logon");
		MyDriver my_mydriver = null;
		int count = 0;
		while (count++ < 3) {
			try {
				my_driver = new FirefoxDriver();
				my_driver.manage().window().maximize();
				my_mydriver = new MyDriver(my_driver);
				my_mydriver.get("http://testmcenter.weixinxk.com/admin/logon");
				MyWDWait waite = new MyWDWait(my_driver);
				waite.WaiteAllElement(By.xpath(".//*[@id='mcenter-logon']/*"));

				my_mydriver.findElement(By.xpath(".//*[@id='mcenter-logon']/div[1]/div[1]/input")).clear(); // 找到手机输入框并清空
				my_mydriver.findElement(By.xpath(".//*[@id='mcenter-logon']/div[1]/div[1]/input")).sendKeys(name); //
				Thread.sleep(100);
				my_mydriver.findElement(By.xpath(".//*[@id='mcenter-logon']//div[text()='获取登录密码']")).click(); // 点击获得登录密码
				Thread.sleep(100);
				my_mydriver.findElement(By.xpath(".//*[@id='mcenter-logon']/div[3]/div/input")).clear(); // 找到密码并清空
				my_mydriver.findElement(By.xpath(".//*[@id='mcenter-logon']/div[3]/div/input")).sendKeys("8888"); // 输入密码
				my_mydriver.findElement(By.xpath(".//*[@id='mcenter-logon']/div[5]")).click(); // 点击登录
				Thread.sleep(2000);

				waite.WaiteAllElement(By.xpath(".//*[@id='app']/*"));
				break;
			} catch (Exception ex) {
				quiteBrower();

			}

		}

	}

	public void quiteBrower() {
		my_driver.quit();
	}

	// 判断元素有没有刷新 getTagName如果在页面刷新后再次运行会报异常
	public boolean waitPageRefresh(WebElement trigger) {
		int refreshTime = 0;

		boolean isRefresh = false;
		try {
			for (int i = 1; i < 100; i++) {
				refreshTime = i;
				trigger.getTagName();
				Thread.sleep(10);
			}
		} catch (StaleElementReferenceException e) {
			isRefresh = true;
			System.out.println("Page refresh time is:" + refreshTime + " seconds!");
			return isRefresh;
		} catch (WebDriverException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Page didnt refresh in 60 seconds!");
		return isRefresh;
	}

}
