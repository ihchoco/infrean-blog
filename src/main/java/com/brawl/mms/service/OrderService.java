package com.brawl.mms.service;

import com.brawl.mms.discount.DiscountPolicy;
import com.brawl.mms.discount.FixDiscountPolicy;
import com.brawl.mms.discount.RateDiscountPolicy;
import com.brawl.mms.domain.Member;
import com.brawl.mms.domain.Order;
import com.brawl.mms.repository.MemberRepository;
import com.brawl.mms.repository.MemoryMemberRepository;

import java.util.Optional;

public class OrderService {
    private final MemberRepository memberRepository = new MemoryMemberRepository();
//    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
    private final DiscountPolicy discountPolicy = new RateDiscountPolicy();


    public Order createOrder(Long memberId, String itemName, int itemPrice){
        Member member = memberRepository.findById(memberId).get();
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return Order.builder()
                .member(member)
                .itemName(itemName)
                .price(itemPrice)
                .discountPrice(discountPrice)
                .build();
    }

}
