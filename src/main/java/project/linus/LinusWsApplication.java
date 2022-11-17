package project.linus;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.config.annotation.CorsRegistration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import project.linus.model.content.Content;
import project.linus.model.user.User;
import project.linus.repository.content.ContentRepository;
import project.linus.service.content.ContentService;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class LinusWsApplication {

	@Autowired
	private Environment env;

	public static void main(String[] args) {
		SpringApplication.run(LinusWsApplication.class, args);
	}
	@Bean
	public WebMvcConfigurer corsConfigurer() {
			return new WebMvcConfigurerAdapter() {
				@Override
				public void addCorsMappings(CorsRegistry registry) {
					String urls = env.getProperty("cors.urls");
					CorsRegistration reg = registry.addMapping("/**");
					for (String url : urls.split(",")) {
						reg.allowedOrigins(url);
					}
				}
			};

		}
	}

