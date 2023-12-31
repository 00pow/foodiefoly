package com.foly.pay.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.foly.pay.db.PayDAO;
import com.foly.pay.db.PayDTO;
import com.foly.res.db.BoDTO;
import com.foly.util.Action;
import com.foly.util.ActionForward;

public class PayBoInfoAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println(" M : PayBoInfoAction 호출 ");
		
		HttpSession session = request.getSession();
		String us_id = (String)session.getAttribute("us_id");
		String pay_num = (String)session.getAttribute("pay_num");
		String own_id = (String)session.getAttribute("own_id");
		
		System.out.println(us_id+"@@@@@@@@@@@@@us_id");
		System.out.println(pay_num+"@@@@@@@@@@@@@pay_id");
		System.out.println(own_id+"@@@@@@@@@@@@@own_id");
		
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
		
		PayDAO dao = new PayDAO();
		PayDTO dto = dao.payBoInfo(us_id, pay_num);
		request.setAttribute("dto", dto);
		
		BoDTO boDto = dao.boInfo(us_id, pay_num);
		request.setAttribute("boDto", boDto);
		
		System.out.println(dto+"@@@@@@@@@@@@@@@@@@@@info");
		System.out.println(boDto+"@@@@@@@@@@@@@@@@@@@@info");
		
		session.removeAttribute(own_id);
		session.removeAttribute(pay_num);
		
		forward = new ActionForward();
		forward.setPath("./pay/payBoInfo.jsp");
		forward.setRedirect(false);
		return forward;
	}

}
