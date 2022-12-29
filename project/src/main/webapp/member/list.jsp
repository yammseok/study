<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>회원 리스트</h1>
<table border="1">
<tr>
	<th>이메일</th>
	<th>비밀번호</th>
	<th>닉네임</th>
</tr>
<c:forEach items="${list }" var="mvo">
<tr>
	<td><a href="/mem/detail?email=${mvo.email }">${mvo.email }</a></td>
	<td>${mvo.pwd }</td>
	<td>${mvo.nick_name }</td>
</tr>
</c:forEach>
</table>
<a href="/">처음으로</a>
</body>
</html>