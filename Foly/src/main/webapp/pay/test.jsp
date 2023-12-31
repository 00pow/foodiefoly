<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="./js/jquery-ui.theme.min.css">
<script src="./js/code.jquery.com_jquery-3.7.1.js"></script>
<script src="./js/code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script>
$( function() {
  $( "input" ).datepicker({
	  changeMonth: true,
      changeYear : true,
      dateFormat: "yy-mm-dd"
  });
  
  $.datepicker.setDefaults({
	    prevText: '이전 달',
	    nextText: '다음 달',
	    monthNames: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'], //월 이름
	    monthNamesShort: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'], //
	    dayNames: ['일', '월', '화', '수', '목', '금', '토'],
	    dayNamesShort: ['일', '월', '화', '수', '목', '금', '토'],
	    dayNamesMin: ['일', '월', '화', '수', '목', '금', '토'],
	    showMonthAfterYear: true,
	    yearSuffix: '년'
	});  
} );
</script>
</head>
<body>
	<form>
		<input type="text">
		<input type="date">
	</form>
</body>
</html>