<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">

    function back(){
        history.back();  // 뒤로가기
    }

</script>
<style>
 		 body {
            font-family: Arial, sans-serif;
            background-color: white;
            padding: 20px;
        } 

        fieldset {
            width: 100%;
            max-width: 500px;
            margin: 20px auto;
            background-color: #FFD1DC; /* 연분홍색 배경 */
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0px 0px 10px rgba(0,0,0,0.1);
        }

        legend {
            text-align: center;
            width: 100%;
            padding: 0;
            border: none;
            font-size: 1.5em;
        }

        label {
            display: block;
            margin-bottom: 5px;
        }

        input[type="text"] {
            width: 100%;
            padding: 10px;
            margin-bottom: 15px;
            border: 1px solid #FF8080;
            border-radius: 5px;
            box-sizing: border-box;
        }

        input[type="submit"], input[type="button"] {
            background-color: #FF8080;
            color: white;
            border: none;
            padding: 10px 20px;
            border-radius: 5px;
            cursor: pointer;
            margin-right: 5px;
        }

        input[type="submit"]:hover, input[type="button"]:hover {
            background-color: #D64F75;
        }
        
		/* 추가 스타일 */
		.center-container {
		    display: flex;
		    justify-content: center;
		    align-items: center;
		    height: 100vh; 
		    margin-top: -150px;
		}
		
		.center-content {
		    text-align: center;
		}

        
</style>
<!-- Favicon -->
    <link rel="icon" href="img/core-img/favicon.ico">

    <!-- Core Stylesheet -->
    <link href="style.css" rel="stylesheet">

    <!-- Responsive CSS -->
    <link href="css/responsive/responsive.css" rel="stylesheet">
</head>
<body>
<!-- 	<h1> userPwCheck.jsp(foly) </h1>
    <h1> 정보 수정을 위한 비밀번호 입력 </h1> -->
    
    
   	<c:if test="${empty sessionScope.us_id}">
		<c:redirect url="./Main.lo"/>
	</c:if>
	
    	
	<!-- ****** Top Header Area Start ****** --> <!-- 로그인 -->
 	<jsp:include page="../inc/top_header_area.jsp"/>
    <!-- ****** Top Header Area End ****** -->

	<!-- ****** 메뉴이동 시작 ****** --> <!-- text-center 어디에 쏟아야할지 어디해야 메인이동 박스가 중간에 가나 -->
	<jsp:include page="../inc/mypage.jsp"/>  
	<!-- ****** 메뉴이동 끝 ****** -->

    <div class="center-container">
    <fieldset class="center-content">
        <legend>비밀번호 입력</legend>
        <form action ="./UserQuestionPwCheck.us" method="post" name="fr">
            비밀번호를 입력해주세요 <br>
            <input type="password" name="us_pw"> <br>
            <input type ="submit" value="확인">
            <input type ="button" value="뒤로가기" onclick ="back();">
        </form>
    </fieldset>
</div>
</body>
</html>