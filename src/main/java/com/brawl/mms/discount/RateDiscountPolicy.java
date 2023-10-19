package com.brawl.mms.discount;

import com.brawl.mms.domain.Grade;
import com.brawl.mms.domain.Member;

public class RateDiscountPolicy implements DiscountPolicy{

    private final int discountRate = 50;

    @Override
    public int discount(Member member, int price) {
        if(member.getGrade() == Grade.VIP){
            return price * discountRate / 100;
        }
        return 0;
    }
}
