import java.io.IOException;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.swing.*;

//pr


public class SendEmail extends JDialog{
	    String gmail;
	    String message;
	    public int ok;
	    
	    public SendEmail(String uname,String message,String gmail,int ok) {
	        //authentication info
	        final String username = "universitatep@gmail.com";
	        final String password = "cobizmyrnkngqjad";
	        String fromEmail = "universitatep@gmail.com";
	        String toEmail = gmail;

	        Properties properties = new Properties();
	        properties.put("mail.smtp.auth", "true");
	        properties.put("mail.smtp.starttls.enable", "true");
	        properties.put("mail.smtp.host", "smtp.gmail.com");
	        properties.put("mail.smtp.port", "587");

	        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
	            protected PasswordAuthentication getPasswordAuthentication() {
	                return new PasswordAuthentication(username,password);
	            }
	        });
	        //Start our mail message
	        MimeMessage msg = new MimeMessage(session);
	        try {
	            msg.setFrom(new InternetAddress(fromEmail));
	            msg.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
	            msg.setSubject(uname+", Bine ai venit!");
	            msg.setText(message);
	  

	            Transport.send(msg);
	           
	        } catch (MessagingException e) {
	            this.ok=1;
	        }

	    }
	    
	   /* public static void main(String[] args) {
	    	//new SendEmail("Raul","Bine ai venit la Universitate!","raul.marinescu02@e-uvt.ro",0);
	    	System.out.println("gata");
	    }*/
	
	      	
	    }


	 


