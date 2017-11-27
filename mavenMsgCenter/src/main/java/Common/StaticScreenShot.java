/**
 * 
 */
package Common;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.apache.commons.io.FileUtils;

/**
 * @author wei
 * 静态类 用于截图
 */
public class StaticScreenShot {
	public static void testScreenShot(WebDriver driver, String s) throws Exception
    {
        File srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        Date date=new Date();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
        String currentTime=sdf.format(date);
       
        FileUtils.copyFile(srcFile, new File("/Users/wei/Desktop/SeleniumScreenshots/"+s+"_"+currentTime+".png"));
    }
	public static void testScreenShot(WebDriver driver, String s, String folder) throws Exception
    {
        File srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
       
        Date date=new Date();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
        String currentTime=sdf.format(date);
       
        FileUtils.copyFile(srcFile, new File("/Users/wei/Desktop/SeleniumScreenshots/"+folder+"/"+s+"_"+currentTime+".png"));
    }
	
}
