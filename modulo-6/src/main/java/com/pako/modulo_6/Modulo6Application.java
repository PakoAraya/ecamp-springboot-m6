package com.pako.modulo_6;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class Modulo6Application {

	public static void main(String[] args) {

		SpringApplication.run(Modulo6Application.class, args);
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String rawPassword = "admin123";
		String encodedPassword = encoder.encode(rawPassword);
		System.out.println("Contraseña encriptada: " + encodedPassword);
	}

}
