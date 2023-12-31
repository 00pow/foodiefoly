package com.foly.own.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.foly.own.db.OwnMainDAO;
import com.foly.own.db.OwnMainDTO;
import com.foly.util.Action;
import com.foly.util.ActionForward;

public class OwnRestaurantUpdatePro implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		// 세션제어
		HttpSession session = request.getSession();
				
		String own_id = (String)session.getAttribute("own_id");
				
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
		System.out.println(request.getParameter("res_name"));
		OwnMainDTO dto = new OwnMainDTO();
		
		dto.setRes_img(request.getParameter("res_img"));
		dto.setRes_name(request.getParameter("res_name"));
		dto.setRes_tel(request.getParameter("res_tel"));
		dto.setRes_addr(request.getParameter("res_addr"));
		dto.setRes_deposit(request.getParameter("res_deposit"));
		dto.setRes_type(request.getParameter("res_type"));
		dto.setRes_mng(request.getParameter("res_mng"));
		dto.setRes_stH(request.getParameter("res_stH"));
		dto.setRes_stM(request.getParameter("res_stM"));
		dto.setRes_etH(request.getParameter("res_etH"));
		dto.setRes_etM(request.getParameter("res_etM"));
		
		OwnMainDAO dao = new OwnMainDAO();
		
		dao.resUpdate(dto, own_id);
		
		forward = new ActionForward();
		forward.setPath("./OwnRestaurant.ow");
		forward.setRedirect(true);
		
		
		return forward;
	}
	
}
