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

@Aspect
@Slf4j
@Component
@Order(10)
public class Advisor {
    ThreadLocal<String> before = new ThreadLocal<>();

    @Around(value = "@annotation(withResource) && args(dataSourceName)")
    public void doPrepareResource(ProceedingJoinPoint jp, WithResource withResource, String dataSourceName) throws Throwable {
        MethodSignature signature = (MethodSignature) jp.getSignature();
        log.info("{}", signature);
        log.info("preppare resource");
        log.info("{}", withResource);
        log.info("{}", dataSourceName);

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
