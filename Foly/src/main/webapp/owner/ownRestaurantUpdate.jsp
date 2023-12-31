<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<style>
        body {
            font-family: Verdana,  fantasy;
            background-color: #F9EAFD; 
            padding: 20px;
        }

        h5 {
            color: #FF8080 !important; 
            border-bottom: 2px solid #E56B8B;
            padding-bottom: 10px;
            margin-bottom: 20px;
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

        input[type="text"], select {
            width: 50%; 
            padding: 10px; 
            margin-bottom: 10px; 
            border: 1px solid #E56B8B; 
            border-radius: 5px; 
        }
 		.space 
			{
  				 margin-left:15px;
				}
	

  /* input[type="text"]와 select 요소의 넓이를 작게 조절합니다. */
  input[type="text"]{
    width: 40%; /* 원하는 넓이로 조절하세요. */
    padding: 10px;
    margin-bottom: 10px;
    border: 1px solid #E56B8B;
    border-radius: 5px;
  }
    select {
    width: 20%; /* 원하는 넓이로 조절하세요. */
    padding: 10px;
    margin-bottom: 10px;
    border: 1px solid #E56B8B;
    border-radius: 5px;
  }
  
  

</style>
			

    </style>
    <meta charset="UTF-8">
    <meta name="description" content="">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <!-- The above 4 meta tags *must* come first in the head; any other head content must come *after* these tags -->

    <!-- Title -->
    <title>사업자 내정보 수정하기</title>

    <!-- Favicon -->
    <link rel="icon" href="img/core-img/favicon.ico">

    <!-- Core Stylesheet -->
    <link href="style.css" rel="stylesheet">

    <!-- Responsive CSS -->
    <link href="css/responsive/responsive.css" rel="stylesheet">

<script type="text/javascript">

	// 가게 정보가 잘 입력여부 체크 
	function check(){
		
		// 가게이름
		var res_name = document.fr.res_name.value;
		if(res_name == ""){  //가게이름이 없으면 공백이 전달되므로
			alert("가게이름을 입력하세요.");
			document.fr.res_name.focus();
			return false;
		}
		// 가게전화번호
		var res_tel = document.fr.res_tel.value;
		if(res_tel == ""){  //가게전화번호가 없으면 공백이 전달되므로
			alert("가게전화번호를 입력하세요.");
			document.fr.res_tel.focus();
			return false;
		}
		// 가게주소
		var res_addr = document.fr.res_addr.value;
		if(res_addr == ""){  //가게주소 없으면 공백이 전달되므로
			alert("가게주소를 입력하세요.");
			document.fr.res_addr.focus();
			return false;
		}
		
		// 예약금
		var res_deposit = document.fr.res_deposit.value;
		if(res_deposit == ""){  //예약금이 없으면 공백이 전달되므로
			alert("예약금을 입력하세요.");
			document.fr.res_deposit.focus();
			return false;
		}
		
		// 업종
		var res_type = document.fr.res_type.value;
		if(res_type == ""){  //업종 없으면 공백이 전달되므로
			alert("업종을 입력하세요.");
			document.fr.res_type.focus();
			return false;
		}
		
		// 오픈시간 '시'
		var res_stH = document.fr.res_stH.value;
		if(res_stH == ""){  //오픈시간 '시' 없으면 공백이 전달되므로
			alert("오픈시간 시를 입력하세요.");
			document.fr.res_stH.focus();
			return false;
		}
		// 오픈시간 분
		var res_stM = document.fr.res_stM.value;
		if(res_stM == ""){  //오픈시간 분 없으면 공백이 전달되므로
			alert("오픈시간 분을 입력하세요.");
			document.fr.res_stM.focus();
			return false;
		}
		
		// 마감시간 시
		var res_etH = document.fr.res_etH.value;
		if(res_etH == ""){  //마감시간 시가 없으면 공백이 전달되므로
			alert("마감시간 시를 입력하세요.");
			document.fr.res_etH.focus();
			return false;
		}
		
		
		// 마감시간 분
		var res_etM = document.fr.res_etM.value;
		if(res_etM == ""){  //마감시간 분 없으면 공백이 전달되므로
			alert("마감시간 분을 입력하세요.");
			document.fr.res_etM.focus();
			return false;
		}
		
		
	

	}
	
	
	function back(){
		history.back();  // 뒤로가기
	}

</script>


</head>
<body>


<!-- 	<h1> ownRestaurantUpdate.jsp (foly)</h1>
	<h1> 사업자 매장정보관리 페이지 - 가게정보 수정(foly)</h1> -->
	
	<!-- 세션에 저장된 로그인 정보 확인 -->
	<c:if test="${empty sessionScope.own_id}">
		<c:redirect url="./OwnLogin.lo"/>
	</c:if>
	
   	<!-- ****** Top Header Area Start ****** -->
 	<jsp:include page="../inc/ownLogin_top_header_area.jsp"/>
    <!-- ****** Top Header Area End ****** -->
   
   
    <!-- 사업자 메뉴이동 inc -->
      <jsp:include page="../inc/own.jsp"></jsp:include>
    <!-- 사업자 메뉴이동 inc -->

	<h5> 가게 정보 수정</h5>
		<fieldset>
		
		<form action ="OwnRestaurantUpdatePro.ow" method="post" name ="fr"  onsubmit="return check()">
			<div style="display: flex; align-items: center;">
			<!-- 왼쪽에 사진 배치 -->
			 <div style="flex: 1; padding: 10px;">
               <img src="./upload/${requestScope.dto.res_img }" name="res_img" width="450px" height="450px" style="margin-left: 150px;"> <br>
            </div>
             <!-- 오른쪽에 텍스트 배치 -->
            <div style="flex: 1; padding: 10px;">
			
			가게 이름 :<span class='space'> </span><span class='space'> </span> <input type="text" value="${requestScope.dto.res_name}" name="res_name"> <br>
 			가게 전화번호 : <input type="text" value="${requestScope.dto.res_tel}" name="res_tel"> <br>
			가게 주소 :<span class='space'> </span><span class='space'> </span> <input type="text" value="${requestScope.dto.res_addr}" name="res_addr"> <br>
			
			가게 예약금 : <span class='space'> </span>
			<select name="res_deposit" >
				<option value="${requestScope.dto.res_deposit}">${requestScope.dto.res_deposit}</option>
				
				<option value="0">0</option> 
				
				<option value="10000">10,000</option> 
				<option value="20000">20,000</option> 
				<option value="30000">30,000</option> 
				<option value="40000">40,000</option> 
				<option value="50000">50,000</option> 
				<option value="60000">60,000</option> 
				
				<option value="70000">70,000</option> 
				<option value="80000">80,000</option> 
				<option value="90000">90,000</option> 
				<option value="100000">100,000</option> 

			</select>
			원
			<br>
			<br>
				
	
			업종 :&nbsp;&nbsp;<span class='space'> </span><span class='space'> </span><span class='space'> </span><span class='space'> </span>
			<select name="res_type" >
				<option value="${requestScope.dto.res_type}">${requestScope.dto.res_type}</option>업종
				
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
				<option value="까페&디저트">까페&디저트</option> 
				<option value="패스트푸드">패스트푸드</option> 
	
			</select>
			
			<br>
			<br>	
			영업여부 :<span class='space'> </span><span class='space'> </span> <span class='space'> </span> 
				<input type = "radio" name = "res_mng" value="0" checked> 오픈완료 
				<input type = "radio" name = "res_mng" value="1"> 오픈준비중 <br>
				<br>
			오픈시간 : &nbsp;<span class='space'> </span><span class='space'> </span>
			<select name="res_stH" >
				<option value="${requestScope.dto.res_stH}">${requestScope.dto.res_stH}</option> 시
				<%
	 				for(int i = 0; i <= 24 ;i++){
				%>
				<option value="<%=i%>"><%=i%></option> 
				<%
				 	}
				%>
			</select>
			
			<select name="res_stM" >
				<option value="${requestScope.dto.res_stM}">${requestScope.dto.res_stM} </option> 분
	
				<option value="0">00</option> 
				<option value="10">10</option> 
				<option value="20">20</option> 
				<option value="30">30</option> 
				<option value="40">40</option> 
				<option value="50">50</option> 
	
			</select>
			
			<br>
			<br>
			
			마감시간 :&nbsp;&nbsp;<span class='space'> </span><span class='space'> </span>
			<select name="res_etH" >
				<option value="${requestScope.dto.res_etH}">${requestScope.dto.res_etH}</option> 시
				<%
	 				for(int i = 0; i <= 24 ;i++){
				%>
				<option value="<%=i%>"><%=i%></option> 
				<%
				 	}
				%>
			</select>
			
			<select name="res_etM" >
				<option value="${requestScope.dto.res_etM}">${requestScope.dto.res_etM}</option> 분
				
				<option value="0">00</option> 
				<option value="10">10</option> 
				<option value="20">20</option> 
				<option value="30">30</option> 
				<option value="40">40</option> 
				<option value="50">50</option> 
			</select>
		
			<br>
			<br>	
			<input type ="submit" value="가게정보 수정완료"> 
			<input type ="button" value="돌아가기" onclick ="back();">
			</div>
			</div>
		</form>
	</fieldset>
	
	
</body>
</html>