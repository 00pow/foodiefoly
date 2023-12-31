package com.foly.login.action;



import java.io.PrintWriter;
import java.sql.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.foly.login.db.LoginDAO;
import com.foly.login.db.UserLoginDTO;
import com.foly.util.Action;
import com.foly.util.ActionForward;

/*
 * 	UserJoinAction - (개인)회원가입에 필요한 동작 처리
 * 	insertPro.jsp 페이지의 역할 수행
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
public class UserJoinAction implements Action {
	
	@Override
	public ActionForward execute(HttpServletRequest request,
								HttpServletResponse response) throws Exception{
	
		System.out.println(" M : UserJoinAction_execute() 호출 ");
		 
		
		// 한글 처리
			request.setCharacterEncoding("UTF-8");
		 //  1) 정보전달 받기(+한글인코딩)
		 //  2) 객체정보를 정보 사용 파라메터 저장
		   
		 //	String id = request.getParameter("id");
		 //	System.out.println("M : id=" + id);
		   
			
		 // UserLoginDTO 객체 생성
			UserLoginDTO dto = new UserLoginDTO();
			
			dto.setUs_id(request.getParameter("us_id"));
			dto.setUs_pw(request.getParameter("us_pw"));
			dto.setUs_name(request.getParameter("us_name"));
			dto.setUs_nick(request.getParameter("us_nick"));
			dto.setUs_email(request.getParameter("us_email"));
			
			dto.setUs_tel(request.getParameter("us_tel"));
			dto.setUs_birY(request.getParameter("us_birY"));
			dto.setUs_birM(request.getParameter("us_birM"));
			dto.setUs_birD(request.getParameter("us_birD"));
			dto.setUs_addr(request.getParameter("us_addr"));
			
			
			dto.setUs_regdate(new Date(System.currentTimeMillis()));
			
			System.out.println(" M1 : " + dto);
			
			
		 //  3) DAO 객체 생성 - 로그인 메서드
			LoginDAO dao = new LoginDAO();
			dao.insertUser(dto);
			
			// 알림
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println(" alert(' 회원가입 완료! ')");
			out.println(" ");
			out.println(" ");
			out.println("</script>");
			


		 //  4) 페이지 이동(로그인페이지)	
		 //  => 페이지 이동 무조건 컨트롤러에서!
		 //  => 페이지 이동정보만 저장(티켓생성)
		 ActionForward forward = new ActionForward();
		 
		 // 목적지 입력
		 forward.setPath("./Main.lo");
		 // 이동방식 입력 
		 forward.setRedirect(true);
			
		 System.out.println(" M : " + forward);
		 System.out.println(" M : 실제 페이지이동 X, 페이지이동에 필요한 티켓 생성");
			
		return forward;
	}

}
