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


<script src="./js/code.jquery.com_jquery-3.7.1.min.js"></script>	
<script type="text/javascript">
	$(function(){
		
		
		$(".id").keyup(function(){
			var own_id = $("#own_id").val();
				if(own_id != ""){
					if(own_id.length > 15){
						$("#checkId").html('15자 이하 입력하세주세요.');
						$("#checkId").attr('color','red');
						$("#joinok").attr('disabled', 'disabled');
						document.fr.own_id.focus();
					}
					else if(own_id.length < 6 ){
						$("#checkId").html('6자 이상 입력하세주세요.');
						$("#checkId").attr('color','red');
						$("#joinok").attr('disabled', 'disabled');
						document.fr.own_id.focus();
					}
					else{
						$("#checkId").html('');
						$("#joinok").removeAttr("disabled");
						
						$.ajax({
							url : "./OwnIDCheck.lo",
							data : {"own_id":$("#own_id").val()},
							success : function(data){
								
								if(data == 0 ){
									$("#checkId").text("사용이 불가능한 아이디입니다.");
									$("#checkId").attr('color','red');
									$("#joinok").attr('disabled', 'disabled');
									document.fr.own_id.focus();
									
									 
								}
								else if(data == "" ){
									$("#checkId").text("아이디를 입력해주세요");
									$("#checkId").attr('color','red');
									$("#joinok").attr('disabled', 'disabled');
									document.fr.own_id.focus();
									
									 
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
		

		$(".pw").keyup(function(){
			var own_pw1 = $("#own_pw1").val();
			var own_pw2 = $("#own_pw2").val();
				if(own_pw1 != "" || own_pw2 != ""){
					if(own_pw1 == own_pw2){
						$("#checkPw").text('일치');
						$("#checkPw").attr('color','green');
					}else{
						$("#checkPw").text('불일치');
						$("#checkPw").attr('color','red');
					}
				}
					if(own_pw1.length > 20){
						$("#checkPwLeng1").text('20자 이하로 입력해주세요.');
						$("#checkPwLeng1").attr('color','red');
					}else if(own_pw1.length  < 20){
						$("#checkPwLeng1").text('');
					}
		});//pwKeyup
	});
	
	// 회원가입 정보가 잘 입력여부 체크 
	function check(){
		
		// 아이디
		var own_id = document.fr.own_id.value;
		if(own_id == ""){  //아이디가 없으면 공백이 전달되므로
			alert("사업자번호(아이디)를 입력하세요.");
			document.fr.own_id.focus();
			return false;
		}
		
		// 비밀번호
		var own_pw = document.fr.own_pw.value;
		if(own_pw == ""){  //비밀번호가 없으면 공백이 전달되므로
			alert("비밀번호를 입력하세요.");
			document.fr.own_pw.focus();
			return false;
			
		}
		
		// 비밀번호 확인
		var own_pwC = document.fr.own_pwC.value;
		if(own_pwC == ""){  //비밀번호확인이 없으면 공백이 전달되므로
			alert("'비밀번호 확인'를 입력하세요.");
			document.fr.own_pwC.focus();
			return false;
		}
		
		// 비밀번호 똑같이 입력
		if(own_pw == own_pwC){
			
		}
		else{
			alert("'비밀번호'와 '비밀번호 확인'이 다릅니다.");
			document.fr.own_pwC.focus();
			return false;
		}
		
		// 사업자 이름
		var own_name = document.fr.own_name.value;
		if(own_name == ""){  //이메일이 없으면 공백이 전달되므로
			alert("이름(실명)을 입력하세요.");
			document.fr.own_name.focus();
			return false;
		}
		
		// 사업자 전화번호 
		var own_tel = document.fr.own_tel.value;
		if(own_tel == ""){  //전화번호가 없으면 공백이 전달되므로
			alert("사업자 전화번호를 입력하세요.");
			document.fr.own_tel.focus();
			return false;
		}
		// 사업자 이메일
		var own_email = document.fr.own_email.value;
		if(own_email == ""){  //이메일이 없으면 공백이 전달되므로
			alert("이메일을 입력하세요.");
			document.fr.own_email.focus();
			return false;
		}
		// 식당 이름
		var res_name = document.fr.res_name.value;
		if(res_name == ""){  //식당이름이 없으면 공백이 전달되므로
			alert("식당이름을 입력하세요.");
			document.fr.res_name.focus();
			return false;
		}
		// 식당 전화번호
		var res_tel = document.fr.res_tel.value;
		if(res_tel == ""){  //식당 전화번호가 없으면 공백이 전달되므로
			alert("식당 전화번호를 입력하세요.");
			document.fr.res_tel.focus();
			return false;
		}
		// 식당 주소
		var res_addr = document.fr.res_addr.value;
		if(res_addr == ""){  //주소가 없으면 공백이 전달되므로
			alert("주소를 입력하세요.");
			document.fr.res_addr.focus();
			return false;
		}
		// 식당 업종
		var res_type = document.fr.res_type.value;
		if(res_type == ""){  //식당 업종이 없으면 공백이 전달되므로
			alert("업종을 입력하세요.");
			document.fr.res_type.focus();
			return false;
		}
		// 식당 영업여부
		var res_mng = document.fr.res_mng.value;
		if(res_mng == ""){  //영업여부가 없으면 공백이 전달되므로
			alert("영업여부를 입력하세요.");
			document.fr.res_mng.focus();
			return false;
		}
		// 식당 오픈시간H
		var res_stH = document.fr.res_stH.value;
		if(res_stH == ""){  //시가 없으면 공백이 전달되므로
			alert("오픈시간 '시'를 입력하세요.");
			document.fr.res_stH.focus();
			return false;
		}
		// 식당 오픈시간M
		var res_stM = document.fr.res_stM.value;
		if(res_stM == ""){  //분이 없으면 공백이 전달되므로
			alert("오픈시간 '분'을 입력하세요.");
			document.fr.res_stM.focus();
			return false;
		}
		// 식당 마감시간H
		var res_etH = document.fr.res_etH.value;
		if(res_etH == ""){  //시가 없으면 공백이 전달되므로
			alert("마감시간 '시'를 입력하세요.");
			document.fr.res_etH.focus();
			return false;
		}
		// 식당 마감시간M
		var res_etM = document.fr.res_etM.value;
		if(res_etM == ""){  //분이 없으면 공백이 전달되므로
			alert("마감시간 '분'을 입력하세요.");
			document.fr.res_etM.focus();
			return false;
		}
	}
	
	function back(){
		history.back();  // 뒤로가기
	}

	
	<!-- 이미지 미리보기 -->

    function setThumbnail(event){
        var reader = new FileReader();

        reader.onload = function(event){
            var img = document.createElement("img");
            img.setAttribute("src", event.target.result);
            img.setAttribute("class", "col-lg-6");
            document.querySelector("div#image_container").appendChild(img);
        };

        reader.readAsDataURL(event.target.files[0]);
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
        background-color: #EEDFE3;
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
        background-color: #fff;
        border-radius: 8px;
        box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
    }

    input[type=text], input[type=date], input[type=number], select, textarea {
        width: 100%;
        padding: 10px;
        margin: 10px 0;
        border: 1px solid #ddd;
        border-radius: 4px;
        background-color: #F7F7F7
        
        
    }

    input[type=submit], input[type=reset] , input[type=button]{
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

<!-- 	<h1>ownJoinForm.jsp(foly)</h1>
	<h2> 사업자 회원가입 </h2> -->
	
	<!-- ****** Top Header Area Start ****** --> <!-- 로그인 -->
 	<jsp:include page="../inc/top_header_area.jsp"/>
    <!-- ****** Top Header Area End ****** -->
	
  	<!-- ****** Header Area Start ****** -->  <!-- 사이트 이름 -->
	<jsp:include page="../inc/header_area.jsp"/>
    <!-- ****** Header Area End ****** -->

    <div class="board_title">
	<h1> 사업자 회원가입 </h1>
	<p style="font-size:20px;" > Foodie Foly의 다양한 기능을 회원이 되어 누려보세요</p>
</div>
    
	<fieldset>
<!-- 	<legend> 사업자 회원가입 </legend> -->
	<form action="./OwnJoinAction.lo" method="post" name ="fr" enctype="multipart/form-data" onsubmit ="return check()">
	
	<div class="container">
	<table>
		<colgroup>
			<col width="25%">
			<col width="*">
		</colgroup>
		<tr>
		<td colspan="2" style="background-color: #cee3f6; font-weight: bold;">
            사업자 회원기본정보         
         </td>
      </tr>
      <tr>
         <td>
	사업자번호(아이디) 
	</td>
	<td>
	<input type ="text" name = "own_id" id="own_id" class="id" placeholder="사업자번호" maxlength="20"  style="width: 220px" >
	<br>
	<font id="checkId" size="2"></font>
	
		 </td>
      </tr>
      <tr>
         <td>
		비밀번호
		</td>
         <td>
		<input type = "password" name="own_pw" id="own_pw1" class="pw"> 
		</td>
		
      </tr>
      
      <tr>
         <td>비밀번호 확인</td>
         <td>
		<input type = "password" name="own_pwC" id="own_pw2" class="pw"> 
		<font id="checkPw" size="2"></font>
		</td>
      </tr>
      <tr>
         <td>  
		이름(실명) 
		 </td>
         <td>
		<input type = "text" name = "own_name" id="own_name"> 
		</td>
      </tr>
      <tr>
         <td>
		전화번호
		</td>
         <td> 
		<input type = "text" name = "own_tel" id="own_tel"> 
		</td>
      </tr>
      <tr>
         <td>
		이메일
		 </td>
         <td> 
		<input type = "email" name = "own_email" id="own_email" style="width: 200px"> 
		<input type = "button" id="ebtn" value="이메일 중복확인">
		</td>
      </tr>
      
		
<!--        <tr> -->
<!--          <td colspan="2" style="background-color: #cee3f6; font-weight: bold;"> -->
<!--             선택 사항 -->
           
<!--          </td> -->
<!--       </tr> -->
      
      
		
		<tr>
         <td>
		가게 대표 사진 
		</td>
         <td>
         <div id="image_container"></div>
		<div>
		<input type="file" name="res_img" onchange="setThumbnail(event);" >
		</div>
		</td>
      </tr>
      <tr>
         <td>
		
		가게 이름 
		 </td>
         <td>
		<input type ="text" name = "res_name"> 
		</td>
      </tr>
      <tr>
         <td>
		가게 전화번호 
		</td>
         <td>
		<input type = "text" name = "res_tel"> 
		</td>
      </tr>
      <tr>
         <td>
		가게 주소
		</td>
         <td>
		<input type = "text" name = "res_addr"> 
		</td>
      </tr>
      <tr>
         <td>
		가게 예약금
		</td>
         <td>
		<select name="res_deposit" >
			<option value="">금액</option>
			
			<option value="10000">10,000</option> 
			<option value="20000">20,000</option> 
			<option value="30000">30,000</option> 
			<option value="40000">40,000</option> 
			<option value="50000">50,000</option> 
			<option value="60000">60,000</option> 
			
			<option value="70000">70,000</option> 
			<option value="80000">80,000</option> 
			<option value="90000">90,000</option> 
			<option value="100000">10,0000</option> 

		</select>
		원
		
		</td>
      </tr>
      <tr>
         <td>
		업종
		 </td>
         <td>
		<select name="res_type" >
			<option value="">업종</option>
			
			<option value="한식">한식</option> 
			<option value="양식">양식</option> 
			<option value="중식">중식</option> 
			<option value="일식">일식</option> 
			
			<option value="아시안">아시안</option> 
			<option value="족발&보쌈">족발&보쌈</option> 
			<option value="돈까스">돈까스</option> 
			<option value="죽">죽</option> 
			
			<option value="도시락">도시락</option> 
			<option value="분식">분식</option> 
			<option value="카페&디저트">카페&디저트</option> 
			<option value="패스트푸드">패스트푸드</option> 

		</select>
		 </td>
      </tr>
      
		
		<hr>
		
		<tr>
         <td>
		영업여부
		</td>
         <td>
			<input type = "radio" name = "res_mng" value="0" checked> 오픈완료 
			<input type = "radio" name = "res_mng" value="1"> 오픈준비중 <br>
		</td>
      </tr>
      <tr>
         <td>
		
		오픈시간 
		 </td>
         <td>
		<select name="res_stH" >
			<option value="">시</option> 시
			<%
 				for(int i = 0; i <= 24 ;i++){
			%>
			<option value="<%=i%>"><%=i%></option> 
			<%
			 	}
			%>
		</select>
		
		<select name="res_stM" >
			<option value="">분</option> 분

			<option value="00">00</option> 
			<option value="10">10</option> 
			<option value="20">20</option> 
			<option value="30">30</option> 
			<option value="40">40</option> 
			<option value="50">50</option> 

		</select>
		 </td>
      </tr>
      <tr>
         <td>
		<br>
		
		마감시간
		</td>
         <td>
		<select name="res_etH" >
			<option value="">시</option> 시
			<%
 				for(int i = 0; i <= 24 ;i++){
			%>
			<option value="<%=i%>"><%=i%></option> 
			<%
			 	}
			%>
		</select>
		
		<select name="res_etM" >
			<option value="">분</option> 분
			
			<option value="0">00</option> 
			<option value="10">10</option> 
			<option value="20">20</option> 
			<option value="30">30</option> 
			<option value="40">40</option> 
			<option value="50">50</option> 
		</select>
	</td>
      </tr>
		</table>
		<hr>
		
		<div class="click-btn" align="center">
		<br>
		<input type = "submit" value="완료" id="joinok">
		<input type = "button" value="돌아가기" onclick ="back();">
		</div>
	
	</div>
	</form>
	</fieldset>
</body>
</html>