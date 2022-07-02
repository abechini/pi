package com.esprit.bankPi.notification;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;

public class MailUtility {
	private static Properties gMailConfig = new Properties();
	static Logger logger = Logger.getLogger(MailUtility.class);

	static {
		loadMailParam();
	}
	
	public static void sendEmail(String mTo, String mTitle, String mText ) 
	{
		String mFrom = gMailConfig.getProperty("mail.smtp.mail");
		String mPsw = gMailConfig.getProperty("mail.smtp.password");
		
        Session session = Session.getInstance(gMailConfig, new javax.mail.Authenticator() {

            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(mFrom, mPsw);
            }
        });

		try {
	        session.setDebug(true);
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(mFrom));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(mTo));
            message.setSubject(mTitle);
            message.setText(mText);
            System.out.println("sending...");
            Transport.send(message);
            System.out.println("Sent message successfully....");
			
			
		} catch (Exception e) {
			logger.error(e);
		} 		
		 
		
	}

	private static void loadMailParam() {
		InputStream input = null;
		try {
			input = MailUtility.class.getResourceAsStream("/application.properties");
			gMailConfig.load( input );
		}
		catch ( IOException ex ){
			logger.error("Cannot open or load application.properties file." ,ex);
		}
		finally {
			try {
				if ( input != null ) input.close();
			}
			catch ( IOException ex ){
				logger.error("Cannot close application.properties file." ,ex);
			}
		}
	}
}
