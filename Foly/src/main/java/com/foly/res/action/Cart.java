package com.foly.res.action;



import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.foly.res.db.ResDAO;
import com.foly.res.db.UserBookingDTO;
import com.foly.util.Action;
import com.foly.util.ActionForward;

/*
 * 	OwnRestaurant - 매장정보 나타내기
 * 
 * 
 *  
 * */
public class Cart implements Action {
	
	@Override
	public ActionForward execute(HttpServletRequest request,
								HttpServletResponse response) throws Exception{
	
		System.out.println(" M : Cart_execute() 호출 ");
		 
		
		// 세션정보 확인
		// 로그인을 안한 경우 
		HttpSession session = request.getSession();
		String us_id = (String)session.getAttribute("us_id");
			
		ActionForward forward = new ActionForward();
		if (us_id == null) {
			// 사용자가 보는 화면은 html 형식을 띄게 하면서
			response.setContentType("text/html; charset=UTF-8");
			// 글을 쓸 수 있게 해준다
			PrintWriter out = response.getWriter();
					
			out.println("<script>");
			out.println("alert('로그인이 필요합니다.');");
			out.println("location.href='./UserLogin.lo';");
			out.println("</script>");
					
			out.close();
					
			// 컨트롤러의 페이지 이동 막음 
			return null;
		}	
		   
		 // ResDAO 객체
		ResDAO dao = new ResDAO();
		
		// 작성되어있는 전체 글의 개수 계산(DB메서드) => Model
		int count = dao.getUserCartCount(us_id);  // 글 개수 count 해주는 메서드

		System.out.println(" M : 장바구니 메뉴 개수 : " + count);
		
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
		
		//////////////////////페이징처리-1/////////////////////////

		
		// 페이징처리된 글 데이터를 가져오기(DB메서드) => Model
		List<UserBookingDTO> cartList = null;
		if(count > 0) {
			cartList = dao.getUserCartListPage(startRow, pageSize, us_id);
		}
		
		
		// 테이블에 출력(반복문) => view
		
		// 페이징 처리 -2 => Model
		
		/////////// 페이징 처리 -2 ///////////
		
			// 전체 페이지 수 => 글/페이지당 출력 개수
			//					 50/ 10 => 5	55 / 10 => 6
			
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
		
		
		// 페이징처리 블럭 출력 => view
		
		
		// Model => 데이터 처리 동작 (DB사용, 계산동작들..)
		// view => 정보 전달받아서 출력
		
		
		// 계산된 정보를 view 페이지로 전달
		// 1. 리스트 전달(boardList), 2.페이징 처리 정보
	
		//1
		request.setAttribute("cartList", cartList);
		//2
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("count", count);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("pageBlock", pageBlock);
		request.setAttribute("endPage", endPage);
		request.setAttribute("startPage", startPage);
		
		
		// 페이지 이동
		//forward = new ActionForward();
		forward.setPath("./res/cart.jsp");
		forward.setRedirect(false);
		
		return forward;
	}

}
