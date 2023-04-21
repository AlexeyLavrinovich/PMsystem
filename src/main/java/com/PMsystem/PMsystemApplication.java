package com.PMsystem;

import com.PMsystem.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@AllArgsConstructor
@SpringBootApplication
public class PMsystemApplication implements ApplicationRunner {

	private UserService userService;

	public static void main(String[] args) {
		SpringApplication.run(PMsystemApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		userService.updateEncode();
	}
}
