import javax.servlet.RequestDispatcher;
import static java.sql.DriverManager.getConnection;
import java.io.*;
import java.rmi.ServerException;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

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

  // 新規投稿されたとき
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
      pstmt.setInt(9, 1); // 1は親レスでなおかつ子供がいないとき
      int num = pstmt.executeUpdate();
    } catch (Exception e) {
    } finally {
    }
  }

  // 編集をしたいとき
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
      pstmt.setInt(9, 3); // 3は返信レス
      int num = pstmt.executeUpdate();

      sql = "UPDATE New SET  reply_flag = ? WHERE id = ?";
      pstmt = conn.prepareStatement(sql);
      pstmt.setInt(1, 2); // 2は親レスでなおかつ子供がいるとき
      pstmt.setInt(2, Integer.parseInt(request.getParameter("id")));
      pstmt.executeUpdate();
      RequestDispatcher dispatcher = request.getRequestDispatcher("/finish.jsp");
      dispatcher.forward(request, response);
    } catch (Exception e) {
    } finally {
    }
  }

  public Boolean Auth(int check_id, String check_pass) throws Exception {

    Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/BBS", "BBS", "SPn!UA5,,iU,");
    Statement stmt = conn.createStatement();
    ResultSet rs = stmt.executeQuery("SELECT * FROM New WHERE id = " + check_id);
    rs.next();
    boolean auth = rs.getString("pass").equals(check_pass);
    return auth;

  }

}