package com.telusko.config;

import com.telusko.Alien;
import com.telusko.Laptop;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.telusko.Desktop;
import org.springframework.context.annotation.Scope;

@Configuration
public class AppConfig {

	/*
	@Bean(name = "comdesk")
	The default name of the bean is our method name.
	Not only one name we can give multiple names(stores in the form of array) for the single bean i.e
	@Bean(name = "comdesk","desk","com")
	If we use a different name which was not mentioned int the array then we will get an error.*/


	@Bean
	@Scope("prototype")
	//We know that by default every bean will be singleton.
	//To change the scope we have to specify the scope as prototype.
	public Desktop desktop() {
		return new Desktop();
	}

	@Bean
	public Laptop laptop(){
		return  new Laptop();
	}

}
