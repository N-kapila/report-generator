package lk.kelaniya.uni.output;

import lk.kelaniya.uni.inputs.JsonFileInputData;
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
    final JsonFileInputData jsonFileInputData;

    public ExcelDataEmailOutput(DataResult dataResult, JsonFileInputData jsonFileInputData, String recipientEmail) {
        super(dataResult);
        this.recipientEmail = recipientEmail;
        this.jsonFileInputData = jsonFileInputData;
    }

    @Override
    public void execute() throws DataOutputException {
        super.execute();

        Properties prop = new Properties();
        prop.put("mail.smtp.host", jsonFileInputData.getSmtpHost());
        prop.put("mail.smtp.port", jsonFileInputData.getSmtpPort());
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true");


        Session session = Session.getDefaultInstance(prop,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(
                                jsonFileInputData.getSmtpUserName(), jsonFileInputData.getSmtpPassword()
                        );
                    }
                });

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(jsonFileInputData.getSenderEmail()));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipientEmail));
            message.setSubject("Report");

            BodyPart messageBodyPart1 = new MimeBodyPart();
            messageBodyPart1.setText("Report file attached!");

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