package com.foly.user.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.foly.user.db.UserDAO;
import com.foly.util.Action;
import com.foly.util.ActionForward;

public class MypagePwCheckAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		System.out.println(" M : MypagePwCheckAction_execute() 호출 ");
		 
			// pro.jsp 페이지의 동작 수행
		
			// 한글 처리 인코딩
			// request.setCharacterEncoding("UTF-8");
		
			// 세션정보 확인
			// 로그인을 안한 경우 
			HttpSession session = request.getSession();
			String us_id = (String)session.getAttribute("us_id");
				
			ActionForward forward = new ActionForward();	
			
			if (us_id == null) {
				// 사용자가 보는 화면은 html 형식을 띄게 하면서
				response.setContentType("text/html; charset=UTF-8");
				// 글을 쓸 수 있게 해준다
				PrintWriter out = response.getWriter();
						
				out.println("HTML 코드 사용 가능");
				out.println("<script>");
				out.println("alert('로그인이 필요합니다.');");
				out.println("location.href='./UserLogin.lo';");
				out.println("</script>");
						
				out.close();
						
				// 컨트롤러의 페이지 이동 막음 
				return null;
			}	
			
			// 전달정보 저장 (PW)
			String us_pw = request.getParameter("us_pw");
			
			
			//3) DAO 객체 생성 - userPwCheck(us_pw);
			UserDAO dao = new UserDAO();
			int result = dao.userPwCheck(us_id,us_pw);
			System.out.println(" M : 비밀번호 확인 결과(" +result+")");
			
			
		// DB에서 전달된 정보에 따른 페이지 이동	
		if(result == -1) {
			// 비회원 -> 페이지이동 (JS)
			// 사용자가 보는 화면은 html 형식을 띄게 하면서
			response.setContentType("text/html; charset=UTF-8");
			// 글을 쓸 수 있게 해준다
			PrintWriter out = response.getWriter();
			
			out.println("HTML 코드 사용 가능");
			out.println("<script>");
			out.println(" alert('회원정보 없음!!');");
			out.println(" history.back();");
			out.println("</script>");
			
			out.close();
			
			// 컨트롤러의 페이지 이동 막음 
			return null;
			
		
		}else if(result == 0){
			// 비밀번호 오류 -> 페이지이동 (JS)
			
			// 사용자가 보는 화면은 html 형식을 띄게 하면서
			response.setContentType("text/html; charset=UTF-8");
			// 글을 쓸 수 있게 해준다
			PrintWriter out = response.getWriter();
					
			out.println("HTML 코드 사용 가능");
			out.println("<script>");
			out.println(" alert('비밀번호 오류!!');");
			out.println(" history.back();");
			out.println("</script>");
					
			out.close();
					
			// 컨트롤러의 페이지 이동 막음 
			return null;
			
		}else {
			// result == 1
			// 로그인 성공 -> 페이지 이동(forward)
			
			// 비회원 -> 페이지이동 (JS)
			
			
			// 페이지 이동(forward)
			forward = new ActionForward();
			// 목적지 입력(main페이지의 가상주소 입력)
			forward.setPath("./MypageUpdate.us");  
			// 이동방식 입력 (가상주소 -> 가상주소 이므로 주소도 바뀌고 화면도 바뀌므로 true)
			forward.setRedirect(true);
			
			System.out.println(" M : " + forward);
			
			
			
			// 컨트롤러의 페이지 이동 막음 
			return forward;
			
		}// else
		 
			
	}

}

