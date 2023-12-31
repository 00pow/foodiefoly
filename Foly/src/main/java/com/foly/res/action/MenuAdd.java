package com.foly.res.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.foly.res.db.ResDAO;
import com.foly.res.db.UserBookingDTO;
import com.foly.util.Action;
import com.foly.util.ActionForward;

public class MenuAdd implements Action{
	
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println(" M : MenuAdd_execute() 호출 ");
		
		// 세션정보 확인
		// 로그인을 안한 경우 
		HttpSession session = request.getSession();
		String us_id = (String)session.getAttribute("us_id");
		
		// 같은 가게인지 비교용
		String own_id = request.getParameter("own_id");
		int result = -1;
					
		if(us_id == null) {
			
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
		
		
		// CartDTO 객체 생성
		UserBookingDTO dto = new UserBookingDTO();
	 
		 dto.setOwn_id(request.getParameter("own_id"));
		 dto.setRes_name(request.getParameter("res_name"));
		 
		 dto.setUs_id(us_id);
		 
		 dto.setMe_img(request.getParameter("me_img"));
		 dto.setMe_num(Integer.parseInt(request.getParameter("me_num")));
		 dto.setMe_name(request.getParameter("me_name"));
		 dto.setMe_price(Integer.parseInt(request.getParameter("me_price")));
		 dto.setMe_exp(request.getParameter("me_exp"));
		 dto.setMe_amount(Integer.parseInt(request.getParameter("me_amount")));
		 

		 System.out.println("@MenuAdd@"+request.getParameter("me_num"));
		 System.out.println("@MenuAdd@"+request.getParameter("me_img"));
		 System.out.println("@MenuAdd@"+us_id);
		 System.out.println(" M : "+dto);
		 
		// DAO 객체 - getInsertCart()
		ResDAO dao = new ResDAO();
		result = dao.addMenu(dto, us_id, own_id);
		
		if(result == 1 ) {
			
			// 사용자가 보는 화면은 html 형식을 띄게 하면서
			response.setContentType("text/html; charset=UTF-8");
			// 글을 쓸 수 있게 해준다
			PrintWriter out = response.getWriter();
					
			out.println("<script>");
			out.println(" alert('상품을 장바구니에 담았습니다.');");
			out.println(" history.back();");
			out.println("</script>");
					
			out.close();
					
			// 컨트롤러의 페이지 이동 막음 
			return null;

			
		}
		else {
			
			// 사용자가 보는 화면은 html 형식을 띄게 하면서
			response.setContentType("text/html; charset=UTF-8");
			// 글을 쓸 수 있게 해준다
			PrintWriter out = response.getWriter();
					
			out.println("HTML 코드 사용 가능");
			out.println("<script>");
			out.println(" alert('같은 가게의 메뉴만 가능합니다.');");
			out.println(" history.back();");
			out.println("</script>");
					
			out.close();
					
			// 컨트롤러의 페이지 이동 막음 
			return null;

			
		}
		
	}	 

}
