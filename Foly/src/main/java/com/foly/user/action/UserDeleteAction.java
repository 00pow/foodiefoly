package com.foly.user.action;

import java.io.PrintWriter;
import java.sql.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.foly.user.db.UserDAO;
import com.foly.user.db.UserDTO;
import com.foly.util.Action;
import com.foly.util.ActionForward;
import com.foly.util.JSMethod;

public class UserDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		String us_id = (String) session.getAttribute("us_id");
		
		
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
		
		
		String us_pw = request.getParameter("us_pw");
		UserDTO dto = new UserDTO();
		dto.setUs_od(new Date(System.currentTimeMillis()));
		dto.setUs_id(us_id);
		dto.setUs_pw(us_pw);
		
		UserDAO dao = new UserDAO();
		int result = dao.userDelete(dto);
		
		session.invalidate();
		System.out.println(us_id);
		if (result == -1) {
			
			JSMethod.alertBack(response, "회원정보 없음");

			return null; // js이동 수행시 컨트롤러 이동X
			} else if (result == 0) {
				
				JSMethod.alertBack(response, "비밀번호 오류");
				
				return null; // js이동 수행시 컨트롤러 이동X
				} else { // result == 1
				
				
				JSMethod.alertLocation(response,"탈퇴 완료", "./Main.lo");
			}
		return null;
	}

}
