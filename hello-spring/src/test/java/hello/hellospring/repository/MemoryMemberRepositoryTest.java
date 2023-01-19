package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

// 굳이 public 안해도 됌
// class에서 돌리면 모든 @Test 다돌림
// class에서 모든 @Test 돌릴 시 Test 하는 순서 중요함!
// 각 메서드별로 Data를 지워주어야함
// Test는 서로 의존관계없이 설게가 되어야함
// 각 Test는 독립적이여야함
// Test를 먼저 만들고 구현클래스륾 만드는 방법도 있음 (Test주도 개발: TDD)

class MemoryMemberRepositoryTest {
    
    // 실제 사용할 MemoryMemberRepositoy의 메서드들을 테스트하기 위해
    // 객체를 선언함
    MemoryMemberRepository repository = new MemoryMemberRepository();
    

    @AfterEach
    // 각 메서드가 끝날 때마다 동작함
    // 각 메서드가 끝나면 Data 지워주는 메서드
    public void aftetEach(){
        repository.clearStore();

    }


    // JUnit 라이브러리 활용하여 Test 함
    // import org.junit.jupiter.api.Test;
    // Test하고 싶은 메서드만 실행 후 녹색불 확인
    @Test
    public void save(){

        Member member = new Member();
        member.setName("test_정민");
        
        repository.save(member); // 저장시 sequence++로 id 생성

        // get()을 쓰면 Optional 한번까서 꺼냄
        // Optional<Member> result => Memeber result
        Member result = repository.findById(member.getId()).get();

        // System.out.println("result  = " + (result == member));
        // 1. 여기까지 출력해보면 result = true 라고 뜸

        // 2. Assertion 활용
        // Assertions.assertEquals(member,result);
        // 같으면 녹색불

        // 3. 다르면 빨간불
        // Assertions.assertEquals(member,null);

        // 4. import org.assertj.core.api.Assertions;
        Assertions.assertThat(member).isEqualTo(result);
    }

    @Test
    public void findByName(){

        // member 2개 만들기
        Member member1 = new Member();
        member1.setName("test_정민1");
        repository.save(member1);

        // shift + F6 한번에 member1 => member2 로 고쳐줌
        Member member2 = new Member();
        member2.setName("test_정민2");
        repository.save(member2);

        Member result=
        repository.findByName("test_정민1").get();

        Assertions.assertThat(member1).isEqualTo(result);
        // 녹색불 뜨면 ok

        // member2로 바꾸어 보면
        //Member result= repository.findByName("test_정민1").get();

        //Assertions.assertThat(member2).isEqualTo(result);
        // 붉은색

    }

    @Test
    public void findAll(){
        
        Member member3 = new Member();
        member3.setName("test_정민3");
        repository.save(member3);

        Member member4 = new Member();
        member4.setName("test_정민4");
        repository.save(member4);

        List<Member> result = repository.findAll();

         Assertions.assertThat(result.size()).isEqualTo(2);
         // 녹색불

        // Assertions.assertThat(result.size()).isEqualTo(3);
        // 빨간불
    }
}
