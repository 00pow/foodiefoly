<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>User Pickup Page</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #F9EAFD;
            padding: 20px;
        }

        h5 {
            color: #FF8080 !important; 
            border-bottom: 2px solid #FF8080;
            padding-bottom: 10px;
            margin-bottom: 20px;
        }

        fieldset {
            border: 1px solid #FF8080;
            border-radius: 10px;
            padding: 20px;
        }

        form {
            text-align: center;
        }

       
        input[type="button"] {
            background-color: #FF8080;
            color: white;
            border: none;
            padding: 10px 20px;
            border-radius: 5px;
            cursor: pointer;
            margin: 10px 0;
        }

        
        input[type="button"]:hover {
            background-color: #D64F75;
        }


 		.submit-btn {
	  background-color: #FF8080;
	  color:white;
	  padding: 10px 20px; /* Increase padding for larger button */
      border:1px solid #E56B8B;
      border-radius: 15px; /* Round the corners */
      cursor: pointer; 
      font-size: 15px; /* Adjust the font size as needed */
	   display: inline-block;
	}
	.submit-btn-container {
    text-align: right; /* Align contents to the right */
}
		 	.submit-btn:hover {
		 background-color: #D64F75; 
	} 
 		
        h2 {
            color: #E56B8B;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
           
        }

        table, th, td {
            border: 1px solid #FF8080;
        }

        th, td {
            padding: 10px;
            text-align: center;
        }

        th {
            background-color: #FF8080;
            color: white;
           
        }

        tr:nth-child(even) {
            background-color: #F4E0F4;
        }

        tr:nth-child(odd) {
            background-color: #F9EAFD;
        }
              .box {
           text-align: center; /* 텍스트를 가운데 정렬 */
   		   margin: 0 auto; /* 좌우 마진을 auto로 설정하여 수평 가운데 정렬 */
    	   width: 100%; /* 부모 요소의 100% 너비로 설정하여 가운데 정렬 */
    		max-width: 1000px; /* 최대 너비 설정 (원하는 너비로 조정) */
            flex: 1;
             margin-bottom: 10px;
            background-color: white; /* 백그라운드 색상 */
            padding: 20px; 
            border-radius: 10px; /* 둥근 모서리 */
            
            }
            .pagination-container {
    text-align: center;
    margin-top: 20px; 
}

.pagination-container a {
    margin: 0 5px;     color: #FF8080; /* Change the color to blue or your desired color */
    text-decoration: none; /* Remove underline from links */
}

.pagination-container a:hover {
    color: darkblue; /* Change the hover color */

}
             
            	
    </style>
    <meta charset="UTF-8">
    <meta name="description" content="">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <!-- The above 4 meta tags *must* come first in the head; any other head content must come *after* these tags -->

    <!-- Title -->
    <title>마이페이지의 메인</title>

    <!-- Favicon -->
    <link rel="icon" href="img/core-img/favicon.ico">

    <!-- Core Stylesheet -->
    <link href="style.css" rel="stylesheet">

    <!-- Responsive CSS -->
    <link href="css/responsive/responsive.css" rel="stylesheet">
</head>
<body>

	<c:if test="${empty sessionScope.us_id}">
		<c:redirect url="./Main.lo"/>
	</c:if>
	
	<!-- ****** Top Header Area Start ****** --> <!-- 로그인 -->
 	<jsp:include page="../inc/top_header_area.jsp"/>
    <!-- ****** Top Header Area End ****** -->

	<!-- ****** 메뉴이동 시작 ****** --> <!-- text-center 어디에 쏟아야할지 어디해야 메인이동 박스가 중간에 가나 -->
	<jsp:include page="../inc/mypage.jsp"/>  
	<!-- ****** 메뉴이동 끝 ****** -->
    
	<h5>나의 문의내역</h5>
	
	<div class="container">
		     <div class="box">
		<form action="./QuestionWrite.us" method="post">
	<table border="1">
		<tr>
		    <th class="no" colspan="3"> No. </th>
		    <th class="nickname" colspan="3"> 작성자 </th>
		    <th class="title" colspan="3"> 제목 </th>
		    <th class="answer" colspan="3"> 답변상태 </th>
		    <th class="date" colspan="3"> 날짜 </th>
		</tr>
		<c:forEach var="qList" items="${qList }">
		<tr>
		    <td colspan="3"> ${qList.qna_num } </th>
		    <td colspan="3"> <input type="hidden" name="us_id" value="${qList.us_id}">${qList.us_id} </th>
		    <td colspan="3"> <a href="./UserInfoContent.us?qna_num=${qList.qna_num }&pageNum=${pageNum }">${qList.qna_sub }</a> </th>
		    <td colspan="3"> ${qList.qna_re }</th>
		    <td colspan="3"> ${qList.date } </th>
		</tr>
			<input type="hidden" name="qna_num" value="${qList.qna_num }">
			<input type="hidden" name="date" value="${qList.date }">	
		</c:forEach>
	</table>
	 </div>
	 	 <div class="submit-btn-container">
			<td><input type="submit" value="글쓰기" class="submit-btn" ><br></td>
		</div>

			</form>
        </div>
    </div>
		<div class="pagination-container">
		
		<c:if test="${startPage > pageBlock }">
		<a href="./Question.us?pageNum=${startPage-pageBlock }">Prev</a>
	</c:if>
	
	<c:forEach var="i" begin="${startPage }" end="${endPage }" step="1">
		<a href="./Question.us?pageNum=${i }" >${i }</a>
	</c:forEach>
	
	<c:if test="${endPage < pageCount }">
		<a href="./Question.us?pageNum=${startPage+pageBlock }" >Next</a>
	</c:if>
	</div>

	<!-- <a href="./QuestionWrite.us"> 글쓰기 </a> -->
</body>
</html>