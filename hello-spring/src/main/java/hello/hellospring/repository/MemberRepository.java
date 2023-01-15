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
    List<Member> finALl(); // 모든 회원 리스트 반환

}
