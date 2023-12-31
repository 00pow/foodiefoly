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
    <title> 주문상세 </title>

    <!-- Favicon -->
    <link rel="icon" href="img/core-img/favicon.ico">

    <!-- Core Stylesheet -->
    <link href="style.css" rel="stylesheet">

    <!-- Responsive CSS -->
    <link href="css/responsive/responsive.css" rel="stylesheet">

<link rel="stylesheet" href="main/webapp/css/others/font-awesome.min.css">
    
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
<script src="https://cdn.iamport.kr/v1/iamport.js"></script>
<script src="./js/code.jquery.com_jquery-3.7.1.min.js"></script>
<script type="text/javascript">
		   	
	  function requestPay() {
		   	
		var IMP = window.IMP;
		IMP.init("imp33034306"); //가맹점 식별코드
			var today = new Date();   
		    var hours = today.getHours(); // 시
		    var minutes = today.getMinutes();  // 분
		    var seconds = today.getSeconds();  // 초
		    var milliseconds = today.getMilliseconds();
		    var makeMerchantUid = hours +  minutes + seconds + milliseconds;
		   	
		   	// form 태그에 정보들
		   	var me_name = $("#me_name").val(); //메뉴이름
		   	var pay_total = $("#pay_total").val(); //총합금액
		   	var res_name = $("#res_name").val(); //가게이름
		   	var res_addr = $("#res_addr").val(); //가게주소
		   	var us_tel = $("#us_tel").val(); //회원전화번호
		   	var us_name = $("#us_name").val(); //회원이름
		   	var pay_memo = $("#pay_memo").val(); //요청사항
		   	var us_email = $("#us_email").val();
		// 사용자에게 결제 화면을 보여주기 전에 서버 코드에서
		    IMP.request_pay({
		      pg: "html5_inicis", //이니시스 결제코드
		      pay_method: "card", //지불방법
		      merchant_uid: "IMP"+makeMerchantUid ,   // 주문번호(중복x), 결제시간으로 설정 (iamport_test_id)
		      name: res_name,	// 결제창에 노출 될 상품명
		      amount: pay_total,       // 금액
		      buyer_name: us_name,
		      buyer_tel: us_tel,
		      buyer_email: us_email,
		      custom_data : pay_memo
		    }, function (rsp) { // callback
		    	console.log(rsp);
		     	if(rsp.success){
		     		var msg = "결제가 완료되었습니다";
			    	console.log(rsp.merchant_uid);
					console.log(rsp.imp_uid);
					console.log(rsp.pay_method);
					var result = {
					 	 "imp_uid" : rsp.imp_uid, //결제 고유번호 (결제 확인하기 위해서 필요함)
			    		 "merchant_uid" : rsp.merchant_uid, // 주문번호
			    		 "res_name" : rsp.name,	// 결제창에 노출 될 상품명
			    		 "pay_total" : rsp.paid_amount,	// 결제금액
			    		 "us_name" : rsp.buyer_name, //회원이름
			    		 //"pay_date" : new Date.toISOString.slice(0,10), // 결제일자
			    		 "us_tel" : rsp.buyer_tel , //전화번호
			    		 "pay_memo" : rsp.custom_data, //요청사항
			    		 "card_num" : rsp.card_number,	//카드승인번호(card_num)
			    		 "pay_card" : rsp.card_name, //카드사
			    		 "us_email" : rsp.buyer_email 
					}
					console.log(result);
					
				    $.ajax({
				    	url : "./PaySuccess.pay?me_name="+me_name,
				    	method: "POST",
				    	ContentType : "application/json",
				    	data:result,// 가상주소로 보내는 값
			    		success : function(res){
			    			 console.log(res);
			                 //location.href = res; 
			                 //location.href = "./PaySuccess.pay"+res;
			   	 			//location.href = "./PayInfo.pay"+res;
			   	 			var url = './PayInfo.pay';
			   	 			location.replace(url);
			              }
 				    });
				    
		     	}else{
		     		//실패시 로직ss
		     		var msg = "결제에 실패하였습니다";
		     		msg += '에러코드'+rsp.error_code+'\n에러내용 : ' + rsp.error_msg;
		     	}
		     		alert(msg);
		    });//IMP.request_pay
		  }//requestPay()
			/* location.href='./PaySuccess.pay'+form.submit(); */

</script>
<style>
@import url('https://fonts.googleapis.com/css2?family=Noto+Sans+KR&display=swap');

:root {
  --button-color: #ffffff;
  --button-bg-color: #0d6efd;
  --button-hover-bg-color: #025ce2;
}

button {
  -webkit-appearance: none;
  -moz-appearance: none;
  appearance: none;
  
  background: var(--button-bg-color);
  color: var(--button-color);
  
  margin: 0;
  padding: 0.5rem 1rem;
  
  font-family: 'Noto Sans KR', sans-serif;
  font-size: 1rem;
  font-weight: 400;
  text-align: center;
  text-decoration: none;
  
  border: none;
  border-radius: 4px;
  
  display: inline-block;
  width: auto;
  
  box-shadow: 0 4px 6px -1px rgba(0, 0, 0, 0.1), 0 2px 4px -1px rgba(0, 0, 0, 0.06);
  
  cursor: pointer;
  
  transition: 0.5s;
}

@font-face {
    
    src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2304-01@1.0/omyu_pretty.woff2') format('woff2');
    font-weight: normal;
    font-style: normal;
}

body { 
         
        margin: 0; 
         padding: 0; 
        color: #000000; 
         font-weight: bold; 
         font-size: 20px; 
         text-align: center;
         width: 1500px;
     } 

     h1 {
        text-align: center; 
        color: #333; 
         margin-top: 20px; 
    } 
    
     h3 {
        color: #ff8080
    } 

     input[type=text], input[type=date], input[type=number], select, textarea { 
        width: auto; 
         padding: 10px; 
         margin: 10px 0; 
         border: 1px solid #ddd;
        border-radius: 4px; 
         background-color: #F7F7F7 ;
         text-align: center; 


    } 

     input[type=submit], input[type=reset] , input[type=button]{ 
         padding: 10px 20px; 
         border: none; 
        background-color: #333; 
         color: #fff; 
        cursor: pointer; 
         margin: 10px 0; 
         transition: background-color 0.3s; 
         border-radius: 4px; 

     }  
     
   
     
    table {
  border-collapse: collapse;
  border: 1px solid #444444;
  margin-left: auto;
  margin-right: auto;
  width: 500px;
}
th,td {
  border: 1px solid #aaa;
  background-clip: padding-box;
  scroll-snap-align: start;
}
	
	#pay_memo{
	width: 500px;
	height: 200px;
	}
		   	
</style>	
</head>
<body>
	<!-- ****** Top Header Area Start ****** --> <!-- 로그인 -->
 	<jsp:include page="../inc/top_header_area.jsp"/>
    <!-- ****** Top Header Area End ****** -->
	
  	<!-- ****** Header Area Start ****** -->  <!-- 사이트 이름 -->
	<jsp:include page="../inc/header_booking.jsp"/>
    <!-- ****** Header Area End ****** -->


	<c:if test="${empty sessionScope.us_id}">
		<c:redirect url="./Main.lo"/>
	</c:if>
	<fieldset>
	<!-- <form action="./PaySuccess.pay" method="post"> -->
	<!-- <form action="./PaySuccess.pay" method="post" onsubmit="retrun requestPay()"> -->

	<h3> 포장 주문 목록 </h3>
	
		<%
                   int i = 0;

               %>
	<c:forEach var="cartList" items="${cartList }">
	 <% if(i == 0){ %> 
	<table border="1">
		<tr>
			<th>제품</th>
			<th>수량</th>
			<th>가격</th>
		</tr>
	 <% i++; }
             else{ %> 
		<tr>
			<td>${cartList.me_name }<input type="hidden"	value="${cartList.me_name }" id="me_name" name="me_name"></td>
			<td>${cartList.me_amount }<input type="hidden"	value="${cartList.me_amount }" id="me_amount" name="me_amount"></td>
			<td>${cartList.cart_price }<input type="hidden"	value="${cartList.cart_price }" id="cart_price " name="cart_price "><br>
		</tr>
		 <%  }%>
		
	</c:forEach>
	
	</table>
	<p> </p>
<!-- 	<h1> 가게이름,주소 </h1> -->

	<h3> 가게 정보 </h3>
	
	<table border="1">
		<tr>
			<th>가게이름</th>
			<th>주소</th>
		</tr>
		<tr>
			<td>${ownDto.res_name }<input type="hidden"	value="${ownDto.res_name }" id="res_name" name="res_name"> </td>
			<td>${ownDto.res_addr }<input type="hidden"	value="${ownDto.res_addr }" id="res_addr" name="res_addr"> </td>
		</tr>
	</table>
	<p></p>
<!-- 	<br> -->
<!-- 	전화번호 , 이메일<br> -->
<!-- 	<br> -->
	
	<h3> 고객 연락처 </h3>
	
	<table border="1">
		<tr>
			<th class="us_tel">전화번호</th>
		</tr>
		<tr>
			<td>${userList.us_tel }<input type="hidden" name="us_tel" id="us_tel" value="${userList.us_tel }" readonly="readonly" ></td>
		</tr>
	</table>
		<input type="button" value="전화번호 변경하기"> <br>	<!-- ajax써서click로 readonly풀고 저장버튼 -->
		<input type="hidden" name="us_email" value="${userList.us_email }" id="us_email">
		<input type="hidden" name="us_name" value="${userList.us_name }" id="us_name">
		
		<h3>요청사항</h3>
		
		<input type="text" name="pay_memo" placeholder="요청사항을 입력하세요" id="pay_memo">
		<input type="hidden" name="cart_price_total" id="cart_price_total" value="${cartTotalPrice.cart_price_total}"> 
	<h3> 결제 상세 </h3>
	
	<table border="1">
		<tr>
<!-- 			<th>상품 금액</th> -->
<%-- 			<td>${cartTotalPrice.cart_price_total} 원 --%>
			
<!-- 			</td> -->
			<th>결제 금액</th>
			<td>${cartTotalPrice.cart_price_total} 원<input type="hidden" name="pay_total" id="pay_total" value="${cartTotalPrice.cart_price_total}"></td>
<%-- 			value="${cartTotalPrice.cart_price_total}" --%>

		</tr>
		
	</table>
	<p></p>
	
	<input type="button" value="장바구니 이동" onclick="./Cart.re">
	<input type="button" value="결제하기" onclick="requestPay()">

	<!-- </form> -->
	</fieldset>

	
            <!-- ** Footer menu Area Start ** --> <!-- footer -->
     <jsp:include page="../inc/footer_menu_area.jsp"/>
    <!-- ** Footer menu Area End ** -->
	
</body>
</html>