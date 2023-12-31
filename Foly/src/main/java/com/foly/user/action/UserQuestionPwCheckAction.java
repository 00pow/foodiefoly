package com.foly.user.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.foly.user.db.UserqDAO;
import com.foly.util.Action;
import com.foly.util.ActionForward;
import com.foly.util.JSMethod;

public class UserQuestionPwCheckAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println(" M : UserQuestionPwCheckAction");
		
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
		
		
		String us_pw = request.getParameter("us_pw");
		UserqDAO dao = new UserqDAO();
		int result = dao.usPwCheck(us_id, us_pw);
		System.out.println(" M : 비밀번호 확인 결과(" +result+")");

		if(result == -1) {
			JSMethod.alertBack(response, "비밀번호 오류");
			return null;
		}else{
			forward.setPath("./UserInfoContentUpdate.us");
			forward.setRedirect(true);
		}
		
		return forward;
	}

}
