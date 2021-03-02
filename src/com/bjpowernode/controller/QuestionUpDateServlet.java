package com.bjpowernode.controller;

import com.bjpowernode.Dao.QuestionDao;
import com.bjpowernode.entity.Question;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class QuestionUpDateServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
//试题更新功能
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1、从请求头中想要更新的题目信息
        String questionId=request.getParameter("questionId");
        String question=request.getParameter("question");
        String optionA = request.getParameter("optionA");
        String optionB = request.getParameter("optionB");
        String optionC = request.getParameter("optionC");
        String optionD = request.getParameter("optionD");
        String answer  = request.getParameter("answer");
        Question questionValues = new Question(questionId,question, optionA, optionB, optionC, optionD,answer);
        //2、Dao层更新操作
        QuestionDao questionDao = new QuestionDao();
        int result=questionDao.QuestionUpdate(questionValues, request);
        //3、调用JSP操作结果响应
        if (result == 1) {
            //更新成功
            //ps:这里借用了QuestionAddSuccessInfo的JSP作输出，名字不太对但是效果都一样
            request.setAttribute("QuestionAddSuccessInfo","试题更新成功");
        } else {
            //更新失败
            request.setAttribute("QuestionAddSuccessInfo","试题更新失败");
        }
        request.getRequestDispatcher("/QuestionAddSuccessInfo.jsp").forward(request,response);
    }
}
