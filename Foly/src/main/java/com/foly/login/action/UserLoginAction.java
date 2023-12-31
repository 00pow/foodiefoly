package com.foly.login.action;



import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.foly.login.db.LoginDAO; 
import com.foly.util.Action;
import com.foly.util.ActionForward;

/*
 * 	UserLoginAction - 회원가입에 필요한 동작 처리
 * 	LoginPro.jsp 페이지와 같은 역할 수행
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
public class UserLoginAction implements Action {
	
	@Override
	public ActionForward execute(HttpServletRequest request,
								HttpServletResponse response) throws Exception{
	
		System.out.println(" M : UserLoginAction_execute() 호출 ");
		 
			// pro.jsp 페이지의 동작 수행
		
			// 한글 처리 인코딩
//			request.setCharacterEncoding("UTF-8");
			
			// 전달정보 저장 (ID,PW)
			String us_id = request.getParameter("us_id");
			String us_pw = request.getParameter("us_pw");
			
			
		 //  3) DAO 객체 생성 - userCheck(us_id,us_pw);
			LoginDAO dao = new LoginDAO();
			int result = dao.userCheck(us_id,us_pw);
			System.out.println(" M : 로그인 결과(" +result+")");
			
			
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
			
			// 세션에 아이디 정보 저장 
			HttpSession session = request.getSession();
			session.setAttribute("us_id", us_id);
			
			// 페이지 이동(forward)
			ActionForward forward = new ActionForward();
			// 목적지 입력(main페이지의 가상주소 입력)
			forward.setPath("./UserMain.lo");  
			// 이동방식 입력 (가상주소 -> 가상주소 이므로 주소도 바뀌고 화면도 바뀌므로 true)
			forward.setRedirect(true);
			
			System.out.println(" M : " + forward);
			
			
			
			// 컨트롤러의 페이지 이동
			return forward;
			
		}// else
		 
			
	}

}
