package com.example;

import com.github.xiaoymin.swaggerbootstrapui.annotations.EnableSwaggerBootstrapUI;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Hello world!
 *
 */

@SpringBootApplication
@EnableSwagger2
@EnableSwaggerBootstrapUI
public class SystemApplication
{
    public static void main( String[] args ) {
        SpringApplication.run(SystemApplication.class, args);
    }
}
