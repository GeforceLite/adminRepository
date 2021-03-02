package com.bjpowernode.controller;

import com.bjpowernode.Dao.QuestionDao;
import com.bjpowernode.entity.Question;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class QuestionFindByIdServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
//通过试题编号查询题目详细信息
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.根据请求头参数拿到试题编号
        String questionId=request.getParameter("questionId");
        System.out.println(questionId);
        //2.将试题编号通过Dao层传入数据库进行检索
        QuestionDao questionDao = new QuestionDao();
        //接收返回的Question对象，并放入list集合
        Question question=questionDao.QuestionFindById(questionId,request);
        List list = new ArrayList();
        list.add(question);
        request.setAttribute("key1",list);
        //3.拿到试题并且返回通过JSP响应
        request.getRequestDispatcher("/findQuestionById.jsp").forward(request, response);
    }
}
