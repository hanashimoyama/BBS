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
    }
  }

  // 新規投稿されたとき
  private void Insert(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
    try {
      Class.forName("com.mysql.jdbc.Driver").newInstance();
      conn = DriverManager.getConnection(url, user, password);
      String sql = "INSERT into New values(?,?,?,?,?,?)";
      PreparedStatement pstmt = conn.prepareStatement(sql);
      pstmt.setInt(1, 0);
      pstmt.setString(2, request.getParameter("name"));
      pstmt.setString(3, request.getParameter("password"));
      pstmt.setString(4, request.getParameter("title"));
      pstmt.setTimestamp(5, new Timestamp(System.currentTimeMillis()));
      pstmt.setString(6, request.getParameter("comment"));
      int num = pstmt.executeUpdate();
    } catch (Exception e) {
    } finally {
    }
  }

  // 編集をしたいとき
  private void Update(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
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
  }

  /*
   * private void Update(HttpServletRequest request, HttpServletResponse response)
   * throws IOException, ServletException { /* try { boolean check_ps =
   * Auth(Integer.parseInt(request.getParameter("id")),
   * request.getParameter("password")); if (check_ps) {
   */
  /*
   * try { Class.forName("com.mysql.jdbc.Driver").newInstance(); conn =
   * DriverManager.getConnection(url, user, password); String sql =
   * "UPDATE New SET user_name =?,title =?,postdate=?,comment=? WHERE id =?;";
   * PreparedStatement pstmt = conn.prepareStatement(sql);
   * 
   * pstmt.setString(1, request.getParameter("name")); pstmt.setString(2,
   * request.getParameter("title")); pstmt.setString(3,
   * request.getParameter("comment")); pstmt.setTimestamp(4, new
   * Timestamp(System.currentTimeMillis())); pstmt.setInt(5,
   * Integer.parseInt(request.getParameter("id")));
   * 
   * int num = pstmt.executeUpdate(); } catch (Exception e) { } finally { } /* }
   * else { RequestDispatcher dispatcher =
   * getServletContext().getRequestDispatcher("/connectionerror.jsp"); } } catch
   * (Exception e) { }
   */
  // }

  /*
   * public boolean Auth(int check_id, String check_pass) throws Exception {
   * Connection conn = null; Statement stmt = null; ResultSet rs = null; conn =
   * DriverManager.getConnection("jdbc:mysql://localhost/BBS", "BBS",
   * "SPn!UA5,,iU,"); stmt = conn.createStatement(); rs =
   * stmt.executeQuery("select password from New where id=" + check_id);
   * rs.next(); boolean auth = rs.getString("password").equals(check_pass); //
   * データベースとの接続をクローズ rs.close(); stmt.close(); conn.close(); return auth; }
   */
}