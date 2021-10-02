package lk.kelaniya.uni.output;

import lk.kelaniya.uni.repository.DataResult;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
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

        final String user = "bookberriesbookberries@gmail.com";
        final String password = "Debuggers";

        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true");


        Session session = Session.getDefaultInstance(prop,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(user, password);
                    }
                });

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(user));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipientEmail));
            message.setSubject("Message Aleart");

            BodyPart messageBodyPart1 = new MimeBodyPart();
            messageBodyPart1.setText("This is message body");

            MimeBodyPart messageBodyPart2 = new MimeBodyPart();

            DataSource source = new FileDataSource(this.outputPath);
            messageBodyPart2.setDataHandler(new DataHandler(source));
            messageBodyPart2.setFileName(this.outputPath);

            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPart1);
            multipart.addBodyPart(messageBodyPart2);

            message.setContent(multipart);

            Transport.send(message);

        } catch (MessagingException e) {
            throw new DataOutputException(e, e.getMessage());
        }
    }

}
