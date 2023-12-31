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
	<title>문의 사항 QnA</title>

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

#title{
	font-size: 34px;
	font-family: 'yg-jalnan';
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

.button-group {
    text-align: right; 
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

a {
    color: #FF8080;
    text-decoration: none;
}

a:hover {
    text-decoration: underline;
}

table td:nth-child(2), /* 작성자 열 */
table td:nth-child(4)  /* 날짜 열 */
{
    text-align: center; 
}

.pagination { 
    margin-top: 20px;
    display: flex;
    justify-content: center;
    align-items: center;
}

.pagination a {
    margin: 0 5px;   /* 좌우 마진 추가 */
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

#qnasub { 
	color: #3248a8;
}

#writeLink {
	font-family: 'yg-jalnan';
    color: #000000;
    display: block;
    margin-top: 10px;  /* 여백을 조금 줄입니다. */
    text-align: right;
    font-size: 0.9em;  /* 폰트 크기를 줄입니다. */
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
.admin{
	font-size: 18px;
	font-family: 'yg-jalnan';
	color: #51545F;
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
            <input type="button" value="F A Q" onclick="location.href='./Faq.cs';">
            <c:if test="${!empty sessionScope.us_id }">
                <input type="button" value="메인" onclick="location.href='./UserMain.lo'">
            </c:if>
            <c:if test="${empty sessionScope.us_id }">
                <input type="button" value="메인" onclick="location.href='./Main.lo'">
            </c:if>  --%>
        </div>
        
        <h1 id="title">문의 사항 Q&A</h1>

        <c:if test="${sessionScope.us_id eq 'admin'}">
            <h2 class="admin">Welcome, Admin!</h2>
        </c:if>

 
        <br>

        <c:if test="${!empty sessionScope.us_id or !empty sessionScope.own_id}">
    		<c:if test="${sessionScope.us_id ne 'admin'}">	
            <h3>
                <a href="./QnaWriteForm.cs" id="writeLink">글쓰기</a>
            </h3>
        	</c:if>
		</c:if>
        <table>
            <tr>
                <th>번호</th>
                <th>작성자</th>
                <th>제목</th>
                <th>날짜</th>
            </tr>

            <c:forEach var="qt" items="${qnaList }">
                <tr>
                    <td>${qt.qna_num }</td>
                    <td>${qt.us_id }</td>
                    <td><a href="./QnaDetail.cs?qna_num=${qt.qna_num}" id="qnasub">${qt.qna_sub}</a></td>
                    <td>${qt.date }</td>
                </tr>
            </c:forEach> 
        </table>

        <div class="pagination">
            <c:if test="${startPage > pageBlock }">
                <a href="./Qna.cs?pageNum=${startPage-pageBlock }">Prev</a>
            </c:if>

            <c:forEach var="i" begin="${startPage }" end="${endPage }" step="1">
                <a href="./Qna.cs?pageNum=${i }">${i }</a>
            </c:forEach> 

            <c:if test="${endPage < pageCount }">
                <a href="./Qna.cs?pageNum=${startPage+pageBlock }">Next</a>
            </c:if>
        </div>
    </div>



	<jsp:include page="../inc/cs_footer_menu_area.jsp"/>


</body>
</html>
