<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>BBS List</title>
	<!-- CSS only -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi" crossorigin="anonymous">
	<!-- Bootstrap -->
	<link href="bootstrap-3.3.7/css/bootstrap.min.css" rel="stylesheet">
	<style>
		#container {
			width: 70%;
			margin: 0 auto;			/* 가로로 중앙에 배치 */
			padding-top: 10%;		/* 테두리와 내용 사이의 패딩 여백 */
		}
		
		#list {
			text-align: center;
		}
	
		#write {
			text-align: right;
		}
		
		
		/* Bootstrap 수정 */
		.table > thead {
			background-color: #b3c6ff;
		}
		.table > thead > tr > th {
			text-align: center;
		}
		.table-hover > tbody > tr:hover {
			background-color: #e6ecff;
		}
		.table > tbody > tr > td {
			text-align: center;
		}
		.table > tbody > tr > #title {
			text-align: left;
		}
		
		div > #paging {
			text-align: center;
		}
		
		.hit {
			animation-name: blink;
			animation-duration: 1.5s;
			animation-timing-function: ease;
			animation-iteration-count: infinite;
			/* 위 속성들을 한 줄로 표기하기 */
			/* -webkit-animation: blink 1.5s ease infinite; */
		}
		
		/* 애니메이션 지점 설정하기 */
		/* 익스플로러 10 이상, 최신 모던 브라우저에서 지원 */
		@keyframes blink {
			from {color: white;}
			30% {color: yellow;}
			to {color: red; font-weight: bold;}
			/* 0% {color:white;}
			30% {color: yellow;}
			100% {color:red; font-weight: bold;} */
		}
	</style>
</head>
<body>
		
		<div class="mainboard">
		<div id="list">
			<h1>게시판</h1>
		</div>
			<table class="table table-striped table-bordered table-hover">
				<thead>
					<tr>
						<th width="10%">번호</th>
						<th width="50%">제목</th>
						<th width="10%">작성자</th>
						<th width="20%">작성일</th>
						<th width="10%">조회</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${list }" var="bvo">
						<tr>
							<td>${bvo.bno }</td>
							<td id="title">
							<c:if test="${bvo.image_file ne '' && bvo.image_file ne null }">
		<img alt="없음" src="/_fileUpload/th_${bvo.image_file }">
		</c:if>
		<a href="/brd/detail?bno=${bvo.bno}">${bvo.title }</a>
							</td>
							<td>${bvo.writer }</td>
							<td>${bvo.reg_date }</td>
							<td>${bvo.read_count }</td>
						<tr>
					</c:forEach>
				</tbody>
			</table>
							
</div>
<div style="text-align:center;">
	<c:if test="${pgh.prev }">
	<a href="/brd/page?pageNo=${pgh.startPage-1 }&qty=${pgh.pgvo.qty}">◀</a>
	</c:if>
	
	<c:forEach begin="${pgh.startPage }" end="${pgh.endPage }" var="i">
		<a href="/brd/page?pageNo=${i }&qty=${pgh.pgvo.qty}">${i } </a>
	</c:forEach>
	
	<c:if test="${pgh.next }">
	<a href="/brd/page?pageNo=${pgh.endPage+1 }&qty=${pgh.pgvo.qty}">▶</a>
	</c:if>
</div>
<a href="/brd/register"><button type="button" class="btn btn-sm btn-primary" id="btnList" style="margin-left:20px;">글쓰기</button></a>
<a href="/"><button type="button" class="btn btn-sm btn-primary" id="btnList">처음으로</button></a>
</body>
</html>