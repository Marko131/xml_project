package tim10.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import tim10.project.model.user.User;

@Service
public class MailService {

    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private Environment env;

    @Async
    public void sendMail(User user, String subject, String content) {
        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom(env.getProperty("spring.mail.username"));
        message.setSubject(subject);
        message.setTo(user.getEmail());
        message.setText(content);

        mailSender.send(message);
    }
}