package com.smart.life.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BranchIdInterceptor extends HandlerInterceptorAdapter {

    private final Logger logger = LoggerFactory.getLogger(BranchContextHolder.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {

        String branchId = request.getHeader("X-BranchID");

        BranchContextHolder.set(branchId);

        logger.info("Search for X-BranchID  :: \" + branchId");

        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        BranchContextHolder.clear();
    }
}
