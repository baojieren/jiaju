package ink.baojie.jiaju.base.aop;

import com.alibaba.fastjson.JSONObject;
import ink.baojie.jiaju.util.IpUtil;
import ink.baojie.jiaju.util.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.net.URLDecoder;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Aspect
@Component
@Slf4j
public class LogAspect {

    private ThreadLocal<Map> threadLocal = new ThreadLocal<>();

    @Pointcut("execution(public * ink.baojie.jiaju.web.*.*(..))")
    public void webLog() {
    }

    @Before("webLog()")
    public void theBefore(JoinPoint joinPoint) {
        // ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        // HttpServletRequest request = requestAttributes.getRequest();
        // // 解析token拿用户id
        // String userId = null;
        // String authorization = request.getHeader("Authorization");
        // if (!StringUtil.isEmpty(authorization) && authorization.startsWith("Bearer")) {
        //     try {
        //         String token = authorization.split(" ")[1];
        //         userId = JwtUtils.getUserId(token);
        //     } catch (Exception e) {
        //         log.error("解析token拿userId出错 {}", e.toString());
        //     }
        // }
        //
        // Object[] args = joinPoint.getArgs();
        // List<Object> list = new ArrayList<>();
        // for (Object arg : args) {
        //     if (arg instanceof ServletRequest || arg instanceof ServletResponse || arg instanceof MultipartFile) {
        //         continue;
        //     }
        //     list.add(arg);
        // }
        // log.info("请求--->: {},\tuserId: {},\tIP: {},\t参数: {}"
        //         , request.getRequestURL().toString()
        //         , userId
        //         , IpUtil.getRemoteIP(request)
        //         , JSONObject.toJSONString(list));
        // log.info("URL         :{}", request.getRequestURL().toString());
        // log.info("HTTP Method :{}", request.getMethod());
        // log.info("Class Method:{}.{}", joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName());
        // log.info("userId      :{}", userId);
        // log.info("IP          :{}", request.getRemoteAddr());
        // log.info("请求参数      :{}", JSONObject.toJSONString(list));
    }

    @After("webLog()")
    public void theAfter() {
        // log.info("===========end==========={}", System.lineSeparator());
    }

    @Around("webLog()")
    public Object aroundGo(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        Map<String, Object> map = new HashMap<>();

        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        assert requestAttributes != null;
        HttpServletRequest request = requestAttributes.getRequest();

        String authorization = request.getHeader("Authorization");
        String requestURL = request.getRequestURL().toString();
        String requestURI = request.getRequestURI();
        String method = request.getMethod();
        String queryString = request.getQueryString();
        Object[] args = joinPoint.getArgs();

        // 解析token拿用户id
        String userId = null;
        if (!StringUtils.isEmpty(authorization) && authorization.startsWith("Bearer")) {
            try {
                String token = authorization.split(" ")[1];
                userId = JwtUtils.getUserId(token);
            } catch (Exception e) {
                log.error("解析token拿userId出错 {}", e.toString());
            }
        }

        // 请求参数
        StringBuilder params = new StringBuilder();
        if (HttpMethod.GET.toString().equals(method)) {
            if (!StringUtils.isEmpty(queryString)) {
                params = new StringBuilder(URLDecoder.decode(queryString, "UTF-8"));
            }
        } else {
            if (!ObjectUtils.isEmpty(args)) {
                for (Object arg : args) {
                    if (arg instanceof ServletRequest || arg instanceof ServletResponse || arg instanceof MultipartFile) {
                        continue;
                    }
                    params.append(JSONObject.toJSONString(arg));
                }
            }
        }

        map.put("startTime", startTime);
        map.put("url", requestURL);
        map.put("userId", userId);
        threadLocal.set(map);

        log.info("请求--->: {},\tuserId: {},\tIP: {},\t参数: {}"
                , map.get("url")
                , map.get("userId")
                , IpUtil.getRemoteIP(request)
                , params.toString());

        // 执行实际方法，result为方法执行返回值
        Object result = joinPoint.proceed();

        Map getMap = threadLocal.get();

        log.info("响应<---: {},\tuserId: {},\tTime: {}ms,\t参数: {}"
                , getMap.get("url")
                , getMap.get("userId")
                , System.currentTimeMillis() - (long) getMap.get("startTime")
                , JSONObject.toJSONString(result));
        return result;
    }
}
