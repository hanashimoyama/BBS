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
    
    <link rel="stylesheet" href="${pageContext.request.contextPath}/form.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/script.js" charset="UTF-8"></script>

    <title>掲示板</title>
</head>
<body>
    <h1>掲示板</h1>
    <section>
        <h2>新規投稿</h2>
        <form action="./Database" method="POST" id="AjaxForm">
            <input type="hidden" name="trigger" value="insert">
            <div><label for="t_message">お名前：</label>
                <input type="text" class="input" name="name" value=""><br></div>
            <div><label for="t_message">パスワード：</label>
                <input type="password" class="input" name="password" value=""><br></div>
            <div><label for="t_message">題名：</label>
                <input type="text" class="input" name="title" value=""><br></div>
            <div><label for="t_message">本文：</label>
                <textarea name="comment" cols="30" rows="3" maxlength="400" wrap="hard"
                    placeholder="400字以内で入力してください。"></textarea></div>
            <ul class="button">
                <li>
                    <input type="submit" class="submit" Value="書き込み">
                </li>
                <li><input type="reset" class="reset" Value="リセット"></li>
            </ul>
        </form>
        <div id="result"></div>
    </section>
    <section>
        <h2>投稿一覧</h2>
    </section>
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
        while(rs.next()){ 
            %>
    <div class="box">
        <table>
            <tr>
                <td>id:<%= rs.getInt(1)%></td>
                <td>名前:<%= rs.getString(2)%></td>
                <td><%= rs.getString(4)%></td>
                <td><%= rs.getString(5)%></td>
                <td><%= rs.getString(6)%></td>
            </tr>
        </table>
    </div>
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