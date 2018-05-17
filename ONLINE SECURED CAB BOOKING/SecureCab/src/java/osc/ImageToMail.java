package osc;    
import com.sun.mail.util.MailSSLSocketFactory;
import java.util.*;  
import javax.mail.*;  
import javax.mail.internet.*;  
import javax.activation.*;  
public class ImageToMail
{  
     String to,user,password;
    public boolean sendImage(String to,String content,String encMailFile)throws Exception
    {
        boolean flag=false;
        this.to=to;
        //this.user="karthiksecure99@gmail.com";
        //this.password="karthikeyaasd";
        
        this.user="kommavinaychand@gmail.com";
        this.password="srkitcse";
       
            Properties props = new Properties();
            MailSSLSocketFactory sf= new MailSSLSocketFactory();
            sf.setTrustAllHosts(true);
            props.put("mail.smtp.ssl.socketFactory", sf);
            props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.starttls.enable", "true");
			props.put("mail.smtp.host", "smtp.gmail.com");
			props.put("mail.smtp.port", "587");
                        
      Session session = Session.getDefaultInstance(props,  
       new javax.mail.Authenticator() {  
       protected PasswordAuthentication getPasswordAuthentication() {  
       return new PasswordAuthentication(ImageToMail.this.user,ImageToMail.this.password);  
       }  
      });  
         
      try{  
        MimeMessage message = new MimeMessage(session);  
        message.setFrom(new InternetAddress(user));  
        message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));  
        message.setSubject("Cab Driver Details");  
          
        //3) create MimeBodyPart object and set your message text     
        BodyPart messageBodyPart1 = new MimeBodyPart();  
        messageBodyPart1.setText(content);  
          
        //4) create new MimeBodyPart object and set DataHandler object to this object      
        MimeBodyPart messageBodyPart2 = new MimeBodyPart();  
      
        
        DataSource source = new FileDataSource(encMailFile);  
        messageBodyPart2.setDataHandler(new DataHandler(source));  
        //messageBodyPart2.setFileName(encMailFile);  
        messageBodyPart2.setFileName(encMailFile.substring(encMailFile.lastIndexOf("/")+1));  
         
         
        //5) create Multipart object and add MimeBodyPart objects to this object      
        Multipart multipart = new MimeMultipart();  
        multipart.addBodyPart(messageBodyPart1);  
        multipart.addBodyPart(messageBodyPart2);  
      
        //6) set the multiplart object to the message object  
        message.setContent(multipart );  
         
        //7) send message  
        Transport.send(message);  
       flag=true;
       System.out.println("message sent....");  
       }catch (MessagingException ex) {ex.printStackTrace();}  
      return flag;
    }
    public static void main(String [] args)throws Exception
    {    
      //ImageToMail itm=new ImageToMail();
      //itm.sendImage("madhutechskills09@gmail.com", "karthiksecure99@gmail.com", "karthikeyaasd");
     }  
    }  