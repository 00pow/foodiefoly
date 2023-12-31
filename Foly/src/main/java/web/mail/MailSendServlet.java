package web.mail;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*Servlet implementation class MailSendServlet*/

@WebServlet("/mailSend")
public class MailSendServlet extends HttpServlet{
	
	
	private static final long serialVersionUID = 1L;
	
	/* @see HttpServlet #HttpServlet() */


public MailSendServlet(){
	super();
}

/*
 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
 * response)
 */

protected void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
	
	request.setCharacterEncoding("UTF-8");
	String sender = request.getParameter("sender");
	String receiver = request.getParameter("receiver");
	String subject = request.getParameter("subject");
	String content = request.getParameter("content");
	response.setContentType("text/html;charset=UTF-8");
	PrintWriter out = response.getWriter();
	
	try {
		
		 Properties properties = System.getProperties();
		 properties.put("mail.smtp.starttls.enable", "true");
		 properties.put("mail.smtp.host", "smtp.gmail.com");
		 properties.put("mail.smtp.auth", "true");
		 properties.put("mail.smtp.port", "587");  // gmail포트
		 
		 
		/*
		 * prop.put("mail.smtp.ssl.trust", "smtp.gmail.com");
		 * prop.put("mail.smtp.ssl.protocols", "TLSv1.2");
		 */
        
        Authenticator auth = new GoogleAuthentication();
        Session s = Session.getDefaultInstance(properties, auth);
        Message message = new MimeMessage(s);
	    
        
        Address sender_address = new InternetAddress(sender);
        Address receiver_address = new InternetAddress(receiver);
        
        message.setHeader("content-type","text/html;charset=UTF-8");
        message.setFrom(sender_address);
        message.addRecipient(Message.RecipientType.TO, receiver_address);
        message.setSubject(subject);
        message.setContent(content, "text/html;charset=UTF-8");
        message.setSentDate(new java.util.Date());
        Transport.send(message);
        
        out.println("<script>");
        out.println(" alert('입력하신 이메일을 확인해주세요.')");
		out.print("location.href='Main.lo';");
		out.println("</script>");
		
		out.close();
        
		
	} catch (Exception e) {
        out.println("<script>");
        out.println(" alert('SMTP 서버가 잘못 설정되었거나, 서비스에 문제가 있습니다.')");
		out.print("location.href='Main.lo';");
		out.println("</script>");
		
		// out.println("SMTP 서버가 잘못 설정되었거나, 서비스에 문제가 있습니다.");
		e.printStackTrace();
	}
	
	
	
	}
	                
	   
}
