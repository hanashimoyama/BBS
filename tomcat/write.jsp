<%@ page contentType="text/html; charset=EUC-JP" %>
<%@ page import="java.sql.*" %>

<%
  // まずは、ユーザーが入力フォームに入力したデータを読み込む
  String name, pass, title, comment, host;  
  name = jpn2unicode(request.getParameter("name"), "EUC-JP");
  password = jpn2unicode(request.getParameter("pass"), "EUC-JP");
  title = jpn2unicode(request.getParameter("title"), "EUC-JP");
  comment = jpn2unicode(request.getParameter("comment"), "EUC-JP");

  host = request.getRemoteHost();

  if ((name == null) || username.equals("")) {
    out.println("お名前が入力されていません");
    return;
   }
  if ((pass == null) || pass.equals("")){
    out.println("本文が入力されていません");
    return;
   } 
  if ((title == null) || (title.equals("")){
    out.println("タイトルが入力されていません");
    return;
   }
  if ((comment == null) || comment.equals("")){
    out.println("本文が入力されていません");
    return;
   }
%>