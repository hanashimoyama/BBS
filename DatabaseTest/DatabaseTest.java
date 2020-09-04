import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class DatabaseTest extends HttpServlet {
  public void doGet(HttpServletRequest request, HttpServletResponse response)
    throws IOException, ServletException{

    response.setContentType("text/html; charset=Shift_JIS");
    PrintWriter out = response.getWriter();

    out.println("<html>");
    out.println("<head>");
    out.println("<title>データベーステスト</title>");
    out.println("</head>");
    out.println("<body>");

    String msg = "";
    try {
      Class.forName("com.mysql.jdbc.Driver").newInstance();
      msg = "ドライバのロードに成功しました";
    }catch (ClassNotFoundException e){
      msg = "ドライバのロードに失敗しました";
    }catch (Exception e){
      msg = "ドライバのロードに失敗しました";
    }

    out.println("<p>");
    out.println(msg);
    out.println("</p>");

    out.println("</body>");
    out.println("</html>");
  }
}