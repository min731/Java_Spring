package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

// 인터페이스가 인터페이스를 받을 떄는 extends
// 인터페이스는 다중 상속이 가능함
// 인터페이스가 구현체를 자동으로 만들어 빈 등록해줌
// 가져다 쓰기만 하면 됌
public interface SpringDataJpaMemberRepository extends JpaRepository<Member,Long>,MemberRepository {

    @Override
    Optional<Member> findByName(String name);
}
