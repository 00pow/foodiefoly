package com.foly.user.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.foly.user.db.UserqDAO;
import com.foly.user.db.UserqDTO;
import com.foly.util.Action;
import com.foly.util.ActionForward;
import com.foly.util.JSMethod;

public class UserInfoContentDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println(" M : UserInfoContentDeleteAction_execute() 호출 ");
		
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
		int qna_num = Integer.parseInt(request.getParameter("qna_num"));
		
		UserqDTO dto = new UserqDTO();
		dto.setQna_num(qna_num);
		dto.setUs_id(us_id);
		
		UserqDAO dao = new UserqDAO();
		int result = dao.UserInfoDelete(dto);
		
			if(result == -1) {
			JSMethod.alertBack(response, "글 없음");
			return null;
		}else { // result == 1
			JSMethod.alertLocation(response, "삭제 완료!","./Question.us?qna_num="+qna_num);
		}
		return null;
	}

}
