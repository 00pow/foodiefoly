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
 	<title>Q&A 상세내용</title>

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
    margin: 50px auto;
    padding: 20px;
    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
    background-color: #ffffff;
    border-radius: 5px;
}

h1 {
    font-size: 24px;
    font-family: 'Pretendard-Regular';
    text-align: center;
    margin-bottom: 20px;
}

table {
    width: 100%;
    border-collapse: collapse;
    font-family: 'Pretendard-Regular';
    margin-bottom: 20px;
    border-collapse: collapse;
}

table th, table td {
    padding: 10px;
    border: 5px solid #EFEFEF;
}

table th {
    background-color: #f5f5f5;
}

textarea {
    width: 100%;
    padding: 10px;
    margin-top: 10px;
    border: 4px solid #EFEFEF;
    border-radius: 5px;
    font-family: 'Pretendard-Regular';
    resize: vertical;
}

input[type="submit"], button {
    background-color: #FF8080;
    color: #FFFFFF;
    border: none;
    padding: 10px 20px;
    margin-top: 10px;
    border-radius: 5px;
    cursor: pointer;
    transition: opacity 0.3s;
    font-size: 16px;
    font-family: 'yg-jalnan';
}

input[type="submit"]:hover, button:hover {
    opacity: 0.8;
}

#deleteModal {
    display: none; 
    position: fixed; 
    top: 50%; 
    left: 50%; 
    transform: translate(-50%, -50%);
    z-index: 2; 
    background-color: white;
    border: 1px solid #aaa;
    padding: 20px;
    width: 300px; 
    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
    border-radius: 5px;
}

#modalBackdrop {
    position: fixed;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    background-color: rgba(0, 0, 0, 0.5);
    z-index: 1; 
    display: none;
}

#deleteButton {
    background-color: #FF3333;
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
    
    
    
    
	<div id="modalBackdrop" style="display:none;"></div>
	
	<div id="deleteModal" style="display:none;">
	    <div>
	        <p>정말로 삭제하시겠습니까?</p>
	        <button id="confirmDelete">예</button>
	        <button id="cancelDelete">아니오</button>
	    </div>
	</div>
		<h1>QnA 상세내용</h1>
		
	<table>
    	<tr>
	    	<td>작성자</td>
	    	<td>  ${dto.us_id} </td>    
	    </tr>
	    <tr>
	        <td> 내 용 </td>
	        <td> ${dto.qna_cont} </td>
	    </tr>
	    <tr>
	        <td> 작성일 </td>
	        <td> ${dto.date} </td>
	    </tr>

    <!-- 관리자 답글이 있는 경우 해당 답글을 표시 -->
	    <c:if test="${dto.qna_re != null && dto.qna_re != ''}"> 
	        <tr>
	            <td> 관리자 답글 </td>
	            <td> ${dto.qna_re} </td> 
	        </tr>
	        <tr>
	            <td> 답글 작성일 </td> 
	            <td> ${dto.qna_redate} </td> 
	        </tr> 
	    </c:if>

    <!-- 관리자가 로그인한 경우에만 답글 작성 폼을 보여줌 -->
	    <c:if test="${sessionScope.us_id eq 'admin' }">
	        <tr>
	            <td> 답글 작성 </td>
	            <td>
	                <form action="./QnaReplyAction.cs" method="post">
	                    <!-- 답글 작성 영역 --> 
	                    <textarea name="qna_re" rows="5" cols="50" style="resize: none;" placeholder="여기에 답글을 작성하세요."></textarea>
	                    <!-- 현재 게시글 번호 -->
	                    <input type="hidden" name="qna_num" value="${dto.qna_num}"/>
	                    <!-- 답글 제출 버튼 -->
	                    <input type="submit" value="답글 작성"/>
	                </form> 
	            </td>
	        </tr>
	    </c:if>
	</table> 
   
	<c:if test="${sessionScope.us_id eq dto.us_id || sessionScope.us_id eq 'admin' }">
		<button id="deleteButton">삭제하기</button>
	</c:if> 
		<button onclick="location.href='./Qna.cs';">목록으로</button>
	
	<script>
    // EL을 사용하여 qna_num 값을 JavaScript 변수로 할당합니다.
    var qnaNum = ${dto.qna_num};

    document.getElementById('deleteButton').addEventListener('click', function() {
        document.getElementById('modalBackdrop').style.display = 'block';
        document.getElementById('deleteModal').style.display = 'block';
    });

    document.getElementById('confirmDelete').addEventListener('click', function() {
        location.href = 'QnaDeleteAction.cs?qna_num=' + qnaNum;
    }); 

    document.getElementById('cancelDelete').addEventListener('click', function() {
        document.getElementById('modalBackdrop').style.display = 'none';
        document.getElementById('deleteModal').style.display = 'none';
    });
</script>
		 	<jsp:include page="../inc/cs_footer_menu_area.jsp"/>
</body>
</html>