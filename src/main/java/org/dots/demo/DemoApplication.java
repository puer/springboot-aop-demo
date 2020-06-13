package org.dots.demo;

import lombok.extern.slf4j.Slf4j;
import org.dots.demo.aop.Query;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
@Slf4j
public class DemoApplication implements ApplicationRunner {

	private final Query query;

	public DemoApplication(Query query) {
		this.query = query;
	}
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		log.info("Running");
		try {
			query.doQuery("datasource picc", "another");
		} catch(Exception e) {
			log.error("query failed : {}", e.getMessage());
		}
		query.doQuery2();
	}
}
