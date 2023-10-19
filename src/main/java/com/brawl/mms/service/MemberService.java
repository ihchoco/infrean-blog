package com.brawl.mms.service;

import com.brawl.mms.domain.Member;
import com.brawl.mms.repository.MemberRepository;
import com.brawl.mms.repository.MemoryMemberRepository;

public class MemberService {
    MemberRepository memberRepository = new MemoryMemberRepository();

    public void saveMember(Member member){
        validationDuplicateMember(member);
        memberRepository.save(member);
    }

    public Member findMemberById(Long id){
        return memberRepository.findById(id).get();
    }

    public void validationDuplicateMember(Member member){
        memberRepository.findByName(member.getName()).ifPresent(x -> {
            throw new IllegalArgumentException("중복 이름을 가진 회원은 가입이 불가능 합니다");
        });
    }

}
