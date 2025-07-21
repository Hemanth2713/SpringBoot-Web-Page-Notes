package com.Hemanth.myfirstproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//@SpringBootApplication --> This is an annotation. Annotations are applied to classes, methods, interfaces, or fields.
//Annotations provide metadata or additional information about the class, method, interface, or field they are applied to.

@SpringBootApplication
/*
@SpringBootApplication is a combination of three annotations:
 * 1. @Configuration: Marks this class as a source of bean definitions for the application context.
 * 2. @EnableAutoConfiguration: Tells Spring Boot to automatically configure your application based on the dependencies you have in your classpath.
 * 3. @ComponentScan: Tells Spring to scan for other components, configurations, and services in the same package or sub-packages.
 *
 * In a Spring Boot application, you typically use only one @SpringBootApplication annotation, which is applied to the main class.
*/


public class MyFirstProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyFirstProjectApplication.class, args);
	}

}
