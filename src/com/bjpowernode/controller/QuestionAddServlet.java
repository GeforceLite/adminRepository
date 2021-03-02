package com.bjpowernode.controller;

import com.bjpowernode.Dao.QuestionDao;
import com.bjpowernode.entity.Question;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class QuestionAddServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
//用户试题增加方法
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //拿到请求行里的参数信息
        String question= request.getParameter("question");
        String optionA = request.getParameter("optionA");
        String optionB = request.getParameter("optionB");
        String optionC = request.getParameter("optionC");
        String optionD = request.getParameter("optionD");
        String answer  = request.getParameter("answer");
        Question questionValues = new Question(null,question, optionA, optionB, optionC, optionD,answer);
        //通过Dao层将参数信息由JDBC转交给数据库，如果成功就调用JSP信息返回成功信息
        QuestionDao questionDao = new QuestionDao();
        int result=questionDao.QuestionAdd(questionValues,request);
        if (result == 1) {
            //添加成功
            request.setAttribute("QuestionAddSuccessInfo","试题添加成功");
        } else {
            //添加失败
            request.setAttribute("QuestionAddSuccessInfo","试题添加失败");
        }
        //跳转到JSP页面
        request.getRequestDispatcher("/QuestionAddSuccessInfo.jsp").forward(request,response);
    }
}
