package com.brawl.mms.repository;

import com.brawl.mms.domain.Grade;
import com.brawl.mms.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class MemoryMemberRepositoryTest {

    MemoryMemberRepository memberRepository = new MemoryMemberRepository();
    /*
    @Test
    @DisplayName("Member 저장 테스트")
    void test_멤버저장(){
        //given
        Member member = Member.builder()
                .id(1L)
                .name("홍길동")
                .grade(Grade.BASIC).build();

        //when
        memberRepository.save(member);
        Member findMember = memberRepository.findById(1L).get();


        //then
        System.out.println(findMember);
        Assertions.assertThat(findMember.getName()).isEqualTo("홀길동");
    }
     */

}