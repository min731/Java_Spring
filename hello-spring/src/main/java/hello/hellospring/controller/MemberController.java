package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

// 자바 컨테이너에 빈 등록하는 방법
// 1) Component를 스캔
// MemberController(@Controller), 생성자의 Autowired
// Autowired한 클래스 MemberService(@Service)
// Autowired한 클래스 memoryMemberRepository(@Repository)
// 2) 자바 코드로 직접 빈 등록
// * 둘 다 알아야함



// MemberController가 MemberService를 통해 회원가입,데이터 조회
// 의존성 주입

// @Controller 해놓을 시,
// 스프링 컨테이너라는 통이 @Controller 객체를 생성해서 관리함
// (스프링 컨테이너가 스프링 빈을 관리한다.)

// 스프링 컨테이너에 빈을 등록할 때 싱글톤으로 등록한다. (유일하게 하나만 등록해서 공유한다.)
// ex) HelloController 1개, MemberService 1개, MemberRepository 1개씩만 등록한다.

@Controller
public class MemberController {

    // new 로 생성해서 쓸 수는 있지만
    // 하나만 생성해서 여러 곳에 쓰면 됌
    // 스프링 컨테이너에 하나의 빈만 등록해서 쓰면 됌
    private final MemberService memberService;

    // 생성자 단축키: Alt + Insert

    @Autowired // 생성자에 Autowired 라고 되어 있으면 (연관관계를 맺어줌)
               // MemberController가 생성이 될 때, 스프링 빈에 등록되어있는 MemberService 객체를 가져다가 넣어줌(DI)

               // 스프링이 컨테이너에 있는 MemberService를 연결해줌
               // 연결해줄 클래스에도 @Service 어노테이션 해주어가 빌드시 에러 안남
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/members/new")
    public String createForm(){

        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(MemberForm form){
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);

        return "redirect:/"; // 회원가입 끝나면 홈으로 이동
    }

    @GetMapping("/members")
    public String memberList(Model model){
        List<Member> members = memberService.findMembers();
        model.addAttribute("members",members);

        return "members/memberList";

    }



}
