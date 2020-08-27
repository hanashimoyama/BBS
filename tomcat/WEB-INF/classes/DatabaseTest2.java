import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class DatabaseTest2 extends HttpServlet {
  public void doGet(HttpServletRequest request, HttpServletResponse response)
    throws IOException, ServletException{

    response.setContentType("text/html; charset=Shift_JIS");
    PrintWriter out = response.getWriter();

    out.println("<html>");
    out.println("<head>");
    out.println("<title>データベーステスト</title>");
    out.println("</head>");
    out.println("<body>");

    Connection conn = null;
    String url = "jdbc:mysql://localhost/BBS";
    String user = "BBS";
    String password = "SPn!UA5,,iU,";

    try {
      Class.forName("com.mysql.jdbc.Driver").newInstance();
      conn = DriverManager.getConnection(url, user, password);

      Statement stmt = conn.createStatement();
      String sql = "SELECT * FROM New";
      ResultSet rs = stmt.executeQuery(sql);

      while(rs.next()){
        int id = rs.getInt("id");
        String name = rs.getString("user_name");
        String pass = rs.getString("pass");
        String title = rs.getString("title");
        String postdate = rs.getString("postdate");
        String comment = rs.getString("sentence");
        out.println("<p>");
        out.println("ID:" + name + ",お名前:" + name + "パス：" + pass );
        out.println("題名：" + title + "日時：" + postdate + "コメント：" + comment );
        out.println("</p>");
      }

      rs.close();
      stmt.close();
    }catch (ClassNotFoundException e){
      out.println("ClassNotFoundException:" + e.getMessage());
    }catch (SQLException e){
      out.println("SQLException:" + e.getMessage());
    }catch (Exception e){
      out.println("Exception:" + e.getMessage());
    }finally{
      try{
        if (conn != null){
          conn.close();
        }
      }catch (SQLException e){
        out.println("SQLException:" + e.getMessage());
      }
    }

    out.println("</body>");
    out.println("</html>");
  }
}