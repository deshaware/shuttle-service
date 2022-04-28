package com.deshaware.shuttleservice;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.deshaware.shuttleservice.controller.AuthController;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ShuttleServiceApplicationTests {

	@Autowired
	private AuthController controller;

	@Test
	void contextLoads() {
		System.out.println(controller);
		assertNotNull(controller);
	}

}
