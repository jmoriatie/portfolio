package com.example.movieshelf.serviceTest;

import com.example.movieshelf.MovieShelfApplication;
import com.example.movieshelf.service.TalkService.TalkService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import static org.assertj.core.api.Assertions.assertThat;

public class ServiceTest {


    @Test
    @DisplayName("빈 등록 확인")
    void findBean(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(MovieShelfApplication.class );

        String[] beans = ac.getBeanDefinitionNames();
        for (String bean : beans) {
            System.out.println("bean = " + bean);
        }

    }



    @Test
    @DisplayName("Service 인터페이스 Bean 싱글톤 등록 테스트 >> TalkService 로 작성")
    void serviceCallTest(){

        ApplicationContext ac = new AnnotationConfigApplicationContext(MovieShelfApplication.class);

        ServiceBean serviceBean = ac.getBean("serviceBean", ServiceBean.class);


        assertThat(serviceBean).isInstanceOf(MovieShelfApplication.class);
//
//        TalkService ObjectProvider(talkService1) = ac.getBean("talkService", TalkService.class);
//        TalkService talkService2 = ac.getBean("talkService", TalkService.class);
//
//        assertThat(talkService1).isSameAs(talkService2);
    }

}
