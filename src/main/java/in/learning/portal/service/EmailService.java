package in.learning.portal.service;

import org.springframework.stereotype.Service;

@Service
public interface EmailService {
	
public boolean sendMail(String to,String subject,String body);
}
