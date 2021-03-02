package com.bjpowernode.Dao;

import com.bjpowernode.entity.Question;
import com.bjpowernode.util.JdbcUtil;
import javax.servlet.http.HttpServletRequest;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class QuestionDao  {
    JdbcUtil jdbc = new JdbcUtil();
    //用户试题增加
    public int QuestionAdd(Question question,HttpServletRequest request){

        int result = 0;
        //创建SQL语句
        String SQL = "insert into question(titel,optionA,optionB,optionC,optionD,answer)" + "values(?,?,?,?,?,?)";
        //这里使用在服务器提前建立好的那20个通道，节省插入语句时间
        PreparedStatement ps = jdbc.createStatement(SQL,request);
        try {
            ps.setString(1,question.getQuestion());
            ps.setString(2,question.getOptionA());
            ps.setString(3,question.getOptionB());
            ps.setString(4,question.getOptionC());
            ps.setString(5, question.getOptionD());
            ps.setString(6,question.getAnswer());
            result = ps.executeUpdate();
            System.out.println(result);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            jdbc.close();
        }
        return result;
    }
    //用户试题查询
    public List QuestionFind(HttpServletRequest request){
        //创建SQL语句
        String SQL = "select * from question";
        //老规矩，建立通道然后传参
        PreparedStatement ps = jdbc.createStatement(SQL,request);
        //建立查询结果集
        ResultSet resultSet = null;
        List questionList = new ArrayList();
        //上传SQL语句然后进行赋值遍历
        try {
            resultSet = ps.executeQuery();
            while (resultSet.next()){
                //通过表头拿取参数
                String quetionId=resultSet.getString("questionId");
                String titel = resultSet.getString("titel");
                String optionA = resultSet.getString("optionA");
                String optionB = resultSet.getString("optionB");
                String optionC = resultSet.getString("optionC");
                String optionD = resultSet.getString("optionD");
                String answer = resultSet.getString("answer");
                //传入Question对象就行
                Question question=new Question(quetionId,titel,optionA, optionB, optionC, optionD, answer);
                questionList.add(question);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            jdbc.close(resultSet);
        }
        return questionList;
    }
    //通过试题ID检索题目办法
    public Question QuestionFindById(String questionId,HttpServletRequest request){
        Question question =null;
        String SQL = "select * from question where questionId=?";
        //创建流对象并且创建查询结果集
        PreparedStatement preparedStatement = jdbc.createStatement(SQL, request);
        ResultSet resultSet = null;
        try {
            //SQL的?处塞入数据
            preparedStatement.setString(1,questionId);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String quetionId=resultSet.getString("questionId");
                String titel = resultSet.getString("titel");
                String optionA = resultSet.getString("optionA");
                String optionB = resultSet.getString("optionB");
                String optionC = resultSet.getString("optionC");
                String optionD = resultSet.getString("optionD");
                String answer = resultSet.getString("answer");
                //传入Question对象创建出新对象
                question=new Question(quetionId,titel,optionA, optionB, optionC, optionD, answer);
                System.out.println(question.getQuestionId());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            jdbc.close(resultSet);
        }
        return question;
    }
    //根据试题ID删除方法
    public int QuestionDelete(String Id,HttpServletRequest request){
        int result=0;
        String SQL = "delete from question where questionId=" + Id;
        PreparedStatement preparedStatement = jdbc.createStatement(SQL, request);
        try {
            result=preparedStatement.executeUpdate(SQL);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            jdbc.close();
        }
        return result;
    }
    //试题更新功能
    public int QuestionUpdate(Question question,HttpServletRequest request){
        String SQL = "update question set titel=?,optionA=?,optionB=?,optionC=?,optionD=?,answer=? where questionId=" + question.getQuestionId();
        PreparedStatement preparedStatement = jdbc.createStatement(SQL,request);
        int result=0;
        try {
            preparedStatement.setString(1,question.getQuestion());
            preparedStatement.setString(2,question.getOptionA());
            preparedStatement.setString(3,question.getOptionB());
            preparedStatement.setString(4,question.getOptionC());
            preparedStatement.setString(5,question.getOptionD());
            preparedStatement.setString(6,question.getAnswer());
            result=preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            jdbc.close();
        }
        return result;
    }
    //试题自动出题功能
    public List QuestionRand(HttpServletRequest request){
        String SQL = "select * from question order by rand() limit  0,4";
        //老规矩，建立通道然后传参
        PreparedStatement ps = jdbc.createStatement(SQL,request);
        //建立查询结果集
        ResultSet resultSet = null;
        List questionList = new ArrayList();
        //上传SQL语句然后进行赋值遍历
        try {
            resultSet = ps.executeQuery();
            while (resultSet.next()){
                //通过表头拿取参数
                String quetionId=resultSet.getString("questionId");
                String titel = resultSet.getString("titel");
                String optionA = resultSet.getString("optionA");
                String optionB = resultSet.getString("optionB");
                String optionC = resultSet.getString("optionC");
                String optionD = resultSet.getString("optionD");
                String answer = resultSet.getString("answer");
                //传入Question对象就行
                Question question=new Question(quetionId,titel,optionA, optionB, optionC, optionD, answer);
                questionList.add(question);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            jdbc.close(resultSet);
        }
        return questionList;
    }
}
