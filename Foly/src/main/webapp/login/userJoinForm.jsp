<%@page import="java.text.SimpleDateFormat"%>
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
    <title>Foodidy foly site</title>

    <!-- Favicon -->
    <link rel="icon" href="img/core-img/favicon.ico">

    <!-- Core Stylesheet -->
    <link href="style.css" rel="stylesheet">

    <!-- Responsive CSS -->
    <link href="css/responsive/responsive.css" rel="stylesheet">

<link rel="stylesheet" href="main/webapp/css/others/font-awesome.min.css">
    
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
<script src="./js/code.jquery.com_jquery-3.7.1.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	
	$(".id").keyup(function(){
		var us_id = $("#us_id").val();
			if(us_id != ""){
				if(us_id.length > 15){
					$("#checkId").html('15자 이하 입력하세주세요.');
					$("#checkId").attr('color','red');
					$("#joinok").attr('disabled', 'disabled');
					document.fr.us_id.focus();
				}
				else if(us_id.length < 6 ){
					$("#checkId").html('6자 이상 입력하세주세요.');
					$("#checkId").attr('color','red');
					$("#joinok").attr('disabled', 'disabled');
					document.fr.us_id.focus();
				}
				else{
					$("#checkId").html('');
					$.ajax({
						url : "./UserIDCheck.lo",
						data : {"us_id":$("#us_id").val()},
						success : function(data){
							
							if(data == 0 ){
								$("#checkId").text("사용이 불가능한 아이디입니다.");
								$("#checkId").attr('color','red');
								$("#joinok").attr('disabled', 'disabled');
								document.fr.us_id.focus();
								
								 
							}
							else if(data == "" ){
								$("#checkId").text("아이디를 입력해주세요.");
								$("#checkId").attr('color','red');
								$("#joinok").attr('disabled', 'disabled');
								document.fr.us_id.focus();
								
								 
							}else{
								$("#checkId").text("사용이 가능한 아이디입니다.");
								$("#checkId").attr('color','green');
								$("#joinok").removeAttr("disabled");
								
							}
						}
							
							
					});
				}
			}
	});//idKeyup
	

	// 회원가입시 이메일 중복 체크
	
	$("#us_email").blur(function(){
		$.ajax({
			url : "./UserEmailCheck.lo",
			data : {"us_email":$("#us_email").val()},
			success : function(data){
				
				if(data == 0 ){
					$("#checkEmail").text("사용이 불가능한 이메일입니다.");
					$("#checkEmail").attr('color','red');
					$("#joinok").attr('disabled', 'disabled');
					document.fr.us_email.focus();
					
					 
				}
				else if(data == "" ){
					$("#checkEmail").text("이메일을 입력해주세요.");
					$("#checkEmail").attr('color','red');
					$("#joinok").attr('disabled', 'disabled');
					document.fr.us_email.focus();
					
					 
				}else{
					$("#checkEmail").text(" ");
					$("#joinok").removeAttr("disabled");
					
				}
			}
				
				
		});
	});
	
	
	
	$(".pw").keyup(function(){
		var us_pw1 = $("#us_pw1").val();
		var us_pw2 = $("#us_pw2").val();
			if(us_pw1 != "" || us_pw2 != ""){
				if(us_pw1 == us_pw2){
					$("#checkPw").text('일치');
					$("#checkPw").attr('color','green');
				}else{
					$("#checkPw").text('불일치');
					$("#checkPw").attr('color','red');
				}
			}
	});//pwKeyup

	
			
		
		
});
	
	
	
	
// 	// 회원가입 정보가 잘 입력여부 체크 
	function check(){
		
		// 아이디
		var us_id = document.fr.us_id.value;
		
		if(us_id == "" ){  //아이디가 없으면 공백이 전달되므로
			alert("아이디를 입력하세요.");
			document.fr.us_id.focus();
			return false;
		}
		
		
		// 비밀번호
		var us_pw = document.fr.us_pw.value;
		if(us_pw == ""){  //비밀번호가 없으면 공백이 전달되므로
			alert("비밀번호를 입력하세요.");
			document.fr.us_pw.focus();
			return false;
		}
		
		// 비밀번호 확인
		var us_pwC = document.fr.us_pwC.value;
		if(us_pwC == ""){  // 비밀번호를 옳바르게 입력했는지 확인
			alert("비밀번호 확인을 입력하세요.");
			document.fr.us_pwC.focus();
			return false;
		}
		
		// 비밀번호 맞게 두번 입력
		if(us_pw == us_pwC){
			
		}
		else{
			alert("'비밀번호'와 '비밀번호 확인'이 다릅니다.");
			document.fr.us_pwC.focus();
			return false;
		}
		
		// 이름(실명)
		var us_name = document.fr.us_name.value;
		if(us_name == ""){  //닉네임이 없으면 공백이 전달되므로
			alert("이름(실명)을 입력하세요.");
			document.fr.us_name.focus();
			return false;
		}
		
		// 닉네임
		var us_nick = document.fr.us_nick.value;
		if(us_nick == ""){  //닉네임이 없으면 공백이 전달되므로
			alert("닉네임을 입력하세요.");
			document.fr.us_nick.focus();
			return false;
		}
		
		// 이메일
		var us_email = document.fr.us_email.value;
		if(us_email == ""){  //이메일이 없으면 공백이 전달되므로
			alert("이메일을 입력하세요.");
			document.fr.us_email.focus();
			return false;
		}
		
		// 전화번호
		var us_tel = document.fr.us_tel.value;
		if(us_tel == ""){  // 전화번호가 없으면 공백이 전달되므로
			alert("전화번호를 입력하세요.");
			document.fr.us_tel.focus();
			return false;
		}
		
		
}
	
	
	
	function back(){
		history.back();  // 뒤로가기
	}

</script>
<style>
@font-face {
    font-family: 'omyu_pretty';
    src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2304-01@1.0/omyu_pretty.woff2') format('woff2');
    font-weight: normal;
    font-style: normal;
}

.click-btn{
 	text-align: center;
 	}
 
 .board_title{
 	margin-bottom: 30px;
 	font-family: 'omyu_pretty';
 	color: #F07392;
 	text-align: center
 	
 }
 	
	 body {
        font-family: 'omyu_pretty';
        background-color: #FFF;
        margin: 0;
        padding: 0;
        color: #F07392;
        font-weight: bold;
        font-size: 20px;
    }

    h1 { 
        text-align: center;
        color: #333;
        margin-top: 20px;
    }

    form {
        max-width: 600px;
        margin: 40px auto;
        padding: 20px;
        background-color: #EEDFE3;
        border-radius: 8px;
        box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
    }

    input[type=text], input[type=date], input[type=number],input[type=password] , input[type=email] , input[type=tel] ,select, textarea {
        width: 100%;
        padding: 10px;
        margin: 10px 0;
        border: 1px solid #ddd;
        border-radius: 4px;
        background-color: #F7F7F7
        
        
    }

    input[type=submit], input[type=reset] , input[type=button] {
        padding: 10px 20px;
        border: none;
        background-color: #333; /* 버튼 배경색 */
        color: #fff; /* 버튼 글자색 */
        cursor: pointer;
        margin: 10px 0;
        transition: background-color 0.3s;
        border-radius: 4px;
        
    }

    input[type=submit]:hover, input[type=reset]:hover {
        background-color: #555; /* 호버 시 버튼 배경색 */
    }
    
   input[type="date"]::-webkit-calendar-picker-indicator {
	color: rgba(0, 0, 0, 0);
	opacity: 1;
	display: block;
	background: url(https://cdn3.iconfinder.com/data/icons/linecons-free-vector-icons-pack/32/calendar-16.png)
		center/80% no-repeat white; 
	width: 20px;
	height: 20px;
	border-width: thin;
	cursor: pointer;
}
    
    
</style>


</head>
<body>

	<!-- ****** Top Header Area Start ****** --> <!-- 로그인 -->
 	<jsp:include page="../inc/top_header_area.jsp"/>
    <!-- ****** Top Header Area End ****** -->
	
  	<!-- ****** Header Area Start ****** -->  <!-- 사이트 이름 -->
	<jsp:include page="../inc/header_area.jsp"/>
    <!-- ****** Header Area End ****** -->
    <hr>
	

<div class="board_title">
	<h1> 개인 회원가입 </h1>
	<p style="font-size:20px;" > Foodie Foly의 다양한 기능을 회원이 되어 누려보세요</p>
</div>
<!-- 	<h1>userJoinForm.jsp(foly)</h1> -->
<!-- 	<h1>유저 회원가입 창</h1> -->

	<fieldset>
<!-- 	<h1> 유저 회원가입</h1> -->
	<form action="./UserJoinAction.lo" class="form" method="post" name ="fr" onsubmit ="return check()">
		


<!-- 	 -->
		(필수) <br>
		아이디 <input type ="text" name = "us_id" id="us_id" class="id"  maxlength="16" > <br>
		<font id="checkId" size="2"></font><br>
		
		비밀번호 <input type = "password" name = "us_pw" maxlength="20" class="pw" id="us_pw1"> <br>
        비밀번호 확인 <input type = "password" name = "us_pwC" maxlength="20" class="pw" id="us_pw2"> 
                <font id="checkPw" size="2" ></font><br><br>
                
		이름(실명) <input type = "text" name = "us_name" maxlength="6"> <br><br>
		닉네임 <input type = "text" name = "us_nick" maxlength="8"> <br><br>
		이메일 
		<input type ="email" name = "us_email" id="us_email"> <br>
		<font id="checkEmail" size="2"></font><br>
		전화번호(-제외) <input type = "tel" name = "us_tel" maxlength="11"> <br>
		
		<br> (선택) <br>
		생년월일 
		<select name="us_birY" >
			<option value="">년도</option> 년
			<%
/* 				SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
				String a = sdf+"";
				int year = Integer.parseInt(a); */
 				for(int i = 1990; i <= 2023 ;i++){
			%>
			<option value="<%=i%>"><%=i%></option> 
			<%
			 	}
			%>
		</select>
		
		<select name="us_birM" >
			<option value="">월</option> 월
			<%
 				for(int i = 1; i <= 12 ;i++){
			%>
			<option value="<%=i%>"><%=i%></option> 
			<%
			 	}
			%>
		</select>
		
		<select name="us_birD" >
			<option value="">일</option> 일
			<%
 				for(int i = 1; i <= 30 ;i++){
			%>
			<option value="<%=i%>"><%=i%></option> 
			<%
			 	}
			%>
		</select>
		<br>
		주소 <input type = "text" name = "us_addr"> <br>
		
		<hr>
		<div class="click-btn">
		<input type = "submit" value="회원가입" id="joinok">
		<input type = "button" value="돌아가기" onclick ="back();">
<!-- 	<button type="button" id="sbmBtn" onclick="check(); return false;">회원가입하기.</button> -->
	</div>
	</form>
	</fieldset>
</body>
</html>