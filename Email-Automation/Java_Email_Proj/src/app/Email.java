package app;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

public class Email {
    final String recipient = System.getenv("RECP_MAIL");

    Session session;

    String user_email;
    String user_pass;
    

    public Email(String ue, String up)
    {
        this.user_email = ue;
        this.user_pass = up;
    }

    public void startUp()
    {
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        this.session = Session.getInstance(properties,
            new Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(
                        user_email, user_pass);
                }
            }
        );
    }

    public void sendMsg()
    {
        try
        {
            MimeMessage message = new MimeMessage(this.session);
            message.setFrom(new InternetAddress(this.user_email));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
            message.setSubject("Check In");
            message.setText("Just sending an email confirming my check in");
            
            Transport.send(message);
        } 
        catch (MessagingException mex)
        {
            mex.printStackTrace();
        }

    }
    
}