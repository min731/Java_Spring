package hello.hellospring.service;

//MemberController의 @Controller, 생성자의 @Autowired만 두고
// 빌드 안될때 , 빈 설정파일 직접 만들기

import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

    @Bean
    public MemberService memberService(){
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository(){
        return new MemoryMemberRepository();
        // 인터페이스는 new가 안됌

        // 차후 메모리리포지토리에서 DB리포지토리로 바꾸고 싶을 때
        // @Bean에서 DBMemberRepository로 바꿔주면 됌(다른 코드 수정X)
        // Component와 다르게 설정파일만 손대면 됌
    }

}
// 이렇게 하면 전부 연결됌

// 1. 스캔하는 방법
// 2. Bean 파일 만드는 방법
// 3. xml로 하는방법 (실무에서 거의 X)
// 장단점이 있음

// DI 주입
// 1. 생성자 주입 (*권장)
// 2. 필드로 주입 (필드에 @Autowired)
// 3. Setter 주입 (연결해야하는 클래스 'set클래스' 메서드 만들어주고 @ Autowired)
//    public으로 만들어야하고, 나중에 pulbic이 문제가 생길 때가 있음

