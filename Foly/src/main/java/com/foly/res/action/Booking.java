package com.foly.res.action;


import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.foly.res.db.ResDAO;
import com.foly.res.db.UserBookingDTO;
import com.foly.user.db.UserDTO;
import com.foly.util.Action;
import com.foly.util.ActionForward;

public class Booking implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println(" M : BookingAction_execute() 호출 ");

		// 정보 받아오기
		String own_id = request.getParameter("own_id");
		
		// 로그인을 안한 경우 
		HttpSession session = request.getSession();
		String us_id = (String)session.getAttribute("us_id");
		session.setAttribute("own_id", own_id);
		
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


			// DTO 객체 생성
		UserBookingDTO UserBookingPage = new UserBookingDTO();



//		 DAO 객체 - getDeposit()
		ResDAO dao = new ResDAO();
		UserBookingPage = dao.getBookingPage(own_id);
		UserDTO usDto = dao.bookingUser(us_id);
		
		request.setAttribute("UserBookingPage", UserBookingPage);
		request.setAttribute("usDto", usDto);

		ActionForward forward = new ActionForward();
		forward.setPath("./res/resBooking.jsp");
		forward.setRedirect(false);

		return forward;
	}
}
