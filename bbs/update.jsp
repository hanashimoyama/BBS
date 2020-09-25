<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.*"%>
<%!
// サーブレットのinitメソッドに相当
public void jspInit() {
    try {
        // JDBCドライバをロード
        Class.forName("com.mysql.jdbc.Driver");
    } catch (Exception e) {
        e.printStackTrace();
    }
} 
%>
<!DOCTYPE html>
<html>

<head>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/style.css">
    <script src="https://code.jquery.com/jquery-2.2.4.min.js" defer></script>
    <script src="${pageContext.request.contextPath}/script.js" defer></script>

    <title>掲示板</title>
</head>

<body>
    <h1>投稿編集</h1>


    <%  
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        /*String url = "jdbc:mysql://localhost/BBS";
        String user = "BBS";
        String password = "SPn!UA5,,iU,";*/
    try{
      conn = DriverManager.getConnection("jdbc:mysql://localhost/BBS","BBS","SPn!UA5,,iU,");
      stmt = conn.createStatement();
      rs = stmt.executeQuery( "select * from New ");
        
        <form action="./Database" method="POST"　id="AjaxForm" >
        <input type="hidden" name="trigger" value="delete">
        <div><label for="t_message">お名前：</label>
            <input type="text" class="input" name="name" value=""><br></div>
        
        <div><label for="t_message">題名：</label>
            <input type="text" class="input" name="title" value=""><br></div>
        <div><label for="t_message">本文：</label>
            <textarea name="comment" cols="30" rows="3" maxlength="400" wrap="hard"
                placeholder="400字以内で入力してください。"></textarea></div>
                <div><label for="t_message">パスワード：</label>
                    <input type="password" class="input" name="password" value=""><br></div>
        <div class="buttons1">
            <li>
                <input type="submit"  Value="編集">
            </li>
            <li><input type="button" Value="閉じる"></li>
        </div>
    </form>

　   <%
        }
    } catch (Exception e) {
        e.printStackTrace();
    } finally {
        // データベースとの接続をクローズ
        try { rs.close(); } catch (Exception e) {}
        try { stmt.close(); } catch (Exception e) {}
        try { conn.close(); } catch (Exception e) {}
    }
        %>
</body>

</html>