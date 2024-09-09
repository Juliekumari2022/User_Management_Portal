
package in.learning.portal.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import in.learning.portal.service.EmailService;

@Service
public class EmailServiceImpl implements EmailService {

	@Autowired
	private JavaMailSender javaMailSender;

	@Override
	public boolean sendMail(String to, String subject, String body) {
		try {
			String fromMail = "krijulie2023@gmail.com";
			SimpleMailMessage simple = new SimpleMailMessage();
			simple.setFrom(fromMail);
			simple.setSubject(subject);
			simple.setTo(to);
			simple.setText(body);
			javaMailSender.send(simple);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
