package org.sabre.util;

import jakarta.mail.*;
import jakarta.mail.internet.*;
import java.io.File;
import java.util.Properties;

public class EmailNotifier {
    private final Properties mailProps;
    private final String username;
    private final String password;

    public EmailNotifier(Properties config) {
        mailProps = new Properties();
        mailProps.put("mail.smtp.auth", config.getProperty("mail.smtp.auth", "true"));
        mailProps.put("mail.smtp.starttls.enable", config.getProperty("mail.smtp.starttls.enable", "true"));
        mailProps.put("mail.smtp.host", config.getProperty("mail.smtp.host"));
        mailProps.put("mail.smtp.port", config.getProperty("mail.smtp.port"));
        username = config.getProperty("mail.smtp.username");
        password = config.getProperty("mail.smtp.password");
    }

    public void sendMail(String to, String subject, String body, File attachment) throws MessagingException {
        try{
            Session session = Session.getInstance(mailProps, new Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(username, password);
                }
            });
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject(subject);

            if (attachment != null && attachment.exists()) {
                MimeBodyPart messageBodyPart = new MimeBodyPart();
                messageBodyPart.setText(body);
                MimeBodyPart attachPart = new MimeBodyPart();
                attachPart.attachFile(attachment);
                Multipart multipart = new MimeMultipart();
                multipart.addBodyPart(messageBodyPart);
                multipart.addBodyPart(attachPart);
                message.setContent(multipart);
            } else {
                message.setText(body);
            }
            Transport.send(message);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public void sendMail(String to, String subject, String body) throws MessagingException {
        sendMail(to, subject, body, null);
    }

    public void sendHtmlMail(String to, String subject, String htmlBody, File attachment) throws MessagingException {
        try{
            Session session = Session.getInstance(mailProps, new Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(username, password);
                }
            });
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject(subject);

            if (attachment != null && attachment.exists()) {
                MimeBodyPart messageBodyPart = new MimeBodyPart();
                messageBodyPart.setContent(htmlBody, "text/html");
                MimeBodyPart attachPart = new MimeBodyPart();
                attachPart.attachFile(attachment);
                Multipart multipart = new MimeMultipart();
                multipart.addBodyPart(messageBodyPart);
                multipart.addBodyPart(attachPart);
                message.setContent(multipart);
            } else {
                message.setContent(htmlBody, "text/html");
            }
            Transport.send(message);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
    public void sendHtmlMail(String to, String subject, String htmlBody) throws MessagingException {
        sendHtmlMail(to, subject, htmlBody, null);
    }
}
