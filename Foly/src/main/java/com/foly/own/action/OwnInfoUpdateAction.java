package com.foly.own.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.foly.own.db.OwnMainDAO;
import com.foly.own.db.OwnMainDTO;
import com.foly.util.Action;
import com.foly.util.ActionForward;
import com.foly.util.JSMethod;

public class OwnInfoUpdateAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println(" M : OwnInfoUpdateAction_execute() 호출 ");
		
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
		
		// 3) DTO 객체 - 내정보 수정 정보 저장
		OwnMainDTO dto = new OwnMainDTO();
		
		dto.setOwn_id(own_id);
		dto.setOwn_pw(request.getParameter("own_pw"));
		dto.setOwn_name(request.getParameter("own_name"));
		dto.setOwn_tel(request.getParameter("own_tel"));
		dto.setOwn_email(request.getParameter("own_email"));
		
		System.out.println(" M : 수정할 데이터");
		System.out.println(" M : " + dto);
		

		// DAO 객체 - 정보 수정 메서드
		OwnMainDAO dao = new OwnMainDAO();
		int result = dao.OwnInfoUpdate(dto);
		
		// 4) 수정 메서드 결과에 따른 페이지 이동(JS)
		if(result == -1) {
			
			JSMethod.alertBack(response, " 정보 없음");
			
			// js이동 수행시 컨트롤러 이동x를 위해 null값을 리턴
			return null;
			
		}else { // result == 1
			
			JSMethod.alertLocation(response, "수정 완료!", "./OwnInfo.ow");
			
		}
		return null;
	}

}
