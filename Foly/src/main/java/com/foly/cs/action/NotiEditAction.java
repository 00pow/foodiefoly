package com.foly.cs.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.foly.cs.db.CsDAO;
import com.foly.cs.db.CsDTO;
import com.foly.util.Action;
import com.foly.util.ActionForward;
import com.foly.util.JSMethod;

public class NotiEditAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println(" M : FaqEditAction_execute 호출 ");

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
		

		// 전달정보 저장(수정할 데이터)
		CsDTO dto = new CsDTO();

		dto.setNoti_num(Integer.parseInt(request.getParameter("noti_num")));
		dto.setNoti_sub(request.getParameter("noti_sub"));
		dto.setNoti_cont(request.getParameter("noti_cont"));

		// DAO - 정보수정메서드 호출
		CsDAO dao = new CsDAO();
		int result = dao.notiEdit(dto);

		// 수정 처리 결과에 따른 페이지 이동(JS)
		if (result == 0) {
			JSMethod.alertBack(response, "글 수정 실패!");
			return null;
		}
		// result == 1
		JSMethod.alertLocation(response, "수정 완료", "./CsCenter.cs");

		return null;
	}
}
