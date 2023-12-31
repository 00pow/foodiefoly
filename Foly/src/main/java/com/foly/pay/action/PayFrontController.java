package com.foly.pay.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.foly.util.Action;
import com.foly.util.ActionForward;

public class PayFrontController extends HttpServlet{
	
	protected void doProcess(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
		System.out.println(" C : PayFrontController-doProcess() 호출 ");
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
		
		if(command.equals("/PayOption.pay")) {
			System.out.println(" C : /PayOption.pay 매핑");
			System.out.println(" C : 패턴3 - DB O, 화면출력 ");
			
			action = new PayOptionAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if(command.equals("/PaySuccess.pay")) {
			System.out.println(" C : /PaySuccess.pay 매핑");
			System.out.println(" C : 패턴2 - DB O, 화면이동 ");
			
			action = new PaySuccessAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if(command.equals("/PayInfo.pay")) {
			System.out.println(" C : /PayInfo.pay 매핑");
			System.out.println(" C : 패턴3 - DB O, 화면출력 ");
			
			action = new PayInfoAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if(command.equals("/PayBo.pay")) {
			System.out.println(" C : /PayBo.pay 매핑");
			System.out.println(" C : 패턴2 - DB O, 화면이동 ");
			
			action = new PayBoAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if(command.equals("/PayBoInfo.pay")) {
			System.out.println(" C : /PayBoInfo.pay 매핑");
			System.out.println(" C : 패턴3 - DB O, 화면출력 ");
			
			action = new PayBoInfoAction();
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
		System.out.println(" C : PayFrontController-doGet() 호출 ");
		doProcess(request, response);
	}//doGet

	@Override
	protected void doPost(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
		System.out.println("\n\n------------------컨트롤러 시작------------------------");
		System.out.println(" C : PayFrontController-doPost() 호출 ");
		doProcess(request, response);
	}//doPost
	
}
