<%@ page import="java.util.List" %>
<%@ page import="com.bjpowernode.entity.Question" %><%--
  Created by IntelliJ IDEA.
  User: GTX
  Date: 2021/1/22
  Time: 16:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <table border="2px" align="center">
        <tr align="center">
            <td>题目序列号</td>
            <td>题目</td>
            <td>选项A</td>
            <td>选项B</td>
            <td>选项C</td>
            <td>选项D</td>
            <td>正确选项</td>
            <td colspan="2">操作</td>
        </tr>
        <%
        //结果用JSP显示
            List list = (List) request.getAttribute("key1");
            for (int i = 0; i < list.size(); i++) {
                //用Question对象接收来自list集合里的Question对象
                Question question=(Question)list.get(i);
        %>
        <%
            if (i%2==0){
        %>

        <tr style="background-color: cyan">
        <%
            }else{
                %>

        <tr style="background-color:royalblue">
                <%
            }
        %>
            <td><%=question.getQuestionId()%></td>
            <td><%=question.getQuestion()%></td>
            <td><%=question.getOptionA()%></td>
            <td><%=question.getOptionB()%></td>
            <td><%=question.getOptionC()%></td>
            <td><%=question.getOptionD()%></td>
            <td><%=question.getAnswer()%></td><!--JSP写跳转名要写对，href多试试，不用写/question/findById是为啥我也不知道
            有可能是自动加入前缀名，这里只写/findById就是自动成功跳转了-->
            <td><a href="findById?questionId=<%=question.getQuestionId()%>">详细信息</a></td>
            <td><a href="delete?questionId=<%=question.getQuestionId()%>"> 删除试题</a></td>
        </tr>
<%
                 }
%>
    </table>
</body>
</html>
