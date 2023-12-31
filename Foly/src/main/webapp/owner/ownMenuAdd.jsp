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
			 h6 {
            color: #FF8080 !important; 
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

        input[type="text"], input [type="file"] {
            width: 30%; 
            padding: 5px; 
            margin-bottom: 10px; 
            border: 1px solid #E56B8B; 
            border-radius: 5px; 
        }
 		.space 
			{
  				 margin-left:60px;
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
<script type="text/javascript">
	
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
    
    <h5> 메뉴관리 </h5>
	<fieldset>
		
		<form action ="OwnMenuAddAction.ow" method="post" name ="fr" enctype="multipart/form-data" onsubmit="return check();"style="text-align: center;">
			
			<div id="image_container"></div>
			<div>
			<h6>메뉴 사진 : <input type="file" name="me_img" onchange="setThumbnail(event);" width="20px" height="20px"></h6>
			</div>
			
			 <br>
			<h6>메뉴 이름 : <input type ="text" name="me_name" > </h6>
			<h6>메뉴 가격 : <input type ="text" name="me_price" > </h6>
			<h6>메뉴 설명 : <input type ="text" name="me_exp" > </h6>

			<input type ="submit" value="메뉴등록" style="background-color: #E56B8B; color: white; border: 1px solid white;"> 
			<input type ="button" value="뒤로가기" onclick ="back();" style="background-color: #E56B8B; color: white; border: 1px solid white;">
			 
		</form>
	</fieldset>

</body>
</html>	