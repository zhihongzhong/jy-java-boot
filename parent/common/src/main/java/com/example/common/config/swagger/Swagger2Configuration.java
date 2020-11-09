package com.example.common.config.swagger;

import com.example.common.config.jwt.JwtConfigProperty;
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

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableConfigurationProperties(JwtConfigProperty.class)
public class Swagger2Configuration {

  private final static String BASIC_PATH = "/";
  private final String header;

  public Swagger2Configuration(JwtConfigProperty property) {
    this.header = property.getBearer();
  }
  @Bean
  public Docket createApi() {
    Docket docket = new Docket(DocumentationType.SWAGGER_2)
      .enable(false)
      .apiInfo(apiInfo())
      .securitySchemes(createSecuritySchemeList())
      .pathMapping(BASIC_PATH)
      .select()
      .apis(RequestHandlerSelectors.basePackage("com.example"))
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
