package com.testmall.Interceptor;

import com.testmall.Service.UserinfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.OutputStream;
import java.io.PrintWriter;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

//使用者登入攔截器
@Component
public class UserLoginInterceptor implements HandlerInterceptor {
    @Autowired
    UserinfoService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        if (request.getSession().getAttribute("manAccount") != null) {
            //若是管理員登入
            return true;
        }else{
            String authorHeader  = request.getHeader(AUTHORIZATION);
            String bearer ="Bearer ";
            if (authorHeader != null && authorHeader.startsWith(bearer)){
                String token = authorHeader.substring(bearer.length()).replace(" ", "");
                return userService.verifyUser(token).equals("0000");
            }else {
                response.setStatus(401);
                response.setCharacterEncoding("UTF-8");
                response.setContentType("text/html;charset=utf-8");
                PrintWriter out = response.getWriter();
                out.write("請先登入!");
                out.flush();
                out.close();
                return false;
            }
        }
    }

//    @Override
//    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
//        //todo
//    }
//
//    @Override
//    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception e) throws Exception {
//        //todo
//    }
}

