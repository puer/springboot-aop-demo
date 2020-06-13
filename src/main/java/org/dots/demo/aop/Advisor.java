package org.dots.demo.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Queue;
import java.util.Stack;

@Aspect
@Slf4j
@Component
@Order(10)
public class Advisor {
    ThreadLocal<Stack<String>> before = new ThreadLocal<>();

    @Pointcut(value = "@annotation(withResource) && args(sql,..)")
    public void withDataSourceResource(WithResource withResource, String sql) { }

//    @Around(value = "@annotation(withResource) && args(dataSourceName,..)")
    @Around(value = "withDataSourceResource(withResource, dataSourceName)")
    public void doPrepareResource(ProceedingJoinPoint jp, WithResource withResource, String dataSourceName) throws Throwable {
        MethodSignature signature = (MethodSignature) jp.getSignature();
        log.info("{}", signature);
        log.info("preppare resource");
        log.info("{}", withResource);
        log.info("{}", dataSourceName);
        // get current datasource
        // if current != dataSourceName
        //     before.get().push(dataSourceName);
        //     proceed
        //     before.get().pop();
        // else
        //     proceed


        try {
            jp.proceed();
        } catch (Throwable throwable) {
            log.info("got exception advisor?");
            throw throwable;
        } finally {
            log.info("release resource");
        }
    }

}
