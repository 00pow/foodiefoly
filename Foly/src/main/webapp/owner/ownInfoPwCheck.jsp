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


<script type="text/javascript">

    function back(){
        history.back();  // 뒤로가기
    }

</script>

</head>
<body>
    <h1> ownPwCheck.jsp(foly) </h1>
    <h1> 정보 수정을 위한 비밀번호 입력 </h1>

    <fieldset>
        <legend> 현재 비밀번호 입력 </legend>
        <form action ="OwnInfoContent.ow" method="post" name ="fr">

            현재 비밀번호를 입력해주세요 <br>
            <input type="text" name="own_pw"> <br>

            <input type ="submit" value="확인">
            <input type ="button" value="뒤로가기" onclick ="back();">

        </form>
    </fieldset>

</body>
</html>