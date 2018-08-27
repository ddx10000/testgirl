package com.example.demo.log;

import com.alibaba.fastjson.JSONObject;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

//@Aspect
//@Component
public class LogAspect {
    @Autowired
    private HttpServletRequest request;
    protected static org.slf4j.Logger logger = LoggerFactory.getLogger(LogAspect.class);

    @Pointcut("execution(* com.example.demo.me..*.*(..))")
    public void controllerLog() {
    }

    @Pointcut("execution(* com.example.demo.me..*.*(..))")
    public void utilLog() {
    }

    @Pointcut("execution(* com.example.demo.me..*.*(..))")
    public void serviceLog() {
    }

    @Pointcut("execution(* com.example.demo.me..*.*(..))")
    public void commonServiceLog() {
    }

    @Pointcut("execution(* com.example.demo.me..*.*(..))")
    public void adapterLog() {
    }

    @Around(value = "controllerLog() || utilLog() || serviceLog() || commonServiceLog() || adapterLog()")
    //@Around(value = "controllerLog()")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        return getObject(joinPoint);
    }

    private Object getObject(ProceedingJoinPoint joinPoint) throws Throwable {
        String sid = (String) request.getAttribute("sid");
        logger.info("sid:{} ==START==> CLASS_METHOD:{}.{}  ARGS:{} ",
                sid, joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName(), Arrays.toString(joinPoint.getArgs()));
        long startTime = System.currentTimeMillis();
        Object result = joinPoint.proceed(joinPoint.getArgs());
        long usetime = System.currentTimeMillis() - startTime;
        Object resultLog = result;
        try {
            resultLog = JSONObject.toJSON(result);
        } catch (Exception e) {
        }
        logger.info("sid:{} ==END==> CLASS_METHOD : {}.{}   RETURN:{}, useTime:{}ms",
                sid, joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName(), resultLog, usetime);
        return result;
    }
}