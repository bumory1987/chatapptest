package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatReactiveWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;


@CrossOrigin("*")
@SpringBootApplication
public class WebsocketApplication {



	@Bean
	TomcatReactiveWebServerFactory tomcatReactiveWebServerFactory(){
		return new TomcatReactiveWebServerFactory();
	}


	public static void main(String[] args) { SpringApplication.run(WebsocketApplication.class, args);
	}

}
