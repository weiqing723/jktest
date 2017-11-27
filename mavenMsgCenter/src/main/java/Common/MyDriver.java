/**
 * 
 */
package Common;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;



/**
 * @author wei
 * WebDriver  的封装类
 *
 */
public class MyDriver {
	private WebDriver wb;
	
	public MyDriver (WebDriver w)
	{
		wb=w;
	
	}
	public WebDriver getDriver () 
	{
		return wb;
	}
	
	//findElement 添加retry 逻辑  By by
	public WebElement findElement (By by) throws InterruptedException
	{
		int count = 0;
		WebElement result = null;
		
		while(true) {
			try {
				result = wb.findElement(by);
				System.out.println(result);
			} catch(Exception ex) {
				result = null;
			}
			if(result != null) {
				break;
			} else {
				if(count++ < 10) {
					Thread.sleep(2000);
					System.out.println("retry "+	count+" time");
					
				}
				else {break;}
				
			}
		}
		if (result==null) {System.out.println("没找到"+by.toString());}
		return result;
		
	}
	//findElements 添加retry 逻辑
	public List <WebElement> findElements (By by) throws InterruptedException
	{
		int count = 0;
		List <WebElement>  result = null;
		
		while(true) {
			try {
			
				result = wb.findElements(by); 
				
			} catch(Exception ex) {
				result = null;
			}
			if(result != null) {
				break;
			} else {
				if(count++ < 10) {
					Thread.sleep(2000);
				}
			}
		}
		return result;
	}
	
	
    /** 
     * rewrite the get method, adding user defined log</BR> 
     * 地址跳转方法，使用WebDriver原生get方法，加入失败重试的次数定义。 
     *  retry 3次，前2次的retry时间10秒，最后1次20秒
     * @throws RuntimeException 
     */  
    public void get(String url) {  
        boolean inited = false;  
        int index = 0, timeout = 20;  
        int actionCount=3;
        while (!inited && index < actionCount){  
            timeout = (index == actionCount - 1) ? 60 : 10;//最后一次跳转使用最大的默认超时时间  
            inited = navigateAndLoad(url, timeout);  
            index ++;  
        }  
        if (!inited && index == actionCount){//最终跳转失败则抛出运行时异常，退出运行  
            throw new RuntimeException("can not get the url [" + url + "] after retry " + actionCount + "times!");  
        }  
    }  
      
    
   public boolean navigate(String url) {  
        try {  
            wb.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);  
            wb.navigate().to(url);  
            
            return true;//跳转并且加载页面成功在返回true  
        } catch (TimeoutException e) {  
            return false;//超时的情况下返回false  `
        } catch (Exception e) {  
            System.out.println(e);
            throw new RuntimeException(e);//抛出运行时异常，退出运行  
        }finally{  
            wb.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);  
            
        }  
    }  
  
      
    /** 
     * judge if the url has navigate and page load completed.</BR> 
     * 跳转到指定的URL并且返回是否跳转完整的结果。 
     *  
     * @param url the url you want to open. 
     * @param timeout the timeout for page load in seconds. 
     * @return if page load completed. 
     */  
    private boolean navigateAndLoad(String url, int timeout){  
        try {  
            wb.manage().timeouts().pageLoadTimeout(timeout, TimeUnit.SECONDS);  
            wb.get(url);  
            return true;//跳转并且加载页面成功在返回true  
        } catch (TimeoutException e) {  
            return false;//超时的情况下返回false  `
        } catch (Exception e) {  
            System.out.println(e);
            throw new RuntimeException(e);//抛出运行时异常，退出运行  
        }finally{  
            wb.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);  
            
        }  
    }  
}
