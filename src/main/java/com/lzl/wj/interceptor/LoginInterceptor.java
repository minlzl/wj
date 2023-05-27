package com.lzl.wj.interceptor;

import com.lzl.wj.pojo.User;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;

public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (HttpMethod.OPTIONS.toString().equals(request.getMethod())) {
            response.setStatus(HttpStatus.NO_CONTENT.value());
            return true;
        }
        Subject subject = SecurityUtils.getSubject();
        if (!subject.isAuthenticated()) {
            return false;
        }
        return true;
//        HttpSession session = request.getSession();
//        String contextPath = session.getServletContext().getContextPath();
//        String[] requireAuthPages = new String[]{
//                "index",
//        };
//        String uri = request.getRequestURI();
//        uri = StringUtils.remove(uri,contextPath+"/");
//        String page = uri;
//
//        if(beginWith(page,requireAuthPages)) {
//            User user = (User) session.getAttribute("user");
//            if(user==null) {
//                response.sendRedirect("login");
//                return false;
//            }
//        }
//        return true;
    }

    private boolean beginWith(String page,String [] requireAuthPages) {
        boolean result = false;
        for(String requireAuthPage:requireAuthPages) {
            if(StringUtils.startsWith(page,requireAuthPage)) {
                result = true;
                break;
            }
        }
        return result;
    }
}
