package com.foly.login.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.foly.util.Action;
import com.foly.util.ActionForward;
/*
 *  컨트롤러의 동작수행 : model(java) - view(jsp) 연결
 * 
 *  실행 : http://itwillbs.com:3306/foly/user
 *  실행 : http://itwillbs.com:3306/foly/*.us
 * */

public class LoginFrontController extends HttpServlet {

	protected void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println(" C : UserFrontController-do.Process() 호출 ");
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
		
		
		// 메인화면(기본)
		if (command.equals("/Main.lo")) {
			System.out.println(" C : /Main.lo 매핑");
			System.out.println(" C : 패턴1 - DB사용x, 화면이동");
			
			forward = new ActionForward();
			forward.setPath("./login/main.jsp");
			forward.setRedirect(false);
		}
		// 메인화면(개인)
		else if (command.equals("/UserMain.lo")) {
			System.out.println(" C : /UserMain.lo 매핑");
			System.out.println(" C : 패턴1 - DB사용x, 화면이동");
			
			forward = new ActionForward();
			forward.setPath("./login/userMain.jsp");
			forward.setRedirect(false);
		}
		// 메인화면 - 로그인 버튼 클릭 - 회원 로그인 페이지(userLogin.jsp)로 이동
		else if (command.equals("/UserLogin.lo")) {
			System.out.println(" C : /UserLogin.lo 매핑");
			System.out.println(" C : 패턴1 - DB사용x, 화면이동");
			
			forward = new ActionForward();
			forward.setPath("./login/userLogin.jsp");
			forward.setRedirect(false);
		}
		//(개인) id,pw 입력후 로그인 버튼 클릭
		else if (command.equals("/UserLoginAction.lo")) {
			System.out.println(" C : /UserLoginAction.lo 매핑");
			System.out.println(" C : 패턴2 - DB사용0, 화면이동");

			// 객체 생성
			action = new UserLoginAction();
			
			try {
				
				// execute 실행값을 forward에 넣기 => 2단계 끝, 3단계로 간다
				// forward = joinAction.execute(request, response);
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		// 메인화면 - 로그인 버튼 클릭 - 사업자 로그인 버튼 클릭 - 사업자 로그인(ownLogin.jsp) 페이지로 이동
		else if (command.equals("/OwnLogin.lo")) {
			System.out.println(" C : /OwnLogin.lo 매핑");
			System.out.println(" C : 패턴1 - DB사용x, 화면이동");
			
			forward = new ActionForward();
			forward.setPath("./login/ownLogin.jsp");
			forward.setRedirect(false);
		}
		//(사업자) id,pw 입력후 로그인 버튼 클릭
		else if (command.equals("/OwnLoginAction.lo")) {
			System.out.println(" C : /OwnLoginAction.lo 매핑");
			System.out.println(" C : 패턴2 - DB사용0, 화면이동");
			
			// 객체 생성
			action = new OwnLoginAction();
			
			try {
				
				// execute 실행값을 forward에 넣기 => 2단계 끝, 3단계로 간다
				// forward = joinAction.execute(request, response);
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		// 메인화면 - 회원가입 버튼 클릭 - 회원가입 페이지로 이동
		else if (command.equals("/Join.lo")) {
			System.out.println(" C : /Join.lo 매핑");
			System.out.println(" C : 패턴1 - DB사용x, 화면이동");
			
			forward = new ActionForward();
			forward.setPath("./login/join.jsp");
			forward.setRedirect(false);

			
		}
		// 회원가입 - 개인 회원가입 페이지로 이동
		else if (command.equals("/UserJoin.lo")) {
			System.out.println(" C : /UserJoin.lo 매핑");
			System.out.println(" C : 패턴1 - DB사용x, 화면이동");
			
			forward = new ActionForward();
			forward.setPath("./login/userJoinForm.jsp");
			forward.setRedirect(false);
			
			
		}
		// 회원가입 - 개인 회원가입 정보처리
		else if (command.equals("/UserJoinAction.lo")) {
			System.out.println(" C : /UserJoinAction.lo 매핑");
			System.out.println(" C : 패턴2 - DB사용0, 화면이동 ");
			// 객체 생성
			// MemberJoinAction joinAction = new MemberJoinAction();
			action = new UserJoinAction();
			
			try {
				
				// execute 실행값을 forward에 넣기 => 2단계 끝, 3단계로 간다
				// forward = joinAction.execute(request, response);
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}			

		}
		// 회원가입 - 사업자 회원가입 페이지로 이동
		else if (command.equals("/OwnJoin.lo")) {
			System.out.println(" C : /OwnJoin.lo 매핑");
			System.out.println(" C : 패턴1 - DB사용x, 화면이동");
			
			forward = new ActionForward();
			forward.setPath("./login/ownJoinForm.jsp");
			forward.setRedirect(false);
			
			
		}
		// 회원가입 - 사업자 회원가입 정보처리
		else if (command.equals("/OwnJoinAction.lo")) {
			System.out.println(" C : /OwnJoinAction.lo 매핑");
			System.out.println(" C : 패턴2 - DB사용0, 화면이동 ");
			// 객체 생성
			// MemberJoinAction joinAction = new MemberJoinAction();
			action = new OwnJoinAction();
			
			try {
				
				// execute 실행값을 forward에 넣기 => 2단계 끝, 3단계로 간다
				// forward = joinAction.execute(request, response);
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}			
			
		}
		// 로그아웃 - 메인 페이지로 이동
		else if (command.equals("/Logout.lo")) {
			System.out.println(" C : /Logout.lo 매핑");
			System.out.println(" C : 패턴2 - DB사용x, 처리동작0, 화면이동");
			
			// 객체 생성
			action = new LogoutAction();
						
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		// (개인)아이디 찾기1
		else if (command.equals("/UserFindId.lo")) {
			System.out.println(" C : /UserFindId.lo 매핑");
			System.out.println(" C : 패턴1 - DB사용x, 화면이동");
			
			forward = new ActionForward();
			forward.setPath("./login/userFindId.jsp");
			forward.setRedirect(false);
			
			
		}
		// (개인)아이디 찾기2
		else if (command.equals("/UserFindIdAction.lo")) {
			System.out.println(" C : /UserFindIdAction.lo 매핑");
			System.out.println(" C : 패턴2 - DB사용x, 처리동작0, 화면이동");
			
			// 객체 생성
			action = new UserFindIdAction();
						
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		// 비밀번호 찾기(개인)1
		else if (command.equals("/UserFindPw.lo")) {
			System.out.println(" C : /UserFindPw.lo 매핑");
			System.out.println(" C : 패턴1 - DB사용x, 화면이동");
			
			forward = new ActionForward();
			forward.setPath("./login/userFindPw.jsp");
			forward.setRedirect(false);
			
			
		}
		// 비밀번호 찾기(개인)2
		else if (command.equals("/UserFindPwAction.lo")) {
			System.out.println(" C : /UserFindPwAction.lo 매핑");
			System.out.println(" C : 패턴2 - DB사용x, 처리동작0, 화면이동");
			
			// 객체 생성
			action = new UserFindPwAction();
						
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		// 비밀번호 찾기(사업자)
		else if (command.equals("/OwnFindPw.lo")) {
			System.out.println(" C : /OwnFindPw.lo 매핑");
			System.out.println(" C : 패턴1 - DB사용x, 화면이동");
			
			forward = new ActionForward();
			forward.setPath("./login/ownFindPw.jsp");
			forward.setRedirect(false);
			
			
		}
		// 비밀번호 찾기(사업자)2
		else if (command.equals("/OwnFindPwAction.lo")) {
			System.out.println(" C : /OwnFindPwAction.lo 매핑");
			System.out.println(" C : 패턴2 - DB사용x, 처리동작0, 화면이동");
			
			// 객체 생성
			action = new OwnFindPwAction();
						
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}// 개인 아이디 중복 체크
		else if (command.equals("/UserIDCheck.lo")) {
			System.out.println(" C : /UserIDCheck.lo 매핑");
			System.out.println(" C : 패턴2 - DB사용x, 처리동작0, 화면이동");
			
			// 객체 생성
			action = new CheckIdAction();
						
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
	// 개인 이메일 중복 체크
	else if (command.equals("/UserEmailCheck.lo")) {
		System.out.println(" C : /UserEmailCheck.lo 매핑");
		System.out.println(" C : 패턴2 - DB사용x, 처리동작0, 화면이동");
		
		// 객체 생성
		action = new CheckEmailAction();
		
		try {
			forward = action.execute(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	// 사업자 아이디 중복 체크
	else if (command.equals("/OwnIDCheck.lo")) {
		System.out.println(" C : /OwnIDCheck.lo 매핑");
		System.out.println(" C : 패턴2 - DB사용x, 처리동작0, 화면이동");
		
		// 객체 생성
		action = new OwnCheckIdAction();
		
		try {
			forward = action.execute(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
		
		// 이메일 중복 체크
				else if (command.equals("/UserEmailCheck.lo")) {
					System.out.println(" C : /UserEmailCheck.lo 매핑");
					System.out.println(" C : 패턴2 - DB사용x, 처리동작0, 화면이동");
					
					// 객체 생성
					action = new CheckEmailAction();
								
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
					
					System.out.println("C : " + forward.getPath() +
							"주소로 방식 : " + forward.isRedirect());
					
					response.sendRedirect(forward.getPath());

				} else { //false면
					System.out.println("C : " + forward.getPath() +
							"주소로 방식 : " + forward.isRedirect());
					
					RequestDispatcher dis = 
							request.getRequestDispatcher(forward.getPath());
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
		System.out.println("\n\n ----------- 컨트롤러 시작----------");
		System.out.println(" C : UserFrontController-do.Get() 호출 ");
		doProcess(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("\n\n ----------- 컨트롤러 시작----------");
		System.out.println(" C : UserFrontController-do.Post() 호출 ");
		doProcess(request, response);

	}

}
