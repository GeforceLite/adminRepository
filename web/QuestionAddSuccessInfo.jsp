<%--
  Created by IntelliJ IDEA.
  User: GTX
  Date: 2021/1/22
  Time: 10:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<center>
    <%
        String result=(String)request.getAttribute("QuestionAddSuccessInfo");
    %>
    <font style="color:red;font-size: 45px"></font>
    <%=result%>
</center>


