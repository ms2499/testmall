package com.testmall.Interceptor;

import com.testmall.Tools.CharsetTool;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class ManLoginInterceptor implements HandlerInterceptor {
    CharsetTool cstool = new CharsetTool();
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        if (request.getSession().getAttribute("manAccount") == null) {
            //cstool.pLogln("session中的manAccount為null");
            response.setStatus(403);
            return false;
        }
        //cstool.pLogln("session中的manAccount為= " + request.getSession().getAttribute("manAccount"));
        return true;
    }
}
