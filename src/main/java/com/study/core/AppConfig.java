package com.study.core;

import com.study.core.discount.DiscountPolicy;
import com.study.core.discount.FixDiscountPolicy;
import com.study.core.member.MemberRepository;
import com.study.core.member.MemberService;
import com.study.core.member.MemberServiceImpl;
import com.study.core.member.MemoryMemberRepository;
import com.study.core.order.OrderService;
import com.study.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 리팩토링을 통해 얻은 점
 * 1. 역할과 구현클래스가 명확해짐.
 * 2. 중복을 제거하여 변경 시 한 부분만 변경하면 됨.
 */
@Configuration
public class AppConfig {

    @Bean
    public MemberService memberService() {
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public OrderService orderService() {
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    @Bean
    public DiscountPolicy discountPolicy() {
        return new FixDiscountPolicy();
    }
}
