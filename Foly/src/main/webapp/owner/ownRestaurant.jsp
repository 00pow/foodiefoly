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


    </style>
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
</head>
<body>

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
    
	
	<h5> 매장정보관리</h5>

	
	
<fieldset>
    
    <form action="OwnRestaurantPwCheck.ow" method="post" name="fr">
        <div style="display: flex; align-items: center;">
            <!-- 왼쪽에 사진 배치 -->
            <div style="flex: 1; padding: 10px;">
                <img src="./upload/${requestScope.dto.res_img}" name="res_img" width="450px" height="450px" style="margin-left: 150px;"><br>
            </div>
            
            <!-- 오른쪽에 텍스트 배치 -->
            <div style="flex: 1; padding: 10px;">
                가게 이름 :<span class='space'></span> <span class='space'></span><input type="text" value="${requestScope.dto.res_name}" name="res_name" readonly="readonly" style="width: 150px;"><br>
                가게 전화번호 : <input type="text" value="${requestScope.dto.res_tel}" name="res_tel" readonly="readonly"style="width: 150px;"><br>
                가게 주소 :<span class='space'></span><span class='space'></span> <input type="text" value="${requestScope.dto.res_addr}" name="res_addr" readonly="readonly"style="width: 400px;"><br>
                가게 예약금 :<span class='space'></span> <input type="text" value="${requestScope.dto.res_deposit}" name="res_deposit" readonly="readonly"style="width: 150px;"><br>
                가게 업종 :<span class='space'></span> <span class='space'></span><input type="text" value="${requestScope.dto.res_type}" name="res_type" readonly="readonly"style="width: 150px;"><br>
                가게 오픈시간 :
                <input type="text" value="${requestScope.dto.res_stH}" name="res_stH" readonly="readonly"style="width: 50px;"> 
                <input type="text" value="${requestScope.dto.res_stM}" name="res_stM" readonly="readonly"style="width: 50px;"><br>
                가게 마감시간 : 
                <input type="text" value="${requestScope.dto.res_etH}" name="res_etH" readonly="readonly"style="width: 50px;"> 
                <input type="text" value="${requestScope.dto.res_etM}" name="res_etM" readonly="readonly"style="width: 50px;"><br>
                 </span><span class='space'> </span><span class='space'> </span><span class='space'> </span><span class='space'>
                </span><span class='space'> </span><span class='space'> </span><span class='space'> </span><span class='space'> </span><span class='space'> </span><span class='space'> </span><span class='space'> </span><span class='space'> 
              </span><span class='space'></span><span class='space'></span><span class='space'></span><span class='space'></span><span class='space'></span><span class='space'></span><span class='space'></span><span class='space'></span> <input type="submit" value="가게정보 수정하기" style="float: center;" style="font-size: 10px;" margin-right: 10px;><br>
            </div>
        </div>
    </form>
</fieldset>
	
</body>
</html>