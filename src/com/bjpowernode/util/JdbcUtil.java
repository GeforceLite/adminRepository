package com.bjpowernode.util;


import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.sql.*;
import java.util.Iterator;
import java.util.Map;

// JdbcUtil obj = new JdbcUtil();  obj.getCon()
// JdbcUtil obj = new JdbcUtil();  obj.createStatement();
// JdbcUtil.getCon();
public class JdbcUtil {

     final String URL="jdbc:mysql://localhost:3306/bjpowernode";
     final String USERNAME="root";
     final String PASSWORD="333";
     PreparedStatement ps= null;
     Connection con = null;

    //将jar包中driver实现类加载到JVM中
    static{
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    //封装连接通道创建细节
    public  Connection   getCon(){

        try {
            con = DriverManager.getConnection(URL,USERNAME,PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return con;
    }
    //对getCon进行重载处理，保证不修改源码的情况下，还能操作创建的Connection流对象
    public Connection getCon(HttpServletRequest request){
        //拿到全局作用域对象需要request对象，所以参数列表要有request
        ServletContext application = request.getServletContext();
        //拿到作用域对象之后，还要拿到Map集合
        Map map=(Map) application.getAttribute("key1");
        //Map集合里有已经放好的Connection接口，循环遍历拿到手
        Iterator iterator=map.keySet().iterator();
        while (iterator.hasNext()) {
            //拿到迭代出来的数据
            con=(Connection)iterator.next();
            //打布尔标记，查看当前流可用状态是否为true
            //true即为可用
            boolean flag = (boolean) map.get(con);
            if (flag == true) {
                //可用，就直接跳出循环break，跳出去之前，要把这个通道的状态设定为false
                map.put(con,false);
                break;
            }
        }
        //返回一个空闲的Connection
        return con;
    }
    //封装交通工具创建细节
    public  PreparedStatement createStatement(String sql){

        try {
            ps =  getCon().prepareStatement(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ps;
    }

    //再次重载，加入request操作
    public  PreparedStatement createStatement(String sql, HttpServletRequest request){
        try {
            //重载操作落实，传入request参数的getCon方法
            ps =  getCon(request).prepareStatement(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ps;
    }
    // ps与con销毁细节 insert,update,delete
    public  void close(){
        if(ps!=null){
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if(con!=null){
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    //close方法也要做重载，对用完的IO流要及时做关闭处理
    public  void close(HttpServletRequest request){
        if(ps!=null){
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        //从全局作用对象的那个Map中专门找Connection对象来做销毁
        //这里之前的方法就要重新关闭掉
        //老规矩先拿全局作用对象
        ServletContext application = request.getServletContext();
        //通过key拿ServletContext对象里的map
        Map map=(Map) application.getAttribute("key1");
        //用完了返回Connection的true也就是空闲状态
        map.put(con,true);
        //这个方法重载完毕后就可以把所有的close里面添加request对象了
    }

     //select ps,con,rs
    public  void close(ResultSet rs){
        if(rs!=null){
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        close();
    }
}
