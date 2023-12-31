package com.foly.pay.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.catalina.ant.jmx.JMXAccessorQueryTask;

import com.foly.pay.db.PayDAO;
import com.foly.pay.db.PayDTO;
import com.foly.res.db.CartDTO;
import com.foly.util.Action;
import com.foly.util.ActionForward;

public class PayInfoAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println(" M : PayInfoAction");
		
		//세션정보
		HttpSession session = request.getSession();
		String us_id = (String)session.getAttribute("us_id");
		// 전달정보 저장
		String pay_num = (String)session.getAttribute("pay_num");
		String own_id = (String)session.getAttribute("own_id");
		
		System.out.println(us_id+"@@@@@@@@@@@@@us_id");
		System.out.println(pay_num+"@@@@@@@@@@@@@pay_num");
		System.out.println(own_id+"@@@@@@@@@@@@@own_id");
		PayDAO dao = new PayDAO();
		PayDTO dto = dao.payInfo(us_id, pay_num);

		System.out.println(dto+"@@@@@@@@@@@@@@@@@@@@info");
		request.setAttribute("dto", dto);
		// us_name,pay_num,pay_date,pay_total
		
		session.removeAttribute(pay_num);
		session.removeAttribute(own_id);
		
		//session = request.getSession();
		//session.setAttribute("us_id", us_id);
		//request.setAttribute("pay_id", pay_id);

		System.out.println(dto+"@@@@@@@@@@@@@@@@@@@@info");
		ActionForward forward = new ActionForward();
		forward.setPath("./pay/payInfo.jsp");
		forward.setRedirect(false);
		return forward;
		
	}

}
