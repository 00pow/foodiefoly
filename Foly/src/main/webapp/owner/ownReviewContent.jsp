<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
	<h1> ownMenuContent.jsp (foly)</h1>
	<h1> 사업자 메뉴수정 페이지 - 메뉴 한개만 보임 (foly)</h1>
	
	
	<!-- 세션에 저장된 로그인 정보 확인 -->
	<c:if test="${empty sessionScope.own_id}">
		<c:redirect url="./OwnLogin.lo"/>
	</c:if>
	
   	<!-- ****** Top Header Area Start ****** -->
 	<jsp:include page="../inc/ownLogin_top_header_area.jsp"/>
    <!-- ****** Top Header Area End ****** -->
   
   
    <!-- 사업자 메뉴이동 inc -->
      <jsp:include page="../inc/own.jsp"></jsp:include>
    <!-- 사업자 메뉴이동 inc -->
	
	<fieldset>
		<legend> 사업자 메뉴 </legend>
		<form action ="OwnRestaurant.ow" method="post" name ="fr" onsubmit="return check();">
			
			

			<input type ="submit" value="매장정보관리"> <br>
			<input type ="button" value="메뉴관리" onclick ="location.href='OwnMenu.ow'"> <br>
			<input type ="button" value="영업관리" onclick ="location.href='OwnOpening.ow'"> <br>
			<input type ="button" value="예약관리" onclick ="location.href='OwnBooking.ow'"> <br>
			<input type ="button" value="포장관리" onclick ="location.href='OwnPickup.ow'"> <br>
			<input type ="button" value="리뷰관리" onclick ="location.href='OwnReview.ow'"> <br>
			<input type ="button" value="로그아웃" onclick ="location.href='Logout.lo'">
			 
		</form>
	</fieldset>
	
	
	<!-- 메뉴 -->
    <h1>가게 메뉴</h1>

    
    <form action="OwnMenuUpdate.ow?me_num=${dto.me_num }&pageNum=${param.pageNum }';">
    
        <input type="text" value="${dto.me_num }" name="me_num"> <br>
        <input type="text" value="${dto.res_menu }" name="res_menu"> <br>
        <input type="text" value="${dto.res_price }" name="res_price"> <br>
        <input type="text" value="${dto.res_exp }" name="res_exp"> <br>
        
   		<input type="submit" value="수정하기">  <br>
		<input type="button" value="삭제하기"
				onclick="location.href='./OwnMenuDelete.ow?bno=${dto.me_num }&pageNum=${param.pageNum }';">
		
    </form>
    
	
	

</body>
</html>	