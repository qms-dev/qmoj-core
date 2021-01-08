package team.akina.qmoj.filter;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import team.akina.qmoj.utils.JwtUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public class NeedLoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String authCode = request.getHeader("Authorization");
        if(authCode == null){
            return false;
        }
        String[] nameAndValue = authCode.split(" ");
        if(nameAndValue.length != 2){
            return false;
        }
        String token = nameAndValue[1];
        if(JwtUtils.verifyToken(token) != 0){
            return false;
        }

        return true;
    }

    //方法执行后
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {

    }

    //页面渲染前
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {

    }

}
