<%@ page import="java.util.List" %>
<%@ page import="com.bjpowernode.entity.Question" %><%--
  Created by IntelliJ IDEA.
  User: GTX
  Date: 2021/1/23
  Time: 15:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <center>
    <%
        List list= (List) request.getAttribute("key1");
        //拿到集合中的第一个元素
        Question question = (Question) list.get(0);
        question.getAnswer();
    %>
        <form action="update" method="get">
            <table border="2px" >
                <tr>
                    <td>试题Id</td>
                    <td><input type="text" name="questionId" value="<%=question.getQuestionId()%>" readonly></td>
                </tr>
                <tr>
                    <td>试题信息</td>
                    <td><input type="text" name="question" value="<%=question.getQuestion()%>" ></td>
                </tr>
                <tr>
                    <td>试题答案A</td>
                    <td><input type="text" name="optionA" value="<%=question.getOptionA()%>"></td>
                </tr>
                <tr>
                    <td>试题答案B</td>
                    <td><input type="text" name="optionB" value="<%=question.getOptionB()%>"></td>
                </tr>
                <tr>
                    <td>试题答案C</td>
                    <td><input type="text" name="optionC" value="<%=question.getOptionC()%>"></td>
                </tr>
                <tr>
                    <td>试题答案D</td>
                    <td><input type="text" name="optionD" value="<%=question.getOptionD()%>"></td>
                </tr>
                <tr>
                    <td>正确答案</td>
                    <td>A<input type="radio" name="answer" value="A">
                        B<input type="radio" name="answer" value="B">
                        C<input type="radio" name="answer" value="C">
                        D<input type="radio" name="answer" value="D">
                    </td>
                </tr>
                <tr>
                    <td><input type="submit" value="提交更新试题"></td>
                    <td><input type="reset" value="重置" /></td>
                </tr>
            </table>
        </form>
</center>
</body>
</html>
