package com.foly.own.action;



import java.io.PrintWriter;
import java.sql.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.foly.login.db.LoginDAO;
import com.foly.login.db.OwnLoginDTO;
import com.foly.login.db.UserLoginDTO;
import com.foly.own.db.OwnMainDAO;
import com.foly.own.db.OwnMainDTO;
import com.foly.util.Action;
import com.foly.util.ActionForward;

/*
 * 	OwnRestaurant - 매장정보 나타내기
 * 
 * 
 *  
 * */
public class OwnOpening implements Action {
	
	@Override
	public ActionForward execute(HttpServletRequest request,
								HttpServletResponse response) throws Exception{
	
		System.out.println(" M : OwnRestaurant_execute() 호출 ");
		 
		
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
		   
		 // OwnMainDAO 객체 - getMemberInfo(id);
		OwnMainDAO dao = new OwnMainDAO();
        OwnMainDTO dto = dao.getOwnRestInfo(own_id);
        // DB에서 가져온 정보를 view페이지로 전달
        //=> request 영역에 정보 저장
        request.setAttribute("dto", dto);
        // 페이지 이동(forward)
        forward.setPath("./owner/ownRestaurant.jsp");
        forward.setRedirect(false);

        return forward;
	}

}
