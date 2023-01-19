package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;

// implements 후 모든 메소드 가져오기

//@Repository // 리포지토리는 @Repository 해줌
// 1. 컨트롤러를 통해 외부 요청을 받고
// 2. 서비스에서 비즈니스로직을 만들고
// 3. 리포지토리에서 데이터를 저장
// * 정형화 되어있는 패턴


public class MemoryMemberRepository implements MemberRepository {

    private static Map<Long,Member> store = new HashMap<>();
    private static long sequence = 0L; // 0,1,2.. 키값을 생성!

    @Override
    public Member save(Member member) {
        member.setId(++sequence); // member에 seqence값을 하나 올려줌
        store.put(member.getId(),member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {

        // return store.get(id); id가 null 일 수 있기 때문에 Optional로 감싸줌
        return Optional.ofNullable(store.get(id)); // Map.get(key) 하면 value 값 반환 (Member 반환)
    }

    @Override
    public Optional<Member> findByName(String name) {

        // 람다식
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny();
    }

    @Override
    // Map인데 List로 반환하게 되어있음
    // 현업에서는 loop 돌리기 편하게 List 자주 씀
    public List<Member> findAll() {

        // store.values : Member들임
        return new ArrayList<>(store.values());
    }

    public void clearStore(){

        store.clear(); // store 비워줌
    }

    // DB 바꾸기
    // 1. h2
    // jdbc:h2:tcp://localhost/~/test 로 접속하면 소켓으로 접근하는 것임
    // 여러 곳에서 접근할 수 있음
    // create table member 만듬
    // id 와 name 만듬
    // 자바에서 Long = h2에서 bigint
    // id generated~ : member 만들때 id 넣지않고 만들면 알아서 id 만들어줌


}
