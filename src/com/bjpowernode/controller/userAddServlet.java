package com.bjpowernode.controller;

import com.bjpowernode.Dao.UserDao;
import com.bjpowernode.entity.user;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

public class userAddServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
        //请求方式Get，所以在这里写
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置浏览器编译方式
        response.setContentType("text/html;charset=utf-8");
        //创建Dao层对象
        UserDao userdao = new UserDao();
        //用变量来接收服务端传来的数据
        String userName = request.getParameter("userName");
        String userPassWord = request.getParameter("userPassWord");
        String userSex = request.getParameter("userSex");
        String userEmail = request.getParameter("userEmail");
        //控制台输出测试，看是否接受到了参数，可有可无的测试代码
        System.out.println(userName+userPassWord+userSex+userEmail);
        //对用户对象进行赋值
        user User = new user(null,userName,userPassWord,userSex,userEmail);
        //接收完后对浏览器进行响应
        //http响应输出流
        PrintWriter printWriter = null;
        printWriter = response.getWriter();
        //添加日期对象对用户添加到数据库的时间做测试
        Date startTime = new Date();
        //接收添加结果
        //优化后要根据重载的add方法传入request对象，等待时间大幅缩短从12ms----->3ms
        //优化成功
        int r = userdao.add(User,request);
        //创建结束时间
        Date endTime = new Date();
        //打印信息到控制台
        /*
        这个位置，用户等待时间过长,等待时间过长的原因是JDBC封装类创建conncetion接口相当于开一个IO流。
        开的过程很慢，要优化。利用监听器ServletContextListener接口下的属性来重写两个方法，实现在Tomcat
        启动的时候就可以提前创建n个Connection通道，之后等着Preparestatement对象来调用connection接口即可
        这个提前创建的通道根据ServletConnectionListener接口下的两个重写方法
        1.public void contextlnitlized服务器启动时自动创建connection对象
        2.public void contextDestory  服务器关闭时自动销毁connection的流
        这样就可以大大减少用户add方法添加新用户到MySQL的时间，同理，这个不局限于add方法
        其他的只要是关于JDBC的connection操作都可以提前创建流对象来做优化
         */
        System.out.println("添加用户到MySQL共计消耗时长为 "+(endTime.getTime()-startTime.getTime())+" 毫秒");
        if (r==1){
            printWriter.write("<font style='color:red;font-size:40'>用户信息注册成功</font>");
        }else{
            printWriter.write("<font style='color:red;font-size:40'>用户信息注册失败</font>");
        }
    }
}
