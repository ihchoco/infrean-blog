package com.brawl.mms.service;

import com.brawl.mms.domain.Grade;
import com.brawl.mms.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    MemberService memberService = new MemberService();

    @Test
    @DisplayName("멤버_저장테스트")
    void test_멤버저장테스트_서비스(){
        //given
        Member member = Member.builder()
                .id(1L)
                .name("홍길동")
                .grade(Grade.BASIC).build();


        //when
        memberService.saveMember(member);
//        memberService.saveMember(member2);

        Member findMember = memberService.findMemberById(1L);

        //then
        Assertions.assertThat(findMember.getName()).isEqualTo("홍길동");
    }

}