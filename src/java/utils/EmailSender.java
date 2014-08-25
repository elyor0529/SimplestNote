package utils;


import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Properties;

/**
 * Created by Elyor on 8/11/2014.
 */
public class EmailSender {


    public static void Send(String toEmail, String subject, String body) {

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(Settings.RESOURCE_BUNDLE.getString("USER_NAME"), Settings.RESOURCE_BUNDLE.getString("PASSWORD"));
            }
        });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(Settings.RESOURCE_BUNDLE.getString("FROM_EMAIL")));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
            message.setSubject(subject);

            Multipart mp = new MimeMultipart();
            MimeBodyPart htmlPart = new MimeBodyPart();
            htmlPart.setContent(body, "text/html");
            mp.addBodyPart(htmlPart);
            message.setContent(mp);

            Transport.send(message);

            System.out.println("Your e-mail has been sent.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
