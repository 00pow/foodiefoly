<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<style>
 h5 {
            color: #FF8080 !important; 
            border-bottom: 2px solid #E56B8B;
            padding-bottom: 10px;
            margin-bottom: 20px;
        }
         h6 {
            color: #FF8080 !important; 
            }
        .space 
			{
  			 margin-left:10px;
				}
		  input[type="password"] {
  			   width: 15%; 
            margin-bottom: 10px; 
            border: 1px solid #E56B8B; 
            border-radius: 5px;  
   			 margin-bottom: 10px; /* 아랫줄 간격 설정 */
			}		
					
         input[type="submit"], input[type="button"] {
            background-color: #E56B8B;
            color: white; 
            border: none; 
            padding: 3px 6px; 
            border-radius: 5px; 
            cursor: pointer; 
            margin-right: 10px;
            margin-bottom: 10px;
            display: inline-block;
        }
     .form-center {
    text-align: center; /* 가운데 정렬을 위한 스타일 */
}

        input[type="submit"]:hover, input[type="button"]:hover {
            background-color: #D64F75; 
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

</script>

</head>
<body>
<!--     <h1> ownDelPwCheck.jsp(foly) </h1>
    <h1> 탈퇴 위한 비밀번호 입력 </h1> -->
    
    
    	<!-- ****** Top Header Area Start ****** -->
 	<jsp:include page="../inc/ownLogin_top_header_area.jsp"/>
    <!-- ****** Top Header Area End ****** -->
   
   
    <!-- 사업자 메뉴이동 inc -->
      <jsp:include page="../inc/own.jsp"></jsp:include>
    
    
    
        <h5> 사업자 탈퇴하기 </h5>
			<fieldset class="form-center">
	
		  	<form action="./OwnDel.ow" method="post" >
    					<div style="background-color: white; padding: 5px;"> <!-- 배경색을 분홍색(pink)으로 설정 -->
        		<span class='space'> </span>	<h6>비밀번호를 입력해주세요</h6>
    					</div>
  				<input type="password" name="own_pw" > <br>
    			<input type="submit" value="탈퇴하기" style="background-color: #E56B8B; color: white; border: 1px solid white;">
    	
    		<input type="button" value="뒤로가기" onclick="history.back();" style="background-color: #E56B8B; color: white; border: 1px solid white;">
		</form>
    </fieldset>

</body>
</html>