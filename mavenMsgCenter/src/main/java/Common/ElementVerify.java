/**
 * 
 */
package Common;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

/**
 * @author wei
 * 用来验证WebElement的状态等信息
 */
public class ElementVerify {
	WebElement we;
public ElementVerify(WebElement e)
{
	we=e;
}
public boolean VerifyClickIfRefresh() 
{
	
	boolean flag=false;
	we.click();
	try {we.click();}
	catch(StaleElementReferenceException e) {flag=true;System.out.println(e);}
	catch(NoSuchElementException  e){flag=true;System.out.println(e);}
	return flag;
}
}
