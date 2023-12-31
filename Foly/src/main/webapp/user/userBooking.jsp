<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>User Pickup Page</title>

	<style>
        body {
            font-family: Arial, sans-serif;
            background-color: #F9EAFD;
            padding: 20px;
        }

        h5 {
            color: #FF8080 !important; 
            border-bottom: 2px solid #FF8080;
            padding-bottom: 10px;
            margin-bottom: 20px;
        }

        fieldset {
            border: 1px solid #FF8080;
            border-radius: 10px;
            padding: 20px;
        }

        form {
            text-align: center;
        }

        input[type="submit"],
        input[type="button"] {
            background-color: #FF8080;
            color: white;
            border: none;
            padding: 10px 20px;
            border-radius: 5px;
            cursor: pointer;
            margin: 10px 0;
        }

        input[type="submit"]:hover,
        input[type="button"]:hover {
            background-color: #D64F75;
        }

        h2 {
            color: #E56B8B;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
           
        }

        table, th, td {
            border: 1px solid #FF8080;
        }

        th, td {
            padding: 10px;
            text-align: center;
        }

        th {
            background-color: #FF8080;
            color: white;
           
        }

        tr:nth-child(even) {
            background-color: #F4E0F4;
        }

        tr:nth-child(odd) {
            background-color: #F9EAFD;
        }
              .box {
           text-align: center; /* 텍스트를 가운데 정렬 */
   		   margin: 0 auto; /* 좌우 마진을 auto로 설정하여 수평 가운데 정렬 */
    	   width: 100%; /* 부모 요소의 100% 너비로 설정하여 가운데 정렬 */
    		max-width: 1000px; /* 최대 너비 설정 (원하는 너비로 조정) */
            flex: 1;
             margin-bottom: 10px;
            background-color: white; /* 백그라운드 색상 */
            padding: 20px; 
            border-radius: 10px; /* 둥근 모서리 */
             
            	
    </style>
    <meta charset="UTF-8">
    <meta name="description" content="">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <!-- The above 4 meta tags *must* come first in the head; any other head content must come *after* these tags -->

    <!-- Title -->
    <title>나의 식당예약내역</title>

    <!-- Favicon -->
    <link rel="icon" href="img/core-img/favicon.ico">

    <!-- Core Stylesheet -->
    <link href="style.css" rel="stylesheet">

    <!-- Responsive CSS -->
    <link href="css/responsive/responsive.css" rel="stylesheet">
</head>
<body>

	<c:if test="${empty sessionScope.us_id}">
		<c:redirect url="./Main.lo"/>
	</c:if>
	
	<!-- ****** Top Header Area Start ****** --> <!-- 로그인 -->
 	<jsp:include page="../inc/top_header_area.jsp"/>
    <!-- ****** Top Header Area End ****** -->

	<!-- ****** 메뉴이동 시작 ****** --> <!-- text-center 어디에 쏟아야할지 어디해야 메인이동 박스가 중간에 가나 -->
	<jsp:include page="../inc/mypage.jsp"/>  
	<!-- ****** 메뉴이동 끝 ****** -->
		
		
	<!-- 세션에 저장된 로그인 정보 확인 -->
	<c:if test="${empty sessionScope.us_id}">
		<c:redirect url="./Main.lo"/>
	</c:if>
	
	<!-- 메뉴 -->
    <h5>나의 식당예약내역</h5>

	 <div class="box">
	   	 <table border="1">
	    	<tr>
	    		<th>예약번호</th>
	        	<th>가게이름</th>
	        	<th>총가격</th>
	        	<th>예약날짜</th>
	       	</tr>
		
	        <c:forEach var="dto" items ="${UserBookingList}">
	            <tr>
	                
	                <td>${dto.bo_num }</td>
	                <td>${dto.res_name }</td>
	                <td>${dto.res_deposit }</td>
	                <td>${dto.bo_date }</td>
	            </tr>
	        </c:forEach>
	    </table>
		</div>
		
</body>



</html>	