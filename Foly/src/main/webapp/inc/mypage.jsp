<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <style>

.navbar.navbar-expand-lg .navbar-nav .nav-link:hover, .navbar.navbar-expand-lg .navbar-nav .active .nav-link{
	color : black;


}
.collapse navbar-collapse justify-content-center{
	text-align: center;
}

.navbar-nav{
	    padding-left: 20%;
}

    </style>    
    
 <!-- ****** 마이페이지 메뉴 시작 ****** --> <!-- text-center 어디에 쏟아야할지 어디해야 메인이동 박스가 중간에 가나 -->
    <header class="header_area text-center">
     
        <div class="container">
            <div class="row">
                <!-- Logo Area Start -->
                <div class="col-12">
                    <div class="logo_area ">
                        <a href="Mypage.us" class="yummy-logo">MyPage</a>
                    </div>
                </div>
            </div>	
<div class="box1">
            <div class="row">
                <div class="col-12">
                    <nav class="navbar navbar-expand-lg">
                        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#yummyfood-nav" aria-controls="yummyfood-nav" aria-expanded="false" aria-label="Toggle navigation"><i class="fa fa-bars" aria-hidden="true" text-aling : center></i> Menu</button>
                        <!-- Menu Area Start -->
                        <div class="collapse navbar-collapse justify-content-center " id="yummyfood-nav">
                            <ul class="navbar-nav" id="yummy-nav">
                                <li class="nav-item active">
                                    <a class="nav-link" href="Mypage.us">내정보</a>
                                </li>
                               
                               
                                <li class="nav-item">
                                    <a class="nav-link" href="UserPickup.us">포장내역</a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link" href="UserBooking.us">식당예약내역</a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link" href="UserReview.us">리뷰관리</a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link" href="Question.us">문의하기</a>
                                </li>
                                <c:if test="${sessionScope.us_id eq 'admin' }" >
	                                <li class="nav-item">
	                                    <a class="nav-link" href="./UserList.cs">회원목록</a>
	                                </li>
	                                <li class="nav-item">
	                                    <a class="nav-link" href="./OwnList.cs">사업자목록</a>
	                                </li>
                                </c:if>
                            </ul>
                        </div>
                    </nav>
                </div>
            </div>
        </div>
        </div>
    </header>
    <!-- ****** 마이페이지 메뉴 끝 ****** -->	