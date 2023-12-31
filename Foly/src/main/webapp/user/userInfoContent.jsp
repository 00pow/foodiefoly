<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
 <style>
        body {
            font-family: Arial, sans-serif;
            background-color: white;
            padding: 20px;
        }

        form {
            width: 100%;
            max-width: 500px;
            margin: 20px auto;
            background-color: #FFD1DC; /* 연분홍색 배경 */
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0px 0px 10px rgba(0,0,0,0.1);
        }

        label {
            display: block;
            margin-bottom: 5px;
        }

        input[type="text"] {
            width: 100%;
            padding: 10px;
            margin-bottom: 15px;
            border: 1px solid #FF8080;
            border-radius: 5px;
            box-sizing: border-box;
        }

        input[type="submit"], input[type="button"] {
            background-color: #FF8080;
            color: white;
            border: none;
            padding: 10px 20px;
            border-radius: 5px;
            cursor: pointer;
            margin-right: 5px;
        }

        input[type="submit"]:hover, input[type="button"]:hover {
            background-color: #D64F75;
        }
    </style>

<script type="text/javascript">

	function back(){
		history.back();
	}
</script>
<style>
	

</style>
	<!-- Favicon -->
    <link rel="icon" href="img/core-img/favicon.ico">

    <!-- Core Stylesheet -->
    <link href="style.css" rel="stylesheet">

    <!-- Responsive CSS -->
    <link href="css/responsive/responsive.css" rel="stylesheet">
</head>
<body>

<!-- 	<h1> user 문의 글 페이지 </h1> -->

	<c:if test="${empty sessionScope.us_id}">
		<c:redirect url="./Main.lo"/>
	</c:if>
	
	
	<!-- ****** Top Header Area Start ****** --> <!-- 로그인 -->
 	<jsp:include page="../inc/top_header_area.jsp"/> 
    <!-- ****** Top Header Area End ****** -->

	<!-- ****** 메뉴이동 시작 ****** --> <!-- text-center 어디에 쏟아야할지 어디해야 메인이동 박스가 중간에 가나 -->
	<jsp:include page="../inc/mypage.jsp"/>  
	<!-- ****** 메뉴이동 끝 ****** -->
	
	<form action="./UserPwCheck.us" method="post">
		작성자 <input type="text" value="${dto.us_id }" readonly="readonly" name="us_id"><br>
		작성일 <input type="text" value="${dto.date }" readonly="readonly" name="date">  <br>
		제목 <input type="text" value="${dto.qna_sub }" name="qna_sub"><br>
		내용 <input type="text" value="${dto.qna_cont }" name="qna_cont"><br>
		<input type="submit" value="수정하기">
		<input type="button" value="삭제하기" onclick="location.href='./UserDeletePwCheck.us?qna_num=${dto.qna_num}';">
		<input type="button" value="돌아가기" onclick="back();"> 
	</form>
</body>
</html>