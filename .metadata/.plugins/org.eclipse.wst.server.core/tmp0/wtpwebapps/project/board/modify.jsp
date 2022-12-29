<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>



<!DOCTYPE html>

<html>

<head>

<meta charset="UTF-8">



<!-- jQuery -->

<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>



<!-- Bootstrap CSS -->

<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css" integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">



<title>board</title>


<style>

body {

  padding-top: 70px;

  padding-bottom: 30px;

}



</style>

</head>

<body>

	<article>

		<div class="container" role="main">

			<h2>글쓰기</h2>

			<form action="/brd/update" method="post" enctype="multipart/form-data">

				<div class="mb-3">

					<label for="title">제목</label>
						<input type="hidden" name="bno" value="${bvo.bno }" readonly><br>
					<input type="text" class="form-control" name="title" id="title" value="${bvo.title }">

				</div>

				

				<div class="mb-3">

					<label for="reg_id">작성자</label>
					<br>

					<input type="text" class="form-control"  name="writer" value="${bvo.writer }" id="reg_id" readonly>

				</div>

				

				<div class="mb-3">

					<label for="content">내용</label>

					<textarea class="form-control" rows="10" name="content" id="content" placeholder="내용을 입력해 주세요" >${bvo.content }</textarea>

				</div>

				

				<div class="mb-3">

					<label for="file">첨부파일</label>
					<input type="hidden" name="image_file" value="${bvo.image_file }">
					<input type="file" class="form-control" id="file" name="new_file" accept="image/png, image/jpg, image/jpeg, image/gif">

				</div>

			


			<div >

				<button type="submit" class="btn btn-sm btn-primary" id="btnSave">저장</button>

				<a href="/brd/pageList"><button type="button" class="btn btn-sm btn-primary" id="btnList">목록</button></a>

			</div>
			</form>

		</div>

	</article>

</body>

</html>