<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.*"%>
<%@ page import="javax.servlet.*"%>
<%@ page import="javax.servlet.http.*"%>


<% 
// 編集するレスのidをURLパラメータから取得する
request.setCharacterEncoding("UTF-8");
int id = Integer.parseInt(request.getParameter("id"));

  %>
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
    <h1>投稿非表示</h1>
    <h2>パスワードを入力してください</h2>


    <%  
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

    try{
      conn = DriverManager.getConnection("jdbc:mysql://localhost/BBS","BBS","SPn!UA5,,iU,");
      stmt = conn.createStatement();
      rs = stmt.executeQuery( "SELECT * FROM New Where id = " +id);
      rs.next();
    %>

    <form action="./Database" method="POST" 　id="AjaxForm">
        <input type="hidden" name="trigger" value="hide">
        <input type="hidden" name="id" value="<%=rs.getInt(1)%>">
        <div><label for="t_message">パスワード：</label>
            <input type="password" class="input" name="password" value=""><br></div>
        <div class="buttons1">
            <li><input type="submit" Value="非表示" class="btn"></li>
            <li><input type="button" Value="閉じる" class="btn" onclick="window.close()"></li>
        </div>
    </form>

    　 <%

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