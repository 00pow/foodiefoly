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
	<title>공지사항 상세내용</title>

    <!-- Favicon -->
    <link rel="icon" href="img/core-img/favicon.ico">

    <!-- Core Stylesheet -->
    <link href="style.css" rel="stylesheet">

    <!-- Responsive CSS -->
    <link href="css/responsive/responsive.css" rel="stylesheet">

<!-- Styles -->
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

.section {
    border-bottom: 1px solid #aaa; /* 구분선 */
    padding: 10px 0;
    font-family: 'Pretendard-Regular';
}

.section-title div {
    font-weight: bold;
    font-size: 1.9em;
}

.section-date div {
    text-align: right;
}

.section:last-child div {
    text-align: right;
}

.button-group button {
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

.button-group button:hover {
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

button {
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

button:hover {
    opacity: 0.8;
}

#deleteModal, #editModal {
    display: none;
    position: fixed;
    top: 50%;
    left: 50%;
    transform: translate(-50%, -50%);
    z-index: 2;
    background-color: white;
    border: 1px solid #aaa;
    padding: 25px;
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

.content-display {
    white-space: pre-line;
}

/* 공지사항 헤더와 버튼을 담기 위한 플렉스 컨테이너 스타일 추가 */
.header-container {
    display: flex;
    justify-content: space-between;
    align-items: center;
}

.header-container h1 {
    margin: 0;
}

/* 공지사항 우측에 있는 버튼 스타일 */
.header-container .customer-service-btn {
    margin-left: 10px; /* 버튼 사이의 간격 조절 */
}

#editModal form {
    display: flex;
    flex-direction: column;
    gap: 10px; /* 각 요소 사이의 간격 */
}

/* 제목 입력 필드 스타일 */
#editModal input[type="text"] {
    padding: 8px 10px; /* 내부 패딩 추가 */
    font-size: 16px;   /* 글자 크기 조절 */
    width: 100%;       /* 입력 필드의 넓이를 최대로 확장 */
    border: 1px solid #ccc; /* 테두리 스타일 */
    border-radius: 4px;     /* 테두리 둥글게 */
    box-sizing: border-box;
}

/* 내용 입력 필드 (textarea) 스타일 */
#editModal textarea {
    padding: 8px 10px; /* 내부 패딩 추가 */
    font-size: 16px;   /* 글자 크기 조절 */
    width: 100%;       /* 입력 필드의 넓이를 최대로 확장 */
    height: 200px;     /* 텍스트 영역 높이 조절 */
    border: 1px solid #ccc; /* 테두리 스타일 */
    border-radius: 4px;     /* 테두리 둥글게 */
    resize: vertical;       /* 사용자가 수직으로만 크기 조절 가능하도록 설정 */
    box-sizing: border-box;
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
    
    
    
    
    <div class="container">

        <div id="modalBackdrop"></div>
        <div id="deleteModal">
        	<p>정말로 삭제하시겠습니까?</p>
	        	<button id="confirmDelete">예</button>
	        	<button id="cancelDelete">아니오</button>
        </div>
        <div id="editModal">
        <h2>내용 수정</h2>
		    <form action="./NotiEditAction.cs" method="post">
		        <input type="hidden" name="noti_num" value="${dto.noti_num}" />
		       		제목: <input type="text" name="noti_sub" value="${dto.noti_sub}" /><br>
		        	내용: <textarea name="noti_cont">${dto.noti_cont}</textarea><br>
		        <button type="submit">수정 완료</button>
		        <button type="button" id="cancelEdit">취소</button>
	    	</form>
	    </div>

       	<div class="header-container">
    		<h1>공지사항</h1> 
   			 <button class="customer-service-btn" onclick="location.href='./CsCenter.cs';">고객센터</button>
		</div>
        
        <div class="section"></div>

        <!-- Title Section -->
         <div class="section section-title">
             <div>${dto.noti_sub }</div>
        </div>

        <!-- Date Section --> 
       <div class="section section-date">
             <div>${dto.date }</div>
        </div>

        <!-- Content Section: Removed the bottom border for this section -->
        <div class="section" style="border-bottom: none;">
           <div class="content-display">${dto.noti_cont }</div>
        </div>

        <c:if test="${sessionScope.us_id eq 'admin' }">
    		<div class="button-group">
		        <button id="deleteButton">삭제하기</button>
		        <button id="editButton">수정하기</button>
   			</div>
		</c:if>

        <div></div>
        
    </div> 

	<script>
	    // EL을 사용하여 qna_num 값을 JavaScript 변수로 할당합니다.
	    var notiNum = ${dto.noti_num};
	
	    document.getElementById('deleteButton').addEventListener('click', function() {
	        document.getElementById('modalBackdrop').style.display = 'block';
	        document.getElementById('deleteModal').style.display = 'block';
	    });
	
	    document.getElementById('confirmDelete').addEventListener('click', function() {
	        location.href = 'NotiDeleteAction.cs?noti_num=' + notiNum;
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

</body>
</html>