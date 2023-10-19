package com.brawl.mms.discount;

import com.brawl.mms.domain.Grade;
import com.brawl.mms.domain.Member;

public class FixDiscountPolicy implements DiscountPolicy{

    private final int discountPrice = 1000;

    @Override
    public int discount(Member member, int price) {
        if(member.getGrade() == Grade.VIP){
            return discountPrice;
        }
        return 0;
    }
}
