<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    
 <!-- ****** 마이페이지 메뉴 시작 ****** --> <!-- text-center 어디에 쏟아야할지 어디해야 메인이동 박스가 중간에 가나 -->
    <header class="header_area text-center">
     
       <div>	
		<div class="box1">
        <div class="row">
                <div class="col-12">
                    <nav class="navbar navbar-expand-lg">
                        <!-- Menu Area Start -->
                        <div class="collapse navbar-collapse justify-content-center " id="yummyfood-nav">
                            <ul class="navbar-nav" id="yummy-nav">
                                <li class="nav-item">
                                    <a class="nav-link" href="Faq.cs">FAQ</a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link" href="Qna.cs">Q&A</a>
                                </li>
                                 <c:if test="${!empty sessionScope.us_id }">
                                	<li class="nav-item">
                                   		 <a class="nav-link" href="UserMain.lo">메인</a>
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
    <!-- ****** CS 상단 메뉴 끝 ****** -->	