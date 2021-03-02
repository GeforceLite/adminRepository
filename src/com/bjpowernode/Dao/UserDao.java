package com.bjpowernode.Dao;

import com.bjpowernode.entity.Question;
import com.bjpowernode.entity.user;
import com.bjpowernode.util.JdbcUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDao {
    private JdbcUtil jdbc = new JdbcUtil();

    //用户添加
    public int add(user users){
        //SQL语句
        String sql="insert into users(userName,userPassWord,userSex,userEmail)"+"values(?,?,?,?)";
        //建立预编译连接通道，把SQL语句传进去
        PreparedStatement ps = jdbc.createStatement(sql);
        int result = 0;
        try {
            //PreparedStatement的setString方法，并把SQL语句中values(?,?,?,?) ?位置的值塞进去
            ps.setString(1,users.getUserName());
            ps.setString(2,users.getPassWord());
            ps.setString(3,users.getSex());
            ps.setString(4, users.getEmail());
            //上传结果执行并且接收
            result = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            //关闭流
            jdbc.close();
        }
        return result;
    }
    //优化添加的Connection方法要重新添加request对象
    //为了不修改源代码并且遵守OCP原则，参数列表要加入request对象来方便getCon(request)的方法调用
    //关闭close时要传参request的
    public int add(user users, HttpServletRequest request){
        //SQL语句
        String sql="insert into users(userName,userPassWord,userSex,userEmail)"+"values(?,?,?,?)";
        //建立预编译连接通道，把SQL语句传进去
        PreparedStatement ps = jdbc.createStatement(sql,request);
        int result = 0;
        try {
            //PreparedStatement的setString方法，并把SQL语句中values(?,?,?,?) ?位置的值塞进去
            ps.setString(1,users.getUserName());
            ps.setString(2,users.getPassWord());
            ps.setString(3,users.getSex());
            ps.setString(4, users.getEmail());
            //上传结果执行并且接收
            result = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            //关闭流，把Request传入
            jdbc.close(request);
        }
        return result;
    }
    //用户查询方法
    public List find(){
        //创建查询结果集对象
        ResultSet rs = null;
        //SQL语句
        String sql="select * from users";
        //建立预编译连接通道，把SQL语句传进去
        PreparedStatement ps = jdbc.createStatement(sql);
        //建立查询结果集
        List userList = new ArrayList();
        try {
            //提交SQL语句，并且接收查询结果集
            rs = ps.executeQuery();
            while (rs.next()){
                Integer userId = rs.getInt("userId");
                String userName = rs.getString("userName");
                String userPassWord = rs.getString("userPassWord");
                String userSex = rs.getString("userSex");
                String userEmail = rs.getString("userEmail");
                //把结果集遍历完，数据交还给user对象赋值，每次遍历都是一个新的对象
                user User = new user(userId, userName, userPassWord, userSex, userEmail);
                //把对象送进查询结果集
                userList.add(User);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            //关闭流
            jdbc.close(rs);
        }
        return userList;
    }
    //用户删除方法
    public int Delete(String Id){
        Integer result=null;
        //删除语句
        String sql="delete from users where userId="+Id;
        //预编译通道
        PreparedStatement ps = jdbc.createStatement(sql);
        try {
            //提交SQl语句并且接收查询结果集
            result = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            //关闭流
            jdbc.close();
        }
        return result;
    }
    //登录查询验证方法
    public int YanZheng(String username,String userpassWord){
        int result=0;
        //SQL语句
        String sql = "select count(*) from users where userName=? and userPassWord=?";
        //创建预编译对象并且把SQL语句传入
        PreparedStatement ps = jdbc.createStatement(sql);
        ResultSet rs = null;
        try {
            //把预编译对象里的？按相应位置的序号送入变量名
            ps.setString(1,username);
            ps.setString(2,userpassWord);
            //查询结果集接收返回对象
            rs=ps.executeQuery();
            while (rs.next()){
                //接收查询到的条数
                result = rs.getInt("count(*)");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            //变量名
            jdbc.close(rs);
        }
        return result;
    }
}
