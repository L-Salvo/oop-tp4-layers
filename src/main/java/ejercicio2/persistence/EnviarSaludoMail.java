package ejercicio2.persistence;


import ejercicio2.model.EnviarMail;
import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import java.util.Properties;


public class EnviarSaludoMail implements EnviarMail {

    @Override
    public void enviar(String mail,String titulo, String cuerpo) {

        final String username = "bfa904f90254ba";
        final String password = "3c4e6a7e553de0";
        String destinatario = mail;
        String emisor = "empresa@gmail.com";
        String host = "sandbox.smtp.mailtrap.io";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", "2525");
        Session session = Session.getInstance(props,
                new Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(emisor));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(destinatario));
            message.setSubject(titulo);
            message.setText(cuerpo);
            Transport.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

}