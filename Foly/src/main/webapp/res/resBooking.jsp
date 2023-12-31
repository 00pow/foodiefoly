<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="description" content="">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <!-- The above 4 meta tags *must* come first in the head; any other head content must come *after* these tags -->

    <!-- Title -->
    <title>Foodie foly site</title>

    <!-- Favicon -->
    <link rel="icon" href="img/core-img/favicon.ico">

    <!-- Core Stylesheet -->
    <link href="style.css" rel="stylesheet">

    <!-- Responsive CSS -->
    <link href="css/responsive/responsive.css" rel="stylesheet">
    
    <!-- <link rel="stylesheet" href="main/webapp/css/others/font-awesome.min.css"> -->
    
<link rel="stylesheet" href="./js/jquery-ui.theme.min.css">
<!-- <script src="./resources/js/validation.js"></script> -->
<!-- <script type="text/javascript" src="https://cdn.jsdelivr.net/npm/daterangepicker/daterangepicker.min.js"></script> -->
<script src="https://cdn.iamport.kr/v1/iamport.js"></script>
<script src="./js/code.jquery.com_jquery-3.7.1.min.js"></script>
<!-- <script src="./js/jquery-ui.min.js"></script> -->
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
	   	var res_name = $("#res_name").val(); //가게이름
	   	var pay_total = $("#res_deposit").val(); //예약금
	   	var date = $("#date").val(); //예약날짜
	   	var bookingTime = $("#bookingTime").val(); //예약시간
	   	var person = $("#person").val(); //인원수
	   	var pay_memo = $("#please").val(); //요청사항
	   	var us_tel = $("#us_tel").val(); //회원전화번호
	   	var us_name = $("#us_name").val(); //회원이름
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
		    		 "us_tel" : rsp.buyer_tel , //전화번호
		    		 "pay_memo" : rsp.custom_data, //요청사항
		    		 "card_num" : rsp.card_number,	//카드승인번호(card_num)
		    		 "pay_card" : rsp.card_name, //카드사
		    		 "us_email" : rsp.buyer_email, 
		    		 "bo_date" : date, //예약일
		    		 "bo_dateH" : bookingTime, //예약시간
		    		 "person" : person//인원수
				}
				console.log(result);
				
			    $.ajax({
			    	url : "./PayBo.pay",
			    	method: "POST",
			    	ContentType : "application/json",
			    	data:result,// 가상주소로 보내는 값
		    		success : function(res){
		    			 console.log(res);

		   	 			var url = './PayBoInfo.pay';
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
</script>
<style>
/* 마진등등.. */
@font-face {
    font-family: 'omyu_pretty';
    src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2304-01@1.0/omyu_pretty.woff2') format('woff2');
    font-weight: normal;
    font-style: normal;
}
  @font-face { 
 	font-family: 'FontAwesome'; 
	src: url('fonts/fontawesome-webfont.eot?v=4.7.0'); 
	src: url('fonts/fontawesome-webfont.eot?#iefix&v=4.7.0') 
		format('embedded-opentype'), 
 		url('fonts/fontawesome-webfont.woff2?v=4.7.0') format('woff2'), 
 		url('fonts/fontawesome-webfont.woff?v=4.7.0') format('woff'), 
		url('fonts/fontawesome-webfont.ttf?v=4.7.0') format('truetype'), 
		url('fonts/fontawesome-webfont.svg?v=4.7.0#fontawesomeregular') 
 		format('svg'); 
 	font-weight: normal; 
 	font-style: normal 
} 
 
  .click-btn{ 
 	text-align: center; 
 	}
 
 .board_title{ 
 	margin-bottom: 30px; 
 	font-family: 'omyu_pretty'; 
 	color: #F07392; 
 	text-align: center 
 	
  } 
 	
	 body { 
         font-family: 'omyu_pretty'; 
        margin: 0; 
         padding: 0; 
        color: #F07392; 
         font-weight: bold; 
         font-size: 20px; 
         width:800px
     } 

     h1 {  
        text-align: center; 
        color: #333; 
         margin-top: 20px; 
    } 

    form { 
         max-width: 600px; 
         margin: 40px auto; 
         padding: 20px; 
        background-color: #fff; 
        border-radius: 8px; 
        box-shadow: 0 0 10px rgba(0, 0, 0, 0.1); 
     } 

     input[type=text], input[type=date], input[type=number], select, textarea { 
        width: 100%; 
         padding: 10px; 
         margin: 10px 0; 
         border: 1px solid #ddd;
        border-radius: 4px; 
         background-color: #F7F7F7 
        
        
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

     input[type=submit]:hover, input[type=reset]:hover {
         background-color: #555; /* 호버 시 버튼 배경색 */ 
     } 
    
    input[type="date"]::-webkit-calendar-picker-indicator { 
 	color: rgba(0, 0, 0, 0); 
	opacity: 1; 
 	display: block; 
 	background: url(https://cdn3.iconfinder.com/data/icons/linecons-free-vector-icons-pack/32/calendar-16.png) */
 		center/80% no-repeat white;  
 	width: 20px; 
 	height: 20px; 
 	border-width: thin; 
 	cursor: pointer; 
 }
    
    
</style>
</head>
<body>
<!-- 	<h1>resBooking.jsp</h1>
	 -->
		
	<!-- ****** Top Header Area Start ****** --> <!-- 로그인 -->
 	<jsp:include page="../inc/top_header_area.jsp"/>
    <!-- ****** Top Header Area End ****** -->
	
  	<!-- ****** Header Area Start ****** -->  <!-- 사이트 이름 -->
	<jsp:include page="../inc/header_area.jsp"/>
    <!-- ****** Header Area End ****** -->
	
	
<div class="board_title">
	<h1> 예약 페이지 </h1>
	<p style="font-size:20px;" > 식당 예약을 더욱 빠르고 편리하게!</p>
</div>
	
	
				<input type="hidden" value="${UserBookingPage.own_id }" name="own_id" id="own_id">
   				<input type="hidden" value="${UserBookingPage.res_name }" name="res_name" id="res_name">		
				<input type="hidden" value="${UserBookingPage.res_tel }" name="res_tel" id="res_tel">		
				<input type="hidden" value="${UserBookingPage.res_addr }" name="res_addr" id="res_addr">
				<input type="hidden" value="${UserBookingPage.res_img }" name="res_img" id="res_img">
				
				<input type="hidden" value="${usDto.us_tel }" name="us_tel" id="us_tel">
				<input type="hidden" value="${usDto.us_name }" name="us_name" id="us_name">
				<input type="hidden" value="${usDto.us_email }" name="us_email" id="us_email">
	
		식당이름<input type="text" value="${UserBookingPage.res_name }" readonly="readonly" id="res_name"><br>
		식당예약금<input type="text" value="${UserBookingPage.res_deposit*1}" readonly="readonly" id="res_deposit" name="res_deposit"><br>
		
		예약날짜 <input type="date" name="date" id="date" min="2023-12-01" max="2024-01-01" required><br>
		예약시간
		<select name="bookingTime" required id="bookingTime">
		  	<option value="" > 예약 시간</option>
		  	<option value="09" >09시00분</option>
		  	<option value="10" >10시00분</option>
		  	<option value="11" >11시00분</option>
		  	<option value="12" >12시00분</option>
		  	<option value="13" >13시00분</option>
		  	<option value="14" >14시00분</option>
		  	<option value="15" >15시00분</option>
		  	<option value="16" >16시00분</option>
		  	<option value="17" >17시00분</option>
		  	<option value="18" >18시00분</option>
		  	<option value="19" >19시00분</option>
		  	<option value="20" >20시00분</option>
	 	</select>
		인원 <input type="number" name="person" min="1" step="1" max="30" id="person" required><br>
		요청사항 입력 <textarea name="please" placeholder="남길 말씀이 있으시면 입력해주세요" id="please"></textarea>
		<div class="click-btn">
		 <input type="button" value="예약금 결제하기" onclick="requestPay()">
		 <input type="button" value="이전 페이지로" onclick="history.go(-1)">
		 </div>
	
	
   	<!-- ****** Footer menu Area Start ****** --> <!-- footer -->
 	<jsp:include page="../inc/footer_menu_area.jsp"/>
    <!-- ****** Footer menu Area End ****** -->
	
</body>
</html>