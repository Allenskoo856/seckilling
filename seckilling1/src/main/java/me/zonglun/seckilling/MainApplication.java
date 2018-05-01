package me.zonglun.seckilling;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

/**
 * @author : Administrator
 * @create 2018-04-19 4:58
 */
@SpringBootApplication
public class MainApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(MainApplication.class);
    }
}
