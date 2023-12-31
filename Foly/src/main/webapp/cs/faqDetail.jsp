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
	<title>FAQ 상세 내용</title>

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

table {
    width: 90%;
    margin: 20px auto;
    border-collapse: collapse;
    font-family: 'Pretendard-Regular';
    font-weight: 400;
    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
    background-color: #ffffff;
    border-radius: 5px;
}

table th, table td {
    padding: 15px;
    border: 2px solid #EFEFEF;
    text-align: center; 
}

table th {
    background-color: #f5f5f5;
}

button {
    background-color: #FF8080;
    color: #FFFFFF;
    border: none;
    padding: 10px 20px;
    border-radius: 5px;
    cursor: pointer;
    transition: opacity 0.3s;
    font-size: 16px;
    font-family: 'yg-jalnan';
    margin-right: 10px;  
}

button:hover {
    opacity: 0.8;
}

h1 {
    text-align: center;
    font-size: 24px;
    margin-bottom: 20px;
}

.button-group {
    text-align: right;
    margin: 20px 0;
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
#editModal {
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
	
	<div id="editModal" style="display:none;">
	    <h2>내용 수정</h2>
	    <form action="./FaqEditAction.cs" method="post">
	        <input type="hidden" name="faq_num" value="${dto.faq_num}" />
	        제목: <input type="text" name="faq_sub" value="${dto.faq_sub}" /><br>
	        내용: <textarea name="faq_cont">${dto.faq_cont}</textarea><br>
	        <button type="submit">수정 완료</button>
	        <button type="button" id="cancelEdit">취소</button>
	    </form>
	</div>
	
	<h1>FAQ 상세 내용</h1>
	
	<table border="1">
	
    <tr>   
		<td> 작성일 </td>	    
		<td> ${dto.date } </td>	    
	     
    </tr>
    <tr>  
		<td> 제 목  </td>
	    <td> ${dto.faq_sub } </td>
    </tr>
    <tr>
		<td> 내 용  </td>
	    <td> ${dto.faq_cont } </td> 
    </tr>
    
	</table> <br>
	 
	<div class="button-group">
		<c:if test="${sessionScope.us_id eq 'admin' }">
			<button id="deleteButton">삭제하기</button>
			<button id="editButton">수정하기</button>
		</c:if>
		<button onclick="location.href='./Faq.cs';">목록으로</button>
	</div>
	
	
	<script>
	    // EL을 사용하여 qna_num 값을 JavaScript 변수로 할당합니다.
	    var faqNum = ${dto.faq_num};
	
	    document.getElementById('deleteButton').addEventListener('click', function() {
	        document.getElementById('modalBackdrop').style.display = 'block';
	        document.getElementById('deleteModal').style.display = 'block';
	    });
	
	    document.getElementById('confirmDelete').addEventListener('click', function() {
	        location.href = 'FaqDeleteAction.cs?faq_num=' + faqNum;
	    }); 
	
	    document.getElementById('cancelDelete').addEventListener('click', function() {
	        document.getElementById('modalBackdrop').style.display = 'none';
	        document.getElementById('deleteModal').style.display = 'none';
	    });
	     
	    document.getElementById('editButton').addEventListener('click', function() {
	        document.getElementById('modalBackdrop').style.display = 'block';
	        document.getElementById('editModal').style.display = 'block';
	    });

	    document.getElementById('cancelEdit').addEventListener('click', function() {
	        document.getElementById('modalBackdrop').style.display = 'none';
	        document.getElementById('editModal').style.display = 'none';
	    });

	</script>
	
	
	<jsp:include page="../inc/cs_footer_menu_area.jsp"/>
	
</body>
</html>