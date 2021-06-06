package c.servicios;

import c.modelo.ServicioEmail;
import c.modelo.VentaPagada;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class Email implements ServicioEmail {
    private String REMITENTE = "YPF@example.com";
    private final String USERNAME = "3660538112c0df";
    private final String PASSWORD = "6946035db2f04d";
    private String HOST = "smtp.mailtrap.io";
    private String destinatario;
    private Properties props;

    public Email() {
        this.props =  new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");// it’s optional in Mailtrap
        props.put("mail.smtp.host", HOST);
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
                        return new PasswordAuthentication(USERNAME, PASSWORD);
                    }
                });
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(REMITENTE));
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

