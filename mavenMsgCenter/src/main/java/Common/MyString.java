/**
 * 
 */
package Common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * @author wei 扩展的String 操作
 */
public class MyString {
	/**
	 * 返回参数1从参数2开始到参数3之前结束的字符串 eg： SubString(123456789，123,789) ="123456"
	 */
	public static String SubString(String str, String s1, String s2) {
		String s = str;
		int indexStart1 = s.indexOf(s1);
		int indexStart2 = s.indexOf(s2);
		return s.substring(indexStart1, indexStart2);

	}

	/**
	 * 输入时间类型的string，返回date 输入的格式为
	 * 
	 * @throws ParseException
	 */
	public static Date string2Date(String sd) throws ParseException {
		Date date = null;
		SimpleDateFormat sFm = new SimpleDateFormat("MM月dd日 HH:mm");
		Date today = new Date();

		int index = 0;
		if (sd.contains("昨天")) {
			index = sd.indexOf(":") - 2;
			sd = sd.substring(index); // 获取 小时：分钟
			// 获取昨天的日期
			Calendar calendar = new GregorianCalendar();
			calendar.setTime(today);
			calendar.add(calendar.DATE, -1);// 把日期往后增加一天.整数往后推,负数往前移动
			date = calendar.getTime(); // 这个时间就是昨天
			SimpleDateFormat formatter = new SimpleDateFormat("MM月dd日");
			String sYesterday = formatter.format(date);
			sYesterday = sYesterday + " " + sd;
			date = sFm.parse(sYesterday);
		} else {
			try {
				date = sFm.parse(sd);
			} catch (ParseException eP) {
				SimpleDateFormat sTime = new SimpleDateFormat("MM月dd日");

				String sToday = sTime.format(today);
				sToday = sToday + " " + sd;

				date = sFm.parse(sToday);

			} catch (Exception ex) {
				ex.printStackTrace();
			}

		}
		System.out.println(date.toString());
		return date;
	}

}
