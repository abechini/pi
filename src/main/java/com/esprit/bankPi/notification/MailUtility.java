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

import org.springframework.scheduling.annotation.Scheduled;

import lombok.extern.slf4j.Slf4j;

//import org.apache.log4j.Logger;

@Slf4j
public class MailUtility {
	private static Properties gMailConfig = new Properties();

	static {
		loadMailParam();
	}
	
	public static void sendEmail(String mTo, String mTitle, String mText ) 
	{
		String mFrom = "maissa.benromdhane@esprit.tn";
		String mPsw = "193JFT2496";
		
        Session session = Session.getInstance(gMailConfig, new javax.mail.Authenticator() {

            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(mFrom, mPsw);
            }
        });

		try {
	        session.setDebug(false);
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(mFrom));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(mTo));
            message.setSubject(mTitle);
            message.setContent(mText, "text/html");
            System.out.println("sending...");
            Transport.send(message);
            System.out.println("Sent message successfully....");
			
		} catch (Exception e) {
			System.err.print(e);
		} 		
		 
	}
	@Scheduled(cron = "0 0 8 1 1/1 *")
	public static void sendScheduledEmail(String mTo, String mTitle, String mText ) 
	{
		String mFrom = "maissa.benromdhane@esprit.tn";
		String mPsw = "193JFT2496";
		
        Session session = Session.getInstance(gMailConfig, new javax.mail.Authenticator() {

            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(mFrom, mPsw);
            }
        });

		try {
	        session.setDebug(false);
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(mFrom));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(mTo));
            message.setSubject(mTitle);
            message.setContent(mText, "text/html");
            System.out.println("sending...");
            Transport.send(message);
            System.out.println("Sent message successfully....");
			
		} catch (Exception e) {
			System.err.print(e);
		} 		
		 
	}

	private static void loadMailParam() {
		InputStream input = null;
		try {
			input = MailUtility.class.getResourceAsStream("/application.properties");
			gMailConfig.load( input );
		}
		catch ( IOException ex ){
			System.err.print("Cannot open or load application.properties file.");
		}
		finally {
			try {
				if ( input != null ) input.close();
			}
			catch ( IOException ex ){
		System.err.print("Cannot close application.properties file.");
			}
		}
	}
}
