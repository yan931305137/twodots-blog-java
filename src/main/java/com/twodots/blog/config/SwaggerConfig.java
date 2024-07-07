package com.twodots.blog.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.documentation.builders.*;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

import static io.swagger.models.properties.PropertyBuilder.build;

/**
 * SwaggerConfig
 *  <a href="http://localhost:8081/swagger-ui.html">...</a\
 * @ date 2024/7/5 22:09
 */
@Configuration
@EnableSwagger2
//是否开启swagger，正式环境一般是需要关闭的（避免不必要的漏洞暴露！），可根据springboot的多环境配置进行设置
public class SwaggerConfig {
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
//设置全局参数
                .globalOperationParameters(globalParamBuilder())
//设置全局响应参数
                .globalResponseMessage(RequestMethod.GET,responseBuilder())
                .globalResponseMessage(RequestMethod.POST,responseBuilder())
                .globalResponseMessage(RequestMethod.PUT,responseBuilder())
                .globalResponseMessage(RequestMethod.DELETE,responseBuilder())
                .select()
// 自行修改为自己的包路径
                .apis(RequestHandlerSelectors.basePackage("com.twodots.blog"))
                .paths(PathSelectors.any())
//设置安全认证
                .build()
                .securitySchemes(security());
    }
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("TwoDots-Blog API文档") //页面标题
                .description("API操作文档 版本1.0 ")//描述
//服务条款网址
                .termsOfServiceUrl("http://localhost:8080/")
                .version("1.0") //版本号
                .contact(new Contact("Wsscg", "https://github.com/yan931305137", "931305137@qq.com"))//创建人
                .build();
    }
    // 构建全局参数列表
    private List globalParamBuilder(){
        List pars = new ArrayList<>();
        pars.add(parameterBuilder("token","令牌","string","header",false).build());
        return pars;
    }
    // 创建参数
    private ParameterBuilder parameterBuilder(String name, String desc, String type , String parameterType, boolean required) {
        ParameterBuilder tokenPar = new ParameterBuilder();
        tokenPar.name(name).description(desc).modelRef(new ModelRef(type)).parameterType(parameterType).required(required).build();
        return tokenPar;
    }
    // 安全认证参数
    private List security() {
        List list=new ArrayList<>();
        list.add(new ApiKey("token", "token", "header"));
        return list;
    }
    // 创建全局响应值
    private List responseBuilder() {
        List responseMessageList = new ArrayList<>();
        responseMessageList.add(new ResponseMessageBuilder().code(200).message("响应成功").build());
        responseMessageList.add(new ResponseMessageBuilder().code(500).message("服务器内部错误").build());
        return responseMessageList;
    }
}
