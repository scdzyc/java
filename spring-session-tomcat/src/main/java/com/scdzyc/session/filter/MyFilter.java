package com.scdzyc.session.filter;

import com.scdzyc.session.config.SessionManage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Component
@Slf4j
public class MyFilter implements Filter {


    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpSession session = request.getSession();
        String id = session.getId();
        log.info("==================sessionID=====================");
        log.info(id);
        log.info("------------------------------------------------");
        filterChain.doFilter(servletRequest,servletResponse);
    }
}
