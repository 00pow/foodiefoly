<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<script src="https://cdn.iamport.kr/v1/iamport.js"></script>
<script src="./js/code.jquery.com_jquery-3.7.1.min.js"></script>
<script type="text/javascript">
		   	
	  function requestPay() {
		var IMP = window.IMP;
		IMP.init("imp33034306"); //가맹점 식별코드
		// 사용자에게 결제 화면을 보여주기 전에 서버 코드에서
			var today = new Date();   
		    var hours = today.getHours(); // 시
		    var minutes = today.getMinutes();  // 분
		    var seconds = today.getSeconds();  // 초
		    var milliseconds = today.getMilliseconds();
		    var makeMerchantUid = hours +  minutes + seconds + milliseconds;
		   	//var location = $("#href").val
		   	
		   	// form 태그에 정보들
		   	var me_name = $("#me_name").val(); //메뉴이름
		   	var pay_total = $("#pay_total").val(); //총합금액
		   	var res_name = $("#res_name").val(); //가게이름
		   	var res_addr = $("#res_addr").val(); //가게주소
		   	var us_tel = $("#us_tel").val(); //회원전화번호
		   	var us_name = $("#us_name").val(); //회원이름
		   	var pay_memo = $("#pay_memo").val(); //요청사항
		   	var us_email = $("#us_email").val();
		   	
		    IMP.request_pay({
		      pg: "html5_inicis", //이니시스 결제코드
		      pay_method: "card", //지불방법
		      merchant_uid: "IMP"+makeMerchantUid ,   // 주문번호(중복x), 결제시간으로 설정 (iamport_test_id)
		      name: res_name,	// 결제창에 노출 될 상품명
		      amount: pay_total,       // 금액
		      buyer_name: us_name,
		      buyer_tel: us_tel,
		      buyer_email: us_email
		    }, function (rsp) { // callback
		    	console.log(rsp);
		     	if(rsp.success){
			    	console.log(rsp.merchant_uid);
					console.log(rsp.imp_uid);
					console.log(rsp.pay_method);
					
				    $.ajax({
				    	datatype : "post",
				    	url : "./PaySuccess.pay",
				    	method: "POST",
				    	headers: { "Content-Type": "application/json" },
				    	 data: {// 가상주소로 보내는 값
				    		 "imp_uid" : rsp.imp_uid, //결제 고유번호 (결제 확인하기 위해서 필요함)
				    		"merchant_uid" : rsp.merchant_uid, // 주문번호
				    		 "res_name" : "name",	// 결제창에 노출 될 상품명
				    		 "pay_total" : rsp.paid_amount,	// 결제금액
				    		 "us_name" : rsp.buyer_name, //회원이름
				    		//"pay_date" : today, // 결제일자
				    		 "us_tel" : rsp.buyer_tel , //전화번호
				    		 "pay_memo" : pay_memo, //요청사항
				    		 "card_num" : rsp.card_number,	//카드승인번호(card_num)
				    		 "pay_card" : rsp.card_name, //카드사
				    		 "us_email" : rsp.buyer_email
				    		 //카드사 코드번호 card_code
// 				    	        imp_key: "2633734213860501", // REST API 키
// 				    	        imp_secret: "fndMBAEyuntemrD702tdiqRtC0nX33ZJfoLeK9weHh0a62xamabmlJHzdzt6AGtWVRONIGZXqTk2ihOj" // REST API Secret
				    		 }
				    }).done(function(data) {
				    	 console.log(data);
		     			//성공시 로직
		     		
			     		var msg = "결제가 완료되었습니다";
			     		msg += '\n고유ID' + rsp.imp_uid;
			     		msg += '\n상점 거래 ID'+ rsp.merchant_uid;
			     		msg += '\n결제 금액'+ rsp.paid_amount;
			     		msg += '\n카드 승인 번호'+ rsp.card_number;
			     		alert(msg);
			     		alert(location.href='./PaySuccess.pay'+form.submit());

				    })//done
		     	}else{
		     		//실패시 로직ss
		     		var msg = "결제에 실패하였습니다";
		     		msg += '에러코드'+rsp.error_code+'\n에러내용 : ' + rsp.error_msg;
		     		alert(msg);
		     	}
		    
		    });//IMP.request_pay
		  }//requestPay()

</script>

</head>
<body>
	<c:if test="${empty sessionScope.us_id}">
		<c:redirect url="./Main.lo"/>
	</c:if>
	<fieldset>
	<form action="./PaySuccess.pay" method="post">
	<!-- <form action="./PaySuccess.pay" method="post" onsubmit="retrun requestPay()"> -->
	<a href="./Cart.re">장바구니로 이동</a><br>
	<h3> 포장 내역 </h3>
	<table border="1">
		<tr>
			<th>제품</th>
			<th>수량</th>
			<th>가격</th>
		</tr>
	<c:forEach var="cartList" items="${cartList }">
		<tr>
			<td><input type="text"	value="${cartList.me_name }" id="me_name" name="me_name"></td>
			<td><input type="text"	value="${cartList.me_amount }" id="me_amount" name="me_amount"></td>
			<td><input type="text"	value="${cartList.me_price }" id="me_price" name="me_price">
		</tr>
	</c:forEach>
	</table>
	<h1> 가게이름,주소 </h1>
			<input type="text"	value="${ownDto.res_name }" id="res_name" name="res_name"><br> <!-- 글 크고, 굵게 -->
			<input type="text"	value="${ownDto.res_addr }" id="res_addr" name="res_addr"><br>
			
	
	
	<br>
	전화번호 , 이메일<br>
	<br>
		<input type="text" name="us_tel" id="us_tel" value="${userList.us_tel }" readonly="readonly">
		<input type="button" value="전화번호 변경하기"> <br>	<!-- ajax써서click로 readonly풀고 저장버튼 -->
		<input type="hidden" name="us_email" value="${userList.us_email }" id="us_email"><br>
		<input type="hidden" name="us_name" value="${userList.us_name }" id="us_name"><br>
		
		요청사항
		<input type="text" name="pay_memo"><br>
	 
	<hr>
	
	<hr>
	<h3> 결제 상세 </h3>
	
			상품 금액 <input type="text" name="cart_price_total" id="cart_price_total" value="${cartTotalPrice.cart_price_total}"> 원<br>
			-----------------------<br>
		
			결제 금액 <input type="text" name="pay_total" id="pay_total" value="${100 }">원<br>

	
	<br>
	
	<button onclick="requestPay()">결제하기</button>
	 <!-- <input type="submit" value="결제하기" > -->

	</form>
	</fieldset>
</body>
</html>