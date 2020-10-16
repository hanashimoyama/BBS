import javax.servlet.RequestDispatcher;
import static java.sql.DriverManager.getConnection;
import java.io.*;
import java.rmi.ServerException;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

/* 
DBの中身
1 = int id　レコードを管理するためのid
2 = varchar user_name 投稿者の名前 
3 = varchar pass パスワード
4 = varchar title 題名
5 = datetime postdate 投稿日時
6 = varchar sentence 本文
7 = int flag 非表示か表示かを判断する管理番号 (0 = 非表示　1 = 表示)
8 = int connection_id 親記事と返信レスを結びつけるためのid　(-1 = 親記事　それ以外の数字 = 対象の返信レスがある親記事のid)
9 = int reply_flag レコードがどれに該当するのか判断する管理番号 (1 =　親記事（返信なし） 2 = 親記事（返信あり） 3 = 返信レス)
*/ 

public class Database extends HttpServlet {
  // DBへ接続するのに必要な情報
  Connection conn = null;
  String url = "jdbc:mysql://localhost/BBS";
  String user = "BBS";
  String password = "SPn!UA5,,iU,";
  String pass = null;

  // triggerを受け取り、処理を分岐させる
  public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
    request.setCharacterEncoding("utf-8");
    String Trigger = request.getParameter("trigger");
    switch (Trigger) {

      case "insert":
        Insert(request, response);
        break;

      case "update":
        Update(request, response);
        break;

      case "hide":
        Hide(request, response);
        break;

      case "delete":
        Delete(request, response);
        break;

      case "reply":
        Reply(request, response);
        break;

    }

  }

  // 新規投稿
  private void Insert(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
    try {
      Class.forName("com.mysql.jdbc.Driver").newInstance();
      conn = DriverManager.getConnection(url, user, password);
      String sql = "INSERT into New values(?,?,?,?,?,?,?,?,?)";
      PreparedStatement pstmt = conn.prepareStatement(sql);
      pstmt.setInt(1, 0);
      pstmt.setString(2, request.getParameter("name"));
      pstmt.setString(3, request.getParameter("password"));
      pstmt.setString(4, request.getParameter("title"));
      pstmt.setTimestamp(5, new Timestamp(System.currentTimeMillis()));
      pstmt.setString(6, request.getParameter("comment"));
      pstmt.setInt(7, 1);
      pstmt.setInt(8, -1);
      pstmt.setInt(9, 1); 
      int num = pstmt.executeUpdate();
    } catch (Exception e) {
    } finally {
    }
  }

  // 編集
  private void Update(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
    try {
      boolean check_ps = Auth(Integer.parseInt(request.getParameter("id")), request.getParameter("password"));
      if (check_ps) {
        try {
          Class.forName("com.mysql.jdbc.Driver").newInstance();
          conn = DriverManager.getConnection(url, user, password);
          String sql = "UPDATE New SET id = ?, user_name = ?, title = ?, postdate = ?, sentence = ? WHERE id = ?";
          PreparedStatement pstmt = conn.prepareStatement(sql);
          pstmt.setInt(1, Integer.parseInt(request.getParameter("id")));
          pstmt.setString(2, request.getParameter("name"));
          pstmt.setString(3, request.getParameter("title"));
          pstmt.setTimestamp(4, new Timestamp(System.currentTimeMillis()));
          pstmt.setString(5, request.getParameter("comment"));
          pstmt.setInt(6, Integer.parseInt(request.getParameter("id")));
          int num = pstmt.executeUpdate();
          RequestDispatcher dispatcher = request.getRequestDispatcher("/finish.jsp");
          dispatcher.forward(request, response);
        } catch (Exception e) {

        } finally {
        }
      } else {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/connectionerror.jsp");
        dispatcher.forward(request, response);
      }
    } catch (Exception e) {
    }
  }

  // 非表示
  private void Hide(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
    try {
      boolean check_ps = Auth(Integer.parseInt(request.getParameter("id")), request.getParameter("password"));
      if (check_ps) {
        try {
          Class.forName("com.mysql.jdbc.Driver").newInstance();
          conn = DriverManager.getConnection(url, user, password);
          String sql = "UPDATE New SET flag = ? WHERE id = ?";
          PreparedStatement pstmt = conn.prepareStatement(sql);
          pstmt.setInt(1, 0);
          pstmt.setInt(2, Integer.parseInt(request.getParameter("id")));

          int num = pstmt.executeUpdate();
          RequestDispatcher dispatcher = request.getRequestDispatcher("/finish.jsp");
          dispatcher.forward(request, response);
        } catch (Exception e) {

        } finally {
        }
      } else {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/connectionerror.jsp");
        dispatcher.forward(request, response);
      }
    } catch (Exception e) {
    }
  }

  // 削除
  private void Delete(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
    try {
      boolean check_ps = Auth(Integer.parseInt(request.getParameter("id")), request.getParameter("password"));
      if (check_ps) {
        try {
          Class.forName("com.mysql.jdbc.Driver").newInstance();
          conn = DriverManager.getConnection(url, user, password);
          String sql = "Delete FROM New WHERE id = ?";
          PreparedStatement pstmt = conn.prepareStatement(sql);
          pstmt.setInt(1, Integer.parseInt(request.getParameter("id")));

          int num = pstmt.executeUpdate();
          RequestDispatcher dispatcher = request.getRequestDispatcher("/finish.jsp");
          dispatcher.forward(request, response);
        } catch (Exception e) {

        } finally {
        }
      } else {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/connectionerror.jsp");
        dispatcher.forward(request, response);
      }
    } catch (Exception e) {
    }
  }

  private void Reply(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
    try {
      // 返信レスをDBに追加します
      Class.forName("com.mysql.jdbc.Driver").newInstance();
      conn = DriverManager.getConnection(url, user, password);
      String sql = "INSERT into New values(?,?,?,?,?,?,?,?,?)";
      PreparedStatement pstmt = conn.prepareStatement(sql);
      pstmt.setInt(1, 0);
      pstmt.setString(2, request.getParameter("name"));
      pstmt.setString(3, request.getParameter("password"));
      pstmt.setString(4, request.getParameter("title"));
      pstmt.setTimestamp(5, new Timestamp(System.currentTimeMillis()));
      pstmt.setString(6, request.getParameter("comment"));
      pstmt.setInt(7, 1);
      pstmt.setInt(8, Integer.parseInt(request.getParameter("id")));
      pstmt.setInt(9, 3); 
      int num = pstmt.executeUpdate();

      // 返信されたとき、対象の親記事のレコードのreply_flagを2（返信あり）に変えます
      sql = "UPDATE New SET  reply_flag = ? WHERE id = ?";
      pstmt = conn.prepareStatement(sql);
      pstmt.setInt(1, 2); 
      pstmt.setInt(2, Integer.parseInt(request.getParameter("id")));
      pstmt.executeUpdate();
      RequestDispatcher dispatcher = request.getRequestDispatcher("/finish.jsp");
      dispatcher.forward(request, response);
    } catch (Exception e) {
    } finally {
    }
  }

  // パスワード認証
  public Boolean Auth(int check_id, String check_pass) throws Exception {

    Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/BBS", "BBS", "SPn!UA5,,iU,");
    Statement stmt = conn.createStatement();
    ResultSet rs = stmt.executeQuery("SELECT * FROM New WHERE id = " + check_id);
    rs.next();
    boolean auth = rs.getString("pass").equals(check_pass);
    return auth;

  }

}