package com.foly.own.action;

import java.io.PrintWriter;
import java.sql.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.foly.own.db.OwnMainDAO;
import com.foly.own.db.OwnMainDTO;
import com.foly.util.Action;
import com.foly.util.ActionForward;
import com.foly.util.JSMethod;

public class OwnDelAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println(" M : OwnDelAction_execute 호출");
		
		HttpSession session = request.getSession();
		String own_id = (String) session.getAttribute("own_id"); 
		
		ActionForward forward = new ActionForward();
		if (own_id == null) {
			// 사용자가 보는 화면은 html 형식을 띄게 하면서
			response.setContentType("text/html; charset=UTF-8");
			// 글을 쓸 수 있게 해준다
			PrintWriter out = response.getWriter();
					
			out.println("HTML 코드 사용 가능");
			out.println("<script>");
			out.println("alert('로그인이 필요합니다.');");
			out.println("location.href='./OwnLogin.lo';");
			out.println("</script>");
					
			out.close();
					
			// 컨트롤러의 페이지 이동 막음 
			return null;
		}	
		String own_pw = request.getParameter("own_pw");
		OwnMainDTO dto = new OwnMainDTO();
		dto.setOwn_id(own_id);
		dto.setOwn_pw(own_pw);
		dto.setOwn_od(new Date(System.currentTimeMillis()));
				
		OwnMainDAO dao = new OwnMainDAO();
		int result = dao.ownDelete(dto);
		
			if (result == -1) {
			
			JSMethod.alertBack(response, "사업자정보 없음");

			return null; // js이동 수행시 컨트롤러 이동X
			} else if (result == 0) {
				
				JSMethod.alertBack(response, "비밀번호 오류");
				
				return null; // js이동 수행시 컨트롤러 이동X
				} else { // result == 1
				
				JSMethod.alertLocation(response,"탈퇴 완료", "./OwnLogin.lo");
			}
		return null;
	}

}
