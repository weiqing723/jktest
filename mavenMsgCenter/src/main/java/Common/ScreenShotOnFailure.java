package Common;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class ScreenShotOnFailure {

	private final static String SCREEN_SHOT_PATH = new File(".").getAbsolutePath() + "/screen-shot";
	private static String SCREEN_SHOT_NAME = null;

	public static void takeScreentShot(TestBase TestBase, String methodname) {
		File screenShotDir = new File(SCREEN_SHOT_PATH);
		if (!screenShotDir.exists()) {
			screenShotDir.mkdirs();
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
		Date date = new Date();
		String currentTime = sdf.format(date);
		SCREEN_SHOT_NAME = currentTime + ".jpg";
		WebDriver driver = TestBase.getDriver();
		String filename = SCREEN_SHOT_PATH + "/" + methodname + "_" + SCREEN_SHOT_NAME;
		System.out.println(filename);
		try {
			File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

			// TODO Auto-generated catch block

			System.out.println(srcFile.length());

			FileUtils.copyFile(srcFile, new File(filename));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static String getScreenShotPath() {
		return SCREEN_SHOT_PATH;
	}

	public static String getScreenShotName() {
		return SCREEN_SHOT_NAME;
	}
}