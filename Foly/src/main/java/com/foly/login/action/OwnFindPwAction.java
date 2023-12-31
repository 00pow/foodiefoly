package com.foly.login.action;



import java.io.PrintWriter;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.foly.login.db.LoginDAO;
import com.foly.util.Action;
import com.foly.util.ActionForward;

import web.mail.GoogleAuthentication;

/*
 *  
 *  OwnFindPwAction - 사업자 비밀번호 찾기에 필요한 동작 처리(이름, 이메일, 아이디 확인)
 *  
 * */
public class OwnFindPwAction implements Action {
	
	@Override
	public ActionForward execute(HttpServletRequest request,
								HttpServletResponse response) throws Exception{
	
		System.out.println(" M : OwnFindPwAction_execute() 호출 ");
		
		// 한글 처리 인코딩
//		request.setCharacterEncoding("UTF-8");
		
		// 전달정보 저장 (own_name, own_email)
		String own_name = request.getParameter("own_name");
		String own_id = request.getParameter("own_id");
		String own_email = request.getParameter("own_email");
		
		
		
		int result = -1;
		String end = null;
	 //  3) DAO 객체 생성 - userFIndPwCheck(us_name,us_email);
		LoginDAO dao = new LoginDAO();
		String own_pw = dao.OwnFindPwCheck(own_name,own_id,own_email);
		if(own_pw.equals("찾는비밀번호없음")) {
			result = -1;
		}
		else {
			result = 1;
		}
		
		System.out.println(" M : 로그인 결과(" +result+")");
		
		// 메일 전달 정보
		String sender = "foodiefoly@gmail.com";
		String receiver = own_email;
		String subject = "FoodieFoly 사장님의 비밀번호를 알려드립니다.";
		String content = "사장님의 비밀번호는 "+own_pw+"입니다.";
		
		// DB에서 전달된 정보에 따른 페이지 이동	
		if(result == -1) {
			// 비회원 -> 페이지이동 (JS)
			// 사용자가 보는 화면은 html 형식을 띄게 하면서
			response.setContentType("text/html; charset=UTF-8");
			// 글을 쓸 수 있게 해준다
			PrintWriter out = response.getWriter();
			
			//out.println("HTML 코드 사용 가능1");
			out.println("<script>");
			out.println(" alert('사장님의 정보가 없습니다.');");
			out.println(" history.back();");
			out.println("</script>");
			
			out.close();
			
			// 컨트롤러의 페이지 이동 막음 
			return null;
			
		}else {
			// result == 1
			// 정보찾기 성공 -> 페이지 이동(forward)
			
			// 메일 보내는 동작
			request.setCharacterEncoding("UTF-8");
//			String sender = request.getParameter("sender");
//			String receiver = request.getParameter("receiver");
//			String subject = request.getParameter("subject");
//			String content = request.getParameter("content");
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
		        
		        
		        // 제대로 전송시 
		        out.println("<script>");
		        out.println(" alert('비밀번호 발송완료. 이메일을 확인해주세요.')");
				out.print("location.href='Main.lo';");
				out.println("</script>");
				
				out.close();
		        
				
			} catch (Exception e) {
		        out.println("<script>");
		        out.println(" alert('SMTP 서버가 잘못 설정되었거나, 서비스에 문제가 있습니다.')");
				out.print("location.href='Main.lo';");
				out.println("</script>");
				out.close();
				
				// out.println("SMTP 서버가 잘못 설정되었거나, 서비스에 문제가 있습니다.");
				e.printStackTrace();
			}
			
			
			
//			// 사용자가 보는 화면은 html 형식을 띄게 하면서
//			response.setContentType("text/html; charset=UTF-8");
//			// 글을 쓸 수 있게 해준다
//			
//			
//			out.println("HTML 코드 사용 가능2");
//			out.println("<script>");
//			out.println(" alert('비밀번호 발송완료.이메일을 확인해주세요.')");
//			out.print("location.href='Main.lo';");
//			out.println("</script>");
//			out.close();		

			// 컨트롤러의 페이지 이동
			return null;
			
		}// else
	 
		 
			
	}
}


