package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class JpaMemberRepository implements MemberRepository{

    // build.gradle에 jpa 라이브러리 추가하면
    // spring이 자동으로 EntityManger 생성해줌
    // 생성해준 것을 Injection받기만 하면됌
    // jpa EntityManger가 모든 일을 함

    private final EntityManager em;

    public JpaMemberRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Member save(Member member) {
        em.persist(member); // persist 지속하다
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        Member member = em.find(Member.class,id);
        return Optional.ofNullable(member);
    }

    @Override
    public Optional<Member> findByName(String name) {
        List<Member>result = em.createQuery("selct m Member m where m.name= :name", Member.class)
                .setParameter("name",name)
                .getResultList();
        return result.stream().findAny();
    }

    @Override
    public List<Member> findAll() {

        // jpql 이라는 쿼리 언어
        // 객체를 대상으로 날리는 쿼리 언어
        List<Member>result = em.createQuery("select m from Member m", Member.class).getResultList();
        return result;
    }
}
