<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>회원정보</h1>
	<table border = "1">
		<tr>
			<th>회원번호</th>
			<th>회원아이디</th>
			<th>회원이름</th>
			<th>회원나이</th>
		</tr>
		<tr>
			<td>${member.user_no}</td>
			<td>아이디</td>
			<td>이름</td>
			<td>이름</td>
		</tr>
		
	</table>
</body>
</html>