package com.foly.cs.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.foly.cs.db.CsDAO;
import com.foly.util.Action;
import com.foly.util.ActionForward;

public class NotiDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("M: QnaDeleteAction_execute() 호출");

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
		
		// 글 번호 파라미터로 받기
		int noti_num = Integer.parseInt(request.getParameter("noti_num"));

		// DAO 객체 생성
		CsDAO csDAO = new CsDAO();

		// 해당 번호의 글 삭제 메서드 호출
		csDAO.deleteNoti(noti_num);

		// 페이지 이동 (리다이렉트)
		forward.setPath("./CsCenter.cs");
		forward.setRedirect(true); // 리다이렉트 방식

		return forward;
	}
}
