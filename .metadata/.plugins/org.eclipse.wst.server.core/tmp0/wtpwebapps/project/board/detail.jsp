<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- Bootstrap CSS -->

<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css" integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">
<!-- CSS only -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi" crossorigin="anonymous">
<!-- JavaScript Bundle with Popper -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3" crossorigin="anonymous"></script>
    <style>
      body {
        margin: 0px;
        justify-content: center;
        text-align: center;
      }
      .marginBox {
        height: 70px;
        /* background-color: blue; */
      }
      .mainBox {
        /* background-color: red; */
        width: 1200px;
        margin: 0 auto;
        text-align: center;
      }
      .userinfo {
        text-align: left;
        height: 60px;
        /* background-color: brown; */
      }
      .contentBox{
        /* height: 500px; */
        /* background-color: aqua; */
      }
      .contentImg{
     	margin-top:30px;
     	margin-bottom:30px;
      }
      	
		/* img {
		width: 1000px;
		height: 400px;
		  object-fit: cover;
		} */
    </style>
  </head>
  <body>
    <div class="marginBox"></div>
    <div class="mainBox">
    <h1>본문</h1>
      <div class="userinfo">
      <hr>
        <strong class="">${bvo.title }</strong><br />
        <span>${bvo.writer }</span> <span>|</span>
        <span>${bvo.reg_date }</span>
        <span style="float: right">조회수:${bvo.read_count }</span>
        <div><hr></div>
      </div>
      <div class="contentBox" style="text-align:left;">
      <div class="contentImg">
      <img alt='' src="/_fileUpload/${bvo.image_file }">
      </div>
		${bvo.content }
      </div>
      <div><hr></div>
      <div style="text-align:left;margin-bottom:20px;">    
      <a href="/brd/modify?bno=${bvo.bno}"><button type="button" class="btn btn-sm btn-primary" id="btnList" style="margin-left:10px;">수정</button></a>
		<a href="/brd/remove?bno=${bvo.bno}"><button type="button" class="btn btn-sm btn-primary" id="btnList" style="margin-left:10px;">삭제</button></a>
		<a href="/brd/pageList"><button type="button" class="btn btn-sm btn-primary" id="btnList" style="margin-left:10px;">목록</button></a>
      </div>
      
      <div  class="댓글" style="float:left; text-align:left; margin-top:10px;">
	<input type="text" id="cmtWriter" value="${ses.nick_name }" readonly="readonly"><br>
	<input type="text" id="cmtText" placeholder="Add Comment">
	<button type="button" id="cmtAddBtn">댓글등록</button>

</div>
      <!-- 댓글 표시 영역 -->
   <div class="accordion" id="accordionExample">
   <!-- 댓글 아이템 표시 영역 -->
   <div class="accordion-item">
    <h2 class="accordion-header" id="headingOne">
      <button class="accordion-button" type="button" data-bs-toggle="collapse" 
      data-bs-target="#collapseOne" aria-expanded="true" aria-controls="collapseOne"> 
      </button>
    </h2>
    <div id="collapseOne" class="accordion-collapse collapse show" 
    aria-labelledby="headingOne" data-bs-parent="#accordionExample">
      <div class="accordion-body">
      </div>
    </div>
</div>

<c:if test="${bvo.writer ne ses.email}">
<a href="/brd/modify?bno=${bvo.bno}">수정</a><br>
<a href="/brd/remove?bno=${bvo.bno}">삭제</a><br>
<a href="/brd/pageList">목록</a><br>
</c:if>

   
   <script type="text/javascript">
   const bnoVal = '<c:out value="${bvo.bno}"/>';
   console.log(bnoVal)
   </script>
   
   <script src="/resources/board_detail.js"></script>
  
   <script type="text/javascript">
   console.log(bnoVal)
   printCommentList(bnoVal);
   </script>
    
      
    </div>
  </body>
</html>
