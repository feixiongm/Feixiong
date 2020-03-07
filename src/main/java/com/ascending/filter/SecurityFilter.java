package com.ascending.filter;

import com.ascending.model.User;
import com.ascending.service.JWTService;
import com.ascending.service.UserService;
import io.jsonwebtoken.Claims;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "security", urlPatterns = {"/*"}, dispatcherTypes = {DispatcherType.REQUEST})
public class SecurityFilter implements Filter {
    private static String AUTH_URI = "/auth";
    @Autowired
    private JWTService jwtService;
    @Autowired
    private UserService userService;
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        int statusCode = authorization(req);
        if(statusCode == HttpServletResponse.SC_ACCEPTED) filterChain.doFilter(request,response);
        else((HttpServletResponse)response).sendError(statusCode);
    }

    private int authorization(HttpServletRequest request) {
        int statusCode = HttpServletResponse.SC_UNAUTHORIZED;
        String uri = request.getRequestURI();
        String verb = request.getMethod();
        if (uri.equalsIgnoreCase(AUTH_URI)) return HttpServletResponse.SC_ACCEPTED;

        try {
            String token = request.getHeader("Authorization").replaceAll("^(.*?) ", "");
            if (token == null || token.isEmpty()) return statusCode;

            Claims claims = jwtService.decryptJwtToken(token);
            if(claims.getId() != null){
                User u = userService.getUserById(Long.valueOf(claims.getId()));
               if(u != null) statusCode = HttpServletResponse.SC_ACCEPTED;
            }
        }catch(Exception e){
            logger.error("can not verify the token",e);
        }
        return statusCode;
    }

}
