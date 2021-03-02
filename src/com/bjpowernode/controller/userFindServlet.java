package com.bjpowernode.controller;

import com.bjpowernode.Dao.UserDao;
import com.bjpowernode.entity.user;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

//用户查询方法
public class userFindServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //用户查询，如果有Session柜子令牌就给服务，反之就不给你服务
        HttpSession session = request.getSession(false);
        if (session == null) {
            //没有令牌柜子，拿到的null，恶意提交的申请，应该不提供服务
            //重定向来到登录错误界面
            response.sendRedirect("/ServletDemo/user/login_Error.html");
            //return结束方法
        }
            //否则就是可以session!=null可以为其提供服务
            //将查询信息通过DAO层交给数据库
            UserDao userdao = new UserDao();
            //创建集合对象来接收Dao层返回数据
            List<user> userList = userdao.find();
            //拿到查询结果集之后通过<table>标签送回浏览器端
            response.setContentType("text/html;charset=utf-8");
            PrintWriter printWriter = response.getWriter();
            printWriter.print("<table border='2' align='center'>");
            printWriter.print("<tr>");
            printWriter.print("<td>用户编号</td>");
            printWriter.print("<td>用户姓名</td>");
            printWriter.print("<td>用户密码</td>");
            printWriter.print("<td>用户性别</td>");
            printWriter.print("<td>用户邮箱</td>");
            printWriter.print("<td>操作</td>");
            printWriter.print("</tr>");
            for (user User : userList) {
                printWriter.print("<tr>");
                printWriter.print("<td>" + User.getUserId()+ "</td>");
                printWriter.print("<td>" + User.getUserName() + "</td>");
                printWriter.print("<td>******</td>");
                printWriter.print("<td>" + User.getSex() + "</td>");
                printWriter.print("<td>" + User.getEmail()+ "</td>");
                printWriter.print("<td><a href='Delete?userId="+User.getUserId()+"'>删除用户</a></td>");
                printWriter.print("</tr>");
            }
            printWriter.print("</table>");

    }
}
