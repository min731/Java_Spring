package hello.hellospring;
// main 실행 Application의 패키지의 스프링이 하위 패키지 전부 스캔
// 아니면 Spring이 Component 스캔의 대상아님

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HelloSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(HelloSpringApplication.class, args);
	}

	// main문 실행 시 내장된 tomcat 웹 서버를 실행, spring boot도 같이 올라감
	// 설정 - gradle - gradle projects - 1) Bulid and run using 2) Run tests Using => Gradle에서 intellij 로
	// Intellij에서 바로 띄어버림 (Gradle은 느릴 수 있음)
	// Project Structure java 버전 확인


	// spring.io 에서 document 검색을 할 줄 알아야함
	// resources/static/index.html 만들면 자동으로 welcome 페이지로 인식(정적 페이지)
	// thymeleaf라는 template 엔진을 쓰면

}
