package com.foly.login.action;



import java.io.PrintWriter;
import java.sql.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.foly.login.db.LoginDAO;
import com.foly.login.db.OwnLoginDTO;
import com.foly.util.Action;
import com.foly.util.ActionForward;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

/*
 * 	OwnJoinAction - (개인)회원가입에 필요한 동작 처리
 * 	insertPro.jsp 페이지의 역할 수행
 * 
 * 
 * 
 * 	1) 정보전달 받기(+한글인코딩)
 *  2) 객체정보를 정보 사용 파라메터 저장
 *  
 *  3) DAO 객체 생성 - 회원가입 메서드
 *  4) 페이지 이동(로그인페이지)	
 *  
 *  JSP 내장객체를 사용X (기본자바객체 POJO)
 *  
 *  C -> M 호출 -> 작업처리(DB처리) -> (티켓)-> C
 *  
 * */
public class OwnJoinAction implements Action {
	
	@Override
	public ActionForward execute(HttpServletRequest request,
								HttpServletResponse response) throws Exception{
	
		System.out.println(" M : OwnJoinAction_execute() 호출 ");

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
			
		 // OwnLoginDTO 객체 생성
			OwnLoginDTO dto = new OwnLoginDTO();
			
			dto.setOwn_id(multi.getParameter("own_id"));
			dto.setOwn_pw(multi.getParameter("own_pw"));
			dto.setOwn_name(multi.getParameter("own_name"));
			dto.setOwn_tel(multi.getParameter("own_tel"));
			dto.setOwn_email(multi.getParameter("own_email"));
			
			dto.setOwn_regdate(new Date(System.currentTimeMillis()));

			dto.setRes_name(multi.getParameter("res_name"));
			dto.setRes_tel(multi.getParameter("res_tel"));
			dto.setRes_addr(multi.getParameter("res_addr"));
			dto.setRes_deposit(multi.getParameter("res_deposit"));
			dto.setRes_type(multi.getParameter("res_type"));
			
			dto.setRes_mng(multi.getParameter("res_mng"));
			dto.setRes_stH(multi.getParameter("res_stH"));
			dto.setRes_stM(multi.getParameter("res_stM"));
			dto.setRes_etH(multi.getParameter("res_etH"));
			dto.setRes_etM(multi.getParameter("res_etM"));
			
			dto.setRes_img(multi.getFilesystemName("res_img"));
			dto.setRes_img_path(realPath);
			
		
			
			System.out.println(" M : " + dto);
			
			System.out.println("@@@@");
			System.out.println(realPath);
		 //  3) DAO 객체 생성 - 사업자 회원가입 메서드
			LoginDAO dao = new LoginDAO();
			dao.insertOwn(dto);

			// 알림
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('사업자 회원가입 완료!')");
			out.println("location.href='./Main.lo';");
			out.println("</script>");
			out.close();

			
		 //  4) 페이지 이동(로그인페이지)	
		 //  => 페이지 이동 무조건 컨트롤러에서!
		 //  => 페이지 이동정보만 저장(티켓생성)
		 ActionForward forward = new ActionForward();
		 
		 // 목적지 입력
		 forward.setPath("./Main.lo");
		 // 이동방식 입력 
		 forward.setRedirect(true);
			
		 System.out.println(" M : " + forward);
		 System.out.println(" M : 실제 페이지이동 X, 페이지이동에 필요한 티켓 생성");
			
		return null;
	}

}
