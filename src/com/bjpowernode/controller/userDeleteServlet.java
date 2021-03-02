package com.bjpowernode.controller;

import com.bjpowernode.Dao.UserDao;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class userDeleteServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
//UserDelete Method
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        //将查询信息通过DAO层交给数据库
        UserDao userdao = new UserDao();
        //从请求头中拿到对象
        String userId = request.getParameter("userId");
        PrintWriter printWriter = response.getWriter();
        if (userdao.Delete(userId)==1){
            //删除成功
            printWriter.write("<font style='color:red;font-size:40'>用户信息删除成功</font>");
        }else{
            //删除失败本Id无记录
            printWriter.write("<font style='color:red;font-size:40'>用户信息删除失败</font>");
        }
    }
}
