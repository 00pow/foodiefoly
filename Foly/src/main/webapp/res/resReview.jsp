<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	<fieldset>
		<form action="">
			<table id="notice">
					<tr>
						<th class="ttitle" colspan="3">아이티윌 게시판</th>
					</tr>
					<tr>
						<td>작성자 :</td>
						<td colspan="2"><input type="text" size="35" id="wInput"
							name="name"></td>
					</tr>
					<tr>
						<td>비밀번호 :</td>
						<td colspan="2"><input type="password" id="wInput"
							name="pass"></td>
					</tr>
					<tr>
						<td>제 목 :</td>
						<td colspan="2"><input type="text" id="wInput" name="subject">
						</td>
					</tr>
					<tr>
						<td>내 용 :</td>
						<td colspan="2"><textarea rows="" cols="" id="wInput"
								name="content"></textarea></td>
					</tr>
				</table>
		</form>
	</fieldset>

</body>
</html>