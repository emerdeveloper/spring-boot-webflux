package com.emegonza.reactive.webflux.app.configuration;

import com.emegonza.reactive.webflux.app.utils.InitData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
public class Config {

    @Autowired
    InitData initData;

    @PostConstruct
    public void configInitData() {
        initData.configInitData();
    }

//    @Bean
//    public Docket docket() {
//        return new Docket(DocumentationType.SWAGGER_2)
//                .apiInfo(this.apiInfo())
//                .enable(true)
//                .select()
//                .paths(PathSelectors.any())
//                .build();
//    }
//
//    private ApiInfo apiInfo() {
//        return new ApiInfoBuilder()
//                .title("Reactive Documentation")
//                .description("Reactive API Documentation")
//                .version("1.0.0")
//                .build();
//    }
}
