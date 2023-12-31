package com.foly.res.action;



import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.foly.own.db.OwnMainDTO;
import com.foly.res.db.ResDAO;
import com.foly.res.db.ResDTO;
import com.foly.util.Action;
import com.foly.util.ActionForward;

public class ResDetail implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		// 전달정보 저장
		String own_id = request.getParameter("own_id");
		System.out.println("@ResDetail.java@"+own_id);
		
		// 식당 메뉴정보
		ResDAO dao = new ResDAO();		
		ArrayList<ResDTO> menuInfo = dao.getMenuInfo(own_id);
		
		OwnMainDTO resInfo = dao.getResInfo(own_id);
		
		request.setAttribute("menuInfo", menuInfo);
		request.setAttribute("resInfo", resInfo);
		
		ActionForward forward = new ActionForward();
		forward.setPath("./res/resDetail.jsp");
		forward.setRedirect(false);
		
		return forward;
		
	
	}

}
