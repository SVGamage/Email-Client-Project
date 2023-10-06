package Client;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

public class SendEmailTLS implements Serializable{
    private String email;
    private String subject;
    private String content;
    private String date;

    public SendEmailTLS(String email,String subject,String content){
        this.email=email;
        this.subject=subject;
        this.content=content;
        this.date = new SimpleDateFormat("yyyy/MM/dd").format(new Date());

    }

    public void sendMail(){
        final String username = "mail@gmail.com"; //enter your mail here
        final String password = "jsdjafjjasjfjjf"; //add your app password here


        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true"); //TLS

        Session session = Session.getInstance(prop,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("gamagevs99@gmail.com"));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(email)
            );
            message.setSubject(subject);
            message.setText(content);
//            System.out.println("Sending...");
            Transport.send(message);

//            System.out.println("Done.");

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    public String getDate() {
        return date;
    }

    public String getEmail() {
        return email;
    }

    public String getSubject() {
        return subject;
    }

    public String getContent() {
        return content;
    }
}
