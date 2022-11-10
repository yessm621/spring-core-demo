package com.study.core.member;

public class MemberServiceImpl implements MemberService {

    // 의존관계가 인터페이스 뿐만 아니라 구현체까지 의존하고 있어 OCP, DIP 원칙을 지키지 못하고 있다.
    // OCP: 개방-폐쇄 원칙, 소프트웨어 요소는 확장에서는 열려있고 변경에는 닫혀있어야 함. (다형성을 활용)
    // DIP: 의존관계 역전 원칙, 구현체에 의존하지 말고 인터페이스에 의존해야 함.
//    private final MemberRepository memberRepository = new MemoryMemberRepository();
    private final MemberRepository memberRepository;

    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
