package com.bjpowernode.controller;

import com.bjpowernode.Dao.UserDao;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class logInServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
//用户登陆Servlet验证
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置编译器类型
        response.setContentType("text/html;charset=utf-8");
        //从请求行中获取参数
        String userName= request.getParameter("userName");
        String userPassWord = request.getParameter("userPassWord");
        //创建Dao层对象
        UserDao userdao = new UserDao();
        //接收用户验证结果
        int result=userdao.YanZheng(userName,userPassWord);
        if (result==1){
            //判断其身份合法后，在服务端为他提供一个HttpSession作为一个独有的保险箱令牌
            HttpSession httpSession = request.getSession();
            //登陆成功,数据库中有这条数据,将页面导向index著页面上
            response.sendRedirect("/ServletDemo/index.html");
        }else{
            //登陆失败,数据库中没有这条数据
            //跳转到重新登陆页面上
            response.sendRedirect("/ServletDemo/login_Error.html");
        }
    }
}
