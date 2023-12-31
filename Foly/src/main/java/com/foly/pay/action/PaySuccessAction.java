package com.foly.pay.action;

import java.io.PrintWriter;
import java.sql.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.foly.pay.db.PayDAO;
import com.foly.pay.db.PayDTO;
import com.foly.res.db.BoDTO;
import com.foly.res.db.CartDTO;
import com.foly.util.Action;
import com.foly.util.ActionForward;

public class PaySuccessAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println(" M : PaySuccessAction");
		
		HttpSession session = request.getSession();
		String us_id = (String)session.getAttribute("us_id");
		String own_id = (String)session.getAttribute("own_id");
		
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

		System.out.println("@@@@"+request.getParameter("imp_uid"));
		System.out.println("@@@@"+request.getParameter("merchant_uid"));
		System.out.println("@@@@"+request.getParameter("card_num"));
		System.out.println("@@@@"+request.getParameter("pay_card"));
		System.out.println("@@@@"+request.getParameter("res_name"));
		System.out.println("@@@@"+request.getParameter("pay_total"));
		System.out.println("@@@@"+request.getParameter("us_name"));
		System.out.println("@@@@"+request.getParameter("us_tel"));
		System.out.println("@@@@"+request.getParameter("pay_memo"));
		System.out.println("@@@@"+request.getParameter("me_name"));
		String pay_id = request.getParameter("imp_uid"); //결제 고유번호
		String pay_num = request.getParameter("merchant_uid"); // 주문번호
		String card_num = request.getParameter("card_num "); //카드승인번호
		String pay_card = request.getParameter("pay_card"); //카드사(제공되는지 확인하기)
		String res_name = request.getParameter("res_name");// 결제창에 노출 될 상품명
		int pay_total = Integer.parseInt(request.getParameter("pay_total"));
		String us_name = request.getParameter("us_name");
		String us_tel = request.getParameter("us_tel");
		String pay_memo = request.getParameter("pay_memo");
		String me_name = request.getParameter("me_name");
		
		PayDTO dto = new PayDTO();
		dto.setPay_num(pay_num);
		dto.setPay_id(pay_id);
		dto.setCard_num(card_num);
		dto.setPay_card(pay_card);
		dto.setRes_name(res_name);
		dto.setPay_total(pay_total);
		dto.setUs_name(us_name);
		dto.setPay_date(new Date(System.currentTimeMillis()));
		dto.setUs_tel(us_tel);
		dto.setPay_memo(pay_memo);
		dto.setUs_id(us_id);
		dto.setOwn_id(own_id);
		
		PayDAO dao = new PayDAO();
		dao.payInsert(dto);
		
		BoDTO boDto = new BoDTO();
		boDto.setPay_num(pay_num);
		boDto.setUs_id(us_id);
		boDto.setOwn_id(own_id);
		boDto.setBo_price(pay_total);
		boDto.setBo_menu(me_name);
		
		dao.boPayInsert(boDto,us_id);
		 
		CartDTO delDto = dao.cartDelete(us_id);
		 
		request.setAttribute("delDto ",delDto );
		session.setAttribute("pay_num", pay_num);
		System.out.println(pay_num+"@@@@@@@@@@@@@@@@@@@@@@@@pay_num");
		
		forward = new ActionForward();
		forward.setPath("./PayInfo.pay");
		forward.setRedirect(true);
		
		return forward;
	}

}
