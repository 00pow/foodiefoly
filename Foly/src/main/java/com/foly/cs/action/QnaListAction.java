package com.foly.cs.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.foly.cs.db.CsDAO;
import com.foly.cs.db.CsDTO;
import com.foly.util.Action;
import com.foly.util.ActionForward;

public class QnaListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println(" M : BoardContentAction_execute() 호출 ");
		// 1. DAO 객체를 생성해서 데이터를 가져온다.
			CsDAO dao = new CsDAO();
			
			// 작성되어있는 전체 메뉴의 개수 계산(DB메서드) => Model
	        int count = dao.getQnaListCount();  // 글 개수 count 해주는 메서드
	        System.out.println(" M : 전체 글 개수 : " + count);
			
			// 페이징 처리-1 => Model
			//////////////////페이징처리-1///////////////////////////////
			
			// 페이지당 출력할 글의 개수
			int pageSize = 10;  // 한페이지에 10개씩 출력
			
			// 페이지의 정보(몇페이지인지 확인하는 정보)
			String pageNum = request.getParameter("pageNum");
			
			if(pageNum == null){
				pageNum = "1"; //페이지 정보가 없을 경우 1페이지로 고정(기본값)
			}
			
			// 시작행 번호 계산 공식	1 11 21 31 .....
			int currentPage = Integer.parseInt(pageNum);

			int startRow = (currentPage-1)*pageSize +1  ;
			
			// 끝행 번호 계산 10 20 30 40...
			int endRow = currentPage * pageSize;
			
			//////////////////////페이징처리-1/////////////////////////d
			
			//페이징 처리된 메뉴 데이터를 가져오기(DB메서드) => Model
			// OwnMenuDAO 객체 - getOwnMenuInfo(own_id);
			ArrayList<CsDTO> qnaList = null;
			if(count > 0) {
				qnaList = dao.qnaList(startRow, pageSize);
			}
			
			
			// 테이블에 출력(반복문) => view

			// 페이징 처리 -2 => Model
			
			/////////// 페이징 처리 -2 ///////////
			
				// 전체 페이지 수 => 글/페이지당 출력 개수
				//				50/ 10 => 5	55 / 10 => 6
				
				int pageCount = count / pageSize + (count % pageSize != 0? 1 : 0);
				
				// 한 화면에서 보여줄 페이지번호 개수(block)  1....10
				int pageBlock = 10;
				
				// 페이지블럭의 시작번호  1~10 => 1 , 11~20 => 11, 21~30 => 21
				// (currentPage - 1) 의 값이 1을 넘지 못하면 정수이므로 0이다. 
			int startPage = ((currentPage - 1)/ pageBlock) * pageBlock + 1;		
				
				// 페이지 블럭의 끝 번호  1~10 => 10, 11~20 => 20, 21~30 => 30
				int endPage = startPage + pageBlock - 1;
				if(endPage > pageCount){
					endPage = pageCount;
				}

			
			/////////// 페이징 처리 -2 ///////////
		
		// 2. 가져온 데이터를 request 영역에 저장한다.
			request.setAttribute("qnaList", qnaList);
			request.setAttribute("pageNum", pageNum);
			request.setAttribute("count", count);
			request.setAttribute("pageCount", pageCount);
			request.setAttribute("pageBlock", pageBlock);
			request.setAttribute("endPage", endPage);
			request.setAttribute("startPage", startPage);
		
		// 3. 페이지 이동 (포워드 방식을 사용하여 데이터를 JSP 페이지로 전달한다.)
			ActionForward forward = new ActionForward();
			forward.setPath("/./cs/qna.jsp"); 
			forward.setRedirect(false); // 포워드 방식
	
			return forward;
	}

}
