package com.example.board.config;

import com.example.board.filter.LogFilter;
import com.example.board.filter.LoginCheckFilter;
import com.example.board.interceptor.LogInceptor;
import com.example.board.interceptor.LoginCheckInterceptor;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.Filter;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    private String[] excludePaths = {"/", "/member/join", "/member/login", "/member/logout", "/css/**", "/*.ico", "/error"};

//    @Bean
    public FilterRegistrationBean logFilter() {
        FilterRegistrationBean<Filter> filterFilterRegistrationBean = new FilterRegistrationBean<>();
        // 사용할 필터를 지정한다.
        filterFilterRegistrationBean.setFilter(new LogFilter());
        // 필터의 순서. 낮을수록 먼저 동작한다.
        filterFilterRegistrationBean.setOrder(1);
        // 필터를 적용할 URL 패턴을 지정한다. 한번에 여러 패턴을 지정할 수 있다.
        filterFilterRegistrationBean.addUrlPatterns("/*");

        return filterFilterRegistrationBean;
    }

//    @Bean
    public FilterRegistrationBean loginCheckFilter() {
        FilterRegistrationBean<Filter> filterFilterRegistrationBean = new FilterRegistrationBean<>();
        filterFilterRegistrationBean.setFilter(new LoginCheckFilter());
        filterFilterRegistrationBean.setOrder(2);
        filterFilterRegistrationBean.addUrlPatterns("/*");
        return filterFilterRegistrationBean;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 인터셉터를 등록한다.
//        registry.addInterceptor(new LogInceptor())
//                // 인터셉터의 호출 순서를 지정. 낮을수록 먼저 호출된다.
//                .order(1)
//                // 인터셉터를 적용할 URL 패턴을 지정
//                .addPathPatterns("/**")
//                // 인터셉터에서 제외할 패턴을 지정
//                .excludePathPatterns("/css/**", "/*.ico", "/error");

        registry.addInterceptor(new LoginCheckInterceptor())
                .order(2)
                .addPathPatterns("/**")
                .excludePathPatterns(excludePaths);
    }
}
