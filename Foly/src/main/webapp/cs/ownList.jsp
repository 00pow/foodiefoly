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
	<title>사업자 회원 목록</title>
 
    <!-- Favicon -->
    <link rel="icon" href="img/core-img/favicon.ico">

    <!-- Core Stylesheet -->
    <link href="style.css" rel="stylesheet">

    <!-- Responsive CSS -->
    <link href="css/responsive/responsive.css" rel="stylesheet">
<style type="text/css">
@font-face {
    font-family: 'yg-jalnan';
    src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_four@1.2/JalnanOTF00.woff') format('woff');
    font-weight: normal;
    font-style: normal;
}

@font-face {
    font-family: 'Pretendard-Regular';
    src: url('https://cdn.jsdelivr.net/gh/Project-Noonnu/noonfonts_2107@1.1/Pretendard-Regular.woff') format('woff');
    font-weight: 400;
    font-style: normal;
}

body, html {
    font-family: 'yg-jalnan';
    background-color: #f4f4f4;
    color: #555;
    line-height: 1.6;
}

.container {
    width: 90%;
    margin: 20px auto;
    padding: 20px;
    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
    background-color: #ffffff;
    border-radius: 5px;
}

.button-group input[type="button"] {
    background-color: #FF8080;
    color: #FFFFFF;
    border: none;
    padding: 10px 20px;
    margin-right: 10px;
    border-radius: 5px;
    cursor: pointer;
}

.button-group {
    text-align: right; 
}

table {
    width: 100%;
    border-collapse: collapse;
    font-family: 'Pretendard-Regular';
    font-weight: 400;
}

table th, table td {
    padding: 15px;
    border: 1px solid #EFEFEF;
}

table th {
    background-color: #f5f5f5;
}

a {
    color: #FF8080;
    text-decoration: none;
}

a:hover {
    text-decoration: underline;
}

.pagination { 
    margin-top: 20px;
    display: flex;
    justify-content: center;
    align-items: center;
}

.pagination a {
    margin: 0 5px;
    padding: 5px 10px;
    background-color: #ffffff;
    border: 1px solid #cccccc;
    border-radius: 5px;
    color: #333;
    text-decoration: none;
}

.pagination a:hover {
    background-color: #f5f5f5;
}
.top_header_area {
    max-width: 62%; 
    margin: 0 auto; 
}
.list{
	font-family: 'yg-jalnan';
}
</style> 
</head>
<body>
	
	
		<!-- ****** Top Header Area Start ****** --> <!-- 로그인 -->
 	<jsp:include page="../inc/cs_top_header_area.jsp"/>
    <!-- ****** Top Header Area End ****** -->	
    
    <jsp:include page="../inc/adminpage.jsp"/>
    
    
    
	<div class="container">
    <h2 class="list">사업자회원 목록</h2> <br>
	
  	<table>
		<tr class="table">
			<td>회원번호</td>
			<td>사업주명</td>
			<td>사업주연락처</td> 
			<td>가입일</td>
			<td>가게이름</td>
			<td>가게주소</td>
		</tr> 
		<c:forEach var="ownList" items="${ownList }">
			<tr class="table2">
				<td>${ownList.own_id }</td>
				<td>${ownList.own_name }</td>
				<td>${ownList.own_tel }</td>
				<td>${ownList.own_regdate }</td>
				<td>${ownList.res_name }</td>
				<td>${ownList.res_addr }</td>
			</tr>
		</c:forEach> 
	</table>

	<div class="pagination">
		<c:if test="${startPage > pageBlock }">
			<a href="./OwnList.cs?pageNum=${startPage-pageBlock }">Prev</a>
		</c:if>
	
		<c:forEach var="i" begin="${startPage }" end="${endPage }" step="1">
			<a href="./OwnList.cs?pageNum=${i }">${i }</a>
		</c:forEach>
	
		<c:if test="${endPage < pageCount }">
			<a href="./OwnList.cs?pageNum=${startPage+pageBlock }">Next</a>
		</c:if>
	</div>

	<hr>
	<div class="button-group">
		<input type="button" value="메인으로" onclick="location.href='./UserMain.lo'">
	</div>
</div>
	
	<jsp:include page="../inc/cs_footer_menu_area.jsp"/>
</body>
</html>