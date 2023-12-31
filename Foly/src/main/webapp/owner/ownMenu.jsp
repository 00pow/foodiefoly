<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
            border-bottom: 2px solid #FF8080;
            padding-bottom: 10px;
            margin-bottom: 20px;
        }
	

        .box {
        	 text-align: center;
             max-width: 1000px; /* 최대 너비 설정 */
  			 margin: 0 auto; /* 가운데 정렬 */
             margin-bottom: 20px;
             background-color: white; /* 백그라운드 색상 */
             padding: 20px; 
             border-radius: 10px; /* 둥근 모서리 */
             
            
        }
         
        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
            border-radius: 10px;
           
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
            background-color: #white;
        }

        tr:nth-child(odd) {
            background-color: #white;
        }
      
        input[type="submit"], input[type="button"] {
            background-color: #FF8080;
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

        input[type="text"], select {
            width: 50%; 
            padding: 10px; 
            margin-bottom: 10px; 
            border: 1px solid #FF8080; 
            border-radius: 5px; 
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

    	<!-- ****** Top Header Area Start ****** -->
 	<jsp:include page="../inc/ownLogin_top_header_area.jsp"/>
    <!-- ****** Top Header Area End ****** -->
   
   
    <!-- 사업자 메뉴이동 inc -->
      <jsp:include page="../inc/own.jsp"></jsp:include>
    
    
	
		
	<!-- 세션에 저장된 로그인 정보 확인 -->
	<c:if test="${empty sessionScope.own_id}">
		<c:redirect url="./OwnLogin.lo"/>
	</c:if>
	
	<!-- 넘어온 정보 받아오기 -->

	
	
<!-- 메뉴 -->
    <h5>가게 메뉴</h5>
		<div class="box">
		<input type ="button" value="메뉴등록" onclick ="location.href='OwnMenuAdd.ow'"style="background-color: #E56B8B; color: white; border: 1px solid white;"> <br>
	   	 <table border="1" id="menuManage">
	    	<tr>
	    		    <th style="text-align: center;">메뉴번호</th>
            	 	<th style="text-align: center;">메뉴이름</th>
            		<th style="text-align: center;">메뉴가격</th>
            		<th style="text-align: center;">메뉴설명</th>
	       	</tr>
		
	        <c:forEach var="dto" items ="${menuList}">
	            <tr>
	                <td>${dto.me_num }</td>
	                <td>
	                <a href="./OwnMenuContent.ow?me_num=${dto.me_num }&pageNum=${pageNum }">
	                    ${dto.me_name }</a>
	                </td>
	                <td>${dto.me_price }</td>
	                <td>${dto.me_exp }</td>
	            </tr>
	        </c:forEach>
	    </table>
			</div>
</body>
</html>	