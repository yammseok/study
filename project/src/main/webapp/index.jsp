<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<!-- JavaScript Bundle with Popper -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3" crossorigin="anonymous"></script>
<!-- CSS only -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi" crossorigin="anonymous">
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
body{
/* 	background-image:url(img.gif);
	background-repeat:no-repeat;
	background-size: 1920px 1080px; */

	
}
a{
background color: white;
text-shadow: -1px 0px white, 0px 1px white, 1px 0px white, 0px -1px white;
font-size: 20pt;
font-weight: 700;
            margin-right: 20px;

}
h1{
            font-size: 40pt;
            text-align: center;
            text-shadow: -1px 0px aqua, 0px 1px aqua, 1px 0px aqua, 0px -1px aqua;
        }
	.userinfo{
		background-color: rgba(0, 0, 0, 0.092);
		width:300px;
		 display: inline-block;
		float:right;
		text-align:center;
		height:200px;
		margin-right:30px;
		margin-top: 30px;
	}
	#catimg{
	position: absolute;
 	 top: 300px;
 	 left: 460px;
	}
	#catimg2{
	position: absolute;
 	 top: 300px;
 	 left: 160px;
	}
	#catimg3{
	position: absolute;
 	 top: 300px;
 	 left: 760px;
	}
}
</style>
</head>
<header>
<a href="/"><img style="width:40px; float:right; margin-right:30px" alt="" src="https://static-00.iconduck.com/assets.00/house-emoji-430x512-e8etim8n.png"></a>
</header>
<body>

<h1 style="color:aquamarine;"></h1>
<c:if test="${ses.email ne null && ses.email ne '' }">
<div class="userinfo">
<p style="margin-top:20px;">${ses.nick_name } 님 안녕하세요</p><br>
계정생성일 : ${ses.reg_at}<br>
마지막 접속 : ${ses.last_login }<br>
<a href="/mem/modify?email=${ses.email }"><button type="button" class="btn btn-warning">회원정보수정</button></a>
<a href="/mem/logout"><button type="button" class="btn btn-warning">로그아웃</button></a>
</div>
</c:if>
<Br>

<c:if test="${ses.email ne null && ses.email ne '' }">
<a href="/brd/register"><button type="button" style="margin-left:20px" class="btn btn-outline-primary">글쓰기</button></a>
<a href="/brd/pageList2"><button type="button" stlye="border:0;" class="btn btn-outline-info">내가 쓴 글보기</button></a>
</c:if>

<a href="/brd/pageList"><button type="button" style="margin-left:20px" class="btn btn-outline-primary">게시판</button></a>
<c:if test="${ses.email eq null}">
<a href="/mem/login"><button type="button" stlye="border:0;" class="btn btn-outline-info">로그인</button></a>
<a href="/mem/join"><button type="button" class="btn btn-outline-primary">회원가입</button></a>
</c:if>
<a href="/mem/list"><button type="button" class="btn btn-outline-info">회원리스트</button></a>
<br>
<c:if test="${ses.email ne null && ses.email ne '' }">
<br><br><br><br><br>
<!-- <a href="/mem/remove">회원탈퇴</a> -->
</c:if>
<img id="catimg" alt="" src="https://cdn.class101.net/images/7ea79c5f-7d56-486d-8a95-6e2cf8eff770">
<img id="catimg2" alt="" src="https://cdn.class101.net/images/7ea79c5f-7d56-486d-8a95-6e2cf8eff770">
<img id="catimg3" alt="" src="https://cdn.class101.net/images/7ea79c5f-7d56-486d-8a95-6e2cf8eff770">
</body>
</html>