<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
    <style>
       body{
        margin: 0px;
        height: 100vh;
        justify-content: center;
        text-align: center;
        align-items: center;
        background-image: url(/image/배경.jpg);
        font-family: yeon;
        font-size: 14pt;
        display: flex;
       background-repeat: no-repeat;
    }
    input:focus{
            outline: none;
        }

        .outbox{
            overflow: hidden;
            border-radius: 20px;
            width: 500px;
            height: 370px;
            background-color: rgba(237, 253, 247, 0.381);
            box-shadow: 3px 3px 10px grey;
        
        }
        input[type=text],button,input[type=password],input[type=email]{
            width: 400px;
            height: 55px;
            margin-top: 20px;
            vertical-align:middle;
            font-size: 17pt;
            border-radius: 12px;
            text-indent: 0.5em;
            outline-color: hidden;
            border: 0 solid black;
            box-shadow: 1px 1px 3px rgb(209, 204, 204);
            
        }
        button:hover{
            transform: scale(1.02);
        }
    </style>
</head>
<body>
<form action="/mem/userUpdate" method="post">
		<div class="outbox">
            <div class="id" style="margin-top: 27px;"><input type="email" name="email" id="mid" value="${ses.email }" readonly></div>
            <div class="id"><input type="text" name="nick_name" id="mid" value="${ses.nick_name }" placeholder="닉네임"></div>
            <div><input type="password" placeholder="비밀번호" name="pwd" value="${ses.pwd }" id="mpass"></div>
            <button type="sumbit" id="btn1" style="text-indent: 0; background-color: rgb(142, 239, 246);margin-bottom: 10px; font-family: yeon;">회원정보수정</button><br>
            <br>

        </div>
</form>
</body>
</html>