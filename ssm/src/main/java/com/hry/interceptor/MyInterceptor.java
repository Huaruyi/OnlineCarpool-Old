package com.hry.interceptor;

import com.hry.domain.Admin;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MyInterceptor implements HandlerInterceptor {

    /**
     * 预处理，controller方法执行前
     * return true放行，执行下一个拦截器，如果没有，执行controller里面的方法
     * return false不放行 可利用request等跳转
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String url = request.getRequestURI();
        System.out.println(url);
        if (url.equals("/forward")||url.equals("/loginTypeUI")||url.equals("/loginUI")||url.equals("/registerTypeUI")||url.equals("/registerUI")||url.equals("admin/admin_login.jsp")){
            return true;
        }else {
            Admin admin = (Admin) request.getSession().getAttribute("admin");
            if (admin != null) {
                return true;
            }
        }
        //response.sendRedirect("/admin/admin_login.jsp");
        request.getRequestDispatcher("/admin/admin_login.jsp").forward(request,response);
        return false;
    }
}
