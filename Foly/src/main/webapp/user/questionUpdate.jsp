<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<!-- BOARD list참고 -->

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="description" content="">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <!-- The above 4 meta tags *must* come first in the head; any other head content must come *after* these tags -->

    <!-- Title -->
    <title>나의 문의글 수정</title>

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



	<!-- 본문들어가는 곳 -->


	<!-- 게시판 -->
	<article>
		<%-- ${questionUpdate } --%>
		
		<table border="1">
			<tr>
				<th class="no" colspan="3">No.</th>
				<th class="nickname" colspan="3">작성자</th>
				<th class="title" colspan="3">제목</th>
				<th class="answer" colspan="3">답변상태</th>
				<th class="date" colspan="3">날짜</th>
			</tr>

					<!-- 여기에 자동으로 글번호 부여되는거 적기 -->
			<c:forEach var="dto" items="${questionUpdate }">
				<tr>
					<td>${dto.bno }</td>
					<td class="left"><c:if test="${dto.re_lev > 0 }">
						
						</c:if> <a href="./BoardContent.bo?bno=${dto.bno }&pageNum=${pageNum }">
							${dto.subject } </a> <c:if test="${!empty dto.file }">
							<!-- <img src="./images/board/save.png" width="20px"> -->
						</c:if></td>
					<td>${dto.name }</td>
					<td><fmt:formatDate value="${dto.date }" /></td>
					<td>${dto.readcount }</td>
				</tr>
			</c:forEach>
		</table>




		<div>
			<form action="./QuestionWrite.us" method="post">
				<input type="submit" value="글쓰기"> <br>

			</form>
		</div>









		<div class="clear"></div>
		<div id="page_control">

			<c:if test="${startPage > pageBlock }">
				<a href="./QuestionUpdate.us?pageNum=${startPage-pageBlock }">Prev</a>
			</c:if>

			<c:forEach var="i" begin="${startPage }" end="${endPage }" step="1">
				<a href="./QuestionUpdate.us?pageNum=${i }">${i }</a>
			</c:forEach>

			<c:if test="${endPage < pageCount }">
				<a href="./QuestionUpdate.us?pageNum=${startPage+pageBlock }">Next</a>
			</c:if>

		</div>

</body>
</html>

