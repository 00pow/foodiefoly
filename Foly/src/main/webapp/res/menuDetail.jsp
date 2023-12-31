<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="description" content="">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">


<title>Foodidy foly site</title>

 <!-- Favicon -->
    <link rel="icon" href="img/core-img/favicon.ico">

    <!-- Core Stylesheet -->
    <link href="style.css" rel="stylesheet">

    <!-- Responsive CSS -->
    <link href="css/responsive/responsive.css" rel="stylesheet">

<link rel="stylesheet" href="main/webapp/css/others/font-awesome.min.css">
    
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">


<script src="./js/code.jquery.com_jquery-3.7.1.min.js"></script>
<script type="text/javascript" src=".js?v=<%=System.currentTimeMillis()%>"></script> 	
<script src="comm.js"></script>
<!-- Jquery 사용 -->
<script type="text/javascript">

$(document).ready(function(){
	
	
	$("#sub").click(function(){	
	var sub = $("#me_amount").val();
	   if(sub>1){
			$("#me_amount").val(sub-1);
	   }
	
	});


	
});

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

.res_name{
 border-bottom: 3px solid black;
 }
 
.me_exp{
width:500px;
  height:250px;
 word-wrap:break-word;
 font-size:15px;
}

.click-btn{
 	text-align: center;
 	}
 	 .board_title{
 	margin-bottom: 30px;
 	font-family: 'omyu_pretty';
 	color: #F07392;
 	text-align: center;
 	
 }
 body {
        font-family: 'omyu_pretty';
        margin: 0;
        padding: 0;
        color: #F07392;
        font-weight: bold;
        font-size: 20px;
        text-align: center;
    }
    
    * {
    box-sizing:border-box;
   
    }
  
   .container {
  width: 900px;
  margin: 10px auto;
} /*폭을 일정하게 담기 위한 css*/

.menu {
  display: flex;
  justify-content: space-between;
  align-items: center;
  height: 50px;
  width: 900px;
  margin: 10px auto;
  background-color: #f4f4f2;
} /* 상단 메뉴를 디자인하는 CSS */

.logo {
  font-family: omyu_pretty';
  font-size: 35px;
  text-decoration: none;
  color: #FF8080;
  margin-left: 3%;
  font-weight: bold;
} /* FoodieFoly font를 위한 CSS*/



.left {
  font-size: 20px;
  font-weight: bold;
}

.res_name {
  font-size: 23px;
  font-weight: bold;
}
.gray {
  text-align: right;
  margin-left: 3%;
  color: gray;
  font-size: 20px;
  font-style: bold;
}

.me_name {
  font-size: 20px;
  font-weight: bold;
  margin-bottom: 0;
}

.me_name2 {
  font-size: 18px;
  margin-top: 3px;
  padding-bottom: 5px;
}

.seconddetail {
  padding-top: 17px;
/*   border-top: 3px solid black; */
  font-size: 13px;
  font-weight: bold;
  padding-bottom: 30px;
  border-bottom: 1px solid lightgray;
  margin: 0;
  
}

.productdetail2 {
  display: flex;
  justify-content: space-between;

  padding-top: 20px;
  padding-bottom: 20px;
  margin: 0;
}

.boxone {
  width: 50%;
  border-right: 2px dotted gray;
}
.boxtwo {
  width: 50%;
}

/* 	img {  */
/*    display: block; */
/*   margin-left: auto;  */
/*    margin-right: auto;  */
/*    float:right; */
/*    width: 40%;  */
/* }  */
    h1 { 
/*         text-align: center; */
        color: #333;
        margin-top: 20px;
    }

    form {
        max-width: 900px;
        margin: 40px auto;
        padding: 20px;
        background-color: #fff;
        border-radius: 8px;
        box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
    }
    
     input[type=text], input[type=date],  select, textarea { 
        width: 100%; 
         padding: 10px; 
         margin: 10px 0; 
         border: 1px solid #ddd;
        border-radius: 4px; 
         background-color: #F7F7F7 
        
        
    } 

input[type=number] {
width:10%;
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
    
    
    textarea {
    font-size: 1.2em;
    }
    
    #me_amount{
    text
    	padding-left: 4%;
    	text-align: center;
    	text : bold;
    }
    
    .tdcenter{
    	padding-left: 20%;
    }
    
</style>

</head>
<body>
	<nav> <!--최상단의 배너-->
	<!-- ****** Top Header Area Start ****** --> <!-- 로그인 -->
 	<jsp:include page="../inc/top_header_area.jsp"/>
    <!-- ****** Top Header Area End ****** -->
	
  	<!-- ****** Header Area Start ****** -->  <!-- 사이트 이름 -->
	<jsp:include page="../inc/header_area.jsp"/>
    <!-- ****** Header Area End ****** -->
    
<!-- 	<h1>메뉴상세</h1>
	<h2>menuDetail.jsp</h2> -->
	
<!--         <div class="menu">
            <a href="" class="logo">FoodieFoly</a>
            <p class="gray">홈 > 카테고리 > 식당 > 메뉴 상세 페이지</p>
        </div> -->
    </nav>

    <section> <!--중반부의 레이아웃-->

 			<fieldset>
           <form action="./MenuAdd.re" method="post" name="frm" >
        <div class="container" id="one"> <!-- 중반부 전체를 감싸는 div 태그-->
           <table>
           <tr>
           <td class="tdcenter">
            <div class="first"> <!--중반부 를 두개의 div태그로 나누어 왼쪽 절반의 구역으로 나눠줌-->
                <p class="res_name"> ${res_name } 의 메뉴 </p>
            </div>
                 <img src="./upload/${menuDetail.me_img}" id="me_img" name="me_img" width="300px" height="300x" align="center">
                 
            <div class="second"> <!--중반부 를 두개의 div태그로 나누어 오른쪾 절반의 구역으로 나눠줌-->
                    

                    <div id="fifth">
                        <div class="emojiright">
                            <p class="me_name"> ${menuDetail.me_name }</p>
                            <p class="me_name2"> ${menuDetail.me_price }</p>

                         
                        </div>
                    </div>
             </div>
             
             <p class="seconddetail">
             <textarea rows="4" cols="50">${menuDetail.me_exp }</textarea>    
                   
			<input type="button" id="sub" value="-" >
			<input type="number" value="1" name="me_amount" id="me_amount" readonly="readonly">
			<input type="button" name="plus" value="+" onclick="++me_amount.value" class="countP">
				
                    
               
           </td>
         <td>
                
          </table>
          
         </div>

         	<input type="hidden" name="res_name" value="${res_name }">
            <input type="hidden" name="me_num" value="${menuDetail.me_num }">
            <input type="hidden" name="me_img" value="${menuDetail.me_img}">
             
          	<input type="hidden" name="me_name" value="${menuDetail.me_name }" >
			<input type="hidden" name="me_price" value="${menuDetail.me_price }">
			
			<input type="hidden" name="own_id" value="${own_id }"><br>
			<div class="click-btn" align="center">
			<input type="submit" value="담기" > 
			<input type = "button" value="돌아가기" onclick ="back();">
			<input type = "button" value="장바구니로" onclick= "location.href='Cart.re'">
			</div>  
	
	</form>
	</fieldset>
</section>

	<!-- ****** Footer menu Area Start ****** --> <!-- footer -->
 	<jsp:include page="../inc/footer_menu_area.jsp"/>
    <!-- ****** Footer menu Area End ****** -->
</body>
</html>
