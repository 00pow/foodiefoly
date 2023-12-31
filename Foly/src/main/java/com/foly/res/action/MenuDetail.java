
package com.foly.res.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.foly.res.db.ResDAO;
import com.foly.res.db.ResDTO;
import com.foly.util.Action;
import com.foly.util.ActionForward;

public class MenuDetail implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println(" M : MenuDetail_execute 호출 ");
		
		
		// 로그인을 안한 경우 
		HttpSession session = request.getSession();
		String us_id = (String)session.getAttribute("us_id");
		
	
		
		// 받아온 정보 저장
		int me_num = Integer.parseInt(request.getParameter("me_num"));
		String own_id = request.getParameter("own_id");
		String res_name = request.getParameter("res_name");
		
		System.out.println("@MenuDetail@"+me_num);
		System.out.println("@MenuDetail@"+own_id);
		System.out.println("@MenuDetail@"+res_name);
		
		ResDAO dao = new ResDAO();		
		ResDTO menuDetail = dao.getMenuDetail(own_id,me_num);
		
		System.out.println(menuDetail);
		request.setAttribute("menuDetail", menuDetail);
		
		request.setAttribute("me_num", me_num);
		request.setAttribute("own_id", own_id);
		request.setAttribute("res_name", res_name);
		
		ActionForward forward = new ActionForward();
		forward.setPath("./res/menuDetail.jsp");
		forward.setRedirect(false);
		
		return forward;
		
	}

}
