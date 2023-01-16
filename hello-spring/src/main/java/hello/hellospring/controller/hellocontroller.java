package hello.hellospring.controller;


import hello.hellospring.HelloSpringApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class hellocontroller {

    @GetMapping("hello")
    public String hello(Model model){

        model.addAttribute("data","hello!!");
        return "hello";
        // viewResolver가 받음

    }

    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam(name = "name") String name, Model model) {
        model.addAttribute("name", name);
        return "hello-template";
    }

    // html 없이 그대로 내려줌
    @GetMapping("hello-string")
    @ResponseBody // html없이 view 해줌
    public String helloString(@RequestParam("name") String name){
        return  "hello " + name;

    }

    @GetMapping("hello-api")
    @ResponseBody // 붙어있으면 hmtl 안쓰고 그대로 넘김
                  // return에 보니 문자가 아니고 객체임
                  // 객체는 json 방식으로 반환함
    
    public Hello helloApi(@RequestParam("name") String name){
        Hello hello = new Hello();
        hello.setName(name);
                      // HttpMessageConverter : 1) JsonConverter 2) StringConverter
        return hello; // viewResolver대신에 HttpMessageConverter가 작동하여 JsonConverter가 json으로 반환됌
                      // Jackson 이나 Gson 가 객체를 json으로 바꿔줌
    }

    static class Hello{
        private String name;

        // Alt + Insert
        // getter,setter 자동으로 만들기
        public String getName() {

            return name;
        }

        public void setName(String name) {

            this.name = name;
        }
    }

}
