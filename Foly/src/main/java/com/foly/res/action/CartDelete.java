package com.foly.res.action;



import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.foly.res.db.ResDAO;
import com.foly.util.Action;
import com.foly.util.ActionForward;

/*
 * 	CartDelete - 장바구니에 메뉴 추가 동작
 * 

 *  
 * */
public class CartDelete implements Action {
	
	@Override
	public ActionForward execute(HttpServletRequest request,
								HttpServletResponse response) throws Exception{
	
		System.out.println(" M : CartDelete_execute() 호출 ");
		 

		// 세션정보 확인
		// 로그인을 안한 경우 
		HttpSession session = request.getSession();
		String us_id = (String)session.getAttribute("us_id");
			
		ActionForward forward = new ActionForward();
		if (us_id == null) {
			// 사용자가 보는 화면은 html 형식을 띄게 하면서
			response.setContentType("text/html; charset=UTF-8");
			// 글을 쓸 수 있게 해준다
			PrintWriter out = response.getWriter();
					
			out.println("<script>");
			out.println("alert('로그인이 필요합니다.');");
			out.println("location.href='./UserLogin.lo';");
			out.println("</script>");
					
			out.close();
					
			// 컨트롤러의 페이지 이동 막음 
			return null;
		}	


			// DB연결 -> DAO 객체 - 글쓰기 메서드
			ResDAO dao = new ResDAO();
			
			
			// 장바구니에 물품 모두 삭제 
			 dao.cartDelete(us_id);
			
			
			// 사용자가 보는 화면은 html 형식을 띄게 하면서
			response.setContentType("text/html; charset=UTF-8");
			// 글을 쓸 수 있게 해준다
			PrintWriter out = response.getWriter();
					
			out.println("<script>");
			out.println(" alert('모두 삭제 완료');");
			out.println(" location.href='./UserMain.lo';");
			out.println("</script>");
					
			out.close();
					
			// 컨트롤러의 페이지 이동 막음 
			return null;
				
			
	}

}
