// 스프링부트 국제화기능 : 로케일 설정(리졸버), 파라미터 설정(인터셉터)

package com.spring.project3rd.config;



import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import java.util.Locale;

@Configuration // 이 부분 추가되었음
public class WebMvcConfig implements WebMvcConfigurer {

    // Springboot에서 이미 bean으로 등록되어 있음
    /*@Bean
    public MessageSource messageSource(){
        ResourceBundleMessageSource messageSource= new ResourceBundleMessageSource();
        messageSource.setDefaultEncoding("UTF-8");
        messageSource.setBasename("message");

        return messageSource;
    }*/

    // CORS에 대한 설정, CORS는 Cross Origin Resource Sharing의 약자
    // 프론트엔드, 백엔드 개발
    // 프론트엔드는 3000번 포트(React.js), 백엔드는 8080번 포트
    // http://localhost:3000 ---> 8080 api를 호출할 수 있도록 설정.

    @Bean
    public LocaleResolver localeResolver(){
        SessionLocaleResolver slr=new SessionLocaleResolver();
        slr.setDefaultLocale(Locale.US); // 1차 수정
        slr.setLocaleAttributeName("session.current.locale");
//        slr.setTimeZoneAttributeName("session.current.timezone");
        return slr;
    }

    // 2023.08.18 주석처리
    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor(){
        LocaleChangeInterceptor localeChangeInterceptor=new LocaleChangeInterceptor();
        localeChangeInterceptor.setParamName("lang");
        return localeChangeInterceptor;
    }

    @Override // 인터셉터에 추가
    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(localeChangeInterceptor());
    }

}


