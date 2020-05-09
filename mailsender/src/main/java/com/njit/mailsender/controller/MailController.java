package com.njit.mailsender.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.websocket.server.PathParam;

@RestController
public class MailController {
    @Autowired
    private JavaMailSender javaMailSender;

    @GetMapping("/send/{to}/{subject}/{text}")
    public Boolean sendMail(@PathVariable String to, @PathVariable String subject, @PathVariable String text){
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom("winflush@163.com");
        simpleMailMessage.setText(text);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setTo(to);
        javaMailSender.send(simpleMailMessage);
        return Boolean.TRUE;
    }

    @GetMapping("/send1/{to}/{subject}/{text}")
    public Boolean sendMail1(@PathVariable String to, @PathVariable String subject,
                             @PathVariable String text, @RequestParam("file") MultipartFile file) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage,true);
        mimeMessageHelper.setFrom("winflush@163.com");
        mimeMessageHelper.setTo(to);
        mimeMessageHelper.setSubject(subject);
        mimeMessageHelper.setText(text);
        mimeMessageHelper.addAttachment("abc.txt",file);
        javaMailSender.send(mimeMessage);
        return Boolean.TRUE;
    }
}
