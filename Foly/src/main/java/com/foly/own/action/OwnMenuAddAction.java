package com.foly.own.action;

import java.io.PrintWriter;
import java.sql.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.foly.own.db.OwnMainDAO;
import com.foly.res.db.ResDTO;
import com.foly.util.Action;
import com.foly.util.ActionForward;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;



public class OwnMenuAddAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println(" C : OwnMenuAddAction_execute() 실행 ");
		

		
		// 세션정보 확인
		// 로그인을 안한 경우 
		HttpSession session = request.getSession();
		String own_id = (String)session.getAttribute("own_id");
			
		ActionForward forward = new ActionForward();
		if (own_id == null) {
			// 사용자가 보는 화면은 html 형식을 띄게 하면서
			response.setContentType("text/html; charset=UTF-8");
			// 글을 쓸 수 있게 해준다
			PrintWriter out = response.getWriter();
					
			out.println("HTML 코드 사용 가능");
			out.println("<script>");
			out.println("alert('로그인이 필요합니다.');");
			out.println("location.href='./OwnLogin.lo';");
			out.println("</script>");
					
			out.close();
					
			// 컨트롤러의 페이지 이동 막음 
			return null;
		}	
		
		// 업로드할 폴더 생성 - upload (가상경로)

        // 실제 파일이 업로드 되는 경로
        String realPath = request.getRealPath("/upload");
        System.out.println("M : realPath : " + realPath);

        // 파일 업로드를 할때 진짜가 아니라 숙짐머제 

        // 서버에 올라가서 동작하는 가상주소와 다르다
        // 톰캣안에 내프로젝트 - 사용자가 파일 업로드 -
        // 웹페이지의 정보를 톰캣에 저장한다(was에 정보저장)



        // 파일 크기
        int maxSize = 10 * 1024 * 1024;


        // 1) 파일업로드 수행 -> MultipartRequest 객체 생성
        // 원래는 먼저햇는데 원활한 처리를 위해 이제야 전달정보 저장
            MultipartRequest multi
            = new MultipartRequest(

                            request,
                            realPath,
                            maxSize,
                            "UTF-8",
                            new DefaultFileRenamePolicy()
                                    );

            System.out.println("M : 파일업로드 성공");

        // 2) 전달정보(파라메터) 저장(DTO)
		
		// ResDTO 객체 생성
		ResDTO dto = new ResDTO();
		
		dto.setOwn_id(own_id);
		dto.setMe_name(multi.getParameter("me_name"));
		dto.setMe_price(Integer.parseInt(multi.getParameter("me_price")));
		dto.setMe_exp(multi.getParameter("me_exp"));
		
		dto.setMe_img(multi.getFilesystemName("me_img"));
		dto.setMe_img_path(realPath);
		
		// IP주소 저장
		// dto.setIp(request.getRemoteAddr());
		
		System.out.println(" M : " + dto);
		
		System.out.println("@@@@");
		System.out.println(realPath);
		
		// DB연결 -> DAO 객체 - 글쓰기 메서드
		OwnMainDAO dao = new OwnMainDAO();
		dao.insertMenu(dto);
		
		// 페이지 이동
		forward = new ActionForward();
		forward.setPath("./OwnMenu.ow");	
		forward.setRedirect(true);
		
		return forward;
	}

}
