package com.foly.cs.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.foly.cs.db.CsDAO;
import com.foly.util.Action;
import com.foly.util.ActionForward;


public class QnaDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		 System.out.println("M: QnaDeleteAction_execute() 호출");

	        // 글 번호 파라미터로 받기
	        int qna_num = Integer.parseInt(request.getParameter("qna_num"));

	        // DAO 객체 생성
	        CsDAO csDAO = new CsDAO();

	        // 해당 번호의 글 삭제 메서드 호출
	        csDAO.deleteQna(qna_num);

	        // 페이지 이동 (리다이렉트)
	        ActionForward forward = new ActionForward();
	        forward.setPath("./Qna.cs"); 
	        forward.setRedirect(true); // 리다이렉트 방식

	        return forward;
	    }
}
