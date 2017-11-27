package Common;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

//显性等待
//等待的条件
//WebDriver方法
//页面元素是否在页面上可用和可被单击
//elementToBeClickable(By locator)
//页面元素处于被选中状态
//elementToBeSelected(WebElement element)
//页面元素在页面中存在
//presenceOfElementLocated(By locator)
//在页面元素中是否包含特定的文本
//textToBePresentInElement(By locator)
//页面元素值
//textToBePresentInElementValue(By locator, java.lang.String text)
//titleContains(java.lang.String title)
public class MyWDWait {
	public WebDriverWait wait;

	public WebDriverWait getwait() {
		return wait;
	}

	public MyWDWait(WebDriver wd) {
		wait = new WebDriverWait(wd, 60);

	}

	// 等待页面元素存在
	public boolean WaitElementPresent(By s) {
		boolean flag = true;
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(s));
		} catch (Exception e) {
			System.out.println(s + "is not present, see the details below:");
			System.out.println(e);
			flag = false;
		}
		return flag;

	}

	// 等待页面元素不存在
	public boolean WaitElementNotPresent(By s) throws InterruptedException {
		boolean flag = false;
		int count = 0;
		WebDriverWait wait2 = wait;
		wait2.withTimeout(2, TimeUnit.SECONDS);
		while (count++ < 20 & flag == false) {

			try {
				System.out.println("第 " + count + " 次wait");
				wait2.until(ExpectedConditions.visibilityOfElementLocated(s));
				Thread.sleep(1000);

			} catch (Exception e) {
				System.out.println(s + "is not present");
				System.out.println(e);
				flag = true;
				break;
			}

		}
		return flag;

	}

	public boolean WaiteElementClickable(By s) {
		boolean flag = true;

		try {
			wait.until(ExpectedConditions.elementToBeClickable(s));
		} catch (Exception e) {
			System.out.println(s + "is not clickable, see the details below:");
			System.out.println(e);
			flag = false;
		}
		return flag;
	}

	public boolean WaiteElementClickable(WebElement e) {
		boolean flag = true;
		try {
			wait.until(ExpectedConditions.elementToBeClickable(e));
		} catch (Exception ex) {
			System.out.println(e + "is not clickable, see the details below:");
			System.out.println(ex);
			flag = false;
		}
		return flag;
	}

	public boolean WaiteElementSelected(By s) {
		boolean flag = true;
		try {
			wait.until(ExpectedConditions.elementToBeSelected(s));
		} catch (Exception ex) {
			System.out.println(s + "is not selected, see the details below:");
			System.out.println(ex);
			flag = false;
		}
		return flag;
	}

	public boolean WaiteElementSelected(WebElement e) {
		boolean flag = true;
		try {
			wait.until(ExpectedConditions.elementToBeSelected(e));
		} catch (Exception ex) {
			System.out.println(e + "is not selected, see the details below:");
			System.out.println(ex);
			flag = false;
		}
		return flag;
	}

	public void WaiteAllElement(By s) {
		try {
			wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(s));

		} catch (Exception ex) {
			System.out.println("Not all the elements are visibility, see the details below:");
			System.out.println(ex);
		}

	}

	@SuppressWarnings("deprecation")
	public boolean WaiteTextPresent(By b, String s) {

		try {
			return wait.until(ExpectedConditions.textToBePresentInElement(b, s));
		} catch (Exception ex) {
			System.out.println("Title does not contains " + s + ", see the details below:");
			System.out.println(ex);
			return false;
		}

	}

	public void WaiteTitleContains(String s) {

		try {
			wait.until(ExpectedConditions.titleContains(s));
		} catch (Exception ex) {
			System.out.println("Title does not contains " + s + ", see the details below:");
			System.out.println(ex);
		}

	}

	public boolean WaitURLContains(String s) {
		boolean flag = true;
		try {
			wait.until(ExpectedConditions.urlContains(s));
		} catch (Exception ex) {
			System.out.println("URL does not contains " + s + ", see the details below:");
			System.out.println(ex);
			flag = false;
		}
		return flag;

	}

}
