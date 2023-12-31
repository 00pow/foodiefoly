<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
   <meta charset="UTF-8">
    <meta name="description" content="">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <!-- The above 4 meta tags *must* come first in the head; any other head content must come *after* these tags -->

    <!-- Title -->
	<title>공지사항 작성</title>

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
input[type="text"], textarea {
    width: 100%;
    padding: 10px;
    margin: 10px 0;
    border: 1px solid #ccc;
    border-radius: 5px;
    font-size: 16px;
    font-family: 'yg-jalnan';
}
input[type="submit"] {
    background-color: #FF8080;
    color: #FFFFFF;
    border: none;
    padding: 10px 20px;
    border-radius: 5px;
    cursor: pointer;
    transition: opacity 0.3s;
    font-family: 'yg-jalnan';
}
input[type="submit"]:hover {
    opacity: 0.8;
}
fieldset {
    border: 1px solid #ccc;
    border-radius: 5px;
    padding: 20px;
}
legend { 
    font-size: 24px;
    font-weight: bold;
}

#text-cont{
	 font-family: 'Pretendard-Regular';
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
	    <h1>공지사항 작성</h1>    
	    <hr>   
	    <fieldset>
	        <legend><b>공 지 사 항</b></legend>
	        <form action="./NotiWriteAction.cs" method="post">
	            작성자: <input type="text" value="admin"> <br>
	            제목: <input type="text" name="noti_sub"> <br>
	            내용: <textarea rows="5" cols="20" style="resize: none;" name="noti_cont" id="text-cont"></textarea> <hr>
	            <input type="submit" value="작성하기">
	        </form>
	    </fieldset> 
	</div>
	
	<jsp:include page="../inc/cs_footer_menu_area.jsp"/>
		
</body>
</html>