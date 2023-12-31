<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
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

</head>
<body>
<!-- 	<h1> ownMain.jsp (foly)</h1>
		<h1> 사업자 메인페이지 (foly)</h1> 
-->
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
	<!-- 세션에 저장된 로그인 정보 확인 -->
	<c:if test="${empty sessionScope.own_id}">
		<c:redirect url="./OwnLogin.lo"/>
		
	</c:if>
	
  	<!-- ****** Top Header Area Start ****** -->
 	<jsp:include page="../inc/ownLogin_top_header_area.jsp"/>
    <!-- ****** Top Header Area End ****** -->
	
  	<!-- ****** Header Area Start ****** -->
	<jsp:include page="../inc/header_area.jsp"/>
    <!-- ****** Header Area End ****** -->
    
       <!-- ******Owner Categories Area Start ****** --> <!-- 사업자 페이지 카테고리 사진 -->
    <section class="categories_area clearfix" id="about">
        <div class="container">
            <div class="row">
                <div class="col-12 col-md-6 col-lg-4">
                    <div class="single_catagory wow fadeInUp" >
                        <img src="img/catagory-img/hansick.png" alt="" width="400px" height="200px">
                        <div class="catagory-title">
                            <a href="./OwnInfo.ow">
                                <h5>내정보관리</h5>
                            </a>
                        </div>
                    </div>
                </div>
                <div class="col-12 col-md-6 col-lg-4">
                    <div class="single_catagory wow fadeInUp" >
                        <img src="img/catagory-img/mi.png" alt="" width="400px" height="200px">
                        <div class="catagory-title">
                            <a href="./OwnRestaurant.ow">
                                <h5>매장정보관리</h5>
                            </a>
                        </div>
                    </div>
                </div>
                <div class="col-12 col-md-6 col-lg-4">
                    <div class="single_catagory wow fadeInUp" >
                        <img src="img/catagory-img/china.png" alt="" width="400px" height="200px">
                        <div class="catagory-title">
                            <a href="./OwnMenu.ow">
                                <h5>메뉴관리</h5>
                            </a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
         <div class="container">
           	<div class="row">  
                <div class="col-12 col-md-6 col-lg-4">
                    <div class="single_catagory wow fadeInUp" >
                        <img src="img/catagory-img/japan.png" alt="" width="400px" height="200px">
                        <div class="catagory-title">
                            <a href="./OwnBooking.ow">
                                <h5>예약관리</h5>
                            </a>
                        </div>
                    </div>
                </div><div class="col-12 col-md-6 col-lg-4">
                    <div class="single_catagory wow fadeInUp" >
                        <img src="img/catagory-img/asian.png" alt="" width="400px" height="200px">
                        <div class="catagory-title">
                            <a href="./OwnPickup.ow">
                                <h5>포장관리</h5>
                            </a>
                        </div>
                    </div>
                </div>
                 <div class="col-12 col-md-6 col-lg-4">
                    <div class="single_catagory wow fadeInUp" >
                        <img src="img/catagory-img/jockandbo.png" alt="" width="400px" height="200px">
                        <div class="catagory-title">
                            <a href="./OwnReview.ow">
                                <h5>리뷰관리</h5>
                            </a>
                        </div>
                    </div>
                </div>
              </div>
        	</div>

        	
    </section>
    <!-- ****** Categories Area End ****** -->

</body>
</html>	