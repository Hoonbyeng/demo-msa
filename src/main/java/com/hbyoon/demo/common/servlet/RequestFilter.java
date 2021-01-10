package com.hbyoon.demo.common.servlet;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.MDC;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(1)
public class RequestFilter implements Filter {

    @Override
    public void destroy() { 
    	/*do nothing*/
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
            ServletException {
    	if ( request instanceof HttpServletRequest ) {
    		MDC.put("requestURL", ((HttpServletRequest)request).getRequestURI());
    	}
    	
    	chain.doFilter(request, response);
    }

    @Override
    public void init(FilterConfig config) {
    }

}
