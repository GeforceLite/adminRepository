package com.bjpowernode.Filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class oneFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //过滤器对异常请求登陆做过滤处理
        //分别把请求对象做强转处理拿出来,好拿Session对象
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        /*
        getSession(boolean create)意思是返回当前reqeust中的HttpSession ，
        如果当前reqeust中的HttpSession 为null，当create为true，就创建一个新的Session，否则返回null；
        简而言之：
        HttpServletRequest.getSession(ture) 等同于 HttpServletRequest.getSession()
        HttpServletRequest.getSession(false) 等同于 如果当前Session没有就为null；
         */
        /*
        抓取请求包中的uri，了解到用户想要访问的文件是什么，如果文件内包含了含有login的文件名
        那么这种登陆文件无论是静态资源文件还是动态资源文件，都应该无条件放行
         */
        String uri = request.getRequestURI();// 网站名/资源文件名  /ServletDemo/login...HTML，Servlet之类的关于登陆相关的文件
        //做判断。如果请求资源文件与login相关，此时应该无条件进行放行
        //uri.indexOf("login")!=-1 看login在字符串中出现的位置，等于-1代表没出现过
        //不等于负一代表出现了
        if (uri.indexOf("login")!=-1||"/ServletDemo/".equals(uri)) {
            //filterChain.doFilter(servletRequest,servletResponse);这个方法代表拦截的文件交还给服务器
            filterChain.doFilter(servletRequest,servletResponse);
            return;
        }
        //如果访问的是其他文件，就需要这个用户在服务器这里有过保险箱，也就是有过Session令牌
        HttpSession session = request.getSession(false);
        if (session != null) {//session!=null就代表着有session存在
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }
        //如果以上都不是，那就是恶意请求做拒绝处理才对,直接内部跳转就行
        request.getRequestDispatcher("/login_Error.html").forward(servletRequest,servletResponse);
    }
}
