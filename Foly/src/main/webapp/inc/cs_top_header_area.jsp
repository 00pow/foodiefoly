<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


	<!-- ****** Top Header Area Start ****** -->
    <div class="top_header_area">
        <div>
            <div class="row">
                <div class="col-5 col-sm-6">
                    <!--  Top Social bar start -->
                    <div class="top_social_bar">
                        <a href="./Main.lo" ><img src="img/logo-img/piglogo.png" width="60px" height="50px">FoodieFoly</a>
                    </div>
                </div>
                <!--  Login Register Area -->
                <div class="col-7 col-sm-6">
                    <div class="signup-search-area d-flex align-items-center justify-content-end">
                        <div class="login_register_area d-flex">
                         <c:if test="${empty sessionScope.us_id  }" >
                            <div class="login">
                                <a href="./UserLogin.lo">로그인</a>
                            </div>
                           </c:if>
                         <c:if test="${not empty sessionScope.us_id}" >
                            <div class="login">
                                <a href="./Logout.lo">로그아웃</a>
                            </div>
                           </c:if>
                            <div class="login">
                                <a href="./Join.lo">회원가입</a>
                            </div>
                            <div class="register">
                                <a href="./CsCenter.cs">고객센터</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- ****** Top Header Area End ****** -->	
	
