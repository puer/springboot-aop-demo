package org.dots.demo.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Slf4j
@Component
@Order(Ordered.LOWEST_PRECEDENCE)
public class TransactionalAspect {
    @Around(value = "@annotation(org.dots.demo.aop.Transactional)")
    public void doPrepareResource(ProceedingJoinPoint jp) throws Throwable {
        MethodSignature signature = (MethodSignature) jp.getSignature();
        log.info("transaction begin");
        jp.proceed();
        log.info("transaction end");
    }
}

