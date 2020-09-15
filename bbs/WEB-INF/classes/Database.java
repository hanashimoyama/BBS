import java.io.IOException;
import java.sql.SQLException;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Database extends HttpServlet {
  String url = "jdbc:mysql://localhost/BBS";
  String user = "BBS";
  String password = "SPn!UA5,,iU,";

  public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    String Trigger = request.getParameter("trigger");

    switch (Trigger) {
      case "insert":
        Insert(request, response);
        break;
    }

  }

  private void Insert(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
    Connection conn = null;
    PreparedStatement pstmt = null;
    String sql = "insert into New values(?,?,?,?,?,?)";

    try {

      conn = DriverManager.getConnection(url, user, password);

      pstmt.setInt(1, 0);
      pstmt.setString(2, request.getParameter("name"));
      pstmt.setString(3, request.getParameter("password"));
      pstmt.setString(4, request.getParameter("title"));
      pstmt.setTimestamp(5, new Timestamp(System.currentTimeMillis()));
      pstmt.setString(6, request.getParameter("comment"));

      pstmt.executeUpdate();

    } catch (SQLException e) {
    } finally {
    }
  }
}
