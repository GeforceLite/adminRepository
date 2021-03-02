package com.bjpowernode.Listener;

import com.bjpowernode.util.JdbcUtil;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpServlet;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class ServletConetextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        //启动时创建Connection对象
        //for循环先建他20个
        //引入JDBC对象
        JdbcUtil jdbc = new JdbcUtil();
        //抓来一个野生Map来放Connection
        Map map = new HashMap();
        for (int i = 1; i <= 20; i++) {
            //启动JDBC通道
            Connection connection = jdbc.getCon();
            //在JDBCutil工具类内，对getCon方法进行重载，保证了OCP原则不修改源代码
            //Http服务器启动完成，自行创建Connection通道
            System.out.println("Http服务器启动完成，自行创建"+connection+"Connection通道");
            //为保证key不一样，把connection的地址放到key里
            //true代表该通道处于空闲状态，反之就是正在被占用
            map.put(connection, true);
            //把他保存在全局作用域对象中，让他在服务器启动的过程中全程可用sce是这个方法里唯一的对象
            //通过sce拿到全局作用域对象
            ServletContext application=sce.getServletContext();
            //把保存好的JDBC的20个Connection的Map，放到全局作用对象application中
            //保证在服务器启动的全局作用域中始终可用
            application.setAttribute("key1",map);

        }
    }
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        //服务器关闭时销毁对象关闭流资源
        //这里也是得先拿到全局作用对象
        //也是得拿sce这个唯一的对象来拿全局作用对象
        ServletContext application = sce.getServletContext();
        /*
        通过之前放的application.setAttribute("key1",map);
        拿到map集合，由于开了20个IO流所以要把Map中的IO流拿出来根据每一个Key
        来挨个摁死IO流
        如果直接removeAttribute(key)的话，的确移除了Map但是Connection流没关
        所以直接remove方法不可取
         */
        Map map=(Map)application.getAttribute("key1");
        //keySet()方法可以把Map中的所有Key都取出来放到Set集合中，但是Set集合无序，
        //无法根据Key读取Value，因此只能用迭代器
        Iterator iterator=map.keySet().iterator();
        while(iterator.hasNext()){
            //每次拿到后，把地址赋值给Connection
            Connection connection=(Connection)iterator.next();
            if (connection!=null){
                System.out.println("内存地址为"+connection+"自动销毁完成");
            }
        }
    }
}
