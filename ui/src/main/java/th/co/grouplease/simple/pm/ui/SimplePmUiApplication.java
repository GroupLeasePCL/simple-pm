package th.co.grouplease.simple.pm.ui;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import th.co.grouplease.simple.pm.ui.common.RestTemplateResponseErrorHandler;

@SpringBootApplication
public class SimplePmUiApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(SimplePmUiApplication.class, args);
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder
                .errorHandler(new RestTemplateResponseErrorHandler())
                .build();
    }
}
