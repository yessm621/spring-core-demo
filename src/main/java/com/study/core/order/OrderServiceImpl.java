package com.study.core.order;

import com.study.core.discount.DiscountPolicy;
import com.study.core.discount.FixDiscountPolicy;
import com.study.core.member.Member;
import com.study.core.member.MemberRepository;
import com.study.core.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService {

    private final MemberRepository memberRepository = new MemoryMemberRepository();
    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member findMember = memberRepository.findById(memberId);
        // 회원이 VIP 라면 1000원 할인
        int discountPrice = discountPolicy.discount(findMember, itemPrice);

        // Order 객체를 반환
        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}
