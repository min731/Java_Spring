package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

// Create new Test 로 껍데기만 만들어줌

class MemberServiceTest {

    MemberService memberService;

    // *주의
    // Test 마다 clear() 해주기 위해
    // MemberRepository 객체 생성해서 clearStore() 메서드 가져옴
    // MemoryMemberRepository memberRepository = new MemoryMemberRepository();

    // * 주의
    // main 과 test의 memberRepository가 각각 다른 인스턴스이기 때문에
    // 통합해줄 필요가 있다.

    MemoryMemberRepository memberRepository;


    // Test는 독립적으로 실행되야 되기 때문에
    // 실행전 각각 실행해줌
    @BeforeEach
    public void beforeEach(){

        memberRepository = new MemoryMemberRepository(); 
        // memberRepository를 먼저 만들고 MemberService에 넣어줌
        //
        memberService = new MemberService(memberRepository);

    }

    @AfterEach
    public void afterEach(){
        memberRepository.clearStore();
        // ctrl + R 누르면 이전에 실행했던 코드 다시 실행해줌
    }

    // Test는 메소드명 한글로 해도됌
    // build 될 때 테스트 코드는 실제 코드에 포함되지 않음
    @Test
    void 회원가입() {
        // 팁! (머리,가슴,배처럼 나누기)
        // given (뭔가를 실행했을 때)
        Member member1 = new Member();
        member1.setName("회원가입_정민");

        // when (어떤일이 일어나면)
        Long saveId = memberService.join(member1);

        // then (어떤 것을 실행하자)
        // Optional<Member> findMember = memberService.findOne(saveId);
        // get()으로 Optional 꺼내서 받기
        Member findMember = memberService.findOne(saveId).get();
            // assertj 쓸것
        // alt + enter로 statc import 해도 됌
        Assertions.assertThat(member1.getName()).isEqualTo(findMember.getName());
    }

    // 회원가입()은 잘 되지만 중복인지(예외까지) 체크해야함
    @Test
    void 중복_회원가입_예외() {

        // given
        Member member1 = new Member();
        member1.setName("중복회원_정민");
        Member member2 = new Member();
        member2.setName("중복회원_정민");
        // when

        memberService.join(member1);

        // 방법1
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));// 같은 이름의 예외 : test 성공
        Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        // NullPointerException e = assertThrows(NullPointerException.class, () -> memberService.join(member2));// 다른 이름의 예외 : test 실패
        // Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

        // 방법2
//        try {
//            memberService.join(member2); // 이름이 똑같아서 throw new로 예외를 터트려야함
//            fail();
//        }catch (IllegalStateException e){
//            Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
//            // 예외 출력문이 달라 fail
//            // Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.22222");
//
//        }

        // * 주의
        // 회원가입() test할 때 "회원가입_정민" 만들었음으로
        // "회원가입_정민" 는 이미 메모리에 쌓인 상태임
        // 중복 회원 점검할 때 이를 피해서 중복 회원가입하자. or clear() 해주자!



        // then
    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}