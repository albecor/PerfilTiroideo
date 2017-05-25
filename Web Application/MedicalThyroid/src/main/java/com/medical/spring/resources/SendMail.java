
package com.medical.spring.resources;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * send email to recover password
 * @author GTST : Grupo de Tratamiento de SeÃ±ales y telecomunicaciones
 */
public class SendMail {
	
	private String sender;
	private String senderPass;
    
    /**
	 * @return the sender
	 */
	public String getSender() {
		return sender;
	}


	/**
	 * @param sender the sender to set
	 */
	public void setSender(String sender) {
		this.sender = sender;
	}


	/**
	 * @return the senderPass
	 */
	public String getSenderPass() {
		return senderPass;
	}


	/**
	 * @param senderPass the senderPass to set
	 */
	public void setSenderPass(String senderPass) {
		this.senderPass = senderPass;
	}


	
    /**
     * 
     * @param sender - Sender's email
     * @param senderPass - Sender's password
     */
    public SendMail(String sender, String senderPass){
        this.sender = sender;
        this.senderPass = senderPass;    
    }    
    
    
    /**
     * Send email to recover password
     * @param recipient
     * @param password
     * @param user
     */
    public void sendMail(String recipient, String password, String user){        

        
        try
        {
            // Connection's properties
            Properties props = new Properties();
            props.setProperty("mail.smtp.host", "smtp.gmail.com");
            props.setProperty("mail.smtp.starttls.enable", "true");
            props.setProperty("mail.smtp.port", "587");
            props.setProperty("mail.smtp.user", this.sender);
            props.setProperty("mail.smtp.auth", "true");

            Session session = Session.getDefaultInstance(props);

            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(this.sender));
            message.addRecipient(
                Message.RecipientType.TO,
                new InternetAddress(recipient));
            message.setSubject("Plataforma médica. Envío de contraseña");
            message.setText(
                "Hola "+user+". Tu contraseña para el acceso a la plataforma es: "+password);

            // sent
            Transport t = session.getTransport("smtp");
            t.connect(this.sender, this.senderPass);
            t.sendMessage(message, message.getAllRecipients());

            // Close.
            t.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    
    
}
