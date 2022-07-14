package com.roshka.bootcamp.ProyectoJunio.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class CorreoService {
    @Autowired
    private JavaMailSender mailSender;

    public void sendEmail(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom("roshkaprueba@gmail.com"); // remitente
        message.setTo(to); // destinatario
        message.setSubject(subject); // titulo
        message.setText(text); // cuerpo

        mailSender.send(message);
    }

}