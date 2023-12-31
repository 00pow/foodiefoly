package com.foly.user.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.foly.util.Action;
import com.foly.util.ActionForward;



/**
 * 컨트롤러의 동작수행 : model(java)-view(jsp)
 * 실행 : http://localhost:8088/JspMVC6/member 
 * 실행 : http://localhost:8088/JspMVC6/*.me
 * 실행 : http://localhost:8088/JspMVC6/ITWILL.me
 * 실행 : http://localhost:8088/JspMVC6/안녕.me
 */
public class UserFrontController extends HttpServlet {

	protected void doProcess(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
		System.out.println(" C : UserFrontController-doProcess() 호출 ");
		System.out.println(" C : GET/POST 방식 상관없이 모든주소 처리 ");
		
		
		/******************************1. 가상주소 계산*******************************/
		System.out.println("\n C : 1. 가상주소 계산 - 시작 ");
		// 가상주소 http://localhost:8088/JspMVC6/ITWILL.me
		String requestURI = request.getRequestURI();
		System.out.println("requestURI : "+requestURI);
		// 프로젝트명(Context = 프로젝트)
		String CTXPath = request.getContextPath();
		System.out.println("CTXPath : "+CTXPath);
		// 계산된 가상주소 ( URI - 프로젝트명 )
		String command = requestURI.substring(CTXPath.length());
		System.out.println("command : "+command);
		System.out.println(" C : 1. 가상주소 계산 - 끝 ");           
		/******************************1. 가상주소 계산*******************************/
		
		/******************************2. 가상주소 매핑*******************************/
		System.out.println("\n C : 2. 가상주소 매핑 - 시작 ");
		Action action = null;
		ActionForward forward = null;
		// 마이페이지로 이동
		if(command.equals("/Mypage.us")) {
			System.out.println(" C : /Mypage.us 매핑 ");
			System.out.println(" C : 패턴3 - DB사용o, 화면출력 ");

			action = new Mypage();
			
			try {
				//forward = joinAction.execute(request, response);
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		// 회원정보 수정 버튼 클릭시 비번입력 페이지
		else if(command.equals("/MypagePwCheck.us")) {
			System.out.println(" C : /MypagePwCheck.us 매핑 ");
			System.out.println(" C : 패턴1 - DB사용X, 화면이동 ");
			
			forward = new ActionForward();
			forward.setPath("./user/mypagePwCheck.jsp");
			forward.setRedirect(false);
		}
		// 회원정보 수정 버튼 클릭시 비번확인 페이지
		else if(command.equals("/MypagePwCheckAction.us")) {
			System.out.println(" C : /MypagePwCheckAction.us 매핑 ");
			System.out.println(" C : 패턴2 - DB사용X, 화면이동 ");
			 
			action = new MypagePwCheckAction(); 
			
			 try { //forward = joinAction.execute(request, response); 
				 forward = action.execute(request,response); 
			 }catch (Exception e) {
				e.printStackTrace(); 
			 } 
		}
		// 회원정보 수정 페이지로 이동
		else if(command.equals("/MypageUpdate.us")) {
			System.out.println(" C : /MypageUpdate.us 매핑 ");
			System.out.println(" C : 패턴3 - DB사용o, 페이지이동 ");
			
			 
			action = new MypageUpdate(); 
			//action = new UserInfoFixAction(); 
			
			 try { //forward = joinAction.execute(request, response); 
				 forward = action.execute(request,response); 
			 }catch (Exception e) {
				e.printStackTrace(); 
			 } 
		
		} 
		// 회원정보 수정완료후 mypage 페이지로 이동
		else if(command.equals("/MypageUpdateAction.us")) {
			System.out.println(" C : /MypageUpdateAction.us 매핑 ");
			System.out.println(" C : 패턴2 - DB사용o, 페이지이동 ");
			
			
			action = new MypageUpdateAction(); 
			
			try { 
				forward = action.execute(request,response); 
			}catch (Exception e) {
				e.printStackTrace(); 
			} 
			
		}
		else if(command.equals("/UserReview.us")) {
			System.out.println(" C : /Review.us 매핑 ");
			System.out.println(" C : 패턴3 - DB사용o, 페이지출력 ");
			
			action = new UserReviewAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		
		}// 문의하기 페이지로 넘어가는 동작
		else if(command.equals("/Question.us")) {
			System.out.println(" C : /Question.us 매핑 ");
			System.out.println(" C : 패턴3 - DB사용o, 페이지출력 ");
			
			action = new QuestionAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		
		}
		// 문의글 쓰는 페이지로 이동
		else if(command.equals("/QuestionWrite.us")) {
			System.out.println(" C : /QuestionWrite.us 매핑 ");
			System.out.println(" C : 패턴1");
			
			forward = new ActionForward();
			forward.setPath("./user/questionWrite.jsp");
			forward.setRedirect(false);
		}
		// 문의글 작성 완료
		else if (command.equals("/UserQuestionWrite.us")) {
			System.out.println(" C : /QuestionWrite.us 매핑 ");
			System.out.println(" C : 패턴2 - DB사용o, 페이지이동 ");
			
			action = new UserQuestionWriteAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
			
			else if(command.equals("/UserPwCheck.us")) {
				System.out.println(" C : /QuestionUpdate.us 매핑 ");
				System.out.println(" C : 패턴1");
				
				forward = new ActionForward();
				forward.setPath("./user/userPwCheck.jsp");
				forward.setRedirect(false);
			}
			else if(command.equals("/UserQuestionPwCheck.us")) {
				System.out.println(" C : /UserQuestionPwCheck.us 매핑 ");
				System.out.println(" C : 패턴3 - DB사용o, 페이지출력 ");
				
				action = new UserQuestionPwCheckAction();
				try {
					forward = action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			else if(command.equals("/UserInfoContentUpdate.us")) {
				System.out.println(" C : /UserInfoContentUpdate.us 매핑 ");
				System.out.println(" C : 패턴3 - DB사용o, 페이지출력 ");
				
				action = new UserInfoContentUpdate();
				try {
					forward = action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
		
				}
			}
			else if(command.equals("/UserInfoContentUpdateAction.us")) {
				System.out.println(" C : /UserInfoContentUpdateAction.us 매핑 ");
				System.out.println(" C : 패턴3 - DB사용o, 페이지출력 ");
				
				action = new UserInfoContentUpdateAction();
				try {
					forward = action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
		
				}
			}
		
			else if(command.equals("/UserInfoContent.us")) {
				System.out.println(" C : /UsContent.us 매핑 ");
				System.out.println(" C : 패턴3 - DB사용o, 페이지출력 ");
				
				action = new UserInfoContentAction();
				try {
					forward = action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			else if(command.equals("/UserDeletePwCheck.us")) {
				System.out.println(" C : /UserDeletePwCheck.us 매핑 ");
				System.out.println(" C : 패턴3 - DB사용o, 페이지출력 ");
				
				forward = new ActionForward();
				forward.setPath("./user/userDeletePwCheck.jsp");
				forward.setRedirect(false);
				
			}
			else if(command.equals("/UserInfoContentDelete.us")) {
				System.out.println(" C : /UserInfoContentDelete.us 매핑 ");
				System.out.println(" C : 패턴3 - DB사용o, 페이지출력 ");
				
				action = new UserInfoContentDeleteAction();
				try {
					forward = action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		//회원탈퇴
			else if(command.equals("/UserDelectPw.us")) {
				System.out.println(" C : /UserDelectPw.us 매핑 ");
				System.out.println(" C : 패턴1 ");
				
				forward = new ActionForward();
				forward.setPath("./user/userDeletePw.jsp");
				forward.setRedirect(false);
			}
			else if(command.equals("/UserDeleteAction.us")) {
				System.out.println(" C : /UserDelectPw.us 매핑 ");
				System.out.println(" C : 패턴3 - DB사용o, 페이지출력 ");
				
				action = new UserDeleteAction();
				try {
					forward = action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			else if(command.equals("/UserPickup.us")) {
				System.out.println(" C : /UserPickup.us 매핑 ");
				System.out.println(" C : 패턴3 - DB사용o, 페이지출력 ");
				
				action = new UserPickupAction();
				try {
					forward = action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			else if(command.equals("/UserBooking.us")) {
				System.out.println(" C : /UserBooking.us 매핑 ");
				System.out.println(" C : 패턴3 - DB사용o, 페이지출력 ");
				
				action = new UserBookingAction();
				try {
					forward = action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		
		System.out.println(" C : 2. 가상주소 매핑 - 끝 ");
		/******************************2. 가상주소 매핑*******************************/
		
		/******************************3. 가상주소 이동*******************************/
		System.out.println("\n C : 3. 가상주소 이동 - 시작 ");
		if(forward != null){
			if(forward.isRedirect()) {
				System.out.println(" C : "+forward.getPath()+"주소로 방식 : "+forward.isRedirect() );
				response.sendRedirect(forward.getPath());
			}else {
				System.out.println(" C : "+forward.getPath()
								+"주소로 방식 : "+forward.isRedirect() );
				RequestDispatcher dis =
						request.getRequestDispatcher(forward.getPath());
				dis.forward(request, response);
				}
			}
			
		System.out.println(" C : 3. 가상주소 이동 - 끝 ");
		/******************************3. 가상주소 이동*******************************/
		System.out.println("------------------컨트롤러 끝------------------------");
				
	}//doProcess

	@Override
	protected void doGet(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
		System.out.println("\n\n------------------컨트롤러 시작------------------------");
		System.out.println(" C : MemberFrontController-doGet() 호출 ");
		doProcess(request, response);
	}//doGet

	@Override
	protected void doPost(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
		System.out.println("\n\n------------------컨트롤러 시작------------------------");
		System.out.println(" C : MemberFrontController-doPost() 호출 ");
		doProcess(request, response);
	}//doPost
	
}

