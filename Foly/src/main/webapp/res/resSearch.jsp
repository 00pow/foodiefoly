<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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

<style>

#ln{
margin: 30px;

}

#res-name{
margin-top : 40px;
}

#map{
float : left;
}


</style>
</head>
<body>
<!-- 
	<h1>카테고리별 식당리스트</h1>
	<h1>serchList.jsp</h1> 
-->

	<!-- ****** Top Header Area Start ****** --> <!-- 로그인 -->
 	<jsp:include page="../inc/top_header_area.jsp"/>
    <!-- ****** Top Header Area End ****** -->

  	<!-- ****** Header Area Start ****** -->  <!-- 사이트 이름 -->
	<jsp:include page="../inc/header_area.jsp"/>
    <!-- ****** Header Area End ****** -->
    
  	<!-- ****** 검색바 시작 ****** -->
	<jsp:include page="../inc/searchBar.jsp"/>
    <!-- ****** 검색바 끝 ****** -->
	

 <!-- ****** Blog Area Start ****** -->
    <section class="blog_area section_padding_0_80" style="width:1250px;">
        <div class="container">
            <div class="row justify-content-center">
                <div class="col-12 col-lg-8">
                    <div class="row">

<!-- ******* List Blog Area Start ******* -->

	
     		<c:forEach var="resSearch" items="${resSearch }">             
                <!-- Single Post -->
                <div >
                <div class="col-12">
                     <div class="list-blog single-post d-sm-flex wow fadeInUpBig" >
                     
                         <!-- Post Thumb -->
                         <div class="post-thumb" id="ln">
                             <img src="./upload/${resSearch.res_img }" name="res_img" width="300px" height="300px">
                         </div>

                         <!-- Post Content -->
                         <div class="post-content">
                            
                             <a href="./ResDetail.re?own_id=${resSearch.own_id }">
                                 <h4 class="post-headline" id="res-name" >${resSearch.res_name }</h4>
                             </a>
                              
                             <p>
                              주소 : 부산광역시 부산진구 ${resSearch.res_addr }<br>
                              오픈시간 : ${resSearch.res_stH }시 ${resSearch.res_stM }분<br>
                              마감시간 : ${resSearch.res_etH }시 ${resSearch.res_etM }분<br>
                              전화번호 : ${resSearch.res_tel }
                             	<input type="hidden" value="${resSearch.res_mng }" name="res_mng">
								<input type="hidden" value="${resSearch.res_deposit }" name="res_deposit">		
                             </p>
                         </div>
                       </div>
                    </div>
                </div>
                
                <br><br>
			</c:forEach>	
							
            </div>
        </div>
    </section>
    <!-- ****** Blog Area End ****** -->
   
   	<!-- ****** Footer menu Area Start ****** -->
 	<jsp:include page="../inc/footer_menu_area.jsp"/>
    <!-- ****** Footer menu Area End ****** -->
    	
	

</body>
</html>