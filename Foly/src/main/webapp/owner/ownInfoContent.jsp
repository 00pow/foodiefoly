<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
 <style>
        body {
            font-family: Verdana,  fantasy;
            background-color: #F9EAFD; 
            padding: 20px;
        }

        h5 {
            color: #FF8080 !important; 
            border-bottom: 2px solid #E56B8B;
            padding-bottom: 10px;
            margin-bottom: 20px;
        }
	

  
        input[type="submit"], input[type="button"] {
            background-color: #E56B8B;
            color: white; 
            border: none; 
            padding: 10px 20px; 
            border-radius: 5px; 
            cursor: pointer; 
            margin-right: 10px;
            margin-bottom: 10px;
            display: inline-block;
        }

        input[type="submit"]:hover, input[type="button"]:hover {
            background-color: #D64F75; 
        }

        input[type="text"] {
            width: 15%; 
            padding: 10px; 
            margin-bottom: 10px; 
            border: 1px solid #E56B8B; 
            border-radius: 5px; 
        }
 		.space 
			{
  				 margin-left:15px;
				}


    </style>
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
		<h5>사업자 정보 수정</h5> 
		<form action ="OwnInfoUpdate.ow" method="post" name ="fr" style="text-align: center;">
			
			아이디 :<span class='space'> </span> <input type="text" value="${requestScope.dto.own_id}" name="own_id"> <br>
			비밀번호 : <input type="text" value="${requestScope.dto.own_pw}" name="own_pw" > <br>
			이름 :<span class='space'> </span><span class='space'> </span> <input type="text" value="${requestScope.dto.own_name}" name="own_name" > <br>
			전화번호 : <input type="text" value="${requestScope.dto.own_tel}" name="own_tel" > <br>
			이메일 :<span class='space'> </span> <input type="text" value="${requestScope.dto.own_email}" name="own_email" > <br>

		<span class='space'> </span><span class='space'> </span><span class='space'> </span><span class='space'> </span><span class='space'> </span><span class='space'> </span> <input type ="submit" value="수정하기"> <br>
			 
		</form>
	</fieldset>
</body>
</html>