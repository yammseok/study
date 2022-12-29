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
            height: 350px;
            background-color: rgba(237, 253, 247, 0.381);
            box-shadow: 3px 3px 10px grey;
        
        }
        input[type=text],button,input[type=password]{
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
<form action="/mem/login_auth" method="post">
		<div class="outbox">
            <div class="id" style="margin-top: 27px;"><input type="text" name="email" id="mid" placeholder="아이디"></div>
            <div><input type="password" placeholder="비밀번호" name="pwd" id="mpass"></div>
            <button type="sumbit" id="btn1" style="text-indent: 0; background-color: rgb(142, 239, 246);margin-bottom: 10px; font-family: yeon;">로그인</button><br>
            <br>

            <a href="/mem/list" style="color: black;" >비밀번호를 잊어버리셨나요?</a>
        </div>
</form>
</body>
</html>