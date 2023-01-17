package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

// 클래스 전부 만들고 ctrl + shift + T 하면 Test 자동으로 만들어줌!

//@Service // @Controller로 하나의 빈 생성한 컨트롤러와 연결
// 원래는 @Component 로 했음
// @Component 는 @Service,@Controller 등 포함함
public class MemberService {

    // Service 패키지는 회원가입,전체 회원 조회 등 비지니스와 관련된 것들임
    // 회원 서비스를 만드려면 멤버 repository가 있어야함

    // * 주의
    // private final MemberRepository memberRepository = new MemoryMemberRepository();
    // main과 test를 같은 memberRepository로 설정하기 위해
    // 다음과 같이 생성자를 만들어줌
    private final MemberRepository memberRepository;
    // 생성자 만드는 단축키
    // alt + insert
    // 외부에서 파라미터로 넣어주게 바꿈

    //@Autowired
    // MemberService는 MemberRepository가 필요하기 때문에
    // 스프링 컨테이너에 MemberRepository 를 넣어줌
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }


    
    // 회원가입
    public Long join(Member member){

        // 1번 방법
        // 같은 이름이 있는 중복 회원이 있으면 안된다고 가정

        // 1) ifPresent : result 값이 있으면~
        // 2) 다음 메서드 사용하기도 함
        //    result.orElseGet : 값이 있으면 꺼내고 없으면 메서드를 실행~

//        Optional<Member> result = memberRepository.findByName(member.getName());
//        result.ifPresent(m -> {
//            throw new IllegalStateException("이미 존재하는 회원입니다.");
//        });


        // 2번 방법 (한번에 쓸 수도 있음)
        // shif+crtl+alt+t 눌러서 Refactor this 해서 메서드화 시켜도됌

        validateDuplicateMember(member);

        memberRepository.save(member);
        return member.getId(); // Id만 반환해주기로 결정
                               // 같은 이름이 있는 회원이 안된다고 가정

    }

    // Extract Method 해서
    // 중복회원 검증 메서드화 시켰음
    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                       .ifPresent(m -> {
                           throw new IllegalStateException("이미 존재하는 회원입니다.");
                       });
    }

    // 전체 회원 조회
    public List<Member> findMembers(){
        return memberRepository.finALl();

    }

    // Id로 회원 찾기
    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }

}
