<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<style>
        body {
            font-family: Verdana,  fantasy;
            background-color: #F9EAFD; 
            padding: 20px;
        }
		 h6 {
            color: #FF8080 !important; 
            }
        h2 {
            color: #FF8080 !important; 
            border-bottom: 2px solid #E56B8B;
            padding-bottom: 10px;
            margin-bottom: 20px;
        }

        .box {
        text-align: center;
             width: 1150px; /* 원하는 너비로 설정 */
            flex: 1;
             margin-bottom: 20px;
            background-color: white; /* 백그라운드 색상 */
            padding: 20px; 
            border-radius: 10px; /* 둥근 모서리 */
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1); /* 그림자 효과 */
            
        }
         .box1 {
        text-align: center;
             width: 1150px; /* 원하는 너비로 설정 */
            flex: 1;
             margin-bottom: 10px;
            background-color: white; /* 백그라운드 색상 */
            padding: 20px; 
            border-radius: 10px; /* 둥근 모서리 */
            
            	
            
        }
      
        input[type="submit"], input[type="button"] {
            background-color: #E56B8B;
            color: white; 
            border: none; 
            padding: 10px 20px; 
            border-radius: 5px; 
            cursor: pointer; 
            margin-right: 10px;
            margin-bottom: 10px;
            display: inline-block;
        }

        input[type="submit"]:hover, input[type="button"]:hover {
            background-color: #D64F75; 
        }

        input[type="text"], input[type="password"] {
            width: 12%; 
            padding: 10px; 
            margin-bottom: 10px; 
            border: 1px solid #E56B8B; 
            border-radius: 5px; 
        }
     
  			select[name="us_birY"], select[name="us_birM"], select[name="us_birD"] {
    width: 4%; /* 각 select 요소의 너비를 14%로 설정하여 한 줄로 표시 */
    padding: 10px; 
    margin-bottom: 10px; 
    border: 1px solid #E56B8B; 
    border-radius: 5px; 
}
.space 
{
   margin-left:20px;
}


    </style>
<meta charset="UTF-8">
 <meta name="description" content="">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <!-- The above 4 meta tags *must* come first in the head; any other head content must come *after* these tags -->

 <!-- Title -->
<title>소비자 정보 업데이트 페이지</title>


    <!-- Favicon -->
    <link rel="icon" href="img/core-img/favicon.ico">

    <!-- Core Stylesheet -->
    <link href="style.css" rel="stylesheet">

    <!-- Responsive CSS -->
    <link href="css/responsive/responsive.css" rel="stylesheet">
<script type="text/javascript">

	// 로그인시 정보 입력여부 체크 
	function check(){
		
		// 유저 아이디
		var us_id = document.fr.us_id.value;
		if(us_id == ""){ <% //아이디가 없으면 공백이 전달되므로%>
			alert("아이디를 입력하세요.");
			document.fr.us_id.focus();
			return false;
		}
		
		// 유저 비밀번호
		var us_pw = document.fr.us_pw.value;
		if(us_pw == ""){  <%//비밀번호가 없으면 공백이 전달되므로%>
			alert("비밀번호를 입력하세요.");
			document.fr.us_pw.focus();
			return false;
		}
		
		// 유저 닉네임
		var us_nick = document.fr.us_nick.value;
		if(us_nick == ""){  <%//닉네임이 없으면 공백이 전달되므로%>
			alert("비밀번호를 입력하세요.");
			document.fr.us_nick.focus();
			return false;
		}
		
		// 유저 이메일
		var us_email = document.fr.us_email.value;
		if(us_email == ""){  <%//이메일이 없으면 공백이 전달되므로%>
			alert("이메일을 입력하세요.");
			document.fr.us_email.focus();
			return false;
		}
		
		
	}


</script>


</head>
<body>
	<!-- ****** Top Header Area Start ****** -->
 	<jsp:include page="../inc/userLogin_top_header_area.jsp"/>
    <!-- ****** Top Header Area End ****** -->
    
		
 	<!-- ****** 마이페이지 메뉴 시작 ****** --> <!-- text-center 어디에 쏟아야할지 어디해야 메인이동 박스가 중간에 가나 -->
 	<jsp:include page="../inc/mypage.jsp"></jsp:include>
    <!-- ****** 마이페이지 메뉴 끝 ****** -->	
		
	
	
	
		<fieldset>
				<h2> 나의정보 수정하기</h2>
			 <form action="./MypageUpdateAction.us" method="POST" name="fr" onsubmit="return checnk()" style="text-align: center;">
					<p></p>
					<p></p>
					<p></p>
			 	
			 	<h6>이름 :&nbsp;&nbsp;<span class='space'> </span> <input type="text" name="us_name" value="${requestScope.dto.us_name}"><br></h6>
			 	<h6>아이디 :&nbsp;&nbsp;&nbsp; <input type="text" name="us_id" value="${requestScope.dto.us_id}"readonly="readonly"  ><br></h6>
			 	<h6>비밀번호 :  <input type="password" name="us_pw" value="${requestScope.dto.us_pw}"><br></h6>
			 	<!--  비밀번호 바꾸는 것도 넣어주기 -->
			 	<h6>닉네임 :&nbsp;&nbsp;&nbsp; <input type="text" name ="us_nick" value="${requestScope.dto.us_nick}"><br></h6>
			 	<h6>이메일 : &nbsp;&nbsp;&nbsp;<input type="text" name="us_email" value="${requestScope.dto.us_email}" readonly="readonly"><br></h6>
		 		<h6>생년월일 :&nbsp;&nbsp;
					<select name="us_birY">
						<option value="${requestScope.dto.us_birY}">${requestScope.dto.us_birY}</option> 년
						<%
			 				for(int i = 1990; i <= 2023 ;i++){
						%>
						<option value="<%=i%>"><%=i%></option> 
						<%
						 	}
						%>
					</select>
					
					<select name="us_birM" >
						<option value="${requestScope.dto.us_birM}" >${requestScope.dto.us_birM}</option> 월
						<%
			 				for(int i = 1; i <= 12 ;i++){
						%>
						<option value="<%=i%>"><%=i%></option> 
						<%
						 	}
						%>
					</select>
					
					<select name="us_birD" >
						<option value="${requestScope.dto.us_birD}" >${requestScope.dto.us_birD}</option> 일
						<%
			 				for(int i = 1; i <= 30 ;i++){
						%>
						<option value="<%=i%>"><%=i%></option> 
						<%
						 	}
						%>
					</select>
					<br>
					
			 	<h6>전화번호 : <input type="text" name="us_tel" value="${requestScope.dto.us_tel} "><br></h6>
			 	<h6>주소 :&nbsp;&nbsp;&nbsp;<span class='space'> </span> <input type="text" name="us_addr" value="${requestScope.dto.us_addr}" ><br></h6>
			 	
			 <span class='space'> </span><span class='space'> </span><span class='space'> </span> <input type="submit" value="수정완료">
				
				</form>
		</fieldset>
		
		
</body>
</html>