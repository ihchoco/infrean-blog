package com.brawl.mms.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Order {
    private Member member;
    private String itemName;
    private int price;
    private int discountPrice;

    @Builder
    public Order(Member member, String itemName, int price, int discountPrice) {
        this.member = member;
        this.itemName = itemName;
        this.price = price;
        this.discountPrice = discountPrice;
    }

    //할인 이후 금액 계산
    public int afterDiscountPrice(){
        return price - discountPrice;
    }
}
