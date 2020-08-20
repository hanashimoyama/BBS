<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/form.css">
<title>掲示板</title>
</head>
<body>
<h1>掲示板</h1>
<section>
    <h2>新規投稿</h2>
    <form action="" method="post">
        <div><label for="t_message">お名前：</label>
        <input type="text" name="name" value=""><br></div>
        <div><label for="t_massege">パスワード：</label>
        <input type="password" name="password" value=""><br></div>
        <div><label for="t_massege" >題名：</label>
        <input type="text" name="title" value=""><br></div>
        <div><label for="t_message">本文：</label>
        <textarea name="comment" cols="30" rows="3" maxlength="400" wrap="hard" placeholder="400字以内で入力してください。"></textarea></div>
        <p class="submit"><input type="submit" value="投稿"></p>
        <p class="reset"><input type="reset" Value="リセット"></p>
    </form>
</section>
<section>
    <h2>投稿一覧</h2>
    <p>投稿はまだありません</p>
</section>
</body>
</html>