<%@page import="com.foly.cs.db.CsDTO"%>
<%@page import="java.util.ArrayList"%>
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
     height: 100%;
     margin: 0;
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

.header-buttons {
    text-align: right;  /* 버튼 그룹을 우측에 정렬 */
    margin-bottom: 20px; /* 버튼 그룹과 글쓰기 링크 사이에 여백 추가 */
}

.header-buttons input[type="button"] {
     background-color: #FF8080;
     color: #FFFFFF;
     border: none;
     padding: 10px 20px;
     margin-right: 10px;
     border-radius: 5px;
     cursor: pointer;
     transition: opacity 0.3s;
     font-size: 16px;
     font-family: 'yg-jalnan';
}

.header-buttons input[type="button"]:hover {
     opacity: 0.8;
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

table td:first-child, 
table td:last-child {
    text-align: center;
}

a {
     color: #FF8080;
     text-decoration: none;
}

a:hover {
     text-decoration: underline;
}

.pagination { 
    margin-top: 30px;
    display: flex;
    justify-content: center;
    align-items: center;
    padding: 10px;
}

.pagination a {
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

#writeLink {
	font-family: 'yg-jalnan';
    color: #000000;
    display: block;
    margin-top: 10px;  /* 여백을 조금 줄입니다. */
    text-align: right;
    font-size: 0.9em;  /* 폰트 크기를 줄입니다. */
}

.header-title {
    margin-top: 10px; /* 상단 여백을 조절 */
    text-align: left; /* 글자 위치를 왼쪽으로 정렬 */
    font-family: 'yg-jalnan';
    font-size: 2em;
}

.navbar-nav { 
    display: flex;
    justify-content: center;
    width: 100%;  /* 전체 너비를 차지하도록 설정 */
}

.navbar-nav .nav-link {
    font-size: 1.2em;  /* 폰트 크기를 1.2배로 증가 */
}
.top_header_area {
    max-width: 62%; 
    margin: 0 auto; 
}
.subc{
	color: #3248a8;
}
</style>
</head>
<body>
	<!-- ****** Top Header Area Start ****** --> <!-- 로그인 -->
 	<jsp:include page="../inc/cs_top_header_area.jsp"/>
    <!-- ****** Top Header Area End ****** -->	
    
    <jsp:include page="../inc/cs_header_area.jsp"/>
    
   	<jsp:include page="../inc/cs_mypage.jsp"/>
    
    
	<div class="container"> 
    <!-- 고객센터 & 버튼 --> 
    
     
    <div class="header-section">
	        <h1 class="header-title">공지사항</h1>
	        <%-- <div class="header-buttons">
	            <button onclick="location.href='./Faq.cs';">F A Q</button>
	            <button onclick="location.href='./Qna.cs';">Q & A</button> 
	            <c:if test="${!empty sessionScope.us_id }">
	                <button onclick="location.href='./UserMain.lo'">메인</button>
	            </c:if>
	            <c:if test="${empty sessionScope.us_id }">
	                <button onclick="location.href='./Main.lo'">메인</button>
	            </c:if>
	        </div>  --%>
	    </div>
	
	    <!-- 공지사항 -->
	    <div class="notice-section">
	       
	        
	         <c:if test="${sessionScope.us_id eq 'admin'}">
	            <p>Welcome, Admin!</p>
	            	<div class="write-link">
			       		 <h3>
			         		<a href="./NotiWriteForm.cs"  id="writeLink">글쓰기</a> 
			       	 	</h3>
   				 	</div> 
       		 </c:if>
	        
	        <table class="notice-table">
	            <tr>
	                <th>No.</th>
	                <th>제목</th> 
	                <th>등록일</th>
	            </tr>
	            <c:forEach var="nt" items="${notiList}">
	                <tr>
	                    <td>${nt.noti_num}</td>
	                    <td><a href="./NotiDetail.cs?noti_num=${nt.noti_num}"  class="subc">${nt.noti_sub}</a></td>
	                    <td id="notice-datetd">${nt.date}</td>
	                </tr>
	            </c:forEach>
	        </table>
			<!-- 페이징 처리 -->
			 <div class="pagination">
			    <c:if test="${startPage > pageBlock}">
			        <a href="./CsCenter.cs?pageNum=${startPage-pageBlock}">Prev</a>
			    </c:if>
			
			    <c:forEach var="i" begin="${startPage}" end="${endPage}" step="1">
			        <a href="./CsCenter.cs?pageNum=${i}">${i}</a>
			    </c:forEach>
			
			    <c:if test="${endPage < pageCount}">
			        <a href="./CsCenter.cs?pageNum=${startPage+pageBlock}">Next</a>
			    </c:if>
			</div>
	    </div>
	</div> 
	
	<!-- ****** Footer menu Area Start ****** --> <!-- footer -->
 	<jsp:include page="../inc/cs_footer_menu_area.jsp"/>
    <!-- ****** Footer menu Area End ****** -->
	
</body>
</html>