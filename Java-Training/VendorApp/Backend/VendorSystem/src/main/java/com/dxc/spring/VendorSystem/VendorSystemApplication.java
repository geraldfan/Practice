package com.dxc.spring.VendorSystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class VendorSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(VendorSystemApplication.class, args);
		System.out.println("Connected");
	}

}
