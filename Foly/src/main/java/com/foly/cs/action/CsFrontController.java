package com.foly.cs.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.foly.util.Action;
import com.foly.util.ActionForward;

public class CsFrontController extends HttpServlet {

	protected void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println(" C : CsFrontController-do.Process() 호출 ");
		System.out.println(" C : Get/Post 방식 상관없이 모든 주소 처리 ");

		/************************** 1. 가상주소 계산 ************************************/
		System.out.println("C : 1 가상주소 계산 - 시작 ");

		// 가상 주소 http://itwillbs.com:3306/foly/*.me
		// 주소 정보(URI)
		String RequestURI = request.getRequestURI();
		System.out.println(RequestURI);
		// 프로젝트 명
		String CTXPath = request.getContextPath();
		System.out.println("CTXPath : " + CTXPath);
		// 계산된 가상주소 (URI - 프로젝트명)
		String command = RequestURI.substring(CTXPath.length());
		System.out.println(" command : " + command);

		System.out.println("C : 1 가상주소 계산 - 끝 ");

		/************************** 1. 가상주소 계산 ************************************/

		/************************** 2. 가상주소 매핑 ************************************/
		System.out.println("C : 2 가상주소 매핑 - 시작 ");

		Action action = null;

		// 빈 객체 만들어놓음
		ActionForward forward = null;

		// 회원정보 조회
		if (command.equals("/UserList.cs")) {

			// 객체 생성
			action = new UserListAction();

			try {

				
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}

		} 
		else if (command.equals("/OwnList.cs")) {
			// 객체 생성
			action = new OwnListAction();

			try {

				// 사업자 조회
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if(command.equals("/CsCenter.cs")) {
			System.out.println(" C : /CsCenter.cs 호출 ");
			System.out.println(" C : 패턴 3 - DB사용 O, 페이지 출력");
			
			 // notiWriteAction2() 객체 
			   action = new NotiListAction();
			  
			  try { forward = action.execute(request, response); } 
			  catch (Exception e) {
			   e.printStackTrace();
			  } 		
		}
		else if(command.equals("/Faq.cs")) {
			System.out.println(" C : /Faq.cs 호출 ");
			System.out.println(" C : 패턴 3 - DB사용 O, 페이지 출력");
			
			// notiWriteAction2() 객체 
			   action = new FaqListAction();
			  
			  try { forward = action.execute(request, response); } 
			  catch (Exception e) {
			   e.printStackTrace();
			  } 	
		}
		else if(command.equals("/Qna.cs")) {
			System.out.println(" C : /Qna.cs 호출 ");
			System.out.println(" C : 패턴 3 - DB사용 O, 페이지 출력");
			
			// QnaListAction 객체 
			   action = new QnaListAction();
			  
			  try { forward = action.execute(request, response); } 
			  catch (Exception e) {
			   e.printStackTrace();
			  } 	
		}
		else if(command.equals("/NotiWriteForm.cs")) {
			System.out.println(" C : /csWriteForm.cs 호출 ");
			System.out.println(" C : 패턴 1 - DB사용 X, 페이지 이동");
			
			forward = new ActionForward();
			forward.setPath("./cs/notiWriteForm.jsp");
			forward.setRedirect(false); 
		}
		else if(command.equals("/NotiWriteAction.cs")) { 
			System.out.println(" C : /NotiWriteAction.cs 호출 ");
			System.out.println(" C : 패턴 2 - DB사용 O, 페이지 이동");
			
			  // notiWriteAction() 객체 
			   action = new NotiWriteAction();
			  
			  try { forward = action.execute(request, response); } 
			  catch (Exception e) {
			   e.printStackTrace(); }
		}
		else if(command.equals("/FaqWriteForm.cs")) {
			System.out.println(" C : /FaqWriteForm.cs 호출 ");
			System.out.println(" C : 패턴 1 - DB 사용 X, 페이지 이동");
			
			forward = new ActionForward();
			forward.setPath("./cs/faqWriteForm.jsp");
			forward.setRedirect(false);
		}
		else if(command.equals("/FaqWriteAction.cs")) { 
			System.out.println(" C : /FaqWriteAction.cs 호출 ");
			System.out.println(" C : 패턴 2 - DB사용 O, 페이지 이동");
			 
			  // notiWriteAction2() 객체 
			   action = new FaqWriteAction();
			  
			  try { forward = action.execute(request, response); } 
			  catch (Exception e) {
			   e.printStackTrace(); }
		}
		else if(command.equals("/QnaWriteForm.cs")) {
			System.out.println(" C : /QnaWriteForm3.cs 호출 ");
			System.out.println(" C : 패턴 1 - DB 사용 X, 페이지 이동");
			
			forward = new ActionForward(); 
			forward.setPath("./cs/qnaWriteForm.jsp");
			forward.setRedirect(false); 
		}
		else if(command.equals("/QnaWriteAction.cs")) { 
			System.out.println(" C : /QnaWriteAction.cs 호출 ");
			System.out.println(" C : 패턴 2 - DB사용 O, 페이지 이동");
			
			  // QnaWriteAction 객체 
			   action = new QnaWriteAction();
			  
			  try { forward = action.execute(request, response); } 
			  catch (Exception e) {
			   e.printStackTrace(); }
			}
		else if(command.equals("/NotiDetail.cs")) {
			System.out.println("  C : /notiDetailAction.cs 호출");
			System.out.println(" C : 패턴3 - DB 사용 O, 페이지 출력");
			
			// notiDetailAction 객체 
			   action = new NotiDetailAction(); 
			   
			  try { forward = action.execute(request, response); } 
			  catch (Exception e) {
			   e.printStackTrace();
			  } 	
		}
		else if(command.equals("/FaqDetail.cs")) {
			System.out.println(" C : /FaqDetail.cs 호출");
			System.out.println(" C : 패턴3 - DB 사용 O, 페이지 출력");
			
			// faqDetailAction 객체
				action = new FaqDetailAction();
				
			try { forward = action.execute(request, response);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if(command.equals("/QnaDetail.cs")) {
			System.out.println(" C : /QnaDetail.cs 호출");
			System.out.println(" C : 패턴3 - DB 사용 O, 페이지 출력");
			
			// faqDetailAction 객체
				action = new QnaDetailAction();
				
			try { forward = action.execute(request, response);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if(command.equals("/QnaDeleteAction.cs")) {
			System.out.println(" C : /QnaDeleteAction.cs 호출");
			System.out.println(" C : 패턴2 - DB 사용 O, 페이지 이동");
			
			// QnaDeleteAction 객체
				action = new QnaDeleteAction();
				
			try { forward = action.execute(request, response);
				
			} catch (Exception e) {
				e.printStackTrace();
			} 
		}
		else if(command.equals("/QnaReplyAction.cs")) {
			System.out.println(" C : /QnaReplyAction.cs 호출");
			System.out.println(" C : 패턴3 - DB 사용 O, 페이지 출력");
			
				// aqDetailAction 객체
				action = new QnaReplyAction(); 
					
				try { forward = action.execute(request, response);
					} catch (Exception e) {
					e.printStackTrace(); 
				} 
		} 
		else if(command.equals("/FaqDeleteAction.cs")) {
			System.out.println(" C : /QnaReplyAction.cs 호출");
			System.out.println(" C : 패턴3 - DB 사용 O, 페이지 출력");
			
				// QnaDeleteAction 객체
				action = new FaqDeleteAction();
						
					try {
						forward = action.execute(request, response);
					} catch (Exception e) {
						e.printStackTrace();
				} 
		}
		else if(command.equals("/FaqEditAction.cs")) {
			System.out.println(" C : /FaqEditAction.cs 호출");
			System.out.println(" C : 패턴 2 - DB 사용 O, 페이지 이동");
			
				// QnaDeleteAction 객체
				action = new FaqEditAction();
					
				try {
					forward = action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
			} 
			
		}
		else if(command.equals("/NotiDeleteAction.cs")) {
			System.out.println(" C : /NotiDeleteAction.cs 호출");
			System.out.println(" C : 패턴3 - DB 사용 O, 페이지 출력");
			
				// QnaDeleteAction 객체
				action = new NotiDeleteAction();
						
					try { 
						forward = action.execute(request, response);
					} catch (Exception e) {
						e.printStackTrace();
				} 
		}
		else if(command.equals("/NotiEditAction.cs")) {
			System.out.println(" C : /NotiEditAction.cs 호출");
			System.out.println(" C : 패턴 2 - DB 사용 O, 페이지 이동");
			
				// QnaDeleteAction 객체
				action = new NotiEditAction(); 
					
				try {
					forward = action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
			} 
			
		}


		System.out.println("C : 2 가상주소 매핑 - 끝 ");

		/************************** 2. 가상주소 매핑 ************************************/

		/************************** 3. 가상주소 이동 ************************************/
		System.out.println("C : 3. 가상주소 고민 -  시작 ");

		if (forward != null) {
			if (forward.isRedirect()) {

				System.out.println("C : " + forward.getPath() + "주소로 방식 : " + forward.isRedirect());

				response.sendRedirect(forward.getPath());

			} else { // false면
				System.out.println("C : " + forward.getPath() + "주소로 방식 : " + forward.isRedirect());

				RequestDispatcher dis = request.getRequestDispatcher(forward.getPath());
				dis.forward(request, response);

			}
		}

		System.out.println("C : 3. 가상주소 고민 -  끝 ");
		/************************** 3. 가상주소 이동 ************************************/
		System.out.println("----------- 컨트롤러 끝----------");

	}// doProcess

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		doProcess(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		;
		doProcess(request, response);
	}

}
