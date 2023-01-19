package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {

    Member save(Member member); // member 저장하기

    // Optional java 8 부터 생김
    // Optional id,Name이 Null 일때 감싸서 반환
    Optional<Member> findById(Long id); // id로 회원 찾기

    Optional<Member> findByName(String name);
    List<Member> findAll(); // 모든 회원 리스트 반환

}

// 현재 메모리리포지토리 사용한다고 가정
// 차후 다른 리포지토리로 바꿀 때 다른 코드 안건드릴 수 있는 방법이 있음
