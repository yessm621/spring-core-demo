package com.study.core.singleton;

import com.study.core.AppConfig;
import com.study.core.member.Grade;
import com.study.core.member.Member;
import com.study.core.member.MemberRepository;
import com.study.core.member.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.*;

public class SingletonTest {

    @Test
    @DisplayName("스프링 없는 순수한 DI 컨테이너")
    void pureContainer() {
        AppConfig appConfig = new AppConfig();

        MemberService memberService1 = appConfig.memberService();
        MemberService memberService2 = appConfig.memberService();

        System.out.println("memberService1 = " + memberService1);
        System.out.println("memberService2 = " + memberService2);

        assertThat(memberService1).isNotEqualTo(memberService2);
        assertThat(memberService1).isNotSameAs(memberService2);
    }

    @Test
    @DisplayName("싱글톤 패턴을 적용한 객체 사용")
    public void singletonServiceTest() {
        SingletonService singletonService1 = SingletonService.getInstance();
        SingletonService singletonService2 = SingletonService.getInstance();

        System.out.println("singletonService1 = " + singletonService1);
        System.out.println("singletonService2 = " + singletonService2);

        // 진짜 인스턴스와 같은지 비교하기 위해 isSameAs 사용
        assertThat(singletonService1).isSameAs(singletonService2);
    }

    /**
     * AppConfig에서 getInstance()를 사용하면 DIP를 위반하지 않음.
     * DIP의 핵심은 클라이언트 코드가 구체 코드에 의존하지 않고 변경할 수 있으면 됨.
     */
    @Test
    @DisplayName("싱글톤 패턴을 적용, DIP 원칙도 지킴")
    public void singletonTest() {
        AppConfig appConfig = new AppConfig();

        MemberRepository memberRepository1 = appConfig.memberRepository();
        MemberRepository memberRepository2 = appConfig.memberRepository();

        System.out.println("memberRepository1 = " + memberRepository1);
        System.out.println("memberRepository2 = " + memberRepository2);
        assertThat(memberRepository1).isSameAs(memberRepository2);
    }

    @Test
    @DisplayName("스프링 컨테이너와 싱글톤")
    void springContainer() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberService memberService1 = ac.getBean("memberService", MemberService.class);
        MemberService memberService2 = ac.getBean("memberService", MemberService.class);

        System.out.println("memberService1 = " + memberService1);
        System.out.println("memberService2 = " + memberService2);

        assertThat(memberService1).isSameAs(memberService2);
    }
}
