package com.foly.own.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.foly.login.action.OwnJoinAction;
import com.foly.util.Action;
import com.foly.util.ActionForward;
/*
 *  컨트롤러의 동작수행 : model(java) - view(jsp) 연결
 * 
 *  실행 : http://itwillbs.com:3306/foly/user
 *  실행 : http://itwillbs.com:3306/foly/*.us
 * */

public class OwnFrontController extends HttpServlet {

	protected void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println(" C : OwnFrontController-do.Process() 호출 ");
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
		
		
		// 메인화면(기본 - 사업자페이지 / 로그인 필수)
		if (command.equals("/OwnMain.ow")) {
			System.out.println(" C : /OwnMain.lo 매핑");
			System.out.println(" C : 패턴1 - DB사용x, 화면이동");
			
			forward = new ActionForward();
			forward.setPath("./owner/ownMain.jsp");
			forward.setRedirect(false);
		}
		// 매장정보 관리 화면
		else if (command.equals("/OwnRestaurant.ow")) {
			System.out.println(" C : /OwnRestaurant.ow 매핑");
			System.out.println(" C : 패턴3 - DB사용0, 화면출력");
			
			// 객체 생성
			action = new OwnRestaurant();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		// 매장정보 관리 화면
		else if (command.equals("/OwnInfo.ow")) {
			System.out.println(" C : /OwnInfo.ow 매핑");
			System.out.println(" C : 패턴3 - DB사용0, 화면출력");
			
			// 객체 생성
			action = new OwnInfo();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		// 가게정보 수정버튼 클릭시 비밀번호 확인
		else if(command.equals("/OwnRestaurantPwCheck.ow")) {
			
			// 패턴1번
            forward = new ActionForward();
            forward.setPath("./owner/ownPwCheck.jsp");
            forward.setRedirect(false);

        }
		// 비밀번호 비교 후 수정 페이지
        else if(command.equals("/OwnRestaurantUpdate.ow")) {

            // 객체 생성
            action = new OwnRestaurantUpdate();

            try {
                forward = action.execute(request, response);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        // 가게정보 수정
        else if(command.equals("/OwnRestaurantUpdatePro.ow")) {

            // 객체 생성
            action = new OwnRestaurantUpdatePro();

            try {
                forward = action.execute(request, response);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
		// 사업자 정보 수정 비밀번호 체크
		else if(command.equals("/OwnInfoUpdatePwCheck.ow")) {
			System.out.println(" C : /OwnInfoUpdatePwCheck.ow매핑");
			System.out.println(" C : 패턴1 - DB사용x, 화면이동");
			
			forward = new ActionForward();
			forward.setPath("./owner/ownUpdatePwCheck.jsp");
			forward.setRedirect(false);
		}
		// 사업자 정보수정
		else if(command.equals("/OwnInfo.ow")) {
			
			action = new OwnInfoAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		// 사업자 정보 수정
        else if(command.equals("/OwnInfoUpdate.ow")) {

            // 객체 생성
            action = new OwnInfoUpdateAction();

            try {
                forward = action.execute(request, response);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
		// 가게 메뉴 관리 화면
		else if (command.equals("/OwnMenu.ow")) {
			System.out.println(" C : /OwnMenu.ow 매핑");
			System.out.println(" C : 패턴3 - DB사용0, 화면출력");
			
			// 객체 생성
			action = new OwnMenu();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		// 가게 메뉴 추가 화면으로 이동
		else if (command.equals("/OwnMenuAdd.ow")) {
			System.out.println(" C : /OwnMenuAdd.ow 매핑");
			System.out.println(" C : 패턴1 - DB사용0, 화면출력");
			
			forward = new ActionForward();
			forward.setPath("./owner/ownMenuAdd.jsp");
			forward.setRedirect(false);
		}
		// 가게 메뉴 추가 동작
		else if (command.equals("/OwnMenuAddAction.ow")) {
			System.out.println(" C : /OwnMenuAddAction.ow 매핑");
			System.out.println(" C : 패턴2 - DB사용0, 화면출력");
			
			// 객체 생성
			action = new OwnMenuAddAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		// 가게 메뉴 수정 화면
		else if (command.equals("/OwnMenuContent.ow")) {
			System.out.println(" C : /OwnMenuContent.ow 매핑");
			System.out.println(" C : 패턴3 - DB사용0, 화면출력");
			
			// 객체 생성
			action = new OwnMenuContent();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		// 식당 메뉴 수정 동작
		else if (command.equals("/OwnMenuUpdate.ow")) {
			System.out.println(" C : /OwnMenuUpdate.ow 매핑");
			System.out.println(" C : 패턴2 - DB사용0, 화면출력");
			
			// 객체 생성
			action = new OwnMenuUpdate();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		// 식당 메뉴 삭제 동작
		else if (command.equals("/OwnMenuDelete.ow")) {
			System.out.println(" C : /OwnMenuDelete.ow 매핑");
			System.out.println(" C : 패턴2 - DB사용0, 화면출력");
			
			// 객체 생성
			action = new OwnMenuDelete();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		// 영업시간 관리 화면
		else if (command.equals("/OwnOpening.ow")) {
			System.out.println(" C : /OwnOpening.ow 매핑");
			System.out.println(" C : 패턴3 - DB사용0, 화면출력");
			
			// 객체 생성
			action = new OwnOpening();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		// 영업시간 수정 화면
//		else if (command.equals("/OwnOpeningAction.ow")) {
//			System.out.println(" C : /OwnOpeningAction.ow 매핑");
//			System.out.println(" C : 패턴3 - DB사용0, 화면출력");
//			
//			// 객체 생성
//			action = new OwnOpeningAction();
//			
//			try {
//				forward = action.execute(request, response);
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
		// 예약 관리 화면
		else if (command.equals("/OwnBooking.ow")) {
			System.out.println(" C : /OwnBooking.ow 매핑");
			System.out.println(" C : 패턴3 - DB사용0, 화면출력");
			
			// 객체 생성
			action = new OwnBooking();
			
			try {
				
				// execute 실행값을 forward에 넣기 => 2단계 끝, 3단계로 간다
				// forward = joinAction.execute(request, response);
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		// 포장 관리 화면
		else if (command.equals("/OwnPickup.ow")) {
			System.out.println(" C : /OwnPickup.ow 매핑");
			System.out.println(" C : 패턴3 - DB사용0, 화면출력");
			
			// 객체 생성
			action = new OwnPickup();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		// 리뷰 관리 화면
		else if (command.equals("/OwnReview.ow")) {
			System.out.println(" C : /OwnReview.ow 매핑");
			System.out.println(" C : 패턴3 - DB사용0, 화면출력");
			
			// 객체 생성
			action = new OwnReview();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		// 사업자 탈퇴 비번체크
		else if(command.equals("/OwnDelPwCheck.ow")) {
			System.out.println(" C : /OwnDelPwCheck.ow매핑");
			System.out.println(" C : 패턴1 - DB사용x, 화면이동");
			
			forward = new ActionForward();
			forward.setPath("./owner/ownDelPwCheck.jsp");
			forward.setRedirect(false);
		}
		//사업자 탈퇴
		else if(command.equals("/OwnDel.ow")) {
			System.out.println(" C : /OwnReview.ow 매핑");
			System.out.println(" C : 패턴3 - DB사용0, 화면출력");
			
			action = new OwnDelAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}// 맵테스트
		else if (command.equals("/MapTest.ow")) {
			System.out.println(" C : /MapTest.ow 매핑");
			System.out.println(" C : 패턴1 - DB사용0, 화면출력");
			
			forward = new ActionForward();
			forward.setPath("./map/mapTest1.jsp");
			forward.setRedirect(false);
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
