<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>자바 메일보내기 폼</title>
<!-- Jquery 라이브러리 추가  -->
<script src="../js/code.jquery.com_jquery-3.7.1.min.js"></script>

$(document).ready(function){





});


</head>
<body>

<!-- 	<h1>자바메일보내기</h1> -->
	
	
	

		<form action= "mailSend" method="post">	
		<input type="text" name="sender" value="foodiefoly@gmail.com">
		<input type="text" name="receiver" value="us_email">
		<input type="text" name="subject" value="FoodieFoly 회원님의 비밀번호를 알려드립니다.">
	<textarea name="content">안녕하세요. FoodieFoly입니다. 회원님의 비밀번호는 ${us_pw }입니다.  
	</textarea>
	
	<input type="submit" value="보내기" id="mail">

	</form> 





</body>
</html>