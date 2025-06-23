package br.ufscar.dc.dsw.AA1Veiculos.service.impl;

import java.io.File;
import java.io.UnsupportedEncodingException; // Added for InternetAddress

import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import br.ufscar.dc.dsw.AA1Veiculos.service.spec.IEmailService; 

@Service
public class EmailService implements IEmailService {

    @Autowired
    JavaMailSender emailSender;

    @Override
    public void enviarEmail(String to, String assunto, String corpo) {
        InternetAddress fromAddress;
        InternetAddress toAddress;
        try {
            fromAddress = new InternetAddress("aaveiculosa@gmail.com", "AppCompraVendaVeiculos");
            toAddress = new InternetAddress(to);
        } catch (UnsupportedEncodingException | MessagingException e) {
            System.err.println("Erro ao criar endereços de e-mail: " + e.getMessage());
            e.printStackTrace();
            return;
        }
        send(fromAddress, toAddress, assunto, corpo, null);
    }

    private void send(InternetAddress from, InternetAddress to, String subject, String body, File file) {
        try {
            MimeMessage message = emailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(body, true); 

            if (file != null) {
                helper.addAttachment(file.getName(), file);
            }

            emailSender.send(message);

            System.out.println("Mensagem enviada com sucesso para " + to.getAddress() + "!");

        } catch (MessagingException e) {
            System.err.println("Mensagem não enviada para " + to.getAddress() + "!");
            e.printStackTrace();
        }
    }
}