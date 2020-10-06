<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.*"%>
<%@ page import="javax.servlet.*"%>
<%@ page import="javax.servlet.http.*"%>



<!DOCTYPE html>
<html>

<head>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/style.css">
    <script src="https://code.jquery.com/jquery-2.2.4.min.js" defer></script>
    <script src="${pageContext.request.contextPath}/script.js" defer></script>

    <title>掲示板</title>
</head>

<body>
    <h1>完了しました</h1>
    <h2>閉じるボタンを押してください</h2>



    <input type="button" Value="閉じる" class="btn" onclick="window.opener.location.reload(),window.close()">




</body>

</html>