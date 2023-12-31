<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
	<title>자주 묻는 질문 FAQ</title>

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

.button-group {
    text-align: right;  /* 버튼 그룹을 우측에 정렬 */
    margin-bottom: 20px; /* 버튼 그룹과 글쓰기 링크 사이에 여백 추가 */
}

.button-group input[type="button"] {
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

.button-group input[type="button"]:hover {
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
    margin-top: 20px;
    display: flex;
    justify-content: center;
    align-items: center;
}
 
.pagination a {
    padding: 5px 10px;   /* 패딩 추가 */
    background-color: #ffffff;
    border: 1px solid #cccccc;   /* 테두리 추가 */
    border-radius: 5px;   /* 모서리 둥글게 */
    color: #333;
    text-decoration: none;
}

.pagination a:hover {
    background-color: #f5f5f5;   /* 배경색 변경 */
}

#writeLink {
	font-family: 'yg-jalnan';
    color: #000000;
    display: block;
    margin-top: 10px;  /* 여백을 조금 줄입니다. */
    text-align: right;
    font-size: 0.9em;  /* 폰트 크기를 줄입니다. */
}

#faqsub {
	color: #3248a8; 
}

.header-title{
	font-family: 'yg-jalnan';
	font-size: 2em;
}
.navbar-nav {
    display: flex;
    justify-content: center;
    width: 100%;  /* 전체 너비를 차지하도록 설정 */
}
.top_header_area {
    max-width: 62%; 
    margin: 0 auto; 
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
    
        <div class="button-group">
           <%--  <input type="button" value="고객센터" onclick="location.href='./CsCenter.cs';"> 
            <input type="button" value="Q & A" onclick="location.href='./Qna.cs';">
            <c:if test="${!empty sessionScope.us_id }">
                <input type="button" value="메인" onclick="location.href='./UserMain.lo'">
            </c:if>
            <c:if test="${empty sessionScope.us_id }">
                <input type="button" value="메인" onclick="location.href='./Main.lo'">
            </c:if> --%>
        </div>
        
        <h1 class="header-title">자주 묻는 질문 (FAQ)</h1>
 
        <c:if test="${sessionScope.us_id eq 'admin'}">
            <p>Welcome, Admin!</p>
            <h3 style="text-align: right;">
                <a href="./FaqWriteForm.cs" id="writeLink">글쓰기</a>
            </h3>
        </c:if>

        <table>
            <tr>
                <th>No.</th>
                <th>제목</th>
                <th>날짜</th>
            </tr>

            <c:forEach var="ft" items="${faqList }">
                <tr>
                    <td>${ft.faq_num }</td>
                    <td><a href="./FaqDetail.cs?faq_num=${ft.faq_num}" id="faqsub">${ft.faq_sub}</a></td>
                    <td>${ft.date }</td>
                </tr>
            </c:forEach>
        </table>

        <div class="pagination">
            <c:if test="${startPage > pageBlock }">
                <a href="./Faq.cs?pageNum=${startPage-pageBlock }">Prev</a>
            </c:if>

            <c:forEach var="i" begin="${startPage }" end="${endPage }" step="1">
                <a href="./Faq.cs?pageNum=${i }">${i }</a>
            </c:forEach>

            <c:if test="${endPage < pageCount }">
                <a href="./Faq.cs?pageNum=${startPage+pageBlock }">Next</a>
            </c:if> 
        </div>
    </div>
    

 	<jsp:include page="../inc/cs_footer_menu_area.jsp"/>
  
    
</body>
</html>
