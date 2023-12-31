<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<style>
 		h5 {
            color: #FF8080 !important; 
            border-bottom: 2px solid #E56B8B;
            padding-bottom: 10px;
            margin-bottom: 20px;
        }
         h4 {
            color: #FF8080 !important; 
            }
        .space 
			{
  			 margin-left:10px;
				}
		  input[type="password"] , input[type="text"]{
  			   width: 10%; 
            margin-bottom: 10px; 
            border: 1px solid #E56B8B; 
            border-radius: 5px;  
   			 margin-bottom: 5px; /* 아랫줄 간격 설정 */
			}		
					
         input[type="submit"], input[type="button"] {
            background-color: #E56B8B;
            color: white; 
            border: none; 
            padding: 3px 6px; 
            border-radius: 5px; 
            cursor: pointer; 
            margin-right: 10px;
            margin-bottom: 10px;
            display: inline-block;
        }
     .form-center {
    text-align: center; /* 가운데 정렬을 위한 스타일 */
}

        input[type="submit"]:hover, input[type="button"]:hover {
            background-color: #D64F75; 
        }
</style>
    <meta charset="UTF-8">
    <meta name="description" content="">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <!-- The above 4 meta tags *must* come first in the head; any other head content must come *after* these tags -->

    <!-- Title -->
    <title>회원 탈퇴 비밀번호 체크</title>

    <!-- Favicon -->
    <link rel="icon" href="img/core-img/favicon.ico">

    <!-- Core Stylesheet -->
    <link href="style.css" rel="stylesheet">

    <!-- Responsive CSS -->
    <link href="css/responsive/responsive.css" rel="stylesheet">
</head>
<body>
		
  	<!-- ****** Top Header Area Start ****** -->
 	<jsp:include page="../inc/userLogin_top_header_area.jsp"/>
    <!-- ****** Top Header Area End ****** -->

	<!-- ****** 메뉴이동 시작 ****** --> <!-- text-center 어디에 쏟아야할지 어디해야 메인이동 박스가 중간에 가나 -->
	<jsp:include page="../inc/mypage.jsp"/>  
	<!-- ****** 메뉴이동 끝 ****** -->
	
	<c:if test="${empty sessionScope.us_id }">
		<c:redirect url="./Mein.lo"/>
	</c:if>
	<h5> 회원 탈퇴하기 </h5>
	<fieldset>
	<br>
	<br>
	
		<form action="./UserDeleteAction.us" method="post" style="text-align: center;">
		<h4>  비밀번호를 입력해주세요 </h4>
		 <input type="password" name="us_pw">
			<input type="submit" value="탈퇴하기"> 
		</form>
	</fieldset>
</body>
</html>