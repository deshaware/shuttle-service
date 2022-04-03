package com.deshaware.shuttleservice;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ShuttleServiceApplication {
	final static Logger logger = LogManager.getLogger(ShuttleServiceApplication.class);
	public static void main(String[] args) {
		logger.info("Started Shuttle App");
		SpringApplication.run(ShuttleServiceApplication.class, args);
	}

}
