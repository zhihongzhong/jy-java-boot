package com.example.common.config.swagger;

import com.example.common.config.jwt.JwtConfigProperty;
import com.github.xiaoymin.swaggerbootstrapui.annotations.EnableSwaggerBootstrapUI;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.Contact;
import springfox.documentation.service.SecurityScheme;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableConfigurationProperties(JwtConfigProperty.class)
@EnableSwagger2
@EnableSwaggerBootstrapUI
public class Swagger2Configuration {

  private final static String BASIC_PATH = "/";
  private final String header;

  public Swagger2Configuration(JwtConfigProperty property) {
    this.header = property.getBearer();
  }


  @Bean
  public Docket createApi() {
    Docket docket = new Docket(DocumentationType.SWAGGER_2)
      .enable(true)
      .apiInfo(apiInfo())
      .securitySchemes(createSecuritySchemeList())
      .groupName("system")
      .select()
      .apis(RequestHandlerSelectors.any())
      .paths(PathSelectors.any())
      .build();

    return docket;
  }


  private ApiInfo apiInfo() {
    return new ApiInfoBuilder()
      .title("测试文档")
      .description("多个模块测试文档")
      .version("1.0")
      .contact(new Contact("ZzH", "https://baidu.com", "qingtian88@live.com"))
      .build();
  }

  private List<SecurityScheme> createSecuritySchemeList() {
    List<SecurityScheme> schemeList = new ArrayList<>();
    schemeList.add(new ApiKey(header, "access_token授权值", "header"));
    return schemeList;
  }
}
