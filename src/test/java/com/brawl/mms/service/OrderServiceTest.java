package com.brawl.mms.service;

import com.brawl.mms.domain.Grade;
import com.brawl.mms.domain.Member;
import com.brawl.mms.domain.Order;
import com.brawl.mms.repository.MemberRepository;
import com.brawl.mms.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrderServiceTest {

    MemberRepository memberRepository = new MemoryMemberRepository();
    OrderService orderService = new OrderService();

    @Test
    @DisplayName("주문 생성 테스트")
    void Order_주문테스트(){
        //given
        Member member = new Member(1L, "홍길동", Grade.VIP);
        String itemName = "만년필";
        int itemPrice = 10000;

        //when
        memberRepository.save(member);
        Order order = orderService.createOrder(member.getId(), itemName, itemPrice);

        //then
        Assertions.assertThat(order.getItemName()).isEqualTo("만년필");
        Assertions.assertThat(order.getDiscountPrice()).isEqualTo(5000);
        Assertions.assertThat(order.afterDiscountPrice()).isEqualTo(5000);



    }

}