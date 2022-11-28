package com.study.core.order;

import com.study.core.annotation.MainDiscountPolicy;
import com.study.core.discount.DiscountPolicy;
import com.study.core.member.Member;
import com.study.core.member.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderServiceImpl implements OrderService {

    private final MemberRepository memberRepository;
    /**
     * 새로운 정책으로 변경하기 위해 구현체를 변경해야 함.
     * 역할과 구현을 분리했음. 즉, 다형성을 활용하고 인터페이스와 구현 객체를 분리했음 (interface, **Impl)
     * OCP, DIP 원칙을 준수한 것처럼 보이지만 그렇지 않음.
     */
//    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
//    private final DiscountPolicy discountPolicy = new RateDiscountPolicy();
    private final DiscountPolicy discountPolicy;

    @Autowired
    public OrderServiceImpl(MemberRepository memberRepository, @MainDiscountPolicy DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member findMember = memberRepository.findById(memberId);
        // 회원이 VIP 라면 1000원 할인
        int discountPrice = discountPolicy.discount(findMember, itemPrice);

        // Order 객체를 반환
        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}
