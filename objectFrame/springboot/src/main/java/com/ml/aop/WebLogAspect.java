package com.ml.aop;

import com.alibaba.fastjson.JSONObject;
import com.ml.domain.dto.WebLogAspectDto;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Aspect
@Component
public class WebLogAspect {

    @Pointcut("execution(public * com.ml.controller..*.*(..))")
    public void webLog(){

    }

    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint){
        WebLogAspectDto webLogAspectDto = new WebLogAspectDto();
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        webLogAspectDto.setUrl(request.getRequestURL().toString());
        webLogAspectDto.setHttpMethod(request.getMethod());
        webLogAspectDto.setIp(request.getRemoteAddr());
        webLogAspectDto.setClassMethod(joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        webLogAspectDto.setArgs(Arrays.toString(joinPoint.getArgs()));

        Map<String,String> paramMap = new HashMap<>();
        Enumeration enumeration = request.getParameterNames();
        while (enumeration.hasMoreElements()){
            String paraName = (String) enumeration.nextElement();
            paramMap.put(paraName,request.getParameter(paraName));
        }
        webLogAspectDto.setParaName(paramMap);
        log.info(JSONObject.toJSONString(webLogAspectDto));

    }

}
