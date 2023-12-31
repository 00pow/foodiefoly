package com.foly.res.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.foly.util.Action;
import com.foly.util.ActionForward;




/**
 *  컨트롤러의 동작수행 : model(java)-view(jsp) 연결
 *  
 *  실행 : http://localhost:8088/JspMVC6/member
 *  실행 : http://localhost:8088/JspMVC6/*.me
 *  실행 : http://localhost:8088/JspMVC6/ITWILL.me
 *  실행 : http://localhost:8088/JspMVC6/안녕.me
 *  
 */
public class ResFrontController extends HttpServlet{
	
	
	protected void doProcess(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		System.out.println(" C : MemberFrontController-doProcess() 호출");
		System.out.println(" C : GET/POST 방식 상관없이 모든주소 처리 ");
		
		/*********************1. 가상주소 계산***************************/
		System.out.println("\n C : 1. 가상주소 계산 - 시작");
		//  가상주소  http://localhost:8088/JspMVC6/ITWILL.me
		// 주소정보(URI)
		String requestURI = request.getRequestURI();		
		System.out.println(" requestURI : "+requestURI);
		// 프로젝트명
		String CTXPath = request.getContextPath();
		System.out.println(" CTXPath : "+CTXPath);
		// 계산된 가상주소 ( URI - 프로젝트명 )
		String command = requestURI.substring(CTXPath.length());
		System.out.println(" command : "+command);	                   
		System.out.println(" C : 1. 가상주소 계산 - 끝");		
		/*********************1. 가상주소 계산***************************/
				
		/*********************2. 가상주소 매핑***************************/
		System.out.println("\n C : 2. 가상주소 매핑 - 시작");
		Action action = null;
		ActionForward forward = null;
		// - 패턴1 => DB안쓰고 화면이동
		

		// 카테고리 클릭시 카테고리 검색창으로 이동 
		if(command.equals("/Categori.re")) {
			
			action = new Categori();
			
			try {
				forward = action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
			
				}
		
		}
		// (메뉴 상세페이지) 식당 상세페이지에서 메뉴 클릭시 이동 - 
		else if(command.equals("/MenuDetail.re")){
			System.out.println(" C : /MenuDetail.re 호출 ");
			
			action = new MenuDetail();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		// 
		else if(command.equals("/SearchRes.re")) {
			System.out.println(" C : /SearchRes.re 호출 ");
			
			action = new SearchResAction();

			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();

			}
			
		}
		// (식당 상세 페이지) 검색페이지에서 식당 클릭시 이동 -
		else if(command.equals("/ResDetail.re")) {
			System.out.println(" C : /ResDetail.re 호출 ");
			
			action = new ResDetail();

			try {
				forward = action.execute(request, response);
			}
			catch (Exception e) {
				e.printStackTrace();

			}
			
		}
		//목록을 저장하는 
		else if(command.equals("/MenuAdd.re")) {
			System.out.println(" C : /MenuAdd.re 호출 ");
			
			
			//MenuAdd() 객체
			action = new MenuAdd();
			try {
				forward = action.execute(request, response); // forward에 결과 저장
			} catch (Exception e) {
				
				e.printStackTrace();
			}
		}
		// 장바구니 페이지로 이동-
		else if (command.equals("/Cart.re")) {
			System.out.println(" C : /Cart.re 매핑");
			System.out.println(" C : 패턴3 - DB사용0, 화면출력");
			
			// 객체 생성
			action = new Cart();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		// 장바구니 내용 모두 삭제
		else if(command.equals("/CartDelete.re")) {
			System.out.println(" C : /CartDelete.re 호출 ");
			
			
			//BookingtAction() 객체
			action = new CartDelete();
			try {
				forward = action.execute(request, response); // forward에 결과 저장
			} catch (Exception e) {
				
				e.printStackTrace();
			}
			
		}
		//예약페이지로 이동
		else if(command.equals("/Booking.re")) {
			System.out.println(" C : /Booking.re 호출 ");
			
			
			//BookingtAction() 객체
			action = new Booking();
			try {
				forward = action.execute(request, response); // forward에 결과 저장
			} catch (Exception e) {
				
				e.printStackTrace();
			}
		
		}

				
					
			
		
		
		
		
		System.out.println(" C : 2. 가상주소 매핑 - 끝");
		/*********************2. 가상주소 매핑***************************/
		
		
		
		
		
		
		/*********************3. 가상주소 이동***************************/
		System.out.println("\n C : 3. 가상주소 이동 - 시작");
		
		if(forward != null) {
			if(forward.isRedirect()) {
				System.out.println(" C : "+forward.getPath()
				                    +"주소로 방식:"+forward.isRedirect());
				
				response.sendRedirect(forward.getPath());
			}else {
				System.out.println(" C : "+forward.getPath()
								+"주소로 방식:"+forward.isRedirect());
				
				RequestDispatcher dis = 
						request.getRequestDispatcher(forward.getPath());
				dis.forward(request, response);				
			}		
			
		}	
		
		System.out.println(" C : 3. 가상주소 이동 - 끝");
		/*********************3. 가상주소 이동***************************/
		System.out.println("\n\n ------------컨트롤러 끝--------------");
	}// doProcess

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		System.out.println("\n\n ------------컨트롤러 시작--------------");
		System.out.println(" C : MemberFrontController-doGet() 호출");
		doProcess(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		System.out.println("\n\n ------------컨트롤러 시작--------------");
		System.out.println(" C : MemberFrontController-doPost() 호출");
		doProcess(request, response);		
	}
}
