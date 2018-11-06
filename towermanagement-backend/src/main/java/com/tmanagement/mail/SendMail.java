package com.tmanagement.mail;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.mail.internet.MimeMessage;

public class SendMail {
	String host = "smtp.gmail.com";
	String user = "rockstardth007@gmail.com";
	String pass = "rockStar007!";
	String to = "";
	String from = "rockstar007@gmail.com";
	String subjectt = "Your OTP for Account recovery";
	String messageText = "";
	boolean sessionDebug = false;
	String name = "";
	String otp="";

	public String getOtp() {
		return otp;
	}

	public void setOtp(String otp) {
		this.otp = otp;
	}

	public SendMail() {
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public void setSubjectt(String subject) {
		this.subjectt = subject;
	}

	public String getSubjectt() {
		return subjectt;
	}

	public String getMessageText() {
		return messageText;
	}

	public void setMessageText(String messageText) {
		this.messageText = messageText;
	}

	public boolean send() {

		try {
			Properties props = System.getProperties();

			props.put("mail.smtp.starttls.enable", "true");
			props.put("mail.smtp.host", this.host);
			props.put("mail.smtp.port", "587");
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.starttls.required", "true");

			java.security.Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
			Session mailSession = Session.getDefaultInstance(props, null);
			mailSession.setDebug(sessionDebug);
			Message msg = new MimeMessage(mailSession);
			msg.setFrom(new InternetAddress(from));
			InternetAddress[] address = { new InternetAddress(to) };
			msg.setRecipients(Message.RecipientType.TO, address);
			msg.setSubject(subjectt);
			msg.setSentDate(new Date());
			msg.setText("Hi " + name + ",\n\n" + "\tYour OTP is : " + otp );
			Transport transport = mailSession.getTransport("smtp");
			transport.connect(host, user, pass);
			transport.sendMessage(msg, msg.getAllRecipients());
			transport.close();
			System.out.println("message send successfully");
			return true;
		} catch (Exception ex) {
			System.out.println(ex);
			return false;
		}

	}
}
