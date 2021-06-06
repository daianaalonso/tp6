package c.servicios;

import c.modelo.ServicioEmail;
import c.modelo.VentaPagada;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class Email implements ServicioEmail {
    private String remitente = "YPF@example.com";
    private String destinatario;
    private final String username = "3660538112c0df";
    private final String password = "6946035db2f04d";
    private String host = "smtp.mailtrap.io";
    private Properties props = new Properties();

    public Email() {
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");// it’s optional in Mailtrap
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", "2525");
    }

    @Override
    public void enviarEmail(VentaPagada ventaPagada) {
        // Get the Session object.
        this.destinatario = ventaPagada.email();
        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(remitente));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(destinatario));
            message.setSubject("Se realizó la carga de combustible de manera exitosa.");
            message
                    .setText("Detalles\n Litros cargados: " +ventaPagada.litrosCargados()
                    +"\nMonto: $" +ventaPagada.monto()+ "\nFecha: " +ventaPagada.fecha());
            // Send message
            Transport.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}

