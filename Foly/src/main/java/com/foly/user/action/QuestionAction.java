package com.foly.user.action;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.foly.user.db.UserqDAO;
import com.foly.user.db.UserqDTO;
import com.foly.util.Action;
import com.foly.util.ActionForward;

public class QuestionAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("M : QuestionAction");
		
		HttpSession session = request.getSession();
		String us_id = (String)session.getAttribute("us_id");
		
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
		
		UserqDAO dao = new UserqDAO();
		int count = dao.getQnaCount(us_id);
		System.out.println(" M : 전체 글 개수 : "+count+"개");
		
		// 페이징 처리 -1 => Model
		///////////////////////////////페이징처리-1///////////////////////////
		// 페이지당 출력할 글의 개수
		int pageSize = 5; //한 페이지에 10개씩 출력
		
		// 페이지의 정보 (몇페이지 인지 확인하는 정보)
		String pageNum = request.getParameter("pageNum");
		
		if(pageNum == null){
		pageNum = "1"; //페이지 정보가 없을경우 1페이지로 고정(기본값)
		
		}
		// 시작행 번호 계산	1	11	21	31.....
		int currentPage = Integer.parseInt(pageNum);
		
		int startRow = (currentPage-1)*pageSize+1; 
		
		// 끝행 번호 계산	10	20	30	40
		int endRow = currentPage * pageSize;
		///////////////////////////////페이징처리-1///////////////////////////		
		ArrayList<UserqDTO> qList = null;
		if(count>0) {			
			qList = dao.getQuestionList(startRow, pageSize, us_id);
		}
		// 페이징 처리 -2 => Model
		
					// 전체 페이지 수 => 글 / 페이지당 출력 개수
					// 					 50 / 10 => 5		55 / 10 => 6
					int pageCount = count / pageSize + (count % pageSize != 0? 1:0);
					
					// 한 화면에서 보여줄 페이지 번호 개수(block)
					int pageBlock = 5;
					
					// 페이지 블럭의  시작 번호 1~10 => 1	11~3 => 11	21~30 => 21
					int startPage = ((currentPage -1) / pageBlock) * pageBlock +1;		
					
					// 페이지 블럭의 끝 번호 1~10 => 10	11~20 => 20 	21~30 => 30
					int endPage = startPage + pageBlock -1;
					if(endPage > pageCount)	{
						endPage = pageCount;
					}
				
				// 페이징 처리 블럭 출력 => view
				
				request.setAttribute("qList", qList);
				
				request.setAttribute("pageNum", pageNum);
				request.setAttribute("count", count);
				request.setAttribute("pageCount", pageCount);
				request.setAttribute("pageBlock", pageBlock);
				request.setAttribute("startPage", startPage);
				request.setAttribute("endPage", endPage);
		
		forward.setPath("./user/question.jsp");
		forward.setRedirect(false);
		return forward;
		
	}
}
