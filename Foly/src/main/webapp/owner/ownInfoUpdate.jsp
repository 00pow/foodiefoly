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



<script type="text/javascript">
	
	function back(){
		history.back();  // 뒤로가기
	}

</script>

</head>
<body>


	<h1> ownInfoUpdate.jsp (foly)</h1>
	<h1> 사업자 매장정보관리 페이지 -  내정보 수정 (foly)</h1>
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
		<legend> 내 정보 수정 </legend>
		<form action ="OwnInfoUpdate.ow" method="post" name ="fr">
			
			아이디 : <input type="text" value="${sessionScope.dto.own_id}" name="own_id" readonly> <br>
			비밀번호 : <input type="text" value="${requestScope.dto.own_pw}" name="own_pw"> <br>
			이름 : <input type="text" value="${requestScope.dto.own_name}" name="own_name"> <br>
			전화번호 : <input type="text" value="${requestScope.dto.own_tel}" name="own_tel"> <br>
			이메일 : <input type="text" value="${requestScope.dto.own_email}" name="own_email"> <br>

			<input type ="submit" value="내정보 수정완료"> <br>
			<input type ="button" value="돌아가기" onclick ="back();">
			 
		</form>
	</fieldset>
	
	
	
</body>
</html>