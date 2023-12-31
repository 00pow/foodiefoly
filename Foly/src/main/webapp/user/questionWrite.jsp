<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
 <style>
        body {
            font-family: Verdana,  fantasy;
            background-color: #F9EAFD; 
            padding: 20px;
         
        }

        h1 {
            color: #FF8080 !important; 
            border-bottom: 2px solid #FF8080;
            padding-bottom: 10px;
            margin-bottom: 20px;
        }
         h3 {
           
            color:  #FF8080 !important; /* 글자색이 안바껴서 !import쓰니깐 바뀜 */
            pad	ding: 10px; 
            border-radius: 5px; 
        }
		td {
   		 color: #FF8080 !important;
		}

        .box {
        text-align: center ;
             width: 1100px; /* 원하는 너비로 설정 */
            flex: 1;
             margin-bottom: 20px;
            background-color: white; /* 백그라운드 색상 */
            padding: 20px; 
            border-radius: 10px; /* 둥근 모서리 */
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1); /* 그림자 효과 */
            
        }
       
        input[type="submit"], input[type="button"] {
            background-color:  #FF8080;
            color: white; 
            border: none; 
            padding: 10px 20px; 
            border-radius: 5px; 
            cursor: pointer; 
            margin-right: 10px;
            margin-bottom: 10px;
            display: inline-block;
        }

        input[type="submit"]:hover, input[type="button"]:hover {
            background-color: #D64F75; 
        }

        input[type="text"], select {
            width: 80%; 
            padding: 10px; 
            margin-bottom: 10px; 
            border: 2px solid #FF8080; 
            border-radius: 5px; 
        }
        .rounded-textarea {
    padding: 10px;
    border: 2px solid #FF8080; /* 테두리 */
    border-radius: 10px; /* Rounded corners */
    resize: none; /* Prevent textarea resizing by the user */
}
    
        

        
  		
		.space 
			{
   				margin-left:20px;
		}

    </style>
	<meta charset="UTF-8">
    <meta name="description" content="">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <!-- The above 4 meta tags *must* come first in the head; any other head content must come *after* these tags -->

    <!-- Title -->
     <h1>Writing an inquiry</h1>  

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
	
	
  	<!-- ****** Top Header Area Start -->
 	<jsp:include page="../inc/top_header_area.jsp"/>
    <!-- ****** Top Header Area End ****** -->
    
<!-- ****** Header Area Start ****** -->
	<jsp:include page="../inc/header_area.jsp"/>
    <!-- ****** Header Area End ****** -->
		

		
		
		
			<div class="container">
		     <div class="box">
           <h3>문의글쓰기</h3>
           <form action="./UserQuestionWrite.us" method="post">
			<table id="notice">
			
		<tr>
			<td> &emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;작성자 : </td>
		    <td colspan="2">
		    	<input type="text" size="35" name="us_id" value="${sessionScope.us_id }" readonly="readonly">
		    </td>
	    </tr>
		<tr>
			<td> &emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;제 목 : </td>
		    <td colspan="2">
				<input type="text" name="qna_sub">
			</td>
	    </tr>
			<tr>
			<td> &emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;내 용 : &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
		    <td colspan="2">
				<textarea rows="6" cols="50" name="qna_cont" class="rounded-textarea"></textarea>
			</td>
	    </tr>
		</table>

	<div id="table_search">
	<input type="hidden" name="qna_num" value="${param.qna_num }">
	<input type="hidden" name="qna_num" value="${param.qna_num }">
		<input type="submit" value="작성완료" >
		<input type="button" value="목록이동" onclick="location.href='./Question.us';">
	</div>
	
</form>

        </div>
    </div>
    </div>	
	
		
</body>
</html>