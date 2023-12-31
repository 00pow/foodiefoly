<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="description" content="">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <!-- The above 4 meta tags *must* come first in the head; any other head content must come *after* these tags -->

    <!-- Title -->
    <title>Foodidy foly site</title>

    <!-- Favicon -->
    <link rel="icon" href="img/core-img/favicon.ico">

    <!-- Core Stylesheet -->
    <link href="style.css" rel="stylesheet">

    <!-- Responsive CSS -->
    <link href="css/responsive/responsive.css" rel="stylesheet">

</head>
<body>
<!-- 	<h1> main.jsp (foly)</h1>
	<h1> (비회원도 접근 가능)기본 메인페이지 (foly)</h1> -->
	
		<!-- 세션에 저장된 로그인 정보 확인 -->
	<c:if test="${not empty sessionScope.us_id}">
		<c:redirect url="./UserMain.lo"/>
	</c:if>
	
	
	<!-- ****** Top Header Area Start ****** --> <!-- 로그인 -->
 	<jsp:include page="../inc/top_header_area.jsp"/>
    <!-- ****** Top Header Area End ****** -->
	

	

  	<!-- ****** Header Area Start ****** -->  <!-- 사이트 이름 -->
	<jsp:include page="../inc/header_area.jsp"/>
    <!-- ****** Header Area End ****** -->
    
  	<!-- ****** 검색바 시작 ****** -->
	<jsp:include page="../inc/searchBar.jsp"/>
    <!-- ****** 검색바 끝 ****** -->
	
	<!-- ****** Welcome Post Area Start ****** --> <!-- 추천메뉴 -->
   	<jsp:include page="../inc/welcome_post_area.jsp"/>
    <!-- ****** Welcome Area End ****** -->

 	<!-- ****** Categories Area Start ****** --> <!-- 메인페이지 카테고리 사진 -->
   	<jsp:include page="../inc/categoris_area.jsp"/>
    <!-- ****** Categories Area End ****** -->
    
   	<!-- ****** Footer menu Area Start ****** --> <!-- footer -->
 	<jsp:include page="../inc/footer_menu_area.jsp"/>
    <!-- ****** Footer menu Area End ****** -->
    


</body>
</html>