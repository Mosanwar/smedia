package edu.mum.ea.socialmedia.service;

import edu.mum.ea.socialmedia.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.io.IOException;

@Service
public class EmailServiceImpl implements EmailService {
    @Autowired
    private JavaMailSender sender;

    public String sendMail(User user) {
//        MimeMessage message = sender.createMimeMessage();

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(user.getEmail());
        message.setSubject("smedia deactivation service");
        message.setText("Dear "+user.getName()+", \n we are sorry to inform you that your account has been deactivated and that is for you made about 20 melious post " );
        sender.send(message);

        return "Mail Sent Success!";
    }

}
