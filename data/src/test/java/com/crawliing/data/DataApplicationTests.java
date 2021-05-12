package com.crawliing.data;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import com.crawliing.data.home.service.HomeService;

import org.junit.jupiter.api.Test;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

import ch.qos.logback.classic.Logger;
import io.sentry.Sentry;

@SpringBootTest
class DataApplicationTests {

	private final static Logger log = (Logger) LoggerFactory.getLogger(DataApplicationTests.class);

	@Resource(name="homeService")
	private HomeService homeService;

	@Test
	void contextLoads() {



	}	

}
