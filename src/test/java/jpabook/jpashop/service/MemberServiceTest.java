package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional      // test 클래스에 있으면 기본적으로 롤백을 함.
class MemberServiceTest {

    @Autowired
    MemberService memberService;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    EntityManager em;

    @Test
//    @Rollback(value = false)  // DB 에 내용 반영
    public void 회원가입() throws Exception {
        // given
        Member member = new Member();
        member.setName("choi");

        // when
        Long savedId = memberService.join(member);

        // then
//        em.flush();     // DB 에 내용 반영
        assertEquals(member, memberRepository.findOne(savedId));
    }

    @Test
    public void 중복_회원_예외() throws Exception {
        // given
        Member member1 = new Member();
        member1.setName("choi1");

        Member member2 = new Member();
        member2.setName("choi1");

        // when
        memberService.join(member1);
        Assertions.assertThrows(IllegalStateException.class, () -> {
            memberService.join(member2);    // 예외발생
        });

        // then
//        fail("예외가 발생해야 한다.");
    }
}