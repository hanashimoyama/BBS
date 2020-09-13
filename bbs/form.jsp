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
        <form action="./Database">
            <input type="hidden" name="trigger" value="insert">
            <div><label for="t_message">お名前：</label>
                <input type="text" class="input" name="name" value=""><br></div>
            <div><label for="t_massege">パスワード：</label>
                <input type="password" class="input" name="password" value=""><br></div>
            <div><label for="t_massege">題名：</label>
                <input type="text" class="input" name="title" value=""><br></div>
            <div><label for="t_message">本文：</label>
                <textarea name="comment" cols="30" rows="3" maxlength="400" wrap="hard"
                    placeholder="400字以内で入力してください。"></textarea></div>
            <ul class="button">
                <li><input type="submit" class="submit" value="投稿"></li>
                <li><input type="reset" class="reset" Value="リセット"></li>
            </ul>
        </form>
    </section>
    <section>
        <h2>投稿一覧</h2>
        <p>投稿はまだありません</p>
    </section>
</body>

</html>