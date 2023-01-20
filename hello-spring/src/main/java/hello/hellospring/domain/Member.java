package hello.hellospring.domain;


import jdk.jfr.Name;

import javax.persistence.*;

@Entity
// jpa가 관리하는 것들:entity
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // DB에서 값을 생산해내고 있음
    // 자동으로 생산해내는 것 : identity 전략
    private Long id; // 시스템이 정해줌

    //@Column(name="username")
    // db에 username이라고 했으면 위와 같이 하면됌
    private String name; // 고객이 회원가입할 때 저장

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
