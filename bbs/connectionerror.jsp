<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.*"%>


<!DOCTYPE html>
<html>

<head>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/style.css">
    <script src="https://code.jquery.com/jquery-2.2.4.min.js" defer></script>
    <script src="${pageContext.request.contextPath}/script.js" defer></script>

    <title>掲示板</title>
</head>

<body>
    <h1>掲示板</h1>
    <section>
        <h2>パスワードが違います</h2>
        <h3>もう一度最初からやり直してください</h3>
        <input type="button" Value="閉じる" class="btn" onclick="window.close()">
</body>

</html>