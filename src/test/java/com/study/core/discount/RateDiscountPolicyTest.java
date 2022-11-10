package com.study.core.discount;

import com.study.core.member.Grade;
import com.study.core.member.Member;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RateDiscountPolicyTest {

    DiscountPolicy discountPolicy = new RateDiscountPolicy();

    @Test
    @DisplayName("VIP는 10% 할인이 적용되어야 한다.")
    void vip_o() {
        // given
        Member member = new Member(1L, "memberVIP", Grade.VIP);
        // when
        int discountPrice = discountPolicy.discount(member, 8000);
        // then
        assertThat(discountPrice).isEqualTo(800);
    }

    @Test
    @DisplayName("VIP가 아니면 할인이 적용되지 않아야 한다.")
    void vip_x() {
        // given
        Member member = new Member(1L, "memberBASIC", Grade.BASIC);
        // when
        int discountPrice = discountPolicy.discount(member, 8000);
        //then
        assertThat(discountPrice).isEqualTo(0);
    }
}