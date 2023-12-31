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

</head>

<body>
<!-- 
	<h1>카테고리별 식당리스트</h1>
	<h1>serchList.jsp</h1> 
-->
	<div>
		<fieldset>
			<form action="./SerchRes.re" method="get">
				<input type="search" name="serch">
				<input type="submit" value="검색">
			</form>
		</fieldset>
	</div>
	
	<div>
	<c:forEach var="cateSearch" items="${cateSearch }">
		<fieldset>
			<form action="./ResDetail.re" method="post">
				가게 이름 : <input type="submit" value="${cateSearch.res_name }" name="res_name"> <br>		
				주소 : <input type="text" value="${cateSearch.res_addr }" name="res_addr">	 <br>	
				전화번호 : <input type="text" value="${cateSearch.res_tel }" name="res_tel"> <br>		
				영업시간 <br>
				오픈시간
				<input type="text" value="${cateSearch.res_stH }" name="res_stH"> : 
				<input type="text" value="${cateSearch.res_stM }" name="res_stM"> <br>
				마감시간 
				<input type="text" value="${cateSearch.res_etH }" name="res_etH"> : 
				<input type="text" value="${cateSearch.res_etM }" name="res_etM"> <br>
				
				<input type="hidden" value="${cateSearch.own_id }" name="own_id">
				<input type="hidden" value="${cateSearch.own_name }" name="own_name">
				<input type="hidden" value="${cateSearch.res_mng }" name="res_mng">
				<input type="hidden" value="${cateSearch.res_deposit }" name="res_deposit">		
			</form>
		</fieldset>
	</c:forEach>
	</div>

 <!-- ****** Blog Area Start ****** -->
    <section class="blog_area section_padding_0_80" style="width:1250px;">
        <div class="container">
            <div class="row justify-content-center">
                <div class="col-12 col-lg-8">
                    <div class="row">

<!-- ******* List Blog Area Start ******* -->

	
     		<c:forEach var="cateSearch" items="${cateSearch }">             
                <!-- Single Post -->
                <div class="col-12">
                     <div class="list-blog single-post d-sm-flex wow fadeInUpBig" >
                     
                         <!-- Post Thumb -->
                         <div class="post-thumb">
                             <img src="./upload/${cateSearch.res_img }" name="res_img" width="300px" height="300px">
                         </div>

                         <!-- Post Content -->
                         <div class="post-content">
                            
                             <a href="./ResMain.fo">
                                 <h4 class="post-headline">${cateSearch.res_name }</h4>
                             </a>
                              
                             <p>
                              주소 : ${cateSearch.res_addr }<br>
                              오픈시간 : ${cateSearch.res_stH }시 ${cateSearch.res_stM }분<br>
                              마감시간 : ${cateSearch.res_etH }시 ${cateSearch.res_etM }분<br>
                              전화번호 : ${cateSearch.res_tel }
                             </p>
                         </div>
                    </div>
                </div>
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