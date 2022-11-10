package com.study.core;

import com.study.core.member.*;
import com.study.core.order.OrderService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.*;

public class AppConfigTest {

    ApplicationContext applicationContext;

    @BeforeEach
    void init() {
        applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
    }

    @Test
    void sameInstance() {
        MemberService memberService1 = applicationContext.getBean("memberService", MemberService.class);
        MemberService memberService2 = applicationContext.getBean("memberService", MemberService.class);

        assertThat(memberService1).isEqualTo(memberService2);

        MemberRepository memberRepository1 = applicationContext.getBean("memberRepository", MemberRepository.class);
        MemberRepository memberRepository2 = applicationContext.getBean("memberRepository", MemberRepository.class);

        assertThat(memberRepository1).isEqualTo(memberRepository2);
    }
}
