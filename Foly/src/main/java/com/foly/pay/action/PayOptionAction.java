package com.foly.pay.action;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.foly.own.db.OwnMainDTO;
import com.foly.pay.db.PayDAO;
import com.foly.res.db.CartDTO;
import com.foly.user.db.UserDTO;
import com.foly.util.Action;
import com.foly.util.ActionForward;

public class PayOptionAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println(" M : PayOptionAction_execute() 호출 ");
		
		//세션정보
		HttpSession session = request.getSession();
		String us_id = (String)session.getAttribute("us_id");
		String own_id = request.getParameter("own_id");
		System.out.println("us_id :"+us_id+"@@@@@@@@@@@@@@@@@@@");
		System.out.println("own_id :"+own_id+"@@@@@@@@@@@@@@@@@@@");
		ActionForward forward = new ActionForward();
		if (us_id == null) {
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
		// DAO 객체 - 가게 정보 가져오기 - 사업자 테이블(식당이름, 식당주소)
		PayDAO dao = new PayDAO();
		OwnMainDTO ownDto = dao.getOptionRes(own_id);
		
		System.out.println("ownDto : "+ownDto);
		//회원정보 가져오기
		UserDTO userList = dao.getOptionUser(us_id);
		
		// 장바구니 정보 가져오기 
		ArrayList<CartDTO> cartList = dao.getCartList(us_id); 
		
		// 장바구니 총합 가져오기
		CartDTO cartTotalPrice = dao.getCartTotalPrice(us_id);
		
		
		
		// DB정보 request 영역에 저장
		request.setAttribute("ownDto", ownDto);
		request.setAttribute("userList", userList);
		request.setAttribute("cartList", cartList);
		request.setAttribute("cartTotalPrice", cartTotalPrice);
		session.setAttribute("own_id", own_id);
		
		forward.setPath("./pay/payOption.jsp");
		forward.setRedirect(false);
		
		return forward;
	}
	
}

