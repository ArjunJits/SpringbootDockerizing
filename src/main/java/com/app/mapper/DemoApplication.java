package com.app.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

import com.app.mapper.config.AppConfig;


@SpringBootApplication
@EnableConfigurationProperties(AppConfig.class)
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
	@Component
	class ConfigData implements CommandLineRunner{
	    @Autowired
		private AppConfig appConfig;
		@Override
		public void run(String... args) throws Exception {
			System.out.println(" appconfig properties "+appConfig.toString());
			
		}
		
	}
}


