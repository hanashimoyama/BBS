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
            <div class="buttons1">
                <li>
                    <input type="submit" class="submit" Value="書き込み" class="btn">
                </li>
                <li><input type="reset" class="reset" Value="リセット" class="btn"></li>
            </div>
        </form>
    </section>
    <section>
        <h2>投稿一覧</h2>
    </section>
    <%  
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

    try{
      conn = DriverManager.getConnection("jdbc:mysql://localhost/BBS","BBS","SPn!UA5,,iU,");
      stmt = conn.createStatement();
      rs = stmt.executeQuery( "select * from New WHERE flag = 1 AND connection_id = -1");

        while(rs.next()){
            if(rs.getInt(9) == 1){
    %>
    <h2><%= rs.getInt(9) %></h2>
    <div class="box">
        <table>

            <tr>
                <td>
                    <div id="ID"><%= rs.getInt(1)%>:</div>
                </td>
                <td>
                    <div id="TITLE">
                        <%= rs.getString(4)%>
                    </div>
                </td>

            </tr>
            <tr>

                <td>
                    名前:<%= rs.getString(2)%>
                </td>


                <td>
                    投稿日<%= rs.getString(5)%>
                </td>

            </tr>
            <tr>
                <td>
                    <%= rs.getString(6)%>
                </td>
            </tr>
    
    </table>
    <div class="buttons2">
        <input type="button" value="返信" onclick="win_open('reply.jsp','reply','<%=rs.getInt(1)%>')">
        <input type="button" value="編集" onclick="win_open('update.jsp','update','<%=rs.getInt(1)%>')">
        <input type="button" value="非表示" onclick="win_open('hide.jsp','hide','<%=rs.getInt(1)%>')">
        <input type="button" value="削除" onclick="win_open('delete.jsp','delete','<%=rs.getInt(1)%>')">
    </div>
    </div>
    <%
}else if(rs.getInt(9) == 2){
            %>
            <h2><%= rs.getInt(9) %></h2>
    <div class="box">
        <table>

            <tr>
                <td>
                    <div id="ID"><%= rs.getInt(1)%>:</div>
                </td>
                <td>
                    <div id="TITLE">
                        <%= rs.getString(4)%>
                    </div>
                </td>

            </tr>
            <tr>

                <td>
                    名前:<%= rs.getString(2)%>
                </td>


                <td>
                    投稿日<%= rs.getString(5)%>
                </td>

            </tr>
            <tr>
                <td>
                    <%= rs.getString(6)%>
                </td>
            </tr>
    
    </table>
    
    <div class="buttons2">
        <input type="button" value="返信" onclick="win_open('reply.jsp','reply','<%=rs.getInt(1)%>')">
        <input type="button" value="編集" onclick="win_open('update.jsp','update','<%=rs.getInt(1)%>')">
        <input type="button" value="非表示" onclick="win_open('hide.jsp','hide','<%=rs.getInt(1)%>')">
        <input type="button" value="削除" onclick="win_open('delete.jsp','delete','<%=rs.getInt(1)%>')">
    </div>
       
    
    <% 
    Connection conn2 = null;
    Statement stmt2 = null;
    ResultSet rs2 = null;

    conn2 = DriverManager.getConnection("jdbc:mysql://localhost/BBS","BBS","SPn!UA5,,iU,");
    stmt2 = conn2.createStatement();
    rs2 = stmt2.executeQuery( "select * from New WHERE flag = 1 AND connection_id ="+ rs.getInt(1));
    while(rs2.next()){  
        
    %>
            <ul>
        <div class="replybox">
            <table>

                <tr>
                    <td>
                        <div id="ID"><%= rs2.getInt(1)%>:</div>
                    </td>
                    <td>
                        <div id="TITLE">
                            <%= rs2.getString(4)%>
                        </div>
                    </td>

                </tr>
                <tr>

                    <td>
                        名前:<%= rs2.getString(2)%>
                    </td>


                    <td>
                        投稿日<%= rs2.getString(5)%>
                    </td>

                </tr>
                <tr>
                    <td>
                        <%= rs2.getString(6)%>
                    </td>
                </tr>
        </table>
        <div class="buttons2">
            <input type="button" value="編集" onclick="win_open('update.jsp','update','<%=rs.getInt(1)%>')">
            <input type="button" value="非表示" onclick="win_open('hide.jsp','hide','<%=rs.getInt(1)%>')">
            <input type="button" value="削除" onclick="win_open('delete.jsp','delete','<%=rs.getInt(1)%>')">
        </div>
        </div>
    </ul>
    <%
    rs2.close();
    stmt2.close();
    conn2.close();
}
%>
</div>
    <%

    }
}   %>
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