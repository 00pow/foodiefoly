package com.foly.cs.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.foly.cs.db.CsDAO;
import com.foly.util.Action;
import com.foly.util.ActionForward;

public class QnaReplyAction implements Action {

    @Override
    public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        int qna_num = Integer.parseInt(request.getParameter("qna_num"));
        String qna_re = request.getParameter("qna_re");

        ActionForward forward = new ActionForward();
        // 답글이 null이거나 공백인 경우
        if(qna_re == null || qna_re.trim().isEmpty()) {
            // 클라이언트에게 JavaScript alert를 보내는 로직
            response.setContentType("text/html; charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println("<script>alert('내용을 넣어주세요'); history.back();</script>");
            out.flush();
            return null;  // 현재 작업 중단하고 결과 반환
        }

        CsDAO dao = new CsDAO();
        boolean result = dao.QnaReply(qna_num, qna_re);

        if(result) {
            forward.setPath("./QnaDetail.cs?qna_num=" + qna_num);
        } else {
            // 답글 저장에 실패했을 경우의 처리를 추가하세요.
            // 예를 들면, 오류 메시지를 표시하는 페이지로 리다이렉트할 수 있습니다.
            forward.setPath("./QnaDetailAction.cs?qna_num=" + qna_num + "&error=true");
        } 

        forward.setRedirect(true);
        return forward;
    }
}
