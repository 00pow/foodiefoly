package com.foly.own.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.foly.own.db.OwnMainDAO;
import com.foly.res.db.ResDTO;
import com.foly.util.Action;
import com.foly.util.ActionForward;
import com.foly.util.JSMethod;


public class OwnMenuDelete implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		System.out.println(" M : ownMenuDelete_execute() 호출 ");

		// 세션정보 확인
		// 로그인을 안한 경우 
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
		
		// 전달정보 저장()
		
//		System.out.println(request.getParameter("res_price"));
//		int res_price = Integer.parseInt(request.getParameter("res_price"));
		int me_num = Integer.parseInt(request.getParameter("me_num"));
		System.out.println(me_num);
		String pageNum = request.getParameter("pageNum");
		System.out.println(pageNum);
//		String pass = request.getParameter("pass");
//		

		// 3) DTO 객체 - 게시판 글삭제전 정보 저장 
		ResDTO dto = new ResDTO();
		dto.setOwn_id(own_id);
		dto.setMe_num(me_num);
//		dto.setRes_menu(request.getParameter("res_menu"));
//		dto.setRes_price(res_price);
//		dto.setRes_exp(request.getParameter("res_exp"));
		
		System.out.println(" M : 삭제할 데이터");
		System.out.println(" M : " + dto);
		

		// DAO 객체 - 글 삭제 메서드
		OwnMainDAO dao = new OwnMainDAO();
		int result = dao.OwnMenuDelete(dto);
		
		// 4) 수정 메서드 결과에 따른 페이지 이동(JS)
		if(result == -1) {
			
			JSMethod.alertBack(response, "글 없음");
			
			return null;
			
		}else { // result == 1
			
			JSMethod.alertLocation(response, "삭제 완료!", "./OwnMenu.ow?pageNum="+pageNum);
			
		}
		
		return null;
	}

}
