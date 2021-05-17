package com.hr.auth;

import javax.annotation.Resource;

import com.hr.auth.home.module.HomeModule;
import com.hr.auth.home.service.HomeService;

import org.junit.jupiter.api.Test;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

import ch.qos.logback.classic.Logger;

@SpringBootTest
class DataApplicationTests {

	private final static Logger log = (Logger) LoggerFactory.getLogger(DataApplicationTests.class);

	@Resource(name="homeService")
	private HomeService homeService;
	@Resource(name="homeModule")
	private HomeModule homeModulel;
	@Test
	void contextLoads() {
		System.out.println(homeModulel.getSHA512("wjsguswls71@naver.com"));
	}	

}
