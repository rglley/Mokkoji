package online.mokkoji;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MokkojiApplication {

    public static void main(String[] args) {
        // TODO : 2024.01.29 이거 안하고 run하고 redis 되는지 보기->해도 안됨...^^
//        System.setProperty("spring.devtools.restart.enabled", "false");
        SpringApplication.run(MokkojiApplication.class, args);
    }

}
