package com.bjpowernode.controller;

import com.bjpowernode.Dao.QuestionDao;
import com.bjpowernode.Dao.UserDao;
import com.bjpowernode.entity.Question;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class QuestionFindServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
//用户试题查询处理方法
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //必须先创建QuestionDao层对象
        QuestionDao questionDao = new QuestionDao();
        //查询并且返回结果集
        List questionFind=questionDao.QuestionFind(request);
        //把集合放进响应对象中
        request.setAttribute("key1", questionFind);
        //转发调用到jsp，把结果打回
        request.getRequestDispatcher("/findQuestion.jsp").forward(request,response);

    }
}
