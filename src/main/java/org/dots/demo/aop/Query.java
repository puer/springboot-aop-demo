package org.dots.demo.aop;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class Query {

    @WithResource
    public void doQuery(String dataSourceName) {
        log.info("in the query");
        throw new RuntimeException("OOM");
    }

    @WithResource
    public void doQuery2() {
        log.info("in the query2");
    }
}
