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
<title>Insert title here</title>
<!-- Favicon -->
    <link rel="icon" href="img/core-img/favicon.ico">

    <!-- Core Stylesheet -->
    <link href="style.css" rel="stylesheet">

    <!-- Responsive CSS -->
    <link href="css/responsive/responsive.css" rel="stylesheet">

<link rel="stylesheet" href="main/webapp/css/others/font-awesome.min.css">
    
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">

<script
  src="https://code.jquery.com/jquery-3.3.1.min.js"
  integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8="
  crossorigin="anonymous"></script><!-- jQuery CDN --->
<style>

@font-face {
    
    src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2304-01@1.0/omyu_pretty.woff2') format('woff2');
    font-weight: normal;
    font-style: normal;
}

body { 
         
        margin: 0; 
         padding: 0; 
        color: #000000; 
         font-weight: bold; 
         font-size: 20px; 
         text-align: center;
     } 

     h1 {
        text-align: center; 
        color: #333; 
         margin-top: 20px; 
    } 

.note1 {
margin: 2em auto;
padding:2em;
	position:relative;
	background:#fb7b7b4a;
	width:300px;
	max-width: 100%;
	text-align: center;
	padding: 12px;
	color: black;

}

.note2 {
margin: 2em auto;
padding:2em;
	position:relative;
	background:#fb7b7b4a;
	width:300px;
	max-width: 100%;
	text-align: center;
	padding: 12px;
	color: black;

}
input[type=submit], input[type=reset] , input[type=button]{ 
         padding: 10px 20px; 
         border: none; 
        background-color: #333; 
         color: #fff; 
        cursor: pointer; 
         margin: 10px 0; 
         transition: background-color 0.3s; 
         border-radius: 4px; 

     }  
</style>

</head>
<body>
<!-- ****** Top Header Area Start ****** --> <!-- 로그인 -->
 	<jsp:include page="../inc/top_header_area.jsp"/>
    <!-- ****** Top Header Area End ****** -->
	
  	<!-- ****** Header Area Start ****** -->  <!-- 사이트 이름 -->
	<jsp:include page="../inc/header_thanku.jsp"/>
    <!-- ****** Header Area End ****** -->
	<p></p>
	
	<h3>결제 완료 되었습니다</h3>
	
	<div class="note1">
	<h4>결제 정보</h4>
	
		고객명 ${dto.us_name }<input type="hidden" name="us_name" value="${dto.us_name }" id="us_name"> <br>
	결제 번호 ${dto.pay_num} <input type="hidden" name="pay_num" value="${dto.pay_num}" id="pay_num"><br>
	결제 일자 ${dto.pay_date } <input type="hidden" name="pay_date" value="${dto.pay_date }" id="pay_date"><br>
	가게 ${dto.res_name }<input type="hidden" name="res_name" value="${dto.res_name }" id="res_name"> <br>
	</div>
	<div class="note2">
	<h4>결제 정보</h4>
	결제금액 ${dto.pay_total }     원<input type="hidden" name="pay_total" value="${dto.pay_total }" id="pay_total"> <br>
	--------------------------<br>
	최종결제금액 ${dto.pay_total }    원<input type="hidden" name="pay_total" value="${dto.pay_total }" id="pay_total"><br>
	</div>
	<br>
	<input type="button" value="포장내역 이동" onclick="location.href='./UserPickup.us';">
	<input type="button" value="홈으로 가기" onclick="location.href='./UserMain.lo';">


	<!-- ****** Footer menu Area Start ****** --> <!-- footer -->
 	<jsp:include page="../inc/footer_menu_area.jsp"/>
    <!-- ****** Footer menu Area End ****** -->
</body>
</html>