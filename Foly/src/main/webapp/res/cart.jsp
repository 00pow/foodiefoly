<%@page import="java.util.ArrayList"%>
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

<!-- Title -->
<!--     <title>Foodidy foly site</title> -->

 <!-- Favicon -->
    <link rel="icon" href="img/core-img/favicon.ico">

    <!-- Core Stylesheet -->
    <link href="style.css" rel="stylesheet">

    <!-- Responsive CSS -->
    <link href="css/responsive/responsive.css" rel="stylesheet">

<link rel="stylesheet" href="main/webapp/css/others/font-awesome.min.css">
    
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
<script src="./js/code.jquery.com_jquery-3.7.1.js"></script>
<script type="text/javascript">

	function back(){
		history.back();  // 뒤로가기
	}

</script>
<style>
@font-face {
    font-family: 'omyu_pretty';
    src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2304-01@1.0/omyu_pretty.woff2') format('woff2');
    font-weight: normal;
    font-style: normal;
}

body {
  font: 90%/1.4 system-ui;
  margin: 0;
  font-family: sans-serif;
  text-align: center;
  background-color: #fff; 
}

h1 {  
        text-align: center; 
        color: #333; 
         margin-top: 20px; 
    } 
    
header {
  padding: 7vh 5vw;
  border-bottom: 1px solid #ddd;
}
header h1,
header p {
  margin: 0;
}
footer {
  padding: 7vh 5vw;
  border-top: 1px solid #ddd;
}
aside {
  padding: 7vh 5vw;
}
.primary {
  overflow: auto;
  scroll-snap-type: both mandatory;
  /* height: 80vh; */
}
@media (min-width: 40em) {
  main {
    display: flex;
  }
  aside {
    flex: 0 1 20vw;
    order: 1;
    border-right: 1px solid #ddd;
  }
  .primary {
    order: 2;
  }
}
table {
  border-collapse: collapse;
  border: 1px solid #444444;
  margin-left: auto;
  margin-right: auto;
}
th,
td {
  border: 1px solid #aaa;
  background-clip: padding-box;
  scroll-snap-align: start;
}
tbody tr:last-child th,
tbody tr:last-child td {
  border-bottom: 0;
}
thead {
  z-index: 1000;
  position: relative;
}
th,
td {
  padding: 0.6rem;
  min-width: 6rem;
  text-align: left;
  margin: 0;
}
thead th {
  position: sticky;
  top: 0;
  border-top: 0;
  background-clip: padding-box;
}
thead th.pin {
  left: 0;
  z-index: 1001;
  border-left: 0;
}
tbody th {
  background-clip: padding-box;
  border-left: 0;
}
tbody {
  z-index: 10;
  position: relative;
}
tbody th {
  position: sticky;
  left: 0;
}
thead th,
tbody th {
  background-color: #f8f8f8;
}

 input[type=submit], input[type=reset] , input[type=button]{ 
         padding: 10px 20px; 
         border: none; 
        background-color: #333; /* 버튼 배경색 */ 
         color: #fff; /* 버튼 글자색 */
        cursor: pointer; 
         margin: 10px 0; 
         transition: background-color 0.3s; 
         border-radius: 4px; 
        
     } 

</style>
</head>
<body>

<!-- ****** Top Header Area Start ****** --> <!-- 로그인 -->
 	<jsp:include page="../inc/top_header_area.jsp"/>
    <!-- ****** Top Header Area End ****** -->
	
  	<!-- ****** Header Area Start ****** -->  <!-- 사이트 이름 -->
	<jsp:include page="../inc/header_cart.jsp"/>
    <!-- ****** Header Area End ****** -->

<!-- 	<h1> cart.jsp (foly)</h1> -->
<!-- 	<h1> 장바구니 페이지 (foly)</h1> -->
	
	
	<!-- 세션에 저장된 로그인 정보 확인 -->
	<c:if test="${empty sessionScope.us_id}">
		<c:redirect url="./UserLogin.lo"/>
	</c:if>
	
	
	<!-- 장바구니 목록 -->
    <h1> 나의 장바구니 </h1>

       		<%
       			int i = 0;
       		
       		%>
       		<fieldset>
            <form action ="PayOption.pay" method="post" >
            
	        <c:forEach var="cartList" items ="${cartList}">
	               
 	        <% if(i == 0){ %> 
			        식당명 : ${cartList.res_name }
			        <input type ="hidden" value="${cartList.res_name }" name="res_name"> <br>
					장바구니 수량 : ${cartList.cart_amount }
					<input type="hidden" value="${cartList.cart_amount }" name ="cart_amount" readonly = "readonly"> <br>
	        		 총 금액 : ${cartList.cart_price_total } 
	        		 <input type="hidden" value="${cartList.cart_price_total }" name ="cart_price_total "readonly = "readonly"> <br>  
					<input type="hidden" value="${cartList.own_id }" name="own_id">
					
					<input type ="submit" value="결제하기" >
	        		<input type="button" value="모두 삭제" onclick ="location.href='CartDelete.re'">
	        		<input type="button" value="뒤로가기" onclick ="location.href='./ResDetail.re?own_id=${cartList.own_id}';">
					
<!-- 					<div role="region" aria-label="data table" tabindex="0" class="primary"> -->
					
				<table border="1">
					<thead>
					 <tr>
						<th class="pin">메뉴그림</th>
			    		<th>메뉴번호</th>
			        	<th>메뉴이름</th>
			        	<th>메뉴가격</th>
			        	<th>수량</th>
			       	</tr>
			       	</thead>
		       	
 	        	<% i++; }
 	        else{ %> 
 	         <tbody>
		         	<tr>
						<td><img src="./upload/${cartList.me_img}" name="me_img" width="150px" height="150px"></td>
		                <td>${cartList.me_num }</td>
		                <td>${cartList.me_name}</td>
		                <td>${cartList.me_price }</td>
		                <td>${cartList.me_amount }</td>
		            </tr>
		             </tbody>
		            		       	
 	        	<%  }%>
			
	        </c:forEach>
	        	</table>
	        </form>
   			 </fieldset>	
	        

				
			
		
	<!-- ****** Footer menu Area Start ****** --> <!-- footer -->
 	<jsp:include page="../inc/footer_menu_area.jsp"/>
    <!-- ****** Footer menu Area End ****** -->

</body>
</html>	