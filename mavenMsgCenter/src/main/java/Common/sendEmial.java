package Common;

import Common.Email.MailInfo;
import Common.Email.MailSender;

public class sendEmial {

	public static void main(String[] args) {

		// TODO Auto-generated method stub
		MailSender mailSender = MailSender.getInstance();
		MailInfo mailInfo = mailSender.getMailInfo();
		try {
			mailSender.sendHtmlMail(mailInfo, 3);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Send Email Done");
	}

}
