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
			h3 {
            color: #FF8080 !important; 
            font-size: 24px; /* 원하는 크기로 조정하세요 */
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

       input[type="text"] {
    width: 15%;
    padding: 10px;
    margin-bottom: 10px;
    border: 1px solid #E56B8B;
    border-radius: 5px;
    outline: none; /* 포커스 시 테두리 제거 */
    border-color: #your-border-color; /* 원하는 테두리 색상으로 변경 */
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
    <!-- The above 4 meta tags must come first in the head; any other head content must come after these tags -->

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
        history.back();
    }
</script>
</head>
<body>
<!--     <h1> ownMenuContent.jsp (foly)</h1>
    <h1> 사업자 메뉴수정 페이지 - 메뉴 한개만 보임 (foly)</h1> -->


    <!-- 세션에 저장된 로그인 정보 확인 -->
    <c:if test="${empty sessionScope.own_id}">
        <c:redirect url="./OwnLogin.lo"/>
    </c:if>

       <!-- ** Top Header Area Start ** -->
     <jsp:include page="../inc/ownLogin_top_header_area.jsp"/>
    <!-- ** Top Header Area End ** -->


    <!-- 사업자 메뉴이동 inc -->
      <jsp:include page="../inc/own.jsp"></jsp:include>
    <!-- 사업자 메뉴이동 inc -->
 

    <!-- 메뉴 -->
    <h5>메뉴수정</h5>

    <form action="OwnMenuUpdate.ow" method="get" style="text-align: center;">

           <img src="./upload/${dto.me_img }">

        <input type="hidden" value="${param.pageNum  }" name="pageNum"> <br>
        <input type="hidden" value="${dto.own_id }" name="own_id"> <br>
       <h3> 메뉴 번호 : <input type="text" value="${dto.me_num }" name="me_num"> </h6>
       <h3> 메뉴 이름 : <input type="text" value="${dto.me_name}" name="me_name"> </h6>
       <h3> 메뉴 가격 : <input type="text" value="${dto.me_price }" name="me_price"></h6>
       <h3> 메뉴 설명 : <input type="text" value="${dto.me_exp }" name="me_exp"></h6> 

           <input type="submit" value="수정완료">  
        <input type="button" value="삭제하기" onclick="location.href='./OwnMenuDelete.ow?me_num=${dto.me_num}&pageNum=${param.pageNum }';">
          <input type="button" value="돌아가기" onclick="back();">  

    </form>




</body>
</html>