package com.foly.login.action;



import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.foly.login.db.LoginDAO;
import com.foly.util.Action;
import com.foly.util.ActionForward;

/*
 *  UserFindIdAction - 유저 아이디 찾기에 필요한 동작 처리(이름, 이메일 확인)
 *  
 *  
 * */
public class UserFindIdAction implements Action {
	
	@Override
	public ActionForward execute(HttpServletRequest request,
								HttpServletResponse response) throws Exception{
	
		System.out.println(" M : UserFindIdAction_execute() 호출 ");
		
		// 한글 처리 인코딩
//		request.setCharacterEncoding("UTF-8");
		
		// 전달정보 저장 (us_name, us_email)
		String us_name = request.getParameter("us_name");
		String us_email = request.getParameter("us_email");
		
		int result = -1;
		String end = null;
	 //  3) DAO 객체 생성 - userFIndIdCheck(us_name,us_email);
		LoginDAO dao = new LoginDAO();
		String us_id = dao.userFindIdCheck(us_name,us_email);
		if(us_id.equals("찾는아이디없음")) {
			result = -1;
		}
		else {
			result = 1;
		}
		
		System.out.println(" M : 로그인 결과(" +result+")");
		
		
		// DB에서 전달된 정보에 따른 페이지 이동	
		if(result == -1) {
			// 비회원 -> 페이지이동 (JS)
			// 사용자가 보는 화면은 html 형식을 띄게 하면서
			response.setContentType("text/html; charset=UTF-8");
			// 글을 쓸 수 있게 해준다
			PrintWriter out = response.getWriter();
			
			//out.println("HTML 코드 사용 가능1");
			out.println("<script>");
			out.println(" alert('회원정보가 없습니다.');");
			out.println(" history.back();");
			out.println("</script>");
			
			out.close();
			
			// 컨트롤러의 페이지 이동 막음 
			return null;
			
		}else {
			// result == 1
			// 정보찾기 성공 -> 페이지 이동(forward)
			
			// 사용자가 보는 화면은 html 형식을 띄게 하면서
			response.setContentType("text/html; charset=UTF-8");
			// 글을 쓸 수 있게 해준다
			PrintWriter out = response.getWriter();
			
			out.println("HTML 코드 사용 가능2");
			out.println("<script>");
			out.println(" alert('"+us_name+"님의 아이디는 "+us_id+" 입니다')");
			out.print("location.href='./Main.lo'; ");
			out.println("</script>");
			
			out.close();		
			
			
			// 컨트롤러의 페이지 이동
			return null;
			
		}// else
	 
		 
			
	}
}


