package com.foly.user.action;
 import java.io.PrintWriter;
import java.sql.Date;

//BoardWriteAction 참고
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.foly.user.db.UserqDAO;
import com.foly.user.db.UserqDTO;
import com.foly.util.Action;
import com.foly.util.ActionForward;
import com.foly.util.JSMethod;

// BoardWriteAction 참고
public class UserInfoContentUpdateAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		System.out.println(" M : UserInfoContentUpdateAction_execute() 실행 ");

		HttpSession session = request.getSession();
		String us_id = (String)session.getAttribute("us_id");
		int qna_num = Integer.parseInt(request.getParameter("qna_num"));
		
		ActionForward forward = new ActionForward();
		
		
		// 로그인을 안한 경우 
		
		
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
		
		UserqDTO dto = new UserqDTO();
		dto.setUs_id(us_id);
		dto.setQna_sub(request.getParameter("qna_sub"));
		dto.setQna_cont(request.getParameter("qna_cont"));
		dto.setDate(new Date(System.currentTimeMillis()));
		
		UserqDAO dao = new UserqDAO();
		int result = dao.usInfoUpdate(dto);
		
		if(result == -1) {
			JSMethod.alertBack(response, " 정보 없음");
			return null;
			
		}else { // result == 1
			JSMethod.alertLocation(response, "수정 완료!", "./UserInfoContent.us?qna_num="+qna_num);
		}
		return null;
		
	}

}