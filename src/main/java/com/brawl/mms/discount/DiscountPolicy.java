package com.brawl.mms.discount;

import com.brawl.mms.domain.Member;

public interface DiscountPolicy {
    int discount(Member member, int price);
}
