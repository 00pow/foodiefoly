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
  				 margin-left:40px;
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
        input[type="text"], select {
            width: 15%; 
            padding: 3px; 
            margin-bottom: 10px; 
            border: 1px solid #E56B8B; 
            border-radius: 5px; 
        }

        input[type="submit"]:hover, input[type="button"]:hover {
            background-color: #D64F75; 
        }
				  input[type="password"] ,input[type="hidden"] {
            border: 1px solid #E56B8B; 
        }
        .form-center {
    text-align: center; /* 가운데 정렬을 위한 스타일 */
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
<!--     <h1> ownUpdatePwCheck.jsp(foly) </h1>
    <h1> 수정 위한 비밀번호 입력 </h1> -->


   	<!-- ****** Top Header Area Start ****** -->
 	<jsp:include page="../inc/ownLogin_top_header_area.jsp"/>
    <!-- ****** Top Header Area End ****** -->
   
   
    <!-- 사업자 메뉴이동 inc -->
      <jsp:include page="../inc/own.jsp"></jsp:include>
    <!-- 사업자 메뉴이동 inc -->

   
        <h5> 사업자 개인정보 수정 </h5>
         <fieldset class="form-center">
        <form action ="./OwnInfoContent.ow" method="post" name ="fr">

          <h6>  현재 비밀번호를 입력해주세요</h6> 
            <input type="text" name="own_pw"> <br>
			<input type="hidden" name="own_id" value="${param.own_id }">
			<input type="hidden" name="own_name" value="${param.own_name }">
			<input type="hidden" name="own_tel" value="${param.own_tel }">
			<input type="hidden" name="own_eamil" value="${param.own_email }">
			
            <input type ="submit" value="확인">
            <input type ="button" value="뒤로가기" onclick ="back();">

        </form>
    </fieldset>

</body>
</html>