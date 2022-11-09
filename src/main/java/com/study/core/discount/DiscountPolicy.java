package com.study.core.discount;

import com.study.core.member.Member;

public interface DiscountPolicy {
    int discount(Member member, int price);
}
