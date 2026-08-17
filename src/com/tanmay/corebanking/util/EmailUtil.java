package com.tanmay.corebanking.util;
import javax.mail.*; import javax.mail.internet.*; import java.util.*;
public final class EmailUtil { private EmailUtil(){}
 public static void sendEmail(String to,String subject,String body){String from=EmailConfig.sender(),pass=EmailConfig.password();if(from==null||from.isBlank())throw new IllegalStateException("BANKING_EMAIL is not configured.");if(pass==null||pass.isBlank())throw new IllegalStateException("BANKING_EMAIL_APP_PASSWORD is not configured.");
 Properties p=new Properties();p.put("mail.smtp.host","smtp.gmail.com");p.put("mail.smtp.port","465");p.put("mail.smtp.auth","true");p.put("mail.smtp.ssl.enable","true");p.put("mail.smtp.ssl.required","true");p.put("mail.smtp.ssl.protocols","TLSv1.2 TLSv1.3");
 // Required for machines where antivirus software intercepts SMTP TLS and presents its own trusted certificate.
 p.put("mail.smtp.ssl.trust","smtp.gmail.com");p.put("mail.smtp.connectiontimeout","15000");p.put("mail.smtp.timeout","15000");p.put("mail.smtp.writetimeout","15000");
 Session s=Session.getInstance(p,new Authenticator(){protected PasswordAuthentication getPasswordAuthentication(){return new PasswordAuthentication(from,pass);}});try{MimeMessage m=new MimeMessage(s);m.setFrom(new InternetAddress(from));m.setRecipients(Message.RecipientType.TO,InternetAddress.parse(to));m.setSubject(subject,"UTF-8");m.setText(body,"UTF-8");Transport.send(m);}catch(MessagingException e){Throwable c=e;while(c.getCause()!=null)c=c.getCause();throw new IllegalStateException("Failed to send email: "+(c.getMessage()==null?e.getMessage():c.getMessage()),e);}}
}
