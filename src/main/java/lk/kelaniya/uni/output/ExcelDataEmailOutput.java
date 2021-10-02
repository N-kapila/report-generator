package lk.kelaniya.uni.output;

import lk.kelaniya.uni.repository.DataResult;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class ExcelDataEmailOutput extends ExcelDataOutput {
    final String recipientEmail;

    public ExcelDataEmailOutput(DataResult dataResult, String fileName, String recipientEmail) {
        super(dataResult, fileName);
        this.recipientEmail = recipientEmail;
    }

    @Override
    public void execute() throws DataOutputException {
        super.execute();
        final String username = "";
        final String password = "";

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
            message.setFrom(new InternetAddress(""));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(recipientEmail)
            );
            message.setSubject("");
            message.setText("");

            Transport.send(message);

            System.out.println("Done");

        } catch (MessagingException e) {
            throw new DataOutputException(e, e.getMessage());
        }

    }
}
