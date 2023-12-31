package com.foly.cs.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.foly.cs.db.CsDAO;
import com.foly.cs.db.CsDTO;
import com.foly.util.Action;
import com.foly.util.ActionForward;

public class FaqWriteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println(" C : NotiWriteAction_execute() 실행 ");

		// 로그인 정보 (관리자계정만 기능)
		HttpSession session = request.getSession();
		String us_id = (String) session.getAttribute("us_id");
		ActionForward forward = new ActionForward();
		if(us_id.equals("admin")) {
			
		}else {
				
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
		

		CsDTO dto = new CsDTO();

		dto.setFaq_sub(request.getParameter("faq_sub"));
		dto.setFaq_cont(request.getParameter("faq_cont"));

		System.out.println(" M : " + dto);

		CsDAO dao = new CsDAO();
		dao.insertFaq(dto);

		// 페이지 이동
		forward.setPath("./Faq.cs");
		forward.setRedirect(true);

		return forward;
	}

}
