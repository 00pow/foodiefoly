<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<h1>index.jsp(foly)</h1>
	Model2에서 실행가능한 유일한 JSP페이지
	Model2프로젝트의 시작지점
	index.jsp -> UserController 연결
	
	<%
	
	// http://itwiillbs.com:3306/foly
	 response.sendRedirect("./Main.lo");
	// response.sendRedirect("./MapTest.ow");
	/* 	./의 의미
	가상주소 - 플젝 이름
	실제주소 - webapp폴더 이름 */
	
	%>
	
</body>
</html>