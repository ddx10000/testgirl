package com.example.demo.log;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.UUID;

@Order(1)
@Component
//@WebFilter(urlPatterns = "/*", filterName = "logFilter")
public class LogFilter implements Filter {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Override
    public void init(FilterConfig filterConfig){
    }

    @Override
    public void doFilter(ServletRequest srequest, ServletResponse sresponse, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) srequest;
        String sid = UUID.randomUUID().toString().replace("-", "");
        long startTime = System.currentTimeMillis();
        logger.info("sid:{}, reuqest url:{}", sid, request.getRequestURI());
        String method = request.getMethod();
        request.getParameterMap().forEach((s, strings) ->
                logger.info("sid:{},request method:{},param==>{}:{}", sid, method, s, Arrays.asList(strings)));
        request.setAttribute("sid", sid);
        if (method.toLowerCase().equals("get")){
            chain.doFilter(request, sresponse);
        }else if (method.toLowerCase().equals("post")){
            ReaderReuseHttpServletRequestWrapper requestWrapper = new ReaderReuseHttpServletRequestWrapper(request);
            JSONObject requestBody = JSONObject.parseObject(requestWrapper.getBody());
            logger.info("sid:{},request method:{}, requestBody:{}", sid, request.getMethod(), requestBody);
            requestWrapper.setBody(requestBody.toJSONString());
            WriterReuseHttpServletResponseWrapper responseWrapper = new WriterReuseHttpServletResponseWrapper((HttpServletResponse) sresponse);
            chain.doFilter(requestWrapper, responseWrapper);
            JSONObject responseBody = JSONObject.parseObject(responseWrapper.getBody());
            if(responseBody != null){
                sresponse.getWriter().write(responseBody.toJSONString());
            }
            long usetime = System.currentTimeMillis() - startTime;
            logger.info("sid:{},request method:{}, responseBody:{}, useTime:{}ms", sid, request.getMethod(), responseBody, usetime);
        }else {
            logger.error("sid:{},no support request type:{}", sid, method);
        }
    }


    @Override
    public void destroy() {
    }

}
