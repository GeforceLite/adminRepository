package com.bjpowernode.controller;

import com.bjpowernode.Dao.QuestionDao;
import com.bjpowernode.entity.Question;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class QuestionRandServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
//自动出题Servlet
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1、调用Dao层自动拿出四个随机题目
        QuestionDao questionDao = new QuestionDao();
            //创建session对象并且用false处理防止恶意登陆
        HttpSession session = request.getSession(false);
        List list=questionDao.QuestionRand(request);
        //2、把题目作为共享数据添加到request
        session.setAttribute("key",list);
        //3、请求转发，申请exam.jsp进行请求转发，把题目写入响应体
        request.getRequestDispatcher("/exam.jsp").forward(request,response);
    }
}
