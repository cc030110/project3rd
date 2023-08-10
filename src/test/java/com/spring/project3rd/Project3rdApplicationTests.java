package com.spring.project3rd;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing   // Timestamp 클래스 내에서 자동으로 auditing을 해준다 : sql 내에서 옵션을 주지 말아야함.
@SpringBootTest
class Project3rdApplicationTests {

	@Test
	void contextLoads() {
	}

}
