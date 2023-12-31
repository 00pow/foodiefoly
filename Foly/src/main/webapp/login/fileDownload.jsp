<%@page import="java.net.URLEncoder"%>
<%@page import="java.io.FileInputStream"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<h1>fileDownload.jsp(foly)</h1>
	
	<%
		// 다운로드할 파일의 이름정보 가져오기
//		String fileName = request.getParameter("file_Name");	//jsp
		String fileName = (String)request.getAttribute("fileName");	// MVC
	
		// 다운로드 위치 = 업로드 위치
		String savePath = "upload";		
		
		// 실제 위치정보
		// request.getRealPath(path)
		// 프로젝트 
		ServletContext CTX = getServletContext();
		String downloadPath = CTX.getRealPath(savePath);
		
		System.out.println("다운로드 위치 : " + downloadPath);
		
		// 내가 다운로드 받을 패스를 설정
		String filePath = downloadPath + "\\" + fileName;
		
		System.out.println("다운로드할 파일의 위치 : " + filePath);
		
		//////////////////////////////
		// 파일 다운로드 설정
		
		// 파일을 한번에 많이 읽고 사용가능한 배열 => 버퍼
		byte[] b = new byte[4096];	// 4KB 배열 , 1byte가 배열
		
		// 파일정보를 읽어서 사용가능 하도록 하는 객체
		FileInputStream fis = new FileInputStream(filePath);
		
		// 다운로드할 파일의 마임타입을 설정
		String MimeType = getServletContext().getMimeType(filePath);
		System.out.println("MimeType : " + MimeType);
		
		if(MimeType == null){
			MimeType = "application/octet-stream";
			// -> 잘 알려지지 않은 이진파일
		}
		
		// 응답정보 데이터 처리를 수행
		response.setContentType(MimeType);
		
		/////////////////////////////////////////
		// 파일 인코딩
		
		// 사용자의 브라우저 정보 체크
		String agent = request.getHeader("User-Agent");
		// ie 브라우저여부를 체크(브라우저 정보안에 MSIE / Trident 있으면 true)
		boolean ieBrowser
			= (agent.indexOf("MSIE") > -1) || (agent.indexOf("Trident") > -1);
		
		if(ieBrowser){	// ie브라우저 일때
			fileName = URLEncoder.encode(fileName, "UTF-8").replaceAll("\\+","%20");
			
		}else{ // ie브라우저 아닐때
			fileName = new String(fileName.getBytes("UTF-8"),"iso-8859-1");
		
		}
		
		// 모든 다운로드 파일의 형태를 일반화 시킴
		response.setHeader("Content-Disposition","attachment; filename="+fileName);
		
		// 기존의 JSP-out 내장객체와의 충돌 해결
		out.clear();
		out = pageContext.pushBody();
		
		// 다운로드 시작
		ServletOutputStream out2 = response.getOutputStream();
		int data = 0;
		while( (data=fis.read(b,0,b.length)) != -1){ // 파일이 끝나기 전까지 계속
			out2.write(b,0,data);
		}
		
		out2.flush();
		out2.close();
		fis.close();
				
	%>

</body>
</html>