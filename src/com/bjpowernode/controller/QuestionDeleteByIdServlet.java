package com.bjpowernode.controller;

import com.bjpowernode.Dao.QuestionDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class QuestionDeleteByIdServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
//通过试题编号删除题目
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1、从请求头拿到题目ID参数信息
        String questionId=request.getParameter("questionId");
        //2、调用Dao层来进行数据库操作
        QuestionDao questionDao = new QuestionDao();
        int result = questionDao.QuestionDelete(questionId, request);
        //3、调用JSP来返回操作信息
        if (result==1){
            //删除成功
            request.getRequestDispatcher("/questionDeleteSuccess.jsp").forward(request,response);
        }else {
            //删除失败
            request.getRequestDispatcher("/questionDeleteFail.jsp").forward(request,response);
        }
    }
}
