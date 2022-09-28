package project.linus.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import project.linus.util.encoder.PasswordEncoder;

@Configuration
public class PasswordEncoderConfig {
    @Bean
    PasswordEncoder passwordEncoder(){
        return new PasswordEncoder();
    }
}
