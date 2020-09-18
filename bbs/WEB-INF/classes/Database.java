import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class Database extends HttpServlet {
  Connection conn = null;
  String url = "jdbc:mysql://localhost/BBS";
  String user = "BBS";
  String password = "SPn!UA5,,iU,";


  public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
    
    request.setCharacterEncoding("utf-8");
    String Trigger = request.getParameter("trigger");

    switch (Trigger) {
      case "insert":
        Insert(request, response);
        break;
    }
  }

  private void Insert(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

    try {
      Class.forName("com.mysql.jdbc.Driver").newInstance();
      conn = DriverManager.getConnection(url, user, password);
      String sql = "insert into New values(?,?,?,?,?,?)";
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
}
