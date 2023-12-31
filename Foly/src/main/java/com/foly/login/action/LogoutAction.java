package com.foly.login.action;



import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.foly.util.Action;
import com.foly.util.ActionForward;

/*
 * 	MemberLogoutAction - 로그아웃에 필요한 동작 처리
 * 	LogoutPro.jsp 페이지와 같은 역할 수행
 * 
 * 
 * 
 * 	1) 정보전달 받기(+한글인코딩)
 *  2) 객체정보를 정보 사용 파라메터 저장
 *  
 *  3) DAO 객체 생성 - 회원가입 메서드
 *  4) 페이지 이동(로그인페이지)	
 *  
 *  JSP 내장객체를 사용X (기본자바객체 POJO)
 *  
 *  C -> M 호출 -> 작업처리(DB처리) -> (티켓)-> C
 *  
 * */
public class LogoutAction implements Action {
	
	@Override
	public ActionForward execute(HttpServletRequest request,
								HttpServletResponse response) throws Exception{
	
		System.out.println(" M : LogoutAction_execute() 호출 ");
		 
			// pro.jsp 페이지의 동작 수행
		
			// 한글 처리 인코딩
			// request.setCharacterEncoding("UTF-8");
			
			// 로그아웃 => 세션 초기화
			HttpSession session = request.getSession();
			session.invalidate();
			
			// 메인페이지로 이동(JS)
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println(" alert(' 로그아웃 완료! ')");
			out.println(" location.href='./Main.lo'; ");
			out.println(" ");
			out.println(" ");
			out.println("</script>");

			
			// 컨트롤러 이동 X (티켓 생성X)
			System.out.println("M : JS 페이지 이동0, ");
			// 컨트롤러의 페이지 이동 막음 
			return null;
			
		}// else
		 
			
	}


