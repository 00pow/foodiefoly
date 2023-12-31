
package com.foly.user.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.foly.user.db.UserDAO;
import com.foly.user.db.UserDTO;
import com.foly.util.Action;
import com.foly.util.ActionForward;

public class MypageUpdateAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, 
			HttpServletResponse response) throws Exception {
		System.out.println(" M : UserInfoUpdateAction_execute() 호출 ");
		

		
		// 세션정보 확인
		// 로그인을 안한 경우 
		HttpSession session = request.getSession();
		String us_id = (String)session.getAttribute("us_id");

		
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
		
		// 수정할 정보(파라메터)를 DTO 객체에 저장
		UserDTO dto = new UserDTO();
		
		dto.setUs_id(us_id);
		dto.setUs_name(request.getParameter("us_name"));
		dto.setUs_pw(request.getParameter("us_pw"));
		dto.setUs_nick(request.getParameter("us_nick"));
		dto.setUs_birY(request.getParameter("us_birY"));
		dto.setUs_birM(request.getParameter("us_birM"));
		dto.setUs_birD(request.getParameter("us_birD"));
		dto.setUs_tel(request.getParameter("us_tel"));
		dto.setUs_addr(request.getParameter("us_addr"));
		
		
		System.out.println(" M : 수정할 데이터 ");
		System.out.println(" M : "+dto);
		
		// DAO 객체 - 정보 수정 메서드
		
		UserDAO dao = new UserDAO();
		int result = dao.userInfoUpdate(dto);
		
		// 수정 메서드 결과에 따른 페이지 이동(JS)
		// result 가 -1, 0 일때 로그인 페이지로 이동
		if(result == -1) {
			response.setContentType("text/html; charset=utf-8"); // html사용할수있게 만듬(JS포함)
			PrintWriter out = response.getWriter();
			out.print("<script>");
			out.print(" alert('회원정보 없음'); ");
			out.print(" history.back(); ");
			out.print("</script>");
			out.close();
			
			return null; //js이동 수행시 컨트롤러 이동X
		}else if(result == 0) {
			response.setContentType("text/html; charset=utf-8"); // html사용할수있게 만듬(JS포함)
			PrintWriter out = response.getWriter();
			out.print("<script>");
			out.print(" alert('비밀번호 오류'); ");
			out.print(" history.back(); ");
			out.print("</script>");
			out.close();
			
			return null; //js이동 수행시 컨트롤러 이동X
		}else { // result == 1
			response.setContentType("text/html; charset=utf-8"); // html사용할수있게 만듬(JS포함)
			PrintWriter out = response.getWriter();
			out.print("<script>");
			out.print(" alert('수정 완료'); ");
			out.print(" location.href='./Mypage.us'; ");
			out.print("</script>");
			out.close();
		}
		return null;
	}
}