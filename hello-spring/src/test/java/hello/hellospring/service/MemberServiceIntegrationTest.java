package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
// DB와 연동했음
// Spring이 DB의 연결정보를 알고 있기 때문에
// @SpringBootTest

// 꼭 Spring빌드하는 테스트 하는 것은 아님
// 시간이 오래 걸리기 떄문에
// 순수히 Java 언어로만 하는 '단위 테스트'도 사용함
// 단위 테스트가 훨씬 좋은 테스트일 확률이 있음
// Spring, DB 까지 연동하는 테스트를 '통합 테스트'

@Transactional
// Test가 끝나면 롤백해줌
// Test하려고 DB에 넣었던 데이터가 깔끔히 지워짐

class MemberServiceIntegrationTest {

    // 이전에는 직접 객체 생성해서 넣었는데
    // Spring Contrainer에서 MemberRepository,MemberService
    // 가져와야함

    @Autowired MemberService memberService;

    // MemoryMemberRepository -> MemberRepository
    @Autowired MemberRepository memberRepository;

    //    @BeforeEach
//    public void beforeEach(){
//
//        memberRepository = new MemoryMemberRepository();
//        memberService = new MemberService(memberRepository);
//
//    }
    
      // 필요없어짐
//    @AfterEach
//    public void afterEach(){
//        memberRepository.clearStore();
//    }

    @Test
    void 회원가입() {

        Member member1 = new Member();
        
        // DB상에 같은 "" Name 있으면 
        // 같은 이름의 회원가입 안됌
        // 만약 있다면 "delete from member" sql문으로 삭제 후 테스트
        // 보통 test 전용 DB에 연결해서 돌림
        // 테스트하면 MemoryDB 테스트와는 다르게 SpirngConfig 전부 올라오고, JAVA 빌드됌

        member1.setName("spring1");

        // 한번 실행시 "spring1"이라는 Name의 멤버가 DB상에 생김
        // 두번 실행시 이미 DB에 저장됐기 떄문에 Test failed
        // Test 할 때마다 지워주어야 하나?
        // => @Transational

        // @Trasational 하면
        // Test하면서 회원가입 만들고 테스트 성공 후 DB 롤백해줌
        // 따로 DB 비워주지 않아도 Test만 계속 반복할 수 있음

        Long saveId = memberService.join(member1);

        Member findMember = memberService.findOne(saveId).get();
        Assertions.assertThat(member1.getName()).isEqualTo(findMember.getName());
    }

    @Test
    void 중복_회원가입_예외() {

        Member member1 = new Member();
        member1.setName("중복회원_정민");
        Member member2 = new Member();
        member2.setName("중복회원_정민");

        memberService.join(member1);

        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));// 같은 이름의 예외 : test 성공
        Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}