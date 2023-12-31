package com.foly.cs.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.foly.cs.db.CsDAO;
import com.foly.cs.db.CsDTO;
import com.foly.util.Action;
import com.foly.util.ActionForward;


public class FaqDetailAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 전달정보 저장 bno, pageNum
		int faq_num = Integer.parseInt(request.getParameter("faq_num"));
		
		// DAO - 조회수 1증가() 
		CsDAO dao = new CsDAO(); 
				
		// DAO - 특정글 정보 조회(bno)
		CsDTO dto = dao.getFaq(faq_num);  
		
		// request 영역에 정보 저장
		request.setAttribute("dto", dto);
		// request.setAttribute("pageNum", pageNum);
		
		// 페이지 이동
		ActionForward forward = new ActionForward();
		forward.setPath("./cs/faqDetail.jsp?faq_num="+faq_num);
		forward.setRedirect(false); 
		
		return forward;
	}


}
