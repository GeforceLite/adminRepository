<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: GTX
  Date: 2021/1/25
  Time: 17:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<center>
    <%
        int score=(int)session.getAttribute("score");
        System.out.println(score);
    %>
    <h1 style="color: red;font-size: 45px">考试完成，您的成绩为<%=score%></h1>
</center>
