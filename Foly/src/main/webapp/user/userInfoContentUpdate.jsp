<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

</head>
<body>

	<!-- <h1> user 문의글수정 페이지 - 메뉴 한개만 보임 (foly)</h1> -->

	<c:if test="${empty sessionScope.us_id}">
		<c:redirect url="./Main.lo"/>
	</c:if>
	
	<!-- ****** Top Header Area Start ****** --> <!-- 로그인 -->
 	<jsp:include page="../inc/top_header_area.jsp"/>
    <!-- ****** Top Header Area End ****** -->
	
	<!-- ****** 메뉴이동 시작 ****** --> <!-- text-center 어디에 쏟아야할지 어디해야 메인이동 박스가 중간에 가나 -->
	<jsp:include page="../inc/mypage.jsp"/>  
	<!-- ****** 메뉴이동 끝 ****** -->
	
	<h1>문의 글 수정</h1>
	
	<form action="./UserInfoContentUpdateAction.us" method="post">
		<input type="hidden" value="${dto.qna_num }" name="qna_num">
		작성자 <input type="text" value="${dto.us_id }" readonly="readonly" name="us_id"><br>
		작성일 <input type="text" value="${dto.date }" readonly="readonly" name="date">  <br>
		제목 <input type="text" value="${dto.qna_sub }" name="qna_sub"><br>
		내용 <input type="text" value="${dto.qna_cont }" name="qna_cont"><br>
		<input type="submit" value="수정완료">
		<input type="button" value="돌아가기" onclick="location.href='./Question.us';"> 
	</form>
</body>
</html>