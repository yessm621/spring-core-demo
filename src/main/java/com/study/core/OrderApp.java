package com.study.core;

import com.study.core.member.Grade;
import com.study.core.member.Member;
import com.study.core.member.MemberService;
import com.study.core.order.Order;
import com.study.core.order.OrderService;


public class OrderApp {

    /**
     * Order 객체를 출력하여 할인 금액을 확인 할 수 있다.
     * 하지만 테스트 코드를 이렇게 작성하는 것은 좋지 않다.
     * 이를 개선하기 위해 OrderServiceTest 에 작성
     */
    public static void main(String[] args) {
        AppConfig appConfig = new AppConfig();
        MemberService memberService = appConfig.memberService();
        OrderService orderService = appConfig.orderService();

        long memberId = 1L;
        Member member = new Member(memberId, "memberA", Grade.VIP);
        memberService.join(member);

        Order order = orderService.createOrder(memberId, "itemA", 10000);
        System.out.println("order = " + order);
    }
}
