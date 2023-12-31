package com.foly.user.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.foly.user.db.UserqDAO;
import com.foly.user.db.UserqDTO;
import com.foly.util.Action;
import com.foly.util.ActionForward;

public class UserInfoContentUpdate implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println(" M :  UserInfoContentUpdate");
		// 문의글 정보 출력
		HttpSession session = request.getSession();
		String us_id = (String)session.getAttribute("us_id");
		String pageNum = request.getParameter("pageNum");
		

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
		
		
		UserqDAO dao = new UserqDAO();
		UserqDTO dto = dao.getQna(us_id);
		
		request.setAttribute("dto", dto);
		
		ActionForward forward = new ActionForward();
		forward.setPath("./user/userInfoContentUpdate.jsp");
		forward.setRedirect(false);
		return forward;
	}

}
