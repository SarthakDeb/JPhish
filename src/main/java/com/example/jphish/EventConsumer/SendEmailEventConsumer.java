package com.example.jphish.EventConsumer;

import com.example.jphish.Dtos.SendEmailEventDto;
import com.example.jphish.Utils.EmailUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.kafka.annotation.KafkaListener;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import java.util.Properties;

public class SendEmailEventConsumer {

    private ObjectMapper objectMapper = new ObjectMapper();

    public SendEmailEventConsumer(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @KafkaListener(topics = "SendEmail")
    public void handleSendEmailEvent(String message) throws JsonProcessingException {
        SendEmailEventDto sendEmailEventDto = objectMapper.readValue(message, SendEmailEventDto.class);

        String to = sendEmailEventDto.getTo();
        String from = sendEmailEventDto.getFrom();
        String subject = sendEmailEventDto.getSubject();
        String body = sendEmailEventDto.getBody();

        System.out.println("Email Start");
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.mailbit.io"); //SMTP Host
        props.put("mail.smtp.port", "500"); //TLS Port
        props.put("mail.smtp.auth", "true"); //enable authentication
        props.put("mail.smtp.starttls.enable", "false"); //enable STARTTLS

        //create Authenticator object to pass in Session.getInstance argument
        Authenticator auth = new Authenticator() {
            //override the getPasswordAuthentication method
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("graph-7f6945520636996d", "keNWBR6IRWdbgwnjxhRsSLZl04qE");
            }
        };
        Session session = Session.getInstance(props, auth);

        EmailUtils.sendEmail(session, to, subject, body);
    }
}
