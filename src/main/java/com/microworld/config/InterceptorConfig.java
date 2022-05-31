package com.microworld.config;

import com.microworld.common.interceptor.Authentication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.config.annotation.*;

/**
 * 拦截器配置
 * @author birdbro
 * @date 11:15 2022-4-28
 **/
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authenticationInterceptor())
                // 拦截所有请求，通过判断是否有 @LoginRequired 注解 决定是否需要登录
                .addPathPatterns("/api/**");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/swagger-resources/*").addResourceLocations("classpath:/META-INF/swagger-resources/");
        registry.addResourceHandler("/v2/api-docs/**").addResourceLocations("classpath:/META-INF/resources/v2/api-docs/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
    }
    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        //这里一定要设置为true，才能覆盖默认的xml format
        configurer.ignoreAcceptHeader(true).defaultContentType(MediaType.APPLICATION_JSON);
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                /**
                 * 当allowCredentials为真时，allowedorigin不能包含特殊值"*"，因为不能在"访问-控制-起源“响应头中设置该值。
                 * 要允许凭证到一组起源，显示地列出它们，或者考虑使用"allowedOriginPatterns”代替"allowedOrigins("*")。
                 **/
                .allowedOriginPatterns("*")
                //是否允许证书
                .allowCredentials(true)
                .allowedMethods("GET", "POST", "DELETE", "PUT", "HEAD", "OPTIONS")
                //允许跨域时间
                .maxAge(3600 * 24)
                .allowedHeaders("*");
    }

    @Bean
    public Authentication authenticationInterceptor() {
        return new Authentication();
    }


}
