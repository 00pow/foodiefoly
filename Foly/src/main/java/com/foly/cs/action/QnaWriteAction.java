package com.foly.cs.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.foly.cs.db.CsDAO;
import com.foly.cs.db.CsDTO;
import com.foly.util.Action;
import com.foly.util.ActionForward;

public class QnaWriteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println(" C : NotiWriteAction_execute() 실행 ");
		
		HttpSession session = request.getSession();
		String us_id = (String)session.getAttribute("us_id");
		ActionForward forward = new ActionForward();
		
		if(us_id == null) {
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
		
		dto.setUs_id(request.getParameter("us_id"));
		dto.setQna_sub(request.getParameter("qna_sub"));
		dto.setQna_cont(request.getParameter("qna_cont"));
		
		System.out.println(" M : "+dto);
		
		CsDAO dao = new CsDAO();
		dao.insertQna(dto);
		
		// 페이지 이동
		forward.setPath("./Qna.cs"); 
		forward.setRedirect(true);
		
		return forward;
	}

}