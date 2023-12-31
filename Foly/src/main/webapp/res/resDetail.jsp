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

<title>Insert title here</title>

<!-- Favicon -->
    <link rel="icon" href="img/core-img/favicon.ico">

    <!-- Core Stylesheet -->
    <link href="style.css" rel="stylesheet">

    <!-- Responsive CSS -->
    <link href="css/responsive/responsive.css" rel="stylesheet">

<link rel="stylesheet" href="main/webapp/css/others/font-awesome.min.css">
    
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">


<script src="../js/code.jquery.com_jquery-3.7.1.min.js"></script>
<script type="text/javascript" src=".js?v=<%=System.currentTimeMillis()%>"></script> 	
<script src="comm.js"></script>
<!-- Jquery 사용 -->
<script type="text/javascript">

function back(){
	history.back();  // 뒤로가기
}



</script>
<!-- kakao map -->
<!-- services와 clusterer, drawing 라이브러리 불러오기 -->
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=APIKEY&libraries=services,clusterer,drawing"></script>

<style>
@font-face {
    font-family: 'omyu_pretty';
    src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2304-01@1.0/omyu_pretty.woff2') format('woff2');
    font-weight: normal;
    font-style: normal;
}

body { 
         
        margin: 0; 
         padding: 0; 
        color: #F07392; 
         font-weight: bold; 
         font-size: 20px; 
         text-align: center;
     } 

  #map {
  
  margin-left : 900px;
  margin-top : -200px;
  
  }  
  
  .shop {
  text-align: left;
  margin-left: 300px;

  }
  
  #res_addr{
  width: 500px;
  }
</style>

</head>
<body>
	<!-- ****** Top Header Area Start ****** --> <!-- 로그인 -->
 	<jsp:include page="../inc/top_header_area.jsp"/>
    <!-- ****** Top Header Area End ****** -->
	
  	<!-- ****** Header Area Start ****** -->  <!-- 사이트 이름 -->
	<jsp:include page="../inc/header_area.jsp"/>
    <!-- ****** Header Area End ****** -->




<!-- 	<h1>resDetail.jsp</h1> -->
<!-- 	<h1>식당상세페이지.jsp</h1> -->
	
		<fieldset>
        <form action="./Booking.re" method="post" class="formcss">

           <hr>
           		<input type="hidden" value="${resInfo.own_id }" name="own_id">
           		
				
				<div class="shop">
   				가게 이름 <input type="text" value="${resInfo.res_name }" name="res_name" readonly="readonly"> &nbsp;&nbsp; <input type="submit" value="식당 예약하기"> <br> 		
				주   소&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				  <input type="text" value="${resInfo.res_addr }" name="res_addr" id="res_addr" readonly="readonly">	 <br>	
				전화번호&nbsp;&nbsp; <input type="text" value="${resInfo.res_tel }" name="res_tel" readonly="readonly" width="100px"> <br>		
				
				오픈시간&nbsp;&nbsp;
					<input type="text" value="${resInfo.res_stH }" name="res_stH"> : 
					<input type="text" value="${resInfo.res_stM }" name="res_stM"> <br>
				마감시간&nbsp;&nbsp; 
					<input type="text" value="${resInfo.res_etH }" name="res_etH"> : 
					<input type="text" value="${resInfo.res_etM }" name="res_etM"> <br>
 					
            	<br>
            	</div>
								
            	
   	        </form>
   	 	</fieldset>
            	<!-- kakako map  -->
                             <div id="map" style="width:300px;height:200px;"></div>
							<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=773255b66befd38bb1ded22084a98c9f"></script>
								<script>
									var container = document.getElementById('map');
									var options = {
										center: new kakao.maps.LatLng(${resInfo.res_la }, ${resInfo.res_lo }), /* la 위도, lo 경도 */
										level: 3
									};
							
									var map = new kakao.maps.Map(container, options);
									

									// 마커가 표시될 위치입니다 
									var markerPosition  = new kakao.maps.LatLng(${resInfo.res_la }, ${resInfo.res_lo }); 

									// 마커를 생성합니다
									var marker = new kakao.maps.Marker({
									    position: markerPosition
									});

									// 마커가 지도 위에 표시되도록 설정합니다
									marker.setMap(map);
								</script>
            	<hr>
       <c:forEach var="menuInfo" items="${menuInfo }"> 
            <fieldset>
            <form action="./MenuDetail.re" method="post">
            <br>
            <div class="menulist">
                 <img src="./upload/${menuInfo.me_img }" width="150px" height="150px"> <br>
                   <input type="hidden" value="${menuInfo.me_num }" name="me_num" readonly="readonly"> <br>
                   메뉴이름 <input type="submit" value="${menuInfo.me_name }" name="me_name" readonly="readonly"> <br><br>
                메뉴가격 ${menuInfo.me_price }  원<input type="hidden" value="${menuInfo.me_price }" name="me_price" readonly="readonly"><br>
                   </div>
                        <input type="hidden" value="${resInfo.own_id }" name="own_id">
                        <input type="hidden" value="${resInfo.res_name }" name="res_name">
                <br> <br>

            </form>
                </fieldset>
       </c:forEach>

            <!-- ** Footer menu Area Start ** --> <!-- footer -->
     <jsp:include page="../inc/footer_menu_area.jsp"/>
    <!-- ** Footer menu Area End ** -->
</body>
</html>