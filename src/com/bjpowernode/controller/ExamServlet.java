package com.bjpowernode.controller;

import com.bjpowernode.entity.Question;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class ExamServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //从session拿取题目集合
        HttpSession session = request.getSession(false);
        List<Question> list=(List)session.getAttribute("key");
        int score = 0;
        //从请求包中拿出题目编号以及答案
        for (Question question:list){
            //拿取题目对应正确答案
            String questionId = question.getQuestionId();
            String answer = question.getAnswer();
            //拿取对应请求头客户端答案
            String userAnswer=request.getParameter("answer_"+questionId);
            System.out.println(userAnswer);
            //判分
            if (answer.equals(userAnswer)){
                //答案正确
                score += 25;
            }
        }
        //写入request，作为共享数据
        session.setAttribute("score",score);
        //写入响应体,这里也可以用QuestionAddSuccessInfo的标签来做提示也是没问题的
        request.getRequestDispatcher("/examScore.jsp").forward(request,response);
    }
}
