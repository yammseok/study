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
<h1>회원 정보 페이지</h1>
<table border="1">
<tr>
	<th>이메일</th>
	<td>${mvo.email }</td>
</tr>
<tr>
	<th>비밀번호</th>
	<td>${mvo.pwd }</td>
</tr>
<tr>
	<th>닉네임</th>
	<td>${mvo.nick_name }</td>
</tr>
<tr>
	<th>회원가입일</th>
	<td>${mvo.reg_at }</td>
</tr>
<tr>
	<th>마지막접속일</th>
	<td>${mvo.last_login }</td>
</tr>
</table>
<a href="/mem/modify?email=${mvo.email }">수정</a>
<a href="/mem/remove?email=${mvo.email }">삭제</a>
<a href="mem/list">리스트</a>

</body>
</html>