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
        h3 {
            color: #FF8080 !important; 
             text-align: center;
            }
		h6 {
            color: #FF8080 !important; 
            }
        h5 {
            color: #FF8080 !important; 
            border-bottom: 2px solid #E56B8B;
            padding-bottom: 10px;
            margin-bottom: 20px;
        }
        input[type="submit"]:hover, input[type="button"]:hover {
            background-color: #D64F75; 
        }

        input[type="text"] {
            width: 18%; 
            padding: 10px; 
            margin-bottom: 10px; 
            border: 1px solid #E56B8B; 
            border-radius: 5px; 
        }
  			select[name="us_birY"], select[name="us_birM"], select[name="us_birD"] {
    width: 6%; /* 각 select 요소의 너비를 14%로 설정하여 한 줄로 표시 */
    padding: 10px; 
    margin-bottom: 10px; 
    border: 1px solid #E56B8B; 
    border-radius: 5px; 
		}
	.form-center {
   		 text-align: center; /* 가운데 정렬을 위한 스타일 */
		}
	.space 
		{
   margin-left:20px;
		}
	
	#us_birth{
	width : 6%;
	}

    </style>
	<meta charset="UTF-8">
    <meta name="description" content="">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <!-- The above 4 meta tags *must* come first in the head; any other head content must come *after* these tags -->

    <!-- Title -->
    <title>마이페이지의 메인</title>

    <!-- Favicon -->
    <link rel="icon" href="img/core-img/favicon.ico">

    <!-- Core Stylesheet -->
    <link href="style.css" rel="stylesheet">

    <!-- Responsive CSS -->
    <link href="css/responsive/responsive.css" rel="stylesheet">
</head>
<body>

	
  	<!-- ****** Top Header Area Start ****** -->
 	<jsp:include page="../inc/userLogin_top_header_area.jsp"/>
    <!-- ****** Top Header Area End ****** -->
    
		
 	<!-- ****** 마이페이지 메뉴 시작 ****** --> <!-- text-center 어디에 쏟아야할지 어디해야 메인이동 박스가 중간에 가나 -->
 	<jsp:include page="../inc/mypage.jsp"></jsp:include>
    <!-- ****** 마이페이지 메뉴 끝 ****** -->	
		<h5>마이페이지</h5>
		<h3 class="space">내정보</h3>
		<div class="form-center">
         
            <form action="MypagePwCheck.us" method="post">
			 <input type="hidden" name="oldurl" 
	          value="<%=request.getParameter("oldurl")%>">
	          
			<h6>   &nbsp;&nbsp;아이디 &nbsp;&nbsp; : <span class='space'></span>
			 	<input type="text" name="us_id" value="${requestScope.dto.us_id}" readonly="readonly"  ><br></h6>
			<h6>  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;이름 &nbsp;&nbsp;&nbsp; :<span class='space'></span> 
			 	<input type="text"  name="us_name" value="${requestScope.dto.us_name}" readonly="readonly" ><br></h6>
			 <h6>	&nbsp;&nbsp;&nbsp;닉네임&nbsp;&nbsp;&nbsp; : <span class='space'></span>
			 	<input type="text"  name="us_nick" value="${requestScope.dto.us_nick}" readonly="readonly"><br></h6>
			 <h6>	&nbsp;&nbsp;&nbsp;이메일 &nbsp;&nbsp; : <span class='space'></span>
			 	<input type="text"  name="us_email" value="${requestScope.dto.us_email}" readonly="readonly"><br></h6>
			 	
			 	
		<h6>	생년월일&nbsp;&nbsp;&nbsp; : <span class='space'></span>

		<input type="text" value="${requestScope.dto.us_birY}" readonly="readonly" name="us_birY"  id="us_birth"> 
		<input type="text" value="${requestScope.dto.us_birM}" readonly="readonly" name="us_birM" id="us_birth"> 
		<input type="text" value="${requestScope.dto.us_birD}" readonly="readonly" name="us_birD" id="us_birth"> 
		


<!-- <select name="us_birM" > -->
<%--     <option value="">${requestScope.dto.us_birM}</option> 월 --%>
<%--     <% for(int i = 1; i <= 12 ;i++) { %> --%>
<%--     <option value="<%=i%>"><%=i%></option>  --%>
<%--     <% } %> --%>
<!-- </select> -->
<!-- <select name="us_birD" value=""> -->
<%--     <option value="">${requestScope.dto.us_birD}</option> 일 --%>
<%--     <% for(int i = 1; i <= 30 ;i++) { %> --%>
<%--     <option value="<%=i%>"><%=i%></option>  --%>
<%--     <% } %> --%>
<!-- </select></h6> -->
					
					
			 <h6>	 &nbsp;전화번호 :&nbsp;&nbsp;  <span class='space'></span> <input type="text" name="res_tel" value="${requestScope.dto.us_tel}"readonly="readonly" ><br></h6>
			 	
			<h6> 	&nbsp;&nbsp;&nbsp;&nbsp;주소&nbsp;&nbsp;&nbsp;&nbsp; :&nbsp;&nbsp; <span class='space'></span> <input type="text" name="res_addr" value="${requestScope.dto.us_addr}"readonly="readonly" ><br></h6>
			 	 
		<span class='space'></span>	  <span class='space'> </span> <span class='space'> </span> <span class='space'> </span>	<input type="submit" value="수정하기" >
			 	<input type="button" value="탈퇴하기" onclick="location.href='./UserDelectPw.us';">
				
				</form>
        </div>
  
   </div>
	
		
</body>
</html>